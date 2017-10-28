package com.seckill.service;

import java.util.List;
import java.util.Map;

import com.seckill.entity.Page;
import com.seckill.entity.Seckill;

/**
 * seckill秒杀数据表管理
 * @author LOForever
 *
 */
public interface ManageSeckillService {

	/**
	 * 更改秒杀时间
	 * @param seckill
	 * @return
	 */
	int updateTime(String seckillId,String startTime,String endTime);
	
	/**
	 * 批量插入秒杀商品
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	int insertBatch(String []name,String number[],String []startTime,String[] endTime);
	
	/**
	 * 
	 * 单条删除
	 */
	int deleteOne(String seckillId);
	
	/**
	 * 批量删除
	 * @param list
	 */
	int deleteBatch(String []ids);
	
	/**
	 * 分页查询
	 */
	List<Seckill> getSeckillListByPage(String name,Page page);
	
	/**
	 * 数量查询
	 */
	int getListCount(String name);
	
}
