# 删除外键， 索引, 栏位
ALTER TABLE `measurement`.`instrument_employment_info` DROP FOREIGN KEY `FKcpren9c64q0vkp7lnidafoeuh`,
  DROP FOREIGN KEY `FKki2tt3fj5noxf6oyq086pvbum`,
  DROP FOREIGN KEY `FKnxkwaqn55jk4ertf34pfpay60`;
ALTER TABLE `measurement`.`instrument_employment_info` DROP INDEX `FKki2tt3fj5noxf6oyq086pvbum`,
  DROP INDEX `FKnxkwaqn55jk4ertf34pfpay60`,
  DROP INDEX `FKcpren9c64q0vkp7lnidafoeuh`;
ALTER TABLE `measurement`.`instrument_employment_info` DROP COLUMN `last_check_apply_id`,
  DROP COLUMN `mandatory_instrument_apply_id`,
  DROP COLUMN `check_apply_id`;
ALTER TABLE `measurement`.`instrument_check_info` DROP FOREIGN KEY `FKlmcdmgl436nxu6nnhmaevdq6p`;
ALTER TABLE `measurement`.`instrument_check_info` DROP INDEX `FKlmcdmgl436nxu6nnhmaevdq6p`;
ALTER TABLE `measurement`.`instrument_check_info` DROP COLUMN `check_apply_id`;

ALTER TABLE `measurement`.`instrument_employment_info` DROP FOREIGN KEY `FKkgrixq6bkxo0ak307sge0kitp`;
ALTER TABLE `measurement`.`instrument_employment_info` DROP COLUMN `last_instrument_check_info_id`, DROP INDEX `FKkgrixq6bkxo0ak307sge0kitp`;

ALTER TABLE `measurement`.`attachment` DROP FOREIGN KEY `FKotjgwpws7rascdqp8g5rq5a66`;
ALTER TABLE `measurement`.`attachment` DROP COLUMN `mandatory_instrument_apply_id`, DROP INDEX `FKotjgwpws7rascdqp8g5rq5a66`;

ALTER TABLE `measurement`.`work` DROP FOREIGN KEY `FK9tbr2obpiava27livmhvxmvv7`;
ALTER TABLE `measurement`.`work` DROP COLUMN `apply_id`, DROP INDEX `FK9tbr2obpiava27livmhvxmvv7`;

#  删除以下表
DROP TABLE IF EXISTS `apply_mandatory_instrument_set`, `apply_mandatory_instruments`,`check_apply`,`apply_field_record`,`instrument_check_info`
, `apply`, `current_work`,  `work` ;

# 更改历史数据
UPDATE `apply_type` SET `name` = 'OverdueCheck' WHERE `id` = 1;
UPDATE `apply_type` SET `name` = 'MandatoryInstrument' WHERE `id` = 2;

# 使用update的方式启动程序
