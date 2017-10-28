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

	// ע������
	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	// md5��ֵ�ַ��������ڻ���MD5;
	private final String slat = "dsdasdgfdgfdgdfdasqwej2131DADAsadadasda";

	public List<Seckill> getSeckillList() {
		// TODO �Զ����ɵķ������
		return seckillDao.queryAll(0, 5);
	}

	public Seckill getById(long seckillId) {
		// TODO �Զ����ɵķ������
		return seckillDao.queryById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		// TODO �Զ����ɵķ������
		Seckill seckill = seckillDao.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// ϵͳ��ǰʱ��
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
		}
		// ת���ض��ַ�������
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
	 * ʹ��ע������񷽷����ŵ�: 1�������ŶӴ��һ�¾�������ȷ��ע���﷽���ı�̷��
	 * 2����֤���񷽷���ִ��ʱ�価���̣ܶ���Ҫ�����������������RPC/HTTP������߰��뵽���񷽷��ⲿ
	 * 3���������еķ�������Ҫ������ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		// TODO �Զ����ɵķ������
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}

		// ִ����ɱ�߼��������+��¼�������
		Date nowTime = new Date();
		try {
			// ��¼�������
			int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			// ��������:seckillId,userPhone
			if (insertCount <= 0) {
				// �ظ���ɱ
				throw new RepeatKillException("seckill repeated");
			} else {
				// �����
				int updateNumber = seckillDao.reduceNumber(seckillId, nowTime);
				if (updateNumber <= 0) {
					// û�и��¼�¼
					throw new SeckillCloseException("seckill is closed");
				} else {
					// ��ɱ�ɹ�
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
			// �������쳣ת��Ϊ�������쳣
			throw new SeckillException("seckill inner error" + e.getMessage());
		}
	}

	@Override
	public SeckillExecution executeSeckillByPRC(long seckillId, long userPhone, String md5) {
		// TODO �Զ����ɵķ������
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		// ִ����ɱ�߼��������+��¼�������
		Date nowTime = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seckillId", seckillId);
		map.put("phone", userPhone);
		map.put("killTime", nowTime);
		map.put("result", null);
		// ִ�д洢����,result������
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
