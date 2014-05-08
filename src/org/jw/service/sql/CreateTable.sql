--create schema cir;
--set schema cir;

drop table cir.congregation if exists;
drop table cir.service_group if exists;
drop table cir.meeting_place if exists;
drop table cir.configuration if exists;
drop table cir.contact_status if exists;
drop table cir.location_map if exists;
drop table cir.direction_map if exists;
drop table cir.contact_call if exists;

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
    icon longvarbinary,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.meeting_place(
    id integer identity primary key,
    service_group_id integer,
    name varchar(50),
    address varchar(100),
    latitude double,
    longitude double,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.contact_status(
    id integer identity primary key,
    name varchar(50),
    description varchar(100),
    modifiable bit,
    printable bit,
    icon longvarbinary,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.configuration(
    id integer identity primary key,
    report_title varchar(50),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.contact(
    id integer identity primary key,
    service_group_id integer,
    status_id integer,
    location_map_id integer,    
    record_number varchar(15),
    record_date datetime,
    birthdate datetime,
    last_name varchar(30),
    first_name varchar(30),
    nick_name varchar(30),
    sex varchar(6),
    marital_status varchar(15),
    nationaltiy varchar(30),
    profile_picture longvarbinary,
    house_number varchar(15),
    street varchar(200),
    barangay varchar(20),
    city varchar(50),
    area varchar(50),
    personal_background varchar(150),
    family_background varchar(150),
    work_background varchar(150),
    phone_number varchar(50),
    mobile_number varchar(50),
    email_address varchar(50),
    skype_account varchar(50),
    facebook_account varchar(50),
    fathers_name varchar(50),
    mothers_name varchar(50),
    religion varchar(50),
    found_by varchar(50),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.location_map(
    id integer identity primary key,
    image longvarbinary,
    latitude double,
    longitude double,
    width integer,
    height integer,
    scale integer,
    zoom integer,
    image_format varchar(15),
    map_type varchar(15),    
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.direction_map(
    id integer identity primary key,
    meeting_place_id integer,
    location_map_id integer,
    image longvarbinary,
    direction longvarchar,    
    travel_mode varchar(15),
    width integer,
    height integer,
    zoom integer,
    scale integer,
    path_color varchar(10),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.contact_call(
    id integer identity primary key,
    contact_id integer,
    call_date datetime,
    call_day varchar(15),
    call_time varchar(10),
    status varchar(15),
    scriptures varchar(75),
    literature varchar(75),
    publishers varchar(75),
    notes longvarchar,
    next_visit datetime,
    next_topic longvarchar,
    created_datetime datetime,
    updated_datetime datetime
);

alter table cir.service_group add foreign key (congregation_id) references cir.congregation (id);
alter table cir.meeting_place add foreign key (service_group_id) references cir.service_group (id);
alter table cir.contact add foreign key (service_group_id) references cir.service_group (id);
alter table cir.contact add foreign key (status_id) references cir.contact_status (id);
alter table cir.contact add foreign key (location_map_id) references cir.location_map (id);
alter table cir.direction_map add foreign key (meeting_place_id) references cir.meeting_place (id);
alter table cir.direction_map add foreign key (location_map_id) references cir.location_map (id);
alter table cir.contact_call add foreign key (contact_id) references cir.contact (id);