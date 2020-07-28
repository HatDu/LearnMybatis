CREATE TABLE account(
	id int(11) NOT NULL COMMENT '账户编号' auto_increment,
    uid int(11) default NULL COMMENT '用户id',
    money double default NULL COMMENT '金额',
    primary key (id),
    KEY FK_Reference_8 (uid),
    CONSTRAINT FK_Reference_8 FOREIGN KEY (uid) REFERENCES user (id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;