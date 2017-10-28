package com.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.Seckill;

/**
 * ��ɱ���ݱ�DAO(�û�)
 * @author LOForever
 *
 */
public interface SeckillDao{
	
	/**
	 * �����
	 * @param seckillId
	 * @param killTime
	 * @return
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
	
	/**
	 * ����id��ѯ��������
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * ��ҳ��ѯ
	 * @param offet
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
	
	/**
	 * ʹ�ô洢����ִ����ɱ
	 */
	void killByPRC(Map<String,Object> paramMap);
	
}
