package com.seckill.dtoentity;

import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStateEnum;

/**
 * ��װ��ɱִ�к�Ľ��
 * @author LOForever
 *
 */
public class SeckillExecution {

	private long seckillId;
	
	//��ɱִ�н��״̬
	private int state;
	
	//״̬��Ϣ
	private String stateInfo;
	
	//��ɱ�ɹ�����
	private SuccessKilled successKilled;

	public SeckillExecution(long seckillId, SeckillStateEnum sateEnum, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		state = sateEnum.getState();
		stateInfo=sateEnum.getStateInfo();
		this.successKilled = successKilled;
	}

	
	
	public SeckillExecution(long seckillId,  SeckillStateEnum sateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = sateEnum.getState();
		stateInfo=sateEnum.getStateInfo();
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", stateInfo=" + stateInfo
				+ ", successKilled=" + successKilled + "]";
	}
	
	
}
