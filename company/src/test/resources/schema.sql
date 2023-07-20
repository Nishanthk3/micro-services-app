CREATE SCHEMA IF NOT EXISTS `account`;
SET SCHEMA `account`;

create table if not exists `user`
   (
    `id` number not null auto_increment,
	`first_name` varchar(120) not null,
	`last_name` varchar(120) not null,
	PRIMARY KEY ( id )
	);

CREATE SCHEMA IF NOT EXISTS `playground`;
SET SCHEMA `playground`;

create table if not exists `employee`
   (
    `id` number not null auto_increment,
	`employee_name` varchar(120) not null,
	PRIMARY KEY ( id )
	);
