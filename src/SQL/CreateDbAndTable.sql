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

/*
 *
 */
create table nameValuePairs(
  id INTEGER NOT NULL AUTO_INCREMENT,
  customerId long,
  classification1 varchar(100),
  classification2 varchar(100),
  classification3 varchar(100),
  pairName varchar(100),
  pairFriendlyName varchar(100),
  pairValue text,
  createdDate TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (id)
);

insert into nameValuePairs(pairName,pairFriendlyName, pairValue) values('btn1', 'blue button',
'{"contactName":"button1","text":"button one","textColour":"#006666","backgroundColorHex":"#CCFF99","padding":"10dp 17dp 3dp 10dp"}');

insert into nameValuePairs(pairName,pairFriendlyName, pairValue) values('btn2', 'green button',
'{"contactName":"button2","text":"button two","textColour":"#000000","backgroundColorHex":"#33CC33","padding":"14dp 10dp 2dp 10dp"}');

select * from nameValuePairs;

/*
 *
 */

create user 'user456'@'localhost' identified by 'userHotDogFiasco$';
grant insert,execute,select,delete on DemoJunk1.* to 'user'@'localhost';


/*
 *
 */

CREATE TABLE `appUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `lastName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `physicalAddress` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `emailAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
);

create table customer(
  id int not null auto_increment,
  businessName nvarchar(100),
  phoneNumber varchar(30),
  emailAddress nvarchar(50),
  physicalAddress nvarchar(300),
  PRIMARY KEY (id)
);


/*
 *
 */
create table buttonStyle(
  id INTEGER NOT NULL AUTO_INCREMENT,
  createdDate TIMESTAMP NOT NULL DEFAULT current_timestamp,
  styleName varchar(50),
  textColour varchar(20),
  backgroundColourHex varchar(20),
  padding varchar(50),
  customerId long,
  PRIMARY KEY (id)
)

insert into buttonStyle(styleName, textColour, backgroundColourHex, padding) values('buttonStyle1','#000000','#33CC33', '14dp 10dp 2dp 10dp');
select * from buttonStyle;