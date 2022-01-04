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

CREATE TABLE `penalty`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content CHAR(100)  NOT NULL,
    money INT(20) NOT NULL DEFAULT 0,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `type` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '제한여부(돈 미정 : 1, 돈 정함 : 0)'
);

CREATE TABLE `room`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title CHAR(100)  NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    adminId INT(10) UNSIGNED NOT NULL,
    
    FOREIGN KEY(adminId) REFERENCES `member`(Id)
);

INSERT INTO `member`
SET loginId="mmmmm",
loginPw="kkkkk",
nickname="ggace15",
regDate = NOW(),
updateDate = NOW(),
email = "test@test.com",
delStatus=0;
