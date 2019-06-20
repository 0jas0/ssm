# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
#
# Database: beihua
# Generation Time: 2019-06-20 10:44:56 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table admin
# ------------------------------------------------------------

CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) NOT NULL COMMENT '用户名称',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员信息';

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;

INSERT INTO `admin` (`id`, `name`, `password`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,'admin','827ccb0eea8a706c4c34a16891f84e7b',0,1527057172,1527057172);

/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table administrative_class
# ------------------------------------------------------------

DROP TABLE IF EXISTS `administrative_class`;

CREATE TABLE `administrative_class` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `class_id` varchar(64) NOT NULL COMMENT '班级号',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '班级名称',
  `class_number` int(11) NOT NULL DEFAULT '0' COMMENT '班级人数',
  `instructor` varchar(200) NOT NULL DEFAULT '' COMMENT '辅导员',
  `major` int(11) NOT NULL COMMENT '所属专业',
  `college` int(11) NOT NULL COMMENT '所属学院',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';

LOCK TABLES `administrative_class` WRITE;
/*!40000 ALTER TABLE `administrative_class` DISABLE KEYS */;

INSERT INTO `administrative_class` (`id`, `class_id`, `name`, `class_number`, `instructor`, `major`, `college`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,'1501','计算机1班',33,'老师1',39,9,0,1550579803,1550579803),
	(2,'1502','计算机二班',33,'',39,9,0,1550579877,1550579877),
	(3,'1503','软件工程1班',33,'',37,9,0,1554898477,1554898477),
	(4,'1504','软件工程2班',34,'',37,9,0,1554898563,1554898563),
	(5,'1505','数学1班',54,'',28,5,0,1554898873,1554898873),
	(6,'1506','数学2班',44,'',28,5,0,1554898898,1554898898);

/*!40000 ALTER TABLE `administrative_class` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table choice_courses
# ------------------------------------------------------------

DROP TABLE IF EXISTS `choice_courses`;

CREATE TABLE `choice_courses` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) NOT NULL COMMENT '学号',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选课信息';

LOCK TABLES `choice_courses` WRITE;
/*!40000 ALTER TABLE `choice_courses` DISABLE KEYS */;

INSERT INTO `choice_courses` (`id`, `course_id`, `student_id`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,2,1,1,1550580887,1550580887),
	(2,2,1,1,1550580957,1550580957),
	(3,2,1,1,1555060766,1555060766),
	(4,4,1,1,1555060768,1555060768),
	(5,2,1,1,1555061809,1555061809),
	(6,4,1,1,1555066478,1555066478),
	(7,4,1,1,1555066481,1555066481),
	(8,4,1,1,1555066482,1555066482),
	(9,4,1,1,1555066483,1555066483),
	(10,7,1,1,1555066486,1555066486),
	(11,7,1,1,1555066486,1555066486),
	(12,7,1,1,1555066486,1555066486),
	(13,7,1,1,1555066487,1555066487),
	(14,7,1,1,1555066487,1555066487),
	(15,7,1,1,1555066487,1555066487),
	(16,7,1,1,1555066487,1555066487),
	(17,7,1,1,1555066488,1555066488),
	(18,4,1,1,1555066642,1555066642),
	(19,2,1,1,1555066676,1555066676),
	(20,4,1,1,1555066677,1555066677),
	(21,7,1,1,1555066679,1555066679),
	(22,7,1,1,1555066679,1555066679),
	(23,2,1,1,1555066684,1555066684),
	(24,4,1,0,1555066684,1555066684),
	(25,7,1,1,1555066686,1555066686),
	(26,7,1,1,1555066840,1555066840),
	(27,7,1,0,1555067574,1555067574),
	(28,2,1,1,1555144446,1555144446);

/*!40000 ALTER TABLE `choice_courses` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table college_major
# ------------------------------------------------------------

DROP TABLE IF EXISTS `college_major`;

CREATE TABLE `college_major` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
  `parent_id` int(11) NOT NULL COMMENT '父id',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学院和专业';

LOCK TABLES `college_major` WRITE;
/*!40000 ALTER TABLE `college_major` DISABLE KEYS */;

