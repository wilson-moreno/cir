drop table cir.congregation if exist;
drop table cir.service_group if exist;
drop table cir.meeting_place if exist;

create table cir.congregation(
    id integer identity primary key,
    name varchar(50),
    address_1 varchar(100),
    address_2 varchar(100),
    city varchar(50),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.service_group(
    id integer identity primary key,
    congregation_id integer,
    name varchar(50),
    overseer varchar(75),
    assistant varchar(75),
    prefix varchar(5),
    start_number integer,
    next_number integer,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.meeting_place(
    id integer identity primary key,
    service_group_id,
    name varchar(50),
    address varchar(100),
    latitude doube,
    longitude double,
    created_datetime,
    updated_datetime
);
