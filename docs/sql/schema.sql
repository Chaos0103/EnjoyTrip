create schema if not exists `enjoy_trip` default character set utf8mb4 collate utf8mb4_0900_ai_ci;

use enjoy_trip;

drop table if exists member;

create table if not exists `MEMBER`
(
    `member_id`          bigint      not null primary key auto_increment,
    `login_id`           varchar(20) not null,
    `login_pw`           varchar(20) not null,
    `username`           varchar(20) not null,
    `email`              varchar(50) not null,
    `phone`              varchar(11) not null,
    `nickname`           varchar(10) not null,
    `birth`              varchar(6) not null,
    `gender`             varchar(1) not null,
    `created_date`       timestamp   not null default current_timestamp,
    `last_modified_date` timestamp   not null default current_timestamp
);

drop table if exists content_type;

create table if not exists `CONTENT_TYPE`
(
    `content_type_id`   int         not null primary key,
    `content_type_name` varchar(10) not null
);

create table if not exists `ARTICLE`
(
    `article_id`         bigint        not null primary key auto_increment,
    `member_id`          bigint        not null,
    `title`              varchar(100)  not null,
    `content`            varchar(1000) not null,
    `hit`                int           not null default 0,
    `created_date`       timestamp     not null default current_timestamp,
    `last_modified_date` timestamp     not null default current_timestamp,
    foreign key (`member_id`) references MEMBER (`member_id`)
);