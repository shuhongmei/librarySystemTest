/*
Navicat MySQL Data Transfer

Source Server         : zhewaida
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : userinfo

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-01-20 01:20:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_book`
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) NOT NULL,
  `book_writer` varchar(255) NOT NULL,
  `book_publisher` varchar(255) NOT NULL,
  `book_isrent` bigint(20) NOT NULL,
  `book_person` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('6', '算法导论', 'Thomas', '机械工业出版社', '0', '');
INSERT INTO `t_book` VALUES ('7', '机器学习', '周志华', '清华大学出版社', '0', '');
INSERT INTO `t_book` VALUES ('8', '实战机器学习', 'Peter', '人民邮电出版社', '0', '');
INSERT INTO `t_book` VALUES ('1', 'java编程思想', 'Bruce Eckel', '机械工业出版社', '1', 'shm');
INSERT INTO `t_book` VALUES ('2', '史蒂夫·乔布斯传', '沃尔特·艾萨克森', '中信出版社', null, null);
INSERT INTO `t_book` VALUES ('3', 'Java EE', 'aa', '明日出版社', null, null);
INSERT INTO `t_book` VALUES ('4', 'Spring', 'bb', '未来出版社', '0', '');
INSERT INTO `t_book` VALUES ('5', 'Spring + Spring MVC + MyBatis', 'cc', '将来出版社', '1', 'may');
