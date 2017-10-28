package com.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.Page;
import com.seckill.entity.Seckill;

/**
 * 配置spring和junit整合,junit启动时加载springIOC容器
 * 
 * @author LOForever
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-mybatis.xml" })
public class ManageSeckillServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManageSeckillService msService;

	@Test
	public void testUpdateTime() {
		String seckillId = "1001";
		String startTime = "2017-10-21 00:00:00";
		String endTime = "2017-11-1 08:00:00";
		System.out.println(msService.updateTime(seckillId, startTime, endTime));
	}

	@Test
	public void testInsertBatch() {
		String name[] = { "700元秒杀SamsungS8+" };
		String number[] = { "150" };
		String startTime[] = { "2017-10-23 00:00:00" };
		String endTime[] = { "2017-10-31 00:00:00" };
		int count = msService.insertBatch(name, number, startTime, endTime);
		logger.info("number={}", count);
	}

	@Test
	public void testDeleteOne() {

	}

	@Test
	public void testDeleteBatch() {

	}

	@Test
	public void testGetSeckillListByPage() {
		String name = "";
		String currentPage = "1";
		Page page = new Page();
		page.setCurrentPage(Integer.parseInt(currentPage));
		List<Seckill> list = msService.getSeckillListByPage(name, page);
		/*
		 * for (Seckill seckill : list) { System.out.println(seckill); }
		 */
		logger.info("list={}", list);
	}

	@Test
	public void testGetListCount() {

	}

}
