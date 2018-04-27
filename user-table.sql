create database rezodb default character set utf8;
use rezodb;


create table user(
	id SERIAL,
	login_id varchar(255) UNIQUE NOT NULL,
	name varchar(255) NOT NULL,
	birth_date DATE NOT NULL,
	password varchar(255) NOT NULL,
	create_date DATETIME NOT NULL,
	update_date DATETIME NOT NULL
);

insert into user(login_id, name,birth_date,password,create_date,update_date) 
	values('admin', '管理者', '1995-03-01', 'dsrf33', '2018-04-19 13:45:00', '2018-04-19 13:45:00');