INSERT INTO `college_major` (`id`, `name`, `parent_id`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,'政法学院',0,0,1526130921,1526130921),
	(2,'体育学院',0,0,1526130921,1526130921),
	(3,'外语学院',0,0,1526130921,1526130921),
	(4,'美术学院',0,0,1526130921,1526130921),
	(5,'数学学院',0,0,1526130921,1526130921),
	(6,'化学与生物学院',0,0,1526130921,1526130921),
	(7,'电气信息工程学院',0,0,1526130921,1526130921),
	(8,'交通建筑工程学院',0,0,1526130921,1526130921),
	(9,'计算机与信息技术学院',0,0,1526130921,1526130921),
	(10,'机械工程学院',0,0,1526130921,1526130921),
	(11,'物理学院',0,0,1526130921,1526130921),
	(12,'药学院',0,0,1526130921,1526130921),
	(13,'第一临床医学院',0,0,1526130921,1526130921),
	(14,'口腔医学院',0,0,1526130921,1526130921),
	(15,'法学专业',1,0,1526132077,1526132077),
	(16,'社会学专业',1,0,1526132077,1526132077),
	(17,'运动训练专业',2,0,1526132077,1526132077),
	(18,'体育教育专业',2,0,1526132077,1526132077),
	(19,'英语专业',3,0,1526132077,1526132077),
	(20,'日语专业',3,0,1526132077,1526132077),
	(21,'西班牙语专业',3,0,1526132077,1526132077),
	(22,'朝鲜语专业',3,0,1526132077,1526132077),
	(23,'翻译专业',3,0,1526132077,1526132077),
	(24,'俄语专业',3,0,1526132077,1526132077),
	(25,'美术学专业',4,0,1526132077,1526132077),
	(26,'绘画专业',4,0,1526132077,1526132077),
	(27,'统计学类',5,0,1526132077,1526132077),
	(28,'数学与应用数学',5,0,1526132077,1526132077),
	(29,'化学专业',6,0,1526132077,1526132077),
	(30,'应用化学专业',6,0,1526132077,1526132077),
	(31,'生物科学专业',6,0,1526132077,1526132077),
	(32,'电气工程及其自动化',7,0,1526132077,1526132077),
	(33,'电子信息工程专业',7,0,1526132077,1526132077),
	(34,'电子信息科学与技术',7,0,1526132077,1526132077),
	(35,'自动化',7,0,1526132077,1526132077),
	(36,'交通运输',8,0,1526132077,1526132077),
	(37,'软件工程',9,0,1526132077,1526132077),
	(38,'网络工程',9,0,1526132077,1526132077),
	(39,'计算机科学与技术',9,0,1526132077,1526132077),
	(40,'机械类专业',10,0,1526132077,1526132077),
	(41,'工业设计专业',10,0,1526132077,1526132077),
	(42,'物理学专业',11,0,1526132077,1526132077),
	(43,'材料物理专业',11,0,1526132077,1526132077),
	(44,'药学',12,0,1526132077,1526132077),
	(45,'临床医学',13,0,1526132077,1526132077),
	(46,'口腔医学专业',14,0,1526132077,1526132077);

/*!40000 ALTER TABLE `college_major` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '课程号',
  `teacher_id` varchar(64) NOT NULL COMMENT '教师工号',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '课程名称',
  `course_start` int(11) NOT NULL COMMENT '课程开始时间',
  `course_end` int(11) NOT NULL COMMENT '课程结束时间',
  `semester` tinyint(4) NOT NULL COMMENT '1:大一上学期 2:大一下学期 3:大二上学期 4:大二下学期 5:大三上学期 6:大三下学期 7:大四上学期 8:大四下学期',
  `college` int(11) NOT NULL COMMENT '开课学院',
  `period` tinyint(4) NOT NULL COMMENT '学时',
  `credit` tinyint(4) NOT NULL COMMENT '学分',
  `type` tinyint(4) NOT NULL COMMENT '课程类型：0选修1必修',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;

INSERT INTO `course` (`id`, `teacher_id`, `name`, `course_start`, `course_end`, `semester`, `college`, `period`, `credit`, `type`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,'2015101043','C语言',1548950400,1551283200,7,9,2,2,1,0,1550580208,1550580208),
	(2,'2015101043','ps',1548950400,1551283200,7,9,2,2,0,0,1550580437,1550580437),
	(3,'002','数学',1551369600,1557590400,7,5,2,2,1,0,1554966413,1554966413),
	(4,'002','数学',1551369600,1556985600,7,9,2,2,0,0,1554966557,1554966557),
	(5,'003','数据结构',1548950400,1559404800,7,9,2,2,1,0,1554966916,1554966916),
	(6,'004','linux',1551110400,1559404800,7,9,2,2,1,0,1554967118,1554967118),
	(7,'005','英语',1551024000,1559404800,7,9,2,2,0,0,1554967228,1554967228),
	(8,'006','数据库',1547136000,1562342400,7,9,2,2,1,0,1554969030,1554969030),
	(9,'007','物理',1549555200,1562428800,7,9,2,2,1,0,1554969713,1554969713),
	(10,'0011','qqq',1548691200,1562342400,7,9,2,2,0,0,1555144293,1555144293);

/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table course_time_place
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course_time_place`;

CREATE TABLE `course_time_place` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `course_time` tinyint(4) NOT NULL COMMENT '第几节课，1第一大节课，第二大节课，3第三大节课，4第四大节课',
  `course_week` tinyint(4) NOT NULL COMMENT '1周一 2周二 3周三 4周四 5周五 6周六 7周日',
  `course_place` varchar(200) NOT NULL COMMENT '课程地点',
  `class_id` int(11) DEFAULT NULL COMMENT '班级号',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程时间地点表';

LOCK TABLES `course_time_place` WRITE;
/*!40000 ALTER TABLE `course_time_place` DISABLE KEYS */;

