/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3308
 Source Schema         : ttt

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 22/07/2020 18:10:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行操作',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `err_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '失败原因',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (16, 'ww22345', '登录', 1, NULL, '2020-07-20 01:50:31', NULL);
INSERT INTO `sys_log` VALUES (17, 'ww22345', '登录', 1, NULL, '2020-07-20 02:42:55', NULL);
INSERT INTO `sys_log` VALUES (18, 'ww22345', '登录', 1, NULL, '2020-07-20 02:45:20', NULL);
INSERT INTO `sys_log` VALUES (19, 'ww22345', '登录', 1, NULL, '2020-07-20 02:47:15', NULL);
INSERT INTO `sys_log` VALUES (20, 'ww22345', '登录', 1, NULL, '2020-07-20 02:49:30', NULL);
INSERT INTO `sys_log` VALUES (21, 'ww22345', '登录', 1, NULL, '2020-07-20 06:37:14', NULL);
INSERT INTO `sys_log` VALUES (22, 'ww22345', '登录', 1, NULL, '2020-07-20 06:45:04', NULL);
INSERT INTO `sys_log` VALUES (23, 'ww22345', '登录', 1, NULL, '2020-07-20 06:47:19', NULL);
INSERT INTO `sys_log` VALUES (24, 'ww22345', '登录', 1, NULL, '2020-07-20 07:08:18', NULL);
INSERT INTO `sys_log` VALUES (25, 'ww22345', '登录', 1, NULL, '2020-07-20 07:10:40', NULL);
INSERT INTO `sys_log` VALUES (26, 'ww22345', '登录', 1, NULL, '2020-07-20 07:14:35', NULL);
INSERT INTO `sys_log` VALUES (27, 'ww22345', '登录', 1, NULL, '2020-07-20 07:24:02', NULL);
INSERT INTO `sys_log` VALUES (28, 'ww22345', '登录', 1, NULL, '2020-07-21 08:39:01', NULL);
INSERT INTO `sys_log` VALUES (29, 'ww22345', '登录', 1, NULL, '2020-07-21 08:55:35', NULL);
INSERT INTO `sys_log` VALUES (30, 'ww22345', '登录', 1, NULL, '2020-07-21 09:06:36', NULL);
INSERT INTO `sys_log` VALUES (31, 'ww22345', '登录', 1, NULL, '2020-07-21 09:09:18', NULL);
INSERT INTO `sys_log` VALUES (32, 'ww22345', '登录', 1, NULL, '2020-07-21 09:11:03', NULL);
INSERT INTO `sys_log` VALUES (33, 'ww22345', '登录', 1, NULL, '2020-07-21 09:12:05', NULL);
INSERT INTO `sys_log` VALUES (34, 'ww22345', '登录', 1, NULL, '2020-07-21 09:13:17', NULL);
INSERT INTO `sys_log` VALUES (35, 'ww22345', '登录', 1, NULL, '2020-07-21 09:14:27', NULL);
INSERT INTO `sys_log` VALUES (36, 'ww22345', '登录', 1, NULL, '2020-07-21 09:15:58', NULL);
INSERT INTO `sys_log` VALUES (37, 'ww22345', '登录', 1, NULL, '2020-07-21 09:17:09', NULL);
INSERT INTO `sys_log` VALUES (38, 'ww22345', '登录', 1, NULL, '2020-07-21 09:17:37', NULL);
INSERT INTO `sys_log` VALUES (39, 'ww22345', '登录', 1, NULL, '2020-07-21 09:18:05', NULL);
INSERT INTO `sys_log` VALUES (40, 'ww22345', '登录', 1, NULL, '2020-07-21 09:19:52', NULL);
INSERT INTO `sys_log` VALUES (41, 'ww22345', '登录', 1, NULL, '2020-07-21 09:21:28', NULL);
INSERT INTO `sys_log` VALUES (42, 'ww22345', '登录', 1, NULL, '2020-07-21 09:22:58', NULL);
INSERT INTO `sys_log` VALUES (43, 'ww22345', '登录', 1, NULL, '2020-07-21 09:23:51', NULL);
INSERT INTO `sys_log` VALUES (44, 'ww22345', '登录', 1, NULL, '2020-07-21 09:32:53', NULL);
INSERT INTO `sys_log` VALUES (45, 'ww22345', '退出', 1, NULL, '2020-07-21 09:34:48', NULL);
INSERT INTO `sys_log` VALUES (46, 'ww22345', '登录', 1, NULL, '2020-07-21 09:36:41', NULL);
INSERT INTO `sys_log` VALUES (47, 'ww22345', '退出', 1, NULL, '2020-07-21 09:51:20', NULL);
INSERT INTO `sys_log` VALUES (48, 'ww22345', '登录', 1, NULL, '2020-07-21 09:59:15', NULL);
INSERT INTO `sys_log` VALUES (49, 'ww22345', '登录', 1, NULL, '2020-07-21 10:29:13', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `ico` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型 1 菜单 2按钮',
  `permission` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` int(0) NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 1, NULL, 1, '2020-07-21 15:40:54', NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '菜单管理', 'fa-user', '/pages/sys/sysMenu/sysmenuList.html', 1, NULL, 2, '2020-07-21 15:41:16', NULL, NULL);
INSERT INTO `sys_menu` VALUES (3, 1, '菜单查询', NULL, NULL, 2, 'sys:menu:query', 1, '2020-07-21 18:28:09', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '系统管理员', '2020-07-21 15:41:35', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `actual_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `head_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号码',
  `tele_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `e_mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 -1 禁用 1正常 -2 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (4, 'ww22345', '$2a$10$ixr2w9bWHw9BUZ8Y.nXB.ukLN6.cZ5f7SCPiXgq9Ru2mhtBjy9qCK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-07-06 03:52:08', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `role_id` int(0) NULL DEFAULT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-角色对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (4, 1);

SET FOREIGN_KEY_CHECKS = 1;
