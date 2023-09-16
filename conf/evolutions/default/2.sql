# --- Sample dataset

# --- !Ups
insert into types (id,name) values (  1,'Apple Inc.');
insert into types (id,name) values (  2,'Thinking Machines');
insert into types (id,name) values (  3,'Lenovo');

insert into category (id,name) values (  1,'Apple Inc.');
insert into category (id,name) values (  2,'Thinking Machines');
insert into category (id,name) values (  3,'RCA');
insert into category (id,name) values (  4,'Netronics');
insert into category (id,name) values (  5,'Tandy Corporation');
insert into category (id,name) values (  6,'Commodore International');
insert into category (id,name) values (  7,'MOS Technology');
insert into category (id,name) values (  8,'Micro Instrumentation and Telemetry Systems');
insert into category (id,name) values (  9,'IMS Associates, Inc.');
insert into category (id,name) values ( 10,'Digital Equipment Corporation');
insert into category (id,name) values ( 11,'Lincoln Laboratory');
insert into category (id,name) values ( 12,'Moore School of Electrical Engineering');
insert into category (id,name) values ( 13,'IBM');
insert into category (id,name) values ( 14,'Amiga Corporation');
insert into category (id,name) values ( 15,'Canon');
insert into category (id,name) values ( 16,'Nokia');
insert into category (id,name) values ( 17,'Sony');
insert into category (id,name) values ( 18,'OQO');
insert into category (id,name) values ( 19,'NeXT');    
insert into category (id,name) values ( 20,'Atari');
insert into category (id,name) values ( 22,'Acorn computer');
insert into category (id,name) values ( 23,'Timex Sinclair');
insert into category (id,name) values ( 24,'Nintendo');
insert into category (id,name) values ( 25,'Sinclair Research Ltd');
insert into category (id,name) values ( 26,'Xerox');
insert into category (id,name) values ( 27,'Hewlett-Packard');
insert into category (id,name) values ( 28,'Zemmix');
insert into category (id,name) values ( 29,'ACVS');
insert into category (id,name) values ( 30,'Sanyo');
insert into category (id,name) values ( 31,'Cray');
insert into category (id,name) values ( 32,'Evans & Sutherland');    
insert into category (id,name) values ( 33,'E.S.R. Inc.');
insert into category (id,name) values ( 34,'OMRON');
insert into category (id,name) values ( 35,'BBN Technologies');
insert into category (id,name) values ( 36,'Lenovo Group');
insert into category (id,name) values ( 37,'ASUS');
insert into category (id,name) values ( 38,'Amstrad');
insert into category (id,name) values ( 39,'Sun Microsystems');
insert into category (id,name) values ( 40,'Texas Instruments');
insert into category (id,name) values ( 41,'HTC Corporation');
insert into category (id,name) values ( 42,'Research In Motion');
insert into category (id,name) values ( 43,'Samsung Electronics');

insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  1,'MacBook Pro 15.4 inch',null,11,1,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  2,'CM-2a',null,12,2,null);
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  3,'CM-200',null,12,2,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  4,'CM-5e',null,12,2,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  5,'CM-5','1991-01-01',12,2,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  6,'MacBook Pro','2006-01-10',11,1,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  7,'Apple IIe',null,11,1,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  8,'Apple IIc',null,11,1,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values (  9,'Apple IIGS',null,11,1,'reflection');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values ( 10,'Apple IIc Plus',null,11,1,'some');
insert into completed_task (id,name,achived,category_id,type_id,reflections) values ( 11,'Apple II Plus',null,11,1,'txt');

# --- !Downs

delete from completed_task;
delete from category;
delete from types