INSERT INTO `course_time_place` (`id`, `course_id`, `course_time`, `course_week`, `course_place`, `class_id`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,1,1,1,'综合楼101',2,0,1550580272,1556282030),
	(2,1,2,1,'综合楼102',2,1,1550580339,1550580339),
	(3,2,2,1,'综合楼102',2,0,1550580464,1550580464),
	(4,1,1,1,'1111',1,1,1554965144,1554965144),
	(5,1,1,2,'1111',1,0,1554965176,1554965176),
	(6,2,2,2,'2222',1,0,1554965229,1554965229),
	(7,1,1,3,'1212',3,0,1554966318,1554966318),
	(8,1,1,4,'4444',4,0,1554966332,1554966332),
	(9,2,2,3,'5555',3,0,1554966363,1554966363),
	(10,2,2,4,'6666',4,0,1554966373,1554966373),
	(11,3,1,1,'7777',5,0,1554966434,1554966434),
	(12,3,1,2,'1313',6,0,1554966464,1554966464),
	(13,4,3,1,'1414',1,0,1554966579,1554966579),
	(14,4,3,2,'1515',2,0,1554966593,1554966593),
	(15,4,3,3,'1616',3,0,1554966610,1554966610),
	(16,4,3,4,'1717',4,0,1554966621,1554966621),
	(17,5,4,1,'2323',1,0,1554966938,1554966938),
	(18,5,4,2,'2424',2,0,1554966950,1554966950),
	(19,5,4,3,'2525',3,0,1554966968,1554966968),
	(20,5,4,4,'2626',4,0,1554967026,1554967026),
	(21,6,1,5,'3434',1,0,1554967136,1554967136),
	(22,6,2,5,'3434',2,0,1554967147,1554967147),
	(23,7,3,5,'4545',1,0,1554967253,1554967253),
	(24,7,4,5,'4545',2,0,1554967267,1554967267),
	(25,1,1,3,'4646',2,0,1554968739,1554968739),
	(26,5,2,4,'6767',2,0,1554968767,1554968767),
	(27,6,1,2,'7878',2,0,1554968866,1554968866),
	(28,7,4,4,'6565',2,0,1554968900,1554968900),
	(29,7,4,4,'6565',2,1,1554968900,1554968900),
	(30,8,3,1,'7676',2,0,1554969046,1554969046),
	(31,8,3,4,'9898',2,0,1554969075,1554969075),
	(32,9,1,5,'6535',2,0,1554969766,1554969766),
	(33,9,2,3,'6446',2,0,1554969791,1554969791),
	(34,10,1,6,'5555',4,0,1555144315,1555144315);

