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

 Date: 12/08/2020 19:14:25
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
  `actual_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '执行操作',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `err_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '失败原因',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 87 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户操作记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (16, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 01:50:31', NULL);
INSERT INTO `sys_log` VALUES (17, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 02:42:55', NULL);
INSERT INTO `sys_log` VALUES (18, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 02:45:20', NULL);
INSERT INTO `sys_log` VALUES (19, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 02:47:15', NULL);
INSERT INTO `sys_log` VALUES (20, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 02:49:30', NULL);
INSERT INTO `sys_log` VALUES (21, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 06:37:14', NULL);
INSERT INTO `sys_log` VALUES (22, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 06:45:04', NULL);
INSERT INTO `sys_log` VALUES (23, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 06:47:19', NULL);
INSERT INTO `sys_log` VALUES (24, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 07:08:18', NULL);
INSERT INTO `sys_log` VALUES (25, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 07:10:40', NULL);
INSERT INTO `sys_log` VALUES (26, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 07:14:35', NULL);
INSERT INTO `sys_log` VALUES (27, 'ww22345', NULL, '登录', 1, NULL, '2020-07-20 07:24:02', NULL);
INSERT INTO `sys_log` VALUES (28, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 08:39:01', NULL);
INSERT INTO `sys_log` VALUES (29, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 08:55:35', NULL);
INSERT INTO `sys_log` VALUES (30, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:06:36', NULL);
INSERT INTO `sys_log` VALUES (31, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:09:18', NULL);
INSERT INTO `sys_log` VALUES (32, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:11:03', NULL);
INSERT INTO `sys_log` VALUES (33, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:12:05', NULL);
INSERT INTO `sys_log` VALUES (34, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:13:17', NULL);
INSERT INTO `sys_log` VALUES (35, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:14:27', NULL);
INSERT INTO `sys_log` VALUES (36, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:15:58', NULL);
INSERT INTO `sys_log` VALUES (37, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:17:09', NULL);
INSERT INTO `sys_log` VALUES (38, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:17:37', NULL);
INSERT INTO `sys_log` VALUES (39, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:18:05', NULL);
INSERT INTO `sys_log` VALUES (40, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:19:52', NULL);
INSERT INTO `sys_log` VALUES (41, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:21:28', NULL);
INSERT INTO `sys_log` VALUES (42, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:22:58', NULL);
INSERT INTO `sys_log` VALUES (43, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:23:51', NULL);
INSERT INTO `sys_log` VALUES (44, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:32:53', NULL);
INSERT INTO `sys_log` VALUES (45, 'ww22345', NULL, '退出', 1, NULL, '2020-07-21 09:34:48', NULL);
INSERT INTO `sys_log` VALUES (46, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:36:41', NULL);
INSERT INTO `sys_log` VALUES (47, 'ww22345', NULL, '退出', 1, NULL, '2020-07-21 09:51:20', NULL);
INSERT INTO `sys_log` VALUES (48, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 09:59:15', NULL);
INSERT INTO `sys_log` VALUES (49, 'ww22345', NULL, '登录', 1, NULL, '2020-07-21 10:29:13', NULL);
INSERT INTO `sys_log` VALUES (50, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 02:13:26', NULL);
INSERT INTO `sys_log` VALUES (51, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 03:08:42', NULL);
INSERT INTO `sys_log` VALUES (52, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 08:38:20', NULL);
INSERT INTO `sys_log` VALUES (53, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 09:16:16', NULL);
INSERT INTO `sys_log` VALUES (54, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 09:17:01', NULL);
INSERT INTO `sys_log` VALUES (55, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 09:22:08', NULL);
INSERT INTO `sys_log` VALUES (56, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 09:24:55', NULL);
INSERT INTO `sys_log` VALUES (57, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 09:26:50', NULL);
INSERT INTO `sys_log` VALUES (58, 'ww22345', NULL, '登录', 1, NULL, '2020-08-06 09:52:36', NULL);
INSERT INTO `sys_log` VALUES (59, 'ww22345', NULL, '登录', 1, NULL, '2020-08-07 02:30:09', NULL);
INSERT INTO `sys_log` VALUES (60, 'ww22345', NULL, '登录', 1, NULL, '2020-08-07 02:54:11', NULL);
INSERT INTO `sys_log` VALUES (61, 'ww22345', NULL, '退出', 1, NULL, '2020-08-07 02:55:41', NULL);
INSERT INTO `sys_log` VALUES (62, 'ww22345', NULL, '登录', 1, NULL, '2020-08-07 02:55:52', NULL);
INSERT INTO `sys_log` VALUES (63, 'admin', NULL, '登录', 1, NULL, '2020-08-11 07:15:54', NULL);
INSERT INTO `sys_log` VALUES (64, 'admin', NULL, '退出', 1, NULL, '2020-08-11 07:17:04', NULL);
INSERT INTO `sys_log` VALUES (65, 'admin', NULL, '登录', 1, NULL, '2020-08-11 07:17:18', NULL);
INSERT INTO `sys_log` VALUES (66, 'admin', NULL, '登录', 1, NULL, '2020-08-11 07:33:44', NULL);
INSERT INTO `sys_log` VALUES (67, 'admin', NULL, '退出', 1, NULL, '2020-08-11 07:36:28', NULL);
INSERT INTO `sys_log` VALUES (68, 'admin', NULL, '登录', 1, NULL, '2020-08-11 07:37:11', NULL);
INSERT INTO `sys_log` VALUES (69, 'admin', NULL, '登录', 1, NULL, '2020-08-11 07:39:14', NULL);
INSERT INTO `sys_log` VALUES (70, 'admin', NULL, '登录', 1, NULL, '2020-08-11 07:39:43', NULL);
INSERT INTO `sys_log` VALUES (71, 'admin', NULL, '退出', 1, NULL, '2020-08-11 08:32:40', NULL);
INSERT INTO `sys_log` VALUES (72, 'test', NULL, '登录', 1, NULL, '2020-08-11 08:32:54', NULL);
INSERT INTO `sys_log` VALUES (73, 'test', NULL, '退出', 1, NULL, '2020-08-11 08:33:05', NULL);
INSERT INTO `sys_log` VALUES (74, 'admin', NULL, '登录', 1, NULL, '2020-08-11 08:33:57', NULL);
INSERT INTO `sys_log` VALUES (75, 'admin', NULL, '登录', 1, NULL, '2020-08-11 08:35:02', NULL);
INSERT INTO `sys_log` VALUES (76, 'admin', NULL, '登录', 1, NULL, '2020-08-11 08:37:13', NULL);
INSERT INTO `sys_log` VALUES (77, 'admin', NULL, '登录', 1, NULL, '2020-08-12 01:50:59', NULL);
INSERT INTO `sys_log` VALUES (78, 'admin', NULL, '登录', 1, NULL, '2020-08-12 02:01:41', NULL);
INSERT INTO `sys_log` VALUES (79, 'admin', NULL, '登录', 1, NULL, '2020-08-12 06:00:19', NULL);
INSERT INTO `sys_log` VALUES (80, 'admin', NULL, '登录', 1, NULL, '2020-08-12 06:37:07', NULL);
INSERT INTO `sys_log` VALUES (81, 'admin', NULL, '登录', 1, NULL, '2020-08-12 08:05:40', NULL);
INSERT INTO `sys_log` VALUES (82, 'admin', NULL, '登录', 1, NULL, '2020-08-12 08:07:44', NULL);
INSERT INTO `sys_log` VALUES (83, 'admin', NULL, '登录', 1, NULL, '2020-08-12 08:12:36', NULL);
INSERT INTO `sys_log` VALUES (84, 'admin', NULL, '登录', 1, NULL, '2020-08-12 08:15:03', NULL);
INSERT INTO `sys_log` VALUES (85, 'admin', NULL, '登录', 1, NULL, '2020-08-12 08:17:39', NULL);
INSERT INTO `sys_log` VALUES (86, 'admin', NULL, '登录', 1, NULL, '2020-08-12 09:54:54', NULL);
INSERT INTO `sys_log` VALUES (87, 'admin', NULL, '登录', 1, NULL, '2020-08-12 10:53:34', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 1, NULL, 1, '2020-08-07 10:53:51', NULL, NULL);
INSERT INTO `sys_menu` VALUES (2, 1, '菜单管理', 'fa-cab', '/pages/sys/sysMenu/sysmenuList.html', 1, NULL, 2, '2020-07-21 15:41:16', '2020-08-11 07:39:30', NULL);
INSERT INTO `sys_menu` VALUES (3, 1, '菜单查询', NULL, NULL, 2, NULL, 1, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (17, 1, '用户管理', 'fa-user', '/pages/sys/sysUser/sysuserList.html', 1, '', 100, '2020-08-11 07:32:54', '2020-08-12 08:22:22', NULL);
INSERT INTO `sys_menu` VALUES (18, 1, '角色管理', 'fa-user', '/pages/sys/sysRole/sysroleList.html', 1, '', 100, '2020-08-11 08:36:39', '2020-08-12 08:22:07', NULL);
INSERT INTO `sys_menu` VALUES (30, 1, '操作日志', '', '/pages/sys/sysLog/syslogList.html', 1, '', 100, '2020-08-12 10:53:15', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '系统管理员', '2020-07-21 15:41:35', '2020-08-12 10:53:25');
INSERT INTO `sys_role` VALUES (2, '测试角色', '测试用', '2020-08-11 08:48:11', NULL);
INSERT INTO `sys_role` VALUES (10, 'Test', 'kkk', '2020-08-11 10:34:31', '2020-08-12 10:46:32');

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
INSERT INTO `sys_role_menu` VALUES (10, 1);
INSERT INTO `sys_role_menu` VALUES (10, 2);
INSERT INTO `sys_role_menu` VALUES (10, 17);
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 30);

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
  `mailbox` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别 1男 0女',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `status` int(0) NULL DEFAULT NULL COMMENT '状态 -1 禁用 1正常 -2 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (4, 'admin', '$2a$10$ixr2w9bWHw9BUZ8Y.nXB.ukLN6.cZ5f7SCPiXgq9Ru2mhtBjy9qCK', '管理员', '11.jpg', '13554212454', '5587', '6512@qq.com', '2020-08-11', 1, 12, 1, '2020-07-06 03:52:08', '2020-08-12 09:56:52', NULL);
INSERT INTO `sys_user` VALUES (7, 'test', '$2a$10$KQdO3/lwfcKrUxNfjJcHhOnJdgrrOE66DIrqLY.TV35Jhit1UG67a', '测试', '55', '13825215445', '15888', '55@qq.com', '2020-08-05', 1, 12, 1, '2020-08-12 07:29:46', '2020-08-12 08:17:49', '管理员');
INSERT INTO `sys_user` VALUES (8, 'test2', '$2a$10$9R9Fw3Rv.KjYG6tRCKr4D.jjGoQEMWjhqgmaL1Q6G5/zYHpgO8vXG', '小溪', '555', '13887521221', '88221', '65122@qq.com', '2020-08-30', 1, 21, 1, '2020-08-12 08:18:24', '2020-08-12 10:09:56', '管理员');

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
INSERT INTO `sys_user_role` VALUES (8, 2);

SET FOREIGN_KEY_CHECKS = 1;
