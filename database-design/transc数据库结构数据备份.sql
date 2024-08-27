/*
 Navicat Premium Data Transfer

 Source Server         : TransC
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : 124.70.31.213:3306
 Source Schema         : transc

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 27/08/2024 11:09:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES (1, '用户', '添加用户');
INSERT INTO `permissions` VALUES (2, '用户', '获取所有用户');
INSERT INTO `permissions` VALUES (3, '用户', '根据角色获取用户列表');
INSERT INTO `permissions` VALUES (4, '用户', '更新用户信息');
INSERT INTO `permissions` VALUES (5, '用户', '重置用户密码');
INSERT INTO `permissions` VALUES (6, '用户', '激活用户');
INSERT INTO `permissions` VALUES (7, '用户', '禁用用户');
INSERT INTO `permissions` VALUES (8, '角色', '新建角色');
INSERT INTO `permissions` VALUES (9, '角色', '获取所有角色');
INSERT INTO `permissions` VALUES (10, '角色', '更新角色');
INSERT INTO `permissions` VALUES (11, '角色', '删除角色');
INSERT INTO `permissions` VALUES (12, '用户', '给用户添加角色');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (3, '用户');
INSERT INTO `roles` VALUES (2, '管理员');
INSERT INTO `roles` VALUES (1, '超级管理员');

-- ----------------------------
-- Table structure for user_security
-- ----------------------------
DROP TABLE IF EXISTS `user_security`;
CREATE TABLE `user_security`  (
  `user_id` bigint(20) NOT NULL,
  `password_salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `failed_login_attempts` int(11) NULL DEFAULT 0,
  `account_non_locked` tinyint(1) NULL DEFAULT 1,
  `account_non_expired` tinyint(1) NULL DEFAULT 1,
  `credentials_non_expired` tinyint(1) NULL DEFAULT 1,
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`user_id`) USING BTREE,
  CONSTRAINT `user_security_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_security
-- ----------------------------
INSERT INTO `user_security` VALUES (1, 'salt123', 0, 1, 1, 1, 1);
INSERT INTO `user_security` VALUES (2, 'salt456', 0, 1, 1, 1, 1);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('ACTIVE','INACTIVE','LOCKED','DISABLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'INACTIVE',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'super', 'super', '2024-08-08 22:47:46', 'ACTIVE');
INSERT INTO `users` VALUES (2, 'admin', 'admin', '2024-08-08 22:47:46', 'ACTIVE');
INSERT INTO `users` VALUES (3, 'test', 'test', '2024-08-10 17:43:20', 'ACTIVE');

SET FOREIGN_KEY_CHECKS = 1;
