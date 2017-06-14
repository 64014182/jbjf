SET SESSION FOREIGN_KEY_CHECKS=0;

-- ALTER table table_name MODIFY `column_name` datetime DEFAULT NULL COMMENT '这是字段的注释' 

ALTER TABLE b_trading_salessettlement add orderUnitId varchar(32) COMMENT '订货单位';
ALTER TABLE b_trading_salessettlement add manufacturerId varchar(32) COMMENT '生产厂家';
ALTER TABLE b_trading_salessettlement add pName varchar(256) COMMENT '品名';
ALTER TABLE b_trading_salessettlement add grade varchar(256) COMMENT '牌号';
ALTER TABLE b_trading_salessettlement add specification varchar(256) COMMENT '规格';
ALTER TABLE b_trading_salessettlement add weight varchar(256) COMMENT '实结重量';
ALTER TABLE b_trading_salessettlement add orderItemNo varchar(256) COMMENT '订单项次号';
ALTER TABLE b_trading_salessettlement add contractMonth varchar(256) COMMENT '合同月份';
ALTER TABLE b_trading_salessettlement add price varchar(256) COMMENT '单价';
ALTER TABLE b_trading_salessettlement add addPrice varchar(256) COMMENT '销售加价';
ALTER TABLE b_trading_salessettlement add salesOrderNo varchar(256) COMMENT '销售订单号';

ALTER TABLE b_trading_deliverydetailed add state varchar(1) COMMENT '状态 0-未入库 1-已入库 2-已出库';
ALTER TABLE b_trading_deliverydetailed add inNo varchar(128) COMMENT '入库号';
ALTER TABLE b_trading_deliverydetailed add outNo varchar(128) COMMENT '出库号';

SELECT ids FROM `pt_module` where `names` = '销售结算';

权限添加
添加发票 /trading/salesSettlement/add1.html
开发票  /trading/salesSettlement/summary