/*!40000 ALTER TABLE `course_time_place` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table score
# ------------------------------------------------------------

DROP TABLE IF EXISTS `score`;

CREATE TABLE `score` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) NOT NULL COMMENT '学号',
  `grade` double DEFAULT NULL COMMENT '分数',
  `access_credits` double DEFAULT NULL COMMENT '所获学分',
  `is_rework` tinyint(4) NOT NULL DEFAULT '0' COMMENT '重修：0否1是',
  `fail_reason` text COMMENT '挂科原因',
  `rework_situation` text COMMENT '重修情况',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='成绩信息';

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;

INSERT INTO `score` (`id`, `course_id`, `student_id`, `grade`, `access_credits`, `is_rework`, `fail_reason`, `rework_situation`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,2,1,90,NULL,0,'缓考','',0,1550581161,1552475287),
	(2,1,1,30,20,0,'','',0,1552308195,1552308832),
	(3,2,9,99,NULL,0,'','',0,1555144506,1555144506),
	(4,2,10,33,NULL,0,'成绩低','',0,1555144514,1555144530),
	(5,2,10,33,NULL,0,'','',0,1555144516,1555144516);

/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table student
# ------------------------------------------------------------

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` varchar(64) NOT NULL COMMENT '学号',
  `photo` varchar(64) NOT NULL COMMENT '图片',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '学生名称',
  `born_date` date DEFAULT NULL COMMENT '出生日期',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别0男 1女',
  `political_outlook` varchar(200) NOT NULL DEFAULT '' COMMENT '政治面貌',
  `class_id` int(11) NOT NULL COMMENT '班级号',
  `college` int(11) NOT NULL COMMENT '学院',
  `major` int(11) NOT NULL COMMENT '专业',
  `native_place` varchar(200) NOT NULL COMMENT '籍贯',
  `nation` varchar(200) NOT NULL COMMENT '民族',
  `address` varchar(200) NOT NULL COMMENT '住址',
  `postalcode` varchar(200) NOT NULL DEFAULT '' COMMENT '邮政编码',
  `mobile` varchar(20) NOT NULL COMMENT '联系电话',
  `identity_card_number` varchar(50) NOT NULL COMMENT '身份证号',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;

INSERT INTO `student` (`id`, `student_id`, `photo`, `password`, `name`, `born_date`, `sex`, `political_outlook`, `class_id`, `college`, `major`, `native_place`, `nation`, `address`, `postalcode`, `mobile`, `identity_card_number`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,'2015101080','','827ccb0eea8a706c4c34a16891f84e7b','赵立娟',NULL,1,'',2,9,39,'','','','','','',0,1550579980,1558448353),
	(2,'2015101001','','827ccb0eea8a706c4c34a16891f84e7b','学生1',NULL,0,'',2,9,39,'','','','','','',0,1554898531,1554898660),
	(3,'2015101002','','827ccb0eea8a706c4c34a16891f84e7b','学生2',NULL,0,'',2,9,39,'','','','','','',0,1554898605,1554898605),
	(4,'2015101003','','827ccb0eea8a706c4c34a16891f84e7b','学生3',NULL,0,'',3,9,37,'','','','','','',0,1554898646,1554898646),
	(5,'2015101004','','827ccb0eea8a706c4c34a16891f84e7b','学生4',NULL,0,'',4,9,37,'','','','','','',0,1554898698,1554898698),
	(6,'2015101005','','827ccb0eea8a706c4c34a16891f84e7b','学生5',NULL,0,'',4,9,37,'','','','','','',0,1554898727,1554898727),
	(7,'2015101006','','827ccb0eea8a706c4c34a16891f84e7b','学生6',NULL,0,'',1,9,39,'','','','','','',1,1554898758,1554898934),
	(8,'2015101006','','827ccb0eea8a706c4c34a16891f84e7b','学生6',NULL,0,'',1,9,39,'','','','','','',1,1554898793,1554898934),
	(9,'2015101006','','827ccb0eea8a706c4c34a16891f84e7b','学生6',NULL,0,'',1,9,39,'','','','','','',0,1554898964,1554898964),
	(10,'2015101007','','827ccb0eea8a706c4c34a16891f84e7b','学生7',NULL,0,'',1,9,39,'','','','','','',0,1554898994,1554898994),
	(11,'2015101008','','827ccb0eea8a706c4c34a16891f84e7b','学生8',NULL,0,'',6,5,28,'','','','','','',0,1554899023,1554899023),
	(12,'2015101009','','827ccb0eea8a706c4c34a16891f84e7b','学生9',NULL,0,'',2,9,39,'','','','','','',0,1554899082,1554899082),
	(13,'2015101010','','827ccb0eea8a706c4c34a16891f84e7b','学生10',NULL,0,'',2,9,39,'','','','','','',0,1554899104,1554899104);

/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table supplementary
# ------------------------------------------------------------

DROP TABLE IF EXISTS `supplementary`;

CREATE TABLE `supplementary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) NOT NULL COMMENT '学号',
  `grade` double NOT NULL COMMENT '分数',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='补考信息';



# Dump of table system_setting
# ------------------------------------------------------------

DROP TABLE IF EXISTS `system_setting`;

CREATE TABLE `system_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `choice_start_time` int(11) NOT NULL COMMENT '选程开始时间',
  `choice_end_time` int(11) NOT NULL COMMENT '选课结束时间',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统设置';

LOCK TABLES `system_setting` WRITE;
/*!40000 ALTER TABLE `system_setting` DISABLE KEYS */;

