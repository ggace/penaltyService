DROP DATABASE IF EXISTS `penaltyService`; 
CREATE DATABASE `penaltyService`;
USE `penaltyService`;

DROP TABLE `room`;
DROP TABLE `member`;
DROP TABLE `penalty`;

CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    loginId CHAR(30)  NOT NULL,
    loginPw CHAR(60)  NOT NULL,
    nickname CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

CREATE TABLE `room`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title CHAR(100)  NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    adminId INT(10) UNSIGNED NOT NULL,
    
    FOREIGN KEY(adminId) REFERENCES `member`(Id)
);

create table `roomPW`(
    roomId int(10) unsigned not null,
    pw char(100) not null,
    
    FOREIGN KEY(roomId) REFERENCES `room`(Id)
);

CREATE TABLE `money`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    money INT(20) NOT NULL DEFAULT 0
);

create table `penalty`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content char(100) not null,
    moneyId INT(10) UNSIGNED NOT NULL,
    regDate datetime not null,
    updateDate dateTime not null,
    `type` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '제한여부(돈 미정 : 1, 돈 정함 : 0)',
    
    FOREIGN KEY(moneyId) REFERENCES `money`(Id)
);

CREATE TABLE `penaltyInRoom`(
    penaltyId INT(10) UNSIGNED NOT NULL,
    roomId INT(10) UNSIGNED NOT NULL ,
    
    FOREIGN KEY(penaltyId) REFERENCES `penalty`(Id),
    FOREIGN KEY(roomId) REFERENCES `room`(Id)
);

insert into `money`
set money=1000;

INSERT INTO `penalty`
SET content="aa",
moneyId=2,
regDate = now(),
updateDate = now(),
`type`=0;

INSERT INTO `member`
SET loginId="mmmmm",
loginPw="kkkkk",
nickname="ggace15",
regDate = NOW(),
updateDate = NOW(),
email = "test@test.com",
delStatus=0;

SELECT *
		FROM penalty;

select * from penaltyInRoom;

SELECT P.*, R.roomId
		FROM penalty P
		JOIN penaltyInRoom R
		ON P.id = R.roomId;

SELECT R.*, M.nickname
		FROM room AS R
		JOIN member AS M
		ON R.adminId = M.id	;
		
SELECT *
		FROM `member`
		WHERE delStatus = 0;