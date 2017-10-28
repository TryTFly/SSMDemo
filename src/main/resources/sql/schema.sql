CREATE DATABASE ssm CHARACTER SET utf8;

use ssm;

--������ɱ����
CREATE table seckill (
'seckill_id' bigint NOT NUll AUTO_INCREMENT PRIMARY KEY COMMENT '��Ʒid',
'name' varchar(120) NOT NULL COMMENT '��Ʒ����',
'number' int NOT NULL COMMENT '��Ʒ����',
'create_time' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
'start_time' timestamp NOT NULL COMMENT '��ɱ����ʱ��',
'end_time' timestamp NOT NULL COMMENT '��ɱ����ʱ��',


key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)

)AUTO_INCREMENT=1000 COMMENT='��ɱ����';


--��ʼ����ɱ��
insert into seckill (name,number,start_time,end_time) 
values
('1000Ԫ��ɱiphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('500Ԫ��ɱipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('300Ԫ��ɱmi4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('200Ԫ��ɱredminote4',400,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('1Ԫ��ɱiphoneX',10,'2015-11-01 00:00:00','2015-11-02 00:00:00');

--��ɱ�ɹ���ϸ��
--�û���¼��֤�����Ϣ

create table success_killed(
'seckill_id' bigint NOT NUll COMMENT '��ɱ��Ʒid',
'user_phone' bigint NOT NUll COMMENT '�û��ֻ���',
'state' tinyint NOT NULL DEFAULT -1 COMMENT '״̬��ʾ:-1:��Ч  0:�ɹ�  1:�Ѹ���',
'create_time' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
primary key(seckill_id,user_phone),/*��������*/
key idx_create_time(create_time)
)COMMENT='��ɱ�ɹ���ϸ��';

--�������ݿ����̨
mysql -uroot -p -P3306 -h127.0.0.1

--�������Mysql5.5�汾����
CREATE table if not EXISTS seckill (
seckill_id bigint NOT NUll AUTO_INCREMENT PRIMARY KEY COMMENT '��Ʒid',
name varchar(120) NOT NULL COMMENT '��Ʒ����',
number int NOT NULL COMMENT '��Ʒ����',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
start_time timestamp NOT NULL COMMENT '��ɱ����ʱ��',
end_time timestamp NOT NULL COMMENT '��ɱ����ʱ��',


key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)

)AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='��ɱ����';



insert into seckill (name,number,start_time,end_time) 
values
('1000Ԫ��ɱiphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('500Ԫ��ɱipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('300Ԫ��ɱmi4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('200Ԫ��ɱredminote4',400,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('1Ԫ��ɱiphoneX',10,'2015-11-01 00:00:00','2015-11-02 00:00:00');



create table if not EXISTS success_killed(
seckill_id bigint NOT NUll COMMENT '��ɱ��Ʒid',
user_phone bigint NOT NUll COMMENT '�û��ֻ���',
state tinyint NOT NULL DEFAULT -1 COMMENT '״̬��ʾ:-1:��Ч  0:�ɹ�  1:�Ѹ���',
create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
primary key(seckill_id,user_phone),/*��������*/
key idx_create_time(create_time)
)DEFAULT CHARSET=utf8 COMMENT='��ɱ�ɹ���ϸ��';