INSERT INTO `system_setting` (`id`, `choice_start_time`, `choice_end_time`, `addtime`, `modtime`)
VALUES
	(1,1540742400,1562428800,1548316168,1555144400);

/*!40000 ALTER TABLE `system_setting` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table teacher
# ------------------------------------------------------------

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_id` varchar(64) NOT NULL COMMENT '教师工号',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '教师名称',
  `born_date` date DEFAULT NULL COMMENT '出生日期',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别0男 1女',
  `education` varchar(200) NOT NULL DEFAULT '' COMMENT '学历',
  `position` varchar(200) NOT NULL COMMENT '职称',
  `nation` varchar(200) NOT NULL COMMENT '民族',
  `address` varchar(200) NOT NULL COMMENT '住址',
  `postalcode` varchar(200) NOT NULL DEFAULT '' COMMENT '邮政编码',
  `mobile` varchar(20) NOT NULL COMMENT '联系电话',
  `identity_card_number` varchar(50) NOT NULL COMMENT '身份证号',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师信息表';

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;

INSERT INTO `teacher` (`id`, `teacher_id`, `password`, `name`, `born_date`, `sex`, `education`, `position`, `nation`, `address`, `postalcode`, `mobile`, `identity_card_number`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,'2015101043','827ccb0eea8a706c4c34a16891f84e7b','老师1','2018-01-04',1,'本科','教授','满','黑龙江省牡丹江市','','13804810000','',0,1550580020,1554964647),
	(2,'002','827ccb0eea8a706c4c34a16891f84e7b','老师2','2019-04-03',0,'本科','教授','汉','黑龙江省牡丹江市','','13804811111','230521199710121111',0,1554963998,1554964750),
	(3,'003','827ccb0eea8a706c4c34a16891f84e7b','老师3','2019-01-09',0,'本科','教授','汉','黑龙江省牡丹江市','','13804813333','230521199710122222',0,1554964515,1554964674),
	(4,'004','827ccb0eea8a706c4c34a16891f84e7b','老师4','2019-04-02',0,'本科','教授','汉','黑龙江省双鸭山市','','13804814444','230521199710124444',0,1554964727,1554964741),
	(5,'005','827ccb0eea8a706c4c34a16891f84e7b','老师5','2019-04-03',1,'','教授','满','北京市朝阳区','','13804815555','230521199710125555',0,1554965052,1554965052),
	(6,'006','827ccb0eea8a706c4c34a16891f84e7b','老师6','2019-06-05',1,'本科','教授','汉','黑龙江省鸡西市','','13804813434','23052119971219',0,1554969003,1554969003),
	(7,'007','827ccb0eea8a706c4c34a16891f84e7b','老师7','2019-04-04',1,'本科','教授','汉','黑龙江省双鸭山市','','13804818767','230521199710129768',1,1554969352,1557667822),
	(8,'0011','827ccb0eea8a706c4c34a16891f84e7b','老师111',NULL,0,'','','','','','','',0,1555144262,1555144262),
	(9,'007','827ccb0eea8a706c4c34a16891f84e7b','42432',NULL,0,'436','645','757','465','645','754','547',1,1557667815,1557667822);

/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table teaching_evaluation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `teaching_evaluation`;

CREATE TABLE `teaching_evaluation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `evaluation_grade` tinyint(4) NOT NULL COMMENT '评价等级：0差，1一般，2好，3很好，4非常好',
  `teacher_id` varchar(64) NOT NULL DEFAULT '' COMMENT '教师id',
  `student_id` varchar(64) NOT NULL COMMENT '学生id',
  `is_del` tinyint(4) NOT NULL DEFAULT '0',
  `addtime` int(11) NOT NULL,
  `modtime` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评教信息';

LOCK TABLES `teaching_evaluation` WRITE;
/*!40000 ALTER TABLE `teaching_evaluation` DISABLE KEYS */;

INSERT INTO `teaching_evaluation` (`id`, `course_id`, `evaluation_grade`, `teacher_id`, `student_id`, `is_del`, `addtime`, `modtime`)
VALUES
	(1,2,0,'2015101043','2015101080',0,1551792495,1551792495),
	(2,4,2,'002','2015101080',0,1555144468,1555144468),
	(3,7,2,'005','2015101080',0,1555144468,1555144468),
	(4,1,2,'2015101043','2015101080',0,1555144469,1555144469);

/*!40000 ALTER TABLE `teaching_evaluation` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
