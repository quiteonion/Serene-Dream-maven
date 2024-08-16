-- 一对多：班级 与 学生
--        博客 与 评论
--        ...

show databases;

create database demo03;

create database if not exists demo03;

use demo03;

show tables;

-- 班级
create table if not exists class
(
    id          int primary key auto_increment comment '主键ID',
    name        varchar(50) not null unique comment '班级名',
    description varchar(512) comment '班级描述',
    created_at  datetime    not null comment '创建时间',
    updated_at  datetime    not null comment '修改时间'
    ) comment '班级';

-- alter table class modify updated_at datetime null comment '修改时间';

-- 添加班级数据
insert into class (id, name, created_at, updated_at)
values (1, 'JavaSE', now(), now()),
       (2, 'HTML+CSS+JS', now(), now()),
       (3, 'MySQL', now(), now());

-- 复习：删除/清空表数据
delete from class;
truncate table class;

-- 注释复习：
# 单行注释方式一, 井号开头
-- 单行注释方式二，第二个破折号后面至少要又一个空格或者制表符
/*
 多行注释
 */

-- 学生
create table if not exists students
(
    id          int primary key auto_increment comment '主键ID',
    username    varchar(20)                  not null comment '姓名',
    password    char(8) default '12345678'   null comment '密码',
    gender      tinyint unsigned             not null comment '性别, 1=男, 2=女，0=未知',
    avatar      varchar(300)                 null comment '头像 url',
    job         tinyint unsigned             null comment '角色/职务。1=班长, 2=助教, 3=组长',
    enrolled_at date                         null comment '注册/入学日期',
    created_at  datetime                     not null comment '创建时间',
    updated_at  datetime                     null comment '修改时间',
    class_id     int comment '对应班级表的ID'
    ) comment '学生表';

select * from class where id=2;




-- truncate table students;

-- 添加学生数据
INSERT INTO students(id, username, password, gender, avatar, job, enrolled_at, created_at, updated_at, class_id)
VALUES (1, '张xx', '123xyz98', 1, '1.jpg', 3, '2024-01-01', now(), now(), 1),
       (2, '林xx', '123xyz98', 1, '2.jpg', 0, '2024-02-03', now(), now(), 1),
       (3, '廖xx', '123xyz98', 1, '3.jpg', 0, '2024-02-11', now(), now(), 1),
       (4, '郑xx', '123xyz88', 1, '4.jpg', 3, '2024-02-21', now(), now(), 1),
       (5, '林yy', '123xyz98', 1, '5.jpg', 0, '2024-02-05', now(), now(), 1),
       (6, '林zz', '123xyz98', 1, '6.jpg', 3, '2024-03-05', now(), now(), 1),
       (7, '林aa', '123xyz98', 1, '7.jpg', 0, '2024-02-01', now(), now(), 1),
       (8, '李xx', '123xyz98', 1, '8.jpg', 0, '2024-01-09', now(), now(), 1),
       (9, '林cc', '123xyz98', 1, '9.jpg', 0, '2024-03-11', now(), now(), 1),
       (10, '李xx', '123xyz98', 1, '10.jpg', 3, '2024-01-05', now(), now(), 1),
       (11, '杨xx', '123xyz98', 1, '11.jpg', 0, '2024-02-01', now(), now(), 1),
       (12, '王xx', '123xyz89', 1, '12.jpg', 0, '2024-01-18', now(), now(), 1),
       (13, '尧xx', '123xyz98', 1, '13.jpg', 0, '2024-03-01', now(), now(), 1),
       (14, '朱xx', '123xyz98', 1, '14.jpg', 0, '2024-01-11', now(), now(), 1),
       (15, '吴BB', '123xyz98', 1, '15.jpg', 0, '2024-02-05', now(), now(), 1),
       (16, '张JJ', '123xyz98', 1, '16.jpg', 0, '2024-05-01', now(), now(), 1),
       (17, '叶X', '123xyz68', 2, '17.jpg', 2, '2024-06-01', now(), now(), 2),
       (18, '赵YY', '123xyz98', 2, '18.jpg', 1, '2024-06-11', now(), now(), 2),
       (19, '钱ZZ', '123xyz98', 2, '19.jpg', 2, '2024-06-12', now(), now(), 3),
       (20, 'BB孙', '123xyz98', 1, '20.jpg', null, '2024-06-13', now(), now(), 3)
;

-- 复习 select，查找出班级id=3的学生信息
select * from students where class_id=3;

-- 复习 insert，给ID=3的班级增加一名新学生
insert into students(username, gender, created_at, class_id) value ('王CC', 2, now(), 3);

-- 给ID=4的班级增加一名新学生
insert into students(username, gender, created_at, class_id) value ('王CC', 2, now(), 4);

-- 复习 delete，删除班级ID=4的所有学生
delete from students where class_id=4;

-- 增加外键约束（如果 students 表的 class_id 字段类型是 `int unsigned`，class 表的 id 字段类型是 `int`，执行这句会报错，见下一段注释的语句）
alter table students
    add constraint students_class_fk
        foreign key (`class_id`) references class (`id`);

alter table students
    add constraint students_class_fk
        foreign key ('class_id') references class ('id');

-- 再次操作：给ID=4的班级增加一名新学生（备注：class_id=4的班级并不存在）
insert into students(username, gender, created_at, class_id) value ('王CC', 2, now(), 4);


