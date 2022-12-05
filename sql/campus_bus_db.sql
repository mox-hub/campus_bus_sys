/*
 Navicat Premium Data Transfer

 Source Server         : findplatform
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : campus_bus_db

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 05/12/2022 17:02:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `admin_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员ID',
  `admin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin', 'admin', 'https://img.moxhub.cn/aboutme/avatar.png');

-- ----------------------------
-- Table structure for sys_bus
-- ----------------------------
DROP TABLE IF EXISTS `sys_bus`;
CREATE TABLE `sys_bus`  (
  `bus_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bus_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bus_rows` int(11) UNSIGNED NULL DEFAULT NULL,
  `bus_columns` int(11) UNSIGNED NULL DEFAULT NULL,
  `bus_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_bus
-- ----------------------------
INSERT INTO `sys_bus` VALUES (1, '001号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (2, '002号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (3, '003号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (4, '004号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (5, '005号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (6, '006号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (7, '007号校车', 4, 9, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (8, '008号校车', 4, 11, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (9, '009号校车', 4, 11, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (10, '010号校车', 4, 11, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (11, '011号校车', 4, 11, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (12, '012号校车', 4, 11, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (13, '013号校车', 4, 11, 'http://dummyimage.com/400x400');
INSERT INTO `sys_bus` VALUES (14, '014号校车', 4, 11, 'http://dummyimage.com/400x400');

-- ----------------------------
-- Table structure for sys_campus
-- ----------------------------
DROP TABLE IF EXISTS `sys_campus`;
CREATE TABLE `sys_campus`  (
  `campus_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `campus_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`campus_id`) USING BTREE,
  INDEX `campus_name`(`campus_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_campus
-- ----------------------------
INSERT INTO `sys_campus` VALUES (3, '六安路校区');
INSERT INTO `sys_campus` VALUES (4, '宣城校区');
INSERT INTO `sys_campus` VALUES (1, '屯溪路校区');
INSERT INTO `sys_campus` VALUES (2, '翡翠湖校区');

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `order_id` bigint(19) UNSIGNED NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL,
  `schedule_id` int(11) UNSIGNED NOT NULL,
  `seat_info` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `order_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`order_id`, `user_id`, `schedule_id`) USING BTREE,
  INDEX `schedule_id`(`schedule_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `schedule_id` FOREIGN KEY (`schedule_id`) REFERENCES `sys_schedule` (`schedule_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES (1593989850954031106, 1, 1, '0401', '2022-12-21 23:49:53', '2022-12-22 07:10:00');
INSERT INTO `sys_order` VALUES (1593989857065132033, 1, 1, '0402', '2022-12-21 23:49:53', '2022-12-22 07:10:00');
INSERT INTO `sys_order` VALUES (1593989861213298689, 1, 1, '0403', '2022-12-21 23:49:53', '2022-12-22 07:10:00');
INSERT INTO `sys_order` VALUES (1593989865134972930, 1, 1, '0404', '2022-12-21 23:49:53', '2022-12-22 07:10:00');
INSERT INTO `sys_order` VALUES (1593989870356881410, 1, 1, '0503', '2022-12-21 23:49:53', '2022-12-22 07:10:00');
INSERT INTO `sys_order` VALUES (1593989873821376514, 1, 1, '0504', '2022-12-21 23:49:53', '2022-12-22 07:10:00');
INSERT INTO `sys_order` VALUES (1598231099518066690, 1, 4, '0104', '2022-12-1 16:32:42', '1');
INSERT INTO `sys_order` VALUES (1598236162193989633, 1, 2, '0302', '2022-12-1 16:52:49', '1');
INSERT INTO `sys_order` VALUES (1599687863343202305, 1, 6, '0501', '2022-12-5 17:01:22', '1');

-- ----------------------------
-- Table structure for sys_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_route`;
CREATE TABLE `sys_route`  (
  `route_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_station` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `end_station` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `stop_station` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`route_id`) USING BTREE,
  INDEX `start_station`(`start_station` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_route
-- ----------------------------
INSERT INTO `sys_route` VALUES (1, '屯溪路校区北大门', '翡翠湖校区5教', '');
INSERT INTO `sys_route` VALUES (2, '屯溪路校区北大门', '翡翠湖校区5教', '黄山路,靶场路');
INSERT INTO `sys_route` VALUES (3, '六安路校区门口', '翡翠湖校区5教', '');
INSERT INTO `sys_route` VALUES (4, '六安路校区门口', '翡翠湖校区5教', '长丰路,仪表厂');
INSERT INTO `sys_route` VALUES (5, '翡翠湖校区5教', '屯溪路校区北大门', '翡翠湖校区校医院旁');
INSERT INTO `sys_route` VALUES (6, '六安路校区门口', '屯溪路校区北大门', '');
INSERT INTO `sys_route` VALUES (7, '翡翠湖校区校医院旁', '六安路校区', '');
INSERT INTO `sys_route` VALUES (8, '翡翠湖校区校医院旁', '屯溪路校区北大门', '');
INSERT INTO `sys_route` VALUES (9, '屯溪路校区北大门', '翡翠湖校区5教', '黄山路');
INSERT INTO `sys_route` VALUES (10, '翡翠湖校区5教', '屯溪路校区北大门', '黄山路');
INSERT INTO `sys_route` VALUES (11, '翡翠湖校区5教', '六安路校区门口', '长丰路,仪表厂');
INSERT INTO `sys_route` VALUES (12, '屯溪路校区北大门', '六安路校区门口', '');
INSERT INTO `sys_route` VALUES (13, '翡翠湖校区5教', '屯溪路校区北大门', '黄山路,靶场路');
INSERT INTO `sys_route` VALUES (14, '翡翠湖校区5教', '屯溪路校区北大门', '');
INSERT INTO `sys_route` VALUES (15, '翡翠湖校区5教', '屯溪路校区北大门', '长丰路,仪表厂');
INSERT INTO `sys_route` VALUES (16, '翡翠湖校区5教', '六安路校区', '翡翠湖校区图书馆');
INSERT INTO `sys_route` VALUES (17, '翡翠湖校区5教', '屯溪路校区', '翡翠湖校区图书馆');

-- ----------------------------
-- Table structure for sys_schedule
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule`;
CREATE TABLE `sys_schedule`  (
  `schedule_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `end_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `route_id` int(11) UNSIGNED NOT NULL,
  `start_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bus_id` int(11) UNSIGNED NOT NULL,
  `seat_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`schedule_id`, `route_id`, `bus_id`) USING BTREE,
  INDEX `start`(`start_location` ASC) USING BTREE,
  INDEX `end`(`end_location` ASC) USING BTREE,
  INDEX `rout_id`(`route_id` ASC) USING BTREE,
  INDEX `bus_id`(`bus_id` ASC) USING BTREE,
  INDEX `schedule_id`(`schedule_id` ASC) USING BTREE,
  CONSTRAINT `bus_id` FOREIGN KEY (`bus_id`) REFERENCES `sys_bus` (`bus_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `end` FOREIGN KEY (`end_location`) REFERENCES `sys_campus` (`campus_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `route_id` FOREIGN KEY (`route_id`) REFERENCES `sys_route` (`route_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `start` FOREIGN KEY (`start_location`) REFERENCES `sys_campus` (`campus_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_schedule
-- ----------------------------
INSERT INTO `sys_schedule` VALUES (1, '屯溪路校区', '翡翠湖校区', 1, '7:00', '1,2,3,4,5', 1, '');
INSERT INTO `sys_schedule` VALUES (2, '屯溪路校区', '翡翠湖校区', 2, '7:10', '1,2,3,4,5,6,7', 2, '');
INSERT INTO `sys_schedule` VALUES (3, '六安路校区', '翡翠湖校区', 3, '7:00', '1,2,3,4,5', 3, '');
INSERT INTO `sys_schedule` VALUES (4, '六安路校区', '翡翠湖校区', 4, '7:10', '1,2,3,4,5,6,7', 4, '');
INSERT INTO `sys_schedule` VALUES (5, '翡翠湖校区', '屯溪路校区', 5, '7:10', '1,2,3,4,5', 5, '');
INSERT INTO `sys_schedule` VALUES (6, '六安路校区', '屯溪路校区', 6, '7:20', '1,2,3,4,5', 6, '');
INSERT INTO `sys_schedule` VALUES (7, '六安路校区', '屯溪路校区', 6, '7:50', '1,2,3,4,5', 7, '');
INSERT INTO `sys_schedule` VALUES (8, '翡翠湖校区', '六安路校区', 7, '8:20', '1,2,3,4,5', 8, '');
INSERT INTO `sys_schedule` VALUES (9, '翡翠湖校区', '屯溪路校区', 8, '8:20', '1,2,3,4,5', 9, '');
INSERT INTO `sys_schedule` VALUES (10, '屯溪路校区', '翡翠湖校区', 9, '9:20', '1,2,3,4,5,6', 10, '');
INSERT INTO `sys_schedule` VALUES (11, '六安路校区', '翡翠湖校区', 4, '9:20', '1,2,3,4,5,6', 11, '');
INSERT INTO `sys_schedule` VALUES (12, '翡翠湖校区', '屯溪路校区', 10, '10:20', '1,2,3,4,5,6', 12, '');
INSERT INTO `sys_schedule` VALUES (13, '翡翠湖校区', '六安路校区', 11, '10:20', '1,2,3,4,5,6', 13, '');
INSERT INTO `sys_schedule` VALUES (14, '屯溪路校区', '六安路校区', 12, '11:50', '1,2,3,4,5', 5, '');
INSERT INTO `sys_schedule` VALUES (15, '屯溪路校区', '六安路校区', 12, '12:10', '1,2,3,4,5', 6, '');
INSERT INTO `sys_schedule` VALUES (16, '翡翠湖校区', '屯溪路校区', 13, '12:20', '1,2,3,4,5,6', 1, '');
INSERT INTO `sys_schedule` VALUES (17, '翡翠湖校区', '六安路校区', 11, '12:20', '1,2,3,4,5,6', 2, '');
INSERT INTO `sys_schedule` VALUES (18, '屯溪路校区', '翡翠湖校区', 2, '13:00', '1,2,3,4,5,6', 7, '');
INSERT INTO `sys_schedule` VALUES (19, '六安路校区', '翡翠湖校区', 4, '13:00', '1,2,3,4,5,6', 8, '');
INSERT INTO `sys_schedule` VALUES (20, '翡翠湖校区', '屯溪路校区', 14, '13:40', '1,2,3,4,5', 3, '');
INSERT INTO `sys_schedule` VALUES (21, '六安路校区', '屯溪路校区', 6, '13:30', '1,2,3,4,5', 13, '');
INSERT INTO `sys_schedule` VALUES (22, '六安路校区', '屯溪路校区', 6, '13:50', '1,2,3,4,5', 5, '');
INSERT INTO `sys_schedule` VALUES (23, '屯溪路校区', '翡翠湖校区', 1, '14:50', '1,2,3,4,5', 9, '');
INSERT INTO `sys_schedule` VALUES (24, '六安路校区', '翡翠湖校区', 3, '14:50', '1,2,3,4,5', 6, '');
INSERT INTO `sys_schedule` VALUES (25, '翡翠湖校区', '屯溪路校区', 13, '16:10', '1,2,3,4,5,6', 4, '');
INSERT INTO `sys_schedule` VALUES (26, '翡翠湖校区', '六安路校区', 11, '16:10', '1,2,3,4,5,6', 10, '');
INSERT INTO `sys_schedule` VALUES (27, '翡翠湖校区', '屯溪路校区', 13, '17:00', '1,2,3,4,5', 11, '');
INSERT INTO `sys_schedule` VALUES (28, '翡翠湖校区', '六安路校区', 11, '17:00', '1,2,3,4,5', 7, '');
INSERT INTO `sys_schedule` VALUES (29, '屯溪路校区', '六安路校区', 12, '17:20', '1,2,3,4,5', 12, '');
INSERT INTO `sys_schedule` VALUES (30, '翡翠湖校区', '屯溪路校区', 10, '17:30', '7', 3, '');
INSERT INTO `sys_schedule` VALUES (31, '翡翠湖校区', '六安路校区', 11, '17:30', '7', 4, '');
INSERT INTO `sys_schedule` VALUES (32, '屯溪路校区', '翡翠湖校区', 1, '17:50', '1,2,3,4,5', 1, '');
INSERT INTO `sys_schedule` VALUES (33, '六安路校区', '翡翠湖校区', 3, '17:50', '1,2,3,4,5', 2, '');
INSERT INTO `sys_schedule` VALUES (34, '翡翠湖校区', '屯溪路校区', 10, '18:00', '1,2,3,4,5,6', 8, '');
INSERT INTO `sys_schedule` VALUES (35, '翡翠湖校区', '六安路校区', 11, '18:00', '1,2,3,4,5,6', 9, '');
INSERT INTO `sys_schedule` VALUES (36, '翡翠湖校区', '六安路校区', 11, '21:10', '1,2,3,4,5', 6, '');
INSERT INTO `sys_schedule` VALUES (37, '翡翠湖校区', '屯溪路校区', 15, '21:10', '1,2,3,4,5', 1, '');
INSERT INTO `sys_schedule` VALUES (38, '翡翠湖校区', '六安路校区', 16, '22:00', '1,2,3,4,5,6,7', 2, '');
INSERT INTO `sys_schedule` VALUES (39, '翡翠湖校区', '屯溪路校区', 17, '22:00', '1,2,3,4,5,6,7', 14, '');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(11) UNSIGNED NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `work_id` int(11) NULL DEFAULT NULL,
  `campus_id` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `campus_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '李浩然', '123', '18812341234', 2019218211, 3);

SET FOREIGN_KEY_CHECKS = 1;
