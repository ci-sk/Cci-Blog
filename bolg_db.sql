/*
 Navicat Premium Dump SQL

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : bolg_db

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 27/09/2025 12:09:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account`  (
  `uid` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户权限',
  `register_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE INDEX `username_sy`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3415658785 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_account
-- ----------------------------
INSERT INTO `db_account` VALUES (1, 'admin', '$2a$10$YtIoqno7mw0ETlTen9xq3ey.8x08UECN6YeliBYDprYwt3tgsQsQK', 'cci591367@gmail.com', 'admin', '2024-09-27 08:35:02', NULL, NULL);
INSERT INTO `db_account` VALUES (2, 'test', '$2a$10$YtIoqno7mw0ETlTen9xq3ey.8x08UECN6YeliBYDprYwt3tgsQsQK', '1111@qq.com', 'user', '2024-09-28 18:28:39', NULL, NULL);
INSERT INTO `db_account` VALUES (3, 'cci', '$2a$10$YtIoqno7mw0ETlTen9xq3ey.8x08UECN6YeliBYDprYwt3tgsQsQK', '14142@qq.com', 'user', '2024-09-30 20:57:51', NULL, NULL);
INSERT INTO `db_account` VALUES (5, '吻鲸看日落', '$2a$10$DvHxf.u4F1v9d6lxEzr.pO57aCXZN3JFv400AoZ5ZgeUHeSGEHEWW', '2101096597@qq.com', 'user', '2024-10-01 18:41:49', NULL, NULL);
INSERT INTO `db_account` VALUES (6, '桃酥酥', '$2a$10$cEmMGVT8qccolmLjBnDSwOca7FeofJO0JgNPC6jzvOX6XD9z1nhvu', '2517491719@qq.com', 'user', '2024-10-01 23:28:55', NULL, NULL);
INSERT INTO `db_account` VALUES (7, '桃酥酥1', '$2a$10$buCgwD5wFBvStRMmMydE3OivIC28.XiJGFceBaUUoZA.4dP4zz9pO', '2517491719@qq.com', 'user', '2024-10-01 23:51:34', NULL, NULL);
INSERT INTO `db_account` VALUES (8, '平年', '$2a$10$XhjMkjAQ9cuGsdJyjpuafe.uwyrc/kmHZBKNFuFCk9rn5MMPm3pya', '14422414@qq.com', 'user', '2024-10-02 18:59:10', NULL, NULL);
INSERT INTO `db_account` VALUES (24, '洪亭', NULL, '1233121@163.com', 'user', '2024-10-14 19:46:32', NULL, NULL);
INSERT INTO `db_account` VALUES (25, '红莲', NULL, '4898923@qq.com', 'user', '2024-10-14 19:46:44', NULL, NULL);
INSERT INTO `db_account` VALUES (28, '方源', NULL, 'yongsheng@ys.com', 'user', '2024-10-17 21:24:10', NULL, NULL);
INSERT INTO `db_account` VALUES (29, '67', NULL, '5342424@qq.com', 'user', '2024-10-17 21:24:51', NULL, NULL);
INSERT INTO `db_account` VALUES (30, '张三', NULL, 'zhangsan@example.com', 'user', '2025-04-11 15:02:26', 'https://api.dicebear.com/7.x/avataaars/svg?seed=Felix\",\r\nhttps://api.dicebear.com/7.x/avataaars/svg?seed=Felix\",\r\nhttps://api.dicebear.com/7.x/avataaars/svg?seed=Felix\r\n', 'https://example.com');
INSERT INTO `db_account` VALUES (31, '王五', NULL, 'wangwu@example.com', NULL, NULL, 'https://api.dicebear.com/7.x/avataaars/svg?seed=Wangwu', NULL);
INSERT INTO `db_account` VALUES (32, '李四', NULL, 'lisi@example.com', NULL, NULL, 'https://api.dicebear.com/7.x/avataaars/svg?seed=Aneka', NULL);
INSERT INTO `db_account` VALUES (196943863, '196943863', '$2a$10$tQJkwwtldx/J9rbnIOPfKOn43H4boqIS/MPuYgY4sDuOQfE2OcN7K', '196943863@qq.com', 'user', '2025-04-13 21:28:23', 'http://q.qlogo.cn/headimg_dl?dst_uin=196943863&spec=640&img_type=jpg', '');
INSERT INTO `db_account` VALUES (3415658784, '3415658784', '$2a$10$McGeKv3nv.e5r/H7rENa2u9aEvwRbKp7XDDHDzHYBcicsSzsvidOu', '3415658784@qq.com', 'user', '2025-04-13 21:02:36', 'http://q.qlogo.cn/headimg_dl?dst_uin=3415658784&spec=640&img_type=jpg', NULL);

-- ----------------------------
-- Table structure for db_articles
-- ----------------------------
DROP TABLE IF EXISTS `db_articles`;
CREATE TABLE `db_articles`  (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文章内容',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章描述',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片链接',
  `del` int NULL DEFAULT NULL COMMENT '是否删除',
  `publish_Time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `category_id` int NULL DEFAULT NULL COMMENT '文章分类ID',
  `view_count` int NULL DEFAULT 0,
  PRIMARY KEY (`aid`) USING BTREE,
  INDEX `fk_article_category`(`category_id` ASC) USING BTREE,
  CONSTRAINT `fk_article_category` FOREIGN KEY (`category_id`) REFERENCES `db_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_articles
-- ----------------------------
INSERT INTO `db_articles` VALUES (0, 'zhuye', '主页', '主页和友链的评论寄存', 'null', '“”', NULL, NULL, NULL, 8);
INSERT INTO `db_articles` VALUES (1, '修改测试文本3', '# 标题 1\n\n## 标题 1.1\n\n这是一个段落。\n\n### 标题 1.1.1\n\n这是一个子段落。\n\n## 标题 1.2\n\n这是另一个段落。\n\n ![Image](https://cciblog.oss-cn-guangzhou.aliyuncs.com/01.png)\n```java\npublic static void main(String[] args) {\n    Frame frame = new Frame();\n    frame.setSize(500, 300);\n    frame.setVisible(true);\n}\n```\n\n```C\n#include<stdio.h>\nint main() {\n    printf(\"Hello, World!\");\n    return 0;\n}\n#include<stdio.h>\nint main() {\n    printf(\"Hello, World!\");\n    return 0;\n}\n#include<stdio.h>\nint main() {\n    printf(\"Hello, World!\");\n    return 0;\n}\n#include<stdio.h>\nint main() {\n    printf(\"Hello, World!\");\n    return 0;\n}#include<stdio.h>\nint main() {\n    printf(\"Hello, World!\");\n    return 0;\n}\n```\n\n', '', 'Spring Boot,Vue', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/备选.jpg', 2, '2023-09-28 15:11:29', 7, 132);
INSERT INTO `db_articles` VALUES (23, '修改测试文本2', '文本dsaf', '简介', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 2, '2023-09-28 21:34:27', 1, 20);
INSERT INTO `db_articles` VALUES (25, 'end test text 4.x', 'context 2.xfdsaf', 'dd', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 2, '2024-09-29 14:01:05', 9, 17);
INSERT INTO `db_articles` VALUES (27, '修改测试文本3', '文本\n```java\npublic static main(){\n  int a;\n}\n```', 'fsdaf', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 3, '2024-09-29 18:40:38', 3, 22);
INSERT INTO `db_articles` VALUES (28, '随笔', '1. 文本\n   # 文本测试\n![Image](https://cciblog.oss-cn-guangzhou.aliyuncs.com/01.png)\n', '带图片的修改测试', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 2, '2024-09-29 18:41:50', 7, 7);
INSERT INTO `db_articles` VALUES (29, '修改测试文本3', '文本', 'upData', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 2, '2024-09-29 22:21:14', 9, 4);
INSERT INTO `db_articles` VALUES (30, '修改测试文本3', '上传图片的修改测试', NULL, 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 3, '2024-09-29 22:21:49', 11, 0);
INSERT INTO `db_articles` VALUES (31, 'end uodata test', '修改', '最后一次修改测试', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 2, '2024-09-29 22:49:48', 1, 3);
INSERT INTO `db_articles` VALUES (32, '修改测试文本3', 'text', NULL, 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/备选.jpg', 2, '2024-09-29 23:49:04', 7, 6);
INSERT INTO `db_articles` VALUES (33, 'end test text 1.x', 'context', '测试新注解', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/备选.jpg', 2, '2024-09-30 11:40:30', 3, 4);
INSERT INTO `db_articles` VALUES (34, 'end test text 2.x', 'context 1.x', NULL, 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/备选.jpg', 2, '2024-09-30 19:52:44', 11, 0);
INSERT INTO `db_articles` VALUES (35, 'end test text 2.x', 'context 1.x', NULL, 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/备选.jpg', 2, '2024-10-02 18:58:06', 7, 1);
INSERT INTO `db_articles` VALUES (37, '随笔测试', '# Hello Editor\n这是我的随笔测试 主要用于看看在前台会不会展示图片\n\n ![Image](\nhttps://cciblog.oss-cn-guangzhou.aliyuncs.com/%E4%B8%9C%E5%B1%B1%E5%B0%8F%E7%BA%A2.jpg)', '我的随笔', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/备选.jpg', 2, '2024-10-23 11:00:36', 8, 2);
INSERT INTO `db_articles` VALUES (38, 'tes', '# Hello Editor', 'sdf', 'Test', 'https://cciblog.oss-cn-guangzhou.aliyuncs.com/东山小红.jpg', 1, '2024-10-23 11:46:32', 7, 0);
INSERT INTO `db_articles` VALUES (49, 'Hello', '# Hello Editor\n```java\n\npublic static void main(arr []){\n  System.out.printf(\"Hello World\")\n}\n \n', '第一篇java博客', 'Java', 'http://sl1dlcz2s.hn-bkt.clouddn.com/img/b4eeb1c9-e0c7-4498-b826-91bc6eea2b78.jpg', 0, '2024-10-23 17:13:40', 7, 0);

-- ----------------------------
-- Table structure for db_category
-- ----------------------------
DROP TABLE IF EXISTS `db_category`;
CREATE TABLE `db_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Category_Name_sy`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_category
-- ----------------------------
INSERT INTO `db_category` VALUES (11, 'SQL');
INSERT INTO `db_category` VALUES (7, 'test');
INSERT INTO `db_category` VALUES (1, '前端');
INSERT INTO `db_category` VALUES (3, '后端');
INSERT INTO `db_category` VALUES (9, '工具');
INSERT INTO `db_category` VALUES (8, '生活随笔');
INSERT INTO `db_category` VALUES (12, '资源软件');

-- ----------------------------
-- Table structure for db_comments
-- ----------------------------
DROP TABLE IF EXISTS `db_comments`;
CREATE TABLE `db_comments`  (
  `cid` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `aid` int NULL DEFAULT NULL COMMENT '文章外键',
  `uid` bigint NULL DEFAULT NULL COMMENT '用户外键',
  `reply_cid` int NULL DEFAULT NULL COMMENT '回复id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论',
  `time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `is_read` int NULL DEFAULT 0,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `fk_aid`(`aid` ASC) USING BTREE,
  INDEX `fk_uid`(`uid` ASC) USING BTREE,
  CONSTRAINT `fk_aid` FOREIGN KEY (`aid`) REFERENCES `db_articles` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_uid` FOREIGN KEY (`uid`) REFERENCES `db_account` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_comments
-- ----------------------------
INSERT INTO `db_comments` VALUES (2, 23, 3, NULL, 'nb', '2024-10-02 11:25:56', 1);
INSERT INTO `db_comments` VALUES (11, 28, 3, NULL, 'nb 2.x', '2024-10-02 19:36:29', 1);
INSERT INTO `db_comments` VALUES (12, 28, 24, 11, 'nb121 2.x', '2024-10-02 19:36:30', 1);
INSERT INTO `db_comments` VALUES (13, 28, 25, 12, '12121', '2024-10-02 19:38:21', 1);
INSERT INTO `db_comments` VALUES (14, 25, 6, NULL, '1331', '2024-11-12 10:32:01', 1);
INSERT INTO `db_comments` VALUES (15, 0, 30, NULL, '这篇文章写得非常好，学到了很多东西！', '2023-10-01 10:00:00', 5);
INSERT INTO `db_comments` VALUES (16, 0, 31, 15, '俺也一样！', '2025-04-13 18:30:01', 1);
INSERT INTO `db_comments` VALUES (17, 0, 32, NULL, '感谢分享，这些内容对我的项目很有帮助。期待更多类似的文章！', '2023-10-02 14:30:00', 1);
INSERT INTO `db_comments` VALUES (33, 0, 3415658784, 16, 'cci', '2025-06-07 09:45:08', 1);
INSERT INTO `db_comments` VALUES (34, 0, 196943863, 16, 'fasdf', '2025-06-07 09:46:34', 1);

-- ----------------------------
-- Table structure for db_f_link
-- ----------------------------
DROP TABLE IF EXISTS `db_f_link`;
CREATE TABLE `db_f_link`  (
  `fid` int NOT NULL AUTO_INCREMENT COMMENT '友链id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT 'icon',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '友链表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_f_link
-- ----------------------------
INSERT INTO `db_f_link` VALUES (1, '1122', '1221', 'https://www.baidu.com', 'www.baidu.com');
INSERT INTO `db_f_link` VALUES (3, '121', 'asd', 'https://www.baidu.com', 'https://th.bing.com/th/id/R.694d8ce6ed856ada966ad49990fdaeb9?rik=3Ywf1XuPbgRwMA&pid=ImgRaw&r=0');
INSERT INTO `db_f_link` VALUES (4, 'qsa', 'a', 'https://picsum.photos/100', 'https://picsum.photos/100');

-- ----------------------------
-- Table structure for db_message
-- ----------------------------
DROP TABLE IF EXISTS `db_message`;
CREATE TABLE `db_message`  (
  `mid` int NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外键用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '留言',
  `time` datetime NULL DEFAULT NULL COMMENT '留言时间',
  `is_read` int NULL DEFAULT 0,
  PRIMARY KEY (`mid`) USING BTREE,
  INDEX `fk_message_account`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_message
-- ----------------------------
INSERT INTO `db_message` VALUES (1, '1', '这是留言测试', '2024-10-03 10:49:40', 1);
INSERT INTO `db_message` VALUES (2, '1', '这是留言测试', '2024-10-03 10:49:48', 1);
INSERT INTO `db_message` VALUES (3, '3', '这是留言测试', '2024-10-03 11:10:32', 1);
INSERT INTO `db_message` VALUES (5, 'cci', 'das', '2024-11-16 18:18:09', 1);
INSERT INTO `db_message` VALUES (6, '4', '消息留言', '2024-12-09 13:19:52', 1);
INSERT INTO `db_message` VALUES (7, '吻鲸', '测试消息通知', '2024-12-09 13:19:55', 1);
INSERT INTO `db_message` VALUES (8, '3', 'asfa', '2025-01-14 17:40:33', 1);

-- ----------------------------
-- Table structure for db_tag
-- ----------------------------
DROP TABLE IF EXISTS `db_tag`;
CREATE TABLE `db_tag`  (
  `tid` int NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `tagName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
  `time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`tid`) USING BTREE,
  UNIQUE INDEX `TagName_sy`(`tagName` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_tag
-- ----------------------------
INSERT INTO `db_tag` VALUES (27, 'Spring Boot', '2024-10-12 19:47:27');
INSERT INTO `db_tag` VALUES (28, 'Vue', '2024-10-12 19:48:40');
INSERT INTO `db_tag` VALUES (29, 'Java', '2024-10-15 11:48:23');
INSERT INTO `db_tag` VALUES (30, 'Test', '2024-10-21 11:12:07');
INSERT INTO `db_tag` VALUES (33, 'HTML', '2024-10-23 11:10:01');
INSERT INTO `db_tag` VALUES (34, 'JavaScript', '2024-10-23 11:11:08');

SET FOREIGN_KEY_CHECKS = 1;
