create table user_role(
uid int(11) not null,
rid int(11) not null,
primary key (uid, rid),
FOREIGN KEY (rid) references role(id),
foreign key (uid) references user(id)
)ENGINE = InnoDB DEFAULT CHARSET =utf8;

insert into user_role(uid, rid)
values (5,1), (5,2) , (6,1), (6,2), (11,3), (11,2), (12, 1), (12, 2), (12, 3);
select * from user_role;