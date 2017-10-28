package com.seckill.service;

import java.util.List;

import com.seckill.dtoentity.Exposer;
import com.seckill.dtoentity.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

/**
 * ҵ��ӿ�:վ�ڡ�ʹ���ߡ��ĽǶ���ƽӿ�
 * һ���������������
 * ��������
 * ������������(return)
 * @author LOForever
 *
 */
public interface SeckillService {

	/**
	 * ��ѯ������ɱ��¼
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ��ѯ������ɱ��¼
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * ��ɱ����ʱ�����ɱ�ӿڵ�ַ
	 * �������ϵͳʱ�����ɱʱ��
	 * @param seckillId
	 */
	 Exposer exportSeckillUrl(long seckillId);
	 
	/**
	 * ִ����ɱ����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)throws SeckillException,RepeatKillException,SeckillCloseException;
	
	/**
	 * ͨ���洢����ִ����ɱ����
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckillByPRC(long seckillId, long userPhone, String md5)throws SeckillException,RepeatKillException,SeckillCloseException;
}
