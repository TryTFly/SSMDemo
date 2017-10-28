package com.seckill.dao;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
public class SeckillDaoTest {

	// 注入Dao实现类依赖
	@Resource
	private SeckillDao seckillDao;

	

	@Test
	public void testReduceNumber() {
		Date killTime = new Date();
		int updateNumber = seckillDao.reduceNumber(1000l, killTime);
		System.out.println(updateNumber);
	}

	@Test
	public void testQueryById() {
		long id = 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.toString());
	}

	@Test
	public void testQueryAll() {
		List<Seckill> sList = seckillDao.queryAll(0, 50);
		for (Seckill seckill : sList) {
			System.out.println(seckill.toString());
		}

	}

}
