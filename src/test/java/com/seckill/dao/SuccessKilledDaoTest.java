package com.seckill.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.entity.SuccessKilled;

/**
 * ����spring��junit����,junit����ʱ����springIOC����
 * @author LOForever
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//junit spring�����ļ�
@ContextConfiguration({"classpath:spring/spring-mybatis.xml"})
public class SuccessKilledDaoTest {

	//����ע��Daoʵ����
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
