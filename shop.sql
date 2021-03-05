/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2020-07-12 22:22:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `represent` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('32', '耳机', '耳机');
INSERT INTO `category` VALUES ('33', '支架', '支架');
INSERT INTO `category` VALUES ('30', '智能音箱', '智能音箱');
INSERT INTO `category` VALUES ('31', '硬件·配件', '硬件·配件');
INSERT INTO `category` VALUES ('27', '其他商品', '杂七杂八的');
INSERT INTO `category` VALUES ('28', '大·彩电', '电视机');
INSERT INTO `category` VALUES ('29', '平板', '最新商品');
INSERT INTO `category` VALUES ('25', '笔记本', '最新、最热的笔记本都在这里');
INSERT INTO `category` VALUES ('26', '手机', '最新产品、热门手机、新潮流');
INSERT INTO `category` VALUES ('34', '测试', '测试');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `represent` varchar(50) NOT NULL DEFAULT '' COMMENT '商品描述',
  `money` double(9,0) unsigned zerofill NOT NULL,
  `img` varchar(100) NOT NULL DEFAULT '',
  `host_id` int(3) unsigned NOT NULL,
  `category_id` int(3) unsigned NOT NULL,
  `status` int(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('17', '小米6 硅胶保护套', '小米6 硅胶保护套', '000000049', 'static/images/photograph/peijian2.jpg', '1', '31', '1');
INSERT INTO `goods` VALUES ('16', '小米笔记本', '更轻更薄，像杂志一样随身携带', '000003599', 'static/images/photograph/pinpai5.png', '1', '25', '1');
INSERT INTO `goods` VALUES ('15', '小米电视3s 55英寸', '5月9日，下单立减200元', '000003999', 'static/images/photograph/pinpai4.png', '1', '28', '1');
INSERT INTO `goods` VALUES ('14', '小米手机5 64GB', '5月9日-10日，下单立减100元', '000001799', 'static/images/photograph/pinpai3.png', '1', '26', '1');
INSERT INTO `goods` VALUES ('12', '小米MIX', '5月9日-21日享花呗12期分期免息', '000003499', 'static/images/photograph/liebiao_xiaomimix.jpg', '1', '26', '1');
INSERT INTO `goods` VALUES ('13', '小米5s', '5月9日-10日，下单立减200元', '000001999', 'static/images/photograph/liebiao_hongmin42.jpg', '1', '26', '1');
INSERT INTO `goods` VALUES ('18', '小米支架式自拍杆', '小米支架式自拍杆', '000000089', 'static/images/photograph/peijian9.jpg', '1', '33', '1');
INSERT INTO `goods` VALUES ('19', '米家随身风扇', '米家随身风扇', '000000019', 'static/images/photograph/peijian8.jpg', '1', '27', '1');
INSERT INTO `goods` VALUES ('20', '红米4X 高透软胶保护套', '红米4X 高透软胶保护套', '000000059', 'static/images/photograph/peijian9.jpg', '1', '27', '1');
INSERT INTO `goods` VALUES ('21', '小米手机4c 小米4c 智能', '小米手机4c 小米4c 智能', '000000036', 'static/images/photograph/peijian3.jpg', '1', '30', '1');
INSERT INTO `goods` VALUES ('22', '华为耳机', '华为耳机', '000000198', 'static/images/photograph/peijian10.jpg', '1', '32', '1');
INSERT INTO `goods` VALUES ('23', '华为平板', '测试', '000003999', 'static/images/photograph/liebiao_hongmin4.jpg', '1', '29', '1');
INSERT INTO `goods` VALUES ('24', '平衡车', '平衡车', '000000900', 'static/images/photograph/pinghengche.jpg', '1', '27', '1');
INSERT INTO `goods` VALUES ('25', '配件', '测试', '000000090', 'static/images/photograph/peijian10.jpg', '1', '31', '1');

-- ----------------------------
-- Table structure for myorder
-- ----------------------------
DROP TABLE IF EXISTS `myorder`;
CREATE TABLE `myorder` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` int(3) unsigned NOT NULL,
  `user_id` int(3) unsigned NOT NULL,
  `status` int(1) unsigned NOT NULL DEFAULT '1' COMMENT '订单状态：1.未支付、2.已支付、3.发货中、4.已接收',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of myorder
-- ----------------------------
INSERT INTO `myorder` VALUES ('22', '24', '1', '2');
INSERT INTO `myorder` VALUES ('20', '21', '1', '3');
INSERT INTO `myorder` VALUES ('19', '23', '1', '1');
INSERT INTO `myorder` VALUES ('21', '14', '8', '1');

-- ----------------------------
-- Table structure for trolley
-- ----------------------------
DROP TABLE IF EXISTS `trolley`;
CREATE TABLE `trolley` (
  `id` int(3) unsigned NOT NULL AUTO_INCREMENT,
  `goods_id` int(3) NOT NULL,
  `user_id` int(3) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of trolley
-- ----------------------------
INSERT INTO `trolley` VALUES ('29', '23', '1');
INSERT INTO `trolley` VALUES ('31', '14', '8');
INSERT INTO `trolley` VALUES ('32', '24', '1');
INSERT INTO `trolley` VALUES ('30', '21', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL,
  `rule` int(1) unsigned NOT NULL DEFAULT '1',
  `address` varchar(30) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123', '2', '地球', '12312312312');
INSERT INTO `user` VALUES ('8', 'test', '123', '1', '广东省', '12312312312');
