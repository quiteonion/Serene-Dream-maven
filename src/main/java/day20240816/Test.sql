# 创建一个单独的数据库
create database Test;

# 使用这个数据库
use Test;

# 先思考总共需要使用到几个表单，以及各个表单之间的关系
# 以下便是我们目前所需要的表单
# 由于职务与用户是绑定的，所以我们并不需要为职务创建一个表单
-- table_1: user  columns: id \ username \ password \ officer
-- table_2: menu  columns: id \ user_management \ article_management \ system_configuration \ permission_assignment

# 接下来是表单之间的关系
-- 表一的用户能够拥有多个不同的菜单， 一个菜单也可以被多个不同的用户所看到，所以这是一个 多对多的模型，那么就需要我们创建第三个表单
-- table_3: relationship columns: id \ user_username \ user_menu

# 在经过思考之后，我绝定修改表单二的列，整合为 menu 所以可以得到以下的表单结构
-- table_1: user  columns: id \ username \ password \ officer
-- table_2: menu  columns: id \ menu
-- 其中 各个属性我将其放置在 menu 中
-- table_3: relationship columns: id \ user_username \ user_menu

# 接下来是按照我的想法创建出所有的表单
# 在创建表单的时候我们仍旧要先定义各个表单之间的属性等等
-- table_1: user  columns: id \ username \ password \ officer

create table user
(
    id       int auto_increment comment 'id , 目前没有明确的作用',
    username varchar(20)                not null comment '用户名，不能为空，而且用户名不能重复',
    password varchar(20)                not null comment '密码，不能为空',
    officer  varchar(20) default 'user' not null comment '职务，不能为空，默认为用户',
    constraint user_pk
        primary key (id),
    constraint user_pk2
        unique (username)
);

# 接下来是菜单
-- table_2: menu  columns: id \ menu

create table menu
(
    id   int auto_increment comment 'id,目前没有明确的用途',
    menu varchar(20) not null comment '菜单，菜单表内不能重复',
    constraint menu_pk
        primary key (id),
    constraint menu_pk2
        unique (menu)
);

# 接下来是创建一个多对多的表单，内容是将其 '职务' 与 '菜单' 的内容一一对应

create table relationship
(
    id           int auto_increment comment 'id,目前并没有明确作用',
    user_officer varchar(20) not null comment '用户方面的职务，对应着菜单',
    menu_content varchar(20) not null comment '菜单中的内容，对应菜单的属性',
    constraint relationship_pk
        primary key (id)
);

# 接下来是配对 中继器 与二者之间的关系

alter table relationship
    add constraint relationship_user_officer_fk
        foreign key (user_officer) references user (officer);

alter table relationship
    add constraint relationship_menu_menu_fk
        foreign key (menu_content) references menu (menu);

# 运行之后报错，发现 缺少 index
# 添加 index

create index menu_menu_index
    on menu (menu);

create index user_officer_index
    on user (officer);

-- 创建完成后进行连接

-- =================== * 连接完成 * ==========================

# 为表单添加内容
-- user_management \ article_management \ system_configuration \ permission_assignment

insert into user(username, password, officer)
values ('admin', 'admin123', 'system_configurator'),
       ('user', 'user123', 'article_administrator'),
       ('root', 'root123', 'super_administrator');

# 接着发现字符太长，更改字符长度

alter table user
    modify officer varchar(50) default 'user' not null comment '职务，不能为空，默认为用户';

alter table menu
    modify menu varchar(50) not null comment '菜单，菜单表内不能重复';


insert into menu(menu)
values ('user_management'),
       ('article_management'),
       ('system_configuration'),
       ('permission_assignment');

# 最后是填充中继器

alter table relationship
    modify user_officer varchar(50) not null comment '用户方面的职务，对应着菜单';

alter table relationship
    modify menu_content varchar(50) not null comment '菜单中的内容，对应菜单的属性';



insert into relationship(user_officer, menu_content)
values ('system_configurator', 'user_management'),
       ('system_configurator', 'article_management'),
       ('system_configurator', 'system_configuration'),
       ('super_administrator', 'user_management'),
       ('super_administrator', 'article_management'),
       ('super_administrator', 'system_configuration'),
       ('super_administrator', 'permission_assignment'),
       ('article_administrator', 'article_management');


# 完成，当不同的情况下就调用不同的事物

