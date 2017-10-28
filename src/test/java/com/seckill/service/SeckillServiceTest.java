package com.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dtoentity.Exposer;
import com.seckill.dtoentity.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.service.SeckillService;

/**
 * 配置spring和junit整合,junit启动时加载springIOC容器
 * 
 * @author LOForever
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
// junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-mybatis.xml"})
public class SeckillServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() throws Exception{
		List<Seckill> list=seckillService.getSeckillList();
		logger.info("list={}",list);
	}
	
	@Test
	public void testGetById() throws Exception{
		long id=1002;
		Seckill seckill=seckillService.getById(id);
		logger.info("seckill={}",seckill);
	}
	
	
	//测试秒杀逻辑，注意可重复执行（集成测试的完整性）
	@Test
	public void testSeckillLogic() throws Exception{
		long id=1000;
		Exposer exposer=seckillService.exportSeckillUrl(id);
		
		if(exposer.isExposed())
		{
			logger.info("exposer={}",exposer);
			long phone=18012764261l;
			String md5=exposer.getMd5();
			try {
				SeckillExecution seckillExecution= seckillService.executeSeckill(id, phone, md5);
				logger.info("seckillExecution={}",seckillExecution);
			} catch (RepeatKillException e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			}catch (SeckillCloseException e) {
				// TODO: handle exception
				logger.error(e.getMessage());
			}
		}else {
			//秒杀未开启
			logger.warn("exposer={}",exposer);
		}
	}
	
	@Test
	public void testExecuteSeckillByPRC() throws Exception{
		long seckillId=1003;
		long phone=13112390828l;
		Exposer exposer= seckillService.exportSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5=exposer.getMd5();
			SeckillExecution execution= seckillService.executeSeckillByPRC(seckillId, phone, md5);
			logger.info(execution.getStateInfo());
		}
	}
	
}
