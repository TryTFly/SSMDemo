CREATE DATABASE ssm CHARACTER SET utf8;

use ssm;

--创建秒杀库存表
CREATE table seckill (
'seckill_id' bigint NOT NUll AUTO_INCREMENT PRIMARY KEY COMMENT '商品id',
'name' varchar(120) NOT NULL COMMENT '商品名称',
'number' int NOT NULL COMMENT '商品数量',
'create_time' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
'start_time' timestamp NOT NULL COMMENT '秒杀开启时间',
'end_time' timestamp NOT NULL COMMENT '秒杀结束时间',


key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)

)AUTO_INCREMENT=1000 COMMENT='秒杀库存表';


--初始化秒杀表
insert into seckill (name,number,start_time,end_time) 
values
('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('300元秒杀mi4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('200元秒杀redminote4',400,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('1元秒杀iphoneX',10,'2015-11-01 00:00:00','2015-11-02 00:00:00');

--秒杀成功明细表
--用户登录认证相关信息

create table success_killed(
'seckill_id' bigint NOT NUll COMMENT '秒杀商品id',
'user_phone' bigint NOT NUll COMMENT '用户手机号',
'state' tinyint NOT NULL DEFAULT -1 COMMENT '状态表示:-1:无效  0:成功  1:已付款',
'create_time' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
primary key(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)COMMENT='秒杀成功明细表';

--连接数据库控制台
mysql -uroot -p -P3306 -h127.0.0.1

--以下语句Mysql5.5版本可用
CREATE table if not EXISTS seckill (
seckill_id bigint NOT NUll AUTO_INCREMENT PRIMARY KEY COMMENT '商品id',
name varchar(120) NOT NULL COMMENT '商品名称',
number int NOT NULL COMMENT '商品数量',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
start_time timestamp NOT NULL COMMENT '秒杀开启时间',
end_time timestamp NOT NULL COMMENT '秒杀结束时间',


key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)

)AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';



insert into seckill (name,number,start_time,end_time) 
values
('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('300元秒杀mi4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('200元秒杀redminote4',400,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('1元秒杀iphoneX',10,'2015-11-01 00:00:00','2015-11-02 00:00:00');



create table if not EXISTS success_killed(
seckill_id bigint NOT NUll COMMENT '秒杀商品id',
user_phone bigint NOT NUll COMMENT '用户手机号',
state tinyint NOT NULL DEFAULT -1 COMMENT '状态表示:-1:无效  0:成功  1:已付款',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
primary key(seckill_id,user_phone),/*联合主键*/
key idx_create_time(create_time)
)DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';