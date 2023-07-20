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

create table if not exists playground.`employee`
   (
    `id` number not null auto_increment,
	`employee_name` varchar(120) not null,
	PRIMARY KEY ( id )
	);

CREATE TABLE if not exists playground.`author` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE if not exists playground.`book` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `author_id` BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT book_ibfk_1 FOREIGN KEY (author_id) REFERENCES playground.author (id)
);