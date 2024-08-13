show databases;

use demo01;

show tables;

create database demo02;

create database if not exists demo;

#使用某个表
use demo;

#comment 是注释
#创建一个表
create table if not exists demo_user
(
    id       int primary key auto_increment comment '表头单元格之 ID ， 类型： Integer ， 主键 ， 唯一标识 ， 自动递增',
    username char(20) not null comment '用户名 ，类型 char 在创建时就定义了长度 20 ',
    name     varchar(20) comment '真实姓名 ,类型 varchar 在添加时才定义长度，最大 20 ',
    age      int      not null comment '年龄',
    gender   char(1) comment '男，女'
);

#在某个表内添加内容
insert into demo_user(username, name, age, gender) value ('Serene_Dream', 'Serene_Dream', 20, '男');

#查看表结构
desc demo_user;

#查看表内所有内容
select *
from demo_user;
-- select * from article;

#查看建表语句
show create table demo_user;

#修改列表属性
alter table demo_user
    modify name varchar(15) not null;

#删除表单
drop table demo_user;

#创建表单
#增加约束： unique 让其唯一
create table demo
(
    id       int primary key not null,
    username char(20)        not null unique comment '用户名 ，类型 char 在创建时就定义了长度 20 ',
    name     varchar(20)     not null default 'SERENE_DREAM' comment '真实姓名 ,类型 varchar 在添加时才定义长度，最大 20 ',
    age      int             not null default '20' comment '年龄',
    gender   char(1)         not null default '男' comment '男，女'
);

#增加约束
alter table demo
    modify name varchar(20) unique not null;

#查看表的设置
desc demo;

#删除表单
drop table 'article';

#创建表单
create table article
(
    id         int primary key not null,
    url        varchar(100)    not null unique,
    create_at  varchar(100)    not null,
    updated_at varchar(100)    not null
);






