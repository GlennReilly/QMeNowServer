create database grTestDB;

use grTestDB;

create table Greeting(
	Id integer auto_increment not null primary key,
	comment varchar(256),
	createdDate timestamp not null default current_timestamp
);

create table Phrase(
  Id integer auto_increment not null primary key,
  PhraseText varchar(1000) CHARSET utf8,
  author  varchar(100) CHARSET utf8,
  phraseDate date,
  createdDate timestamp not null default current_timestamp,
  addedByUserId integer
);