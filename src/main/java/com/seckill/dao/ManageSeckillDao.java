package com.seckill.dao;

import java.util.List;
import java.util.Map;

import com.seckill.entity.Seckill;

/**
 * 秒杀数据表DAO(administrator)
 * @author LOForever
 *
 */
public interface ManageSeckillDao {

	/**
	 * 更改秒杀时间
	 * @param seckill
	 * @return
	 */
	int updateTime(Seckill seckill);
	
	/**
	 * 批量添加秒杀商品信息
	 * @param seckillList
	 * @return
	 */
	int insertBatch(List<Seckill> seckillList);
	
	/**
	 * 
	 * 单条删除
	 */
	int deleteOne(long seckillId);
	
	/**
	 * 批量删除
	 * @param list
	 */
	int deleteBatch(List<Long> list);
	
	/**
	 * 分页查询
	 */
	List<Seckill> querySeckillListByPage(Map<String,Object> map);
	
	/**
	 * 数量查询
	 */
	int queryListCount(String name);
	
}
