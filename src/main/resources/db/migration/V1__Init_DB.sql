create sequence hibernate_sequence start 1 increment 1;

create table charter
(
    id         int4 not null,
    category   int4,
    filename   varchar(255),
    event_id   int4,
    student_id int4,
    primary key (id)
);

create table coursework
(
    id         int4 not null,
    filename   varchar(255),
    name       varchar(255),
    student_id int4,
    primary key (id)
);

create table event
(
    id        int4 not null,
    name      varchar(255),
    organizer varchar(255),
    primary key (id)
);

create table form
(
    id   int4 not null,
    name varchar(255),
    primary key (id)
);

create table level
(
    id   int4 not null,
    name varchar(255),
    primary key (id)
);

create table seat
(
    group_id   int4 not null,
    student_id int4 not null,
    is_budget  boolean,
    primary key (group_id, student_id)
);

create table speciality
(
    id   int4 not null,
    name varchar(255),
    primary key (id)
);

create table student
(
    id                   int4 not null,
    certificate_filename varchar(255),
    injection_date       date,
    name                 varchar(255),
    patronymic           varchar(255),
    surname              varchar(255),
    user_id              int4,
    primary key (id)
);

create table ueegroup
(
    id            int4 not null,
    name          varchar(255),
    form_id       int4,
    level_id      int4,
    speciality_id int4,
    primary key (id)
);

create table user_role
(
    user_id int4 not null,
    roles   varchar(255)
);

create table usr
(
    id       int4    not null,
    enabled  boolean not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table charter
    add constraint charter_event_fk
        foreign key (event_id)
            references event;

alter table charter
    add constraint charter_student_fk
        foreign key (student_id)
            references student;

alter table coursework
    add constraint coursework_student_fk
        foreign key (student_id)
            references student;

alter table seat
    add constraint seat_group_fk
        foreign key (group_id)
            references ueegroup;

alter table seat
    add constraint seat_student_fk
        foreign key (student_id)
            references student;

alter table student
    add constraint student_user_fk
        foreign key (user_id)
            references usr;

alter table ueegroup
    add constraint group_form_fk
        foreign key (form_id)
            references form;

alter table ueegroup
    add constraint group_level_fk
        foreign key (level_id)
            references level;

alter table ueegroup
    add constraint group_speciality_fk
        foreign key (speciality_id)
            references speciality;

alter table user_role
    add constraint user_role_user_fk
        foreign key (user_id)
            references usr;
