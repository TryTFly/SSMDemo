package com.seckill.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.seckill.entity.Seckill;

/**
 * 工具类
 * 
 * @author LOForever
 *
 */
public class SeckillUtil {

	public static Seckill seckillDateFormat(String startTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date startDate = format.parse(startTime);
			Date endDate = format.parse(endTime);
			String fsTime = format.format(startDate);
			String feTime = format.format(endDate);
			Timestamp sTime = Timestamp.valueOf(fsTime);
			Timestamp eTime = Timestamp.valueOf(feTime);
			Seckill seckill = new Seckill();
			seckill.setsTime(sTime);
			seckill.seteTime(eTime);
			return seckill;
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	public static boolean timeCheck(String startTime, String endTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			format.parse(startTime);
			format.parse(endTime);
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
			return false;
		}
		return true;

	}

}
