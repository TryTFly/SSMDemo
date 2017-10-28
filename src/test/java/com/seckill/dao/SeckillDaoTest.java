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
 * ����spring��junit����,junit����ʱ����springIOC����
 * 
 * @author LOForever
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// junit spring�����ļ�
@ContextConfiguration({ "classpath:spring/spring-mybatis.xml" })
public class SeckillDaoTest {

	// ע��Daoʵ��������
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
