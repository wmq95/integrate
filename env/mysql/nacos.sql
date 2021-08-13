SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES ('1', 'transport.type', 'SEATA_GROUP', 0x544350, 'b136ef5f6a01d816991fe3cf7a6ac763', '2021-03-23 10:42:15', '2021-03-23 10:42:15', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('2', 'transport.server', 'SEATA_GROUP', 0x4E494F, 'b6d9dfc0fb54277321cebc0fff55df2f', '2021-03-23 10:42:15', '2021-03-23 10:42:15', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('3', 'transport.heartbeat', 'SEATA_GROUP', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 10:42:16', '2021-03-23 10:42:16', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('4', 'transport.enableClientBatchSendRequest', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:16', '2021-03-23 10:42:16', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('5', 'transport.threadFactory.bossThreadPrefix', 'SEATA_GROUP', 0x4E65747479426F7373, '0f8db59a3b7f2823f38a70c308361836', '2021-03-23 10:42:17', '2021-03-23 10:42:17', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('6', 'transport.threadFactory.workerThreadPrefix', 'SEATA_GROUP', 0x4E657474795365727665724E494F576F726B6572, 'a78ec7ef5d1631754c4e72ae8a3e9205', '2021-03-23 10:42:17', '2021-03-23 10:42:17', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('7', 'transport.threadFactory.serverExecutorThreadPrefix', 'SEATA_GROUP', 0x4E6574747953657276657242697A48616E646C6572, '11a36309f3d9df84fa8b59cf071fa2da', '2021-03-23 10:42:18', '2021-03-23 10:42:18', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('8', 'transport.threadFactory.shareBossWorker', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:18', '2021-03-23 10:42:18', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('9', 'transport.threadFactory.clientSelectorThreadPrefix', 'SEATA_GROUP', 0x4E65747479436C69656E7453656C6563746F72, 'cd7ec5a06541e75f5a7913752322c3af', '2021-03-23 10:42:19', '2021-03-23 10:42:19', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('10', 'transport.threadFactory.clientSelectorThreadSize', 'SEATA_GROUP', 0x31, 'c4ca4238a0b923820dcc509a6f75849b', '2021-03-23 10:42:19', '2021-03-23 10:42:19', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('22', 'client.rm.lock.retryPolicyBranchRollbackOnConflict', 'SEATA_GROUP', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 10:42:24', '2021-03-23 10:42:24', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('23', 'client.rm.reportRetryCount', 'SEATA_GROUP', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 10:42:25', '2021-03-23 10:42:25', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('24', 'client.rm.tableMetaCheckEnable', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:25', '2021-03-23 10:42:25', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('25', 'client.rm.tableMetaCheckerInterval', 'SEATA_GROUP', 0x3630303030, '2b4226dd7ed6eb2d419b881f3ae9c97c', '2021-03-23 10:42:26', '2021-03-23 10:42:26', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('26', 'client.rm.sqlParserType', 'SEATA_GROUP', 0x6472756964, '3d650fb8a5df01600281d48c47c9fa60', '2021-03-23 10:42:26', '2021-03-23 10:42:26', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('27', 'client.rm.reportSuccessEnable', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:27', '2021-03-23 10:42:27', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('28', 'client.rm.sagaBranchRegisterEnable', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:27', '2021-03-23 10:42:27', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('29', 'client.tm.commitRetryCount', 'SEATA_GROUP', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 10:42:28', '2021-03-23 10:42:28', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('30', 'client.tm.rollbackRetryCount', 'SEATA_GROUP', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 10:42:28', '2021-03-23 10:42:28', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('31', 'client.tm.defaultGlobalTransactionTimeout', 'SEATA_GROUP', 0x3630303030, '2b4226dd7ed6eb2d419b881f3ae9c97c', '2021-03-23 10:42:29', '2021-03-23 10:42:29', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('32', 'client.tm.degradeCheck', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:29', '2021-03-23 10:42:29', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('33', 'client.tm.degradeCheckAllowTimes', 'SEATA_GROUP', 0x3130, 'd3d9446802a44259755d38e6d163e820', '2021-03-23 10:42:30', '2021-03-23 10:42:30', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('34', 'client.tm.degradeCheckPeriod', 'SEATA_GROUP', 0x32303030, '08f90c1a417155361a5c4b8d297e0d78', '2021-03-23 10:42:30', '2021-03-23 10:42:30', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('35', 'store.mode', 'SEATA_GROUP', 0x66696C65, '8c7dd922ad47494fc02c388e12c00eac', '2021-03-23 10:42:31', '2021-03-23 10:42:31', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('36', 'store.file.dir', 'SEATA_GROUP', 0x66696C655F73746F72652F64617461, '6a8dec07c44c33a8a9247cba5710bbb2', '2021-03-23 10:42:31', '2021-03-23 10:42:31', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('37', 'store.file.maxBranchSessionSize', 'SEATA_GROUP', 0x3136333834, 'c76fe1d8e08462434d800487585be217', '2021-03-23 10:42:32', '2021-03-23 10:42:32', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('38', 'store.file.maxGlobalSessionSize', 'SEATA_GROUP', 0x353132, '10a7cdd970fe135cf4f7bb55c0e3b59f', '2021-03-23 10:42:32', '2021-03-23 10:42:32', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('39', 'store.file.fileWriteBufferCacheSize', 'SEATA_GROUP', 0x3136333834, 'c76fe1d8e08462434d800487585be217', '2021-03-23 10:42:33', '2021-03-23 10:42:33', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('40', 'store.file.flushDiskMode', 'SEATA_GROUP', 0x6173796E63, '0df93e34273b367bb63bad28c94c78d5', '2021-03-23 10:42:33', '2021-03-23 10:42:33', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('41', 'store.file.sessionReloadReadSize', 'SEATA_GROUP', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 10:42:33', '2021-03-23 10:42:33', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('42', 'store.db.datasource', 'SEATA_GROUP', 0x6472756964, '3d650fb8a5df01600281d48c47c9fa60', '2021-03-23 10:42:34', '2021-03-23 10:42:34', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('43', 'store.db.dbType', 'SEATA_GROUP', 0x6D7973716C, '81c3b080dad537de7e10e0987a4bf52e', '2021-03-23 10:42:34', '2021-03-23 10:42:34', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('44', 'store.db.driverClassName', 'SEATA_GROUP', 0x636F6D2E6D7973716C2E6A6462632E447269766572, '683cf0c3a5a56cec94dfac94ca16d760', '2021-03-23 10:42:35', '2021-03-23 10:42:35', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('45', 'store.db.url', 'SEATA_GROUP', 0x6A6462633A6D7973716C3A2F2F6D793A373737372F73656174613F757365556E69636F64653D7472756526636861726163746572456E636F64696E673D757466382675736553534C3D747275652673657276657254696D657A6F6E653D417369612F5368616E67686169, 'ee10076c0f56aa51d946fc588b61fd72', '2021-03-23 10:42:35', '2021-03-23 13:27:15', null, '0:0:0:0:0:0:0:1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', '', '', '', '', '');
INSERT INTO `config_info` VALUES ('46', 'store.db.user', 'SEATA_GROUP', 0x726F6F74, '63a9f0ea7bb98050796b649e85481845', '2021-03-23 10:42:36', '2021-03-23 10:42:36', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('47', 'store.db.password', 'SEATA_GROUP', 0x313233343536, 'e10adc3949ba59abbe56e057f20f883e', '2021-03-23 10:42:36', '2021-03-23 10:42:36', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('48', 'store.db.minConn', 'SEATA_GROUP', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 10:42:37', '2021-03-23 10:42:37', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('49', 'store.db.maxConn', 'SEATA_GROUP', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-23 10:42:37', '2021-03-23 10:42:37', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('50', 'store.db.globalTable', 'SEATA_GROUP', 0x676C6F62616C5F7461626C65, '8b28fb6bb4c4f984df2709381f8eba2b', '2021-03-23 10:42:38', '2021-03-23 10:42:38', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('51', 'store.db.branchTable', 'SEATA_GROUP', 0x6272616E63685F7461626C65, '54bcdac38cf62e103fe115bcf46a660c', '2021-03-23 10:42:38', '2021-03-23 10:42:38', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('52', 'store.db.queryLimit', 'SEATA_GROUP', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 10:42:39', '2021-03-23 10:42:39', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('53', 'store.db.lockTable', 'SEATA_GROUP', 0x6C6F636B5F7461626C65, '55e0cae3b6dc6696b768db90098b8f2f', '2021-03-23 10:42:39', '2021-03-23 10:42:39', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('54', 'store.db.maxWait', 'SEATA_GROUP', 0x35303030, 'a35fe7f7fe8217b4369a0af4244d1fca', '2021-03-23 10:42:40', '2021-03-23 10:42:40', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('55', 'store.redis.mode', 'SEATA_GROUP', 0x73696E676C65, 'dd5c07036f2975ff4bce568b6511d3bc', '2021-03-23 10:42:40', '2021-03-23 10:42:40', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('56', 'store.redis.single.host', 'SEATA_GROUP', 0x3132372E302E302E31, 'f528764d624db129b32c21fbca0cb8d6', '2021-03-23 10:42:41', '2021-03-23 10:42:41', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('57', 'store.redis.single.port', 'SEATA_GROUP', 0x36333739, '92c3b916311a5517d9290576e3ea37ad', '2021-03-23 10:42:41', '2021-03-23 10:42:41', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('58', 'store.redis.maxConn', 'SEATA_GROUP', 0x3130, 'd3d9446802a44259755d38e6d163e820', '2021-03-23 10:42:42', '2021-03-23 10:42:42', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('59', 'store.redis.minConn', 'SEATA_GROUP', 0x31, 'c4ca4238a0b923820dcc509a6f75849b', '2021-03-23 10:42:43', '2021-03-23 10:42:43', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('60', 'store.redis.maxTotal', 'SEATA_GROUP', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 10:42:43', '2021-03-23 10:42:43', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('61', 'store.redis.database', 'SEATA_GROUP', 0x30, 'cfcd208495d565ef66e7dff9f98764da', '2021-03-23 10:42:44', '2021-03-23 10:42:44', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('62', 'store.redis.queryLimit', 'SEATA_GROUP', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 10:42:44', '2021-03-23 10:42:44', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('63', 'server.recovery.committingRetryPeriod', 'SEATA_GROUP', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 10:42:45', '2021-03-23 10:42:45', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('64', 'server.recovery.asynCommittingRetryPeriod', 'SEATA_GROUP', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 10:42:45', '2021-03-23 10:42:45', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('65', 'server.recovery.rollbackingRetryPeriod', 'SEATA_GROUP', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 10:42:46', '2021-03-23 10:42:46', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('66', 'server.recovery.timeoutRetryPeriod', 'SEATA_GROUP', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 10:42:46', '2021-03-23 10:42:46', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('67', 'server.maxCommitRetryTimeout', 'SEATA_GROUP', 0x2D31, '6bb61e3b7bce0931da574d19d1d82c88', '2021-03-23 10:42:47', '2021-03-23 10:42:47', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('68', 'server.maxRollbackRetryTimeout', 'SEATA_GROUP', 0x2D31, '6bb61e3b7bce0931da574d19d1d82c88', '2021-03-23 10:42:47', '2021-03-23 10:42:47', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('69', 'server.rollbackRetryTimeoutUnlockEnable', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:48', '2021-03-23 10:42:48', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('70', 'client.undo.dataValidation', 'SEATA_GROUP', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 10:42:48', '2021-03-23 10:42:48', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('71', 'client.undo.logSerialization', 'SEATA_GROUP', 0x6A61636B736F6E, 'b41779690b83f182acc67d6388c7bac9', '2021-03-23 10:42:48', '2021-03-23 10:42:48', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('72', 'client.undo.onlyCareUpdateColumns', 'SEATA_GROUP', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 10:42:49', '2021-03-23 10:42:49', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('73', 'server.undo.logSaveDays', 'SEATA_GROUP', 0x37, '8f14e45fceea167a5a36dedd4bea2543', '2021-03-23 10:42:49', '2021-03-23 10:42:49', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('74', 'server.undo.logDeletePeriod', 'SEATA_GROUP', 0x3836343030303030, 'f4c122804fe9076cb2710f55c3c6e346', '2021-03-23 10:42:50', '2021-03-23 10:42:50', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('75', 'client.undo.logTable', 'SEATA_GROUP', 0x756E646F5F6C6F67, '2842d229c24afe9e61437135e8306614', '2021-03-23 10:42:50', '2021-03-23 10:42:50', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('76', 'client.undo.compress.enable', 'SEATA_GROUP', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 10:42:51', '2021-03-23 10:42:51', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('77', 'client.undo.compress.type', 'SEATA_GROUP', 0x7A6970, 'adcdbd79a8d84175c229b192aadc02f2', '2021-03-23 10:42:51', '2021-03-23 10:42:51', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('78', 'client.undo.compress.threshold', 'SEATA_GROUP', 0x36346B, 'bd44a6458bdbff0b5cac721ba361f035', '2021-03-23 10:42:52', '2021-03-23 10:42:52', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('79', 'log.exceptionRate', 'SEATA_GROUP', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 10:42:52', '2021-03-23 10:42:52', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('80', 'transport.serialization', 'SEATA_GROUP', 0x7365617461, 'b943081c423b9a5416a706524ee05d40', '2021-03-23 10:42:52', '2021-03-23 10:42:52', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('81', 'transport.compressor', 'SEATA_GROUP', 0x6E6F6E65, '334c4a4c42fdb79d7ebc3e73b517e6f8', '2021-03-23 10:42:53', '2021-03-23 10:42:53', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('82', 'metrics.enabled', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 10:42:53', '2021-03-23 10:42:53', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('83', 'metrics.registryType', 'SEATA_GROUP', 0x636F6D70616374, '7cf74ca49c304df8150205fc915cd465', '2021-03-23 10:42:54', '2021-03-23 10:42:54', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('84', 'metrics.exporterList', 'SEATA_GROUP', 0x70726F6D657468657573, 'e4f00638b8a10e6994e67af2f832d51c', '2021-03-23 10:42:54', '2021-03-23 10:42:54', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('85', 'metrics.exporterPrometheusPort', 'SEATA_GROUP', 0x39383938, '7b9dc501afe4ee11c56a4831e20cee71', '2021-03-23 10:42:55', '2021-03-23 10:42:55', null, '127.0.0.1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('87', 'service.vgroupMapping.user-seata-service-group', 'DEFAULT_GROUP', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-23 13:29:44', '2021-03-23 13:29:44', null, '0:0:0:0:0:0:0:1', '', '7d1f4d3a-30b3-437b-afc7-77425bc392d0', null, null, null, 'text', null);
INSERT INTO `config_info` VALUES ('88', 'service.vgroupMapping.my_test_tx_group', 'SEATA_GROUP', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-24 10:04:09', '2021-03-24 15:21:51', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('89', 'service.default.grouplist', 'SEATA_GROUP', 0x3132372E302E302E313A38303931, 'c32ce0d3e264525dcdada751f98143a3', '2021-03-24 10:04:10', '2021-03-24 15:21:52', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('90', 'service.enableDegrade', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 10:04:11', '2021-03-24 15:21:53', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('91', 'service.disableGlobalTransaction', 'SEATA_GROUP', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 10:04:11', '2021-03-24 15:21:53', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('92', 'store.mode', 'SEATA_GROUP', 0x6462, 'd77d5e503ad1439f585ac494268b351b', '2021-03-24 10:04:12', '2021-03-26 09:28:29', null, '0:0:0:0:0:0:0:1', '', '', '', '', '', '', '');
INSERT INTO `config_info` VALUES ('93', 'store.db.datasource', 'SEATA_GROUP', 0x6472756964, '3d650fb8a5df01600281d48c47c9fa60', '2021-03-24 10:04:12', '2021-03-26 09:43:41', null, '0:0:0:0:0:0:0:1', '', '', '', '', '', '', '');
INSERT INTO `config_info` VALUES ('94', 'store.db.dbType', 'SEATA_GROUP', 0x6D7973716C, '81c3b080dad537de7e10e0987a4bf52e', '2021-03-24 10:04:13', '2021-03-24 15:21:56', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('95', 'store.db.driverClassName', 'SEATA_GROUP', 0x636F6D2E6D7973716C2E636A2E6A6462632E447269766572, '33763409bb7f4838bde4fae9540433e4', '2021-03-24 10:04:14', '2021-03-24 15:21:57', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('96', 'store.db.url', 'SEATA_GROUP', 0x6A6462633A6D7973716C3A2F2F6D793A373737372F73656174613F757365556E69636F64653D7472756526636861726163746572456E636F64696E673D757466382675736553534C3D747275652673657276657254696D657A6F6E653D417369612F5368616E67686169, 'ee10076c0f56aa51d946fc588b61fd72', '2021-03-24 10:04:14', '2021-03-24 15:21:58', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('97', 'store.db.user', 'SEATA_GROUP', 0x726F6F74, '63a9f0ea7bb98050796b649e85481845', '2021-03-24 10:04:15', '2021-03-24 15:21:59', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('98', 'store.db.password', 'SEATA_GROUP', 0x313233343536, 'e10adc3949ba59abbe56e057f20f883e', '2021-03-24 10:04:15', '2021-03-24 15:22:00', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('99', 'store.db.minConn', 'SEATA_GROUP', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-24 10:04:16', '2021-03-24 15:22:00', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('100', 'store.db.maxConn', 'SEATA_GROUP', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-24 10:04:17', '2021-03-24 15:22:01', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('101', 'store.db.globalTable', 'SEATA_GROUP', 0x676C6F62616C5F7461626C65, '8b28fb6bb4c4f984df2709381f8eba2b', '2021-03-24 10:04:18', '2021-03-24 15:22:02', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('102', 'store.db.branchTable', 'SEATA_GROUP', 0x6272616E63685F7461626C65, '54bcdac38cf62e103fe115bcf46a660c', '2021-03-24 10:04:18', '2021-03-24 15:22:03', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('103', 'store.db.queryLimit', 'SEATA_GROUP', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-24 10:04:19', '2021-03-24 15:22:04', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('104', 'store.db.lockTable', 'SEATA_GROUP', 0x6C6F636B5F7461626C65, '55e0cae3b6dc6696b768db90098b8f2f', '2021-03-24 10:04:19', '2021-03-24 15:22:05', null, '127.0.0.1', '', '', null, null, null, null, null);
INSERT INTO `config_info` VALUES ('105', 'store.db.maxWait', 'SEATA_GROUP', 0x35303030, 'a35fe7f7fe8217b4369a0af4244d1fca', '2021-03-24 10:04:20', '2021-03-24 15:22:06', null, '127.0.0.1', '', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`) USING BTREE,
  KEY `idx_gmt_modified` (`gmt_modified`) USING BTREE,
  KEY `idx_did` (`data_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES ('0', '1', 'transport.type', 'SEATA_GROUP', '', 0x544350, 'b136ef5f6a01d816991fe3cf7a6ac763', '2021-03-23 02:42:15', '2021-03-23 10:42:15', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '2', 'transport.server', 'SEATA_GROUP', '', 0x4E494F, 'b6d9dfc0fb54277321cebc0fff55df2f', '2021-03-23 02:42:15', '2021-03-23 10:42:15', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '3', 'transport.heartbeat', 'SEATA_GROUP', '', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 02:42:15', '2021-03-23 10:42:16', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '4', 'transport.enableClientBatchSendRequest', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:16', '2021-03-23 10:42:16', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '5', 'transport.threadFactory.bossThreadPrefix', 'SEATA_GROUP', '', 0x4E65747479426F7373, '0f8db59a3b7f2823f38a70c308361836', '2021-03-23 02:42:16', '2021-03-23 10:42:17', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '6', 'transport.threadFactory.workerThreadPrefix', 'SEATA_GROUP', '', 0x4E657474795365727665724E494F576F726B6572, 'a78ec7ef5d1631754c4e72ae8a3e9205', '2021-03-23 02:42:17', '2021-03-23 10:42:17', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '7', 'transport.threadFactory.serverExecutorThreadPrefix', 'SEATA_GROUP', '', 0x4E6574747953657276657242697A48616E646C6572, '11a36309f3d9df84fa8b59cf071fa2da', '2021-03-23 02:42:17', '2021-03-23 10:42:18', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '8', 'transport.threadFactory.shareBossWorker', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:18', '2021-03-23 10:42:18', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '9', 'transport.threadFactory.clientSelectorThreadPrefix', 'SEATA_GROUP', '', 0x4E65747479436C69656E7453656C6563746F72, 'cd7ec5a06541e75f5a7913752322c3af', '2021-03-23 02:42:18', '2021-03-23 10:42:19', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '10', 'transport.threadFactory.clientSelectorThreadSize', 'SEATA_GROUP', '', 0x31, 'c4ca4238a0b923820dcc509a6f75849b', '2021-03-23 02:42:19', '2021-03-23 10:42:19', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '11', 'transport.threadFactory.clientWorkerThreadPrefix', 'SEATA_GROUP', '', 0x4E65747479436C69656E74576F726B6572546872656164, '61cf4e69a56354cf72f46dc86414a57e', '2021-03-23 02:42:19', '2021-03-23 10:42:19', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '12', 'transport.threadFactory.bossThreadSize', 'SEATA_GROUP', '', 0x31, 'c4ca4238a0b923820dcc509a6f75849b', '2021-03-23 02:42:20', '2021-03-23 10:42:20', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '13', 'transport.threadFactory.workerThreadSize', 'SEATA_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-23 02:42:20', '2021-03-23 10:42:20', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '14', 'transport.shutdown.wait', 'SEATA_GROUP', '', 0x33, 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '2021-03-23 02:42:21', '2021-03-23 10:42:21', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '15', 'service.vgroupMapping.test', 'SEATA_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-23 02:42:21', '2021-03-23 10:42:21', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '16', 'service.default.grouplist', 'SEATA_GROUP', '', 0x3132372E302E302E313A38303931, 'c32ce0d3e264525dcdada751f98143a3', '2021-03-23 02:42:21', '2021-03-23 10:42:22', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '17', 'service.enableDegrade', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:22', '2021-03-23 10:42:22', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '18', 'service.disableGlobalTransaction', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:22', '2021-03-23 10:42:23', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '19', 'client.rm.asyncCommitBufferLimit', 'SEATA_GROUP', '', 0x3130303030, 'b7a782741f667201b54880c925faec4b', '2021-03-23 02:42:23', '2021-03-23 10:42:23', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '20', 'client.rm.lock.retryInterval', 'SEATA_GROUP', '', 0x3130, 'd3d9446802a44259755d38e6d163e820', '2021-03-23 02:42:23', '2021-03-23 10:42:24', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '21', 'client.rm.lock.retryTimes', 'SEATA_GROUP', '', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-23 02:42:24', '2021-03-23 10:42:24', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '22', 'client.rm.lock.retryPolicyBranchRollbackOnConflict', 'SEATA_GROUP', '', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 02:42:24', '2021-03-23 10:42:24', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '23', 'client.rm.reportRetryCount', 'SEATA_GROUP', '', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 02:42:24', '2021-03-23 10:42:25', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '24', 'client.rm.tableMetaCheckEnable', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:25', '2021-03-23 10:42:25', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '25', 'client.rm.tableMetaCheckerInterval', 'SEATA_GROUP', '', 0x3630303030, '2b4226dd7ed6eb2d419b881f3ae9c97c', '2021-03-23 02:42:25', '2021-03-23 10:42:26', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '26', 'client.rm.sqlParserType', 'SEATA_GROUP', '', 0x6472756964, '3d650fb8a5df01600281d48c47c9fa60', '2021-03-23 02:42:26', '2021-03-23 10:42:26', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '27', 'client.rm.reportSuccessEnable', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:26', '2021-03-23 10:42:27', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '28', 'client.rm.sagaBranchRegisterEnable', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:27', '2021-03-23 10:42:27', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '29', 'client.tm.commitRetryCount', 'SEATA_GROUP', '', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 02:42:27', '2021-03-23 10:42:28', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '30', 'client.tm.rollbackRetryCount', 'SEATA_GROUP', '', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 02:42:28', '2021-03-23 10:42:28', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '31', 'client.tm.defaultGlobalTransactionTimeout', 'SEATA_GROUP', '', 0x3630303030, '2b4226dd7ed6eb2d419b881f3ae9c97c', '2021-03-23 02:42:28', '2021-03-23 10:42:29', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '32', 'client.tm.degradeCheck', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:29', '2021-03-23 10:42:29', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '33', 'client.tm.degradeCheckAllowTimes', 'SEATA_GROUP', '', 0x3130, 'd3d9446802a44259755d38e6d163e820', '2021-03-23 02:42:29', '2021-03-23 10:42:30', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '34', 'client.tm.degradeCheckPeriod', 'SEATA_GROUP', '', 0x32303030, '08f90c1a417155361a5c4b8d297e0d78', '2021-03-23 02:42:30', '2021-03-23 10:42:30', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '35', 'store.mode', 'SEATA_GROUP', '', 0x66696C65, '8c7dd922ad47494fc02c388e12c00eac', '2021-03-23 02:42:30', '2021-03-23 10:42:31', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '36', 'store.file.dir', 'SEATA_GROUP', '', 0x66696C655F73746F72652F64617461, '6a8dec07c44c33a8a9247cba5710bbb2', '2021-03-23 02:42:31', '2021-03-23 10:42:31', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '37', 'store.file.maxBranchSessionSize', 'SEATA_GROUP', '', 0x3136333834, 'c76fe1d8e08462434d800487585be217', '2021-03-23 02:42:31', '2021-03-23 10:42:32', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '38', 'store.file.maxGlobalSessionSize', 'SEATA_GROUP', '', 0x353132, '10a7cdd970fe135cf4f7bb55c0e3b59f', '2021-03-23 02:42:32', '2021-03-23 10:42:32', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '39', 'store.file.fileWriteBufferCacheSize', 'SEATA_GROUP', '', 0x3136333834, 'c76fe1d8e08462434d800487585be217', '2021-03-23 02:42:32', '2021-03-23 10:42:33', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '40', 'store.file.flushDiskMode', 'SEATA_GROUP', '', 0x6173796E63, '0df93e34273b367bb63bad28c94c78d5', '2021-03-23 02:42:33', '2021-03-23 10:42:33', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '41', 'store.file.sessionReloadReadSize', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 02:42:33', '2021-03-23 10:42:33', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '42', 'store.db.datasource', 'SEATA_GROUP', '', 0x6472756964, '3d650fb8a5df01600281d48c47c9fa60', '2021-03-23 02:42:34', '2021-03-23 10:42:34', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '43', 'store.db.dbType', 'SEATA_GROUP', '', 0x6D7973716C, '81c3b080dad537de7e10e0987a4bf52e', '2021-03-23 02:42:34', '2021-03-23 10:42:34', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '44', 'store.db.driverClassName', 'SEATA_GROUP', '', 0x636F6D2E6D7973716C2E6A6462632E447269766572, '683cf0c3a5a56cec94dfac94ca16d760', '2021-03-23 02:42:35', '2021-03-23 10:42:35', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '45', 'store.db.url', 'SEATA_GROUP', '', 0x6A6462633A6D7973716C3A2F2F6D793A373737372F74657374537072696E673F757365556E69636F64653D7472756526636861726163746572456E636F64696E673D757466382675736553534C3D747275652673657276657254696D657A6F6E653D417369612F5368616E67686169, 'fac59153647f7ecc8e6c384a50157590', '2021-03-23 02:42:35', '2021-03-23 10:42:35', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '46', 'store.db.user', 'SEATA_GROUP', '', 0x726F6F74, '63a9f0ea7bb98050796b649e85481845', '2021-03-23 02:42:35', '2021-03-23 10:42:36', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '47', 'store.db.password', 'SEATA_GROUP', '', 0x313233343536, 'e10adc3949ba59abbe56e057f20f883e', '2021-03-23 02:42:36', '2021-03-23 10:42:36', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '48', 'store.db.minConn', 'SEATA_GROUP', '', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-23 02:42:37', '2021-03-23 10:42:37', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '49', 'store.db.maxConn', 'SEATA_GROUP', '', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-23 02:42:37', '2021-03-23 10:42:37', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '50', 'store.db.globalTable', 'SEATA_GROUP', '', 0x676C6F62616C5F7461626C65, '8b28fb6bb4c4f984df2709381f8eba2b', '2021-03-23 02:42:38', '2021-03-23 10:42:38', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '51', 'store.db.branchTable', 'SEATA_GROUP', '', 0x6272616E63685F7461626C65, '54bcdac38cf62e103fe115bcf46a660c', '2021-03-23 02:42:38', '2021-03-23 10:42:38', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '52', 'store.db.queryLimit', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 02:42:39', '2021-03-23 10:42:39', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '53', 'store.db.lockTable', 'SEATA_GROUP', '', 0x6C6F636B5F7461626C65, '55e0cae3b6dc6696b768db90098b8f2f', '2021-03-23 02:42:39', '2021-03-23 10:42:39', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '54', 'store.db.maxWait', 'SEATA_GROUP', '', 0x35303030, 'a35fe7f7fe8217b4369a0af4244d1fca', '2021-03-23 02:42:40', '2021-03-23 10:42:40', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '55', 'store.redis.mode', 'SEATA_GROUP', '', 0x73696E676C65, 'dd5c07036f2975ff4bce568b6511d3bc', '2021-03-23 02:42:40', '2021-03-23 10:42:40', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '56', 'store.redis.single.host', 'SEATA_GROUP', '', 0x3132372E302E302E31, 'f528764d624db129b32c21fbca0cb8d6', '2021-03-23 02:42:41', '2021-03-23 10:42:41', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '57', 'store.redis.single.port', 'SEATA_GROUP', '', 0x36333739, '92c3b916311a5517d9290576e3ea37ad', '2021-03-23 02:42:41', '2021-03-23 10:42:41', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '58', 'store.redis.maxConn', 'SEATA_GROUP', '', 0x3130, 'd3d9446802a44259755d38e6d163e820', '2021-03-23 02:42:42', '2021-03-23 10:42:42', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '59', 'store.redis.minConn', 'SEATA_GROUP', '', 0x31, 'c4ca4238a0b923820dcc509a6f75849b', '2021-03-23 02:42:42', '2021-03-23 10:42:43', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '60', 'store.redis.maxTotal', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 02:42:43', '2021-03-23 10:42:43', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '61', 'store.redis.database', 'SEATA_GROUP', '', 0x30, 'cfcd208495d565ef66e7dff9f98764da', '2021-03-23 02:42:43', '2021-03-23 10:42:44', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '62', 'store.redis.queryLimit', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 02:42:44', '2021-03-23 10:42:44', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '63', 'server.recovery.committingRetryPeriod', 'SEATA_GROUP', '', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 02:42:44', '2021-03-23 10:42:45', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '64', 'server.recovery.asynCommittingRetryPeriod', 'SEATA_GROUP', '', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 02:42:45', '2021-03-23 10:42:45', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '65', 'server.recovery.rollbackingRetryPeriod', 'SEATA_GROUP', '', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 02:42:45', '2021-03-23 10:42:46', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '66', 'server.recovery.timeoutRetryPeriod', 'SEATA_GROUP', '', 0x31303030, 'a9b7ba70783b617e9998dc4dd82eb3c5', '2021-03-23 02:42:46', '2021-03-23 10:42:46', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '67', 'server.maxCommitRetryTimeout', 'SEATA_GROUP', '', 0x2D31, '6bb61e3b7bce0931da574d19d1d82c88', '2021-03-23 02:42:46', '2021-03-23 10:42:47', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '68', 'server.maxRollbackRetryTimeout', 'SEATA_GROUP', '', 0x2D31, '6bb61e3b7bce0931da574d19d1d82c88', '2021-03-23 02:42:47', '2021-03-23 10:42:47', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '69', 'server.rollbackRetryTimeoutUnlockEnable', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:47', '2021-03-23 10:42:48', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '70', 'client.undo.dataValidation', 'SEATA_GROUP', '', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 02:42:48', '2021-03-23 10:42:48', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '71', 'client.undo.logSerialization', 'SEATA_GROUP', '', 0x6A61636B736F6E, 'b41779690b83f182acc67d6388c7bac9', '2021-03-23 02:42:48', '2021-03-23 10:42:48', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '72', 'client.undo.onlyCareUpdateColumns', 'SEATA_GROUP', '', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 02:42:48', '2021-03-23 10:42:49', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '73', 'server.undo.logSaveDays', 'SEATA_GROUP', '', 0x37, '8f14e45fceea167a5a36dedd4bea2543', '2021-03-23 02:42:49', '2021-03-23 10:42:49', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '74', 'server.undo.logDeletePeriod', 'SEATA_GROUP', '', 0x3836343030303030, 'f4c122804fe9076cb2710f55c3c6e346', '2021-03-23 02:42:49', '2021-03-23 10:42:50', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '75', 'client.undo.logTable', 'SEATA_GROUP', '', 0x756E646F5F6C6F67, '2842d229c24afe9e61437135e8306614', '2021-03-23 02:42:50', '2021-03-23 10:42:50', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '76', 'client.undo.compress.enable', 'SEATA_GROUP', '', 0x74727565, 'b326b5062b2f0e69046810717534cb09', '2021-03-23 02:42:50', '2021-03-23 10:42:51', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '77', 'client.undo.compress.type', 'SEATA_GROUP', '', 0x7A6970, 'adcdbd79a8d84175c229b192aadc02f2', '2021-03-23 02:42:51', '2021-03-23 10:42:51', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '78', 'client.undo.compress.threshold', 'SEATA_GROUP', '', 0x36346B, 'bd44a6458bdbff0b5cac721ba361f035', '2021-03-23 02:42:51', '2021-03-23 10:42:52', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '79', 'log.exceptionRate', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-23 02:42:52', '2021-03-23 10:42:52', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '80', 'transport.serialization', 'SEATA_GROUP', '', 0x7365617461, 'b943081c423b9a5416a706524ee05d40', '2021-03-23 02:42:52', '2021-03-23 10:42:52', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '81', 'transport.compressor', 'SEATA_GROUP', '', 0x6E6F6E65, '334c4a4c42fdb79d7ebc3e73b517e6f8', '2021-03-23 02:42:53', '2021-03-23 10:42:53', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '82', 'metrics.enabled', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-23 02:42:53', '2021-03-23 10:42:53', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '83', 'metrics.registryType', 'SEATA_GROUP', '', 0x636F6D70616374, '7cf74ca49c304df8150205fc915cd465', '2021-03-23 02:42:53', '2021-03-23 10:42:54', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '84', 'metrics.exporterList', 'SEATA_GROUP', '', 0x70726F6D657468657573, 'e4f00638b8a10e6994e67af2f832d51c', '2021-03-23 02:42:54', '2021-03-23 10:42:54', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '85', 'metrics.exporterPrometheusPort', 'SEATA_GROUP', '', 0x39383938, '7b9dc501afe4ee11c56a4831e20cee71', '2021-03-23 02:42:54', '2021-03-23 10:42:55', null, '127.0.0.1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('16', '86', 'service.default.grouplist', 'SEATA_GROUP', '', 0x3132372E302E302E313A38303931, 'c32ce0d3e264525dcdada751f98143a3', '2021-03-23 05:15:21', '2021-03-23 13:15:21', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('45', '87', 'store.db.url', 'SEATA_GROUP', '', 0x6A6462633A6D7973716C3A2F2F6D793A373737372F74657374537072696E673F757365556E69636F64653D7472756526636861726163746572456E636F64696E673D757466382675736553534C3D747275652673657276657254696D657A6F6E653D417369612F5368616E67686169, 'fac59153647f7ecc8e6c384a50157590', '2021-03-23 05:27:15', '2021-03-23 13:27:15', null, '0:0:0:0:0:0:0:1', 'U', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '88', 'service.vgroupMapping.user-seata-service-group', 'DEFAULT_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-23 05:29:43', '2021-03-23 13:29:44', null, '0:0:0:0:0:0:0:1', 'I', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('0', '89', 'service.vgroupMapping.my_test_tx_group', 'SEATA_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-24 02:04:07', '2021-03-24 10:04:09', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '90', 'service.default.grouplist', 'SEATA_GROUP', '', 0x3132372E302E302E313A38303931, 'c32ce0d3e264525dcdada751f98143a3', '2021-03-24 02:04:08', '2021-03-24 10:04:10', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '91', 'service.enableDegrade', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 02:04:08', '2021-03-24 10:04:11', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '92', 'service.disableGlobalTransaction', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 02:04:09', '2021-03-24 10:04:11', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '93', 'store.mode', 'SEATA_GROUP', '', 0x66696C65, '8c7dd922ad47494fc02c388e12c00eac', '2021-03-24 02:04:09', '2021-03-24 10:04:12', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '94', 'store.db.datasource', 'SEATA_GROUP', '', 0x68696B617269, '80e0b2a2c1a1d6d47f9a9ff573c08c42', '2021-03-24 02:04:10', '2021-03-24 10:04:12', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '95', 'store.db.dbType', 'SEATA_GROUP', '', 0x6D7973716C, '81c3b080dad537de7e10e0987a4bf52e', '2021-03-24 02:04:11', '2021-03-24 10:04:13', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '96', 'store.db.driverClassName', 'SEATA_GROUP', '', 0x636F6D2E6D7973716C2E636A2E6A6462632E447269766572, '33763409bb7f4838bde4fae9540433e4', '2021-03-24 02:04:11', '2021-03-24 10:04:14', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '97', 'store.db.url', 'SEATA_GROUP', '', 0x6A6462633A6D7973716C3A2F2F6D793A373737372F73656174613F757365556E69636F64653D7472756526636861726163746572456E636F64696E673D757466382675736553534C3D747275652673657276657254696D657A6F6E653D417369612F5368616E67686169, 'ee10076c0f56aa51d946fc588b61fd72', '2021-03-24 02:04:12', '2021-03-24 10:04:14', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '98', 'store.db.user', 'SEATA_GROUP', '', 0x726F6F74, '63a9f0ea7bb98050796b649e85481845', '2021-03-24 02:04:12', '2021-03-24 10:04:15', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '99', 'store.db.password', 'SEATA_GROUP', '', 0x313233343536, 'e10adc3949ba59abbe56e057f20f883e', '2021-03-24 02:04:13', '2021-03-24 10:04:15', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '100', 'store.db.minConn', 'SEATA_GROUP', '', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-24 02:04:14', '2021-03-24 10:04:16', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '101', 'store.db.maxConn', 'SEATA_GROUP', '', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-24 02:04:15', '2021-03-24 10:04:17', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '102', 'store.db.globalTable', 'SEATA_GROUP', '', 0x676C6F62616C5F7461626C65, '8b28fb6bb4c4f984df2709381f8eba2b', '2021-03-24 02:04:15', '2021-03-24 10:04:18', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '103', 'store.db.branchTable', 'SEATA_GROUP', '', 0x6272616E63685F7461626C65, '54bcdac38cf62e103fe115bcf46a660c', '2021-03-24 02:04:16', '2021-03-24 10:04:18', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '104', 'store.db.queryLimit', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-24 02:04:17', '2021-03-24 10:04:19', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '105', 'store.db.lockTable', 'SEATA_GROUP', '', 0x6C6F636B5F7461626C65, '55e0cae3b6dc6696b768db90098b8f2f', '2021-03-24 02:04:17', '2021-03-24 10:04:19', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('0', '106', 'store.db.maxWait', 'SEATA_GROUP', '', 0x35303030, 'a35fe7f7fe8217b4369a0af4244d1fca', '2021-03-24 02:04:18', '2021-03-24 10:04:20', null, '0:0:0:0:0:0:0:1', 'I', '');
INSERT INTO `his_config_info` VALUES ('11', '107', 'transport.threadFactory.clientWorkerThreadPrefix', 'SEATA_GROUP', '', 0x4E65747479436C69656E74576F726B6572546872656164, '61cf4e69a56354cf72f46dc86414a57e', '2021-03-24 07:19:44', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('12', '108', 'transport.threadFactory.bossThreadSize', 'SEATA_GROUP', '', 0x31, 'c4ca4238a0b923820dcc509a6f75849b', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('13', '109', 'transport.threadFactory.workerThreadSize', 'SEATA_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('14', '110', 'transport.shutdown.wait', 'SEATA_GROUP', '', 0x33, 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('15', '111', 'service.vgroupMapping.test', 'SEATA_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('17', '112', 'service.enableDegrade', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('18', '113', 'service.disableGlobalTransaction', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('19', '114', 'client.rm.asyncCommitBufferLimit', 'SEATA_GROUP', '', 0x3130303030, 'b7a782741f667201b54880c925faec4b', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('20', '115', 'client.rm.lock.retryInterval', 'SEATA_GROUP', '', 0x3130, 'd3d9446802a44259755d38e6d163e820', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('21', '116', 'client.rm.lock.retryTimes', 'SEATA_GROUP', '', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-24 07:19:45', '2021-03-24 15:19:45', null, '0:0:0:0:0:0:0:1', 'D', '7d1f4d3a-30b3-437b-afc7-77425bc392d0');
INSERT INTO `his_config_info` VALUES ('88', '117', 'service.vgroupMapping.my_test_tx_group', 'SEATA_GROUP', '', 0x64656661756C74, 'c21f969b5f03d33d43e04f8f136e7682', '2021-03-24 07:21:50', '2021-03-24 15:21:51', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('89', '118', 'service.default.grouplist', 'SEATA_GROUP', '', 0x3132372E302E302E313A38303931, 'c32ce0d3e264525dcdada751f98143a3', '2021-03-24 07:21:51', '2021-03-24 15:21:52', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('90', '119', 'service.enableDegrade', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 07:21:52', '2021-03-24 15:21:53', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('91', '120', 'service.disableGlobalTransaction', 'SEATA_GROUP', '', 0x66616C7365, '68934a3e9455fa72420237eb05902327', '2021-03-24 07:21:53', '2021-03-24 15:21:53', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('92', '121', 'store.mode', 'SEATA_GROUP', '', 0x66696C65, '8c7dd922ad47494fc02c388e12c00eac', '2021-03-24 07:21:54', '2021-03-24 15:21:54', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('93', '122', 'store.db.datasource', 'SEATA_GROUP', '', 0x68696B617269, '80e0b2a2c1a1d6d47f9a9ff573c08c42', '2021-03-24 07:21:54', '2021-03-24 15:21:55', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('94', '123', 'store.db.dbType', 'SEATA_GROUP', '', 0x6D7973716C, '81c3b080dad537de7e10e0987a4bf52e', '2021-03-24 07:21:55', '2021-03-24 15:21:56', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('95', '124', 'store.db.driverClassName', 'SEATA_GROUP', '', 0x636F6D2E6D7973716C2E636A2E6A6462632E447269766572, '33763409bb7f4838bde4fae9540433e4', '2021-03-24 07:21:56', '2021-03-24 15:21:57', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('96', '125', 'store.db.url', 'SEATA_GROUP', '', 0x6A6462633A6D7973716C3A2F2F6D793A373737372F73656174613F757365556E69636F64653D7472756526636861726163746572456E636F64696E673D757466382675736553534C3D747275652673657276657254696D657A6F6E653D417369612F5368616E67686169, 'ee10076c0f56aa51d946fc588b61fd72', '2021-03-24 07:21:57', '2021-03-24 15:21:58', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('97', '126', 'store.db.user', 'SEATA_GROUP', '', 0x726F6F74, '63a9f0ea7bb98050796b649e85481845', '2021-03-24 07:21:58', '2021-03-24 15:21:59', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('98', '127', 'store.db.password', 'SEATA_GROUP', '', 0x313233343536, 'e10adc3949ba59abbe56e057f20f883e', '2021-03-24 07:21:59', '2021-03-24 15:22:00', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('99', '128', 'store.db.minConn', 'SEATA_GROUP', '', 0x35, 'e4da3b7fbbce2345d7772b0674a318d5', '2021-03-24 07:22:00', '2021-03-24 15:22:00', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('100', '129', 'store.db.maxConn', 'SEATA_GROUP', '', 0x3330, '34173cb38f07f89ddbebc2ac9128303f', '2021-03-24 07:22:00', '2021-03-24 15:22:01', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('101', '130', 'store.db.globalTable', 'SEATA_GROUP', '', 0x676C6F62616C5F7461626C65, '8b28fb6bb4c4f984df2709381f8eba2b', '2021-03-24 07:22:01', '2021-03-24 15:22:02', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('102', '131', 'store.db.branchTable', 'SEATA_GROUP', '', 0x6272616E63685F7461626C65, '54bcdac38cf62e103fe115bcf46a660c', '2021-03-24 07:22:02', '2021-03-24 15:22:03', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('103', '132', 'store.db.queryLimit', 'SEATA_GROUP', '', 0x313030, 'f899139df5e1059396431415e770c6dd', '2021-03-24 07:22:03', '2021-03-24 15:22:04', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('104', '133', 'store.db.lockTable', 'SEATA_GROUP', '', 0x6C6F636B5F7461626C65, '55e0cae3b6dc6696b768db90098b8f2f', '2021-03-24 07:22:04', '2021-03-24 15:22:05', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('105', '134', 'store.db.maxWait', 'SEATA_GROUP', '', 0x35303030, 'a35fe7f7fe8217b4369a0af4244d1fca', '2021-03-24 07:22:05', '2021-03-24 15:22:06', null, '127.0.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES ('92', '135', 'store.mode', 'SEATA_GROUP', '', 0x66696C65, '8c7dd922ad47494fc02c388e12c00eac', '2021-03-26 01:28:22', '2021-03-26 09:28:24', null, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES ('92', '136', 'store.mode', 'SEATA_GROUP', '', 0x6462, 'd77d5e503ad1439f585ac494268b351b', '2021-03-26 01:28:27', '2021-03-26 09:28:29', null, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES ('93', '137', 'store.db.datasource', 'SEATA_GROUP', '', 0x68696B617269, '80e0b2a2c1a1d6d47f9a9ff573c08c42', '2021-03-26 01:43:35', '2021-03-26 09:43:37', null, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES ('93', '138', 'store.db.datasource', 'SEATA_GROUP', '', 0x6472756964, '3d650fb8a5df01600281d48c47c9fa60', '2021-03-26 01:43:39', '2021-03-26 09:43:41', null, '0:0:0:0:0:0:0:1', 'U', '');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Records of tenant_info
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$R9PBNRueXT8lqyt6QQDxBuHH.9Li7FWkXA1JbjrsCNuA8irkh3mPu', '1');
