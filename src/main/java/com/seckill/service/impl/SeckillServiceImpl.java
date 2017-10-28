package com.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dtoentity.Exposer;
import com.seckill.dtoentity.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

//@Component @Service @DAO @Controller
@Component
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入依赖
	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	// md5盐值字符串，用于混淆MD5;
	private final String slat = "dsdasdgfdgfdgdfdasqwej2131DADAsadadasda";

	public List<Seckill> getSeckillList() {
		// TODO 自动生成的方法存根
		return seckillDao.queryAll(0, 5);
	}

	public Seckill getById(long seckillId) {
		// TODO 自动生成的方法存根
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// TODO 自动生成的方法存根
		Seckill seckill = seckillDao.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		// 转化特定字符串过程
		String md5 = getMD5(seckillId);

		return new Exposer(true, md5, seckillId);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Transactional
	/**
	 * 使用注解控事务方法的优点: 1，开发团队达成一致决定，明确标注事物方法的编程风格
	 * 2，保证事务方法的执行时间尽可能短，不要穿插其他的网络操作RPC/HTTP请求或者剥离到事务方法外部
	 * 3，不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		// TODO 自动生成的方法存根
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}

		// 执行秒杀逻辑：减库存+记录购买操作
		Date nowTime = new Date();
		try {
			// 记录购买操作
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			// 联合主键:seckillId,userPhone
			if (insertCount <= 0) {
				// 重复秒杀
				throw new RepeatKillException("seckill repeated");
			} else {
				// 减库存
				int updateNumber = seckillDao.reduceNumber(seckillId, nowTime);
				if (updateNumber <= 0) {
					// 没有更新记录
					throw new SeckillCloseException("seckill is closed");
				} else {
					// 秒杀成功
					SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e) {
			// TODO: handle exception
			throw e;
		} catch (RepeatKillException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			// 编译期异常转化为运行期异常
			throw new SeckillException("seckill inner error" + e.getMessage());
		}
	}

	@Override
	public SeckillExecution executeSeckillByPRC(long seckillId, long userPhone, String md5) {
		// TODO 自动生成的方法存根
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		// 执行秒杀逻辑：减库存+记录购买操作
		Date nowTime = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seckillId", seckillId);
		map.put("phone", userPhone);
		map.put("killTime", nowTime);
		map.put("result", null);
		// 执行存储过程,result被复制
		try {
			seckillDao.killByPRC(map);
			//
			int result = MapUtils.getInteger(map, "result", -2);
			if (result == 1) {
				SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
			} else {
				return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
		}
	}

}
