/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : vote

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-04-03 11:29:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `logintime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk ROW_FORMAT=REDUNDANT;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('4', 'admin', '1111', '2018-03-09 14:31:47');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------

-- ----------------------------
-- Table structure for tab1
-- ----------------------------
DROP TABLE IF EXISTS `tab1`;
CREATE TABLE `tab1` (
  `Tab1_id` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tab1
-- ----------------------------

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `createdate` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `publish` int(11) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES ('1', '你喜欢的编程语言是？', '2013-08-25 17:50:06', '1', '1', '4');
INSERT INTO `vote` VALUES ('2', '??', '2018-03-07 23:33:24', '1', '1', '4');
INSERT INTO `vote` VALUES ('3', 'java?cookie???', '2018-03-09 14:33:43', '1', '1', '4');

-- ----------------------------
-- Table structure for votecontext
-- ----------------------------
DROP TABLE IF EXISTS `votecontext`;
CREATE TABLE `votecontext` (
  `votecontext_id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `vote_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`votecontext_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gbk ROW_FORMAT=REDUNDANT;

-- ----------------------------
-- Records of votecontext
-- ----------------------------
INSERT INTO `votecontext` VALUES ('5', 'Java??', '0', '1');
INSERT INTO `votecontext` VALUES ('6', 'C#.Net??', '0', '1');
INSERT INTO `votecontext` VALUES ('7', 'PHP??', '0', '1');
INSERT INTO `votecontext` VALUES ('8', '213', '0', '2');
INSERT INTO `votecontext` VALUES ('9', '123', '0', '2');
INSERT INTO `votecontext` VALUES ('10', '123', '0', '2');
INSERT INTO `votecontext` VALUES ('11', '123', '0', '2');
INSERT INTO `votecontext` VALUES ('12', 'abc', '0', '3');
INSERT INTO `votecontext` VALUES ('13', 'def', '0', '3');
INSERT INTO `votecontext` VALUES ('14', '123', '0', '3');

-- ----------------------------
-- Table structure for voter
-- ----------------------------
DROP TABLE IF EXISTS `voter`;
CREATE TABLE `voter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk ROW_FORMAT=REDUNDANT;

-- ----------------------------
-- Records of voter
-- ----------------------------
INSERT INTO `voter` VALUES ('2', '1', '0:0:0:0:0:0:0:1');
