CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `appointmentTypeId` int(11) DEFAULT NULL,
  `appointmentDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `customerId` int(11) DEFAULT NULL,
  `messageToUser` varchar(400) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `locationId` int(11) DEFAULT NULL,
  `isComplete` bit(1) DEFAULT b'0',
  `checkInDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

CREATE TABLE `appointmentStatus` (
  `id` int(11) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `businessId` int(11) DEFAULT NULL,
  `statusName` varchar(100) DEFAULT NULL,
  `backgroundColourHexCode` varchar(10) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `sequenceNumber` int(11) DEFAULT '0',
  `customerInitiated` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `appointmentType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `styleJson` varchar(400) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `businessId` int(11) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `backgroundColourHexCode` varchar(10) DEFAULT NULL,
  `prefix` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

CREATE TABLE `appUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `physicalAddress` varchar(300) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `userType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE `appUserType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `businessName` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `physicalAddress` varchar(300) DEFAULT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` bit(1) DEFAULT b'1',
  `contactName` varchar(100) DEFAULT NULL,
  `logoName` varchar(100) DEFAULT NULL,
  `defaultLocationId` int(11) DEFAULT NULL,
  `logoFilePath` varchar(400) DEFAULT NULL,
  `buttonColourHexCode` varchar(10) DEFAULT NULL,
  `headerColourHexCode` varchar(10) DEFAULT NULL,
  `backgroundColourHexCode` varchar(10) DEFAULT NULL,
  `footerColourHexCode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `buttonStyle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `styleName` varchar(50) DEFAULT NULL,
  `textColour` varchar(20) DEFAULT NULL,
  `backgroundColourHex` varchar(20) DEFAULT NULL,
  `padding` varchar(50) DEFAULT NULL,
  `classification1` varchar(100) DEFAULT NULL,
  `classification2` varchar(100) DEFAULT NULL,
  `classification3` varchar(100) DEFAULT NULL,
  `customerId` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `ConfigStore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config` text,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `customerId` int(11) DEFAULT NULL,
  `revisionNumber` int(11) DEFAULT '1',
  `title` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=latin1;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(30) DEFAULT NULL,
  `emailAddress` varchar(50) DEFAULT NULL,
  `physicalAddress` varchar(300) DEFAULT NULL,
  `active` bit(1) DEFAULT b'1',
  `firstName` varchar(100) DEFAULT NULL,
  `lastName` varchar(100) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `locationName` varchar(100) DEFAULT NULL,
  `businessId` int(11) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `backgroundColourHexCode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE `nameValuePairs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` mediumtext,
  `classification1` varchar(100) DEFAULT NULL,
  `classification2` varchar(100) DEFAULT NULL,
  `classification3` varchar(100) DEFAULT NULL,
  `pairName` varchar(100) DEFAULT NULL,
  `pairFriendlyName` varchar(100) DEFAULT NULL,
  `pairValue` text,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
SELECT * FROM DemoJunk1.appointmentStatus;