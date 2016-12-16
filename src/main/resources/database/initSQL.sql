-- ----------------------------
-- sql for create database.
-- ----------------------------
DROP DATABASE if exists test1;
CREATE DATABASE test1 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- sql for use database.
-- ----------------------------
use test1;

-- ----------------------------
-- Table structure for test_teacher
-- ----------------------------
DROP table if exists test_teacher;
CREATE TABLE test_teacher (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  name varchar(45) NOT NULL DEFAULT '' COMMENT '老师的名字',
  mobile varchar(20) NOT NULL DEFAULT '' COMMENT '联系方式',
  gender tinyint(1) NOT NULL DEFAULT 1 COMMENT '1男,0女',
  age int(9) NOT NULL DEFAULT 0 COMMENT '年龄',
  major varchar(10) NOT NULL DEFAULT '' COMMENT '教授专业',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='老师管理表';

-- ----------------------------
-- Table structure for test_student
-- ----------------------------
DROP table if exists test_student;
CREATE TABLE test_student (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  name varchar(45) NOT NULL DEFAULT '' COMMENT '学生的名字',
  mobile varchar(20) NOT NULL DEFAULT '' COMMENT '联系方式',
  gender tinyint(1) NOT NULL DEFAULT 1 COMMENT '1男,0女',
  age int(9) NOT NULL DEFAULT 0 COMMENT '年龄',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生管理表';


-- ----------------------------
-- sql for create database.
-- ----------------------------
DROP DATABASE if exists test2;
CREATE DATABASE test2 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- ----------------------------
-- sql for use database.
-- ----------------------------
use test2;

-- ----------------------------
-- Table structure for test_school
-- ----------------------------
DROP table if exists test_school;
CREATE TABLE test_school (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  name varchar(45) NOT NULL DEFAULT '' COMMENT '学校名字',
  address varchar(100) NOT NULL DEFAULT '' COMMENT '学校地址',
  phone varchar(100) NOT NULL DEFAULT '' COMMENT '联系方式',
  area varchar(10) NOT NULL DEFAULT '' COMMENT '地区',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学校管理表';

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP table if exists messages;
CREATE TABLE messages (
  id int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长的主键',
  disabled tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志,1删除,0正常使用',
  uuid varchar(45) NOT NULL DEFAULT '' COMMENT '系统生成的一个随机码',
  create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
  update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  content varchar(45) NOT NULL DEFAULT '' COMMENT '短信的内容',
  status tinyint(2) NOT NULL DEFAULT 1 COMMENT '结果标识:1成功,0失败',
  mobile varchar(20) NOT NULL DEFAULT '' COMMENT '给谁发的',
  type tinyint(2) NOT NULL DEFAULT 1 COMMENT '1示远,2未来无线',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统短信消息发送记录表';




