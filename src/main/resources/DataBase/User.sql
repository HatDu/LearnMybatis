CREATE DATABASE db_mybatis;
USE DATABASE db_mybatis;
CREATE TABLE user(
    id INT(11) NOT NULL auto_increment,
    username VARCHAR(32) NOT NULL COMMENT '用户名称',
	birthday DATETIME default NULL COMMENT '生日',
    sex CHAR(1) default NULL COMMENT '性别',
    address VARCHAR(256) default NULL COMMENT '地址',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;