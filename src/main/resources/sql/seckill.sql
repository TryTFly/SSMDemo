--秒杀执行存储过程
DELIMITER //
--定义存储过程
--DECLARE定义变量
--row_count():返回上一条修改类型sql(delete,insert,update)的影响行数
--row_count(): 0-->未修改数据;>0-->修改的行数;<0-->sql错误/未执行sql
CREATE PROCEDURE execute_seckill
(in v_seckill_id bigint,in v_phone bigint,
in v_kill_time timestamp,out r_result int)
BEGIN
	DECLARE insert_count int DEFAULT 0;
	START TRANSACTION;
	insert ignore into success_killed (seckill_id,user_phone,create_time) 
	values (v_seckill_id,v_phone,v_kill_time);
	select row_count() into insert_count;
	IF(insert_count=0) THEN
		ROLLBACK;
		set r_result=-1;
	ELSEIF(insert_count<0)THEN
		set r_result=-2;
	ELSE
		update seckill
		set number=number-1
	where seckill_id=v_seckill_id
		and end_time>v_kill_time
		and start_time<v_kill_time
		and number>0;
	select row_count() into insert_count;
	IF(insert_count=0)THEN
		ROLLBACK;
		set r_result=0;
	ELSEIF(insert_count<0)THEN
		set r_result=-2;
	ELSE
		COMMIT;
		set r_result=1;
	END IF;
	END iF;
END 
//


create procedure queryAll(in v_id int)
BEGIN
	IF(v_id>0)THEN
SELECT seckill_id,name,number,create_time,start_time,end_time
FROM seckill where seckill_id=v_id;
ELSE
SELECT seckill_id,name,number,create_time,start_time,end_time
FROM seckill;
END if;
END
//

--存储过程创建完成

DELIMITER ;

--定义变量(cmd中)
set @r_result=-3;

--执行存储过程
call execute_seckill(1004,13211328937,now(),@r_result);

--获取结果
select @r_result;

--1.存储过程优化:事物行级锁持有的时间
--2.不要过度依赖存储过程
--3.简单的逻辑可以应用存储过程
--4.qps:一个秒杀单6000/qps