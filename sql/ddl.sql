drop table if exists todo_box CASCADE;
create table todo_box
(
    id bigint generated by default as identity,
    title varchar(255),
    fixed boolean default 0,
    primary key (id)
);

drop table todo_box;

drop table if exists weekly_box CASCADE;
create table weekly_box
(
    id bigint generated by default as identity,
    title varchar(255),
    primary key (id)
);