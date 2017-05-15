--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `database`.`pt_operator` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_manufacturer` DROP PRIMARY KEY;

ALTER TABLE `database`.`qintai_cusinfo` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_poci` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_usergroup` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_role` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_menu` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_syslog` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_wiscosettlement` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_stationoperator` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_planordercomplete` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_declareplan` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_retrospect` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_fun_iedtd` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_station` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_module` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_systems` DROP PRIMARY KEY;

ALTER TABLE `database`.`test_blog` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_upload` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_fun_gc_tableconf` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_group` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_fun_dimensional` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_salesorder` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_user` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_payment` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_orderplan` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_department` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_fun_dycomponent` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_fun_gc_columnconf` DROP PRIMARY KEY;

ALTER TABLE `database`.`test_user` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_deliverydetailed` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_orderunit` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_resources` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_param` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_userinfo` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_dict` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_grouprole` DROP PRIMARY KEY;

ALTER TABLE `database`.`b_trading_salessettlement` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_roleoperator` DROP PRIMARY KEY;

ALTER TABLE `database`.`pt_userinfo` DROP INDEX `database`.`msnindex`;

ALTER TABLE `database`.`pt_operator` DROP INDEX `database`.`rowfilterindex`;

ALTER TABLE `database`.`pt_userinfo` DROP INDEX `database`.`specialityindex`;

ALTER TABLE `database`.`pt_userinfo` DROP INDEX `database`.`bloodgroupindex`;

ALTER TABLE `database`.`pt_userinfo` DROP INDEX `database`.`schoolnameindex`;

ALTER TABLE `database`.`pt_user` DROP INDEX `database`.`usernameindex`;

ALTER TABLE `database`.`pt_upload` DROP INDEX `database`.`md5_index`;

ALTER TABLE `database`.`pt_upload` DROP INDEX `database`.`filename_index`;

ALTER TABLE `database`.`pt_userinfo` DROP INDEX `database`.`postboyindex`;

ALTER TABLE `database`.`b_trading_retrospect` DROP INDEX `database`.`ids`;

ALTER TABLE `database`.`pt_operator` DROP INDEX `database`.`onemanyindex`;

ALTER TABLE `database`.`b_trading_salesorder` DROP INDEX `database`.`orderItemNoUnique`;

ALTER TABLE `database`.`b_trading_planordercomplete` DROP INDEX `database`.`orderItemNoUniQue`;

ALTER TABLE `database`.`b_trading_deliverydetailed` DROP INDEX `database`.`ids`;

ALTER TABLE `database`.`pt_operator` DROP INDEX `database`.`returnparamkeysindex`;

ALTER TABLE `database`.`pt_operator` DROP INDEX `database`.`urlindex`;

ALTER TABLE `database`.`pt_operator` DROP INDEX `database`.`returnurlindex`;

ALTER TABLE `database`.`pt_userinfo` DROP INDEX `database`.`nativityaddressindex`;

ALTER TABLE `database`.`b_trading_poci` DROP INDEX `database`.`invoceNo`;

DROP TABLE `database`.`pt_resources`;

DROP TABLE `database`.`pt_usergroup`;

DROP TABLE `database`.`pt_fun_gc_columnconf`;

DROP TABLE `database`.`pt_stationoperator`;

DROP TABLE `database`.`pt_fun_gc_tableconf`;

DROP TABLE `database`.`b_trading_orderunit`;

DROP TABLE `database`.`pt_fun_dimensional`;

DROP TABLE `database`.`b_trading_orderplan`;

DROP TABLE `database`.`qintai_cusinfo`;

DROP TABLE `database`.`pt_fun_iedtd`;

DROP TABLE `database`.`pt_upload`;

DROP TABLE `database`.`pt_station`;

DROP TABLE `database`.`pt_roleoperator`;

DROP TABLE `database`.`pt_param`;

DROP TABLE `database`.`pt_menu`;

DROP TABLE `database`.`b_trading_retrospect`;

DROP TABLE `database`.`b_trading_manufacturer`;

DROP TABLE `database`.`b_trading_deliverydetailed`;

DROP TABLE `database`.`pt_role`;

DROP TABLE `database`.`b_trading_planordercomplete`;

DROP TABLE `database`.`b_trading_poci`;

DROP TABLE `database`.`test_user`;

DROP TABLE `database`.`pt_grouprole`;

DROP TABLE `database`.`pt_user`;

DROP TABLE `database`.`pt_group`;

DROP TABLE `database`.`pt_syslog`;

DROP TABLE `database`.`b_trading_wiscosettlement`;

DROP TABLE `database`.`pt_department`;

