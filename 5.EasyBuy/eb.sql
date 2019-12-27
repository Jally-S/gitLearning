/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : eb

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-04-03 11:27:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for easybuy_news
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_news`;
CREATE TABLE `easybuy_news` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(40) NOT NULL COMMENT '标题',
  `content` varchar(1000) NOT NULL COMMENT '内容',
  `createTime` varchar(10) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___C63B5EE724927208` (`id`),
  UNIQUE KEY `UQ__EASYBUY___C12AD09D276EDEB3` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=704 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_news
-- ----------------------------
INSERT INTO `easybuy_news` VALUES ('531', '会员特惠月开始了', '会员特惠月开始了,亲们赶快下单啊,不到剁手誓不罢休啊,不到离婚誓不清空购物车啊。更多优惠，尽在易买网。', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('597', '迎双旦促销大酬宾', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('649', '加入会员，赢千万大礼包', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('650', '新年不夜天，通宵也是开张了', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('651', '积分兑换开始了', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('653', '团购阿迪1折起', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('664', '最新酷睿笔记本', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('675', 'aa', '012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('676', 'ResultR', 'ResultResultResultResultResu', '2016-03-28');
INSERT INTO `easybuy_news` VALUES ('677', '会员特惠月开始了1', '会员特惠月开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('678', '迎双旦促销大酬宾2', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('679', '加入会员，赢千万大礼包3', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('680', '新年不夜天，通宵也是开张了4', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('681', '积分兑换开始了5', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('682', '团购阿迪1折起6', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('683', '最新酷睿笔记本7', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('684', 'aa8', '012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('685', 'ResultR9', 'ResultResultResultResultResu', '2016-03-28');
INSERT INTO `easybuy_news` VALUES ('686', '会员特惠月开始了11', '会员特惠月开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('687', '迎双旦促销大酬宾21', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('688', '加入会员，赢千万大礼包31', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('689', '新年不夜天，通宵也是开张了41', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('690', '积分兑换开始了51', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('691', '团购阿迪1折起61', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('692', '最新酷睿笔记本71', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('693', 'aa81', '012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('694', 'ResultR91', 'ResultResultResultResultResu', '2016-03-28');
INSERT INTO `easybuy_news` VALUES ('695', '会员特惠月开始了111', '会员特惠月开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('696', '迎双旦促销大酬宾211', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('697', '加入会员，赢千万大礼包311', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('698', '新年不夜天，通宵也是开张了411', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('699', '积分兑换开始了511', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('700', '团购阿迪1折起611', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('701', '最新酷睿笔记本711', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('702', 'aa811', '012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('703', 'ResultR911', 'ResultResultResultResultResu', '2016-03-28');

-- ----------------------------
-- Table structure for easybuy_order
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order`;
CREATE TABLE `easybuy_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(255) DEFAULT NULL COMMENT '用户主键',
  `loginName` varchar(255) DEFAULT NULL,
  `userAddress` varchar(255) DEFAULT NULL COMMENT '用户地址',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `cost` float DEFAULT NULL COMMENT '总消费',
  `serialNumber` varchar(255) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_order
-- ----------------------------
INSERT INTO `easybuy_order` VALUES ('1', '18', 'shangzezhong', '北京市花园路小区', '2016-06-02 14:51:46', '1721', '60B7487F47F9434EAA5FD1D9E22CB06C');
INSERT INTO `easybuy_order` VALUES ('2', '18', 'shangzezhong', '北京市海淀区成府路', '2016-06-02 14:52:49', '8596', '8EF5E1557D55413781658A65FC301B8A');
INSERT INTO `easybuy_order` VALUES ('3', '2', 'admin', '北京市海淀区大有庄', '2016-06-03 11:41:09', '456', '51718726C1274CC59504AB4E6FD64BA0');
INSERT INTO `easybuy_order` VALUES ('4', '21', 'zhangsan', '1111111111111111111111111', '2018-01-15 12:18:17', '152', '9634C93F9B4E4E45B417D63B080FFCBF');
INSERT INTO `easybuy_order` VALUES ('5', '21', 'zhangsan', '1111111111111111111111111', '2018-01-15 17:21:59', '152', '958418E1549E4728904DE184D46B555E');
INSERT INTO `easybuy_order` VALUES ('6', '21', 'zhangsan', '1111111111111111111111111', '2018-01-15 17:23:12', '5896', '72298C90674440B183724A057F10E73E');
INSERT INTO `easybuy_order` VALUES ('7', '22', 'zhangsan2', '北京市海淀区成府路', '2018-01-16 09:03:04', '720', 'A0C4E82718964549B70145877CF8594A');
INSERT INTO `easybuy_order` VALUES ('8', '22', 'zhangsan2', '北京市海淀区成府路', '2018-01-16 09:16:50', '152', '064502A9DFA84E37A13415510BE01FE7');
INSERT INTO `easybuy_order` VALUES ('9', '22', 'zhangsan2', '北京市海淀区成府路', '2018-01-16 09:19:27', '152', '8821E8A06973486282D914D2C486E254');
INSERT INTO `easybuy_order` VALUES ('11', '22', 'zhangsan2', 'fwref', '2018-01-25 21:08:10', '589', '4AAF459602D928F8590F72B9DF6B7C06');
INSERT INTO `easybuy_order` VALUES ('12', '22', 'zhangsan2', '6666aaa', '2018-01-25 21:41:47', '589', 'B61C0DB7A4B580C7534055AFE4146361');
INSERT INTO `easybuy_order` VALUES ('13', '23', 'lili', 'sdgfvdfe', '2018-01-26 13:36:18', '11732', 'B9E3B00879089D6D47CC090C809846D0');
INSERT INTO `easybuy_order` VALUES ('14', '23', 'lili', 'sdgfvdfe', '2018-01-26 13:52:10', '859', 'CD9EE67FC94A697604A3FB6C12DB12CB');
INSERT INTO `easybuy_order` VALUES ('15', '23', 'lili', 'sdgfvdfe', '2018-01-26 14:08:08', '5866', 'D44D9148A3BD8A6BAB4E861A74984FC5');
INSERT INTO `easybuy_order` VALUES ('16', '23', 'lili', 'sdgfvdfe', '2018-01-26 14:10:13', '152', '220D1E2017F0C4F87F617B9F8D351069');
INSERT INTO `easybuy_order` VALUES ('17', '22', 'zhangsan2', 'fwref', '2018-01-26 14:27:05', '152', '74C6E374B3D1F4585AA4C6F9E8C4FE1F');
INSERT INTO `easybuy_order` VALUES ('18', '22', 'zhangsan2', '6666aaa', '2018-01-26 14:28:33', '1011', '02036E86557AD7C54F6F971DD1082F4B');
INSERT INTO `easybuy_order` VALUES ('19', '22', 'zhangsan2', '6666aaa', '2018-01-26 14:31:08', '589', '7DD4201CED4DC8BDF0A773DF4480E892');
INSERT INTO `easybuy_order` VALUES ('20', '22', 'zhangsan2', '6666aaa', '2018-01-26 15:11:15', '589', 'F3F2798056D2A37F6E0056E35F998692');
INSERT INTO `easybuy_order` VALUES ('21', '21', 'zhangsan', '1111111111111111111111111', '2018-01-26 15:16:21', '589', '09B5236441926A5CABD268EADF50D3E3');
INSERT INTO `easybuy_order` VALUES ('22', '22', 'zhangsan2', '6666aaa', '2018-01-26 15:17:27', '5866', '4137EC7873A8329568D70F667F2A93A2');
INSERT INTO `easybuy_order` VALUES ('23', '2', 'admin', '北京市海淀区大有庄', '2018-01-26 15:36:46', '152', 'EB66A9EBD4B54281AB271B0A0B78FEED');
INSERT INTO `easybuy_order` VALUES ('24', '23', 'lili', 'sdgfvdfe', '2018-01-26 19:02:16', '589', '963E289AEFE1BD1E96ADF5E290A3148E');
INSERT INTO `easybuy_order` VALUES ('25', '23', 'lili', 'sdgfvdfe', '2018-01-30 21:28:06', '1560', '590C2BDF534A378754F7DED3A8DE9C2D');
INSERT INTO `easybuy_order` VALUES ('26', '23', 'lili', 'qwe', '2018-01-30 21:29:45', '589', '73002A2C6C2C28D6599269B2A48D5534');
INSERT INTO `easybuy_order` VALUES ('27', '2', 'admin', '北京市海淀区大有庄', '2018-01-30 21:39:42', '7614', '4934EBCC2666140BE9105D7BA25A313E');
INSERT INTO `easybuy_order` VALUES ('28', '23', 'lili', 'sdgfvdfe', '2018-02-27 20:49:39', '152', '7DD13911941EA46026AA287AE088A678');
INSERT INTO `easybuy_order` VALUES ('29', '23', 'lili', 'å°å¯¨è¥¿è·¯', '2018-03-12 09:20:23', '1374', '0801384B4DCC188873EE52B052375AD3');
INSERT INTO `easybuy_order` VALUES ('30', '23', 'lili', 'å°å¯¨è¥¿è·¯', '2018-03-26 10:23:37', '456', '571A2E4A2BDB71697CD0BF2E2AF76078');
INSERT INTO `easybuy_order` VALUES ('31', '23', 'lili', 'sdgfvdfe', '2018-04-03 11:19:44', '589', 'B9022A0AAE10977927D9F52037FDC314');

-- ----------------------------
-- Table structure for easybuy_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order_detail`;
CREATE TABLE `easybuy_order_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderId` int(10) NOT NULL COMMENT '订单主键',
  `productId` int(10) NOT NULL COMMENT '商品主键',
  `quantity` int(10) NOT NULL COMMENT '数量',
  `cost` float NOT NULL COMMENT '消费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___66E1BD8E2F10007B` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_order_detail
-- ----------------------------
INSERT INTO `easybuy_order_detail` VALUES ('1', '1', '733', '5', '760');
INSERT INTO `easybuy_order_detail` VALUES ('2', '1', '734', '4', '608');
INSERT INTO `easybuy_order_detail` VALUES ('3', '1', '735', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('4', '1', '738', '1', '45');
INSERT INTO `easybuy_order_detail` VALUES ('5', '1', '739', '1', '156');
INSERT INTO `easybuy_order_detail` VALUES ('6', '2', '755', '1', '8596');
INSERT INTO `easybuy_order_detail` VALUES ('7', '3', '733', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('8', '3', '734', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('9', '3', '735', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('10', '4', '733', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('11', '5', '733', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('12', '6', '768', '1', '5896');
INSERT INTO `easybuy_order_detail` VALUES ('13', '7', '748', '1', '520');
INSERT INTO `easybuy_order_detail` VALUES ('14', '7', '766', '1', '200');
INSERT INTO `easybuy_order_detail` VALUES ('15', '8', '733', '1', '152');
INSERT INTO `easybuy_order_detail` VALUES ('16', '9', '734', '1', '152');

-- ----------------------------
-- Table structure for easybuy_product
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product`;
CREATE TABLE `easybuy_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `description` varchar(1024) DEFAULT NULL COMMENT '描述',
  `price` float NOT NULL COMMENT '价格',
  `stock` int(10) NOT NULL COMMENT '库存',
  `categoryLevel1Id` int(10) DEFAULT NULL COMMENT '分类1',
  `categoryLevel2Id` int(10) DEFAULT NULL COMMENT '分类2',
  `categoryLevel3Id` int(10) DEFAULT NULL COMMENT '分类3',
  `fileName` varchar(200) DEFAULT NULL COMMENT '文件名称',
  `isDelete` int(1) DEFAULT '0' COMMENT '是否删除(1：删除 0：未删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___94F6E55132E0915F` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=777 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_product
-- ----------------------------
INSERT INTO `easybuy_product` VALUES ('733', '香奈儿222', '订单', '152', '100', '548', '654', '655', 'baby_1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('734', '洗面奶', '', '152', '1', '548', '654', '655', 'baby_2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('735', '啫喱水', '', '152', '998', '548', '654', '655', 'baby_3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('736', '香水5852', '', '152', '1000', '548', '654', '655', 'baby_4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('737', '香水', '', '152', '111', '548', '654', '655', 'baby_5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('738', '润肤露', '', '45', '109', '548', '654', '655', 'baby_6.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('739', '洁面装', '', '156', '99', '548', '654', '655', 'bk_2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('740', '电饭锅', '', '158', '100', '628', '656', '659', 'bk_1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('741', '婴儿喂奶装', '', '569', '100', '632', '637', '653', 'bk_3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('742', '坚果套餐', '', '158', '1000', '660', '661', '662', 'bk_4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('743', '超甜蜜崭', '', '589', '1000', '660', '661', '663', 'bk_5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('744', '华为2566', '', '589', '1000', '670', '671', '672', 'de1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('745', '荣耀3C', '', '589', '100', '670', '671', '672', 'de2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('746', '小米手环', '', '963', '100', '670', '674', '675', 'de3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('747', '华为2265', '', '896', '1000', '670', '671', '673', 'de4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('748', '越南坚果', '', '520', '1', '660', '661', '662', 'de5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('749', '日本进口马桶', '', '5866', '100', '628', '657', '0', 'food_1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('750', '联想Y系列', '', '569', '1000', '670', '690', '691', 'food_2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('751', '脑白金1号', '', '589', '1000', '676', '677', '680', 'food_3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('752', '莫里斯按', '', '589', '1000', '676', '678', '0', 'food_4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('753', '三鹿好奶粉', '', '859', '100', '676', '679', '0', 'food_5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('754', '儿童牛奶', '', '5896', '100', '676', '679', '0', 'food_6.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('755', '软沙发', '', '8596', '99', '628', '696', '0', 'food_b1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('756', '收纳盒', '', '5966', '100', '628', '696', '0', 'food_b2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('757', '洗衣液', '', '58', '1000', '628', '696', '0', 'food_r.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('758', '红短沙发', '', '596', '123', '628', '696', '0', 'fre_1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('759', '新西兰奶粉', '', '5896', '100', '676', '679', '0', 'fre_2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('760', '婴儿车', '', '11000', '100', '681', '682', '687', 'fre_3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('761', '夏款婴儿车', '', '963', '100', '681', '682', '688', 'fre_4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('762', '抗压旅行箱', '', '569', '1000', '681', '683', '685', 'fre_5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('763', '透明手提箱', '', '8596', '1000', '681', '683', '684', 'fre_6.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('764', '婴儿果粉', '', '5896', '1000', '660', '661', '662', 'milk_1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('765', '椰子粉', '', '5963', '1000', '660', '661', '662', 'milk_2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('766', '坚果蛋糕', '', '200', '1', '660', '661', '663', 'milk_3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('767', '编制手提箱', '', '5896', '1000', '681', '682', '688', 'milk_4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('768', '纸箱', '', '5896', '1', '681', '682', '687', 'milk_5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('769', '健胃液', '', '152', '1000', '676', '679', '0', 'milk_6.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('770', '联想NTC', '', '8596', '100', '670', '671', '673', 'milk_7.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('771', '香水1', null, '100', '100', '548', '654', '655', 'milk_8.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('772', '香水2', null, '100', '100', '548', '654', '655', 'pro1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('773', '香水3', null, '100', '100', '548', '654', '655', 'pro2.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('774', '香水4', null, '100', '100', '548', '654', '655', 'pro3.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('775', '香水5', null, '100', '100', '548', '654', '655', 'pro4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('776', '香水6', null, '1', '1', '548', '654', '655', 'pro5.jpg', '0');

-- ----------------------------
-- Table structure for easybuy_product_category
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product_category`;
CREATE TABLE `easybuy_product_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `parentId` int(10) NOT NULL COMMENT '父级目录id',
  `type` int(11) DEFAULT NULL COMMENT '级别(1:一级 2：二级 3：三级)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___9EC2A4E236B12243` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=697 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_product_category
-- ----------------------------
INSERT INTO `easybuy_product_category` VALUES ('548', '化妆品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('628', '家用商品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('654', '面部护理', '548', '2');
INSERT INTO `easybuy_product_category` VALUES ('655', '少女派', '654', '3');
INSERT INTO `easybuy_product_category` VALUES ('656', '餐具', '628', '2');
INSERT INTO `easybuy_product_category` VALUES ('657', '卫具', '628', '2');
INSERT INTO `easybuy_product_category` VALUES ('658', '叉子', '656', '3');
INSERT INTO `easybuy_product_category` VALUES ('659', '锅', '656', '3');
INSERT INTO `easybuy_product_category` VALUES ('660', '进口食品,生鲜', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('661', '零食/糖果/巧克力', '660', '2');
INSERT INTO `easybuy_product_category` VALUES ('662', '坚果', '661', '3');
INSERT INTO `easybuy_product_category` VALUES ('663', '蜜饯', '661', '3');
INSERT INTO `easybuy_product_category` VALUES ('669', '孕期教育', '546', '3');
INSERT INTO `easybuy_product_category` VALUES ('670', '电子商品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('671', '手机', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('672', '华为手机', '671', '3');
INSERT INTO `easybuy_product_category` VALUES ('673', '联想手机', '671', '3');
INSERT INTO `easybuy_product_category` VALUES ('674', '手环', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('675', '小米手环', '674', '3');
INSERT INTO `easybuy_product_category` VALUES ('676', '保健食品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('677', '老年保健品', '676', '2');
INSERT INTO `easybuy_product_category` VALUES ('678', '中年营养品', '676', '2');
INSERT INTO `easybuy_product_category` VALUES ('679', '儿童保健品', '676', '2');
INSERT INTO `easybuy_product_category` VALUES ('680', '脑白金', '677', '3');
INSERT INTO `easybuy_product_category` VALUES ('681', '箱包', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('682', '旅行箱', '681', '2');
INSERT INTO `easybuy_product_category` VALUES ('683', '手提箱', '681', '2');
INSERT INTO `easybuy_product_category` VALUES ('684', '大型', '683', '3');
INSERT INTO `easybuy_product_category` VALUES ('685', '小型', '683', '3');
INSERT INTO `easybuy_product_category` VALUES ('686', '中型', '683', '3');
INSERT INTO `easybuy_product_category` VALUES ('687', '大型', '682', '3');
INSERT INTO `easybuy_product_category` VALUES ('688', '中型', '682', '3');
INSERT INTO `easybuy_product_category` VALUES ('689', '小型', '682', '3');
INSERT INTO `easybuy_product_category` VALUES ('690', '电脑', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('691', '联想电脑', '690', '3');
INSERT INTO `easybuy_product_category` VALUES ('692', '刀叉', '656', '3');
INSERT INTO `easybuy_product_category` VALUES ('693', '碗筷', '656', '3');
INSERT INTO `easybuy_product_category` VALUES ('696', '客厅专用', '628', '2');

-- ----------------------------
-- Table structure for easybuy_user
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_user`;
CREATE TABLE `easybuy_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `loginName` varchar(255) NOT NULL COMMENT '登录名',
  `userName` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `sex` int(2) NOT NULL DEFAULT '1' COMMENT '性别(1:男 0：女)',
  `identityCode` varchar(60) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(80) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `type` int(2) DEFAULT '0' COMMENT '类型（1：后台 0:前台）',
  `fileName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___C96109CC3A81B327` (`loginName`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_user
-- ----------------------------
INSERT INTO `easybuy_user` VALUES ('2', 'admin', '系统管理员', '123123', '0', '130406198302141869', 'hello11@bdqn.com', '1583233515', '1', '7.jpg');
INSERT INTO `easybuy_user` VALUES ('10', 'cgn', '程广宁', '123', '1', '140225189987854589', '1044732267@qq.com', '13366055011', '0', '2.jpg');
INSERT INTO `easybuy_user` VALUES ('11', 'hyl', '韩语良', '1231', '1', '140225198874584539', '1044732267@qq.com', '13366055010', '0', '3.jpg');
INSERT INTO `easybuy_user` VALUES ('12', 'ck', '陈康', '123', '1', '140225189987854589', '1044732267@qq.com', '13366055010', '0', '4.jpg');
INSERT INTO `easybuy_user` VALUES ('13', 'kys', '康有沈', '123', '1', '1402251985512541525', '1044732267@qq.com', '13366055010', '0', '5.jpg');
INSERT INTO `easybuy_user` VALUES ('14', 'sb', '沈白', '123', '1', '140225158987854589', '1044732267@qq.com', '13366055010', '0', '6.jpg');
INSERT INTO `easybuy_user` VALUES ('15', 'lb', '李白', '123', '1', '140225189987854589', '10447322658@qq.com', '1336998554', '0', '6.jpg');
INSERT INTO `easybuy_user` VALUES ('16', 'lgw', '李高伟', '123123', '1', '140225189987854589', '1011322658@qq.com', '1336998554', '0', '7.jpg');
INSERT INTO `easybuy_user` VALUES ('18', 'shangzezhong', '尚泽忠', '123123', '1', '140225198810013745', '1044888844@qq.com', '13366528458', '0', '8.jpg');
INSERT INTO `easybuy_user` VALUES ('19', 'liguangliang', '李光亮', '123123', '1', '140225198841547785', '1047777@qq.com', '13366055048', '0', '9.jpg');
INSERT INTO `easybuy_user` VALUES ('20', 'szz', '李光亮', '123123', '1', '140225198810013748', '1044732267@qq.com', '13366555010', '1', '10.jpg');
INSERT INTO `easybuy_user` VALUES ('21', 'zhangsan', '李四', '123123', '0', '610456789098765432', '163@163.com', '13523456789', '0', '5.jpg');
INSERT INTO `easybuy_user` VALUES ('22', 'zhangsan2', '张三', '123123', '0', '610999999999999999', '123123123@163.com', '123123', '0', '5.jpg');
INSERT INTO `easybuy_user` VALUES ('23', 'lili', '丽丽', '123123', '0', '111111111111111111', '15029180718@163.com', '15029180718', '0', '5.jpg');
INSERT INTO `easybuy_user` VALUES ('24', 'qwq', '榨取', '123121321', '1', '111111111111111111', '15029180718@163.com', '15029180718', '0', null);
INSERT INTO `easybuy_user` VALUES ('25', 'sdfsds', '撒房产税', '123123', '1', '530381199409273387', '15029180718@163.com', '15029180718', '0', null);

-- ----------------------------
-- Table structure for easybuy_user_address
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_user_address`;
CREATE TABLE `easybuy_user_address` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userId` int(255) DEFAULT NULL COMMENT '用户主键',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `isDefault` int(2) DEFAULT '0' COMMENT '是否是默认地址（1:是 0否）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of easybuy_user_address
-- ----------------------------
INSERT INTO `easybuy_user_address` VALUES ('11', '2', '北京市海淀区大有庄', null, '0', '朋友家');
INSERT INTO `easybuy_user_address` VALUES ('12', '2', '北京市海淀区大有庄', null, '0', '女朋友公司');
INSERT INTO `easybuy_user_address` VALUES ('13', '9', '北京市西直门大桥芬兰国际大厦', null, '0', '女朋友地址');
INSERT INTO `easybuy_user_address` VALUES ('14', '18', '北京市花园路小区', null, '0', '家里');
INSERT INTO `easybuy_user_address` VALUES ('15', '18', '北京市海淀区成府路', null, '0', '公司');
INSERT INTO `easybuy_user_address` VALUES ('16', '21', '1111111111111111111111111', '2018-01-15 12:18:16', '0', '11');
INSERT INTO `easybuy_user_address` VALUES ('17', '22', '个v风格v', '2018-01-16 09:03:04', '0', '123');
INSERT INTO `easybuy_user_address` VALUES ('18', '22', '6666aaa', '2018-01-16 09:16:50', '0', '家');
INSERT INTO `easybuy_user_address` VALUES ('23', '2', 'ewrfw', '2018-01-25 17:03:03', '0', 'ref');
INSERT INTO `easybuy_user_address` VALUES ('28', '22', 'fwref', '2018-01-25 21:08:10', '0', 'ewfwr');
INSERT INTO `easybuy_user_address` VALUES ('29', '23', 'sdgfvdfe', '2018-01-26 13:36:18', '0', 'dsvdf');
INSERT INTO `easybuy_user_address` VALUES ('30', '23', 'qwe', '2018-01-30 21:29:45', '0', 'dsdds');
INSERT INTO `easybuy_user_address` VALUES ('31', '23', 'å°å¯¨è¥¿è·¯', '2018-03-12 09:20:23', '0', 'å¬å¸');
INSERT INTO `easybuy_user_address` VALUES ('32', '23', 'å°å¯¨è¥¿è·¯', '2018-03-26 10:23:37', '0', 'å¬å¸');
