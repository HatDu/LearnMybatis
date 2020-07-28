create table role(
    id int(11) not null,
    role_name varchar(30) default null,
    role_desc varchar(60) default null,
    primary key (id)
)ENGINE = InnoDB default CHARSET=utf8;

insert into role(role_name, role_desc)
values ('院长', '管理整个学院'),
       ('总裁', '管理整个公司'),
       ('校长', '管理整个学校');