DROP TABLE `database`.`test_blog`;

DROP TABLE `database`.`b_trading_payment`;

DROP TABLE `database`.`pt_module`;

DROP TABLE `database`.`pt_operator`;

DROP TABLE `database`.`pt_dict`;

DROP TABLE `database`.`b_trading_salesorder`;

DROP TABLE `database`.`pt_systems`;

DROP TABLE `database`.`pt_userinfo`;

DROP TABLE `database`.`b_trading_declareplan`;

DROP TABLE `database`.`pt_fun_dycomponent`;

DROP TABLE `database`.`b_trading_salessettlement`;

CREATE TABLE `database`.`pt_resources` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`osname` VARCHAR(200),
	`ips` VARCHAR(50),
	`hostname` VARCHAR(200),
	`cpunumber` BIGINT,
	`cpuratio` DECIMAL(10 , 10),
	`phymemory` BIGINT,
	`phyfreememory` BIGINT,
	`jvmtotalmemory` BIGINT,
	`jvmfreememory` BIGINT,
	`jvmmaxmemory` BIGINT,
	`gccount` BIGINT,
	`createdate` TIMESTAMP,
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_usergroup` (
	`ids` VARCHAR(32) NOT NULL,
	`userids` VARCHAR(32),
	`groupids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_fun_gc_columnconf` (
	`ids` VARCHAR(32) NOT NULL,
	`tablename` VARCHAR(1024),
	`columnname` VARCHAR(1024),
	`viewname` VARCHAR(1024),
	`formType` VARCHAR(1024),
	`formDataSorce` VARCHAR(1024),
	`hasQuery` INT,
	`hasListView` INT,
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_stationoperator` (
	`ids` VARCHAR(32) NOT NULL,
	`stationids` VARCHAR(32),
	`operatorids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_fun_gc_tableconf` (
	`ids` VARCHAR(32) NOT NULL,
	`tablename` VARCHAR(1024),
	`classname` VARCHAR(1024),
	`packagename` VARCHAR(256),
	`srcFolder` VARCHAR(256),
	`viewname` VARCHAR(256),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_orderunit` (
	`ids` VARCHAR(32) NOT NULL,
	`name` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_fun_dimensional` (
	`ids` VARCHAR(32) NOT NULL,
	`indexkey` VARCHAR(64),
	`paravalue` VARCHAR(256),
	`vieworder` INT,
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_orderplan` (
	`ids` VARCHAR(32) NOT NULL,
	`orderItemNo` VARCHAR(1024) NOT NULL,
	`standard` VARCHAR(1024),
	`pName` VARCHAR(1024),
	`grade` VARCHAR(1024),
	`pForm` VARCHAR(1024),
	`thickness` VARCHAR(1024),
	`width` VARCHAR(1024),
	`length` VARCHAR(1024),
	`itemWeight` VARCHAR(1024),
	`price` VARCHAR(1024),
	`rrived` VARCHAR(1024),
	`specialName` VARCHAR(1024),
	`freight` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`qintai_cusinfo` (
	`ids` VARCHAR(32) NOT NULL,
	`name` VARCHAR(1024),
	`cusbank` VARCHAR(1024),
	`bankNum` VARCHAR(128),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_fun_iedtd` (
	`ids` VARCHAR(32) NOT NULL,
	`indexkey` VARCHAR(64),
	`intoDbSQL` VARCHAR(8192),
	`excelDataColNo` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_upload` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`parametername` VARCHAR(50),
	`filename` VARCHAR(50),
	`contenttype` VARCHAR(100),
	`originalfilename` VARCHAR(500),
	`path` VARCHAR(500),
	`md5` VARCHAR(100),
	`describe` TEXT,
	`orderids` BIGINT,
	`size` BIGINT,
	`targetids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_station` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` VARCHAR(200),
	`images` VARCHAR(50),
	`isparent` VARCHAR(5),
	`names` VARCHAR(25),
	`orderids` BIGINT,
	`parentstationids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_roleoperator` (
	`ids` VARCHAR(32) NOT NULL,
	`roleIds` VARCHAR(32),
	`operatorIds` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_param` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` VARCHAR(200),
	`images` VARCHAR(50),
	`names` VARCHAR(25),
	`orderids` BIGINT,
	`paths` VARCHAR(1000),
	`zhuangtai` CHAR(1),
	`parentids` VARCHAR(32),
	`isparent` VARCHAR(5),
	`levels` BIGINT,
	`numbers` VARCHAR(50),
	`val` VARCHAR(500),
	`i18n` CHAR(1),
	`val_zhcn` VARCHAR(500),
	`val_zhhk` VARCHAR(500),
	`val_zhtw` VARCHAR(500),
	`val_ja` VARCHAR(500),
	`val_enus` VARCHAR(500),
	`status` CHAR(1),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_menu` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`images` VARCHAR(50),
	`levels` BIGINT,
	`orderids` BIGINT,
	`operatorids` VARCHAR(32),
	`parentmenuids` VARCHAR(32),
	`systemsids` VARCHAR(32),
	`isparent` VARCHAR(5),
	`names_zhcn` VARCHAR(25),
	`names_zhhk` VARCHAR(25),
	`names_zhtw` VARCHAR(25),
	`names_enus` VARCHAR(25),
	`names_ja` VARCHAR(25),
	`param` VARCHAR(200),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_retrospect` (
	`ids` VARCHAR(32) NOT NULL,
	`mon` VARCHAR(4) NOT NULL,
	`pName` VARCHAR(1024) NOT NULL,
	`grade` VARCHAR(1024) NOT NULL,
	`specification` VARCHAR(1024) NOT NULL,
	`price` VARCHAR(1024),
	`docNo` VARCHAR(1024),
	`gapPrice` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_manufacturer` (
	`ids` VARCHAR(32) NOT NULL,
	`name` VARCHAR(2056),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_deliverydetailed` (
	`ids` VARCHAR(32) NOT NULL,
	`orderItemNo` VARCHAR(1024) NOT NULL,
	`contractMonth` VARCHAR(1024),
	`tag` VARCHAR(1024),
	`weight` VARCHAR(1024),
	`quantity` VARCHAR(128),
	`writeOffDate` VARCHAR(128),
	`freight` VARCHAR(128),
	`railwayTIP` VARCHAR(1024),
	`waterTIP` VARCHAR(1024),
	`extensionFreight` VARCHAR(1024),
	`thickness` VARCHAR(128),
	`width` VARCHAR(128),
	`length` VARCHAR(128),
	`destination` VARCHAR(256),
	`privateLine` VARCHAR(256),
	`gapPrice` VARCHAR(1024),
	`dvPrice` VARCHAR(1024),
	`docNo` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_role` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`numbers` VARCHAR(50),
	`names` VARCHAR(50),
	`description` VARCHAR(2000),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_planordercomplete` (
	`ids` VARCHAR(32) NOT NULL,
	`orderItemNo` VARCHAR(128),
	`standard` VARCHAR(1024),
	`pName` VARCHAR(1024),
	`grade` VARCHAR(1024),
	`specification` VARCHAR(1024),
	`itemWeight` VARCHAR(1024),
	`price` VARCHAR(1024),
	`rrived` VARCHAR(1024),
	`specialName` VARCHAR(1024),
	`freight` VARCHAR(1024),
	`pForm` VARCHAR(1024),
	`thickness` VARCHAR(1024),
	`width` VARCHAR(1024),
	`length` VARCHAR(1024),
	`cDate` VARCHAR(1024),
	`cNo` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_poci` (
	`ids` VARCHAR(32) NOT NULL,
	`invoceNo` VARCHAR(128),
	`cDate` VARCHAR(4),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`test_user` (
	`Id` INT NOT NULL,
	`UserName` VARCHAR(256),
	`Age` VARCHAR(256),
	`Birthday` TIMESTAMP,
	`DeptName` VARCHAR(256),
	`DeptId` INT,
	PRIMARY KEY (`Id`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_grouprole` (
	`ids` VARCHAR(32) NOT NULL,
	`groupids` VARCHAR(32),
	`roleids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_user` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`errorcount` BIGINT,
	`orderids` BIGINT,
	`password` VARCHAR(500),
	`salt` VARCHAR(500),
	`status` CHAR(1),
	`stopdate` TIMESTAMP,
	`username` VARCHAR(50),
	`departmentids` VARCHAR(32),
	`stationids` TEXT,
	`email` VARCHAR(100),
	`idcard` VARCHAR(25),
	`mobile` VARCHAR(20),
	`names` VARCHAR(25),
	`secretkey` VARCHAR(100),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_group` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` VARCHAR(2000),
	`names` VARCHAR(50),
	`numbers` VARCHAR(50),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_syslog` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`actionenddate` TIMESTAMP,
	`actionendtime` BIGINT,
	`actionhaoshi` BIGINT,
	`actionstartdate` TIMESTAMP,
	`actionstarttime` BIGINT,
	`cause` CHAR(1),
	`cookie` VARCHAR(1024),
	`description` TEXT,
	`enddate` TIMESTAMP,
	`endtime` BIGINT,
	`haoshi` BIGINT,
	`ips` VARCHAR(128),
	`method` VARCHAR(4),
	`referer` VARCHAR(500),
	`requestpath` TEXT,
	`startdate` TIMESTAMP,
	`starttime` BIGINT,
	`status` CHAR(1),
	`useragent` VARCHAR(1000),
	`viewhaoshi` BIGINT,
	`operatorids` VARCHAR(32),
	`accept` VARCHAR(200),
	`acceptencoding` VARCHAR(200),
	`acceptlanguage` VARCHAR(200),
	`connection` VARCHAR(200),
	`host` VARCHAR(200),
	`xrequestedwith` VARCHAR(200),
	`pvids` VARCHAR(32),
	`userids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_wiscosettlement` (
	`ids` VARCHAR(32) NOT NULL,
	`settlementNo` VARCHAR(1024),
	`issuanceDate` VARCHAR(1024),
	`orderItemNo` VARCHAR(1024),
	`pName` VARCHAR(1024),
	`grade` VARCHAR(1024),
	`specification` VARCHAR(1024),
	`contractMonth` VARCHAR(1024),
	`weight` VARCHAR(1024),
	`price` VARCHAR(1024),
	`loan` VARCHAR(1024),
	`tax` VARCHAR(1024),
	`invoice` VARCHAR(1024),
	`freight` VARCHAR(1024),
	`railwayTIP` VARCHAR(1024),
	`waterTIP` VARCHAR(1024),
	`extensionFreight` VARCHAR(1024),
	`openCause` VARCHAR(1024),
	`hasConfirm` VARCHAR(1) DEFAULT 0,
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_department` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`allchildnodeids` VARCHAR(2000),
	`departmentlevel` BIGINT,
	`depttype` CHAR(1),
	`description` VARCHAR(200),
	`images` VARCHAR(50),
	`isparent` VARCHAR(5),
	`names` VARCHAR(25),
	`orderids` BIGINT,
	`url` VARCHAR(100),
	`parentdepartmentids` VARCHAR(32),
	`principaluserids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`test_blog` (
	`ids` VARCHAR(32) NOT NULL,
	`title` VARCHAR(200),
	`content` TEXT,
	`createTime` TIMESTAMP,
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_payment` (
	`ids` VARCHAR(32) NOT NULL,
	`unit` VARCHAR(128),
	`amount` VARCHAR(128),
	`abstract` VARCHAR(1028),
	`method` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_module` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` VARCHAR(200),
	`images` VARCHAR(50),
	`isparent` VARCHAR(5),
	`names` VARCHAR(25),
	`orderids` BIGINT,
	`parentmoduleids` VARCHAR(32),
	`systemsids` VARCHAR(32),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_operator` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` TEXT,
	`names` VARCHAR(25),
	`onemany` CHAR(1),
	`returnparamkeys` VARCHAR(100),
	`returnurl` VARCHAR(200),
	`rowfilter` CHAR(1),
	`url` VARCHAR(200),
	`moduleids` VARCHAR(32),
	`splitpage` CHAR(1),
	`formtoken` CHAR(1),
	`ipblack` CHAR(1),
	`privilegess` CHAR(1),
	`ispv` CHAR(1),
	`pvtype` CHAR(1),
	`modulenames` VARCHAR(50),
	`csrf` CHAR(1),
	`referer` CHAR(1),
	`method` CHAR(1),
	`syslog` CHAR(1),
	`enctype` CHAR(1),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_dict` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` VARCHAR(200),
	`images` VARCHAR(50),
	`names` VARCHAR(25),
	`orderids` BIGINT,
	`paths` VARCHAR(1000),
	`zhuangtai` CHAR(1),
	`parentids` VARCHAR(32),
	`isparent` VARCHAR(5),
	`levels` BIGINT,
	`numbers` VARCHAR(50),
	`val` VARCHAR(500),
	`i18n` CHAR(1),
	`val_zhcn` VARCHAR(500),
	`val_zhhk` VARCHAR(500),
	`val_zhtw` VARCHAR(500),
	`val_ja` VARCHAR(500),
	`val_enus` VARCHAR(500),
	`status` CHAR(1),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_salesorder` (
	`ids` VARCHAR(32) NOT NULL,
	`orderUnit` VARCHAR(1024),
	`manufacturer` VARCHAR(1024),
	`salesOrderNo` VARCHAR(1024),
	`salesPrice` VARCHAR(1024),
	`freightage` VARCHAR(1024),
	`storag` VARCHAR(1024),
	`other` VARCHAR(1024),
	`pocIds` VARCHAR(32),
	`orderItemNo` VARCHAR(128),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_systems` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`description` VARCHAR(200),
	`names` VARCHAR(25),
	`numbers` VARCHAR(25),
	`orderids` BIGINT,
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_userinfo` (
	`ids` VARCHAR(32) NOT NULL,
	`version` BIGINT,
	`address` VARCHAR(300),
	`addressinfo` VARCHAR(300),
	`avoirdupois` VARCHAR(5),
	`birthday` DATE,
	`bloodgroup` VARCHAR(15),
	`clientlevelend` TIMESTAMP,
	`clientlevelstart` TIMESTAMP,
	`culture` VARCHAR(30),
	`description` VARCHAR(200),
	`finishschooldate` DATE,
	`folk` VARCHAR(20),
	`government` VARCHAR(25),
	`homepage` VARCHAR(100),
	`householder` VARCHAR(20),
	`marriage` VARCHAR(20),
	`msn` VARCHAR(20),
	`nativityaddress` VARCHAR(20),
	`postboy` VARCHAR(6),
	`qq` VARCHAR(20),
	`schoolname` VARCHAR(20),
	`sex` VARCHAR(5),
	`speciality` VARCHAR(20),
	`stature` VARCHAR(5),
	`telephone` VARCHAR(20),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_declareplan` (
	`ids` VARCHAR(32) NOT NULL,
	`orderUnit` VARCHAR(256),
	`pName` VARCHAR(1024),
	`grade` VARCHAR(1024),
	`weight` VARCHAR(1024),
	`manufacturer` VARCHAR(256),
	`specification` VARCHAR(256),
	`planDate` VARCHAR(256),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`pt_fun_dycomponent` (
	`ids` VARCHAR(32) NOT NULL,
	`sqlkey` VARCHAR(1024),
	`sqlvalue` VARCHAR(2056),
	`comtype` VARCHAR(64),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE TABLE `database`.`b_trading_salessettlement` (
	`ids` VARCHAR(32) NOT NULL,
	`salesOrderIds` VARCHAR(32),
	`wiscoSettlementIds` VARCHAR(32),
	`noTaxPrice` VARCHAR(2056),
	`goodsAmount` VARCHAR(2056),
	`taxPrice` VARCHAR(2056),
	`totalAmount` VARCHAR(2056),
	`hasDraw` VARCHAR(1) DEFAULT 0,
	`invoiceNo` VARCHAR(1024),
	PRIMARY KEY (`ids`)
) ENGINE=InnoDB;

CREATE INDEX `msnindex` ON `database`.`pt_userinfo` (`msn` ASC);

CREATE INDEX `rowfilterindex` ON `database`.`pt_operator` (`rowfilter` ASC);

CREATE INDEX `specialityindex` ON `database`.`pt_userinfo` (`speciality` ASC);

CREATE INDEX `bloodgroupindex` ON `database`.`pt_userinfo` (`bloodgroup` ASC);

CREATE INDEX `schoolnameindex` ON `database`.`pt_userinfo` (`schoolname` ASC);

CREATE INDEX `usernameindex` ON `database`.`pt_user` (`username` ASC);

CREATE UNIQUE INDEX `md5_index` ON `database`.`pt_upload` (`md5` ASC);

CREATE UNIQUE INDEX `filename_index` ON `database`.`pt_upload` (`filename` ASC);

CREATE INDEX `postboyindex` ON `database`.`pt_userinfo` (`postboy` ASC);

CREATE UNIQUE INDEX `ids` ON `database`.`b_trading_retrospect` (`ids` ASC);

CREATE INDEX `onemanyindex` ON `database`.`pt_operator` (`onemany` ASC);

CREATE UNIQUE INDEX `orderItemNoUnique` ON `database`.`b_trading_salesorder` (`orderItemNo` ASC);

CREATE UNIQUE INDEX `orderItemNoUniQue` ON `database`.`b_trading_planordercomplete` (`orderItemNo` ASC);

CREATE UNIQUE INDEX `ids` ON `database`.`b_trading_deliverydetailed` (`ids` ASC);

CREATE INDEX `returnparamkeysindex` ON `database`.`pt_operator` (`returnparamkeys` ASC);

CREATE INDEX `urlindex` ON `database`.`pt_operator` (`url` ASC);

CREATE INDEX `returnurlindex` ON `database`.`pt_operator` (`returnurl` ASC);

CREATE INDEX `nativityaddressindex` ON `database`.`pt_userinfo` (`nativityaddress` ASC);

CREATE UNIQUE INDEX `invoceNo` ON `database`.`b_trading_poci` (`invoceNo` ASC);

