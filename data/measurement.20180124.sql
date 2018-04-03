/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : localhost:3633
Source Database       : measurement

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-01-24 10:19:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `accuracy`
-- ----------------------------
DROP TABLE IF EXISTS `accuracy`;
CREATE TABLE `accuracy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT 'NULL',
  `value` varchar(255) DEFAULT 'NULL',
  `weight` int(11) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `instrument_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr0n8mffkilct5nxtn2cwmum7e` (`create_user_id`),
  KEY `FKrcwh8pvlvwwjg49dc1stg8by6` (`instrument_type_id`),
  CONSTRAINT `FKr0n8mffkilct5nxtn2cwmum7e` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKrcwh8pvlvwwjg49dc1stg8by6` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of accuracy
-- ----------------------------
INSERT INTO `accuracy` VALUES ('3', '', '', 'Ⅱ₁₀', '0', null, '2');
INSERT INTO `accuracy` VALUES ('4', '', '', 'Ⅱ₈', '2', null, '2');
INSERT INTO `accuracy` VALUES ('5', '', '', 'Ⅱ₉', '1', null, '2');
INSERT INTO `accuracy` VALUES ('6', '', '', 'Ⅰ₄', '6', null, '2');
INSERT INTO `accuracy` VALUES ('7', '', '', 'Ⅰ₃', '7', null, '2');
INSERT INTO `accuracy` VALUES ('8', '', '', 'Ⅰ₅', '5', null, '2');
INSERT INTO `accuracy` VALUES ('9', '', '', 'Ⅰ₂', '8', null, '2');
INSERT INTO `accuracy` VALUES ('10', '', '', 'Ⅰ₆', '4', null, '2');
INSERT INTO `accuracy` VALUES ('11', '', '', 'Ⅰ₇', '3', null, '2');
INSERT INTO `accuracy` VALUES ('12', '', '', 'Ⅰ₁', '9', null, '2');
INSERT INTO `accuracy` VALUES ('13', '', '', 'Ⅳ', '0', null, '3');
INSERT INTO `accuracy` VALUES ('14', '', '', 'Ⅲ', '1', null, '3');
INSERT INTO `accuracy` VALUES ('15', '', '', 'Ⅱ', '2', null, '3');
INSERT INTO `accuracy` VALUES ('16', '', '', 'Ⅰ', '3', null, '3');
INSERT INTO `accuracy` VALUES ('17', '', '', '1.6（1.5）级', '2', null, '4');
INSERT INTO `accuracy` VALUES ('18', '', '', '1.0级', '3', null, '4');
INSERT INTO `accuracy` VALUES ('19', '', '', '4级', '0', null, '4');
INSERT INTO `accuracy` VALUES ('20', '', '', '2.5级', '1', null, '4');
INSERT INTO `accuracy` VALUES ('21', '', '', 'Ⅳ', '0', null, '5');
INSERT INTO `accuracy` VALUES ('22', '', '', 'Ⅲ', '1', null, '5');
INSERT INTO `accuracy` VALUES ('23', '', '', 'Ⅱ级', '0', null, '6');
INSERT INTO `accuracy` VALUES ('24', '', '', 'Ⅰ级', '1', null, '6');
INSERT INTO `accuracy` VALUES ('25', '', '', '-------', '0', null, '7');
INSERT INTO `accuracy` VALUES ('26', '', '', '------', '0', null, '8');
INSERT INTO `accuracy` VALUES ('27', '', '', '10℃', '0', null, '9');
INSERT INTO `accuracy` VALUES ('28', '', '', '0.1℃', '6', null, '9');
INSERT INTO `accuracy` VALUES ('29', '', '', '5℃', '1', null, '9');
INSERT INTO `accuracy` VALUES ('30', '', '', '0.2℃', '5', null, '9');
INSERT INTO `accuracy` VALUES ('31', '', '', '0.02℃', '7', null, '9');
INSERT INTO `accuracy` VALUES ('32', '', '', '1℃', '3', null, '9');
INSERT INTO `accuracy` VALUES ('33', '', '', '', '9', null, '9');
INSERT INTO `accuracy` VALUES ('34', '', '', '2℃', '2', null, '9');
INSERT INTO `accuracy` VALUES ('35', '', '', '0.5℃', '4', null, '9');
INSERT INTO `accuracy` VALUES ('36', '', '', '0.01℃', '8', null, '9');
INSERT INTO `accuracy` VALUES ('38', '', '', '------', '0', null, '11');
INSERT INTO `accuracy` VALUES ('39', '', '', '------', '0', null, '12');
INSERT INTO `accuracy` VALUES ('40', '', '', '------', '0', null, '13');
INSERT INTO `accuracy` VALUES ('41', '', '', '------', '0', null, '14');
INSERT INTO `accuracy` VALUES ('42', '', '', '------', '0', null, '15');
INSERT INTO `accuracy` VALUES ('43', '', '', 'F₁', '6', null, '16');
INSERT INTO `accuracy` VALUES ('44', '', '', 'M₃', '0', null, '16');
INSERT INTO `accuracy` VALUES ('45', '', '', 'M₁', '4', null, '16');
INSERT INTO `accuracy` VALUES ('46', '', '', 'F₂', '5', null, '16');
INSERT INTO `accuracy` VALUES ('47', '', '', 'M₂', '2', null, '16');
INSERT INTO `accuracy` VALUES ('48', '', '', 'M₂₃', '1', null, '16');
INSERT INTO `accuracy` VALUES ('49', '', '', 'M₁₂', '3', null, '16');
INSERT INTO `accuracy` VALUES ('52', '', '', 'Ⅳ', '0', null, '18');
INSERT INTO `accuracy` VALUES ('53', '', '', 'Ⅲ', '1', null, '18');
INSERT INTO `accuracy` VALUES ('54', '', '', 'Ⅳ', '0', null, '19');
INSERT INTO `accuracy` VALUES ('55', '', '', 'Ⅲ', '1', null, '19');
INSERT INTO `accuracy` VALUES ('56', '', '', 'Ⅲ', '0', null, '20');
INSERT INTO `accuracy` VALUES ('57', '', '', 'Ⅲ', '0', null, '21');
INSERT INTO `accuracy` VALUES ('58', '', '', '±0.5%', '0', null, '22');
INSERT INTO `accuracy` VALUES ('59', '', '', '±0.5%', '0', null, '23');
INSERT INTO `accuracy` VALUES ('60', '', '', '±2.0mL', '0', null, '24');
INSERT INTO `accuracy` VALUES ('61', '', '', '±0.3%', '0', null, '25');
INSERT INTO `accuracy` VALUES ('62', '', '', '二等', '1', null, '26');
INSERT INTO `accuracy` VALUES ('63', '', '', '工作用', '0', null, '26');
INSERT INTO `accuracy` VALUES ('64', '', '', '一等', '2', null, '26');
INSERT INTO `accuracy` VALUES ('65', '', '', '二等', '1', null, '27');
INSERT INTO `accuracy` VALUES ('66', '', '', '一等', '2', null, '27');
INSERT INTO `accuracy` VALUES ('67', '', '', '工作用', '0', null, '27');
INSERT INTO `accuracy` VALUES ('68', '', '', '工作用', '0', null, '28');
INSERT INTO `accuracy` VALUES ('69', '', '', '一等', '2', null, '28');
INSERT INTO `accuracy` VALUES ('70', '', '', '二等', '1', null, '28');
INSERT INTO `accuracy` VALUES ('71', '', '', '工作用', '0', null, '29');
INSERT INTO `accuracy` VALUES ('72', '', '', '一等', '2', null, '29');
INSERT INTO `accuracy` VALUES ('73', '', '', '二等', '1', null, '29');
INSERT INTO `accuracy` VALUES ('74', '', '', 'B级', '0', null, '30');
INSERT INTO `accuracy` VALUES ('75', '', '', 'A级', '1', null, '30');
INSERT INTO `accuracy` VALUES ('76', '', '', '±2%', '0', null, '31');
INSERT INTO `accuracy` VALUES ('77', '', '', '±0.1%', '4', null, '22');
INSERT INTO `accuracy` VALUES ('78', '', '', '±0.2%', '3', null, '22');
INSERT INTO `accuracy` VALUES ('79', '', '', '±0.4%', '1', null, '22');
INSERT INTO `accuracy` VALUES ('80', '', '', '±0.3%', '2', null, '22');
INSERT INTO `accuracy` VALUES ('81', '', '', '0.5级', '3', null, '32');
INSERT INTO `accuracy` VALUES ('82', '', '', '0.2级', '6', null, '32');
INSERT INTO `accuracy` VALUES ('83', '', '', '0.3级', '4', null, '32');
INSERT INTO `accuracy` VALUES ('84', '', '', '0.25级', '5', null, '32');
INSERT INTO `accuracy` VALUES ('85', '', '', '1.5级', '1', null, '32');
INSERT INTO `accuracy` VALUES ('86', '', '', '2.0级', '0', null, '32');
INSERT INTO `accuracy` VALUES ('87', '', '', '1.0级', '2', null, '32');
INSERT INTO `accuracy` VALUES ('88', '', '', '1.5级', '2', null, '33');
INSERT INTO `accuracy` VALUES ('89', '', '', '0.3级', '5', null, '33');
INSERT INTO `accuracy` VALUES ('90', '', '', '0.5级', '4', null, '33');
INSERT INTO `accuracy` VALUES ('91', '', '', '2.0级', '1', null, '33');
INSERT INTO `accuracy` VALUES ('92', '', '', '0.25级', '6', null, '33');
INSERT INTO `accuracy` VALUES ('93', '', '', '1.0级', '3', null, '33');
INSERT INTO `accuracy` VALUES ('94', '', '', '0.2级', '7', null, '33');
INSERT INTO `accuracy` VALUES ('103', '', '', '1.0级', '3', null, '35');
INSERT INTO `accuracy` VALUES ('104', '', '', '2.5级', '1', null, '35');
INSERT INTO `accuracy` VALUES ('105', '', '', '1.6（1.5）级', '2', null, '35');
INSERT INTO `accuracy` VALUES ('106', '', '', '4.0级', '0', null, '35');
INSERT INTO `accuracy` VALUES ('107', '', '', '------', '0', null, '36');
INSERT INTO `accuracy` VALUES ('108', '', '', '4.0级', '0', null, '37');
INSERT INTO `accuracy` VALUES ('109', '', '', '2.5级', '1', null, '37');
INSERT INTO `accuracy` VALUES ('110', '', '', '1.6（1.5）级', '2', null, '37');
INSERT INTO `accuracy` VALUES ('111', '', '', '1.0级', '3', null, '37');
INSERT INTO `accuracy` VALUES ('112', '', '', '±0.5MPa', '0', null, '38');
INSERT INTO `accuracy` VALUES ('113', '', '', '±0.5MPa', '0', null, '39');
INSERT INTO `accuracy` VALUES ('114', '', '', '-4%~1%', '0', null, '40');
INSERT INTO `accuracy` VALUES ('115', '', '', '±1%', '0', null, '41');
INSERT INTO `accuracy` VALUES ('116', '', '', '3级', '0', null, '42');
INSERT INTO `accuracy` VALUES ('117', '', '', '1级', '2', null, '42');
INSERT INTO `accuracy` VALUES ('118', '', '', '2级', '1', null, '42');
INSERT INTO `accuracy` VALUES ('119', '', '', '0.5级', '3', null, '42');
INSERT INTO `accuracy` VALUES ('120', '', '', '2级', '1', null, '43');
INSERT INTO `accuracy` VALUES ('121', '', '', '1级', '2', null, '43');
INSERT INTO `accuracy` VALUES ('122', '', '', '0.5级', '3', null, '43');
INSERT INTO `accuracy` VALUES ('123', '', '', '3级', '0', null, '43');
INSERT INTO `accuracy` VALUES ('124', '', '', '0.2s级', '1', null, '44');
INSERT INTO `accuracy` VALUES ('125', '', '', '0.5级', '0', null, '44');
INSERT INTO `accuracy` VALUES ('126', '', '', '0.2级', '2', null, '44');
INSERT INTO `accuracy` VALUES ('127', '', '', '0.1级', '3', null, '44');
INSERT INTO `accuracy` VALUES ('128', '', '', '0.2级', '3', null, '45');
INSERT INTO `accuracy` VALUES ('129', '', '', '1级', '0', null, '45');
INSERT INTO `accuracy` VALUES ('130', '', '', '0.5级', '1', null, '45');
INSERT INTO `accuracy` VALUES ('131', '', '', '0.2s级', '2', null, '45');
INSERT INTO `accuracy` VALUES ('132', '', '', '0.1级', '4', null, '45');
INSERT INTO `accuracy` VALUES ('133', '', '', '0.5级', '1', null, '46');
INSERT INTO `accuracy` VALUES ('134', '', '', '1级', '0', null, '46');
INSERT INTO `accuracy` VALUES ('135', '', '', '0.2s级', '2', null, '46');
INSERT INTO `accuracy` VALUES ('136', '', '', '0.1级', '3', null, '46');
INSERT INTO `accuracy` VALUES ('137', '', '', '0.05级', '4', null, '46');
INSERT INTO `accuracy` VALUES ('138', '', '', '5级', '1', null, '47');
INSERT INTO `accuracy` VALUES ('139', '', '', '1级', '3', null, '47');
INSERT INTO `accuracy` VALUES ('140', '', '', '2级', '2', null, '47');
INSERT INTO `accuracy` VALUES ('141', '', '', '10级', '0', null, '47');
INSERT INTO `accuracy` VALUES ('142', '', '', '10级', '0', null, '48');
INSERT INTO `accuracy` VALUES ('143', '', '', '1级', '3', null, '48');
INSERT INTO `accuracy` VALUES ('144', '', '', '2级', '2', null, '48');
INSERT INTO `accuracy` VALUES ('145', '', '', '5级', '1', null, '48');
INSERT INTO `accuracy` VALUES ('146', '', '', '------', '0', null, '49');
INSERT INTO `accuracy` VALUES ('147', '', '', '------', '0', null, '50');
INSERT INTO `accuracy` VALUES ('148', '', '', '------', '0', null, '51');
INSERT INTO `accuracy` VALUES ('149', '', '', '------', '0', null, '52');
INSERT INTO `accuracy` VALUES ('150', '', '', '------', '0', null, '53');
INSERT INTO `accuracy` VALUES ('151', '', '', '------', '0', null, '54');
INSERT INTO `accuracy` VALUES ('152', '', '', '------', '0', null, '55');
INSERT INTO `accuracy` VALUES ('153', '', '', '------', '0', null, '56');
INSERT INTO `accuracy` VALUES ('154', '', '', '------', '0', null, '57');
INSERT INTO `accuracy` VALUES ('155', '', '------', '------', '0', null, '58');
INSERT INTO `accuracy` VALUES ('156', '', '', '------', '0', null, '59');
INSERT INTO `accuracy` VALUES ('157', '', '', '------', '0', null, '60');
INSERT INTO `accuracy` VALUES ('158', '', '', '------', '0', null, '61');
INSERT INTO `accuracy` VALUES ('159', '', '', '------', '0', null, '62');
INSERT INTO `accuracy` VALUES ('160', '', '', '------', '0', null, '63');
INSERT INTO `accuracy` VALUES ('161', '', '', '2级', '0', null, '64');
INSERT INTO `accuracy` VALUES ('162', '', '', '1级', '1', null, '64');
INSERT INTO `accuracy` VALUES ('163', '', '', '------', '0', null, '65');
INSERT INTO `accuracy` VALUES ('164', '', '', '1ppm', '0', null, '66');
INSERT INTO `accuracy` VALUES ('165', '', '', '0.1ppm', '1', null, '66');
INSERT INTO `accuracy` VALUES ('166', '', '', '0.01ppm', '2', null, '66');
INSERT INTO `accuracy` VALUES ('167', '', '', '0.1ppm', '0', null, '67');
INSERT INTO `accuracy` VALUES ('168', '', '', '0.1ppm', '1', null, '68');
INSERT INTO `accuracy` VALUES ('169', '', '', '1ppm', '0', null, '68');
INSERT INTO `accuracy` VALUES ('170', '', '', '0.1ppm', '1', null, '69');
INSERT INTO `accuracy` VALUES ('171', '', '', '1ppm', '0', null, '69');
INSERT INTO `accuracy` VALUES ('172', '', '', '0.001级', '4', null, '70');
INSERT INTO `accuracy` VALUES ('173', '', '', '0.1级', '1', null, '70');
INSERT INTO `accuracy` VALUES ('174', '', '', '0.01级', '3', null, '70');
INSERT INTO `accuracy` VALUES ('175', '', '', '0.02级', '2', null, '70');
INSERT INTO `accuracy` VALUES ('176', '', '', '0.2级', '0', null, '70');
INSERT INTO `accuracy` VALUES ('177', '', '', '±5%', '0', null, '71');
INSERT INTO `accuracy` VALUES ('178', '', '', '0.1%LEL', '0', null, '72');
INSERT INTO `accuracy` VALUES ('179', '', '', '0.01级', '0', null, '73');
INSERT INTO `accuracy` VALUES ('180', '', '', '0.001级', '1', null, '73');
INSERT INTO `accuracy` VALUES ('181', '', '', '±10%', '0', null, '74');
INSERT INTO `accuracy` VALUES ('182', '', '', '------', '0', null, '75');
INSERT INTO `accuracy` VALUES ('183', '', '', 'Ⅳ级', '0', null, '76');
INSERT INTO `accuracy` VALUES ('184', '', '', 'Ⅲ级', '1', null, '76');
INSERT INTO `accuracy` VALUES ('185', '', '', 'Ⅱ级', '2', null, '76');
INSERT INTO `accuracy` VALUES ('186', '', '', 'Ⅰ级', '3', null, '76');
INSERT INTO `accuracy` VALUES ('187', '', '', 'Ⅳ级', '0', null, '77');
INSERT INTO `accuracy` VALUES ('188', '', '', 'Ⅲ级', '1', null, '77');
INSERT INTO `accuracy` VALUES ('189', '', '', 'Ⅱ级', '2', null, '77');
INSERT INTO `accuracy` VALUES ('190', '', '', 'Ⅰ级', '3', null, '77');
INSERT INTO `accuracy` VALUES ('191', '', '', '分辨率1.5 cm-1', '0', null, '78');
INSERT INTO `accuracy` VALUES ('192', '', '', '分辨率1.0cm-1', '1', null, '78');
INSERT INTO `accuracy` VALUES ('193', '', '', '发射波长准确度±2nm', '0', null, '79');
INSERT INTO `accuracy` VALUES ('194', '', '', '铜检出限0.008μg/mL', '0', null, '80');
INSERT INTO `accuracy` VALUES ('195', '', '', '铜检出限0.006μg/mL', '1', null, '80');
INSERT INTO `accuracy` VALUES ('196', '', '', '------', '0', null, '81');
INSERT INTO `accuracy` VALUES ('197', '', '', '------', '0', null, '82');
INSERT INTO `accuracy` VALUES ('198', '', '', '------', '0', null, '83');
INSERT INTO `accuracy` VALUES ('199', '', '', '分辨率0.1L/min', '0', null, '84');
INSERT INTO `accuracy` VALUES ('200', '', '', '3%FS', '1', null, '85');
INSERT INTO `accuracy` VALUES ('201', '', '', '5%FS', '0', null, '85');
INSERT INTO `accuracy` VALUES ('202', '', '', '0.01mg/m³', '0', null, '86');
INSERT INTO `accuracy` VALUES ('203', '', '', '0.001mg/m³', '1', null, '86');
INSERT INTO `accuracy` VALUES ('204', '', '', '------', '0', null, '87');
INSERT INTO `accuracy` VALUES ('205', '', '', '------', '0', null, '88');
INSERT INTO `accuracy` VALUES ('206', '', '', '------', '0', null, '89');
INSERT INTO `accuracy` VALUES ('207', '', '', '1%', '0', null, '90');
INSERT INTO `accuracy` VALUES ('208', '', '', '------', '0', null, '91');
INSERT INTO `accuracy` VALUES ('209', '', '', '------', '0', null, '92');
INSERT INTO `accuracy` VALUES ('210', '', '', '±（0.06~0.25）m-1', '0', null, '93');
INSERT INTO `accuracy` VALUES ('211', '', '', '±（1+T10-3)s', '0', null, '94');
INSERT INTO `accuracy` VALUES ('212', '', '', '±1%', '0', null, '95');
INSERT INTO `accuracy` VALUES ('213', '', '', '±(0.25~0.50)m-1', '0', null, '96');
INSERT INTO `accuracy` VALUES ('214', '', '', '±(0.04~0.12)m-1', '0', null, '97');
INSERT INTO `accuracy` VALUES ('215', '', '', '±（1~2.75）dB', '0', null, '98');
INSERT INTO `accuracy` VALUES ('216', '', '', '±1%', '0', null, '99');
INSERT INTO `accuracy` VALUES ('217', '', '', '2级', '1', null, '100');
INSERT INTO `accuracy` VALUES ('218', '', '', '3级', '0', null, '100');
INSERT INTO `accuracy` VALUES ('219', '', '', '1级', '2', null, '100');
INSERT INTO `accuracy` VALUES ('220', '', '', 'Ⅲ级', '0', null, '101');
INSERT INTO `accuracy` VALUES ('221', '', '', 'Ⅲ级', '0', null, '102');
INSERT INTO `accuracy` VALUES ('222', '', '', '1级', '0', null, '103');
INSERT INTO `accuracy` VALUES ('223', '', '', '0.5级', '1', null, '103');
INSERT INTO `accuracy` VALUES ('224', '', '', '0.2级', '2', null, '103');
INSERT INTO `accuracy` VALUES ('225', '', '', '0.1级', '3', null, '103');
INSERT INTO `accuracy` VALUES ('226', '', '', '2.5级', '0', null, '33');
INSERT INTO `accuracy` VALUES ('227', '', '', '0.5级', '3', null, '104');
INSERT INTO `accuracy` VALUES ('228', '', '', '2.0级', '1', null, '104');
INSERT INTO `accuracy` VALUES ('229', '', '', '2.5级', '0', null, '104');
INSERT INTO `accuracy` VALUES ('230', '', '', '0.1级', '5', null, '104');
INSERT INTO `accuracy` VALUES ('231', '', '', '1.0级', '2', null, '104');
INSERT INTO `accuracy` VALUES ('232', '', '', '0.2级', '4', null, '104');
INSERT INTO `accuracy` VALUES ('233', '', '', '------', '0', null, '105');
INSERT INTO `accuracy` VALUES ('234', '', '', '------', '0', null, '106');
INSERT INTO `accuracy` VALUES ('235', '', '', '------', '0', null, '107');
INSERT INTO `accuracy` VALUES ('236', '', '', '------', '0', null, '108');
INSERT INTO `accuracy` VALUES ('237', '', '', '铜检出限0.008μg/mL', '0', null, '109');
INSERT INTO `accuracy` VALUES ('238', '', '', '铜检出限0.006μg/mL', '1', null, '109');
INSERT INTO `accuracy` VALUES ('239', '', '', '0.02级', '7', null, '43');
INSERT INTO `accuracy` VALUES ('240', '', '', '0.2级', '4', null, '43');
INSERT INTO `accuracy` VALUES ('241', '', '', '0.05级', '6', null, '43');
INSERT INTO `accuracy` VALUES ('242', '', '', '0.1级', '5', null, '43');
INSERT INTO `accuracy` VALUES ('243', '', '', '0.05s级', '5', null, '44');
INSERT INTO `accuracy` VALUES ('244', '', '', '0.05级', '4', null, '44');
INSERT INTO `accuracy` VALUES ('245', '', '', '0.05级', '5', null, '45');
INSERT INTO `accuracy` VALUES ('246', '', 'adscas', 'yids', '0', null, '110');
INSERT INTO `accuracy` VALUES ('247', '', '1', '1', '0', null, '111');
INSERT INTO `accuracy` VALUES ('248', '', '', '0.4级', '5', null, '37');
INSERT INTO `accuracy` VALUES ('249', '', '', '0.25级', '6', null, '37');
INSERT INTO `accuracy` VALUES ('250', '', '', '0.6级', '4', null, '37');
INSERT INTO `accuracy` VALUES ('251', '', '', '0.16级', '7', null, '35');
INSERT INTO `accuracy` VALUES ('252', '', '', '0.25级', '6', null, '35');
INSERT INTO `accuracy` VALUES ('253', '', '', '0.4级', '5', null, '35');
INSERT INTO `accuracy` VALUES ('254', '', '', '0.6级', '4', null, '35');
INSERT INTO `accuracy` VALUES ('255', '', '', '0.16级', '7', null, '4');
INSERT INTO `accuracy` VALUES ('256', '', '', '0.1级', '8', null, '4');
INSERT INTO `accuracy` VALUES ('257', '', '', '0.6级', '4', null, '4');
INSERT INTO `accuracy` VALUES ('258', '', '', '0.25级', '6', null, '4');
INSERT INTO `accuracy` VALUES ('259', '', '', '0.4级', '5', null, '4');
INSERT INTO `accuracy` VALUES ('260', '', '', '0.1级', '8', null, '35');
INSERT INTO `accuracy` VALUES ('261', '', '', '0.16级', '7', null, '37');
INSERT INTO `accuracy` VALUES ('262', '', '', '0.1级', '8', null, '37');

-- ----------------------------
-- Table structure for `accuracy_display_name`
-- ----------------------------
DROP TABLE IF EXISTS `accuracy_display_name`;
CREATE TABLE `accuracy_display_name` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT 'NULL',
  `pinyin` varchar(255) DEFAULT 'NULL',
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkpchgm20b1v02jsmn9y1fqjr9` (`create_user_id`),
  CONSTRAINT `FKkpchgm20b1v02jsmn9y1fqjr9` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of accuracy_display_name
-- ----------------------------
INSERT INTO `accuracy_display_name` VALUES ('1', null, '示值相对误差', 'shizhixiangduiwucha', null, null);
INSERT INTO `accuracy_display_name` VALUES ('2', null, 'MPE', 'MPE', null, null);
INSERT INTO `accuracy_display_name` VALUES ('3', null, '测量误差', 'celiangwucha', null, null);
INSERT INTO `accuracy_display_name` VALUES ('4', null, '示值误差', 'shizhiwucha', null, null);
INSERT INTO `accuracy_display_name` VALUES ('5', null, '检测误差', 'jiancewucha', null, null);
INSERT INTO `accuracy_display_name` VALUES ('6', null, '最大允许误差', 'zuidayunxuwucha', null, null);
INSERT INTO `accuracy_display_name` VALUES ('7', null, '基本误差', 'jibenwucha', null, null);
INSERT INTO `accuracy_display_name` VALUES ('8', null, '精度', 'jingdu', null, null);
INSERT INTO `accuracy_display_name` VALUES ('9', null, '准确度等级', 'zhunquedudengji', null, null);

-- ----------------------------
-- Table structure for `apply`
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `table_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_time` datetime DEFAULT NULL,
  `is_agree` bit(1) DEFAULT NULL,
  `is_done` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `latest_check_date` date DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `apply_type_id` bigint(20) DEFAULT NULL,
  `auditing_department_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ixcpxp850vq5gsb0c91o27ej` (`apply_type_id`),
  KEY `FK2n1cnl4n6bkn98iw21bcwy3v4` (`auditing_department_id`),
  KEY `FKqlyxcngs8463q4nb8bg293p9g` (`create_user_id`),
  KEY `FK67qpdos920uurw8t6nmst15ld` (`department_id`),
  CONSTRAINT `FK1ixcpxp850vq5gsb0c91o27ej` FOREIGN KEY (`apply_type_id`) REFERENCES `apply_type` (`id`),
  CONSTRAINT `FK2n1cnl4n6bkn98iw21bcwy3v4` FOREIGN KEY (`auditing_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FK67qpdos920uurw8t6nmst15ld` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKqlyxcngs8463q4nb8bg293p9g` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES ('MandatoryInstrument', '1', '2017-12-15 09:41:18', null, '', null, '2017-12-15 09:46:46', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '2', '2017-12-16 14:40:02', null, '', null, '2017-12-16 17:40:08', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '3', '2017-12-16 14:44:58', null, '', null, '2017-12-16 14:45:05', null, null, '2', null, '2', '1');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '4', '2017-12-16 14:45:52', null, '', null, '2017-12-16 15:28:18', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '5', '2017-12-16 15:06:25', null, '', null, '2017-12-17 15:39:37', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('OverdueCheck', '6', '2017-12-16 17:47:40', '', '', '元宝山面粉厂_超期检定申请', '2017-12-16 17:49:18', '2017-12-23', '', '1', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '7', '2017-12-17 16:41:51', null, '', null, '2017-12-18 16:00:33', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('OverdueCheck', '8', '2017-12-17 16:51:12', '', '', '元宝山面粉厂_超期检定申请', '2017-12-17 16:52:16', '2017-12-22', '', '1', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '9', '2017-12-17 21:10:16', null, '', null, '2017-12-18 15:36:17', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('OverdueCheck', '10', '2017-12-17 21:43:03', null, '', '元宝山面粉厂_超期检定申请', '2017-12-18 15:32:57', null, '这里写明超期未检的原因', '1', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '11', '2017-12-17 22:43:22', null, '', null, '2017-12-17 22:46:43', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '12', '2017-12-18 09:12:01', null, '', null, '2017-12-18 09:17:11', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '13', '2017-12-18 15:18:37', null, '', null, '2017-12-18 15:28:50', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('OverdueCheck', '14', '2017-12-18 15:44:28', '', '', '元宝山面粉厂_超期检定申请', '2017-12-18 15:47:01', '2017-12-29', '', '1', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '15', '2017-12-18 16:12:36', null, '', null, '2017-12-18 16:19:56', null, null, '2', '21', '29', '35');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '16', '2017-12-18 16:21:18', null, '', null, '2017-12-18 16:21:58', null, null, '2', '25', '32', '40');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '17', '2017-12-18 16:22:25', null, '', null, '2017-12-18 16:26:47', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '18', '2017-12-18 16:24:41', null, '', null, '2017-12-18 16:30:03', null, null, '2', '22', '30', '37');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '19', '2017-12-18 16:29:15', null, '', null, '2017-12-18 16:30:23', null, null, '2', '21', '29', '35');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '20', '2017-12-18 16:33:29', null, '', null, '2017-12-18 16:35:06', null, null, '2', '25', '32', '40');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '21', '2017-12-18 16:37:58', null, '', null, '2017-12-18 16:38:49', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '22', '2017-12-18 21:10:39', null, '', null, '2017-12-18 21:11:41', null, null, '2', '23', '37', '48');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '23', '2017-12-18 21:17:57', null, '', null, '2017-12-18 21:18:54', null, null, '2', '23', '37', '48');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '24', '2017-12-19 08:52:55', null, '', null, '2017-12-19 08:53:55', null, null, '2', '25', '39', '51');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '25', '2017-12-19 09:19:56', null, '', null, '2017-12-19 09:21:00', null, null, '2', '25', '40', '52');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '26', '2017-12-19 09:37:12', null, '', null, '2017-12-19 09:38:58', null, null, '2', '25', '42', '54');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '27', '2017-12-19 11:44:02', null, '', null, '2017-12-19 11:59:04', null, null, '2', '26', '44', '57');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '28', '2017-12-19 11:49:03', null, '', null, '2017-12-19 11:58:50', null, null, '2', '26', '44', '57');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '29', '2017-12-19 12:36:28', null, '', null, '2017-12-19 12:37:48', null, null, '2', '26', '44', '57');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '30', '2017-12-19 14:57:00', null, '', null, '2017-12-19 15:00:59', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '31', '2017-12-19 15:02:51', null, '', null, '2017-12-19 15:02:51', null, null, '2', '61', '48', '61');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '32', '2017-12-19 15:06:27', null, '', null, '2017-12-19 15:07:17', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '33', '2017-12-19 15:18:33', null, '', null, '2017-12-19 15:19:37', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '34', '2017-12-19 15:37:47', null, '', null, '2017-12-19 15:38:59', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('OverdueCheck', '35', '2017-12-20 10:45:47', '', '', '元宝山面粉厂_超期检定申请', '2017-12-20 10:46:55', '2017-12-24', '', '1', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '36', '2017-12-21 11:05:02', null, '', null, '2017-12-21 11:07:03', null, null, '2', '24', '56', '70');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '37', '2017-12-21 14:30:42', null, '', null, '2017-12-21 14:30:42', null, null, '2', '24', '22', '24');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '38', '2017-12-21 14:53:45', null, '', null, '2017-12-21 15:02:13', null, null, '2', '24', '57', '72');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '39', '2017-12-21 16:35:17', null, '', null, '2017-12-21 16:37:37', null, null, '2', '27', '59', '76');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '40', '2017-12-22 09:42:50', null, '', null, '2017-12-22 09:47:50', null, null, '2', '78', '63', '81');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '41', '2017-12-22 09:54:26', null, '', null, '2017-12-22 09:54:45', null, null, '2', '24', '57', '72');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '42', '2017-12-22 10:37:49', null, '', null, '2017-12-22 10:37:49', null, null, '2', '83', '64', '83');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '43', '2017-12-22 10:39:43', null, '', null, '2017-12-22 10:39:43', null, null, '2', '83', '64', '83');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '44', '2017-12-22 10:55:43', null, '', null, '2017-12-22 10:55:43', null, null, '2', '83', '64', '83');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '45', '2017-12-23 08:18:49', null, '', null, '2018-01-11 10:31:53', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '46', '2017-12-23 08:27:13', null, '', null, '2018-01-11 10:32:32', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '47', '2017-12-23 08:32:10', null, '', null, '2018-01-11 10:31:24', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '48', '2017-12-23 08:39:50', null, '', null, '2018-01-11 10:31:12', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '49', '2017-12-23 08:42:47', null, '', null, '2018-01-11 10:30:56', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '50', '2017-12-23 08:45:24', null, '', null, '2018-01-11 10:30:38', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '51', '2017-12-23 08:47:17', null, '', null, '2018-01-11 10:30:20', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '52', '2017-12-23 08:50:04', null, '', null, '2018-01-11 10:34:17', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '53', '2017-12-23 08:52:48', null, '', null, '2018-01-11 10:33:57', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '54', '2017-12-23 08:59:37', null, '', null, '2018-01-11 10:33:42', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '55', '2017-12-23 09:01:39', null, '', null, '2018-01-11 10:33:24', null, null, '2', '79', '65', '84');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '56', '2017-12-25 08:51:56', null, '', null, '2017-12-26 11:07:04', null, null, '2', '78', '67', '101');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '57', '2017-12-25 14:13:35', null, '', null, '2018-01-10 14:50:47', null, null, '2', '78', '67', '101');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '58', '2017-12-25 14:19:46', null, '', null, '2018-01-10 14:51:04', null, null, '2', '78', '67', '101');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '59', '2017-12-25 14:22:29', null, '', null, '2018-01-10 14:51:23', null, null, '2', '78', '67', '101');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '60', '2017-12-25 15:34:37', null, '', null, '2017-12-29 10:47:48', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '61', '2017-12-25 15:40:50', null, '', null, '2017-12-29 10:47:29', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '62', '2017-12-25 15:45:58', null, '', null, '2017-12-29 10:47:12', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '63', '2017-12-25 15:49:30', null, '', null, '2017-12-29 10:46:53', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '64', '2017-12-25 16:03:52', null, '', null, '2017-12-29 10:46:30', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '65', '2017-12-25 16:06:40', null, '', null, '2017-12-29 10:46:01', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '66', '2017-12-26 09:51:09', null, '', null, '2017-12-26 11:04:38', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '67', '2017-12-26 10:02:52', null, '', null, '2017-12-29 10:45:46', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '68', '2017-12-26 10:05:34', null, '', null, '2017-12-29 10:43:41', null, null, '2', '78', '85', '128');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '69', '2017-12-26 10:09:15', null, '', null, '2017-12-29 10:43:25', null, null, '2', '78', '85', '128');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '70', '2017-12-26 10:11:30', null, '', null, '2017-12-29 10:43:04', null, null, '2', '78', '85', '128');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '71', '2017-12-26 10:15:40', null, '', null, '2017-12-29 10:45:29', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '72', '2017-12-26 10:19:42', null, '', null, '2017-12-29 10:45:12', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '73', '2017-12-26 10:19:48', null, '', null, '2017-12-29 10:29:09', null, null, '2', '78', '85', '128');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '74', '2017-12-26 10:20:21', null, '', null, '2018-01-10 14:54:23', null, null, '2', '78', '94', '137');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '75', '2017-12-26 10:32:16', null, '', null, '2017-12-29 10:44:29', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '76', '2017-12-26 10:35:09', null, '', null, '2017-12-29 10:44:01', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '77', '2017-12-26 10:40:22', null, '', null, '2018-01-10 14:54:14', null, null, '2', '78', '95', '138');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '78', '2017-12-26 10:45:03', null, '', null, '2017-12-29 10:37:06', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '79', '2017-12-26 10:47:59', null, '', null, '2017-12-26 11:03:37', null, null, '2', '78', '74', '108');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '80', '2017-12-26 10:54:39', null, '', null, '2018-01-09 08:56:53', null, null, '2', '78', '93', '136');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '81', '2017-12-26 11:15:25', null, '', null, '2018-01-09 08:57:11', null, null, '2', '78', '93', '136');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '82', '2017-12-26 11:25:53', null, '', null, '2017-12-26 11:25:53', null, null, '2', '177', '126', '177');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '83', '2017-12-26 14:41:47', null, '', null, '2017-12-26 14:53:47', null, null, '2', '78', '79', '113');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '84', '2017-12-26 14:44:22', null, '', null, '2017-12-26 14:56:05', null, null, '2', '78', '79', '113');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '85', '2017-12-26 14:46:16', null, '', null, '2018-01-10 14:48:06', null, null, '2', '78', '79', '113');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '86', '2017-12-26 14:48:06', null, '', null, '2017-12-29 10:26:14', null, null, '2', '78', '79', '113');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '87', '2017-12-26 15:11:07', null, '', null, '2017-12-26 15:11:07', null, null, '2', '129', '86', '129');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '88', '2017-12-26 15:40:27', null, '', null, '2018-01-10 14:54:04', null, null, '2', '78', '131', '186');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '89', '2017-12-26 15:47:53', null, '', null, '2017-12-27 09:28:07', null, null, '2', '24', '91', '134');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '90', '2017-12-26 15:57:08', null, '', null, '2017-12-26 15:57:08', null, null, '2', '106', '72', '106');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '91', '2017-12-26 16:11:52', null, '', null, '2017-12-29 10:25:42', null, null, '2', '78', '135', '194');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '92', '2017-12-26 16:20:00', null, '', null, '2018-01-10 14:53:54', null, null, '2', '78', '135', '194');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '93', '2017-12-26 16:38:48', null, '', null, '2017-12-29 10:25:13', null, null, '2', '78', '151', '214');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '94', '2017-12-26 17:02:50', null, '', null, '2017-12-26 17:02:50', null, null, '2', '112', '78', '112');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '95', '2017-12-26 17:15:56', null, '', null, '2017-12-27 10:27:42', null, null, '2', '24', '91', '134');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '96', '2017-12-26 17:19:52', null, '', null, '2017-12-27 10:28:43', null, null, '2', '24', '91', '134');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '97', '2017-12-27 09:33:28', null, '', null, '2017-12-27 09:33:28', null, null, '2', '131', '88', '131');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '98', '2017-12-27 09:35:57', null, '', null, '2017-12-29 10:24:44', null, null, '2', '78', '88', '131');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '99', '2017-12-27 10:08:08', null, '', null, '2017-12-29 10:22:06', null, null, '2', '78', '146', '209');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '100', '2017-12-27 10:14:48', null, '', null, '2017-12-29 10:17:47', null, null, '2', '78', '146', '209');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '101', '2017-12-27 10:21:17', null, '', null, '2017-12-29 10:17:22', null, null, '2', '78', '146', '209');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '102', '2017-12-27 13:37:39', null, '', null, '2017-12-29 10:15:08', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '103', '2017-12-27 13:39:50', null, '', null, '2017-12-29 10:16:03', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '104', '2017-12-27 13:42:00', null, '', null, '2017-12-29 10:16:25', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '105', '2017-12-27 13:43:46', null, '', null, '2017-12-29 10:14:40', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '106', '2017-12-27 13:45:08', null, '', null, '2017-12-29 10:14:14', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '107', '2017-12-27 13:48:51', null, '', null, '2017-12-29 10:13:43', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '108', '2017-12-27 13:50:43', null, '', null, '2017-12-29 10:13:21', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '109', '2017-12-27 13:51:56', null, '', null, '2017-12-29 10:12:56', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '110', '2017-12-27 13:53:29', null, '', null, '2017-12-29 10:12:30', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '111', '2017-12-27 13:54:53', null, '', null, '2017-12-29 10:12:09', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '112', '2017-12-27 13:58:33', null, '', null, '2017-12-29 10:11:42', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '113', '2017-12-27 15:34:19', null, '', null, '2017-12-29 10:11:04', null, null, '2', '78', '231', '307');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '114', '2017-12-27 15:59:38', null, '', null, '2017-12-29 14:51:46', null, null, '2', '78', '89', '132');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '115', '2017-12-27 16:05:54', null, '', null, '2018-01-10 14:53:31', null, null, '2', '78', '260', '338');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '116', '2017-12-27 16:27:06', null, '', null, '2017-12-29 10:10:25', null, null, '2', '78', '273', '352');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '117', '2017-12-27 16:34:26', null, '', null, '2017-12-27 16:34:26', null, null, '2', '126', '83', '126');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '118', '2017-12-27 16:35:36', null, '', null, '2017-12-27 16:35:36', null, null, '2', '112', '78', '112');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '119', '2017-12-27 16:41:36', null, '', null, '2017-12-29 10:09:56', null, null, '2', '78', '274', '354');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '120', '2017-12-27 16:45:29', null, '', null, '2017-12-27 16:45:29', null, null, '2', '72', '57', '72');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '121', '2017-12-27 16:51:44', null, '', null, '2017-12-27 16:51:44', null, null, '2', '355', '275', '355');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '122', '2017-12-27 16:53:11', null, '', null, '2017-12-29 10:09:25', null, null, '2', '78', '88', '131');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '123', '2017-12-27 17:07:04', null, '', null, '2018-01-10 14:52:01', null, null, '2', '78', '88', '131');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '124', '2017-12-27 18:16:40', null, '', null, '2017-12-29 10:07:37', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '125', '2017-12-28 08:03:28', null, '', null, '2018-01-10 14:51:45', null, null, '2', '78', '88', '131');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '126', '2017-12-28 08:13:16', null, '', null, '2018-01-10 14:45:37', null, null, '2', '78', '88', '131');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '127', '2017-12-28 09:52:30', null, '', null, '2018-01-17 15:10:52', null, null, '2', '9', '11', '12');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '128', '2017-12-28 10:30:19', null, '', null, '2017-12-28 10:37:15', null, null, '2', '21', '277', '372');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '129', '2017-12-28 15:11:56', null, '', null, '2018-01-18 10:00:21', null, null, '2', '24', '180', '248');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '130', '2017-12-28 16:02:21', null, '', null, '2017-12-29 09:01:00', null, null, '2', '24', '278', '373');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '131', '2017-12-28 16:32:08', null, '', null, '2017-12-29 14:52:19', null, null, '2', '78', '89', '132');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '132', '2017-12-28 17:09:12', null, '', null, '2018-01-02 14:44:45', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '133', '2017-12-28 17:11:36', null, '', null, '2017-12-29 14:52:40', null, null, '2', '78', '89', '132');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '134', '2017-12-28 19:41:40', null, '', null, '2017-12-29 10:05:15', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '135', '2017-12-28 19:41:56', null, '', null, '2017-12-29 15:16:25', null, null, '2', '24', '310', '405');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '136', '2017-12-28 22:01:12', null, '', null, '2018-01-02 14:45:49', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '137', '2017-12-28 22:33:20', null, '', null, '2018-01-02 14:44:08', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '138', '2017-12-29 08:51:03', null, '', null, '2018-01-10 14:53:20', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '139', '2017-12-29 09:00:27', null, '', null, '2017-12-29 09:15:48', null, null, '2', '24', '311', '414');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '140', '2017-12-29 09:35:37', null, '', null, '2017-12-29 09:47:55', null, null, '2', '24', '278', '373');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '141', '2017-12-29 10:04:37', null, '', null, '2018-01-10 14:53:10', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '142', '2017-12-29 10:15:42', null, '', null, '2017-12-29 10:21:32', null, null, '2', '26', '222', '297');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '143', '2017-12-29 10:25:22', null, '', null, '2017-12-29 10:28:33', null, null, '2', '26', '222', '297');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '144', '2017-12-29 10:26:38', null, '', null, '2018-01-10 14:52:14', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '145', '2017-12-29 10:31:32', null, '', null, '2018-01-02 14:45:18', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '146', '2017-12-29 10:47:54', null, '', null, '2017-12-29 11:01:23', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '147', '2017-12-29 11:10:31', null, '', null, '2018-01-02 14:43:36', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '148', '2017-12-29 14:02:21', null, '', null, '2018-01-02 14:42:51', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '149', '2017-12-29 14:15:13', null, '', null, '2017-12-29 15:18:32', null, null, '2', '24', '310', '405');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '150', '2017-12-29 14:45:48', null, '', null, '2018-01-02 14:42:24', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '151', '2017-12-29 14:59:42', null, '', null, '2018-01-10 14:46:02', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '152', '2017-12-29 15:05:57', null, '', null, '2018-01-10 14:53:01', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '153', '2017-12-29 15:20:51', null, '', null, '2018-01-10 14:50:13', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '154', '2017-12-29 15:26:14', null, '', null, '2018-01-10 14:52:51', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '155', '2017-12-29 15:52:00', null, '', null, '2018-01-02 14:50:20', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '156', '2017-12-29 15:59:58', null, '', null, '2018-01-02 14:49:56', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '157', '2017-12-29 16:14:07', null, '', null, '2018-01-02 14:41:38', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '158', '2017-12-29 16:15:54', null, '', null, '2018-01-09 15:36:45', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '159', '2017-12-29 16:24:27', null, '', null, '2018-01-10 14:46:46', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '160', '2017-12-29 16:28:48', null, '', null, '2018-01-02 14:41:05', null, null, '2', '78', '178', '245');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '161', '2017-12-29 16:30:51', null, '', null, '2018-01-10 14:52:40', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '162', '2017-12-29 16:34:35', null, '', null, '2018-01-10 14:52:29', null, null, '2', '78', '90', '133');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '163', '2017-12-31 09:44:18', null, '', null, '2018-01-10 14:44:42', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '164', '2017-12-31 09:56:18', null, '', null, '2018-01-02 14:48:29', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '165', '2017-12-31 10:09:26', null, '', null, '2018-01-10 14:43:32', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '166', '2017-12-31 10:15:24', null, '', null, '2018-01-10 11:25:57', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '167', '2017-12-31 10:28:31', null, '', null, '2018-01-10 14:45:51', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '168', '2017-12-31 10:35:27', null, '', null, '2018-01-10 11:25:18', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '169', '2017-12-31 14:58:02', null, '', null, '2018-01-09 15:34:59', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '170', '2017-12-31 15:14:28', null, '', null, '2018-01-09 15:34:37', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '171', '2017-12-31 15:27:26', null, '', null, '2018-01-09 15:33:56', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '172', '2017-12-31 16:08:00', null, '', null, '2018-01-08 15:57:17', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '173', '2017-12-31 16:14:02', null, '', null, '2018-01-08 15:56:42', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '174', '2017-12-31 16:36:11', null, '', null, '2018-01-08 15:56:16', null, null, '2', '78', '81', '121');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '175', '2018-01-02 09:33:32', null, '', null, '2018-01-02 10:13:43', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '176', '2018-01-02 09:40:18', null, '', null, '2018-01-02 10:14:15', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '177', '2018-01-02 09:43:36', null, '', null, '2018-01-02 10:14:43', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '178', '2018-01-02 09:46:54', null, '', null, '2018-01-02 10:15:11', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '179', '2018-01-02 09:49:02', null, '', null, '2018-01-02 10:15:40', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '180', '2018-01-02 09:54:23', null, '', null, '2018-01-02 10:16:10', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '181', '2018-01-02 09:57:12', null, '', null, '2018-01-02 10:16:33', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '182', '2018-01-02 09:59:11', null, '', null, '2018-01-02 10:16:58', null, null, '2', '24', '313', '433');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '183', '2018-01-02 15:31:47', null, '', null, '2018-01-02 15:53:28', null, null, '2', '27', '316', '456');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '184', '2018-01-02 16:31:55', null, '', null, '2018-01-02 17:02:07', null, null, '2', '27', '317', '458');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '185', '2018-01-02 16:47:44', null, '', null, '2018-01-02 17:02:25', null, null, '2', '27', '317', '458');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '186', '2018-01-02 16:51:48', null, '', null, '2018-01-02 17:02:57', null, null, '2', '27', '317', '458');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '187', '2018-01-03 19:24:34', null, '', null, '2018-01-08 15:43:40', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '188', '2018-01-03 19:32:24', null, '', null, '2018-01-08 15:49:06', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '189', '2018-01-03 19:48:12', null, '', null, '2018-01-08 15:48:55', null, null, '2', '78', '76', '110');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '190', '2018-01-04 08:18:45', null, '', null, '2018-01-04 09:17:24', null, null, '2', '24', '312', '418');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '191', '2018-01-04 08:33:19', null, '', null, '2018-01-04 09:18:04', null, null, '2', '24', '312', '418');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '192', '2018-01-04 08:46:08', null, '', null, '2018-01-09 08:41:43', null, null, '2', '24', '312', '418');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '193', '2018-01-04 09:28:37', null, '', null, '2018-01-04 09:55:43', null, null, '2', '24', '312', '418');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '194', '2018-01-04 09:36:30', null, '', null, '2018-01-04 09:38:49', null, null, '2', '24', '312', '418');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '195', '2018-01-04 17:24:42', null, '', null, '2018-01-05 11:16:58', null, null, '2', '24', '310', '405');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '196', '2018-01-05 10:22:03', null, '', null, '2018-01-05 10:22:03', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '197', '2018-01-05 10:26:36', null, '', null, '2018-01-05 10:26:36', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '198', '2018-01-05 10:29:42', null, '', null, '2018-01-05 10:29:42', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '199', '2018-01-05 10:40:11', null, '', null, '2018-01-05 10:40:11', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '200', '2018-01-05 10:42:46', null, '', null, '2018-01-05 10:42:46', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '201', '2018-01-05 10:45:44', null, '', null, '2018-01-05 10:45:44', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '202', '2018-01-05 10:49:06', null, '', null, '2018-01-05 10:49:06', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '203', '2018-01-05 10:51:40', null, '', null, '2018-01-05 10:51:40', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '204', '2018-01-05 10:53:53', null, '', null, '2018-01-05 10:53:53', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '205', '2018-01-05 11:02:17', null, '', null, '2018-01-05 11:02:17', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '206', '2018-01-05 11:05:20', null, '', null, '2018-01-05 11:05:20', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '207', '2018-01-05 11:08:02', null, '', null, '2018-01-05 11:08:02', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '208', '2018-01-05 11:10:12', null, '', null, '2018-01-05 11:10:12', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '209', '2018-01-05 11:12:39', null, '', null, '2018-01-05 11:12:39', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '210', '2018-01-05 11:14:52', null, '', null, '2018-01-05 11:14:52', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '211', '2018-01-05 11:17:12', null, '', null, '2018-01-05 11:17:12', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '212', '2018-01-05 11:19:47', null, '', null, '2018-01-05 11:19:47', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '213', '2018-01-05 11:22:10', null, '', null, '2018-01-05 11:22:10', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '214', '2018-01-05 11:35:23', null, '', null, '2018-01-05 11:35:23', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '215', '2018-01-05 11:40:28', null, '', null, '2018-01-05 11:40:28', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '216', '2018-01-05 11:42:46', null, '', null, '2018-01-05 11:42:46', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '217', '2018-01-05 11:44:31', null, '', null, '2018-01-05 11:44:31', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '218', '2018-01-05 11:46:35', null, '', null, '2018-01-05 11:46:35', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '219', '2018-01-05 11:49:46', null, '', null, '2018-01-05 11:49:46', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '220', '2018-01-05 11:52:07', null, '', null, '2018-01-05 11:52:07', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '221', '2018-01-05 11:54:27', null, '', null, '2018-01-05 11:54:27', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '222', '2018-01-05 14:52:18', null, '', null, '2018-01-05 14:52:18', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '223', '2018-01-05 14:54:23', null, '', null, '2018-01-05 14:54:23', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '224', '2018-01-05 14:56:17', null, '', null, '2018-01-05 14:56:17', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '225', '2018-01-05 15:02:13', null, '', null, '2018-01-05 15:02:13', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '226', '2018-01-05 15:04:37', null, '', null, '2018-01-05 15:04:37', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '227', '2018-01-05 15:05:12', null, '', null, '2018-01-08 14:25:33', null, null, '2', '24', '318', '468');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '228', '2018-01-05 15:06:16', null, '', null, '2018-01-05 15:06:16', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '229', '2018-01-05 15:07:52', null, '', null, '2018-01-05 15:07:52', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '230', '2018-01-05 15:09:41', null, '', null, '2018-01-05 15:09:41', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '231', '2018-01-05 15:11:13', null, '', null, '2018-01-05 15:11:13', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '232', '2018-01-05 15:17:01', null, '', null, '2018-01-05 15:17:01', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '233', '2018-01-05 15:58:22', null, '', null, '2018-01-05 15:58:22', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '234', '2018-01-05 16:01:05', null, '', null, '2018-01-05 16:01:05', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '235', '2018-01-05 16:07:08', null, '', null, '2018-01-05 16:07:08', null, null, '2', '109', '75', '109');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '236', '2018-01-06 09:03:09', null, '', null, '2018-01-08 14:25:10', null, null, '2', '24', '318', '468');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '237', '2018-01-08 09:31:36', null, '', null, '2018-01-08 15:28:47', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '238', '2018-01-08 10:32:15', null, '', null, '2018-01-08 15:29:11', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '239', '2018-01-08 10:36:28', null, '', null, '2018-01-08 15:29:25', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '240', '2018-01-08 10:41:22', null, '', null, '2018-01-08 15:29:59', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '241', '2018-01-08 10:57:34', null, '', null, '2018-01-09 14:29:48', null, null, '2', '27', '320', '474');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '242', '2018-01-08 11:03:28', null, '', null, '2018-01-09 14:28:44', null, null, '2', '27', '320', '474');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '243', '2018-01-08 14:57:05', null, '', null, '2018-01-08 15:03:21', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '244', '2018-01-08 16:29:58', null, '', null, '2018-01-09 08:52:32', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '245', '2018-01-08 16:36:48', null, '', null, '2018-01-09 08:54:26', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '246', '2018-01-08 16:40:08', null, '', null, '2018-01-09 09:00:03', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '247', '2018-01-08 16:41:47', null, '', null, '2018-01-09 15:35:03', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '248', '2018-01-08 16:43:19', null, '', null, '2018-01-09 15:35:26', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '249', '2018-01-08 16:45:12', null, '', null, '2018-01-09 15:35:46', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '250', '2018-01-08 16:46:59', null, '', null, '2018-01-09 15:36:07', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '251', '2018-01-08 16:48:21', null, '', null, '2018-01-09 15:36:34', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '252', '2018-01-08 16:51:45', null, '', null, '2018-01-09 15:37:07', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '253', '2018-01-09 08:43:05', null, '', null, '2018-01-09 08:44:07', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '254', '2018-01-09 11:26:29', null, '', null, '2018-01-09 14:23:26', null, null, '2', '24', '310', '405');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '255', '2018-01-09 15:28:38', null, '', null, '2018-01-09 15:33:52', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '256', '2018-01-09 15:30:03', null, '', null, '2018-01-09 15:34:26', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '257', '2018-01-09 15:31:36', null, '', null, '2018-01-09 15:33:24', null, null, '2', '24', '322', '478');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '258', '2018-01-09 15:36:12', null, '', null, '2018-01-09 15:37:35', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '259', '2018-01-10 09:50:15', null, '', null, '2018-01-10 09:50:15', null, null, '2', '105', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '260', '2018-01-10 10:14:29', null, '', null, '2018-01-10 10:20:23', null, null, '2', '78', '71', '105');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '261', '2018-01-10 14:28:46', null, '', null, '2018-01-10 14:30:24', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '262', '2018-01-10 14:41:43', null, '', null, '2018-01-10 14:42:33', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '263', '2018-01-10 14:49:04', null, '', null, '2018-01-10 14:50:35', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '264', '2018-01-10 15:08:40', null, '', null, '2018-01-10 15:23:57', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '265', '2018-01-10 15:37:28', null, '', null, '2018-01-10 15:39:46', null, null, '2', '38', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '266', '2018-01-11 10:21:03', null, '', null, '2018-01-11 15:12:21', null, null, '2', '78', '131', '186');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '267', '2018-01-11 17:20:36', null, '', null, '2018-01-15 14:42:33', null, null, '2', '38', '351', '510');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '268', '2018-01-11 17:28:11', null, '', null, '2018-01-15 14:42:51', null, null, '2', '38', '351', '510');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '269', '2018-01-11 17:37:49', null, '', null, '2018-01-15 14:45:26', null, null, '2', '38', '351', '510');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '270', '2018-01-11 17:46:04', null, '', null, '2018-01-15 14:45:47', null, null, '2', '38', '351', '510');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '271', '2018-01-12 10:31:27', null, '', null, '2018-01-15 14:46:48', null, null, '2', '38', '342', '501');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '272', '2018-01-12 10:38:49', null, '', null, '2018-01-15 14:46:28', null, null, '2', '38', '342', '501');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '273', '2018-01-12 11:07:49', null, '', null, '2018-01-17 10:53:04', null, null, '2', '38', '345', '504');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '274', '2018-01-12 12:34:17', null, '', null, '2018-01-15 14:47:23', null, null, '2', '38', '336', '494');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '275', '2018-01-12 12:39:40', null, '', null, '2018-01-15 14:47:07', null, null, '2', '38', '336', '494');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '276', '2018-01-13 11:41:45', null, '', null, '2018-01-15 14:47:37', null, null, '2', '38', '346', '505');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '277', '2018-01-13 11:48:46', null, '', null, '2018-01-15 14:47:54', null, null, '2', '38', '346', '505');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '278', '2018-01-13 11:59:52', null, '', null, '2018-01-15 14:48:08', null, null, '2', '38', '346', '505');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '279', '2018-01-13 12:04:36', null, '', null, '2018-01-15 14:48:20', null, null, '2', '38', '346', '505');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '280', '2018-01-15 10:04:23', null, '', null, '2018-01-15 10:04:59', null, null, '2', '38', '370', '533');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '281', '2018-01-15 10:36:00', null, '', null, '2018-01-15 14:53:53', null, null, '2', '24', '369', '532');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '282', '2018-01-15 10:38:32', null, '', null, '2018-01-15 14:54:23', null, null, '2', '24', '369', '532');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '283', '2018-01-15 10:42:47', null, '', null, '2018-01-15 14:54:45', null, null, '2', '24', '369', '532');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '284', '2018-01-15 10:46:00', null, '', null, '2018-01-15 14:55:05', null, null, '2', '24', '369', '532');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '285', '2018-01-16 11:01:34', null, '', null, '2018-01-16 14:37:12', null, null, '2', '78', '374', '537');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '286', '2018-01-16 14:17:08', null, '', null, '2018-01-17 10:48:01', null, null, '2', '38', '352', '511');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '287', '2018-01-16 14:37:58', null, '', null, '2018-01-16 14:37:58', null, null, '2', '483', '326', '483');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '288', '2018-01-16 14:42:28', null, '', null, '2018-01-17 10:48:53', null, null, '2', '38', '353', '512');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '289', '2018-01-16 14:49:16', null, '', null, '2018-01-17 10:49:56', null, null, '2', '38', '354', '513');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '290', '2018-01-16 15:04:31', null, '', null, '2018-01-17 10:50:24', null, null, '2', '38', '356', '515');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '291', '2018-01-16 15:23:16', null, '', null, '2018-01-17 10:50:39', null, null, '2', '38', '355', '514');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '292', '2018-01-16 15:27:58', null, '', null, '2018-01-17 10:51:06', null, null, '2', '38', '357', '516');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '293', '2018-01-16 15:42:34', null, '', null, '2018-01-17 10:51:24', null, null, '2', '38', '358', '517');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '294', '2018-01-16 15:46:15', null, '', null, '2018-01-17 10:51:39', null, null, '2', '38', '359', '518');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '295', '2018-01-16 15:54:21', null, '', null, '2018-01-17 10:52:02', null, null, '2', '38', '360', '519');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '296', '2018-01-16 16:04:55', null, '', null, '2018-01-17 10:52:16', null, null, '2', '38', '361', '520');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '297', '2018-01-16 16:09:35', null, '', null, '2018-01-17 10:52:29', null, null, '2', '38', '362', '521');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '298', '2018-01-16 16:13:47', null, '', null, '2018-01-17 10:52:42', null, null, '2', '38', '363', '522');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '299', '2018-01-17 10:06:13', null, '', null, '2018-01-18 09:11:00', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '300', '2018-01-17 10:15:05', null, '', null, '2018-01-18 09:10:47', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '301', '2018-01-17 10:22:20', null, '', null, '2018-01-18 09:09:17', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '302', '2018-01-17 10:25:05', null, '', null, '2018-01-18 09:12:25', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '303', '2018-01-17 10:26:30', null, '', null, '2018-01-18 09:12:10', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '304', '2018-01-17 10:27:36', null, '', null, '2018-01-18 09:11:48', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '305', '2018-01-17 10:28:37', null, '', null, '2018-01-18 09:11:31', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '306', '2018-01-17 10:29:56', null, '', null, '2018-01-18 09:11:17', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '307', '2018-01-17 10:31:10', null, '', null, '2018-01-18 09:10:17', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '308', '2018-01-17 10:34:19', null, '', null, '2018-01-18 09:10:31', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '309', '2018-01-17 11:04:05', null, '', null, '2018-01-18 09:08:28', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '310', '2018-01-17 11:08:04', null, '', null, '2018-01-18 09:09:00', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '311', '2018-01-17 11:09:45', null, '', null, '2018-01-18 09:10:01', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '312', '2018-01-17 14:14:27', null, '', null, '2018-01-17 14:57:50', null, null, '2', '26', '193', '268');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '313', '2018-01-17 15:54:12', null, '', null, '2018-01-18 09:27:47', null, null, '2', '38', '347', '506');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '314', '2018-01-17 15:57:25', null, '', null, '2018-01-18 09:27:35', null, null, '2', '38', '347', '506');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '315', '2018-01-17 16:06:28', null, '', null, '2018-01-17 16:06:28', null, null, '2', '497', '339', '497');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '316', '2018-01-17 16:07:34', null, '', null, '2018-01-22 09:10:16', null, null, '2', '38', '339', '497');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '317', '2018-01-17 16:10:08', null, '', null, '2018-01-22 09:10:37', null, null, '2', '38', '339', '497');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '318', '2018-01-17 16:33:51', null, '', null, '2018-01-17 16:34:42', null, null, '2', '78', '375', '544');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '319', '2018-01-17 16:49:10', null, '', null, '2018-01-17 16:49:49', null, null, '2', '78', '375', '544');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '320', '2018-01-18 09:00:56', null, '', null, '2018-01-18 09:07:34', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '321', '2018-01-18 09:05:02', null, '', null, '2018-01-18 09:14:46', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '322', '2018-01-18 09:06:51', null, '', null, '2018-01-18 09:14:03', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '323', '2018-01-18 09:08:09', null, '', null, '2018-01-18 09:13:39', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '324', '2018-01-18 09:09:36', null, '', null, '2018-01-18 09:13:12', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '325', '2018-01-18 09:38:29', null, '', null, '2018-01-18 09:38:29', null, null, '2', '489', '331', '489');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '326', '2018-01-18 09:49:48', null, '', null, '2018-01-18 10:13:27', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '327', '2018-01-18 09:51:06', null, '', null, '2018-01-18 10:13:55', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '328', '2018-01-18 09:52:15', null, '', null, '2018-01-18 10:15:36', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '329', '2018-01-18 09:53:38', null, '', null, '2018-01-18 10:16:02', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '330', '2018-01-18 09:54:45', null, '', null, '2018-01-18 10:16:29', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '331', '2018-01-18 09:55:59', null, '', null, '2018-01-18 10:12:26', null, null, '2', '24', '180', '248');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '332', '2018-01-18 09:56:08', null, '', null, '2018-01-18 10:16:52', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '333', '2018-01-18 09:57:25', null, '', null, '2018-01-18 10:17:19', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '334', '2018-01-18 09:58:45', null, '', null, '2018-01-18 10:17:40', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '335', '2018-01-18 10:00:09', null, '', null, '2018-01-18 10:13:01', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '336', '2018-01-18 15:50:04', null, '', null, '2018-01-22 09:13:26', null, null, '2', '38', '340', '498');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '337', '2018-01-18 16:15:46', null, '', null, '2018-01-22 09:13:13', null, null, '2', '38', '340', '498');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '338', '2018-01-18 16:19:58', null, '', null, '2018-01-22 09:12:52', null, null, '2', '38', '340', '498');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '339', '2018-01-18 16:31:33', null, '', null, '2018-01-19 14:23:19', null, null, '2', '24', '310', '405');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '340', '2018-01-20 16:29:16', null, '', null, '2018-01-22 09:50:04', null, null, '2', '78', '374', '537');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '341', '2018-01-20 16:55:13', null, '', null, '2018-01-22 09:50:26', null, null, '2', '78', '374', '537');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '342', '2018-01-20 17:05:43', null, '', null, '2018-01-22 09:50:56', null, null, '2', '78', '374', '537');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '343', '2018-01-21 14:43:45', null, '', null, '2018-01-22 09:12:07', null, null, '2', '38', '340', '498');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '344', '2018-01-22 11:05:48', null, '', null, '2018-01-22 14:26:56', null, null, '2', '24', '310', '405');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '345', '2018-01-22 15:46:20', null, '', null, '2018-01-23 15:46:54', null, null, '2', '24', '323', '479');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '346', '2018-01-22 16:07:43', null, '', null, '2018-01-23 15:45:35', null, null, '2', '24', '323', '479');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '347', '2018-01-22 16:14:08', null, '', null, '2018-01-23 15:45:08', null, null, '2', '24', '323', '479');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '348', '2018-01-22 16:22:56', null, '', null, '2018-01-23 15:44:47', null, null, '2', '24', '323', '479');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '349', '2018-01-22 16:36:01', null, '', null, '2018-01-23 15:44:30', null, null, '2', '24', '323', '479');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '350', '2018-01-23 10:50:34', null, '', null, '2018-01-23 14:55:58', null, null, '2', '38', '340', '498');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '351', '2018-01-23 14:03:58', null, '', null, '2018-01-23 14:03:58', null, null, '2', '558', '379', '558');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '352', '2018-01-23 14:53:23', null, '', null, '2018-01-24 09:16:25', null, null, '2', '38', '378', '554');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '353', '2018-01-23 15:01:02', null, '', null, '2018-01-24 09:16:37', null, null, '2', '38', '378', '554');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '354', '2018-01-23 15:03:19', null, '', null, '2018-01-23 15:03:19', null, null, '2', '42', '34', '42');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '355', '2018-01-23 15:07:53', null, '', null, '2018-01-23 15:07:53', null, null, '2', '479', '323', '479');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '356', '2018-01-23 16:16:26', null, '', null, '2018-01-24 09:43:53', null, null, '2', '24', '379', '558');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '357', '2018-01-23 16:19:41', null, '', null, '2018-01-24 08:51:04', null, null, '2', '24', '379', '558');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '358', '2018-01-23 16:51:05', null, '', null, '2018-01-24 09:04:57', null, null, '2', '22', '380', '559');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '359', '2018-01-23 16:55:02', null, '', null, '2018-01-24 09:05:11', null, null, '2', '22', '380', '559');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '360', '2018-01-23 16:55:32', null, '', null, '2018-01-23 16:55:40', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '361', '2018-01-23 16:57:06', null, '', null, '2018-01-23 16:57:14', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '362', '2018-01-23 16:57:42', null, '', null, '2018-01-24 09:04:22', null, null, '2', '22', '380', '559');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '363', '2018-01-23 16:58:28', null, '', null, '2018-01-23 16:58:35', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '364', '2018-01-23 17:00:14', null, '', null, '2018-01-23 17:00:21', null, null, '2', '24', '321', '477');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '365', '2018-01-23 17:18:36', null, '', null, '2018-01-24 08:59:20', null, null, '2', '22', '381', '568');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '366', '2018-01-24 08:20:15', null, '', null, '2018-01-24 08:58:55', null, null, '2', '22', '381', '568');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '367', '2018-01-24 09:22:16', null, '', null, '2018-01-24 09:38:39', null, null, '2', '38', '350', '509');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '368', '2018-01-24 09:26:59', null, '', null, '2018-01-24 09:26:59', null, null, '2', '568', '381', '568');
INSERT INTO `apply` VALUES ('MandatoryInstrument', '369', '2018-01-24 09:33:48', null, '', null, '2018-01-24 09:38:48', null, null, '2', '38', '350', '509');

-- ----------------------------
-- Table structure for `apply_attachment_list`
-- ----------------------------
DROP TABLE IF EXISTS `apply_attachment_list`;
CREATE TABLE `apply_attachment_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `apply_type_id` bigint(20) DEFAULT NULL,
  `attachment_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgrh0wqxeb6adtlgm5s1o4yjco` (`apply_type_id`),
  KEY `FKosqpsnx5ya8ugfprdsjbgbmsa` (`attachment_id`),
  KEY `FK1uh7qvq35m4kpts7gu8m7kggr` (`create_user_id`),
  CONSTRAINT `FK1uh7qvq35m4kpts7gu8m7kggr` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgrh0wqxeb6adtlgm5s1o4yjco` FOREIGN KEY (`apply_type_id`) REFERENCES `apply_type` (`id`),
  CONSTRAINT `FKosqpsnx5ya8ugfprdsjbgbmsa` FOREIGN KEY (`attachment_id`) REFERENCES `attachment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply_attachment_list
-- ----------------------------

-- ----------------------------
-- Table structure for `apply_field`
-- ----------------------------
DROP TABLE IF EXISTS `apply_field`;
CREATE TABLE `apply_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `required` bit(1) NOT NULL,
  `type` char(20) DEFAULT NULL,
  `warn_message` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `apply_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn4mrcyacqthu38033gw4al3e3` (`apply_type_id`),
  CONSTRAINT `FKn4mrcyacqthu38033gw4al3e3` FOREIGN KEY (`apply_type_id`) REFERENCES `apply_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply_field
-- ----------------------------
INSERT INTO `apply_field` VALUES ('1', '用于与您取得必要的联系', '联络人', 'name', '', 'text', '请输入联络人', '0', '2');
INSERT INTO `apply_field` VALUES ('2', '当主要联络方式联系不上您时可能会使用', '联络手机(备用)', 'slaveContactNumber', '', 'text', '请输入备用联络手机', '2', '2');
INSERT INTO `apply_field` VALUES ('3', '用于与您取得联系', '联系人', 'contactName', '', 'text', '请输入联系人信息', '3', '1');
INSERT INTO `apply_field` VALUES ('4', '佐证图片', '佐证图片', 'images', '', 'images', '如有必要，请您上传相关佐证图片', '5', '1');
INSERT INTO `apply_field` VALUES ('5', '用于接收系统短信提醒（重要!）', '联络手机(主要)', 'contactNumber', '', 'text', '请输入主要联络手机', '1', '2');
INSERT INTO `apply_field` VALUES ('6', '描述您延期检测的原因', '延期检定原因', 'reason', '', 'textarea', '请详细描述延期检测的原因', '4', '1');

-- ----------------------------
-- Table structure for `apply_field_config`
-- ----------------------------
DROP TABLE IF EXISTS `apply_field_config`;
CREATE TABLE `apply_field_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `k` char(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `apply_field_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkk4dtrdvujdnnjt4q88aa85ha` (`apply_field_id`),
  CONSTRAINT `FKkk4dtrdvujdnnjt4q88aa85ha` FOREIGN KEY (`apply_field_id`) REFERENCES `apply_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply_field_config
-- ----------------------------

-- ----------------------------
-- Table structure for `apply_field_record`
-- ----------------------------
DROP TABLE IF EXISTS `apply_field_record`;
CREATE TABLE `apply_field_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  `apply_id` bigint(20) DEFAULT NULL,
  `apply_field_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6l2aphxbfwvgrfwktg7mld3rd` (`apply_id`),
  KEY `FKbld3gqgduymi76xdtj2euhn6l` (`apply_field_id`),
  CONSTRAINT `FK6l2aphxbfwvgrfwktg7mld3rd` FOREIGN KEY (`apply_id`) REFERENCES `apply` (`id`),
  CONSTRAINT `FKbld3gqgduymi76xdtj2euhn6l` FOREIGN KEY (`apply_field_id`) REFERENCES `apply_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply_field_record
-- ----------------------------

-- ----------------------------
-- Table structure for `apply_mandatory_instrument_set`
-- ----------------------------
DROP TABLE IF EXISTS `apply_mandatory_instrument_set`;
CREATE TABLE `apply_mandatory_instrument_set` (
  `overdue_check_apply_id` bigint(20) NOT NULL,
  `mandatory_instrument_set_id` bigint(20) NOT NULL,
  PRIMARY KEY (`overdue_check_apply_id`,`mandatory_instrument_set_id`),
  KEY `FK4tn1hfjxocoikt61xd2acsc9e` (`mandatory_instrument_set_id`),
  CONSTRAINT `FK4tn1hfjxocoikt61xd2acsc9e` FOREIGN KEY (`mandatory_instrument_set_id`) REFERENCES `instrument_employment_info` (`id`),
  CONSTRAINT `FK5hv6c2ansdokdpqabcjcqbowy` FOREIGN KEY (`overdue_check_apply_id`) REFERENCES `apply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply_mandatory_instrument_set
-- ----------------------------
INSERT INTO `apply_mandatory_instrument_set` VALUES ('6', '10');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('6', '11');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('8', '12');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('10', '7');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('10', '8');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('14', '13');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('14', '14');
INSERT INTO `apply_mandatory_instrument_set` VALUES ('35', '8');

-- ----------------------------
-- Table structure for `apply_type`
-- ----------------------------
DROP TABLE IF EXISTS `apply_type`;
CREATE TABLE `apply_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `handle_url_when_apply_done` varchar(255) DEFAULT NULL,
  `is_override_submit_button` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `view_directory` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `workflow_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pavvbfu7wrvtose32486b3d1k` (`flag`),
  UNIQUE KEY `UK_1319j8b4e3lohp95awlbvwf2d` (`name`),
  KEY `FKa2q41irqq8tb4sgsa99n159hr` (`create_user_id`),
  KEY `FKjv0v0qr1ufvupsurx456m98pk` (`workflow_type_id`),
  CONSTRAINT `FKa2q41irqq8tb4sgsa99n159hr` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjv0v0qr1ufvupsurx456m98pk` FOREIGN KEY (`workflow_type_id`) REFERENCES `workflow_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of apply_type
-- ----------------------------
INSERT INTO `apply_type` VALUES ('1', null, '器具在指定的日期内未进行检定的，在送技术机构检定前，应该先提交过期检定申请', 'overdueCheckApply', '', '', '过期未检器具检定申请', 'overdueCheckApply', null, 'instrument/overdueCheckApply/', null, '1');
INSERT INTO `apply_type` VALUES ('2', null, '强制检定器具备案申请', 'forcedAdd', '/MandatoryInstrumentApply/handleWhenApplyDoneByApplyId/', '', '区县器具用户新增器具', 'quxianqijuyonghuxinzengqiju', null, 'instrument/forcedApply/', null, '1');

-- ----------------------------
-- Table structure for `attachment`
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mime` varchar(255) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `save_name` varchar(255) DEFAULT NULL,
  `save_path` varchar(255) DEFAULT NULL,
  `sha1` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `mandatory_instrument_apply_id` bigint(20) DEFAULT NULL,
  `mandatory_instrument_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtask8ickjw84iwlo1j03a87ko` (`operator_id`),
  KEY `FKotjgwpws7rascdqp8g5rq5a66` (`mandatory_instrument_apply_id`),
  KEY `FK2vkou3f3huqdmhkgn1rm383d8` (`mandatory_instrument_id`),
  CONSTRAINT `FK2vkou3f3huqdmhkgn1rm383d8` FOREIGN KEY (`mandatory_instrument_id`) REFERENCES `instrument_employment_info` (`id`),
  CONSTRAINT `FKotjgwpws7rascdqp8g5rq5a66` FOREIGN KEY (`mandatory_instrument_apply_id`) REFERENCES `apply` (`id`),
  CONSTRAINT `FKtask8ickjw84iwlo1j03a87ko` FOREIGN KEY (`operator_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of attachment
-- ----------------------------
INSERT INTO `attachment` VALUES ('1', 'image/png', null, 'png', '962407f8303104d88be29d016ded2f47', '微信图片_20171225100645.png', 'f32ab010ca214b929cc5d13fcd04c591.png', 'attachment\\image\\f32ab010ca214b929cc5d13fcd04c591.png', 'c112cb7e1762b26df95721de9d330c25f60f6f3d', '504192', null, null, null, '121');
INSERT INTO `attachment` VALUES ('2', 'image/png', null, 'png', '04eb1dd9ed4e9eaeb559eb66b1fb3be2', '微信图片_20171225100636.png', '857c2c3b9f9f0ea7c0fa5372a15abc36.png', 'attachment\\image\\857c2c3b9f9f0ea7c0fa5372a15abc36.png', 'cfcc7b55bebcddde816f5821cc668a1ad36eedbf', '567939', null, null, null, '122');
INSERT INTO `attachment` VALUES ('4', 'image/png', null, 'jpg', 'a2494c74f888f4a86ebf979ae2f136c1', '1514182304(1).jpg', '28afb4854069a5f13c61a6561dcee893.jpg', 'attachment\\image\\28afb4854069a5f13c61a6561dcee893.jpg', '64a0c15a034cef8fe043a33e3dc34743b4d84977', '737433', null, null, null, '123');
INSERT INTO `attachment` VALUES ('5', 'image/png', null, 'jpg', '5f7551d3579f6d448e7153be718ee532', '1514182641(1).jpg', '8d9b637d4778585e0651c3801fcbfe69.jpg', 'attachment\\image\\8d9b637d4778585e0651c3801fcbfe69.jpg', '696820dc43f2ce00f3735b599c148df2b661d86b', '531325', null, null, null, '124');
INSERT INTO `attachment` VALUES ('6', 'image/png', null, 'jpg', '7d5016da1a4834c8b01d73e46be1b51f', '1514182681(1).jpg', '92d29796a2ccfc3c9cb04f37f259b435.jpg', 'attachment\\image\\92d29796a2ccfc3c9cb04f37f259b435.jpg', 'f124cd6da55d25b53d25d1517f96b6e9d11b6483', '258485', null, null, null, null);
INSERT INTO `attachment` VALUES ('11', 'image/png', null, 'jpg', '505c50c3c79e5cea4744a91ac80c326c', '1514183056(1).jpg', 'acc0d4d7fa8d0b7cc757e858c1e16e25.jpg', 'attachment\\image\\acc0d4d7fa8d0b7cc757e858c1e16e25.jpg', 'ca68c377f940b8ac51bd25b2272349b192d40f38', '542043', null, null, null, '127');
INSERT INTO `attachment` VALUES ('12', 'image/jpeg', null, 'jpg', '815978750217ae122a3c7d5996c0e5d3', '微信图片_20171226160819.jpg', '8808fadbc4b5409946f16525b0538d76.jpg', 'attachment\\image\\8808fadbc4b5409946f16525b0538d76.jpg', '0c6e11358a8b4e63652e9eedd33a137435730432', '86034', null, null, null, '191');
INSERT INTO `attachment` VALUES ('13', 'image/jpeg', null, 'jpg', '19e259ce3e2ebd63dc44d091a10b725a', '酸度计.jpg', 'ad78de1264fe8a4a53786e32e08312f9.jpg', 'attachment\\image\\ad78de1264fe8a4a53786e32e08312f9.jpg', '8ab9e12571f6bd207abd77abcbbef76b2d9ae7cc', '50937', null, null, null, '455');
INSERT INTO `attachment` VALUES ('14', 'image/jpeg', null, 'jpg', '8c4cc8a87aae59c750913948acd4bfcc', '原子吸收.jpg', '8eef2f7f4a14ded4044d05b046e206d3.jpg', 'attachment\\image\\8eef2f7f4a14ded4044d05b046e206d3.jpg', 'b3ed1e25d00425a3a5827dae6e5702ee0b116ccd', '53451', null, null, null, '576');
INSERT INTO `attachment` VALUES ('15', 'image/jpeg', null, 'jpg', '2abb2473befa4dffda9bd4fc87624256', 'T6.jpg', '61c7473d1bd4a0fab90cd0d44f01bea2.jpg', 'attachment\\image\\61c7473d1bd4a0fab90cd0d44f01bea2.jpg', '9dcca2560daec965b7d507640bcf75c5476edc8f', '42618', null, null, null, '581');
INSERT INTO `attachment` VALUES ('16', 'image/jpeg', null, 'jpg', '71bed61911f31ced82694c8ab1a57d94', '紫外可见分光光度计.jpg', '93d1e318d8ace0d1f42246d0c17db04f.jpg', 'attachment\\image\\93d1e318d8ace0d1f42246d0c17db04f.jpg', '8ded2873d09ef976081978a02f70a40960675116', '40414', null, null, null, '586');
INSERT INTO `attachment` VALUES ('17', 'image/jpeg', null, 'jpg', 'befb6eca5777aab2364a44d0ccaa8320', '原子荧光光度计.jpg', '848ef171fe682147d76e91989d30fe.jpg', 'attachment\\image\\848ef171fe682147d76e91989d30fe.jpg', 'ff616e80154d860a4432ba363fc1678651279cec', '62864', null, null, null, '587');
INSERT INTO `attachment` VALUES ('18', 'image/jpeg', null, 'jpg', 'ffe75d2dfe658eb96c2eedff453a385a', '电子天平.jpg', '6564bb8dc8d4708ae7a22fd76c5cfade.jpg', 'attachment\\image\\6564bb8dc8d4708ae7a22fd76c5cfade.jpg', 'dbeafeac54c2709072f8125a72f00539d298330c', '39825', null, null, null, '588');
INSERT INTO `attachment` VALUES ('19', 'image/jpeg', null, 'jpg', 'f3b4812a558eaddfddba43f18ce22fdc', '电子天平 (2).jpg', 'bfd8a61dd606148330e2bfe35f45fdca.jpg', 'attachment\\image\\bfd8a61dd606148330e2bfe35f45fdca.jpg', 'ec240d6eb85a984587f6258af8383bd69c641e1d', '43606', null, null, null, '589');
INSERT INTO `attachment` VALUES ('20', 'image/jpeg', null, 'jpg', '9c5615cdb54203fa33819a0884527acc', '482284064046355883.jpg', 'b4c3acbc8def1ffa82e268968119c1f5.jpg', 'attachment\\image\\b4c3acbc8def1ffa82e268968119c1f5.jpg', '29e87aa022b7e649f0ebd2067416c5ba1f2a2b1e', '72162', null, null, null, null);
INSERT INTO `attachment` VALUES ('21', 'image/jpeg', null, '5', '2a8efd3a6c69be2e554c555c552fb91f', '0-2.5.jpg', '2295472abe5ac20de0d82d359af43233.5', 'attachment\\image\\2295472abe5ac20de0d82d359af43233.5', 'bb25c9482fe6d0fd865e9fcdf630765da5b21d05', '88782', null, null, null, null);
INSERT INTO `attachment` VALUES ('22', 'image/jpeg', null, 'jpg', 'bf21eae702fa6a03ad6f6181e959900d', '0-4.jpg', 'e4be3614babbb9fa948e0ea155481aec.jpg', 'attachment\\image\\e4be3614babbb9fa948e0ea155481aec.jpg', '1d644069846b8944c8f2a2c0d49b50190ed387c4', '93305', null, null, null, '608');
INSERT INTO `attachment` VALUES ('23', 'image/jpeg', null, '25', 'cfa96ad26bdbd640bfd984d1041fa395', '0-0.25.jpg', '248c7ec9a1b0eeeb8a1b788e845f07c9.25', 'attachment\\image\\248c7ec9a1b0eeeb8a1b788e845f07c9.25', 'ee797e9efd6117a3329de3502d523d33152832cd', '91270', null, null, null, '615');
INSERT INTO `attachment` VALUES ('24', 'image/jpeg', null, 'jpg', '885eb18f506733fafda645ffb8d1aba8', '微信图片_20171230075125.jpg', 'b116866d14b3b967ba8bba58d3081bb7.jpg', 'attachment\\image\\b116866d14b3b967ba8bba58d3081bb7.jpg', 'ed0d1749a69f6b0010b9ea3be0d98e597c664019', '74937', null, null, null, '190');
INSERT INTO `attachment` VALUES ('25', 'image/jpeg', null, 'jpg', 'e106861c543d6f8d88a18347a72429cc', '微信图片_20171230075136.jpg', 'a2e94202cd0b4750d01a9f0c15063e5c.jpg', 'attachment\\image\\a2e94202cd0b4750d01a9f0c15063e5c.jpg', '86de3a48d7904f0926333b1ab3cc50d37b4bf058', '56876', null, null, null, '189');
INSERT INTO `attachment` VALUES ('26', 'image/jpeg', null, 'jpg', 'dcb5a80e222f3fb3851bbd7b0bd19871', '微信图片_20171231094126.jpg', '69c4aa7d88df469c76a0bc6cb23667c2.jpg', 'attachment\\image\\69c4aa7d88df469c76a0bc6cb23667c2.jpg', 'e71c6bc7592476840d5b66bcc68a9aca590d644b', '56929', null, null, null, '626');
INSERT INTO `attachment` VALUES ('27', 'image/jpeg', null, 'jpg', 'f54400d5df2b2adf1aba2d9e6a3da446', '微信图片_20171231095340.jpg', '54895819e4217a16f6bf0f4dde8b740a.jpg', 'attachment\\image\\54895819e4217a16f6bf0f4dde8b740a.jpg', '3b00a0d24980bc6ef3ad81382edff1c2c9514ca1', '45105', null, null, null, '627');
INSERT INTO `attachment` VALUES ('28', 'image/jpeg', null, 'jpg', '812bfb4158d07fb9fb95a25db91c305d', '微信图片_20171231100654.jpg', '67c809f97e79201025812b6952d2ec01.jpg', 'attachment\\image\\67c809f97e79201025812b6952d2ec01.jpg', '687e758eb56ee69b634f7a39cc50faa218fc803c', '48329', null, null, null, '628');
INSERT INTO `attachment` VALUES ('29', 'image/jpeg', null, 'jpg', '1d1355e33d4761af8d0e9dafc9b629ad', '微信图片_20171231101256.jpg', '3fddae9b2d10b9c8f0937c66fdeb02bc.jpg', 'attachment\\image\\3fddae9b2d10b9c8f0937c66fdeb02bc.jpg', 'db593323c740c61bb899d6f0d94a621b4d078f93', '59425', null, null, null, '629');
INSERT INTO `attachment` VALUES ('30', 'image/jpeg', null, 'jpg', '62ce9e0fd36685ee12ea5486c4e3d7f2', '微信图片_20171231102603.jpg', 'a78fd9f2869050875967b1604a93a63a.jpg', 'attachment\\image\\a78fd9f2869050875967b1604a93a63a.jpg', '633b1d05567156c918e37354558cf48e7dd36d1c', '121346', null, null, null, '630');
INSERT INTO `attachment` VALUES ('31', 'image/jpeg', null, 'jpg', '59d51610e3d7875b76167647b19e0a2c', '微信图片_20171231103255.jpg', '59ec1ee315324cc0bf7bc54997799661.jpg', 'attachment\\image\\59ec1ee315324cc0bf7bc54997799661.jpg', '50e7ff00d4838042d50a563468e2f8aabe99fec9', '38051', null, null, null, '631');
INSERT INTO `attachment` VALUES ('32', 'image/jpeg', null, 'jpg', 'db4d0ca5c6d4713f410ff7d37688f22b', '335865347676715876.jpg', 'bb3788dce92ad9c0dd45a86627705e7d.jpg', 'attachment\\image\\bb3788dce92ad9c0dd45a86627705e7d.jpg', '6f49c0ce1423080597d976235e7843fafe870daf', '80616', null, null, null, '632');
INSERT INTO `attachment` VALUES ('34', 'image/jpeg', null, 'jpg', '662a7043d731e84161af9a72c0d78604', '183330968304379177.jpg', 'a42d67570e8c3921fa33174282f5240f.jpg', 'attachment\\image\\a42d67570e8c3921fa33174282f5240f.jpg', '8011692a90a29cc1c7579e312d7ce95e345ed965', '76473', null, null, null, '633');
INSERT INTO `attachment` VALUES ('37', 'image/jpeg', null, 'jpg', '826cd98e17c70cee7ac4c3183fd93d3b', '877724666292935668.jpg', '860cbb67c771dc6a07c62a7d9da59dab.jpg', 'attachment\\image\\860cbb67c771dc6a07c62a7d9da59dab.jpg', '59b2864e8560220cae14df0072523636cfc3b37d', '86105', null, null, null, '634');
INSERT INTO `attachment` VALUES ('38', 'image/jpeg', null, 'jpg', '4d90267f45642879a4f7876911aba8c7', '47096770077458888.jpg', 'e85679b4caa3f5268b214288728bca99.jpg', 'attachment\\image\\e85679b4caa3f5268b214288728bca99.jpg', '59752db170baa592349925e6b08ffb1a661e8297', '74049', null, null, null, '635');
INSERT INTO `attachment` VALUES ('39', 'image/jpeg', null, 'jpg', '826cd98e17c70cee7ac4c3183fd93d3b', '877724666292935668.jpg', '8d58743cc207b1ccc7e1e851ab4b2483.jpg', 'attachment\\image\\8d58743cc207b1ccc7e1e851ab4b2483.jpg', '59b2864e8560220cae14df0072523636cfc3b37d', '86105', null, null, null, null);
INSERT INTO `attachment` VALUES ('40', 'image/jpeg', null, 'jpg', 'db4d0ca5c6d4713f410ff7d37688f22b', '335865347676715876.jpg', '8dd4d418be7eb5e7be1804c87239ca6e.jpg', 'attachment\\image\\8dd4d418be7eb5e7be1804c87239ca6e.jpg', '6f49c0ce1423080597d976235e7843fafe870daf', '80616', null, null, null, null);
INSERT INTO `attachment` VALUES ('41', 'image/jpeg', null, 'jpg', '662a7043d731e84161af9a72c0d78604', '183330968304379177.jpg', 'bb6358378740bdf79f9666a8e3429411.jpg', 'attachment\\image\\bb6358378740bdf79f9666a8e3429411.jpg', '8011692a90a29cc1c7579e312d7ce95e345ed965', '76473', null, null, null, null);
INSERT INTO `attachment` VALUES ('42', 'image/jpeg', null, 'jpg', '5229c64008198772e97a28b59a56b6ce', '130209920279282386.jpg', '990b1519571207d537f67a0f4e1c02bc.jpg', 'attachment\\image\\990b1519571207d537f67a0f4e1c02bc.jpg', '98eafd86b161ce3439ce6ed533b38f62dcde07f7', '77965', null, null, null, null);
INSERT INTO `attachment` VALUES ('43', 'image/jpeg', null, 'jpg', '5229c64008198772e97a28b59a56b6ce', '130209920279282386.jpg', 'f386f83a898b0e9de6a98a0e098e6fba.jpg', 'attachment\\image\\f386f83a898b0e9de6a98a0e098e6fba.jpg', '98eafd86b161ce3439ce6ed533b38f62dcde07f7', '77965', null, null, null, '636');
INSERT INTO `attachment` VALUES ('44', 'image/jpeg', null, 'jpg', 'e74ccdf877ae01c7ca7d0b7065b92d86', '911869870585197356.jpg', '4384ba9a5c4f2319762e4b2fe76a46d9.jpg', 'attachment\\image\\4384ba9a5c4f2319762e4b2fe76a46d9.jpg', '6e699558f2a6dc7dc020348acacf3cbbb499fa3a', '77978', null, null, null, '637');
INSERT INTO `attachment` VALUES ('45', 'image/jpeg', null, 'jpg', '58800dbbe6aba0b082cebd6989a13aa7', '839769804922582180.jpg', '3f3de139cafff0e8e7ee2e935acd323d.jpg', 'attachment\\image\\3f3de139cafff0e8e7ee2e935acd323d.jpg', '014ca3860c379d13516d272f3eec6848268e2b76', '104339', null, null, null, '638');
INSERT INTO `attachment` VALUES ('47', 'image/png', null, 'png', 'd11d408b15814baa5636f59b5b0fabef', '林西华龙矿业有限公司.png', 'b291ea4cd686c71c96e70b9eec0489b6.png', 'attachment\\image\\b291ea4cd686c71c96e70b9eec0489b6.png', 'a11b96f8825105be85df3d1d7d312a92d4efa894', '54431', null, null, null, '681');
INSERT INTO `attachment` VALUES ('48', 'image/png', null, 'png', 'd11d408b15814baa5636f59b5b0fabef', '林西华龙矿业有限公司.png', '5785448fd27d50bf06d17a8b17b190ae.png', 'attachment\\image\\5785448fd27d50bf06d17a8b17b190ae.png', 'a11b96f8825105be85df3d1d7d312a92d4efa894', '54431', null, null, null, '691');
INSERT INTO `attachment` VALUES ('49', 'image/png', null, 'png', 'd11d408b15814baa5636f59b5b0fabef', '林西华龙矿业有限公司.png', 'e3b90e874014d4bda096336542ca04c7.png', 'attachment\\image\\e3b90e874014d4bda096336542ca04c7.png', 'a11b96f8825105be85df3d1d7d312a92d4efa894', '54431', null, null, null, '700');
INSERT INTO `attachment` VALUES ('50', 'image/png', null, 'png', 'd11d408b15814baa5636f59b5b0fabef', '林西华龙矿业有限公司.png', '7b9c8df65aad20cf126283320561743c.png', 'attachment\\image\\7b9c8df65aad20cf126283320561743c.png', 'a11b96f8825105be85df3d1d7d312a92d4efa894', '54431', null, null, null, '701');
INSERT INTO `attachment` VALUES ('51', 'image/jpeg', null, 'jpg', '2f2480afef5ad45365f1a17197dec10c', '1号机.jpg', '5b625269c4b002e233c3d2eedcf044b9.jpg', 'attachment\\image\\5b625269c4b002e233c3d2eedcf044b9.jpg', 'aaba5ce6b6af1de62dc4f69ccdf65f2270d8f5a3', '34787', null, null, null, '818');
INSERT INTO `attachment` VALUES ('52', 'image/jpeg', null, 'jpg', 'a164d85147d514c63bec021295f2b96d', '2号机.jpg', '98744828f94dc1f2fdbe28b06f711a39.jpg', 'attachment\\image\\98744828f94dc1f2fdbe28b06f711a39.jpg', '7c28af6cc02d6affae64ee5adae141106c288a5b', '36693', null, null, null, '819');
INSERT INTO `attachment` VALUES ('53', 'image/jpeg', null, 'jpg', '53ba84c5e0e26affdfb31ef4dfae5c73', '3号机.jpg', 'b8859159c688851ff8c524a8834e1c6c.jpg', 'attachment\\image\\b8859159c688851ff8c524a8834e1c6c.jpg', '6efb9ddbb8d86d35f2671e7acaec5ec6811cdd0b', '184364', null, null, null, '822');
INSERT INTO `attachment` VALUES ('54', 'image/jpeg', null, 'jpg', 'd7b287b2832478c50f2300923c42a0ae', '4号机.jpg', '7fa3669a2e79fc36e8088f9fd66ae2af.jpg', 'attachment\\image\\7fa3669a2e79fc36e8088f9fd66ae2af.jpg', '708d50cd3211b53336379daf44bb70bed10d6d27', '180736', null, null, null, '823');
INSERT INTO `attachment` VALUES ('55', 'image/jpeg', null, 'jpeg', '80d18fa9a0770564864ff5197d251896', '4862.jpeg', '401034dc95f344b4a10e5521509fc776.jpeg', 'attachment\\image\\401034dc95f344b4a10e5521509fc776.jpeg', '1c14daaf099afcbc970470e53b143756f8f5a687', '73866', null, null, null, '838');
INSERT INTO `attachment` VALUES ('56', 'image/jpeg', null, 'jpeg', '396d48958c37f4a3ed75f38a8d326651', '4608.jpeg', 'a316bf17a5f0aaefa1cd6317341a69d8.jpeg', 'attachment\\image\\a316bf17a5f0aaefa1cd6317341a69d8.jpeg', '4aaa807c5261eb3e9ce14d2a9f24434eb3a98346', '72218', null, null, null, '839');
INSERT INTO `attachment` VALUES ('57', 'image/jpeg', null, 'jpeg', '295dedcd4508f39c847f4d7e5b2bbf92', '7060.jpeg', 'c91f478b056b1be3b7e825fd2945df56.jpeg', 'attachment\\image\\c91f478b056b1be3b7e825fd2945df56.jpeg', '702885c3bad21f89ca9633c0071b660f6fb77b62', '72544', null, null, null, '840');
INSERT INTO `attachment` VALUES ('58', 'image/jpeg', null, 'jpeg', 'e7719cf5ca4bfa1859ab5c907f872c21', '7158.jpeg', 'feccc9ce4b4ec286045ccfadc96e52fa.jpeg', 'attachment\\image\\feccc9ce4b4ec286045ccfadc96e52fa.jpeg', 'b8d28007f3eac35e6570d7fd6a292ba050adb374', '65140', null, null, null, '841');
INSERT INTO `attachment` VALUES ('59', 'image/jpeg', null, 'jpg', '3e852a20997fdcaba5963652a11cf2d8', 'QQ图片20171207115112.jpg', 'edae2a67add01fdf282036cbc600cbe6.jpg', 'attachment\\image\\edae2a67add01fdf282036cbc600cbe6.jpg', 'da194f4c97332c41a8898363f352b4c6c3fd9299', '1047970', null, null, null, '49');
INSERT INTO `attachment` VALUES ('60', 'image/jpeg', null, 'jpg', '76dc59a2f3b9a709727f11c7813fc217', '尿素加注机1.jpg', 'a3fea14d8f8f4c40f424b2b9b3356ef0.jpg', 'attachment\\image\\a3fea14d8f8f4c40f424b2b9b3356ef0.jpg', '919230d93264dad9bbc2073070e5f7dac94f82b6', '69483', null, null, null, null);
INSERT INTO `attachment` VALUES ('61', 'image/jpeg', null, 'jpg', 'a597a9fdfc72d521c444fbefc98172d9', '尿素加注机2.jpg', '823d891838603a9427e718ea1e792c97.jpg', 'attachment\\image\\823d891838603a9427e718ea1e792c97.jpg', '109c49681ad68c9fa32e881b8c591fe6cf86e621', '79349', null, null, null, null);

-- ----------------------------
-- Table structure for `backed_reason`
-- ----------------------------
DROP TABLE IF EXISTS `backed_reason`;
CREATE TABLE `backed_reason` (
  `db_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_67i3wy144nsc8n9kiyltcj9aw` (`reason`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of backed_reason
-- ----------------------------
INSERT INTO `backed_reason` VALUES ('MDBackedReason', '1', '22', '2017-12-15 09:46:23');
INSERT INTO `backed_reason` VALUES ('MDBackedReason', '2', 'qw', '2017-12-18 16:28:12');
INSERT INTO `backed_reason` VALUES ('MDBackedReason', '3', '重复备案', '2018-01-02 15:51:01');

-- ----------------------------
-- Table structure for `certificate_status`
-- ----------------------------
DROP TABLE IF EXISTS `certificate_status`;
CREATE TABLE `certificate_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj8xp9i7ouqbthfxvma4koumhe` (`create_user_id`),
  CONSTRAINT `FKj8xp9i7ouqbthfxvma4koumhe` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of certificate_status
-- ----------------------------
INSERT INTO `certificate_status` VALUES ('1', '20170816', '存档', 'cundang', '20170817', null);

-- ----------------------------
-- Table structure for `certified_product`
-- ----------------------------
DROP TABLE IF EXISTS `certified_product`;
CREATE TABLE `certified_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `county` varchar(255) DEFAULT NULL,
  `issue_date` varchar(255) DEFAULT NULL,
  `license_num` varchar(255) DEFAULT NULL,
  `manufacture_unit` varchar(255) DEFAULT NULL,
  `measure_scale` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rank` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `validity_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of certified_product
-- ----------------------------

-- ----------------------------
-- Table structure for `check_apply`
-- ----------------------------
DROP TABLE IF EXISTS `check_apply`;
CREATE TABLE `check_apply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accept_user_phone` varchar(255) DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  `apply_user_phone` varchar(255) DEFAULT NULL,
  `check_place` varchar(255) DEFAULT NULL,
  `is_reply` bit(1) DEFAULT NULL,
  `is_submit` bit(1) DEFAULT NULL,
  `planned_check_date` date DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `reply_remarks` varchar(255) DEFAULT NULL,
  `accept_technical_institution_department_id` bigint(20) DEFAULT NULL,
  `accept_user_id` bigint(20) DEFAULT NULL,
  `apply_instrument_user_department_id` bigint(20) DEFAULT NULL,
  `apply_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6gkohpby4655mjon9nm77be4a` (`accept_technical_institution_department_id`),
  KEY `FK6scds8guyvn0e9oqwdixaugh8` (`accept_user_id`),
  KEY `FKsrjn0vil7c8r6vdyx7e9nub65` (`apply_instrument_user_department_id`),
  KEY `FKockryepqowc1s02u2sndvwxn9` (`apply_user_id`),
  CONSTRAINT `FK6gkohpby4655mjon9nm77be4a` FOREIGN KEY (`accept_technical_institution_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FK6scds8guyvn0e9oqwdixaugh8` FOREIGN KEY (`accept_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKockryepqowc1s02u2sndvwxn9` FOREIGN KEY (`apply_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKsrjn0vil7c8r6vdyx7e9nub65` FOREIGN KEY (`apply_instrument_user_department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of check_apply
-- ----------------------------
INSERT INTO `check_apply` VALUES ('1', '13456788765', '2017-12-15', '', '送检', '', '', '2017-12-06', '', null, '10', '9', '12', '11');
INSERT INTO `check_apply` VALUES ('2', null, '2017-12-15', '', '送检', '', '', null, '', null, '11', null, '12', '11');
INSERT INTO `check_apply` VALUES ('3', null, '2017-12-16', '', '现场', '', '', '2017-12-22', '', null, '11', '10', '12', '11');
INSERT INTO `check_apply` VALUES ('4', null, '2017-12-17', '', '送检', '', '', '2017-12-21', '', null, '11', '10', '12', '11');
INSERT INTO `check_apply` VALUES ('5', null, '2017-12-17', '', '', '', '', '2017-12-28', '', null, '11', '10', '12', '11');
INSERT INTO `check_apply` VALUES ('6', null, '2017-12-17', '', '送检', '', '', '2017-12-22', '', null, '10', '9', '12', '11');
INSERT INTO `check_apply` VALUES ('7', null, '2017-12-17', '', '送检或是现场检定', '', '', null, '这里写备注信息', null, '10', null, '12', '11');
INSERT INTO `check_apply` VALUES ('8', null, '2017-12-17', '', '现场', '', '', '2017-12-23', '', null, '10', '9', '12', '11');
INSERT INTO `check_apply` VALUES ('9', null, '2017-12-17', '', '现场', '', '', '2017-12-23', '', null, '11', '10', '12', '11');
INSERT INTO `check_apply` VALUES ('10', null, '2017-12-18', '', '现场', '', '', '2017-12-27', '', null, '10', '9', '12', '11');
INSERT INTO `check_apply` VALUES ('11', null, '2017-12-18', '', '现场', '', '', '2017-12-25', '', null, '11', '10', '12', '11');
INSERT INTO `check_apply` VALUES ('12', null, '2017-12-18', '', '现场', '', '', '2017-12-23', '', null, '10', '9', '12', '11');
INSERT INTO `check_apply` VALUES ('13', null, '2017-12-18', '', '现场', '', '', '2017-12-28', '', null, '11', '10', '12', '11');
INSERT INTO `check_apply` VALUES ('14', null, '2017-12-18', '', '', '', '', '2017-12-30', '', null, '19', '17', '40', '32');
INSERT INTO `check_apply` VALUES ('15', null, '2017-12-18', '', '', '', '', null, '', null, '10', null, '37', '30');
INSERT INTO `check_apply` VALUES ('16', null, '2017-12-18', '', '天山', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('17', null, '2017-12-19', '', 'gjmnbm', '', '', null, 'vbmdfgm', null, '11', null, '51', '39');
INSERT INTO `check_apply` VALUES ('18', null, '2017-12-19', '', '现场', '', '', null, '', null, '11', '10', '51', '39');
INSERT INTO `check_apply` VALUES ('19', null, '2017-12-19', '', '现场', '', '', '2017-12-28', '', '不想做，别烦我！', '19', '17', '52', '40');
INSERT INTO `check_apply` VALUES ('20', null, '2017-12-19', '', '赤峰市盛港加油站', '', '', null, '', null, '10', null, '57', '44');
INSERT INTO `check_apply` VALUES ('21', null, '2017-12-19', '', '丰富 是否', '', '', null, '我的', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('22', null, '2017-12-19', '', '天山镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('23', null, '2017-12-19', '', '天山镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('24', null, '2017-12-19', '', '阿鲁科尔沁旗天山镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('25', '15004761234', '2017-12-21', '', '', '', '', '2017-12-25', '', null, '18', '16', '72', '57');
INSERT INTO `check_apply` VALUES ('26', null, '2017-12-28', '', '', '', '', null, '', null, '18', '16', '134', '91');
INSERT INTO `check_apply` VALUES ('27', null, '2017-12-28', '', '', '', '', null, '', null, '10', null, '134', '91');
INSERT INTO `check_apply` VALUES ('28', null, '2017-12-28', '', '', '', '', '2017-12-29', '', null, '15', '13', '372', '277');
INSERT INTO `check_apply` VALUES ('29', '5329355', '2017-12-29', '', '', '', '', '2017-12-29', '', null, '18', '16', '414', '311');
INSERT INTO `check_apply` VALUES ('30', '5329355', '2017-12-29', '', '', '', '', '2017-12-29', '', null, '18', '16', '373', '278');
INSERT INTO `check_apply` VALUES ('31', null, '2017-12-29', '', '赤峰市松山区建峰加油站', '', '', null, '两台加油机均为双枪', null, '10', null, '297', '222');
INSERT INTO `check_apply` VALUES ('32', '5329355', '2017-12-29', '', '', '', '', '2018-01-04', '', null, '18', '16', '405', '310');
INSERT INTO `check_apply` VALUES ('33', null, '2018-01-02', '', '', '', '', null, '', null, '10', null, '433', '313');
INSERT INTO `check_apply` VALUES ('34', '5322256', '2018-01-02', '', '', '', '', '2018-01-22', '', null, '18', '16', '433', '313');
INSERT INTO `check_apply` VALUES ('35', null, '2018-01-02', '', '', '', '', null, '', null, '10', null, '456', '316');
INSERT INTO `check_apply` VALUES ('36', null, '2018-01-02', '', '', '', '', null, '', null, '16', null, '458', '317');
INSERT INTO `check_apply` VALUES ('37', '5329355', '2018-01-04', '', '', '', '', null, '', '2018年1月9日送检测所', '18', '16', '418', '312');
INSERT INTO `check_apply` VALUES ('38', null, '2018-01-06', '', '', '', '', null, '', null, '10', null, '405', '310');
INSERT INTO `check_apply` VALUES ('39', null, '2018-01-08', '', '天山镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('40', '5329355', '2018-01-08', '', '', '', '', null, '', '2018年1月9日送检测所', '18', '16', '468', '318');
INSERT INTO `check_apply` VALUES ('41', null, '2018-01-08', '', '阿鲁科尔沁旗天山镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('42', null, '2018-01-09', '', '阿鲁科尔沁旗', '', '', '2018-01-25', '', null, '164', '123', '42', '34');
INSERT INTO `check_apply` VALUES ('43', '123456788952', '2018-01-09', '', '天山镇呢好呢', '', '', '2018-09-06', '', null, '164', '123', '42', '34');
INSERT INTO `check_apply` VALUES ('44', null, '2018-01-09', '', '', '', '', '2018-01-18', '', null, '18', '16', '478', '322');
INSERT INTO `check_apply` VALUES ('45', null, '2018-01-09', '', '', '', '', null, '', null, '10', null, '405', '310');
INSERT INTO `check_apply` VALUES ('46', '毕科研', '2018-01-10', '', '阿鲁科尔沁旗天山镇', '', '', '2018-10-10', '希望你们10月6日前来', null, '164', '123', '42', '34');
INSERT INTO `check_apply` VALUES ('47', null, '2018-01-10', '', '双胜镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('48', '18904763702', '2018-01-10', '', '巴彦花镇', '', '', '2018-01-31', '', '上午', '164', '123', '42', '34');
INSERT INTO `check_apply` VALUES ('49', null, '2018-01-10', '', '阿鲁科尔沁旗天山镇', '', '', null, '', null, '10', null, '42', '34');
INSERT INTO `check_apply` VALUES ('50', null, '2018-01-11', '', '', '', '', null, '', null, '10', null, '84', '65');
INSERT INTO `check_apply` VALUES ('51', null, '2018-01-11', '', '', '', '', null, '', null, '10', null, '186', '131');
INSERT INTO `check_apply` VALUES ('52', null, '2018-01-11', '', '', '', '', null, '', null, '10', null, '186', '131');
INSERT INTO `check_apply` VALUES ('53', null, '2018-01-11', '', '', '', '', null, '', null, '527', '368', '186', '131');
INSERT INTO `check_apply` VALUES ('54', null, '2018-01-15', '', '天山镇东桥东', '', '', null, '', null, '164', null, '533', '370');
INSERT INTO `check_apply` VALUES ('55', null, '2018-01-16', '', '', '', '', null, '', null, '18', '16', '532', '369');
INSERT INTO `check_apply` VALUES ('56', null, '2018-01-16', '', '', '', '', null, '', null, '527', '368', '537', '374');
INSERT INTO `check_apply` VALUES ('57', null, '2018-01-16', '', '', '', '', null, '', null, '10', null, '537', '374');
INSERT INTO `check_apply` VALUES ('58', null, '2018-01-16', '', '阿鲁科尔沁旗天山镇', '', '', null, '', null, '164', null, '510', '351');
INSERT INTO `check_apply` VALUES ('59', null, '2018-01-17', '', '', '', '', null, '', null, '527', '368', '544', '375');
INSERT INTO `check_apply` VALUES ('60', null, '2018-01-17', '', '', '', '', null, '', null, '10', null, '544', '375');
INSERT INTO `check_apply` VALUES ('61', null, '2018-01-18', '', '', '', '', null, '', null, '18', '16', '248', '180');
INSERT INTO `check_apply` VALUES ('62', null, '2018-01-18', '', '', '', '', null, '', null, '18', null, '477', '321');
INSERT INTO `check_apply` VALUES ('63', '5322256', '2018-01-18', '', '', '', '', '2018-01-22', '', null, '18', '16', '477', '321');
INSERT INTO `check_apply` VALUES ('64', '5329355', '2018-01-18', '', '', '', '', '2018-01-22', '', null, '18', '16', '477', '321');
INSERT INTO `check_apply` VALUES ('65', null, '2018-01-23', '', '', '', '', null, '', null, '10', null, '479', '323');
INSERT INTO `check_apply` VALUES ('66', null, '2018-01-23', '', '', '', '', null, '', null, '18', null, '479', '323');
INSERT INTO `check_apply` VALUES ('67', null, '2018-01-24', '', '', '', '', null, '', null, '11', null, '558', '379');
INSERT INTO `check_apply` VALUES ('68', null, '2018-01-24', '', '', '', '', null, '', null, '18', null, '558', '379');

-- ----------------------------
-- Table structure for `check_result`
-- ----------------------------
DROP TABLE IF EXISTS `check_result`;
CREATE TABLE `check_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `parent_check_result_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65v3o8g1xd5dux48i278fsv0i` (`create_user_id`),
  KEY `FKi4ek6178uvsm7nimbjm3oyr07` (`parent_check_result_id`),
  CONSTRAINT `FK65v3o8g1xd5dux48i278fsv0i` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKi4ek6178uvsm7nimbjm3oyr07` FOREIGN KEY (`parent_check_result_id`) REFERENCES `check_result` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of check_result
-- ----------------------------
INSERT INTO `check_result` VALUES ('1', '2017-12-15 08:46:15', '不合格', 'buhege', '2017-12-15 08:46:15', null, null);
INSERT INTO `check_result` VALUES ('2', '2017-12-15 08:46:15', '合格', 'hege', '2017-12-15 08:46:15', null, null);
INSERT INTO `check_result` VALUES ('3', '2017-12-15 08:46:15', '一次合格', 'yicihege', '2017-12-15 08:46:15', null, '2');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_out_of_range` bit(1) DEFAULT NULL,
  `is_standard` bit(1) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `legal_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `register_date` date DEFAULT NULL,
  `registrant_mail` varchar(255) DEFAULT NULL,
  `registrant_name` varchar(255) DEFAULT NULL,
  `registrant_phone` varchar(255) DEFAULT NULL,
  `registrant_tel` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_type_id` bigint(20) DEFAULT NULL,
  `district_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6v5mg6c38a6r3c2lt8ic73sbp` (`create_user_id`),
  KEY `FKlqp34iubxvsqq0169w82fgcrq` (`department_type_id`),
  KEY `FKq72728gx9gxr45fqhobmowktb` (`district_id`),
  CONSTRAINT `FK6v5mg6c38a6r3c2lt8ic73sbp` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlqp34iubxvsqq0169w82fgcrq` FOREIGN KEY (`department_type_id`) REFERENCES `department_type` (`id`),
  CONSTRAINT `FKq72728gx9gxr45fqhobmowktb` FOREIGN KEY (`district_id`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=576 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '测试地址', '机构代码', '2017-12-15 08:46:18', null, null, '法人姓名', '法人电话', '测试器具用户', '联系电话', 'ceshiqijuyonghu', '邮政编码', '1970-01-01', '注册人邮箱', '注册人姓名', '注册人手机', '注册人座机', null, '2017-12-15 08:46:18', null, '3', '4');
INSERT INTO `department` VALUES ('2', '', '', '2017-12-15 08:46:18', null, null, '', '', '内蒙古自治区管理部门', '', 'neimengguzishiquguanglibumeng', '', null, '', '', '', null, null, '2017-12-15 08:46:18', null, '2', '1');
INSERT INTO `department` VALUES ('3', '', '', '2017-12-15 08:46:18', null, null, '', '', '测试市市属器具用户', '', 'ceshishishuqijuyonghu', '', null, '', '', '', null, null, '2017-12-15 08:46:18', null, '3', '3');
INSERT INTO `department` VALUES ('5', '', '', '2017-12-15 08:46:18', null, null, '', '', '测试区县技术机构', '', 'anciqujishujigou', '', null, '', '', '', null, null, '2017-12-15 08:46:18', null, '4', '4');
INSERT INTO `department` VALUES ('6', '', '', '2017-12-15 08:46:18', null, null, '', '', '测试生产企业', '', 'ceshishengcanqiye', '', null, '', '', '', null, null, '2017-12-15 08:46:18', null, '1', '4');
INSERT INTO `department` VALUES ('9', '赤峰市元宝山区平庄镇内环北路西侧', '1150403MB0Q470211', '2017-12-15 09:06:14', null, null, '', '', '赤峰市元宝山区市场监督管理局', '', '', '024076', null, '', '', '', null, null, '2017-12-15 09:06:14', null, '2', '8');
INSERT INTO `department` VALUES ('10', '222', '0066777', '2017-12-15 09:21:54', null, null, '22', '13967654567', '赤峰市所', '', '', '050065', null, '', '22', '13456789876', null, null, '2017-12-15 09:21:54', null, '4', '2');
INSERT INTO `department` VALUES ('11', '222', '1234', '2017-12-15 09:23:23', null, null, '', '', '赤峰质检', '', '', '023456', null, '', '', '', null, null, '2017-12-15 09:23:23', null, '2', '2');
INSERT INTO `department` VALUES ('12', '222', '2334533', '2017-12-15 09:25:53', null, null, '22222', '13654567654', '元宝山面粉厂', '', '', '023987', null, '', '22222', '13987656789', null, null, '2017-12-15 09:25:53', null, '3', '8');
INSERT INTO `department` VALUES ('13', '', '', '2017-12-15 09:41:19', null, null, '', '', '222', '', '', '', null, '', '', '', null, null, '2017-12-15 09:41:19', null, '1', null);
INSERT INTO `department` VALUES ('15', '敖汉旗新惠镇新州街75号', '12150430MB1230879F', '2017-12-15 11:16:47', null, null, '张莫然', '13847642627', '敖汉旗市场监督管理局检验检测中心', '', '', '024300', null, '', '张莫然', '13847642627', null, null, '2017-12-15 11:16:47', null, '4', '6');
INSERT INTO `department` VALUES ('16', '巴林右旗大板镇大板街中段', '752574365', '2017-12-15 11:19:44', null, null, '卢海军', '18904767366', '巴林右旗产品质量计量检测所', '', '', '025150', null, '', '卢海军', '18904767366', null, null, '2017-12-15 11:19:44', null, '4', '5');
INSERT INTO `department` VALUES ('17', '赤峰市喀喇沁旗锦山镇锦山大街123号', '1150428MB0R093685', '2017-12-15 11:26:07', null, null, '胡志恒', '13847628899', '喀喇沁旗市场监督管理局 喀喇沁旗产品质量计量检测所', '', '', '024400', null, '476565721@qq.com', '孙亚娟', '15047622711', '0476-3751322', null, '2018-01-11 16:01:59', null, '4', '12');
INSERT INTO `department` VALUES ('18', '内蒙古赤峰市林西', '11150424MB0Q62256P', '2017-12-15 11:33:03', null, null, '李爱国', '15149075666', '林西县产品质量计量检验检测所', '', '', '025250', null, '', '李爱国', '15149075666', null, null, '2017-12-15 11:33:03', null, '4', '13');
INSERT INTO `department` VALUES ('19', '宁城县天义镇大宁路中段', '121504297013028884', '2017-12-15 11:36:23', null, null, '李俊青', '13789437999', '宁城县产品质量计量检测所', '', '', '024200', null, '', '姜艳凤', '13948863585', null, null, '2017-12-15 11:36:23', null, '4', '11');
INSERT INTO `department` VALUES ('20', '巴林左旗林东西城区契丹大街路北福山路东', '12150422MB0N87784F', '2017-12-15 11:39:20', null, null, '李军', '18947656351', '巴林左旗产品质量检验检测所', '', '', '025450', null, '', '刘培桥', '18947677553', null, null, '2017-12-15 11:39:20', null, '4', '9');
INSERT INTO `department` VALUES ('21', '敖汉旗河东新区新洲街69号', '115043074385025XA', '2017-12-15 11:48:00', null, null, '', '', '敖汉旗市场监督管理局', '', '', '024300', null, '', '', '', null, null, '2017-12-15 11:48:00', null, '2', '6');
INSERT INTO `department` VALUES ('22', '11', '11504020115661578', '2017-12-15 11:51:46', null, null, '', '', '红山区市场监督管理局计量股', '', '', '11', null, '', '', '', null, null, '2017-12-15 11:51:46', null, '2', '15');
INSERT INTO `department` VALUES ('23', '赤峰市喀喇沁旗锦山镇锦山锦桥路1号', '1150428MB0R093685', '2017-12-15 11:54:04', null, null, '', '', '喀喇沁旗市场监督管理局', '', '', '024400', null, '', '', '', null, null, '2017-12-15 11:54:04', null, '2', '12');
INSERT INTO `department` VALUES ('24', '内蒙古赤峰市林西县市', '11150424MB0Q62256P', '2017-12-15 11:56:30', null, null, '', '', '林西县市场监督管理局', '', '', '025250', null, '', '', '', null, null, '2017-12-15 11:56:30', null, '2', '13');
INSERT INTO `department` VALUES ('25', '宁城县天义镇哈河大街东段', '11150429MB0U468075', '2017-12-15 11:58:29', null, null, '', '', '宁城县市场监督管理局', '', '', '024200', null, '', '', '', null, null, '2017-12-15 11:58:29', null, '2', '11');
INSERT INTO `department` VALUES ('26', '松山区松山大街松山法院北侧', '11150404341374131w', '2017-12-15 13:49:37', null, null, '', '', '赤峰市松山区市场监督管理局', '', '', '024005', null, '', '', '', null, null, '2017-12-15 13:49:37', null, '2', '16');
INSERT INTO `department` VALUES ('27', '巴林右旗大板镇查干沐沦街西段北侧', '11150423011576515R', '2017-12-15 14:01:10', null, null, '', '', '巴林右旗市场监督管理局', '', '', '025150', null, '', '', '', null, null, '2017-12-15 14:01:10', null, '2', '5');
INSERT INTO `department` VALUES ('28', '克旗应昌路', '111504257361007Y', '2017-12-15 14:04:03', null, null, '', '', '\"克旗市场监督 管理局\"', '', '', '025350', null, '', '', '', null, null, '2017-12-15 14:04:03', null, '2', '10');
INSERT INTO `department` VALUES ('30', '克旗解放路', '111504257361007Y', '2017-12-15 14:18:41', null, null, '刘子国', '13604765025', '克旗产品质量 计量检测所', '', '', '025350', null, 'zhijiansuo456@126.com', '杨玉民', '13847629620', '5231436', null, '2018-01-12 09:43:26', null, '4', '10');
INSERT INTO `department` VALUES ('31', '', '', '2017-12-16 14:40:02', null, null, '', '', '33', '', '', '', null, '', '', '', null, null, '2017-12-16 14:40:02', null, '1', null);
INSERT INTO `department` VALUES ('32', '', '', '2017-12-16 14:44:59', null, null, '', '', '12123', '', '', '', null, '', '', '', null, null, '2017-12-16 14:44:59', null, '1', null);
INSERT INTO `department` VALUES ('33', '', '', '2017-12-16 15:06:25', null, null, '', '', '122323', '', '', '', null, '', '', '', null, null, '2017-12-16 15:06:25', null, '1', null);
INSERT INTO `department` VALUES ('34', '', '', '2017-12-17 21:10:16', null, null, '', '', '制造单位，输入后需要选择一下', '', '', '', null, '', '', '', null, null, '2017-12-17 21:10:16', null, '1', null);
INSERT INTO `department` VALUES ('35', 'ah', '234567678', '2017-12-18 16:09:29', null, null, 'ceshi', '12345678901', 'ceshi', '', '', '024300', null, '', '002', '12345678901', null, null, '2017-12-27 16:39:47', null, '3', '6');
INSERT INTO `department` VALUES ('37', '123', '123', '2017-12-18 16:11:32', null, null, '123', '15847606708', '123', '', '', '024005', null, '', '123', '15847606708', null, null, '2017-12-18 16:11:32', null, '3', '15');
INSERT INTO `department` VALUES ('38', '内蒙古自治区赤峰市阿鲁科尔沁旗天山镇新城区天元大街71-12', '11150421MBOR87681W', '2017-12-18 16:11:59', null, null, '王升', '13804766747', '阿鲁科尔沁旗市场监督管理局', '', '', '025550', null, 'cfaqsjj@163.com', '林枫', '13804761962', '7220632', null, '2018-01-11 16:03:21', null, '2', '14');
INSERT INTO `department` VALUES ('39', '', '', '2017-12-18 16:12:36', null, null, '', '', 'aohan', '', '', '', null, '', '', '', null, null, '2017-12-18 16:12:36', null, '1', null);
INSERT INTO `department` VALUES ('40', '宁城县', '5253', '2017-12-18 16:14:13', null, null, '李', '15847683354', '辽中京', '', '', '024200', null, '', '李', '15847683354', null, null, '2017-12-18 16:14:13', null, '3', '11');
INSERT INTO `department` VALUES ('41', '内蒙古赤峰市巴林左旗林东镇契丹大街西段', '11150422353121094M', '2017-12-18 16:17:17', null, null, '', '', '巴林左旗市场监督管理局', '', '', '025450', null, '', '', '', null, null, '2017-12-18 16:17:17', null, '2', '9');
INSERT INTO `department` VALUES ('42', '天山镇', '111504219090998988', '2017-12-18 16:20:13', null, null, '你猜啊', '18904761110', '豪大大粮购', '', '', '025550', null, '', '嗯哼', '18911101110', null, null, '2017-12-18 16:20:13', null, '3', '14');
INSERT INTO `department` VALUES ('43', '', '', '2017-12-18 16:21:18', null, null, '', '', '宁城县', '', '', '', null, '', '', '', null, null, '2017-12-18 16:21:18', null, '1', null);
INSERT INTO `department` VALUES ('44', '', '', '2017-12-18 16:22:25', null, null, '', '', '常州市电子汽车衡器厂', '', '', '', null, '', '', '', null, null, '2017-12-18 16:22:25', null, '1', null);
INSERT INTO `department` VALUES ('46', '克旗经棚镇', '1264787', '2017-12-18 16:28:59', null, null, '小明', '13847623355', '黄冈铁矿', '', '', '025350', null, '', '小明', '13847623355', null, null, '2017-12-18 16:28:59', null, '3', '10');
INSERT INTO `department` VALUES ('47', '', '', '2017-12-18 16:29:15', null, null, '', '', '123456', '', '', '', null, '', '', '', null, null, '2017-12-18 16:29:15', null, '1', null);
INSERT INTO `department` VALUES ('48', '喀喇沁旗锦山镇', '12345678', '2017-12-18 21:08:30', null, null, 'sss', '13847645879', '锦山面粉厂', '', '', '024400', null, '', 'sss', '15847678548', null, null, '2017-12-18 21:08:30', null, '3', '12');
INSERT INTO `department` VALUES ('49', '', '', '2017-12-18 21:17:57', null, null, '', '', '2345', '', '', '', null, '', '', '', null, null, '2017-12-18 21:17:57', null, '1', null);
INSERT INTO `department` VALUES ('51', 'sdfedshbgfxn', '25522352305103', '2017-12-19 08:50:37', null, null, 'xvfsdgbnc', '18647615296', 'dgbgfnfg', '', '', '024200', null, '', 'zSErbxdzc', '15651240280', null, null, '2017-12-19 08:50:37', null, '3', '11');
INSERT INTO `department` VALUES ('52', '挖人梵蒂冈', '15042955859462546626', '2017-12-19 09:17:39', null, null, '撞死人', '18647615296', '宁城县家法国红尘', '', '', '024200', null, '', '大锅饭', '18647615296', null, null, '2017-12-19 09:17:39', null, '3', '11');
INSERT INTO `department` VALUES ('53', '宁城县天义镇', '11223344', '2017-12-19 09:17:45', null, null, '太上老君', '13488522477', '宁城县飞滚吧药店', '', '', '024200', null, '', '玉帝', '13488512477', null, null, '2017-12-19 09:17:45', null, '3', '11');
INSERT INTO `department` VALUES ('54', '宁城县天义镇', '1234625615261', '2017-12-19 09:32:07', null, null, '李上天', '13547658555', '太上皇酒店', '', '', '024200', null, '', '李上天', '13547658555', null, null, '2017-12-19 09:32:07', null, '3', '11');
INSERT INTO `department` VALUES ('55', '', '', '2017-12-19 09:37:12', null, null, '', '', '上天', '', '', '', null, '', '', '', null, null, '2017-12-19 09:37:12', null, '1', null);
INSERT INTO `department` VALUES ('57', '哈拉道口镇横牌子村2组', '91150404L187024097', '2017-12-19 11:35:56', null, null, '康会杰', '18678360021', '赤峰市松山区盛港加油站', '', '', '024000', null, '285@qq.com', '王林林', '18678360021', '8420000', null, '2017-12-19 14:42:49', null, '3', '16');
INSERT INTO `department` VALUES ('58', '', '', '2017-12-19 11:44:02', null, null, '', '', '邢台仪通电脑有限公司', '', '', '', null, '', '', '', null, null, '2017-12-19 11:44:02', null, '1', null);
INSERT INTO `department` VALUES ('59', '', '', '2017-12-19 11:54:29', null, null, '', '', '正星科技有限公司', '', '', '', null, '', '', '', null, null, '2017-12-19 11:54:29', null, '1', null);
INSERT INTO `department` VALUES ('61', '123456', '123456', '2017-12-19 14:44:31', null, null, '123', '13013136669', '123456', '', '', '024000', null, '', '123', '13013136669', null, null, '2017-12-19 14:44:31', null, '3', '16');
INSERT INTO `department` VALUES ('62', '123', '123456', '2017-12-19 14:49:12', null, null, '123', '13196969696', '123', '', '', '024000', null, '', '123', '13196969696', null, null, '2017-12-19 14:49:12', null, '3', '16');
INSERT INTO `department` VALUES ('63', '123', '123456', '2017-12-19 14:50:18', null, null, '123', '13196969696', '123', '', '', '123456', null, '', '123', '13196969696', null, null, '2017-12-19 14:50:18', null, '3', '16');
INSERT INTO `department` VALUES ('64', '123', '123456', '2017-12-19 14:51:12', null, null, '123', '13196969696', '123', '', '', '123456', null, '', '123', '13196969696', null, null, '2017-12-19 14:51:12', null, '3', '16');
INSERT INTO `department` VALUES ('65', '123', '123', '2017-12-19 14:52:04', null, null, '123', '13196969696', '123', '', '', '123', null, '', '123', '13196969696', null, null, '2017-12-19 14:52:04', null, '3', '16');
INSERT INTO `department` VALUES ('66', '123', '123', '2017-12-19 14:53:08', null, null, '123', '13196969696', '123', '', '', '123', null, '', '123', '13196969696', null, null, '2017-12-19 14:53:08', null, '3', '16');
INSERT INTO `department` VALUES ('67', '123', '123', '2017-12-19 14:54:11', null, null, '123', '13196969696', '123', '', '', '123', null, '', '123', '13196969696', null, null, '2017-12-19 14:54:11', null, '3', '16');
INSERT INTO `department` VALUES ('68', '123', '123', '2017-12-19 14:54:55', null, null, '123', '13196969696', '123', '', '', '123', null, '', '123', '13196969696', null, null, '2017-12-19 14:54:55', null, '3', '16');
INSERT INTO `department` VALUES ('69', '', '', '2017-12-19 15:18:34', null, null, '', '', '常熟市电子天平厂', '', '', '', null, '', '', '', null, null, '2017-12-19 15:18:34', null, '1', null);
INSERT INTO `department` VALUES ('70', '赤峰市林西县十二吐乡西山根村', '91150424690098825H', '2017-12-21 10:58:23', null, null, '张安立', '15047686632', '赤峰市利拓矿业有限公司', '', '', '025250', null, '', '向再国', '15047686632', null, null, '2017-12-21 10:58:23', null, '3', '13');
INSERT INTO `department` VALUES ('71', '', '', '2017-12-21 11:05:02', null, null, '', '', '中国红旗仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-21 11:05:02', null, '1', null);
INSERT INTO `department` VALUES ('72', '林西县大井镇大发村', '91150424MA0N35XLXN', '2017-12-21 14:49:09', null, null, '姜国严', '13948167590', '林西县天缘食品加工有限公司', '', '', '025250', null, '', '姜国严', '13948167590', null, null, '2017-12-21 14:49:09', null, '3', '13');
INSERT INTO `department` VALUES ('73', '', '', '2017-12-21 14:53:45', null, null, '', '', '北仑天恒自动化仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-21 14:53:45', null, '1', null);
INSERT INTO `department` VALUES ('74', '', '', '2017-12-21 14:57:43', null, null, '', '', '余姚市金诺天平仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-21 14:57:43', null, '1', null);
INSERT INTO `department` VALUES ('76', '巴林右旗宝日勿苏镇新井村四组', '13333333333', '2017-12-21 16:33:19', null, null, '好的', '13333333333', '巴林右旗双利农业专业合作社', '', '', '025150', null, '', '东方', '13333333333', null, null, '2017-12-21 16:33:19', null, '3', '5');
INSERT INTO `department` VALUES ('77', '', '', '2017-12-21 16:35:17', null, null, '', '', '好发', '', '', '', null, '', '', '', null, null, '2017-12-21 16:35:17', null, '1', null);
INSERT INTO `department` VALUES ('78', '翁牛特旗乌丹镇清泉路南段行政办公楼4号楼', '11150426353078380R', '2017-12-21 16:44:56', null, null, '', '', '翁牛特旗市场监督管理局', '', '', '024500', null, '', '', '', null, null, '2017-12-21 16:44:56', null, '2', '7');
INSERT INTO `department` VALUES ('79', '翁牛特旗乌丹镇清泉路南段行政办公楼4号楼', '11150426353078380R', '2017-12-21 16:56:15', null, null, '', '', '翁牛特旗产品质量计量检测所', '', '', '024500', null, '', '', '', null, null, '2017-12-21 16:56:15', null, '2', '7');
INSERT INTO `department` VALUES ('81', '翁牛特旗乌丹镇南外环路', '91150426053925410Q', '2017-12-22 09:29:55', null, null, '冯纪宇', '18047662000', '内蒙古恒都农业开发有限公司', '', '', '024500', null, '', '吕志敏', '18047662000', null, null, '2017-12-22 09:29:55', null, '3', '7');
INSERT INTO `department` VALUES ('82', '', '', '2017-12-22 09:42:50', null, null, '', '', '赤峰长发衡器公司', '', '', '', null, '', '', '', null, null, '2017-12-22 09:42:50', null, '1', null);
INSERT INTO `department` VALUES ('83', '翁牛特旗乌丹镇（全宁路西侧）', '91150426626480683j', '2017-12-22 10:13:03', null, null, '王先清', '18647265981', '安琪酵母（赤峰）有限公司', '', '', '024500', null, 'chengjx@angelyeast.com', '程井祥', '18504766638', '0476-6338177', null, '2017-12-22 11:15:01', null, '3', '7');
INSERT INTO `department` VALUES ('84', '内蒙古自治区赤峰市翁牛特旗玉龙工业园区', '9115042683436021J', '2017-12-22 10:36:29', null, null, '兰国隆', '13394841187', '赤峰广圆电力铁塔制造有限公司', '', '', '024500', null, '1429638516@qq.com', '潘文强', '18248038121', '0476-2870005', null, '2017-12-22 16:59:43', null, '3', '7');
INSERT INTO `department` VALUES ('85', '', '', '2017-12-22 10:37:49', null, null, '', '', '沈阳特种设备压力表厂', '', '', '', null, '', '', '', null, null, '2017-12-22 10:37:49', null, '1', null);
INSERT INTO `department` VALUES ('86', '', '', '2017-12-22 10:39:44', null, null, '', '', '杭州东亚仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:39:44', null, '1', null);
INSERT INTO `department` VALUES ('87', '', '', '2017-12-22 10:40:50', null, null, '', '', '杭州富阳华仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:40:50', null, '1', null);
INSERT INTO `department` VALUES ('88', '', '', '2017-12-22 10:42:02', null, null, '', '', '上海银普仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:42:02', null, '1', null);
INSERT INTO `department` VALUES ('89', '', '', '2017-12-22 10:42:40', null, null, '', '', '富阳华科仪有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:42:40', null, '1', null);
INSERT INTO `department` VALUES ('90', '', '', '2017-12-22 10:44:03', null, null, '', '', '大华仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:44:03', null, '1', null);
INSERT INTO `department` VALUES ('91', '', '', '2017-12-22 10:45:02', null, null, '', '', '武汉测压仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:45:02', null, '1', null);
INSERT INTO `department` VALUES ('92', '', '', '2017-12-22 10:45:22', null, null, '', '', '武汉江新仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:45:22', null, '1', null);
INSERT INTO `department` VALUES ('93', '', '', '2017-12-22 10:46:56', null, null, '', '', '沈阳特种仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-22 10:46:56', null, '1', null);
INSERT INTO `department` VALUES ('94', '', '', '2017-12-22 10:47:29', null, null, '', '', '大连精密仪器仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-22 10:47:29', null, '1', null);
INSERT INTO `department` VALUES ('95', '', '', '2017-12-22 10:48:53', null, null, '', '', '青岛华夏集团有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:48:53', null, '1', null);
INSERT INTO `department` VALUES ('96', '', '', '2017-12-22 10:49:14', null, null, '', '', '天津市新大华仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:49:14', null, '1', null);
INSERT INTO `department` VALUES ('97', '', '', '2017-12-22 10:55:43', null, null, '', '', '青岛华清集团有限公司', '', '', '', null, '', '', '', null, null, '2017-12-22 10:55:43', null, '1', null);
INSERT INTO `department` VALUES ('98', '内蒙古自治区赤峰市翁牛特旗乌丹玉龙食品工业集中区内', '911504007901939421', '2017-12-22 10:59:52', null, null, '闫洪志', '13000000000', '内蒙古凌志马铃薯科技股份有限公司', '', '', '024500', null, '', '闫君', '13000000000', null, null, '2017-12-22 10:59:52', null, '3', '7');
INSERT INTO `department` VALUES ('99', '', '', '2017-12-22 11:02:38', null, null, '', '', '沈阳市特种仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-22 11:02:38', null, '1', null);
INSERT INTO `department` VALUES ('100', '', '', '2017-12-22 11:06:18', null, null, '', '', '上海亿川仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-22 11:06:18', null, '1', null);
INSERT INTO `department` VALUES ('101', '内蒙古自治区翁牛特旗玉龙工业园区', '9115040032958358XG', '2017-12-22 15:13:47', null, null, '闫洪志', '15049988999', '内蒙古凌志法姆福瑞食品有限公司', '', '', '024500', null, 'abbywu@linkagefarmfrites.com', '吴凡', '18703242986', '0476-2275022', null, '2017-12-22 16:57:29', null, '3', '7');
INSERT INTO `department` VALUES ('102', '赤峰市翁牛特旗乌丹镇东道村', '91150426797166533U', '2017-12-22 15:22:08', null, null, 'xxx', '13000000000', '赤峰利源肉类加工有限公司', '', '', '024500', null, '', '张清泉', '13134760268', null, null, '2017-12-22 15:22:08', null, '3', '7');
INSERT INTO `department` VALUES ('103', '翁牛特旗乌丹镇新华街西段北侧', '91150426797155383N', '2017-12-22 15:27:19', null, null, '金德友', '13000000000', '赤峰少郎河酒业有限公司', '', '', '024500', null, '', '田艳慧', '13150917517', null, null, '2017-12-22 15:27:19', null, '3', '7');
INSERT INTO `department` VALUES ('104', '翁牛特旗赤大高速头分地服务区', '911504263184876804', '2017-12-22 15:45:20', null, null, '文静', '13000000000', '内蒙古威斯特燃气控股有限责任公司头分地液化天然气加气站', '', '', '024500', null, '', '付万涛', '15124923528', null, null, '2017-12-22 15:45:20', null, '3', '7');
INSERT INTO `department` VALUES ('105', '翁牛特旗乌丹镇乌丹路桥北', '12150426460390134T', '2017-12-22 15:57:38', null, null, '孙东云', '13948693617', '翁牛特旗蒙医中医医院', '', '', '024500', null, '1739428225@qq.com', '马景国', '13190929436', '0476-6340959', null, '2018-01-12 22:52:37', null, '3', '7');
INSERT INTO `department` VALUES ('106', '翁牛特旗乌丹镇南山', '911504265554911573', '2017-12-22 16:02:40', null, null, '黄守军', '15647661717', '翁牛特旗富龙燃气有限责任公司', '', '', '024500', null, '', '黄守军', '15647661717', null, null, '2017-12-22 16:02:40', null, '3', '7');
INSERT INTO `department` VALUES ('107', '翁牛特旗玉龙工业园区南区', '911504265973072824', '2017-12-22 16:11:10', null, null, '李闯', '13000000000', '翁牛特旗同路单采血浆有限公司', '', '', '024500', null, '', '房xx', '13190924837', null, null, '2017-12-22 16:11:10', null, '3', '7');
INSERT INTO `department` VALUES ('108', '翁牛特旗乌丹镇新城区地质大厦', '1215042677612385XM', '2017-12-22 16:17:01', null, null, '李铁', '13000000000', '翁牛特旗环境保护监测站', '', '', '024500', null, '', '刘海兴', '13947634555', null, null, '2017-12-22 16:17:01', null, '3', '7');
INSERT INTO `department` VALUES ('109', '翁牛特旗乌丹镇清泉路南段路西', '11150426460391436C', '2017-12-22 16:23:48', null, null, '侯利民', '13947677598', '翁牛特旗公安局交通警察大队', '', '', '024500', null, '', '侯利民', '13947677598', null, null, '2017-12-22 16:23:48', null, '3', '7');
INSERT INTO `department` VALUES ('110', '翁牛特旗乌丹镇全宁居委会', '91150400092165899A', '2017-12-22 16:38:13', null, null, '徐稼农', '13000000000', '中粮家佳康（赤峰）有限公司', '', '', '024500', null, '', '程海峰', '18643017792', null, null, '2017-12-22 16:38:13', null, '3', '7');
INSERT INTO `department` VALUES ('111', '翁牛特旗乌丹镇全宁路北段路西', '91150426MAON8Q6HXX', '2017-12-22 16:43:14', null, null, '李海波', '10000000000', '国网内蒙古东部电力有限公司翁牛特旗供电分公司', '', '', '024500', null, '', '裴晓鹏', '13404887171', null, null, '2017-12-22 16:43:14', null, '3', '7');
INSERT INTO `department` VALUES ('112', '翁牛特旗乌丹镇白音汉嘎查梅林营子组', '91150426MAOMWW631X', '2017-12-22 16:56:34', null, null, '陈建新', '13624868982', '翁牛特旗虹霖再生资源回收利用有限公司', '', '', '024500', null, '', '陈建新', '13624868982', null, null, '2017-12-22 16:56:34', null, '3', '7');
INSERT INTO `department` VALUES ('113', '翁牛特旗乌丹镇桥南', '91150426115073533A', '2017-12-22 17:02:56', null, null, '杨旭东', '10000000000', '内蒙古翁牛特旗国家粮食储备库', '', '', '024500', null, '', '赵磊', '13848667662', null, null, '2017-12-22 17:02:56', null, '3', '7');
INSERT INTO `department` VALUES ('114', '', '', '2017-12-23 08:18:50', null, null, '', '', '上海良平仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-23 08:18:50', null, '1', null);
INSERT INTO `department` VALUES ('115', '', '', '2017-12-23 08:27:13', null, null, '', '', '石家庄科信工具有限公司', '', '', '', null, '', '', '', null, null, '2017-12-23 08:27:13', null, '1', null);
INSERT INTO `department` VALUES ('116', '', '', '2017-12-23 08:39:50', null, null, '', '', '宁波鄞州姜山玻璃仪器表厂', '', '', '', null, '', '', '', null, null, '2017-12-23 08:39:50', null, '1', null);
INSERT INTO `department` VALUES ('117', '', '', '2017-12-23 08:59:37', null, null, '', '', '宁波长城精工实业有限公司', '', '', '', null, '', '', '', null, null, '2017-12-23 08:59:37', null, '1', null);
INSERT INTO `department` VALUES ('118', '', '', '2017-12-23 09:01:40', null, null, '', '', '特斯', '', '', '', null, '', '', '', null, null, '2017-12-23 09:01:40', null, '1', null);
INSERT INTO `department` VALUES ('119', '', '', '2017-12-25 08:51:56', null, null, '', '', '河北博云衡器制造有限公司', '', '', '', null, '', '', '', null, null, '2017-12-25 08:51:56', null, '1', null);
INSERT INTO `department` VALUES ('120', '翁牛特旗乌丹镇全宁桥北路东', '91150426099172266Y', '2017-12-25 10:35:17', null, null, '杨志新', '13947684158', '翁牛特旗鑫源气体有限公司', '', '', '024500', null, '', '杨志新', '13947684158', null, null, '2017-12-25 10:35:17', null, '3', '7');
INSERT INTO `department` VALUES ('121', '翁牛特旗玉龙工业园区南区', '91150426085181903W', '2017-12-25 11:07:57', null, null, '张子清', '13789692999', '赤峰华远酒业有限公司', '', '', '024500', null, '813665551@qq.com', '赵国民', '13847691599', '0476-5635959', null, '2017-12-26 18:55:44', null, '3', '7');
INSERT INTO `department` VALUES ('122', '翁旗五分地镇医院对过', '91150426701282725', '2017-12-25 11:20:29', null, null, '赵光坡', '15774875586', '翁牛特旗五分地镇海运加油站', '', '', '024500', null, '', '赵光坡', '15774875586', null, null, '2017-12-25 11:20:29', null, '3', '7');
INSERT INTO `department` VALUES ('123', '', '', '2017-12-25 14:13:35', null, null, '', '', '常熟市八字轻工机械厂', '', '', '', null, '', '', '', null, null, '2017-12-25 14:13:35', null, '1', null);
INSERT INTO `department` VALUES ('124', '', '', '2017-12-25 14:19:46', null, null, '', '', '香河砝码制造厂', '', '', '', null, '', '', '', null, null, '2017-12-25 14:19:46', null, '1', null);
INSERT INTO `department` VALUES ('125', '', '', '2017-12-25 15:34:37', null, null, '', '', '青岛华青集团有限公司', '', '', '', null, '', '', '', null, null, '2017-12-25 15:34:37', null, '1', null);
INSERT INTO `department` VALUES ('126', '林西镇南门外工业园区', '91150424787058360F', '2017-12-25 15:35:44', null, null, '张春健', '13804763032', '林西天一矿业开发有限公司', '', '', '025250', null, '', '李亚军', '13947647700', null, null, '2017-12-25 15:35:44', null, '3', '13');
INSERT INTO `department` VALUES ('127', '翁旗食品工业园区', '91150400772200512N', '2017-12-25 15:46:39', null, null, '许录', '10000000000', '内蒙古蒙都羊业食品股份有限公司', '', '', '024500', null, '', '13947694159', '10000000000', null, null, '2017-12-25 15:46:39', null, '3', '7');
INSERT INTO `department` VALUES ('128', '翁牛特旗梧桐花镇工业园区', '91150426099371695K', '2017-12-25 15:52:18', null, null, '张建宏', '13901109992', '赤峰朗晟电子科技有限公司', '', '', '024519', null, 'cflshdz@163.com', '战晓旭', '15047693044', '0476-6506989', null, '2017-12-25 16:47:41', null, '3', '7');
INSERT INTO `department` VALUES ('129', '翁旗梧桐花工业园区', '911504266994870935', '2017-12-25 15:58:52', null, null, '王洪权', '18747856663', '翁牛特旗晨光糠化有限责任公司', '', '', '024500', null, '', '王洪权', '18747856663', null, null, '2017-12-25 15:58:52', null, '3', '7');
INSERT INTO `department` VALUES ('130', '翁旗乌丹镇物流园区白音他拉街路北玉龙路西', '91150426099553122T', '2017-12-25 16:04:39', null, null, '张宝林', '13848493854', '翁牛特旗民禾天然气有限责任公司', '', '', '024500', null, '394572952@qq.com', '高云雷', '15560449393', '6355177', null, '2017-12-26 11:16:12', null, '3', '7');
INSERT INTO `department` VALUES ('131', '翁旗乌丹镇新华街中段', '121504264603901185', '2017-12-25 16:09:12', null, null, '张杰', '13947681868', '翁牛特旗医院', '', '', '024500', null, '380201593@qq.com', '韩景祎', '15847353289', '0476-6340077', null, '2017-12-27 09:11:07', null, '3', '7');
INSERT INTO `department` VALUES ('132', '翁旗乌丹镇全宁路中段路西', '121504264603901778', '2017-12-25 16:13:51', null, null, '王月清', '13848562988', '翁牛特旗妇幼保健保', '', '', '024500', null, '597198155@qq.com', '王洪泽', '15124998860', '6336084', null, '2017-12-26 11:05:01', null, '3', '7');
INSERT INTO `department` VALUES ('133', '翁旗乌丹镇全宁路中段路东', '12150426764461296A', '2017-12-25 16:23:24', null, null, '丛宇', '13847623818', '翁牛特旗疾病预防控制中心', '', '', '024500', null, 'wqjk100@163.com', '孙玉荣', '15047698747', '0476-6355010', null, '2017-12-28 16:37:59', null, '3', '7');
INSERT INTO `department` VALUES ('134', '内蒙古赤峰市林西县林西镇东环', '12150424460370045W', '2017-12-26 08:29:59', null, null, '边剑锋', '13947660719', '林西县医院', '', '', '025250', null, '', '王冰', '15048650898', null, null, '2017-12-26 08:29:59', null, '3', '13');
INSERT INTO `department` VALUES ('135', '翁牛特旗五分地镇', '12150426460390206K', '2017-12-26 08:58:57', null, null, '付德信', '13948463684', '翁牛特旗五分地镇中心卫生院', '', '', '024506', null, '294205778@qq.com', '牟云峰', '13154762287', '6684294', null, '2018-01-02 16:11:05', null, '3', '7');
INSERT INTO `department` VALUES ('136', '翁旗那什罕苏木白音敖包嘎查', '91150426581764331D', '2017-12-26 09:03:11', null, null, '蔡成洪', '13959556688', '翁牛特旗那什罕苏木白音敖包加油站', '', '', '024500', null, '', '蔡成洪', '13959556688', null, null, '2017-12-26 09:03:11', null, '3', '7');
INSERT INTO `department` VALUES ('137', '翁旗白音套海苏木白音套海嘎查', '911504266865156301', '2017-12-26 09:07:07', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木富源达加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:07:07', null, '3', '7');
INSERT INTO `department` VALUES ('138', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:24:33', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:24:33', null, '3', '7');
INSERT INTO `department` VALUES ('139', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:09', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:09', null, '3', '7');
INSERT INTO `department` VALUES ('140', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:25', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:25', null, '3', '7');
INSERT INTO `department` VALUES ('141', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:33', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:33', null, '3', '7');
INSERT INTO `department` VALUES ('142', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:38', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:38', null, '3', '7');
INSERT INTO `department` VALUES ('143', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:41', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:41', null, '3', '7');
INSERT INTO `department` VALUES ('144', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:46', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:46', null, '3', '7');
INSERT INTO `department` VALUES ('145', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:25:56', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:25:56', null, '3', '7');
INSERT INTO `department` VALUES ('146', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:26:03', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:26:03', null, '3', '7');
INSERT INTO `department` VALUES ('147', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:26:09', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:26:09', null, '3', '7');
INSERT INTO `department` VALUES ('148', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:26:17', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:26:17', null, '3', '7');
INSERT INTO `department` VALUES ('149', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:10', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:10', null, '3', '7');
INSERT INTO `department` VALUES ('150', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:19', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:19', null, '3', '7');
INSERT INTO `department` VALUES ('151', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:24', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:24', null, '3', '7');
INSERT INTO `department` VALUES ('152', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:29', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:29', null, '3', '7');
INSERT INTO `department` VALUES ('153', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:36', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:36', null, '3', '7');
INSERT INTO `department` VALUES ('154', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:39', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:39', null, '3', '7');
INSERT INTO `department` VALUES ('155', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:40', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:40', null, '3', '7');
INSERT INTO `department` VALUES ('156', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:42', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:42', null, '3', '7');
INSERT INTO `department` VALUES ('157', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:44', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:44', null, '3', '7');
INSERT INTO `department` VALUES ('158', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:51', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:51', null, '3', '7');
INSERT INTO `department` VALUES ('159', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:27:55', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:27:55', null, '3', '7');
INSERT INTO `department` VALUES ('160', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:28:06', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:28:06', null, '3', '7');
INSERT INTO `department` VALUES ('161', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:28:09', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:28:09', null, '3', '7');
INSERT INTO `department` VALUES ('162', '翁旗白音套海苏木黑鱼泡子嘎查', '9115042668652764X2', '2017-12-26 09:39:17', null, null, '周俊祥', '13847623892', '翁牛特旗白音套海苏木黑鱼泡子加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 09:39:17', null, '3', '7');
INSERT INTO `department` VALUES ('163', '', '', '2017-12-26 09:51:09', null, null, '', '', '青岛金仕达电子科技有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 09:51:09', null, '1', null);
INSERT INTO `department` VALUES ('164', '内蒙古自治区赤峰市阿鲁科尔沁旗天山镇新城区天元大街71-12', '11150421MBOR87681W', '2017-12-26 10:05:32', null, null, '孟和德力根', '13947601879', '阿鲁科尔沁旗市场监督管理局检测所', '', '', '024500', null, '', '孟和德力根', '13947601879', null, null, '2017-12-28 10:03:32', null, '4', '14');
INSERT INTO `department` VALUES ('165', '', '', '2017-12-26 10:05:34', null, null, '', '', '沈阳市特种压力表厂', '', '', '', null, '', '', '', null, null, '2017-12-26 10:05:34', null, '1', null);
INSERT INTO `department` VALUES ('166', '', '', '2017-12-26 10:14:15', null, null, '', '', '青岛冠力特种仪器仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-26 10:14:15', null, '1', null);
INSERT INTO `department` VALUES ('167', '', '', '2017-12-26 10:15:40', null, null, '', '', '北京普析通用仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:15:40', null, '1', null);
INSERT INTO `department` VALUES ('168', '', '', '2017-12-26 10:20:21', null, null, '', '', '郑州正星科技股份有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:20:21', null, '1', null);
INSERT INTO `department` VALUES ('169', '', '', '2017-12-26 10:26:14', null, null, '', '', '天津市压力表厂', '', '', '', null, '', '', '', null, null, '2017-12-26 10:26:14', null, '1', null);
INSERT INTO `department` VALUES ('170', '', '', '2017-12-26 10:28:09', null, null, '', '', '南京质普仪器仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:28:09', null, '1', null);
INSERT INTO `department` VALUES ('171', '', '', '2017-12-26 10:31:48', null, null, '', '', '北京布莱迪仪器仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:31:48', null, '1', null);
INSERT INTO `department` VALUES ('172', '', '', '2017-12-26 10:32:17', null, null, '', '', '上海仪电科学仪器股份有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:32:17', null, '1', null);
INSERT INTO `department` VALUES ('173', '', '', '2017-12-26 10:40:22', null, null, '', '', '北京佳力佳仪表设备厂', '', '', '', null, '', '', '', null, null, '2017-12-26 10:40:22', null, '1', null);
INSERT INTO `department` VALUES ('174', '', '', '2017-12-26 10:45:03', null, null, '', '', '上海精科天美科学仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:45:03', null, '1', null);
INSERT INTO `department` VALUES ('175', '翁旗那什罕苏木白音敖包嘎查', '91150426581764331D', '2017-12-26 10:49:58', null, null, '蔡成洪', '13959556688', '翁牛特旗那什罕苏木白音敖包加油站', '', '', '024500', null, '', '蔡成洪', '13959556688', null, null, '2017-12-26 10:49:58', null, '3', '7');
INSERT INTO `department` VALUES ('176', '', '', '2017-12-26 10:54:40', null, null, '', '', '沈阳航天新阳机电有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 10:54:40', null, '1', null);
INSERT INTO `department` VALUES ('177', '翁旗高日罕苏木敖包嘎查', '91150426L01153917B', '2017-12-26 11:22:40', null, null, '周俊祥', '13847623892', '翁牛特旗高日罕苏木俊祥加油站', '', '', '024500', null, '', '周俊祥', '13847623892', null, null, '2017-12-26 11:22:40', null, '3', '7');
INSERT INTO `department` VALUES ('178', '', '', '2017-12-26 14:41:47', null, null, '', '', '大连柯力电子衡器公司', '', '', '', null, '', '', '', null, null, '2017-12-26 14:41:47', null, '1', null);
INSERT INTO `department` VALUES ('179', '翁旗玉龙工业园区', '911504263999713778', '2017-12-26 14:42:20', null, null, '李明', '18904768530', '中国石化销售有限公司赤峰石油分公司玉龙北加油站', '', '', '024500', null, '', '张晓丽', '15148109909', null, null, '2017-12-26 14:42:20', null, '3', '7');
INSERT INTO `department` VALUES ('180', '', '', '2017-12-26 14:44:22', null, null, '', '', '唐山太合电子衡器公司', '', '', '', null, '', '', '', null, null, '2017-12-26 14:44:22', null, '1', null);
INSERT INTO `department` VALUES ('181', '', '', '2017-12-26 14:46:16', null, null, '', '', '济南金钟电子衡器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 14:46:16', null, '1', null);
INSERT INTO `department` VALUES ('182', '', '', '2017-12-26 14:48:07', null, null, '', '', '天津市力源衡器厂', '', '', '', null, '', '', '', null, null, '2017-12-26 14:48:07', null, '1', null);
INSERT INTO `department` VALUES ('183', '林西县林西镇常胜村303线南侧', '91150424097933981W', '2017-12-26 14:58:42', null, null, '张宝林', '18648151880', '林西县民禾天然气有限责任公司', '', '', '025250', null, '554513511@qq.com', '王海东', '15047624686', '2850660', null, '2017-12-29 08:32:15', null, '3', '13');
INSERT INTO `department` VALUES ('184', '翁旗乌敦套海镇', '91150426399971406K', '2017-12-26 15:19:06', null, null, '李明', '10000000000', '中国石化销售有限公司内蒙古赤峰石油分公司乌敦套海加油站', '', '', '024500', null, '', '张晓丽', '15148109909', null, null, '2017-12-26 15:19:06', null, '3', '7');
INSERT INTO `department` VALUES ('185', '翁旗广德公镇高家梁村', '91150426566911225N', '2017-12-26 15:22:57', null, null, '李明', '10000000000', '中国石化销售有限公司内蒙古赤峰石油分公司捷达加油站', '', '', '024500', null, '', '张晓丽', '15148109909', null, null, '2017-12-26 15:22:57', null, '3', '7');
INSERT INTO `department` VALUES ('186', '翁旗五分地镇头分地村', '91150426695924866Q', '2017-12-26 15:34:34', null, null, '林广琴', '13722141586', '翁牛特旗运达加油站', '', '', '024500', null, '', '林广琴', '13722141586', null, null, '2017-12-26 15:34:34', null, '3', '7');
INSERT INTO `department` VALUES ('187', '', '', '2017-12-26 15:47:54', null, null, '', '', '淄博新时代仪表制造有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 15:47:54', null, '1', null);
INSERT INTO `department` VALUES ('188', '1', '1', '2017-12-26 15:52:26', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 15:52:26', null, '3', '16');
INSERT INTO `department` VALUES ('189', '', '', '2017-12-26 15:55:25', null, null, '', '', '天津市泰菲特仪器仪表技术有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 15:55:25', null, '1', null);
INSERT INTO `department` VALUES ('190', '1', '1', '2017-12-26 15:55:46', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 15:55:46', null, '3', '16');
INSERT INTO `department` VALUES ('191', '1', '111', '2017-12-26 15:55:54', null, null, '1', '13804760003', '1', '', '', '1', null, '123@123', '1', '13804760003', '1100000', null, '2017-12-26 16:40:22', null, '3', '15');
INSERT INTO `department` VALUES ('192', '', '', '2017-12-26 15:57:09', null, null, '', '', '临沂安福电子有限责任公司', '', '', '', null, '', '', '', null, null, '2017-12-26 15:57:09', null, '1', null);
INSERT INTO `department` VALUES ('193', '', '', '2017-12-26 16:00:26', null, null, '', '', '上海减压器厂有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 16:00:26', null, '1', null);
INSERT INTO `department` VALUES ('194', '翁旗乌丹镇新华街中段', 'PDY60021815042612B2001', '2017-12-26 16:02:54', null, null, '韩树春', '13847651236', '翁牛特旗乌丹镇新华社区卫生服务站', '', '', '024500', null, '', '韩树春', '13847651236', null, null, '2017-12-26 16:02:54', null, '3', '7');
INSERT INTO `department` VALUES ('195', '1', '1', '2017-12-26 16:05:01', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:05:01', null, '3', '16');
INSERT INTO `department` VALUES ('196', '', '', '2017-12-26 16:10:20', null, null, '', '', '中国天津市力源衡器厂', '', '', '', null, '', '', '', null, null, '2017-12-26 16:10:20', null, '1', null);
INSERT INTO `department` VALUES ('197', '', '', '2017-12-26 16:11:52', null, null, '', '', '上海鱼跃医疗有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 16:11:52', null, '1', null);
INSERT INTO `department` VALUES ('198', '', '', '2017-12-26 16:20:01', null, null, '', '', '浙江永康', '', '', '', null, '', '', '', null, null, '2017-12-26 16:20:01', null, '1', null);
INSERT INTO `department` VALUES ('199', '1', '1', '2017-12-26 16:20:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:20:21', null, '3', '16');
INSERT INTO `department` VALUES ('200', '1', '1', '2017-12-26 16:21:01', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:21:01', null, '3', '16');
INSERT INTO `department` VALUES ('201', '1', '1', '2017-12-26 16:21:36', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:21:36', null, '3', '16');
INSERT INTO `department` VALUES ('202', '1', '1', '2017-12-26 16:22:27', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:22:27', null, '3', '16');
INSERT INTO `department` VALUES ('204', '1', '1', '2017-12-26 16:24:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:24:12', null, '3', '16');
INSERT INTO `department` VALUES ('205', '1', '1', '2017-12-26 16:24:40', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:24:40', null, '3', '16');
INSERT INTO `department` VALUES ('206', '1', '1', '2017-12-26 16:25:10', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:25:10', null, '3', '16');
INSERT INTO `department` VALUES ('207', '1', '1', '2017-12-26 16:25:41', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:25:41', null, '3', '16');
INSERT INTO `department` VALUES ('208', '111111', '123456', '2017-12-26 16:26:25', null, null, '111111', '13669696969', 'liangzhan', '', '', '111111', null, '100300@qq.com', '11', '13669696969', '8420606', null, '2017-12-26 16:28:03', null, '3', '16');
INSERT INTO `department` VALUES ('209', '翁旗乌丹镇新华街中段路北', '91150426690074313E', '2017-12-26 16:27:48', null, null, '周贵有', '13804767258', '赤峰五佳铸诚搅拌有限责任公司', '', '', '024500', null, 'qq@1872465058', '付连利', '15847495653', '0467-6333311', null, '2017-12-26 16:34:02', null, '3', '7');
INSERT INTO `department` VALUES ('210', '1', '1', '2017-12-26 16:32:14', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:32:14', null, '3', '16');
INSERT INTO `department` VALUES ('211', '1', '1', '2017-12-26 16:32:49', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:32:49', null, '3', '16');
INSERT INTO `department` VALUES ('212', '1', '1', '2017-12-26 16:33:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:33:21', null, '3', '16');
INSERT INTO `department` VALUES ('213', '1', '1', '2017-12-26 16:33:51', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:33:51', null, '3', '16');
INSERT INTO `department` VALUES ('214', '乌丹镇全宁路北段282号', 'PDY60090415042617D6002', '2017-12-26 16:34:12', null, null, '张吉春', '15849635160', '翁牛特旗乌丹镇全宁路北段路东', '', '', '024500', null, '', '张吉春', '15849635160', null, null, '2017-12-26 16:34:12', null, '3', '7');
INSERT INTO `department` VALUES ('215', '1', '1', '2017-12-26 16:34:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:34:21', null, '3', '16');
INSERT INTO `department` VALUES ('216', '1', '1', '2017-12-26 16:34:52', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:34:52', null, '3', '16');
INSERT INTO `department` VALUES ('217', '1', '1', '2017-12-26 16:35:24', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:35:24', null, '3', '16');
INSERT INTO `department` VALUES ('218', '1', '1', '2017-12-26 16:35:56', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:35:56', null, '3', '16');
INSERT INTO `department` VALUES ('219', '1', '1', '2017-12-26 16:36:32', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:36:32', null, '3', '16');
INSERT INTO `department` VALUES ('220', '1', '1', '2017-12-26 16:37:15', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:37:15', null, '3', '16');
INSERT INTO `department` VALUES ('221', '1', '1', '2017-12-26 16:37:43', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:37:43', null, '3', '16');
INSERT INTO `department` VALUES ('222', '1', '1', '2017-12-26 16:38:14', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:38:14', null, '3', '16');
INSERT INTO `department` VALUES ('223', '', '', '2017-12-26 16:38:48', null, null, '', '', '江苏鱼跃医疗设备有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 16:38:48', null, '1', null);
INSERT INTO `department` VALUES ('224', '1', '1', '2017-12-26 16:38:54', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:38:54', null, '3', '16');
INSERT INTO `department` VALUES ('225', '1', '1', '2017-12-26 16:39:27', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:39:27', null, '3', '16');
INSERT INTO `department` VALUES ('226', '1', '1', '2017-12-26 16:40:00', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:40:00', null, '3', '16');
INSERT INTO `department` VALUES ('227', '1', '1', '2017-12-26 16:40:31', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:40:31', null, '3', '16');
INSERT INTO `department` VALUES ('228', '1', '1', '2017-12-26 16:41:00', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:41:00', null, '3', '16');
INSERT INTO `department` VALUES ('229', '1', '1', '2017-12-26 16:41:29', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:41:29', null, '3', '16');
INSERT INTO `department` VALUES ('230', '1', '1', '2017-12-26 16:42:14', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:42:14', null, '3', '16');
INSERT INTO `department` VALUES ('231', '1', '1', '2017-12-26 16:42:43', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:42:43', null, '3', '16');
INSERT INTO `department` VALUES ('232', '1', '1', '2017-12-26 16:43:17', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:43:17', null, '3', '16');
INSERT INTO `department` VALUES ('233', '1', '1', '2017-12-26 16:43:45', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:43:45', null, '3', '16');
INSERT INTO `department` VALUES ('234', '1', '1', '2017-12-26 16:44:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:44:12', null, '3', '16');
INSERT INTO `department` VALUES ('235', '1', '1', '2017-12-26 16:44:53', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:44:53', null, '3', '16');
INSERT INTO `department` VALUES ('236', '1', '1', '2017-12-26 16:45:26', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:45:26', null, '3', '16');
INSERT INTO `department` VALUES ('237', '1', '1', '2017-12-26 16:45:54', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:45:54', null, '3', '16');
INSERT INTO `department` VALUES ('238', '翁旗五分地镇头分地卫生院', '1215042673324685XJ', '2017-12-26 16:46:14', null, null, '牟鹏', '15049958777', '翁牛特旗五分地镇头分地卫生院', '', '', '024500', null, '', '牟鹏', '15049958777', null, null, '2017-12-26 16:46:14', null, '3', '7');
INSERT INTO `department` VALUES ('239', '1', '1', '2017-12-26 16:46:23', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:46:23', null, '3', '16');
INSERT INTO `department` VALUES ('240', '1', '1', '2017-12-26 16:48:47', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:48:47', null, '3', '16');
INSERT INTO `department` VALUES ('241', '1', '1', '2017-12-26 16:52:15', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-26 16:52:15', null, '3', '16');
INSERT INTO `department` VALUES ('242', '', '', '2017-12-26 17:15:57', null, null, '', '', '青岛工业仪表研究所', '', '', '', null, '', '', '', null, null, '2017-12-26 17:15:57', null, '1', null);
INSERT INTO `department` VALUES ('243', '', '', '2017-12-26 17:19:52', null, null, '', '', '常州市天利控制器制造有限公司', '', '', '', null, '', '', '', null, null, '2017-12-26 17:19:52', null, '1', null);
INSERT INTO `department` VALUES ('244', '', '', '2017-12-26 17:20:40', null, null, '', '', '阳泉市精工特种仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-26 17:20:40', null, '1', null);
INSERT INTO `department` VALUES ('245', '翁牛特旗', '91150426115071810H', '2017-12-27 08:39:18', null, null, '赵志刚', '13404899111', '中国石油翁旗经营部', '', '', '024500', null, '', '苏日娜', '13948636869', null, null, '2017-12-27 08:39:18', null, '3', '7');
INSERT INTO `department` VALUES ('247', '', '', '2017-12-27 09:49:28', null, null, '', '', '金坛', '', '', '', null, '', '', '', null, null, '2017-12-27 09:49:28', null, '1', null);
INSERT INTO `department` VALUES ('248', '林西镇东风村南', '911504247361347143', '2017-12-27 09:55:42', null, null, '付振华', '13789666678', '中石油天然气股份有限公司内蒙古赤峰市林西县经营部', '', '', '025250', null, '', '付振华', '13789666678', null, null, '2017-12-27 09:55:42', null, '3', '13');
INSERT INTO `department` VALUES ('249', '', '', '2017-12-27 10:08:08', null, null, '', '', '三一重工股份有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 10:08:08', null, '1', null);
INSERT INTO `department` VALUES ('250', '', '', '2017-12-27 10:14:48', null, null, '', '', '福州华志科学仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 10:14:48', null, '1', null);
INSERT INTO `department` VALUES ('251', '', '', '2017-12-27 10:21:17', null, null, '', '', '上海永杰衡器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 10:21:17', null, '1', null);
INSERT INTO `department` VALUES ('252', '', '', '2017-12-27 10:23:20', null, null, '', '', '上海舜宇恒丰科学仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 10:23:20', null, '1', null);
INSERT INTO `department` VALUES ('253', '', '', '2017-12-27 10:26:18', null, null, '', '', '常熟市双杰测试仪器厂', '', '', '', null, '', '', '', null, null, '2017-12-27 10:26:18', null, '1', null);
INSERT INTO `department` VALUES ('254', '', '', '2017-12-27 10:29:09', null, null, '', '', '天津市天马仪器厂', '', '', '', null, '', '', '', null, null, '2017-12-27 10:29:09', null, '1', null);
INSERT INTO `department` VALUES ('255', '', '', '2017-12-27 10:31:20', null, null, '', '', '武义奥利亚电子有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 10:31:20', null, '1', null);
INSERT INTO `department` VALUES ('256', '1', '1', '2017-12-27 10:48:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:48:21', null, '3', '16');
INSERT INTO `department` VALUES ('257', '1', '1', '2017-12-27 10:48:52', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:48:52', null, '3', '16');
INSERT INTO `department` VALUES ('258', '1', '1', '2017-12-27 10:49:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:49:21', null, '3', '16');
INSERT INTO `department` VALUES ('259', '1', '1', '2017-12-27 10:49:48', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:49:48', null, '3', '16');
INSERT INTO `department` VALUES ('260', '1', '1', '2017-12-27 10:50:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:50:19', null, '3', '16');
INSERT INTO `department` VALUES ('261', '1', '1', '2017-12-27 10:50:54', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:50:54', null, '3', '16');
INSERT INTO `department` VALUES ('262', '1', '1', '2017-12-27 10:51:28', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:51:28', null, '3', '16');
INSERT INTO `department` VALUES ('263', '1', '1', '2017-12-27 10:51:57', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:51:57', null, '3', '16');
INSERT INTO `department` VALUES ('264', '1', '1', '2017-12-27 10:52:23', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:52:23', null, '3', '16');
INSERT INTO `department` VALUES ('265', '1', '1', '2017-12-27 10:53:02', null, null, '11', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:53:02', null, '3', '16');
INSERT INTO `department` VALUES ('266', '1', '1', '2017-12-27 10:53:31', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:53:31', null, '3', '16');
INSERT INTO `department` VALUES ('267', '1', '1', '2017-12-27 10:54:16', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:54:16', null, '3', '16');
INSERT INTO `department` VALUES ('268', '赤峰市松山区振兴大街25号', '121500404460333033D', '2017-12-27 10:54:43', null, null, '李永革', '13674760007', '松山区妇幼保健所', '', '', '024010', null, '632350504@qq.com', '杨广华', '15804715670', '0476-8443639', null, '2018-01-17 11:59:33', null, '3', '16');
INSERT INTO `department` VALUES ('269', '1', '1', '2017-12-27 10:55:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:55:12', null, '3', '16');
INSERT INTO `department` VALUES ('270', '赤峰市松山区（安庆）工业园区内标准化厂房二期2-4号', '91150404MAONHYT16W', '2017-12-27 10:55:41', null, null, '胡志远', '13694756743', '赤峰华卫医用科技有限公司', '', '', '024000', null, '313215402@qq.com', '司健', '13694756743', '0476-8177096', null, '2018-01-15 11:30:11', null, '3', '16');
INSERT INTO `department` VALUES ('271', '1', '1', '2017-12-27 10:56:07', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:56:07', null, '3', '16');
INSERT INTO `department` VALUES ('272', '1', '1', '2017-12-27 10:56:42', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:56:42', null, '3', '16');
INSERT INTO `department` VALUES ('273', '1', '1', '2017-12-27 10:57:31', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:57:31', null, '3', '16');
INSERT INTO `department` VALUES ('274', '1', '1', '2017-12-27 10:57:58', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:57:58', null, '3', '16');
INSERT INTO `department` VALUES ('275', '1', '1', '2017-12-27 10:58:26', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 10:58:26', null, '3', '16');
INSERT INTO `department` VALUES ('276', '1', '1', '2017-12-27 11:18:01', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:18:01', null, '3', '16');
INSERT INTO `department` VALUES ('277', '1', '1', '2017-12-27 11:18:45', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:18:45', null, '3', '16');
INSERT INTO `department` VALUES ('278', '1', '1', '2017-12-27 11:19:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:19:21', null, '3', '16');
INSERT INTO `department` VALUES ('279', '1', '1', '2017-12-27 11:19:54', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:19:54', null, '3', '16');
INSERT INTO `department` VALUES ('280', '1', '1', '2017-12-27 11:20:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:20:21', null, '3', '16');
INSERT INTO `department` VALUES ('281', '1', '1', '2017-12-27 11:20:52', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:20:52', null, '3', '16');
INSERT INTO `department` VALUES ('282', '1', '1', '2017-12-27 11:21:42', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:21:42', null, '3', '16');
INSERT INTO `department` VALUES ('283', '1', '1', '2017-12-27 11:22:09', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:22:09', null, '3', '16');
INSERT INTO `department` VALUES ('284', '1', '1', '2017-12-27 11:22:50', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:22:50', null, '3', '16');
INSERT INTO `department` VALUES ('285', '1', '1', '2017-12-27 11:23:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:23:19', null, '3', '16');
INSERT INTO `department` VALUES ('286', '1', '1', '2017-12-27 11:23:58', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:23:58', null, '3', '16');
INSERT INTO `department` VALUES ('287', '1', '1', '2017-12-27 11:24:27', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:24:27', null, '3', '16');
INSERT INTO `department` VALUES ('288', '1', '1', '2017-12-27 11:24:56', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:24:56', null, '3', '16');
INSERT INTO `department` VALUES ('289', '1', '1', '2017-12-27 11:25:25', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:25:25', null, '3', '16');
INSERT INTO `department` VALUES ('290', '1', '1', '2017-12-27 11:25:53', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:25:53', null, '3', '16');
INSERT INTO `department` VALUES ('291', '1', '1', '2017-12-27 11:26:20', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:26:20', null, '3', '16');
INSERT INTO `department` VALUES ('292', '1', '1', '2017-12-27 11:26:46', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:26:46', null, '3', '16');
INSERT INTO `department` VALUES ('293', '1', '1', '2017-12-27 11:27:16', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:27:16', null, '3', '16');
INSERT INTO `department` VALUES ('294', '1', '1', '2017-12-27 11:27:47', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:27:47', null, '3', '16');
INSERT INTO `department` VALUES ('295', '1', '1', '2017-12-27 11:28:17', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:28:17', null, '3', '16');
INSERT INTO `department` VALUES ('296', '1', '1', '2017-12-27 11:28:45', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:28:45', null, '3', '16');
INSERT INTO `department` VALUES ('297', '内蒙古自治区赤峰市松山区大夫营子乡崔家营子村1组', '91150404L0115475XK', '2017-12-27 11:29:12', null, null, '黄建峰', '13947634398', '赤峰市松山区建峰加油站', '', '', '024041', null, 'huang123456@qq.com', '黄建峰', '13947634398', '8420007', null, '2017-12-29 10:05:12', null, '3', '16');
INSERT INTO `department` VALUES ('298', '1', '1', '2017-12-27 11:29:39', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:29:39', null, '3', '16');
INSERT INTO `department` VALUES ('299', '1', '1', '2017-12-27 11:30:09', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:30:09', null, '3', '16');
INSERT INTO `department` VALUES ('300', '1', '1', '2017-12-27 11:30:37', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:30:37', null, '3', '16');
INSERT INTO `department` VALUES ('301', '1', '1', '2017-12-27 11:31:11', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:31:11', null, '3', '16');
INSERT INTO `department` VALUES ('302', '1', '1', '2017-12-27 11:31:39', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:31:39', null, '3', '16');
INSERT INTO `department` VALUES ('303', '1', '1', '2017-12-27 11:32:07', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:32:07', null, '3', '16');
INSERT INTO `department` VALUES ('304', '1', '1', '2017-12-27 11:32:35', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:32:35', null, '3', '16');
INSERT INTO `department` VALUES ('305', '1', '1', '2017-12-27 11:33:02', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 11:33:02', null, '3', '16');
INSERT INTO `department` VALUES ('306', '', '', '2017-12-27 13:53:30', null, null, '', '', '上海泸凡自动化仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 13:53:30', null, '1', null);
INSERT INTO `department` VALUES ('307', '翁旗海金山种牛场东分场 一队', '91150426MAON1RM231', '2017-12-27 14:57:59', null, null, '红光', '13734790461', '翁牛特旗海金山种牛场尚美超市', '', '', '024500', null, '', '红光', '13734790461', null, null, '2017-12-27 14:57:59', null, '3', '7');
INSERT INTO `department` VALUES ('308', '', '', '2017-12-27 15:34:19', null, null, '', '', '唐山市天元电子稳重有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 15:34:19', null, '1', null);
INSERT INTO `department` VALUES ('309', '1', '1', '2017-12-27 15:39:13', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:39:13', null, '3', '16');
INSERT INTO `department` VALUES ('310', '1', '1', '2017-12-27 15:40:00', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:40:00', null, '3', '16');
INSERT INTO `department` VALUES ('311', '1', '1', '2017-12-27 15:40:26', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:40:26', null, '3', '16');
INSERT INTO `department` VALUES ('312', '1', '1', '2017-12-27 15:41:11', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:41:11', null, '3', '16');
INSERT INTO `department` VALUES ('313', '1', '1', '2017-12-27 15:41:52', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:41:52', null, '3', '16');
INSERT INTO `department` VALUES ('314', '1', '1', '2017-12-27 15:42:32', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:42:32', null, '3', '16');
INSERT INTO `department` VALUES ('315', '内蒙古自治区赤峰市新城区巴林大街北', '91150400756663066Y', '2017-12-27 15:43:34', null, null, '丽达', '13634769119', '赤峰新城富龙管道燃气有限公司', '', '', '024000', null, '381361318@qq.com', '杨楠', '13634769119', '0476-5897077', null, '2018-01-09 09:50:00', null, '3', '16');
INSERT INTO `department` VALUES ('316', '1', '1', '2017-12-27 15:44:08', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:44:08', null, '3', '16');
INSERT INTO `department` VALUES ('317', '1', '1', '2017-12-27 15:44:47', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:44:47', null, '3', '16');
INSERT INTO `department` VALUES ('318', '1', '1', '2017-12-27 15:45:13', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:45:13', null, '3', '16');
INSERT INTO `department` VALUES ('319', '1', '1', '2017-12-27 15:45:54', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:45:54', null, '3', '16');
INSERT INTO `department` VALUES ('320', '1', '1', '2017-12-27 15:46:44', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:46:44', null, '3', '16');
INSERT INTO `department` VALUES ('321', '1', '1', '2017-12-27 15:47:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:47:19', null, '3', '16');
INSERT INTO `department` VALUES ('322', '1', '1', '2017-12-27 15:51:03', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:51:03', null, '3', '16');
INSERT INTO `department` VALUES ('323', '1', '1', '2017-12-27 15:51:46', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:51:46', null, '3', '16');
INSERT INTO `department` VALUES ('324', '1', '1', '2017-12-27 15:52:18', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:52:18', null, '3', '16');
INSERT INTO `department` VALUES ('325', '1', '1', '2017-12-27 15:52:55', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:52:55', null, '3', '16');
INSERT INTO `department` VALUES ('326', '1', '1', '2017-12-27 15:53:30', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:53:30', null, '3', '16');
INSERT INTO `department` VALUES ('327', '1', '1', '2017-12-27 15:54:10', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:54:10', null, '3', '16');
INSERT INTO `department` VALUES ('328', '1', '1', '2017-12-27 15:54:43', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:54:43', null, '3', '16');
INSERT INTO `department` VALUES ('329', '1', '1', '2017-12-27 15:55:15', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:55:15', null, '3', '16');
INSERT INTO `department` VALUES ('330', '1', '1', '2017-12-27 15:55:52', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:55:52', null, '3', '16');
INSERT INTO `department` VALUES ('331', '1', '1', '2017-12-27 15:56:31', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:56:31', null, '3', '16');
INSERT INTO `department` VALUES ('332', '1', '1', '2017-12-27 15:56:56', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:56:56', null, '3', '16');
INSERT INTO `department` VALUES ('333', '1', '1', '2017-12-27 15:57:31', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:57:31', null, '3', '16');
INSERT INTO `department` VALUES ('334', '1', '1', '2017-12-27 15:58:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:58:12', null, '3', '16');
INSERT INTO `department` VALUES ('335', '1', '1', '2017-12-27 15:58:49', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 15:58:49', null, '3', '16');
INSERT INTO `department` VALUES ('336', '', '', '2017-12-27 15:59:38', null, null, '', '', '深圳邦健生物医疗设备股份公司', '', '', '', null, '', '', '', null, null, '2017-12-27 15:59:38', null, '1', null);
INSERT INTO `department` VALUES ('337', '1', '1', '2017-12-27 16:00:26', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:00:26', null, '3', '16');
INSERT INTO `department` VALUES ('338', '翁旗桥头镇杜家地村原粮站院内', '911504265888311891', '2017-12-27 16:01:07', null, null, '初孟林', '15947162438', '赤峰绿仁粮食贸易有限公司', '', '', '024500', null, '', '初孟林', '15947162438', null, null, '2017-12-27 16:01:07', null, '3', '7');
INSERT INTO `department` VALUES ('339', '1', '1', '2017-12-27 16:01:53', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:01:53', null, '3', '16');
INSERT INTO `department` VALUES ('340', '11', '1', '2017-12-27 16:02:30', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:02:30', null, '3', '16');
INSERT INTO `department` VALUES ('341', '1', '1', '2017-12-27 16:02:56', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:02:56', null, '3', '16');
INSERT INTO `department` VALUES ('342', '1', '1', '2017-12-27 16:03:29', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:03:29', null, '3', '16');
INSERT INTO `department` VALUES ('343', '1', '1', '2017-12-27 16:03:55', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:03:55', null, '3', '16');
INSERT INTO `department` VALUES ('344', '1', '1', '2017-12-27 16:04:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:04:21', null, '3', '16');
INSERT INTO `department` VALUES ('345', '1', '1', '2017-12-27 16:04:50', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:04:50', null, '3', '16');
INSERT INTO `department` VALUES ('346', '1', '1', '2017-12-27 16:05:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:05:19', null, '3', '16');
INSERT INTO `department` VALUES ('347', '1', '1', '2017-12-27 16:05:46', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:05:46', null, '3', '16');
INSERT INTO `department` VALUES ('348', '', '', '2017-12-27 16:05:54', null, null, '', '', '安徽省合肥雄鹰电子电器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 16:05:54', null, '1', null);
INSERT INTO `department` VALUES ('349', '1', '1', '2017-12-27 16:06:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:06:19', null, '3', '16');
INSERT INTO `department` VALUES ('350', '1', '1', '2017-12-27 16:06:47', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:06:47', null, '3', '16');
INSERT INTO `department` VALUES ('351', '1', '1', '2017-12-27 16:07:30', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-27 16:07:30', null, '3', '16');
INSERT INTO `department` VALUES ('352', '翁旗毛山东乡苦力吐村', '12150426460390302-W', '2017-12-27 16:17:43', null, null, '王梭栋', '13948160858', '翁牛特旗毛山东卫生院', '', '', '024500', null, '', '13948160858', '13948160858', null, null, '2017-12-27 16:17:43', null, '3', '7');
INSERT INTO `department` VALUES ('353', '', '', '2017-12-27 16:34:26', null, null, '', '', '沈阳大成仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 16:34:26', null, '1', null);
INSERT INTO `department` VALUES ('354', '翁旗乌敦套海镇政府南100米', '91150526561246297E', '2017-12-27 16:37:45', null, null, '包文志', '13948766399', '翁牛特旗富鸿热力有限公司', '', '', '024500', null, '', '包文志', '13948766399', null, null, '2017-12-27 16:37:45', null, '3', '7');
INSERT INTO `department` VALUES ('355', '翁旗桥头镇北常胜村民组26号', '92150426MAONKY7UXB', '2017-12-27 16:47:23', null, null, '侯国庆', '15847368988', '翁牛特旗桥头镇侯国庆干冰经销处', '', '', '024500', null, '', '侯国庆', '15847368988', null, null, '2017-12-27 16:47:23', null, '3', '7');
INSERT INTO `department` VALUES ('356', '', '', '2017-12-27 16:53:11', null, null, '', '', '上海自动化仪表股份有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 16:53:11', null, '1', null);
INSERT INTO `department` VALUES ('357', '', '', '2017-12-27 16:56:41', null, null, '', '', '上海仪川仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-27 16:56:41', null, '1', null);
INSERT INTO `department` VALUES ('358', '', '', '2017-12-27 16:59:57', null, null, '', '', '常熟市中亚仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-27 16:59:57', null, '1', null);
INSERT INTO `department` VALUES ('359', '', '', '2017-12-27 17:07:04', null, null, '', '', '三星麦迪逊公司', '', '', '', null, '', '', '', null, null, '2017-12-27 17:07:04', null, '1', null);
INSERT INTO `department` VALUES ('360', '', '', '2017-12-27 17:07:55', null, null, '', '', '美国GE公司', '', '', '', null, '', '', '', null, null, '2017-12-27 17:07:55', null, '1', null);
INSERT INTO `department` VALUES ('361', '', '', '2017-12-27 17:09:10', null, null, '', '', '日本日立株式会社', '', '', '', null, '', '', '', null, null, '2017-12-27 17:09:10', null, '1', null);
INSERT INTO `department` VALUES ('362', '', '', '2017-12-27 17:10:08', null, null, '', '', '飞利浦公司', '', '', '', null, '', '', '', null, null, '2017-12-27 17:10:08', null, '1', null);
INSERT INTO `department` VALUES ('363', '', '', '2017-12-28 08:03:28', null, null, '', '', '荷兰普兰梅卡', '', '', '', null, '', '', '', null, null, '2017-12-28 08:03:28', null, '1', null);
INSERT INTO `department` VALUES ('364', '', '', '2017-12-28 08:05:17', null, null, '', '', '西门子医疗系统有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:05:17', null, '1', null);
INSERT INTO `department` VALUES ('365', '', '', '2017-12-28 08:06:35', null, null, '', '', '通用电气有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:06:35', null, '1', null);
INSERT INTO `department` VALUES ('366', '', '', '2017-12-28 08:07:38', null, null, '', '', '中科美伦科技有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:07:38', null, '1', null);
INSERT INTO `department` VALUES ('367', '', '', '2017-12-28 08:13:16', null, null, '', '', '北京福田电子仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:13:16', null, '1', null);
INSERT INTO `department` VALUES ('368', '', '', '2017-12-28 08:18:29', null, null, '', '', '深圳迈瑞生物医疗设备有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:18:29', null, '1', null);
INSERT INTO `department` VALUES ('369', '', '', '2017-12-28 08:23:00', null, null, '', '', '珠海佳润亚新有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:23:00', null, '1', null);
INSERT INTO `department` VALUES ('370', '', '', '2017-12-28 08:24:41', null, null, '', '', '北京世纪今科有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:24:41', null, '1', null);
INSERT INTO `department` VALUES ('371', '', '', '2017-12-28 08:27:04', null, null, '', '', '上海光电医疗设备有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 08:27:04', null, '1', null);
INSERT INTO `department` VALUES ('372', '内蒙古自治区赤峰市敖汉旗四家子镇工业集中区', '911504306673069999', '2017-12-28 10:11:23', null, null, '刘民成', '13931108119', '赤峰博大氧化锌有限公司', '', '', '024329', null, '', '王玉辉', '15847387565', null, null, '2017-12-28 10:11:23', null, '3', '6');
INSERT INTO `department` VALUES ('373', '林西镇北门外', '91150424626494102J', '2017-12-28 10:35:59', null, null, '刘德林', '13947671577', '林西县自来水总公司', '', '', '025250', null, '', '刘德林', '13947671577', null, null, '2017-12-28 10:35:59', null, '3', '13');
INSERT INTO `department` VALUES ('374', '1', '1', '2017-12-28 10:36:50', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:36:50', null, '3', '16');
INSERT INTO `department` VALUES ('375', '1', '1', '2017-12-28 10:37:44', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:37:44', null, '3', '16');
INSERT INTO `department` VALUES ('376', '1', '1', '2017-12-28 10:38:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:38:12', null, '3', '16');
INSERT INTO `department` VALUES ('377', '1', '1', '2017-12-28 10:38:42', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:38:42', null, '3', '16');
INSERT INTO `department` VALUES ('378', '1', '1', '2017-12-28 10:39:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:39:12', null, '3', '16');
INSERT INTO `department` VALUES ('379', '1', '1', '2017-12-28 10:39:44', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:39:44', null, '3', '16');
INSERT INTO `department` VALUES ('380', '1', '1', '2017-12-28 10:40:12', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:40:12', null, '3', '16');
INSERT INTO `department` VALUES ('381', '1', '1', '2017-12-28 10:43:03', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:43:03', null, '3', '16');
INSERT INTO `department` VALUES ('382', '1', '1', '2017-12-28 10:46:52', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:46:52', null, '3', '16');
INSERT INTO `department` VALUES ('383', '1', '1', '2017-12-28 10:47:20', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:47:20', null, '3', '16');
INSERT INTO `department` VALUES ('384', '1', '1', '2017-12-28 10:47:51', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:47:51', null, '3', '16');
INSERT INTO `department` VALUES ('385', '1', '1', '2017-12-28 10:48:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:48:19', null, '3', '16');
INSERT INTO `department` VALUES ('386', '1', '1', '2017-12-28 10:50:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:50:21', null, '3', '16');
INSERT INTO `department` VALUES ('387', '1', '1', '2017-12-28 10:53:25', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:53:25', null, '3', '16');
INSERT INTO `department` VALUES ('388', '1', '1', '2017-12-28 10:54:01', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:54:01', null, '3', '16');
INSERT INTO `department` VALUES ('389', '1', '1', '2017-12-28 10:54:35', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:54:35', null, '3', '16');
INSERT INTO `department` VALUES ('390', '1', '1', '2017-12-28 10:55:01', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:55:01', null, '3', '16');
INSERT INTO `department` VALUES ('391', '1', '1', '2017-12-28 10:55:31', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:55:31', null, '3', '16');
INSERT INTO `department` VALUES ('392', '1', '1', '2017-12-28 10:55:54', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:55:54', null, '3', '16');
INSERT INTO `department` VALUES ('393', '1', '1', '2017-12-28 10:56:21', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:56:21', null, '3', '16');
INSERT INTO `department` VALUES ('394', '1', '1', '2017-12-28 10:56:51', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:56:51', null, '3', '16');
INSERT INTO `department` VALUES ('395', '1', '1', '2017-12-28 10:57:20', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:57:20', null, '3', '16');
INSERT INTO `department` VALUES ('396', '1', '1', '2017-12-28 10:57:44', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 10:57:44', null, '3', '16');
INSERT INTO `department` VALUES ('397', '1', '1', '2017-12-28 11:00:37', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:00:37', null, '3', '16');
INSERT INTO `department` VALUES ('398', '1', '1', '2017-12-28 11:01:19', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:01:19', null, '3', '16');
INSERT INTO `department` VALUES ('399', '1', '1', '2017-12-28 11:01:45', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:01:45', null, '3', '16');
INSERT INTO `department` VALUES ('400', '1', '1', '2017-12-28 11:02:10', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:02:10', null, '3', '16');
INSERT INTO `department` VALUES ('401', '1', '1', '2017-12-28 11:02:38', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:02:38', null, '3', '16');
INSERT INTO `department` VALUES ('402', '1', '1', '2017-12-28 11:03:06', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:03:06', null, '3', '16');
INSERT INTO `department` VALUES ('403', '1', '1', '2017-12-28 11:03:34', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:03:34', null, '3', '16');
INSERT INTO `department` VALUES ('404', '1', '1', '2017-12-28 11:31:42', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2017-12-28 11:31:42', null, '3', '16');
INSERT INTO `department` VALUES ('405', '林西县赤峰北方商贸物流园区', '9115042439948603X9', '2017-12-28 15:59:25', null, null, '高睿华', '13722166667', '中海油赤峰交通新能源有限公司林西四号站', '', '', '025250', null, '', '高睿华', '13722166667', null, null, '2017-12-28 15:59:25', null, '3', '13');
INSERT INTO `department` VALUES ('406', '', '', '2017-12-28 16:34:42', null, null, '', '', '上海宜川上岭仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 16:34:42', null, '1', null);
INSERT INTO `department` VALUES ('407', '', '', '2017-12-28 16:34:52', null, null, '', '', '飞利浦医疗系统公司', '', '', '', null, '', '', '', null, null, '2017-12-28 16:34:52', null, '1', null);
INSERT INTO `department` VALUES ('408', '', '', '2017-12-28 16:37:54', null, null, '', '', '北京万东医疗装备股份有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 16:37:54', null, '1', null);
INSERT INTO `department` VALUES ('409', '', '', '2017-12-28 17:09:12', null, null, '', '', '上海雷磁仪器厂', '', '', '', null, '', '', '', null, null, '2017-12-28 17:09:12', null, '1', null);
INSERT INTO `department` VALUES ('410', '', '', '2017-12-28 19:41:40', null, null, '', '', '沧州市金泰衡器公司', '', '', '', null, '', '', '', null, null, '2017-12-28 19:41:40', null, '1', null);
INSERT INTO `department` VALUES ('411', '', '', '2017-12-28 19:41:56', null, null, '', '', '上海天川仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-28 19:41:56', null, '1', null);
INSERT INTO `department` VALUES ('412', '', '', '2017-12-28 19:51:53', null, null, '', '', '梅特勒-托利多公司', '', '', '', null, '', '', '', null, null, '2017-12-28 19:51:53', null, '1', null);
INSERT INTO `department` VALUES ('413', '', '', '2017-12-28 22:09:44', null, null, '', '', '北京三盈联合石油技术有限公司', '', '', '', null, '', '', '', null, null, '2017-12-28 22:09:44', null, '1', null);
INSERT INTO `department` VALUES ('414', '赤峰市林西县农牧业局三楼', '91150424MAON094J3U', '2017-12-29 08:34:52', null, null, '吴志军', '18745696688', '林西正邦农牧有限公司', '', '', '025250', null, '', '吴志军', '18745696688', null, null, '2017-12-29 08:34:52', null, '3', '13');
INSERT INTO `department` VALUES ('415', '', '', '2017-12-29 08:51:03', null, null, '', '', '北京麦迪克有限公司', '', '', '', null, '', '', '', null, null, '2017-12-29 08:51:03', null, '1', null);
INSERT INTO `department` VALUES ('416', '', '', '2017-12-29 10:15:42', null, null, '', '', '郑州三金石油设备制造有限公司', '', '', '', null, '', '', '', null, null, '2017-12-29 10:15:42', null, '1', null);
INSERT INTO `department` VALUES ('417', '', '', '2017-12-29 10:43:10', null, null, '', '', '稳恩佳力佳（北京）石油化工设备有限公司', '', '', '', null, '', '', '', null, null, '2017-12-29 10:43:10', null, '1', null);
INSERT INTO `department` VALUES ('418', '林西县工业园区一号路北侧', '911504245581107003', '2017-12-29 15:05:52', null, null, '王彦军', '15147678899', '林西华龙矿业有限公司', '', '', '025250', null, '', '王彦军', '15147678899', null, null, '2017-12-29 15:05:52', null, '3', '13');
INSERT INTO `department` VALUES ('419', '', '', '2017-12-29 15:20:52', null, null, '', '', '上海陵光', '', '', '', null, '', '', '', null, null, '2017-12-29 15:20:52', null, '1', null);
INSERT INTO `department` VALUES ('420', '', '', '2017-12-29 15:59:58', null, null, '', '', '北京赛多利斯', '', '', '', null, '', '', '', null, null, '2017-12-29 15:59:58', null, '1', null);
INSERT INTO `department` VALUES ('421', '', '', '2017-12-29 16:15:54', null, null, '', '', '上海正宝压力表长', '', '', '', null, '', '', '', null, null, '2017-12-29 16:15:54', null, '1', null);
INSERT INTO `department` VALUES ('422', '', '', '2017-12-31 09:44:18', null, null, '', '', '上海晓霄实验仪器设备有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 09:44:18', null, '1', null);
INSERT INTO `department` VALUES ('423', '', '', '2017-12-31 09:56:18', null, null, '', '', '上海菁华科技仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 09:56:18', null, '1', null);
INSERT INTO `department` VALUES ('424', '', '', '2017-12-31 10:09:26', null, null, '', '', '上海方峻仪器仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 10:09:26', null, '1', null);
INSERT INTO `department` VALUES ('425', '', '', '2017-12-31 10:15:24', null, null, '', '', '上海菁海仪器有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 10:15:24', null, '1', null);
INSERT INTO `department` VALUES ('426', '', '', '2017-12-31 10:28:31', null, null, '', '', '浙江伯利恒仪器设备有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 10:28:31', null, '1', null);
INSERT INTO `department` VALUES ('427', '', '', '2017-12-31 10:35:27', null, null, '', '', '河北省武强县同辉仪表厂', '', '', '', null, '', '', '', null, null, '2017-12-31 10:35:27', null, '1', null);
INSERT INTO `department` VALUES ('428', '', '', '2017-12-31 14:58:02', null, null, '', '', '天津衡器公司', '', '', '', null, '', '', '', null, null, '2017-12-31 14:58:02', null, '1', null);
INSERT INTO `department` VALUES ('429', '', '', '2017-12-31 15:14:28', null, null, '', '', '上海耀华称重系统有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 15:14:28', null, '1', null);
INSERT INTO `department` VALUES ('430', '', '', '2017-12-31 15:27:26', null, null, '', '', '青州市星河机械制造有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 15:27:26', null, '1', null);
INSERT INTO `department` VALUES ('431', '', '', '2017-12-31 16:08:00', null, null, '', '', '上海威尔泰仪器仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 16:08:00', null, '1', null);
INSERT INTO `department` VALUES ('432', '', '', '2017-12-31 16:36:11', null, null, '', '', '科泰仪器仪表有限公司', '', '', '', null, '', '', '', null, null, '2017-12-31 16:36:11', null, '1', null);
INSERT INTO `department` VALUES ('433', '林西镇南门外工业园区', '91150424787058360F', '2018-01-02 09:16:57', null, null, '张春健', '13804763023', '林西天一矿业开发有限公司', '', '', '025250', null, '', '张春健', '13804763023', null, null, '2018-01-02 09:16:57', null, '3', '13');
INSERT INTO `department` VALUES ('456', '巴林右旗巴彦他拉苏木政府所在地', '911504233530937591', '2018-01-02 15:20:31', null, null, '马怀冬', '15648666522', '巴林右旗东浩科技节水有限公司', '', '', '025150', null, '', '马怀冬', '15648666522', null, null, '2018-01-02 15:20:31', null, '3', '5');
INSERT INTO `department` VALUES ('457', '', '', '2018-01-02 15:31:47', null, null, '', '', '赤峰广和电子衡器有限公司', '', '', '', null, '', '', '', null, null, '2018-01-02 15:31:47', null, '1', null);
INSERT INTO `department` VALUES ('458', '内蒙古自治区赤峰市巴林右旗大板镇巴林路南段西侧（维多利亚一楼）', '150423600123745', '2018-01-02 16:10:54', null, null, '杨洪飞', '13947979595', '巴林右旗帝客隆超市', '', '', '025150', null, '', '杨洪飞', '13947979595', null, null, '2018-01-02 16:10:54', null, '3', '5');
INSERT INTO `department` VALUES ('459', '', '', '2018-01-02 16:31:56', null, null, '', '', '梅特勒-托利多（常州）测量技术有限公司', '', '', '', null, '', '', '', null, null, '2018-01-02 16:31:56', null, '1', null);
INSERT INTO `department` VALUES ('460', '', '', '2018-01-02 16:57:07', null, null, '', '', '昆明金瑞克电子衡量有限公司制造', '', '', '', null, '', '', '', null, null, '2018-01-02 16:57:07', null, '1', null);
INSERT INTO `department` VALUES ('461', '', '', '2018-01-03 19:24:34', null, null, '', '', '杭州富阳华仪仪表有限公司', '', '', '', null, '', '', '', null, null, '2018-01-03 19:24:34', null, '1', null);
INSERT INTO `department` VALUES ('462', '', '', '2018-01-03 19:32:24', null, null, '', '', '江苏宏事达电气制造有限公司', '', '', '', null, '', '', '', null, null, '2018-01-03 19:32:24', null, '1', null);
INSERT INTO `department` VALUES ('463', '', '', '2018-01-03 19:48:12', null, null, '', '', '常州宏力称重设备制造有限公司', '', '', '', null, '', '', '', null, null, '2018-01-03 19:48:12', null, '1', null);
INSERT INTO `department` VALUES ('464', '', '', '2018-01-04 17:24:43', null, null, '', '', '威卡自动化仪表（苏州）有限公司', '', '', '', null, '', '', '', null, null, '2018-01-04 17:24:43', null, '1', null);
INSERT INTO `department` VALUES ('465', '', '', '2018-01-05 10:22:03', null, null, '', '', '沈阳恒凯电子工程有限公司', '', '', '', null, '', '', '', null, null, '2018-01-05 10:22:03', null, '1', null);
INSERT INTO `department` VALUES ('466', '', '', '2018-01-05 10:40:12', null, null, '', '', '北京川速微波科技有限公司', '', '', '', null, '', '', '', null, null, '2018-01-05 10:40:12', null, '1', null);
INSERT INTO `department` VALUES ('467', '', '', '2018-01-05 11:02:17', null, null, '', '', '浙江大华科技股份有限公司', '', '', '', null, '', '', '', null, null, '2018-01-05 11:02:17', null, '1', null);
INSERT INTO `department` VALUES ('468', '林西镇饶州大街97号', '52150424MJY0966993', '2018-01-05 11:25:14', null, null, '杨良喜', '13804763492', '内蒙古林西金城医院', '', '', '025250', null, '', '杨良喜', '13804763492', null, null, '2018-01-05 11:25:14', null, '3', '13');
INSERT INTO `department` VALUES ('469', '', '', '2018-01-05 15:05:13', null, null, '', '', '常熟市压力表厂', '', '', '', null, '', '', '', null, null, '2018-01-05 15:05:13', null, '1', null);
INSERT INTO `department` VALUES ('470', '', '', '2018-01-05 15:17:02', null, null, '', '', '沈阳天泽智能交通工程有限公司', '', '', '', null, '', '', '', null, null, '2018-01-05 15:17:02', null, '1', null);
INSERT INTO `department` VALUES ('471', '', '', '2018-01-05 16:07:08', null, null, '', '', '大连双立科技有限公司', '', '', '', null, '', '', '', null, null, '2018-01-05 16:07:08', null, '1', null);
INSERT INTO `department` VALUES ('473', '内蒙古自治区赤峰市巴林右旗大板镇红旗村二组', '91150423MA0MYQ3L4Y', '2018-01-05 16:34:11', null, null, '程寅峰', '13674871698', '巴林右旗思亲达来生物制品有限公司', '', '', '025150', null, '', '程寅峰', '13674871698', null, null, '2018-01-05 16:34:11', null, '3', '5');
INSERT INTO `department` VALUES ('474', '内蒙古自治区赤峰市巴林右旗大板镇乌兰图嘎查二组', '91150423MA0NBYFW8J', '2018-01-08 08:50:10', null, null, '金锋', '13501021104', '赤峰阿拉坦新资源食品有限公司', '', '', '025150', null, '', '程春明', '13947666678', null, null, '2018-01-08 08:50:10', null, '3', '5');
INSERT INTO `department` VALUES ('475', '', '', '2018-01-08 10:57:34', null, null, '', '', '天津万达佛斯特', '', '', '', null, '', '', '', null, null, '2018-01-08 10:57:34', null, '1', null);
INSERT INTO `department` VALUES ('476', '', '', '2018-01-08 11:10:13', null, null, '', '', '青岛新精密仪表有限公司', '', '', '', null, '', '', '', null, null, '2018-01-08 11:10:13', null, '1', null);
INSERT INTO `department` VALUES ('477', '内蒙古自治区赤峰市林西县林西镇北门外', '9115042474386474XC', '2018-01-08 14:50:20', null, null, '麻玉昌', '13304763358', '林西县旺达液化气供应站', '', '', '025250', null, '', '麻玉昌', '13304763358', null, null, '2018-01-08 14:50:20', null, '3', '13');
INSERT INTO `department` VALUES ('478', '林西县林西镇宝林路1号', '121504246834007904', '2018-01-08 15:28:56', null, null, '杨文泽', '13789699011', '林西县环境监测站', '', '', '025250', null, '', '杨文泽', '13789699011', null, null, '2018-01-08 15:28:56', null, '3', '13');
INSERT INTO `department` VALUES ('479', '林西县林西镇南门外民营工业经济创业园', '91150424L18741416M', '2018-01-08 16:10:11', null, null, '耿思齐', '17091753831', '林西县源泉食品厂', '', '', '025250', null, '', '耿思齐', '17091753831', null, null, '2018-01-08 16:10:11', null, '3', '13');
INSERT INTO `department` VALUES ('480', '', '', '2018-01-08 16:51:45', null, null, '', '', '上海申安医疗器械厂', '', '', '', null, '', '', '', null, null, '2018-01-08 16:51:45', null, '1', null);
INSERT INTO `department` VALUES ('481', '巴林右旗工业园区', '9115042376106114X9', '2018-01-09 14:41:13', null, null, '高秀丽', '13947686128', '巴林右旗东泰液化石油气有限责任公司', '', '', '025150', null, '', '高玉芹', '13947686128', null, null, '2018-01-09 14:41:13', null, '3', '5');
INSERT INTO `department` VALUES ('482', '内蒙古自治区赤峰市巴林右旗工业园区', '911504237901799805', '2018-01-09 15:39:54', null, null, '王景利', '15947060996', '内蒙古大板发电有限责任公司', '', '', '025150', null, '', '李福占', '18648358119', null, null, '2018-01-09 15:39:54', null, '3', '5');
INSERT INTO `department` VALUES ('483', '巴林右旗大板街东段路南', '12150423460360795K', '2018-01-09 16:34:45', null, null, '郭志伟', '13669696969', '巴林右旗妇幼保健计划生育服务中心', '', '', '025150', null, '13669696969@qq.com', '1', '13669696969', '0476-6206179', null, '2018-01-09 16:53:07', null, '3', '5');
INSERT INTO `department` VALUES ('484', '内蒙古自治区赤峰市巴林右旗东南六公里', '91150423787054140N', '2018-01-09 17:24:16', null, null, '李玉斌', '13704766086', '中国石油天然气股份有限公司内蒙古赤峰销售分公司巴林右旗大板东加油站', '', '', '025150', null, 'monglong1@126.com', '李玉斌', '13704766086', '6223447', null, '2018-01-19 10:16:13', null, '3', '5');
INSERT INTO `department` VALUES ('485', '林西县工业园区冶金化工区', '91150424397898735T', '2018-01-10 08:43:28', null, null, '陈木兴', '13600690858', '林西县富强金属有限公司', '', '', '025250', null, '', '陈木兴', '13600690858', null, null, '2018-01-10 08:43:28', null, '3', '13');
INSERT INTO `department` VALUES ('486', '翁旗玉龙工业园区', '91150426558110612H', '2018-01-10 09:01:06', null, null, '乌力吉', '13000000000', '京蓝沐禾节水装备有限公司', '', '', '024500', null, '', '李海红', '15947466689', null, null, '2018-01-10 09:01:06', null, '3', '7');
INSERT INTO `department` VALUES ('487', '', '', '2018-01-10 14:49:04', null, null, '', '', '河南正星', '', '', '', null, '', '', '', null, null, '2018-01-10 14:49:04', null, '1', null);
INSERT INTO `department` VALUES ('488', '阿鲁科尔沁旗天山镇西桥西汉林路北', '121504216834010139', '2018-01-10 15:30:20', null, null, '肖涛', '13847619298', '阿鲁科尔沁旗环境监测站', '', '', '025550', null, '', '郑继羽', '13789793261', null, null, '2018-01-10 15:30:20', null, '3', '14');
INSERT INTO `department` VALUES ('489', '阿鲁科尔沁旗天山镇集体路27号', '11150421K04824133W', '2018-01-10 16:31:23', null, null, '韩琨', '15247643057', '阿鲁科尔沁旗公安局交通管理大队', '', '', '025550', null, '', '项宇航', '15247643057', null, null, '2018-01-10 16:31:23', null, '3', '14');
INSERT INTO `department` VALUES ('490', '天山镇天元大街东段路北', '12150421460340479G', '2018-01-11 08:53:14', null, null, '彭国栋', '15049660055', '阿鲁科尔沁旗医院', '', '', '025550', null, '', '傅冠群', '13847664719', null, null, '2018-01-11 08:53:14', null, '3', '14');
INSERT INTO `department` VALUES ('491', '阿鲁科尔沁旗天山镇西桥西', '52150421460343960H', '2018-01-11 08:57:59', null, null, '姜伟杰', '13789693902', '阿鲁科尔沁旗同济医院', '', '', '025550', null, '895787014@qq.com', '刘海宇', '13789693902', '0476-7212256', null, '2018-01-15 14:27:37', null, '3', '14');
INSERT INTO `department` VALUES ('492', '阿鲁科尔沁旗天山镇汉林路', '121504214603433464', '2018-01-11 09:00:45', null, null, '金朝阳', '13451368990', '阿鲁科尔沁旗中医医院', '', '', '025550', null, '', '魏新', '13451368990', null, null, '2018-01-11 09:00:45', null, '3', '14');
INSERT INTO `department` VALUES ('493', '阿鲁科尔沁旗天山镇集通路东乌力吉沐伦街北', '12150421460340487B', '2018-01-11 09:05:43', null, null, '那日苏', '15849953676', '阿鲁科尔沁旗蒙医医院', '', '', '025550', null, '', '朝鲁门', '15849953676', null, null, '2018-01-11 09:05:43', null, '3', '14');
INSERT INTO `department` VALUES ('494', '阿鲁科尔沁旗温都花煤矿', '911504215528304320', '2018-01-11 09:13:52', null, null, '张志诚', '13948668459', '阿鲁科尔沁旗温都花煤炭加油有限责任公司', '', '', '025550', null, '', '孙小红', '15148118018', null, null, '2018-01-11 09:13:52', null, '3', '14');
INSERT INTO `department` VALUES ('495', '阿鲁科尔沁旗荞麦塔拉乡宝家店村', '911504215528276473', '2018-01-11 09:18:33', null, null, '赵广慧', '15147620815', '阿鲁科尔沁旗荞麦塔拉加油有限责任公司', '', '', '025550', null, '', '赵广慧', '15147620815', null, null, '2018-01-11 09:18:33', null, '3', '14');
INSERT INTO `department` VALUES ('496', '阿鲁科尔沁旗303杨树林村村南', '911504211701296553P', '2018-01-11 09:23:46', null, null, '纪灿瑞', '13088408915', '赤峰市永平安商贸有限责任公司天山加油站', '', '', '025550', null, '', '吴越军', '13088408915', null, null, '2018-01-11 09:23:46', null, '3', '14');
INSERT INTO `department` VALUES ('497', '阿鲁科尔沁旗巴彦花镇东沙布台村', '91150421552815101R', '2018-01-11 09:28:00', null, null, '张燕', '13847666177', '阿鲁科尔沁旗东沙布台油品销售有限公司', '', '', '025550', null, '', '张燕', '13847666177', null, null, '2018-01-11 09:28:00', null, '3', '14');
INSERT INTO `department` VALUES ('498', '内蒙古阿鲁科尔沁旗扎嘎斯台镇乌兰绍荣嘎查', '9115042139+6304237K', '2018-01-11 09:31:23', null, null, '钟勇', '15104812323', '内蒙古阿鲁科尔沁旗太极天驴有限公司', '', '', '025550', null, '', '张宏秀', '15104812323', null, null, '2018-01-11 09:31:23', null, '3', '14');
INSERT INTO `department` VALUES ('499', '', '', '2018-01-11 09:35:28', null, null, '', '', '正兴科技有限公司', '', '', '', null, '', '', '', null, null, '2018-01-11 09:35:28', null, '1', null);
INSERT INTO `department` VALUES ('500', '阿鲁科尔沁旗天山镇新能源产业园区', '91150421050559814T', '2018-01-11 09:35:47', null, null, '陈立东', '13704767837', '赤峰元易生物质科技有限责任公司', '', '', '025550', null, '1434409246@qq.com', '杨冉', '15004761651', '7270296', null, '2018-01-12 15:24:09', null, '3', '14');
INSERT INTO `department` VALUES ('501', '阿鲁科尔沁旗罕苏木苏木', '91150421552826230E', '2018-01-11 09:41:31', null, null, '呼格力勒图', '13948631045', '阿鲁科尔沁旗罕苏木苏木加油有限公司', '', '', '025550', null, '2991571236@qq.com', '高娃', '13948631045', '0476-7420050', null, '2018-01-11 17:31:25', null, '3', '14');
INSERT INTO `department` VALUES ('502', '赤峰市元宝山区五家镇房身村', '12150403mb0u31867n', '2018-01-11 10:07:03', null, null, '姜春艳', '13804765725', '赤峰市元宝山区五家镇中心卫生院', '', '', '024087', null, '', '姜杉', '18504763119', null, null, '2018-01-11 10:07:03', null, '3', '8');
INSERT INTO `department` VALUES ('503', '阿鲁科尔沁旗巴拉奇如德苏木', '911504215817908710', '2018-01-11 10:09:00', null, null, '邹吉明', '13948165005', '阿鲁科尔沁旗巴拉奇如德供销加油站', '', '', '025550', null, '', '邹吉明', '13948165005', null, null, '2018-01-11 10:09:00', null, '3', '14');
INSERT INTO `department` VALUES ('504', '内蒙古赤峰市阿鲁科尔沁旗罕苏木苏木巴彦宝力格', '91150421552826062C', '2018-01-11 10:15:55', null, null, '阿荣', '15048389587', '阿鲁科尔沁旗罕苏木苏木巴彦宝力格加油有限公司', '', '', '025550', null, '921880855@qq.com', '灵云', '15049675073', '0467-7430015', null, '2018-01-11 20:45:28', null, '3', '14');
INSERT INTO `department` VALUES ('505', '阿鲁科尔沁旗天山镇汉林路东段路北', '9115042168343988XA', '2018-01-11 10:19:03', null, null, '马丽中', '13042642666', '阿鲁科尔沁旗交通贸易有限责任公司', '', '', '025550', null, '', '陈永胜', '13042642666', null, null, '2018-01-11 10:19:03', null, '3', '14');
INSERT INTO `department` VALUES ('506', '阿鲁科尔沁旗巴彦花镇五一村', '911504216609735027', '2018-01-11 10:22:34', null, null, '王玉华', '15148195078', '阿鲁科尔沁旗巴彦花镇巴彦花农机加油站', '', '', '025550', null, 'cy-aaa1160@163.com', '王玉华', '15148195078', '0476-7400056', null, '2018-01-17 16:23:52', null, '3', '14');
INSERT INTO `department` VALUES ('507', '阿鲁科尔沁旗天山口镇大虎头嘎村', '911504215528278230', '2018-01-11 10:26:06', null, null, '陈晓鹏', '15847326686', '阿鲁科尔沁旗天山口镇白城子加油有限公司', '', '', '025550', null, '', '陈晓鹏', '15847326686', null, null, '2018-01-11 10:26:06', null, '3', '14');
INSERT INTO `department` VALUES ('508', '内蒙古赤峰市阿鲁科尔沁旗巴拉奇如德中诺尔嘎查', '911504215888262913', '2018-01-11 10:30:48', null, null, '巴雅尔', '13947686657', '阿鲁科尔沁旗查干诺尔加油有限公司', '', '', '025550', null, '', '巴雅尔', '13947686657', null, null, '2018-01-11 10:30:48', null, '3', '14');
INSERT INTO `department` VALUES ('509', '阿鲁科尔沁旗省际大通道与陶海公路交汇处', '91150421341412281Q', '2018-01-11 10:50:22', null, null, '李明', '15548386169', '中石化销售有限公司内蒙古赤峰石油分公司阿旗陶海加油站', '', '', '025550', null, '', '韩国辉', '15548386169', null, null, '2018-01-11 10:50:22', null, '3', '14');
INSERT INTO `department` VALUES ('510', '阿鲁科尔沁旗天山镇新城区天元大街北', '91150421MAON053U6G', '2018-01-11 11:01:11', null, null, '侯立新', '15147697387', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗天元大街加油站', '', '', '025550', null, '', '薛颖慧', '15147697387', null, null, '2018-01-11 11:01:11', null, '3', '14');
INSERT INTO `department` VALUES ('511', '阿鲁科尔沁旗天山镇西桥西', '91150421MAONO53K42', '2018-01-11 11:05:20', null, null, '侯立新', '13947692638', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗汉林路加油站', '', '', '025550', null, '', '宋立杰', '13947692638', null, null, '2018-01-11 11:05:20', null, '3', '14');
INSERT INTO `department` VALUES ('512', '阿鲁科尔沁旗天山镇新城区303与省际道叉口', '91150421MA0N053R1Y', '2018-01-11 14:03:28', null, null, '刘健', '15048686155', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗经营部天通加油站', '', '', '025550', null, '', '张枫', '15048686155', null, null, '2018-01-11 14:03:28', null, '3', '14');
INSERT INTO `department` VALUES ('513', '阿鲁科尔沁旗坤都镇', '91150421MA0N054J2G', '2018-01-11 14:07:23', null, null, '刘健', '13947362522', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗坤都加油站', '', '', '025550', null, '', '王立锋', '13947362522', null, null, '2018-01-11 14:07:23', null, '3', '14');
INSERT INTO `department` VALUES ('514', '阿鲁科尔沁旗先锋乡', '91150421MA0N055464', '2018-01-11 14:12:02', null, null, '刘健', '13848899697', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗先锋加油站', '', '', '025550', null, '', '范志磊', '13848899697', null, null, '2018-01-11 14:12:02', null, '3', '14');
INSERT INTO `department` VALUES ('515', '阿鲁科尔沁旗绍根镇', '91150421MA0N0552X9', '2018-01-11 14:17:01', null, null, '刘健', '15147697387', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗绍根加油站', '', '', '025550', null, '', '薛颖慧', '15147697387', null, null, '2018-01-11 14:17:01', null, '3', '14');
INSERT INTO `department` VALUES ('516', '阿鲁科尔沁旗天山镇', '91150421MA0N053B0D', '2018-01-11 14:20:32', null, null, '刘健', '13947674588', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗桥南加油站', '', '', '025550', null, '', '杨大勇', '13947674588', null, null, '2018-01-11 14:20:32', null, '3', '14');
INSERT INTO `department` VALUES ('517', '阿鲁科尔沁旗天山镇', '91150421MA0N053N9L', '2018-01-11 14:24:31', null, null, '刘健', '18747613007', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗运输公司加油站', '', '', '025550', null, '', '李福生', '18747613007', null, null, '2018-01-11 14:24:31', null, '3', '14');
INSERT INTO `department` VALUES ('518', '阿鲁科尔沁旗天山镇', '91150421MA0N05351A', '2018-01-11 14:29:15', null, null, '刘健', '15004873377', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗西郊加油站', '', '', '025550', null, '', '苗永杰', '15004873377', null, null, '2018-01-11 14:29:15', null, '3', '14');
INSERT INTO `department` VALUES ('519', '阿鲁科尔沁旗绍根镇阿拉格沙嘎查303国道790公里处', '91150421MA0N054Y56', '2018-01-11 14:42:09', null, null, '刘健', '13789662085', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗道德加油站', '', '', '025550', null, '', '刘颖峰', '13789662085', null, null, '2018-01-11 14:42:09', null, '3', '14');
INSERT INTO `department` VALUES ('520', '阿鲁科尔沁旗天山镇敖木伦街', '91150421MA0N052Q7T', '2018-01-11 14:53:32', null, null, '刘健', '13948567016', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗天山加油站', '', '', '025550', null, '', '白云龙', '13948567016', null, null, '2018-01-11 14:53:32', null, '3', '14');
INSERT INTO `department` VALUES ('521', '阿鲁科尔沁旗双胜镇', '91150421MA0N05431X', '2018-01-11 14:57:49', null, null, '侯立新', '15804769861', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗双胜加油站', '', '', '025550', null, 'aqmyj@163.com', '苗永杰', '15004873377', '0476-7360877', null, '2018-01-12 09:24:28', null, '3', '14');
INSERT INTO `department` VALUES ('522', '阿鲁科尔沁旗天山镇', '91150421MA0N053F3T', '2018-01-11 14:59:54', null, null, '刘健', '13947692638', '中国石油天然气股份有限公司内蒙古赤峰阿鲁科尔沁旗城北加油站', '', '', '025550', null, '', '宋立杰', '13947692638', null, null, '2018-01-11 14:59:54', null, '3', '14');
INSERT INTO `department` VALUES ('523', '阿鲁科尔沁旗天山镇西桥西路北', '91150421736145253B', '2018-01-11 15:03:33', null, null, '李树洪', '13684762875', '中石化集团内蒙古石油销售有限公司赤峰分公司天山加油站', '', '', '025550', null, '', '李吉星', '13684762875', null, null, '2018-01-11 15:03:33', null, '3', '14');
INSERT INTO `department` VALUES ('524', '阿鲁科尔沁旗天山镇北岗台', '91150421MA0N7G4640', '2018-01-11 15:07:55', null, null, '杨学广', '18248008700', '阿鲁科尔沁旗鑫阳商贸有限公司天山加油站', '', '', '025550', null, '', '杨学广', '18248008700', null, null, '2018-01-11 15:07:55', null, '3', '14');
INSERT INTO `department` VALUES ('525', '阿鲁科尔沁旗天山镇岗台村', '91150421353054397K', '2018-01-11 15:15:14', null, null, '杨广学', '18248008700', '阿鲁科尔沁旗鑫阳商贸有限公司', '', '', '025550', null, '', '杨广学', '18248008700', null, null, '2018-01-11 15:15:14', null, '3', '14');
INSERT INTO `department` VALUES ('526', '内蒙古赤峰市阿鲁科尔沁旗绍根镇', '912111007164843620', '2018-01-11 15:35:21', null, null, '刘洋', '18604769130', '中国石油辽河油田茨榆采油厂科尔沁开发公司', '', '', '025550', null, '', '罗信德', '18604769130', null, null, '2018-01-11 15:35:21', null, '3', '14');
INSERT INTO `department` VALUES ('527', '翁牛特旗乌丹镇清泉路南段行政办公楼4号', '11150426353078380R', '2018-01-11 15:49:46', null, null, '米常学', '13684767955', '翁牛特旗产品质量计量检测所', '', '', '024500', null, 'scjdglj1406@qq.com', 'wqzjs1', '13684767955', '6323122', null, '2018-01-11 16:18:55', null, '4', '7');
INSERT INTO `department` VALUES ('528', '', '', '2018-01-11 17:20:36', null, null, '', '', '正星科技股份有限公司', '', '', '', null, '', '', '', null, null, '2018-01-11 17:20:36', null, '1', null);
INSERT INTO `department` VALUES ('529', '', '', '2018-01-12 10:31:27', null, null, '', '', '托肯恒山科技有限公司', '', '', '', null, '', '', '', null, null, '2018-01-12 10:31:27', null, '1', null);
INSERT INTO `department` VALUES ('530', '', '', '2018-01-12 11:07:49', null, null, '', '', '托肯恒山有限公司', '', '', '', null, '', '', '', null, null, '2018-01-12 11:07:49', null, '1', null);
INSERT INTO `department` VALUES ('531', '', '', '2018-01-13 11:41:46', null, null, '', '', '河南郑州正星科技股份有限公司', '', '', '', null, '', '', '', null, null, '2018-01-13 11:41:46', null, '1', null);
INSERT INTO `department` VALUES ('532', '赤峰市林西县林西镇北门外工业园区西侧', '92150424MAON9WT25A', '2018-01-15 08:47:42', null, null, '刘景明', '13789563355', '林西县宾友干洗店', '', '', '025250', null, '', '刘景明', '13789563355', null, null, '2018-01-15 08:47:42', null, '3', '13');
INSERT INTO `department` VALUES ('533', '阿鲁科尔沁旗天山镇东桥东', '91150421L32426949Y', '2018-01-15 10:02:11', null, null, '王丽芝', '13034767409', '阿鲁科尔沁旗永存宝枝米业有限责任公司', '', '', '025550', null, '', '王丽芝', '13034767409', null, null, '2018-01-15 10:02:11', null, '3', '14');
INSERT INTO `department` VALUES ('534', '阿鲁科尔沁旗赛罕塔拉苏木查干塔拉嘎查', '911504217830316973', '2018-01-15 10:46:16', null, null, '王立军', '13789669999', '阿鲁科尔沁旗天山口农机有限公司扎嘎斯台农机加油站', '', '', '025550', null, '', '王立军', '13789669999', null, null, '2018-01-15 10:46:16', null, '3', '14');
INSERT INTO `department` VALUES ('535', '阿鲁科尔沁旗新民乡新丰村', '911504217761122964', '2018-01-15 10:48:37', null, null, '王立军', '13789669999', '阿鲁科尔沁旗天山口农机有限责任公司新丰加油站', '', '', '025550', null, '', '王立军', '13789669999', null, null, '2018-01-15 10:48:37', null, '3', '14');
INSERT INTO `department` VALUES ('536', '阿鲁科尔沁旗天山口镇政府所在地', '911504217644758158', '2018-01-15 11:06:27', null, null, '王立军', '13789669999', '阿鲁科尔沁旗天山口农机有限责任公司', '', '', '025550', null, '', '王立军', '13789669999', null, null, '2018-01-15 11:06:27', null, '3', '14');
INSERT INTO `department` VALUES ('537', '翁旗乌丹镇玉龙食品工业集中区内', '911504260755698832', '2018-01-16 09:42:21', null, null, '闫洪志', '15049988999', '赤峰凌志食品有限公司', '', '', '024500', null, '', '冯雪', '15734765769', null, null, '2018-01-16 09:42:21', null, '3', '7');
INSERT INTO `department` VALUES ('538', '', '', '2018-01-16 11:01:34', null, null, '', '', '北京精密燕宁仪表厂', '', '', '', null, '', '', '', null, null, '2018-01-16 11:01:34', null, '1', null);
INSERT INTO `department` VALUES ('539', '', '', '2018-01-16 14:37:59', null, null, '', '', '永康市锣金五金制品厂', '', '', '', null, '', '', '', null, null, '2018-01-16 14:37:59', null, '1', null);
INSERT INTO `department` VALUES ('540', '', '', '2018-01-17 10:06:14', null, null, '', '', '承德市热工仪表厂', '', '', '', null, '', '', '', null, null, '2018-01-17 10:06:14', null, '1', null);
INSERT INTO `department` VALUES ('541', '', '', '2018-01-17 10:25:05', null, null, '', '', '沈阳压力表厂', '', '', '', null, '', '', '', null, null, '2018-01-17 10:25:05', null, '1', null);
INSERT INTO `department` VALUES ('542', '', '', '2018-01-17 11:08:04', null, null, '', '', '上海大华仪表厂', '', '', '', null, '', '', '', null, null, '2018-01-17 11:08:04', null, '1', null);
INSERT INTO `department` VALUES ('543', '', '', '2018-01-17 14:21:35', null, null, '', '', '德国威卡', '', '', '', null, '', '', '', null, null, '2018-01-17 14:21:35', null, '1', null);
INSERT INTO `department` VALUES ('544', '翁旗海拉苏镇医院东侧', '91150426588813108L', '2018-01-17 16:02:11', null, null, '周亚明', '13847615036', '翁牛特旗香泉农副产品购销有限公司', '', '', '024500', null, '', '周洋', '18247604321', null, null, '2018-01-17 16:02:11', null, '3', '7');
INSERT INTO `department` VALUES ('545', '', '', '2018-01-17 16:12:00', null, null, '', '', '北京长吉加油设备有限公司', '', '', '', null, '', '', '', null, null, '2018-01-17 16:12:00', null, '1', null);
INSERT INTO `department` VALUES ('546', '', '', '2018-01-17 16:33:52', null, null, '', '', '唐山骏腾衡器有限公司', '', '', '', null, '', '', '', null, null, '2018-01-17 16:33:52', null, '1', null);
INSERT INTO `department` VALUES ('547', '', '', '2018-01-17 16:49:10', null, null, '', '', '上海上平仪器公司', '', '', '', null, '', '', '', null, null, '2018-01-17 16:49:10', null, '1', null);
INSERT INTO `department` VALUES ('548', '', '', '2018-01-18 09:14:38', null, null, '', '', '上海新大华压力表厂', '', '', '', null, '', '', '', null, null, '2018-01-18 09:14:38', null, '1', null);
INSERT INTO `department` VALUES ('549', '', '', '2018-01-18 15:50:04', null, null, '', '', '上海荣华仪表厂', '', '', '', null, '', '', '', null, null, '2018-01-18 15:50:04', null, '1', null);
INSERT INTO `department` VALUES ('550', '', '', '2018-01-18 16:31:33', null, null, '', '', '艾默生过程控制流量技术有限公司', '', '', '', null, '', '', '', null, null, '2018-01-18 16:31:33', null, '1', null);
INSERT INTO `department` VALUES ('551', '林西镇铜都大街', '111504240115787051', '2018-01-19 09:28:39', null, null, '王辉', '18847667779', '林西县公安局', '', '', '025250', null, '', '王辉', '18847667779', null, null, '2018-01-19 09:28:39', null, '3', '13');
INSERT INTO `department` VALUES ('552', '1', '1', '2018-01-19 10:52:25', null, null, '1', '13669696969', '1', '', '', '1', null, '', '1', '13669696969', null, null, '2018-01-19 10:52:25', null, '3', '5');
INSERT INTO `department` VALUES ('553', '', '', '2018-01-21 14:43:46', null, null, '', '', '淮安鹰衡称重设备制造有限公司', '', '', '', null, '', '', '', null, null, '2018-01-21 14:43:46', null, '1', null);
INSERT INTO `department` VALUES ('554', '阿鲁科尔沁旗巴拉奇如德苏木诺尔嘎查', '91150421552829538X', '2018-01-22 09:15:56', null, null, '史永丽', '13451339292', '阿鲁科尔沁旗巴彦诺尔加油有限公司', '', '', '025550', null, '', '史永丽', '13451339292', null, null, '2018-01-22 09:15:56', null, '3', '14');
INSERT INTO `department` VALUES ('555', '', '', '2018-01-22 15:46:20', null, null, '', '', '上海大普', '', '', '', null, '', '', '', null, null, '2018-01-22 15:46:20', null, '1', null);
INSERT INTO `department` VALUES ('556', '', '', '2018-01-22 16:07:43', null, null, '', '', '上海精科', '', '', '', null, '', '', '', null, null, '2018-01-22 16:07:43', null, '1', null);
INSERT INTO `department` VALUES ('557', '', '', '2018-01-22 16:14:08', null, null, '', '', '沪南电炉烘箱厂', '', '', '', null, '', '', '', null, null, '2018-01-22 16:14:08', null, '1', null);
INSERT INTO `department` VALUES ('558', '林西镇西门外工程公司东', '911504247794547422', '2018-01-23 09:54:45', null, null, '席玉民', '18904760797', '林西县健源气体有限公司', '', '', '025250', null, '', '席玉民', '18904760797', null, null, '2018-01-23 09:54:45', null, '3', '13');
INSERT INTO `department` VALUES ('559', '内蒙古赤峰市红山绿色食品产业园区', '91150402747904139C', '2018-01-23 10:29:10', null, null, '赵晓军', '13684770899', '赤峰禾士营养食品科技有限公司', '', '', '024000', null, '', '宋景云', '13684770899', null, null, '2018-01-23 10:29:10', null, '3', '15');
INSERT INTO `department` VALUES ('560', '', '', '2018-01-23 14:03:59', null, null, '', '', '沈阳测压仪表厂', '', '', '', null, '', '', '', null, null, '2018-01-23 14:03:59', null, '1', null);
INSERT INTO `department` VALUES ('561', '', '', '2018-01-23 15:03:19', null, null, '', '', '赤峰市长兴', '', '', '', null, '', '', '', null, null, '2018-01-23 15:03:19', null, '1', null);
INSERT INTO `department` VALUES ('562', '', '', '2018-01-23 16:19:41', null, null, '', '', '青岛金渊焊割设备有限公司', '', '', '', null, '', '', '', null, null, '2018-01-23 16:19:41', null, '1', null);
INSERT INTO `department` VALUES ('563', '', '', '2018-01-23 16:29:14', null, null, '', '', '丹东市观通仪表制造有限公司', '', '', '', null, '', '', '', null, null, '2018-01-23 16:29:14', null, '1', null);
INSERT INTO `department` VALUES ('564', '', '', '2018-01-23 16:30:18', null, null, '', '', '沈阳市天成仪表有限公司', '', '', '', null, '', '', '', null, null, '2018-01-23 16:30:18', null, '1', null);
INSERT INTO `department` VALUES ('565', '', '', '2018-01-23 16:33:44', null, null, '', '', '沈阳奥德普仪表厂', '', '', '', null, '', '', '', null, null, '2018-01-23 16:33:44', null, '1', null);
INSERT INTO `department` VALUES ('566', '', '', '2018-01-23 16:51:06', null, null, '', '', '西安自动化仪表一厂', '', '', '', null, '', '', '', null, null, '2018-01-23 16:51:06', null, '1', null);
INSERT INTO `department` VALUES ('567', '', '', '2018-01-23 17:00:14', null, null, '', '', '上海压力表厂', '', '', '', null, '', '', '', null, null, '2018-01-23 17:00:14', null, '1', null);
INSERT INTO `department` VALUES ('568', '内蒙古赤峰市红山区经济开发区13号', '91150402329027997J', '2018-01-23 17:14:19', null, null, '韩风雨', '15048370418', '内蒙古博奥蒙中药工程研究院有限公司', '', '', '024000', null, '', '马海峰', '15048370418', null, null, '2018-01-23 17:14:19', null, '3', '15');
INSERT INTO `department` VALUES ('569', '', '', '2018-01-23 17:33:09', null, null, '', '', '中国雷尔达仪表有限公司', '', '', '', null, '', '', '', null, null, '2018-01-23 17:33:09', null, '1', null);
INSERT INTO `department` VALUES ('570', '', '', '2018-01-23 17:38:13', null, null, '', '', '杭州鹤山仪表有限公司', '', '', '', null, '', '', '', null, null, '2018-01-23 17:38:13', null, '1', null);
INSERT INTO `department` VALUES ('571', '', '', '2018-01-24 08:20:15', null, null, '', '', '蕾尔达仪器有限公司', '', '', '', null, '', '', '', null, null, '2018-01-24 08:20:15', null, '1', null);
INSERT INTO `department` VALUES ('572', '', '', '2018-01-24 08:24:02', null, null, '', '', '中国红旗仪器有限公司', '', '', '', null, '', '', '', null, null, '2018-01-24 08:24:02', null, '1', null);
INSERT INTO `department` VALUES ('573', '', '', '2018-01-24 08:37:36', null, null, '', '', '安徽天康（集团）股份', '', '', '', null, '', '', '', null, null, '2018-01-24 08:37:36', null, '1', null);
INSERT INTO `department` VALUES ('574', '', '', '2018-01-24 08:43:51', null, null, '', '', '天水泰益特种仪器有限公司', '', '', '', null, '', '', '', null, null, '2018-01-24 08:43:51', null, '1', null);
INSERT INTO `department` VALUES ('575', '阿鲁科尔沁旗巴彦包特政府所在地', '91150421067524634X', '2018-01-24 09:48:28', null, null, '尚国华', '15847626035', '阿鲁科尔沁旗巴彦包特农机服务站', '', '', '02550', null, '', '尚国华', '15847626035', null, null, '2018-01-24 09:48:28', null, '3', '14');

-- ----------------------------
-- Table structure for `department_post_user`
-- ----------------------------
DROP TABLE IF EXISTS `department_post_user`;
CREATE TABLE `department_post_user` (
  `department_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`department_id`,`post_id`,`user_id`),
  KEY `FKqeh9nov0skkwoo8o98xam5iks` (`post_id`),
  KEY `FKmb29c9xnvfqyhsj2rpaknd0w0` (`user_id`),
  CONSTRAINT `FK81itxktfs5miocv1muqlc84wr` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKmb29c9xnvfqyhsj2rpaknd0w0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqeh9nov0skkwoo8o98xam5iks` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of department_post_user
-- ----------------------------

-- ----------------------------
-- Table structure for `department_type`
-- ----------------------------
DROP TABLE IF EXISTS `department_type`;
CREATE TABLE `department_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2a8jp2vfagsea9geg7q8fc8ed` (`name`),
  UNIQUE KEY `UK_rcryvaaxfo02bxgefmr3yum62` (`pinyin`),
  KEY `FK3957tvyn7tgpsge5cenyncggp` (`create_user_id`),
  CONSTRAINT `FK3957tvyn7tgpsge5cenyncggp` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of department_type
-- ----------------------------
INSERT INTO `department_type` VALUES ('1', '0', '生产企业', 'shengchanqiye', '0', null);
INSERT INTO `department_type` VALUES ('2', '1513298774985', '管理部门', 'guanlibumen', '1513298774985', null);
INSERT INTO `department_type` VALUES ('3', '0', '器具用户', 'qijuyonghu', '0', null);
INSERT INTO `department_type` VALUES ('4', '0', '技术机构', 'jishujigou', '0', null);

-- ----------------------------
-- Table structure for `department_type_post`
-- ----------------------------
DROP TABLE IF EXISTS `department_type_post`;
CREATE TABLE `department_type_post` (
  `department_type_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`post_id`,`department_type_id`),
  KEY `FK7qvo2l2ysarb83kxygrm151y8` (`department_type_id`),
  CONSTRAINT `FK7qvo2l2ysarb83kxygrm151y8` FOREIGN KEY (`department_type_id`) REFERENCES `department_type` (`id`),
  CONSTRAINT `FKjvir6ney4mt9ldgvmgfgokj5r` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of department_type_post
-- ----------------------------

-- ----------------------------
-- Table structure for `device_instrument`
-- ----------------------------
DROP TABLE IF EXISTS `device_instrument`;
CREATE TABLE `device_instrument` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accuracy_id` bigint(20) DEFAULT NULL,
  `device_set_id` bigint(20) DEFAULT NULL,
  `instrument_type_id` bigint(20) DEFAULT NULL,
  `max_measure_scale_id` bigint(20) DEFAULT NULL,
  `min_measure_scale_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlfr1igollfp8ao9mhp50nunmf` (`accuracy_id`),
  KEY `FKamtx3yep1rhk4soc0p03rbf5h` (`device_set_id`),
  KEY `FKdk08t6o0ieeuki7mltqgf9dhx` (`instrument_type_id`),
  KEY `FK2uvolp83yiremwiu1pqhthiad` (`max_measure_scale_id`),
  KEY `FKofwgcxjlhp4883yl1s4rehi2r` (`min_measure_scale_id`),
  CONSTRAINT `FK2uvolp83yiremwiu1pqhthiad` FOREIGN KEY (`max_measure_scale_id`) REFERENCES `measure_scale` (`id`),
  CONSTRAINT `FKamtx3yep1rhk4soc0p03rbf5h` FOREIGN KEY (`device_set_id`) REFERENCES `device_set` (`id`),
  CONSTRAINT `FKdk08t6o0ieeuki7mltqgf9dhx` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`),
  CONSTRAINT `FKlfr1igollfp8ao9mhp50nunmf` FOREIGN KEY (`accuracy_id`) REFERENCES `accuracy` (`id`),
  CONSTRAINT `FKofwgcxjlhp4883yl1s4rehi2r` FOREIGN KEY (`min_measure_scale_id`) REFERENCES `measure_scale` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of device_instrument
-- ----------------------------
INSERT INTO `device_instrument` VALUES ('2', '46', '2', '16', '148', '142');
INSERT INTO `device_instrument` VALUES ('3', '7', '2', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('5', '16', '2', '3', '51', '14');
INSERT INTO `device_instrument` VALUES ('6', '22', '3', '5', '76', '172');
INSERT INTO `device_instrument` VALUES ('7', '53', '3', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('8', '55', '3', '19', '756', '181');
INSERT INTO `device_instrument` VALUES ('9', '56', '3', '20', '757', '210');
INSERT INTO `device_instrument` VALUES ('10', '61', '4', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('11', '17', '5', '4', '59', '54');
INSERT INTO `device_instrument` VALUES ('12', '105', '5', '35', '401', '395');
INSERT INTO `device_instrument` VALUES ('13', '113', '6', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('14', '112', '6', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('15', '46', '7', '16', '148', '142');
INSERT INTO `device_instrument` VALUES ('16', '45', '8', '16', '153', '138');
INSERT INTO `device_instrument` VALUES ('17', '7', '8', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('18', '6', '8', '2', '690', '2');
INSERT INTO `device_instrument` VALUES ('19', '16', '8', '3', '24', '14');
INSERT INTO `device_instrument` VALUES ('20', '55', '8', '19', '189', '181');
INSERT INTO `device_instrument` VALUES ('21', '56', '8', '20', '206', '210');
INSERT INTO `device_instrument` VALUES ('22', '61', '9', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('23', '17', '10', '4', '59', '55');
INSERT INTO `device_instrument` VALUES ('24', '105', '10', '35', '409', '405');
INSERT INTO `device_instrument` VALUES ('25', '110', '10', '37', '442', '448');
INSERT INTO `device_instrument` VALUES ('26', '112', '11', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('27', '113', '11', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('28', '46', '12', '16', '148', '137');
INSERT INTO `device_instrument` VALUES ('29', '7', '12', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('30', '16', '12', '3', '24', '14');
INSERT INTO `device_instrument` VALUES ('31', '45', '13', '16', '153', '137');
INSERT INTO `device_instrument` VALUES ('32', '6', '13', '2', '691', '2');
INSERT INTO `device_instrument` VALUES ('33', '14', '13', '3', '40', '14');
INSERT INTO `device_instrument` VALUES ('34', '22', '14', '5', '75', '172');
INSERT INTO `device_instrument` VALUES ('35', '53', '14', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('36', '55', '14', '19', '187', '181');
INSERT INTO `device_instrument` VALUES ('37', '56', '14', '20', '202', '210');
INSERT INTO `device_instrument` VALUES ('38', '61', '15', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('39', '17', '16', '4', '59', '54');
INSERT INTO `device_instrument` VALUES ('41', '105', '16', '35', '401', '395');
INSERT INTO `device_instrument` VALUES ('42', '112', '17', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('43', '113', '17', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('44', '46', '18', '16', '148', '137');
INSERT INTO `device_instrument` VALUES ('45', '45', '19', '16', '153', '138');
INSERT INTO `device_instrument` VALUES ('46', '7', '19', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('47', '16', '19', '3', '51', '14');
INSERT INTO `device_instrument` VALUES ('50', '6', '20', '2', '691', '12');
INSERT INTO `device_instrument` VALUES ('51', '14', '20', '3', '40', '41');
INSERT INTO `device_instrument` VALUES ('52', '55', '20', '19', '756', '181');
INSERT INTO `device_instrument` VALUES ('54', '56', '20', '20', '757', '210');
INSERT INTO `device_instrument` VALUES ('55', '61', '21', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('56', '17', '22', '4', '57', '54');
INSERT INTO `device_instrument` VALUES ('57', '105', '22', '35', '413', '395');
INSERT INTO `device_instrument` VALUES ('58', '112', '23', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('59', '113', '23', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('60', '46', '24', '16', '155', '137');
INSERT INTO `device_instrument` VALUES ('61', '7', '24', '2', '11', '2');
INSERT INTO `device_instrument` VALUES ('62', '16', '24', '3', '24', '14');
INSERT INTO `device_instrument` VALUES ('63', '45', '25', '16', '153', '138');
INSERT INTO `device_instrument` VALUES ('64', '6', '25', '2', '691', '2');
INSERT INTO `device_instrument` VALUES ('65', '14', '25', '3', '40', '14');
INSERT INTO `device_instrument` VALUES ('66', '55', '26', '19', '759', '181');
INSERT INTO `device_instrument` VALUES ('67', '56', '26', '20', '758', '210');
INSERT INTO `device_instrument` VALUES ('68', '61', '27', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('69', '17', '28', '4', '59', '54');
INSERT INTO `device_instrument` VALUES ('70', '105', '28', '35', '401', '395');
INSERT INTO `device_instrument` VALUES ('71', '112', '29', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('72', '113', '29', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('73', '46', '30', '16', '148', '163');
INSERT INTO `device_instrument` VALUES ('74', '7', '30', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('75', '16', '30', '3', '52', '14');
INSERT INTO `device_instrument` VALUES ('76', '45', '31', '16', '760', '138');
INSERT INTO `device_instrument` VALUES ('77', '15', '31', '3', '46', '14');
INSERT INTO `device_instrument` VALUES ('78', '22', '32', '5', '76', '172');
INSERT INTO `device_instrument` VALUES ('79', '53', '32', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('80', '55', '32', '19', '184', '181');
INSERT INTO `device_instrument` VALUES ('81', '56', '32', '20', '198', '210');
INSERT INTO `device_instrument` VALUES ('82', '61', '33', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('83', '17', '34', '4', '59', '54');
INSERT INTO `device_instrument` VALUES ('84', '105', '34', '35', '401', '395');
INSERT INTO `device_instrument` VALUES ('85', '112', '35', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('86', '113', '35', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('87', '114', '36', '40', '461', '461');
INSERT INTO `device_instrument` VALUES ('88', '46', '37', '16', '148', '163');
INSERT INTO `device_instrument` VALUES ('89', '7', '37', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('90', '16', '37', '3', '51', '14');
INSERT INTO `device_instrument` VALUES ('91', '45', '38', '16', '760', '138');
INSERT INTO `device_instrument` VALUES ('92', '22', '39', '5', '76', '172');
INSERT INTO `device_instrument` VALUES ('93', '53', '39', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('94', '55', '39', '19', '182', '181');
INSERT INTO `device_instrument` VALUES ('95', '56', '39', '20', '212', '210');
INSERT INTO `device_instrument` VALUES ('96', '61', '40', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('97', '17', '41', '4', '59', '54');
INSERT INTO `device_instrument` VALUES ('98', '105', '41', '35', '401', '395');
INSERT INTO `device_instrument` VALUES ('99', '112', '42', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('102', '113', '42', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('103', '24', '44', '6', '742', '78');
INSERT INTO `device_instrument` VALUES ('104', '25', '44', '7', '741', '102');
INSERT INTO `device_instrument` VALUES ('105', '28', '45', '9', '107', '116');
INSERT INTO `device_instrument` VALUES ('106', '39', '45', '12', '762', '125');
INSERT INTO `device_instrument` VALUES ('107', '40', '45', '13', '127', '128');
INSERT INTO `device_instrument` VALUES ('108', '41', '45', '14', '763', '132');
INSERT INTO `device_instrument` VALUES ('109', '42', '45', '15', '133', '136');
INSERT INTO `device_instrument` VALUES ('110', '43', '46', '16', '162', '137');
INSERT INTO `device_instrument` VALUES ('111', '46', '47', '16', '153', '138');
INSERT INTO `device_instrument` VALUES ('112', '49', '48', '16', '143', '154');
INSERT INTO `device_instrument` VALUES ('113', '12', '46', '2', '11', '2');
INSERT INTO `device_instrument` VALUES ('114', '16', '46', '3', '24', '14');
INSERT INTO `device_instrument` VALUES ('115', '9', '47', '2', '691', '2');
INSERT INTO `device_instrument` VALUES ('116', '15', '47', '3', '40', '14');
INSERT INTO `device_instrument` VALUES ('121', '22', '47', '5', '75', '172');
INSERT INTO `device_instrument` VALUES ('122', '53', '47', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('123', '55', '48', '19', '190', '181');
INSERT INTO `device_instrument` VALUES ('124', '56', '48', '20', '213', '210');
INSERT INTO `device_instrument` VALUES ('125', '57', '48', '21', '216', '223');
INSERT INTO `device_instrument` VALUES ('126', '220', '47', '101', '709', '695');
INSERT INTO `device_instrument` VALUES ('127', '221', '47', '102', '729', '714');
INSERT INTO `device_instrument` VALUES ('128', '61', '49', '25', '257', '258');
INSERT INTO `device_instrument` VALUES ('129', '75', '50', '30', '303', '306');
INSERT INTO `device_instrument` VALUES ('130', '76', '51', '31', '320', '312');
INSERT INTO `device_instrument` VALUES ('131', '81', '52', '32', '748', '749');
INSERT INTO `device_instrument` VALUES ('132', '224', '52', '103', '732', '733');
INSERT INTO `device_instrument` VALUES ('135', '110', '53', '37', '442', '448');
INSERT INTO `device_instrument` VALUES ('136', '249', '53', '37', '442', '456');
INSERT INTO `device_instrument` VALUES ('137', '17', '53', '4', '59', '55');
INSERT INTO `device_instrument` VALUES ('138', '258', '53', '4', '59', '54');
INSERT INTO `device_instrument` VALUES ('139', '252', '53', '35', '401', '395');
INSERT INTO `device_instrument` VALUES ('140', '105', '53', '35', '401', '405');
INSERT INTO `device_instrument` VALUES ('141', '112', '54', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('142', '113', '54', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('143', '114', '55', '40', '461', '461');
INSERT INTO `device_instrument` VALUES ('144', '115', '56', '41', '465', '463');
INSERT INTO `device_instrument` VALUES ('145', '119', '57', '42', '470', '764');
INSERT INTO `device_instrument` VALUES ('146', '122', '57', '43', '488', '750');
INSERT INTO `device_instrument` VALUES ('147', '243', '58', '44', '752', '765');
INSERT INTO `device_instrument` VALUES ('148', '245', '58', '45', '500', '500');
INSERT INTO `device_instrument` VALUES ('149', '137', '58', '46', '505', '505');
INSERT INTO `device_instrument` VALUES ('150', '143', '59', '48', '516', '520');
INSERT INTO `device_instrument` VALUES ('151', '146', '60', '49', '522', '522');
INSERT INTO `device_instrument` VALUES ('152', '147', '60', '50', '523', '523');
INSERT INTO `device_instrument` VALUES ('153', '148', '61', '51', '524', '524');
INSERT INTO `device_instrument` VALUES ('154', '159', '62', '62', '535', '535');
INSERT INTO `device_instrument` VALUES ('155', '235', '63', '107', '738', '739');
INSERT INTO `device_instrument` VALUES ('156', '236', '63', '108', '744', '745');
INSERT INTO `device_instrument` VALUES ('157', '170', '63', '69', '564', '568');
INSERT INTO `device_instrument` VALUES ('158', '174', '64', '70', '571', '570');
INSERT INTO `device_instrument` VALUES ('159', '186', '65', '76', '587', '583');
INSERT INTO `device_instrument` VALUES ('160', '190', '65', '77', '589', '588');
INSERT INTO `device_instrument` VALUES ('161', '238', '65', '109', '746', '747');
INSERT INTO `device_instrument` VALUES ('162', '198', '66', '83', '605', '609');
INSERT INTO `device_instrument` VALUES ('163', '200', '66', '85', '617', '618');
INSERT INTO `device_instrument` VALUES ('164', '213', '67', '96', '638', '637');
INSERT INTO `device_instrument` VALUES ('165', '214', '67', '97', '639', '640');
INSERT INTO `device_instrument` VALUES ('166', '216', '68', '99', '645', '643');
INSERT INTO `device_instrument` VALUES ('167', '46', '70', '16', '148', '146');
INSERT INTO `device_instrument` VALUES ('168', '7', '70', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('170', '14', '71', '3', '40', '14');
INSERT INTO `device_instrument` VALUES ('171', '22', '71', '5', '76', '172');
INSERT INTO `device_instrument` VALUES ('172', '53', '71', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('173', '55', '71', '19', '183', '181');
INSERT INTO `device_instrument` VALUES ('174', '56', '71', '20', '200', '210');
INSERT INTO `device_instrument` VALUES ('175', '61', '72', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('176', '105', '73', '35', '403', '395');
INSERT INTO `device_instrument` VALUES ('177', '17', '73', '4', '68', '54');
INSERT INTO `device_instrument` VALUES ('178', '112', '74', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('179', '113', '74', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('180', '114', '75', '40', '461', '461');
INSERT INTO `device_instrument` VALUES ('181', '46', '76', '16', '148', '137');
INSERT INTO `device_instrument` VALUES ('182', '7', '76', '2', '5', '2');
INSERT INTO `device_instrument` VALUES ('183', '16', '76', '3', '51', '14');
INSERT INTO `device_instrument` VALUES ('184', '45', '77', '16', '153', '138');
INSERT INTO `device_instrument` VALUES ('185', '22', '77', '5', '76', '172');
INSERT INTO `device_instrument` VALUES ('186', '53', '77', '18', '678', '173');
INSERT INTO `device_instrument` VALUES ('187', '55', '77', '19', '759', '181');
INSERT INTO `device_instrument` VALUES ('188', '56', '77', '20', '758', '210');
INSERT INTO `device_instrument` VALUES ('189', '61', '78', '25', '260', '258');
INSERT INTO `device_instrument` VALUES ('190', '17', '79', '4', '57', '54');
INSERT INTO `device_instrument` VALUES ('191', '105', '79', '35', '413', '395');
INSERT INTO `device_instrument` VALUES ('192', '112', '80', '38', '457', '458');
INSERT INTO `device_instrument` VALUES ('193', '113', '80', '39', '460', '459');
INSERT INTO `device_instrument` VALUES ('194', '114', '81', '40', '461', '461');

-- ----------------------------
-- Table structure for `device_set`
-- ----------------------------
DROP TABLE IF EXISTS `device_set`;
CREATE TABLE `device_set` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alert_date` date DEFAULT NULL,
  `certificate_num` varchar(255) DEFAULT NULL,
  `check_date` date DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `issue_date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `validity_date` date DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt1ny4w8qrf3btebpxujjr2x79` (`create_user_id`),
  KEY `FK264t0ws2eu1hw6o345fcfyvol` (`department_id`),
  CONSTRAINT `FK264t0ws2eu1hw6o345fcfyvol` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKt1ny4w8qrf3btebpxujjr2x79` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of device_set
-- ----------------------------
INSERT INTO `device_set` VALUES ('2', '2018-07-09', '0036', '2017-06-06', '12113701', '2017-12-15 16:42:08', '2017-06-06', 'M1等级砝大码标准装置', null, '2018-01-12 10:01:47', '2018-07-09', null, '30');
INSERT INTO `device_set` VALUES ('3', '2018-07-09', '0037', '2017-06-06', '12113402   12113403', '2017-12-15 16:50:03', '2017-06-06', 'F1级克组、毫克组砝码标准装置', null, '2018-01-12 09:52:17', '2018-07-09', null, '30');
INSERT INTO `device_set` VALUES ('4', '2018-07-09', '0033', '2017-06-06', '12216100', '2017-12-15 17:00:34', '2017-06-06', '燃油加油机检定装置', null, '2018-01-12 09:58:13', '2018-07-09', null, '30');
INSERT INTO `device_set` VALUES ('5', '2018-07-09', '0035', '2017-06-06', '12415100', '2017-12-15 17:03:09', '2017-06-06', '精密压力表检定装置', null, '2018-01-12 09:56:51', '2018-07-09', null, '30');
INSERT INTO `device_set` VALUES ('6', '2018-07-09', '0034', '2017-06-06', '12416200', '2017-12-15 17:07:02', '2017-06-06', '血压计(表）检定装置', null, '2018-01-12 09:54:46', '2018-07-09', null, '30');
INSERT INTO `device_set` VALUES ('7', '2022-06-01', '070007', '2017-12-01', '020001', '2017-12-15 17:17:41', '2017-12-01', 'F1级砝码标准装置', null, '2017-12-15 17:21:20', '2022-12-01', null, '20');
INSERT INTO `device_set` VALUES ('8', '2022-06-01', '070008', '2017-12-01', '020002', '2017-12-15 17:20:47', '2017-12-01', 'F2级砝码标准装置', null, '2017-12-15 17:20:47', '2022-12-01', null, '20');
INSERT INTO `device_set` VALUES ('9', '2022-06-01', '070008', '2017-12-01', '020004', '2017-12-16 09:17:05', '2017-12-01', '燃油加油机检定装置', null, '2017-12-16 09:17:05', '2022-12-01', null, '20');
INSERT INTO `device_set` VALUES ('10', '2022-06-01', '070009', '2017-12-01', '020005', '2017-12-16 09:22:04', '2017-12-01', '压力表检定装置', null, '2017-12-16 09:22:04', '2022-12-01', null, '20');
INSERT INTO `device_set` VALUES ('11', '2022-06-01', '070010', '2017-12-01', '020006', '2017-12-16 09:29:59', '2017-12-01', '血压计检定装置', null, '2017-12-16 09:29:59', '2022-12-01', null, '20');
INSERT INTO `device_set` VALUES ('12', '2022-06-01', '070011', '2017-12-01', '020001', '2017-12-16 09:45:13', '2017-12-01', 'F1级砝码标准装置', null, '2017-12-16 09:45:13', '2022-12-01', null, '19');
INSERT INTO `device_set` VALUES ('13', '2022-06-01', '070012', '2017-12-01', '020002', '2017-12-16 09:53:17', '2017-12-01', 'F2级砝码标准装置', null, '2017-12-16 09:53:17', '2022-12-01', null, '19');
INSERT INTO `device_set` VALUES ('14', '2022-06-01', '070013', '2017-12-01', '020003', '2017-12-16 10:06:39', '2017-12-01', 'M1级砝码标准装置', null, '2017-12-16 10:06:39', '2022-12-01', null, '19');
INSERT INTO `device_set` VALUES ('15', '2022-06-01', '070014', '2017-12-01', '020004', '2017-12-16 10:10:44', '2017-12-01', '燃油加油机检定装置', null, '2017-12-16 10:10:44', '2022-12-01', null, '19');
INSERT INTO `device_set` VALUES ('16', '2022-06-01', '070015', '2017-12-01', '020005', '2017-12-16 10:12:27', '2017-12-01', '压力表检定装置', null, '2017-12-16 10:12:27', '2022-12-01', null, '19');
INSERT INTO `device_set` VALUES ('17', '2022-06-01', '070016', '2017-12-01', '020006', '2017-12-16 10:18:15', '2017-12-01', '血压计检定装置', null, '2017-12-16 10:18:15', '2022-12-01', null, '19');
INSERT INTO `device_set` VALUES ('18', '2022-06-01', '070017', '2017-12-01', '020001', '2017-12-16 10:22:26', '2017-12-01', 'F1级砝码标准装置', null, '2017-12-16 10:22:26', '2022-12-01', null, '18');
INSERT INTO `device_set` VALUES ('19', '2022-06-01', '070018', '2017-12-01', '020002', '2017-12-16 10:24:51', '2017-12-01', 'F2级砝码标准装置', null, '2017-12-16 10:24:51', '2022-12-01', null, '18');
INSERT INTO `device_set` VALUES ('20', '2022-06-01', '070019', '2017-12-01', '020003', '2017-12-16 10:28:58', '2017-12-01', 'M1级砝码标准装置', null, '2017-12-16 10:28:58', '2022-12-01', null, '18');
INSERT INTO `device_set` VALUES ('21', '2022-06-01', '070020', '2017-12-01', '020004', '2017-12-16 10:43:57', '2017-12-01', '燃油加油机检定装置', null, '2017-12-16 10:43:57', '2022-12-01', null, '18');
INSERT INTO `device_set` VALUES ('22', '2022-06-01', '070021', '2017-12-01', '020005', '2017-12-16 10:45:47', '2017-12-01', '压力表检定装置', null, '2017-12-16 10:45:47', '2022-12-01', null, '18');
INSERT INTO `device_set` VALUES ('23', '2022-06-01', '070022', '2017-12-01', '020006', '2017-12-16 10:48:42', '2017-12-01', '血压计检定装置', null, '2017-12-16 10:48:42', '2022-12-01', null, '18');
INSERT INTO `device_set` VALUES ('24', '2022-06-01', '070023', '2017-12-01', '020001', '2017-12-16 10:54:58', '2017-12-01', 'F1级砝码标准装置', null, '2017-12-16 10:54:58', '2022-12-01', null, '17');
INSERT INTO `device_set` VALUES ('25', '2022-06-01', '070024', '2017-12-01', '020002', '2017-12-16 10:57:57', '2017-12-01', 'F2级砝码标准装置', null, '2017-12-16 10:57:57', '2022-12-01', null, '17');
INSERT INTO `device_set` VALUES ('26', '2022-06-01', '070025', '2017-12-01', '020003', '2017-12-16 11:11:27', '2017-12-01', 'M1级砝码标准装置', null, '2017-12-16 11:11:27', '2022-12-01', null, '17');
INSERT INTO `device_set` VALUES ('27', '2022-06-01', '070026', '2017-12-01', '020004', '2017-12-16 11:17:51', '2017-12-01', '燃油加油机检定装置', null, '2017-12-16 11:17:51', '2022-12-01', null, '17');
INSERT INTO `device_set` VALUES ('28', '2022-06-01', '070026', '2017-12-01', '020005', '2017-12-16 11:19:13', '2017-12-01', '压力表检定装置', null, '2017-12-16 11:19:13', '2022-12-01', null, '17');
INSERT INTO `device_set` VALUES ('29', '2018-06-01', '2014009', '2014-07-10', '12416200', '2017-12-16 11:26:36', '2014-07-10', '血压计检定装置', null, '2018-01-02 14:30:59', '2018-07-09', null, '17');
INSERT INTO `device_set` VALUES ('30', '2022-06-01', '070029', '2017-12-01', '020001', '2017-12-16 11:34:03', '2017-12-01', 'F1级砝码标准装置', null, '2017-12-16 11:34:03', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('31', '2022-06-01', '070030', '2017-12-01', '020002', '2017-12-16 11:37:34', '2017-12-01', 'F2级砝码标准装置', null, '2017-12-16 11:37:34', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('32', '2022-06-01', '070031', '2017-12-01', '020003', '2017-12-16 11:48:01', '2017-12-01', 'M1级砝码标准装置', null, '2017-12-16 11:48:01', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('33', '2022-06-01', '070032', '2017-12-01', '020004', '2017-12-16 11:52:17', '2017-12-01', '燃油加油机检定装置', null, '2017-12-16 11:52:17', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('34', '2022-06-01', '070033', '2017-12-01', '020005', '2017-12-16 11:53:52', '2017-12-01', '压力表检定装置', null, '2017-12-16 11:53:52', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('35', '2022-06-01', '070034', '2017-12-01', '020006', '2017-12-16 11:56:05', '2017-12-01', '血压计检定装置', null, '2017-12-16 11:56:05', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('36', '2022-06-01', '070035', '2017-12-01', '020007', '2017-12-16 11:58:45', '2017-12-01', '出租车计价表检定装置', null, '2017-12-16 11:58:45', '2022-12-01', null, '16');
INSERT INTO `device_set` VALUES ('37', '2022-06-01', '070037', '2017-12-01', '020001', '2017-12-16 14:20:12', '2017-12-01', 'F1级砝码标准装置', null, '2017-12-16 14:20:12', '2022-12-01', null, '15');
INSERT INTO `device_set` VALUES ('38', '2022-06-01', '070038', '2017-12-01', '020002', '2017-12-16 14:24:41', '2017-12-01', 'F2级砝码标准装置', null, '2017-12-16 14:24:41', '2022-12-01', null, '15');
INSERT INTO `device_set` VALUES ('39', '2022-06-01', '070039', '2017-12-01', '020003', '2017-12-16 14:28:23', '2017-12-01', 'M1级砝码标准装置', null, '2017-12-16 14:28:23', '2022-12-01', null, '15');
INSERT INTO `device_set` VALUES ('40', '2022-06-01', '070040', '2017-12-01', '020004', '2017-12-16 14:59:09', '2017-12-01', '燃油加油机检定装置', null, '2017-12-16 14:59:09', '2022-12-01', null, '15');
INSERT INTO `device_set` VALUES ('41', '2022-06-01', '070041', '2017-12-01', '020005', '2017-12-16 15:03:23', '2017-12-01', '压力表检定装置', null, '2017-12-16 15:03:23', '2022-12-01', null, '15');
INSERT INTO `device_set` VALUES ('42', '2022-06-01', '070042', '2017-12-01', '020006', '2017-12-16 15:06:10', '2017-12-01', '血压计检定装置', null, '2017-12-16 15:06:10', '2022-12-01', null, '15');
INSERT INTO `device_set` VALUES ('44', '2022-06-01', '070046', '2017-12-01', '010001', '2017-12-16 18:48:37', '2017-12-01', '钢卷尺检定装置', null, '2017-12-16 18:48:37', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('45', '2022-06-01', '070048', '2017-12-01', '030001', '2017-12-17 17:35:19', '2017-12-01', '温度计检定装置', null, '2017-12-17 17:35:19', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('46', '2022-06-01', '070049', '2017-12-01', '020001', '2017-12-17 17:46:03', '2017-12-01', 'E2等级砝码标准装置', null, '2017-12-17 17:46:03', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('47', '2022-06-01', '070050', '2017-12-01', '020002', '2017-12-17 17:47:53', '2017-12-01', 'F1等级砝码标准装置', null, '2017-12-17 17:47:53', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('48', '2022-06-01', '070051', '2017-12-01', '020003', '2017-12-17 17:54:01', '2017-12-01', 'F2等级砝码标准装置', null, '2017-12-17 17:54:01', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('49', '2022-06-01', '070052', '2017-12-01', '02004', '2017-12-17 18:11:22', '2017-12-01', '燃油加油机检定装置', null, '2017-12-17 18:11:22', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('50', '2022-06-01', '070055', '2017-12-01', '020005', '2017-12-17 18:16:38', '2017-12-01', '煤气表检定装置', null, '2017-12-17 18:16:38', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('51', '2022-06-01', '070056', '2017-12-01', '020006', '2017-12-17 18:19:14', '2017-12-01', '水表检定装置', null, '2017-12-17 18:19:14', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('52', '2022-06-01', '070058', '2017-12-01', '020007', '2017-12-17 18:21:41', '2017-12-01', '流量计检定装置', null, '2017-12-17 18:21:41', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('53', '2022-06-01', '070059', '2017-12-01', '020008', '2017-12-17 18:26:05', '2017-12-01', '压力表检定装置', null, '2017-12-17 18:26:05', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('54', '2022-06-01', '070060', '2017-12-01', '020009', '2017-12-17 21:20:19', '2017-12-01', '血压计检定装置', null, '2017-12-17 21:20:19', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('55', '2022-06-01', '070061', '2017-12-01', '020010', '2017-12-17 21:22:58', '2017-12-01', '出租汽车计价表检定装置', null, '2017-12-17 21:22:58', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('56', '2022-06-01', '070062', '2017-12-01', '020011', '2017-12-17 21:25:39', '2017-12-01', '测速仪检定装置', null, '2017-12-17 21:25:39', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('57', '2022-06-01', '070063', '2017-12-01', '040001', '2017-12-17 21:28:29', '2017-12-01', '电能表检定装置', null, '2017-12-17 21:28:29', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('58', '2022-06-01', '070063', '2017-12-01', '040002', '2017-12-17 21:36:23', '2017-12-01', '测量互感器检定装置', null, '2017-12-17 21:36:23', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('59', '2022-06-01', '070064', '2017-12-01', '040003', '2017-12-17 21:45:07', '2017-12-01', '绝缘电阻、接地电阻测量仪检定装置', null, '2017-12-17 21:45:07', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('60', '2022-06-01', '070066', '2017-12-01', '040005', '2017-12-17 21:51:37', '2017-12-01', '心脑电图仪检定装置', null, '2017-12-17 21:51:37', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('61', '2022-06-01', '070067', '2017-12-01', '080001', '2017-12-17 21:54:15', '2017-12-01', '照射量计检定装置', null, '2017-12-17 21:54:15', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('62', '2022-06-01', '070069', '2017-12-01', '100001', '2017-12-17 21:58:06', '2017-12-01', '超声功率计检定装置', null, '2017-12-17 21:58:06', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('63', '2022-06-01', '070070', '2017-12-01', '070001', '2017-12-17 22:00:09', '2017-12-01', '有害气体分析仪检定装置', null, '2017-12-17 22:00:09', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('64', '2022-06-01', '070071', '2017-12-01', '070002', '2017-12-17 22:03:51', '2017-12-01', '酸度计检定装置', null, '2017-12-17 22:03:51', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('65', '2022-06-01', '070072', '2017-12-01', '070003', '2017-12-17 22:05:49', '2017-12-01', '分光光度计检定装置', null, '2017-12-17 22:05:49', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('66', '2022-06-01', '070071', '2017-12-01', '070005', '2017-12-17 22:09:11', '2017-12-01', '烟尘、粉尘测量仪检定装置', null, '2017-12-17 22:09:11', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('67', '2022-06-01', '070072', '2017-12-01', '090001', '2017-12-17 22:12:17', '2017-12-01', '验光仪检定装置', null, '2017-12-17 22:12:17', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('68', '2022-06-01', '070073', '2017-12-01', '020012', '2017-12-17 22:14:59', '2017-12-01', '燃气加气机检定装置', null, '2017-12-17 22:14:59', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('69', '2022-06-01', '070074', '2017-12-01', '030002', '2017-12-17 22:18:05', '2017-12-01', '热能表检定装置', null, '2017-12-17 22:18:05', '2022-12-01', null, '10');
INSERT INTO `device_set` VALUES ('70', '2022-07-01', '070071', '2018-01-01', '020001', '2018-01-08 15:26:33', '2018-01-01', 'F1等级砝码标准装置', null, '2018-01-08 15:26:33', '2023-01-01', null, '164');
INSERT INTO `device_set` VALUES ('71', '2022-07-01', '070072', '2018-01-01', '020002', '2018-01-08 15:42:05', '2018-01-01', 'F2等砝码标准装置', null, '2018-01-08 15:42:05', '2023-01-01', null, '164');
INSERT INTO `device_set` VALUES ('72', '2022-07-01', '070073', '2018-01-01', '020007', '2018-01-08 16:11:20', '2018-01-01', '燃油加油机检定装置', null, '2018-01-08 16:11:20', '2023-01-01', null, '164');
INSERT INTO `device_set` VALUES ('73', '2022-07-01', '070074', '2018-01-01', '020003', '2018-01-08 16:16:18', '2018-01-01', '压力表检定装置', null, '2018-01-08 16:16:18', '2023-01-01', null, '164');
INSERT INTO `device_set` VALUES ('74', '2022-07-01', '070075', '2018-01-01', '020004', '2018-01-08 16:21:28', '2018-01-01', '血压计检定装置', null, '2018-01-08 16:21:28', '2023-01-01', null, '164');
INSERT INTO `device_set` VALUES ('75', '2022-06-27', '070075', '2018-01-01', '020005', '2018-01-08 16:26:04', '2018-01-01', '出租车计价器检定装置', null, '2018-01-08 16:26:04', '2022-12-26', null, '164');
INSERT INTO `device_set` VALUES ('76', '2022-07-01', '070075', '2018-01-01', '020001', '2018-01-11 15:55:51', '2018-01-01', 'F1等级砝码标准装置', null, '2018-01-11 15:55:51', '2023-01-01', null, '527');
INSERT INTO `device_set` VALUES ('77', '2022-07-01', '070076', '2018-01-01', '020002', '2018-01-11 16:01:03', '2018-01-01', 'F2等砝码标准装置', null, '2018-01-11 16:01:03', '2023-01-01', null, '527');
INSERT INTO `device_set` VALUES ('78', '2022-07-01', '070077', '2018-01-01', '020005', '2018-01-11 16:06:08', '2018-01-01', '燃油加油机检定装置', null, '2018-01-11 16:06:08', '2023-01-01', null, '527');
INSERT INTO `device_set` VALUES ('79', '2022-07-01', '070078', '2018-01-01', '020003', '2018-01-11 16:08:41', '2018-01-01', '压力表检定装置', null, '2018-01-11 16:08:41', '2023-01-01', null, '527');
INSERT INTO `device_set` VALUES ('80', '2022-07-01', '070078', '2018-01-01', '020004', '2018-01-11 16:11:16', '2018-01-01', '血压计检定装置', null, '2018-01-11 16:11:16', '2023-01-01', null, '527');
INSERT INTO `device_set` VALUES ('81', '2022-07-01', '070079', '2018-01-01', '020007', '2018-01-11 16:13:51', '2018-01-01', '出租车计价器检定装置', null, '2018-01-11 16:13:51', '2023-01-01', null, '527');

-- ----------------------------
-- Table structure for `discipline`
-- ----------------------------
DROP TABLE IF EXISTS `discipline`;
CREATE TABLE `discipline` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT 'NULL',
  `pinyin` varchar(255) DEFAULT 'NULL',
  `update_time` bigint(20) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsdbsvuvi9aogcwlaslx7oi1pd` (`create_user_id`),
  CONSTRAINT `FKsdbsvuvi9aogcwlaslx7oi1pd` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of discipline
-- ----------------------------
INSERT INTO `discipline` VALUES ('1', null, '热学', 'rexue', null, '-2147483645', null);
INSERT INTO `discipline` VALUES ('2', null, '物理化学', 'wulihuaxue', null, '-2147483646', null);
INSERT INTO `discipline` VALUES ('3', null, '长度', 'changdu', null, '-2147483648', null);
INSERT INTO `discipline` VALUES ('4', null, '电磁学', 'diancixue', null, '-2147483647', null);
INSERT INTO `discipline` VALUES ('5', null, '力学', 'lixue', null, '-2147483640', null);
INSERT INTO `discipline` VALUES ('6', null, '声学', 'shengxue', null, '-2147483643', null);
INSERT INTO `discipline` VALUES ('7', null, '光学', 'guanxue', null, '-2147483641', null);
INSERT INTO `discipline` VALUES ('8', null, '电离辐射', 'dianlifushe', null, '-2147483642', null);
INSERT INTO `discipline` VALUES ('9', null, '时间频率', 'shijianpinlv', null, '-2147483639', null);
INSERT INTO `discipline` VALUES ('10', null, '无线电', 'wuxiandain', null, '-2147483644', null);

-- ----------------------------
-- Table structure for `district`
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `district_type_id` bigint(20) DEFAULT NULL,
  `parent_district_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7se8hubshhc9jgyqiybryijd2` (`create_user_id`),
  KEY `FKh10d8wsmhiykbcvb0n2yndwjb` (`district_type_id`),
  KEY `FKbq03xg2p16sijo8cmco76aiqr` (`parent_district_id`),
  CONSTRAINT `FK7se8hubshhc9jgyqiybryijd2` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKbq03xg2p16sijo8cmco76aiqr` FOREIGN KEY (`parent_district_id`) REFERENCES `district` (`id`),
  CONSTRAINT `FKh10d8wsmhiykbcvb0n2yndwjb` FOREIGN KEY (`district_type_id`) REFERENCES `district_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of district
-- ----------------------------
INSERT INTO `district` VALUES ('1', null, '内蒙古自治区', 'neimengguzizhiqu', null, null, '3', null);
INSERT INTO `district` VALUES ('2', null, '赤峰市', 'chifengshi', null, null, '2', '1');
INSERT INTO `district` VALUES ('3', null, '测试市', 'ceshishi', null, null, '2', '1');
INSERT INTO `district` VALUES ('4', null, '测试区县', 'ceshiquxian', null, null, '1', '3');
INSERT INTO `district` VALUES ('5', null, '巴林右旗', 'balinyouqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('6', null, '敖汉旗', 'aohanqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('7', null, '翁牛特旗', 'wengniuteqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('8', null, '元宝山区', 'yuanbaoshanqu', null, null, '1', '2');
INSERT INTO `district` VALUES ('9', null, '巴林左旗', 'balinzuoqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('10', null, '克什克腾旗', 'keshenketengqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('11', null, '宁城县', 'ningchengxian', null, null, '1', '2');
INSERT INTO `district` VALUES ('12', null, '喀喇沁旗', 'halaqinqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('13', null, '林西县', 'linxixian', null, null, '1', '2');
INSERT INTO `district` VALUES ('14', null, '阿鲁科尔沁旗', 'alvkeerqinqi', null, null, '1', '2');
INSERT INTO `district` VALUES ('15', null, '红山区', 'hongshanqu', null, null, '1', '2');
INSERT INTO `district` VALUES ('16', null, '松山区', 'songshanqu', null, null, '1', '2');

-- ----------------------------
-- Table structure for `district_type`
-- ----------------------------
DROP TABLE IF EXISTS `district_type`;
CREATE TABLE `district_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmc855ka36kopag45770tl8jwn` (`create_user_id`),
  CONSTRAINT `FKmc855ka36kopag45770tl8jwn` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of district_type
-- ----------------------------
INSERT INTO `district_type` VALUES ('1', '区\\县', 'quxian', null);
INSERT INTO `district_type` VALUES ('2', '市', 'shi', null);
INSERT INTO `district_type` VALUES ('3', '省', 'sheng', null);

-- ----------------------------
-- Table structure for `instrument_certificate_type`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_certificate_type`;
CREATE TABLE `instrument_certificate_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqif0o815sdj6bnfpael01hyrp` (`create_user_id`),
  CONSTRAINT `FKqif0o815sdj6bnfpael01hyrp` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_certificate_type
-- ----------------------------
INSERT INTO `instrument_certificate_type` VALUES ('1', '20170815', '校准证书', 'jiaozhunzhengshu', '20170816', null);
INSERT INTO `instrument_certificate_type` VALUES ('2', '20170815', '检定证书', 'jiandingzhengshu', '20170816', null);
INSERT INTO `instrument_certificate_type` VALUES ('3', '20170815', '测试证书', 'ceshizhengshu', '20170816', null);

-- ----------------------------
-- Table structure for `instrument_check_info`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_check_info`;
CREATE TABLE `instrument_check_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alert_date` date DEFAULT NULL,
  `certificate_num` varchar(255) DEFAULT NULL,
  `check_date` date DEFAULT NULL,
  `cost` bigint(20) DEFAULT NULL,
  `validity_date` date DEFAULT NULL,
  `certificate_status_id` bigint(20) DEFAULT NULL,
  `check_apply_id` bigint(20) NOT NULL,
  `check_department_id` bigint(20) DEFAULT NULL,
  `check_result_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `inspector_qualifier_id` bigint(20) DEFAULT NULL,
  `instrument_certificate_type_id` bigint(20) DEFAULT NULL,
  `instrument_production_id` bigint(20) DEFAULT NULL,
  `mandatory_instrument_id` bigint(20) NOT NULL,
  `ratifier_qualifier_id` bigint(20) DEFAULT NULL,
  `verifier_qualifier_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3wtx55t3lmnhdlt8si0mxjf2n` (`certificate_status_id`),
  KEY `FKlmcdmgl436nxu6nnhmaevdq6p` (`check_apply_id`),
  KEY `FKiou5mmf2517lmitf911krfk5b` (`check_department_id`),
  KEY `FK909lsx5dy53fle22ehke9lfyg` (`check_result_id`),
  KEY `FKo4bdrigdbmqc1n4qduwkk1m2n` (`create_user_id`),
  KEY `FK2djgw7iji9mm38enaxcm5vnjf` (`inspector_qualifier_id`),
  KEY `FKefqbotfc0grcnfwofak9fuem4` (`instrument_certificate_type_id`),
  KEY `FK8efv8bfbjtu6q091vwiabhim7` (`instrument_production_id`),
  KEY `FKnti09hw2j6quanpcgmscc8fmv` (`mandatory_instrument_id`),
  KEY `FKcef8ctqo6effri2wnryfpcfsx` (`ratifier_qualifier_id`),
  KEY `FKovy0924um4vuff47wegeu7hw9` (`verifier_qualifier_id`),
  CONSTRAINT `FK2djgw7iji9mm38enaxcm5vnjf` FOREIGN KEY (`inspector_qualifier_id`) REFERENCES `qualifier` (`id`),
  CONSTRAINT `FK3wtx55t3lmnhdlt8si0mxjf2n` FOREIGN KEY (`certificate_status_id`) REFERENCES `certificate_status` (`id`),
  CONSTRAINT `FK8efv8bfbjtu6q091vwiabhim7` FOREIGN KEY (`instrument_production_id`) REFERENCES `instrument_production` (`id`),
  CONSTRAINT `FK909lsx5dy53fle22ehke9lfyg` FOREIGN KEY (`check_result_id`) REFERENCES `check_result` (`id`),
  CONSTRAINT `FKcef8ctqo6effri2wnryfpcfsx` FOREIGN KEY (`ratifier_qualifier_id`) REFERENCES `qualifier` (`id`),
  CONSTRAINT `FKefqbotfc0grcnfwofak9fuem4` FOREIGN KEY (`instrument_certificate_type_id`) REFERENCES `instrument_certificate_type` (`id`),
  CONSTRAINT `FKiou5mmf2517lmitf911krfk5b` FOREIGN KEY (`check_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKlmcdmgl436nxu6nnhmaevdq6p` FOREIGN KEY (`check_apply_id`) REFERENCES `check_apply` (`id`),
  CONSTRAINT `FKnti09hw2j6quanpcgmscc8fmv` FOREIGN KEY (`mandatory_instrument_id`) REFERENCES `instrument_employment_info` (`id`),
  CONSTRAINT `FKo4bdrigdbmqc1n4qduwkk1m2n` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKovy0924um4vuff47wegeu7hw9` FOREIGN KEY (`verifier_qualifier_id`) REFERENCES `qualifier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_check_info
-- ----------------------------
INSERT INTO `instrument_check_info` VALUES ('1', null, 'C20171531', '2017-12-15', null, '2018-12-14', null, '3', '12', '2', '11', null, null, null, '13', null, null);
INSERT INTO `instrument_check_info` VALUES ('2', null, 'C20171532', '2017-12-15', null, '2018-12-14', null, '3', '12', '2', '11', null, null, null, '12', null, null);
INSERT INTO `instrument_check_info` VALUES ('3', null, 'C20171533', '2017-12-15', null, '2018-12-14', null, '3', '10', '2', '9', null, null, null, '11', null, null);
INSERT INTO `instrument_check_info` VALUES ('4', null, 'C20171534', '2017-12-15', null, '2018-12-14', null, '3', '10', '2', '9', null, null, null, '10', null, null);

-- ----------------------------
-- Table structure for `instrument_employment_info`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_employment_info`;
CREATE TABLE `instrument_employment_info` (
  `db_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `fix_site` varchar(255) DEFAULT NULL,
  `license_num` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `out_of_factory_name` varchar(255) DEFAULT NULL,
  `serial_num` varchar(255) DEFAULT NULL,
  `specification_name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `audit` bit(1) DEFAULT NULL,
  `audit_date` date DEFAULT NULL,
  `last_check_date` date DEFAULT NULL,
  `next_check_date` date DEFAULT NULL,
  `accuracy_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `generative_department_id` bigint(20) DEFAULT NULL,
  `instrument_production_id` bigint(20) DEFAULT NULL,
  `instrument_type_id` bigint(20) DEFAULT NULL,
  `max_measure_scale_id` bigint(20) DEFAULT NULL,
  `min_measure_scale_id` bigint(20) DEFAULT NULL,
  `purpose_id` bigint(20) DEFAULT NULL,
  `specification_id` bigint(20) DEFAULT NULL,
  `last_check_apply_id` bigint(20) DEFAULT NULL,
  `last_check_department_id` bigint(20) DEFAULT NULL,
  `last_instrument_check_info_id` bigint(20) DEFAULT NULL,
  `management_department_backed_reason_id` bigint(20) DEFAULT NULL,
  `mandatory_instrument_apply_id` bigint(20) DEFAULT NULL,
  `check_apply_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7jh84txijwm6uyr865t5y0epc` (`accuracy_id`),
  KEY `FKjhuxvndnsg2e6ne60lvku5qc8` (`create_user_id`),
  KEY `FKbcaw7eme9tep7jju0ihk2hbcb` (`department_id`),
  KEY `FKnuij2vke1g25awqci8mhy6tc` (`generative_department_id`),
  KEY `FKpk33lxng456huys2e1uhqka19` (`instrument_production_id`),
  KEY `FKmnt0iukwnokhvfoo3ka8mk6bf` (`instrument_type_id`),
  KEY `FKgnmiov6b864bfygwogl6j4er` (`max_measure_scale_id`),
  KEY `FK9o9m5ombqt83mdd85ot7fsu3r` (`min_measure_scale_id`),
  KEY `FKo3qukw5j9pvuu81issxqb4g61` (`purpose_id`),
  KEY `FK830aakw6ayr19nanfe0oqtnp2` (`specification_id`),
  KEY `FKki2tt3fj5noxf6oyq086pvbum` (`last_check_apply_id`),
  KEY `FKh6ydjx2hl9bolti3gdl3eu5rw` (`last_check_department_id`),
  KEY `FKkgrixq6bkxo0ak307sge0kitp` (`last_instrument_check_info_id`),
  KEY `FK7dob8mb4dbahfnf8evpxdt9x7` (`management_department_backed_reason_id`),
  KEY `FKnxkwaqn55jk4ertf34pfpay60` (`mandatory_instrument_apply_id`),
  KEY `FKcpren9c64q0vkp7lnidafoeuh` (`check_apply_id`),
  CONSTRAINT `FK7dob8mb4dbahfnf8evpxdt9x7` FOREIGN KEY (`management_department_backed_reason_id`) REFERENCES `backed_reason` (`id`),
  CONSTRAINT `FK7jh84txijwm6uyr865t5y0epc` FOREIGN KEY (`accuracy_id`) REFERENCES `accuracy` (`id`),
  CONSTRAINT `FK830aakw6ayr19nanfe0oqtnp2` FOREIGN KEY (`specification_id`) REFERENCES `specification` (`id`),
  CONSTRAINT `FK9o9m5ombqt83mdd85ot7fsu3r` FOREIGN KEY (`min_measure_scale_id`) REFERENCES `measure_scale` (`id`),
  CONSTRAINT `FKbcaw7eme9tep7jju0ihk2hbcb` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKcpren9c64q0vkp7lnidafoeuh` FOREIGN KEY (`check_apply_id`) REFERENCES `check_apply` (`id`),
  CONSTRAINT `FKgnmiov6b864bfygwogl6j4er` FOREIGN KEY (`max_measure_scale_id`) REFERENCES `measure_scale` (`id`),
  CONSTRAINT `FKh6ydjx2hl9bolti3gdl3eu5rw` FOREIGN KEY (`last_check_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKjhuxvndnsg2e6ne60lvku5qc8` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkgrixq6bkxo0ak307sge0kitp` FOREIGN KEY (`last_instrument_check_info_id`) REFERENCES `instrument_check_info` (`id`),
  CONSTRAINT `FKki2tt3fj5noxf6oyq086pvbum` FOREIGN KEY (`last_check_apply_id`) REFERENCES `check_apply` (`id`),
  CONSTRAINT `FKmnt0iukwnokhvfoo3ka8mk6bf` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`),
  CONSTRAINT `FKnuij2vke1g25awqci8mhy6tc` FOREIGN KEY (`generative_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKnxkwaqn55jk4ertf34pfpay60` FOREIGN KEY (`mandatory_instrument_apply_id`) REFERENCES `apply` (`id`),
  CONSTRAINT `FKo3qukw5j9pvuu81issxqb4g61` FOREIGN KEY (`purpose_id`) REFERENCES `purpose` (`id`),
  CONSTRAINT `FKpk33lxng456huys2e1uhqka19` FOREIGN KEY (`instrument_production_id`) REFERENCES `instrument_production` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1064 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_employment_info
-- ----------------------------
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '6', '2017-12-16 14:40:02', '22', '333', '分析天平', '分析天平', '233', '22', '0', '', '2017-12-16', null, '2017-12-15', '7', '11', '12', '31', null, '2', '682', '2', '1', null, null, null, null, null, '2', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '7', '2017-12-16 14:40:16', '22', '333', '分析天平', '分析天平', '23', '22', '0', '', '2017-12-16', null, '2017-12-15', '7', '11', '12', '31', null, '2', '683', '2', '1', null, null, null, null, null, '2', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '8', '2017-12-16 14:40:38', '22', '333', '分析天平', '分析天平', '12', '22', '0', '', '2017-12-16', null, '2017-12-24', '9', '11', '12', '31', null, '2', '679', '2', '1', null, null, null, null, null, '2', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '9', '2017-12-16 14:44:59', '安装地点', 'sdf', '王', '出厂名称', '出厂编号', '规格型号', '-1', '', null, null, '2017-12-14', '24', '2', '1', '32', null, '6', '80', '78', '4', null, null, null, null, null, '3', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '10', '2017-12-16 14:45:52', '22', '23433', '分析天平', '分析天平', '233', '22', '0', '', '2017-12-16', null, '2017-12-23', '7', '11', '12', '13', null, '2', '53', '2', '1', null, null, null, null, null, '4', '3');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '11', '2017-12-16 14:46:05', '22', '23433', '分析天平', '分析天平', '322', '22', '0', '', '2017-12-16', null, '2017-12-23', '9', '11', '12', '13', null, '2', '693', '2', '1', null, null, null, null, null, '4', '3');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '12', '2017-12-16 14:46:15', '22', '23433', '分析天平', '分析天平', '43', '22', '0', '', '2017-12-16', null, '2017-12-22', '9', '11', '12', '13', null, '2', '693', '2', '1', null, null, null, null, null, '4', '3');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '13', '2017-12-16 14:46:28', '22', '23433', '分析天平', '分析天平', '11', '22', '0', '', '2017-12-16', null, '2017-12-29', '8', '11', '12', '13', null, '2', '693', '2', '1', null, null, null, null, null, '4', '3');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '14', '2017-12-16 15:06:25', '安装地点', '许可证号', 's', '出厂名称', '123123', '21323', '0', '', '2017-12-17', null, '2017-12-29', '24', '11', '12', '33', null, '6', '742', '78', '4', null, null, null, null, null, '5', '7');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '15', '2017-12-17 16:41:51', '22', '22', '22', '22', '222', '22', '0', '', '2017-12-17', null, '2018-12-17', '25', '11', '12', '13', null, '7', '740', '102', '1', null, null, null, null, null, '7', '7');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '16', '2017-12-17 21:10:16', '实际的安装地点', '许可证号', '器具名称', '出厂名称', '出厂编号', '规格型号', '-2', '', '2017-12-18', null, '2018-12-17', '24', '11', '12', '34', null, '6', '80', '78', '6', null, null, null, null, null, '9', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '17', '2017-12-17 22:43:22', '22', '2222', '分析天平', '分析天平', '2222', '22', '0', '', '2017-12-17', null, '2018-12-17', '7', '11', '12', '13', null, '2', '3', '2', '1', null, null, null, null, null, '11', '8');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '18', '2017-12-17 22:43:41', '22', '2222', '分析天平', '分析天平', '23', '22', '0', '', '2017-12-17', null, '2018-12-17', '9', '11', '12', '13', null, '2', '3', '2', '1', null, null, null, null, null, '11', '8');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '19', '2017-12-17 22:43:56', '22', '2222', '分析天平', '分析天平', '24', '22', '0', '', '2017-12-17', null, '2018-12-17', '12', '11', '12', '13', null, '2', '3', '2', '1', null, null, null, null, null, '11', '8');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '20', '2017-12-17 22:44:12', '22', '2222', '分析天平', '分析天平', '25', '22', '0', '', '2017-12-17', null, '2018-12-17', '12', '11', '12', '13', null, '2', '13', '2', '1', null, null, null, null, null, '11', '9');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '21', '2017-12-17 22:44:39', '22', '2222', '分析天平', '分析天平', '26', '22', '0', '', '2017-12-17', null, '2018-12-17', '12', '11', '12', '13', null, '2', '680', '2', '1', null, null, null, null, null, '11', '9');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '22', '2017-12-17 22:45:13', '22', '2222', '分析天平', '分析天平', '27', '22', '0', '', '2017-12-17', null, '2018-12-17', '12', '11', '12', '13', null, '2', '686', '2', '1', null, null, null, null, null, '11', '9');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '23', '2017-12-18 09:12:01', '33', '23456', '电子天平', '微量天平', '234', '33', '0', '', '2017-12-18', null, '2018-12-18', '14', '11', '12', '13', null, '3', '51', '14', '1', null, null, null, null, null, '12', '10');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '24', '2017-12-18 09:12:21', '33', '23456', '电子天平', '微量天平', '356', '33', '0', '', '2017-12-18', null, '2018-12-18', '15', '11', '12', '13', null, '3', '15', '14', '1', null, null, null, null, null, '12', '10');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '25', '2017-12-18 09:12:44', '33', '23456', '电子天平', '微量天平', '236', '33', '0', '', '2017-12-18', null, '2018-12-18', '16', '11', '12', '13', null, '3', '42', '14', '1', null, null, null, null, null, '12', '10');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '26', '2017-12-18 09:13:54', '33', '23456', '电子天平', '微量天平', '456', '33', '0', '', '2017-12-18', null, '2018-12-18', '16', '11', '12', '13', null, '3', '45', '14', '1', null, null, null, null, null, '12', '11');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '27', '2017-12-18 15:18:37', '33', '4567', '地秤', '汽车衡', '357', '22', '0', '', '2017-12-18', null, '2018-12-18', '55', '11', '12', '13', null, '19', '183', '181', '1', null, null, null, null, null, '13', '12');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '28', '2017-12-18 15:19:16', '33', '4567', '地秤', '汽车衡', '432', '22', '0', '', '2017-12-18', null, '2018-12-18', '55', '11', '12', '13', null, '19', '191', '181', '1', null, null, null, null, null, '13', '12');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '29', '2017-12-18 15:19:41', '33', '4567', '地秤', '汽车衡', '567', '22', '0', '', '2017-12-18', null, '2018-12-18', '55', '11', '12', '13', null, '19', '185', '181', '1', null, null, null, null, null, '13', '12');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '30', '2017-12-18 15:20:01', '33', '4567', '地秤', '汽车衡', '123', '22', '0', '', '2017-12-18', null, '2018-12-18', '55', '11', '12', '13', null, '19', '186', '181', '1', null, null, null, null, null, '13', '13');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '31', '2017-12-18 16:12:36', 'aohan', '1234', 'juanchi', '1234', '1234', '1234', '-2', '', '2017-12-18', null, '2018-12-18', '55', '19', '21', '39', null, '19', '184', '181', '1', null, null, null, null, null, '15', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '32', '2017-12-18 16:21:18', '现场', '683658326', '地称2', '地称3', '6464283468', '63468723', '-2', '', '2017-12-18', null, '2018-12-18', '55', '23', '25', '43', null, '19', '182', '181', '1', null, null, null, null, null, '16', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '33', '2017-12-18 16:22:25', '天山镇', '91111111111', '电子汽车衡', '电子汽车衡', '123456789', '12346', '-2', '', '2017-12-18', null, '2018-12-18', '55', '34', '42', '44', null, '19', '184', '181', '1', null, null, null, null, null, '17', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '34', '2017-12-18 16:24:42', '123', '123', '123', '123', '123', '123', '0', '', '2017-12-18', null, '2018-12-18', '14', '30', '37', '32', null, '3', '51', '14', '1', null, null, null, null, '2', '18', '15');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '35', '2017-12-18 16:29:15', 'AOHAN', '12345', 'diangzhi', 'aohan', '123456', 'D2004', '-2', '', '2017-12-18', null, '2018-12-18', '14', '29', '35', '47', null, '3', '51', '14', '1', null, null, null, null, null, '19', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '36', '2017-12-18 16:33:29', '现场', '93879', '95739', '5974390', '4859238', '4326487', '0', '', '2017-12-18', null, '2018-12-18', '56', '32', '40', '43', null, '20', '199', '210', '1', null, null, null, null, null, '20', '14');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '37', '2017-12-18 16:37:58', '天山镇', '1232141435353', '电子汽车衡', '电子汽车衡', '128974894717', '1234', '0', '', '2017-12-18', null, '2018-12-18', '56', '34', '42', '44', null, '20', '198', '757', '1', null, null, null, null, null, '21', '16');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '38', '2017-12-18 21:10:39', '锦山镇', '344532134', '天平', '222', '12345', '22222', '-2', '', '2017-12-18', null, '2018-12-18', '16', '37', '48', '13', null, '3', '32', '14', '1', null, null, null, null, null, '22', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '39', '2017-12-18 21:17:57', '锦山镇', '2345775', '223344', '3433332', '12345', '235556', '0', '', '2017-12-18', null, '2018-12-18', '55', '37', '48', '49', null, '19', '756', '181', '1', null, null, null, null, null, '23', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '40', '2017-12-19 08:52:55', 'asgdzxv', 'saregvxdzcv', '飞洒滚蛋吧', 'adfdsf', 'fwe', 'agdsgv', '0', '', '2017-12-19', null, '2018-12-19', '58', '23', '25', '43', null, '22', '233', '227', '5', null, null, null, null, null, '24', '18');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '41', '2017-12-19 09:19:56', '现场', '15354962589658698', '地秤2', '地秤生复活甲开发', '123456789211', '4365343', '0', '', '2017-12-19', null, '2018-12-19', '55', '40', '52', '43', null, '19', '182', '181', '1', null, null, null, null, null, '25', '19');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '42', '2017-12-19 09:37:12', '天上', '736186286', '钻天后', '上天了', '438', '676', '0', '', '2017-12-19', null, '2018-12-19', '55', '42', '54', '55', null, '19', '759', '181', '1', null, null, null, null, null, '26', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '44', '2017-12-19 11:49:04', '松山区哈拉道口镇横排子村盛港加油站', '123456', '燃油加油机', '税控燃油加油机', '16640096', 'ytsj', '-2', '', '2017-12-19', null, '2018-12-19', '61', '44', '57', '58', null, '25', '263', '261', '1', null, null, null, null, null, '28', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '45', '2017-12-19 11:54:29', '赤峰市盛港加油站', '123456', '燃油加油机', '税控燃油加油机', '3K10904082', 'cs32j1110f', '-2', '', '2017-12-19', null, '2018-12-19', '61', '44', '57', '59', null, '25', '263', '261', '1', null, null, null, null, null, '28', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '46', '2017-12-19 11:55:09', '赤峰市盛港加油站', '123456', '燃油加油机', '税控燃油加油机', '3K10904082', 'cs32j1110f', '-2', '', '2017-12-19', null, '2018-12-19', '61', '44', '57', '59', null, '25', '263', '261', '1', null, null, null, null, null, '28', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '47', '2017-12-19 11:55:40', '赤峰市盛港加油站', '123456', '燃油加油机', '税控燃油加油机', '3K10904075', 'cs32j1110f', '-2', '', '2017-12-19', null, '2018-12-19', '61', '44', '57', '59', null, '25', '263', '261', '1', null, null, null, null, null, '28', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '48', '2017-12-19 12:06:24', '123456', '1233', '1233', '1233', '123', '1233', '-1', '', null, null, '2018-12-19', '61', '44', '57', '33', null, '25', '263', '261', '1', null, null, null, null, null, '28', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '49', '2017-12-19 12:36:28', '赤峰市盛港加油站', '123456', '燃油加油机', '税控燃油加油机', '3K10904082', 'CS32J1110F', '0', '', '2017-12-19', null, '2018-12-19', '61', '44', '57', '59', null, '25', '263', '261', '1', null, null, null, null, null, '29', '20');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '50', '2017-12-19 12:36:49', '赤峰市盛港加油站', '123456', '燃油加油机', '税控燃油加油机', '3K10904082', 'CS32J1110F', '0', '', '2017-12-19', null, '2018-12-19', '61', '44', '57', '59', null, '25', '263', '261', '1', null, null, null, null, null, '29', '20');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '51', '2017-12-19 14:57:00', '天山镇', '15616516516556', '电子汽车衡', '常州电子汽车衡', '1231564642323', 'IW-89', '0', '', '2017-12-19', null, '2018-12-19', '55', '34', '42', '44', null, '19', '184', '181', '1', null, null, null, null, null, '30', '21');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '52', '2017-12-19 14:57:21', '天山镇', '15616516516556', '电子汽车衡', '常州电子汽车衡', '1261465122', 'IW-89', '0', '', '2017-12-19', null, '2018-12-19', '55', '34', '42', '44', null, '19', '184', '181', '1', null, null, null, null, null, '30', '21');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '53', '2017-12-19 15:02:52', '123', '123456', '加油机', '加油机', '123456', '123456', '-1', '', null, null, '2018-12-19', '61', '48', '61', '47', null, '25', '263', '261', '1', null, null, null, null, null, '31', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '54', '2017-12-19 15:03:21', '456', '123456', '加油机', '加油机', '123456', '123456', '-1', '', null, null, '2018-12-19', '61', '48', '61', '47', null, '25', '263', '261', '1', null, null, null, null, null, '31', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '55', '2017-12-19 15:06:27', '天山镇', '1231321213', '电子汽车衡', '常州汽车衡厂', '45645645465', 'WA-89', '0', '', '2017-12-19', null, '2018-12-19', '55', '34', '42', '44', null, '19', '759', '181', '1', null, null, null, null, null, '32', '22');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '56', '2017-12-19 15:18:34', '天山镇', '48454545', '电子天平', '电子天平', '1564212132', 'SW-789456', '0', '', '2017-12-19', null, '2018-12-19', '16', '34', '42', '69', null, '3', '32', '14', '1', null, null, null, null, null, '33', '23');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '57', '2017-12-19 15:37:47', '天山镇', 'a12582', '天平', '天平', '147852369', 'QWE', '0', '', '2017-12-19', null, '2018-12-19', '16', '34', '42', '69', null, '3', '51', '14', '1', null, null, null, null, null, '34', '39');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '58', '2017-12-21 11:05:02', '锅炉', 'CMC12345', '压力表', '压力表', '2071951', 'Y-100', '-2', '', '2017-12-21', null, '2018-12-21', '105', '56', '70', '71', null, '35', '407', '395', '4', null, null, null, null, null, '36', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '59', '2017-12-21 14:30:42', '锅炉', 'CMC2345', '压力表', '压力表', '78956', 'Y-100', '-1', '', null, null, '2018-12-21', '105', '22', '24', '71', null, '35', '407', '395', '4', null, null, null, null, null, '37', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '60', '2017-12-21 14:53:45', '高压锅灭菌', 'CMC234', '压力表', '压力表', '0133', 'Y-60', '0', '', '2017-12-21', null, '2018-12-21', '105', '22', '24', '73', null, '35', '397', '395', '4', null, null, null, null, null, '38', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '61', '2017-12-21 14:57:43', '化验室', 'CMC234', '电子天平', '电子天平', '1H560', 'JT1003B', '0', '', '2017-12-21', null, '2018-12-21', '13', '57', '72', '74', null, '3', '32', '14', '1', null, null, null, null, null, '38', '25');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '62', '2017-12-21 16:35:17', 'df48', '22+', '26+5+', '265', '6649', '58489', '-2', '', '2017-12-21', null, '2018-12-21', '163', '25', '27', '77', null, '65', '539', '538', '2', null, null, null, null, null, '39', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '63', '2017-12-22 09:42:50', '车间外', 'scs-100', '电子汽车衡', '电子汽车衡', '2013177', 'scs-100', '-2', '', '2017-12-22', null, '2018-12-22', '55', '63', '81', '82', null, '19', '184', '181', '1', null, null, null, null, null, '40', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '64', '2017-12-22 09:43:52', '车间外', 'scs-150', '电子汽车衡', '电子汽车衡', '13154', 'scs-150', '-2', '', '2017-12-22', null, '2018-12-22', '55', '63', '81', '82', null, '19', '185', '181', '1', null, null, null, null, null, '40', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '65', '2017-12-22 09:54:26', '锅炉', 'CMC1234', '压力表', '压力表', '7895H', 'Y-100', '-1', '', null, null, '2018-12-22', '105', '57', '72', '71', null, '35', '407', '395', '4', null, null, null, null, null, '41', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '66', '2017-12-22 10:37:49', '一楼冷干机', '01060102', '压力表', '压力表', '01060102', '100', '-1', '', null, null, '2018-12-22', '104', '64', '83', '85', null, '35', '398', '395', '4', null, null, null, null, null, '42', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '67', '2017-12-22 10:39:44', '一楼冷干机', '141116062', '压力表', '压力表', '141116062', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '86', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '68', '2017-12-22 10:40:50', '一楼制氮机', 'YXC100', '压力表', '压力表', 'YXC100', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '87', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '69', '2017-12-22 10:42:02', '一楼制氮机', 'CM0765', '压力表', '压力表', 'CM0765', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '88', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '70', '2017-12-22 10:42:18', '一楼制氮机', 'CM0742', '压力表', '压力表', 'CM0742', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '88', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '71', '2017-12-22 10:42:40', '一楼制氮机', '14036457', '压力表', '压力表', '14036457', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '89', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '72', '2017-12-22 10:44:03', '冷冻1号氨机', '1205554', '压力表', '压力表', '1205554', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '90', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '73', '2017-12-22 10:44:39', '冷冻1号氨机', '4714-9-010', '压力表', '压力表', '4714-9-010', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '90', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '74', '2017-12-22 10:45:02', '冷冻1号氨机', '4708-9-010', '压力表', '压力表', '4708-9-010', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '91', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '75', '2017-12-22 10:45:22', '冷冻1号氨机', '1051170', '压力表', '压力表', '1051170', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '92', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '76', '2017-12-22 10:45:47', '冷冻1号氨机', '4101-9-010', '压力表', '压力表', '4101-9-010', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '91', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '77', '2017-12-22 10:46:05', '冷冻1号氨机', '4542-9-010', '压力表', '压力表', '4542-9-010', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '91', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '78', '2017-12-22 10:46:56', '冷冻蒸发器', '2013-6-304', '压力表', '压力表', '2013-6-304', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '93', null, '4', '67', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '79', '2017-12-22 10:47:30', '冷冻蒸发器', '13-9-2007', '压力表', '压力表', '13-9-2007', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '94', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '80', '2017-12-22 10:48:08', '冷冻集油器', '04-10-7-731', '压力表', '压力表', '04-10-7-731', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '71', null, '4', '71', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '81', '2017-12-22 10:48:53', '冷冻制氮机', 'PL-11-3521', '压力表', '压力表', 'PL-11-3521', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '95', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '82', '2017-12-22 10:49:14', '冷冻制氮机', '204090120', '压力表', '压力表', '204090120', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '96', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '83', '2017-12-22 10:49:48', '冷冻制氮机', '114193269', '压力表', '压力表', '114193269', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '71', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '84', '2017-12-22 10:50:13', '冷冻制氮机', 'XC414007', '压力表', '压力表', 'XC414007', '60', '-1', '', null, null, '2018-12-22', '20', '64', '83', '96', null, '4', '71', '54', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '85', '2017-12-22 10:51:09', '冷冻分水器', '1416220', '压力表', '压力表', '1416220', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '94', null, '4', '67', '55', '4', null, null, null, null, null, '43', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '86', '2017-12-22 10:55:43', '锅炉', 'QL.10.6882', '压力表', '压力表', 'QL.10.6882', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '87', '2017-12-22 10:56:08', '锅炉', 'QL.10.6729', '压力表', '压力表', 'QL.10.6729', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '88', '2017-12-22 10:56:32', '锅炉', 'PL.10.2840', '压力表', '压力表', 'PL.10.2840', '150', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '89', '2017-12-22 10:56:57', '锅炉', 'PL.11.7836', '压力表', '压力表', 'PL.11.7836', '150', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '90', '2017-12-22 10:57:29', '锅炉', '0.07.7475', '压力表', '压力表', '0.07.7475', '250', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '91', '2017-12-22 10:58:01', '锅炉', 'QL.10.7542', '压力表', '压力表', 'QL.10.7542', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '92', '2017-12-22 10:58:33', '锅炉', 'QL.10.6897', '压力表', '压力表', 'QL.10.6897', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '93', '2017-12-22 10:59:03', '锅炉', 'QL.10.6761', '压力表', '压力表', 'QL.10.6761', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '94', '2017-12-22 10:59:21', '锅炉', 'QL.10.6908', '压力表', '压力表', 'QL.10.6908', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '95', '2017-12-22 10:59:51', '锅炉', 'QL.10.6797', '压力表', '压力表', 'QL.10.6797', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '96', '2017-12-22 11:00:09', '锅炉', 'QL.10.6849', '压力表', '压力表', 'QL.10.6849', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '97', '2017-12-22 11:00:47', '锅炉', 'QL.10.6855', '压力表', '压力表', 'QL.10.6855', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '98', '2017-12-22 11:01:03', '锅炉', 'QL.10.6771', '压力表', '压力表', 'QL.10.6771', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '99', '2017-12-22 11:01:21', '锅炉', '0.07.7464', '压力表', '压力表', '0.07.7464', '250', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '100', '2017-12-22 11:01:31', '锅炉', '0.07.7457', '压力表', '压力表', '0.07.7457', '250', '-1', '', null, null, '2018-12-22', '20', '64', '83', '97', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '101', '2017-12-22 11:02:38', '锅炉', '201510371', '压力表', '压力表', '201510371', '250', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '102', '2017-12-22 11:03:16', '锅炉', '201611759', '压力表', '压力表', '201611759', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '103', '2017-12-22 11:03:36', '锅炉', '2011330', '压力表', '压力表', '2011330', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '104', '2017-12-22 11:04:05', '锅炉', '201471417', '压力表', '压力表', '201471417', '250', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '105', '2017-12-22 11:04:27', '锅炉', '20136805', '压力表', '压力表', '20136805', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '106', '2017-12-22 11:04:57', '锅炉', '20136302', '压力表', '压力表', '20136302', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '55', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '107', '2017-12-22 11:05:26', '锅炉', '20136802', '压力表', '压力表', '20136802', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '99', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '108', '2017-12-22 11:06:18', '锅炉', '150413345', '压力表', '压力表', '150413345', '150', '-1', '', null, null, '2018-12-22', '20', '64', '83', '100', null, '4', '71', '54', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '109', '2017-12-22 11:07:09', '锅炉', '1416509', '压力表', '压力表', '1416509', '100', '-1', '', null, null, '2018-12-22', '20', '64', '83', '94', null, '4', '71', '55', '4', null, null, null, null, null, '44', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '110', '2017-12-23 08:18:50', '理化室', 'CMC', '电子天平', '电子天平', '057726', 'FA1004A', '-2', '', '2018-01-11', null, '2018-12-23', '13', '65', '84', '114', null, '3', '32', '14', null, null, null, null, null, null, '45', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '111', '2017-12-23 08:27:13', '车间', 'MC', '氧气压力表', '氧气压力表', '178113', '0-2.5', '0', '', '2018-01-11', null, '2018-12-23', '258', '65', '84', '115', null, '4', '67', '54', null, null, null, null, null, null, '46', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '112', '2017-12-23 08:32:10', '车间', 'MC', '氧气压力表', '氧气压力表', '117272', '0-2.5', '0', '', '2018-01-11', null, '2018-12-23', '258', '65', '84', '115', null, '4', '67', '54', null, null, null, null, null, null, '47', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '113', '2017-12-23 08:39:50', '车间', 'MC', '乙炔压力表', '乙炔压力表', '10040916', '0-4', '0', '', '2018-01-11', null, '2018-12-23', '259', '65', '84', '116', null, '4', '58', '54', null, null, null, null, null, null, '48', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '114', '2017-12-23 08:42:47', '车间', 'MC', '乙炔压力表', '乙炔压力表', '105380080', '0-4', '0', '', '2018-01-11', null, '2018-12-23', '253', '65', '84', '116', null, '35', '404', '395', null, null, null, null, null, null, '49', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '115', '2017-12-23 08:45:24', '车间', 'MC', '乙炔压力表', '乙炔压力表', '1004113', '0-0.25', '0', '', '2018-01-11', null, '2018-12-23', '253', '65', '84', '116', null, '35', '397', '395', null, null, null, null, null, null, '50', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '116', '2017-12-23 08:47:17', '车间', 'MC', '乙炔压力表', '乙炔压力表', '10841128', '0-0.25', '0', '', '2018-01-11', null, '2018-12-23', '253', '65', '84', '116', null, '35', '397', '395', null, null, null, null, null, null, '51', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '117', '2017-12-23 08:50:04', '车间', 'MC', '氧气压力表', '氧气压力表', '117297', '0-25', '0', '', '2018-01-11', null, '2018-12-23', '248', '65', '84', '115', null, '37', '439', '456', null, null, null, null, null, null, '52', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '118', '2017-12-23 08:52:48', '车间', 'MC', '氧气压力表', '氧气压力表', '1711202', '0-25', '0', '', '2018-01-11', null, '2018-12-23', '248', '65', '84', '115', null, '37', '439', '456', null, null, null, null, null, null, '53', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '119', '2017-12-23 08:59:37', '车间', 'GW', '钢卷尺', '钢卷尺', '9211008', '0-10', '0', '', '2018-01-11', null, '2018-12-23', '23', '65', '84', '117', null, '6', '84', '78', null, null, null, null, null, null, '54', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '120', '2017-12-23 09:01:40', '车间', 'GW', '钢卷尺', '钢卷尺', '9211008', '0-5', '0', '', '2018-01-11', null, '2018-12-23', '23', '65', '84', '118', null, '6', '82', '78', null, null, null, null, null, null, '55', '50');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '121', '2017-12-25 08:51:56', '厂区北门内', '冀制00000126', '电子汽车衡', '电子汽车衡', '201709011', 'SCS-120', '0', '', '2017-12-26', null, '2018-12-25', '55', '67', '101', '119', null, '19', '185', '181', '1', null, null, null, null, null, '56', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '122', '2017-12-25 08:52:25', '厂区北门内', '冀制00000126', '电子汽车衡', '电子汽车衡', '201709012', 'SCS-120', '0', '', '2017-12-26', null, '2018-12-25', '55', '67', '101', '119', null, '19', '185', '181', '1', null, null, null, null, null, '56', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '123', '2017-12-25 14:13:35', '质量部', '苏制00000153', '砝码', '标准砝码', 'JL-FM-01', '500g', '-2', '', '2018-01-10', null, '2018-12-25', '45', '67', '101', '123', null, '16', null, null, '4', null, null, null, null, null, '57', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '124', '2017-12-25 14:19:46', '质量部', '10000038', '砝码', '标准砝码', 'JL-FM-02', '5kg', '-2', '', '2018-01-10', null, '2018-12-25', '45', '67', '101', '124', null, '16', null, null, '4', null, null, null, null, null, '58', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '125', '2017-12-25 14:22:29', '质量部', '10000038', '砝码', '标准砝码', 'JL-FM-03', '10kg', '-2', '', '2018-01-10', null, '2018-12-25', '45', '67', '101', '124', null, '16', null, null, '4', null, null, null, null, null, '59', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '126', '2017-12-25 14:23:33', '质量部', '10000038', '砝码', '标准砝码', 'JL-FM-04', '20kg', '-2', '', '2018-01-10', null, '2018-12-25', '45', '67', '101', '124', null, '16', null, null, '4', null, null, null, null, null, '59', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '127', '2017-12-25 14:25:51', '质量部', '10000038', '砝码', '标准砝码', 'JL-FM-05', '25kg', '-2', '', '2018-01-10', null, '2018-12-25', '45', '67', '101', '124', null, '16', null, null, '4', null, null, null, null, null, '59', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '128', '2017-12-25 15:34:37', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.06.5110', '（0-1.6）MPa', '0', '', '2017-12-29', null, '2018-12-25', '110', '71', '105', '125', null, '37', '443', '456', '2', null, null, null, null, null, '60', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '129', '2017-12-25 15:40:52', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.06.5094', '（0-1.6）MPa', '-2', '', '2017-12-29', null, '2018-12-25', '110', '71', '105', '125', null, '37', '443', '456', '2', null, null, null, null, null, '61', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '130', '2017-12-25 15:45:59', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.01.2944', '（0-1.6）MPa', '-2', '', '2017-12-29', null, '2018-12-25', '110', '71', '105', '125', null, '37', '443', '456', '2', null, null, null, null, null, '62', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '131', '2017-12-25 15:49:30', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.01.2947', '（0-1.6）MPa', '-2', '', '2017-12-29', null, '2018-12-25', '110', '71', '105', '125', null, '37', '443', '456', '2', null, null, null, null, null, '63', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '132', '2017-12-25 16:03:53', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'OL.07.9725', '（0-2.5）MPa', '0', '', '2017-12-29', null, '2018-12-25', '109', '71', '105', '125', null, '37', '444', '456', '2', null, null, null, null, null, '64', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '133', '2017-12-25 16:06:47', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'N.09.8594', '（0-2.5）MPa', '-2', '', '2017-12-29', null, '2018-12-25', '109', '71', '105', '125', null, '37', '444', '456', '2', null, null, null, null, null, '65', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '134', '2017-12-26 09:51:09', '环境保护局', '2L01001840', '智能综合校准仪', '青岛崂山研究所', '1410102', '20176946', '0', '', '2017-12-26', null, '2018-12-26', '198', '74', '108', '163', null, '83', '608', '608', '5', null, null, null, null, null, '66', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '135', '2017-12-26 10:02:52', '翁牛特旗环保局', '2L01001840', '智能综合校准仪', '青岛崂山研究所', '1410103', '20176945', '-2', '', '2017-12-29', null, '2018-12-26', '198', '74', '108', '163', null, '83', '608', '608', '5', null, null, null, null, null, '67', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '136', '2017-12-26 10:05:34', '厂房', '20172313', '氨压力真空表', '氨压力真空表', '2017-4-655', 'y-100', '-2', '', '2017-12-29', null, '2018-12-26', '17', '85', '128', '165', null, '4', '69', '54', '4', null, null, null, null, null, '68', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '137', '2017-12-26 10:09:15', '厂房', '2017334', '耐震不锈钢隔膜压力表', '耐震不锈钢隔膜压力表', '2017-2-427', '0-2.5Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '396', '395', '4', null, null, null, null, null, '69', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '138', '2017-12-26 10:11:31', '厂房', '2017335', '耐震不锈钢隔膜压力表', '耐震不锈钢隔膜压力表', '2017-2-428', '0-2.5Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '396', '395', '4', null, null, null, null, null, '70', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '139', '2017-12-26 10:14:15', '厂房', '2017336', '压力表', '压力表', '07607016', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '166', null, '35', '409', '395', '4', null, null, null, null, null, '70', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '140', '2017-12-26 10:14:41', '厂房', '2017337', '压力表', '压力表', '076707096', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '166', null, '35', '409', '395', '4', null, null, null, null, null, '70', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '141', '2017-12-26 10:15:38', '厂房', '2017341', '耐震不锈钢隔膜压力表', '耐震不锈钢隔膜压力表', '2017-2-637', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '407', '395', '4', null, null, null, null, null, '70', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '142', '2017-12-26 10:15:40', '翁牛特旗环保局', '20176184', '中性滤光片', '青岛崂山', '22-1880-01-0030', 'ZK025', '-2', '', '2017-12-29', null, '2018-12-26', '190', '74', '108', '167', null, '77', '589', '588', '5', null, null, null, null, null, '71', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '143', '2017-12-26 10:16:32', '厂房', '2017338', '耐震不锈钢隔膜压力表', '耐震不锈钢隔膜压力表', '2017-2-636', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '407', '395', '4', null, null, null, null, null, '70', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '144', '2017-12-26 10:19:43', '翁旗环境保护局', '20176185', '中性滤光片', 'ZK025', '22-1880-01-0028', 'ZK025', '0', '', '2017-12-29', null, '2018-12-26', '190', '74', '108', '167', null, '77', '589', '588', '5', null, null, null, null, null, '72', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '145', '2017-12-26 10:19:49', '厂房', '2017339', '耐震不锈钢隔膜压力表', '耐震不锈钢隔膜压力表', '2017-6-638', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '146', '2017-12-26 10:20:21', '加油站', 'CS32J2220F', '燃油加油机', '燃油加油机', '8YO150800054-1', 'CS32J2220F', '-2', '', '2018-01-10', null, '2018-12-26', '61', '94', '137', '168', null, '25', null, null, '1', null, null, null, null, null, '74', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '147', '2017-12-26 10:20:57', '厂房', '2017340', '耐震不锈钢隔膜压力表', '耐震不锈钢隔膜压力表', '2017-2-635', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '148', '2017-12-26 10:21:20', '加油站', 'CS32J2220F', '燃油加油机', '燃油加油机', '8YO150800054-2', 'CS32J2220F', '-2', '', '2018-01-10', null, '2018-12-26', '61', '94', '137', '168', null, '25', null, null, '1', null, null, null, null, null, '74', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '149', '2017-12-26 10:22:26', '厂房', '2017354', '抗震压力表', '抗震压力表', 'Y2-4-17-395', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '71', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '150', '2017-12-26 10:23:07', '厂房', '2017352', '抗震压力表', '抗震压力表', 'Y2-4-17-322', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '71', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '151', '2017-12-26 10:23:23', '加油站', 'CS32J2220F', '燃油加油机', '燃油加油机', '3A0604508', 'CS32C1110A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '94', '137', '168', null, '25', null, null, '1', null, null, null, null, null, '74', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '152', '2017-12-26 10:24:20', '厂房', '2017350', '压力表', '压力表', '2011-9-5137', '0-1Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '398', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '153', '2017-12-26 10:24:26', '加油站', 'CS32J2220F', '燃油加油机', '燃油加油机', '3A440112110010120725', 'CS32C1110A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '94', '137', '168', null, '25', null, null, '1', null, null, null, null, null, '74', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '154', '2017-12-26 10:24:51', '厂房', '2017351', '压力表', '压力表', '2011-9-5148', '0-1Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '165', null, '35', '398', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '155', '2017-12-26 10:25:26', '加油站', 'CS32J2220F', '燃油加油机', '燃油加油机', '1A10300309', 'CS30C111E', '-2', '', '2018-01-10', null, '2018-12-26', '61', '94', '137', '168', null, '25', null, null, '1', null, null, null, null, null, '74', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '156', '2017-12-26 10:26:01', '加油站', 'CS32J2220F', '燃油加油机', '燃油加油机', '1A10300309', 'CS30C111E', '-2', '', '2018-01-10', null, '2018-12-26', '61', '94', '137', '168', null, '25', null, null, '1', null, null, null, null, null, '74', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '157', '2017-12-26 10:26:14', '厂房', '2017349', '电接点压力表', '电接点压力表', '15710', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '169', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '158', '2017-12-26 10:28:09', '厂房', '2017344', '感应开关式压力表', '感应开关式压力表', '15-12-1262', '0-40Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '170', null, '35', '403', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '159', '2017-12-26 10:28:33', '厂房', '2017346', '感应开关式压力表', '感应开关式压力表', '15-12-1102', '0-40Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '170', null, '35', '403', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '160', '2017-12-26 10:29:16', '厂房', '2017343', '感应开关式压力表', '感应开关式压力表', '15-12-1022', '0-40Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '170', null, '35', '403', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '161', '2017-12-26 10:29:42', '厂房', '2017345', '感应开关式压力表', '感应开关式压力表', '15-12-1449', '0-40Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '170', null, '35', '403', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '162', '2017-12-26 10:30:06', '厂房', '2017342', '感应开关式压力表', '感应开关式压力表', '15-12-1091', '0-40Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '170', null, '35', '403', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '163', '2017-12-26 10:31:48', '厂房', '2017347', '电接点压力表', '电接点压力表', 'R151020R5037', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '171', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '164', '2017-12-26 10:32:17', '翁旗环境保护局', '20176182', '智能化酸度计检定仪', '上海电科学研究所', '601109N0013060016', '1042', '-2', '', '2017-12-29', null, '2018-12-26', '174', '74', '108', '172', null, '70', '571', '570', '5', null, null, null, null, null, '75', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '165', '2017-12-26 10:32:21', '厂房', '2017348', '电接点压力表', '电接点压力表', '1512161S127', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '171', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '166', '2017-12-26 10:33:07', '厂房', '2017353', '抗震压力表', '抗震压力表', 'Y2-4-20-79', '0-1.6Mpa', '-2', '', '2017-12-29', null, '2018-12-26', '105', '85', '128', '71', null, '35', '407', '395', '4', null, null, null, null, null, '73', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '167', '2017-12-26 10:35:10', '翁旗环境保护局', '20176183', '智能化酸度计检定仪', '1042', '601109N0013060003', '141101', '-2', '', '2017-12-29', null, '2018-12-26', '174', '74', '108', '172', null, '70', '571', '570', '5', null, null, null, null, null, '76', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '168', '2017-12-26 10:40:22', '加油站', 'jsk-45F1121A', '燃油加油机', '燃油加油机', '041653', 'jsk-45F1121A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '95', '138', '173', null, '25', null, '258', '1', null, null, null, null, null, '77', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '169', '2017-12-26 10:41:13', '加油站', 'jsk-45F1121A', '燃油加油机', '燃油加油机', '041662', 'jsk-45F1121A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '95', '138', '173', null, '25', null, '258', '1', null, null, null, null, null, '77', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '170', '2017-12-26 10:41:45', '加油站', 'jsk-45F1121A', '燃油加油机', '燃油加油机', '090116', 'jsk-45F1121A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '95', '138', '173', null, '25', null, '258', '1', null, null, null, null, null, '77', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '171', '2017-12-26 10:42:06', '加油站', 'jsk-45F1121A', '燃油加油机', '燃油加油机', '090116', 'jsk-45F1121A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '95', '138', '173', null, '25', null, '258', '1', null, null, null, null, null, '77', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '172', '2017-12-26 10:42:27', '加油站', 'jsk-45F1121A', '燃油加油机', '燃油加油机', '090115', 'jsk-45F1121A', '-2', '', '2018-01-10', null, '2018-12-26', '61', '95', '138', '173', null, '25', null, '258', '1', null, null, null, null, null, '77', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '173', '2017-12-26 10:45:03', '翁旗环境保护局', '20179081', '电子', 'FA2204B', '401113025138', 'FA2204B', '-2', '', '2017-12-29', null, '2018-12-26', '16', '74', '108', '174', null, '3', '32', '14', '5', null, null, null, null, null, '78', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '174', '2017-12-26 10:47:59', '翁旗环境保护局', '20170819', '电子天平', 'FA2204', '401113025055', 'FA2204', '-2', '', '2017-12-26', null, '2018-12-26', '16', '74', '108', '174', null, '3', '32', '14', '5', null, null, null, null, null, '79', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '175', '2017-12-26 10:54:40', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', '20173004F-2', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '176', '2017-12-26 10:55:24', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', '20173005F-1', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '177', '2017-12-26 10:55:49', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', '20173005F-2', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '178', '2017-12-26 10:56:14', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', '20173006F-1', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '179', '2017-12-26 10:56:40', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', '20173006F-2', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '180', '2017-12-26 10:57:07', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', 'R20173007-1', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '181', '2017-12-26 10:57:37', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', 'R20173007-2', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, null, '1', null, null, null, null, null, '80', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '182', '2017-12-26 11:15:26', '加油站', 'TB-3222', '燃油加油机', '燃油加油机', '20173004F-1', 'TB-3222', '-2', '', '2018-01-09', null, '2018-12-26', '61', '93', '136', '176', null, '25', null, '258', '1', null, null, null, null, null, '81', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '183', '2017-12-26 11:25:53', '加油站', 'JSK-45F1121A', '燃油加油机', '燃油加油机', '080107', 'JSK-45F1121A', '-1', '', null, null, '2018-12-26', '61', '126', '177', '173', null, '25', null, '258', '1', null, null, null, null, null, '82', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '184', '2017-12-26 11:26:39', '加油站', 'JSK-45F1121A', '燃油加油机', '燃油加油机', '080111', 'JSK-45F1121A', '-1', '', null, null, '2018-12-26', '61', '126', '177', '173', null, '25', null, '258', '1', null, null, null, null, null, '82', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '185', '2017-12-26 11:27:12', '加油站', 'JSK-45F1121A', '燃油加油机', '燃油加油机', '080111', 'JSK-45F1121A', '-1', '', null, null, '2018-12-26', '61', '126', '177', '173', null, '25', null, '258', '1', null, null, null, null, null, '82', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '186', '2017-12-26 11:27:31', '加油站', 'JSK-45F1121A', '燃油加油机', '燃油加油机', '1011129', 'JSK-45F1121A', '-1', '', null, null, '2018-12-26', '61', '126', '177', '173', null, '25', null, '258', '1', null, null, null, null, null, '82', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '187', '2017-12-26 11:27:44', '加油站', 'JSK-45F1121A', '燃油加油机', '燃油加油机', '1011014', 'JSK-45F1121A', '-1', '', null, null, '2018-12-26', '61', '126', '177', '173', null, '25', null, '258', '1', null, null, null, null, null, '82', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '188', '2017-12-26 14:41:47', '内蒙古翁牛特旗国家粮食储备库院内', '辽制00000128号', '电子汽车衡', '电子汽车衡', '20100911', 'scs-60', '0', '', '2017-12-26', null, '2018-12-26', '55', '79', '113', '178', null, '19', '189', '181', '1', null, null, null, null, null, '83', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '189', '2017-12-26 14:44:22', '内蒙古翁牛特旗国家粮食储备库院内', '冀制00000346号', '电子汽车衡', '电子汽车衡', '20090829', 'SCS-150', '0', '', '2017-12-26', null, '2018-12-26', '55', '79', '113', '180', null, '19', '190', '181', '1', null, null, null, null, null, '84', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '190', '2017-12-26 14:46:16', '内蒙古翁牛特旗国家粮食储备库', '量制鲁字01000121', '电子汽车衡', '电子汽车衡', '950809', 'SCS-QC', '0', '', '2018-01-10', null, '2018-12-26', '55', '79', '113', '181', null, '19', '189', '181', '1', null, null, null, null, null, '85', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '191', '2017-12-26 14:48:07', '内蒙古翁牛特旗国家粮食储备库大门北侧', '（2003）量制津字00000588号', '电子汽车衡', '电子汽车衡', '052478', 'SCS-100', '-2', '', '2017-12-29', null, '2018-12-26', '55', '79', '113', '196', null, '19', '187', '181', '1', null, null, null, null, null, '86', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '192', '2017-12-26 15:11:08', '车间', '翁计检字 第2017774号', '压力表', '精密压力表标准装置', '2017-4-788', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '193', '2017-12-26 15:12:15', '车间', '翁计检字 第2017775号', '压力表', '精密压力表标准装置', '2017-3-915', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '194', '2017-12-26 15:12:58', '车间', '翁计检字 第2017776号', '压力表', '精密压力表标准装置', '2017-3-955', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '195', '2017-12-26 15:14:33', '车间', '翁计检字 第2017777号', '压力表', '精密压力表标准装置', '2017-4-809', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '196', '2017-12-26 15:15:12', '车间', '翁计检字 第2017778号', '压力表', '精密压力表标准装置', '2017-4-842', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '197', '2017-12-26 15:16:00', '车间', '翁计检字 第2017779号', '压力表', '精密压力表标准装置', '2017-4-839', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '198', '2017-12-26 15:16:54', '车间', '翁计检字 第2017780号', '压力表', '精密压力表标准装置', '2017-5-851', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '199', '2017-12-26 15:18:29', '车间', '翁计检字 第2017781号', '压力表', '精密压力表标准装置', '2017-4-849', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '200', '2017-12-26 15:19:05', '车间', '翁计检字 第2017782号', '压力表', '精密压力表标准装置', '2017-1-651', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '201', '2017-12-26 15:20:11', '车间', '翁计检字 第2017783号', '压力表', '精密压力表标准装置', '2016-8-458', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '202', '2017-12-26 15:21:06', '车间', '翁计检字 第2017498号', '压力表', '精密压力表标准装置', '2011-9-3230', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '203', '2017-12-26 15:22:06', '车间', '翁计检字 第2017497号', '压力表', '精密压力表标准装置', '2011-11-7037', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '204', '2017-12-26 15:22:41', '车间', '翁计检字 第2017496号', '压力表', '精密压力表标准装置', '2013-9-1874', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '205', '2017-12-26 15:23:24', '车间', '翁计检字 第2017499号', '压力表', '精密压力表标准装置', '2013-10-1042', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '206', '2017-12-26 15:23:56', '车间', '翁计检字 第2017500号', '压力表', '精密压力表标准装置', '2013-3-1215', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '207', '2017-12-26 15:24:34', '车间', '翁计检字 第2017501号', '压力表', '精密压力表标准装置', '2017-4-4771', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '208', '2017-12-26 15:25:01', '车间', '翁计检字 第2017502号', '压力表', '精密压力表标准装置', '2017-4-765', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '209', '2017-12-26 15:25:48', '车间', '翁计检字 第2017503号', '压力表', '精密压力表标准装置', '2013-9-1421', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '210', '2017-12-26 15:26:12', '车间', '翁计检字 第2017504号', '压力表', '精密压力表标准装置', '2013-9-1856', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '211', '2017-12-26 15:26:46', '车间', '翁计检字 第2017505号', '压力表', '精密压力表标准装置', '2014-5-434', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '212', '2017-12-26 15:27:12', '车间', '翁计检字 第2017506号', '压力表', '精密压力表标准装置', '2017-4-752', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '213', '2017-12-26 15:27:57', '车间', '翁计检字 第2017507号', '压力表', '精密压力表标准装置', '2017-4-826', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '214', '2017-12-26 15:28:32', '车间', '翁计检字 第2017508号', '压力表', '精密压力表标准装置', '2017-4-754', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '215', '2017-12-26 15:29:25', '车间', '翁计检字 第2017509号', '压力表', '精密压力表标准装置', 'L2010-08-734', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '216', '2017-12-26 15:30:36', '车间', '翁计检字 第2017510号', '压力表', '精密压力表标准装置', 'L2010-08-745', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '217', '2017-12-26 15:31:23', '车间', '翁计检字 第2017511号', '压力表', '精密压力表标准装置', 'L2010-08-746', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '218', '2017-12-26 15:32:02', '车间', '翁计检字 第2017512号', '压力表', '精密压力表标准装置', '2010-08-626', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '219', '2017-12-26 15:36:29', '车间', '翁计检字 第2017513号', '压力表', '精密压力表标准装置', '2010-08-616', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '220', '2017-12-26 15:37:01', '车间', '翁计检字 第2017514号', '压力表', '精密压力表标准装置', '140921748', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '221', '2017-12-26 15:37:50', '车间', '翁计检字 第2017515号', '压力表', '精密压力表标准装置', '140921672', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '222', '2017-12-26 15:40:27', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051154', 'C932J1110F', '-2', '', '2018-01-10', null, '2018-12-26', '61', '131', '186', '59', null, '25', null, '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '223', '2017-12-26 15:41:01', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051121', 'C932J1110F', '-2', '', '2018-01-10', null, '2018-12-26', '61', '131', '186', '59', null, '25', null, '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '224', '2017-12-26 15:41:07', '车间', '翁计检字 第2017516号', '耐震压力表', '精密压力表标准装置', '14087242', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '225', '2017-12-26 15:41:36', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051138', 'C932J1110F', '-2', '', '2018-01-10', null, '2018-12-26', '61', '131', '186', '59', null, '25', null, '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '226', '2017-12-26 15:41:59', '车间', '翁计检字 第2017517号', '耐震压力表', '精密压力表标准装置', '2013-11-327', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '67', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '227', '2017-12-26 15:42:06', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051319', 'C932J1110F', '-2', '', '2018-01-10', null, '2018-12-26', '61', '131', '186', '59', null, '25', null, '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '228', '2017-12-26 15:43:28', '车间', '翁计检字 第2017517号', '压力表', '精密压力表标准装置', '2014-2-132', '0-1.6MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '67', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '229', '2017-12-26 15:45:38', '车间', '翁计检字 第2017518号', '压力表', '精密压力表标准装置', '2014-2-132', '0-2.54MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '230', '2017-12-26 15:46:25', '车间', '翁计检字 第2017519号', '压力表', '精密压力表标准装置', '2013-11-329', '0-2.5MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '165', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '231', '2017-12-26 15:47:54', '供应室', '鲁制03000011', '压力表', '压力表', '20130409609', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '407', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '232', '2017-12-26 15:48:01', '车间', '翁计检字 第2017520号', '压力表', '精密压力表标准装置', 'N-09-1233', '0-2.5MPa', '-1', '', null, null, '2018-12-26', '17', '86', '129', '125', null, '4', '69', '54', '4', null, null, null, null, null, '87', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '233', '2017-12-26 15:50:41', '供应室', '鲁制03000011', '压力表', '内室压力表', '20140702780', 'YZ-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '406', '405', '4', null, null, null, null, null, '89', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '234', '2017-12-26 15:50:55', '供应室', '鲁制03000011', '压力表', '内室压力表', '20140605270', 'YZ-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '406', '405', '4', null, null, null, null, null, '89', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '235', '2017-12-26 15:51:08', '供应室', '鲁制03000011', '压力表', '内室压力表', '20140605248', 'YZ-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '406', '405', '4', null, null, null, null, null, '89', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '236', '2017-12-26 15:51:30', '供应室', '鲁制03000011', '压力表', '夹层压力表', '20140604869', 'YZ-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '406', '405', '4', null, null, null, null, null, '89', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '237', '2017-12-26 15:51:49', '供应室', '鲁制03000011', '压力表', '夹层压力表', '20140413758', 'YZ-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '406', '405', '4', null, null, null, null, null, '89', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '238', '2017-12-26 15:52:16', '供应室', '鲁制03000011', '压力表', '内室压力表', '20171200148', 'YZ-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '406', '405', '4', null, null, null, null, null, '89', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '239', '2017-12-26 15:53:29', '供应室', '鲁制03000011', '压力表', '压力表', '20130614004', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '187', null, '35', '398', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '240', '2017-12-26 15:55:25', '高压氧仓', '津制00000487', '压力表', '压力表', 'T12102916', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '189', null, '35', '396', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '241', '2017-12-26 15:55:44', '高压氧仓', '津制00000487', '压力表', '压力表', 'T12102897', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '189', null, '35', '396', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '242', '2017-12-26 15:56:16', '高压氧仓', '津制00000487', '压力表', '压力表', 'T120832397', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '189', null, '35', '411', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '243', '2017-12-26 15:56:36', '高压氧仓', '津制00000487', '压力表', '压力表', 'T12102979', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '189', null, '35', '411', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '244', '2017-12-26 15:57:09', '车间、罐区', '4888', '点型可燃气体探测器', '点型可燃气体探测器', '489', '4888', '-1', '', null, null, '2018-12-26', '236', '72', '106', '192', null, '108', null, null, '4', null, null, null, null, null, '90', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '245', '2017-12-26 15:57:24', '车间、罐区', '4888', '点型可燃气体探测器', '点型可燃气体探测器', '484', '4888', '-1', '', null, null, '2018-12-26', '236', '72', '106', '192', null, '108', null, null, '4', null, null, null, null, null, '90', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '246', '2017-12-26 16:00:26', '高压氧仓', '沪制02290087', '压力表', '压力表', '12.4.0030', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '193', null, '35', '411', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '247', '2017-12-26 16:01:05', '高压氧仓', '沪制02290087', '压力表', '压力表', '12.8.0018', 'Y-150', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '193', null, '35', '396', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '248', '2017-12-26 16:01:20', '高压氧仓', '沪制02290087', '压力表', '压力表', '12.8.0021', 'Y-150', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '193', null, '35', '396', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '249', '2017-12-26 16:01:34', '高压氧仓', '沪制02290087', '压力表', '压力表', '12.5.0248', 'Y-150', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '193', null, '35', '396', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '250', '2017-12-26 16:04:44', '氧气储槽', '渝制00000612', '压力表', '氧气压力表', '100723E01', 'YB-150', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '171', null, '35', '407', '395', '4', null, null, null, null, null, '89', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '251', '2017-12-26 16:11:52', '卫生站内', '3557171', '血压计', '台式血压计', '3557171', '0-40', '-2', '', '2017-12-29', null, '2018-12-26', '113', '135', '194', '197', null, '39', '460', '459', '2', null, null, null, null, null, '91', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '252', '2017-12-26 16:20:01', '卫生院内', '500G', '吊秤', '吊秤', 'se51415456', '500', '-2', '', '2018-01-10', null, '2018-12-26', '57', '135', '194', '198', null, '21', null, '223', '1', null, null, null, null, null, '92', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '253', '2017-12-26 16:38:48', '诊所', '22406602', '血压计', '血压计', '22406602', '22406602', '0', '', '2017-12-29', null, '2018-12-26', '113', '151', '214', '223', null, '39', '460', '459', '2', null, null, null, null, null, '93', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '254', '2017-12-26 16:40:50', '诊所', '222226050', '血压计', '血压计', '22226050', '22226050', '0', '', '2017-12-29', null, '2018-12-26', '113', '151', '214', '223', null, '39', '460', '459', '2', null, null, null, null, null, '93', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '256', '2017-12-26 17:15:57', '高压氧仓', '鲁制02000113', '压力表', '压力表', '11122012', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '242', null, '35', '411', '395', '4', null, null, null, null, null, '95', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '257', '2017-12-26 17:16:09', '高压氧仓', '鲁制02000113', '压力表', '压力表', '11122005', 'Y-100', '0', '', '2017-12-27', null, '2018-12-26', '105', '91', '134', '242', null, '35', '411', '395', '4', null, null, null, null, null, '95', '26');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '258', '2017-12-26 17:19:52', '高压氧仓', '苏制04000188', '压力表', '精密压力表', '12121163', 'YB-150', '0', '', '2017-12-27', null, '2018-12-26', '253', '91', '134', '243', null, '35', '411', '395', '4', null, null, null, null, null, '96', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '259', '2017-12-26 17:20:40', '高压氧仓', '晋制03000121', '压力表', '精密压力表', 'J11013016', 'YB-150', '0', '', '2017-12-27', null, '2018-12-26', '253', '91', '134', '244', null, '35', '411', '395', '4', null, null, null, null, null, '96', '27');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '260', '2017-12-27 09:33:28', '翁牛特旗医院', '苏食药监械（准）2007第2200607号', '血压计', '血压计', '10028020', '鱼跃', '-1', '', null, null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '97', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '261', '2017-12-27 09:35:57', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '16816998', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '262', '2017-12-27 09:36:16', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '13651813', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '263', '2017-12-27 09:36:29', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '19203550', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '264', '2017-12-27 09:36:40', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '7490296', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '265', '2017-12-27 09:36:53', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '15347809', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '266', '2017-12-27 09:37:03', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '16100823', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '267', '2017-12-27 09:37:13', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '15389202', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '268', '2017-12-27 09:37:23', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '14315692', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '269', '2017-12-27 09:37:35', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '/', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '270', '2017-12-27 09:37:48', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '9490522', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '271', '2017-12-27 09:38:04', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17720487', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '272', '2017-12-27 09:38:21', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '1867716', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '273', '2017-12-27 09:38:37', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '19203146', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '274', '2017-12-27 09:39:03', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17763775', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '275', '2017-12-27 09:39:18', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '11490199', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '276', '2017-12-27 09:39:53', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '10563460', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '277', '2017-12-27 09:40:11', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17764283', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '278', '2017-12-27 09:40:23', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '16287573', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '279', '2017-12-27 09:40:47', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '20979266', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '280', '2017-12-27 09:41:02', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '18677148', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '281', '2017-12-27 09:41:14', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '18677144', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '282', '2017-12-27 09:41:31', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '18677114', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '283', '2017-12-27 09:41:51', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '8136536', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '284', '2017-12-27 09:42:03', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '16572436', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '285', '2017-12-27 09:42:17', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17055991', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '286', '2017-12-27 09:42:30', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17055963', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '287', '2017-12-27 09:42:39', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '15388722', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '288', '2017-12-27 09:43:00', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '003618', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '289', '2017-12-27 09:43:13', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17055993', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '290', '2017-12-27 09:43:22', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17056029', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '291', '2017-12-27 09:43:33', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17764341', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '292', '2017-12-27 09:44:00', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '8394821', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '293', '2017-12-27 09:44:11', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '14315452', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '294', '2017-12-27 09:44:20', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '9490325', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '295', '2017-12-27 09:44:33', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '11490823', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '296', '2017-12-27 09:44:45', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '9512075', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '297', '2017-12-27 09:44:59', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '9512072', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '298', '2017-12-27 09:45:07', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '/', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '299', '2017-12-27 09:45:17', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '11540442', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '300', '2017-12-27 09:45:29', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '15389195', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '301', '2017-12-27 09:45:39', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '15389204', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '302', '2017-12-27 09:45:49', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '18677096', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '303', '2017-12-27 09:46:01', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '15291254', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '304', '2017-12-27 09:46:10', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17763771', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '305', '2017-12-27 09:46:21', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '19203544', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '306', '2017-12-27 09:46:31', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '19267330', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '307', '2017-12-27 09:46:42', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '17763683', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '308', '2017-12-27 09:47:16', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '16811945', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '309', '2017-12-27 09:47:26', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '8366910', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '310', '2017-12-27 09:47:36', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '12479923', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '311', '2017-12-27 09:47:46', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '6194104', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '312', '2017-12-27 09:47:58', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '19203482', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '313', '2017-12-27 09:48:11', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '16808073', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '314', '2017-12-27 09:48:20', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '9871028', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '315', '2017-12-27 09:48:34', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '105832375', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '316', '2017-12-27 09:48:44', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '7486820', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '317', '2017-12-27 09:48:55', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '539787', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '318', '2017-12-27 09:49:28', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '539752', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '247', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '319', '2017-12-27 09:50:00', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '009361', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '320', '2017-12-27 09:50:10', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '012768', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '321', '2017-12-27 09:50:19', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '095248', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '322', '2017-12-27 09:50:29', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '2008614', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '323', '2017-12-27 09:50:38', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '2104469', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '324', '2017-12-27 09:50:54', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '045362', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '325', '2017-12-27 09:51:02', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '000776', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '326', '2017-12-27 09:51:08', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '/', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '327', '2017-12-27 09:51:18', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '191784', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '328', '2017-12-27 09:51:25', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '/', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '329', '2017-12-27 09:51:34', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '0124257', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '330', '2017-12-27 09:51:44', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '014925', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '331', '2017-12-27 09:51:53', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '03191', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '332', '2017-12-27 09:52:04', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '008647', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '333', '2017-12-27 09:52:12', '院内', '苏食药监械（准）字2007第2200607号', '血压计', '血压计', '/', '鱼跃', '-2', '', '2017-12-29', null, '2018-12-27', '113', '88', '131', '223', null, '39', '460', '459', '2', null, null, null, null, null, '98', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '334', '2017-12-27 10:08:08', '赤峰五佳铸诚公司', '0', '水泥混凝土搅拌站', '三一重工股份有限公司', '09HL01200287', 'HZS120型', '-2', '', '2017-12-29', null, '2018-12-27', '56', '146', '209', '249', null, '20', '192', '209', '1', null, null, null, null, null, '99', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '335', '2017-12-27 10:08:51', '赤峰五佳铸诚公司', '0', '水泥混凝土搅拌站', '三一重工股份有限公司', '10HL01200181', 'HZS120型', '-2', '', '2017-12-29', null, '2018-12-27', '56', '146', '209', '249', null, '20', '192', '209', '1', null, null, null, null, null, '99', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '336', '2017-12-27 10:14:48', '赤峰五佳铸诚公司', '0', '电子天平', '福州华志科学仪器有限公司', '09361', 'HZF-B1200', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '250', null, '3', '52', '14', '1', null, null, null, null, null, '100', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '337', '2017-12-27 10:21:17', '赤峰五佳铸诚公司', '0', '电子计价秤', '上海永杰衡器有限公司', '627', 'TCS-100', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '251', null, '3', '48', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '338', '2017-12-27 10:23:20', '赤峰五佳铸诚公司', '0', '电子天平', '上海舜宇恒丰科学仪器有限公司', 'SHP02200469566', 'FA2004', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '252', null, '3', '32', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '339', '2017-12-27 10:24:50', '赤峰五佳铸诚公司', '0', '电子天平', '福州华志科学仪器有限公司', '09397', 'HZF-A2000', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '250', null, '3', '26', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '340', '2017-12-27 10:26:18', '赤峰五佳铸诚公司', '0', '电子天平', '常熟市双杰测试仪器厂', '411009060193', 'HZF-A2000', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '253', null, '3', '16', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '341', '2017-12-27 10:27:10', '赤峰五佳铸诚公司', '0', '电子天平', '常熟市双杰测试仪器厂', '411009060193', 'TC10K-H', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '253', null, '3', '16', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '342', '2017-12-27 10:29:09', '赤峰五佳铸诚公司', '0', '电子天平', '天津市天马仪器厂', '234100015', 'TD31001', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '254', null, '3', '52', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '343', '2017-12-27 10:31:20', '赤峰五佳铸诚公司', '0', '电子台秤', '武义奥利亚电子有限公司', '201625', 'FCS-T272-100', '0', '', '2017-12-29', null, '2018-12-27', '15', '146', '209', '255', null, '3', '17', '14', '1', null, null, null, null, null, '101', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '344', '2017-12-27 13:37:39', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.6604', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '102', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '345', '2017-12-27 13:39:50', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.7227', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '103', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '346', '2017-12-27 13:42:00', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.6688', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '104', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '347', '2017-12-27 13:43:46', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.7015', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '105', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '348', '2017-12-27 13:45:09', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.7024', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '106', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '349', '2017-12-27 13:48:51', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.7101', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '107', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '350', '2017-12-27 13:50:43', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.7282', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '108', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '351', '2017-12-27 13:51:56', '锅炉', '鲁制02000105号', '压力表', '压力表', 'QL.07.7075', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '125', null, '35', '407', '395', '4', null, null, null, null, null, '109', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '352', '2017-12-27 13:53:30', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181007', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '110', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '353', '2017-12-27 13:54:53', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181023', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '111', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '354', '2017-12-27 13:58:34', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181005', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '355', '2017-12-27 14:00:35', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181024', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '356', '2017-12-27 14:03:39', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181030', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '357', '2017-12-27 14:04:40', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181025', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '358', '2017-12-27 14:05:50', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181003', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '359', '2017-12-27 14:08:54', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181034', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '360', '2017-12-27 14:09:58', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181017', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '361', '2017-12-27 14:13:20', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181019', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '362', '2017-12-27 14:15:47', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181009', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '363', '2017-12-27 14:17:12', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181410', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '364', '2017-12-27 14:18:25', '锅炉', '量制沪字02200103', '压力表', '压力表', 'C07181029', 'Y100', '0', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '306', null, '35', '407', '395', '4', null, null, null, null, null, '112', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '365', '2017-12-27 15:34:19', '超市门前', '冀制00000179号', '地秤', '地秤', '100709', 'SOS-150', '0', '', '2017-12-29', null, '2018-12-27', '55', '231', '307', '308', null, '19', '190', '181', '1', null, null, null, null, null, '113', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '366', '2017-12-27 15:59:38', '翁牛特旗妇幼保健所心电图室', '粤食药监械生产许20010081', '心电图机', '心电图工作站', '409030537', 'ECG-2000', '0', '', '2017-12-29', null, '2018-12-27', '146', '89', '132', '336', null, '49', '522', '522', '2', null, null, null, null, null, '114', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '367', '2017-12-27 16:05:54', '包装车间', 'XY2015060611', '电子秤', '电子秤', 'XY2015060611', 'CS-50', '-2', '', '2018-01-10', null, '2018-12-27', null, '260', '338', '348', null, '20', '208', '209', '1', null, null, null, null, null, '115', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '368', '2017-12-27 16:27:06', '卫生院', '16601143', '血压计', '血压计', '16601143', 'Y12B苏609-2007', '0', '', '2017-12-29', null, '2018-12-27', '113', '273', '352', '223', null, '39', '460', '459', '2', null, null, null, null, null, '116', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '369', '2017-12-27 16:27:51', '卫生院', '1958451', '血压计', '血压计', '1958451', 'Y12B苏609-2007', '0', '', '2017-12-29', null, '2018-12-27', '113', '273', '352', '223', null, '39', '460', '459', '2', null, null, null, null, null, '116', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '370', '2017-12-27 16:34:26', '制冷车间', '辽制00000286', '压力表', '氧压表', '2222', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '371', '2017-12-27 16:35:36', '翁牛特旗虹霖再生资源回收利用有限公司', '2017481', '压力表', '沈阳市特种压力表', '2016-7-1317', '0-1.6mpa', '-1', '', null, null, '2018-12-27', '42', '78', '112', '165', null, '15', '134', '136', null, null, null, null, null, null, '118', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '372', '2017-12-27 16:35:39', '制冷车间', '辽制00000286', '压力表', '氧压表', '2224', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '373', '2017-12-27 16:36:10', '翁牛特旗虹霖再生资源回收利用有限公司', '2017481', '压力表', '沈阳市特种压力表', '2016-7-1317', '0-1.6mpa', '-1', '', null, null, '2018-12-27', '42', '78', '112', '165', null, '15', '134', '136', null, null, null, null, null, null, '118', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '374', '2017-12-27 16:37:04', '制冷车间', '辽制00000286', '压力表', '氧压表', '2225', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '375', '2017-12-27 16:38:08', '制冷车间', '辽制00000286', '压力表', '氧压表', '2247', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '376', '2017-12-27 16:41:36', '锅炉车间', 'PL.1.7913', '压力表', '压力表', 'PL.1.7913', '0-1.6MP', '0', '', '2017-12-29', null, '2018-12-27', '259', '274', '354', '97', null, '4', '71', '54', '4', null, null, null, null, null, '119', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '377', '2017-12-27 16:42:34', '锅炉车间', 'PL.11.2443', '压力表', '压力表', 'PL11.2443', '0-1.6MP', '0', '', '2017-12-29', null, '2018-12-27', '259', '274', '354', '97', null, '4', '71', '54', '4', null, null, null, null, null, '119', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '378', '2017-12-27 16:43:58', '制冷车间', '辽制00000286', '压力表', '氧压表', '2255', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '379', '2017-12-27 16:45:29', '锅炉', 'CMC1546', '压力表', '压力表', 'CM1568', 'Y-100', '-1', '', null, null, '2018-12-27', '105', '57', '72', '71', null, '35', '407', '395', '4', null, null, null, null, null, '120', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '380', '2017-12-27 16:46:43', '锅炉', '浙制03820214', '压力表', '压力表', '2507', 'Y0-150', '-1', '', null, null, '2018-12-27', '105', '83', '126', '71', null, '35', '407', '395', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '381', '2017-12-27 16:48:48', '锅炉', '浙制03820214', '压力表', '压力表', '4814', 'Y0-150', '-1', '', null, null, '2018-12-27', '105', '83', '126', '71', null, '35', '407', '395', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '382', '2017-12-27 16:49:29', '锅炉', '浙制03820214', '压力表', '压力表', '8929', 'Y0-150', '-1', '', null, null, '2018-12-27', '105', '83', '126', '71', null, '35', '407', '395', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '383', '2017-12-27 16:51:11', '制冷车间', '辽制00000286', '压力表', '氧压表', 'NO', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '384', '2017-12-27 16:51:44', '厂房', 'HC675912238888', '氧压力表', '氧气表', 'HC675912238888', 'HC675912238888', '-1', '', null, null, '2018-12-27', '259', '275', '355', '71', null, '4', '58', '54', '4', null, null, null, null, null, '121', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '385', '2017-12-27 16:53:11', '供应室', '/', '压力真空表', '压力真空表', '14-04-144', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '356', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '386', '2017-12-27 16:53:34', '供应室', '/', '压力真空表', '压力真空表', '14-04-145', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '356', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '387', '2017-12-27 16:53:46', '供应室', '/', '压力真空表', '压力真空表', '14-04-169', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '356', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '388', '2017-12-27 16:53:57', '供应室', '/', '压力真空表', '压力真空表', '14-04-178', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '356', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '389', '2017-12-27 16:54:10', '制冷车间', '辽制00000286', '压力表', '氧压表', 'NO', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '390', '2017-12-27 16:55:20', '供应室', '/', '压力真空表', '压力真空表', '14-04-187', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '356', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '391', '2017-12-27 16:55:22', '制冷车间', '辽制00000286', '压力表', '氧压表', 'NO', 'Y0-100', '-1', '', null, null, '2018-12-27', '105', '83', '126', '353', null, '35', '414', '405', '5', null, null, null, null, null, '117', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '392', '2017-12-27 16:55:40', '供应室', '/', '压力真空表', '压力真空表', '14-04-175', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '356', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '393', '2017-12-27 16:56:41', '供应室', '/', '压力真空表', '压力真空表', 'YA06079461', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '357', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '394', '2017-12-27 16:57:01', '供应室', '/', '压力真空表', '压力真空表', 'YA06079639', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '357', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '395', '2017-12-27 16:57:15', '供应室', '/', '压力真空表', '压力真空表', 'YA06052034', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '357', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '396', '2017-12-27 16:57:40', '供应室', '/', '压力真空表', '压力真空表', 'YA06079633', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '357', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '397', '2017-12-27 16:57:57', '供应室', '/', '压力真空表', '压力真空表', 'YA06079635', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '357', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '398', '2017-12-27 16:58:10', '供应室', '/', '压力真空表', '压力真空表', 'YA06079640', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '17', '88', '131', '357', null, '4', '70', '55', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '399', '2017-12-27 16:59:10', '供应室', '/', '压力真空表', '压力真空表', '/', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '104', '88', '131', '357', null, '35', '407', '395', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '400', '2017-12-27 16:59:18', '供应室', '/', '压力真空表', '压力真空表', '/', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '104', '88', '131', '357', null, '35', '407', '395', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '401', '2017-12-27 16:59:57', '供应室', '/', '压力真空表', '压力真空表', '14-3-1030', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '104', '88', '131', '358', null, '35', '407', '395', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '402', '2017-12-27 17:00:07', '供应室', '/', '压力真空表', '压力真空表', '14-3-1020', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '104', '88', '131', '358', null, '35', '407', '395', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '403', '2017-12-27 17:00:56', '供应室', '/', '压力真空表', '压力真空表', '090323j03', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '104', '88', '131', '171', null, '35', '407', '395', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '404', '2017-12-27 17:01:24', '供应室', '/', '压力真空表', '压力真空表', 'K.10.0934', '-0.1-0.5Mpa', '0', '', '2017-12-29', null, '2018-12-27', '104', '88', '131', '125', null, '35', '407', '395', '4', null, null, null, null, null, '122', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '405', '2017-12-27 17:07:04', '彩超室', '/', '医用超声诊断仪', '医用超声诊断仪', 'A-2-009', 'SONOACE', '-2', '', '2018-01-10', null, '2018-12-27', '234', '88', '131', '359', null, '106', null, null, '2', null, null, null, null, null, '123', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '406', '2017-12-27 17:07:55', '彩超室', '/', '医用超声诊断仪', '医用超声诊断仪', '20931090210064', 'VOLVSON730', '-2', '', '2018-01-10', null, '2018-12-27', '234', '88', '131', '360', null, '106', null, null, '2', null, null, null, null, null, '123', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '407', '2017-12-27 17:09:11', '彩超室', '/', '医用超声诊断仪', '医用超声诊断仪', 'KE13675110', 'HI VISION', '-2', '', '2018-01-10', null, '2018-12-27', '234', '88', '131', '361', null, '106', null, null, '2', null, null, null, null, null, '123', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '408', '2017-12-27 17:10:08', '彩超室', '/', '医用超声诊断仪', '医用超声诊断仪', 'US40833881', 'HD11', '-2', '', '2018-01-10', null, '2018-12-27', '234', '88', '131', '362', null, '106', null, null, '2', null, null, null, null, null, '123', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '409', '2017-12-27 18:16:40', '锅炉', '12810126', '压力表', '压力表', '170505813', 'Y100', '-2', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '100', null, '35', '396', '395', '4', null, null, null, null, null, '124', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '410', '2017-12-27 18:25:24', '锅炉', '12810126', '压力表', '压力表', '160401619', 'Y100', '-2', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '100', null, '35', '407', '395', '4', null, null, null, null, null, '124', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '411', '2017-12-27 18:31:33', '锅炉', '12810126', '压力表', '压力表', '170505851', 'Y100', '-2', '', '2017-12-29', null, '2018-12-27', '105', '76', '110', '100', null, '35', '396', '395', '4', null, null, null, null, null, '124', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '412', '2017-12-28 08:03:28', '放射线', '/', '齿科全景', '齿科全景摄影系统', 'XC4326545', 'PLOLINEXC', '-2', '', '2018-01-10', null, '2018-12-28', '148', '88', '131', '363', null, '51', null, null, null, null, null, null, null, null, '125', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '413', '2017-12-28 08:05:17', '放射线', '/', '64排CT', '医用计算机断层摄影装置', '66091', 'Definition AS', '-2', '', '2018-01-10', null, '2018-12-28', '148', '88', '131', '364', null, '51', null, null, '2', null, null, null, null, null, '125', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '414', '2017-12-28 08:06:35', 'CT室', '/', '单排螺旋CT', '医用计算机断层摄影装置', '41335HM1', 'CT/e', '-2', '', '2018-01-10', null, '2018-12-28', '148', '88', '131', '365', null, '51', null, null, '2', null, null, null, null, null, '125', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '415', '2017-12-28 08:07:38', '放射线', '/', 'DR', 'DR', '20071207069', 'ZK-DR', '-2', '', '2018-01-10', null, '2018-12-28', '148', '88', '131', '366', null, '51', null, null, '2', null, null, null, null, null, '125', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '416', '2017-12-28 08:08:23', '放射线', '/', 'DR', 'DR', 'Ros07100', '飞天6000', '-2', '', '2018-01-10', null, '2018-12-28', '148', '88', '131', '360', null, '51', null, null, '2', null, null, null, null, null, '125', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '417', '2017-12-28 08:13:16', '心内科', '/', '心电图机', '心电图机', '13111180', 'FX-7402', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '418', '2017-12-28 08:13:47', '神经内科一病区', '/', '心电图机', '心电图机', '17040233', 'FX-7302', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '419', '2017-12-28 08:14:22', '神经内科二病区', '/', '心电图机', '心电图机', '22054847', 'FX-7020', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '420', '2017-12-28 08:14:55', '肾内科', '/', '心电图机', '心电图机', '1802318', 'FX-7202', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '421', '2017-12-28 08:15:31', '传染病', '/', '心电图机', '心电图机', '18077576', 'FX-7000', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '422', '2017-12-28 08:16:51', 'ICU', '/', '心电图机', '心电图机', '20124305', 'FX-7202', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '423', '2017-12-28 08:17:22', '心电图室', '/', '心电图机', '心电图机', '20124306', 'FX-7202', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '367', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '424', '2017-12-28 08:18:29', '120', '/', '心电图机', '心电图机', 'FK-49003872', 'R3', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '368', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '425', '2017-12-28 08:19:19', '神经内科三病区', '/', '心电图机', '心电图机', 'FK-49003865', 'R3', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '368', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '426', '2017-12-28 08:20:45', '内三科', '/', '心电图机', '心电图机', '1913120675', 'ECG-6010', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '336', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '427', '2017-12-28 08:21:09', '消化内科', '/', '心电图机', '心电图机', '1913120674', 'ECG-6010', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '336', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '428', '2017-12-28 08:21:43', '120', '/', '心电图机', '心电图机', '0113091291', 'ECG-101', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '336', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '429', '2017-12-28 08:21:54', '120', '/', '心电图机', '心电图机', '0113091234', 'ECG-101', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '336', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '430', '2017-12-28 08:23:00', '120', '/', '心电图机', '心电图机', '09020001', 'ECG-901', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '369', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '431', '2017-12-28 08:23:13', '120', '/', '心电图机', '心电图机', '08110008', 'ECG-901', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '369', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '432', '2017-12-28 08:24:41', '心电图室', '/', '心电图机', '心电分析系统', 'J5050', 'MIC-1', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '370', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '433', '2017-12-28 08:25:53', '体检中心', '/', '心电图机', '心电分析系统', '55002913', 'MAC-1200ST', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '360', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '434', '2017-12-28 08:27:04', '急诊科', '/', '心电图机', '心电分析系统', '04966', '9620P', '-2', '', '2018-01-10', null, '2018-12-28', '146', '88', '131', '371', null, '49', null, null, '2', null, null, null, null, null, '126', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '435', '2017-12-28 09:52:30', '北京', '22', '22', '22', '232', '22', '0', '', '2018-01-17', null, '2018-12-28', '7', '11', '12', '13', null, '2', '4', '2', '1', null, null, null, null, null, '127', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '436', '2017-12-28 10:30:20', '生产车间', '鲁制02000105号', '压力表', '压力表', 'PL.11.7845', 'D343H', '0', '', '2017-12-28', null, '2018-12-28', '17', '277', '372', '97', null, '4', '71', '54', '4', null, null, null, null, null, '128', '28');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '437', '2017-12-28 15:11:57', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3744', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '438', '2017-12-28 15:13:19', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3636.', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '439', '2017-12-28 15:13:54', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3524', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '440', '2017-12-28 15:14:14', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3673', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '441', '2017-12-28 15:14:32', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3615', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '442', '2017-12-28 15:14:50', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.P.09.7269', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '443', '2017-12-28 15:15:05', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3735', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '444', '2017-12-28 15:15:18', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL10.3572', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '445', '2017-12-28 15:15:30', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.PL07.4189', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '446', '2017-12-28 15:15:45', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.P .09.7234', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '447', '2017-12-28 15:16:24', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.P 10.5597.', 'YF-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '448', '2017-12-28 15:16:45', '输油平台', '鲁制02000105号', '压力表', '压力表', 'N0.N.09.0400', 'Y-100', '0', '', '2018-01-18', null, '2018-12-28', '17', '180', '248', '125', null, '4', '66', '54', '4', null, null, null, null, null, '129', '61');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '449', '2017-12-28 16:02:21', '自来水总公司一楼校验中心水表检定装置管道', 'CMC', '压力表', '压力表', '2012.10.0148', 'Y-100', '-2', '', '2017-12-29', null, '2018-12-28', '105', '278', '373', '193', null, '35', '396', '395', '4', null, null, null, null, null, '130', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '450', '2017-12-28 16:04:02', '自来水总公司五楼水表检验室制备室高压蒸汽灭菌锅', 'CMC', '压力表', '压力表', '41537', 'Y-60', '-2', '', '2017-12-29', null, '2018-12-28', '104', '278', '373', '406', null, '35', '397', '395', '4', null, null, null, null, null, '130', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '451', '2017-12-28 16:05:47', '自来水总公司一楼校验中心水表检定装置压力罐', 'CMC', '压力表', '压力表', '11.9.0486', 'Y-100', '-2', '', '2017-12-29', null, '2018-12-28', '105', '278', '373', '193', null, '35', '407', '395', '4', null, null, null, null, null, '130', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '452', '2017-12-28 16:32:08', '翁牛特旗妇幼保健所超声科', '国食药监械（进）字2007第3231935号', '医用超声诊断仪超声源', '数字化超声波诊断装置', 'KE14471910', 'EUB-6500HV', '0', '', '2017-12-29', null, '2018-12-28', '234', '89', '132', '361', null, '106', '737', '737', '2', null, null, null, null, null, '131', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '453', '2017-12-28 16:34:52', '翁牛特旗妇幼保健所超声科', '国食药监械（准）字2009第3230388号', '医用超声诊断仪超声源', '超声诊断系统', 'CD54120136', 'DH7', '0', '', '2017-12-29', null, '2018-12-28', '234', '89', '132', '407', null, '106', '737', '737', '2', null, null, null, null, null, '131', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '454', '2017-12-28 16:37:54', '翁牛特旗妇幼保健所X线科', '北京万东', '医用诊断X射线辐射源', 'X光机', '1204006', 'XD52-30.50/125H-T2F', '0', '', '2017-12-29', null, '2018-12-28', '148', '89', '132', '408', null, '51', '524', '524', '2', null, null, null, null, null, '131', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '455', '2017-12-28 17:09:12', '检验科', '0', '精密PH计', '精密PH计', '2', 'PHS-3C', '0', '', '2018-01-02', null, '2018-12-28', '174', '90', '133', '409', null, '70', '571', '570', '2', null, null, null, null, null, '132', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '456', '2017-12-28 17:11:36', '翁牛特旗妇幼保健所超声科', '国械注进20152231495', '医用超声诊断仪超声源', '超声诊断仪', '082437060651', 'VolusonE8', '-2', '', '2017-12-29', null, '2018-12-28', '234', '89', '132', '365', null, '106', '737', '737', '2', null, null, null, null, null, '133', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '457', '2017-12-28 19:41:40', '第二养殖场', '浙制00000577号', '电子汽车衡', '电子汽车衡', '140540', 'SCS-80型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '410', null, '19', '184', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '458', '2017-12-28 19:41:56', '1#泵池', '沪制02220048', '压力表', '不锈钢压力表', '1F0509284', '2.5级', '0', '', '2017-12-29', null, '2018-12-28', '104', '310', '405', '411', null, '35', '397', '395', '4', null, null, null, null, null, '135', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '459', '2017-12-28 19:42:43', '1#泵池出口', '沪制02220048', '压力表', '不锈钢压力表', '1F0509312', '2.5级', '0', '', '2017-12-29', null, '2018-12-28', '104', '310', '405', '411', null, '35', '397', '395', '4', null, null, null, null, null, '135', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '460', '2017-12-28 19:43:02', '2#泵池出口', '沪制02220048', '压力表', '不锈钢压力表', '1F0509303', '2.5级', '0', '', '2017-12-29', null, '2018-12-28', '104', '310', '405', '411', null, '35', '397', '395', '4', null, null, null, null, null, '135', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '461', '2017-12-28 19:43:10', '第一养殖场', '浙制00000577号', '电子汽车衡', '电子汽车衡', '140541', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '410', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '462', '2017-12-28 19:43:50', '2#泵池', '沪制02220048', '压力表', '不锈钢压力表', '126940', '2.5级', '0', '', '2017-12-29', null, '2018-12-28', '104', '310', '405', '411', null, '35', '397', '395', '4', null, null, null, null, null, '135', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '463', '2017-12-28 19:44:17', '第三养殖场', '浙制00000577号', '电子汽车衡', '电子汽车衡', '140542', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '410', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '464', '2017-12-28 19:45:33', '第四养殖场', '浙制00000577号', '电子汽车衡', '电子汽车衡', '140543', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '410', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '465', '2017-12-28 19:51:53', '第五养殖场', '（苏）制00000070号', '电子汽车衡', '电子汽车衡', 'Q013773-6KS', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '412', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '466', '2017-12-28 19:55:18', '第十养殖场', '（苏）制00000070号', '电子汽车衡', '电子汽车衡', 'Q013779-6KS', 'SCS-80型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '412', null, '19', '184', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '467', '2017-12-28 19:56:59', '第六养殖场', '（苏）制00000070号', '电子汽车衡', '电子汽车衡', 'Q01335-6KS', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '412', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '468', '2017-12-28 19:58:12', '第九养殖场', '（苏）制00000070号', '电子汽车衡', '电子汽车衡', 'Q01375-6KS', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '412', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '469', '2017-12-28 19:59:34', '第十一养殖场', '（苏）制00000070号', '电子汽车衡', '电子汽车衡', 'Q01374-6KS', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '412', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '470', '2017-12-28 20:01:00', '第十二养殖场', '（苏）制00000070号', '电子汽车衡', '电子汽车衡', 'Q013728-6KS', 'SCS-50型', '0', '', '2017-12-29', null, '2018-12-28', '55', '76', '110', '412', null, '19', '191', '181', '1', null, null, null, null, null, '134', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '471', '2017-12-28 22:01:12', '内蒙古赤峰市翁旗桥头镇北段中国石油翁旗桥头加油站', '2017450', '税控燃油加油机', 'CS46J2220G', 'WCDX900239', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '168', null, '25', '264', '261', '1', null, null, null, null, null, '136', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '472', '2017-12-28 22:02:17', '内蒙古赤峰市翁旗桥头镇北段中国石油翁旗桥头加油站', '2017602', '税控燃油加油机', 'CS46J2220G', 'WCDX900258', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '168', null, '25', '264', '261', '1', null, null, null, null, null, '136', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '473', '2017-12-28 22:02:49', '内蒙古赤峰市翁旗桥头镇北段中国石油翁旗桥头加油站', '2017601', '税控燃油加油机', 'CS46J2220G', 'WCDX900250', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '168', null, '25', '264', '261', '1', null, null, null, null, null, '136', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '474', '2017-12-28 22:08:35', '内蒙古赤峰市翁旗桥头镇北段中国石油翁旗桥头加油站', '2017449', '税控燃油加油机', 'IC卡税控燃油加油机', 'SA01043005', 'SK52ZF111A', '0', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '168', null, '25', '264', '261', '1', null, null, null, null, null, '136', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '475', '2017-12-28 22:09:44', '内蒙古赤峰市翁旗桥头镇北段中国石油翁旗桥头加油站', '2017448', '税控燃油加油机', 'IC卡税控燃油加油机', 'SA00043007', 'SK52ZF111A', '0', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '136', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '476', '2017-12-28 22:33:21', '内蒙古赤峰市翁旗乌丹镇南中国石油黄金加油站', '2017440', '税控燃油加油机', 'IC卡税控燃油加油机', 'WFEN900004', 'CS46J1112G', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '477', '2017-12-28 22:39:11', '内蒙古赤峰市翁旗乌丹镇南中国石油黄金加油站', '2017439', '税控燃油加油机', '税控燃油加油机', 'WF8A900002', 'CS46J1112G', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '478', '2017-12-28 22:40:23', '内蒙古赤峰市翁旗乌丹镇南中国石油黄金加油站', '2017438', '税控燃油加油机', '税控燃油加油机', 'WCDX900778', 'CS46J2220G', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '479', '2017-12-28 22:44:55', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017541', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1D97A002', 'SK52GF212K', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '480', '2017-12-28 22:45:53', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017540', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1D97A002', 'SK52GF212K', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '481', '2017-12-28 22:46:38', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017539', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1C97A002', 'SK52GF212K', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '482', '2017-12-28 22:47:14', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017538', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1C97A002', 'SK52GF212K', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '483', '2017-12-28 22:48:14', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017537', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1997A002', 'SK52GF212K', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '484', '2017-12-28 22:48:45', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017536', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1997A002', 'SK52GF212K', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '485', '2017-12-28 22:50:38', '内蒙古赤峰市翁旗梧桐花镇中国石油梧桐花加油站', '2017447', '税控燃油加油机', 'IC卡税控燃油加油机', 'WFEN900008', 'CS46J1112G', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '486', '2017-12-28 22:53:10', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017497', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117090015', 'CS42D4243F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '487', '2017-12-28 22:55:13', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017498', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117090015', 'CS42D4243F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '488', '2017-12-28 22:56:21', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017494', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117090025', 'CS30D2223F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '489', '2017-12-28 22:57:04', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017495', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117090025', 'CS30D2223F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '490', '2017-12-28 22:57:59', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017496', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117090015', 'CS42D4243F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '491', '2017-12-28 22:58:30', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017499', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117090015', 'CS42D4243F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '492', '2017-12-28 22:59:09', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017493', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117090032', 'CS42D4243F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '493', '2017-12-28 23:00:58', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017492', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117090032', 'CS30D2223F', '-2', '', '2018-01-02', null, '2018-12-28', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '137', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '494', '2017-12-29 08:51:03', '翁牛特旗蒙医中医医院', '082661', '心电图机', '心电图机', 'C021102235', 'MECG-200', '-2', '', '2018-01-10', null, '2018-12-29', '146', '71', '105', '415', null, '49', null, null, '2', null, null, null, null, null, '138', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '495', '2017-12-29 09:00:27', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HC67581753127', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '496', '2017-12-29 09:01:13', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HC67581753126', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '497', '2017-12-29 09:01:40', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HC67581753125', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '498', '2017-12-29 09:02:07', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HC66531412239', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '499', '2017-12-29 09:03:27', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HC67540552606', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '500', '2017-12-29 09:03:52', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HC67581753114', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '501', '2017-12-29 09:04:51', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'NOHA665113065029', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '502', '2017-12-29 09:05:29', '五十家子镇孤榆树村林西正邦农牧有限公司', '浙制03820214', '压力表', '压力表', 'HA665113064477', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '71', null, '35', '407', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '503', '2017-12-29 09:07:05', '五十家子镇孤榆树村林西正邦农牧有限公司', '鲁制02000105', '压力表', '压力表', '8365', '0-1.6MPa', '0', '', '2017-12-29', null, '2018-12-29', '105', '311', '414', '125', null, '35', '398', '395', '4', null, null, null, null, null, '139', '29');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '504', '2017-12-29 09:20:53', '自来水总公司一楼校验中心水表检定装置压力罐', 'CMC沪制02290087号', '压力表', '压力表', '11.9.0486', 'Y-100', '-1', '', null, null, '2018-12-29', '105', '22', '24', '193', null, '35', '407', '395', '4', null, null, null, null, null, '130', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '505', '2017-12-29 09:23:03', '自来水总公司一楼校验中心水表检定装置管道', 'CMC沪制02290087号', '压力表', '压力表', '2012.10.0148', 'Y-100', '-1', '', null, null, '2018-12-29', '105', '278', '373', '193', null, '35', '396', '395', '4', null, null, null, null, null, '130', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '506', '2017-12-29 09:27:34', '自来水总公司五楼水表检验室制备室高压蒸汽灭菌锅', 'CMC沪制01070009号', '压力表', '压力表', '41537', 'Y-60', '-1', '', null, null, '2018-12-29', '104', '278', '373', '406', null, '35', '397', '395', '4', null, null, null, null, null, '130', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '507', '2017-12-29 09:35:37', '自来水总公司一楼校验中心水表检定装置压力罐', 'CMC沪制02290087号', '压力表', '压力表', '11.9.0486', 'Y-100', '0', '', '2017-12-29', null, '2018-12-29', '105', '278', '373', '193', null, '35', '407', '395', '4', null, null, null, null, null, '140', '30');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '508', '2017-12-29 09:37:43', '自来水总公司一楼校验中心水表检定装置管道', 'CMC沪制02290087号', '压力表', '压力表', '2012.10.0148', 'Y-100', '0', '', '2017-12-29', null, '2018-12-29', '105', '22', '24', '193', null, '35', '396', '395', '4', null, null, null, null, null, '140', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '509', '2017-12-29 09:39:18', '自来水总公司五楼水表检验室制备室高压蒸汽灭菌锅', 'CMC沪制01070009号', '压力表', '压力表', '41537', 'Y-60', '0', '', '2017-12-29', null, '2018-12-29', '104', '22', '24', '193', null, '35', '397', '395', '4', null, null, null, null, null, '140', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '510', '2017-12-29 10:04:37', '翁牛特旗蒙医中医医院', '国食药监械（进）字2008第3232373号', '医用超声诊断仪超声源', '医用超声诊断仪超声源', 'KE15296902', 'EUB-7500', '-2', '', '2018-01-10', null, '2018-12-29', '160', '71', '105', '361', null, '63', null, null, '2', null, null, null, null, null, '141', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '511', '2017-12-29 10:15:42', '赤峰市松山区建峰加油站', '豫制00000316号', '燃油加油机', '税控燃油加油机', '520407C001', 'SK52GF222K', '-2', '', '2017-12-29', null, '2018-12-29', '61', '222', '297', '416', null, '25', '264', '261', '1', null, null, null, null, null, '142', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '512', '2017-12-29 10:18:46', '赤峰市松山区建峰加油站', '豫制00000316号', '燃油加油机', '税控燃油加油机', '510407C001', 'SK52GF212K', '-2', '', '2017-12-29', null, '2018-12-29', '61', '222', '297', '416', null, '25', '264', '261', '1', null, null, null, null, null, '142', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '513', '2017-12-29 10:25:22', '赤峰市松山区建峰加油站', '豫制00000316号', '燃油加油机', '税控燃油加油机', '520407C001', 'SK52GF222K', '0', '', '2017-12-29', null, '2018-12-29', '61', '222', '297', '416', null, '25', '264', '261', '1', null, null, null, null, null, '143', '31');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '514', '2017-12-29 10:26:15', '赤峰市松山区建峰加油站', '豫制00000316号', '燃油加油机', '税控燃油加油机', '510407C001', 'SK52GF212K', '0', '', '2017-12-29', null, '2018-12-29', '61', '222', '297', '416', null, '25', '264', '261', '1', null, null, null, null, null, '143', '31');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '515', '2017-12-29 10:26:38', '翁牛特旗蒙医中医医院', '国药管械（进）2003第3230328', '医用超声诊断仪超声源', '医用超声诊断仪超声源', 'M000641', 'SSD-3500', '-2', '', '2018-01-10', null, '2018-12-29', '160', '71', '105', '361', null, '63', null, null, '2', null, null, null, null, null, '144', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '516', '2017-12-29 10:31:32', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017490', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117090033', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '517', '2017-12-29 10:32:52', '内蒙古赤峰市翁旗乌丹镇乌丹路南段中国石油乌丹加油站', '2017491', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117090033', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '518', '2017-12-29 10:43:10', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017372', '税控燃油加油机', 'IC卡税控燃油加油机', '097692', 'ICJSK-45H2242', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '417', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '519', '2017-12-29 10:45:29', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017535', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1C97A001', 'SK52GF212K', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '520', '2017-12-29 10:46:21', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017534', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1C97A001', 'SK52GF212K', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '521', '2017-12-29 10:47:20', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017533', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1D97A001', 'SK52GF212K', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '522', '2017-12-29 10:47:54', '翁牛特旗蒙医中医医院', '国食药监械（进）字2014第3231328', '医用超声诊断仪超声源', '全数字化彩色超声诊断仪', '20063US4', 'LOGIQ9E9', '-2', '', '2017-12-29', null, '2018-12-29', '160', '71', '105', '360', null, '63', null, null, '2', null, null, null, null, null, '146', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '523', '2017-12-29 10:48:10', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017530', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1997A001', 'SK52GF212K', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '524', '2017-12-29 10:49:07', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017531', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1997A001', 'SK52GF212K', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '525', '2017-12-29 10:50:14', '内蒙古赤峰市翁旗五分地镇北小八段村中国石油五分地加油站', '2017532', '税控燃油加油机', 'IC卡税控燃油加油机', 'YB1D97A001', 'SK52GF212K', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '413', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '526', '2017-12-29 10:54:32', '内蒙古赤峰市翁旗广德公镇巴林道村中国石油广德公加油站', '2017381', '税控燃油加油机', 'IC卡税控燃油加油机', '9610700014', 'CS30J1110G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '527', '2017-12-29 10:56:29', '内蒙古赤峰市翁旗广德公镇巴林道村中国石油广德公加油站', '2017379', '税控燃油加油机', 'IC卡税控燃油加油机', '9610700011', 'CS30J1110G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '528', '2017-12-29 10:57:22', '内蒙古赤峰市翁旗广德公镇巴林道村中国石油广德公加油站', '2017382', '税控燃油加油机', 'IC卡税控燃油加油机', '9610700006', 'CS30J1110G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '529', '2017-12-29 10:59:25', '内蒙古赤峰市翁旗广德公镇巴林道村中国石油广德公加油站', '2017378', '税控燃油加油机', 'IC卡税控燃油加油机', 'WFD2700045', 'CS46J1110G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '530', '2017-12-29 10:59:58', '内蒙古赤峰市翁旗广德公镇巴林道村中国石油广德公加油站', '2017380', '税控燃油加油机', 'IC卡税控燃油加油机', 'WFD2700044', 'CS46J1110G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '145', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '531', '2017-12-29 11:10:32', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017334', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0234203AA10111011465-2', 'CS42D4443F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '532', '2017-12-29 11:11:40', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017335', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0234203AA10111011465-1', 'CS42D4443F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '533', '2017-12-29 11:12:42', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017330', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0234303AA10111011465-3', 'CS42D4443F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '534', '2017-12-29 11:13:41', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017331', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0234203AA10111011465-4', 'CS42D4443F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '535', '2017-12-29 11:15:17', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017329', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ234203111011102801-2', 'CS46D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '536', '2017-12-29 11:16:18', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017328', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ234203111011102801-1', 'CS46D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '537', '2017-12-29 11:17:42', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017332', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0234203AA10111011463-1', 'CS42D4443F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '538', '2017-12-29 11:18:39', '内蒙古赤峰市翁旗乌丹镇玉龙路与白音他拉大街中国石油西桥加油站', '2017333', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0234203AA10111011463-2', 'CS46D4443F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '539', '2017-12-29 11:36:27', '内蒙古赤峰市翁旗大兴农场中国石油大兴加油站', '2017553', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCD2900033', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '540', '2017-12-29 11:38:40', '内蒙古赤峰市翁旗大兴农场中国石油大兴加油站', '2017602', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900265', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '541', '2017-12-29 11:40:11', '内蒙古赤峰市翁旗大兴农场中国石油大兴加油站', '2017551', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900242', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '542', '2017-12-29 11:40:51', '内蒙古赤峰市翁旗大兴农场中国石油大兴加油站', '2017552', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900034', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '147', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '543', '2017-12-29 14:02:21', '内蒙古赤峰市翁牛特旗乌丹镇全宁路中段中国石油全宁桥加油站', '2017309', '税控燃油加油机', 'IC卡税控燃油加油机', '097686', 'ICJSK-45H2242', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '417', null, '25', '264', '261', '1', null, null, null, null, null, '148', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '544', '2017-12-29 14:07:38', '内蒙古赤峰市翁牛特旗乌丹镇全宁路中段中国石油全宁桥加油站', '2017461', '税控燃油加油机', 'IC卡税控燃油加油机', 'TP117080013', 'CS30J2223G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '148', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '545', '2017-12-29 14:08:18', '内蒙古赤峰市翁牛特旗乌丹镇全宁路中段中国石油全宁桥加油站', '2017463', '税控燃油加油机', 'IC卡税控燃油加油机', 'TP117080001', 'CS30J2223G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '148', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '546', '2017-12-29 14:09:05', '内蒙古赤峰市翁牛特旗乌丹镇全宁路中段中国石油全宁桥加油站', '2017460', '税控燃油加油机', 'IC卡税控燃油加油机', 'TP117080012', 'CS30J2223G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '148', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '547', '2017-12-29 14:10:01', '内蒙古赤峰市翁牛特旗乌丹镇全宁路中段中国石油全宁桥加油站', '2017462', '税控燃油加油机', 'IC卡税控燃油加油机', 'TP117080002', 'CS30J2223G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '148', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '548', '2017-12-29 14:15:13', '1#加气机', '沪制02220048', '压力表', '不锈钢压力表', '1F0509310', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '549', '2017-12-29 14:15:34', '2#加气机', '沪制02220048', '压力表', '不锈钢压力表', '1F0509326', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '550', '2017-12-29 14:15:50', '3#加气机', '沪制02220048', '压力表', '不锈钢压力表', '1F0509279', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '551', '2017-12-29 14:16:11', '4#加气机', '沪制02220048', '压力表', '不锈钢压力表', '1F0509283', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '552', '2017-12-29 14:19:04', '1#泵池（备）', '沪制02220048', '压力表', '不锈钢压力表', '1F0509318', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '553', '2017-12-29 14:19:35', '1#泵池出口（备）', '沪制02220048', '压力表', '不锈钢压力表', '1F0509286', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '554', '2017-12-29 14:20:02', '2#泵池出口（备）', '沪制02220048', '压力表', '不锈钢压力表', '1F0509315', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '555', '2017-12-29 14:20:28', '2#泵池（备）', '沪制02220048', '压力表', '不锈钢压力表', '1F0509328', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '556', '2017-12-29 14:21:13', '柱塞泵进液', '沪制02220048', '压力表', '不锈钢压力表', '1F0509288', '2.5级', '0', '', '2017-12-29', null, '2018-12-29', '104', '310', '405', '411', null, '35', '396', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '557', '2017-12-29 14:24:12', '柱塞泵进液', '苏制02000284号', '压力表', '不锈钢压力表', '13122126P', '1.6级', '0', '', '2017-12-29', null, '2018-12-29', '105', '310', '405', '411', null, '35', '407', '395', '4', null, null, null, null, null, '149', '32');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '558', '2017-12-29 14:45:49', '内蒙古赤峰市翁牛特旗乌敦套海镇南中国石油红山加油站', '2017536', '税控燃油加油机', '税控燃油加油机', 'WCDX90037', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '559', '2017-12-29 14:46:41', '内蒙古赤峰市翁牛特旗乌敦套海镇南中国石油红山加油站', '2017535', '税控燃油加油机', '税控燃油加油机', 'WCDX900247', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '560', '2017-12-29 14:47:13', '内蒙古赤峰市翁牛特旗乌敦套海镇南中国石油红山加油站', '2017533', '税控燃油加油机', '税控燃油加油机', 'WCDX900245', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '561', '2017-12-29 14:48:13', '内蒙古赤峰市翁牛特旗乌敦套海镇南中国石油红山加油站', '2017537', '税控燃油加油机', '税控燃油加油机', 'WCDX900286', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '562', '2017-12-29 14:48:46', '内蒙古赤峰市翁牛特旗乌敦套海镇南中国石油红山加油站', '2017534', '税控燃油加油机', '税控燃油加油机', 'WCDX900292', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '563', '2017-12-29 14:50:15', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017471', '税控燃油加油机', '税控燃油加油机', 'TG016090061-1', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '564', '2017-12-29 14:51:04', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017468', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090064-2', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '565', '2017-12-29 14:51:39', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017467', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090064-1', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '566', '2017-12-29 14:52:11', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017466', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090063-2', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '567', '2017-12-29 14:52:57', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017465', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090063-1', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '568', '2017-12-29 14:53:31', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017472', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090061-2', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '569', '2017-12-29 14:55:10', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017469', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090062-1', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '570', '2017-12-29 14:55:46', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017470', '税控燃油加油机', 'IC卡税控燃油加油机', 'TG016090062-2', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '571', '2017-12-29 14:56:45', '内蒙古赤峰市翁牛特旗乌丹镇南中国石油东兴加油站', '2017307', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117060029', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '572', '2017-12-29 14:57:48', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017308', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117060029', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '573', '2017-12-29 14:58:33', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017305', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117060020', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '574', '2017-12-29 14:59:02', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017306', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117060020', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '575', '2017-12-29 14:59:33', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017304', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117060020', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '576', '2017-12-29 14:59:42', '检验科', '02260001', '原子吸收分光光度计', '原子吸收分光光度计', '16-990A-02-0120', 'TAS-990', '-2', '', '2018-01-10', null, '2018-12-29', '182', '90', '133', '167', null, '75', null, null, '2', null, null, null, null, null, '151', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '577', '2017-12-29 15:00:05', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017303', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH117060020', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '578', '2017-12-29 15:00:54', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017301', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ11760030', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '579', '2017-12-29 15:01:31', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017302', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117060028', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '580', '2017-12-29 15:02:10', '内蒙古赤峰市翁牛特旗乌丹镇全宁路北中国石油全宁加油站', '2017300', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ117060030', 'CS30D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '581', '2017-12-29 15:05:57', '检验科', '0226001', '可见分光光度计', '可见分光光度计', '16-1610-01-0206', 'T6', '-2', '', '2018-01-10', null, '2018-12-29', '185', '90', '133', '167', null, '76', null, null, '2', null, null, null, null, null, '152', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '582', '2017-12-29 15:08:57', '内蒙古赤峰市翁牛特旗海拉苏镇迎宾路南中国石油海日苏加油站', '2017385', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900241-1', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '583', '2017-12-29 15:09:33', '内蒙古赤峰市翁牛特旗海拉苏镇迎宾路南中国石油海日苏加油站', '2017386', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900243-1', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '584', '2017-12-29 15:10:08', '内蒙古赤峰市翁牛特旗海拉苏镇迎宾路南中国石油海日苏加油站', '2017388', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900240-1', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '585', '2017-12-29 15:10:43', '内蒙古赤峰市翁牛特旗海拉苏镇迎宾路南中国石油海日苏加油站', '2017387', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900243-2', 'CS46J2220G', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '150', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '586', '2017-12-29 15:20:52', '检验科', '01030007', '紫外可见分光光度计', '紫外可见分光光度计', '756S15040', '756S', '-2', '', '2018-01-10', null, '2018-12-29', null, '90', '133', '419', null, '77', null, null, '2', null, null, null, null, null, '153', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '587', '2017-12-29 15:26:14', '检验科', '02260001', '原子荧光光度计', '原子荧光光度计', '24A1708-01-0035', 'PF3', '-2', '', '2018-01-10', null, '2018-12-29', '193', '90', '133', '167', null, '79', null, null, '2', null, null, null, null, null, '154', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '588', '2017-12-29 15:52:00', '检验科', '120810', '电子天平', '电子天平', '120810', 'ESJ-205', '0', '', '2018-01-02', null, '2018-12-29', '14', '90', '133', '85', null, '3', '51', '14', '2', null, null, null, null, null, '155', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '589', '2017-12-29 15:59:58', '检验科', '22591390', '电子天平', '电子天平', '22591390', 'BS423S', '0', '', '2018-01-02', null, '2018-12-29', '14', '90', '133', '420', null, '3', '49', '14', '2', null, null, null, null, null, '156', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '590', '2017-12-29 16:14:07', '内蒙古赤峰市赤大高速桥头服务区二站', '2017655', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ240203110010072394-1', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '591', '2017-12-29 16:15:22', '内蒙古赤峰市赤大高速桥头服务区二站', '2017654', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0240203AA0010070530-2', 'CS42D4440F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '593', '2017-12-29 16:16:17', '内蒙古赤峰市赤大高速桥头服务区二站', '2017652', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ240203220010072387-1', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '594', '2017-12-29 16:16:52', '内蒙古赤峰市赤大高速桥头服务区二站', '2017651', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ240203220010072390-1', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '595', '2017-12-29 16:17:24', '内蒙古赤峰市赤大高速桥头服务区二站', '2017650', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ24020322001007287-2', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '596', '2017-12-29 16:19:34', '内蒙古赤峰市赤大高速头分地服务区一站', '2017606', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900017-2', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '597', '2017-12-29 16:20:16', '内蒙古赤峰市赤大高速头分地服务区一站', '2017607', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900024-2', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '598', '2017-12-29 16:20:52', '内蒙古赤峰市赤大高速头分地服务区一站', '2017610', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900017-1', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '599', '2017-12-29 16:21:20', '内蒙古赤峰市赤大高速头分地服务区一站', '2017609', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900023-2', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '600', '2017-12-29 16:21:51', '内蒙古赤峰市赤大高速头分地服务区一站', '2017608', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900023-1', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '157', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '602', '2017-12-29 16:28:49', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017710', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ016100020', 'CS46D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '603', '2017-12-29 16:29:09', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017711', '税控燃油加油机', 'IC卡税控燃油加油机', 'CS46D2223F', 'CS46D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '604', '2017-12-29 16:29:54', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017712', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100010', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '605', '2017-12-29 16:30:17', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017713', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100010', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '606', '2017-12-29 16:30:29', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017714', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100010', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '607', '2017-12-29 16:30:41', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017715', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100010', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '608', '2017-12-29 16:30:51', '检验科', '1407385', '乙炔压力表', '乙炔压力表', '1407385', '0-4MPa', '-2', '', '2018-01-10', null, '2018-12-29', null, '90', '133', null, null, '35', null, null, null, null, null, null, null, null, '161', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '609', '2017-12-29 16:31:12', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017716', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100011', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '610', '2017-12-29 16:31:22', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017717', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100011', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '611', '2017-12-29 16:31:32', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017718', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100011', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '612', '2017-12-29 16:31:44', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017719', '税控燃油加油机', 'IC卡税控燃油加油机', 'TH016100011', 'CS42D4243F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '613', '2017-12-29 16:32:39', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017720', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ016100019', 'CS46D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '614', '2017-12-29 16:32:51', '内蒙古赤峰翁旗乌丹镇古城街西段中国石油紫城加油站', '2017721', '税控燃油加油机', 'IC卡税控燃油加油机', 'TQ016100019', 'CS46D2223F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '615', '2017-12-29 16:34:35', '检验科', '1407374', '乙炔压力表', '乙炔压力表', '1407374', '0-0.25MPa', '-2', '', '2018-01-10', null, '2018-12-29', '104', '60', '78', '421', null, '35', null, null, '2', null, null, null, null, null, '162', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '616', '2017-12-29 16:36:08', '内蒙古赤峰赤大高速中国石油桥头服务区第一加油站', '2017656', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ240203220010072388', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '617', '2017-12-29 16:36:53', '内蒙古赤峰赤大高速中国石油桥头服务区第一加油站', '2017657', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0240203AA0010070533', 'CS42D4440F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '618', '2017-12-29 16:37:44', '内蒙古赤峰赤大高速中国石油桥头服务区第一加油站', '2017658', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ240203110010072393', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '619', '2017-12-29 16:38:24', '内蒙古赤峰赤大高速中国石油桥头服务区第一加油站', '2017659', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQ240203110010072392', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '620', '2017-12-29 16:42:18', '内蒙古赤峰赤大高速中国石油头分地服务区第二加油站', '2017605', '税控燃油加油机', 'IC卡税控燃油加油机', 'WCDX900016', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '621', '2017-12-29 16:56:41', '内蒙古赤峰赤大高速中国石油头分地服务区第二加油站', '2017603', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900018', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '622', '2017-12-29 16:57:29', '内蒙古赤峰赤大高速中国石油头分地服务区第二加油站', '2017601', '税控燃油加油机', 'IC卡税控燃油加油机', 'D0DX900042', 'CS42D4440F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '623', '2017-12-29 16:57:45', '内蒙古赤峰赤大高速中国石油头分地服务区第二加油站', '2017600', '税控燃油加油机', 'IC卡税控燃油加油机', 'DODX900042', 'CS42D4440F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '624', '2017-12-29 16:58:18', '内蒙古赤峰赤大高速中国石油头分地服务区第二加油站', '2017604', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900016', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '625', '2017-12-29 16:58:31', '内蒙古赤峰赤大高速中国石油头分地服务区第二加油站', '2017602', '税控燃油加油机', 'IC卡税控燃油加油机', 'WQDX900021', 'CS46D2220F', '0', '', '2018-01-02', null, '2018-12-29', '61', '178', '245', '59', null, '25', '264', '261', '1', null, null, null, null, null, '160', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '626', '2017-12-31 09:44:19', '实验室', '310116002287559', '精密酸度计', '精密酸度计', '015031107', 'PHS-3C', '0', '', '2018-01-10', null, '2018-12-31', '174', '81', '121', '422', null, '70', '571', '570', '2', null, null, null, null, null, '163', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '627', '2017-12-31 09:56:18', '实验室', '沪制01060128号', '721可见光分光光度计', '721可见光分光光度计', 'A1412056', '721', '0', '', '2018-01-02', null, '2018-12-31', '185', '81', '121', '423', null, '76', '587', '583', '2', null, null, null, null, null, '164', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '628', '2017-12-31 10:09:27', '实验室', '913101206793436902', '玻璃液体温度计', '玻璃液体温度计', '0163', '0°-50°', '0', '', '2018-01-10', null, '2018-12-31', '28', '81', '121', '424', null, '9', '118', '108', '1', null, null, null, null, null, '165', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '629', '2017-12-31 10:15:24', '实验室', '沪制00000314号', '电子天平', '电子天平', 'Y10151073', 'FA1004N', '0', '', '2018-01-10', null, '2018-12-31', '16', '81', '121', '425', null, '3', '32', '14', '1', null, null, null, null, null, '166', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '630', '2017-12-31 10:28:31', '实验室', '91331002797641154P', 'GHCS-1000型谷物电子容重器', 'GHCS-1000型谷物电子容重器', 'GHCS-100008014', 'GHCS-1000', '-2', '', '2018-01-10', null, '2018-12-31', null, '81', '121', '426', null, '24', null, null, '1', null, null, null, null, null, '167', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '631', '2017-12-31 10:35:27', '实验室', '92131123MA090U0QXF', '精密酒精计', '精密酒精计', '0023', '0°-100°', '0', '', '2018-01-10', null, '2018-12-31', '62', '81', '121', '427', null, '26', '265', '266', '1', null, null, null, null, null, '168', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '632', '2017-12-31 14:58:02', '粉碎车间', '91120111681879136P', '台秤', '台秤', '0024', 'TGT-1000型', '0', '', '2018-01-09', null, '2018-12-31', '53', '81', '121', '428', null, '18', '678', '173', '1', null, null, null, null, null, '169', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '633', '2017-12-31 15:14:28', '粉碎车间', '沪ICP备050403136', '称重显示控制器', '称重显示控制器', '1703189812', 'XK3190-A12+E', '0', '', '2018-01-09', null, '2018-12-31', '56', '81', '121', '429', null, '20', '192', '210', '1', null, null, null, null, null, '170', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '634', '2017-12-31 15:27:26', '灌装车间', '鲁07000063', '智能感应全自动定量灌装机', '智能感应全自动定量灌装机', '011611', 'GZE-18', '0', '', '2018-01-09', null, '2018-12-31', '59', '81', '121', '430', null, '23', '245', '237', '1', null, null, null, null, null, '171', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '635', '2017-12-31 15:48:31', '锅炉车间', '91210106702066629N', '压力表', '压力表', '201341044', '1.6MPa', '0', '', '2018-01-09', null, '2018-12-31', '17', '81', '121', '353', null, '4', '71', '54', '1', null, null, null, null, null, '170', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '636', '2017-12-31 16:08:00', '酒库', '91310112607428361T', 'WT4500涡街流量计', 'WT4500涡街流量计', '201606170981', 'WT4500EX-23WL50DS02KC10Y', '0', '', '2018-01-08', null, '2018-12-31', '87', '81', '121', '431', null, '32', '357', '749', '1', null, null, null, null, null, '172', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '637', '2017-12-31 16:14:02', '酒库', '91310112607428361T', '电磁流量计', '电磁流量计', '13116070387', 'DE47P', '0', '', '2018-01-08', null, '2018-12-31', '81', '81', '121', '431', null, '32', '358', '357', '1', null, null, null, null, null, '173', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '638', '2017-12-31 16:36:11', '车间', 'B-2105BN', '智能涡街流量计', '智能涡街流量计', '20151211', 'K5121103', '0', '', '2018-01-08', null, '2018-12-31', '223', '81', '121', '432', null, '103', '732', '733', '1', null, null, null, null, null, '174', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '639', '2018-01-02 09:33:33', '制冷车间', '辽制00000286', '压力表', '氧压表', '2222', 'Y0-100', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '353', null, '35', '414', '405', '5', null, null, null, null, null, '175', '33');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '640', '2018-01-02 09:40:18', '制冷车间', '辽制00000286', '压力表', '氧压表', '2224', 'Y0-100', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '353', null, '35', '414', '405', '5', null, null, null, null, null, '176', '33');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '641', '2018-01-02 09:43:36', '制冷车间', '辽制00000286', '压力表', '氧压表', '2225', 'Y0-100', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '353', null, '35', '414', '405', '5', null, null, null, null, null, '177', '33');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '642', '2018-01-02 09:46:54', '制冷车间', '辽制00000286', '压力表', '氧压表', '2247', 'Y0-100', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '353', null, '35', '414', '405', '5', null, null, null, null, null, '178', '33');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '643', '2018-01-02 09:49:02', '制冷车间', '辽制00000286', '压力表', '氧压表', '2255', 'Y0-100', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '353', null, '35', '414', '405', '5', null, null, null, null, null, '179', '33');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '644', '2018-01-02 09:54:23', '锅炉', '浙制03820214', '压力表', '压力表', '2507', 'Y0-150', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '71', null, '35', '407', '395', '5', null, null, null, null, null, '180', '34');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '645', '2018-01-02 09:57:12', '锅炉', '浙制03820214', '压力表', '压力表', '4814', 'Y0-150', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '71', null, '35', '407', '395', '5', null, null, null, null, null, '181', '34');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '646', '2018-01-02 09:59:11', '锅炉', '浙制03820214', '压力表', '压力表', '8929', 'Y0-150', '0', '', '2018-01-02', null, '2019-01-02', '105', '313', '433', '71', null, '35', '407', '395', '5', null, null, null, null, null, '182', '34');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '647', '2018-01-02 15:31:47', '工厂院里', '蒙制00000035', '地秤', 'SCS-150型电子汽车衡', '160060', '32×16m', '0', '', '2018-01-02', null, '2019-01-02', '55', '316', '456', '457', null, '19', '190', '181', '1', null, null, null, null, null, '183', '35');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '648', '2018-01-02 15:32:29', '工厂院里', '蒙制00000035', '地秤', 'SCS-150型电子汽车衡', '160060', '32×16m', '-2', '', '2018-01-02', null, '2019-01-02', '55', '316', '456', '457', null, '19', '190', '181', '1', null, null, null, null, '3', '183', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '649', '2018-01-02 16:31:56', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005438', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '184', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '650', '2018-01-02 16:47:44', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005412', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '185', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '651', '2018-01-02 16:51:49', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005319', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '652', '2018-01-02 16:52:42', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B614301901', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '653', '2018-01-02 16:52:57', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005447', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '654', '2018-01-02 16:53:12', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B614302130', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '655', '2018-01-02 16:53:25', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005300', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '656', '2018-01-02 16:53:39', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B614306364', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '657', '2018-01-02 16:53:53', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005510', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '658', '2018-01-02 16:54:06', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005456', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '659', '2018-01-02 16:54:18', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B614302118', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '660', '2018-01-02 16:54:29', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005471', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '661', '2018-01-02 16:54:41', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005310', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '662', '2018-01-02 16:54:55', '散装称重区', '(2005)量制苏字00000626', '条形码打印电子计价秤', '条形码打印电子计价秤', 'B603005380', 'ACS-JJ(RLOO)', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '459', null, '20', '214', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '663', '2018-01-02 16:57:07', '公平秤区', '（99）量制昆字00000501', '电子计价秤', '电子计价秤', '78291', 'ACS-30', '0', '', '2018-01-02', null, '2019-01-02', '56', '317', '458', '460', null, '20', '201', '210', '1', null, null, null, null, null, '186', '36');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '664', '2018-01-03 19:24:34', '车间', '浙制01830158号', '压力表', '压力表', 'HY80945', 'Y100', '-2', '', '2018-01-08', null, '2019-01-03', '105', '76', '110', '461', null, '35', '407', '395', '4', null, null, null, null, null, '187', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '665', '2018-01-03 19:32:24', '饲料厂', '（苏）制00000043号', '电子汽车衡', '电子汽车衡', '15120002', 'SCS-120型', '0', '', '2018-01-08', null, '2019-01-03', '55', '76', '110', '462', null, '19', '185', '181', '1', null, null, null, null, null, '188', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '666', '2018-01-03 19:48:12', '饲料厂', '（苏）制00000436号-3', '电子汽车衡', '电子汽车衡', 'A1706540-2', 'SCS-50', '0', '', '2018-01-08', null, '2019-01-03', '55', '76', '110', '463', null, '19', '191', '181', '1', null, null, null, null, null, '189', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '667', '2018-01-04 08:18:45', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '92028', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '190', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '668', '2018-01-04 08:19:25', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '97613', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '190', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '669', '2018-01-04 08:21:04', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '95791', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '190', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '670', '2018-01-04 08:22:12', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '93276', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '190', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '671', '2018-01-04 08:33:19', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '97651', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '672', '2018-01-04 08:33:38', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '97647', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '673', '2018-01-04 08:33:50', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '90794', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '674', '2018-01-04 08:34:01', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '09622', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '675', '2018-01-04 08:35:11', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '07779', 'Y-200', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '676', '2018-01-04 08:35:29', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '07897', 'Y-200', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '396', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '677', '2018-01-04 08:36:14', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '17309', 'Y-150', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '407', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '678', '2018-01-04 08:36:42', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '98624', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '398', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '679', '2018-01-04 08:37:13', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '98621', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '398', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '680', '2018-01-04 08:37:26', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '70286', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '398', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '681', '2018-01-04 08:37:40', '林西县南工业园区林西华龙矿业有限公司', '浙制0380214', '压力表', '压力表', '98581', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '398', '395', '4', null, null, null, null, null, '191', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '683', '2018-01-04 08:46:09', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '70791', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '104', '312', '418', '71', null, '35', null, '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '684', '2018-01-04 08:46:54', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '70762', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '104', '312', '418', '71', null, '35', null, '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '685', '2018-01-04 08:47:43', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '13995', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '104', '312', '418', '71', null, '35', null, '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '686', '2018-01-04 08:48:25', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '58315', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '104', '312', '418', '71', null, '35', null, '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '687', '2018-01-04 08:48:44', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '73296', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '104', '312', '418', '71', null, '35', null, '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '688', '2018-01-04 08:49:35', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '02654', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '689', '2018-01-04 08:50:16', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '03437', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '690', '2018-01-04 08:50:28', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '03422', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '691', '2018-01-04 08:50:36', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '03044', 'Y-100', '-2', '', '2018-01-09', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '192', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '692', '2018-01-04 09:28:37', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '70791', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '104', '312', '418', '71', null, '35', '416', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '693', '2018-01-04 09:28:54', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '13995', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '104', '312', '418', '71', null, '35', '416', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '694', '2018-01-04 09:29:04', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '70762', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '104', '312', '418', '71', null, '35', '416', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '695', '2018-01-04 09:29:16', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '58315', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '104', '312', '418', '71', null, '35', '416', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '696', '2018-01-04 09:29:25', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '73296', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '104', '312', '418', '71', null, '35', '416', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '697', '2018-01-04 09:29:51', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '02654', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '698', '2018-01-04 09:30:01', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '03437', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '699', '2018-01-04 09:30:10', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '03422', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '700', '2018-01-04 09:30:20', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '03044', 'Y-100', '0', '', '2018-01-04', null, '2019-01-04', '105', '312', '418', '71', null, '35', '400', '395', '4', null, null, null, null, null, '193', '37');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '701', '2018-01-04 09:36:30', '林西县南工业园区林西华龙矿业有限公司', '浙制03820214', '压力表', '压力表', '70791', 'Y-100', '-2', '', '2018-01-04', null, '2019-01-04', '104', '312', '418', '71', null, '35', null, null, '4', null, null, null, null, null, '194', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '702', '2018-01-04 17:24:43', '储罐', 'EN837-1', '压力表', '真空压力表', '88000784', ':沪B2-20040569', '0', '', '2018-01-05', null, '2019-01-04', '18', '310', '405', '464', null, '4', '67', '54', null, null, null, null, null, null, '195', '38');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '703', '2018-01-04 17:25:02', '储罐', 'EN837-1', '压力表', '真空压力表', 'EN837-1', ':沪B2-20040569', '0', '', '2018-01-05', null, '2019-01-04', '18', '310', '405', '464', null, '4', '67', '54', null, null, null, null, null, null, '195', '38');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '704', '2018-01-05 10:22:03', 'S205线129km+500m', 'CPA2008-L172', '雷达', '雷达测速仪', 'ST010929', 'HKMEP-II', '-1', '', null, null, '2019-01-05', '115', '75', '109', '465', null, '41', '465', '464', '4', null, null, null, null, null, '196', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '705', '2018-01-05 10:26:36', '大海线102km', 'CPA2008-L172', '雷达', '雷达测速仪', 'ST010426', 'HKMEP-II', '-1', '', null, null, '2019-01-05', '115', '75', '109', '465', null, '41', '465', '464', '4', null, null, null, null, null, '197', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '706', '2018-01-05 10:29:42', '大海线102km', 'CPA2008-L172', '雷达', '雷达测速仪', 'ST010429', 'HKMEP-II', '-1', '', null, null, '2019-01-05', '115', '75', '109', '465', null, '41', '465', '464', '4', null, null, null, null, null, '198', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '707', '2018-01-05 10:40:12', '外环路玉环岗', '01080350', '雷达', '雷达测速仪', '1K5001476S2S4', 'VCC-A023-ITK', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '199', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '708', '2018-01-05 10:42:46', '外环路玉环岗', '01080350', '雷达', '雷达测速仪', '1K5801215S2S4', 'VCC-A023-ITK', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '200', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '709', '2018-01-05 10:45:44', 'S205线99k+800m', '01080350', '雷达', '雷达测速仪', '1R5100056SB4S4', 'HTS-HC161', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '201', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '710', '2018-01-05 10:49:07', '外环路与乌哈线交叉路口100m', '01080350', '雷达', '雷达测速仪', '1R5100089SB4S4', 'CSR-IR', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '202', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '711', '2018-01-05 10:51:40', '外环路与乌哈线交叉路口500m', '01080350', '雷达', '雷达测速仪', '1R5100073SB4S4', 'CSR-IR', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '203', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '712', '2018-01-05 10:53:53', '外环路与乌哈线交叉路口800m', '01080350', '雷达', '雷达测速仪', '1R5100088SB4S4', 'CSR-IR', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '204', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '713', '2018-01-05 11:02:17', '新立屯G305线597km+500m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00216', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '205', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '714', '2018-01-05 11:05:20', '新立屯G305线597km+500m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00186', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '206', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '715', '2018-01-05 11:08:02', '乌兰敖都G305线642km+150m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00215', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '207', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '716', '2018-01-05 11:10:13', '乌兰敖都G305线642km+150m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00223', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '208', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '717', '2018-01-05 11:12:39', '海拉苏水管局G305线667km+530m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00162', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '209', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '718', '2018-01-05 11:14:52', '海拉苏水管局G305线667km+530m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00178', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '210', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '719', '2018-01-05 11:17:12', '杜家地G306线352km+100m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00197', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '211', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '720', '2018-01-05 11:19:47', '杜家地G306线352km+100m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00209', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '212', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '721', '2018-01-05 11:22:11', '杜家地G306线352km+100m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00208', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '213', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '722', '2018-01-05 11:35:23', '杜家地G306线352km+100m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00182', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '214', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '723', '2018-01-05 11:40:28', '黑水道班G306线402km', '00000926', '雷达', '雷达测速仪', '2H04788GAJ00013', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '215', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '724', '2018-01-05 11:42:46', '黑水道班G306线402km', '00000926', '雷达', '雷达测速仪', '2H00001PAJ002169', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '216', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '725', '2018-01-05 11:44:31', '黑水道班G306线402km', '00000926', '雷达', '雷达测速仪', '2J0563BPAJ00147', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '217', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '726', '2018-01-05 11:46:35', '黑水道班G306线402km', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00166', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '218', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '727', '2018-01-05 11:49:47', '小海苏沟S205线144km+500m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00201', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '219', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '728', '2018-01-05 11:52:07', '小海苏沟S205线144km+500m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00263', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '220', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '729', '2018-01-05 11:54:27', '中粮家佳康S304线494km', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00172', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '221', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '730', '2018-01-05 14:52:18', '中粮家佳康S304线494km', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00164', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '463', '4', null, null, null, null, null, '222', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '731', '2018-01-05 14:54:23', '中粮家佳康S304线494km', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00192', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '223', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '732', '2018-01-05 14:56:17', '中粮家佳康S304线494km', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00173', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', null, null, '41', '465', '464', '4', null, null, null, null, null, '224', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '733', '2018-01-05 15:02:13', '白音汉S304线525km+170m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00170', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '225', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '734', '2018-01-05 15:04:38', '白音汉S304线525km+170m', '00000926', '雷达', '雷达测速仪', '2H04788GAJ00042', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '226', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '735', '2018-01-05 15:05:13', '消毒供应室', '（苏）制08510007', '压力表', '压力表', '13-8-6799', '无', '0', '', '2018-01-08', null, '2019-01-05', '105', '22', '24', '469', null, '35', '411', '395', '2', null, null, null, null, null, '227', '40');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '736', '2018-01-05 15:06:16', '白音汉S304线525km+170m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00203', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', null, null, null, null, null, null, '228', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '737', '2018-01-05 15:07:52', '白音汉S304线525km+170m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00194', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '229', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '738', '2018-01-05 15:09:41', '赵大门S205线58km+200m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00204', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '230', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '739', '2018-01-05 15:11:13', '赵大门S205线58km+200m', '00000926', '雷达', '雷达测速仪', '2H00001PAJ00191', 'NH-ITARD-024SA', '-1', '', null, null, '2019-01-05', '115', '75', '109', '467', null, '41', '465', '464', '4', null, null, null, null, null, '231', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '740', '2018-01-05 15:17:02', 'G306线375km+400m', '00000181', '雷达', '雷达测速仪', '2010001', 'TZYDBX', '-1', '', null, null, '2019-01-05', '115', '75', '109', '470', null, '41', '465', '464', '4', null, null, null, null, null, '232', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '741', '2018-01-05 15:58:22', '外环路与乌哈线交叉路口500m', '01080350', '雷达', '雷达测速仪', 'IR5100090SB4S4', 'WEP-1-B', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '233', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '742', '2018-01-05 16:01:05', '外环路与乌大线交叉路口', '01080350', '雷达', '雷达测速仪', 'IR5100091SB4S4', 'WEP-1-B', '-1', '', null, null, '2019-01-05', '115', '75', '109', '466', null, '41', '465', '464', '4', null, null, null, null, null, '234', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '743', '2018-01-05 16:07:08', 'S205线129km+500m', '00000887', '雷达', '雷达测速仪', 'ST009318', 'SL-MVCS', '-1', '', null, null, '2019-01-05', '115', '75', '109', '471', null, '41', '465', '464', '4', null, null, null, null, null, '235', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '744', '2018-01-06 09:03:10', '消毒供应室', '（苏）制05810007', '压力表', '压力表', '13-8-6779', '无', '0', '', '2018-01-08', null, '2019-01-06', '105', '22', '24', '469', null, '35', '411', '395', '2', null, null, null, null, null, '236', '40');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '745', '2018-01-08 09:31:36', '翁牛特旗蒙医中医医院', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.01.2947', '（0-1.6）MPa', '0', '', '2018-01-08', null, '2019-01-08', '17', '71', '105', '97', null, '4', '71', '54', '2', null, null, null, null, null, '237', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '746', '2018-01-08 10:32:16', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.06.5094', '(0-1.60)MPa', '0', '', '2018-01-08', null, '2019-01-08', '17', '71', '105', '97', null, '4', '71', '54', '2', null, null, null, null, null, '238', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '747', '2018-01-08 10:36:28', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.01.2944', '（0-1.6）MPa', '-2', '', '2018-01-08', null, '2019-01-08', '17', '71', '105', '97', null, '4', '71', '54', '2', null, null, null, null, null, '239', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '748', '2018-01-08 10:41:22', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'N.09.8594', '（0-2.5）MPa', '0', '', '2018-01-08', null, '2019-01-08', '109', '71', '105', '97', null, '37', '444', '456', '2', null, null, null, null, null, '240', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '749', '2018-01-08 10:57:34', '赤峰阿拉坦新资源食品有限公司', '津制01040018', '压力表', '压力表', 'HKA3005', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '475', null, '35', '398', '395', '4', null, null, null, null, null, '241', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '750', '2018-01-08 10:58:10', '赤峰阿拉坦新资源食品有限公司', '津制01040018', '压力表', '压力表', 'HKA3299', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '475', null, '35', '398', '395', '4', null, null, null, null, null, '241', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '751', '2018-01-08 11:03:29', '赤峰阿拉坦新资源食品有限公司', '津制01040018', '压力表', '压力表', 'HKA3349', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '475', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '752', '2018-01-08 11:03:50', '赤峰阿拉坦新资源食品有限公司', '津制01040018', '压力表', '压力表', 'HKA3297', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '475', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '753', '2018-01-08 11:04:07', '赤峰阿拉坦新资源食品有限公司', '津制01040018', '压力表', '压力表', 'HKA3287', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '475', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '754', '2018-01-08 11:04:23', '赤峰阿拉坦新资源食品有限公司', '津制01040018', '压力表', '压力表', 'HKA3294', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '475', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '755', '2018-01-08 11:06:05', '赤峰阿拉坦新资源食品有限公司', '浙制03820214', '压力表', '压力表', 'HA65593040586', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '71', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '756', '2018-01-08 11:07:14', '赤峰阿拉坦新资源食品有限公司', '浙制03820214', '压力表', '压力表', 'HA65593041900', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '71', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '757', '2018-01-08 11:07:42', '赤峰阿拉坦新资源食品有限公司', '浙制03820214', '压力表', '压力表', 'HA65593040797', 'Y-100', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '71', null, '35', '398', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '758', '2018-01-08 11:10:13', '赤峰阿拉坦新资源食品有限公司', '鲁制02000188号', '压力表', '压力表', '20161205045', 'Y-150', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '476', null, '35', '407', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '759', '2018-01-08 11:11:06', '赤峰阿拉坦新资源食品有限公司', '鲁制02000188号', '压力表', '压力表', '20161202199', 'Y-150', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '476', null, '35', '407', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '760', '2018-01-08 11:11:33', '赤峰阿拉坦新资源食品有限公司', '鲁制02000188号', '压力表', '压力表', '20161202215', 'Y-150', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '476', null, '35', '407', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '761', '2018-01-08 11:12:11', '赤峰阿拉坦新资源食品有限公司', '鲁制02000188号', '压力表', '压力表', '20161202209', 'Y-150', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '476', null, '35', '407', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '762', '2018-01-08 11:12:38', '赤峰阿拉坦新资源食品有限公司', '鲁制02000188号', '压力表', '压力表', '20161207017', 'Y-150', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '476', null, '35', '407', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '763', '2018-01-08 11:13:39', '赤峰阿拉坦新资源食品有限公司', '鲁制02000188号', '压力表', '压力表', '20161207186', 'Y-150', '0', '', '2018-01-09', null, '2019-01-08', '260', '320', '474', '476', null, '35', '407', '395', '4', null, null, null, null, null, '242', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '764', '2018-01-08 14:57:05', '天山镇', '58698752415245', '电子汽车衡', '电子汽车衡', 'NM123456', 'SW-1250', '0', '', '2018-01-08', null, '2019-01-08', '55', '34', '42', '44', null, '19', '756', '181', '1', null, null, null, null, null, '243', '41');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '774', '2018-01-09 08:43:06', '阿鲁科尔沁旗天山镇', 'AS-15022', '电子汽车衡', '电子汽车衡', '123456789', 'sw-123', '0', '', '2018-01-09', null, '2019-01-09', '55', '34', '42', '253', null, '19', '756', '181', '1', null, null, null, null, null, '253', '42');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '785', '2018-01-09 11:26:30', 'A#储罐', ':沪B2-20040569', '压力表', '不锈钢压力表', 'EN837-1', '0-2.5', '0', '', '2018-01-09', null, '2019-01-09', '103', '310', '405', '464', null, '35', '396', '395', '4', null, null, null, null, null, '254', '45');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '786', '2018-01-09 11:26:59', 'B#储罐', ':沪B2-20040569', '压力表', '不锈钢压力表', 'EN837-1', '0-2.5', '0', '', '2018-01-09', null, '2019-01-09', '103', '310', '405', '464', null, '35', '396', '395', '4', null, null, null, null, null, '254', '45');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '787', '2018-01-09 14:44:21', '实验室', '02050120', '压力表', '乙炔压力表', '1211947', '1-4mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '404', '398', '5', null, null, null, null, null, '252', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '788', '2018-01-09 14:46:13', '实验室', '02050120', '压力表', '乙炔压力表', '1211811', '0-0.025mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '397', '395', '5', null, null, null, null, null, '251', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '789', '2018-01-09 14:47:45', '实验室', '02050120', '压力表', '氧气压力表', '1110417', '0-25mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '413', '395', '5', null, null, null, null, null, '250', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '790', '2018-01-09 14:49:18', '实验室', '02050120', '压力表', '氧气压力表', '1110219', '0-25mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '413', '395', '5', null, null, null, null, null, '249', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '791', '2018-01-09 14:50:53', '实验室', '02050120', '压力表', '乙炔压力表', '1110863', '0-2.5mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '396', '395', '5', null, null, null, null, null, '248', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '792', '2018-01-09 14:52:20', '实验室', '02050120', '压力表', '氧气压力表', '1110381', '0-2.5mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '396', '395', '5', null, null, null, null, null, '247', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '793', '2018-01-09 14:53:34', '实验室', '02050120', '压力表', '氧气压力表', '1272311', '0-2.5mpa', '-1', '', null, null, '2019-01-09', '20', '322', '478', '115', null, '4', '67', '54', '5', null, null, null, null, null, '246', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '794', '2018-01-09 14:54:50', '实验室', '02050120', '压力表', '氧气压力表', '1271349', '0-25mpa', '-1', '', null, null, '2019-01-09', '20', '322', '478', '115', null, '4', '67', '54', '5', null, null, null, null, null, '245', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '795', '2018-01-09 14:59:51', '实验室', '02050120', '压力表', '压力表', '24GB170177', 'DSX-280KB24', '-1', '', null, null, '2019-01-09', '105', '22', '24', '480', null, '35', '411', '395', '5', null, null, null, null, null, '244', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '796', '2018-01-09 15:21:43', '实验室', '02050120', '压力表', '氧气压力表', '1272311', '0-2.5mpa', '-1', '', null, null, '2019-01-09', '104', '322', '478', '115', null, '35', '396', '395', '5', null, null, null, null, null, '246', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '797', '2018-01-09 15:23:03', '实验室', '02050120', '压力表', '氧气压力表', '1271349', '0-25mpa', '-1', '', null, null, '2019-01-09', '104', '322', '478', '115', null, '35', '413', '395', '5', null, null, null, null, null, '245', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '798', '2018-01-09 15:24:35', '实验室', '02050120', '压力表', '压力表', '24GB170177', 'DSX-280KB24', '-1', '', null, null, '2019-01-09', '105', '322', '478', '480', null, '35', '411', '395', '5', null, null, null, null, null, '244', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '799', '2018-01-09 15:28:38', '实验室', '02050120', '压力表', '氧气压力表', '1272311', '0-2.5mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '396', '395', '5', null, null, null, null, null, '255', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '800', '2018-01-09 15:30:03', '实验室', '02050120', '压力表', '氧气压力表', '1271349', '0-25mpa', '0', '', '2018-01-09', null, '2019-01-09', '104', '322', '478', '115', null, '35', '413', '395', '5', null, null, null, null, null, '256', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '801', '2018-01-09 15:31:36', '实验室', '02050120', '压力表', '压力表', '24GB170177', 'DSX-280KB24', '0', '', '2018-01-09', null, '2019-01-09', '105', '322', '478', '480', null, '35', '411', '395', '5', null, null, null, null, null, '257', '44');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '802', '2018-01-09 15:36:12', '天山镇', '456789456123', '电子汽车衡', '电子汽车衡', 'AS-152', 'IPZ-841', '0', '', '2018-01-09', null, '2019-01-09', '55', '34', '42', '123', null, '19', '756', '181', '1', null, null, null, null, null, '258', '43');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '804', '2018-01-10 10:14:29', '翁牛特旗蒙医中医医院高压氧科', '鲁制02000105号', '弹簧管式一般压力表和真空表', '氧气表', 'P.01.2944', '（0-1.6）MPa', '0', '', '2018-01-10', null, '2019-01-10', '110', '71', '105', '125', null, '37', '443', '456', '2', null, null, null, null, null, '260', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '805', '2018-01-10 14:28:47', '天山镇', '1524252414522', '电子汽车衡', '电子汽车衡', 'SW-15212', 'SW-1502', '0', '', '2018-01-10', null, '2019-01-10', '55', '34', '42', '69', null, '19', '756', '181', '1', null, null, null, null, null, '261', '46');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '806', '2018-01-10 14:41:44', '双胜镇', '123456789', '电子汽车衡', '电子汽车衡', '123456', 'sw-1520', '0', '', '2018-01-10', null, '2019-01-10', '55', '34', '42', '253', null, '19', '191', '181', '1', null, null, null, null, null, '262', '47');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '807', '2018-01-10 14:49:04', '巴彦花加油站', '5852115222', '燃油税控加油机', '燃油税控加油机', '15242511225', 'SW-1502', '0', '', '2018-01-10', null, '2019-01-10', '61', '34', '42', '487', null, '25', '260', '258', '1', null, null, null, null, null, '263', '48');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '808', '2018-01-10 15:08:40', '天山镇', 'j562253', '压力表', '压力表', '01', 'SW-1502', '0', '', '2018-01-10', null, '2019-01-10', '17', '276', '38', '469', null, '4', '66', '54', '4', null, null, null, null, null, '264', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '809', '2018-01-10 15:37:29', '天山镇', '123456789689', '电子天平', '电子天平', 'D307030882', 'DDA307', '0', '', '2018-01-10', null, '2019-01-10', '14', '34', '42', '167', null, '3', '24', '14', '5', null, null, null, null, null, '265', '49');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '810', '2018-01-11 09:35:28', '加油站', '3K016051154', '燃油加油机', '燃油加油机', '3K016051154', 'C932J1110F', '-1', '', null, null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '811', '2018-01-11 09:36:10', '加油站', '3K016051154', '燃油加油机', '燃油加油机', '3K016051121', 'C932J1110F', '-1', '', null, null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '812', '2018-01-11 09:36:49', '加油站', '3K016051154', '燃油加油机', '燃油加油机', '3K016051138', 'C932J1110F', '-1', '', null, null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '813', '2018-01-11 09:37:35', '加油站', '3K016051154', '燃油加油机', '燃油加油机', '3K016051319', 'C932J1110F', '-1', '', null, null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '88', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '814', '2018-01-11 10:21:03', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051154', '3K016051154', '0', '', '2018-01-11', null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '266', '53');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '815', '2018-01-11 10:21:25', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051121', '3K016051154', '0', '', '2018-01-11', null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '266', '53');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '816', '2018-01-11 10:21:59', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051138', '3K016051154', '0', '', '2018-01-11', null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '266', '53');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '817', '2018-01-11 10:22:16', '加油站', 'C932J1110F', '燃油加油机', '燃油加油机', '3K016051319', '3K016051154', '0', '', '2018-01-11', null, '2019-01-11', '61', '131', '186', '499', null, '25', '263', '258', '1', null, null, null, null, null, '266', '53');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '818', '2018-01-11 17:20:36', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117090014', 'CS30D2223F', '0', '', '2018-01-15', null, '2019-01-11', '61', '351', '510', '528', null, '25', '260', '258', '1', null, null, null, null, null, '267', '58');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '819', '2018-01-11 17:28:12', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117090016', 'CS30D2223F', '0', '', '2018-01-15', null, '2019-01-11', '61', '351', '510', '528', null, '25', '260', '258', '1', null, null, null, null, null, '268', '58');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '820', '2018-01-11 17:37:50', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117090015', 'CS30D2223F', '0', '', '2018-01-15', null, '2019-01-11', '61', '351', '510', '528', null, '25', '260', '258', '1', null, null, null, null, null, '269', '58');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '821', '2018-01-11 17:38:17', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117090015', 'CS30D2223F', '-2', '', '2018-01-15', null, '2019-01-11', '61', '351', '510', '528', null, '25', '260', '258', '1', null, null, null, null, null, '269', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '822', '2018-01-11 17:39:19', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117090015', 'CS30D2223F', '-2', '', '2018-01-15', null, '2019-01-11', '61', '351', '510', '528', null, '25', '260', '258', '1', null, null, null, null, null, '269', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '823', '2018-01-11 17:46:05', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'Y71985B001', 'SK56QF424K', '0', '', '2018-01-15', null, '2019-01-11', '61', '351', '510', '413', null, '25', '260', '258', '1', null, null, null, null, null, '270', '58');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '824', '2018-01-12 10:31:27', '罕苏木苏木加油有限公司', 'CMC粤制00000004', '税控燃油加油机', '税控燃油加油机', '1404282J', 'HS2818A', '0', '', '2018-01-15', null, '2019-01-12', '61', '342', '501', '529', null, '25', '260', '258', '1', null, null, null, null, null, '271', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '825', '2018-01-12 10:38:49', '罕苏木苏木加油有限公司', 'CMC豫制00000198', '税控燃油加油机', '税控燃油加油机', '3K440323110010052589', '32J1110F', '0', '', '2018-01-15', null, '2019-01-12', '61', '342', '501', '59', null, '25', '260', '258', '1', null, null, null, null, null, '272', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '826', '2018-01-12 10:39:37', '罕苏木苏木加油有限公司', 'CMC豫制00000198', '税控燃油加油机', '税控燃油加油机', '3K440323110010052585', '32J1110F', '0', '', '2018-01-15', null, '2019-01-12', '61', '342', '501', '59', null, '25', '260', '258', '1', null, null, null, null, null, '272', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '827', '2018-01-12 11:07:49', '巴彦宝力格加油有限公司', 'CMC  粵制00000004', '燃油加油机', '托肯恒山科技有限公司。', '1404283j', 'HS2818A', '0', '', '2018-01-17', null, '2019-01-12', '61', '345', '504', '530', null, '25', '260', '258', '1', null, null, null, null, null, '273', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '828', '2018-01-12 11:16:02', '巴彦宝力格加油有限公司', '豫制00000198号', '燃油加油机', '正星科技有限公司', '3K10000856', 'CS32j1110F', '0', '', '2018-01-17', null, '2019-01-12', '61', '345', '504', '59', null, '25', '260', '258', '1', null, null, null, null, null, '273', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '829', '2018-01-12 11:31:03', '巴彦宝力格加油有限公司', '豫制00000198号', '燃油加油机', '正星科技有限公司', '3K44032311001012D334', 'CS32j1110F', '0', '', '2018-01-17', null, '2019-01-12', '61', '345', '504', '59', null, '25', '260', '258', '1', null, null, null, null, null, '273', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '830', '2018-01-12 12:34:17', '阿鲁科尔沁旗温都花煤炭加油有限责任公司', '豫制00000198', '燃油加油机', '税控燃油加油机', '8Y014070077', 'CS32J2220F', '0', '', '2018-01-15', null, '2019-01-12', '61', '336', '494', '499', null, '25', '260', '258', '1', null, null, null, null, null, '274', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '831', '2018-01-12 12:39:41', '阿鲁科尔沁旗温都花煤炭加油有限责任公司', '豫制00000198号', '燃油加油机', '税控燃油加油机', '8Y014070077', 'CS32J2220F', '0', '', '2018-01-15', null, '2019-01-12', '61', '336', '494', '59', null, '25', '260', '258', '1', null, null, null, null, null, '275', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '832', '2018-01-12 12:40:16', '阿鲁科尔沁旗温都花煤炭加油有限责任公司', '豫制00000198号', '燃油加油机', '税控燃油加油机', '8Y017090065', 'CS32J2220F', '0', '', '2018-01-15', null, '2019-01-12', '61', '336', '494', '168', null, '25', '260', '258', '1', null, null, null, null, null, '275', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '833', '2018-01-13 11:41:46', '天山镇汉林路东交通石油', '豫制00000198号', '税控燃油加油机', '正星燃油加油机', 'WD017100019', 'CS46J2120G', '0', '', '2018-01-15', null, '2019-01-13', '61', '346', '505', '531', null, '25', '264', '261', '1', null, null, null, null, null, '276', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '834', '2018-01-13 11:48:46', '天山镇汉林路东交通石油', '豫制00000198号', '税控燃油加油机', '正星燃油加油机', 'WD017100020', 'CS46J2120G', '0', '', '2018-01-15', null, '2019-01-13', '61', '346', '505', '531', null, '25', '264', '261', '1', null, null, null, null, null, '277', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '835', '2018-01-13 11:59:53', '天山镇汉林路东交通石油', '豫制00000198号', '税控燃油加油机', '正星燃油加油机', 'WD01700018', 'CS46J2120G', '0', '', '2018-01-15', null, '2019-01-13', '61', '346', '505', '531', null, '25', '264', '261', '1', null, null, null, null, null, '278', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '836', '2018-01-13 12:04:36', '天山镇汉林路东交通石油', '豫制00000198号', '税控燃油加油机', '正星燃油加油机', 'WC017100025', 'CS46J2220G', '0', '', '2018-01-15', null, '2019-01-13', '61', '346', '505', '531', null, '25', '264', '261', '1', null, null, null, null, null, '279', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '837', '2018-01-15 10:04:23', '阿鲁科尔沁旗天山镇东桥东', 'GHDZ12123', '电子汽车衡', '电子汽车衡', '01', 'GB120', '0', '', '2018-01-15', null, '2019-01-15', '55', '370', '533', '457', null, '19', '759', '181', '1', null, null, null, null, null, '280', '54');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '838', '2018-01-15 10:36:01', '锅炉', '70534862', '压力表', '压力表', '70534862', '1806', '0', '', '2018-01-15', null, '2019-01-15', '17', '369', '532', '86', null, '4', '67', '54', '2', null, null, null, null, null, '281', '55');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '839', '2018-01-15 10:38:32', '锅炉', '70534608', '压力表', '压力表', '70534608', '1806', '0', '', '2018-01-15', null, '2019-01-15', '17', '369', '532', '86', null, '4', '67', '54', '2', null, null, null, null, null, '282', '55');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '840', '2018-01-15 10:42:47', '锅炉', '02000105', '压力表', '压力表', 'QL077060', 'QL077060', '0', '', '2018-01-15', null, '2019-01-15', '17', '369', '532', '125', null, '4', '71', '54', '2', null, null, null, null, null, '283', '55');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '841', '2018-01-15 10:46:00', '锅炉', 'QL077158', '压力表', '压力表', '02000105', 'QL077158', '0', '', '2018-01-15', null, '2019-01-15', '17', '369', '532', '125', null, '4', '71', '54', '2', null, null, null, null, null, '284', '55');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '842', '2018-01-16 11:01:34', '厂区', '京制01080298-01', '压力表', '压力表', '1038317-5', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '843', '2018-01-16 11:03:27', '厂区', '京制01080298-01', '压力表', '压力表', '1212299-5', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '844', '2018-01-16 11:03:56', '厂区', '京制01080298-01', '压力表', '压力表', '1212301-5', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '845', '2018-01-16 11:04:15', '厂区', '京制01080298-01', '压力表', '压力表', '1212156-3', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '846', '2018-01-16 11:04:41', '厂区', '京制01080298-01', '压力表', '压力表', '1212175-3', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '847', '2018-01-16 11:05:00', '厂区', '京制01080298-01', '压力表', '压力表', '1212189-3', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '848', '2018-01-16 11:05:14', '厂区', '京制01080298-01', '压力表', '压力表', '1212171-3', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '849', '2018-01-16 11:05:31', '厂区', '京制01080298-01', '压力表', '压力表', '1212106-3', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '850', '2018-01-16 11:05:56', '厂区', '京制01080298-01', '压力表', '压力表', '1212320-5', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '538', null, '4', '71', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '851', '2018-01-16 11:07:41', '厂区', '京制01080298-01', '压力表', '压力表', '072562-6', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '20', '374', '537', '538', null, '4', '67', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '852', '2018-01-16 11:08:08', '厂区', '京制01080298-01', '压力表', '压力表', '072596-6', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '20', '374', '537', '538', null, '4', '67', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '853', '2018-01-16 11:08:19', '厂区', '京制01080298-01', '压力表', '压力表', '072519', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '20', '374', '537', '538', null, '4', '67', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '854', '2018-01-16 11:09:06', '厂区', '京制01080298-01', '压力表', '压力表', '1215301-2', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '18', '374', '537', '538', null, '4', '66', '54', '4', null, null, null, null, null, '285', '57');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '855', '2018-01-16 11:09:24', '厂区', '京制01080298-01', '压力表', '压力表', '1215329-2', 'Y-100', '0', '', '2018-01-16', null, '2019-01-16', '18', '374', '537', '538', null, '4', '66', '54', '4', null, null, null, null, null, '285', '57');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '856', '2018-01-16 11:30:14', '厂区', '沪制01070009', '压力表', '压力表', '17-1062', 'YN-100', '0', '', '2018-01-16', null, '2019-01-16', '17', '374', '537', '406', null, '4', '69', '54', '4', null, null, null, null, null, '285', '56');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '857', '2018-01-16 14:17:08', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117060048', 'CS30D2223F', '0', '', '2018-01-17', null, '2019-01-16', '61', '352', '511', '528', null, '25', '260', '258', '1', null, null, null, null, null, '286', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '858', '2018-01-16 14:18:10', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117060001', 'CS30D2223F', '0', '', '2018-01-17', null, '2019-01-16', '61', '352', '511', '528', null, '25', '260', '258', '1', null, null, null, null, null, '286', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '859', '2018-01-16 14:18:47', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117060047', 'CS30D2223F', '0', '', '2018-01-17', null, '2019-01-16', '61', '352', '511', '528', null, '25', '260', '258', '1', null, null, null, null, null, '286', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '860', '2018-01-16 14:19:05', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TQ117060002', 'CS30D2223F', '0', '', '2018-01-17', null, '2019-01-16', '61', '352', '511', '528', null, '25', '260', '258', '1', null, null, null, null, null, '286', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '861', '2018-01-16 14:37:59', '药房', '2017', '戥称', '戥称', '20160101', '500g', '-1', '', null, null, '2019-01-16', '57', '326', '483', '539', null, '21', '223', '223', '1', null, null, null, null, null, '287', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '862', '2018-01-16 14:42:28', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'D0240203110010111186', 'CS42D4440F', '0', '', '2018-01-17', null, '2019-01-16', '61', '353', '512', '59', null, '25', '260', '258', '1', null, null, null, null, null, '288', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '863', '2018-01-16 14:44:02', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TE117080015', 'CS42J4243G', '0', '', '2018-01-17', null, '2019-01-16', '61', '353', '512', '528', null, '25', '260', '258', '1', null, null, null, null, null, '288', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '864', '2018-01-16 14:44:45', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TE117080016', 'CS42J4243G', '0', '', '2018-01-17', null, '2019-01-16', '61', '353', '512', '528', null, '25', '260', '258', '1', null, null, null, null, null, '288', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '865', '2018-01-16 14:49:17', '赤峰市阿鲁科尔沁旗坤都镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900282', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '354', '513', '59', null, '25', '260', '258', '1', null, null, null, null, null, '289', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '866', '2018-01-16 14:55:06', '赤峰市阿鲁科尔沁旗坤都镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900270', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '354', '513', '59', null, '25', '260', '258', '1', null, null, null, null, null, '289', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '867', '2018-01-16 15:04:31', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900141', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '356', '515', '59', null, '25', '260', '258', '1', null, null, null, null, null, '290', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '868', '2018-01-16 15:08:45', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX960267', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '356', '515', '59', null, '25', '260', '258', '1', null, null, null, null, null, '290', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '869', '2018-01-16 15:11:01', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TP117080016', 'CS30J2223G', '0', '', '2018-01-17', null, '2019-01-16', '61', '356', '515', '528', null, '25', '260', '258', '1', null, null, null, null, null, '290', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '870', '2018-01-16 15:12:07', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TE117080013', 'CS42J4243G', '0', '', '2018-01-17', null, '2019-01-16', '61', '356', '515', '528', null, '25', '260', '258', '1', null, null, null, null, null, '290', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '871', '2018-01-16 15:23:16', '赤峰市阿鲁科尔沁旗先锋乡', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900222', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '355', '514', '59', null, '25', '260', '258', '1', null, null, null, null, null, '291', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '872', '2018-01-16 15:23:59', '赤峰市阿鲁科尔沁旗先锋乡', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900266', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '355', '514', '59', null, '25', '260', '258', '1', null, null, null, null, null, '291', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '873', '2018-01-16 15:27:58', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900115', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '357', '516', '59', null, '25', '260', '258', '1', null, null, null, null, null, '292', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '874', '2018-01-16 15:30:53', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'SC01R3A001', 'SK52ZF222A', '0', '', '2018-01-17', null, '2019-01-16', '61', '357', '516', '413', null, '25', '260', '258', '1', null, null, null, null, null, '292', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '875', '2018-01-16 15:31:50', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'SC00R3A003', 'SK52ZF222A', '0', '', '2018-01-17', null, '2019-01-16', '61', '357', '516', '413', null, '25', '260', '258', '1', null, null, null, null, null, '292', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '876', '2018-01-16 15:42:34', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'JK440323110098432', 'CS32J1110F', '0', '', '2018-01-17', null, '2019-01-16', '61', '358', '517', '59', null, '25', '260', '258', '1', null, null, null, null, null, '293', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '877', '2018-01-16 15:43:03', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'JK440323110098437', 'CS32J1110F', '0', '', '2018-01-17', null, '2019-01-16', '61', '358', '517', '59', null, '25', '260', '258', '1', null, null, null, null, null, '293', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '878', '2018-01-16 15:43:57', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WC21031111001114126', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '358', '517', '59', null, '25', '260', '258', '1', null, null, null, null, null, '293', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '879', '2018-01-16 15:46:15', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900084', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '359', '518', '59', null, '25', '260', '258', '1', null, null, null, null, null, '294', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '880', '2018-01-16 15:47:56', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'SB00043008', 'SK52ZF212A', '0', '', '2018-01-17', null, '2019-01-16', '61', '359', '518', '413', null, '25', '260', '258', '1', null, null, null, null, null, '294', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '881', '2018-01-16 15:54:21', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WQ240203AA0010103655', 'CS46D2220F', '0', '', '2018-01-17', null, '2019-01-16', '61', '360', '519', '59', null, '25', '260', '258', '1', null, null, null, null, null, '295', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '882', '2018-01-16 15:55:54', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WQ240203AA0010114002', 'CS46D2220F', '0', '', '2018-01-17', null, '2019-01-16', '61', '360', '519', '59', null, '25', '260', '258', '1', null, null, null, null, null, '295', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '883', '2018-01-16 15:56:48', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WQ240203AA0010103656', 'CS46D2220F', '0', '', '2018-01-17', null, '2019-01-16', '61', '360', '519', '59', null, '25', '260', '258', '1', null, null, null, null, null, '295', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '884', '2018-01-16 16:00:40', '赤峰市阿鲁科尔沁旗绍根镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WQ240203AA0010103658', 'CS46D2220F', '0', '', '2018-01-17', null, '2019-01-16', '61', '360', '519', '59', null, '25', '260', '258', '1', null, null, null, null, null, '295', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '885', '2018-01-16 16:04:55', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'Y71CQ2C002', 'SK56QF424K', '0', '', '2018-01-17', null, '2019-01-16', '61', '361', '520', '413', null, '25', '260', '258', '1', null, null, null, null, null, '296', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '886', '2018-01-16 16:05:27', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'Y71CQ2C001', 'SK56QF424K', '0', '', '2018-01-17', null, '2019-01-16', '61', '361', '520', '413', null, '25', '260', '258', '1', null, null, null, null, null, '296', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '887', '2018-01-16 16:06:22', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'Y71FQ2C006', 'SK56QF424K', '0', '', '2018-01-17', null, '2019-01-16', '61', '361', '520', '413', null, '25', '260', '258', '1', null, null, null, null, null, '296', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '888', '2018-01-16 16:06:49', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'Y71FQ2C004', 'SK56QF424K', '0', '', '2018-01-17', null, '2019-01-16', '61', '361', '520', '413', null, '25', '260', '258', '1', null, null, null, null, null, '296', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '889', '2018-01-16 16:09:35', '赤峰市阿鲁科尔沁旗双胜镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900112', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '362', '521', '59', null, '25', '260', '258', '1', null, null, null, null, null, '297', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '890', '2018-01-16 16:10:07', '赤峰市阿鲁科尔沁旗双胜镇', '豫制00000198号', '税控燃油加油机', '正星科技有限公司', 'WCDX900133', 'CS46J2220G', '0', '', '2018-01-17', null, '2019-01-16', '61', '362', '521', '59', null, '25', '260', '258', '1', null, null, null, null, null, '297', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '891', '2018-01-16 16:13:48', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TP016100007', 'CS46J2223G', '0', '', '2018-01-17', null, '2019-01-16', '61', '363', '522', '528', null, '25', '260', '258', '1', null, null, null, null, null, '298', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '892', '2018-01-16 16:14:26', '赤峰市阿鲁科尔沁旗天山镇', '豫制00000198号', 'IC税控燃油加油机', '正星科技股份有限公司', 'TP016100008', 'CS46J2223G', '0', '', '2018-01-17', null, '2019-01-16', '61', '363', '522', '528', null, '25', '260', '258', '1', null, null, null, null, null, '298', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '893', '2018-01-16 16:16:11', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'SC01043013', 'SK52ZF222A', '0', '', '2018-01-17', null, '2019-01-16', '61', '363', '522', '413', null, '25', '260', '258', '1', null, null, null, null, null, '298', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '894', '2018-01-16 16:17:11', '赤峰市阿鲁科尔沁旗天山镇', '京制00000103号', 'IC税控燃油加油机', '北京三盈联合石油技术有限公司', 'SB00043009', 'SK52ZF212A', '0', '', '2018-01-17', null, '2019-01-16', '61', '363', '522', '413', null, '25', '260', '258', '1', null, null, null, null, null, '298', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '908', '2018-01-17 14:14:27', '赤峰市松山区妇幼保健所供应室', '鲁制03000011', '夹层压力表', '夹层压力表', '20141200812', '无', '0', '', '2018-01-17', null, '2019-01-17', '17', '193', '268', '187', null, '4', '70', '55', '2', null, null, null, null, null, '312', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '909', '2018-01-17 14:15:16', '赤峰市松山区妇幼保健所供应室', '鲁制03000011', '内室压力表', '内室压力表', '20141111110', '无', '0', '', '2018-01-17', null, '2019-01-17', '17', '193', '268', '187', null, '4', '70', '55', '2', null, null, null, null, null, '312', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '910', '2018-01-17 14:15:50', '赤峰市松山区妇幼保健所供应室', '鲁制03000011', '内室压力表', '内室压力表', '20141111156', '无', '0', '', '2018-01-17', null, '2019-01-17', '17', '193', '268', '187', null, '4', '70', '55', '2', null, null, null, null, null, '312', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '911', '2018-01-17 14:21:35', '赤峰市松山区妇幼保健所供应室', '03295082', '内室压力表', '内室压力表', '2018001', 'PGE232.100', '0', '', '2018-01-17', null, '2019-01-17', '17', '193', '268', '543', null, '4', '70', '55', '2', null, null, null, null, null, '312', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '912', '2018-01-17 14:21:51', '赤峰市松山区妇幼保健所供应室', '03295082', '内室压力表', '内室压力表', '2018002', 'PGE232.100', '0', '', '2018-01-17', null, '2019-01-17', '17', '193', '268', '543', null, '4', '70', '55', '2', null, null, null, null, null, '312', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '913', '2018-01-17 14:22:24', '赤峰市松山区妇幼保健所供应室', '03295082', '夹层压力表', '夹层压力表', '2018003', 'PGE232.100', '0', '', '2018-01-17', null, '2019-01-17', '17', '193', '268', '543', null, '4', '70', '55', '2', null, null, null, null, null, '312', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '914', '2018-01-17 15:54:12', '阿鲁科尔沁旗巴彦花镇五一村', '豫制00000198', '税控燃油加油机', '税控燃油加油机', 'WC817050003', 'CS46J2220G', '0', '', '2018-01-18', null, '2019-01-17', '61', '347', '506', '59', null, '25', '264', '261', '1', null, null, null, null, null, '313', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '915', '2018-01-17 15:57:25', '阿鲁科尔沁旗巴彦花镇五一村', '豫制00000198', '税控燃油加油机', '税控燃油加油机', 'WC817050001', 'CS46J2220G', '0', '', '2018-01-18', null, '2019-01-17', '61', '347', '506', '59', null, '25', '264', '261', '1', null, null, null, null, null, '314', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '916', '2018-01-17 16:06:28', '阿鲁科尔沁旗巴彦花镇东沙布台村', '豫制00000198', '税控燃油加油机', '税控燃油加油机', '3K440323110014032733', 'CS32J1110F', '-1', '', null, null, '2019-01-17', '61', '339', '497', '59', null, '25', '264', '261', '1', null, null, null, null, null, '315', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '917', '2018-01-17 16:07:35', '阿鲁科尔沁旗巴彦花镇东沙布台村', '豫制00000198', '税控燃油加油机', '税控燃油加油机', '3K440323110012078245', 'CS32J1110F', '0', '', '2018-01-22', null, '2019-01-17', '61', '339', '497', '59', null, '25', '264', '261', '1', null, null, null, null, null, '316', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '918', '2018-01-17 16:08:06', '阿鲁科尔沁旗巴彦花镇东沙布台村', '豫制00000198', '税控燃油加油机', '税控燃油加油机', '3K440323110014032733', 'CS32J1110F', '0', '', '2018-01-22', null, '2019-01-17', '61', '339', '497', '59', null, '25', '264', '261', '1', null, null, null, null, null, '316', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '919', '2018-01-17 16:10:08', '阿鲁科尔沁旗巴彦花镇东沙布台村', '豫制00000198', '税控燃油加油机', '税控燃油加油机', '3K10500043', 'CS32J1110F', '0', '', '2018-01-22', null, '2019-01-17', '61', '339', '497', '59', null, '25', '264', '261', '1', null, null, null, null, null, '317', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '920', '2018-01-17 16:12:00', '阿鲁科尔沁旗巴彦花镇东沙布台村', '京制00000287', '税控燃油加油机', '税控燃油加油机', '06451653', 'JT1200GB', '0', '', '2018-01-22', null, '2019-01-17', '61', '339', '497', '545', null, '25', '264', '261', '1', null, null, null, null, null, '317', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '921', '2018-01-17 16:33:52', '公司门前', '冀制00000346号', '地秤', '地秤', '2009.0911', 'SCS-150', '0', '', '2018-01-17', null, '2019-01-17', '55', '375', '544', '546', null, '19', '190', '181', '1', null, null, null, null, null, '318', '60');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '922', '2018-01-17 16:49:10', '公司内', '沪制00000001', '天平', '天平', '13167', 'TG628A', '0', '', '2018-01-17', null, '2019-01-17', '4', '375', '544', '547', null, '2', '5', '2', '1', null, null, null, null, null, '319', '59');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '923', '2018-01-18 09:00:56', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', '8343', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '320', '63');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '924', '2018-01-18 09:05:02', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', 'A1117', 'Y-100', '-2', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '925', '2018-01-18 09:06:51', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', '4504', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '322', '63');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '926', '2018-01-18 09:08:09', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', '1139', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '323', '63');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '927', '2018-01-18 09:09:36', '液化气站泵房', '21-25', '压力表', '压力表', '11603', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '324', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '928', '2018-01-18 09:11:06', '鸿泰花园瓶组间', '21-25', '压力表', '压力表', '25193', 'Y-100', '-2', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '929', '2018-01-18 09:12:25', '液化气站管道', '21-25', '压力表', '压力表', '2899', 'Y-100', '-2', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '169', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '930', '2018-01-18 09:14:38', '液化气站充装间', '21-25', '压力表', '压力表', '03-10-18-1B', 'Y-100', '-2', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '548', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '931', '2018-01-18 09:19:26', '鸿泰花园瓶组间', '21-25', '压力表', '压力表', '11611', 'Y-100', '-1', '', null, null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '932', '2018-01-18 09:21:12', '鸿泰花园瓶组间', '21-25', '压力表', '压力表', '601-1', 'Y-100', '-1', '', null, null, '2019-01-18', '105', '321', '477', '541', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '933', '2018-01-18 09:22:38', '液化气站50立储罐', '21-25', '压力表', '压力表', '6254', 'Y-100', '-1', '', null, null, '2019-01-18', '105', '321', '477', '541', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '934', '2018-01-18 09:25:05', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', '2413', 'Y-100', '-1', '', null, null, '2019-01-18', '105', '321', '477', '541', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '935', '2018-01-18 09:26:46', '液化气站管道', '21-25', '压力表', '压力表', '07063A', 'Y-100', '-1', '', null, null, '2019-01-18', '105', '321', '477', '548', null, '35', '396', '395', '4', null, null, null, null, null, '321', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '936', '2018-01-18 09:38:29', '省际通道573KM+40m处东向西1车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '937', '2018-01-18 09:39:05', '省际通道573KM+40m处东向西2车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '938', '2018-01-18 09:44:25', '省际通道573KM+40m处西向东1车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '939', '2018-01-18 09:44:37', '省际通道573KM+40m处西向东2车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '940', '2018-01-18 09:49:48', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', 'A1117', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '326', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '941', '2018-01-18 09:50:03', '省际通道619KM处西向东1车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '942', '2018-01-18 09:50:22', '省际通道619KM处西向东2车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '943', '2018-01-18 09:51:06', '鸿泰花园瓶组间', '21-25', '压力表', '压力表', '25193', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '540', null, '35', '396', '395', '4', null, null, null, null, null, '327', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '944', '2018-01-18 09:51:31', '省际通道618KM+840m处东向西1车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '945', '2018-01-18 09:51:41', '省际通道618KM+840m处东向西2车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '946', '2018-01-18 09:52:16', '液化气站管道', '21-25', '压力表', '压力表', '2899', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '169', null, '35', '396', '395', '4', null, null, null, null, null, '328', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '947', '2018-01-18 09:53:39', '液化气站充装间', '21-25', '压力表', '压力表', '03-10-18-1B', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '548', null, '35', '396', '395', '4', null, null, null, null, null, '329', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '948', '2018-01-18 09:54:45', '鸿泰花园瓶组间', '21-25', '压力表', '压力表', '11611', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', null, null, '35', '396', '395', '4', null, null, null, null, null, '330', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '949', '2018-01-18 09:55:28', '省际通道627Km+480m处南向北1车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '950', '2018-01-18 09:55:45', '省际通道627Km+480m处南向北2车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '951', '2018-01-18 09:55:59', '卸油泵房', '鲁制02000105号', '压力真空表', '压力真空表', 'NO.PL10.3744', 'YF-100', '-2', '', '2018-01-18', null, '2019-01-18', '17', '180', '248', '97', null, '4', '66', '54', '4', null, null, null, null, null, '331', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '952', '2018-01-18 09:56:04', '省际通道627Km+480m处北向南1车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '953', '2018-01-18 09:56:08', '鸿泰花园瓶组间', '21-25', '压力表', '压力表', '601-1', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '541', null, '35', '396', '395', '4', null, null, null, null, null, '332', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '954', '2018-01-18 09:56:20', '省际通道627Km+480m处北向南2车道', '浙制00000926号-2', '固定式机动车雷达测速仪', '固定式机动车雷达测速仪', '2J02404PAJ00157', 'DH-ITARD-024SA', '-1', '', null, null, '2019-01-18', '115', '331', '489', '467', null, '41', '462', '464', '4', null, null, null, null, null, '325', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '955', '2018-01-18 09:57:25', '液化气站50立储罐', '21-25', '压力表', '压力表', '6254', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '541', null, '35', '396', '395', '4', null, null, null, null, null, '333', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '956', '2018-01-18 09:58:45', '鸿泰小区瓶组间', '21-25', '压力表', '压力表', '2413', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '541', null, '35', '396', '395', '4', null, null, null, null, null, '334', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '957', '2018-01-18 10:00:09', '液化气站管道', '21-25', '压力表', '压力表', '07063A', 'Y-100', '0', '', '2018-01-18', null, '2019-01-18', '105', '321', '477', '548', null, '35', '396', '395', '4', null, null, null, null, null, '335', '64');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '958', '2018-01-18 15:50:04', '锅炉', '01090005', '压力表', '压力表', '12846464', 'Y-150', '0', '', '2018-01-22', null, '2019-01-18', '105', '340', '498', '549', null, '35', '396', '395', '4', null, null, null, null, null, '336', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '959', '2018-01-18 16:15:46', '锅炉', '01090005', '压力表', '压力表', '14382146', 'Y-150', '0', '', '2018-01-22', null, '2019-01-18', '105', '340', '498', '549', null, '35', '396', '395', '4', null, null, null, null, null, '337', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '960', '2018-01-18 16:19:58', '锅炉', '01090005', '压力表', '压力表', '12846466', 'Y-150', '0', '', '2018-01-22', null, '2019-01-18', '105', '340', '498', '549', null, '35', '396', '395', '4', null, null, null, null, null, '338', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '961', '2018-01-18 16:31:33', '5#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1749', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '962', '2018-01-18 16:33:32', '6#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1749', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '963', '2018-01-18 16:34:21', '7#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1750', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '964', '2018-01-18 16:35:16', '8#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1750', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '965', '2018-01-18 16:35:31', '9#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1751', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '966', '2018-01-18 16:35:42', '10#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1751', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '967', '2018-01-18 16:35:55', '11#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1752', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '968', '2018-01-18 16:36:08', '12#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1752', 'CNG050S290NQEPMZZZ', '-2', '', '2018-01-19', null, '2019-01-18', '90', '310', '405', '550', null, '33', '367', '377', '1', '2', null, null, null, null, '339', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '969', '2018-01-20 16:29:17', '厂区', 'MC01060102', '压力表', '压力表', '1227', 'Y-150', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '58', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '970', '2018-01-20 16:29:36', '厂区', 'MC01060102', '压力表', '压力表', '1228', 'Y-150', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '58', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '971', '2018-01-20 16:30:18', '厂区', 'MC01060102', '压力表', '压力表', '1230', 'Y-150', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '58', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '972', '2018-01-20 16:31:00', '厂区', 'MC01060102', '压力表', '压力表', '471', 'Y-250', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '58', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '973', '2018-01-20 16:31:21', '厂区', 'MC01060102', '压力表', '压力表', '470', 'Y-250', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '58', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '974', '2018-01-20 16:32:01', '厂区', 'MC01060102', '压力表', '压力表', '1420', 'Y-250', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '67', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '975', '2018-01-20 16:32:16', '厂区', 'MC01060102', '压力表', '压力表', '1540', 'Y-250', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '67', '54', '4', null, null, null, null, null, '340', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '976', '2018-01-20 16:55:13', '厂区', 'MC01060102', '压力表', '压力表', '209', 'Y-60', '0', '', '2018-01-22', null, '2019-01-20', '20', '374', '537', '165', null, '4', '71', '54', '4', null, null, null, null, null, '341', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '977', '2018-01-20 16:55:30', '厂区', 'MC01060102', '压力表', '压力表', '205', 'Y-60', '0', '', '2018-01-22', null, '2019-01-20', '20', '374', '537', '165', null, '4', '71', '54', '4', null, null, null, null, null, '341', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '978', '2018-01-20 16:55:45', '厂区', 'MC01060102', '压力表', '压力表', '214', 'Y-60', '0', '', '2018-01-22', null, '2019-01-20', '20', '374', '537', '165', null, '4', '71', '54', '4', null, null, null, null, null, '341', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '979', '2018-01-20 16:56:32', '厂区', 'MC01060102', '压力表', '压力表', '220', 'Y-100', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '165', null, '4', '66', '54', '4', null, null, null, null, null, '341', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '980', '2018-01-20 17:05:43', '厂区', 'MC01060102', '压力表', '压力表', '456', 'Y-150', '0', '', '2018-01-22', null, '2019-01-20', '17', '374', '537', '85', null, '4', '71', '54', '4', null, null, null, null, null, '342', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '981', '2018-01-21 14:43:46', '养殖场', '苏制0000742-1', '地秤', 'SCS系列数字式汽车衡', 'YHJ20160526', 'SCS-120', '0', '', '2018-01-22', null, '2019-01-21', '55', '340', '498', '553', null, '19', '185', '181', '1', null, null, null, null, null, '343', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '982', '2018-01-22 11:05:48', '5#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1749', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '983', '2018-01-22 11:06:17', '6#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1749', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '984', '2018-01-22 11:06:55', '7#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1750', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '985', '2018-01-22 11:07:11', '8#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1750', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '986', '2018-01-22 11:07:25', '9#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1751', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '987', '2018-01-22 11:07:37', '10#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1751', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '988', '2018-01-22 11:07:51', '11#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1752', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '989', '2018-01-22 11:08:02', '12#加气枪', 'MC（苏）制01000339号', 'CNG加气机', '质量流量计', 'CNG1752', 'CNG050S290NQEPMZZZ', '0', '', '2018-01-22', null, '2019-01-22', '90', '310', '405', '550', null, '33', '367', '377', '1', null, null, null, null, null, '344', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '990', '2018-01-22 15:46:20', '赤峰市林西县林西镇南门外民营工业经济创业园化验室', '22-01-130555', 'phdz-1', 'phdz-1', '08001', 'phdz-1', '0', '', '2018-01-23', null, '2019-01-22', '174', '323', '479', '555', null, '70', '571', '570', '2', null, null, null, null, null, '345', '65');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '991', '2018-01-22 16:07:43', '赤峰市林西县林西镇南门外民营工业经济创业园化验室', '林计z字1485', '分析天平', '分析天平', '141560', 'tg328b', '0', '', '2018-01-23', null, '2019-01-22', '16', '323', '479', '556', null, '3', '51', '14', null, null, null, null, null, null, '346', '66');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '994', '2018-01-22 16:36:01', '赤峰市林西县林西镇南门外民营工业经济创业园化验室', '林计字14644', '乙炔表', '乙炔表', '13308', '1.6级', '0', '', '2018-01-23', null, '2019-01-22', '105', '323', '479', '357', null, '35', '396', '395', null, null, null, null, null, null, '349', '66');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '995', '2018-01-23 10:50:35', '养殖场', '苏制0000742-1', '地秤', 'SCS系列数字式汽车衡', 'YHJ20160526', 'SCS-3', '0', '', '2018-01-23', null, '2019-01-23', '55', '340', '498', '553', null, '19', '756', '181', '1', null, null, null, null, null, '350', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '997', '2018-01-23 14:53:23', '阿旗巴彦诺尔加油站', 'CNEX11.1433', '税控燃油加油机', '税控燃油加油机', '3H014070013', 'CS32J2120F', '0', '', '2018-01-24', null, '2019-01-23', '61', '378', '554', '59', null, '25', '264', '261', '1', null, null, null, null, null, '352', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '998', '2018-01-23 15:01:02', '阿旗巴彦诺尔加油站', 'CNEX11.1433', '税控燃油加油机', '税控燃油加油机', '3H014070014', 'CS32J2120F', '0', '', '2018-01-24', null, '2019-01-23', '61', '378', '554', '59', null, '25', '264', '261', '1', null, null, null, null, null, '353', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '999', '2018-01-23 15:03:19', '天山镇', '0254', '燃油加油机', '燃油加油机', '123456', 'sd.123', '-1', '', null, null, '2019-01-23', '61', '34', '42', '561', null, '25', '260', '261', '1', null, null, null, null, null, '354', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1001', '2018-01-23 16:16:27', '氧气充装排', '辽字01050103', '压力表', '氧气表', 'N004-20', '2.5级', '-2', '', '2018-01-24', null, '2019-01-23', '262', '379', '558', '560', null, '37', '439', '456', '4', null, null, null, null, null, '356', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1002', '2018-01-23 16:19:41', '氧气充装排', '鲁制02000096', '压力表', '氧气表', 'N0470013', '2.5级', '0', '', '2018-01-24', null, '2019-01-23', '262', '379', '558', '562', null, '37', '439', '456', '4', null, null, null, null, null, '357', '67');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1003', '2018-01-23 16:21:20', '氧气充装排', '鲁制02000105', '压力表', '氧气表', 'N00.04.6037', '2.5级', '0', '', '2018-01-24', null, '2019-01-23', '262', '379', '558', '125', null, '37', '439', '456', '4', null, null, null, null, null, '357', '67');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1004', '2018-01-23 16:22:15', '氧气充装排', '鲁制02000096', '压力表', '氧气表', 'N0.C70004', '2.5级', '0', '', '2018-01-24', null, '2019-01-23', '262', '379', '558', '562', null, '37', '439', '456', '4', null, null, null, null, null, '357', '67');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1005', '2018-01-23 16:22:58', '氧气充装排', '鲁制02000105', '压力表', '氧气表', 'N0.0.04.6038', '2.5级', '0', '', '2018-01-24', null, '2019-01-23', '262', '379', '558', '125', null, '37', '439', '456', '4', null, null, null, null, null, '357', '67');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1006', '2018-01-23 16:23:47', '氧气充装排', '鲁制02000096', '压力表', '氧气表', 'N0.C70020', '2.5级', '0', '', '2018-01-24', null, '2019-01-23', '262', '379', '558', '562', null, '37', '439', '456', '4', null, null, null, null, null, '357', '67');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1007', '2018-01-23 16:27:05', '乙炔充装排', '辽字01050103', '压力表', '压力表', 'N010-71', '0-4', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '560', null, '35', '404', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1008', '2018-01-23 16:27:37', '乙炔充装排', '辽字01050103', '压力表', '压力表', 'N0472', '0-4', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '560', null, '35', '404', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1009', '2018-01-23 16:29:14', '乙炔充装排', '辽制06000115', '压力表', '压力表', 'N020068027', '0-4', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '563', null, '35', '404', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1010', '2018-01-23 16:30:18', '乙炔充装排', '01000324', '压力表', '压力表', 'N02008.08-18', '0-4', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '564', null, '35', '404', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1011', '2018-01-23 16:32:11', '压缩机一级排气', '鲁制02000105', '压力表', '压力表', 'N0.PL.07.0967', '0-0.6', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '125', null, '35', '400', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1012', '2018-01-23 16:33:44', '压缩机一级排气', '辽制00000228', '压力表', '压力表', 'N0201101411', '0-0.6', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '565', null, '35', '400', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1013', '2018-01-23 16:35:08', '压缩机二级排气', '鲁制02000105', '压力表', '压力表', 'N0.PL.073659', '0-1.6', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '125', null, '35', '407', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1014', '2018-01-23 16:36:36', '压缩机二级排气', '鲁制02000105', '压力表', '压力表', 'N0.T-12-0180B', '0-1.6', '0', '', '2018-01-24', null, '2019-01-23', '105', '379', '558', '125', null, '35', '407', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1015', '2018-01-23 16:38:32', '压缩机超压测量', '辽制01050103', '压力表', '压力表', 'N02014-9-55', '0-4', '0', '', '2018-01-24', null, '2019-01-23', '106', '379', '558', '560', null, '35', '413', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1016', '2018-01-23 16:39:25', '压缩机超压测量', '辽字01050103', '压力表', '压力表', 'N02011-1-211', '0-4', '0', '', '2018-01-24', null, '2019-01-23', '106', '379', '558', '560', null, '35', '413', '395', '4', null, null, null, null, null, '357', '68');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1017', '2018-01-23 16:43:08', '压缩机负压测量', '辽字01050103', '压力表', '压力表', 'N02011-1-259', '-0.1-0.06', '0', '', '2018-01-24', null, '2019-01-23', '257', '379', '558', '560', null, '4', '60', '55', '4', null, null, null, null, null, '357', '67');
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1018', '2018-01-23 16:51:06', '锅炉房', 'NOHR666027718', '压力表', '压力表', 'NOHR666027718', '0-1.6', '-1', '', null, null, '2019-01-23', '17', '380', '559', '566', null, '4', null, null, '4', null, null, null, null, null, '358', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1019', '2018-01-23 16:55:02', '锅炉房', 'NOHC67582111905', '压力表', '压力表', 'NOHC67582111905', '0-1.6', '-1', '', null, null, '2019-01-23', '17', '380', '559', '566', null, '4', null, null, '1', null, null, null, null, null, '359', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1020', '2018-01-23 16:55:32', '鸿泰小区瓶组间', '21-51', '压力表', '压力表', '6464', 'Y-100', '-1', '', null, null, '2019-01-23', '105', '321', '477', '540', null, '35', '407', '395', '4', null, null, null, null, null, '360', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1021', '2018-01-23 16:57:06', '液化气站管道', '21-51', '压力表', '压力表', '1102', 'Y-100', '-1', '', null, null, '2019-01-23', '105', '321', '477', '169', null, '35', '407', '395', '4', null, null, null, null, null, '361', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1022', '2018-01-23 16:57:42', '车间', 'NOHR66602770739', '压力表', '压力表', 'NOHR66602770739', '0-1.6', '-1', '', null, null, '2019-01-23', '17', '380', '559', '566', null, '4', null, null, '4', null, null, null, null, null, '362', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1023', '2018-01-23 16:58:28', '液化气站50立储罐', '21-51', '压力表', '压力表', '9236', 'Y-100', '-1', '', null, null, '2019-01-23', '105', '321', '477', '541', null, '35', '407', '395', '4', null, null, null, null, null, '363', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1024', '2018-01-23 16:59:29', '车间', 'NOHR66602770510', '压力表', '压力表', 'NOHR66602770510', '0-1.6', '-1', '', null, null, '2019-01-23', '17', '380', '559', '566', null, '4', null, null, '4', null, null, null, null, null, '362', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1025', '2018-01-23 17:00:14', '液化气站管道', '21-51', '压力表', '压力表', '1120', 'Y-100', '-1', '', null, null, '2019-01-23', '105', '321', '477', '567', null, '35', '407', '395', '4', null, null, null, null, null, '364', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1026', '2018-01-23 17:01:15', '车间', 'NOHR66602772074', '压力表', '压力表', 'NOHR66602772074', '0-1.6', '-1', '', null, null, '2019-01-23', '17', '380', '559', '566', null, '4', null, '54', '4', null, null, null, null, null, '362', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1027', '2018-01-23 17:01:53', '车间', 'NOHR66602772027', '压力表', '压力表', 'NOHR66602772027', '0-1.6', '-1', '', null, null, '2019-01-23', '17', '380', '559', '566', null, '4', null, '54', '4', null, null, null, null, null, '362', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1028', '2018-01-23 17:18:36', '控制室', 'HA66561416258', '压力表', '压力表', 'HA66561416258', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1029', '2018-01-23 17:19:17', '控制室', 'HA66562965442', '压力表', '压力表', 'HA66562965442', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1030', '2018-01-23 17:19:44', '控制室', 'HA66562965442', '压力表', '压力表', 'HA65620275113', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1031', '2018-01-23 17:22:08', '浓缩室', 'HA65621103231', '压力表', '压力表', 'HA65621103231', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1032', '2018-01-23 17:22:53', '浓缩室', 'HC67532616715', '压力表', '压力表', 'HC67532616715', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1033', '2018-01-23 17:23:35', '浓缩室', 'HA65610573035', '压力表', '压力表', 'HA65610573035', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1034', '2018-01-23 17:24:14', '浓缩室', 'HC67532616607', '压力表', '压力表', 'HC67532616607', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1035', '2018-01-23 17:25:40', '热风辅机房', 'HA65552799363', '压力表', '压力表', 'HA65552799363', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1036', '2018-01-23 17:26:54', '提取室', 'HA65521496647', '压力表', '压力表', 'HA65521496647', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1037', '2018-01-23 17:28:40', '提取室', '201541191', '压力表', '压力表', '201541191', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '165', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1038', '2018-01-23 17:30:09', '提取室', 'YY0528147', '压力表', '压力表', 'YY0528147', '0-1', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '357', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1039', '2018-01-23 17:33:09', '提取室', 'YE02107280', '压力表', '压力表', 'YE02107280', '0-2.5', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '569', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1040', '2018-01-23 17:34:24', '提取室', 'Y00958527', '压力表', '压力表', 'Y00958527', '0-0.25', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '569', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1041', '2018-01-23 17:35:28', '提取室', 'YE01206764', '压力表', '压力表', 'YE01206764', '0-2.5', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '569', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1042', '2018-01-23 17:36:18', '提取室', 'YE02106768', '压力表', '压力表', 'YE02106768', '0-2.5', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '569', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1043', '2018-01-23 17:38:13', '提取室', '16031028123', '压力表', '压力表', '16031028123', '0-1', '-2', '', '2018-01-24', null, '2019-01-23', '17', '381', '568', '570', null, '4', null, null, '4', null, null, null, null, null, '365', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1044', '2018-01-24 08:20:15', '提取室', 'YE02106431', '压力表', '压力表', 'YE02106431', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '571', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1045', '2018-01-24 08:21:55', '制粒室', 'HC66611308134', '压力表', '压力表', 'HC66611308134', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '571', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1046', '2018-01-24 08:24:02', '包装室', 'HC66611305950', '压力表', '压力表', 'HC66611305950', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1047', '2018-01-24 08:25:23', '空调机房', 'HC66611300906', '压力表', '压力表', 'HC66611300906', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1048', '2018-01-24 08:26:27', '空调机房', 'HC66611305934', '压力表', '压力表', 'HC66611305934', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1049', '2018-01-24 08:27:40', '提取室', 'HC6753261332', '压力表', '压力表', 'HC6753261332', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1050', '2018-01-24 08:29:17', '提取室', 'HC67532613726', '压力表', '压力表', 'HC67532613726', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1051', '2018-01-24 08:29:49', '提取室', 'HC67532613715', '压力表', '压力表', 'HC67532613715', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1052', '2018-01-24 08:30:23', '提取室', 'HC67532613710', '压力表', '压力表', 'HC67532613710', '0-1.6', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '572', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1053', '2018-01-24 08:34:25', '浓缩室', '160213418', '压力表', '压力表', '160213418', '-0.1-0', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '356', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1054', '2018-01-24 08:37:36', '浓缩室', '1608P-19328-08050', '真空压力表', '真空压力表', '1608P-19328-08050', '-0.1-0.5', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '573', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1055', '2018-01-24 08:39:31', '浓缩室', '15032723227', '真空表', '真空表', '15032723227', '-0.1-0', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '570', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1056', '2018-01-24 08:41:14', '浓缩室', 'HC65532661313', '真空表', '真空表', 'HC65532661313', '-0.1-0', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1057', '2018-01-24 08:43:51', '提取室', '119205', '真空表', '真空表', '119205', '-0.1-0', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '574', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1058', '2018-01-24 08:44:56', '提取室', 'HC65531481913', '真空表', '真空表', 'HC65531481913', '-0.1-0', '-2', '', '2018-01-24', null, '2019-01-24', '17', '381', '568', '71', null, '4', null, null, '4', null, null, null, null, null, '366', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1059', '2018-01-24 09:22:16', '阿鲁科尔沁旗省际大通道与陶海公路交汇处', '粤制00000004号', '加油机', '税控燃油加油机', '1110873Q', 'THS2224Di', '-1', '', null, null, '2019-01-24', '61', '350', '509', '529', null, '25', '263', '261', '1', null, null, null, null, null, '367', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1061', '2018-01-24 09:33:48', '阿鲁科尔沁旗省际大通道与陶海公路交汇处', '粤制00000004号', '加油机', '税控燃油加油机', '1110867Q', 'THS2224Di', '-1', '', null, null, '2019-01-24', '61', '350', '509', '529', null, '25', '257', '261', '1', null, null, null, null, null, '369', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1062', '2018-01-24 09:34:47', '阿鲁科尔沁旗省际大通道与陶海公路交汇处', '粤制00000004号', '加油机', '税控燃油加油机', '1110870Q', 'THS2224Di', '-1', '', null, null, '2019-01-24', '61', '350', '509', '529', null, '25', '257', '261', '1', null, null, null, null, null, '369', null);
INSERT INTO `instrument_employment_info` VALUES ('mandatory', '1063', '2018-01-24 09:37:47', '阿鲁科尔沁旗省际大通道与陶海公路交汇处', '粤制00000004号', '加油机', '税控燃油加油机', '1110871Q', 'THS2224Di', '-1', '', null, null, '2019-01-24', '61', '350', '509', '529', null, '25', '257', '261', '1', null, null, null, null, null, '369', null);

-- ----------------------------
-- Table structure for `instrument_first_level_type`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_first_level_type`;
CREATE TABLE `instrument_first_level_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT 'NULL',
  `pinyin` varchar(255) DEFAULT 'NULL',
  `discipline_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjq4mclktkksudw5l5u43gmxua` (`discipline_id`),
  CONSTRAINT `FKjq4mclktkksudw5l5u43gmxua` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_first_level_type
-- ----------------------------
INSERT INTO `instrument_first_level_type` VALUES ('2', '秤', 'cheng', '5');
INSERT INTO `instrument_first_level_type` VALUES ('3', '天平', 'tianping', '5');
INSERT INTO `instrument_first_level_type` VALUES ('4', '砝码', 'fama', '5');
INSERT INTO `instrument_first_level_type` VALUES ('6', '压力表', 'yalibiao', '5');
INSERT INTO `instrument_first_level_type` VALUES ('7', '测试热学', 'ces', '1');
INSERT INTO `instrument_first_level_type` VALUES ('9', '钢卷尺', 'gangjuanchi', '3');
INSERT INTO `instrument_first_level_type` VALUES ('10', '测深钢卷尺', 'ceshengangjuanchi', '3');
INSERT INTO `instrument_first_level_type` VALUES ('11', '面积计', 'mianjiji', '3');
INSERT INTO `instrument_first_level_type` VALUES ('12', '玻璃液体温度计', 'boliyetiwenduji', '1');
INSERT INTO `instrument_first_level_type` VALUES ('13', '体温计', 'tiwenji', '1');
INSERT INTO `instrument_first_level_type` VALUES ('14', '石油闪点温度计', 'shiyoushandianwenduji', '1');
INSERT INTO `instrument_first_level_type` VALUES ('15', '热量计', 'reliangji', '1');
INSERT INTO `instrument_first_level_type` VALUES ('16', '定量包装机', 'dingliangbaozhuangji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('17', '容重器', 'rongzhongqi', '5');
INSERT INTO `instrument_first_level_type` VALUES ('18', '燃油加油机', 'ranyoujiayouji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('19', '酒精计', '', '5');
INSERT INTO `instrument_first_level_type` VALUES ('20', '密度计', '', '5');
INSERT INTO `instrument_first_level_type` VALUES ('21', '糖量计', 'tangliangji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('22', '乳汁计', 'ruzhiji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('23', '煤气表', 'meiqibiao', '5');
INSERT INTO `instrument_first_level_type` VALUES ('24', '水表', 'shuibiao', '5');
INSERT INTO `instrument_first_level_type` VALUES ('25', '液体流量计', '液体liuliangji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('26', '气体流量计', 'qitiliuliangji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('28', '压力表', 'yalibiao', '5');
INSERT INTO `instrument_first_level_type` VALUES ('29', '风压表', 'fengyabiao', '5');
INSERT INTO `instrument_first_level_type` VALUES ('35', '血压计', 'xueyaji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('36', '出租车计价器', 'chuzuchejijiaqi', '5');
INSERT INTO `instrument_first_level_type` VALUES ('37', '测速仪', 'cesuyi', '5');
INSERT INTO `instrument_first_level_type` VALUES ('38', '电能表', 'diannengbiao', '4');
INSERT INTO `instrument_first_level_type` VALUES ('39', '测量互感器', 'celianghuganqi', '4');
INSERT INTO `instrument_first_level_type` VALUES ('40', '绝缘电阻、接地电阻测量仪', 'jueyuandianzujiedidianzuceliangyi', '4');
INSERT INTO `instrument_first_level_type` VALUES ('41', '心、脑电图仪', 'xinnaodiantuyi', '4');
INSERT INTO `instrument_first_level_type` VALUES ('42', '照射量计', 'zhaosheliangji', '8');
INSERT INTO `instrument_first_level_type` VALUES ('43', '电离辐射防护仪', 'dianlifushefanghuyi', '8');
INSERT INTO `instrument_first_level_type` VALUES ('44', '活度计', 'huoduji', '8');
INSERT INTO `instrument_first_level_type` VALUES ('45', '激光能量、功率计', 'jiguangnenglianggonglvji', '7');
INSERT INTO `instrument_first_level_type` VALUES ('46', '超声功率计', 'chaoshenggonglvji', '6');
INSERT INTO `instrument_first_level_type` VALUES ('47', '声级计', 'shengjiji', '6');
INSERT INTO `instrument_first_level_type` VALUES ('48', '听力计', 'tingliji', '6');
INSERT INTO `instrument_first_level_type` VALUES ('49', '有害气体分析仪', 'youhaiqitifenxiyi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('50', '酸度计', 'suanduji', '2');
INSERT INTO `instrument_first_level_type` VALUES ('51', '瓦斯计', 'wasiji', '2');
INSERT INTO `instrument_first_level_type` VALUES ('52', '测汞仪', 'cegongyi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('53', '火焰光度计', 'huoyanguangduji', '2');
INSERT INTO `instrument_first_level_type` VALUES ('54', '分光光度计', 'fenguangguangduji', '2');
INSERT INTO `instrument_first_level_type` VALUES ('55', '比色计', 'biseji', '2');
INSERT INTO `instrument_first_level_type` VALUES ('56', '烟尘、粉尘测量仪', 'yanchenfenchenceliangyi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('57', '水质污染检测仪', 'shuizhiwuranjianceyi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('58', '呼出气体酒精含量探测器', 'huchuqitijiujinghanliangcedingqi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('59', '血球计数器', 'xueqiujishuqi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('60', '屈光度计', 'quguangduji', '7');
INSERT INTO `instrument_first_level_type` VALUES ('61', '电话计时计费装置', 'dianhuajishijifeizhuangzhi', '9');
INSERT INTO `instrument_first_level_type` VALUES ('62', '棉花水分测量仪', 'mianhuashuifenceliangyi', '2');
INSERT INTO `instrument_first_level_type` VALUES ('63', '验光仪', 'yanguangyi', '7');
INSERT INTO `instrument_first_level_type` VALUES ('64', '微波辐射与泄露测量仪', 'weibofusheyuxielouceliangyi', '8');
INSERT INTO `instrument_first_level_type` VALUES ('65', '燃气加气机', 'ranqijiaqiji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('66', '热能表', 'renengbiao', '1');
INSERT INTO `instrument_first_level_type` VALUES ('67', '蒸汽流量计', 'zhengqiliuliangji', '5');
INSERT INTO `instrument_first_level_type` VALUES ('69', '分类一级', 'fenleiyiji', '1');
INSERT INTO `instrument_first_level_type` VALUES ('70', '测试光学_一级', 'ceshiguangxue1', '7');

-- ----------------------------
-- Table structure for `instrument_production`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_production`;
CREATE TABLE `instrument_production` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alert_date` int(11) NOT NULL,
  `certificate_date` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `license_num` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `validity_date` int(11) NOT NULL,
  `accuracy_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `instrument_type_id` bigint(20) DEFAULT NULL,
  `manufacturer_department_id` bigint(20) DEFAULT NULL,
  `measure_scale_id` bigint(20) DEFAULT NULL,
  `specification_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKawohmfm9w63bbjddtj3lh4q8s` (`accuracy_id`),
  KEY `FKg4fuet8qlc1qxepye4m341v4q` (`create_user_id`),
  KEY `FKr3e2sevfit48296um4m3rixah` (`instrument_type_id`),
  KEY `FKmfqpbfybrnnta1jgo6emqj6dk` (`manufacturer_department_id`),
  KEY `FK2d6whc8ky96da67fbox64jy0f` (`measure_scale_id`),
  KEY `FKa46k6migoiytpe8i2hn8241gm` (`specification_id`),
  CONSTRAINT `FK2d6whc8ky96da67fbox64jy0f` FOREIGN KEY (`measure_scale_id`) REFERENCES `measure_scale` (`id`),
  CONSTRAINT `FKa46k6migoiytpe8i2hn8241gm` FOREIGN KEY (`specification_id`) REFERENCES `specification` (`id`),
  CONSTRAINT `FKawohmfm9w63bbjddtj3lh4q8s` FOREIGN KEY (`accuracy_id`) REFERENCES `accuracy` (`id`),
  CONSTRAINT `FKg4fuet8qlc1qxepye4m341v4q` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmfqpbfybrnnta1jgo6emqj6dk` FOREIGN KEY (`manufacturer_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKr3e2sevfit48296um4m3rixah` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_production
-- ----------------------------

-- ----------------------------
-- Table structure for `instrument_production_annual`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_production_annual`;
CREATE TABLE `instrument_production_annual` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `check_date` int(11) NOT NULL,
  `create_time` int(11) NOT NULL,
  `create_user` tinyblob,
  `update_time` int(11) NOT NULL,
  `instrument_production_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9bj1jdt4kg07skc9wgwrxhs` (`instrument_production_id`),
  CONSTRAINT `FKl9bj1jdt4kg07skc9wgwrxhs` FOREIGN KEY (`instrument_production_id`) REFERENCES `instrument_production` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_production_annual
-- ----------------------------

-- ----------------------------
-- Table structure for `instrument_status`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_status`;
CREATE TABLE `instrument_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` int(11) NOT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `instrument_employment_info_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjwa2d3extq3bvsf676mivcoq` (`create_user_id`),
  KEY `FKjin67off6caoji0gwymwtqjlr` (`instrument_employment_info_id`),
  CONSTRAINT `FKhjwa2d3extq3bvsf676mivcoq` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjin67off6caoji0gwymwtqjlr` FOREIGN KEY (`instrument_employment_info_id`) REFERENCES `instrument_employment_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_status
-- ----------------------------

-- ----------------------------
-- Table structure for `instrument_type`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_type`;
CREATE TABLE `instrument_type` (
  `table_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT 'NULL',
  `pinyin` varchar(255) DEFAULT 'NULL',
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `accuracy_display_name_id` bigint(20) DEFAULT NULL,
  `instrument_first_level_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgq43vg9d2nymphs67hlxeofli` (`create_user_id`),
  KEY `FKji3dhshvnwgekgfnjbr4wbgen` (`accuracy_display_name_id`),
  KEY `FKcf1ml3xacp6qpd28w9ppsd18` (`instrument_first_level_type_id`),
  CONSTRAINT `FKcf1ml3xacp6qpd28w9ppsd18` FOREIGN KEY (`instrument_first_level_type_id`) REFERENCES `instrument_first_level_type` (`id`),
  CONSTRAINT `FKgq43vg9d2nymphs67hlxeofli` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKji3dhshvnwgekgfnjbr4wbgen` FOREIGN KEY (`accuracy_display_name_id`) REFERENCES `accuracy_display_name` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_type
-- ----------------------------
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '1', null, '测试', 'test', null, null, '1', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '2', null, '机械天平', 'jixietianping', null, null, '9', '3');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '3', null, '电子天平', 'dianzitianping', null, null, '9', '3');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '4', null, '压力真空表', 'yalizhenkongbiao', null, null, '9', '6');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '5', null, '案秤', 'ancheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '6', null, '钢卷尺', 'gangjuanchi', null, null, '9', '9');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '7', null, '测深钢卷尺', 'ceshengangjuanchi', null, null, '9', '10');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '8', null, '面积计', 'mianjiji', null, null, '9', '11');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '9', null, '玻璃液体温度计', 'boliyetiwenduji', null, null, '2', '12');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '11', null, '体温计', 'tiwenji', null, null, '9', '13');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '12', null, '闭口闪点1号玻璃温度计', 'bikoushandian1haoboliwenduji', null, null, '9', '14');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '13', null, '闭口闪点2号玻璃温度计', 'bikoushandian2haoboliwenduji', null, null, '9', '14');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '14', null, '开口闪点1号玻璃温度计', 'kaikoushandian1haoboliwenduji', null, null, '9', '14');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '15', null, '氧弹热量计', 'yangdanreliangji', null, null, '9', '15');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '16', null, '砝码', 'fama', null, null, '9', '4');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '18', null, '台秤', 'taicheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '19', null, '地秤', 'dicheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '20', null, '电子称', 'dianzicheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '21', null, '吊秤', 'diaocheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '22', null, '定量包装机', 'dingliangbiaozhuangji', null, null, '2', '16');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '23', null, '定量灌装机', 'dingliangguanzhuangji', null, null, '2', '16');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '24', null, '容重器', 'rongzhongqi', null, null, '2', '17');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '25', null, '燃油加油机', 'ranyoujiayouji', null, null, '2', '18');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '26', null, '酒精计', 'jiujingji', null, null, '9', '19');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '27', null, '液体密度计', 'yetimiduji', null, null, '9', '20');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '28', null, '糖度计', 'tangduji', null, null, '9', '21');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '29', null, '乳汁计', 'ruzhiji', null, null, '9', '22');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '30', null, '煤气表', 'meiqibiao', null, null, '9', '23');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '31', null, '水表', 'shuibiao', null, null, '2', '24');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '32', null, '液体流量计', 'yetiliuliangji', null, null, '9', '25');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '33', null, '气体流量计', 'qitiliuliangji', null, null, '9', '26');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '35', null, '压力表', 'putongyalibiao', null, null, '9', '28');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '36', null, '风压表', 'fengyabiao', null, null, '9', '28');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '37', null, '氧气表', 'yangyabiao', null, null, '9', '28');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '38', null, '血压表', 'xueyaji', null, null, '2', '35');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '39', null, '血压计', 'xueyaji', null, null, '2', '35');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '40', null, '出租车计价器', 'chuzuchejijiaqi', null, null, '2', '36');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '41', null, '机动车雷达测速仪', 'jidongcheleidacesuyi', null, null, '2', '37');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '42', null, '单相电能表', 'danxaingdiannengbiao', null, null, '9', '38');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '43', null, '三相电能表', 'sanxiangdiannengbiao', null, null, '9', '38');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '44', null, '电流互感器', 'dianliuhuganqi', null, null, '9', '39');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '45', null, '电压互感器(单相）', 'dianyahuganqi', null, null, '9', '39');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '46', null, '电压互感器（三相）', 'dianyahuganqi', null, null, '9', '39');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '47', null, '绝缘电阻测量仪', 'jueyuandianzuceliangyi', null, null, '9', '40');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '48', null, '接地电阻测量仪', 'jiedidianzuceliangyi', null, null, '9', '40');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '49', null, '心电图仪（机）(工作用）', 'xindiantuyi', null, null, '9', '41');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '50', null, '脑电图仪（工作用）', 'naodiantuyi', null, null, '9', '41');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '51', null, '医用诊断X射线辐射源（工作用）', 'yiyongzhenduanxshexianfusheyuan', null, null, '9', '42');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '52', null, '照射量计', 'zhaosheliangji', null, null, '9', '42');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '53', null, '医用辐射源', 'yiyongfusheyuan', null, null, '9', '42');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '54', null, '射线检测仪', 'shexianjianceyi', null, null, '9', '43');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '55', null, '照射量率仪', 'zhaoshelianglvyi', null, null, '9', '43');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '56', null, '放射性表面污染仪', 'fangshexingbiaomianwuranyi', null, null, '9', '43');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '57', null, '个人剂量计', 'gerebjiliangji', null, null, '9', '43');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '58', null, '活度计', 'huoduji', null, null, '9', '44');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '59', null, '激光能量计', 'jiguangnengliangji', null, null, '9', '45');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '60', null, '激光功率计', 'jiguanggonglvji', null, null, '9', '45');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '61', null, '医用激光源', 'yiyongjiguangyan', null, null, '9', '45');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '62', null, '超声功率计', 'chaoshenggonglvji', null, null, '9', '46');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '63', null, '医用超声源', 'yiyongchaoshengyuan', null, null, '9', '46');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '64', null, '声级计', 'shengjiji', null, null, '9', '47');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '65', null, '听力计', 'tingliji', null, null, '9', '48');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '66', null, 'CO分析仪', 'cofenxiyi', null, null, '8', '49');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '67', null, 'SO₂分析仪', 'SO₂fenxiyi', null, null, '2', '49');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '68', null, '测氢仪', 'ceqingyi', null, null, '2', '49');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '69', null, '硫化氢测定仪', 'liuhuaqingjianceyi', null, null, '2', '49');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '70', null, '酸度计', 'suanduji', null, null, '9', '50');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '71', null, '瓦斯报警器', 'wasibaojingqi', null, null, '2', '51');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '72', null, '瓦斯测定仪', 'wasicedingyi', null, null, '2', '51');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '73', null, '血气酸碱平衡分析仪', 'xueqisuanjianpinghengfenxiyi', null, null, '9', '50');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '74', null, '测汞仪', 'cegongyi', null, null, '2', '52');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '75', null, '火焰光度计', 'huoyanguangduji', null, null, '9', '53');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '76', null, '可见分光光度计', 'kejianfenguangguangduji', null, null, '9', '54');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '77', null, '紫外分光光度计', 'ziwaifenguangguangduji', null, null, '9', '54');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '78', null, '红外分光光度计', 'hongwaifenguangguangduji', null, null, '8', '54');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '79', null, '荧光分光光度计', 'yingguangfenguangguangduji', null, null, '8', '54');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '80', null, '原子吸收分光光度计', 'yanzixishoufenguangguangduji', null, null, '8', '54');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '81', null, '滤光光电比色计', 'lvguangguangdianbiseji', null, null, '9', '55');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '82', null, '荧光光电比色计', 'yingguangguangdianbiseji', null, null, '9', '55');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '83', null, '烟尘采样器', 'yanchencaiyangqi', null, null, '9', '56');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '84', null, '烟尘测量仪', 'yanchenceliangyi', null, null, '8', '56');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '85', null, '粉尘采样器', 'fenchencaiyangqi', null, null, '4', '56');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '86', null, '粉尘测量仪', 'fenchengceliangyi', null, null, '8', '56');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '87', null, '水质检测仪', 'shuizhijianceyi', null, null, '4', '57');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '88', null, '水质综合分析仪', 'shuizhizonghefenxiyi', null, null, '9', '57');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '89', null, '测氰仪', 'ceqingyi2', null, null, '9', '57');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '90', null, '溶氧测定仪', 'rongyangcedingyi', null, null, '8', '57');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '91', null, '呼出气体酒精含量探测器', 'huchuqitijiujinghanliangtanceqi', null, null, '9', '58');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '92', null, '电子血球计数器', 'dianzixueqiujishuqi', null, null, '9', '59');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '93', null, '屈光度计', 'quguangduji', null, null, '4', '60');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '94', null, '电话计时计费装置', 'dianhuojishijifeizhuangzhi', null, null, '2', '61');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '95', null, '棉花水分测量仪', 'mianhuashuifenceliangyi', null, null, '8', '62');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '96', null, '验光仪', 'yanguangyi', null, null, '2', '63');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '97', null, '验光镜片组', 'yanguangjingpianzu', null, null, '2', '63');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '98', null, '微波辐射与泄露测量仪', 'weibofusheyuxielouceliangyi', null, null, '2', '64');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '99', null, '燃气加气机', 'ranqijiqqiji', null, null, '2', '65');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '100', null, '热能表', 'renengbiao', null, null, '9', '66');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '101', null, '行李秤', 'xinglicheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '102', null, '邮政秤', 'youzhengcheng', null, null, '9', '2');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '103', null, '电动差压变送器(蒸汽流量计)', 'zhengqiliuliqngji', null, null, '9', '26');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '104', null, '电动差压变送器', 'diandongchayabiansongqi', null, null, '9', '67');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '105', null, '毫瓦级超声功率计', 'haowajichaoshenggonglvji', null, null, '9', '46');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '106', null, '医用超声诊断仪超声源（工作用）', 'yiyongchaoshengzhenduanyichaoshengyuan', null, null, '9', '46');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '107', null, '一氧化碳报警器', 'yiyanghuatanbaojingqi', null, null, '9', '49');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '108', null, '可燃气体报警器', 'keranqitibaojingqi', null, null, '9', '49');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '109', null, '锐线光源原子吸收分光光度计', 'ruixianguangyuanyanzixishoufenguangguangduji', null, null, '8', '54');
INSERT INTO `instrument_type` VALUES ('StandardDevice', '110', null, 'fd', 'das', null, null, '2', '12');
INSERT INTO `instrument_type` VALUES ('MandatoryInstrument', '111', null, '测试光学_二级', 'ceshiguangxue2', null, null, '1', '70');

-- ----------------------------
-- Table structure for `instrument_type_purposes`
-- ----------------------------
DROP TABLE IF EXISTS `instrument_type_purposes`;
CREATE TABLE `instrument_type_purposes` (
  `instrument_type_id` bigint(20) NOT NULL,
  `purpose_id` bigint(20) NOT NULL,
  PRIMARY KEY (`instrument_type_id`,`purpose_id`),
  KEY `FKmpx0vmun47kduvsbl38omn89r` (`purpose_id`),
  CONSTRAINT `FK8dt4p6nhct0gh30lb4u1l7s87` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`),
  CONSTRAINT `FKmpx0vmun47kduvsbl38omn89r` FOREIGN KEY (`purpose_id`) REFERENCES `purpose` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of instrument_type_purposes
-- ----------------------------
INSERT INTO `instrument_type_purposes` VALUES ('2', '1');
INSERT INTO `instrument_type_purposes` VALUES ('3', '1');
INSERT INTO `instrument_type_purposes` VALUES ('3', '2');
INSERT INTO `instrument_type_purposes` VALUES ('3', '3');
INSERT INTO `instrument_type_purposes` VALUES ('3', '4');
INSERT INTO `instrument_type_purposes` VALUES ('3', '5');
INSERT INTO `instrument_type_purposes` VALUES ('3', '6');
INSERT INTO `instrument_type_purposes` VALUES ('4', '1');
INSERT INTO `instrument_type_purposes` VALUES ('4', '2');
INSERT INTO `instrument_type_purposes` VALUES ('4', '3');
INSERT INTO `instrument_type_purposes` VALUES ('4', '4');
INSERT INTO `instrument_type_purposes` VALUES ('4', '5');
INSERT INTO `instrument_type_purposes` VALUES ('4', '6');
INSERT INTO `instrument_type_purposes` VALUES ('5', '1');
INSERT INTO `instrument_type_purposes` VALUES ('5', '2');
INSERT INTO `instrument_type_purposes` VALUES ('5', '3');
INSERT INTO `instrument_type_purposes` VALUES ('5', '4');
INSERT INTO `instrument_type_purposes` VALUES ('5', '5');
INSERT INTO `instrument_type_purposes` VALUES ('5', '6');
INSERT INTO `instrument_type_purposes` VALUES ('6', '1');
INSERT INTO `instrument_type_purposes` VALUES ('6', '2');
INSERT INTO `instrument_type_purposes` VALUES ('6', '3');
INSERT INTO `instrument_type_purposes` VALUES ('6', '4');
INSERT INTO `instrument_type_purposes` VALUES ('6', '5');
INSERT INTO `instrument_type_purposes` VALUES ('6', '6');
INSERT INTO `instrument_type_purposes` VALUES ('7', '1');
INSERT INTO `instrument_type_purposes` VALUES ('7', '2');
INSERT INTO `instrument_type_purposes` VALUES ('7', '3');
INSERT INTO `instrument_type_purposes` VALUES ('7', '4');
INSERT INTO `instrument_type_purposes` VALUES ('7', '5');
INSERT INTO `instrument_type_purposes` VALUES ('7', '6');
INSERT INTO `instrument_type_purposes` VALUES ('8', '1');
INSERT INTO `instrument_type_purposes` VALUES ('8', '2');
INSERT INTO `instrument_type_purposes` VALUES ('8', '3');
INSERT INTO `instrument_type_purposes` VALUES ('8', '4');
INSERT INTO `instrument_type_purposes` VALUES ('8', '5');
INSERT INTO `instrument_type_purposes` VALUES ('8', '6');
INSERT INTO `instrument_type_purposes` VALUES ('9', '1');
INSERT INTO `instrument_type_purposes` VALUES ('9', '2');
INSERT INTO `instrument_type_purposes` VALUES ('9', '3');
INSERT INTO `instrument_type_purposes` VALUES ('9', '4');
INSERT INTO `instrument_type_purposes` VALUES ('9', '5');
INSERT INTO `instrument_type_purposes` VALUES ('9', '6');
INSERT INTO `instrument_type_purposes` VALUES ('11', '2');
INSERT INTO `instrument_type_purposes` VALUES ('12', '1');
INSERT INTO `instrument_type_purposes` VALUES ('12', '3');
INSERT INTO `instrument_type_purposes` VALUES ('12', '4');
INSERT INTO `instrument_type_purposes` VALUES ('12', '6');
INSERT INTO `instrument_type_purposes` VALUES ('13', '1');
INSERT INTO `instrument_type_purposes` VALUES ('13', '3');
INSERT INTO `instrument_type_purposes` VALUES ('13', '4');
INSERT INTO `instrument_type_purposes` VALUES ('13', '6');
INSERT INTO `instrument_type_purposes` VALUES ('14', '1');
INSERT INTO `instrument_type_purposes` VALUES ('14', '3');
INSERT INTO `instrument_type_purposes` VALUES ('14', '4');
INSERT INTO `instrument_type_purposes` VALUES ('14', '6');
INSERT INTO `instrument_type_purposes` VALUES ('15', '1');
INSERT INTO `instrument_type_purposes` VALUES ('15', '3');
INSERT INTO `instrument_type_purposes` VALUES ('15', '4');
INSERT INTO `instrument_type_purposes` VALUES ('15', '5');
INSERT INTO `instrument_type_purposes` VALUES ('15', '6');
INSERT INTO `instrument_type_purposes` VALUES ('16', '1');
INSERT INTO `instrument_type_purposes` VALUES ('16', '2');
INSERT INTO `instrument_type_purposes` VALUES ('16', '3');
INSERT INTO `instrument_type_purposes` VALUES ('16', '4');
INSERT INTO `instrument_type_purposes` VALUES ('16', '5');
INSERT INTO `instrument_type_purposes` VALUES ('16', '6');
INSERT INTO `instrument_type_purposes` VALUES ('18', '1');
INSERT INTO `instrument_type_purposes` VALUES ('18', '2');
INSERT INTO `instrument_type_purposes` VALUES ('18', '3');
INSERT INTO `instrument_type_purposes` VALUES ('18', '4');
INSERT INTO `instrument_type_purposes` VALUES ('18', '5');
INSERT INTO `instrument_type_purposes` VALUES ('18', '6');
INSERT INTO `instrument_type_purposes` VALUES ('19', '1');
INSERT INTO `instrument_type_purposes` VALUES ('19', '2');
INSERT INTO `instrument_type_purposes` VALUES ('19', '3');
INSERT INTO `instrument_type_purposes` VALUES ('19', '4');
INSERT INTO `instrument_type_purposes` VALUES ('19', '5');
INSERT INTO `instrument_type_purposes` VALUES ('19', '6');
INSERT INTO `instrument_type_purposes` VALUES ('20', '1');
INSERT INTO `instrument_type_purposes` VALUES ('20', '2');
INSERT INTO `instrument_type_purposes` VALUES ('20', '3');
INSERT INTO `instrument_type_purposes` VALUES ('20', '4');
INSERT INTO `instrument_type_purposes` VALUES ('20', '5');
INSERT INTO `instrument_type_purposes` VALUES ('20', '6');
INSERT INTO `instrument_type_purposes` VALUES ('21', '1');
INSERT INTO `instrument_type_purposes` VALUES ('21', '3');
INSERT INTO `instrument_type_purposes` VALUES ('21', '4');
INSERT INTO `instrument_type_purposes` VALUES ('21', '5');
INSERT INTO `instrument_type_purposes` VALUES ('21', '6');
INSERT INTO `instrument_type_purposes` VALUES ('22', '1');
INSERT INTO `instrument_type_purposes` VALUES ('22', '2');
INSERT INTO `instrument_type_purposes` VALUES ('22', '3');
INSERT INTO `instrument_type_purposes` VALUES ('22', '4');
INSERT INTO `instrument_type_purposes` VALUES ('22', '5');
INSERT INTO `instrument_type_purposes` VALUES ('22', '6');
INSERT INTO `instrument_type_purposes` VALUES ('23', '1');
INSERT INTO `instrument_type_purposes` VALUES ('23', '2');
INSERT INTO `instrument_type_purposes` VALUES ('23', '3');
INSERT INTO `instrument_type_purposes` VALUES ('23', '4');
INSERT INTO `instrument_type_purposes` VALUES ('23', '5');
INSERT INTO `instrument_type_purposes` VALUES ('23', '6');
INSERT INTO `instrument_type_purposes` VALUES ('24', '1');
INSERT INTO `instrument_type_purposes` VALUES ('24', '2');
INSERT INTO `instrument_type_purposes` VALUES ('24', '3');
INSERT INTO `instrument_type_purposes` VALUES ('24', '4');
INSERT INTO `instrument_type_purposes` VALUES ('24', '5');
INSERT INTO `instrument_type_purposes` VALUES ('24', '6');
INSERT INTO `instrument_type_purposes` VALUES ('25', '1');
INSERT INTO `instrument_type_purposes` VALUES ('25', '3');
INSERT INTO `instrument_type_purposes` VALUES ('25', '4');
INSERT INTO `instrument_type_purposes` VALUES ('25', '5');
INSERT INTO `instrument_type_purposes` VALUES ('25', '6');
INSERT INTO `instrument_type_purposes` VALUES ('26', '1');
INSERT INTO `instrument_type_purposes` VALUES ('26', '2');
INSERT INTO `instrument_type_purposes` VALUES ('26', '3');
INSERT INTO `instrument_type_purposes` VALUES ('26', '4');
INSERT INTO `instrument_type_purposes` VALUES ('26', '5');
INSERT INTO `instrument_type_purposes` VALUES ('26', '6');
INSERT INTO `instrument_type_purposes` VALUES ('27', '1');
INSERT INTO `instrument_type_purposes` VALUES ('27', '2');
INSERT INTO `instrument_type_purposes` VALUES ('27', '3');
INSERT INTO `instrument_type_purposes` VALUES ('27', '4');
INSERT INTO `instrument_type_purposes` VALUES ('27', '5');
INSERT INTO `instrument_type_purposes` VALUES ('27', '6');
INSERT INTO `instrument_type_purposes` VALUES ('28', '1');
INSERT INTO `instrument_type_purposes` VALUES ('28', '2');
INSERT INTO `instrument_type_purposes` VALUES ('28', '3');
INSERT INTO `instrument_type_purposes` VALUES ('28', '4');
INSERT INTO `instrument_type_purposes` VALUES ('28', '5');
INSERT INTO `instrument_type_purposes` VALUES ('28', '6');
INSERT INTO `instrument_type_purposes` VALUES ('29', '1');
INSERT INTO `instrument_type_purposes` VALUES ('29', '2');
INSERT INTO `instrument_type_purposes` VALUES ('29', '3');
INSERT INTO `instrument_type_purposes` VALUES ('29', '4');
INSERT INTO `instrument_type_purposes` VALUES ('29', '5');
INSERT INTO `instrument_type_purposes` VALUES ('29', '6');
INSERT INTO `instrument_type_purposes` VALUES ('30', '1');
INSERT INTO `instrument_type_purposes` VALUES ('30', '3');
INSERT INTO `instrument_type_purposes` VALUES ('30', '4');
INSERT INTO `instrument_type_purposes` VALUES ('30', '5');
INSERT INTO `instrument_type_purposes` VALUES ('30', '6');
INSERT INTO `instrument_type_purposes` VALUES ('31', '1');
INSERT INTO `instrument_type_purposes` VALUES ('31', '3');
INSERT INTO `instrument_type_purposes` VALUES ('31', '4');
INSERT INTO `instrument_type_purposes` VALUES ('31', '5');
INSERT INTO `instrument_type_purposes` VALUES ('31', '6');
INSERT INTO `instrument_type_purposes` VALUES ('32', '1');
INSERT INTO `instrument_type_purposes` VALUES ('32', '4');
INSERT INTO `instrument_type_purposes` VALUES ('32', '5');
INSERT INTO `instrument_type_purposes` VALUES ('33', '1');
INSERT INTO `instrument_type_purposes` VALUES ('33', '4');
INSERT INTO `instrument_type_purposes` VALUES ('33', '5');
INSERT INTO `instrument_type_purposes` VALUES ('35', '1');
INSERT INTO `instrument_type_purposes` VALUES ('35', '2');
INSERT INTO `instrument_type_purposes` VALUES ('35', '4');
INSERT INTO `instrument_type_purposes` VALUES ('35', '5');
INSERT INTO `instrument_type_purposes` VALUES ('35', '6');
INSERT INTO `instrument_type_purposes` VALUES ('36', '5');
INSERT INTO `instrument_type_purposes` VALUES ('37', '1');
INSERT INTO `instrument_type_purposes` VALUES ('37', '2');
INSERT INTO `instrument_type_purposes` VALUES ('37', '3');
INSERT INTO `instrument_type_purposes` VALUES ('37', '4');
INSERT INTO `instrument_type_purposes` VALUES ('37', '5');
INSERT INTO `instrument_type_purposes` VALUES ('37', '6');
INSERT INTO `instrument_type_purposes` VALUES ('38', '2');
INSERT INTO `instrument_type_purposes` VALUES ('39', '2');
INSERT INTO `instrument_type_purposes` VALUES ('40', '1');
INSERT INTO `instrument_type_purposes` VALUES ('41', '4');
INSERT INTO `instrument_type_purposes` VALUES ('42', '1');
INSERT INTO `instrument_type_purposes` VALUES ('43', '1');
INSERT INTO `instrument_type_purposes` VALUES ('44', '1');
INSERT INTO `instrument_type_purposes` VALUES ('44', '4');
INSERT INTO `instrument_type_purposes` VALUES ('45', '1');
INSERT INTO `instrument_type_purposes` VALUES ('46', '1');
INSERT INTO `instrument_type_purposes` VALUES ('47', '4');
INSERT INTO `instrument_type_purposes` VALUES ('47', '5');
INSERT INTO `instrument_type_purposes` VALUES ('48', '2');
INSERT INTO `instrument_type_purposes` VALUES ('48', '3');
INSERT INTO `instrument_type_purposes` VALUES ('48', '4');
INSERT INTO `instrument_type_purposes` VALUES ('48', '5');
INSERT INTO `instrument_type_purposes` VALUES ('48', '6');
INSERT INTO `instrument_type_purposes` VALUES ('49', '2');
INSERT INTO `instrument_type_purposes` VALUES ('50', '2');
INSERT INTO `instrument_type_purposes` VALUES ('51', '2');
INSERT INTO `instrument_type_purposes` VALUES ('52', '2');
INSERT INTO `instrument_type_purposes` VALUES ('52', '4');
INSERT INTO `instrument_type_purposes` VALUES ('52', '5');
INSERT INTO `instrument_type_purposes` VALUES ('53', '2');
INSERT INTO `instrument_type_purposes` VALUES ('54', '4');
INSERT INTO `instrument_type_purposes` VALUES ('54', '5');
INSERT INTO `instrument_type_purposes` VALUES ('55', '2');
INSERT INTO `instrument_type_purposes` VALUES ('55', '4');
INSERT INTO `instrument_type_purposes` VALUES ('55', '5');
INSERT INTO `instrument_type_purposes` VALUES ('56', '2');
INSERT INTO `instrument_type_purposes` VALUES ('56', '4');
INSERT INTO `instrument_type_purposes` VALUES ('56', '5');
INSERT INTO `instrument_type_purposes` VALUES ('57', '2');
INSERT INTO `instrument_type_purposes` VALUES ('57', '4');
INSERT INTO `instrument_type_purposes` VALUES ('57', '5');
INSERT INTO `instrument_type_purposes` VALUES ('58', '2');
INSERT INTO `instrument_type_purposes` VALUES ('58', '4');
INSERT INTO `instrument_type_purposes` VALUES ('58', '5');
INSERT INTO `instrument_type_purposes` VALUES ('59', '2');
INSERT INTO `instrument_type_purposes` VALUES ('60', '2');
INSERT INTO `instrument_type_purposes` VALUES ('61', '2');
INSERT INTO `instrument_type_purposes` VALUES ('62', '2');
INSERT INTO `instrument_type_purposes` VALUES ('63', '2');
INSERT INTO `instrument_type_purposes` VALUES ('64', '4');
INSERT INTO `instrument_type_purposes` VALUES ('64', '5');
INSERT INTO `instrument_type_purposes` VALUES ('65', '2');
INSERT INTO `instrument_type_purposes` VALUES ('66', '4');
INSERT INTO `instrument_type_purposes` VALUES ('66', '5');
INSERT INTO `instrument_type_purposes` VALUES ('67', '4');
INSERT INTO `instrument_type_purposes` VALUES ('67', '5');
INSERT INTO `instrument_type_purposes` VALUES ('68', '4');
INSERT INTO `instrument_type_purposes` VALUES ('69', '4');
INSERT INTO `instrument_type_purposes` VALUES ('69', '5');
INSERT INTO `instrument_type_purposes` VALUES ('70', '1');
INSERT INTO `instrument_type_purposes` VALUES ('70', '2');
INSERT INTO `instrument_type_purposes` VALUES ('70', '5');
INSERT INTO `instrument_type_purposes` VALUES ('71', '4');
INSERT INTO `instrument_type_purposes` VALUES ('72', '4');
INSERT INTO `instrument_type_purposes` VALUES ('73', '2');
INSERT INTO `instrument_type_purposes` VALUES ('74', '2');
INSERT INTO `instrument_type_purposes` VALUES ('74', '4');
INSERT INTO `instrument_type_purposes` VALUES ('74', '5');
INSERT INTO `instrument_type_purposes` VALUES ('75', '2');
INSERT INTO `instrument_type_purposes` VALUES ('75', '4');
INSERT INTO `instrument_type_purposes` VALUES ('75', '5');
INSERT INTO `instrument_type_purposes` VALUES ('76', '1');
INSERT INTO `instrument_type_purposes` VALUES ('76', '2');
INSERT INTO `instrument_type_purposes` VALUES ('76', '5');
INSERT INTO `instrument_type_purposes` VALUES ('77', '1');
INSERT INTO `instrument_type_purposes` VALUES ('77', '2');
INSERT INTO `instrument_type_purposes` VALUES ('77', '5');
INSERT INTO `instrument_type_purposes` VALUES ('78', '1');
INSERT INTO `instrument_type_purposes` VALUES ('78', '2');
INSERT INTO `instrument_type_purposes` VALUES ('78', '5');
INSERT INTO `instrument_type_purposes` VALUES ('79', '1');
INSERT INTO `instrument_type_purposes` VALUES ('79', '2');
INSERT INTO `instrument_type_purposes` VALUES ('79', '5');
INSERT INTO `instrument_type_purposes` VALUES ('80', '1');
INSERT INTO `instrument_type_purposes` VALUES ('80', '2');
INSERT INTO `instrument_type_purposes` VALUES ('80', '5');
INSERT INTO `instrument_type_purposes` VALUES ('81', '1');
INSERT INTO `instrument_type_purposes` VALUES ('81', '2');
INSERT INTO `instrument_type_purposes` VALUES ('81', '5');
INSERT INTO `instrument_type_purposes` VALUES ('82', '1');
INSERT INTO `instrument_type_purposes` VALUES ('82', '2');
INSERT INTO `instrument_type_purposes` VALUES ('82', '5');
INSERT INTO `instrument_type_purposes` VALUES ('83', '5');
INSERT INTO `instrument_type_purposes` VALUES ('84', '5');
INSERT INTO `instrument_type_purposes` VALUES ('85', '5');
INSERT INTO `instrument_type_purposes` VALUES ('86', '5');
INSERT INTO `instrument_type_purposes` VALUES ('87', '5');
INSERT INTO `instrument_type_purposes` VALUES ('88', '5');
INSERT INTO `instrument_type_purposes` VALUES ('89', '5');
INSERT INTO `instrument_type_purposes` VALUES ('90', '5');
INSERT INTO `instrument_type_purposes` VALUES ('91', '4');
INSERT INTO `instrument_type_purposes` VALUES ('92', '2');
INSERT INTO `instrument_type_purposes` VALUES ('93', '2');
INSERT INTO `instrument_type_purposes` VALUES ('94', '1');
INSERT INTO `instrument_type_purposes` VALUES ('95', '1');
INSERT INTO `instrument_type_purposes` VALUES ('96', '2');
INSERT INTO `instrument_type_purposes` VALUES ('97', '2');
INSERT INTO `instrument_type_purposes` VALUES ('98', '4');
INSERT INTO `instrument_type_purposes` VALUES ('98', '5');
INSERT INTO `instrument_type_purposes` VALUES ('99', '1');
INSERT INTO `instrument_type_purposes` VALUES ('100', '1');
INSERT INTO `instrument_type_purposes` VALUES ('101', '1');
INSERT INTO `instrument_type_purposes` VALUES ('102', '1');
INSERT INTO `instrument_type_purposes` VALUES ('103', '1');
INSERT INTO `instrument_type_purposes` VALUES ('104', '1');
INSERT INTO `instrument_type_purposes` VALUES ('104', '4');
INSERT INTO `instrument_type_purposes` VALUES ('105', '2');
INSERT INTO `instrument_type_purposes` VALUES ('106', '2');
INSERT INTO `instrument_type_purposes` VALUES ('106', '4');
INSERT INTO `instrument_type_purposes` VALUES ('107', '4');
INSERT INTO `instrument_type_purposes` VALUES ('108', '4');
INSERT INTO `instrument_type_purposes` VALUES ('109', '5');
INSERT INTO `instrument_type_purposes` VALUES ('110', '1');
INSERT INTO `instrument_type_purposes` VALUES ('110', '2');
INSERT INTO `instrument_type_purposes` VALUES ('111', '1');

-- ----------------------------
-- Table structure for `measure_scale`
-- ----------------------------
DROP TABLE IF EXISTS `measure_scale`;
CREATE TABLE `measure_scale` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT 'NULL',
  `update_time` bigint(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT 'NULL',
  `weight` int(11) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `instrument_first_level_type_id` bigint(20) DEFAULT NULL,
  `instrument_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmxd13jhdq613c4qic371jt5vo` (`create_user_id`),
  KEY `FK8stlm00mqy4uml03lmiv2w5h6` (`instrument_first_level_type_id`),
  KEY `FK9th5efjdjcdy7bvreglrglla3` (`instrument_type_id`),
  CONSTRAINT `FK8stlm00mqy4uml03lmiv2w5h6` FOREIGN KEY (`instrument_first_level_type_id`) REFERENCES `instrument_first_level_type` (`id`),
  CONSTRAINT `FK9th5efjdjcdy7bvreglrglla3` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`),
  CONSTRAINT `FKmxd13jhdq613c4qic371jt5vo` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=766 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of measure_scale
-- ----------------------------
INSERT INTO `measure_scale` VALUES ('2', null, '', null, '0', '0', null, null, '2');
INSERT INTO `measure_scale` VALUES ('3', null, '', null, '300g', '5', null, null, '2');
INSERT INTO `measure_scale` VALUES ('4', null, '', null, '100g', '1', null, null, '2');
INSERT INTO `measure_scale` VALUES ('5', null, '', null, '200g', '2', null, null, '2');
INSERT INTO `measure_scale` VALUES ('6', null, '', null, '210g', '3', null, null, '2');
INSERT INTO `measure_scale` VALUES ('7', null, '', null, '320g', '6', null, null, '2');
INSERT INTO `measure_scale` VALUES ('8', null, '', null, '420g', '8', null, null, '2');
INSERT INTO `measure_scale` VALUES ('9', null, '', null, '220g', '4', null, null, '2');
INSERT INTO `measure_scale` VALUES ('10', null, '', null, '400g', '7', null, null, '2');
INSERT INTO `measure_scale` VALUES ('11', null, '', null, '500g', '9', null, null, '2');
INSERT INTO `measure_scale` VALUES ('12', null, '', null, '1Kg', '11', null, null, '2');
INSERT INTO `measure_scale` VALUES ('13', null, '', null, '520g', '10', null, null, '2');
INSERT INTO `measure_scale` VALUES ('14', null, '', null, '0', '0', null, null, '3');
INSERT INTO `measure_scale` VALUES ('15', null, '', null, '300g', '5', null, null, '3');
INSERT INTO `measure_scale` VALUES ('16', null, '', null, '2000g', '18', null, null, '3');
INSERT INTO `measure_scale` VALUES ('17', null, '', null, '10Kg', '26', null, null, '3');
INSERT INTO `measure_scale` VALUES ('18', null, '', null, '200Kg', '37', null, null, '3');
INSERT INTO `measure_scale` VALUES ('19', null, '', null, '8Kg', '25', null, null, '3');
INSERT INTO `measure_scale` VALUES ('20', null, '', null, '5Kg', '21', null, null, '3');
INSERT INTO `measure_scale` VALUES ('21', null, '', null, '150Kg', '36', null, null, '3');
INSERT INTO `measure_scale` VALUES ('22', null, '', null, '3200g', '20', null, null, '3');
INSERT INTO `measure_scale` VALUES ('23', null, '', null, '10Kg', '27', null, null, '3');
INSERT INTO `measure_scale` VALUES ('24', null, '', null, '500g', '9', null, null, '3');
INSERT INTO `measure_scale` VALUES ('25', null, '', null, '520g', '10', null, null, '3');
INSERT INTO `measure_scale` VALUES ('26', null, '', null, '1.5Kg', '17', null, null, '3');
INSERT INTO `measure_scale` VALUES ('27', null, '', null, '600g', '11', null, null, '3');
INSERT INTO `measure_scale` VALUES ('28', null, '', null, '2200g', '19', null, null, '3');
INSERT INTO `measure_scale` VALUES ('29', null, '', null, '52Kg', '31', null, null, '3');
INSERT INTO `measure_scale` VALUES ('30', null, '', null, '82Kg', '33', null, null, '3');
INSERT INTO `measure_scale` VALUES ('31', null, '', null, '820g', '13', null, null, '3');
INSERT INTO `measure_scale` VALUES ('32', null, '', null, '100g', '1', null, null, '3');
INSERT INTO `measure_scale` VALUES ('33', null, '', null, '300Kg', '38', null, null, '3');
INSERT INTO `measure_scale` VALUES ('34', null, '', null, '6KG', '24', null, null, '3');
INSERT INTO `measure_scale` VALUES ('35', null, '', null, '120Kg', '35', null, null, '3');
INSERT INTO `measure_scale` VALUES ('36', null, '', null, '62Kg', '32', null, null, '3');
INSERT INTO `measure_scale` VALUES ('37', null, '', null, '220g', '4', null, null, '3');
INSERT INTO `measure_scale` VALUES ('38', null, '', null, '5200g', '23', null, null, '3');
INSERT INTO `measure_scale` VALUES ('39', null, '', null, '15Kg', '28', null, null, '3');
INSERT INTO `measure_scale` VALUES ('40', null, '', null, '20Kg', '29', null, null, '3');
INSERT INTO `measure_scale` VALUES ('41', null, '', null, '1Kg', '14', null, null, '3');
INSERT INTO `measure_scale` VALUES ('42', null, '', null, '400g', '7', null, null, '3');
INSERT INTO `measure_scale` VALUES ('43', null, '', null, '320g', '6', null, null, '3');
INSERT INTO `measure_scale` VALUES ('44', null, '', null, '32Kg', '30', null, null, '3');
INSERT INTO `measure_scale` VALUES ('45', null, '', null, '1200g', '16', null, null, '3');
INSERT INTO `measure_scale` VALUES ('46', null, '', null, '5000g', '22', null, null, '3');
INSERT INTO `measure_scale` VALUES ('47', null, '', null, '210g', '3', null, null, '3');
INSERT INTO `measure_scale` VALUES ('48', null, '', null, '100Kg', '34', null, null, '3');
INSERT INTO `measure_scale` VALUES ('49', null, '', null, '420g', '8', null, null, '3');
INSERT INTO `measure_scale` VALUES ('50', null, '', null, '620g', '12', null, null, '3');
INSERT INTO `measure_scale` VALUES ('51', null, '', null, '200g', '2', null, null, '3');
INSERT INTO `measure_scale` VALUES ('52', null, '', null, '1000g', '15', null, null, '3');
INSERT INTO `measure_scale` VALUES ('53', null, '', null, '1000g', '12', null, null, '2');
INSERT INTO `measure_scale` VALUES ('54', null, '', null, '0', '1', null, null, '4');
INSERT INTO `measure_scale` VALUES ('55', null, '', null, '-0.1', '0', null, null, '4');
INSERT INTO `measure_scale` VALUES ('56', null, '', null, '0.6MPa', '9', null, null, '4');
INSERT INTO `measure_scale` VALUES ('57', null, '', null, '25MPa', '16', null, null, '4');
INSERT INTO `measure_scale` VALUES ('58', null, '', null, '4MPa', '13', null, null, '4');
INSERT INTO `measure_scale` VALUES ('59', null, '', null, '60MPa', '18', null, null, '4');
INSERT INTO `measure_scale` VALUES ('60', null, '', null, '0.06MPa', '2', null, null, '4');
INSERT INTO `measure_scale` VALUES ('61', null, '', null, '0.3MPa', '6', null, null, '4');
INSERT INTO `measure_scale` VALUES ('62', null, '', null, '0.15MPa', '3', null, null, '4');
INSERT INTO `measure_scale` VALUES ('63', null, '', null, '0.25MPa', '5', null, null, '4');
INSERT INTO `measure_scale` VALUES ('64', null, '', null, '0.16MPa', '4', null, null, '4');
INSERT INTO `measure_scale` VALUES ('65', null, '', null, '10MPa', '15', null, null, '4');
INSERT INTO `measure_scale` VALUES ('66', null, '', null, '1.0MPa', '10', null, null, '4');
INSERT INTO `measure_scale` VALUES ('67', null, '', null, '2.5MPa', '12', null, null, '4');
INSERT INTO `measure_scale` VALUES ('68', null, '', null, '40MPa', '17', null, null, '4');
INSERT INTO `measure_scale` VALUES ('69', null, '', null, '0.4MPa', '7', null, null, '4');
INSERT INTO `measure_scale` VALUES ('70', null, '', null, '0.5MPa', '8', null, null, '4');
INSERT INTO `measure_scale` VALUES ('71', null, '', null, '1.6MPa', '11', null, null, '4');
INSERT INTO `measure_scale` VALUES ('72', null, '', null, '6MPa', '14', null, null, '4');
INSERT INTO `measure_scale` VALUES ('73', null, '', null, '100MPa', '19', null, null, '4');
INSERT INTO `measure_scale` VALUES ('74', null, '', null, '5Kg', '2', null, null, '5');
INSERT INTO `measure_scale` VALUES ('75', null, '', null, '10Kg', '3', null, null, '5');
INSERT INTO `measure_scale` VALUES ('76', null, '', null, '15Kg', '4', null, null, '5');
INSERT INTO `measure_scale` VALUES ('77', null, '', null, '3Kg', '1', null, null, '5');
INSERT INTO `measure_scale` VALUES ('78', null, '', null, '0', '0', null, null, '6');
INSERT INTO `measure_scale` VALUES ('79', null, '', null, '1m', '1', null, null, '6');
INSERT INTO `measure_scale` VALUES ('80', null, '', null, '1.5m', '2', null, null, '6');
INSERT INTO `measure_scale` VALUES ('81', null, '', null, '30m', '10', null, null, '6');
INSERT INTO `measure_scale` VALUES ('82', null, '', null, '5m', '6', null, null, '6');
INSERT INTO `measure_scale` VALUES ('83', null, '', null, '50m', '11', null, null, '6');
INSERT INTO `measure_scale` VALUES ('84', null, '', null, '10m', '8', null, null, '6');
INSERT INTO `measure_scale` VALUES ('85', null, '', null, '20m', '9', null, null, '6');
INSERT INTO `measure_scale` VALUES ('86', null, '', null, '7.5m', '7', null, null, '6');
INSERT INTO `measure_scale` VALUES ('87', null, '', null, '2m', '3', null, null, '6');
INSERT INTO `measure_scale` VALUES ('88', null, '', null, '3m', '4', null, null, '6');
INSERT INTO `measure_scale` VALUES ('89', null, '', null, '100m', '12', null, null, '6');
INSERT INTO `measure_scale` VALUES ('90', null, '', null, '3.5m', '5', null, null, '6');
INSERT INTO `measure_scale` VALUES ('91', null, '', null, '1.5m', '2', null, null, '7');
INSERT INTO `measure_scale` VALUES ('92', null, '', null, '30m', '10', null, null, '7');
INSERT INTO `measure_scale` VALUES ('93', null, '', null, '2m', '3', null, null, '7');
INSERT INTO `measure_scale` VALUES ('94', null, '', null, '20m', '9', null, null, '7');
INSERT INTO `measure_scale` VALUES ('95', null, '', null, '50m', '11', null, null, '7');
INSERT INTO `measure_scale` VALUES ('96', null, '', null, '3m', '4', null, null, '7');
INSERT INTO `measure_scale` VALUES ('97', null, '', null, '5m', '6', null, null, '7');
INSERT INTO `measure_scale` VALUES ('98', null, '', null, '100m', '12', null, null, '7');
INSERT INTO `measure_scale` VALUES ('99', null, '', null, '10m', '8', null, null, '7');
INSERT INTO `measure_scale` VALUES ('100', null, '', null, '1m', '1', null, null, '7');
INSERT INTO `measure_scale` VALUES ('101', null, '', null, '3.5m', '5', null, null, '7');
INSERT INTO `measure_scale` VALUES ('102', null, '', null, '0', '0', null, null, '7');
INSERT INTO `measure_scale` VALUES ('103', null, '', null, '7.5m', '7', null, null, '7');
INSERT INTO `measure_scale` VALUES ('104', null, '', null, '------', '0', null, null, '8');
INSERT INTO `measure_scale` VALUES ('105', null, '', null, '600℃', '16', null, null, '9');
INSERT INTO `measure_scale` VALUES ('106', null, '', null, '20℃', '5', null, null, '9');
INSERT INTO `measure_scale` VALUES ('107', null, '', null, '300℃', '13', null, null, '9');
INSERT INTO `measure_scale` VALUES ('108', null, '', null, '0℃', '4', null, null, '9');
INSERT INTO `measure_scale` VALUES ('109', null, '', null, '70℃', '7', null, null, '9');
INSERT INTO `measure_scale` VALUES ('110', null, '', null, '500℃', '15', null, null, '9');
INSERT INTO `measure_scale` VALUES ('111', null, '', null, '400℃', '14', null, null, '9');
INSERT INTO `measure_scale` VALUES ('112', null, '', null, '110℃', '9', null, null, '9');
INSERT INTO `measure_scale` VALUES ('113', null, '', null, '-10℃', '3', null, null, '9');
INSERT INTO `measure_scale` VALUES ('114', null, '', null, '-20℃', '2', null, null, '9');
INSERT INTO `measure_scale` VALUES ('115', null, '', null, '200℃', '11', null, null, '9');
INSERT INTO `measure_scale` VALUES ('116', null, '', null, '-30℃', '1', null, null, '9');
INSERT INTO `measure_scale` VALUES ('117', null, '', null, '150℃', '10', null, null, '9');
INSERT INTO `measure_scale` VALUES ('118', null, '', null, '50℃', '6', null, null, '9');
INSERT INTO `measure_scale` VALUES ('119', null, '', null, '100℃', '8', null, null, '9');
INSERT INTO `measure_scale` VALUES ('120', null, '', null, '-80℃', '0', null, null, '9');
INSERT INTO `measure_scale` VALUES ('121', null, '', null, '250℃', '12', null, null, '9');
INSERT INTO `measure_scale` VALUES ('123', null, '', null, '43.0℃', '1', null, null, '11');
INSERT INTO `measure_scale` VALUES ('124', null, '', null, '30℃', '0', null, null, '11');
INSERT INTO `measure_scale` VALUES ('125', null, '', null, '-30℃', '0', null, null, '12');
INSERT INTO `measure_scale` VALUES ('126', null, '', null, '170℃', '2', null, null, '12');
INSERT INTO `measure_scale` VALUES ('127', null, '', null, '300℃', '1', null, null, '13');
INSERT INTO `measure_scale` VALUES ('128', null, '', null, '100℃', '0', null, null, '13');
INSERT INTO `measure_scale` VALUES ('129', null, '', null, '360℃', '4', null, null, '12');
INSERT INTO `measure_scale` VALUES ('130', null, '', null, '0℃', '1', null, null, '12');
INSERT INTO `measure_scale` VALUES ('131', null, '', null, '360℃', '2', null, null, '14');
INSERT INTO `measure_scale` VALUES ('132', null, '', null, '0℃', '0', null, null, '14');
INSERT INTO `measure_scale` VALUES ('133', null, '', null, '1500J/K', '3', null, null, '15');
INSERT INTO `measure_scale` VALUES ('134', null, '', null, '1050J/K', '1', null, null, '15');
INSERT INTO `measure_scale` VALUES ('135', null, '', null, '1400J/K', '2', null, null, '15');
INSERT INTO `measure_scale` VALUES ('136', null, '', null, '0', '0', null, null, '15');
INSERT INTO `measure_scale` VALUES ('137', null, '', null, '1mg', '0', null, null, '16');
INSERT INTO `measure_scale` VALUES ('138', null, '', null, '1Kg', '18', null, null, '16');
INSERT INTO `measure_scale` VALUES ('139', null, '', null, '100g', '15', null, null, '16');
INSERT INTO `measure_scale` VALUES ('140', null, '', null, '200Kg', '26', null, null, '16');
INSERT INTO `measure_scale` VALUES ('141', null, '', null, '100mg', '6', null, null, '16');
INSERT INTO `measure_scale` VALUES ('142', null, '', null, '1mg', '1', null, null, '16');
INSERT INTO `measure_scale` VALUES ('143', null, '', null, '2t', '29', null, null, '16');
INSERT INTO `measure_scale` VALUES ('144', null, '', null, '20mg', '4', null, null, '16');
INSERT INTO `measure_scale` VALUES ('145', null, '', null, '50Kg', '24', null, null, '16');
INSERT INTO `measure_scale` VALUES ('146', null, '', null, '1g', '9', null, null, '16');
INSERT INTO `measure_scale` VALUES ('147', null, '', null, '5mg', '2', null, null, '16');
INSERT INTO `measure_scale` VALUES ('148', null, '', null, '200g', '16', null, null, '16');
INSERT INTO `measure_scale` VALUES ('149', null, '', null, '3t', '30', null, null, '16');
INSERT INTO `measure_scale` VALUES ('150', null, '', null, '200mg', '7', null, null, '16');
INSERT INTO `measure_scale` VALUES ('151', null, '', null, '500Kg', '27', null, null, '16');
INSERT INTO `measure_scale` VALUES ('152', null, '', null, '20g', '13', null, null, '16');
INSERT INTO `measure_scale` VALUES ('153', null, '', null, '20Kg', '22', null, null, '16');
INSERT INTO `measure_scale` VALUES ('154', null, '', null, '1t', '28', null, null, '16');
INSERT INTO `measure_scale` VALUES ('155', null, '', null, '500mg', '8', null, null, '16');
INSERT INTO `measure_scale` VALUES ('156', null, '', null, '5g', '11', null, null, '16');
INSERT INTO `measure_scale` VALUES ('157', null, '', null, '10g', '12', null, null, '16');
INSERT INTO `measure_scale` VALUES ('158', null, '', null, '50mg', '5', null, null, '16');
INSERT INTO `measure_scale` VALUES ('159', null, '', null, '2Kg', '19', null, null, '16');
INSERT INTO `measure_scale` VALUES ('160', null, '', null, '10kg', '21', null, null, '16');
INSERT INTO `measure_scale` VALUES ('161', null, '', null, '100Kg', '25', null, null, '16');
INSERT INTO `measure_scale` VALUES ('162', null, '', null, '500g', '17', null, null, '16');
INSERT INTO `measure_scale` VALUES ('163', null, '', null, '10mg', '3', null, null, '16');
INSERT INTO `measure_scale` VALUES ('164', null, '', null, '2g', '10', null, null, '16');
INSERT INTO `measure_scale` VALUES ('165', null, '', null, '5Kg', '20', null, null, '16');
INSERT INTO `measure_scale` VALUES ('166', null, '', null, '50g', '14', null, null, '16');
INSERT INTO `measure_scale` VALUES ('172', null, '', null, '0', '0', null, null, '5');
INSERT INTO `measure_scale` VALUES ('173', null, '', null, '0', '0', null, null, '18');
INSERT INTO `measure_scale` VALUES ('174', null, '', null, '100Kg', '4', null, null, '18');
INSERT INTO `measure_scale` VALUES ('175', null, '', null, '300Kg', '7', null, null, '18');
INSERT INTO `measure_scale` VALUES ('176', null, '', null, '60Kg', '2', null, null, '18');
INSERT INTO `measure_scale` VALUES ('177', null, '', null, '200Kg', '6', null, null, '18');
INSERT INTO `measure_scale` VALUES ('178', null, '', null, '75Kg', '3', null, null, '18');
INSERT INTO `measure_scale` VALUES ('179', null, '', null, '150Kg', '5', null, null, '18');
INSERT INTO `measure_scale` VALUES ('180', null, '', null, '30Kg', '1', null, null, '18');
INSERT INTO `measure_scale` VALUES ('181', null, '', null, '0', '0', null, null, '19');
INSERT INTO `measure_scale` VALUES ('182', null, '', null, '40t', '4', null, null, '19');
INSERT INTO `measure_scale` VALUES ('183', null, '', null, '30t', '3', null, null, '19');
INSERT INTO `measure_scale` VALUES ('184', null, '', null, '80t', '7', null, null, '19');
INSERT INTO `measure_scale` VALUES ('185', null, '', null, '120t', '9', null, null, '19');
INSERT INTO `measure_scale` VALUES ('186', null, '', null, '180t', '11', null, null, '19');
INSERT INTO `measure_scale` VALUES ('187', null, '', null, '100t', '8', null, null, '19');
INSERT INTO `measure_scale` VALUES ('188', null, '', null, '200t', '12', null, null, '19');
INSERT INTO `measure_scale` VALUES ('189', null, '', null, '60t', '6', null, null, '19');
INSERT INTO `measure_scale` VALUES ('190', null, '', null, '150t', '10', null, null, '19');
INSERT INTO `measure_scale` VALUES ('191', null, '', null, '50t', '5', null, null, '19');
INSERT INTO `measure_scale` VALUES ('192', null, '', null, '1000Kg', '12', null, null, '20');
INSERT INTO `measure_scale` VALUES ('193', null, '', null, '120t', '22', null, null, '20');
INSERT INTO `measure_scale` VALUES ('194', null, '', null, '300Kg', '10', null, null, '20');
INSERT INTO `measure_scale` VALUES ('195', null, '', null, '50t', '18', null, null, '20');
INSERT INTO `measure_scale` VALUES ('196', null, '', null, '600Kg', '11', null, null, '20');
INSERT INTO `measure_scale` VALUES ('197', null, '', null, '6Kg', '2', null, null, '20');
INSERT INTO `measure_scale` VALUES ('198', null, '', null, '80t', '20', null, null, '20');
INSERT INTO `measure_scale` VALUES ('199', null, '', null, '75Kg', '6', null, null, '20');
INSERT INTO `measure_scale` VALUES ('200', null, '', null, '30t', '15', null, null, '20');
INSERT INTO `measure_scale` VALUES ('201', null, '', null, '30Kg', '4', null, null, '20');
INSERT INTO `measure_scale` VALUES ('202', null, '', null, '100t', '21', null, null, '20');
INSERT INTO `measure_scale` VALUES ('203', null, '', null, '150Kg', '8', null, null, '20');
INSERT INTO `measure_scale` VALUES ('204', null, '', null, '60Kg', '5', null, null, '20');
INSERT INTO `measure_scale` VALUES ('205', null, '', null, '200Kg', '9', null, null, '20');
INSERT INTO `measure_scale` VALUES ('206', null, '', null, '60t', '19', null, null, '20');
INSERT INTO `measure_scale` VALUES ('207', null, '', null, '180t', '24', null, null, '20');
INSERT INTO `measure_scale` VALUES ('208', null, '', null, '100Kg', '7', null, null, '20');
INSERT INTO `measure_scale` VALUES ('209', null, '', null, '3Kg', '1', null, null, '20');
INSERT INTO `measure_scale` VALUES ('210', null, '', null, '0', '0', null, null, '20');
INSERT INTO `measure_scale` VALUES ('211', null, '', null, '200t', '24', null, null, '20');
INSERT INTO `measure_scale` VALUES ('212', null, '', null, '40t', '16', null, null, '20');
INSERT INTO `measure_scale` VALUES ('213', null, '', null, '150t', '23', null, null, '20');
INSERT INTO `measure_scale` VALUES ('214', null, '', null, '15Kg', '3', null, null, '20');
INSERT INTO `measure_scale` VALUES ('215', null, '', null, '150t', '10', null, null, '21');
INSERT INTO `measure_scale` VALUES ('216', null, '', null, '10t', '5', null, null, '21');
INSERT INTO `measure_scale` VALUES ('217', null, '', null, '50t', '8', null, null, '21');
INSERT INTO `measure_scale` VALUES ('218', null, '', null, '5t', '4', null, null, '21');
INSERT INTO `measure_scale` VALUES ('219', null, '', null, '3t', '3', null, null, '21');
INSERT INTO `measure_scale` VALUES ('220', null, '', null, '1t', '1', null, null, '21');
INSERT INTO `measure_scale` VALUES ('221', null, '', null, '30t', '7', null, null, '21');
INSERT INTO `measure_scale` VALUES ('222', null, '', null, '200t', '11', null, null, '21');
INSERT INTO `measure_scale` VALUES ('223', null, '', null, '0', '0', null, null, '21');
INSERT INTO `measure_scale` VALUES ('224', null, '', null, '20t', '6', null, null, '21');
INSERT INTO `measure_scale` VALUES ('225', null, '', null, '2t', '2', null, null, '21');
INSERT INTO `measure_scale` VALUES ('226', null, '', null, '100t', '9', null, null, '21');
INSERT INTO `measure_scale` VALUES ('227', null, '', null, '100包/min', '0', null, null, '22');
INSERT INTO `measure_scale` VALUES ('228', null, '', null, '300包/min', '2', null, null, '22');
INSERT INTO `measure_scale` VALUES ('229', null, '', null, '800包/min', '7', null, null, '22');
INSERT INTO `measure_scale` VALUES ('230', null, '', null, '1000包/min', '9', null, null, '22');
INSERT INTO `measure_scale` VALUES ('231', null, '', null, '600包/min', '5', null, null, '22');
INSERT INTO `measure_scale` VALUES ('232', null, '', null, '500包/min', '4', null, null, '22');
INSERT INTO `measure_scale` VALUES ('233', null, '', null, '700包/min', '6', null, null, '22');
INSERT INTO `measure_scale` VALUES ('234', null, '', null, '900包/min', '8', null, null, '22');
INSERT INTO `measure_scale` VALUES ('235', null, '', null, '400包/min', '3', null, null, '22');
INSERT INTO `measure_scale` VALUES ('236', null, '', null, '200包/min', '1', null, null, '22');
INSERT INTO `measure_scale` VALUES ('237', null, '', null, '10瓶/min', '0', null, null, '23');
INSERT INTO `measure_scale` VALUES ('238', null, '', null, '60瓶/min', '5', null, null, '23');
INSERT INTO `measure_scale` VALUES ('239', null, '', null, '600瓶/min', '14', null, null, '23');
INSERT INTO `measure_scale` VALUES ('240', null, '', null, '50瓶/min', '4', null, null, '23');
INSERT INTO `measure_scale` VALUES ('241', null, '', null, '70瓶/min', '6', null, null, '23');
INSERT INTO `measure_scale` VALUES ('242', null, '', null, '700瓶/min', '15', null, null, '23');
INSERT INTO `measure_scale` VALUES ('243', null, '', null, '900瓶/min', '17', null, null, '23');
INSERT INTO `measure_scale` VALUES ('244', null, '', null, '100瓶/min', '9', null, null, '23');
INSERT INTO `measure_scale` VALUES ('245', null, '', null, '40瓶/min', '3', null, null, '23');
INSERT INTO `measure_scale` VALUES ('246', null, '', null, '30瓶/min', '2', null, null, '23');
INSERT INTO `measure_scale` VALUES ('247', null, '', null, '90瓶/min', '8', null, null, '23');
INSERT INTO `measure_scale` VALUES ('248', null, '', null, '400瓶/min', '12', null, null, '23');
INSERT INTO `measure_scale` VALUES ('249', null, '', null, '200瓶/min', '10', null, null, '23');
INSERT INTO `measure_scale` VALUES ('250', null, '', null, '300瓶/min', '11', null, null, '23');
INSERT INTO `measure_scale` VALUES ('251', null, '', null, '80瓶/min', '7', null, null, '23');
INSERT INTO `measure_scale` VALUES ('252', null, '', null, '1000瓶/min', '18', null, null, '23');
INSERT INTO `measure_scale` VALUES ('253', null, '', null, '800瓶/min', '16', null, null, '23');
INSERT INTO `measure_scale` VALUES ('254', null, '', null, '20瓶/min', '1', null, null, '23');
INSERT INTO `measure_scale` VALUES ('255', null, '', null, '500瓶/min', '13', null, null, '23');
INSERT INTO `measure_scale` VALUES ('256', null, '', null, '1000mL', '0', null, null, '24');
INSERT INTO `measure_scale` VALUES ('257', null, '', null, '80L/分钟', '7', null, null, '25');
INSERT INTO `measure_scale` VALUES ('258', null, '', null, '0', '0', null, null, '25');
INSERT INTO `measure_scale` VALUES ('259', null, '', null, '40L/分钟', '3', null, null, '25');
INSERT INTO `measure_scale` VALUES ('260', null, '', null, '60L/分钟', '5', null, null, '25');
INSERT INTO `measure_scale` VALUES ('261', null, '', null, '5', '1', null, null, '25');
INSERT INTO `measure_scale` VALUES ('262', null, '', null, '70L/分钟', '6', null, null, '25');
INSERT INTO `measure_scale` VALUES ('263', null, '', null, '30L/分钟', '2', null, null, '25');
INSERT INTO `measure_scale` VALUES ('264', null, '', null, '50L/分钟', '4', null, null, '25');
INSERT INTO `measure_scale` VALUES ('265', null, '', null, '100%VOL', '5', null, null, '26');
INSERT INTO `measure_scale` VALUES ('266', null, '', null, '0%VOL', '0', null, null, '26');
INSERT INTO `measure_scale` VALUES ('267', null, '', null, '40%VOL', '2', null, null, '26');
INSERT INTO `measure_scale` VALUES ('268', null, '', null, '50%VOL', '3', null, null, '26');
INSERT INTO `measure_scale` VALUES ('269', null, '', null, '70%VOL', '4', null, null, '26');
INSERT INTO `measure_scale` VALUES ('270', null, '', null, '30%VOL', '1', null, null, '26');
INSERT INTO `measure_scale` VALUES ('271', null, '', null, '0.9', '3', null, null, '27');
INSERT INTO `measure_scale` VALUES ('272', null, '', null, '1.7', '11', null, null, '27');
INSERT INTO `measure_scale` VALUES ('273', null, '', null, '2.0', '14', null, null, '27');
INSERT INTO `measure_scale` VALUES ('274', null, '', null, '1.5', '9', null, null, '27');
INSERT INTO `measure_scale` VALUES ('275', null, '', null, '1.0', '4', null, null, '27');
INSERT INTO `measure_scale` VALUES ('276', null, '', null, '0.8', '2', null, null, '27');
INSERT INTO `measure_scale` VALUES ('277', null, '', null, '0.7', '1', null, null, '27');
INSERT INTO `measure_scale` VALUES ('278', null, '', null, '1.9', '13', null, null, '27');
INSERT INTO `measure_scale` VALUES ('279', null, '', null, '1.6', '10', null, null, '27');
INSERT INTO `measure_scale` VALUES ('280', null, '', null, '1.2', '6', null, null, '27');
INSERT INTO `measure_scale` VALUES ('281', null, '', null, '0.6', '0', null, null, '27');
INSERT INTO `measure_scale` VALUES ('282', null, '', null, '1.8', '12', null, null, '27');
INSERT INTO `measure_scale` VALUES ('283', null, '', null, '1.3', '7', null, null, '27');
INSERT INTO `measure_scale` VALUES ('284', null, '', null, '1.1', '5', null, null, '27');
INSERT INTO `measure_scale` VALUES ('285', null, '', null, '1.4', '8', null, null, '27');
INSERT INTO `measure_scale` VALUES ('286', null, '', null, '32%', '4', null, null, '28');
INSERT INTO `measure_scale` VALUES ('287', null, '', null, '82%', '10', null, null, '28');
INSERT INTO `measure_scale` VALUES ('288', null, '', null, '80%', '9', null, null, '28');
INSERT INTO `measure_scale` VALUES ('289', null, '', null, '90%', '11', null, null, '28');
INSERT INTO `measure_scale` VALUES ('290', null, '', null, '50%', '7', null, null, '28');
INSERT INTO `measure_scale` VALUES ('291', null, '', null, '62%', '8', null, null, '28');
INSERT INTO `measure_scale` VALUES ('292', null, '', null, '10%', '1', null, null, '28');
INSERT INTO `measure_scale` VALUES ('293', null, '', null, '100%', '12', null, null, '28');
INSERT INTO `measure_scale` VALUES ('294', null, '', null, '28%', '3', null, null, '28');
INSERT INTO `measure_scale` VALUES ('295', null, '', null, '0%', '0', null, null, '28');
INSERT INTO `measure_scale` VALUES ('296', null, '', null, '40%', '5', null, null, '28');
INSERT INTO `measure_scale` VALUES ('297', null, '', null, '20%', '2', null, null, '28');
INSERT INTO `measure_scale` VALUES ('298', null, '', null, '45%', '6', null, null, '28');
INSERT INTO `measure_scale` VALUES ('299', null, '', null, '25', '2', null, null, '29');
INSERT INTO `measure_scale` VALUES ('300', null, '', null, '0', '0', null, null, '29');
INSERT INTO `measure_scale` VALUES ('301', null, '', null, '15', '1', null, null, '29');
INSERT INTO `measure_scale` VALUES ('302', null, '', null, '45', '3', null, null, '29');
INSERT INTO `measure_scale` VALUES ('303', null, '', null, '6m³/h', '7', null, null, '30');
INSERT INTO `measure_scale` VALUES ('304', null, '', null, '2.5m³/h', '5', null, null, '30');
INSERT INTO `measure_scale` VALUES ('305', null, '', null, '0.025', '1', null, null, '30');
INSERT INTO `measure_scale` VALUES ('306', null, '', null, '0.016', '0', null, null, '30');
INSERT INTO `measure_scale` VALUES ('307', null, '', null, '0.06', '3', null, null, '30');
INSERT INTO `measure_scale` VALUES ('308', null, '', null, '1.6m³/h', '4', null, null, '30');
INSERT INTO `measure_scale` VALUES ('309', null, '', null, '4m³/h', '6', null, null, '30');
INSERT INTO `measure_scale` VALUES ('310', null, '', null, '10m³/h', '8', null, null, '30');
INSERT INTO `measure_scale` VALUES ('311', null, '', null, '0.04', '2', null, null, '30');
INSERT INTO `measure_scale` VALUES ('312', null, '', null, 'DN15', '1', null, null, '31');
INSERT INTO `measure_scale` VALUES ('313', null, '', null, 'DN300', '14', null, null, '31');
INSERT INTO `measure_scale` VALUES ('314', null, '', null, 'DN150', '11', null, null, '31');
INSERT INTO `measure_scale` VALUES ('315', null, '', null, 'DN25', '3', null, null, '31');
INSERT INTO `measure_scale` VALUES ('316', null, '', null, 'DN450', '17', null, null, '31');
INSERT INTO `measure_scale` VALUES ('317', null, '', null, 'DN40', '5', null, null, '31');
INSERT INTO `measure_scale` VALUES ('318', null, '', null, 'DN80', '8', null, null, '31');
INSERT INTO `measure_scale` VALUES ('319', null, '', null, 'DN250', '13', null, null, '31');
INSERT INTO `measure_scale` VALUES ('320', null, '', null, 'DN50', '6', null, null, '31');
INSERT INTO `measure_scale` VALUES ('321', null, '', null, 'DN65', '7', null, null, '31');
INSERT INTO `measure_scale` VALUES ('322', null, '', null, 'DN700', '20', null, null, '31');
INSERT INTO `measure_scale` VALUES ('323', null, '', null, 'DN2000', '27', null, null, '31');
INSERT INTO `measure_scale` VALUES ('324', null, '', null, 'DN32', '4', null, null, '31');
INSERT INTO `measure_scale` VALUES ('325', null, '', null, 'DN1200', '24', null, null, '31');
INSERT INTO `measure_scale` VALUES ('326', null, '', null, 'DN20', '2', null, null, '31');
INSERT INTO `measure_scale` VALUES ('327', null, '', null, 'DN1000', '23', null, null, '31');
INSERT INTO `measure_scale` VALUES ('328', null, '', null, 'DN800', '21', null, null, '31');
INSERT INTO `measure_scale` VALUES ('329', null, '', null, 'DN900', '22', null, null, '31');
INSERT INTO `measure_scale` VALUES ('330', null, '', null, 'DN350', '15', null, null, '31');
INSERT INTO `measure_scale` VALUES ('331', null, '', null, 'DN1800', '26', null, null, '31');
INSERT INTO `measure_scale` VALUES ('332', null, '', null, 'DN100', '9', null, null, '31');
INSERT INTO `measure_scale` VALUES ('333', null, '', null, 'DN200', '12', null, null, '31');
INSERT INTO `measure_scale` VALUES ('334', null, '', null, 'DN10', '0', null, null, '31');
INSERT INTO `measure_scale` VALUES ('335', null, '', null, 'DN400', '16', null, null, '31');
INSERT INTO `measure_scale` VALUES ('336', null, '', null, 'DN1400', '25', null, null, '31');
INSERT INTO `measure_scale` VALUES ('337', null, '', null, 'DN600', '19', null, null, '31');
INSERT INTO `measure_scale` VALUES ('338', null, '', null, 'DN500', '18', null, null, '31');
INSERT INTO `measure_scale` VALUES ('339', null, '', null, 'DN125', '10', null, null, '31');
INSERT INTO `measure_scale` VALUES ('340', null, '', null, '', '10', null, null, '22');
INSERT INTO `measure_scale` VALUES ('341', null, '', null, '0.04m³/h', '1', null, null, '32');
INSERT INTO `measure_scale` VALUES ('342', null, '', null, '8m³/h', '14', null, null, '32');
INSERT INTO `measure_scale` VALUES ('343', null, '', null, '100m³/h', '23', null, null, '32');
INSERT INTO `measure_scale` VALUES ('344', null, '', null, '0.6m³/h', '7', null, null, '32');
INSERT INTO `measure_scale` VALUES ('345', null, '', null, '0.5m³/h', '6', null, null, '32');
INSERT INTO `measure_scale` VALUES ('346', null, '', null, '800m³/h', '27', null, null, '32');
INSERT INTO `measure_scale` VALUES ('347', null, '', null, '5m³/h', '13', null, null, '32');
INSERT INTO `measure_scale` VALUES ('348', null, '', null, '9m³/h', '15', null, null, '32');
INSERT INTO `measure_scale` VALUES ('349', null, '', null, '13m³/h', '17', null, null, '32');
INSERT INTO `measure_scale` VALUES ('350', null, '', null, '15m³/h', '18', null, null, '32');
INSERT INTO `measure_scale` VALUES ('351', null, '', null, '0.45m³/h', '5', null, null, '32');
INSERT INTO `measure_scale` VALUES ('352', null, '', null, '0.8m³/h', '8', null, null, '32');
INSERT INTO `measure_scale` VALUES ('353', null, '', null, '0.06m³/h', '2', null, null, '32');
INSERT INTO `measure_scale` VALUES ('354', null, '', null, '0.15m³/h', '3', null, null, '32');
INSERT INTO `measure_scale` VALUES ('355', null, '', null, '250m³/h', '25', null, null, '32');
INSERT INTO `measure_scale` VALUES ('356', null, '', null, '1.5m³/h', '10', null, null, '32');
INSERT INTO `measure_scale` VALUES ('357', null, '', null, '40m³/h', '20', null, null, '32');
INSERT INTO `measure_scale` VALUES ('358', null, '', null, '300m³/h', '26', null, null, '32');
INSERT INTO `measure_scale` VALUES ('359', null, '', null, '0.4m³/h', '4', null, null, '32');
INSERT INTO `measure_scale` VALUES ('360', null, '', null, '20m³/h', '19', null, null, '32');
INSERT INTO `measure_scale` VALUES ('361', null, '', null, '2m³/h', '11', null, null, '32');
INSERT INTO `measure_scale` VALUES ('362', null, '', null, '10m³/h', '16', null, null, '32');
INSERT INTO `measure_scale` VALUES ('363', null, '', null, '200m³/h', '24', null, null, '32');
INSERT INTO `measure_scale` VALUES ('364', null, '', null, '4m³/h', '12', null, null, '32');
INSERT INTO `measure_scale` VALUES ('365', null, '', null, '70m³/h', '22', null, null, '32');
INSERT INTO `measure_scale` VALUES ('366', null, '', null, '1m³/h', '9', null, null, '32');
INSERT INTO `measure_scale` VALUES ('367', null, '', null, '1700m³/h', '22', null, null, '33');
INSERT INTO `measure_scale` VALUES ('368', null, '', null, '1380m³/h', '21', null, null, '33');
INSERT INTO `measure_scale` VALUES ('369', null, '', null, '11000m³/h', '26', null, null, '33');
INSERT INTO `measure_scale` VALUES ('370', null, '', null, '150m³/h', '11', null, null, '33');
INSERT INTO `measure_scale` VALUES ('371', null, '', null, '320m³/h', '15', null, null, '33');
INSERT INTO `measure_scale` VALUES ('372', null, '', null, '4960m³/h', '24', null, null, '33');
INSERT INTO `measure_scale` VALUES ('373', null, '', null, '22m³/h', '5', null, null, '33');
INSERT INTO `measure_scale` VALUES ('374', null, '', null, '970m³/h', '19', null, null, '33');
INSERT INTO `measure_scale` VALUES ('375', null, '', null, '480m³/h', '16', null, null, '33');
INSERT INTO `measure_scale` VALUES ('376', null, '', null, '50m³/h', '8', null, null, '33');
INSERT INTO `measure_scale` VALUES ('377', null, '', null, '70m³/h', '9', null, null, '33');
INSERT INTO `measure_scale` VALUES ('378', null, '', null, '640m³/h', '18', null, null, '33');
INSERT INTO `measure_scale` VALUES ('379', null, '', null, '36m³/h', '6', null, null, '33');
INSERT INTO `measure_scale` VALUES ('380', null, '', null, '580m³/h', '17', null, null, '33');
INSERT INTO `measure_scale` VALUES ('381', null, '', null, '8000m³/h', '25', null, null, '33');
INSERT INTO `measure_scale` VALUES ('382', null, '', null, '8m³/h', '1', null, null, '33');
INSERT INTO `measure_scale` VALUES ('383', null, '', null, '130m³/h', '10', null, null, '33');
INSERT INTO `measure_scale` VALUES ('384', null, '', null, '40m³/h', '7', null, null, '33');
INSERT INTO `measure_scale` VALUES ('385', null, '', null, '1200m³/h', '20', null, null, '33');
INSERT INTO `measure_scale` VALUES ('386', null, '', null, '8.5m³/h', '2', null, null, '33');
INSERT INTO `measure_scale` VALUES ('387', null, '', null, '5m³/h', '0', null, null, '33');
INSERT INTO `measure_scale` VALUES ('388', null, '', null, '280m³/h', '14', null, null, '33');
INSERT INTO `measure_scale` VALUES ('389', null, '', null, '220m³/h', '13', null, null, '33');
INSERT INTO `measure_scale` VALUES ('390', null, '', null, '18m³/h', '4', null, null, '33');
INSERT INTO `measure_scale` VALUES ('391', null, '', null, '2240m³/h', '23', null, null, '33');
INSERT INTO `measure_scale` VALUES ('392', null, '', null, '15m³/h', '3', null, null, '33');
INSERT INTO `measure_scale` VALUES ('393', null, '', null, '200m³/h', '12', null, null, '33');
INSERT INTO `measure_scale` VALUES ('395', null, '', null, '0MPa', '1', null, null, '35');
INSERT INTO `measure_scale` VALUES ('396', null, '', null, '2.5MPa', '14', null, null, '35');
INSERT INTO `measure_scale` VALUES ('397', null, '', null, '0.25MPa', '5', null, null, '35');
INSERT INTO `measure_scale` VALUES ('398', null, '', null, '1.0MPa', '11', null, null, '35');
INSERT INTO `measure_scale` VALUES ('399', null, '', null, '0.15MPa', '3', null, null, '35');
INSERT INTO `measure_scale` VALUES ('400', null, '', null, '0.6MPa', '9', null, null, '35');
INSERT INTO `measure_scale` VALUES ('401', null, '', null, '60MPa', '20', null, null, '35');
INSERT INTO `measure_scale` VALUES ('402', null, '', null, '0.3MPa', '6', null, null, '35');
INSERT INTO `measure_scale` VALUES ('403', null, '', null, '40MPa', '19', null, null, '35');
INSERT INTO `measure_scale` VALUES ('404', null, '', null, '4MPa', '15', null, null, '35');
INSERT INTO `measure_scale` VALUES ('405', null, '', null, '-0.1MPa', '0', null, null, '35');
INSERT INTO `measure_scale` VALUES ('406', null, '', null, '0.5MPa', '8', null, null, '35');
INSERT INTO `measure_scale` VALUES ('407', null, '', null, '1.6MPa', '12', null, null, '35');
INSERT INTO `measure_scale` VALUES ('408', null, '', null, '0.9MPa', '10', null, null, '35');
INSERT INTO `measure_scale` VALUES ('409', null, '', null, '6MPa', '16', null, null, '35');
INSERT INTO `measure_scale` VALUES ('410', null, '', null, '10MPa', '17', null, null, '35');
INSERT INTO `measure_scale` VALUES ('411', null, '', null, '0.4MPa', '7', null, null, '35');
INSERT INTO `measure_scale` VALUES ('412', null, '', null, '100MPa', '21', null, null, '35');
INSERT INTO `measure_scale` VALUES ('413', null, '', null, '25MPa', '18', null, null, '35');
INSERT INTO `measure_scale` VALUES ('414', null, '', null, '2.4MPa', '13', null, null, '35');
INSERT INTO `measure_scale` VALUES ('415', null, '', null, '0.16MPa', '4', null, null, '35');
INSERT INTO `measure_scale` VALUES ('416', null, '', null, '0.06MPa', '2', null, null, '35');
INSERT INTO `measure_scale` VALUES ('417', null, '', null, '-125Pa', '2', null, null, '36');
INSERT INTO `measure_scale` VALUES ('418', null, '', null, '75Pa', '6', null, null, '36');
INSERT INTO `measure_scale` VALUES ('419', null, '', null, '2.5KPa', '13', null, null, '36');
INSERT INTO `measure_scale` VALUES ('420', null, '', null, '5KPa', '15', null, null, '36');
INSERT INTO `measure_scale` VALUES ('421', null, '', null, '500Pa', '9', null, null, '36');
INSERT INTO `measure_scale` VALUES ('422', null, '', null, '-500Pa', '0', null, null, '36');
INSERT INTO `measure_scale` VALUES ('423', null, '', null, '60Pa', '5', null, null, '36');
INSERT INTO `measure_scale` VALUES ('424', null, '', null, '125Pa', '7', null, null, '36');
INSERT INTO `measure_scale` VALUES ('425', null, '', null, '1KPa', '10', null, null, '36');
INSERT INTO `measure_scale` VALUES ('426', null, '', null, '15KPa', '18', null, null, '36');
INSERT INTO `measure_scale` VALUES ('427', null, '', null, '1.5KPa', '11', null, null, '36');
INSERT INTO `measure_scale` VALUES ('428', null, '', null, '-250Pa', '1', null, null, '36');
INSERT INTO `measure_scale` VALUES ('429', null, '', null, '30KPa', '20', null, null, '36');
INSERT INTO `measure_scale` VALUES ('430', null, '', null, '3KPa', '14', null, null, '36');
INSERT INTO `measure_scale` VALUES ('431', null, '', null, '0', '3', null, null, '36');
INSERT INTO `measure_scale` VALUES ('432', null, '', null, '10KPa', '17', null, null, '36');
INSERT INTO `measure_scale` VALUES ('433', null, '', null, '250Pa', '8', null, null, '36');
INSERT INTO `measure_scale` VALUES ('434', null, '', null, '8KPa', '16', null, null, '36');
INSERT INTO `measure_scale` VALUES ('435', null, '', null, '30Pa', '4', null, null, '36');
INSERT INTO `measure_scale` VALUES ('436', null, '', null, '25KPa', '19', null, null, '36');
INSERT INTO `measure_scale` VALUES ('437', null, '', null, '2KPa', '12', null, null, '36');
INSERT INTO `measure_scale` VALUES ('438', null, '', null, '0.15MPa', '3', null, null, '37');
INSERT INTO `measure_scale` VALUES ('439', null, '', null, '25MPa', '16', null, null, '37');
INSERT INTO `measure_scale` VALUES ('440', null, '', null, '0.3MPa', '6', null, null, '37');
INSERT INTO `measure_scale` VALUES ('441', null, '', null, '1.0MPa', '10', null, null, '37');
INSERT INTO `measure_scale` VALUES ('442', null, '', null, '60MPa', '18', null, null, '37');
INSERT INTO `measure_scale` VALUES ('443', null, '', null, '1.6MPa', '11', null, null, '37');
INSERT INTO `measure_scale` VALUES ('444', null, '', null, '2.5MPa', '12', null, null, '37');
INSERT INTO `measure_scale` VALUES ('445', null, '', null, '0.5MPa', '8', null, null, '37');
INSERT INTO `measure_scale` VALUES ('446', null, '', null, '10MPa', '15', null, null, '37');
INSERT INTO `measure_scale` VALUES ('447', null, '', null, '40MPa', '17', null, null, '37');
INSERT INTO `measure_scale` VALUES ('448', null, '', null, '-0.1MPa', '0', null, null, '37');
INSERT INTO `measure_scale` VALUES ('449', null, '', null, '0.6MPa', '9', null, null, '37');
INSERT INTO `measure_scale` VALUES ('450', null, '', null, '0.16MPa', '4', null, null, '37');
INSERT INTO `measure_scale` VALUES ('451', null, '', null, '0.06MPa', '2', null, null, '37');
INSERT INTO `measure_scale` VALUES ('452', null, '', null, '6MPa', '14', null, null, '37');
INSERT INTO `measure_scale` VALUES ('453', null, '', null, '4MPa', '13', null, null, '37');
INSERT INTO `measure_scale` VALUES ('454', null, '', null, '0.25MPa', '5', null, null, '37');
INSERT INTO `measure_scale` VALUES ('455', null, '', null, '0.4MPa', '7', null, null, '37');
INSERT INTO `measure_scale` VALUES ('456', null, '', null, '0', '1', null, null, '37');
INSERT INTO `measure_scale` VALUES ('457', null, '', null, '40KPa', '1', null, null, '38');
INSERT INTO `measure_scale` VALUES ('458', null, '', null, '0', '0', null, null, '38');
INSERT INTO `measure_scale` VALUES ('459', null, '', null, '0', '0', null, null, '39');
INSERT INTO `measure_scale` VALUES ('460', null, '', null, '40MPa', '1', null, null, '39');
INSERT INTO `measure_scale` VALUES ('461', null, '', null, '------', '0', null, null, '40');
INSERT INTO `measure_scale` VALUES ('462', null, '', null, '320Km/h', '3', null, null, '41');
INSERT INTO `measure_scale` VALUES ('463', null, '', null, '20Km/h', '1', null, null, '41');
INSERT INTO `measure_scale` VALUES ('464', null, '', null, '5Km/h', '0', null, null, '41');
INSERT INTO `measure_scale` VALUES ('465', null, '', null, '200Km/h', '2', null, null, '41');
INSERT INTO `measure_scale` VALUES ('466', null, '', null, '1.5A', '1', null, null, '42');
INSERT INTO `measure_scale` VALUES ('467', null, '', null, '3A', '3', null, null, '42');
INSERT INTO `measure_scale` VALUES ('468', null, '', null, '60A', '10', null, null, '42');
INSERT INTO `measure_scale` VALUES ('469', null, '', null, '40A', '9', null, null, '42');
INSERT INTO `measure_scale` VALUES ('470', null, '', null, '100A', '12', null, null, '42');
INSERT INTO `measure_scale` VALUES ('471', null, '', null, '5A', '4', null, null, '42');
INSERT INTO `measure_scale` VALUES ('472', null, '', null, '10A', '6', null, null, '42');
INSERT INTO `measure_scale` VALUES ('473', null, '', null, '20A', '7', null, null, '42');
INSERT INTO `measure_scale` VALUES ('474', null, '', null, '6A', '5', null, null, '42');
INSERT INTO `measure_scale` VALUES ('475', null, '', null, '2.5A', '2', null, null, '42');
INSERT INTO `measure_scale` VALUES ('476', null, '', null, '30A', '8', null, null, '42');
INSERT INTO `measure_scale` VALUES ('477', null, '', null, '80A', '11', null, null, '42');
INSERT INTO `measure_scale` VALUES ('478', null, '', null, '3x1A', '1', null, null, '43');
INSERT INTO `measure_scale` VALUES ('479', null, '', null, '3x1.5A', '2', null, null, '43');
INSERT INTO `measure_scale` VALUES ('480', null, '', null, '3x5A', '4', null, null, '43');
INSERT INTO `measure_scale` VALUES ('481', null, '', null, '3x40A', '8', null, null, '43');
INSERT INTO `measure_scale` VALUES ('482', null, '', null, '3x80A', '10', null, null, '43');
INSERT INTO `measure_scale` VALUES ('483', null, '', null, '3x30A', '7', null, null, '43');
INSERT INTO `measure_scale` VALUES ('484', null, '', null, '3x10A', '5', null, null, '43');
INSERT INTO `measure_scale` VALUES ('485', null, '', null, '3x60A', '9', null, null, '43');
INSERT INTO `measure_scale` VALUES ('486', null, '', null, '3x20A', '6', null, null, '43');
INSERT INTO `measure_scale` VALUES ('487', null, '', null, '3x3A', '3', null, null, '43');
INSERT INTO `measure_scale` VALUES ('488', null, '', null, '3x100A', '11', null, null, '43');
INSERT INTO `measure_scale` VALUES ('489', null, '', null, '100A/5', '4', null, null, '44');
INSERT INTO `measure_scale` VALUES ('490', null, '', null, '150A/5A', '5', null, null, '44');
INSERT INTO `measure_scale` VALUES ('491', null, '', null, '400A/5A', '9', null, null, '44');
INSERT INTO `measure_scale` VALUES ('492', null, '', null, '75A/A', '3', null, null, '44');
INSERT INTO `measure_scale` VALUES ('493', null, '', null, '200A/5A', '6', null, null, '44');
INSERT INTO `measure_scale` VALUES ('494', null, '', null, '300A/5A', '8', null, null, '44');
INSERT INTO `measure_scale` VALUES ('495', null, '', null, '500A/5A', '10', null, null, '44');
INSERT INTO `measure_scale` VALUES ('496', null, '', null, '250A/5A', '7', null, null, '44');
INSERT INTO `measure_scale` VALUES ('497', null, '', null, '30A/5A', '1', null, null, '44');
INSERT INTO `measure_scale` VALUES ('498', null, '', null, '600A/5A', '11', null, null, '44');
INSERT INTO `measure_scale` VALUES ('499', null, '', null, '50A/5A', '2', null, null, '44');
INSERT INTO `measure_scale` VALUES ('500', null, '', null, '10KV/100V', '0', null, null, '45');
INSERT INTO `measure_scale` VALUES ('501', null, '', null, '35KV/100V', '1', null, null, '45');
INSERT INTO `measure_scale` VALUES ('502', null, '', null, '110KV/100V', '2', null, null, '45');
INSERT INTO `measure_scale` VALUES ('503', null, '', null, '(110√3)/KV/(100/√3)V', '2', null, null, '46');
INSERT INTO `measure_scale` VALUES ('504', null, '', null, '(35/√3)KV/(100/√3)V', '1', null, null, '46');
INSERT INTO `measure_scale` VALUES ('505', null, '', null, '(10/√3)KV/(100/√3)V', '0', null, null, '46');
INSERT INTO `measure_scale` VALUES ('506', null, '', null, '500MΩ', '5', null, null, '47');
INSERT INTO `measure_scale` VALUES ('507', null, '', null, '2000MΩ', '7', null, null, '47');
INSERT INTO `measure_scale` VALUES ('508', null, '', null, '2500MΩ', '8', null, null, '47');
INSERT INTO `measure_scale` VALUES ('509', null, '', null, '250MΩ', '4', null, null, '47');
INSERT INTO `measure_scale` VALUES ('510', null, '', null, '1000MΩ', '6', null, null, '47');
INSERT INTO `measure_scale` VALUES ('511', null, '', null, '0', '0', null, null, '47');
INSERT INTO `measure_scale` VALUES ('512', null, '', null, '20MΩ', '2', null, null, '47');
INSERT INTO `measure_scale` VALUES ('513', null, '', null, '100MΩ', '3', null, null, '47');
INSERT INTO `measure_scale` VALUES ('514', null, '', null, '20Ω', '1', null, null, '48');
INSERT INTO `measure_scale` VALUES ('515', null, '', null, '20KΩ', '7', null, null, '48');
INSERT INTO `measure_scale` VALUES ('516', null, '', null, '1000Ω', '4', null, null, '48');
INSERT INTO `measure_scale` VALUES ('517', null, '', null, '10KΩ', '6', null, null, '48');
INSERT INTO `measure_scale` VALUES ('518', null, '', null, '600Ω', '3', null, null, '48');
INSERT INTO `measure_scale` VALUES ('519', null, '', null, '200Ω', '2', null, null, '48');
INSERT INTO `measure_scale` VALUES ('520', null, '', null, '0.01Ω', '0', null, null, '48');
INSERT INTO `measure_scale` VALUES ('521', null, '', null, '2000Ω', '5', null, null, '48');
INSERT INTO `measure_scale` VALUES ('522', null, '', null, '------', '0', null, null, '49');
INSERT INTO `measure_scale` VALUES ('523', null, '', null, '------', '0', null, null, '50');
INSERT INTO `measure_scale` VALUES ('524', null, '', null, '------', '0', null, null, '51');
INSERT INTO `measure_scale` VALUES ('525', null, '', null, '------', '0', null, null, '52');
INSERT INTO `measure_scale` VALUES ('526', null, '', null, '------', '0', null, null, '53');
INSERT INTO `measure_scale` VALUES ('527', null, '', null, '------', '0', null, null, '54');
INSERT INTO `measure_scale` VALUES ('528', null, '', null, '------', '0', null, null, '55');
INSERT INTO `measure_scale` VALUES ('529', null, '', null, '------', '0', null, null, '56');
INSERT INTO `measure_scale` VALUES ('530', null, '', null, '------', '0', null, null, '57');
INSERT INTO `measure_scale` VALUES ('531', null, '', null, '------', '0', null, null, '58');
INSERT INTO `measure_scale` VALUES ('532', null, '', null, '------', '0', null, null, '59');
INSERT INTO `measure_scale` VALUES ('533', null, '', null, '------', '0', null, null, '60');
INSERT INTO `measure_scale` VALUES ('534', null, '', null, '------', '0', null, null, '61');
INSERT INTO `measure_scale` VALUES ('535', null, '', null, '------', '0', null, null, '62');
INSERT INTO `measure_scale` VALUES ('536', null, '', null, '------', '0', null, null, '63');
INSERT INTO `measure_scale` VALUES ('537', null, '', null, '------', '0', null, null, '64');
INSERT INTO `measure_scale` VALUES ('538', null, '', null, 'AC70BHL', '1', null, null, '65');
INSERT INTO `measure_scale` VALUES ('539', null, '', null, 'AC100dBHL,SC50dBHL', '2', null, null, '65');
INSERT INTO `measure_scale` VALUES ('540', null, '', null, 'AC120dBHL,SC70dBHL', '4', null, null, '65');
INSERT INTO `measure_scale` VALUES ('541', null, '', null, 'AC110dBHL,BC70dBHL', '3', null, null, '65');
INSERT INTO `measure_scale` VALUES ('542', null, '', null, '0', '0', null, null, '65');
INSERT INTO `measure_scale` VALUES ('543', null, '', null, '0', '0', null, null, '66');
INSERT INTO `measure_scale` VALUES ('544', null, '', null, '100000ppm', '10', null, null, '66');
INSERT INTO `measure_scale` VALUES ('545', null, '', null, '1000ppm', '4', null, null, '66');
INSERT INTO `measure_scale` VALUES ('546', null, '', null, '2000ppm', '5', null, null, '66');
INSERT INTO `measure_scale` VALUES ('547', null, '', null, '500ppm', '3', null, null, '66');
INSERT INTO `measure_scale` VALUES ('548', null, '', null, '20000ppm', '8', null, null, '66');
INSERT INTO `measure_scale` VALUES ('549', null, '', null, '200ppm', '2', null, null, '66');
INSERT INTO `measure_scale` VALUES ('550', null, '', null, '10000ppm', '7', null, null, '66');
INSERT INTO `measure_scale` VALUES ('551', null, '', null, '40000ppm', '9', null, null, '66');
INSERT INTO `measure_scale` VALUES ('552', null, '', null, '5000ppm', '6', null, null, '66');
INSERT INTO `measure_scale` VALUES ('553', null, '', null, '10%VOL', '11', null, null, '66');
INSERT INTO `measure_scale` VALUES ('554', null, '', null, '100ppm', '1', null, null, '66');
INSERT INTO `measure_scale` VALUES ('555', null, '', null, '0', '0', null, null, '67');
INSERT INTO `measure_scale` VALUES ('556', null, '', null, '500ppm', '3', null, null, '67');
INSERT INTO `measure_scale` VALUES ('557', null, '', null, '20ppm', '1', null, null, '67');
INSERT INTO `measure_scale` VALUES ('558', null, '', null, '1000ppm', '4', null, null, '67');
INSERT INTO `measure_scale` VALUES ('559', null, '', null, '50ppm', '2', null, null, '67');
INSERT INTO `measure_scale` VALUES ('560', null, '', null, '1000ppm', '2', null, null, '68');
INSERT INTO `measure_scale` VALUES ('561', null, '', null, '0', '0', null, null, '68');
INSERT INTO `measure_scale` VALUES ('562', null, '', null, '100ppm', '1', null, null, '68');
INSERT INTO `measure_scale` VALUES ('563', null, '', null, '1000ppm', '5', null, null, '69');
INSERT INTO `measure_scale` VALUES ('564', null, '', null, '100ppm', '3', null, null, '69');
INSERT INTO `measure_scale` VALUES ('565', null, '', null, '20ppm', '1', null, null, '69');
INSERT INTO `measure_scale` VALUES ('566', null, '', null, '50ppm', '2', null, null, '69');
INSERT INTO `measure_scale` VALUES ('567', null, '', null, '200ppm', '4', null, null, '69');
INSERT INTO `measure_scale` VALUES ('568', null, '', null, '0', '0', null, null, '69');
INSERT INTO `measure_scale` VALUES ('569', null, '', null, '5000ppm', '3', null, null, '68');
INSERT INTO `measure_scale` VALUES ('570', null, '', null, '0.00', '0', null, null, '70');
INSERT INTO `measure_scale` VALUES ('571', null, '', null, '14pH', '1', null, null, '70');
INSERT INTO `measure_scale` VALUES ('572', null, '', null, '0', '0', null, null, '71');
INSERT INTO `measure_scale` VALUES ('573', null, '', null, '100%LEL', '1', null, null, '71');
INSERT INTO `measure_scale` VALUES ('574', null, '', null, '100%LEL', '1', null, null, '72');
INSERT INTO `measure_scale` VALUES ('575', null, '', null, '0', '0', null, null, '72');
INSERT INTO `measure_scale` VALUES ('576', null, '', null, '------', '0', null, null, '73');
INSERT INTO `measure_scale` VALUES ('577', null, '', null, '0', '0', null, null, '74');
INSERT INTO `measure_scale` VALUES ('578', null, '', null, '10 μg/L', '1', null, null, '74');
INSERT INTO `measure_scale` VALUES ('579', null, '', null, '------', '0', null, null, '75');
INSERT INTO `measure_scale` VALUES ('580', null, '', null, '600nm', '3', null, null, '76');
INSERT INTO `measure_scale` VALUES ('581', null, '', null, '900nm', '6', null, null, '76');
INSERT INTO `measure_scale` VALUES ('582', null, '', null, '800nm', '5', null, null, '76');
INSERT INTO `measure_scale` VALUES ('583', null, '', null, '100nm', '0', null, null, '76');
INSERT INTO `measure_scale` VALUES ('584', null, '', null, '500nm', '2', null, null, '76');
INSERT INTO `measure_scale` VALUES ('585', null, '', null, '200nm', '1', null, null, '76');
INSERT INTO `measure_scale` VALUES ('586', null, '', null, '700nm', '4', null, null, '76');
INSERT INTO `measure_scale` VALUES ('587', null, '', null, '1000nm', '7', null, null, '76');
INSERT INTO `measure_scale` VALUES ('588', null, '', null, '190nm', '0', null, null, '77');
INSERT INTO `measure_scale` VALUES ('589', null, '', null, '900nm', '1', null, null, '77');
INSERT INTO `measure_scale` VALUES ('590', null, '', null, '400 cm-1', '2', null, null, '78');
INSERT INTO `measure_scale` VALUES ('591', null, '', null, '7800', '0', null, null, '78');
INSERT INTO `measure_scale` VALUES ('592', null, '', null, '4000', '1', null, null, '78');
INSERT INTO `measure_scale` VALUES ('593', null, '', null, '350 cm-1', '3', null, null, '78');
INSERT INTO `measure_scale` VALUES ('594', null, '', null, '850nm', '4', null, null, '79');
INSERT INTO `measure_scale` VALUES ('595', null, '', null, '250nm', '1', null, null, '79');
INSERT INTO `measure_scale` VALUES ('596', null, '', null, '360nm', '2', null, null, '79');
INSERT INTO `measure_scale` VALUES ('597', null, '', null, '200nm', '0', null, null, '79');
INSERT INTO `measure_scale` VALUES ('598', null, '', null, '900nm', '5', null, null, '79');
INSERT INTO `measure_scale` VALUES ('599', null, '', null, '600nm', '3', null, null, '79');
INSERT INTO `measure_scale` VALUES ('600', null, '', null, '190', '0', null, null, '80');
INSERT INTO `measure_scale` VALUES ('601', null, '', null, '900nm', '1', null, null, '80');
INSERT INTO `measure_scale` VALUES ('602', null, '', null, '------', '0', null, null, '81');
INSERT INTO `measure_scale` VALUES ('603', null, '', null, '------', '0', null, null, '82');
INSERT INTO `measure_scale` VALUES ('604', null, '', null, '40L/min', '2', null, null, '83');
INSERT INTO `measure_scale` VALUES ('605', null, '', null, '60L/min', '3', null, null, '83');
INSERT INTO `measure_scale` VALUES ('606', null, '', null, '400L/min', '5', null, null, '83');
INSERT INTO `measure_scale` VALUES ('607', null, '', null, '100L/min', '4', null, null, '83');
INSERT INTO `measure_scale` VALUES ('608', null, '', null, '10L/min', '1', null, null, '83');
INSERT INTO `measure_scale` VALUES ('609', null, '', null, '0', '0', null, null, '83');
INSERT INTO `measure_scale` VALUES ('610', null, '', null, '5L/min', '0', null, null, '84');
INSERT INTO `measure_scale` VALUES ('611', null, '', null, '100L/min', '4', null, null, '84');
INSERT INTO `measure_scale` VALUES ('612', null, '', null, '40L/min', '1', null, null, '84');
INSERT INTO `measure_scale` VALUES ('613', null, '', null, '60L/min', '2', null, null, '84');
INSERT INTO `measure_scale` VALUES ('614', null, '', null, '80L/min', '3', null, null, '84');
INSERT INTO `measure_scale` VALUES ('615', null, '', null, '80L/min', '2', null, null, '85');
INSERT INTO `measure_scale` VALUES ('616', null, '', null, '60L/min', '1', null, null, '85');
INSERT INTO `measure_scale` VALUES ('617', null, '', null, '100L/min', '3', null, null, '85');
INSERT INTO `measure_scale` VALUES ('618', null, '', null, '2.83L/min', '0', null, null, '85');
INSERT INTO `measure_scale` VALUES ('619', null, '', null, '0.01', '1', null, null, '86');
INSERT INTO `measure_scale` VALUES ('620', null, '', null, '0.001', '0', null, null, '86');
INSERT INTO `measure_scale` VALUES ('621', null, '', null, '80mg/m³', '3', null, null, '86');
INSERT INTO `measure_scale` VALUES ('622', null, '', null, '10mg/m³', '2', null, null, '86');
INSERT INTO `measure_scale` VALUES ('623', null, '', null, '100mg/m³', '4', null, null, '86');
INSERT INTO `measure_scale` VALUES ('624', null, '', null, '------', '0', null, null, '87');
INSERT INTO `measure_scale` VALUES ('625', null, '', null, '------', '0', null, null, '88');
INSERT INTO `measure_scale` VALUES ('626', null, '', null, '------', '0', null, null, '89');
INSERT INTO `measure_scale` VALUES ('627', null, '', null, '20mg/L', '1', null, null, '90');
INSERT INTO `measure_scale` VALUES ('628', null, '', null, '0', '0', null, null, '90');
INSERT INTO `measure_scale` VALUES ('629', null, '', null, '------', '0', null, null, '91');
INSERT INTO `measure_scale` VALUES ('630', null, '', null, '------', '0', null, null, '92');
INSERT INTO `measure_scale` VALUES ('631', null, '', null, '+20D', '1', null, null, '93');
INSERT INTO `measure_scale` VALUES ('632', null, '', null, '-20D', '0', null, null, '93');
INSERT INTO `measure_scale` VALUES ('633', null, '', null, '1200s', '1', null, null, '94');
INSERT INTO `measure_scale` VALUES ('634', null, '', null, '0', '0', null, null, '94');
INSERT INTO `measure_scale` VALUES ('635', null, '', null, '7%', '0', null, null, '95');
INSERT INTO `measure_scale` VALUES ('636', null, '', null, '40%', '1', null, null, '95');
INSERT INTO `measure_scale` VALUES ('637', null, '', null, '-20m-1', '0', null, null, '96');
INSERT INTO `measure_scale` VALUES ('638', null, '', null, '+20m-1', '1', null, null, '96');
INSERT INTO `measure_scale` VALUES ('639', null, '', null, '+25m-1', '1', null, null, '97');
INSERT INTO `measure_scale` VALUES ('640', null, '', null, '-25m-1', '0', null, null, '97');
INSERT INTO `measure_scale` VALUES ('641', null, '', null, '100mW/cm²', '1', null, null, '98');
INSERT INTO `measure_scale` VALUES ('642', null, '', null, '0', '0', null, null, '98');
INSERT INTO `measure_scale` VALUES ('643', null, '', null, '0', '0', null, null, '99');
INSERT INTO `measure_scale` VALUES ('644', null, '', null, '30Kg/min', '1', null, null, '99');
INSERT INTO `measure_scale` VALUES ('645', null, '', null, '70Kg/min', '2', null, null, '99');
INSERT INTO `measure_scale` VALUES ('646', null, '', null, 'DN400', '16', null, null, '100');
INSERT INTO `measure_scale` VALUES ('647', null, '', null, 'DN250', '13', null, null, '100');
INSERT INTO `measure_scale` VALUES ('648', null, '', null, 'DN150', '11', null, null, '100');
INSERT INTO `measure_scale` VALUES ('649', null, '', null, 'DN15', '0', null, null, '100');
INSERT INTO `measure_scale` VALUES ('650', null, '', null, 'DN125', '10', null, null, '100');
INSERT INTO `measure_scale` VALUES ('651', null, '', null, 'DN100', '9', null, null, '100');
INSERT INTO `measure_scale` VALUES ('652', null, '', null, 'DN700', '20', null, null, '100');
INSERT INTO `measure_scale` VALUES ('653', null, '', null, 'DN300', '14', null, null, '100');
INSERT INTO `measure_scale` VALUES ('654', null, '', null, 'DN1000', '23', null, null, '100');
INSERT INTO `measure_scale` VALUES ('655', null, '', null, 'DN20', '1', null, null, '100');
INSERT INTO `measure_scale` VALUES ('656', null, '', null, 'DN32', '4', null, null, '100');
INSERT INTO `measure_scale` VALUES ('657', null, '', null, 'DN25', '2', null, null, '100');
INSERT INTO `measure_scale` VALUES ('658', null, '', null, 'DN30', '3', null, null, '100');
INSERT INTO `measure_scale` VALUES ('659', null, '', null, 'DN450', '17', null, null, '100');
INSERT INTO `measure_scale` VALUES ('660', null, '', null, 'DN900', '22', null, null, '100');
INSERT INTO `measure_scale` VALUES ('661', null, '', null, 'DN200', '12', null, null, '100');
INSERT INTO `measure_scale` VALUES ('662', null, '', null, 'DN1200', '24', null, null, '100');
INSERT INTO `measure_scale` VALUES ('663', null, '', null, 'DN350', '15', null, null, '100');
INSERT INTO `measure_scale` VALUES ('664', null, '', null, 'DN2000', '28', null, null, '100');
INSERT INTO `measure_scale` VALUES ('665', null, '', null, 'DN50', '6', null, null, '100');
INSERT INTO `measure_scale` VALUES ('666', null, '', null, 'DN800', '21', null, null, '100');
INSERT INTO `measure_scale` VALUES ('667', null, '', null, 'DN1600', '26', null, null, '100');
INSERT INTO `measure_scale` VALUES ('668', null, '', null, 'DN65', '7', null, null, '100');
INSERT INTO `measure_scale` VALUES ('669', null, '', null, 'DN80', '8', null, null, '100');
INSERT INTO `measure_scale` VALUES ('670', null, '', null, 'DN600', '19', null, null, '100');
INSERT INTO `measure_scale` VALUES ('671', null, '', null, 'DN1800', '27', null, null, '100');
INSERT INTO `measure_scale` VALUES ('672', null, '', null, 'DN500', '18', null, null, '100');
INSERT INTO `measure_scale` VALUES ('673', null, '', null, 'DN40', '5', null, null, '100');
INSERT INTO `measure_scale` VALUES ('674', null, '', null, 'DN1400', '25', null, null, '100');
INSERT INTO `measure_scale` VALUES ('675', null, '', null, '500Kg', '8', null, null, '18');
INSERT INTO `measure_scale` VALUES ('676', null, '', null, '600Kg', '9', null, null, '18');
INSERT INTO `measure_scale` VALUES ('677', null, '', null, '800Kg', '10', null, null, '18');
INSERT INTO `measure_scale` VALUES ('678', null, '', null, '1000Kg', '11', null, null, '18');
INSERT INTO `measure_scale` VALUES ('679', null, '', null, '3200g', '19', null, null, '2');
INSERT INTO `measure_scale` VALUES ('680', null, '', null, '2Kg', '16', null, null, '2');
INSERT INTO `measure_scale` VALUES ('681', null, '', null, '15Kg', '26', null, null, '2');
INSERT INTO `measure_scale` VALUES ('682', null, '', null, '1500g', '15', null, null, '2');
INSERT INTO `measure_scale` VALUES ('683', null, '', null, '2000g', '17', null, null, '2');
INSERT INTO `measure_scale` VALUES ('684', null, '', null, '5200g', '22', null, null, '2');
INSERT INTO `measure_scale` VALUES ('685', null, '', null, '6Kg', '23', null, null, '2');
INSERT INTO `measure_scale` VALUES ('686', null, '', null, '10Kg', '25', null, null, '2');
INSERT INTO `measure_scale` VALUES ('687', null, '', null, '1.5Kg', '14', null, null, '2');
INSERT INTO `measure_scale` VALUES ('688', null, '', null, '5000g', '21', null, null, '2');
INSERT INTO `measure_scale` VALUES ('689', null, '', null, '8Kg', '24', null, null, '2');
INSERT INTO `measure_scale` VALUES ('690', null, '', null, '5Kg', '20', null, null, '2');
INSERT INTO `measure_scale` VALUES ('691', null, '', null, '20Kg', '27', null, null, '2');
INSERT INTO `measure_scale` VALUES ('692', null, '', null, '2200g', '18', null, null, '2');
INSERT INTO `measure_scale` VALUES ('693', null, '', null, '1200g', '13', null, null, '2');
INSERT INTO `measure_scale` VALUES ('694', null, '', null, '5Kg', '2', null, null, '101');
INSERT INTO `measure_scale` VALUES ('695', null, '', null, '0', '0', null, null, '101');
INSERT INTO `measure_scale` VALUES ('696', null, '', null, '3Kg', '1', null, null, '101');
INSERT INTO `measure_scale` VALUES ('697', null, '', null, '15Kg', '4', null, null, '101');
INSERT INTO `measure_scale` VALUES ('698', null, '', null, '10Kg', '3', null, null, '101');
INSERT INTO `measure_scale` VALUES ('699', null, '', null, '800Kg', '15', null, null, '101');
INSERT INTO `measure_scale` VALUES ('700', null, '', null, '2t', '17', null, null, '101');
INSERT INTO `measure_scale` VALUES ('701', null, '', null, '200Kg', '10', null, null, '101');
INSERT INTO `measure_scale` VALUES ('702', null, '', null, '75Kg', '7', null, null, '101');
INSERT INTO `measure_scale` VALUES ('703', null, '', null, '1000Kg', '16', null, null, '101');
INSERT INTO `measure_scale` VALUES ('704', null, '', null, '150Kg', '9', null, null, '101');
INSERT INTO `measure_scale` VALUES ('705', null, '', null, '500Kg', '13', null, null, '101');
INSERT INTO `measure_scale` VALUES ('706', null, '', null, '100Kg', '8', null, null, '101');
INSERT INTO `measure_scale` VALUES ('707', null, '', null, '30Kg', '5', null, null, '101');
INSERT INTO `measure_scale` VALUES ('708', null, '', null, '600Kg', '14', null, null, '101');
INSERT INTO `measure_scale` VALUES ('709', null, '', null, '3t', '18', null, null, '101');
INSERT INTO `measure_scale` VALUES ('710', null, '', null, '250Kg', '11', null, null, '101');
INSERT INTO `measure_scale` VALUES ('711', null, '', null, '300Kg', '12', null, null, '101');
INSERT INTO `measure_scale` VALUES ('712', null, '', null, '50Kg', '6', null, null, '101');
INSERT INTO `measure_scale` VALUES ('713', null, '', null, '30Kg', '5', null, null, '102');
INSERT INTO `measure_scale` VALUES ('714', null, '', null, '0', '0', null, null, '102');
INSERT INTO `measure_scale` VALUES ('715', null, '', null, '5Kg', '2', null, null, '102');
INSERT INTO `measure_scale` VALUES ('716', null, '', null, '250Kg', '11', null, null, '102');
INSERT INTO `measure_scale` VALUES ('717', null, '', null, '100Kg', '8', null, null, '102');
INSERT INTO `measure_scale` VALUES ('718', null, '', null, '200Kg', '10', null, null, '102');
INSERT INTO `measure_scale` VALUES ('719', null, '', null, '3Kg', '1', null, null, '102');
INSERT INTO `measure_scale` VALUES ('720', null, '', null, '15Kg', '4', null, null, '102');
INSERT INTO `measure_scale` VALUES ('721', null, '', null, '10Kg', '3', null, null, '102');
INSERT INTO `measure_scale` VALUES ('722', null, '', null, '50Kg', '6', null, null, '102');
INSERT INTO `measure_scale` VALUES ('723', null, '', null, '75Kg', '7', null, null, '102');
INSERT INTO `measure_scale` VALUES ('724', null, '', null, '300Kg', '12', null, null, '102');
INSERT INTO `measure_scale` VALUES ('725', null, '', null, '150Kg', '9', null, null, '102');
INSERT INTO `measure_scale` VALUES ('726', null, '', null, '500Kg', '13', null, null, '102');
INSERT INTO `measure_scale` VALUES ('727', null, '', null, '1000Kg', '16', null, null, '102');
INSERT INTO `measure_scale` VALUES ('728', null, '', null, '2t', '17', null, null, '102');
INSERT INTO `measure_scale` VALUES ('729', null, '', null, '3t', '18', null, null, '102');
INSERT INTO `measure_scale` VALUES ('730', null, '', null, '800Kg', '15', null, null, '102');
INSERT INTO `measure_scale` VALUES ('731', null, '', null, '600Kg', '14', null, null, '102');
INSERT INTO `measure_scale` VALUES ('732', null, '', null, '1.6MPa', '1', null, null, '103');
INSERT INTO `measure_scale` VALUES ('733', null, '', null, '0', '0', null, null, '103');
INSERT INTO `measure_scale` VALUES ('734', null, '', null, '1.6MPa', '1', null, null, '104');
INSERT INTO `measure_scale` VALUES ('735', null, '', null, '0', '0', null, null, '104');
INSERT INTO `measure_scale` VALUES ('736', null, '', null, '------', '0', null, null, '105');
INSERT INTO `measure_scale` VALUES ('737', null, '', null, '------', '0', null, null, '106');
INSERT INTO `measure_scale` VALUES ('738', null, '', null, '1000μmoL/moL', '1', null, null, '107');
INSERT INTO `measure_scale` VALUES ('739', null, '', null, '0', '0', null, null, '107');
INSERT INTO `measure_scale` VALUES ('740', null, '', null, '150m', '13', null, null, '7');
INSERT INTO `measure_scale` VALUES ('741', null, '', null, '200m', '14', null, null, '7');
INSERT INTO `measure_scale` VALUES ('742', null, '', null, '200m', '14', null, null, '6');
INSERT INTO `measure_scale` VALUES ('743', null, '', null, '150m', '13', null, null, '6');
INSERT INTO `measure_scale` VALUES ('744', null, '', null, '100%LEL', '1', null, null, '108');
INSERT INTO `measure_scale` VALUES ('745', null, '', null, '0', '0', null, null, '108');
INSERT INTO `measure_scale` VALUES ('746', null, '', null, '900nm', '1', null, null, '109');
INSERT INTO `measure_scale` VALUES ('747', null, '', null, '190', '0', null, null, '109');
INSERT INTO `measure_scale` VALUES ('748', null, '', null, '50m³/h', '21', null, null, '32');
INSERT INTO `measure_scale` VALUES ('749', null, '', null, '0', '0', null, null, '32');
INSERT INTO `measure_scale` VALUES ('750', null, '', null, '3x0.01A', '0', null, null, '43');
INSERT INTO `measure_scale` VALUES ('751', null, '', null, '1000A/5A', '12', null, null, '44');
INSERT INTO `measure_scale` VALUES ('752', null, '', null, '2000A/5A', '13', null, null, '44');
INSERT INTO `measure_scale` VALUES ('753', null, '', null, '10MΩ', '1', null, null, '47');
INSERT INTO `measure_scale` VALUES ('754', null, 'ds', null, 'adsfcaf', '0', null, null, '110');
INSERT INTO `measure_scale` VALUES ('755', null, '2', null, '2', '0', null, null, '111');
INSERT INTO `measure_scale` VALUES ('756', null, '', null, '10t', '1', null, null, '19');
INSERT INTO `measure_scale` VALUES ('757', null, '', null, '10t', '13', null, null, '20');
INSERT INTO `measure_scale` VALUES ('758', null, '', null, '20t', '14', null, null, '20');
INSERT INTO `measure_scale` VALUES ('759', null, '', null, '20t', '2', null, null, '19');
INSERT INTO `measure_scale` VALUES ('760', null, '', null, '30Kg', '23', null, null, '16');
INSERT INTO `measure_scale` VALUES ('761', null, '', null, '40t', '17', null, null, '20');
INSERT INTO `measure_scale` VALUES ('762', null, '', null, '300℃', '3', null, null, '12');
INSERT INTO `measure_scale` VALUES ('763', null, '', null, '300℃', '1', null, null, '14');
INSERT INTO `measure_scale` VALUES ('764', null, '', null, '0', '0', null, null, '42');
INSERT INTO `measure_scale` VALUES ('765', null, '', null, '5A/5A', '0', null, null, '44');

-- ----------------------------
-- Table structure for `measure_scale_device_instrument_set`
-- ----------------------------
DROP TABLE IF EXISTS `measure_scale_device_instrument_set`;
CREATE TABLE `measure_scale_device_instrument_set` (
  `measure_scale_id` bigint(20) NOT NULL,
  `device_instrument_set_id` bigint(20) NOT NULL,
  PRIMARY KEY (`measure_scale_id`,`device_instrument_set_id`),
  UNIQUE KEY `UK_tb7oprir0ocdnaa1jo0in6si1` (`device_instrument_set_id`),
  CONSTRAINT `FK8uu4fi7rmbk4occgj3chdrmgh` FOREIGN KEY (`device_instrument_set_id`) REFERENCES `device_instrument` (`id`),
  CONSTRAINT `FKiwlamn70920o5ak7ywcgp1jh8` FOREIGN KEY (`measure_scale_id`) REFERENCES `measure_scale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of measure_scale_device_instrument_set
-- ----------------------------

-- ----------------------------
-- Table structure for `measuringdevice_appliance_archive`
-- ----------------------------
DROP TABLE IF EXISTS `measuringdevice_appliance_archive`;
CREATE TABLE `measuringdevice_appliance_archive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `discipline` varchar(255) DEFAULT NULL,
  `dissipative_name` varchar(255) DEFAULT NULL,
  `legal_name` varchar(255) DEFAULT NULL,
  `legal_phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `registrant_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of measuringdevice_appliance_archive
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `request` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `weight` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn8dgwoff56rth2ts9lfqgq605` (`create_user_id`),
  KEY `FK55pkq5hfsoc4sn07okv6q0u5n` (`p_id`),
  CONSTRAINT `FK55pkq5hfsoc4sn07okv6q0u5n` FOREIGN KEY (`p_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FKn8dgwoff56rth2ts9lfqgq605` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '用户-- 更新用户', null, null, 'PUT', null, null, '/User/update/{id}', null, null, null);
INSERT INTO `menu` VALUES ('2', null, '区域类型 -- 保存区域类型', null, null, 'POST', null, null, '/DistrictType/save', null, null, null);
INSERT INTO `menu` VALUES ('3', null, '用户-- 登录', null, null, 'GET', null, null, '/User/login', null, null, null);
INSERT INTO `menu` VALUES ('4', null, '用户-- 保存用户', null, null, 'POST', null, null, '/User/save', null, null, null);
INSERT INTO `menu` VALUES ('5', null, '非强检器具检定信息 -- 获取所有非强检器具检定信息', null, null, 'GET', null, null, '/OptionalCheckDetail/getAll', null, null, null);
INSERT INTO `menu` VALUES ('6', null, '标准器检定信息-- 保存标准器的检定信息', null, null, 'POST', null, null, '/StandardDeviceCheckDetail/save', null, null, null);
INSERT INTO `menu` VALUES ('7', null, '前台菜单-- 获取所有前台菜单', null, null, 'GET', null, null, '/WebAppMenu/', null, null, null);
INSERT INTO `menu` VALUES ('8', null, '非强检器具 -- 保存非强检器具', null, null, 'GET', null, null, '/OptionalIntergrated/save', null, null, null);
INSERT INTO `menu` VALUES ('9', null, '前台菜单-- 获取一个前台菜单', null, null, 'GET', null, null, '/Apply/get/{id}', null, null, null);
INSERT INTO `menu` VALUES ('10', null, '标准器检定信息-- 获取标准器的检定信息', null, null, 'GET', null, null, '/StandardDeviceCheckDetail/getAllByStandardDevice', null, null, null);
INSERT INTO `menu` VALUES ('11', null, '前台菜单-- 保存前台菜单', null, null, 'POST', null, null, '/Apply/save', null, null, null);
INSERT INTO `menu` VALUES ('12', null, '标准装置-- 获取所有标准装置', null, null, 'GET', null, null, '/StandardFile/getAll', null, null, null);
INSERT INTO `menu` VALUES ('13', null, '用户-- 获取一个用户', null, null, 'GET', null, null, '/User/get/{id}', null, null, null);
INSERT INTO `menu` VALUES ('14', null, '前台菜单-- 获取所有前台菜单', null, null, 'GET', null, null, '/Apply/getAll', null, null, null);
INSERT INTO `menu` VALUES ('15', null, '区域 -- 获取一个区域', null, null, 'GET', null, null, '/District/get/{id}', null, null, null);
INSERT INTO `menu` VALUES ('16', null, '强检器具综合信息 -- 删除', null, null, 'DELETE', null, null, '/MandatoryIntegrated/delete/{id}', null, null, null);
INSERT INTO `menu` VALUES ('17', null, '标准装置-- 保存标准装置', null, null, 'POST', null, null, '/StandardFile/save', null, null, null);
INSERT INTO `menu` VALUES ('18', null, '器具产品 -- 获取所有产品', null, null, 'GET', null, null, '/CertifiedProduct/getAll', null, null, null);
INSERT INTO `menu` VALUES ('19', null, '器具产品 -- 获取所有器具产品', null, null, 'GET', null, null, '/MeasuringdeviceApplianceArchive/getAll', null, null, null);
INSERT INTO `menu` VALUES ('20', null, '人员资质 -- 获取所有人员资质', null, null, 'GET', null, null, '/PersonalFile/getAll', null, null, null);
INSERT INTO `menu` VALUES ('21', null, '强检器具综合信息 -- 获取所有强检器具', null, null, 'GET', null, null, '/MandatoryIntegrated/getAll', null, null, null);
INSERT INTO `menu` VALUES ('22', null, '强检器具综合信息 -- 保存强检器具', null, null, 'POST', null, null, '/MandatoryIntegrated/save', null, null, null);
INSERT INTO `menu` VALUES ('23', null, '区域 -- 获取所有区域', null, null, 'GET', null, null, '/District/getAll', null, null, null);
INSERT INTO `menu` VALUES ('24', null, '强检器具检定信息 -- 获取该器具所有检定信息', null, null, 'GET', null, null, '/MandatoryCheckDetail/getAllByMantoryIntegrated', null, null, null);
INSERT INTO `menu` VALUES ('25', null, '用户-- 删除用户', null, null, 'DELETE', null, null, '/User/delete', null, null, null);
INSERT INTO `menu` VALUES ('26', null, '区域类型 -- 获取所有区域类型', null, null, 'GET', null, null, '/DistrictType/getAll', null, null, null);
INSERT INTO `menu` VALUES ('27', null, '非强检器具检定信息 -- 保存非强检器具', null, null, 'POST', null, null, '/OptionalCheckDetail/save', null, null, null);
INSERT INTO `menu` VALUES ('28', null, '前台菜单-- 删除前台菜单', null, null, 'DELETE', null, null, '/Apply/delete/{id}', null, null, null);
INSERT INTO `menu` VALUES ('29', null, '器具产品 -- 保存', null, null, 'GET', null, null, '/CertifiedProduct/getAll', null, null, null);
INSERT INTO `menu` VALUES ('30', null, '用户-- 登出', null, null, 'GET', null, null, '/User/logout', null, null, null);
INSERT INTO `menu` VALUES ('31', null, '角色 -- 获取一个角色', null, null, 'GET', null, null, '/Role/get/{id}', null, null, null);
INSERT INTO `menu` VALUES ('32', null, '强检器具检定信息 -- 保存', null, null, 'POST', null, null, '/MandatoryCheckDetail/save', null, null, null);
INSERT INTO `menu` VALUES ('33', null, '标准装置-- 获取所有标准装置', null, null, 'GET', null, null, '/StandardAuthorization/getAll', null, null, null);
INSERT INTO `menu` VALUES ('34', null, '角色 -- 保存角色', null, null, 'POST', null, null, '/Role/save', null, null, null);
INSERT INTO `menu` VALUES ('35', null, '器具产品 -- 保存器具产品', null, null, 'POST', null, null, '/MeasuringdeviceApplianceArchive/save', null, null, null);
INSERT INTO `menu` VALUES ('36', null, '用户-- 获取所有用户', null, null, 'GET', null, null, '/User/getAll', null, null, null);
INSERT INTO `menu` VALUES ('37', null, '标准器-- 获取标准装置的标准器', null, null, 'GET', null, null, '/StandardDevice/getAllByStandardFile', null, null, null);
INSERT INTO `menu` VALUES ('38', null, '角色 -- 获取所有角色', null, null, 'GET', null, null, '/Role/getAll', null, null, null);
INSERT INTO `menu` VALUES ('39', null, '标准器-- 保存标准装置的标准器', null, null, 'POST', null, null, '/StandardDevice/save', null, null, null);
INSERT INTO `menu` VALUES ('40', null, '非强检器具检定信息 -- 获取检定信息', null, null, 'GET', null, null, '/OptionalCheckDetail/getAllByOptionalIntergrated', null, null, null);
INSERT INTO `menu` VALUES ('41', null, '标准装置-- 获取所有标准装置', null, null, 'POST', null, null, '/StandardAuthorization/save', null, null, null);
INSERT INTO `menu` VALUES ('42', null, '非强检器具 -- 获取非强检器具', null, null, 'GET', null, null, '/OptionalIntergrated/getAll', null, null, null);
INSERT INTO `menu` VALUES ('43', null, '强检器具综合信息 -- 更新强检器具', null, null, 'PUT', null, null, '/MandatoryIntegrated/update/{id}', null, null, null);
INSERT INTO `menu` VALUES ('44', null, '区域 -- 保存区域', null, null, 'POST', null, null, '/District/save', null, null, null);
INSERT INTO `menu` VALUES ('45', null, '人员资质 -- 保存人员资质', null, null, 'POST', null, null, '/PersonalFile/save', null, null, null);

-- ----------------------------
-- Table structure for `menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK3gapbw9hw4pgxo6x90qb1b69` (`menu_id`),
  CONSTRAINT `FK3gapbw9hw4pgxo6x90qb1b69` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK5ttpucrh8pt6v95jdf5xrn4sk` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of menu_role
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `db_type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `first_read_time` bigint(20) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `first_read_user_id` bigint(20) DEFAULT NULL,
  `from_department_id` bigint(20) DEFAULT NULL,
  `to_department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6qjpqp030qiud2lv9ujwyuknn` (`create_user_id`),
  KEY `FKhmy0oj9ep6xtpqrdtobii0jfv` (`first_read_user_id`),
  KEY `FKomer3adetf55udi1g1o5v3pqo` (`from_department_id`),
  KEY `FKr0b5ssp3dvpocloleew7nsn84` (`to_department_id`),
  CONSTRAINT `FK6qjpqp030qiud2lv9ujwyuknn` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKhmy0oj9ep6xtpqrdtobii0jfv` FOREIGN KEY (`first_read_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKomer3adetf55udi1g1o5v3pqo` FOREIGN KEY (`from_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKr0b5ssp3dvpocloleew7nsn84` FOREIGN KEY (`to_department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `optional_check_detail`
-- ----------------------------
DROP TABLE IF EXISTS `optional_check_detail`;
CREATE TABLE `optional_check_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `certificate_num` varchar(255) DEFAULT NULL,
  `check_result` varchar(255) DEFAULT NULL,
  `check_time` varchar(255) DEFAULT NULL,
  `check_unit` varchar(255) DEFAULT NULL,
  `checker` varchar(255) DEFAULT NULL,
  `examiner` varchar(255) DEFAULT NULL,
  `indication_deviation` varchar(255) DEFAULT NULL,
  `validity_time` varchar(255) DEFAULT NULL,
  `optional_integrated_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrxmhv7njnry8ectoqbru37i92` (`optional_integrated_id`),
  CONSTRAINT `FKrxmhv7njnry8ectoqbru37i92` FOREIGN KEY (`optional_integrated_id`) REFERENCES `optional_integrated` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of optional_check_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `optional_integrated`
-- ----------------------------
DROP TABLE IF EXISTS `optional_integrated`;
CREATE TABLE `optional_integrated` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `county` varchar(255) DEFAULT NULL,
  `factory_num` varchar(255) DEFAULT NULL,
  `fix_site` varchar(255) DEFAULT NULL,
  `license_num` varchar(255) DEFAULT NULL,
  `manufacture_unit` varchar(255) DEFAULT NULL,
  `measure_scale` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `rank` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of optional_integrated
-- ----------------------------

-- ----------------------------
-- Table structure for `personal_file`
-- ----------------------------
DROP TABLE IF EXISTS `personal_file`;
CREATE TABLE `personal_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` varchar(255) DEFAULT NULL,
  `authoriz_item` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `edubg` varchar(255) DEFAULT NULL,
  `generative` varchar(255) DEFAULT NULL,
  `issue_time` varchar(255) DEFAULT NULL,
  `issue_unit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `qualifier_certificate` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `technical` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `validity_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of personal_file
-- ----------------------------

-- ----------------------------
-- Table structure for `post`
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `discription` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lnx7ylm6ckohautp1gua177et` (`name`),
  KEY `FKjf8uc0m816catvv9rm7hikijd` (`create_user_id`),
  CONSTRAINT `FKjf8uc0m816catvv9rm7hikijd` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for `purpose`
-- ----------------------------
DROP TABLE IF EXISTS `purpose`;
CREATE TABLE `purpose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT 'NULL',
  `pinyin` varchar(255) DEFAULT 'NULL',
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbrp0veuhbfnsl833diikjkgdv` (`create_user_id`),
  CONSTRAINT `FKbrp0veuhbfnsl833diikjkgdv` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of purpose
-- ----------------------------
INSERT INTO `purpose` VALUES ('1', null, '贸易结算', 'maoyijiesuan', null, null);
INSERT INTO `purpose` VALUES ('2', null, '医疗卫生', 'yiliaoweisheng', null, null);
INSERT INTO `purpose` VALUES ('3', null, '行政执法', 'xingzhengzhifa', null, null);
INSERT INTO `purpose` VALUES ('4', null, '安全防护', 'anquangfanghu', null, null);
INSERT INTO `purpose` VALUES ('5', null, '环境监测', 'huangjingjiance', null, null);
INSERT INTO `purpose` VALUES ('6', null, '司法鉴定', 'sifajianding', null, null);

-- ----------------------------
-- Table structure for `qualifier`
-- ----------------------------
DROP TABLE IF EXISTS `qualifier`;
CREATE TABLE `qualifier` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `first_practitioner_date` date DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3q76859km0owtswcdk4yqk7i3` (`create_user_id`),
  KEY `FKfhdd1t2bg3jxfxiglk5tp7j0l` (`department_id`),
  CONSTRAINT `FK3q76859km0owtswcdk4yqk7i3` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfhdd1t2bg3jxfxiglk5tp7j0l` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of qualifier
-- ----------------------------
INSERT INTO `qualifier` VALUES ('2', '1985-04-24', null, '本科', '2012-11-19', '助理工程师', '孙亚娟', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('3', '1962-10-08', null, '本科', '1991-01-01', '高级技师', '耿亮', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('4', '1984-10-26', null, '本科', '2012-11-19', '工程师', '李广杰', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('5', '1984-07-13', null, '本科', '2012-11-19', '工程师', '吴景波', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('6', '1981-03-24', null, '高中', '2009-03-01', '技师', '刘志东', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('7', '1987-10-05', null, '本科', '2012-11-19', '助理工程师', '牟相楠', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('8', '1963-12-03', null, '大专', '1981-11-01', '工程师', '李玉华', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('9', '1979-10-18', null, '大专', '2012-11-19', '中级会计', '王文君', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('10', '1979-01-03', null, '本科', '2005-05-01', '工程师', '隋世民', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('11', '1962-03-05', null, '本科', '1982-11-01', '工程师', '耿兴梅', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('12', '1962-02-25', null, '本科', '1983-03-06', '高级工程师', '吕耀东', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('13', '1991-04-29', null, '大专', '2012-10-01', '技师', '景圣洁', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('14', '1973-10-14', null, '大专', '2003-10-01', '技师', '张晓燕', null, null, null, '17');
INSERT INTO `qualifier` VALUES ('15', '1973-05-20', null, '本科', '1996-07-01', '工程师', '杨玉民', null, null, null, '30');
INSERT INTO `qualifier` VALUES ('16', '1975-03-16', null, '本科', '1999-05-04', '助理工程师', '马大勇', null, null, null, '30');
INSERT INTO `qualifier` VALUES ('17', '1963-12-17', null, '大专', '1985-12-05', '高级技师', '王瑞芬', null, null, null, '30');
INSERT INTO `qualifier` VALUES ('18', '1975-07-17', null, '本科', '2006-03-15', '中级', '宗淑芳', null, null, null, '30');

-- ----------------------------
-- Table structure for `qualifier_certificate`
-- ----------------------------
DROP TABLE IF EXISTS `qualifier_certificate`;
CREATE TABLE `qualifier_certificate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alert_date` int(11) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `issue_date` int(11) NOT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `validity_date` int(11) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `qualifier_id` bigint(20) DEFAULT NULL,
  `qualifier_certificate_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK48o3xl16tvq5df8x4a6c7v11m` (`create_user_id`),
  KEY `FK71gmjyg451dquk5gskhosvhe3` (`qualifier_id`),
  KEY `FKcjakybqnrl7tba30t8tvpyvn3` (`qualifier_certificate_type_id`),
  CONSTRAINT `FK48o3xl16tvq5df8x4a6c7v11m` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK71gmjyg451dquk5gskhosvhe3` FOREIGN KEY (`qualifier_id`) REFERENCES `qualifier` (`id`),
  CONSTRAINT `FKcjakybqnrl7tba30t8tvpyvn3` FOREIGN KEY (`qualifier_certificate_type_id`) REFERENCES `qualifier_certificate_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of qualifier_certificate
-- ----------------------------

-- ----------------------------
-- Table structure for `qualifier_certificate_type`
-- ----------------------------
DROP TABLE IF EXISTS `qualifier_certificate_type`;
CREATE TABLE `qualifier_certificate_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `discipline_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk3xpa2uqhuh3vio9n0uptpyh8` (`create_user_id`),
  KEY `FKabqvym1s5lo0ukvct8bwl5ih` (`discipline_id`),
  CONSTRAINT `FKabqvym1s5lo0ukvct8bwl5ih` FOREIGN KEY (`discipline_id`) REFERENCES `discipline` (`id`),
  CONSTRAINT `FKk3xpa2uqhuh3vio9n0uptpyh8` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of qualifier_certificate_type
-- ----------------------------
INSERT INTO `qualifier_certificate_type` VALUES ('1', null, '校准证书', 'jiaozhunzhengshu', null, null, null);
INSERT INTO `qualifier_certificate_type` VALUES ('2', null, '检定证书', 'jiandingzhengshu', null, null, null);
INSERT INTO `qualifier_certificate_type` VALUES ('3', null, '测试证书', 'ceshizhengshu', null, null, null);

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `is_admin` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', null, '', '技术机构', 'jisujigou', null);
INSERT INTO `role` VALUES ('2', null, '', '器具用户', 'qijuyonghu', null);
INSERT INTO `role` VALUES ('3', null, '', '领导', 'lingdao', null);
INSERT INTO `role` VALUES ('4', null, '', '系统管理员', 'xitongguanliyuan', null);
INSERT INTO `role` VALUES ('5', null, '', '管理员', 'guanliyuan', null);
INSERT INTO `role` VALUES ('6', null, '', '管理部门', 'guanlibumen', null);
INSERT INTO `role` VALUES ('7', null, '', '市管理部门', 'shiguanlibumen', null);

-- ----------------------------
-- Table structure for `specification`
-- ----------------------------
DROP TABLE IF EXISTS `specification`;
CREATE TABLE `specification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT 'NULL',
  `update_time` bigint(20) DEFAULT NULL,
  `value` varchar(255) NOT NULL,
  `weight` int(11) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `instrument_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4ri7x6v9wyxjwmgws0j5r72rc` (`create_user_id`),
  KEY `FK742p33i4cv89ec5vchfv1b014` (`instrument_type_id`),
  CONSTRAINT `FK4ri7x6v9wyxjwmgws0j5r72rc` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK742p33i4cv89ec5vchfv1b014` FOREIGN KEY (`instrument_type_id`) REFERENCES `instrument_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of specification
-- ----------------------------
INSERT INTO `specification` VALUES ('1', null, null, '', null, '', '0', null, '58');
INSERT INTO `specification` VALUES ('2', null, null, '', null, '', '0', null, '33');
INSERT INTO `specification` VALUES ('3', null, null, 'dasfc', null, 'dsafc', '0', null, '110');
INSERT INTO `specification` VALUES ('4', null, null, '3', null, '3', '0', null, '111');

-- ----------------------------
-- Table structure for `standard_authorization`
-- ----------------------------
DROP TABLE IF EXISTS `standard_authorization`;
CREATE TABLE `standard_authorization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `measure_scale` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `rank` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `technical` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of standard_authorization
-- ----------------------------

-- ----------------------------
-- Table structure for `standard_device`
-- ----------------------------
DROP TABLE IF EXISTS `standard_device`;
CREATE TABLE `standard_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `factory_num` varchar(255) DEFAULT NULL,
  `is_main` bit(1) DEFAULT NULL,
  `license_num` varchar(255) DEFAULT NULL,
  `manufacturer_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `accuracy_id` bigint(20) DEFAULT NULL,
  `current_user_id` bigint(20) DEFAULT NULL,
  `device_set_id` bigint(20) DEFAULT NULL,
  `measure_scale_id` bigint(20) DEFAULT NULL,
  `specification_id` bigint(20) DEFAULT NULL,
  `standard_device_instrument_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjfx8omjuxxjgun15rqx33r6bj` (`accuracy_id`),
  KEY `FKcmkn47ew3kvqoywvjoym20xe3` (`current_user_id`),
  KEY `FKks7fanp0gd4hfpw0pqpj9yoyx` (`device_set_id`),
  KEY `FKspw3limbr05ah9n4o41puacm4` (`measure_scale_id`),
  KEY `FKs5h5lbi2qfpan3qb5h6rg146e` (`specification_id`),
  KEY `FKiwmr8h6kvq691kmg32amdok4c` (`standard_device_instrument_type_id`),
  CONSTRAINT `FKcmkn47ew3kvqoywvjoym20xe3` FOREIGN KEY (`current_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKiwmr8h6kvq691kmg32amdok4c` FOREIGN KEY (`standard_device_instrument_type_id`) REFERENCES `instrument_type` (`id`),
  CONSTRAINT `FKjfx8omjuxxjgun15rqx33r6bj` FOREIGN KEY (`accuracy_id`) REFERENCES `accuracy` (`id`),
  CONSTRAINT `FKks7fanp0gd4hfpw0pqpj9yoyx` FOREIGN KEY (`device_set_id`) REFERENCES `device_set` (`id`),
  CONSTRAINT `FKs5h5lbi2qfpan3qb5h6rg146e` FOREIGN KEY (`specification_id`) REFERENCES `specification` (`id`),
  CONSTRAINT `FKspw3limbr05ah9n4o41puacm4` FOREIGN KEY (`measure_scale_id`) REFERENCES `measure_scale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of standard_device
-- ----------------------------

-- ----------------------------
-- Table structure for `standard_device_check_detail`
-- ----------------------------
DROP TABLE IF EXISTS `standard_device_check_detail`;
CREATE TABLE `standard_device_check_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alert_date` date DEFAULT NULL,
  `calibration_department` varchar(255) DEFAULT NULL,
  `certificate_num` varchar(255) DEFAULT NULL,
  `check_date` date DEFAULT NULL,
  `correct_value` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `inspector_qualifier_certificate` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `validity_date` date DEFAULT NULL,
  `verifier_qualifier_certificate` varchar(255) DEFAULT NULL,
  `check_result_id` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `standard_device_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm2ly5l5con01ummg98i2s3wso` (`check_result_id`),
  KEY `FKbm543rjsx2ou7quro2j257cg9` (`create_user_id`),
  KEY `FKrn4fk2kuu0yk4a8xxe5d2o61e` (`standard_device_id`),
  CONSTRAINT `FKbm543rjsx2ou7quro2j257cg9` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm2ly5l5con01ummg98i2s3wso` FOREIGN KEY (`check_result_id`) REFERENCES `check_result` (`id`),
  CONSTRAINT `FKrn4fk2kuu0yk4a8xxe5d2o61e` FOREIGN KEY (`standard_device_id`) REFERENCES `standard_device` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of standard_device_check_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `standard_file`
-- ----------------------------
DROP TABLE IF EXISTS `standard_file`;
CREATE TABLE `standard_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `certificate_num` varchar(255) DEFAULT NULL,
  `check_date` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `issue_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `validity_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of standard_file
-- ----------------------------

-- ----------------------------
-- Table structure for `system_config`
-- ----------------------------
DROP TABLE IF EXISTS `system_config`;
CREATE TABLE `system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `k` char(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nomt58er67iwvahi1tv1nbskn` (`k`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of system_config
-- ----------------------------
INSERT INTO `system_config` VALUES ('1', '系统配置缓存过期时间(秒)，后台重启动生效', 'expire_time_key', '1800');
INSERT INTO `system_config` VALUES ('2', '允许技术机构退回指定给它的器具的最长时间', 'maxAllowBackDay', '10');

-- ----------------------------
-- Table structure for `text_field`
-- ----------------------------
DROP TABLE IF EXISTS `text_field`;
CREATE TABLE `text_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of text_field
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKn1eo3hcou22xdhm9vmll8oivg` (`create_user_id`),
  KEY `FKgkh2fko1e4ydv1y6vtrwdc6my` (`department_id`),
  KEY `FKgs0sk67mcc6r48030sm9i7yk2` (`update_user_id`),
  CONSTRAINT `FKgkh2fko1e4ydv1y6vtrwdc6my` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKgs0sk67mcc6r48030sm9i7yk2` FOREIGN KEY (`update_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKn1eo3hcou22xdhm9vmll8oivg` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=383 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2017-12-15 08:46:18', '13752603780', '系统管理员', 'admin', 'xitongguanliyuan', '0', '2017-12-15 08:46:18', 'admin', null, '2', null);
INSERT INTO `user` VALUES ('2', '2017-12-15 08:46:18', '13752603781', '测试器具用户', 'user1', null, '0', '2017-12-15 08:46:18', 'user1', null, '1', null);
INSERT INTO `user` VALUES ('4', '2017-12-15 08:46:18', '13752603783', '测试区县技术机构用户', 'user3', null, '0', '2017-12-15 08:46:18', 'user3', null, '5', null);
INSERT INTO `user` VALUES ('7', '2017-12-15 08:46:19', '13752603786', '测试市市属器具用户', 'user6', null, '0', '2017-12-15 08:46:19', 'user6', null, '3', null);
INSERT INTO `user` VALUES ('8', '2017-12-15 09:06:14', '13947670920', '1123', '123456', null, '0', '2017-12-15 09:06:14', '327336842', null, '9', null);
INSERT INTO `user` VALUES ('9', '2017-12-15 09:21:54', '13456789876', '123', '123456', null, '0', '2017-12-15 09:21:54', 'cfjl@qq.com', null, '10', null);
INSERT INTO `user` VALUES ('10', '2017-12-15 09:23:23', '13567898765', '213', '123456', null, '0', '2017-12-15 09:23:23', 'cfzj@qq.com', null, '11', null);
INSERT INTO `user` VALUES ('11', '2017-12-15 09:25:53', '13567898765', '123', '123456', null, '0', '2018-01-17 15:03:36', 'mfc@qq.com', null, '12', null);
INSERT INTO `user` VALUES ('13', '2017-12-15 11:16:47', '13847642627', '123', '123456', null, '0', '2017-12-15 11:16:47', '279715185@qq.com', null, '15', null);
INSERT INTO `user` VALUES ('14', '2017-12-15 11:19:44', '18904767366', '123', '123456', null, '0', '2017-12-15 11:19:44', '809097716@qq.com', null, '16', null);
INSERT INTO `user` VALUES ('15', '2017-12-15 11:26:07', '13327160829', '吕耀东', 'klqqzjs1', null, '0', '2018-01-11 15:58:11', 'klqbgs@163.com', null, '17', null);
INSERT INTO `user` VALUES ('16', '2017-12-15 11:33:03', '15149075666', '123', '123456', null, '0', '2017-12-15 11:33:03', 'lxzjs169@163.com', null, '18', null);
INSERT INTO `user` VALUES ('17', '2017-12-15 11:36:23', '13948863585', '123', '123456', null, '0', '2017-12-15 11:36:23', '2374229891@qqcom', null, '19', null);
INSERT INTO `user` VALUES ('18', '2017-12-15 11:39:20', '18947677553', '123', '123456', null, '0', '2017-12-15 11:39:20', 'cfzqlpq@163.com', null, '20', null);
INSERT INTO `user` VALUES ('19', '2017-12-15 11:48:00', '18747183410', '123', '123456', null, '0', '2017-12-15 11:48:00', 'hysion@126.com', null, '21', null);
INSERT INTO `user` VALUES ('20', '2017-12-15 11:51:46', '13847688890', '123', '123456', null, '0', '2017-12-15 11:51:46', 'whb6189@163.com', null, '22', null);
INSERT INTO `user` VALUES ('21', '2017-12-15 11:54:04', '13190913232', '123', '123456', null, '0', '2017-12-15 11:54:04', '157939508@qq.com', null, '23', null);
INSERT INTO `user` VALUES ('22', '2017-12-15 11:56:30', '15049951566', '123', '123456', null, '0', '2017-12-15 11:56:30', 'linxiwyl@163.com', null, '24', null);
INSERT INTO `user` VALUES ('23', '2017-12-15 11:58:29', '15847625836', '123', '123456', null, '0', '2017-12-15 11:58:29', 'ncxjcd@163.com', null, '25', null);
INSERT INTO `user` VALUES ('24', '2017-12-15 13:49:37', '15847606708', 'jlg', 'jlg123456', null, '0', '2017-12-19 14:19:44', 'ssqscjgj@sina.com', null, '26', null);
INSERT INTO `user` VALUES ('25', '2017-12-15 14:01:10', '13847617555', '123', '123456', null, '0', '2017-12-15 14:01:10', '316218109@qq.com', null, '27', null);
INSERT INTO `user` VALUES ('26', '2017-12-15 14:04:03', '13848872258', '123', '123456', null, '0', '2017-12-15 14:04:03', 'kqzjjiliang@126.com', null, '28', null);
INSERT INTO `user` VALUES ('28', '2017-12-15 14:18:41', '13847629620', '杨玉民', '123456', null, '0', '2018-01-12 09:39:53', '786355452@qq.com', null, '30', null);
INSERT INTO `user` VALUES ('29', '2017-12-18 16:09:29', '12345678901', 'ddd', '123456', null, '0', '2018-01-10 11:17:28', '1234', null, '35', null);
INSERT INTO `user` VALUES ('30', '2017-12-18 16:11:32', '15847606708', '', '111111', null, '0', '2017-12-18 16:23:01', '12345', null, '37', null);
INSERT INTO `user` VALUES ('31', '2017-12-18 16:11:59', '13804761962', '阿鲁科尔沁旗市场监督管理局', 'cfaqjlg', null, '0', '2017-12-18 16:15:46', 'cfaqjiliang@163.com', null, '38', null);
INSERT INTO `user` VALUES ('32', '2017-12-18 16:14:13', '15847683354', '', '123456', null, '0', '2017-12-18 16:14:13', 'lzj@qq.com', null, '40', null);
INSERT INTO `user` VALUES ('33', '2017-12-18 16:17:17', '18947656351', '', '123456', null, '0', '2017-12-18 16:17:17', '7318277922@qq.com', null, '41', null);
INSERT INTO `user` VALUES ('34', '2017-12-18 16:20:13', '18911101110', '', '123456', null, '0', '2017-12-18 16:20:13', 'hdd123456', null, '42', null);
INSERT INTO `user` VALUES ('36', '2017-12-18 16:28:59', '13847623355', '', '123456', null, '0', '2017-12-18 16:28:59', '计量检定', null, '46', null);
INSERT INTO `user` VALUES ('37', '2017-12-18 21:08:30', '15898748852', '', '123456', null, '0', '2017-12-18 21:08:30', 'test123', null, '48', null);
INSERT INTO `user` VALUES ('39', '2017-12-19 08:50:37', '18647615296', '', '123456', null, '0', '2017-12-19 08:50:37', 'houruiying', null, '51', null);
INSERT INTO `user` VALUES ('40', '2017-12-19 09:17:39', '18647615296', '', '123456', null, '0', '2017-12-19 09:17:39', 'hry1', null, '52', null);
INSERT INTO `user` VALUES ('41', '2017-12-19 09:17:45', '13488512477', '', '123456', null, '0', '2017-12-19 09:17:45', '飞滚吧药铺', null, '53', null);
INSERT INTO `user` VALUES ('42', '2017-12-19 09:32:07', '13547658555', '', '123456', null, '0', '2017-12-19 09:32:07', 'lishangtian', null, '54', null);
INSERT INTO `user` VALUES ('44', '2017-12-19 11:35:56', '18678360021', '', '111111', null, '0', '2018-01-15 16:45:52', 'SSQSGJYZ', null, '57', null);
INSERT INTO `user` VALUES ('48', '2017-12-19 14:44:31', '13013136669', '123456', '111111', null, '0', '2017-12-28 11:42:12', 'ssq0002', null, '61', null);
INSERT INTO `user` VALUES ('49', '2017-12-19 14:49:12', '13196969696', '', '111111', null, '0', '2017-12-28 11:42:21', 'ssq0003', null, '62', null);
INSERT INTO `user` VALUES ('50', '2017-12-19 14:50:18', '13196969696', '', '111111', null, '0', '2017-12-28 11:42:28', 'ssq0004', null, '63', null);
INSERT INTO `user` VALUES ('51', '2017-12-19 14:51:12', '13196969696', '', '111111', null, '0', '2017-12-28 11:42:38', 'ssq0005', null, '64', null);
INSERT INTO `user` VALUES ('52', '2017-12-19 14:52:04', '13196969696', '', '111111', null, '0', '2017-12-28 11:42:59', 'ssq0006', null, '65', null);
INSERT INTO `user` VALUES ('53', '2017-12-19 14:53:08', '13196969696', '', '111111', null, '0', '2017-12-28 11:43:10', 'ssq0007', null, '66', null);
INSERT INTO `user` VALUES ('54', '2017-12-19 14:54:11', '13196969696', '', '111111', null, '0', '2017-12-28 11:43:26', 'ssq0008', null, '67', null);
INSERT INTO `user` VALUES ('55', '2017-12-19 14:54:55', '13196969696', '', '111111', null, '0', '2017-12-28 11:43:35', 'ssq009', null, '68', null);
INSERT INTO `user` VALUES ('56', '2017-12-21 10:58:23', '15047686632', '', '123456', null, '-1', '2018-01-23 16:42:38', 'lxxltky', null, '70', null);
INSERT INTO `user` VALUES ('57', '2017-12-21 14:49:09', '13948167590', '', '123456', null, '0', '2017-12-21 14:49:09', 'lxxtysp', null, '72', null);
INSERT INTO `user` VALUES ('59', '2017-12-21 16:33:19', '13333333333', '', '13333333333', null, '0', '2017-12-21 16:33:19', '13333333333', null, '76', null);
INSERT INTO `user` VALUES ('60', '2017-12-21 16:44:56', '13664767899', '', '123456', null, '0', '2017-12-21 16:44:56', 'wntqscjgj', null, '78', null);
INSERT INTO `user` VALUES ('63', '2017-12-22 09:29:55', '18047662000', '', '123456', null, '0', '2017-12-22 09:29:55', 'nmghdny', null, '81', null);
INSERT INTO `user` VALUES ('64', '2017-12-22 10:13:03', '18504766638', '', '123456', null, '0', '2017-12-22 10:13:03', 'aqjmyxgs', null, '83', null);
INSERT INTO `user` VALUES ('65', '2017-12-22 10:36:29', '18248038121', '潘文强', '123456', null, '0', '2017-12-22 16:58:22', 'cfgydl', null, '84', null);
INSERT INTO `user` VALUES ('66', '2017-12-22 10:59:52', '13000000000', '', '123456', null, '0', '2017-12-22 10:59:52', 'nmglsmls', null, '98', null);
INSERT INTO `user` VALUES ('67', '2017-12-22 15:13:47', '18703242986', '', '123456', null, '0', '2017-12-22 15:13:47', 'lzfmfr', null, '101', null);
INSERT INTO `user` VALUES ('68', '2017-12-22 15:22:08', '13134760268', '', '123456', null, '0', '2017-12-22 15:22:08', 'cflyrl', null, '102', null);
INSERT INTO `user` VALUES ('69', '2017-12-22 15:27:19', '13150917517', '', '123456', null, '0', '2017-12-22 15:27:19', 'cfslhjy', null, '103', null);
INSERT INTO `user` VALUES ('70', '2017-12-22 15:45:20', '15124923528', '', '123456', null, '0', '2017-12-22 15:45:20', 'wstrq', null, '104', null);
INSERT INTO `user` VALUES ('71', '2017-12-22 15:57:38', '13190929436', '马景国', '123456', null, '0', '2017-12-25 10:41:18', 'wntqmyzyyy', null, '105', null);
INSERT INTO `user` VALUES ('72', '2017-12-22 16:02:40', '15647661717', '', '123456', null, '0', '2017-12-22 16:02:40', 'wntqflrq', null, '106', null);
INSERT INTO `user` VALUES ('73', '2017-12-22 16:11:10', '13190924837', '', '123456', null, '0', '2017-12-22 16:11:10', 'tldcxj', null, '107', null);
INSERT INTO `user` VALUES ('74', '2017-12-22 16:17:01', '13947634555', '刘海兴', '150426', null, '0', '2017-12-26 09:25:36', 'wntqhbj', null, '108', null);
INSERT INTO `user` VALUES ('75', '2017-12-22 16:23:48', '13947677598', '', '123456', null, '0', '2017-12-22 16:23:48', 'wntqjjd', null, '109', null);
INSERT INTO `user` VALUES ('76', '2017-12-22 16:38:13', '18643017792', '', '123456', null, '0', '2017-12-22 16:38:13', 'zljjk', null, '110', null);
INSERT INTO `user` VALUES ('77', '2017-12-22 16:43:14', '13404887171', '', '123456', null, '0', '2017-12-22 16:43:14', 'nmgdbdl', null, '111', null);
INSERT INTO `user` VALUES ('78', '2017-12-22 16:56:34', '13624868982', '', '123456', null, '0', '2017-12-22 16:56:34', 'hlzszy', null, '112', null);
INSERT INTO `user` VALUES ('79', '2017-12-22 17:02:56', '13848667662', '', '123456', null, '0', '2017-12-22 17:02:56', 'gjlscbk', null, '113', null);
INSERT INTO `user` VALUES ('80', '2017-12-25 10:35:17', '13947684158', '', '123456', null, '0', '2017-12-25 10:35:17', 'xyqtyxgs', null, '120', null);
INSERT INTO `user` VALUES ('81', '2017-12-25 11:07:57', '13789733718', '赵国民', '192837', null, '0', '2017-12-26 18:49:59', 'cfhyjy', null, '121', null);
INSERT INTO `user` VALUES ('82', '2017-12-25 11:20:30', '15774875586', '', '123456', null, '0', '2017-12-25 11:20:30', 'hyjyz', null, '122', null);
INSERT INTO `user` VALUES ('83', '2017-12-25 15:35:44', '13947647700', '', '123456', null, '0', '2017-12-25 15:35:44', 'lxxtyky', null, '126', null);
INSERT INTO `user` VALUES ('84', '2017-12-25 15:46:39', '13947694159', '', '123456', null, '0', '2017-12-25 15:46:39', 'nmgmdyy', null, '127', null);
INSERT INTO `user` VALUES ('85', '2017-12-25 15:52:18', '15047693044', '', '123456', null, '0', '2017-12-25 15:52:18', 'lsdzkj', null, '128', null);
INSERT INTO `user` VALUES ('86', '2017-12-25 15:58:52', '18747856663', '', '123456', null, '0', '2017-12-25 15:58:52', 'wntqcgkh', null, '129', null);
INSERT INTO `user` VALUES ('87', '2017-12-25 16:04:39', '15560449393', '高云雷', '123456', null, '0', '2017-12-26 11:13:09', 'mhtrq', null, '130', null);
INSERT INTO `user` VALUES ('88', '2017-12-25 16:09:12', '10000000000', '韩景祎', '123456', null, '0', '2017-12-27 09:09:07', 'wntqrnyy', null, '131', null);
INSERT INTO `user` VALUES ('89', '2017-12-25 16:13:51', '15124998860', '王月清', '123456', null, '0', '2017-12-26 10:46:46', 'wntqfybjs', null, '132', null);
INSERT INTO `user` VALUES ('90', '2017-12-25 16:23:24', '15047698747', '', '123456', null, '0', '2017-12-25 16:23:24', 'wntqjkzx', null, '133', null);
INSERT INTO `user` VALUES ('91', '2017-12-26 08:29:59', '15048650898', '', 'tily0412', null, '0', '2017-12-26 08:29:59', 'lxxyy', null, '134', null);
INSERT INTO `user` VALUES ('92', '2017-12-26 08:58:57', '13154762287', '', '123456', null, '0', '2017-12-26 08:58:57', 'wfdwsy', null, '135', null);
INSERT INTO `user` VALUES ('93', '2017-12-26 09:03:11', '13959556688', '', '123456', null, '0', '2017-12-26 09:03:11', 'byabjyz', null, '136', null);
INSERT INTO `user` VALUES ('94', '2017-12-26 09:07:07', '13847623892', '', '123456', null, '0', '2017-12-26 09:07:07', 'fydjyz', null, '137', null);
INSERT INTO `user` VALUES ('95', '2017-12-26 09:24:33', '13847623892', '', '123456', null, '0', '2017-12-26 09:24:33', 'hypzjyz', null, '138', null);
INSERT INTO `user` VALUES ('121', '2017-12-26 09:54:15', '18104766005', '穆艳利', '663330', null, '0', '2017-12-26 11:25:27', 'wqzjs', null, '79', null);
INSERT INTO `user` VALUES ('122', '2017-12-26 10:01:29', '18947677553', '', '123456', null, '0', '2017-12-26 10:01:29', 'zqzjs', null, '41', null);
INSERT INTO `user` VALUES ('123', '2017-12-26 10:05:32', '13342567654', '', '123456', null, '0', '2017-12-26 10:05:32', '512453091@qq.com', null, '164', null);
INSERT INTO `user` VALUES ('124', '2017-12-26 10:24:31', '13947670920', '', '123456', null, '0', '2017-12-26 10:24:31', 'ybsscj', null, '9', null);
INSERT INTO `user` VALUES ('126', '2017-12-26 11:22:40', '13847623892', '', '123456', null, '0', '2017-12-26 11:22:40', 'jxjyz', null, '177', null);
INSERT INTO `user` VALUES ('127', '2017-12-26 14:42:20', '15148109909', '', '123456', null, '0', '2017-12-26 14:42:20', 'zshylb', null, '179', null);
INSERT INTO `user` VALUES ('128', '2017-12-26 14:58:42', '15047624686', '', '15047624686', null, '0', '2017-12-26 14:58:42', 'lxxmhtrq', null, '183', null);
INSERT INTO `user` VALUES ('129', '2017-12-26 15:19:06', '15148109909', '', '123456', null, '0', '2017-12-26 15:19:06', 'zshwdthz', null, '184', null);
INSERT INTO `user` VALUES ('130', '2017-12-26 15:22:57', '15148109909', '', '123456', null, '0', '2017-12-26 15:22:57', 'zshjdz', null, '185', null);
INSERT INTO `user` VALUES ('131', '2017-12-26 15:34:34', '13722141586', '', '123456', null, '0', '2017-12-26 15:34:34', 'ydjyz', null, '186', null);
INSERT INTO `user` VALUES ('132', '2017-12-26 15:52:26', '13669696969', '', '111111', null, '0', '2017-12-26 15:52:26', 'ssq0112', null, '188', null);
INSERT INTO `user` VALUES ('133', '2017-12-26 15:55:46', '13669696969', '', '111111', null, '0', '2017-12-26 15:55:46', 'ssq0113', null, '190', null);
INSERT INTO `user` VALUES ('134', '2017-12-26 15:55:54', '13804760003', '', '111111', null, '0', '2017-12-26 15:55:54', 'hsq00001', null, '191', null);
INSERT INTO `user` VALUES ('135', '2017-12-26 16:02:54', '13847651236', '', '123456', null, '0', '2017-12-26 16:02:54', 'xhsqwsz', null, '194', null);
INSERT INTO `user` VALUES ('136', '2017-12-26 16:05:01', '13669696969', '', '111111', null, '0', '2017-12-26 16:05:01', 'ssq0011', null, '195', null);
INSERT INTO `user` VALUES ('137', '2017-12-26 16:20:22', '13669696969', '', '111111', null, '0', '2017-12-26 16:20:22', 'ssq0114', null, '199', null);
INSERT INTO `user` VALUES ('138', '2017-12-26 16:21:01', '13669696969', '', '111111', null, '0', '2017-12-26 16:21:01', 'ssq0115', null, '200', null);
INSERT INTO `user` VALUES ('139', '2017-12-26 16:21:36', '13669696969', '', '111111', null, '0', '2017-12-26 16:21:36', 'ssq0116', null, '201', null);
INSERT INTO `user` VALUES ('140', '2017-12-26 16:22:27', '13669696969', '', '111111', null, '0', '2017-12-26 16:22:27', 'ssq0117', null, '202', null);
INSERT INTO `user` VALUES ('141', '2017-12-26 16:24:12', '13669696969', '', '111111', null, '0', '2017-12-26 16:24:12', 'ssq0118', null, '204', null);
INSERT INTO `user` VALUES ('142', '2017-12-26 16:24:40', '13669696969', '', '111111', null, '0', '2017-12-26 16:24:40', 'ssq0119', null, '205', null);
INSERT INTO `user` VALUES ('143', '2017-12-26 16:25:10', '13669696969', '', '111111', null, '0', '2017-12-26 16:25:10', 'ssq0120', null, '206', null);
INSERT INTO `user` VALUES ('144', '2017-12-26 16:25:41', '13669696969', '', '111111', null, '0', '2017-12-26 16:25:41', 'ssq0121', null, '207', null);
INSERT INTO `user` VALUES ('145', '2017-12-26 16:26:25', '13669696969', 'liangzhan', '111111', null, '0', '2017-12-26 16:27:11', 'ssq0122', null, '208', null);
INSERT INTO `user` VALUES ('146', '2017-12-26 16:27:48', '15847495653', '', '123456', null, '0', '2017-12-26 16:27:48', 'cfwjzc', null, '209', null);
INSERT INTO `user` VALUES ('147', '2017-12-26 16:32:14', '13669696969', '', '111111', null, '0', '2017-12-26 16:32:14', 'ssq0123', null, '210', null);
INSERT INTO `user` VALUES ('148', '2017-12-26 16:32:49', '13669696969', '', '111111', null, '0', '2017-12-26 16:32:49', 'ssq0124', null, '211', null);
INSERT INTO `user` VALUES ('149', '2017-12-26 16:33:21', '13669696969', '', '111111', null, '0', '2017-12-26 16:33:21', 'ssq0125', null, '212', null);
INSERT INTO `user` VALUES ('150', '2017-12-26 16:33:51', '13669696969', '', '111111', null, '0', '2017-12-26 16:33:51', 'ssq0126', null, '213', null);
INSERT INTO `user` VALUES ('151', '2017-12-26 16:34:12', '15849635160', '', '123456', null, '0', '2017-12-26 16:34:12', 'zjczs', null, '214', null);
INSERT INTO `user` VALUES ('152', '2017-12-26 16:34:21', '13669696969', '', '111111', null, '0', '2017-12-26 16:34:21', 'ssq0127', null, '215', null);
INSERT INTO `user` VALUES ('153', '2017-12-26 16:34:52', '13669696969', '', '111111', null, '0', '2017-12-26 16:34:52', 'ssq0128', null, '216', null);
INSERT INTO `user` VALUES ('154', '2017-12-26 16:35:24', '13669696969', '', '111111', null, '0', '2017-12-26 16:35:24', 'ssq0129', null, '217', null);
INSERT INTO `user` VALUES ('155', '2017-12-26 16:35:57', '13669696969', '', '111111', null, '0', '2017-12-26 16:35:57', 'ssq0131', null, '218', null);
INSERT INTO `user` VALUES ('156', '2017-12-26 16:36:32', '13669696969', '', '111111', null, '0', '2017-12-26 16:36:32', 'ssq0132', null, '219', null);
INSERT INTO `user` VALUES ('157', '2017-12-26 16:37:15', '13669696969', '', '111111', null, '0', '2017-12-26 16:37:15', 'ssq0133', null, '220', null);
INSERT INTO `user` VALUES ('158', '2017-12-26 16:37:43', '13669696969', '', '111111', null, '0', '2017-12-26 16:37:43', 'ssq0134', null, '221', null);
INSERT INTO `user` VALUES ('159', '2017-12-26 16:38:14', '13669696969', '', '111111', null, '0', '2017-12-26 16:38:14', 'ssq0135', null, '222', null);
INSERT INTO `user` VALUES ('160', '2017-12-26 16:38:54', '13669696969', '', '111111', null, '0', '2017-12-26 16:38:54', 'ssq0136', null, '224', null);
INSERT INTO `user` VALUES ('161', '2017-12-26 16:39:27', '13669696969', '', '111111', null, '0', '2017-12-26 16:39:27', 'ssq0137', null, '225', null);
INSERT INTO `user` VALUES ('162', '2017-12-26 16:40:00', '13669696969', '', '111111', null, '0', '2017-12-26 16:40:00', 'ssq0138', null, '226', null);
INSERT INTO `user` VALUES ('163', '2017-12-26 16:40:31', '13669696969', '', '111111', null, '0', '2017-12-26 16:40:31', 'ssq0139', null, '227', null);
INSERT INTO `user` VALUES ('164', '2017-12-26 16:41:00', '13669696969', '', '111111', null, '0', '2017-12-26 16:41:00', 'ssq0140', null, '228', null);
INSERT INTO `user` VALUES ('165', '2017-12-26 16:41:29', '13669696969', '', '111111', null, '0', '2017-12-26 16:41:29', 'ssq0141', null, '229', null);
INSERT INTO `user` VALUES ('166', '2017-12-26 16:42:14', '13669696969', '', '111111', null, '0', '2017-12-26 16:42:14', 'ssq0142', null, '230', null);
INSERT INTO `user` VALUES ('167', '2017-12-26 16:42:43', '13669696969', '', '111111', null, '0', '2017-12-26 16:42:43', 'ssq0143', null, '231', null);
INSERT INTO `user` VALUES ('168', '2017-12-26 16:43:17', '13669696969', '', '111111', null, '0', '2017-12-26 16:43:17', 'ssq0144', null, '232', null);
INSERT INTO `user` VALUES ('169', '2017-12-26 16:43:45', '13669696969', '', '111111', null, '0', '2017-12-26 16:43:45', 'ssq0145', null, '233', null);
INSERT INTO `user` VALUES ('170', '2017-12-26 16:44:12', '13669696969', '', '111111', null, '0', '2017-12-26 16:44:12', 'ssq0146', null, '234', null);
INSERT INTO `user` VALUES ('171', '2017-12-26 16:44:53', '13669696969', '', '111111', null, '0', '2017-12-26 16:44:53', 'ssq0147', null, '235', null);
INSERT INTO `user` VALUES ('172', '2017-12-26 16:45:26', '13669696969', '', '111111', null, '0', '2017-12-26 16:45:26', 'ssq0148', null, '236', null);
INSERT INTO `user` VALUES ('173', '2017-12-26 16:45:55', '13669696969', '', '111111', null, '0', '2017-12-26 16:45:55', 'ssq0149', null, '237', null);
INSERT INTO `user` VALUES ('174', '2017-12-26 16:46:14', '15049958777', '', '123456', null, '0', '2017-12-26 16:46:14', 'tfdwsy', null, '238', null);
INSERT INTO `user` VALUES ('175', '2017-12-26 16:46:23', '13669696969', '', '111111', null, '0', '2017-12-26 16:46:23', 'ssq0150', null, '239', null);
INSERT INTO `user` VALUES ('176', '2017-12-26 16:48:47', '13669696969', '', '111111', null, '0', '2017-12-26 16:48:47', 'ssq0130', null, '240', null);
INSERT INTO `user` VALUES ('177', '2017-12-26 16:52:15', '13669696969', '', '111111', null, '0', '2017-12-26 16:52:15', 'ssq0111', null, '241', null);
INSERT INTO `user` VALUES ('178', '2017-12-27 08:39:18', '13948636869', '', '123456', null, '0', '2017-12-27 08:39:18', 'zgsywqjyb', null, '245', null);
INSERT INTO `user` VALUES ('180', '2017-12-27 09:55:42', '13947671569', '', '123456', null, '0', '2017-12-27 09:55:42', 'lxxzygf', null, '248', null);
INSERT INTO `user` VALUES ('181', '2017-12-27 10:48:21', '13669696969', '', '111111', null, '0', '2017-12-27 10:48:21', 'ssq0151', null, '256', null);
INSERT INTO `user` VALUES ('182', '2017-12-27 10:48:52', '13669696969', '', '111111', null, '0', '2017-12-27 10:48:52', 'ssq0152', null, '257', null);
INSERT INTO `user` VALUES ('183', '2017-12-27 10:49:21', '13669696969', '', '111111', null, '0', '2017-12-27 10:49:21', 'ssq0153', null, '258', null);
INSERT INTO `user` VALUES ('184', '2017-12-27 10:49:48', '13669696969', '', '111111', null, '0', '2017-12-27 10:49:48', 'ssq0154', null, '259', null);
INSERT INTO `user` VALUES ('185', '2017-12-27 10:50:20', '13669696969', '', '111111', null, '0', '2017-12-27 10:50:20', 'ssq0155', null, '260', null);
INSERT INTO `user` VALUES ('186', '2017-12-27 10:50:54', '13669696969', '', '111111', null, '0', '2017-12-27 10:50:54', 'ssq0156', null, '261', null);
INSERT INTO `user` VALUES ('187', '2017-12-27 10:51:28', '13669696969', '', '111111', null, '0', '2017-12-27 10:51:28', 'ssq0157', null, '262', null);
INSERT INTO `user` VALUES ('188', '2017-12-27 10:51:57', '13669696969', '', '111111', null, '0', '2017-12-27 10:51:57', 'ssq0158', null, '263', null);
INSERT INTO `user` VALUES ('189', '2017-12-27 10:52:23', '13669696969', '', '111111', null, '0', '2017-12-27 10:52:23', 'ssq0159', null, '264', null);
INSERT INTO `user` VALUES ('190', '2017-12-27 10:53:02', '13669696969', '', '111111', null, '0', '2017-12-27 10:53:02', 'ssq0160', null, '265', null);
INSERT INTO `user` VALUES ('191', '2017-12-27 10:53:31', '13669696969', '', '111111', null, '0', '2017-12-27 10:53:31', 'ssq0161', null, '266', null);
INSERT INTO `user` VALUES ('192', '2017-12-27 10:54:16', '13669696969', '', '111111', null, '0', '2017-12-27 10:54:16', 'ssq0162', null, '267', null);
INSERT INTO `user` VALUES ('193', '2017-12-27 10:54:43', '13669696969', '松山区妇幼保健所', '8443639', null, '0', '2018-01-17 11:52:11', 'ssq0163', null, '268', null);
INSERT INTO `user` VALUES ('194', '2017-12-27 10:55:12', '13669696969', '', '111111', null, '0', '2017-12-27 10:55:12', 'ssq0164', null, '269', null);
INSERT INTO `user` VALUES ('195', '2017-12-27 10:55:41', '13669696969', '司健', '111111', null, '0', '2018-01-15 11:30:57', 'ssq0165', null, '270', null);
INSERT INTO `user` VALUES ('196', '2017-12-27 10:56:08', '13669696969', '', '111111', null, '0', '2017-12-27 10:56:08', 'ssq0166', null, '271', null);
INSERT INTO `user` VALUES ('197', '2017-12-27 10:56:42', '13669696969', '', '111111', null, '0', '2017-12-27 10:56:42', 'ssq0167', null, '272', null);
INSERT INTO `user` VALUES ('198', '2017-12-27 10:57:31', '13669696969', '', '111111', null, '0', '2017-12-27 10:57:31', 'ssq0168', null, '273', null);
INSERT INTO `user` VALUES ('199', '2017-12-27 10:57:58', '13669696969', '', '111111', null, '0', '2017-12-27 10:57:58', 'ssq0169', null, '274', null);
INSERT INTO `user` VALUES ('200', '2017-12-27 10:58:26', '13669696969', '', '111111', null, '0', '2017-12-27 10:58:26', 'ssq0170', null, '275', null);
INSERT INTO `user` VALUES ('201', '2017-12-27 11:18:01', '13669696969', '', '111111', null, '0', '2017-12-27 11:18:01', 'ssq0010', null, '276', null);
INSERT INTO `user` VALUES ('202', '2017-12-27 11:18:45', '13669696969', '', '111111', null, '0', '2017-12-27 11:18:45', 'ssq0012', null, '277', null);
INSERT INTO `user` VALUES ('203', '2017-12-27 11:19:22', '13669696969', '', '111111', null, '0', '2017-12-27 11:19:22', 'ssq0013', null, '278', null);
INSERT INTO `user` VALUES ('204', '2017-12-27 11:19:54', '13669696969', '', '111111', null, '0', '2017-12-27 11:19:54', 'ssq0014', null, '279', null);
INSERT INTO `user` VALUES ('205', '2017-12-27 11:20:21', '13669696969', '', '111111', null, '0', '2017-12-27 11:20:21', 'ssq0015', null, '280', null);
INSERT INTO `user` VALUES ('206', '2017-12-27 11:20:52', '13669696969', '', '111111', null, '0', '2017-12-27 11:20:52', 'ssq0016', null, '281', null);
INSERT INTO `user` VALUES ('207', '2017-12-27 11:21:42', '13669696969', '', '111111', null, '0', '2017-12-27 11:21:42', 'ssq0017', null, '282', null);
INSERT INTO `user` VALUES ('208', '2017-12-27 11:22:09', '13669696969', '', '111111', null, '0', '2017-12-27 11:22:09', 'ssq0018', null, '283', null);
INSERT INTO `user` VALUES ('209', '2017-12-27 11:22:50', '13669696969', '', '111111', null, '0', '2017-12-27 11:22:50', 'ssq0019', null, '284', null);
INSERT INTO `user` VALUES ('210', '2017-12-27 11:23:19', '13669696969', '', '111111', null, '0', '2017-12-27 11:23:19', 'ssq0020', null, '285', null);
INSERT INTO `user` VALUES ('211', '2017-12-27 11:23:58', '13669696969', '', '111111', null, '0', '2017-12-27 11:23:58', 'ssq0021', null, '286', null);
INSERT INTO `user` VALUES ('212', '2017-12-27 11:24:27', '13669696969', '', '111111', null, '0', '2017-12-27 11:24:27', 'ssq0022', null, '287', null);
INSERT INTO `user` VALUES ('213', '2017-12-27 11:24:56', '13669696969', '', '111111', null, '0', '2017-12-27 11:24:56', 'ssq0023', null, '288', null);
INSERT INTO `user` VALUES ('214', '2017-12-27 11:25:25', '13669696969', '', '111111', null, '0', '2017-12-27 11:25:25', 'ssq0024', null, '289', null);
INSERT INTO `user` VALUES ('215', '2017-12-27 11:25:53', '13669696969', '', '111111', null, '0', '2017-12-27 11:25:53', 'ssq0025', null, '290', null);
INSERT INTO `user` VALUES ('216', '2017-12-27 11:26:20', '13669696969', '', '111111', null, '0', '2017-12-27 11:26:20', 'ssq0026', null, '291', null);
INSERT INTO `user` VALUES ('217', '2017-12-27 11:26:46', '13669696969', '', '111111', null, '0', '2017-12-27 11:26:46', 'ssq0027', null, '292', null);
INSERT INTO `user` VALUES ('218', '2017-12-27 11:27:16', '13669696969', '', '111111', null, '0', '2017-12-27 11:27:16', 'ssq0028', null, '293', null);
INSERT INTO `user` VALUES ('219', '2017-12-27 11:27:47', '13669696969', '', '111111', null, '0', '2017-12-27 11:27:47', 'ssq0029', null, '294', null);
INSERT INTO `user` VALUES ('220', '2017-12-27 11:28:17', '13669696969', '', '111111', null, '0', '2017-12-27 11:28:17', 'ssq0030', null, '295', null);
INSERT INTO `user` VALUES ('221', '2017-12-27 11:28:45', '13669696969', '', '111111', null, '0', '2017-12-27 11:28:45', 'ssq0031', null, '296', null);
INSERT INTO `user` VALUES ('222', '2017-12-27 11:29:12', '13669696969', '黄建峰', '111111', null, '0', '2017-12-29 10:05:58', 'ssq0032', null, '297', null);
INSERT INTO `user` VALUES ('223', '2017-12-27 11:29:39', '13669696969', '', '111111', null, '0', '2017-12-27 11:29:39', 'ssq0033', null, '298', null);
INSERT INTO `user` VALUES ('224', '2017-12-27 11:30:09', '13669696969', '', '111111', null, '0', '2017-12-27 11:30:09', 'ssq0034', null, '299', null);
INSERT INTO `user` VALUES ('225', '2017-12-27 11:30:37', '13669696969', '', '111111', null, '0', '2017-12-27 11:30:37', 'ssq0035', null, '300', null);
INSERT INTO `user` VALUES ('226', '2017-12-27 11:31:11', '13669696969', '', '111111', null, '0', '2017-12-27 11:31:11', 'ssq0036', null, '301', null);
INSERT INTO `user` VALUES ('227', '2017-12-27 11:31:39', '13669696969', '', '111111', null, '0', '2017-12-27 11:31:39', 'ssq0037', null, '302', null);
INSERT INTO `user` VALUES ('228', '2017-12-27 11:32:07', '13669696969', '', '111111', null, '0', '2017-12-27 11:32:07', 'ssq0038', null, '303', null);
INSERT INTO `user` VALUES ('229', '2017-12-27 11:32:35', '13669696969', '', '111111', null, '0', '2017-12-27 11:32:35', 'ssq0039', null, '304', null);
INSERT INTO `user` VALUES ('230', '2017-12-27 11:33:02', '13669696969', '', '111111', null, '0', '2017-12-27 11:33:02', 'ssq0040', null, '305', null);
INSERT INTO `user` VALUES ('231', '2017-12-27 14:57:59', '13734790461', '', '123456', null, '0', '2017-12-27 14:57:59', 'hjssmcs', null, '307', null);
INSERT INTO `user` VALUES ('232', '2017-12-27 15:39:13', '13669696969', '', '111111', null, '0', '2017-12-27 15:39:13', 'ssq0041', null, '309', null);
INSERT INTO `user` VALUES ('233', '2017-12-27 15:40:00', '13669696969', '', '111111', null, '0', '2017-12-27 15:40:00', 'ssq0042', null, '310', null);
INSERT INTO `user` VALUES ('234', '2017-12-27 15:40:27', '13669696969', '', '111111', null, '0', '2017-12-27 15:40:27', 'ssq0043', null, '311', null);
INSERT INTO `user` VALUES ('235', '2017-12-27 15:41:11', '13669696969', '', '111111', null, '0', '2017-12-27 15:41:11', 'ssq0044', null, '312', null);
INSERT INTO `user` VALUES ('236', '2017-12-27 15:41:52', '13669696969', '', '111111', null, '0', '2017-12-27 15:41:52', 'ssq0045', null, '313', null);
INSERT INTO `user` VALUES ('237', '2017-12-27 15:42:32', '13669696969', '', '111111', null, '0', '2017-12-27 15:42:32', 'ssq0046', null, '314', null);
INSERT INTO `user` VALUES ('238', '2017-12-27 15:43:34', '13669696969', '杨楠', '111111', null, '0', '2018-01-09 09:50:31', 'ssq0047', null, '315', null);
INSERT INTO `user` VALUES ('239', '2017-12-27 15:44:08', '13669696969', '', '111111', null, '0', '2017-12-27 15:44:08', 'ssq0048', null, '316', null);
INSERT INTO `user` VALUES ('240', '2017-12-27 15:44:47', '13669696969', '', '111111', null, '0', '2017-12-27 15:44:47', 'ssq0049', null, '317', null);
INSERT INTO `user` VALUES ('241', '2017-12-27 15:45:13', '13669696969', '', '111111', null, '0', '2017-12-27 15:45:13', 'ssq0050', null, '318', null);
INSERT INTO `user` VALUES ('242', '2017-12-27 15:45:54', '13669696969', '', '111111', null, '0', '2017-12-27 15:45:54', 'ssq0051', null, '319', null);
INSERT INTO `user` VALUES ('243', '2017-12-27 15:46:44', '13669696969', '', '111111', null, '0', '2017-12-27 15:46:44', 'ssq0052', null, '320', null);
INSERT INTO `user` VALUES ('244', '2017-12-27 15:47:19', '13669696969', '', '111111', null, '0', '2017-12-27 15:47:19', 'ssq0053', null, '321', null);
INSERT INTO `user` VALUES ('245', '2017-12-27 15:51:03', '13669696969', '', '111111', null, '0', '2017-12-27 15:51:03', 'ssq0054', null, '322', null);
INSERT INTO `user` VALUES ('246', '2017-12-27 15:51:46', '13669696969', '', '111111', null, '0', '2017-12-27 15:51:46', 'ssq0055', null, '323', null);
INSERT INTO `user` VALUES ('247', '2017-12-27 15:52:18', '13669696969', '', '111111', null, '0', '2017-12-27 15:52:18', 'ssq0056', null, '324', null);
INSERT INTO `user` VALUES ('248', '2017-12-27 15:52:55', '13669696969', '', '111111', null, '0', '2017-12-27 15:52:55', 'ssq0057', null, '325', null);
INSERT INTO `user` VALUES ('249', '2017-12-27 15:53:30', '13669696969', '', '111111', null, '0', '2017-12-27 15:53:30', 'ssq0058', null, '326', null);
INSERT INTO `user` VALUES ('250', '2017-12-27 15:54:10', '13669696969', '', '111111', null, '0', '2017-12-27 15:54:10', 'ssq0059', null, '327', null);
INSERT INTO `user` VALUES ('251', '2017-12-27 15:54:43', '13669696969', '', '111111', null, '0', '2017-12-27 15:54:43', 'ssq0060', null, '328', null);
INSERT INTO `user` VALUES ('252', '2017-12-27 15:55:15', '13669696969', '', '111111', null, '0', '2017-12-27 15:55:15', 'ssq0061', null, '329', null);
INSERT INTO `user` VALUES ('253', '2017-12-27 15:55:52', '13669696969', '', '111111', null, '0', '2017-12-27 15:55:52', 'ssq0062', null, '330', null);
INSERT INTO `user` VALUES ('254', '2017-12-27 15:56:31', '13669696969', '', '111111', null, '0', '2017-12-27 15:56:31', 'ssq0063', null, '331', null);
INSERT INTO `user` VALUES ('255', '2017-12-27 15:56:56', '13669696969', '', '111111', null, '0', '2017-12-27 15:56:56', 'ssq0064', null, '332', null);
INSERT INTO `user` VALUES ('256', '2017-12-27 15:57:31', '13669696969', '', '111111', null, '0', '2017-12-27 15:57:31', 'ssq0065', null, '333', null);
INSERT INTO `user` VALUES ('257', '2017-12-27 15:58:12', '13669696969', '', '111111', null, '0', '2017-12-27 15:58:12', 'ssq0066', null, '334', null);
INSERT INTO `user` VALUES ('258', '2017-12-27 15:58:49', '13669696969', '', '111111', null, '0', '2017-12-27 15:58:49', 'ssq0067', null, '335', null);
INSERT INTO `user` VALUES ('259', '2017-12-27 16:00:26', '13669696969', '', '111111', null, '0', '2017-12-27 16:00:26', 'ssq0068', null, '337', null);
INSERT INTO `user` VALUES ('260', '2017-12-27 16:01:07', '15947162438', '', '123456', null, '0', '2017-12-27 16:01:07', 'cflr', null, '338', null);
INSERT INTO `user` VALUES ('261', '2017-12-27 16:01:53', '13669696969', '', '111111', null, '0', '2017-12-27 16:01:53', 'ssq0069', null, '339', null);
INSERT INTO `user` VALUES ('262', '2017-12-27 16:02:30', '13669696969', '', '111111', null, '0', '2017-12-27 16:02:30', 'ssq0070', null, '340', null);
INSERT INTO `user` VALUES ('263', '2017-12-27 16:02:56', '13669696969', '', '111111', null, '0', '2017-12-27 16:02:56', 'ssq0071', null, '341', null);
INSERT INTO `user` VALUES ('264', '2017-12-27 16:03:29', '13669696969', '', '111111', null, '0', '2017-12-27 16:03:29', 'ssq0072', null, '342', null);
INSERT INTO `user` VALUES ('265', '2017-12-27 16:03:55', '13669696969', '', '111111', null, '0', '2017-12-27 16:03:55', 'ssq0073', null, '343', null);
INSERT INTO `user` VALUES ('266', '2017-12-27 16:04:21', '13669696969', '', '111111', null, '0', '2017-12-27 16:04:21', 'ssq0074', null, '344', null);
INSERT INTO `user` VALUES ('267', '2017-12-27 16:04:50', '13669696969', '', '111111', null, '0', '2017-12-27 16:04:50', 'ssq0075', null, '345', null);
INSERT INTO `user` VALUES ('268', '2017-12-27 16:05:19', '13669696969', '', '111111', null, '0', '2017-12-27 16:05:19', 'ssq0076', null, '346', null);
INSERT INTO `user` VALUES ('269', '2017-12-27 16:05:46', '13669696969', '', '111111', null, '0', '2017-12-27 16:05:46', 'ssq0077', null, '347', null);
INSERT INTO `user` VALUES ('270', '2017-12-27 16:06:19', '13669696969', '', '111111', null, '0', '2017-12-27 16:06:19', 'ssq0078', null, '349', null);
INSERT INTO `user` VALUES ('271', '2017-12-27 16:06:48', '13669696969', '', '111111', null, '0', '2017-12-27 16:06:48', 'ssq0079', null, '350', null);
INSERT INTO `user` VALUES ('272', '2017-12-27 16:07:30', '13669696969', '', '111111', null, '0', '2017-12-27 16:07:30', 'ssq0080', null, '351', null);
INSERT INTO `user` VALUES ('273', '2017-12-27 16:17:43', '13948160858', '', '123456', null, '0', '2017-12-27 16:17:43', 'msdwsy', null, '352', null);
INSERT INTO `user` VALUES ('274', '2017-12-27 16:37:45', '13948766399', '', '123456', null, '0', '2017-12-27 16:37:45', 'wntqfhrl', null, '354', null);
INSERT INTO `user` VALUES ('275', '2017-12-27 16:47:23', '15847368988', '', '123456', null, '0', '2017-12-27 16:47:23', 'hgqgb', null, '355', null);
INSERT INTO `user` VALUES ('276', '2017-12-28 10:08:57', '13804761962', '林枫', 'aqscjdglj', null, '0', '2018-01-11 16:01:44', 'cfaqsjj@163.com', null, '38', null);
INSERT INTO `user` VALUES ('277', '2017-12-28 10:11:23', '15847387565', '', '123456', null, '0', '2017-12-28 10:11:23', '15847387565', null, '372', null);
INSERT INTO `user` VALUES ('278', '2017-12-28 10:35:59', '15174844800', '', '123456', null, '0', '2017-12-28 10:35:59', 'lxxzlsgs', null, '373', null);
INSERT INTO `user` VALUES ('279', '2017-12-28 10:36:50', '13669696969', '', '111111', null, '0', '2017-12-28 10:36:50', 'ssq0081', null, '374', null);
INSERT INTO `user` VALUES ('280', '2017-12-28 10:37:45', '13669696969', '', '111111', null, '0', '2017-12-28 10:37:45', 'ssq0082', null, '375', null);
INSERT INTO `user` VALUES ('281', '2017-12-28 10:38:12', '13669696969', '', '111111', null, '0', '2017-12-28 10:38:12', 'ssq0083', null, '376', null);
INSERT INTO `user` VALUES ('282', '2017-12-28 10:38:42', '13669696969', '', '111111', null, '0', '2017-12-28 10:38:42', 'ssq0084', null, '377', null);
INSERT INTO `user` VALUES ('283', '2017-12-28 10:39:12', '13669696969', '', '111111', null, '0', '2017-12-28 10:39:12', 'ssq0085', null, '378', null);
INSERT INTO `user` VALUES ('284', '2017-12-28 10:39:44', '13669696969', '', '111111', null, '0', '2017-12-28 10:39:44', 'ssq0086', null, '379', null);
INSERT INTO `user` VALUES ('285', '2017-12-28 10:40:12', '13669696969', '', '111111', null, '0', '2017-12-28 10:40:12', 'ssq0087', null, '380', null);
INSERT INTO `user` VALUES ('286', '2017-12-28 10:43:03', '13669696969', '', '111111', null, '0', '2017-12-28 10:43:03', 'ssq0088', null, '381', null);
INSERT INTO `user` VALUES ('287', '2017-12-28 10:46:52', '13669696969', '', '111111', null, '0', '2017-12-28 10:46:52', 'ssq0089', null, '382', null);
INSERT INTO `user` VALUES ('288', '2017-12-28 10:47:20', '13669696969', '', '111111', null, '0', '2017-12-28 10:47:20', 'ssq0090', null, '383', null);
INSERT INTO `user` VALUES ('289', '2017-12-28 10:47:51', '13669696969', '', '111111', null, '0', '2017-12-28 10:47:51', 'ssq0091', null, '384', null);
INSERT INTO `user` VALUES ('290', '2017-12-28 10:48:19', '13669696969', '', '111111', null, '0', '2017-12-28 10:48:19', 'ssq0092', null, '385', null);
INSERT INTO `user` VALUES ('291', '2017-12-28 10:50:21', '13669696969', '', '111111', null, '0', '2017-12-28 10:50:21', 'ssq0093', null, '386', null);
INSERT INTO `user` VALUES ('292', '2017-12-28 10:53:25', '13669696969', '', '111111', null, '0', '2017-12-28 10:53:25', 'ssq0094', null, '387', null);
INSERT INTO `user` VALUES ('293', '2017-12-28 10:54:01', '13669696969', '', '111111', null, '0', '2017-12-28 10:54:01', 'ssq0095', null, '388', null);
INSERT INTO `user` VALUES ('294', '2017-12-28 10:54:35', '13669696969', '', '111111', null, '0', '2017-12-28 10:54:35', 'ssq0096', null, '389', null);
INSERT INTO `user` VALUES ('295', '2017-12-28 10:55:01', '13669696969', '', '111111', null, '0', '2017-12-28 10:55:01', 'ssq0097', null, '390', null);
INSERT INTO `user` VALUES ('296', '2017-12-28 10:55:31', '13669696969', '', '111111', null, '0', '2017-12-28 10:55:31', 'ssq0098', null, '391', null);
INSERT INTO `user` VALUES ('297', '2017-12-28 10:55:54', '13669696969', '', '111111', null, '0', '2017-12-28 10:55:54', 'ssq0099', null, '392', null);
INSERT INTO `user` VALUES ('298', '2017-12-28 10:56:21', '13669696969', '', '111111', null, '0', '2017-12-28 10:56:21', 'ssq0100', null, '393', null);
INSERT INTO `user` VALUES ('299', '2017-12-28 10:56:51', '13669696969', '', '1111111', null, '0', '2017-12-28 10:56:51', 'ssq0101', null, '394', null);
INSERT INTO `user` VALUES ('300', '2017-12-28 10:57:20', '13669696969', '', '111111', null, '0', '2017-12-28 10:57:20', 'ssq0102', null, '395', null);
INSERT INTO `user` VALUES ('301', '2017-12-28 10:57:44', '13669696969', '', '111111', null, '0', '2017-12-28 10:57:44', 'ssq0103', null, '396', null);
INSERT INTO `user` VALUES ('302', '2017-12-28 11:00:37', '13669696969', '', '111111', null, '0', '2017-12-28 11:00:37', 'ssq0104', null, '397', null);
INSERT INTO `user` VALUES ('303', '2017-12-28 11:01:19', '13669696969', '', '111111', null, '0', '2017-12-28 11:01:19', 'ssq0105', null, '398', null);
INSERT INTO `user` VALUES ('304', '2017-12-28 11:01:45', '13669696969', '', '111111', null, '0', '2017-12-28 11:01:45', 'ssq0106', null, '399', null);
INSERT INTO `user` VALUES ('305', '2017-12-28 11:02:10', '13669696969', '', '111111', null, '0', '2017-12-28 11:02:10', 'ssq0107', null, '400', null);
INSERT INTO `user` VALUES ('306', '2017-12-28 11:02:39', '13669696969', '', '111111', null, '0', '2017-12-28 11:02:39', 'ssq0108', null, '401', null);
INSERT INTO `user` VALUES ('307', '2017-12-28 11:03:06', '13669696969', '', '111111', null, '0', '2017-12-28 11:03:06', 'ssq0109', null, '402', null);
INSERT INTO `user` VALUES ('308', '2017-12-28 11:03:34', '13669696969', '', '111111', null, '0', '2017-12-28 11:03:34', 'ssq0110', null, '403', null);
INSERT INTO `user` VALUES ('309', '2017-12-28 11:31:42', '13669696969', '', '111111', null, '0', '2017-12-28 11:31:42', 'ssq0001', null, '404', null);
INSERT INTO `user` VALUES ('310', '2017-12-28 15:59:25', '18504862068', '', '123456', null, '0', '2017-12-28 15:59:25', 'lxxzhy', null, '405', null);
INSERT INTO `user` VALUES ('311', '2017-12-29 08:34:52', '13766813007', '', '123456', null, '0', '2017-12-29 08:34:52', 'lxxzbgs', null, '414', null);
INSERT INTO `user` VALUES ('312', '2017-12-29 15:05:52', '18304989888', '', '111111', null, '0', '2018-01-23 16:43:29', 'lxxhlky', null, '418', null);
INSERT INTO `user` VALUES ('313', '2018-01-02 09:16:57', '13947647700', '', '123456', null, '0', '2018-01-02 09:16:57', 'lxxtykygs', null, '433', null);
INSERT INTO `user` VALUES ('316', '2018-01-02 15:20:31', '15648666522', '', '15648666522', null, '0', '2018-01-02 15:20:31', 'dhjs20180102', null, '456', null);
INSERT INTO `user` VALUES ('317', '2018-01-02 16:10:54', '13947979595', '', '13947979595', null, '0', '2018-01-02 16:10:54', 'dkl20180102', null, '458', null);
INSERT INTO `user` VALUES ('318', '2018-01-05 11:25:14', '15047621987', '', '123456', null, '0', '2018-01-05 11:25:14', 'lxxjcyy', null, '468', null);
INSERT INTO `user` VALUES ('319', '2018-01-05 16:34:11', '13674871698', '', '13674871698', null, '0', '2018-01-05 16:34:11', 'sqdl20180105', null, '473', null);
INSERT INTO `user` VALUES ('320', '2018-01-08 08:50:10', '13947666678', '', '13947666678', null, '0', '2018-01-08 08:50:10', 'alt20180108', null, '474', null);
INSERT INTO `user` VALUES ('321', '2018-01-08 14:50:20', '13088411841', '', '123456', null, '0', '2018-01-08 14:50:20', 'lxxwdyhqz', null, '477', null);
INSERT INTO `user` VALUES ('322', '2018-01-08 15:28:56', '15548992566', '', '123456', null, '0', '2018-01-08 15:28:56', 'lxxhjjcz', null, '478', null);
INSERT INTO `user` VALUES ('323', '2018-01-08 16:10:11', '15849680929', '', '123456', null, '0', '2018-01-08 16:10:11', 'lxxyqspc', null, '479', null);
INSERT INTO `user` VALUES ('324', '2018-01-09 14:41:13', '13947686128', '', '13947686128', null, '0', '2018-01-09 14:41:13', 'dt20180109', null, '481', null);
INSERT INTO `user` VALUES ('325', '2018-01-09 15:39:54', '18648358119', '', '18648358119', null, '0', '2018-01-09 15:39:54', 'dbfd20180109', null, '482', null);
INSERT INTO `user` VALUES ('326', '2018-01-09 16:34:45', '13669696969', '郭海波', '999999', null, '0', '2018-01-16 11:29:22', '1111', null, '483', null);
INSERT INTO `user` VALUES ('327', '2018-01-09 17:24:16', '13669696969', '滕志刚', '760831', null, '0', '2018-01-19 10:11:47', '1112', null, '484', null);
INSERT INTO `user` VALUES ('328', '2018-01-10 08:43:28', '15548418067', '', '123456', null, '0', '2018-01-10 08:43:28', 'lxxfqgs', null, '485', null);
INSERT INTO `user` VALUES ('329', '2018-01-10 09:01:06', '15947466689', '', '123456', null, '0', '2018-01-10 09:01:06', 'jlmh', null, '486', null);
INSERT INTO `user` VALUES ('330', '2018-01-10 15:30:20', '13847619298', '', '123456', null, '0', '2018-01-10 15:30:20', '13847619298@126.com', null, '488', null);
INSERT INTO `user` VALUES ('331', '2018-01-10 16:31:23', '15247643057', '项宇航', '123456', null, '0', '2018-01-17 14:53:52', 'aqjtgldd', null, '489', null);
INSERT INTO `user` VALUES ('332', '2018-01-11 08:53:14', '13847664719', '傅冠群', '716818', null, '0', '2018-01-11 16:01:58', 'aqqyy', null, '490', null);
INSERT INTO `user` VALUES ('333', '2018-01-11 08:57:59', '13789693902', '阿鲁科尔沁同济医院', 'xx123456', null, '0', '2018-01-15 14:26:05', 'aqtjyy', null, '491', null);
INSERT INTO `user` VALUES ('334', '2018-01-11 09:00:45', '13451368990', '', '123456', null, '0', '2018-01-11 09:00:45', 'aqzyyy', null, '492', null);
INSERT INTO `user` VALUES ('335', '2018-01-11 09:05:43', '15849953676', '', '123456', null, '0', '2018-01-11 09:05:43', 'aqmyy', null, '493', null);
INSERT INTO `user` VALUES ('336', '2018-01-11 09:13:52', '15148118018', '张志诚', '111111', null, '0', '2018-01-11 20:07:21', 'aqwdhjyz', null, '494', null);
INSERT INTO `user` VALUES ('337', '2018-01-11 09:18:33', '15147620815', '', '123456', null, '0', '2018-01-11 09:18:33', 'aqqmtajyz', null, '495', null);
INSERT INTO `user` VALUES ('338', '2018-01-11 09:23:46', '13088408915', '', '123456', null, '0', '2018-01-11 09:23:46', 'ypasmjyz', null, '496', null);
INSERT INTO `user` VALUES ('339', '2018-01-11 09:28:00', '13847666177', '张燕', '182281!', null, '0', '2018-01-12 09:17:00', 'aqsbtjyz', null, '497', null);
INSERT INTO `user` VALUES ('340', '2018-01-11 09:31:23', '15104812323', '张宏秀', 'aqtjtl', null, '0', '2018-01-11 16:03:12', 'aqtjtl', null, '498', null);
INSERT INTO `user` VALUES ('341', '2018-01-11 09:35:47', '15004761651', '杨冉', '789456', null, '0', '2018-01-12 15:22:38', 'cfyyyxgs', null, '500', null);
INSERT INTO `user` VALUES ('342', '2018-01-11 09:41:31', '13948631045', '呼格力勒图', 'hsmjyz1234', null, '0', '2018-01-11 17:06:36', 'hsmjyz', null, '501', null);
INSERT INTO `user` VALUES ('343', '2018-01-11 10:07:03', '13804765725', '', '666666', null, '0', '2018-01-11 10:07:03', 'wujiawsy@126.com', null, '502', null);
INSERT INTO `user` VALUES ('344', '2018-01-11 10:09:00', '13948165005', '', '123456', null, '0', '2018-01-11 10:09:00', 'blqrdjyz', null, '503', null);
INSERT INTO `user` VALUES ('345', '2018-01-11 10:15:55', '13847679322', '阿荣', '123456', null, '0', '2018-01-11 20:31:48', 'byblgjyz', null, '504', null);
INSERT INTO `user` VALUES ('346', '2018-01-11 10:19:03', '13042642666', '交通石油贸易有限公司', 'jtsy888', null, '0', '2018-01-11 15:36:14', 'jtjyz', null, '505', null);
INSERT INTO `user` VALUES ('347', '2018-01-11 10:22:34', '13804761685', '王玉华', '182281!', null, '0', '2018-01-12 09:18:09', 'byhnjjyz', null, '506', null);
INSERT INTO `user` VALUES ('348', '2018-01-11 10:26:06', '15847326686', '', '123456', null, '0', '2018-01-11 10:26:06', 'bczjyz', null, '507', null);
INSERT INTO `user` VALUES ('349', '2018-01-11 10:30:48', '13947686657', '', '123456', null, '0', '2018-01-11 10:30:48', 'cgnejyz', null, '508', null);
INSERT INTO `user` VALUES ('350', '2018-01-11 10:50:22', '15548386169', '', '111111', null, '0', '2018-01-11 16:17:34', 'zshthjyz', null, '509', null);
INSERT INTO `user` VALUES ('351', '2018-01-11 11:01:11', '15147697387', '', '123456', null, '0', '2018-01-11 11:01:11', 'zsytyjyz', null, '510', null);
INSERT INTO `user` VALUES ('352', '2018-01-11 11:05:20', '13947692638', '', '123456', null, '0', '2018-01-11 11:05:20', 'zsyhljyz', null, '511', null);
INSERT INTO `user` VALUES ('353', '2018-01-11 14:03:28', '15048686155', '', '123456', null, '0', '2018-01-11 14:03:28', 'zsyttjyz', null, '512', null);
INSERT INTO `user` VALUES ('354', '2018-01-11 14:07:23', '13947362522', '', '123456', null, '0', '2018-01-11 14:07:23', 'zsykdjyz', null, '513', null);
INSERT INTO `user` VALUES ('355', '2018-01-11 14:12:02', '13848899697', '', '123456', null, '0', '2018-01-11 14:12:02', 'zsyxfjyz', null, '514', null);
INSERT INTO `user` VALUES ('356', '2018-01-11 14:17:01', '15147697387', '', '123456', null, '0', '2018-01-11 14:17:01', 'zsysgjyz', null, '515', null);
INSERT INTO `user` VALUES ('357', '2018-01-11 14:20:32', '13947674588', '', '123456', null, '0', '2018-01-11 14:20:32', 'zsyqnjyz', null, '516', null);
INSERT INTO `user` VALUES ('358', '2018-01-11 14:24:31', '18747613007', '', '123456', null, '0', '2018-01-11 14:24:31', 'zsyysjyz', null, '517', null);
INSERT INTO `user` VALUES ('359', '2018-01-11 14:29:15', '15004873377', '', '123456', null, '0', '2018-01-11 14:29:15', 'zsyxjjyz', null, '518', null);
INSERT INTO `user` VALUES ('360', '2018-01-11 14:42:09', '13789662085', '', '123456', null, '0', '2018-01-11 14:42:09', 'zsyddjyz', null, '519', null);
INSERT INTO `user` VALUES ('361', '2018-01-11 14:53:32', '13948567016', '', '123456', null, '0', '2018-01-11 14:53:32', 'zsytsjyz', null, '520', null);
INSERT INTO `user` VALUES ('362', '2018-01-11 14:57:49', '13847615606', '苗永杰', '123456', null, '0', '2018-01-12 09:20:32', 'zsyssjyz', null, '521', null);
INSERT INTO `user` VALUES ('363', '2018-01-11 14:59:54', '13947692638', '', '123456', null, '0', '2018-01-11 14:59:54', 'zsycbjyz', null, '522', null);
INSERT INTO `user` VALUES ('364', '2018-01-11 15:03:33', '13684762875', '', '123456', null, '0', '2018-01-11 15:03:33', 'zshtsjyz', null, '523', null);
INSERT INTO `user` VALUES ('365', '2018-01-11 15:07:55', '18248008700', '', '123456', null, '0', '2018-01-11 15:07:55', 'aqxysmjyz', null, '524', null);
INSERT INTO `user` VALUES ('366', '2018-01-11 15:15:14', '18248008700', '', '123456', null, '0', '2018-01-11 15:15:14', 'aqxysm', null, '525', null);
INSERT INTO `user` VALUES ('367', '2018-01-11 15:35:22', '18604769130', '', '123456', null, '0', '2018-01-11 15:35:22', 'zsylhyt', null, '526', null);
INSERT INTO `user` VALUES ('368', '2018-01-11 15:49:46', '13684767955', '', '123456', null, '0', '2018-01-11 15:49:46', 'wqzjs1', null, '527', null);
INSERT INTO `user` VALUES ('369', '2018-01-15 08:47:42', '13789563355', '', '123456', null, '0', '2018-01-15 08:47:42', 'lxxbygxd', null, '532', null);
INSERT INTO `user` VALUES ('370', '2018-01-15 10:02:11', '13034767409', '', '123456', null, '0', '2018-01-15 10:02:11', 'aqycbzmy', null, '533', null);
INSERT INTO `user` VALUES ('371', '2018-01-15 10:46:16', '13789669999', '', '123456', null, '0', '2018-01-15 10:46:16', 'zgstnjjyz', null, '534', null);
INSERT INTO `user` VALUES ('372', '2018-01-15 10:48:37', '13789669999', '', '123456', null, '0', '2018-01-15 10:48:37', 'xmnjjyz', null, '535', null);
INSERT INTO `user` VALUES ('373', '2018-01-15 11:06:27', '13789669999', '', '123456', null, '0', '2018-01-15 11:06:27', 'tsknjjyz', null, '536', null);
INSERT INTO `user` VALUES ('374', '2018-01-16 09:42:21', '15734765769', '', '123456', null, '0', '2018-01-16 09:42:21', 'lzspyxgs', null, '537', null);
INSERT INTO `user` VALUES ('375', '2018-01-17 16:02:11', '18247604321', '', '123456', null, '0', '2018-01-17 16:02:11', 'xqnfcpgx', null, '544', null);
INSERT INTO `user` VALUES ('376', '2018-01-19 09:28:39', '15047624181', '', '123456', null, '0', '2018-01-19 09:28:39', 'lxxgaj', null, '551', null);
INSERT INTO `user` VALUES ('377', '2018-01-19 10:52:25', '13669696969', '', '111111', null, '0', '2018-01-19 10:52:25', '1113', null, '552', null);
INSERT INTO `user` VALUES ('378', '2018-01-22 09:15:56', '13451339292', '', '123456', null, '0', '2018-01-22 09:15:56', 'aqbynejyz', null, '554', null);
INSERT INTO `user` VALUES ('379', '2018-01-23 09:54:45', '15849682977', '', '123456', null, '0', '2018-01-23 09:54:45', 'lxxjyqtgs', null, '558', null);
INSERT INTO `user` VALUES ('380', '2018-01-23 10:29:10', '13684770899', '', '111111', null, '0', '2018-01-23 10:29:10', 'hsq0001', null, '559', null);
INSERT INTO `user` VALUES ('381', '2018-01-23 17:14:20', '15048370418', '', '111111', null, '0', '2018-01-23 17:14:20', 'hsq0002', null, '568', null);
INSERT INTO `user` VALUES ('382', '2018-01-24 09:48:28', '15847626035', '', '111111', null, '0', '2018-01-24 09:59:37', 'bybtnj', null, '575', null);

-- ----------------------------
-- Table structure for `user_post`
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post` (
  `user_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`post_id`),
  KEY `FK993unwep4cqba09j5py92sm9k` (`post_id`),
  CONSTRAINT `FK993unwep4cqba09j5py92sm9k` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKa6yymjh1buy6751sdh9fbc47u` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_post
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('4', '1');
INSERT INTO `user_role` VALUES ('4', '2');
INSERT INTO `user_role` VALUES ('4', '4');
INSERT INTO `user_role` VALUES ('4', '7');
INSERT INTO `user_role` VALUES ('6', '8');
INSERT INTO `user_role` VALUES ('1', '9');
INSERT INTO `user_role` VALUES ('7', '10');
INSERT INTO `user_role` VALUES ('2', '11');
INSERT INTO `user_role` VALUES ('1', '13');
INSERT INTO `user_role` VALUES ('1', '14');
INSERT INTO `user_role` VALUES ('1', '15');
INSERT INTO `user_role` VALUES ('1', '16');
INSERT INTO `user_role` VALUES ('1', '17');
INSERT INTO `user_role` VALUES ('1', '18');
INSERT INTO `user_role` VALUES ('6', '19');
INSERT INTO `user_role` VALUES ('6', '20');
INSERT INTO `user_role` VALUES ('6', '21');
INSERT INTO `user_role` VALUES ('6', '22');
INSERT INTO `user_role` VALUES ('6', '23');
INSERT INTO `user_role` VALUES ('6', '24');
INSERT INTO `user_role` VALUES ('6', '25');
INSERT INTO `user_role` VALUES ('6', '26');
INSERT INTO `user_role` VALUES ('1', '28');
INSERT INTO `user_role` VALUES ('2', '29');
INSERT INTO `user_role` VALUES ('2', '30');
INSERT INTO `user_role` VALUES ('6', '31');
INSERT INTO `user_role` VALUES ('2', '32');
INSERT INTO `user_role` VALUES ('6', '33');
INSERT INTO `user_role` VALUES ('2', '34');
INSERT INTO `user_role` VALUES ('2', '36');
INSERT INTO `user_role` VALUES ('2', '37');
INSERT INTO `user_role` VALUES ('2', '39');
INSERT INTO `user_role` VALUES ('2', '40');
INSERT INTO `user_role` VALUES ('2', '41');
INSERT INTO `user_role` VALUES ('2', '42');
INSERT INTO `user_role` VALUES ('2', '44');
INSERT INTO `user_role` VALUES ('2', '48');
INSERT INTO `user_role` VALUES ('2', '49');
INSERT INTO `user_role` VALUES ('2', '50');
INSERT INTO `user_role` VALUES ('2', '51');
INSERT INTO `user_role` VALUES ('2', '52');
INSERT INTO `user_role` VALUES ('2', '53');
INSERT INTO `user_role` VALUES ('2', '54');
INSERT INTO `user_role` VALUES ('2', '55');
INSERT INTO `user_role` VALUES ('2', '56');
INSERT INTO `user_role` VALUES ('2', '57');
INSERT INTO `user_role` VALUES ('2', '59');
INSERT INTO `user_role` VALUES ('6', '60');
INSERT INTO `user_role` VALUES ('2', '63');
INSERT INTO `user_role` VALUES ('2', '64');
INSERT INTO `user_role` VALUES ('2', '65');
INSERT INTO `user_role` VALUES ('2', '66');
INSERT INTO `user_role` VALUES ('2', '67');
INSERT INTO `user_role` VALUES ('2', '68');
INSERT INTO `user_role` VALUES ('2', '69');
INSERT INTO `user_role` VALUES ('2', '70');
INSERT INTO `user_role` VALUES ('2', '71');
INSERT INTO `user_role` VALUES ('2', '72');
INSERT INTO `user_role` VALUES ('2', '73');
INSERT INTO `user_role` VALUES ('2', '74');
INSERT INTO `user_role` VALUES ('2', '75');
INSERT INTO `user_role` VALUES ('2', '76');
INSERT INTO `user_role` VALUES ('2', '77');
INSERT INTO `user_role` VALUES ('2', '78');
INSERT INTO `user_role` VALUES ('2', '79');
INSERT INTO `user_role` VALUES ('2', '80');
INSERT INTO `user_role` VALUES ('2', '81');
INSERT INTO `user_role` VALUES ('2', '82');
INSERT INTO `user_role` VALUES ('2', '83');
INSERT INTO `user_role` VALUES ('2', '84');
INSERT INTO `user_role` VALUES ('2', '85');
INSERT INTO `user_role` VALUES ('2', '86');
INSERT INTO `user_role` VALUES ('2', '87');
INSERT INTO `user_role` VALUES ('2', '88');
INSERT INTO `user_role` VALUES ('2', '89');
INSERT INTO `user_role` VALUES ('2', '90');
INSERT INTO `user_role` VALUES ('2', '91');
INSERT INTO `user_role` VALUES ('2', '92');
INSERT INTO `user_role` VALUES ('2', '93');
INSERT INTO `user_role` VALUES ('2', '94');
INSERT INTO `user_role` VALUES ('2', '95');
INSERT INTO `user_role` VALUES ('1', '122');
INSERT INTO `user_role` VALUES ('1', '123');
INSERT INTO `user_role` VALUES ('6', '124');
INSERT INTO `user_role` VALUES ('2', '126');
INSERT INTO `user_role` VALUES ('2', '127');
INSERT INTO `user_role` VALUES ('2', '128');
INSERT INTO `user_role` VALUES ('2', '129');
INSERT INTO `user_role` VALUES ('2', '130');
INSERT INTO `user_role` VALUES ('2', '131');
INSERT INTO `user_role` VALUES ('2', '132');
INSERT INTO `user_role` VALUES ('2', '133');
INSERT INTO `user_role` VALUES ('2', '134');
INSERT INTO `user_role` VALUES ('2', '135');
INSERT INTO `user_role` VALUES ('2', '136');
INSERT INTO `user_role` VALUES ('2', '137');
INSERT INTO `user_role` VALUES ('2', '138');
INSERT INTO `user_role` VALUES ('2', '139');
INSERT INTO `user_role` VALUES ('2', '140');
INSERT INTO `user_role` VALUES ('2', '141');
INSERT INTO `user_role` VALUES ('2', '142');
INSERT INTO `user_role` VALUES ('2', '143');
INSERT INTO `user_role` VALUES ('2', '144');
INSERT INTO `user_role` VALUES ('2', '145');
INSERT INTO `user_role` VALUES ('2', '146');
INSERT INTO `user_role` VALUES ('2', '147');
INSERT INTO `user_role` VALUES ('2', '148');
INSERT INTO `user_role` VALUES ('2', '149');
INSERT INTO `user_role` VALUES ('2', '150');
INSERT INTO `user_role` VALUES ('2', '151');
INSERT INTO `user_role` VALUES ('2', '152');
INSERT INTO `user_role` VALUES ('2', '153');
INSERT INTO `user_role` VALUES ('2', '154');
INSERT INTO `user_role` VALUES ('2', '155');
INSERT INTO `user_role` VALUES ('2', '156');
INSERT INTO `user_role` VALUES ('2', '157');
INSERT INTO `user_role` VALUES ('2', '158');
INSERT INTO `user_role` VALUES ('2', '159');
INSERT INTO `user_role` VALUES ('2', '160');
INSERT INTO `user_role` VALUES ('2', '161');
INSERT INTO `user_role` VALUES ('2', '162');
INSERT INTO `user_role` VALUES ('2', '163');
INSERT INTO `user_role` VALUES ('2', '164');
INSERT INTO `user_role` VALUES ('2', '165');
INSERT INTO `user_role` VALUES ('2', '166');
INSERT INTO `user_role` VALUES ('2', '167');
INSERT INTO `user_role` VALUES ('2', '168');
INSERT INTO `user_role` VALUES ('2', '169');
INSERT INTO `user_role` VALUES ('2', '170');
INSERT INTO `user_role` VALUES ('2', '171');
INSERT INTO `user_role` VALUES ('2', '172');
INSERT INTO `user_role` VALUES ('2', '173');
INSERT INTO `user_role` VALUES ('2', '174');
INSERT INTO `user_role` VALUES ('2', '175');
INSERT INTO `user_role` VALUES ('2', '176');
INSERT INTO `user_role` VALUES ('2', '177');
INSERT INTO `user_role` VALUES ('2', '178');
INSERT INTO `user_role` VALUES ('2', '180');
INSERT INTO `user_role` VALUES ('2', '181');
INSERT INTO `user_role` VALUES ('2', '182');
INSERT INTO `user_role` VALUES ('2', '183');
INSERT INTO `user_role` VALUES ('2', '184');
INSERT INTO `user_role` VALUES ('2', '185');
INSERT INTO `user_role` VALUES ('2', '186');
INSERT INTO `user_role` VALUES ('2', '187');
INSERT INTO `user_role` VALUES ('2', '188');
INSERT INTO `user_role` VALUES ('2', '189');
INSERT INTO `user_role` VALUES ('2', '190');
INSERT INTO `user_role` VALUES ('2', '191');
INSERT INTO `user_role` VALUES ('2', '192');
INSERT INTO `user_role` VALUES ('2', '193');
INSERT INTO `user_role` VALUES ('2', '194');
INSERT INTO `user_role` VALUES ('2', '195');
INSERT INTO `user_role` VALUES ('2', '196');
INSERT INTO `user_role` VALUES ('2', '197');
INSERT INTO `user_role` VALUES ('2', '198');
INSERT INTO `user_role` VALUES ('2', '199');
INSERT INTO `user_role` VALUES ('2', '200');
INSERT INTO `user_role` VALUES ('2', '201');
INSERT INTO `user_role` VALUES ('2', '202');
INSERT INTO `user_role` VALUES ('2', '203');
INSERT INTO `user_role` VALUES ('2', '204');
INSERT INTO `user_role` VALUES ('2', '205');
INSERT INTO `user_role` VALUES ('2', '206');
INSERT INTO `user_role` VALUES ('2', '207');
INSERT INTO `user_role` VALUES ('2', '208');
INSERT INTO `user_role` VALUES ('2', '209');
INSERT INTO `user_role` VALUES ('2', '210');
INSERT INTO `user_role` VALUES ('2', '211');
INSERT INTO `user_role` VALUES ('2', '212');
INSERT INTO `user_role` VALUES ('2', '213');
INSERT INTO `user_role` VALUES ('2', '214');
INSERT INTO `user_role` VALUES ('2', '215');
INSERT INTO `user_role` VALUES ('2', '216');
INSERT INTO `user_role` VALUES ('2', '217');
INSERT INTO `user_role` VALUES ('2', '218');
INSERT INTO `user_role` VALUES ('2', '219');
INSERT INTO `user_role` VALUES ('2', '220');
INSERT INTO `user_role` VALUES ('2', '221');
INSERT INTO `user_role` VALUES ('2', '222');
INSERT INTO `user_role` VALUES ('2', '223');
INSERT INTO `user_role` VALUES ('2', '224');
INSERT INTO `user_role` VALUES ('2', '225');
INSERT INTO `user_role` VALUES ('2', '226');
INSERT INTO `user_role` VALUES ('2', '227');
INSERT INTO `user_role` VALUES ('2', '228');
INSERT INTO `user_role` VALUES ('2', '229');
INSERT INTO `user_role` VALUES ('2', '230');
INSERT INTO `user_role` VALUES ('2', '231');
INSERT INTO `user_role` VALUES ('2', '232');
INSERT INTO `user_role` VALUES ('2', '233');
INSERT INTO `user_role` VALUES ('2', '234');
INSERT INTO `user_role` VALUES ('2', '235');
INSERT INTO `user_role` VALUES ('2', '236');
INSERT INTO `user_role` VALUES ('2', '237');
INSERT INTO `user_role` VALUES ('2', '238');
INSERT INTO `user_role` VALUES ('2', '239');
INSERT INTO `user_role` VALUES ('2', '240');
INSERT INTO `user_role` VALUES ('2', '241');
INSERT INTO `user_role` VALUES ('2', '242');
INSERT INTO `user_role` VALUES ('2', '243');
INSERT INTO `user_role` VALUES ('2', '244');
INSERT INTO `user_role` VALUES ('2', '245');
INSERT INTO `user_role` VALUES ('2', '246');
INSERT INTO `user_role` VALUES ('2', '247');
INSERT INTO `user_role` VALUES ('2', '248');
INSERT INTO `user_role` VALUES ('2', '249');
INSERT INTO `user_role` VALUES ('2', '250');
INSERT INTO `user_role` VALUES ('2', '251');
INSERT INTO `user_role` VALUES ('2', '252');
INSERT INTO `user_role` VALUES ('2', '253');
INSERT INTO `user_role` VALUES ('2', '254');
INSERT INTO `user_role` VALUES ('2', '255');
INSERT INTO `user_role` VALUES ('2', '256');
INSERT INTO `user_role` VALUES ('2', '257');
INSERT INTO `user_role` VALUES ('2', '258');
INSERT INTO `user_role` VALUES ('2', '259');
INSERT INTO `user_role` VALUES ('2', '260');
INSERT INTO `user_role` VALUES ('2', '261');
INSERT INTO `user_role` VALUES ('2', '262');
INSERT INTO `user_role` VALUES ('2', '263');
INSERT INTO `user_role` VALUES ('2', '264');
INSERT INTO `user_role` VALUES ('2', '265');
INSERT INTO `user_role` VALUES ('2', '266');
INSERT INTO `user_role` VALUES ('2', '267');
INSERT INTO `user_role` VALUES ('2', '268');
INSERT INTO `user_role` VALUES ('2', '269');
INSERT INTO `user_role` VALUES ('2', '270');
INSERT INTO `user_role` VALUES ('2', '271');
INSERT INTO `user_role` VALUES ('2', '272');
INSERT INTO `user_role` VALUES ('2', '273');
INSERT INTO `user_role` VALUES ('2', '274');
INSERT INTO `user_role` VALUES ('2', '275');
INSERT INTO `user_role` VALUES ('6', '276');
INSERT INTO `user_role` VALUES ('2', '277');
INSERT INTO `user_role` VALUES ('2', '278');
INSERT INTO `user_role` VALUES ('2', '279');
INSERT INTO `user_role` VALUES ('2', '280');
INSERT INTO `user_role` VALUES ('2', '281');
INSERT INTO `user_role` VALUES ('2', '282');
INSERT INTO `user_role` VALUES ('2', '283');
INSERT INTO `user_role` VALUES ('2', '284');
INSERT INTO `user_role` VALUES ('2', '285');
INSERT INTO `user_role` VALUES ('2', '286');
INSERT INTO `user_role` VALUES ('2', '287');
INSERT INTO `user_role` VALUES ('2', '288');
INSERT INTO `user_role` VALUES ('2', '289');
INSERT INTO `user_role` VALUES ('2', '290');
INSERT INTO `user_role` VALUES ('2', '291');
INSERT INTO `user_role` VALUES ('2', '292');
INSERT INTO `user_role` VALUES ('2', '293');
INSERT INTO `user_role` VALUES ('2', '294');
INSERT INTO `user_role` VALUES ('2', '295');
INSERT INTO `user_role` VALUES ('2', '296');
INSERT INTO `user_role` VALUES ('2', '297');
INSERT INTO `user_role` VALUES ('2', '298');
INSERT INTO `user_role` VALUES ('2', '299');
INSERT INTO `user_role` VALUES ('2', '300');
INSERT INTO `user_role` VALUES ('2', '301');
INSERT INTO `user_role` VALUES ('2', '302');
INSERT INTO `user_role` VALUES ('2', '303');
INSERT INTO `user_role` VALUES ('2', '304');
INSERT INTO `user_role` VALUES ('2', '305');
INSERT INTO `user_role` VALUES ('2', '306');
INSERT INTO `user_role` VALUES ('2', '307');
INSERT INTO `user_role` VALUES ('2', '308');
INSERT INTO `user_role` VALUES ('2', '309');
INSERT INTO `user_role` VALUES ('2', '310');
INSERT INTO `user_role` VALUES ('2', '311');
INSERT INTO `user_role` VALUES ('2', '312');
INSERT INTO `user_role` VALUES ('2', '313');
INSERT INTO `user_role` VALUES ('2', '316');
INSERT INTO `user_role` VALUES ('2', '317');
INSERT INTO `user_role` VALUES ('2', '318');
INSERT INTO `user_role` VALUES ('2', '319');
INSERT INTO `user_role` VALUES ('2', '320');
INSERT INTO `user_role` VALUES ('2', '321');
INSERT INTO `user_role` VALUES ('2', '322');
INSERT INTO `user_role` VALUES ('2', '323');
INSERT INTO `user_role` VALUES ('2', '324');
INSERT INTO `user_role` VALUES ('2', '325');
INSERT INTO `user_role` VALUES ('2', '326');
INSERT INTO `user_role` VALUES ('2', '327');
INSERT INTO `user_role` VALUES ('2', '328');
INSERT INTO `user_role` VALUES ('2', '329');
INSERT INTO `user_role` VALUES ('2', '330');
INSERT INTO `user_role` VALUES ('2', '331');
INSERT INTO `user_role` VALUES ('2', '332');
INSERT INTO `user_role` VALUES ('2', '333');
INSERT INTO `user_role` VALUES ('2', '334');
INSERT INTO `user_role` VALUES ('2', '335');
INSERT INTO `user_role` VALUES ('2', '336');
INSERT INTO `user_role` VALUES ('2', '337');
INSERT INTO `user_role` VALUES ('2', '338');
INSERT INTO `user_role` VALUES ('2', '339');
INSERT INTO `user_role` VALUES ('2', '340');
INSERT INTO `user_role` VALUES ('2', '341');
INSERT INTO `user_role` VALUES ('2', '342');
INSERT INTO `user_role` VALUES ('2', '343');
INSERT INTO `user_role` VALUES ('2', '344');
INSERT INTO `user_role` VALUES ('2', '345');
INSERT INTO `user_role` VALUES ('2', '346');
INSERT INTO `user_role` VALUES ('2', '347');
INSERT INTO `user_role` VALUES ('2', '348');
INSERT INTO `user_role` VALUES ('2', '349');
INSERT INTO `user_role` VALUES ('2', '350');
INSERT INTO `user_role` VALUES ('2', '351');
INSERT INTO `user_role` VALUES ('2', '352');
INSERT INTO `user_role` VALUES ('2', '353');
INSERT INTO `user_role` VALUES ('2', '354');
INSERT INTO `user_role` VALUES ('2', '355');
INSERT INTO `user_role` VALUES ('2', '356');
INSERT INTO `user_role` VALUES ('2', '357');
INSERT INTO `user_role` VALUES ('2', '358');
INSERT INTO `user_role` VALUES ('2', '359');
INSERT INTO `user_role` VALUES ('2', '360');
INSERT INTO `user_role` VALUES ('2', '361');
INSERT INTO `user_role` VALUES ('2', '362');
INSERT INTO `user_role` VALUES ('2', '363');
INSERT INTO `user_role` VALUES ('2', '364');
INSERT INTO `user_role` VALUES ('2', '365');
INSERT INTO `user_role` VALUES ('2', '366');
INSERT INTO `user_role` VALUES ('2', '367');
INSERT INTO `user_role` VALUES ('1', '368');
INSERT INTO `user_role` VALUES ('2', '369');
INSERT INTO `user_role` VALUES ('2', '370');
INSERT INTO `user_role` VALUES ('2', '371');
INSERT INTO `user_role` VALUES ('2', '372');
INSERT INTO `user_role` VALUES ('2', '373');
INSERT INTO `user_role` VALUES ('2', '374');
INSERT INTO `user_role` VALUES ('2', '375');
INSERT INTO `user_role` VALUES ('2', '376');
INSERT INTO `user_role` VALUES ('2', '377');
INSERT INTO `user_role` VALUES ('2', '378');
INSERT INTO `user_role` VALUES ('2', '379');
INSERT INTO `user_role` VALUES ('2', '380');
INSERT INTO `user_role` VALUES ('2', '381');
INSERT INTO `user_role` VALUES ('2', '382');

-- ----------------------------
-- Table structure for `web_app_menu`
-- ----------------------------
DROP TABLE IF EXISTS `web_app_menu`;
CREATE TABLE `web_app_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `badge_content` varchar(255) DEFAULT NULL,
  `badge_style` varchar(255) DEFAULT NULL,
  `controller` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_abstract` bit(1) NOT NULL,
  `is_route` bit(1) DEFAULT NULL,
  `is_show` bit(1) DEFAULT NULL,
  `is_show_badge` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `route_name` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `template_url` varchar(255) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `p_route_id` bigint(20) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mvsbml65dv839c534agobqhhy` (`route_name`),
  KEY `FK9ip2cxmargg14eplf393b9oo5` (`create_user_id`),
  KEY `FKcvrb26c1fywnbymocq0ncofxc` (`p_route_id`),
  KEY `FK67t66hi0r5kif460sp1s0i86m` (`p_id`),
  CONSTRAINT `FK67t66hi0r5kif460sp1s0i86m` FOREIGN KEY (`p_id`) REFERENCES `web_app_menu` (`id`),
  CONSTRAINT `FK9ip2cxmargg14eplf393b9oo5` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKcvrb26c1fywnbymocq0ncofxc` FOREIGN KEY (`p_route_id`) REFERENCES `web_app_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of web_app_menu
-- ----------------------------
INSERT INTO `web_app_menu` VALUES ('1', null, null, null, '强检器具检定管理（适用于技术机构）', '', '', '', '', '检定申请', null, null, 'checkApplyForTechnicalInstitution', '', null, '-2147483626', null, '49', '49');
INSERT INTO `web_app_menu` VALUES ('2', null, null, null, '技术机构管理技术机构计量标准装置一览表--新增', '', '', '', '', '技术机构计量标准装置一览表--新增', null, null, 'FileAdd', '', null, '-2147483623', null, '46', '53');
INSERT INTO `web_app_menu` VALUES ('3', null, null, null, '我的工作-待办工作', '', '', '', '', '待办工作', null, null, 'Unhandle', '', 'views/mywork/unhandle/index.html', '-2147483644', null, '32', '32');
INSERT INTO `web_app_menu` VALUES ('4', null, null, null, '本菜单适用于技术机构，用于查询管理部门指定给其的所有器具', '', '', '', '', '综合查询', null, null, 'appointCheckInstrument', '', null, '-2147483635', null, '38', '38');
INSERT INTO `web_app_menu` VALUES ('5', null, null, null, null, '', '', '', '', '用户管理 -- 查看详情', null, null, 'UserfileDetail', '', null, '0', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('6', null, null, null, '生产企业管理', '', '', '', '', '生产企业', null, null, 'enterprise', '', null, '-2147483615', null, '26', '26');
INSERT INTO `web_app_menu` VALUES ('7', null, null, null, '我的消息-收件箱', '', '', '', '', '收件箱', null, null, 'inbox', '', 'views/myMessage/inbox/index.html', '0', null, '45', '45');
INSERT INTO `web_app_menu` VALUES ('8', null, null, null, '强检器具类别管理，每种器具都应该是器具类别的一种', '', '', '', '', '强检器具类别管理', null, null, 'instrumentType', '', null, '-2147483604', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('9', null, null, null, '强检器具检定管理（适用于器具用户）', '', '', '', '', '检定申请', null, null, 'checkApplyForInstrumentUser', '', null, '-2147483627', null, '49', '49');
INSERT INTO `web_app_menu` VALUES ('10', null, null, null, '器具的检定数据管理', '', '', '', '', '检定档案管理', null, null, 'integratedAudit', '', null, '-2147483630', null, '49', '49');
INSERT INTO `web_app_menu` VALUES ('11', null, null, null, '我的消息-未读消息', '', '', '', '', '未读消息', null, null, 'unReadMessage', '', 'views/myMessage/unReadMessage/index.html', '0', null, '45', '45');
INSERT INTO `web_app_menu` VALUES ('12', null, null, null, null, '', '', '', '', '强检器具类别管理 -- 新增', null, null, 'instrumentTypeAdd', '', null, '-2147483603', null, '44', '8');
INSERT INTO `web_app_menu` VALUES ('13', null, null, null, '人员资质管理综合查询', '', '', '', '', '综合查询', null, null, 'personnelQualificationManage', '', null, '-2147483612', null, '17', '17');
INSERT INTO `web_app_menu` VALUES ('14', null, null, null, '适用于器具用户对自己过期器具的综合查询', '', '', '', '', '综合查询', null, null, 'InstrumentForcedTimeoutCheckApplyIntegratedQueryIndex', '', null, '-2147483632', null, '27', '27');
INSERT INTO `web_app_menu` VALUES ('15', null, null, null, null, '', '', '', '', '角色管理', null, null, 'role', '', null, '-2147483606', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('16', null, null, null, '授权检定项目综合管理--新增', '', '', '', '', '授权检定项目管理--新增', null, null, 'AuthorizationManageAdd', '', null, '-2147483619', null, '46', '60');
INSERT INTO `web_app_menu` VALUES ('17', null, null, null, null, '', '', '', '', '人员资质', null, null, 'personnel', '', null, '-2147483613', null, null, null);
INSERT INTO `web_app_menu` VALUES ('18', null, null, null, '计量系统权限设置', '', '', '', '', '系统设置-权限设置', null, null, 'Authority', '', null, '-2147483600', null, '44', '29');
INSERT INTO `web_app_menu` VALUES ('19', 'NEW', 'warning', null, null, '', '', '', '', '大数据平台', null, null, 'analytics', '', null, '-2147483646', null, '30', null);
INSERT INTO `web_app_menu` VALUES ('20', null, null, null, '强制检定管理计量器具强制检定计量器具年检定量统计', '', '', '', '', '器具检定档案管理', null, null, 'instrumentCheckInfo', '', null, '-2147483628', null, '49', '49');
INSERT INTO `web_app_menu` VALUES ('21', null, null, null, '本菜单适用于器具用户，用于查询器具用户提出的备案申请', '', '', '', '', '综合查询', null, null, 'Integrated', '', 'views/mandatory/integrated/index.html', '-2147483636', null, '38', '38');
INSERT INTO `web_app_menu` VALUES ('22', null, null, null, '强制检定管理计量器具强制检定计量器具年检定量统计', '', '', '', '', '器具检定综合查询', null, null, 'instrumentCheckInfoManage', '', null, '-2147483629', null, '49', '49');
INSERT INTO `web_app_menu` VALUES ('23', null, null, null, '强制检定管理计量器具用户强制检定计量器具档案 -- 新增(申请)', '', '', '', '', '新增', null, null, 'IntegratedAdd', '', null, '0', null, '49', '49');
INSERT INTO `web_app_menu` VALUES ('24', null, null, null, '技术机构管理技术机构授权检定项目综合查询', '', '', '', '', '授权检定项目综合查询', null, null, 'Authorization', '', null, '-2147483621', null, '46', '46');
INSERT INTO `web_app_menu` VALUES ('25', 'V0.1', 'success', null, '动态显示系统各项数据指标', '', '', '', '', '仪表台', 'yibiaotai', null, 'dashboard', '', null, '-2147483647', null, '30', null);
INSERT INTO `web_app_menu` VALUES ('26', null, null, null, null, '', '', '', '', '用户管理', null, null, 'department', '', null, '-2147483618', null, null, null);
INSERT INTO `web_app_menu` VALUES ('27', null, null, null, null, '', '', '', '', '强检器具超期检定管理', null, null, 'InstrumentForcedTimeOutCheckApplyParent', '', null, '-2147483634', null, null, null);
INSERT INTO `web_app_menu` VALUES ('28', null, null, null, null, '', '', '', '', '角色管理 -- 编辑', null, null, 'RolefileEdit', '', null, '0', null, '44', '15');
INSERT INTO `web_app_menu` VALUES ('29', null, null, null, '计量系统菜单管理', '', '', '', '', '菜单管理', null, null, 'Menu', '', null, '-2147483605', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('30', null, null, null, null, '', '', '', '', '用于仪表台及大数据平台继承', null, null, 'main', '', 'views/main.html', '-2147483648', null, null, null);
INSERT INTO `web_app_menu` VALUES ('31', null, null, null, '技术机构管理', '', '', '', '', '技术机构', null, null, 'technicalInstitution', '', null, '-2147483616', null, '26', '26');
INSERT INTO `web_app_menu` VALUES ('32', null, null, null, null, '', '', '', '', '我的工作', null, null, 'mywork', '', 'views/common/content.html', '-2147483645', null, null, null);
INSERT INTO `web_app_menu` VALUES ('33', null, null, null, '本菜单适用于技术机构，用于查询管理部门指定给其的器具并进行确认', '', '', '', '', '备案确认', null, null, 'InstrumentForcedTechnologyDepartmentIntegratedQuery', '', null, '-2147483638', null, '38', '38');
INSERT INTO `web_app_menu` VALUES ('34', null, null, null, null, '', '', '', '', '角色管理 -- 删除', null, null, 'RolefileDelete', '', null, '0', null, '44', '15');
INSERT INTO `web_app_menu` VALUES ('35', null, null, null, null, '', '', '', '', '用户管理 -- 增加', null, null, 'UserfileAdd', '', null, '0', null, '44', '64');
INSERT INTO `web_app_menu` VALUES ('36', null, null, null, '器具用户管理', '', '', '', '', '器具用户', null, null, 'instrumentUser', '', null, '-2147483617', null, '26', '26');
INSERT INTO `web_app_menu` VALUES ('37', null, null, null, '我的消息-已发送', '', '', '', '', '已发送', null, null, 'sent', '', 'views/myMessage/sent/index.html', '0', null, '45', '45');
INSERT INTO `web_app_menu` VALUES ('38', 'NEW', 'warning', null, null, '', '', '', '', '强检器具备案管理', null, null, 'InstrumentForced', '', null, '-2147483640', null, null, null);
INSERT INTO `web_app_menu` VALUES ('39', null, null, null, '适用于器具用户、管理部门查看申请工作的办理情况', '', '', '', '', '检定申请', null, null, 'InstrumentForcedTimeOutCheckApplyIndex', '', null, '-2147483633', null, '27', '27');
INSERT INTO `web_app_menu` VALUES ('40', null, null, null, '个人中心', '', '', '', '', '个人中心', null, null, 'Personal', '', null, '-2147483599', null, null, null);
INSERT INTO `web_app_menu` VALUES ('41', null, null, null, '我的工作-已办工作', '', '', '', '', '已办工作', null, null, 'Handled', '', 'views/mywork/handled/index.html', '-2147483642', null, '32', '32');
INSERT INTO `web_app_menu` VALUES ('42', null, null, null, '人员资质管理档案管理', '', '', '', '', '档案管理', null, null, 'PersonnelFile', '', null, '-2147483611', null, '17', '17');
INSERT INTO `web_app_menu` VALUES ('43', null, null, null, '技术机构 -- 标准装置管理', '', '', '', '', '档案管理', null, null, 'deviceSetManage', '', null, '-2147483622', null, '46', '46');
INSERT INTO `web_app_menu` VALUES ('44', null, null, null, null, '', '', '', '', '系统设置', null, null, 'system', '', null, '-2147483609', null, null, null);
INSERT INTO `web_app_menu` VALUES ('45', null, null, null, null, '', '', '', '', '我的消息', null, null, 'myMessage', '', 'views/common/content.html', '-2147483610', null, null, null);
INSERT INTO `web_app_menu` VALUES ('46', null, null, null, null, '', '', '', '', '标准装置管理', null, null, 'standard', '', null, '-2147483625', null, null, null);
INSERT INTO `web_app_menu` VALUES ('47', null, null, null, null, '', '', '', '', '备案申请', null, null, 'InstrumentForcedApply', '', null, '-2147483639', null, '38', '38');
INSERT INTO `web_app_menu` VALUES ('48', null, null, null, '我的工作-在办工作', '', '', '', '', '在办工作', null, null, 'Handling', '', 'views/mywork/handling/index.html', '-2147483643', null, '32', '32');
INSERT INTO `web_app_menu` VALUES ('49', null, null, null, null, '', '', '', '', '强检器具检定管理', null, null, 'mandatory', '', null, '-2147483631', null, null, null);
INSERT INTO `web_app_menu` VALUES ('50', null, null, null, null, '', '', '', '', '强检器具类别管理 -- 编辑', null, null, 'instrumentTypeEdit', '', null, '0', null, '44', '8');
INSERT INTO `web_app_menu` VALUES ('51', null, null, null, null, '', '', '', '', '角色管理 -- 详情', null, null, 'RolefileDetail', '', null, '0', null, '44', '15');
INSERT INTO `web_app_menu` VALUES ('52', null, null, null, '本菜单适用于管理部门', '', '', '', '', '综合查询', null, null, 'Instrument', '', 'views/mandatory/instrument/index.html', '-2147483637', null, '38', '38');
INSERT INTO `web_app_menu` VALUES ('53', null, null, null, '技术机构管理技术机构计量标准装置综合查询', '', '', '', '', '综合查询', null, null, 'File', '', null, '-2147483624', null, '46', '46');
INSERT INTO `web_app_menu` VALUES ('54', null, null, null, '标准器类别管理，每种器具都应该是器具类别的一种', '', '', '', '', '标准器类别管理', null, null, 'standardInstrumentType', '', null, '-2147483602', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('55', null, null, null, '管理部门', '', '', '', '', '管理部门', null, null, 'management', '', null, '-2147483614', null, '26', '26');
INSERT INTO `web_app_menu` VALUES ('56', null, null, null, '登录用户信息', '', '', '', '', '登录用户信息', null, null, 'PersonalInfoManage', '', null, '-2147483598', null, '40', '40');
INSERT INTO `web_app_menu` VALUES ('57', null, null, null, '我的工作-办结工作', '', '', '', '', '办结工作', null, null, 'Done', '', 'views/mywork/done/index.html', '-2147483641', null, '32', '32');
INSERT INTO `web_app_menu` VALUES ('58', null, null, null, '计量系统菜单管理', '', '', '', '', '菜单管理-新增菜单', null, null, 'MenuAdd', '', null, '0', null, '44', '29');
INSERT INTO `web_app_menu` VALUES ('59', null, null, null, '申请类型管理，每种申请对应一个申请类别', '', '', '', '', '申请类型管理', null, null, 'applyType', '', null, '-2147483601', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('60', null, null, null, '授权检定项目综合管理', '', '', '', '', '授权检定项目管理', null, null, 'FileDeviceInstrument', '', null, '-2147483620', null, '46', '46');
INSERT INTO `web_app_menu` VALUES ('61', null, null, null, '部门信息', '', '', '', '', '部门信息', null, null, 'PersonalDepartmentManage', '', null, '-2147483597', null, '40', '40');
INSERT INTO `web_app_menu` VALUES ('62', null, null, 'RoleRolefileAddCtrl', null, '', '', '', '', '角色管理 -- 新增', null, null, 'RolefileAdd', '', null, '0', null, '44', '15');
INSERT INTO `web_app_menu` VALUES ('63', null, null, null, null, '', '', '', '', '查看特殊字符', null, null, 'viewSpecialChar', '', null, '-2147483608', null, '44', '44');
INSERT INTO `web_app_menu` VALUES ('64', null, null, null, null, '', '', '', '', '用户管理', null, null, 'Userfile', '', null, '-2147483607', null, '44', '44');

-- ----------------------------
-- Table structure for `web_app_menu_role`
-- ----------------------------
DROP TABLE IF EXISTS `web_app_menu_role`;
CREATE TABLE `web_app_menu_role` (
  `role_id` bigint(20) NOT NULL,
  `web_app_menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`web_app_menu_id`),
  KEY `FK1vvevs1lh527hvfdydimsbom4` (`web_app_menu_id`),
  CONSTRAINT `FK1vvevs1lh527hvfdydimsbom4` FOREIGN KEY (`web_app_menu_id`) REFERENCES `web_app_menu` (`id`),
  CONSTRAINT `FKfdeerxlip4xkt1m2wp2anuw9o` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of web_app_menu_role
-- ----------------------------
INSERT INTO `web_app_menu_role` VALUES ('1', '1');
INSERT INTO `web_app_menu_role` VALUES ('1', '7');
INSERT INTO `web_app_menu_role` VALUES ('1', '11');
INSERT INTO `web_app_menu_role` VALUES ('1', '17');
INSERT INTO `web_app_menu_role` VALUES ('1', '20');
INSERT INTO `web_app_menu_role` VALUES ('1', '37');
INSERT INTO `web_app_menu_role` VALUES ('1', '40');
INSERT INTO `web_app_menu_role` VALUES ('1', '42');
INSERT INTO `web_app_menu_role` VALUES ('1', '43');
INSERT INTO `web_app_menu_role` VALUES ('1', '45');
INSERT INTO `web_app_menu_role` VALUES ('1', '46');
INSERT INTO `web_app_menu_role` VALUES ('1', '49');
INSERT INTO `web_app_menu_role` VALUES ('1', '56');
INSERT INTO `web_app_menu_role` VALUES ('1', '60');
INSERT INTO `web_app_menu_role` VALUES ('1', '61');
INSERT INTO `web_app_menu_role` VALUES ('2', '7');
INSERT INTO `web_app_menu_role` VALUES ('2', '9');
INSERT INTO `web_app_menu_role` VALUES ('2', '10');
INSERT INTO `web_app_menu_role` VALUES ('2', '11');
INSERT INTO `web_app_menu_role` VALUES ('2', '14');
INSERT INTO `web_app_menu_role` VALUES ('2', '21');
INSERT INTO `web_app_menu_role` VALUES ('2', '23');
INSERT INTO `web_app_menu_role` VALUES ('2', '27');
INSERT INTO `web_app_menu_role` VALUES ('2', '37');
INSERT INTO `web_app_menu_role` VALUES ('2', '38');
INSERT INTO `web_app_menu_role` VALUES ('2', '39');
INSERT INTO `web_app_menu_role` VALUES ('2', '40');
INSERT INTO `web_app_menu_role` VALUES ('2', '45');
INSERT INTO `web_app_menu_role` VALUES ('2', '47');
INSERT INTO `web_app_menu_role` VALUES ('2', '49');
INSERT INTO `web_app_menu_role` VALUES ('2', '56');
INSERT INTO `web_app_menu_role` VALUES ('2', '61');
INSERT INTO `web_app_menu_role` VALUES ('3', '7');
INSERT INTO `web_app_menu_role` VALUES ('3', '11');
INSERT INTO `web_app_menu_role` VALUES ('3', '13');
INSERT INTO `web_app_menu_role` VALUES ('3', '17');
INSERT INTO `web_app_menu_role` VALUES ('3', '22');
INSERT INTO `web_app_menu_role` VALUES ('3', '24');
INSERT INTO `web_app_menu_role` VALUES ('3', '37');
INSERT INTO `web_app_menu_role` VALUES ('3', '38');
INSERT INTO `web_app_menu_role` VALUES ('3', '40');
INSERT INTO `web_app_menu_role` VALUES ('3', '45');
INSERT INTO `web_app_menu_role` VALUES ('3', '46');
INSERT INTO `web_app_menu_role` VALUES ('3', '49');
INSERT INTO `web_app_menu_role` VALUES ('3', '52');
INSERT INTO `web_app_menu_role` VALUES ('3', '53');
INSERT INTO `web_app_menu_role` VALUES ('3', '56');
INSERT INTO `web_app_menu_role` VALUES ('3', '61');
INSERT INTO `web_app_menu_role` VALUES ('4', '1');
INSERT INTO `web_app_menu_role` VALUES ('4', '2');
INSERT INTO `web_app_menu_role` VALUES ('4', '3');
INSERT INTO `web_app_menu_role` VALUES ('4', '4');
INSERT INTO `web_app_menu_role` VALUES ('4', '5');
INSERT INTO `web_app_menu_role` VALUES ('4', '6');
INSERT INTO `web_app_menu_role` VALUES ('4', '7');
INSERT INTO `web_app_menu_role` VALUES ('4', '8');
INSERT INTO `web_app_menu_role` VALUES ('4', '9');
INSERT INTO `web_app_menu_role` VALUES ('4', '10');
INSERT INTO `web_app_menu_role` VALUES ('4', '11');
INSERT INTO `web_app_menu_role` VALUES ('4', '12');
INSERT INTO `web_app_menu_role` VALUES ('4', '13');
INSERT INTO `web_app_menu_role` VALUES ('4', '14');
INSERT INTO `web_app_menu_role` VALUES ('4', '15');
INSERT INTO `web_app_menu_role` VALUES ('4', '16');
INSERT INTO `web_app_menu_role` VALUES ('4', '17');
INSERT INTO `web_app_menu_role` VALUES ('4', '18');
INSERT INTO `web_app_menu_role` VALUES ('4', '19');
INSERT INTO `web_app_menu_role` VALUES ('4', '20');
INSERT INTO `web_app_menu_role` VALUES ('4', '21');
INSERT INTO `web_app_menu_role` VALUES ('4', '22');
INSERT INTO `web_app_menu_role` VALUES ('4', '23');
INSERT INTO `web_app_menu_role` VALUES ('4', '24');
INSERT INTO `web_app_menu_role` VALUES ('4', '25');
INSERT INTO `web_app_menu_role` VALUES ('4', '26');
INSERT INTO `web_app_menu_role` VALUES ('4', '27');
INSERT INTO `web_app_menu_role` VALUES ('4', '28');
INSERT INTO `web_app_menu_role` VALUES ('4', '29');
INSERT INTO `web_app_menu_role` VALUES ('4', '30');
INSERT INTO `web_app_menu_role` VALUES ('4', '31');
INSERT INTO `web_app_menu_role` VALUES ('4', '32');
INSERT INTO `web_app_menu_role` VALUES ('4', '33');
INSERT INTO `web_app_menu_role` VALUES ('4', '34');
INSERT INTO `web_app_menu_role` VALUES ('4', '35');
INSERT INTO `web_app_menu_role` VALUES ('4', '36');
INSERT INTO `web_app_menu_role` VALUES ('4', '37');
INSERT INTO `web_app_menu_role` VALUES ('4', '38');
INSERT INTO `web_app_menu_role` VALUES ('4', '39');
INSERT INTO `web_app_menu_role` VALUES ('4', '40');
INSERT INTO `web_app_menu_role` VALUES ('4', '41');
INSERT INTO `web_app_menu_role` VALUES ('4', '42');
INSERT INTO `web_app_menu_role` VALUES ('4', '43');
INSERT INTO `web_app_menu_role` VALUES ('4', '44');
INSERT INTO `web_app_menu_role` VALUES ('4', '45');
INSERT INTO `web_app_menu_role` VALUES ('4', '46');
INSERT INTO `web_app_menu_role` VALUES ('4', '47');
INSERT INTO `web_app_menu_role` VALUES ('4', '48');
INSERT INTO `web_app_menu_role` VALUES ('4', '49');
INSERT INTO `web_app_menu_role` VALUES ('4', '50');
INSERT INTO `web_app_menu_role` VALUES ('4', '51');
INSERT INTO `web_app_menu_role` VALUES ('4', '52');
INSERT INTO `web_app_menu_role` VALUES ('4', '53');
INSERT INTO `web_app_menu_role` VALUES ('4', '54');
INSERT INTO `web_app_menu_role` VALUES ('4', '55');
INSERT INTO `web_app_menu_role` VALUES ('4', '56');
INSERT INTO `web_app_menu_role` VALUES ('4', '57');
INSERT INTO `web_app_menu_role` VALUES ('4', '58');
INSERT INTO `web_app_menu_role` VALUES ('4', '59');
INSERT INTO `web_app_menu_role` VALUES ('4', '60');
INSERT INTO `web_app_menu_role` VALUES ('4', '61');
INSERT INTO `web_app_menu_role` VALUES ('4', '62');
INSERT INTO `web_app_menu_role` VALUES ('4', '63');
INSERT INTO `web_app_menu_role` VALUES ('4', '64');
INSERT INTO `web_app_menu_role` VALUES ('6', '3');
INSERT INTO `web_app_menu_role` VALUES ('6', '7');
INSERT INTO `web_app_menu_role` VALUES ('6', '11');
INSERT INTO `web_app_menu_role` VALUES ('6', '13');
INSERT INTO `web_app_menu_role` VALUES ('6', '17');
INSERT INTO `web_app_menu_role` VALUES ('6', '22');
INSERT INTO `web_app_menu_role` VALUES ('6', '24');
INSERT INTO `web_app_menu_role` VALUES ('6', '26');
INSERT INTO `web_app_menu_role` VALUES ('6', '27');
INSERT INTO `web_app_menu_role` VALUES ('6', '32');
INSERT INTO `web_app_menu_role` VALUES ('6', '36');
INSERT INTO `web_app_menu_role` VALUES ('6', '37');
INSERT INTO `web_app_menu_role` VALUES ('6', '38');
INSERT INTO `web_app_menu_role` VALUES ('6', '39');
INSERT INTO `web_app_menu_role` VALUES ('6', '40');
INSERT INTO `web_app_menu_role` VALUES ('6', '41');
INSERT INTO `web_app_menu_role` VALUES ('6', '45');
INSERT INTO `web_app_menu_role` VALUES ('6', '46');
INSERT INTO `web_app_menu_role` VALUES ('6', '47');
INSERT INTO `web_app_menu_role` VALUES ('6', '48');
INSERT INTO `web_app_menu_role` VALUES ('6', '49');
INSERT INTO `web_app_menu_role` VALUES ('6', '52');
INSERT INTO `web_app_menu_role` VALUES ('6', '53');
INSERT INTO `web_app_menu_role` VALUES ('6', '56');
INSERT INTO `web_app_menu_role` VALUES ('6', '57');
INSERT INTO `web_app_menu_role` VALUES ('6', '61');
INSERT INTO `web_app_menu_role` VALUES ('7', '1');
INSERT INTO `web_app_menu_role` VALUES ('7', '3');
INSERT INTO `web_app_menu_role` VALUES ('7', '6');
INSERT INTO `web_app_menu_role` VALUES ('7', '7');
INSERT INTO `web_app_menu_role` VALUES ('7', '11');
INSERT INTO `web_app_menu_role` VALUES ('7', '13');
INSERT INTO `web_app_menu_role` VALUES ('7', '17');
INSERT INTO `web_app_menu_role` VALUES ('7', '22');
INSERT INTO `web_app_menu_role` VALUES ('7', '24');
INSERT INTO `web_app_menu_role` VALUES ('7', '26');
INSERT INTO `web_app_menu_role` VALUES ('7', '31');
INSERT INTO `web_app_menu_role` VALUES ('7', '32');
INSERT INTO `web_app_menu_role` VALUES ('7', '37');
INSERT INTO `web_app_menu_role` VALUES ('7', '38');
INSERT INTO `web_app_menu_role` VALUES ('7', '40');
INSERT INTO `web_app_menu_role` VALUES ('7', '41');
INSERT INTO `web_app_menu_role` VALUES ('7', '45');
INSERT INTO `web_app_menu_role` VALUES ('7', '46');
INSERT INTO `web_app_menu_role` VALUES ('7', '48');
INSERT INTO `web_app_menu_role` VALUES ('7', '49');
INSERT INTO `web_app_menu_role` VALUES ('7', '52');
INSERT INTO `web_app_menu_role` VALUES ('7', '53');
INSERT INTO `web_app_menu_role` VALUES ('7', '55');
INSERT INTO `web_app_menu_role` VALUES ('7', '56');
INSERT INTO `web_app_menu_role` VALUES ('7', '57');
INSERT INTO `web_app_menu_role` VALUES ('7', '61');

-- ----------------------------
-- Table structure for `work`
-- ----------------------------
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `audit_type` varchar(255) DEFAULT NULL,
  `click_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `doing` bit(1) DEFAULT NULL,
  `done` bit(1) DEFAULT NULL,
  `opinion` varchar(255) DEFAULT NULL,
  `todo` bit(1) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `alias_work_id` bigint(20) DEFAULT NULL,
  `apply_id` bigint(20) DEFAULT NULL,
  `audit_department_id` bigint(20) DEFAULT NULL,
  `audit_user_id` bigint(20) DEFAULT NULL,
  `next_work_id` bigint(20) DEFAULT NULL,
  `pre_work_id` bigint(20) DEFAULT NULL,
  `workflow_node_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtmhkewexammgqd9v2ysjd0w7r` (`alias_work_id`),
  KEY `FK9tbr2obpiava27livmhvxmvv7` (`apply_id`),
  KEY `FKkwvqdkyxflpb3925u3hcuvwnp` (`audit_department_id`),
  KEY `FKbwthrs613a5elepinm28c3bvx` (`audit_user_id`),
  KEY `FKrsdfj151aqguw93adqwatvoy6` (`next_work_id`),
  KEY `FKfcn8mfc3ce55m8kf9gmiujqa6` (`pre_work_id`),
  KEY `FKi52gsjdfwg5enqd2kpmlriqrh` (`workflow_node_id`),
  CONSTRAINT `FK9tbr2obpiava27livmhvxmvv7` FOREIGN KEY (`apply_id`) REFERENCES `apply` (`id`),
  CONSTRAINT `FKbwthrs613a5elepinm28c3bvx` FOREIGN KEY (`audit_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfcn8mfc3ce55m8kf9gmiujqa6` FOREIGN KEY (`pre_work_id`) REFERENCES `work` (`id`),
  CONSTRAINT `FKi52gsjdfwg5enqd2kpmlriqrh` FOREIGN KEY (`workflow_node_id`) REFERENCES `workflow_node` (`id`),
  CONSTRAINT `FKkwvqdkyxflpb3925u3hcuvwnp` FOREIGN KEY (`audit_department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKrsdfj151aqguw93adqwatvoy6` FOREIGN KEY (`next_work_id`) REFERENCES `work` (`id`),
  CONSTRAINT `FKtmhkewexammgqd9v2ysjd0w7r` FOREIGN KEY (`alias_work_id`) REFERENCES `work` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=678 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of work
-- ----------------------------
INSERT INTO `work` VALUES ('1', 'sendToNextDepartment', null, '2017-12-15 09:41:18', '', '', null, '', '2017-12-15 09:42:48', null, '1', '12', '11', '2', null, '3');
INSERT INTO `work` VALUES ('2', 'done', null, '2017-12-15 09:42:48', '', '', null, '', '2017-12-15 09:46:46', null, '1', '9', '8', null, '1', '4');
INSERT INTO `work` VALUES ('3', 'sendToNextDepartment', null, '2017-12-16 14:40:02', '', '', null, '', '2017-12-16 14:40:45', null, '2', '12', '11', '4', null, '3');
INSERT INTO `work` VALUES ('4', 'done', null, '2017-12-16 14:40:45', '', '', null, '', '2017-12-16 17:40:08', null, '2', '9', '8', null, '3', '4');
INSERT INTO `work` VALUES ('5', 'sendToNextDepartment', null, '2017-12-16 14:44:58', '', '', null, '', '2017-12-16 14:45:05', null, '3', '1', '2', '6', null, '3');
INSERT INTO `work` VALUES ('6', null, null, '2017-12-16 14:45:05', '', '', null, '', '2017-12-16 14:45:05', null, '3', null, null, null, '5', '4');
INSERT INTO `work` VALUES ('7', 'sendToNextDepartment', null, '2017-12-16 14:45:52', '', '', null, '', '2017-12-16 14:46:38', null, '4', '12', '11', '8', null, '3');
INSERT INTO `work` VALUES ('8', 'done', null, '2017-12-16 14:46:38', '', '', null, '', '2017-12-16 15:28:18', null, '4', '9', '8', null, '7', '4');
INSERT INTO `work` VALUES ('9', 'sendToNextDepartment', null, '2017-12-16 15:06:25', '', '', null, '', '2017-12-17 15:38:16', null, '5', '12', '11', '12', null, '3');
INSERT INTO `work` VALUES ('10', 'sendToNextDepartment', '2017-12-16 17:47:40', '2017-12-16 17:47:40', '', '', '发起申请', '', '2017-12-16 17:47:40', null, '6', '12', '11', '11', null, '3');
INSERT INTO `work` VALUES ('11', null, '2017-12-16 17:47:40', '2017-12-16 17:47:40', '', '', null, '', '2017-12-16 17:49:18', null, '6', '9', '8', null, '10', '4');
INSERT INTO `work` VALUES ('12', 'done', null, '2017-12-17 15:38:16', '', '', null, '', '2017-12-17 15:39:37', null, '5', '9', '8', null, '9', '4');
INSERT INTO `work` VALUES ('13', 'sendToNextDepartment', null, '2017-12-17 16:41:51', '', '', null, '', '2017-12-17 16:41:59', null, '7', '12', '11', '14', null, '3');
INSERT INTO `work` VALUES ('14', 'done', null, '2017-12-17 16:41:59', '', '', null, '', '2017-12-18 16:00:33', null, '7', '9', '8', null, '13', '4');
INSERT INTO `work` VALUES ('15', 'sendToNextDepartment', '2017-12-17 16:51:12', '2017-12-17 16:51:12', '', '', '发起申请', '', '2017-12-17 16:51:12', null, '8', '12', '11', '16', null, '3');
INSERT INTO `work` VALUES ('16', null, '2017-12-17 16:51:12', '2017-12-17 16:51:12', '', '', null, '', '2017-12-17 16:52:16', null, '8', '9', '8', null, '15', '4');
INSERT INTO `work` VALUES ('17', 'sendToNextDepartment', null, '2017-12-17 21:10:16', '', '', '这里输入申请备案', '', '2017-12-17 21:11:32', null, '9', '12', '11', '18', null, '3');
INSERT INTO `work` VALUES ('18', 'done', null, '2017-12-17 21:11:32', '', '', null, '', '2017-12-18 15:36:17', null, '9', '9', '8', null, '17', '4');
INSERT INTO `work` VALUES ('19', 'sendToNextDepartment', '2017-12-17 21:43:03', '2017-12-17 21:43:03', '', '', '发起申请', '', '2017-12-17 21:43:03', null, '10', '12', '11', '20', null, '3');
INSERT INTO `work` VALUES ('20', 'done', '2017-12-17 21:43:03', '2017-12-17 21:43:03', '', '', null, '', '2017-12-18 15:32:57', null, '10', '9', '8', null, '19', '4');
INSERT INTO `work` VALUES ('21', 'sendToNextDepartment', null, '2017-12-17 22:43:22', '', '', null, '', '2017-12-17 22:45:24', null, '11', '12', '11', '22', null, '3');
INSERT INTO `work` VALUES ('22', 'done', null, '2017-12-17 22:45:24', '', '', null, '', '2017-12-17 22:46:43', null, '11', '9', '8', null, '21', '4');
INSERT INTO `work` VALUES ('23', 'sendToNextDepartment', null, '2017-12-18 09:12:01', '', '', null, '', '2017-12-18 09:14:25', null, '12', '12', '11', '24', null, '3');
INSERT INTO `work` VALUES ('24', 'done', null, '2017-12-18 09:14:25', '', '', null, '', '2017-12-18 09:17:11', null, '12', '9', '8', null, '23', '4');
INSERT INTO `work` VALUES ('25', 'sendToNextDepartment', null, '2017-12-18 15:18:37', '', '', null, '', '2017-12-18 15:20:56', null, '13', '12', '11', '26', null, '3');
INSERT INTO `work` VALUES ('26', 'done', null, '2017-12-18 15:20:56', '', '', null, '', '2017-12-18 15:28:50', null, '13', '9', '8', null, '25', '4');
INSERT INTO `work` VALUES ('27', 'sendToNextDepartment', '2017-12-18 15:44:28', '2017-12-18 15:44:28', '', '', '发起申请', '', '2017-12-18 15:44:28', null, '14', '12', '11', '28', null, '3');
INSERT INTO `work` VALUES ('28', null, '2017-12-18 15:44:28', '2017-12-18 15:44:28', '', '', null, '', '2017-12-18 15:47:01', null, '14', '9', '8', null, '27', '4');
INSERT INTO `work` VALUES ('29', 'sendToNextDepartment', null, '2017-12-18 16:12:36', '', '', null, '', '2017-12-18 16:19:16', null, '15', '35', '29', '30', null, '3');
INSERT INTO `work` VALUES ('30', 'done', null, '2017-12-18 16:19:16', '', '', null, '', '2017-12-18 16:19:56', null, '15', '21', '19', null, '29', '4');
INSERT INTO `work` VALUES ('31', 'sendToNextDepartment', null, '2017-12-18 16:21:18', '', '', null, '', '2017-12-18 16:21:33', null, '16', '40', '32', '32', null, '3');
INSERT INTO `work` VALUES ('32', 'done', null, '2017-12-18 16:21:33', '', '', null, '', '2017-12-18 16:21:58', null, '16', '25', '23', null, '31', '4');
INSERT INTO `work` VALUES ('33', 'sendToNextDepartment', null, '2017-12-18 16:22:25', '', '', null, '', '2017-12-18 16:23:11', null, '17', '42', '34', '34', null, '3');
INSERT INTO `work` VALUES ('34', 'done', null, '2017-12-18 16:23:11', '', '', null, '', '2017-12-18 16:26:47', null, '17', '38', '31', null, '33', '4');
INSERT INTO `work` VALUES ('35', 'sendToNextDepartment', null, '2017-12-18 16:24:41', '', '', null, '', '2017-12-18 16:27:14', null, '18', '37', '30', '36', null, '3');
INSERT INTO `work` VALUES ('36', 'done', null, '2017-12-18 16:27:14', '', '', null, '', '2017-12-18 16:30:03', null, '18', '22', '20', null, '35', '4');
INSERT INTO `work` VALUES ('37', 'sendToNextDepartment', null, '2017-12-18 16:29:15', '', '', null, '', '2017-12-18 16:29:46', null, '19', '35', '29', '38', null, '3');
INSERT INTO `work` VALUES ('38', 'done', null, '2017-12-18 16:29:46', '', '', null, '', '2017-12-18 16:30:23', null, '19', '21', '19', null, '37', '4');
INSERT INTO `work` VALUES ('39', 'sendToNextDepartment', null, '2017-12-18 16:33:29', '', '', null, '', '2017-12-18 16:33:37', null, '20', '40', '32', '40', null, '3');
INSERT INTO `work` VALUES ('40', 'done', null, '2017-12-18 16:33:37', '', '', null, '', '2017-12-18 16:35:06', null, '20', '25', '23', null, '39', '4');
INSERT INTO `work` VALUES ('41', 'sendToNextDepartment', null, '2017-12-18 16:37:58', '', '', null, '', '2017-12-18 16:38:05', null, '21', '42', '34', '42', null, '3');
INSERT INTO `work` VALUES ('42', 'done', null, '2017-12-18 16:38:05', '', '', null, '', '2017-12-18 16:38:49', null, '21', '38', '31', null, '41', '4');
INSERT INTO `work` VALUES ('43', 'sendToNextDepartment', null, '2017-12-18 21:10:39', '', '', null, '', '2017-12-18 21:11:04', null, '22', '48', '37', '44', null, '3');
INSERT INTO `work` VALUES ('44', 'done', null, '2017-12-18 21:11:04', '', '', null, '', '2017-12-18 21:11:41', null, '22', '23', '21', null, '43', '4');
INSERT INTO `work` VALUES ('45', 'sendToNextDepartment', null, '2017-12-18 21:17:57', '', '', null, '', '2017-12-18 21:18:12', null, '23', '48', '37', '46', null, '3');
INSERT INTO `work` VALUES ('46', 'done', null, '2017-12-18 21:18:12', '', '', null, '', '2017-12-18 21:18:54', null, '23', '23', '21', null, '45', '4');
INSERT INTO `work` VALUES ('47', 'sendToNextDepartment', null, '2017-12-19 08:52:55', '', '', 'fgfsgvdb', '', '2017-12-19 08:53:07', null, '24', '51', '39', '48', null, '3');
INSERT INTO `work` VALUES ('48', 'done', null, '2017-12-19 08:53:07', '', '', null, '', '2017-12-19 08:53:55', null, '24', '25', '23', null, '47', '4');
INSERT INTO `work` VALUES ('49', 'sendToNextDepartment', null, '2017-12-19 09:19:56', '', '', null, '', '2017-12-19 09:20:05', null, '25', '52', '40', '50', null, '3');
INSERT INTO `work` VALUES ('50', 'done', null, '2017-12-19 09:20:05', '', '', null, '', '2017-12-19 09:21:00', null, '25', '25', '23', null, '49', '4');
INSERT INTO `work` VALUES ('51', 'sendToNextDepartment', null, '2017-12-19 09:37:12', '', '', '赶紧给我鉴定', '', '2017-12-19 09:37:41', null, '26', '54', '42', '52', null, '3');
INSERT INTO `work` VALUES ('52', 'done', null, '2017-12-19 09:37:41', '', '', null, '', '2017-12-19 09:38:58', null, '26', '25', '23', null, '51', '4');
INSERT INTO `work` VALUES ('53', 'sendToNextDepartment', null, '2017-12-19 11:44:02', '', '', null, '', '2017-12-19 11:50:48', null, '27', '57', '44', '56', null, '3');
INSERT INTO `work` VALUES ('54', 'sendToNextDepartment', null, '2017-12-19 11:49:04', '', '', null, '', '2017-12-19 11:49:20', null, '28', '57', '44', '55', null, '3');
INSERT INTO `work` VALUES ('55', 'done', null, '2017-12-19 11:49:20', '', '', null, '', '2017-12-19 11:58:50', null, '28', '26', '24', null, '54', '4');
INSERT INTO `work` VALUES ('56', 'done', null, '2017-12-19 11:50:48', '', '', null, '', '2017-12-19 11:59:04', null, '27', '26', '24', null, '53', '4');
INSERT INTO `work` VALUES ('57', 'sendToNextDepartment', null, '2017-12-19 12:36:28', '', '', null, '', '2017-12-19 12:37:04', null, '29', '57', '44', '58', null, '3');
INSERT INTO `work` VALUES ('58', 'done', null, '2017-12-19 12:37:04', '', '', null, '', '2017-12-19 12:37:48', null, '29', '26', '24', null, '57', '4');
INSERT INTO `work` VALUES ('59', 'sendToNextDepartment', null, '2017-12-19 14:57:00', '', '', null, '', '2017-12-19 15:00:16', null, '30', '42', '34', '60', null, '3');
INSERT INTO `work` VALUES ('60', 'done', null, '2017-12-19 15:00:16', '', '', null, '', '2017-12-19 15:00:59', null, '30', '38', '31', null, '59', '4');
INSERT INTO `work` VALUES ('61', null, null, '2017-12-19 15:02:52', '', '', null, '', '2017-12-19 15:03:23', null, '31', '61', '48', null, null, '3');
INSERT INTO `work` VALUES ('62', 'sendToNextDepartment', null, '2017-12-19 15:06:27', '', '', null, '', '2017-12-19 15:06:33', null, '32', '42', '34', '63', null, '3');
INSERT INTO `work` VALUES ('63', 'done', null, '2017-12-19 15:06:33', '', '', null, '', '2017-12-19 15:07:17', null, '32', '38', '31', null, '62', '4');
INSERT INTO `work` VALUES ('64', 'sendToNextDepartment', null, '2017-12-19 15:18:33', '', '', null, '', '2017-12-19 15:19:03', null, '33', '42', '34', '65', null, '3');
INSERT INTO `work` VALUES ('65', 'done', null, '2017-12-19 15:19:03', '', '', null, '', '2017-12-19 15:19:37', null, '33', '38', '31', null, '64', '4');
INSERT INTO `work` VALUES ('66', 'sendToNextDepartment', null, '2017-12-19 15:37:47', '', '', null, '', '2017-12-19 15:38:08', null, '34', '42', '34', '67', null, '3');
INSERT INTO `work` VALUES ('67', 'done', null, '2017-12-19 15:38:08', '', '', null, '', '2017-12-19 15:38:59', null, '34', '38', '31', null, '66', '4');
INSERT INTO `work` VALUES ('68', 'sendToNextDepartment', '2017-12-20 10:45:47', '2017-12-20 10:45:47', '', '', '发起申请', '', '2017-12-20 10:45:47', null, '35', '12', '11', '69', null, '3');
INSERT INTO `work` VALUES ('69', null, '2017-12-20 10:45:47', '2017-12-20 10:45:47', '', '', null, '', '2017-12-20 10:46:55', null, '35', '9', '8', null, '68', '4');
INSERT INTO `work` VALUES ('70', 'sendToNextDepartment', null, '2017-12-21 11:05:02', '', '', '申请检定。', '', '2017-12-21 11:05:21', null, '36', '70', '56', '71', null, '3');
INSERT INTO `work` VALUES ('71', 'done', null, '2017-12-21 11:05:21', '', '', '予以备案', '', '2017-12-21 11:07:03', null, '36', '24', '22', null, '70', '4');
INSERT INTO `work` VALUES ('72', null, null, '2017-12-21 14:30:42', '', '', null, '', '2017-12-21 14:30:45', null, '37', '24', '22', null, null, null);
INSERT INTO `work` VALUES ('73', 'sendToNextDepartment', null, '2017-12-21 14:53:45', '', '', '申请检定。', '', '2017-12-21 14:58:30', null, '38', '72', '57', '74', null, '3');
INSERT INTO `work` VALUES ('74', 'done', null, '2017-12-21 14:58:30', '', '', '予以备案，交县质计所检定。', '', '2017-12-21 15:02:13', null, '38', '24', '22', null, '73', '4');
INSERT INTO `work` VALUES ('75', 'sendToNextDepartment', null, '2017-12-21 16:35:17', '', '', null, '', '2017-12-21 16:35:42', null, '39', '76', '59', '76', null, '3');
INSERT INTO `work` VALUES ('76', 'done', null, '2017-12-21 16:35:42', '', '', null, '', '2017-12-21 16:37:37', null, '39', '27', '25', null, '75', '4');
INSERT INTO `work` VALUES ('77', 'sendToNextDepartment', null, '2017-12-22 09:42:50', '', '', null, '', '2017-12-22 09:44:29', null, '40', '81', '63', '78', null, '3');
INSERT INTO `work` VALUES ('78', 'done', null, '2017-12-22 09:44:29', '', '', null, '', '2017-12-22 09:47:49', null, '40', '78', '60', null, '77', '4');
INSERT INTO `work` VALUES ('79', 'sendToNextDepartment', null, '2017-12-22 09:54:26', '', '', '申请备案', '', '2017-12-22 09:54:45', null, '41', '72', '57', '80', null, '3');
INSERT INTO `work` VALUES ('80', null, null, '2017-12-22 09:54:45', '', '', null, '', '2017-12-22 09:56:23', null, '41', '24', '22', null, '79', '4');
INSERT INTO `work` VALUES ('81', null, null, '2017-12-22 10:37:49', '', '', null, '', '2017-12-22 10:37:54', null, '42', '83', '64', null, null, '3');
INSERT INTO `work` VALUES ('82', null, null, '2017-12-22 10:39:44', '', '', null, '', '2017-12-22 10:51:10', null, '43', '83', '64', null, null, '3');
INSERT INTO `work` VALUES ('83', null, null, '2017-12-22 10:55:43', '', '', null, '', '2017-12-22 11:07:10', null, '44', '83', '64', null, null, '3');
INSERT INTO `work` VALUES ('84', 'sendToNextDepartment', null, '2017-12-23 08:18:50', '', '', null, '', '2017-12-23 08:29:37', null, '45', '84', '65', '87', null, '3');
INSERT INTO `work` VALUES ('85', 'sendToNextDepartment', null, '2017-12-23 08:27:13', '', '', null, '', '2017-12-23 08:28:24', null, '46', '84', '65', '86', null, '3');
INSERT INTO `work` VALUES ('86', 'done', null, '2017-12-23 08:28:24', '', '', null, '', '2018-01-11 10:32:32', null, '46', '79', '121', null, '85', '4');
INSERT INTO `work` VALUES ('87', 'done', null, '2017-12-23 08:29:37', '', '', null, '', '2018-01-11 10:31:53', null, '45', '79', '121', null, '84', '4');
INSERT INTO `work` VALUES ('88', 'sendToNextDepartment', null, '2017-12-23 08:32:10', '', '', null, '', '2017-12-23 08:33:03', null, '47', '84', '65', '89', null, '3');
INSERT INTO `work` VALUES ('89', 'done', null, '2017-12-23 08:33:03', '', '', null, '', '2018-01-11 10:31:24', null, '47', '79', '121', null, '88', '4');
INSERT INTO `work` VALUES ('90', 'sendToNextDepartment', null, '2017-12-23 08:39:50', '', '', null, '', '2017-12-23 08:40:11', null, '48', '84', '65', '91', null, '3');
INSERT INTO `work` VALUES ('91', 'done', null, '2017-12-23 08:40:11', '', '', null, '', '2018-01-11 10:31:12', null, '48', '79', '121', null, '90', '4');
INSERT INTO `work` VALUES ('92', 'sendToNextDepartment', null, '2017-12-23 08:42:47', '', '', null, '', '2017-12-23 08:43:01', null, '49', '84', '65', '93', null, '3');
INSERT INTO `work` VALUES ('93', 'done', null, '2017-12-23 08:43:01', '', '', null, '', '2018-01-11 10:30:56', null, '49', '79', '121', null, '92', '4');
INSERT INTO `work` VALUES ('94', 'sendToNextDepartment', null, '2017-12-23 08:45:24', '', '', null, '', '2017-12-23 08:45:46', null, '50', '84', '65', '95', null, '3');
INSERT INTO `work` VALUES ('95', 'done', null, '2017-12-23 08:45:46', '', '', null, '', '2018-01-11 10:30:38', null, '50', '79', '121', null, '94', '4');
INSERT INTO `work` VALUES ('96', 'sendToNextDepartment', null, '2017-12-23 08:47:17', '', '', null, '', '2017-12-23 08:47:25', null, '51', '84', '65', '97', null, '3');
INSERT INTO `work` VALUES ('97', 'done', null, '2017-12-23 08:47:25', '', '', null, '', '2018-01-11 10:30:20', null, '51', '79', '121', null, '96', '4');
INSERT INTO `work` VALUES ('98', 'sendToNextDepartment', null, '2017-12-23 08:50:04', '', '', null, '', '2017-12-23 08:50:43', null, '52', '84', '65', '99', null, '3');
INSERT INTO `work` VALUES ('99', 'done', null, '2017-12-23 08:50:43', '', '', null, '', '2018-01-11 10:34:17', null, '52', '79', '121', null, '98', '4');
INSERT INTO `work` VALUES ('100', 'sendToNextDepartment', null, '2017-12-23 08:52:48', '', '', null, '', '2017-12-23 08:52:59', null, '53', '84', '65', '101', null, '3');
INSERT INTO `work` VALUES ('101', 'done', null, '2017-12-23 08:52:59', '', '', null, '', '2018-01-11 10:33:57', null, '53', '79', '121', null, '100', '4');
INSERT INTO `work` VALUES ('102', 'sendToNextDepartment', null, '2017-12-23 08:59:37', '', '', null, '', '2017-12-23 08:59:50', null, '54', '84', '65', '103', null, '3');
INSERT INTO `work` VALUES ('103', 'done', null, '2017-12-23 08:59:50', '', '', null, '', '2018-01-11 10:33:42', null, '54', '79', '121', null, '102', '4');
INSERT INTO `work` VALUES ('104', 'sendToNextDepartment', null, '2017-12-23 09:01:40', '', '', null, '', '2017-12-23 09:01:49', null, '55', '84', '65', '105', null, '3');
INSERT INTO `work` VALUES ('105', 'done', null, '2017-12-23 09:01:49', '', '', null, '', '2018-01-11 10:33:24', null, '55', '79', '121', null, '104', '4');
INSERT INTO `work` VALUES ('106', 'sendToNextDepartment', null, '2017-12-25 08:51:56', '', '', null, '', '2017-12-25 14:27:06', null, '56', '101', '67', '113', null, '3');
INSERT INTO `work` VALUES ('107', 'sendToNextDepartment', null, '2017-12-25 14:13:35', '', '', null, '', '2017-12-25 14:26:58', null, '57', '101', '67', '112', null, '3');
INSERT INTO `work` VALUES ('108', 'sendToNextDepartment', null, '2017-12-25 14:19:46', '', '', null, '', '2017-12-25 14:26:47', null, '58', '101', '67', '111', null, '3');
INSERT INTO `work` VALUES ('109', 'sendToNextDepartment', null, '2017-12-25 14:22:29', '', '', null, '', '2017-12-25 14:26:29', null, '59', '101', '67', '110', null, '3');
INSERT INTO `work` VALUES ('110', 'done', null, '2017-12-25 14:26:29', '', '', null, '', '2018-01-10 14:51:23', null, '59', '78', '60', null, '109', '4');
INSERT INTO `work` VALUES ('111', 'done', null, '2017-12-25 14:26:47', '', '', null, '', '2018-01-10 14:51:04', null, '58', '78', '60', null, '108', '4');
INSERT INTO `work` VALUES ('112', 'done', null, '2017-12-25 14:26:58', '', '', null, '', '2018-01-10 14:50:47', null, '57', '78', '60', null, '107', '4');
INSERT INTO `work` VALUES ('113', 'done', null, '2017-12-25 14:27:06', '', '', null, '', '2017-12-26 11:07:04', null, '56', '78', '60', null, '106', '4');
INSERT INTO `work` VALUES ('114', 'sendToNextDepartment', null, '2017-12-25 15:34:37', '', '', null, '', '2017-12-25 15:36:09', null, '60', '105', '71', '115', null, '3');
INSERT INTO `work` VALUES ('115', 'done', null, '2017-12-25 15:36:09', '', '', null, '', '2017-12-29 10:47:48', null, '60', '78', '60', null, '114', '4');
INSERT INTO `work` VALUES ('116', 'sendToNextDepartment', null, '2017-12-25 15:40:50', '', '', null, '', '2017-12-25 15:41:29', null, '61', '105', '71', '117', null, '3');
INSERT INTO `work` VALUES ('117', 'done', null, '2017-12-25 15:41:29', '', '', null, '', '2017-12-29 10:47:29', null, '61', '78', '60', null, '116', '4');
INSERT INTO `work` VALUES ('118', 'sendToNextDepartment', null, '2017-12-25 15:45:58', '', '', null, '', '2017-12-25 15:46:17', null, '62', '105', '71', '119', null, '3');
INSERT INTO `work` VALUES ('119', 'done', null, '2017-12-25 15:46:17', '', '', null, '', '2017-12-29 10:47:12', null, '62', '78', '60', null, '118', '4');
INSERT INTO `work` VALUES ('120', 'sendToNextDepartment', null, '2017-12-25 15:49:30', '', '', null, '', '2017-12-25 15:50:03', null, '63', '105', '71', '121', null, '3');
INSERT INTO `work` VALUES ('121', 'done', null, '2017-12-25 15:50:02', '', '', null, '', '2017-12-29 10:46:53', null, '63', '78', '60', null, '120', '4');
INSERT INTO `work` VALUES ('122', 'sendToNextDepartment', null, '2017-12-25 16:03:52', '', '', null, '', '2017-12-25 16:04:26', null, '64', '105', '71', '123', null, '3');
INSERT INTO `work` VALUES ('123', 'done', null, '2017-12-25 16:04:26', '', '', null, '', '2017-12-29 10:46:30', null, '64', '78', '60', null, '122', '4');
INSERT INTO `work` VALUES ('124', 'sendToNextDepartment', null, '2017-12-25 16:06:40', '', '', null, '', '2017-12-25 16:07:31', null, '65', '105', '71', '125', null, '3');
INSERT INTO `work` VALUES ('125', 'done', null, '2017-12-25 16:07:31', '', '', null, '', '2017-12-29 10:46:01', null, '65', '78', '60', null, '124', '4');
INSERT INTO `work` VALUES ('126', 'sendToNextDepartment', null, '2017-12-26 09:51:09', '', '', null, '', '2017-12-26 09:51:25', null, '66', '108', '74', '127', null, '3');
INSERT INTO `work` VALUES ('127', 'done', null, '2017-12-26 09:51:25', '', '', null, '', '2017-12-26 11:04:38', null, '66', '78', '60', null, '126', '4');
INSERT INTO `work` VALUES ('128', 'sendToNextDepartment', null, '2017-12-26 10:02:52', '', '', null, '', '2017-12-26 10:03:10', null, '67', '108', '74', '129', null, '3');
INSERT INTO `work` VALUES ('129', 'done', null, '2017-12-26 10:03:10', '', '', null, '', '2017-12-29 10:45:46', null, '67', '78', '60', null, '128', '4');
INSERT INTO `work` VALUES ('130', 'sendToNextDepartment', null, '2017-12-26 10:05:34', '', '', null, '', '2017-12-26 10:36:19', null, '68', '128', '85', '144', null, '3');
INSERT INTO `work` VALUES ('131', 'sendToNextDepartment', null, '2017-12-26 10:09:15', '', '', null, '', '2017-12-26 10:36:55', null, '69', '128', '85', '145', null, '3');
INSERT INTO `work` VALUES ('132', 'sendToNextDepartment', null, '2017-12-26 10:11:30', '', '', null, '', '2017-12-26 10:40:07', null, '70', '128', '85', '146', null, '3');
INSERT INTO `work` VALUES ('133', 'sendToNextDepartment', null, '2017-12-26 10:15:40', '', '', null, '', '2017-12-26 10:15:47', null, '71', '108', '74', '134', null, '3');
INSERT INTO `work` VALUES ('134', 'done', null, '2017-12-26 10:15:47', '', '', null, '', '2017-12-29 10:45:29', null, '71', '78', '60', null, '133', '4');
INSERT INTO `work` VALUES ('135', 'sendToNextDepartment', null, '2017-12-26 10:19:42', '', '', null, '', '2017-12-26 10:19:51', null, '72', '108', '74', '137', null, '3');
INSERT INTO `work` VALUES ('136', 'sendToNextDepartment', null, '2017-12-26 10:19:48', '', '', null, '', '2017-12-26 10:45:40', null, '73', '128', '85', '151', null, '3');
INSERT INTO `work` VALUES ('137', 'done', null, '2017-12-26 10:19:51', '', '', null, '', '2017-12-29 10:45:12', null, '72', '78', '60', null, '135', '4');
INSERT INTO `work` VALUES ('138', 'sendToNextDepartment', null, '2017-12-26 10:20:21', '', '', null, '', '2017-12-26 10:27:08', null, '74', '137', '94', '139', null, '3');
INSERT INTO `work` VALUES ('139', 'done', null, '2017-12-26 10:27:08', '', '', null, '', '2018-01-10 14:54:23', null, '74', '78', '60', null, '138', '4');
INSERT INTO `work` VALUES ('140', 'sendToNextDepartment', null, '2017-12-26 10:32:17', '', '', null, '', '2017-12-26 10:32:23', null, '75', '108', '74', '141', null, '3');
INSERT INTO `work` VALUES ('141', 'done', null, '2017-12-26 10:32:23', '', '', null, '', '2017-12-29 10:44:29', null, '75', '78', '60', null, '140', '4');
INSERT INTO `work` VALUES ('142', 'sendToNextDepartment', null, '2017-12-26 10:35:10', '', '', null, '', '2017-12-26 10:35:15', null, '76', '108', '74', '143', null, '3');
INSERT INTO `work` VALUES ('143', 'done', null, '2017-12-26 10:35:15', '', '', null, '', '2017-12-29 10:44:01', null, '76', '78', '60', null, '142', '4');
INSERT INTO `work` VALUES ('144', 'done', null, '2017-12-26 10:36:19', '', '', null, '', '2017-12-29 10:43:41', null, '68', '78', '60', null, '130', '4');
INSERT INTO `work` VALUES ('145', 'done', null, '2017-12-26 10:36:55', '', '', null, '', '2017-12-29 10:43:25', null, '69', '78', '60', null, '131', '4');
INSERT INTO `work` VALUES ('146', 'done', null, '2017-12-26 10:40:07', '', '', null, '', '2017-12-29 10:43:04', null, '70', '78', '60', null, '132', '4');
INSERT INTO `work` VALUES ('147', 'sendToNextDepartment', null, '2017-12-26 10:40:22', '', '', null, '', '2017-12-26 10:43:39', null, '77', '138', '95', '148', null, '3');
INSERT INTO `work` VALUES ('148', 'done', null, '2017-12-26 10:43:39', '', '', null, '', '2018-01-10 14:54:14', null, '77', '78', '60', null, '147', '4');
INSERT INTO `work` VALUES ('149', 'sendToNextDepartment', null, '2017-12-26 10:45:03', '', '', null, '', '2017-12-26 10:45:09', null, '78', '108', '74', '150', null, '3');
INSERT INTO `work` VALUES ('150', 'done', null, '2017-12-26 10:45:09', '', '', null, '', '2017-12-29 10:37:06', null, '78', '78', '60', null, '149', '4');
INSERT INTO `work` VALUES ('151', 'done', null, '2017-12-26 10:45:40', '', '', null, '', '2017-12-29 10:29:09', null, '73', '78', '60', null, '136', '4');
INSERT INTO `work` VALUES ('152', 'sendToNextDepartment', null, '2017-12-26 10:47:59', '', '', null, '', '2017-12-26 10:48:06', null, '79', '108', '74', '153', null, '3');
INSERT INTO `work` VALUES ('153', 'done', null, '2017-12-26 10:48:06', '', '', null, '', '2017-12-26 11:03:37', null, '79', '78', '60', null, '152', '4');
INSERT INTO `work` VALUES ('154', 'sendToNextDepartment', null, '2017-12-26 10:54:39', '', '', null, '', '2017-12-26 11:17:11', null, '80', '136', '93', '157', null, '3');
INSERT INTO `work` VALUES ('155', 'sendToNextDepartment', null, '2017-12-26 11:15:25', '', '', null, '', '2017-12-26 11:16:50', null, '81', '136', '93', '156', null, '3');
INSERT INTO `work` VALUES ('156', 'done', null, '2017-12-26 11:16:50', '', '', null, '', '2018-01-09 08:57:11', null, '81', '78', '60', null, '155', '4');
INSERT INTO `work` VALUES ('157', 'done', null, '2017-12-26 11:17:11', '', '', null, '', '2018-01-09 08:56:53', null, '80', '78', '60', null, '154', '4');
INSERT INTO `work` VALUES ('158', null, null, '2017-12-26 11:25:53', '', '', null, '', '2017-12-26 11:27:49', null, '82', '177', '126', null, null, '3');
INSERT INTO `work` VALUES ('159', 'sendToNextDepartment', null, '2017-12-26 14:41:47', '', '', null, '', '2017-12-26 14:51:16', null, '83', '113', '79', '166', null, '3');
INSERT INTO `work` VALUES ('160', 'sendToNextDepartment', null, '2017-12-26 14:44:22', '', '', null, '', '2017-12-26 14:51:06', null, '84', '113', '79', '165', null, '3');
INSERT INTO `work` VALUES ('161', 'sendToNextDepartment', null, '2017-12-26 14:46:16', '', '', null, '', '2017-12-26 14:50:59', null, '85', '113', '79', '164', null, '3');
INSERT INTO `work` VALUES ('162', 'sendToNextDepartment', null, '2017-12-26 14:48:06', '', '', null, '', '2017-12-26 14:50:47', null, '86', '113', '79', '163', null, '3');
INSERT INTO `work` VALUES ('163', 'done', null, '2017-12-26 14:50:47', '', '', null, '', '2017-12-29 10:26:14', null, '86', '78', '60', null, '162', '4');
INSERT INTO `work` VALUES ('164', 'done', null, '2017-12-26 14:50:59', '', '', null, '', '2018-01-10 14:48:06', null, '85', '78', '60', null, '161', '4');
INSERT INTO `work` VALUES ('165', 'done', null, '2017-12-26 14:51:06', '', '', null, '', '2017-12-26 14:56:05', null, '84', '78', '60', null, '160', '4');
INSERT INTO `work` VALUES ('166', 'done', null, '2017-12-26 14:51:16', '', '', null, '', '2017-12-26 14:53:47', null, '83', '78', '60', null, '159', '4');
INSERT INTO `work` VALUES ('167', null, null, '2017-12-26 15:11:07', '', '', null, '', '2017-12-26 15:11:07', null, '87', '129', null, null, null, '3');
INSERT INTO `work` VALUES ('168', 'sendToNextDepartment', null, '2017-12-26 15:40:27', '', '', null, '', '2017-12-26 15:42:21', null, '88', '186', '131', '169', null, '3');
INSERT INTO `work` VALUES ('169', 'done', null, '2017-12-26 15:42:21', '', '', null, '', '2018-01-10 14:54:04', null, '88', '78', '60', null, '168', '4');
INSERT INTO `work` VALUES ('170', 'sendToNextDepartment', null, '2017-12-26 15:47:53', '', '', null, '', '2017-12-26 17:22:02', null, '89', '134', '91', '183', null, '3');
INSERT INTO `work` VALUES ('171', null, null, '2017-12-26 15:57:08', '', '', null, '', '2017-12-26 15:57:29', null, '90', '106', '72', null, null, '3');
INSERT INTO `work` VALUES ('172', 'sendToNextDepartment', null, '2017-12-26 16:11:52', '', '', null, '', '2017-12-26 16:12:03', null, '91', '194', '135', '173', null, '3');
INSERT INTO `work` VALUES ('173', 'done', null, '2017-12-26 16:12:03', '', '', null, '', '2017-12-29 10:25:41', null, '91', '78', '60', null, '172', '4');
INSERT INTO `work` VALUES ('174', 'sendToNextDepartment', null, '2017-12-26 16:20:00', '', '', null, '', '2017-12-26 16:20:24', null, '92', '194', '135', '175', null, '3');
INSERT INTO `work` VALUES ('175', 'done', null, '2017-12-26 16:20:24', '', '', null, '', '2018-01-10 14:53:54', null, '92', '78', '60', null, '174', '4');
INSERT INTO `work` VALUES ('176', 'sendToNextDepartment', null, '2017-12-26 16:38:48', '', '', null, '', '2017-12-26 16:41:00', null, '93', '214', '151', '177', null, '3');
INSERT INTO `work` VALUES ('177', 'done', null, '2017-12-26 16:41:00', '', '', null, '', '2017-12-29 10:25:13', null, '93', '78', '60', null, '176', '4');
INSERT INTO `work` VALUES ('178', null, null, '2017-12-26 17:02:50', '', '', null, '', '2017-12-27 16:08:34', null, '94', '112', '78', null, null, '3');
INSERT INTO `work` VALUES ('179', 'sendToNextDepartment', null, '2017-12-26 17:15:56', '', '', null, '', '2017-12-26 17:21:52', null, '95', '134', '91', '182', null, '3');
INSERT INTO `work` VALUES ('180', 'sendToNextDepartment', null, '2017-12-26 17:19:52', '', '', null, '', '2017-12-26 17:21:40', null, '96', '134', '91', '181', null, '3');
INSERT INTO `work` VALUES ('181', 'done', null, '2017-12-26 17:21:40', '', '', '予以备案', '', '2017-12-27 10:28:43', null, '96', '24', '22', null, '180', '4');
INSERT INTO `work` VALUES ('182', 'done', null, '2017-12-26 17:21:52', '', '', null, '', '2017-12-27 10:27:42', null, '95', '24', '22', null, '179', '4');
INSERT INTO `work` VALUES ('183', 'done', null, '2017-12-26 17:22:02', '', '', '予以备案', '', '2017-12-27 09:28:07', null, '89', '24', '22', null, '170', '4');
INSERT INTO `work` VALUES ('184', null, null, '2017-12-27 09:33:28', '', '', null, '', '2017-12-27 09:33:30', null, '97', '131', '88', null, null, '3');
INSERT INTO `work` VALUES ('185', 'sendToNextDepartment', null, '2017-12-27 09:35:57', '', '', null, '', '2017-12-27 09:52:48', null, '98', '131', '88', '186', null, '3');
INSERT INTO `work` VALUES ('186', 'done', null, '2017-12-27 09:52:48', '', '', null, '', '2017-12-29 10:24:44', null, '98', '78', '60', null, '185', '4');
INSERT INTO `work` VALUES ('187', 'sendToNextDepartment', null, '2017-12-27 10:08:08', '', '', null, '', '2017-12-27 10:34:18', null, '99', '209', '146', '190', null, '3');
INSERT INTO `work` VALUES ('188', 'sendToNextDepartment', null, '2017-12-27 10:14:48', '', '', null, '', '2017-12-27 10:34:32', null, '100', '209', '146', '191', null, '3');
INSERT INTO `work` VALUES ('189', 'sendToNextDepartment', null, '2017-12-27 10:21:17', '', '', null, '', '2017-12-27 10:34:53', null, '101', '209', '146', '192', null, '3');
INSERT INTO `work` VALUES ('190', 'done', null, '2017-12-27 10:34:18', '', '', null, '', '2017-12-29 10:22:06', null, '99', '78', '60', null, '187', '4');
INSERT INTO `work` VALUES ('191', 'done', null, '2017-12-27 10:34:32', '', '', null, '', '2017-12-29 10:17:47', null, '100', '78', '60', null, '188', '4');
INSERT INTO `work` VALUES ('192', 'done', null, '2017-12-27 10:34:53', '', '', null, '', '2017-12-29 10:17:22', null, '101', '78', '60', null, '189', '4');
INSERT INTO `work` VALUES ('193', 'sendToNextDepartment', null, '2017-12-27 13:37:39', '', '', null, '', '2017-12-27 13:42:38', null, '102', '110', '76', '198', null, '3');
INSERT INTO `work` VALUES ('194', 'sendToNextDepartment', null, '2017-12-27 13:39:50', '', '', null, '', '2017-12-27 13:42:28', null, '103', '110', '76', '197', null, '3');
INSERT INTO `work` VALUES ('195', 'sendToNextDepartment', null, '2017-12-27 13:42:00', '', '', null, '', '2017-12-27 13:42:14', null, '104', '110', '76', '196', null, '3');
INSERT INTO `work` VALUES ('196', 'done', null, '2017-12-27 13:42:14', '', '', null, '', '2017-12-29 10:16:25', null, '104', '78', '60', null, '195', '4');
INSERT INTO `work` VALUES ('197', 'done', null, '2017-12-27 13:42:28', '', '', null, '', '2017-12-29 10:16:03', null, '103', '78', '60', null, '194', '4');
INSERT INTO `work` VALUES ('198', 'done', null, '2017-12-27 13:42:38', '', '', null, '', '2017-12-29 10:15:07', null, '102', '78', '60', null, '193', '4');
INSERT INTO `work` VALUES ('199', 'sendToNextDepartment', null, '2017-12-27 13:43:46', '', '', null, '', '2017-12-27 13:43:52', null, '105', '110', '76', '200', null, '3');
INSERT INTO `work` VALUES ('200', 'done', null, '2017-12-27 13:43:52', '', '', null, '', '2017-12-29 10:14:40', null, '105', '78', '60', null, '199', '4');
INSERT INTO `work` VALUES ('201', 'sendToNextDepartment', null, '2017-12-27 13:45:08', '', '', null, '', '2017-12-27 13:45:14', null, '106', '110', '76', '202', null, '3');
INSERT INTO `work` VALUES ('202', 'done', null, '2017-12-27 13:45:14', '', '', null, '', '2017-12-29 10:14:14', null, '106', '78', '60', null, '201', '4');
INSERT INTO `work` VALUES ('203', 'sendToNextDepartment', null, '2017-12-27 13:48:51', '', '', null, '', '2017-12-27 13:49:19', null, '107', '110', '76', '204', null, '3');
INSERT INTO `work` VALUES ('204', 'done', null, '2017-12-27 13:49:19', '', '', null, '', '2017-12-29 10:13:43', null, '107', '78', '60', null, '203', '4');
INSERT INTO `work` VALUES ('205', 'sendToNextDepartment', null, '2017-12-27 13:50:43', '', '', null, '', '2017-12-27 13:50:49', null, '108', '110', '76', '206', null, '3');
INSERT INTO `work` VALUES ('206', 'done', null, '2017-12-27 13:50:49', '', '', null, '', '2017-12-29 10:13:21', null, '108', '78', '60', null, '205', '4');
INSERT INTO `work` VALUES ('207', 'sendToNextDepartment', null, '2017-12-27 13:51:56', '', '', null, '', '2017-12-27 13:52:03', null, '109', '110', '76', '208', null, '3');
INSERT INTO `work` VALUES ('208', 'done', null, '2017-12-27 13:52:03', '', '', null, '', '2017-12-29 10:12:56', null, '109', '78', '60', null, '207', '4');
INSERT INTO `work` VALUES ('209', 'sendToNextDepartment', null, '2017-12-27 13:53:29', '', '', null, '', '2017-12-27 13:53:36', null, '110', '110', '76', '210', null, '3');
INSERT INTO `work` VALUES ('210', 'done', null, '2017-12-27 13:53:36', '', '', null, '', '2017-12-29 10:12:30', null, '110', '78', '60', null, '209', '4');
INSERT INTO `work` VALUES ('211', 'sendToNextDepartment', null, '2017-12-27 13:54:53', '', '', null, '', '2017-12-27 13:55:42', null, '111', '110', '76', '212', null, '3');
INSERT INTO `work` VALUES ('212', 'done', null, '2017-12-27 13:55:42', '', '', null, '', '2017-12-29 10:12:09', null, '111', '78', '60', null, '211', '4');
INSERT INTO `work` VALUES ('213', 'sendToNextDepartment', null, '2017-12-27 13:58:34', '', '', null, '', '2017-12-27 13:58:38', null, '112', '110', '76', '214', null, '3');
INSERT INTO `work` VALUES ('214', 'done', null, '2017-12-27 13:58:38', '', '', null, '', '2017-12-29 10:11:42', null, '112', '78', '60', null, '213', '4');
INSERT INTO `work` VALUES ('215', 'sendToNextDepartment', null, '2017-12-27 15:34:19', '', '', null, '', '2017-12-27 15:39:55', null, '113', '307', '231', '216', null, '3');
INSERT INTO `work` VALUES ('216', 'done', null, '2017-12-27 15:39:55', '', '', null, '', '2017-12-29 10:11:04', null, '113', '78', '60', null, '215', '4');
INSERT INTO `work` VALUES ('217', 'sendToNextDepartment', null, '2017-12-27 15:59:38', '', '', null, '', '2017-12-29 11:19:50', null, '114', '132', '89', '272', null, '3');
INSERT INTO `work` VALUES ('218', 'sendToNextDepartment', null, '2017-12-27 16:05:54', '', '', null, '', '2017-12-27 16:06:06', null, '115', '338', '260', '219', null, '3');
INSERT INTO `work` VALUES ('219', 'done', null, '2017-12-27 16:06:06', '', '', null, '', '2018-01-10 14:53:31', null, '115', '78', '60', null, '218', '4');
INSERT INTO `work` VALUES ('220', 'sendToNextDepartment', null, '2017-12-27 16:27:06', '', '', null, '', '2017-12-27 16:28:53', null, '116', '352', '273', '221', null, '3');
INSERT INTO `work` VALUES ('221', 'done', null, '2017-12-27 16:28:53', '', '', null, '', '2017-12-29 10:10:25', null, '116', '78', '60', null, '220', '4');
INSERT INTO `work` VALUES ('222', null, null, '2017-12-27 16:34:26', '', '', null, '', '2017-12-27 16:59:47', null, '117', '126', '83', null, null, '3');
INSERT INTO `work` VALUES ('223', null, null, '2017-12-27 16:35:36', '', '', null, '', '2017-12-27 16:35:36', null, '118', '112', null, null, null, '3');
INSERT INTO `work` VALUES ('224', 'sendToNextDepartment', null, '2017-12-27 16:41:36', '', '', null, '', '2017-12-27 16:42:53', null, '119', '354', '274', '225', null, '3');
INSERT INTO `work` VALUES ('225', 'done', null, '2017-12-27 16:42:53', '', '', null, '', '2017-12-29 10:09:56', null, '119', '78', '60', null, '224', '4');
INSERT INTO `work` VALUES ('226', null, null, '2017-12-27 16:45:29', '', '', null, '', '2017-12-27 16:45:31', null, '120', '72', '57', null, null, '3');
INSERT INTO `work` VALUES ('227', null, null, '2017-12-27 16:51:44', '', '', null, '', '2017-12-27 16:51:47', null, '121', '355', '275', null, null, '3');
INSERT INTO `work` VALUES ('228', 'sendToNextDepartment', null, '2017-12-27 16:53:11', '', '', null, '', '2017-12-27 17:02:04', null, '122', '131', '88', '229', null, '3');
INSERT INTO `work` VALUES ('229', 'done', null, '2017-12-27 17:02:04', '', '', null, '', '2017-12-29 10:09:25', null, '122', '78', '60', null, '228', '4');
INSERT INTO `work` VALUES ('230', 'sendToNextDepartment', null, '2017-12-27 17:07:04', '', '', null, '', '2017-12-27 17:10:18', null, '123', '131', '88', '231', null, '3');
INSERT INTO `work` VALUES ('231', 'done', null, '2017-12-27 17:10:18', '', '', null, '', '2018-01-10 14:52:01', null, '123', '78', '60', null, '230', '4');
INSERT INTO `work` VALUES ('232', 'sendToNextDepartment', null, '2017-12-27 18:16:40', '', '', null, '', '2017-12-27 18:17:13', null, '124', '110', '76', '233', null, '3');
INSERT INTO `work` VALUES ('233', 'done', null, '2017-12-27 18:17:13', '', '', null, '', '2017-12-29 10:07:37', null, '124', '78', '60', null, '232', '4');
INSERT INTO `work` VALUES ('234', 'sendToNextDepartment', null, '2017-12-28 08:03:28', '', '', null, '', '2017-12-28 08:09:11', null, '125', '131', '88', '235', null, '3');
INSERT INTO `work` VALUES ('235', 'done', null, '2017-12-28 08:09:11', '', '', null, '', '2018-01-10 14:51:45', null, '125', '78', '60', null, '234', '4');
INSERT INTO `work` VALUES ('236', 'sendToNextDepartment', null, '2017-12-28 08:13:16', '', '', null, '', '2017-12-28 08:27:48', null, '126', '131', '88', '237', null, '3');
INSERT INTO `work` VALUES ('237', 'done', null, '2017-12-28 08:27:47', '', '', null, '', '2018-01-10 14:45:37', null, '126', '78', '60', null, '236', '4');
INSERT INTO `work` VALUES ('238', 'sendToNextDepartment', null, '2017-12-28 09:52:30', '', '', null, '', '2018-01-17 15:10:05', null, '127', '12', '11', '568', null, '3');
INSERT INTO `work` VALUES ('239', 'sendToNextDepartment', null, '2017-12-28 10:30:19', '', '', null, '', '2017-12-28 10:33:15', null, '128', '372', '277', '240', null, '3');
INSERT INTO `work` VALUES ('240', 'done', null, '2017-12-28 10:33:15', '', '', null, '', '2017-12-28 10:37:15', null, '128', '21', '19', null, '239', '4');
INSERT INTO `work` VALUES ('241', 'sendToNextDepartment', null, '2017-12-28 15:11:56', '', '', null, '', '2018-01-18 09:58:42', null, '129', '248', '180', '607', null, '3');
INSERT INTO `work` VALUES ('242', 'sendToNextDepartment', null, '2017-12-28 16:02:21', '', '', null, '', '2017-12-29 08:58:35', null, '130', '373', '278', '253', null, '3');
INSERT INTO `work` VALUES ('243', 'sendToNextDepartment', null, '2017-12-28 16:32:08', '', '', null, '', '2017-12-29 11:19:36', null, '131', '132', '89', '271', null, '3');
INSERT INTO `work` VALUES ('244', 'sendToNextDepartment', null, '2017-12-28 17:09:12', '', '', null, '', '2017-12-29 16:35:47', null, '132', '133', '90', '300', null, '3');
INSERT INTO `work` VALUES ('245', 'sendToNextDepartment', null, '2017-12-28 17:11:36', '', '', null, '', '2017-12-29 11:19:17', null, '133', '132', '89', '270', null, '3');
INSERT INTO `work` VALUES ('246', 'sendToNextDepartment', null, '2017-12-28 19:41:40', '', '', null, '', '2017-12-28 19:41:49', null, '134', '110', '76', '247', null, '3');
INSERT INTO `work` VALUES ('247', 'done', null, '2017-12-28 19:41:49', '', '', null, '', '2017-12-29 10:05:15', null, '134', '78', '60', null, '246', '4');
INSERT INTO `work` VALUES ('248', 'sendToNextDepartment', null, '2017-12-28 19:41:56', '', '', null, '', '2017-12-29 14:31:00', null, '135', '405', '310', '276', null, '3');
INSERT INTO `work` VALUES ('249', 'sendToNextDepartment', null, '2017-12-28 22:01:12', '', '', null, '', '2017-12-29 17:05:26', null, '136', '245', '178', '301', null, '3');
INSERT INTO `work` VALUES ('250', 'sendToNextDepartment', null, '2017-12-28 22:33:20', '', '', null, '', '2017-12-29 17:06:23', null, '137', '245', '178', '303', null, '3');
INSERT INTO `work` VALUES ('251', 'sendToNextDepartment', null, '2017-12-29 08:51:03', '', '', null, '', '2017-12-29 08:53:53', null, '138', '105', '71', '252', null, '3');
INSERT INTO `work` VALUES ('252', 'done', null, '2017-12-29 08:53:53', '', '', null, '', '2018-01-10 14:53:20', null, '138', '78', '60', null, '251', '4');
INSERT INTO `work` VALUES ('253', 'done', null, '2017-12-29 08:58:35', '', '', '予以备案', '', '2017-12-29 09:01:00', null, '130', '24', '22', null, '242', '4');
INSERT INTO `work` VALUES ('254', 'sendToNextDepartment', null, '2017-12-29 09:00:27', '', '', null, '', '2017-12-29 09:09:03', null, '139', '414', '311', '255', null, '3');
INSERT INTO `work` VALUES ('255', 'done', null, '2017-12-29 09:09:03', '', '', '予以备案。', '', '2017-12-29 09:15:48', null, '139', '24', '22', null, '254', '4');
INSERT INTO `work` VALUES ('256', 'sendToNextDepartment', null, '2017-12-29 09:35:37', '', '', '专利备案', '', '2017-12-29 09:44:54', null, '140', '373', '278', '257', null, '3');
INSERT INTO `work` VALUES ('257', 'done', null, '2017-12-29 09:44:54', '', '', '予以备案', '', '2017-12-29 09:47:55', null, '140', '24', '22', null, '256', '4');
INSERT INTO `work` VALUES ('258', 'sendToNextDepartment', null, '2017-12-29 10:04:37', '', '', null, '', '2017-12-29 10:04:57', null, '141', '105', '71', '259', null, '3');
INSERT INTO `work` VALUES ('259', 'done', null, '2017-12-29 10:04:57', '', '', null, '', '2018-01-10 14:53:10', null, '141', '78', '60', null, '258', '4');
INSERT INTO `work` VALUES ('260', 'sendToNextDepartment', null, '2017-12-29 10:15:42', '', '', null, '', '2017-12-29 10:19:46', null, '142', '297', '222', '261', null, '3');
INSERT INTO `work` VALUES ('261', 'done', null, '2017-12-29 10:19:46', '', '', '予以备案', '', '2017-12-29 10:21:32', null, '142', '26', '24', null, '260', '4');
INSERT INTO `work` VALUES ('262', 'sendToNextDepartment', null, '2017-12-29 10:25:22', '', '', null, '', '2017-12-29 10:26:53', null, '143', '297', '222', '265', null, '3');
INSERT INTO `work` VALUES ('263', 'sendToNextDepartment', null, '2017-12-29 10:26:38', '', '', null, '', '2017-12-29 10:26:48', null, '144', '105', '71', '264', null, '3');
INSERT INTO `work` VALUES ('264', 'done', null, '2017-12-29 10:26:48', '', '', null, '', '2018-01-10 14:52:14', null, '144', '78', '60', null, '263', '4');
INSERT INTO `work` VALUES ('265', 'done', null, '2017-12-29 10:26:53', '', '', null, '', '2017-12-29 10:28:33', null, '143', '26', '24', null, '262', '4');
INSERT INTO `work` VALUES ('266', 'sendToNextDepartment', null, '2017-12-29 10:31:32', '', '', null, '', '2017-12-29 17:05:59', null, '145', '245', '178', '302', null, '3');
INSERT INTO `work` VALUES ('267', 'sendToNextDepartment', null, '2017-12-29 10:47:54', '', '', null, '', '2017-12-29 10:48:01', null, '146', '105', '71', '268', null, '3');
INSERT INTO `work` VALUES ('268', 'done', null, '2017-12-29 10:48:01', '', '', null, '', '2017-12-29 11:01:23', null, '146', '78', '60', null, '267', '4');
INSERT INTO `work` VALUES ('269', 'sendToNextDepartment', null, '2017-12-29 11:10:31', '', '', null, '', '2017-12-29 17:06:35', null, '147', '245', '178', '304', null, '3');
INSERT INTO `work` VALUES ('270', 'done', null, '2017-12-29 11:19:17', '', '', null, '', '2017-12-29 14:52:40', null, '133', '78', '60', null, '245', '4');
INSERT INTO `work` VALUES ('271', 'done', null, '2017-12-29 11:19:36', '', '', null, '', '2017-12-29 14:52:19', null, '131', '78', '60', null, '243', '4');
INSERT INTO `work` VALUES ('272', 'done', null, '2017-12-29 11:19:50', '', '', null, '', '2017-12-29 14:51:46', null, '114', '78', '60', null, '217', '4');
INSERT INTO `work` VALUES ('273', 'sendToNextDepartment', null, '2017-12-29 14:02:21', '', '', null, '', '2017-12-29 17:06:47', null, '148', '245', '178', '305', null, '3');
INSERT INTO `work` VALUES ('274', 'sendToNextDepartment', null, '2017-12-29 14:15:13', '', '', null, '', '2017-12-29 14:30:30', null, '149', '405', '310', '275', null, '3');
INSERT INTO `work` VALUES ('275', 'done', null, '2017-12-29 14:30:30', '', '', '予以备案', '', '2017-12-29 15:18:32', null, '149', '24', '22', null, '274', '4');
INSERT INTO `work` VALUES ('276', 'done', null, '2017-12-29 14:31:00', '', '', '予以备案', '', '2017-12-29 15:16:25', null, '135', '24', '22', null, '248', '4');
INSERT INTO `work` VALUES ('277', 'sendToNextDepartment', null, '2017-12-29 14:45:48', '', '', null, '', '2017-12-29 17:06:58', null, '150', '245', '178', '306', null, '3');
INSERT INTO `work` VALUES ('278', 'sendToNextDepartment', null, '2017-12-29 14:59:42', '', '', null, '', '2017-12-29 16:35:18', null, '151', '133', '90', '299', null, '3');
INSERT INTO `work` VALUES ('279', 'sendToNextDepartment', null, '2017-12-29 15:05:57', '', '', null, '', '2017-12-29 15:06:12', null, '152', '133', '90', '280', null, '3');
INSERT INTO `work` VALUES ('280', 'done', null, '2017-12-29 15:06:12', '', '', null, '', '2018-01-10 14:53:01', null, '152', '78', '60', null, '279', '4');
INSERT INTO `work` VALUES ('281', 'sendToNextDepartment', null, '2017-12-29 15:20:52', '', '', null, '', '2017-12-29 15:21:10', null, '153', '133', '90', '282', null, '3');
INSERT INTO `work` VALUES ('282', 'done', null, '2017-12-29 15:21:10', '', '', null, '', '2018-01-10 14:50:13', null, '153', '78', '60', null, '281', '4');
INSERT INTO `work` VALUES ('283', 'sendToNextDepartment', null, '2017-12-29 15:26:14', '', '', null, '', '2017-12-29 15:26:26', null, '154', '133', '90', '284', null, '3');
INSERT INTO `work` VALUES ('284', 'done', null, '2017-12-29 15:26:26', '', '', null, '', '2018-01-10 14:52:51', null, '154', '78', '60', null, '283', '4');
INSERT INTO `work` VALUES ('285', 'sendToNextDepartment', null, '2017-12-29 15:52:00', '', '', null, '', '2017-12-29 15:52:10', null, '155', '133', '90', '286', null, '3');
INSERT INTO `work` VALUES ('286', 'done', null, '2017-12-29 15:52:10', '', '', null, '', '2018-01-02 14:50:20', null, '155', '78', '60', null, '285', '4');
INSERT INTO `work` VALUES ('287', 'sendToNextDepartment', null, '2017-12-29 15:59:58', '', '', null, '', '2017-12-29 16:00:18', null, '156', '133', '90', '288', null, '3');
INSERT INTO `work` VALUES ('288', 'done', null, '2017-12-29 16:00:18', '', '', null, '', '2018-01-02 14:49:56', null, '156', '78', '60', null, '287', '4');
INSERT INTO `work` VALUES ('289', 'sendToNextDepartment', null, '2017-12-29 16:14:07', '', '', null, '', '2017-12-29 17:07:09', null, '157', '245', '178', '307', null, '3');
INSERT INTO `work` VALUES ('290', 'sendToNextDepartment', null, '2017-12-29 16:15:54', '', '', null, '', '2017-12-29 16:19:11', null, '158', '133', '90', '291', null, '3');
INSERT INTO `work` VALUES ('291', 'done', null, '2017-12-29 16:19:11', '', '', null, '', '2018-01-09 15:36:45', null, '158', '78', '60', null, '290', '4');
INSERT INTO `work` VALUES ('292', 'sendToNextDepartment', null, '2017-12-29 16:24:27', '', '', null, '', '2017-12-29 16:35:04', null, '159', '133', '90', '298', null, '3');
INSERT INTO `work` VALUES ('293', 'sendToNextDepartment', null, '2017-12-29 16:28:48', '', '', null, '', '2017-12-29 17:07:18', null, '160', '245', '178', '308', null, '3');
INSERT INTO `work` VALUES ('294', 'sendToNextDepartment', null, '2017-12-29 16:30:51', '', '', null, '', '2017-12-29 16:31:04', null, '161', '133', '90', '295', null, '3');
INSERT INTO `work` VALUES ('295', 'done', null, '2017-12-29 16:31:04', '', '', null, '', '2018-01-10 14:52:40', null, '161', '78', '60', null, '294', '4');
INSERT INTO `work` VALUES ('296', 'sendToNextDepartment', null, '2017-12-29 16:34:35', '', '', null, '', '2017-12-29 16:34:43', null, '162', '133', '90', '297', null, '3');
INSERT INTO `work` VALUES ('297', 'done', null, '2017-12-29 16:34:43', '', '', null, '', '2018-01-10 14:52:29', null, '162', '78', '60', null, '296', '4');
INSERT INTO `work` VALUES ('298', 'done', null, '2017-12-29 16:35:04', '', '', null, '', '2018-01-10 14:46:46', null, '159', '78', '60', null, '292', '4');
INSERT INTO `work` VALUES ('299', 'done', null, '2017-12-29 16:35:18', '', '', null, '', '2018-01-10 14:46:02', null, '151', '78', '60', null, '278', '4');
INSERT INTO `work` VALUES ('300', 'done', null, '2017-12-29 16:35:47', '', '', null, '', '2018-01-02 14:44:45', null, '132', '78', '60', null, '244', '4');
INSERT INTO `work` VALUES ('301', 'done', null, '2017-12-29 17:05:26', '', '', null, '', '2018-01-02 14:45:49', null, '136', '78', '60', null, '249', '4');
INSERT INTO `work` VALUES ('302', 'done', null, '2017-12-29 17:05:59', '', '', null, '', '2018-01-02 14:45:18', null, '145', '78', '60', null, '266', '4');
INSERT INTO `work` VALUES ('303', 'done', null, '2017-12-29 17:06:23', '', '', null, '', '2018-01-02 14:44:08', null, '137', '78', '60', null, '250', '4');
INSERT INTO `work` VALUES ('304', 'done', null, '2017-12-29 17:06:35', '', '', null, '', '2018-01-02 14:43:36', null, '147', '78', '60', null, '269', '4');
INSERT INTO `work` VALUES ('305', 'done', null, '2017-12-29 17:06:47', '', '', null, '', '2018-01-02 14:42:51', null, '148', '78', '60', null, '273', '4');
INSERT INTO `work` VALUES ('306', 'done', null, '2017-12-29 17:06:58', '', '', null, '', '2018-01-02 14:42:24', null, '150', '78', '60', null, '277', '4');
INSERT INTO `work` VALUES ('307', 'done', null, '2017-12-29 17:07:09', '', '', null, '', '2018-01-02 14:41:38', null, '157', '78', '60', null, '289', '4');
INSERT INTO `work` VALUES ('308', 'done', null, '2017-12-29 17:07:18', '', '', null, '', '2018-01-02 14:41:05', null, '160', '78', '60', null, '293', '4');
INSERT INTO `work` VALUES ('309', 'sendToNextDepartment', null, '2017-12-31 09:44:18', '', '', null, '', '2017-12-31 09:45:02', null, '163', '121', '81', '310', null, '3');
INSERT INTO `work` VALUES ('310', 'done', null, '2017-12-31 09:45:02', '', '', null, '', '2018-01-10 14:44:42', null, '163', '78', '60', null, '309', '4');
INSERT INTO `work` VALUES ('311', 'sendToNextDepartment', null, '2017-12-31 09:56:18', '', '', null, '', '2017-12-31 09:56:33', null, '164', '121', '81', '312', null, '3');
INSERT INTO `work` VALUES ('312', 'done', null, '2017-12-31 09:56:32', '', '', null, '', '2018-01-02 14:48:29', null, '164', '78', '60', null, '311', '4');
INSERT INTO `work` VALUES ('313', 'sendToNextDepartment', null, '2017-12-31 10:09:26', '', '', null, '', '2017-12-31 10:09:40', null, '165', '121', '81', '314', null, '3');
INSERT INTO `work` VALUES ('314', 'done', null, '2017-12-31 10:09:40', '', '', null, '', '2018-01-10 14:43:32', null, '165', '78', '60', null, '313', '4');
INSERT INTO `work` VALUES ('315', 'sendToNextDepartment', null, '2017-12-31 10:15:24', '', '', null, '', '2017-12-31 10:15:29', null, '166', '121', '81', '316', null, '3');
INSERT INTO `work` VALUES ('316', 'done', null, '2017-12-31 10:15:29', '', '', null, '', '2018-01-10 11:25:57', null, '166', '78', '60', null, '315', '4');
INSERT INTO `work` VALUES ('317', 'sendToNextDepartment', null, '2017-12-31 10:28:31', '', '', null, '', '2017-12-31 10:28:39', null, '167', '121', '81', '318', null, '3');
INSERT INTO `work` VALUES ('318', 'done', null, '2017-12-31 10:28:39', '', '', null, '', '2018-01-10 14:45:51', null, '167', '78', '60', null, '317', '4');
INSERT INTO `work` VALUES ('319', 'sendToNextDepartment', null, '2017-12-31 10:35:27', '', '', null, '', '2017-12-31 10:35:34', null, '168', '121', '81', '320', null, '3');
INSERT INTO `work` VALUES ('320', 'done', null, '2017-12-31 10:35:34', '', '', null, '', '2018-01-10 11:25:18', null, '168', '78', '60', null, '319', '4');
INSERT INTO `work` VALUES ('321', 'sendToNextDepartment', null, '2017-12-31 14:58:02', '', '', null, '', '2017-12-31 14:58:44', null, '169', '121', '81', '322', null, '3');
INSERT INTO `work` VALUES ('322', 'done', null, '2017-12-31 14:58:44', '', '', null, '', '2018-01-09 15:34:59', null, '169', '78', '60', null, '321', '4');
INSERT INTO `work` VALUES ('323', 'sendToNextDepartment', null, '2017-12-31 15:14:28', '', '', null, '', '2017-12-31 15:14:34', null, '170', '121', '81', '324', null, '3');
INSERT INTO `work` VALUES ('324', 'done', null, '2017-12-31 15:14:34', '', '', null, '', '2018-01-09 15:34:37', null, '170', '78', '60', null, '323', '4');
INSERT INTO `work` VALUES ('325', 'sendToNextDepartment', null, '2017-12-31 15:27:26', '', '', null, '', '2017-12-31 15:27:33', null, '171', '121', '81', '326', null, '3');
INSERT INTO `work` VALUES ('326', 'done', null, '2017-12-31 15:27:33', '', '', null, '', '2018-01-09 15:33:56', null, '171', '78', '60', null, '325', '4');
INSERT INTO `work` VALUES ('327', 'sendToNextDepartment', null, '2017-12-31 16:08:00', '', '', null, '', '2017-12-31 16:08:08', null, '172', '121', '81', '328', null, '3');
INSERT INTO `work` VALUES ('328', 'done', null, '2017-12-31 16:08:08', '', '', null, '', '2018-01-08 15:57:17', null, '172', '78', '60', null, '327', '4');
INSERT INTO `work` VALUES ('329', 'sendToNextDepartment', null, '2017-12-31 16:14:02', '', '', null, '', '2017-12-31 16:14:07', null, '173', '121', '81', '330', null, '3');
INSERT INTO `work` VALUES ('330', 'done', null, '2017-12-31 16:14:07', '', '', null, '', '2018-01-08 15:56:42', null, '173', '78', '60', null, '329', '4');
INSERT INTO `work` VALUES ('331', 'sendToNextDepartment', null, '2017-12-31 16:36:11', '', '', null, '', '2017-12-31 16:36:17', null, '174', '121', '81', '332', null, '3');
INSERT INTO `work` VALUES ('332', 'done', null, '2017-12-31 16:36:17', '', '', null, '', '2018-01-08 15:56:16', null, '174', '78', '60', null, '331', '4');
INSERT INTO `work` VALUES ('333', 'sendToNextDepartment', null, '2018-01-02 09:33:32', '', '', null, '', '2018-01-02 10:07:06', null, '175', '433', '313', '348', null, '3');
INSERT INTO `work` VALUES ('334', 'sendToNextDepartment', null, '2018-01-02 09:40:18', '', '', null, '', '2018-01-02 10:06:50', null, '176', '433', '313', '347', null, '3');
INSERT INTO `work` VALUES ('335', 'sendToNextDepartment', null, '2018-01-02 09:43:36', '', '', null, '', '2018-01-02 10:06:38', null, '177', '433', '313', '346', null, '3');
INSERT INTO `work` VALUES ('336', 'sendToNextDepartment', null, '2018-01-02 09:46:54', '', '', null, '', '2018-01-02 10:06:19', null, '178', '433', '313', '345', null, '3');
INSERT INTO `work` VALUES ('337', 'sendToNextDepartment', null, '2018-01-02 09:49:02', '', '', null, '', '2018-01-02 10:06:04', null, '179', '433', '313', '344', null, '3');
INSERT INTO `work` VALUES ('338', 'sendToNextDepartment', null, '2018-01-02 09:54:23', '', '', null, '', '2018-01-02 10:05:51', null, '180', '433', '313', '343', null, '3');
INSERT INTO `work` VALUES ('339', 'sendToNextDepartment', null, '2018-01-02 09:57:12', '', '', null, '', '2018-01-02 10:05:29', null, '181', '433', '313', '342', null, '3');
INSERT INTO `work` VALUES ('340', 'sendToNextDepartment', null, '2018-01-02 09:59:11', '', '', null, '', '2018-01-02 10:04:47', null, '182', '433', '313', '341', null, '3');
INSERT INTO `work` VALUES ('341', 'done', null, '2018-01-02 10:04:47', '', '', '予以备案', '', '2018-01-02 10:16:58', null, '182', '24', '22', null, '340', '4');
INSERT INTO `work` VALUES ('342', 'done', null, '2018-01-02 10:05:29', '', '', '予以备案', '', '2018-01-02 10:16:33', null, '181', '24', '22', null, '339', '4');
INSERT INTO `work` VALUES ('343', 'done', null, '2018-01-02 10:05:51', '', '', '予以备案', '', '2018-01-02 10:16:10', null, '180', '24', '22', null, '338', '4');
INSERT INTO `work` VALUES ('344', 'done', null, '2018-01-02 10:06:04', '', '', '予以备案', '', '2018-01-02 10:15:40', null, '179', '24', '22', null, '337', '4');
INSERT INTO `work` VALUES ('345', 'done', null, '2018-01-02 10:06:19', '', '', '予以备案', '', '2018-01-02 10:15:11', null, '178', '24', '22', null, '336', '4');
INSERT INTO `work` VALUES ('346', 'done', null, '2018-01-02 10:06:38', '', '', '予以备案', '', '2018-01-02 10:14:43', null, '177', '24', '22', null, '335', '4');
INSERT INTO `work` VALUES ('347', 'done', null, '2018-01-02 10:06:50', '', '', '予以备案', '', '2018-01-02 10:14:15', null, '176', '24', '22', null, '334', '4');
INSERT INTO `work` VALUES ('348', 'done', null, '2018-01-02 10:07:05', '', '', '予以备案', '', '2018-01-02 10:13:43', null, '175', '24', '22', null, '333', '4');
INSERT INTO `work` VALUES ('349', 'sendToNextDepartment', null, '2018-01-02 15:31:47', '', '', null, '', '2018-01-02 15:49:16', null, '183', '456', '316', '350', null, '3');
INSERT INTO `work` VALUES ('350', 'done', null, '2018-01-02 15:49:16', '', '', null, '', '2018-01-02 15:53:28', null, '183', '27', '25', null, '349', '4');
INSERT INTO `work` VALUES ('351', 'sendToNextDepartment', null, '2018-01-02 16:31:55', '', '', null, '', '2018-01-02 17:01:30', null, '184', '458', '317', '356', null, '3');
INSERT INTO `work` VALUES ('352', 'sendToNextDepartment', null, '2018-01-02 16:47:44', '', '', null, '', '2018-01-02 17:01:20', null, '185', '458', '317', '355', null, '3');
INSERT INTO `work` VALUES ('353', 'sendToNextDepartment', null, '2018-01-02 16:51:48', '', '', null, '', '2018-01-02 17:01:06', null, '186', '458', '317', '354', null, '3');
INSERT INTO `work` VALUES ('354', 'done', null, '2018-01-02 17:01:06', '', '', null, '', '2018-01-02 17:02:57', null, '186', '27', '25', null, '353', '4');
INSERT INTO `work` VALUES ('355', 'done', null, '2018-01-02 17:01:20', '', '', null, '', '2018-01-02 17:02:25', null, '185', '27', '25', null, '352', '4');
INSERT INTO `work` VALUES ('356', 'done', null, '2018-01-02 17:01:30', '', '', null, '', '2018-01-02 17:02:07', null, '184', '27', '25', null, '351', '4');
INSERT INTO `work` VALUES ('357', 'sendToNextDepartment', null, '2018-01-03 19:24:34', '', '', null, '', '2018-01-03 19:36:13', null, '187', '110', '76', '360', null, '3');
INSERT INTO `work` VALUES ('358', 'sendToNextDepartment', null, '2018-01-03 19:32:24', '', '', null, '', '2018-01-03 19:35:07', null, '188', '110', '76', '359', null, '3');
INSERT INTO `work` VALUES ('359', 'done', null, '2018-01-03 19:35:07', '', '', null, '', '2018-01-08 15:49:06', null, '188', '78', '60', null, '358', '4');
INSERT INTO `work` VALUES ('360', 'done', null, '2018-01-03 19:36:13', '', '', null, '', '2018-01-08 15:43:40', null, '187', '78', '60', null, '357', '4');
INSERT INTO `work` VALUES ('361', 'sendToNextDepartment', null, '2018-01-03 19:48:12', '', '', null, '', '2018-01-03 19:49:43', null, '189', '110', '76', '362', null, '3');
INSERT INTO `work` VALUES ('362', 'done', null, '2018-01-03 19:49:43', '', '', null, '', '2018-01-08 15:48:55', null, '189', '78', '60', null, '361', '4');
INSERT INTO `work` VALUES ('363', 'sendToNextDepartment', null, '2018-01-04 08:18:45', '', '', null, '', '2018-01-04 08:51:27', null, '190', '418', '312', '368', null, '3');
INSERT INTO `work` VALUES ('364', 'sendToNextDepartment', null, '2018-01-04 08:33:19', '', '', null, '', '2018-01-04 08:51:19', null, '191', '418', '312', '367', null, '3');
INSERT INTO `work` VALUES ('365', 'sendToNextDepartment', null, '2018-01-04 08:46:08', '', '', null, '', '2018-01-04 08:51:08', null, '192', '418', '312', '366', null, '3');
INSERT INTO `work` VALUES ('366', 'done', null, '2018-01-04 08:51:08', '', '', '内容为空', '', '2018-01-09 08:41:43', null, '192', '24', '22', null, '365', '4');
INSERT INTO `work` VALUES ('367', 'done', null, '2018-01-04 08:51:19', '', '', '予以备案', '', '2018-01-04 09:18:04', null, '191', '24', '22', null, '364', '4');
INSERT INTO `work` VALUES ('368', 'done', null, '2018-01-04 08:51:27', '', '', '予以备案', '', '2018-01-04 09:17:24', null, '190', '24', '22', null, '363', '4');
INSERT INTO `work` VALUES ('369', 'sendToNextDepartment', null, '2018-01-04 09:28:37', '', '', null, '', '2018-01-04 09:30:59', null, '193', '418', '312', '370', null, '3');
INSERT INTO `work` VALUES ('370', 'done', null, '2018-01-04 09:30:59', '', '', '予以备案', '', '2018-01-04 09:55:43', null, '193', '24', '22', null, '369', '4');
INSERT INTO `work` VALUES ('371', 'sendToNextDepartment', null, '2018-01-04 09:36:30', '', '', null, '', '2018-01-04 09:36:42', null, '194', '418', '312', '372', null, '3');
INSERT INTO `work` VALUES ('372', 'done', null, '2018-01-04 09:36:42', '', '', null, '', '2018-01-04 09:38:49', null, '194', '24', '22', null, '371', '4');
INSERT INTO `work` VALUES ('373', 'sendToNextDepartment', null, '2018-01-04 17:24:42', '', '', null, '', '2018-01-04 17:25:15', null, '195', '405', '310', '374', null, '3');
INSERT INTO `work` VALUES ('374', 'done', null, '2018-01-04 17:25:15', '', '', '予以备案', '', '2018-01-05 11:16:58', null, '195', '24', '22', null, '373', '4');
INSERT INTO `work` VALUES ('375', null, null, '2018-01-05 10:22:03', '', '', null, '', '2018-01-05 10:22:07', null, '196', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('376', null, null, '2018-01-05 10:26:36', '', '', null, '', '2018-01-05 10:26:39', null, '197', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('377', null, null, '2018-01-05 10:29:42', '', '', null, '', '2018-01-05 10:29:45', null, '198', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('378', null, null, '2018-01-05 10:40:12', '', '', null, '', '2018-01-05 10:40:19', null, '199', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('379', null, null, '2018-01-05 10:42:46', '', '', null, '', '2018-01-05 10:42:49', null, '200', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('380', null, null, '2018-01-05 10:45:44', '', '', null, '', '2018-01-05 10:45:47', null, '201', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('381', null, null, '2018-01-05 10:49:06', '', '', null, '', '2018-01-05 10:49:09', null, '202', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('382', null, null, '2018-01-05 10:51:40', '', '', null, '', '2018-01-05 10:51:42', null, '203', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('383', null, null, '2018-01-05 10:53:53', '', '', null, '', '2018-01-05 10:54:12', null, '204', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('384', null, null, '2018-01-05 11:02:17', '', '', null, '', '2018-01-05 11:02:22', null, '205', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('385', null, null, '2018-01-05 11:05:20', '', '', null, '', '2018-01-05 11:05:22', null, '206', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('386', null, null, '2018-01-05 11:08:02', '', '', null, '', '2018-01-05 11:08:04', null, '207', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('387', null, null, '2018-01-05 11:10:12', '', '', null, '', '2018-01-05 11:10:15', null, '208', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('388', null, null, '2018-01-05 11:12:39', '', '', null, '', '2018-01-05 11:12:42', null, '209', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('389', null, null, '2018-01-05 11:14:52', '', '', null, '', '2018-01-05 11:14:55', null, '210', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('390', null, null, '2018-01-05 11:17:12', '', '', null, '', '2018-01-05 11:17:15', null, '211', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('391', null, null, '2018-01-05 11:19:47', '', '', null, '', '2018-01-05 11:19:49', null, '212', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('392', null, null, '2018-01-05 11:22:11', '', '', null, '', '2018-01-05 11:22:13', null, '213', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('393', null, null, '2018-01-05 11:35:23', '', '', null, '', '2018-01-05 11:35:26', null, '214', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('394', null, null, '2018-01-05 11:40:28', '', '', null, '', '2018-01-05 11:40:31', null, '215', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('395', null, null, '2018-01-05 11:42:46', '', '', null, '', '2018-01-05 11:42:48', null, '216', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('396', null, null, '2018-01-05 11:44:31', '', '', null, '', '2018-01-05 11:44:33', null, '217', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('397', null, null, '2018-01-05 11:46:35', '', '', null, '', '2018-01-05 11:46:38', null, '218', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('398', null, null, '2018-01-05 11:49:46', '', '', null, '', '2018-01-05 11:49:49', null, '219', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('399', null, null, '2018-01-05 11:52:07', '', '', null, '', '2018-01-05 11:52:10', null, '220', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('400', null, null, '2018-01-05 11:54:27', '', '', null, '', '2018-01-05 11:54:30', null, '221', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('401', null, null, '2018-01-05 14:52:18', '', '', null, '', '2018-01-05 14:52:21', null, '222', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('402', null, null, '2018-01-05 14:54:23', '', '', null, '', '2018-01-05 14:54:28', null, '223', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('403', null, null, '2018-01-05 14:56:17', '', '', null, '', '2018-01-05 14:59:48', null, '224', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('404', null, null, '2018-01-05 15:02:13', '', '', null, '', '2018-01-05 15:02:15', null, '225', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('405', null, null, '2018-01-05 15:04:37', '', '', null, '', '2018-01-05 15:04:40', null, '226', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('406', 'sendToNextDepartment', null, '2018-01-05 15:05:12', '', '', null, '', '2018-01-05 15:05:29', null, '227', '468', '318', '407', null, '3');
INSERT INTO `work` VALUES ('407', 'done', null, '2018-01-05 15:05:29', '', '', '予以备案', '', '2018-01-08 14:25:33', null, '227', '24', '22', null, '406', '4');
INSERT INTO `work` VALUES ('408', null, null, '2018-01-05 15:06:16', '', '', null, '', '2018-01-05 15:06:27', null, '228', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('409', null, null, '2018-01-05 15:07:52', '', '', null, '', '2018-01-05 15:07:57', null, '229', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('410', null, null, '2018-01-05 15:09:41', '', '', null, '', '2018-01-05 15:09:43', null, '230', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('411', null, null, '2018-01-05 15:11:13', '', '', null, '', '2018-01-05 15:11:28', null, '231', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('412', null, null, '2018-01-05 15:17:01', '', '', null, '', '2018-01-05 15:17:04', null, '232', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('413', null, null, '2018-01-05 15:58:22', '', '', null, '', '2018-01-05 15:58:25', null, '233', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('414', null, null, '2018-01-05 16:01:05', '', '', null, '', '2018-01-05 16:01:07', null, '234', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('415', null, null, '2018-01-05 16:07:08', '', '', null, '', '2018-01-05 16:07:13', null, '235', '109', '75', null, null, '3');
INSERT INTO `work` VALUES ('416', 'sendToNextDepartment', null, '2018-01-06 09:03:09', '', '', null, '', '2018-01-06 09:05:19', null, '236', '468', '318', '417', null, '3');
INSERT INTO `work` VALUES ('417', 'done', null, '2018-01-06 09:05:19', '', '', '予以备案', '', '2018-01-08 14:25:10', null, '236', '24', '22', null, '416', '4');
INSERT INTO `work` VALUES ('418', 'sendToNextDepartment', null, '2018-01-08 09:31:36', '', '', null, '', '2018-01-08 11:01:01', null, '237', '105', '71', '426', null, '3');
INSERT INTO `work` VALUES ('419', 'sendToNextDepartment', null, '2018-01-08 10:32:15', '', '', null, '', '2018-01-08 10:58:40', null, '238', '105', '71', '425', null, '3');
INSERT INTO `work` VALUES ('420', 'sendToNextDepartment', null, '2018-01-08 10:36:28', '', '', null, '', '2018-01-08 10:55:58', null, '239', '105', '71', '423', null, '3');
INSERT INTO `work` VALUES ('421', 'sendToNextDepartment', null, '2018-01-08 10:41:22', '', '', null, '', '2018-01-08 10:50:34', null, '240', '105', '71', '422', null, '3');
INSERT INTO `work` VALUES ('422', 'done', null, '2018-01-08 10:50:34', '', '', null, '', '2018-01-08 15:29:59', null, '240', '78', '60', null, '421', '4');
INSERT INTO `work` VALUES ('423', 'done', null, '2018-01-08 10:55:58', '', '', null, '', '2018-01-08 15:29:25', null, '239', '78', '60', null, '420', '4');
INSERT INTO `work` VALUES ('424', 'sendToNextDepartment', null, '2018-01-08 10:57:34', '', '', null, '', '2018-01-08 11:15:34', null, '241', '474', '320', '429', null, '3');
INSERT INTO `work` VALUES ('425', 'done', null, '2018-01-08 10:58:40', '', '', null, '', '2018-01-08 15:29:11', null, '238', '78', '60', null, '419', '4');
INSERT INTO `work` VALUES ('426', 'done', null, '2018-01-08 11:01:01', '', '', null, '', '2018-01-08 15:28:47', null, '237', '78', '60', null, '418', '4');
INSERT INTO `work` VALUES ('427', 'sendToNextDepartment', null, '2018-01-08 11:03:28', '', '', null, '', '2018-01-08 11:15:16', null, '242', '474', '320', '428', null, '3');
INSERT INTO `work` VALUES ('428', 'done', null, '2018-01-08 11:15:16', '', '', null, '', '2018-01-09 14:28:44', null, '242', '27', '25', null, '427', '4');
INSERT INTO `work` VALUES ('429', 'done', null, '2018-01-08 11:15:34', '', '', null, '', '2018-01-09 14:29:48', null, '241', '27', '25', null, '424', '4');
INSERT INTO `work` VALUES ('430', 'sendToNextDepartment', null, '2018-01-08 14:57:05', '', '', null, '', '2018-01-08 15:02:12', null, '243', '42', '34', '431', null, '3');
INSERT INTO `work` VALUES ('431', 'done', null, '2018-01-08 15:02:12', '', '', null, '', '2018-01-08 15:03:21', null, '243', '38', '276', null, '430', '4');
INSERT INTO `work` VALUES ('432', 'sendToNextDepartment', null, '2018-01-08 16:29:58', '', '', null, '', '2018-01-08 16:53:19', null, '244', '478', '322', '449', null, '3');
INSERT INTO `work` VALUES ('433', 'sendToNextDepartment', null, '2018-01-08 16:36:48', '', '', null, '', '2018-01-08 16:53:10', null, '245', '478', '322', '448', null, '3');
INSERT INTO `work` VALUES ('434', 'sendToNextDepartment', null, '2018-01-08 16:40:08', '', '', null, '', '2018-01-08 16:53:01', null, '246', '478', '322', '447', null, '3');
INSERT INTO `work` VALUES ('435', 'sendToNextDepartment', null, '2018-01-08 16:41:47', '', '', null, '', '2018-01-08 16:52:50', null, '247', '478', '322', '446', null, '3');
INSERT INTO `work` VALUES ('436', 'sendToNextDepartment', null, '2018-01-08 16:43:19', '', '', null, '', '2018-01-08 16:52:42', null, '248', '478', '322', '445', null, '3');
INSERT INTO `work` VALUES ('437', 'sendToNextDepartment', null, '2018-01-08 16:45:12', '', '', null, '', '2018-01-08 16:52:34', null, '249', '478', '322', '444', null, '3');
INSERT INTO `work` VALUES ('438', 'sendToNextDepartment', null, '2018-01-08 16:46:59', '', '', null, '', '2018-01-08 16:52:24', null, '250', '478', '322', '443', null, '3');
INSERT INTO `work` VALUES ('439', 'sendToNextDepartment', null, '2018-01-08 16:48:21', '', '', null, '', '2018-01-08 16:52:17', null, '251', '478', '322', '442', null, '3');
INSERT INTO `work` VALUES ('440', 'sendToNextDepartment', null, '2018-01-08 16:51:45', '', '', null, '', '2018-01-08 16:52:06', null, '252', '478', '322', '441', null, '3');
INSERT INTO `work` VALUES ('441', 'done', null, '2018-01-08 16:52:06', '', '', '予以备案', '', '2018-01-09 15:37:07', null, '252', '24', '22', null, '440', '4');
INSERT INTO `work` VALUES ('442', 'done', null, '2018-01-08 16:52:17', '', '', '予以备案', '', '2018-01-09 15:36:34', null, '251', '24', '22', null, '439', '4');
INSERT INTO `work` VALUES ('443', 'done', null, '2018-01-08 16:52:24', '', '', '予以备案', '', '2018-01-09 15:36:07', null, '250', '24', '22', null, '438', '4');
INSERT INTO `work` VALUES ('444', 'done', null, '2018-01-08 16:52:34', '', '', '予以备案', '', '2018-01-09 15:35:46', null, '249', '24', '22', null, '437', '4');
INSERT INTO `work` VALUES ('445', 'done', null, '2018-01-08 16:52:42', '', '', '予以备案', '', '2018-01-09 15:35:26', null, '248', '24', '22', null, '436', '4');
INSERT INTO `work` VALUES ('446', 'done', null, '2018-01-08 16:52:50', '', '', '予以备案', '', '2018-01-09 15:35:03', null, '247', '24', '22', null, '435', '4');
INSERT INTO `work` VALUES ('447', 'done', null, '2018-01-08 16:53:01', '', '', null, '', '2018-01-09 09:00:03', null, '246', '24', '22', null, '434', '4');
INSERT INTO `work` VALUES ('448', 'done', null, '2018-01-08 16:53:10', '', '', null, '', '2018-01-09 08:54:26', null, '245', '24', '22', null, '433', '4');
INSERT INTO `work` VALUES ('449', 'done', null, '2018-01-08 16:53:19', '', '', '内容不全', '', '2018-01-09 08:52:32', null, '244', '24', '22', null, '432', '4');
INSERT INTO `work` VALUES ('450', 'sendToNextDepartment', null, '2018-01-09 08:43:05', '', '', null, '', '2018-01-09 08:43:20', null, '253', '42', '34', '451', null, '3');
INSERT INTO `work` VALUES ('451', 'done', null, '2018-01-09 08:43:20', '', '', null, '', '2018-01-09 08:44:07', null, '253', '38', '276', null, '450', '4');
INSERT INTO `work` VALUES ('452', 'sendToNextDepartment', null, '2018-01-09 11:26:29', '', '', null, '', '2018-01-09 11:27:10', null, '254', '405', '310', '453', null, '3');
INSERT INTO `work` VALUES ('453', 'done', null, '2018-01-09 11:27:10', '', '', '予以备案', '', '2018-01-09 14:23:26', null, '254', '24', '22', null, '452', '4');
INSERT INTO `work` VALUES ('454', 'sendToNextDepartment', null, '2018-01-09 15:28:38', '', '', null, '', '2018-01-09 15:30:20', null, '255', '478', '322', '457', null, '3');
INSERT INTO `work` VALUES ('455', 'sendToNextDepartment', null, '2018-01-09 15:30:03', '', '', null, '', '2018-01-09 15:30:11', null, '256', '478', '322', '456', null, '3');
INSERT INTO `work` VALUES ('456', 'done', null, '2018-01-09 15:30:11', '', '', '予以备案', '', '2018-01-09 15:34:26', null, '256', '24', '22', null, '455', '4');
INSERT INTO `work` VALUES ('457', 'done', null, '2018-01-09 15:30:20', '', '', '予以备案', '', '2018-01-09 15:33:52', null, '255', '24', '22', null, '454', '4');
INSERT INTO `work` VALUES ('458', 'sendToNextDepartment', null, '2018-01-09 15:31:36', '', '', null, '', '2018-01-09 15:31:41', null, '257', '478', '322', '459', null, '3');
INSERT INTO `work` VALUES ('459', 'done', null, '2018-01-09 15:31:41', '', '', '予以备案', '', '2018-01-09 15:33:24', null, '257', '24', '22', null, '458', '4');
INSERT INTO `work` VALUES ('460', 'sendToNextDepartment', null, '2018-01-09 15:36:12', '', '', null, '', '2018-01-09 15:37:07', null, '258', '42', '34', '461', null, '3');
INSERT INTO `work` VALUES ('461', 'done', null, '2018-01-09 15:37:07', '', '', null, '', '2018-01-09 15:37:35', null, '258', '38', '276', null, '460', '4');
INSERT INTO `work` VALUES ('462', null, null, '2018-01-10 09:50:15', '', '', null, '', '2018-01-10 10:04:42', null, '259', '105', '71', null, null, '3');
INSERT INTO `work` VALUES ('463', 'sendToNextDepartment', null, '2018-01-10 10:14:29', '', '', null, '', '2018-01-10 10:15:14', null, '260', '105', '71', '464', null, '3');
INSERT INTO `work` VALUES ('464', 'done', null, '2018-01-10 10:15:14', '', '', null, '', '2018-01-10 10:20:23', null, '260', '78', '60', null, '463', '4');
INSERT INTO `work` VALUES ('465', 'sendToNextDepartment', null, '2018-01-10 14:28:46', '', '', '标牌不清', '', '2018-01-10 14:29:38', null, '261', '42', '34', '466', null, '3');
INSERT INTO `work` VALUES ('466', 'done', null, '2018-01-10 14:29:38', '', '', null, '', '2018-01-10 14:30:24', null, '261', '38', '276', null, '465', '4');
INSERT INTO `work` VALUES ('467', 'sendToNextDepartment', null, '2018-01-10 14:41:43', '', '', null, '', '2018-01-10 14:42:05', null, '262', '42', '34', '468', null, '3');
INSERT INTO `work` VALUES ('468', 'done', null, '2018-01-10 14:42:05', '', '', null, '', '2018-01-10 14:42:33', null, '262', '38', '276', null, '467', '4');
INSERT INTO `work` VALUES ('469', 'sendToNextDepartment', null, '2018-01-10 14:49:04', '', '', null, '', '2018-01-10 14:49:13', null, '263', '42', '34', '470', null, '3');
INSERT INTO `work` VALUES ('470', 'done', null, '2018-01-10 14:49:13', '', '', null, '', '2018-01-10 14:50:35', null, '263', '38', '276', null, '469', '4');
INSERT INTO `work` VALUES ('471', 'sendToNextDepartment', null, '2018-01-10 15:08:40', '', '', null, '', '2018-01-10 15:08:47', null, '264', '42', '34', '472', null, '3');
INSERT INTO `work` VALUES ('472', 'done', null, '2018-01-10 15:08:47', '', '', null, '', '2018-01-10 15:23:57', null, '264', '38', '276', null, '471', '4');
INSERT INTO `work` VALUES ('473', 'sendToNextDepartment', null, '2018-01-10 15:37:28', '', '', null, '', '2018-01-10 15:38:21', null, '265', '42', '34', '474', null, '3');
INSERT INTO `work` VALUES ('474', 'done', null, '2018-01-10 15:38:21', '', '', null, '', '2018-01-10 15:39:46', null, '265', '38', '276', null, '473', '4');
INSERT INTO `work` VALUES ('475', 'sendToNextDepartment', null, '2018-01-11 10:21:03', '', '', null, '', '2018-01-11 10:22:24', null, '266', '186', '131', '476', null, '3');
INSERT INTO `work` VALUES ('476', 'done', null, '2018-01-11 10:22:24', '', '', null, '', '2018-01-11 15:12:21', null, '266', '78', '60', null, '475', '4');
INSERT INTO `work` VALUES ('477', 'sendToNextDepartment', null, '2018-01-11 17:20:36', '', '', null, '', '2018-01-11 17:24:52', null, '267', '510', '351', '478', null, '3');
INSERT INTO `work` VALUES ('478', 'done', null, '2018-01-11 17:24:52', '', '', null, '', '2018-01-15 14:42:33', null, '267', '38', '276', null, '477', '4');
INSERT INTO `work` VALUES ('479', 'sendToNextDepartment', null, '2018-01-11 17:28:11', '', '', null, '', '2018-01-11 17:34:21', null, '268', '510', '351', '480', null, '3');
INSERT INTO `work` VALUES ('480', 'done', null, '2018-01-11 17:34:21', '', '', null, '', '2018-01-15 14:42:51', null, '268', '38', '276', null, '479', '4');
INSERT INTO `work` VALUES ('481', 'sendToNextDepartment', null, '2018-01-11 17:37:50', '', '', null, '', '2018-01-11 17:39:56', null, '269', '510', '351', '482', null, '3');
INSERT INTO `work` VALUES ('482', 'done', null, '2018-01-11 17:39:56', '', '', '器具重复备案，请讲重复备案的器具删除', '', '2018-01-15 14:45:26', null, '269', '38', '276', null, '481', '4');
INSERT INTO `work` VALUES ('483', 'sendToNextDepartment', null, '2018-01-11 17:46:04', '', '', null, '', '2018-01-11 17:46:36', null, '270', '510', '351', '484', null, '3');
INSERT INTO `work` VALUES ('484', 'done', null, '2018-01-11 17:46:36', '', '', null, '', '2018-01-15 14:45:47', null, '270', '38', '276', null, '483', '4');
INSERT INTO `work` VALUES ('485', 'sendToNextDepartment', null, '2018-01-12 10:31:27', '', '', null, '', '2018-01-12 12:18:48', null, '271', '501', '342', '489', null, '3');
INSERT INTO `work` VALUES ('486', 'sendToNextDepartment', null, '2018-01-12 10:38:49', '', '', null, '', '2018-01-12 12:18:12', null, '272', '501', '342', '488', null, '3');
INSERT INTO `work` VALUES ('487', 'sendToNextDepartment', null, '2018-01-12 11:07:49', '', '', null, '', '2018-01-16 18:57:14', null, '273', '504', '345', '539', null, '3');
INSERT INTO `work` VALUES ('488', 'done', null, '2018-01-12 12:18:12', '', '', null, '', '2018-01-15 14:46:28', null, '272', '38', '276', null, '486', '4');
INSERT INTO `work` VALUES ('489', 'done', null, '2018-01-12 12:18:48', '', '', null, '', '2018-01-15 14:46:48', null, '271', '38', '276', null, '485', '4');
INSERT INTO `work` VALUES ('490', 'sendToNextDepartment', null, '2018-01-12 12:34:17', '', '', '无', '', '2018-01-12 12:46:56', null, '274', '494', '336', '493', null, '3');
INSERT INTO `work` VALUES ('491', 'sendToNextDepartment', null, '2018-01-12 12:39:41', '', '', '无', '', '2018-01-12 12:43:35', null, '275', '494', '336', '492', null, '3');
INSERT INTO `work` VALUES ('492', 'done', null, '2018-01-12 12:43:35', '', '', null, '', '2018-01-15 14:47:07', null, '275', '38', '276', null, '491', '4');
INSERT INTO `work` VALUES ('493', 'done', null, '2018-01-12 12:46:56', '', '', null, '', '2018-01-15 14:47:23', null, '274', '38', '276', null, '490', '4');
INSERT INTO `work` VALUES ('494', 'sendToNextDepartment', null, '2018-01-13 11:41:45', '', '', '', '', '2018-01-13 11:44:10', null, '276', '505', '346', '495', null, '3');
INSERT INTO `work` VALUES ('495', 'done', null, '2018-01-13 11:44:10', '', '', null, '', '2018-01-15 14:47:37', null, '276', '38', '276', null, '494', '4');
INSERT INTO `work` VALUES ('496', 'sendToNextDepartment', null, '2018-01-13 11:48:46', '', '', '您给看一下对不对  我按照产品合格证书填写的', '', '2018-01-13 11:50:58', null, '277', '505', '346', '497', null, '3');
INSERT INTO `work` VALUES ('497', 'done', null, '2018-01-13 11:50:58', '', '', null, '', '2018-01-15 14:47:54', null, '277', '38', '276', null, '496', '4');
INSERT INTO `work` VALUES ('498', 'sendToNextDepartment', null, '2018-01-13 11:59:52', '', '', null, '', '2018-01-13 12:00:20', null, '278', '505', '346', '499', null, '3');
INSERT INTO `work` VALUES ('499', 'done', null, '2018-01-13 12:00:20', '', '', null, '', '2018-01-15 14:48:08', null, '278', '38', '276', null, '498', '4');
INSERT INTO `work` VALUES ('500', 'sendToNextDepartment', null, '2018-01-13 12:04:36', '', '', null, '', '2018-01-13 12:04:46', null, '279', '505', '346', '501', null, '3');
INSERT INTO `work` VALUES ('501', 'done', null, '2018-01-13 12:04:46', '', '', null, '', '2018-01-15 14:48:20', null, '279', '38', '276', null, '500', '4');
INSERT INTO `work` VALUES ('502', 'sendToNextDepartment', null, '2018-01-15 10:04:23', '', '', null, '', '2018-01-15 10:04:30', null, '280', '533', '370', '503', null, '3');
INSERT INTO `work` VALUES ('503', 'done', null, '2018-01-15 10:04:30', '', '', null, '', '2018-01-15 10:04:59', null, '280', '38', '276', null, '502', '4');
INSERT INTO `work` VALUES ('504', 'sendToNextDepartment', null, '2018-01-15 10:36:01', '', '', null, '', '2018-01-15 10:47:12', null, '281', '532', '369', '511', null, '3');
INSERT INTO `work` VALUES ('505', 'sendToNextDepartment', null, '2018-01-15 10:38:32', '', '', null, '', '2018-01-15 10:47:01', null, '282', '532', '369', '510', null, '3');
INSERT INTO `work` VALUES ('506', 'sendToNextDepartment', null, '2018-01-15 10:42:47', '', '', null, '', '2018-01-15 10:46:53', null, '283', '532', '369', '509', null, '3');
INSERT INTO `work` VALUES ('507', 'sendToNextDepartment', null, '2018-01-15 10:46:00', '', '', null, '', '2018-01-15 10:46:41', null, '284', '532', '369', '508', null, '3');
INSERT INTO `work` VALUES ('508', 'done', null, '2018-01-15 10:46:41', '', '', '予以备案', '', '2018-01-15 14:55:05', null, '284', '24', '22', null, '507', '4');
INSERT INTO `work` VALUES ('509', 'done', null, '2018-01-15 10:46:52', '', '', '予以备案', '', '2018-01-15 14:54:45', null, '283', '24', '22', null, '506', '4');
INSERT INTO `work` VALUES ('510', 'done', null, '2018-01-15 10:47:01', '', '', '予以备案', '', '2018-01-15 14:54:23', null, '282', '24', '22', null, '505', '4');
INSERT INTO `work` VALUES ('511', 'done', null, '2018-01-15 10:47:12', '', '', '予以备案', '', '2018-01-15 14:53:53', null, '281', '24', '22', null, '504', '4');
INSERT INTO `work` VALUES ('512', 'sendToNextDepartment', null, '2018-01-16 11:01:34', '', '', null, '', '2018-01-16 14:35:37', null, '285', '537', '374', '515', null, '3');
INSERT INTO `work` VALUES ('513', 'sendToNextDepartment', null, '2018-01-16 14:17:08', '', '', null, '', '2018-01-16 14:19:29', null, '286', '511', '352', '514', null, '3');
INSERT INTO `work` VALUES ('514', 'done', null, '2018-01-16 14:19:29', '', '', null, '', '2018-01-17 10:48:01', null, '286', '38', '276', null, '513', '4');
INSERT INTO `work` VALUES ('515', 'done', null, '2018-01-16 14:35:37', '', '', null, '', '2018-01-16 14:37:12', null, '285', '78', '60', null, '512', '4');
INSERT INTO `work` VALUES ('516', null, null, '2018-01-16 14:37:58', '', '', null, '', '2018-01-16 14:38:03', null, '287', '483', '326', null, null, '3');
INSERT INTO `work` VALUES ('517', 'sendToNextDepartment', null, '2018-01-16 14:42:28', '', '', null, '', '2018-01-16 14:44:59', null, '288', '512', '353', '518', null, '3');
INSERT INTO `work` VALUES ('518', 'done', null, '2018-01-16 14:44:59', '', '', null, '', '2018-01-17 10:48:53', null, '288', '38', '276', null, '517', '4');
INSERT INTO `work` VALUES ('519', 'sendToNextDepartment', null, '2018-01-16 14:49:16', '', '', null, '', '2018-01-16 14:55:19', null, '289', '513', '354', '520', null, '3');
INSERT INTO `work` VALUES ('520', 'done', null, '2018-01-16 14:55:19', '', '', null, '', '2018-01-17 10:49:56', null, '289', '38', '276', null, '519', '4');
INSERT INTO `work` VALUES ('521', 'sendToNextDepartment', null, '2018-01-16 15:04:31', '', '', null, '', '2018-01-16 15:12:16', null, '290', '515', '356', '522', null, '3');
INSERT INTO `work` VALUES ('522', 'done', null, '2018-01-16 15:12:15', '', '', null, '', '2018-01-17 10:50:24', null, '290', '38', '276', null, '521', '4');
INSERT INTO `work` VALUES ('523', 'sendToNextDepartment', null, '2018-01-16 15:23:16', '', '', null, '', '2018-01-16 15:24:08', null, '291', '514', '355', '524', null, '3');
INSERT INTO `work` VALUES ('524', 'done', null, '2018-01-16 15:24:08', '', '', null, '', '2018-01-17 10:50:39', null, '291', '38', '276', null, '523', '4');
INSERT INTO `work` VALUES ('525', 'sendToNextDepartment', null, '2018-01-16 15:27:58', '', '', null, '', '2018-01-16 15:32:50', null, '292', '516', '357', '526', null, '3');
INSERT INTO `work` VALUES ('526', 'done', null, '2018-01-16 15:32:50', '', '', null, '', '2018-01-17 10:51:06', null, '292', '38', '276', null, '525', '4');
INSERT INTO `work` VALUES ('527', 'sendToNextDepartment', null, '2018-01-16 15:42:34', '', '', null, '', '2018-01-16 15:44:06', null, '293', '517', '358', '528', null, '3');
INSERT INTO `work` VALUES ('528', 'done', null, '2018-01-16 15:44:06', '', '', null, '', '2018-01-17 10:51:24', null, '293', '38', '276', null, '527', '4');
INSERT INTO `work` VALUES ('529', 'sendToNextDepartment', null, '2018-01-16 15:46:15', '', '', null, '', '2018-01-16 15:48:07', null, '294', '518', '359', '530', null, '3');
INSERT INTO `work` VALUES ('530', 'done', null, '2018-01-16 15:48:07', '', '', null, '', '2018-01-17 10:51:39', null, '294', '38', '276', null, '529', '4');
INSERT INTO `work` VALUES ('531', 'sendToNextDepartment', null, '2018-01-16 15:54:21', '', '', null, '', '2018-01-16 16:00:52', null, '295', '519', '360', '532', null, '3');
INSERT INTO `work` VALUES ('532', 'done', null, '2018-01-16 16:00:52', '', '', null, '', '2018-01-17 10:52:02', null, '295', '38', '276', null, '531', '4');
INSERT INTO `work` VALUES ('533', 'sendToNextDepartment', null, '2018-01-16 16:04:55', '', '', null, '', '2018-01-16 16:07:18', null, '296', '520', '361', '534', null, '3');
INSERT INTO `work` VALUES ('534', 'done', null, '2018-01-16 16:07:18', '', '', null, '', '2018-01-17 10:52:16', null, '296', '38', '276', null, '533', '4');
INSERT INTO `work` VALUES ('535', 'sendToNextDepartment', null, '2018-01-16 16:09:35', '', '', null, '', '2018-01-16 16:10:16', null, '297', '521', '362', '536', null, '3');
INSERT INTO `work` VALUES ('536', 'done', null, '2018-01-16 16:10:16', '', '', null, '', '2018-01-17 10:52:29', null, '297', '38', '276', null, '535', '4');
INSERT INTO `work` VALUES ('537', 'sendToNextDepartment', null, '2018-01-16 16:13:48', '', '', null, '', '2018-01-16 16:17:21', null, '298', '522', '363', '538', null, '3');
INSERT INTO `work` VALUES ('538', 'done', null, '2018-01-16 16:17:21', '', '', null, '', '2018-01-17 10:52:42', null, '298', '38', '276', null, '537', '4');
INSERT INTO `work` VALUES ('539', 'done', null, '2018-01-16 18:57:14', '', '', null, '', '2018-01-17 10:53:04', null, '273', '38', '276', null, '487', '4');
INSERT INTO `work` VALUES ('540', 'sendToNextDepartment', null, '2018-01-17 10:06:13', '', '', null, '', '2018-01-17 10:13:20', null, '299', '477', '321', '541', null, '3');
INSERT INTO `work` VALUES ('541', 'done', null, '2018-01-17 10:13:20', '', '', '内容不全', '', '2018-01-18 09:11:00', null, '299', '24', '22', null, '540', '4');
INSERT INTO `work` VALUES ('542', 'sendToNextDepartment', null, '2018-01-17 10:15:05', '', '', null, '', '2018-01-17 10:20:59', null, '300', '477', '321', '543', null, '3');
INSERT INTO `work` VALUES ('543', 'done', null, '2018-01-17 10:20:59', '', '', '内容不全', '', '2018-01-18 09:10:47', null, '300', '24', '22', null, '542', '4');
INSERT INTO `work` VALUES ('544', 'sendToNextDepartment', null, '2018-01-17 10:22:20', '', '', null, '', '2018-01-17 10:37:08', null, '301', '477', '321', '559', null, '3');
INSERT INTO `work` VALUES ('545', 'sendToNextDepartment', null, '2018-01-17 10:25:05', '', '', null, '', '2018-01-17 10:36:45', null, '302', '477', '321', '558', null, '3');
INSERT INTO `work` VALUES ('546', 'sendToNextDepartment', null, '2018-01-17 10:26:30', '', '', null, '', '2018-01-17 10:36:27', null, '303', '477', '321', '557', null, '3');
INSERT INTO `work` VALUES ('547', 'sendToNextDepartment', null, '2018-01-17 10:27:36', '', '', null, '', '2018-01-17 10:36:17', null, '304', '477', '321', '556', null, '3');
INSERT INTO `work` VALUES ('548', 'sendToNextDepartment', null, '2018-01-17 10:28:37', '', '', null, '', '2018-01-17 10:36:07', null, '305', '477', '321', '555', null, '3');
INSERT INTO `work` VALUES ('549', 'sendToNextDepartment', null, '2018-01-17 10:29:56', '', '', null, '', '2018-01-17 10:35:57', null, '306', '477', '321', '554', null, '3');
INSERT INTO `work` VALUES ('550', 'sendToNextDepartment', null, '2018-01-17 10:31:10', '', '', null, '', '2018-01-17 10:35:47', null, '307', '477', '321', '553', null, '3');
INSERT INTO `work` VALUES ('551', 'sendToNextDepartment', null, '2018-01-17 10:34:19', '', '', null, '', '2018-01-17 10:35:27', null, '308', '477', '321', '552', null, '3');
INSERT INTO `work` VALUES ('552', 'done', null, '2018-01-17 10:35:27', '', '', '内容不全', '', '2018-01-18 09:10:31', null, '308', '24', '22', null, '551', '4');
INSERT INTO `work` VALUES ('553', 'done', null, '2018-01-17 10:35:47', '', '', '内容不全', '', '2018-01-18 09:10:17', null, '307', '24', '22', null, '550', '4');
INSERT INTO `work` VALUES ('554', 'done', null, '2018-01-17 10:35:57', '', '', '内容不全', '', '2018-01-18 09:11:17', null, '306', '24', '22', null, '549', '4');
INSERT INTO `work` VALUES ('555', 'done', null, '2018-01-17 10:36:07', '', '', '内容不全', '', '2018-01-18 09:11:31', null, '305', '24', '22', null, '548', '4');
INSERT INTO `work` VALUES ('556', 'done', null, '2018-01-17 10:36:17', '', '', '内容不全', '', '2018-01-18 09:11:48', null, '304', '24', '22', null, '547', '4');
INSERT INTO `work` VALUES ('557', 'done', null, '2018-01-17 10:36:27', '', '', '内容不全', '', '2018-01-18 09:12:10', null, '303', '24', '22', null, '546', '4');
INSERT INTO `work` VALUES ('558', 'done', null, '2018-01-17 10:36:45', '', '', '内容不全', '', '2018-01-18 09:12:25', null, '302', '24', '22', null, '545', '4');
INSERT INTO `work` VALUES ('559', 'done', null, '2018-01-17 10:37:08', '', '', '内容不全', '', '2018-01-18 09:09:17', null, '301', '24', '22', null, '544', '4');
INSERT INTO `work` VALUES ('560', 'sendToNextDepartment', null, '2018-01-17 11:04:05', '', '', null, '', '2018-01-17 11:10:46', null, '309', '477', '321', '565', null, '3');
INSERT INTO `work` VALUES ('561', 'sendToNextDepartment', null, '2018-01-17 11:08:04', '', '', null, '', '2018-01-17 11:10:35', null, '310', '477', '321', '564', null, '3');
INSERT INTO `work` VALUES ('562', 'sendToNextDepartment', null, '2018-01-17 11:09:45', '', '', null, '', '2018-01-17 11:10:21', null, '311', '477', '321', '563', null, '3');
INSERT INTO `work` VALUES ('563', 'done', null, '2018-01-17 11:10:21', '', '', '内容不全', '', '2018-01-18 09:10:01', null, '311', '24', '22', null, '562', '4');
INSERT INTO `work` VALUES ('564', 'done', null, '2018-01-17 11:10:35', '', '', '内容不全', '', '2018-01-18 09:09:00', null, '310', '24', '22', null, '561', '4');
INSERT INTO `work` VALUES ('565', 'done', null, '2018-01-17 11:10:46', '', '', '内容不全', '', '2018-01-18 09:08:28', null, '309', '24', '22', null, '560', '4');
INSERT INTO `work` VALUES ('566', 'sendToNextDepartment', null, '2018-01-17 14:14:27', '', '', null, '', '2018-01-17 14:53:24', null, '312', '268', '193', '567', null, '3');
INSERT INTO `work` VALUES ('567', 'done', null, '2018-01-17 14:53:24', '', '', null, '', '2018-01-17 14:57:50', null, '312', '26', '24', null, '566', '4');
INSERT INTO `work` VALUES ('568', 'done', null, '2018-01-17 15:10:05', '', '', null, '', '2018-01-17 15:10:52', null, '127', '9', '8', null, '238', '4');
INSERT INTO `work` VALUES ('569', 'sendToNextDepartment', null, '2018-01-17 15:54:12', '', '', null, '', '2018-01-18 08:11:49', null, '313', '506', '347', '579', null, '3');
INSERT INTO `work` VALUES ('570', 'sendToNextDepartment', null, '2018-01-17 15:57:25', '', '', null, '', '2018-01-18 08:11:24', null, '314', '506', '347', '578', null, '3');
INSERT INTO `work` VALUES ('571', null, null, '2018-01-17 16:06:28', '', '', null, '', '2018-01-17 16:06:30', null, '315', '497', '339', null, null, '3');
INSERT INTO `work` VALUES ('572', 'sendToNextDepartment', null, '2018-01-17 16:07:34', '', '', null, '', '2018-01-20 17:23:55', null, '316', '497', '339', '623', null, '3');
INSERT INTO `work` VALUES ('573', 'sendToNextDepartment', null, '2018-01-17 16:10:08', '', '', null, '', '2018-01-20 17:29:57', null, '317', '497', '339', '624', null, '3');
INSERT INTO `work` VALUES ('574', 'sendToNextDepartment', null, '2018-01-17 16:33:51', '', '', null, '', '2018-01-17 16:34:11', null, '318', '544', '375', '575', null, '3');
INSERT INTO `work` VALUES ('575', 'done', null, '2018-01-17 16:34:11', '', '', null, '', '2018-01-17 16:34:42', null, '318', '78', '60', null, '574', '4');
INSERT INTO `work` VALUES ('576', 'sendToNextDepartment', null, '2018-01-17 16:49:10', '', '', null, '', '2018-01-17 16:49:24', null, '319', '544', '375', '577', null, '3');
INSERT INTO `work` VALUES ('577', 'done', null, '2018-01-17 16:49:24', '', '', null, '', '2018-01-17 16:49:49', null, '319', '78', '60', null, '576', '4');
INSERT INTO `work` VALUES ('578', 'done', null, '2018-01-18 08:11:24', '', '', null, '', '2018-01-18 09:27:35', null, '314', '38', '276', null, '570', '4');
INSERT INTO `work` VALUES ('579', 'done', null, '2018-01-18 08:11:49', '', '', null, '', '2018-01-18 09:27:47', null, '313', '38', '276', null, '569', '4');
INSERT INTO `work` VALUES ('580', 'sendToNextDepartment', null, '2018-01-18 09:00:56', '', '', null, '', '2018-01-18 09:01:38', null, '320', '477', '321', '581', null, '3');
INSERT INTO `work` VALUES ('581', 'done', null, '2018-01-18 09:01:38', '', '', '予以备案', '', '2018-01-18 09:07:34', null, '320', '24', '22', null, '580', '4');
INSERT INTO `work` VALUES ('582', 'sendToNextDepartment', null, '2018-01-18 09:05:02', '', '', null, '', '2018-01-18 09:05:12', null, '321', '477', '321', '583', null, '3');
INSERT INTO `work` VALUES ('583', 'done', null, '2018-01-18 09:05:12', '', '', '内容不全', '', '2018-01-18 09:14:46', null, '321', '24', '22', null, '582', '4');
INSERT INTO `work` VALUES ('584', 'sendToNextDepartment', null, '2018-01-18 09:06:51', '', '', null, '', '2018-01-18 09:06:59', null, '322', '477', '321', '585', null, '3');
INSERT INTO `work` VALUES ('585', 'done', null, '2018-01-18 09:06:59', '', '', '予以备案', '', '2018-01-18 09:14:03', null, '322', '24', '22', null, '584', '4');
INSERT INTO `work` VALUES ('586', 'sendToNextDepartment', null, '2018-01-18 09:08:09', '', '', null, '', '2018-01-18 09:08:17', null, '323', '477', '321', '587', null, '3');
INSERT INTO `work` VALUES ('587', 'done', null, '2018-01-18 09:08:17', '', '', '予以备案', '', '2018-01-18 09:13:39', null, '323', '24', '22', null, '586', '4');
INSERT INTO `work` VALUES ('588', 'sendToNextDepartment', null, '2018-01-18 09:09:36', '', '', null, '', '2018-01-18 09:09:49', null, '324', '477', '321', '589', null, '3');
INSERT INTO `work` VALUES ('589', 'done', null, '2018-01-18 09:09:49', '', '', '予以备案', '', '2018-01-18 09:13:12', null, '324', '24', '22', null, '588', '4');
INSERT INTO `work` VALUES ('590', null, null, '2018-01-18 09:38:29', '', '', null, '', '2018-01-18 09:38:29', null, '325', '489', null, null, null, '3');
INSERT INTO `work` VALUES ('591', 'sendToNextDepartment', null, '2018-01-18 09:49:48', '', '', null, '', '2018-01-18 09:49:58', null, '326', '477', '321', '592', null, '3');
INSERT INTO `work` VALUES ('592', 'done', null, '2018-01-18 09:49:58', '', '', '予以备案', '', '2018-01-18 10:13:27', null, '326', '24', '22', null, '591', '4');
INSERT INTO `work` VALUES ('593', 'sendToNextDepartment', null, '2018-01-18 09:51:06', '', '', null, '', '2018-01-18 09:51:13', null, '327', '477', '321', '594', null, '3');
INSERT INTO `work` VALUES ('594', 'done', null, '2018-01-18 09:51:13', '', '', '予以备案', '', '2018-01-18 10:13:55', null, '327', '24', '22', null, '593', '4');
INSERT INTO `work` VALUES ('595', 'sendToNextDepartment', null, '2018-01-18 09:52:16', '', '', null, '', '2018-01-18 09:52:23', null, '328', '477', '321', '596', null, '3');
INSERT INTO `work` VALUES ('596', 'done', null, '2018-01-18 09:52:23', '', '', '予以备案', '', '2018-01-18 10:15:36', null, '328', '24', '22', null, '595', '4');
INSERT INTO `work` VALUES ('597', 'sendToNextDepartment', null, '2018-01-18 09:53:38', '', '', null, '', '2018-01-18 09:53:45', null, '329', '477', '321', '598', null, '3');
INSERT INTO `work` VALUES ('598', 'done', null, '2018-01-18 09:53:45', '', '', '予以备案', '', '2018-01-18 10:16:02', null, '329', '24', '22', null, '597', '4');
INSERT INTO `work` VALUES ('599', 'sendToNextDepartment', null, '2018-01-18 09:54:45', '', '', null, '', '2018-01-18 09:54:52', null, '330', '477', '321', '600', null, '3');
INSERT INTO `work` VALUES ('600', 'done', null, '2018-01-18 09:54:52', '', '', '予以备案', '', '2018-01-18 10:16:29', null, '330', '24', '22', null, '599', '4');
INSERT INTO `work` VALUES ('601', 'sendToNextDepartment', null, '2018-01-18 09:55:59', '', '', null, '', '2018-01-18 09:57:22', null, '331', '248', '180', '604', null, '3');
INSERT INTO `work` VALUES ('602', 'sendToNextDepartment', null, '2018-01-18 09:56:08', '', '', null, '', '2018-01-18 09:56:14', null, '332', '477', '321', '603', null, '3');
INSERT INTO `work` VALUES ('603', 'done', null, '2018-01-18 09:56:14', '', '', '予以备案', '', '2018-01-18 10:16:52', null, '332', '24', '22', null, '602', '4');
INSERT INTO `work` VALUES ('604', 'done', null, '2018-01-18 09:57:22', '', '', '退回', '', '2018-01-18 10:12:26', null, '331', '24', '22', null, '601', '4');
INSERT INTO `work` VALUES ('605', 'sendToNextDepartment', null, '2018-01-18 09:57:25', '', '', null, '', '2018-01-18 09:57:31', null, '333', '477', '321', '606', null, '3');
INSERT INTO `work` VALUES ('606', 'done', null, '2018-01-18 09:57:31', '', '', '予以备案', '', '2018-01-18 10:17:19', null, '333', '24', '22', null, '605', '4');
INSERT INTO `work` VALUES ('607', 'done', null, '2018-01-18 09:58:42', '', '', '予以备案', '', '2018-01-18 10:00:21', null, '129', '24', '22', null, '241', '4');
INSERT INTO `work` VALUES ('608', 'sendToNextDepartment', null, '2018-01-18 09:58:45', '', '', null, '', '2018-01-18 09:58:52', null, '334', '477', '321', '609', null, '3');
INSERT INTO `work` VALUES ('609', 'done', null, '2018-01-18 09:58:52', '', '', '予以备案', '', '2018-01-18 10:17:40', null, '334', '24', '22', null, '608', '4');
INSERT INTO `work` VALUES ('610', 'sendToNextDepartment', null, '2018-01-18 10:00:09', '', '', null, '', '2018-01-18 10:00:18', null, '335', '477', '321', '611', null, '3');
INSERT INTO `work` VALUES ('611', 'done', null, '2018-01-18 10:00:18', '', '', '予以备案', '', '2018-01-18 10:13:01', null, '335', '24', '22', null, '610', '4');
INSERT INTO `work` VALUES ('612', 'sendToNextDepartment', null, '2018-01-18 15:50:04', '', '', null, '', '2018-01-21 14:56:12', null, '336', '498', '340', '629', null, '3');
INSERT INTO `work` VALUES ('613', 'sendToNextDepartment', null, '2018-01-18 16:15:46', '', '', null, '', '2018-01-21 14:56:02', null, '337', '498', '340', '628', null, '3');
INSERT INTO `work` VALUES ('614', 'sendToNextDepartment', null, '2018-01-18 16:19:58', '', '', null, '', '2018-01-21 14:55:54', null, '338', '498', '340', '627', null, '3');
INSERT INTO `work` VALUES ('615', 'sendToNextDepartment', null, '2018-01-18 16:31:33', '', '', null, '', '2018-01-18 16:37:27', null, '339', '405', '310', '616', null, '3');
INSERT INTO `work` VALUES ('616', 'done', null, '2018-01-18 16:37:27', '', '', '退回', '', '2018-01-19 14:23:19', null, '339', '24', '22', null, '615', '4');
INSERT INTO `work` VALUES ('617', 'sendToNextDepartment', null, '2018-01-20 16:29:16', '', '', null, '', '2018-01-20 17:06:21', null, '340', '537', '374', '622', null, '3');
INSERT INTO `work` VALUES ('618', 'sendToNextDepartment', null, '2018-01-20 16:55:13', '', '', null, '', '2018-01-20 17:06:10', null, '341', '537', '374', '621', null, '3');
INSERT INTO `work` VALUES ('619', 'sendToNextDepartment', null, '2018-01-20 17:05:43', '', '', null, '', '2018-01-20 17:06:00', null, '342', '537', '374', '620', null, '3');
INSERT INTO `work` VALUES ('620', 'done', null, '2018-01-20 17:06:00', '', '', null, '', '2018-01-22 09:50:56', null, '342', '78', '60', null, '619', '4');
INSERT INTO `work` VALUES ('621', 'done', null, '2018-01-20 17:06:10', '', '', null, '', '2018-01-22 09:50:26', null, '341', '78', '60', null, '618', '4');
INSERT INTO `work` VALUES ('622', 'done', null, '2018-01-20 17:06:21', '', '', null, '', '2018-01-22 09:50:04', null, '340', '78', '60', null, '617', '4');
INSERT INTO `work` VALUES ('623', 'done', null, '2018-01-20 17:23:55', '', '', null, '', '2018-01-22 09:10:16', null, '316', '38', '276', null, '572', '4');
INSERT INTO `work` VALUES ('624', 'done', null, '2018-01-20 17:29:57', '', '', null, '', '2018-01-22 09:10:37', null, '317', '38', '276', null, '573', '4');
INSERT INTO `work` VALUES ('625', 'sendToNextDepartment', null, '2018-01-21 14:43:45', '', '', null, '', '2018-01-21 14:44:16', null, '343', '498', '340', '626', null, '3');
INSERT INTO `work` VALUES ('626', 'done', null, '2018-01-21 14:44:16', '', '', null, '', '2018-01-22 09:12:07', null, '343', '38', '276', null, '625', '4');
INSERT INTO `work` VALUES ('627', 'done', null, '2018-01-21 14:55:54', '', '', null, '', '2018-01-22 09:12:52', null, '338', '38', '276', null, '614', '4');
INSERT INTO `work` VALUES ('628', 'done', null, '2018-01-21 14:56:02', '', '', null, '', '2018-01-22 09:13:13', null, '337', '38', '276', null, '613', '4');
INSERT INTO `work` VALUES ('629', 'done', null, '2018-01-21 14:56:12', '', '', null, '', '2018-01-22 09:13:26', null, '336', '38', '276', null, '612', '4');
INSERT INTO `work` VALUES ('630', 'sendToNextDepartment', null, '2018-01-22 11:05:48', '', '', null, '', '2018-01-22 11:09:11', null, '344', '405', '310', '631', null, '3');
INSERT INTO `work` VALUES ('631', 'done', null, '2018-01-22 11:09:11', '', '', '予以备案', '', '2018-01-22 14:26:56', null, '344', '24', '22', null, '630', '4');
INSERT INTO `work` VALUES ('632', 'sendToNextDepartment', null, '2018-01-22 15:46:20', '', '', null, '', '2018-01-22 15:46:39', null, '345', '479', '323', '633', null, '3');
INSERT INTO `work` VALUES ('633', 'done', null, '2018-01-22 15:46:39', '', '', '予以备案', '', '2018-01-23 15:46:54', null, '345', '24', '22', null, '632', '4');
INSERT INTO `work` VALUES ('634', 'sendToNextDepartment', null, '2018-01-22 16:07:43', '', '', null, '', '2018-01-22 16:07:52', null, '346', '479', '323', '635', null, '3');
INSERT INTO `work` VALUES ('635', 'done', null, '2018-01-22 16:07:52', '', '', '予以备案', '', '2018-01-23 15:45:35', null, '346', '24', '22', null, '634', '4');
INSERT INTO `work` VALUES ('636', 'sendToNextDepartment', null, '2018-01-22 16:14:08', '', '', null, '', '2018-01-22 16:19:23', null, '347', '479', '323', '637', null, '3');
INSERT INTO `work` VALUES ('637', 'done', null, '2018-01-22 16:19:23', '', '', '退回', '', '2018-01-23 15:45:08', null, '347', '24', '22', null, '636', '4');
INSERT INTO `work` VALUES ('638', 'sendToNextDepartment', null, '2018-01-22 16:22:56', '', '', null, '', '2018-01-22 16:27:41', null, '348', '479', '323', '639', null, '3');
INSERT INTO `work` VALUES ('639', 'done', null, '2018-01-22 16:27:40', '', '', '退回', '', '2018-01-23 15:44:47', null, '348', '24', '22', null, '638', '4');
INSERT INTO `work` VALUES ('640', 'sendToNextDepartment', null, '2018-01-22 16:36:01', '', '', null, '', '2018-01-22 16:36:06', null, '349', '479', '323', '641', null, '3');
INSERT INTO `work` VALUES ('641', 'done', null, '2018-01-22 16:36:06', '', '', '予以备案', '', '2018-01-23 15:44:30', null, '349', '24', '22', null, '640', '4');
INSERT INTO `work` VALUES ('642', 'sendToNextDepartment', null, '2018-01-23 10:50:34', '', '', null, '', '2018-01-23 10:50:41', null, '350', '498', '340', '643', null, '3');
INSERT INTO `work` VALUES ('643', 'done', null, '2018-01-23 10:50:41', '', '', null, '', '2018-01-23 14:55:58', null, '350', '38', '276', null, '642', '4');
INSERT INTO `work` VALUES ('644', null, null, '2018-01-23 14:03:58', '', '', null, '', '2018-01-24 08:37:41', null, '351', '558', '379', null, null, '3');
INSERT INTO `work` VALUES ('645', 'sendToNextDepartment', null, '2018-01-23 14:53:23', '', '', null, '', '2018-01-23 15:02:03', null, '352', '554', '378', '648', null, '3');
INSERT INTO `work` VALUES ('646', 'sendToNextDepartment', null, '2018-01-23 15:01:02', '', '', null, '', '2018-01-23 15:01:48', null, '353', '554', '378', '647', null, '3');
INSERT INTO `work` VALUES ('647', 'done', null, '2018-01-23 15:01:48', '', '', null, '', '2018-01-24 09:16:37', null, '353', '38', '276', null, '646', '4');
INSERT INTO `work` VALUES ('648', 'done', null, '2018-01-23 15:02:03', '', '', null, '', '2018-01-24 09:16:25', null, '352', '38', '276', null, '645', '4');
INSERT INTO `work` VALUES ('649', null, null, '2018-01-23 15:03:19', '', '', null, '', '2018-01-23 15:03:24', null, '354', '42', '34', null, null, '3');
INSERT INTO `work` VALUES ('650', null, null, '2018-01-23 15:07:54', '', '', null, '', '2018-01-23 15:07:54', null, '355', '479', null, null, null, '3');
INSERT INTO `work` VALUES ('651', 'sendToNextDepartment', null, '2018-01-23 16:16:26', '', '', null, '', '2018-01-24 08:42:16', null, '356', '558', '379', '666', null, '3');
INSERT INTO `work` VALUES ('652', 'sendToNextDepartment', null, '2018-01-23 16:19:41', '', '', null, '', '2018-01-24 08:42:30', null, '357', '558', '379', '667', null, '3');
INSERT INTO `work` VALUES ('653', 'sendToNextDepartment', null, '2018-01-23 16:51:05', '', '', null, '', '2018-01-24 09:04:57', null, '358', '559', '380', '671', null, '3');
INSERT INTO `work` VALUES ('654', 'sendToNextDepartment', null, '2018-01-23 16:55:02', '', '', null, '', '2018-01-24 09:05:11', null, '359', '559', '380', '672', null, '3');
INSERT INTO `work` VALUES ('655', 'sendToNextDepartment', null, '2018-01-23 16:55:32', '', '', null, '', '2018-01-23 16:55:40', null, '360', '477', '321', '656', null, '3');
INSERT INTO `work` VALUES ('656', null, null, '2018-01-23 16:55:40', '', '', null, '', '2018-01-24 08:37:08', null, '360', '24', '22', null, '655', '4');
INSERT INTO `work` VALUES ('657', 'sendToNextDepartment', null, '2018-01-23 16:57:06', '', '', null, '', '2018-01-23 16:57:14', null, '361', '477', '321', '658', null, '3');
INSERT INTO `work` VALUES ('658', null, null, '2018-01-23 16:57:14', '', '', null, '', '2018-01-24 08:35:45', null, '361', '24', '22', null, '657', '4');
INSERT INTO `work` VALUES ('659', 'sendToNextDepartment', null, '2018-01-23 16:57:42', '', '', null, '', '2018-01-24 09:04:22', null, '362', '559', '380', '670', null, '3');
INSERT INTO `work` VALUES ('660', 'sendToNextDepartment', null, '2018-01-23 16:58:28', '', '', null, '', '2018-01-23 16:58:35', null, '363', '477', '321', '661', null, '3');
INSERT INTO `work` VALUES ('661', null, null, '2018-01-23 16:58:35', '', '', null, '', '2018-01-24 08:33:19', null, '363', '24', '22', null, '660', '4');
INSERT INTO `work` VALUES ('662', 'sendToNextDepartment', null, '2018-01-23 17:00:14', '', '', null, '', '2018-01-23 17:00:21', null, '364', '477', '321', '663', null, '3');
INSERT INTO `work` VALUES ('663', null, null, '2018-01-23 17:00:21', '', '', null, '', '2018-01-24 08:31:32', null, '364', '24', '22', null, '662', '4');
INSERT INTO `work` VALUES ('664', 'sendToNextDepartment', null, '2018-01-23 17:18:36', '', '', null, '', '2018-01-24 08:57:02', null, '365', '568', '381', '668', null, '3');
INSERT INTO `work` VALUES ('665', 'sendToNextDepartment', null, '2018-01-24 08:20:15', '', '', null, '', '2018-01-24 08:57:18', null, '366', '568', '381', '669', null, '3');
INSERT INTO `work` VALUES ('666', 'done', null, '2018-01-24 08:42:16', '', '', '重复申请，退回', '', '2018-01-24 09:43:53', null, '356', '24', '22', null, '651', '4');
INSERT INTO `work` VALUES ('667', 'done', null, '2018-01-24 08:42:30', '', '', '予以备案', '', '2018-01-24 08:51:04', null, '357', '24', '22', null, '652', '4');
INSERT INTO `work` VALUES ('668', 'done', null, '2018-01-24 08:57:02', '', '', null, '', '2018-01-24 08:59:20', null, '365', '22', '20', null, '664', '4');
INSERT INTO `work` VALUES ('669', 'done', null, '2018-01-24 08:57:18', '', '', null, '', '2018-01-24 08:58:55', null, '366', '22', '20', null, '665', '4');
INSERT INTO `work` VALUES ('670', null, null, '2018-01-24 09:04:22', '', '', null, '', '2018-01-24 09:04:22', null, '362', '22', null, null, '659', '4');
INSERT INTO `work` VALUES ('671', null, null, '2018-01-24 09:04:57', '', '', null, '', '2018-01-24 09:05:53', null, '358', '22', '20', null, '653', '4');
INSERT INTO `work` VALUES ('672', null, null, '2018-01-24 09:05:11', '', '', null, '', '2018-01-24 09:05:11', null, '359', '22', null, null, '654', '4');
INSERT INTO `work` VALUES ('673', 'sendToNextDepartment', null, '2018-01-24 09:22:16', '', '', null, '', '2018-01-24 09:38:39', null, '367', '509', '350', '676', null, '3');
INSERT INTO `work` VALUES ('674', null, null, '2018-01-24 09:26:59', '', '', null, '', '2018-01-24 09:28:46', null, '368', '568', '381', null, null, '3');
INSERT INTO `work` VALUES ('675', 'sendToNextDepartment', null, '2018-01-24 09:33:48', '', '', null, '', '2018-01-24 09:38:48', null, '369', '509', '350', '677', null, '3');
INSERT INTO `work` VALUES ('676', null, null, '2018-01-24 09:38:39', '', '', null, '', '2018-01-24 09:38:39', null, '367', '38', null, null, '673', '4');
INSERT INTO `work` VALUES ('677', null, null, '2018-01-24 09:38:48', '', '', null, '', '2018-01-24 09:38:48', null, '369', '38', null, null, '675', '4');

-- ----------------------------
-- Table structure for `workflow_node`
-- ----------------------------
DROP TABLE IF EXISTS `workflow_node`;
CREATE TABLE `workflow_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `form_url` varchar(255) DEFAULT NULL,
  `is_contain_son_district` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `department_type_id` bigint(20) DEFAULT NULL,
  `district_type_id` bigint(20) DEFAULT NULL,
  `pre_id` bigint(20) DEFAULT NULL,
  `workflow_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64if99kvie4knniqsij465gyl` (`create_user_id`),
  KEY `FKmt3nu3mj17erar3m4at571vns` (`department_type_id`),
  KEY `FKg2c0o3ii8kde3iajubytbks2q` (`district_type_id`),
  KEY `FKebcyddi4dh5i95oi538fr57by` (`pre_id`),
  KEY `FKtr39xvvnysyjw2e4nsplh7tui` (`workflow_type_id`),
  CONSTRAINT `FK64if99kvie4knniqsij465gyl` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKebcyddi4dh5i95oi538fr57by` FOREIGN KEY (`pre_id`) REFERENCES `workflow_node` (`id`),
  CONSTRAINT `FKg2c0o3ii8kde3iajubytbks2q` FOREIGN KEY (`district_type_id`) REFERENCES `district_type` (`id`),
  CONSTRAINT `FKmt3nu3mj17erar3m4at571vns` FOREIGN KEY (`department_type_id`) REFERENCES `department_type` (`id`),
  CONSTRAINT `FKtr39xvvnysyjw2e4nsplh7tui` FOREIGN KEY (`workflow_type_id`) REFERENCES `workflow_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of workflow_node
-- ----------------------------
INSERT INTO `workflow_node` VALUES ('1', null, null, '', '市属器具用户', null, null, '3', '2', null, '1');
INSERT INTO `workflow_node` VALUES ('2', null, null, '', '市级管理部门', null, null, '2', '2', '1', '1');
INSERT INTO `workflow_node` VALUES ('3', null, null, '', '区县级器具用户', null, null, '3', '1', null, '1');
INSERT INTO `workflow_node` VALUES ('4', null, null, '', '区县级管理部门', null, null, '2', '1', '3', '1');

-- ----------------------------
-- Table structure for `workflow_node_and_apply_field_access`
-- ----------------------------
DROP TABLE IF EXISTS `workflow_node_and_apply_field_access`;
CREATE TABLE `workflow_node_and_apply_field_access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_read` bit(1) DEFAULT NULL,
  `is_write` bit(1) DEFAULT NULL,
  `apply_field_id` bigint(20) DEFAULT NULL,
  `workflow_node_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4x9or94niuqqycmog2pnvgcp4` (`apply_field_id`),
  KEY `FK1a8lwpeu0ixeh6qgl4pg9jbd` (`workflow_node_id`),
  CONSTRAINT `FK1a8lwpeu0ixeh6qgl4pg9jbd` FOREIGN KEY (`workflow_node_id`) REFERENCES `workflow_node` (`id`),
  CONSTRAINT `FK4x9or94niuqqycmog2pnvgcp4` FOREIGN KEY (`apply_field_id`) REFERENCES `apply_field` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of workflow_node_and_apply_field_access
-- ----------------------------

-- ----------------------------
-- Table structure for `workflow_node_config`
-- ----------------------------
DROP TABLE IF EXISTS `workflow_node_config`;
CREATE TABLE `workflow_node_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `k` char(20) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `workflow_node_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjude57x5f1lh7a6bn8oiow2fq` (`workflow_node_id`),
  CONSTRAINT `FKjude57x5f1lh7a6bn8oiow2fq` FOREIGN KEY (`workflow_node_id`) REFERENCES `workflow_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of workflow_node_config
-- ----------------------------

-- ----------------------------
-- Table structure for `workflow_type`
-- ----------------------------
DROP TABLE IF EXISTS `workflow_type`;
CREATE TABLE `workflow_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pinyin` varchar(255) DEFAULT NULL,
  `update_time` bigint(20) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ryhxjrorhs5wbxu4ui3ljp0jb` (`name`),
  UNIQUE KEY `UK_gdnbfkwtwgko96p111guo95uk` (`pinyin`),
  KEY `FKfvcxuvq89wc13wjdke7t85xx5` (`create_user_id`),
  CONSTRAINT `FKfvcxuvq89wc13wjdke7t85xx5` FOREIGN KEY (`create_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of workflow_type
-- ----------------------------
INSERT INTO `workflow_type` VALUES ('1', null, '1.向上级管理部门提出申请；2.管理部门可以转给同区域或子区域的技术机构。3.技术机构可办结可返回给管理部门', '适用于器具用户新强检器具审批', 'qijuyonghuToGuanlibumen', null, null);
