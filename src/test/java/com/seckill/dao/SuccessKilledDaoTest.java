package com.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.SuccessKilled;

/**
 * 配置spring和junit整合,junit启动时加载springIOC容器
 * @author LOForever
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class SuccessKilledDaoTest {

	//依赖注入Dao实现类
	@Resource
	private SuccessKilledDao successKilledDao;
	
	@Test
	public void testInsertSuccessKilled(){
		int insertNumber= successKilledDao.insertSuccessKilled(1002l, 15319920103l);
		System.out.println(insertNumber);
	}
	
	@Test
	public void testQueryByIdWithSeckill(){
		SuccessKilled sk= successKilledDao.queryByIdWithSeckill(1002l, 15319920103l);
		System.out.println(sk.toString());
		System.out.println(sk.getSeckill().toString());
	}
}
