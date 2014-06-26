drop schema cir cascade;
create schema cir;
set schema cir;

drop table cir.congregation if exists;
drop table cir.service_group if exists;
drop table cir.meeting_place if exists;
drop table cir.configuration if exists;
drop table cir.contact_status if exists;
drop table cir.location_map if exists;
drop table cir.direction_map if exists;
drop table cir.contact_call if exists;
drop table cir.apps_report if exists;
drop table cir.apps_report_parameter if exists;
drop table cir.possible_value if exists;
drop table cir.territory if exists;
drop table cir.call_status if exist;

create table cir.call_status(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    name varchar(20),
    description varchar(100),
    enable bit,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.territory(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    service_group_id int,
    name varchar(30),
    description varchar(100),
    enable bit,
    map_image longvarbinary,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.possible_value(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    parameter_id integer,
    name varchar(30),
    value varchar(100),
    query longvarchar,
    type varchar(15),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.apps_report_parameter(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    report_id integer,
    sequence integer,
    name varchar(30),
    label varchar(30),
    control_type varchar(30),        
    data_type varchar(30),
    parameter_type varchar(30),
    enable bit,
    required bit,
    depends_on integer,
    default_value varchar(30),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.apps_report(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    name varchar(30),
    code varchar(30),
    title varchar(50),
    description varchar(150),
    report_date datetime,
    file_jasper longvarbinary,
    file_name varchar(100),
    file_modified_datetime datetime,
    file_created_datetime datetime,
    report_type varchar(20),
    datasource_type varchar(30),
    line_limit integer,
    query longvarchar,
    enable bit,    
    visible bit,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.congregation(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    name varchar(50),
    address_1 varchar(100),
    address_2 varchar(100),
    city varchar(50),
    latitude double,
    longitude double,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.service_group(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
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
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    service_group_id integer,
    name varchar(50),
    address varchar(100),
    latitude double,
    longitude double,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.contact_status(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    name varchar(50),
    description varchar(100),
    modifiable bit,
    printable bit,
    countable bit,
    icon longvarbinary,
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.configuration(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    report_title varchar(50),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.contact(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    service_group_id integer,
    status_id integer,
    territory_id integer,        
    record_number varchar(15),
    record_date datetime,
    birthdate datetime,
    last_name varchar(30),
    first_name varchar(30),
    nick_name varchar(30),
    sex varchar(6),
    marital_status varchar(15),
    nationality varchar(30),
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
    guardians_name varchar(50),
    religion varchar(50),
    found_by varchar(50),
    history varchar(200),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.location_map(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    contact_id integer,
    image longvarbinary,
    latitude double,
    longitude double,
    width integer,
    height integer,
    scale integer,
    zoom integer,
    image_format varchar(15),
    map_type varchar(15),    
    marker_color varchar(15),
    marker_label varchar(5),
    created_datetime datetime,
    updated_datetime datetime
);

create table cir.direction_map(
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
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
    id integer GENERATED BY DEFAULT AS IDENTITY (START WITH 1) primary key,
    contact_id integer,
    call_date datetime,
    call_day varchar(15),
    call_time varchar(10),
    status varchar(15),
    call_status_id int,
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
alter table cir.location_map add foreign key (contact_id) references cir.contact (id);
alter table cir.direction_map add foreign key (meeting_place_id) references cir.meeting_place (id);
alter table cir.direction_map add foreign key (location_map_id) references cir.location_map (id);
alter table cir.contact_call add foreign key (contact_id) references cir.contact(id);
alter table cir.apps_report_parameter add foreign key (report_id) references cir.apps_report(id);
alter table cir.possible_value add foreign key (parameter_id) references cir.apps_report_parameter(id);
alter table cir.contact add foreign key (territory_id) references cir.territory (id);
alter table cir.territory add foreign key (service_group_id) references cir.service_group(id);
alter table cir.contact_call add foreign key (call_status_id) references cir.call_status(id);

create view cir.vw_age_groups as
select  coalesce(casewhen(datediff('year',birthdate, today()) <= 10, '1 to 10', null), 
                              casewhen(datediff('year',birthdate, today()) <= 20, '11 to 20', null),
	              casewhen(datediff('year',birthdate, today()) <= 30, '21 to 30', null),
	              casewhen(datediff('year',birthdate, today()) <= 40, '31 to 40', null),
                              casewhen(datediff('year',birthdate, today()) <= 50, '41 to 50', null),
                              casewhen(datediff('year',birthdate, today()) <= 60, '51 to 60', null),
                              casewhen(datediff('year',birthdate, today()) <= 70, '61 to 70', null),
                              casewhen(datediff('year',birthdate, today()) <= 80, '71 to 80', null),
                              casewhen(datediff('year',birthdate, today()) <= 90, '81 to 90', null),
                              casewhen(datediff('year',birthdate, today()) <= 100, '91 to 100', null)) as age_range,
coalesce(casewhen(datediff('year',birthdate, today()) <= 10, 1, null), 
                              casewhen(datediff('year',birthdate, today()) <= 20, 1, null),
	              casewhen(datediff('year',birthdate, today()) <= 30, 1, null),
	              casewhen(datediff('year',birthdate, today()) <= 40, 1, null),
                              casewhen(datediff('year',birthdate, today()) <= 50, 1, null),
                              casewhen(datediff('year',birthdate, today()) <= 60, 1, null),
                              casewhen(datediff('year',birthdate, today()) <= 70, 1, null),
                              casewhen(datediff('year',birthdate, today()) <= 80, 1, null),
                              casewhen(datediff('year',birthdate, today()) <= 90, 1, null),
                              casewhen(datediff('year',birthdate, today()) <= 100, 1, null)) as age_count,
coalesce(casewhen(datediff('year',birthdate, today()) <= 10, 1, null), 
                              casewhen(datediff('year',birthdate, today()) <= 20, 2, null),
	              casewhen(datediff('year',birthdate, today()) <= 30, 3, null),
	              casewhen(datediff('year',birthdate, today()) <= 40, 4, null),
                              casewhen(datediff('year',birthdate, today()) <= 50, 5, null),
                              casewhen(datediff('year',birthdate, today()) <= 60, 6, null),
                              casewhen(datediff('year',birthdate, today()) <= 70, 7, null),
                              casewhen(datediff('year',birthdate, today()) <= 80, 8, null),
                              casewhen(datediff('year',birthdate, today()) <= 90, 9, null),
                              casewhen(datediff('year',birthdate, today()) <= 100, 10, null)) as group_order,
s.name service_group_name
from cir.contact c
inner join cir.service_group s on s.id = c.service_group_id
inner join cir.contact_status st on c.status_id = st.id
where c.birthdate is not null
and st.countable = true





commit;