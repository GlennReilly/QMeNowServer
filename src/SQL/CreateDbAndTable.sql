create database grTestDB;

use grTestDB;

create table Greeting(
	Id integer auto_increment not null primary key,
	comment varchar(256),
	createdDate timestamp not null default current_timestamp
);