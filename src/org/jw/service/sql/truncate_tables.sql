truncate table cir.direction_map;
truncate table cir.location_map;
truncate table cir.contact_call;
truncate table cir.contact_status;
truncate table cir.contact; 
truncate table cir.apps_report_parameter;
truncate table cir.apps_report;
truncate table cir.possible_value;
truncate table cir.meeting_place;
truncate table cir.territory;
truncate table cir.service_group;
truncate table cir.congregation;

alter table cir.apps_report_parameter alter column id restart with 1;
alter table cir.apps_report alter column id restart with 1;
alter table cir.congregation alter column id restart with 1;
alter table cir.contact alter column id restart with 1; 
alter table cir.contact_call alter column id restart with 1;
alter table cir.contact_status alter column id restart with 1;
alter table cir.direction_map alter column id restart with 1;
alter table cir.location_map alter column id restart with 1;
alter table cir.meeting_place alter column id restart with 1;
alter table cir.possible_value alter column id restart with 1;
alter table cir.service_group alter column id restart with 1;
alter table cir.territory alter column id restart with 1;
commit;