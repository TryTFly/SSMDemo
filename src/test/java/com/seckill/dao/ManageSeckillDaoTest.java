package com.seckill.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.Page;
import com.seckill.entity.Seckill;
import com.seckill.util.SeckillUtil;

/**
 * 配置spring和junit整合,junit启动时加载springIOC容器
 * 
 * @author LOForever
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-mybatis.xml" })
public class ManageSeckillDaoTest {

	// 注入Dao实现类依赖
	@Resource
	private ManageSeckillDao manageSeckillDao;

	@Test
	public void testUpdateTime() {
		Seckill seckill = new Seckill();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = format.format(date);
		Timestamp startTime = Timestamp.valueOf(nowTime);
		Timestamp endTime = Timestamp.valueOf("2017-10-25 00:00:00");
		seckill.setsTime(startTime);
		seckill.seteTime(endTime);
		seckill.setSeckillId(1000);
		int uNumber = manageSeckillDao.updateTime(seckill);
		System.out.println(uNumber);
	}

	@Test
	public void testDeleteOne() {
		long id = 1009;
		manageSeckillDao.deleteOne(id);
	}

	@Test
	public void testDeleteBatch() {

		List<Long> list = new ArrayList<Long>();
		list.add(1008l);
		list.add(1007l);
		list.add(1006l);
		list.add(1005l);

		manageSeckillDao.deleteBatch(list);
	}

	@Test
	public void testQuerySeckillListByPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		int totalNumber = manageSeckillDao.queryListCount("Red");
		Page page = new Page();
		page.setCurrentPage(1);
		page.setTotalNumber(totalNumber);
		Seckill seckill = new Seckill();
		seckill.setName("Red");
		map.put("seckill", seckill);
		map.put("page", page);
		List<Seckill> list = manageSeckillDao.querySeckillListByPage(map);
		for (Seckill seckill2 : list) {
			System.out.println(seckill2);
		}
	}

	@Test
	public void testQueryListCount() {
		int totalNumber = manageSeckillDao.queryListCount("ip");
		System.out.println(totalNumber);
	}

	@Test
	public void testInsertBatch() {
		Seckill seckillForTime=SeckillUtil.seckillDateFormat("2017-10-21 00:00:00", "2017-10-31 00:00:00");
		List<Seckill> list = new ArrayList<Seckill>();
		Seckill seckill = new Seckill();
		seckill.setName("800元秒杀samsung27寸2k显示器");
		seckill.setNumber(50);
		seckill.setsTime(seckillForTime.getsTime());
		seckill.seteTime(seckillForTime.geteTime());
		list.add(seckill);
		System.out.println(manageSeckillDao.insertBatch(list));
	}

}
