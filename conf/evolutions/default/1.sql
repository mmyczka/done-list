# --- First database schema

# --- !Ups

set ignorecase true;

create table category (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_category primary key (id))
;

create table types (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_type primary key (id))
;

create table completed_task (
  id                        bigint not null,
  name                      varchar(255) not null,
  achived                   timestamp,
  category_id               bigint,
  type_id                   bigint,
  reflections               text,
  constraint pk_completed_task primary key (id))
;

create sequence category_seq start with 1000;
create sequence types_seq start with 1000;

create sequence completed_task_seq start with 1000;

alter table completed_task add constraint fk_completed_task_category_1 foreign key (category_id) references category (id) on delete restrict on update restrict;
alter table completed_task add constraint fk_completed_task_type_1 foreign key (type_id) references types (id) on delete restrict on update restrict;
create index ix_completed_task_category_1 on completed_task (category_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists category;
drop table if exists types;

drop table if exists completed_task;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists category_seq;

drop sequence if exists completed_task_seq;

