SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS b_trading_declareplan;
DROP TABLE IF EXISTS b_trading_deliverydetailed;
DROP TABLE IF EXISTS b_trading_manufacturer;
DROP TABLE IF EXISTS b_trading_orderplan;
DROP TABLE IF EXISTS b_trading_orderunit;
DROP TABLE IF EXISTS b_trading_payment;
DROP TABLE IF EXISTS b_trading_planordercomplete;
DROP TABLE IF EXISTS b_trading_poci;
DROP TABLE IF EXISTS b_trading_retrospect;
DROP TABLE IF EXISTS b_trading_salesorder;
DROP TABLE IF EXISTS b_trading_salessettlement;
DROP TABLE IF EXISTS b_trading_wiscosettlement;




/* Create Tables */

-- b_trading_declareplan
CREATE TABLE b_trading_declareplan
(
	-- 主键 : 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键 : 主键 : 主键',
	-- 订货单位1
	orderUnit varchar(256) COMMENT '订货单位1 : 订货单位1',
	-- 品名 : 创建时间
	pName varchar(1024) COMMENT '品名 : 创建时间 : 品名 : 创建时间',
	-- 牌号
	grade varchar(1024) COMMENT '牌号 : 牌号',
	-- 重量
	weight varchar(1024) COMMENT '重量 : 重量',
	-- 生产厂家
	manufacturer varchar(256) COMMENT '生产厂家 : 生产厂家',
	-- 规格型号
	specification varchar(256) COMMENT '规格型号 : 规格型号',
	-- 计划年月
	planDate varchar(256) COMMENT '计划年月 : 计划年月',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_declareplan' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_deliverydetailed
CREATE TABLE b_trading_deliverydetailed
(
	-- ids
	ids varchar(32) NOT NULL COMMENT 'ids : ids',
	-- 订单项次编号
	orderItemNo varchar(1024) NOT NULL COMMENT '订单项次编号 : 订单项次编号',
	-- 合同月份
	contractMonth varchar(1024) COMMENT '合同月份 : 合同月份',
	-- 标签
	tag varchar(1024) COMMENT '标签 : 标签',
	-- 重量
	weight varchar(1024) COMMENT '重量 : 重量',
	-- 数量
	quantity varchar(128) COMMENT '数量 : 数量',
	-- 销售日期
	writeOffDate varchar(128) COMMENT '销售日期 : 销售日期',
	-- 运费
	freight varchar(128) COMMENT '运费 : 运费',
	-- 代收铁运保险费
	railwayTIP varchar(1024) COMMENT '代收铁运保险费 : 代收铁运保险费',
	-- 代收水运保险费
	waterTIP varchar(1024) COMMENT '代收水运保险费 : 代收水运保险费',
	-- 代办运输延伸费
	extensionFreight varchar(1024) COMMENT '代办运输延伸费 : 代办运输延伸费',
	-- 厚
	thickness varchar(128) COMMENT '厚 : 厚',
	-- 宽
	width varchar(128) COMMENT '宽 : 宽',
	-- 长
	length varchar(128) COMMENT '长 : 长',
	-- 到站(港口)名称
	destination varchar(256) COMMENT '到站(港口)名称 : 到站(港口)名称',
	-- 专用线
	privateLine varchar(256) COMMENT '专用线 : 专用线',
	-- 追溯单价差额
	gapPrice varchar(1024) COMMENT '追溯单价差额 : 追溯单价差额',
	-- 追溯单价
	dvPrice varchar(1024) COMMENT '追溯单价 : 追溯单价',
	-- 追溯文号
	docNo varchar(1024) COMMENT '追溯文号 : 追溯文号',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_deliverydetailed' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_manufacturer
CREATE TABLE b_trading_manufacturer
(
	-- 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键',
	-- 名称
	name varchar(2056) COMMENT '名称 : 名称',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_manufacturer' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_orderplan
CREATE TABLE b_trading_orderplan
(
	ids varchar(32) NOT NULL COMMENT 'ids',
	-- 订单项次号 : 标题
	orderItemNo varchar(1024) NOT NULL COMMENT '订单项次号 : 标题 : 订单项次号 : 标题',
	-- 执行标准 : 内容
	standard varchar(1024) COMMENT '执行标准 : 内容 : 执行标准 : 内容',
	-- 品名 : 创建时间
	pName varchar(1024) COMMENT '品名 : 创建时间 : 品名 : 创建时间',
	-- 牌号
	grade varchar(1024) COMMENT '牌号 : 牌号',
	-- 产品形态
	pForm varchar(1024) COMMENT '产品形态 : 产品形态',
	-- 厚度
	thickness varchar(1024) BINARY COMMENT '厚度 : 厚度',
	-- 宽度
	width varchar(1024) BINARY COMMENT '宽度 : 宽度',
	-- 长度
	length varchar(1024) COMMENT '长度 : 长度',
	-- 项次量
	itemWeight varchar(1024) COMMENT '项次量 : 项次量',
	-- 价格
	price varchar(1024) COMMENT '价格 : 价格',
	rrived varchar(1024) COMMENT 'rrived',
	-- 专用线名称
	specialName varchar(1024) COMMENT '专用线名称 : 专用线名称',
	-- 运费
	freight varchar(1024) COMMENT '运费 : 运费',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_orderplan' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_orderunit
CREATE TABLE b_trading_orderunit
(
	-- ids
	ids varchar(32) NOT NULL COMMENT 'ids : ids',
	-- 名称
	name varchar(1024) COMMENT '名称 : 名称',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_orderunit' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_payment
CREATE TABLE b_trading_payment
(
	-- 主键 : 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键 : 主键 : 主键',
	-- 收货单位 : 标题
	unit varchar(128) COMMENT '收货单位 : 标题 : 收货单位 : 标题',
	-- 收付款金额
	amount varchar(128) COMMENT '收付款金额 : 收付款金额',
	-- 摘要
	abstract varchar(1028) COMMENT '摘要 : 摘要',
	-- 收款方式v
	method varchar(1024) COMMENT '收款方式v : 收款方式v',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_payment' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_planordercomplete
CREATE TABLE b_trading_planordercomplete
(
	-- 主键 : 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键 : 主键 : 主键',
	-- 订单项次号
	orderItemNo varchar(128) COMMENT '订单项次号 : 订单项次号',
	-- 执行标准 : 内容
	standard varchar(1024) COMMENT '执行标准 : 内容 : 执行标准 : 内容',
	-- 品名 : 创建时间
	pName varchar(1024) COMMENT '品名 : 创建时间 : 品名 : 创建时间',
	-- 牌号
	grade varchar(1024) COMMENT '牌号 : 牌号',
	-- 规格型号
	specification varchar(1024) COMMENT '规格型号 : 规格型号',
	-- 项次量
	itemWeight varchar(1024) COMMENT '项次量 : 项次量',
	-- 项次价格
	price varchar(1024) COMMENT '项次价格 : 项次价格',
	-- 到站名称
	rrived varchar(1024) BINARY COMMENT '到站名称 : 到站名称',
	-- 运输方式
	specialName varchar(1024) COMMENT '运输方式 : 运输方式',
	-- 运费
	freight varchar(1024) COMMENT '运费 : 运费',
	-- 产品形态
	pForm varchar(1024) COMMENT '产品形态 : 产品形态',
	-- 厚度
	thickness varchar(1024) BINARY COMMENT '厚度 : 厚度',
	-- 宽度
	width varchar(1024) BINARY COMMENT '宽度 : 宽度',
	-- 长度
	length varchar(1024) COMMENT '长度 : 长度',
	-- 月份
	cDate varchar(1024) COMMENT '月份 : 月份',
	-- 合同编号
	cNo varchar(1024) COMMENT '合同编号 : 合同编号',
	PRIMARY KEY (ids),
	UNIQUE (orderItemNo)
) COMMENT = 'b_trading_planordercomplete' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_poci
CREATE TABLE b_trading_poci
(
	-- 主键 : 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键 : 主键 : 主键',
	-- 发票号 : 标题
	invoceNo varchar(128) COMMENT '发票号 : 标题 : 发票号 : 标题',
	-- 月份
	cDate varchar(4) COMMENT '月份 : 月份',
	PRIMARY KEY (ids),
	UNIQUE (invoceNo)
) COMMENT = 'b_trading_poci' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_retrospect
CREATE TABLE b_trading_retrospect
(
	-- 编辑
	ids varchar(32) NOT NULL COMMENT '编辑 : 编辑',
	-- 月份
	mon varchar(4) NOT NULL COMMENT '月份 : 月份',
	-- 品名
	pName varchar(1024) NOT NULL COMMENT '品名 : 品名',
	-- 牌号
	grade varchar(1024) NOT NULL COMMENT '牌号 : 牌号',
	-- 规格型号
	specification varchar(1024) NOT NULL COMMENT '规格型号 : 规格型号',
	-- 追溯单价
	price varchar(1024) COMMENT '追溯单价 : 追溯单价',
	-- 追溯文号
	docNo varchar(1024) COMMENT '追溯文号 : 追溯文号',
	-- 追溯单价差额
	gapPrice varchar(1024) COMMENT '追溯单价差额 : 追溯单价差额',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_retrospect' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_salesorder
CREATE TABLE b_trading_salesorder
(
	-- 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键',
	-- 订货单位
	orderUnit varchar(1024) COMMENT '订货单位 : 订货单位',
	-- 生产厂家
	manufacturer varchar(1024) COMMENT '生产厂家 : 生产厂家',
	-- 销售订单号
	salesOrderNo varchar(1024) COMMENT '销售订单号 : 销售订单号',
	-- 销售合同价格
	salesPrice varchar(1024) COMMENT '销售合同价格 : 销售合同价格',
	-- 代垫运货
	freightage varchar(1024) COMMENT '代垫运货 : 代垫运货',
	-- 代垫仓储费
	storag varchar(1024) COMMENT '代垫仓储费 : 代垫仓储费',
	-- 其它费用
	other varchar(1024) COMMENT '其它费用 : 其它费用',
	-- poci表IDS
	pocIds varchar(32) COMMENT 'poci表IDS : poci表IDS',
	orderItemNo varchar(128) COMMENT 'orderItemNo',
	PRIMARY KEY (ids),
	UNIQUE (orderItemNo)
) COMMENT = 'b_trading_salesorder' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_salessettlement
CREATE TABLE b_trading_salessettlement
(
	-- 主键
	ids varchar(32) NOT NULL COMMENT '主键 : 主键',
	-- 销售订单编号
	salesOrderIds varchar(32) COMMENT '销售订单编号 : 销售订单编号',
	-- 销售结算Ids
	wiscoSettlementIds varchar(32) COMMENT '销售结算Ids : 销售结算Ids',
	-- 销售不含税价
	noTaxPrice varchar(2056) COMMENT '销售不含税价 : 销售不含税价',
	-- 货款金额
	goodsAmount varchar(2056) COMMENT '货款金额 : 货款金额',
	-- 税款金额
	taxPrice varchar(2056) COMMENT '税款金额 : 税款金额',
	-- 总金额
	totalAmount varchar(2056) COMMENT '总金额 : 总金额',
	-- 是否开票
	hasDraw varchar(1) DEFAULT '0' COMMENT '是否开票 : 是否开票',
	-- 发票号
	invoiceNo varchar(1024) COMMENT '发票号 : 发票号',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_salessettlement' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;


-- b_trading_wiscosettlement
CREATE TABLE b_trading_wiscosettlement
(
	-- ids
	ids varchar(32) NOT NULL COMMENT 'ids : ids',
	-- 结算清单号
	settlementNo varchar(1024) COMMENT '结算清单号 : 结算清单号',
	-- 开立日期
	issuanceDate varchar(1024) COMMENT '开立日期 : 开立日期',
	-- 订单项次编号
	orderItemNo varchar(1024) COMMENT '订单项次编号 : 订单项次编号',
	-- 品种
	pName varchar(1024) COMMENT '品种 : 品种',
	-- 牌号
	grade varchar(1024) COMMENT '牌号 : 牌号',
	-- 规格
	specification varchar(1024) COMMENT '规格 : 规格',
	-- 合同月份
	contractMonth varchar(1024) COMMENT '合同月份 : 合同月份',
	-- 出货重量
	weight varchar(1024) COMMENT '出货重量 : 出货重量',
	-- 单价
	price varchar(1024) COMMENT '单价 : 单价',
	-- 人民币货款金额
	loan varchar(1024) COMMENT '人民币货款金额 : 人民币货款金额',
	-- 税额
	tax varchar(1024) COMMENT '税额 : 税额',
	-- 发票号
	invoice varchar(1024) COMMENT '发票号 : 发票号',
	-- 运费
	freight varchar(1024) COMMENT '运费 : 运费',
	-- 代收铁运保险费
	railwayTIP varchar(1024) COMMENT '代收铁运保险费 : 代收铁运保险费',
	-- 代收水运保险费
	waterTIP varchar(1024) COMMENT '代收水运保险费 : 代收水运保险费',
	-- 代办运输延伸费
	extensionFreight varchar(1024) COMMENT '代办运输延伸费 : 代办运输延伸费',
	-- 开立原因
	openCause varchar(1024) COMMENT '开立原因 : 开立原因',
	-- 是否结算 : 0 未结1 已结
	hasConfirm varchar(1) DEFAULT '0' COMMENT '是否结算 : 0 未结1 已结 : 是否结算 : 0 未结1 已结',
	PRIMARY KEY (ids)
) COMMENT = 'b_trading_wiscosettlement' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



