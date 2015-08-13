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

/*
 *
 */

create DATABASE DemoJunk1;
use DemoJunk1;
create TABLE ConfigStore(
  id INTEGER NOT NULL AUTO_INCREMENT,
  config TEXT,
  createdDate TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updatedDate TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id)
);

insert into ConfigStore(config) value('first test');
insert into ConfigStore(config) value('second test');
insert into ConfigStore(config) value('third test');

SELECT * from ConfigStore;

create user 'user456'@'localhost' identified by 'userHotDogFiasco$';
grant insert,execute,select,delete on DemoJunk1.* to 'user'@'localhost';


/*
 *
 */
