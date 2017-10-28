package com.seckill.service;

import java.util.List;

import com.seckill.dtoentity.Exposer;
import com.seckill.dtoentity.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

/**
 * 业务接口:站在“使用者”的角度设计接口
 * 一，方法定义的粒度
 * 二，参数
 * 三，返回类型(return)
 * @author LOForever
 *
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀记录
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * 查询单个秒杀记录
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * 秒杀开启时输出秒杀接口地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
	 Exposer exportSeckillUrl(long seckillId);
	 
	/**
	 * 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)throws SeckillException,RepeatKillException,SeckillCloseException;
	
	/**
	 * 通过存储过程执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckillByPRC(long seckillId, long userPhone, String md5)throws SeckillException,RepeatKillException,SeckillCloseException;
}
