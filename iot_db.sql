/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : iot_db

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2022-06-03 21:45:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for environment
-- ----------------------------
DROP TABLE IF EXISTS `environment`;
CREATE TABLE `environment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `temperature` double DEFAULT NULL,
  `humidity` double DEFAULT NULL,
  `light` double DEFAULT NULL,
  `clock` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of environment
-- ----------------------------
INSERT INTO `environment` VALUES ('386', '31.5', '68.6', '542', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('387', '31.7', '58', '539', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('388', '31', '51.4', '540', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('389', '31.1', '64.9', '531', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('390', '31.3', '67.5', '540', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('391', '31.8', '64.6', '538', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('392', '31.8', '65.4', '541', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('393', '31.9', '69.2', '540', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('394', '32.2', '64.4', '544', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('395', '32.1', '54.7', '543', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('396', '32.2', '59.3', '543', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('397', '32.2', '57.6', '543', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('398', '32.2', '57.7', '546', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('399', '32.2', '57.6', '545', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('400', '32.3', '56.1', '529', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('401', '32.3', '59.4', '527', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('402', '32.4', '59.9', '535', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('403', '32.4', '57.6', '524', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('404', '32.4', '55.5', '545', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('405', '31.9', '51.9', '543', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('406', '31.9', '63.3', '545', '2022-06-01 11:12:27');
INSERT INTO `environment` VALUES ('407', '32', '60.9', '543', '2022-06-01 11:12:27');
