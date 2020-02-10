-- auto Generated on 2018-09-25T20:04:58.940 
-- DROP TABLE IF EXISTS school_student; 
CREATE TABLE school_student(
	id INTEGER (0) NOT NULL DEFAULT '' COMMENT '主键 Integer 自增',
	uuid VARCHAR (65535) NOT NULL DEFAULT '' COMMENT '唯一主键, 32 位 UUID',
	deleted BOOLEAN (0) NOT NULL DEFAULT '0' COMMENT '是否删除, 1 表示删除 , 0 不删除',
	created_by INTEGER (0) NOT NULL DEFAULT '' COMMENT '当前记录的创建人ID',
	updated_by INTEGER (0) NOT NULL DEFAULT '' COMMENT '当前记录的更新人ID',
	created_at DATETIME (0) NOT NULL DEFAULT '' COMMENT '当前记录的创建时间',
	updated_at DATETIME (0) NOT NULL DEFAULT '' COMMENT '当前记录的更新时间',
	description VARCHAR (65535) NOT NULL DEFAULT '' COMMENT '备注信息',
	name VARCHAR (65535) NOT NULL DEFAULT '' COMMENT 'name',
	age INTEGER (0) NOT NULL DEFAULT '' COMMENT 'age',
	birthday DATETIME (0) NOT NULL DEFAULT '' COMMENT 'birthday',
	merried BOOLEAN (0) NOT NULL DEFAULT '' COMMENT 'merried',
	heigth DOUBLE (0) NOT NULL DEFAULT '' COMMENT 'heigth',
	PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'school_student';