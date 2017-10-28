package com.seckill.dao;

import java.util.List;
import java.util.Map;

import com.seckill.entity.Seckill;

/**
 * ��ɱ���ݱ�DAO(administrator)
 * @author LOForever
 *
 */
public interface ManageSeckillDao {

	/**
	 * ������ɱʱ��
	 * @param seckill
	 * @return
	 */
	int updateTime(Seckill seckill);
	
	/**
	 * ���������ɱ��Ʒ��Ϣ
	 * @param seckillList
	 * @return
	 */
	int insertBatch(List<Seckill> seckillList);
	
	/**
	 * 
	 * ����ɾ��
	 */
	int deleteOne(long seckillId);
	
	/**
	 * ����ɾ��
	 * @param list
	 */
	int deleteBatch(List<Long> list);
	
	/**
	 * ��ҳ��ѯ
	 */
	List<Seckill> querySeckillListByPage(Map<String,Object> map);
	
	/**
	 * ������ѯ
	 */
	int queryListCount(String name);
	
}
