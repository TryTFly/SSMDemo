package com.seckill.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seckill.dao.ManageSeckillDao;
import com.seckill.entity.Page;
import com.seckill.entity.Seckill;
import com.seckill.service.ManageSeckillService;
import com.seckill.util.SeckillUtil;

@Component // 注入到ioc容器中
public class ManageSeckillServiceImpl implements ManageSeckillService {

	@Autowired
	private ManageSeckillDao msDao;

	// 标志
	private boolean flag;

	@Override
	public int updateTime(String seckillId, String startTime, String endTime) {
		// TODO 自动生成的方法存根
		if (startTime != null && !startTime.equals("")) {
			if (endTime != null && !endTime.equals("")) {
				flag = SeckillUtil.timeCheck(startTime, endTime);
				if (flag) {
					Seckill seckill = new Seckill();
					seckill.setSeckillId(Long.valueOf(seckillId));
					seckill.setsTime(Timestamp.valueOf(startTime));
					seckill.seteTime(Timestamp.valueOf(endTime));
					return msDao.updateTime(seckill);
				}
			}
		}
		return 0;
	}

	@Override
	public int deleteOne(String seckillId) {
		long id = Long.valueOf(seckillId);
		return msDao.deleteOne(id);
	}

	@Override
	public int deleteBatch(String ids[]) {
		if (ids != null && ids.length > 0) {
			List<Long> list = new ArrayList<Long>();
			for (int i = 0; i < ids.length; i++) {
				list.add(Long.valueOf(ids[i]));
			}
			return msDao.deleteBatch(list);
		}
		// TODO 自动生成的方法存根
		return 0;

	}

	@Override
	public List<Seckill> getSeckillListByPage(String name, Page page) {
		// TODO 自动生成的方法存根
		Map<String, Object> map = new HashMap<String, Object>();
		int totalNumber = msDao.queryListCount(name);
		page.setTotalNumber(totalNumber);
		Seckill seckill = new Seckill();
		seckill.setName(name);
		map.put("seckill", seckill);
		map.put("page", page);
		return msDao.querySeckillListByPage(map);
	}

	@Override
	public int getListCount(String name) {
		// TODO 自动生成的方法存根
		return msDao.queryListCount(name);
	}

	@Override
	public int insertBatch(String[] name, String[] number, String[] startTime, String endTime[]) {
		// TODO 自动生成的方法存根
		Seckill seckill;
		List<Seckill> list = new ArrayList<Seckill>();
		if (number != null && number.length > 0) {
			if (startTime != null && startTime.length > 0) {
				if (endTime != null && endTime.length > 0) {
					for (int i = 0; i < startTime.length; i++) {
						flag = SeckillUtil.timeCheck(startTime[i], endTime[i]);
						if (flag) {
							seckill = new Seckill();
							seckill.setName(name[i]);
							seckill.setNumber(Integer.parseInt(number[i]));
							seckill.setsTime(Timestamp.valueOf(startTime[i]));
							seckill.seteTime(Timestamp.valueOf(endTime[i]));
							list.add(seckill);
						}
					}
				}
			}
			return msDao.insertBatch(list);
		}
		return 0;
	}

}
