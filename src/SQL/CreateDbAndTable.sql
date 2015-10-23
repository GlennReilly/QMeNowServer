

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessName` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `emailAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `physicalAddress` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

create DATABASE DemoJunk1;
use DemoJunk1;
/*
 *
 */
create user 'user456'@'localhost' identified by 'userHotDogFiasco$';
grant insert,execute,select,delete on DemoJunk1.* to 'user456'@'localhost';
/*
 *
 */
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

#DROP table nameValuePairs;

/*
 *
 */

create table nameValuePairs(
  id INTEGER NOT NULL AUTO_INCREMENT,
  businessId long,
  classification1 varchar(100),
  classification2 varchar(100),
  classification3 varchar(100),
  pairName varchar(100),
  pairFriendlyName varchar(100),
  pairValue text,
  PRIMARY KEY (id)
);

alter table nameValuePairs add column createdDate TIMESTAMP NOT NULL DEFAULT current_timestamp;

insert into nameValuePairs(pairName,pairFriendlyName, pairValue) values('btn2', 'green button',
                                                                        '{"name":"button2","text":"button two","textColour":"#000000","backgroundColorHex":"#33CC33","padding":"14dp 10dp 2dp 10dp"}');

select * from nameValuePairs;
update nameValuePairs set pairfriendlyname = 'green button' where id = 2;

/*
 *
 */


create table appUser(
  id INTEGER NOT NULL AUTO_INCREMENT,
  username varchar(50),
  password varchar(50),
  businessId integer,
  createdDate TIMESTAMP NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (id)
);

alter table appUser add column firstName varchar(50);
alter table appUser add column lastName varchar(50);
alter table appUser add column physicalAddress varchar(300);

CREATE TABLE `appUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `lastName` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `physicalAddress` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `emailAddress` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
);

alter table appUser add column active bit default 1;
alter table customer add column active bit default 1;

select * from customer;
select * from appUser;

create table customer(
  id int not null auto_increment,
  businessName varchar(100),
  phoneNumber varchar(30),
  emailAddress varchar(50),
  physicalAddress varchar(300),
  PRIMARY KEY (id)
);




create table buttonStyle(
  id INTEGER NOT NULL AUTO_INCREMENT,
  createdDate TIMESTAMP NOT NULL DEFAULT current_timestamp,
  styleName varchar(50),
  textColour varchar(20),
  backgroundColourHex varchar(20),
  padding varchar(50),
  classification1 varchar(100),
  classification2 varchar(100),
  classification3 varchar(100),
  businessId long,
  PRIMARY KEY (id)
);

update buttonStyle set styleName = 'buttonStyle4' where id = 4;


insert into buttonStyle(styleName, textColour, backgroundColourHex, padding) values('buttonStyle1','#000000','#33CC33', '14dp 10dp 2dp 10dp');
insert into buttonStyle(styleName, textColour, backgroundColourHex, padding, businessId) values('buttonStyle3','#000066','#33CC66', '18dp 8dp 2dp 10dp',123);
insert into buttonStyle(styleName, textColour, backgroundColourHex, padding, businessId) values('buttonStyle5','#660066','#99CC33', '18dp 8dp 2dp 10dp',456);
select * from buttonStyle;

select 	id,	createdDate, styleName,	textColour, backgroundColourHex, padding, classification1, classification2, classification3, businessId
from buttonStyle where businessId = 123 or businessId is null;

select * from appUser;
SELECT `appUser`.`id`,
  `appUser`.`username`,
  `appUser`.`password`,
  `appUser`.`businessId`,
  `appUser`.`createdDate`,
  `appUser`.`firstName`,
  `appUser`.`lastName`,
  `appUser`.`physicalAddress`,
  `appUser`.`emailAddress`,
  `appUser`.`active`
FROM `DemoJunk1`.`appUser`;

SELECT id, username, businessId, firstName, lastName, physicalAddress,
  emailAddress, active FROM appUser where username = 'fherbert' AND password = 'sandworm$' AND active = true;

select * from nameValuePairs;


set @customerID = 2;
SELECT id, title, config, createdDate, updatedDate, businessId, revisionNumber
FROM ConfigStore where businessId is null or  businessId = @customerID;


alter table ConfigStore add column businessId int;
alter table ConfigStore add column revisionNumber int;
alter table ConfigStore modify column revisionNumber int default 1;
alter table ConfigStore add column title varchar(200);

use DemoJunk1;

create table location(
  id int(11) NOT NULL AUTO_INCREMENT,
  createdDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  locationName varchar(100),
  businessId int,
  PRIMARY KEY (id)
);

create table appointment(
  id int(11) NOT NULL AUTO_INCREMENT,
  createdDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  appointmentType varchar(200),
  appointmentDate timestamp,
  customerId int,
  messageToCustomer varchar(400),
  PRIMARY KEY (id)
);

create table appointmentStatus (
  id int(11) NOT NULL AUTO_INCREMENT,
  createdDate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  businessId int,
  statusName varchar(100),
  PRIMARY KEY (id)
);

ALTER TABLE appointment CONVERT TO CHARACTER SET utf8;
ALTER TABLE appointmentStatus CONVERT TO CHARACTER SET utf8;
ALTER TABLE appUser CONVERT TO CHARACTER SET utf8;
ALTER TABLE business CONVERT TO CHARACTER SET utf8;
ALTER TABLE customer CONVERT TO CHARACTER SET utf8;
ALTER TABLE location CONVERT TO CHARACTER SET utf8;

