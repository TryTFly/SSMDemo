package com.seckill.exception;

/**
 * 重复秒杀异常(运行期异常)
 * @author LOForever
 *
 */
public class RepeatKillException extends SeckillException {

	public RepeatKillException(String message) {
		super(message);
		// TODO 自动生成的构造函数存根
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
		// TODO 自动生成的构造函数存根
	}	
	
}