select * from students where class_id = 1;
select * from class where id = 1;


-- 尝试把 class 表中 id = 2 的班级删除（备注：students 表中，有学生是属于 class_id=2）
delete from class where id=2;
-- 由于有外键约束 foreign key constraint，需要确保 class 表中 id=2 班级没有被其他表引用
-- 要么先删除 students 表中 class_id=2 的学生
delete from students where class_id=2;
-- 或者把 students 表中 class_id=2 的学生移到其他班级去
update students set class_id=3 where class_id=2;
-- 确保现在的 students 表中没有学生是 class_id=2
select count(*) from students where class_id=2;
-- 再次尝试把 class 表中 id = 2 的班级删除（备注：此时 students 表中，没有学生是属于 class_id=2）
delete from class where id=2;


# 增加外键约束语句的报错：变量类型匹配的问题
-- alter table students modify class_id int null comment '对应班级表的ID';
#
-- class 表增加一个 bigint 类型的 other_id
alter table class add other_id bigint null;
-- students 表增加一个 int 类型的 other_class_id
alter table students add other_class_id int null;
-- 再次演示增加外键约
# [HY000][3780] Referencing column 'other_class_id' and referenced column 'other_id' in foreign key constraint 'students_class_fk_other' are incompatible.
alter table students
    add constraint students_class_fk_other
        foreign key (`other_class_id`) references class (`other_id`);

-- 解决办法：修改 class 表 other_id 的数据类类型，改成跟 students 表的 other_class_id 一致
alter table class modify other_id int null;
-- 再次执行“增加外键约束”的语句
alter table students
    add constraint students_class_fk_other
        foreign key (`other_class_id`) references class (`other_id`);
-- 被参考的那个字段，需要有索引 index
-- [HY000][1822] Failed to add the foreign key constraint. Missing index for constraint 'students_class_fk_other' in the referenced table 'class'
create index class_other_id_index on class (other_id);
-- 再次执行“增加外键约束”的语句
alter table students
    add constraint students_class_fk_other
        foreign key (`other_class_id`) references class (`other_id`);


-- 【上面】动手练习时间 part2

-- 删除上面为了演示额外增加的外键约束
alter table students drop foreign key students_class_fk_other;
-- 删除上面为了演示额外增加字段：class.other_id, students.other_class_id
alter table class drop column other_id;
alter table students drop column other_class_id;


-- 一对一: 学生 与 学生证

-- 学生证
create table student_id_card
(
    id         int primary key auto_increment comment 'ID',
    number     varchar(10)  not null comment '学号',
    school     varchar(50)  not null comment '大学',
    department varchar(100) not null comment '院系',
    created_at datetime     not null comment '领证时间',
    student_id int          not null unique comment '学生ID',
    constraint fk_student_id foreign key (student_id) references students (id)
) comment '学生证';

insert into student_id_card(id, number, school, department, created_at, student_id)
values (1, '188996600', '菜鸡大学', '计科', '2021-09-01', 4),
       (2, '188996601', '菜鸡大学', '电子', '2020-09-01', 5),
       (3, '188996602', '菜鸡大学', '物理', '2022-09-01', 6);

-- 把学生证分配给一个不存在的学生，由于有外键约束，会报错
-- [23000][1452] Cannot add or update a child row: a foreign key constraint fails (`demo03`.`student_id_card`, CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`))
insert into student_id_card
    value (null, '188996603', 'NB大学', 'Java', '2023-09-01', 66);

-- 体现一对一的关系。把 student_id 设置成 unique
insert into student_id_card
    value (null, '188996605', 'NB大学2', 'Go', '2023-09-01', 4);

-- 给 number 字段增加唯一约束
alter table student_id_card
    add constraint uk_number
        unique (number);

select * from student_id_card;

alter table course
    add constraint course_students_course_bool_fk
        foreign key (`key`) references students (id);

-- 【上面】动手练习时间 part3


-- 表之间的关系
-- 没关系、一对一、一对多（多对一）、多对多

-- 多对多：博客 与 标签
--        学生 与 课程
--        用户、角色、权限
--        ...

# 课程表
create table course
(
    id   int auto_increment primary key comment '主键ID',
    name varchar(20) comment '课程名称'
) comment '课程表';
# 课程数据
insert into course (name) values ('Java'), ('物理'), ('数学') , ('MySQL');



# 学生和课程关联表
create table student_course
(
    id         int auto_increment comment '主键' primary key,
    student_id int not null comment '学生ID',
    course_id  int not null comment '课程ID',

    constraint fk_student_id_ foreign key (student_id) references students (id),
    constraint fk_course_id foreign key (course_id) references course (id)
) comment '学生与课程关联的中间表';

insert into student_course(student_id, course_id)
values (1, 1),
       (1, 4),
       (3, 2),
       (3, 3),
       (3, 1);

-- 给张xx再选一门物理
insert into student_course(student_id, course_id)
    value (1, 2);

-- 查看 张xx 总共选修了哪些课
# 分步
# 1. 查询关联表 student_course 根据 张xx 的ID进行过滤，查询 course_id 字段（具体选了哪些课）
select course_id from student_course where student_id=1;
# 2. 根据上一步得到的 course_id，再查询课程的详细信息
select * from course where id in (1, 4, 2);

# 一次性查询
select * from course where id in (select course_id from student_course where student_id=1);

