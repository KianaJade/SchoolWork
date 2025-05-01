/*
 Navicat Premium Data Transfer

 Source Server         : a
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : unicollect

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 01/05/2025 14:07:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class1
-- ----------------------------
DROP TABLE IF EXISTS `class1`;
CREATE TABLE `class1`  (
  `c1_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`c1_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class1
-- ----------------------------
INSERT INTO `class1` VALUES ('日常');

-- ----------------------------
-- Table structure for class2
-- ----------------------------
DROP TABLE IF EXISTS `class2`;
CREATE TABLE `class2`  (
  `c2_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `c1_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`c2_name`) USING BTREE,
  INDEX `c1_name`(`c1_name`) USING BTREE,
  CONSTRAINT `class2_ibfk_1` FOREIGN KEY (`c1_name`) REFERENCES `class1` (`c1_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class2
-- ----------------------------
INSERT INTO `class2` VALUES ('生活', '日常');

-- ----------------------------
-- Table structure for class3
-- ----------------------------
DROP TABLE IF EXISTS `class3`;
CREATE TABLE `class3`  (
  `c3_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `c2_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`c3_name`) USING BTREE,
  INDEX `c2_name`(`c2_name`) USING BTREE,
  CONSTRAINT `class3_ibfk_1` FOREIGN KEY (`c2_name`) REFERENCES `class2` (`c2_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class3
-- ----------------------------
INSERT INTO `class3` VALUES ('招牌', '生活');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `g_id` int(0) NOT NULL AUTO_INCREMENT,
  `g_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_price` float NOT NULL,
  `g_number` int(0) NULL DEFAULT 0,
  `s_id` int(0) NOT NULL,
  `c3_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_imgNum` smallint(0) NULL DEFAULT 0,
  `g_sellNum` int(0) NULL DEFAULT 0,
  `g_img1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_img2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_img3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_img4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_img5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`g_id`) USING BTREE,
  UNIQUE INDEX `g_id`(`g_id`) USING BTREE,
  INDEX `s_id`(`s_id`) USING BTREE,
  INDEX `c3_name`(`c3_name`) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `shop` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`c3_name`) REFERENCES `class3` (`c3_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '这是一个商品', 99.9, 1000, 1, '招牌', 1, 0, 'e92df343f52f457490d6da2ea371dd82.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (2, '这是一个商品2号', 99.9, 1000, 1, '招牌', 1, 0, 'd6e60e883e38441d98b9cfa0e1f8d942.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (3, '这是一个商品3号', 99.9, 1000, 1, '招牌', 1, 0, '21e0a73dda5e43dfb60333618bd87532.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (4, '这是一个商品4号', 99.9, 1000, 1, '招牌', 1, 0, '6bff0e816b0b44f682da76c294d8b21b.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (5, '这是一个商品5号', 99.9, 1000, 1, '招牌', 1, 0, '0f3fa41278d6413fbe87b7f0815de40c.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `goods` VALUES (6, '这是一个商品6号', 99.9, 1000, 1, '招牌', 1, 0, '9495a9c6279e4233ac42ed60d37f8dfd.jpg', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for goodsorder
-- ----------------------------
DROP TABLE IF EXISTS `goodsorder`;
CREATE TABLE `goodsorder`  (
  `o_id` int(0) NOT NULL AUTO_INCREMENT,
  `o_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `o_number` int(0) NULL DEFAULT 1,
  `o_totalPrice` float NULL DEFAULT 0,
  `o_singlePrice` float NULL DEFAULT 0,
  `o_status` int(0) NULL DEFAULT 0,
  `g_id` int(0) NOT NULL,
  `id` int(0) NOT NULL,
  PRIMARY KEY (`o_id`) USING BTREE,
  UNIQUE INDEX `o_id`(`o_id`) USING BTREE,
  INDEX `g_id`(`g_id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `goodsorder_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `goodsorder_ibfk_2` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodsorder
-- ----------------------------
INSERT INTO `goodsorder` VALUES (1, '2025-05-01 14:02:29', 3, 299.7, 99.9, 0, 2, 1);
INSERT INTO `goodsorder` VALUES (2, '2025-05-01 14:02:32', 3, 299.7, 99.9, 2, 2, 1);
INSERT INTO `goodsorder` VALUES (3, '2025-05-01 14:02:45', 8, 799.2, 99.9, 0, 5, 1);
INSERT INTO `goodsorder` VALUES (4, '2025-05-01 14:02:47', 8, 799.2, 99.9, 2, 5, 1);
INSERT INTO `goodsorder` VALUES (5, '2025-05-01 14:02:49', 8, 799.2, 99.9, 1, 5, 1);

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `t_id` int(0) NOT NULL AUTO_INCREMENT,
  `t_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `g_id` int(0) NOT NULL,
  PRIMARY KEY (`t_id`) USING BTREE,
  UNIQUE INDEX `t_id`(`t_id`) USING BTREE,
  INDEX `g_id`(`g_id`) USING BTREE,
  CONSTRAINT `goodstype_ibfk_1` FOREIGN KEY (`g_id`) REFERENCES `goods` (`g_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (1, '颜色', 1);
INSERT INTO `goodstype` VALUES (2, '大小', 1);
INSERT INTO `goodstype` VALUES (3, '颜色', 2);
INSERT INTO `goodstype` VALUES (4, '大小', 2);
INSERT INTO `goodstype` VALUES (5, '颜色', 3);
INSERT INTO `goodstype` VALUES (6, '大小', 3);
INSERT INTO `goodstype` VALUES (7, '颜色', 4);
INSERT INTO `goodstype` VALUES (8, '大小', 4);
INSERT INTO `goodstype` VALUES (9, '颜色', 5);
INSERT INTO `goodstype` VALUES (10, '大小', 5);
INSERT INTO `goodstype` VALUES (11, '颜色', 6);
INSERT INTO `goodstype` VALUES (12, '大小', 6);

-- ----------------------------
-- Table structure for ordertag
-- ----------------------------
DROP TABLE IF EXISTS `ordertag`;
CREATE TABLE `ordertag`  (
  `o_id` int(0) NOT NULL,
  `tag_id` int(0) NOT NULL,
  PRIMARY KEY (`o_id`, `tag_id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE,
  CONSTRAINT `ordertag_ibfk_1` FOREIGN KEY (`o_id`) REFERENCES `goodsorder` (`o_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ordertag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ordertag
-- ----------------------------
INSERT INTO `ordertag` VALUES (1, 7);
INSERT INTO `ordertag` VALUES (2, 7);
INSERT INTO `ordertag` VALUES (1, 11);
INSERT INTO `ordertag` VALUES (2, 11);
INSERT INTO `ordertag` VALUES (3, 26);
INSERT INTO `ordertag` VALUES (4, 26);
INSERT INTO `ordertag` VALUES (5, 26);
INSERT INTO `ordertag` VALUES (3, 29);
INSERT INTO `ordertag` VALUES (4, 29);
INSERT INTO `ordertag` VALUES (5, 29);

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `s_id` int(0) NOT NULL AUTO_INCREMENT,
  `id` int(0) NOT NULL,
  `s_img` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `s_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `s_des` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `s_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `s_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`s_id`) USING BTREE,
  UNIQUE INDEX `s_id`(`s_id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (1, 1, NULL, NULL, NULL, NULL, '2025-05-01 11:38:09');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag_id` int(0) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `t_id` int(0) NOT NULL,
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `tag_id`(`tag_id`) USING BTREE,
  INDEX `t_id`(`t_id`) USING BTREE,
  CONSTRAINT `tag_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `goodstype` (`t_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '黑色', 1);
INSERT INTO `tag` VALUES (2, '白色', 1);
INSERT INTO `tag` VALUES (3, '红色', 1);
INSERT INTO `tag` VALUES (4, '大', 2);
INSERT INTO `tag` VALUES (5, '小', 2);
INSERT INTO `tag` VALUES (6, '中', 2);
INSERT INTO `tag` VALUES (7, '黑色', 3);
INSERT INTO `tag` VALUES (8, '白色', 3);
INSERT INTO `tag` VALUES (9, '红色', 3);
INSERT INTO `tag` VALUES (10, '大', 4);
INSERT INTO `tag` VALUES (11, '小', 4);
INSERT INTO `tag` VALUES (12, '中', 4);
INSERT INTO `tag` VALUES (13, '黑色', 5);
INSERT INTO `tag` VALUES (14, '白色', 5);
INSERT INTO `tag` VALUES (15, '红色', 5);
INSERT INTO `tag` VALUES (16, '大', 6);
INSERT INTO `tag` VALUES (17, '小', 6);
INSERT INTO `tag` VALUES (18, '中', 6);
INSERT INTO `tag` VALUES (19, '黑色', 7);
INSERT INTO `tag` VALUES (20, '白色', 7);
INSERT INTO `tag` VALUES (21, '红色', 7);
INSERT INTO `tag` VALUES (22, '大', 8);
INSERT INTO `tag` VALUES (23, '小', 8);
INSERT INTO `tag` VALUES (24, '中', 8);
INSERT INTO `tag` VALUES (25, '黑色', 9);
INSERT INTO `tag` VALUES (26, '白色', 9);
INSERT INTO `tag` VALUES (27, '红色', 9);
INSERT INTO `tag` VALUES (28, '大', 10);
INSERT INTO `tag` VALUES (29, '小', 10);
INSERT INTO `tag` VALUES (30, '中', 10);
INSERT INTO `tag` VALUES (31, '黑色', 11);
INSERT INTO `tag` VALUES (32, '白色', 11);
INSERT INTO `tag` VALUES (33, '红色', 11);
INSERT INTO `tag` VALUES (34, '大', 12);
INSERT INTO `tag` VALUES (35, '小', 12);
INSERT INTO `tag` VALUES (36, '中', 12);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `u_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'M',
  `u_img` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_birthday` date NULL DEFAULT '1990-01-01',
  `u_register` date NULL DEFAULT 'curdate()',
  `u_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  UNIQUE INDEX `u_phone`(`u_phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '111', '小月亮', '11111111111', 'W', '5d2f55aad5454dc1869aa96e56aeb9c2.jpg', '2004-08-15', '2025-05-01', '月亮');

SET FOREIGN_KEY_CHECKS = 1;
