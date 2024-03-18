/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : book_manager

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-08-02 17:18:23
*/
use book_manager;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '7121334', '瓦尔登湖',  '卢梭','1839');
INSERT INTO `book` VALUES ('2', '9781585', '那不勒斯四部曲', '埃莱娜','1961');
INSERT INTO `book` VALUES ('3', '2136158', '活着', '余华','1962');
INSERT INTO `book` VALUES ('4', '2136411', '罪与罚', '陀思妥耶夫斯基','1963');
INSERT INTO `book` VALUES ('5', '2136411', '动物农场', '乔治','2010');
INSERT INTO `book` VALUES ('6', '2136411', '罪与罚', '陀思妥耶夫斯基','1963');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `identity` int(11) DEFAULT NULL,
  `is_admin` int(11) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('4', '北京', '20210906/1630924697826.png', '1997-02-02 00:00:00.000000', 'XXXX@163.com', '3', '1', '超级管理员', '123456', '4', '64733884', 'admin');