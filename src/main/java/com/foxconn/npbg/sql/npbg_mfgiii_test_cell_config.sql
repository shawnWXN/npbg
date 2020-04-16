CREATE DATABASE  IF NOT EXISTS `npbg` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `npbg`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 10.167.219.250    Database: npbg
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mfgiii_test_cell_config`
--

DROP TABLE IF EXISTS `mfgiii_test_cell_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mfgiii_test_cell_config` (
  `line_id` tinyint NOT NULL COMMENT '线体id',
  `seq_id` tinyint NOT NULL COMMENT '工站id',
  `station` varchar(10) NOT NULL COMMENT '工站名',
  `server` varchar(20) NOT NULL COMMENT 'server名',
  `slot_id` int NOT NULL COMMENT 'slot在该工站的位置',
  `slot` varchar(20) NOT NULL COMMENT '插槽编号',
  `slot_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'slot在db里的实际字段名',
  `slot_type` varchar(20) NOT NULL COMMENT 'slot实际字段名的类型',
  `source` varchar(20) NOT NULL DEFAULT '' COMMENT '数据来源，目前仅限UDTS和ITMS_ORIGINAL两项'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='NPBG制造三处处测试cell配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mfgiii_test_cell_config`
--

LOCK TABLES `mfgiii_test_cell_config` WRITE;
/*!40000 ALTER TABLE `mfgiii_test_cell_config` DISABLE KEYS */;
INSERT INTO `mfgiii_test_cell_config` VALUES (1,1,'ICT','MFG3ICT01',1,'','','','ITMS_ORIGINAL'),(1,1,'ICT','MFG3ICT02',2,'','','','ITMS_ORIGINAL'),(1,2,'BST','fxcapp962',1,'01','testr2','str','udts'),(1,2,'BST','fxcapp962',2,'02','testr2','str','udts'),(1,2,'BST','fxcapp962',3,'03','testr2','str','udts'),(1,2,'BST','fxcapp962',4,'04','testr2','str','udts'),(1,2,'BST','fxcapp962',5,'05','testr2','str','udts'),(1,2,'BST','fxcapp962',6,'06','testr2','str','udts'),(1,2,'BST','fxcapp962',7,'07','testr2','str','udts'),(1,2,'BST','fxcapp962',8,'08','testr2','str','udts'),(1,3,'LED-LCD','fxcipcvmfst10',1,'81','cell','int','udts'),(1,3,'LED-LCD','fxcipcvmfst10',2,'82','cell','int','udts'),(1,3,'LED-LCD','fxcipcvmfst10',3,'83','cell','int','udts'),(1,3,'LED-LCD','fxcipcvmfst10',4,'84','cell','int','udts'),(1,3,'LED-LCD','fxcipcvmfst10',5,'85','cell','int','udts'),(1,3,'LED-LCD','fxcipcvmfst10',6,'86','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',1,'53','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',2,'55','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',3,'57','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',4,'59','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',5,'61','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',6,'63','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',7,'65','cell','int','udts'),(1,4,'DIAG','fxcipcvmfst10',8,'67','cell','int','udts'),(1,5,'KEYPAD','fxcipcvmfst10',1,'69','cell','int','udts'),(1,5,'KEYPAD','fxcipcvmfst10',2,'71','cell','int','udts'),(1,5,'KEYPAD','fxcipcvmfst10',3,'73','cell','int','udts'),(1,5,'KEYPAD','fxcipcvmfst10',4,'75','cell','int','udts'),(1,5,'KEYPAD','fxcipcvmfst10',5,'77','cell','int','udts'),(1,5,'KEYPAD','fxcipcvmfst10',6,'79','cell','int','udts'),(2,1,'ICT','MFG3ICT03',1,'','','','ITMS_ORIGINAL'),(2,1,'ICT','MFG3ICT04',2,'','','','ITMS_ORIGINAL'),(2,2,'BST','fxcipcvmauto1',1,'25','cell','int','udts'),(2,2,'BST','fxcipcvmauto1',2,'26','cell','int','udts'),(2,2,'BST','fxcipcvmauto1',3,'27','cell','int','udts'),(2,2,'BST','fxcipcvmauto1',4,'28','cell','int','udts'),(2,2,'BST','fxcipcvmauto1',5,'29','cell','int','udts'),(2,2,'BST','fxcipcvmauto1',6,'30','cell','int','udts'),(2,3,'LED-LCD','fxcipcvmfst7',1,'81','cell','int','udts'),(2,3,'LED-LCD','fxcipcvmfst7',2,'82','cell','int','udts'),(2,3,'LED-LCD','fxcipcvmfst7',3,'83','cell','int','udts'),(2,3,'LED-LCD','fxcipcvmfst7',4,'84','cell','int','udts'),(2,3,'LED-LCD','fxcipcvmfst7',5,'85','cell','int','udts'),(2,3,'LED-LCD','fxcipcvmfst7',6,'86','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',1,'53','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',2,'55','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',3,'57','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',4,'59','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',5,'61','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',6,'63','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',7,'65','cell','int','udts'),(2,4,'DIAG','fxcipcvmfst7',8,'67','cell','int','udts'),(2,5,'KEYPAD','fxcipcvmfst7',1,'69','cell','int','udts'),(2,5,'KEYPAD','fxcipcvmfst7',2,'71','cell','int','udts'),(2,5,'KEYPAD','fxcipcvmfst7',3,'73','cell','int','udts'),(2,5,'KEYPAD','fxcipcvmfst7',4,'75','cell','int','udts'),(2,5,'KEYPAD','fxcipcvmfst7',5,'77','cell','int','udts'),(2,5,'KEYPAD','fxcipcvmfst7',6,'79','cell','int','udts'),(3,1,'ICT','MFG3ICT05',1,'','','','ITMS_ORIGINAL'),(3,1,'ICT','MFG3ICT19',2,'','','','ITMS_ORIGINAL'),(3,2,'BST','fxcapp1245',1,'01','testr2','str','udts'),(3,2,'BST','fxcapp1245',2,'02','testr2','str','udts'),(3,2,'BST','fxcapp1245',3,'03','testr2','str','udts'),(3,2,'BST','fxcapp1245',4,'04','testr2','str','udts'),(3,2,'BST','fxcapp1245',5,'05','testr2','str','udts'),(3,2,'BST','fxcapp1245',6,'06','testr2','str','udts'),(3,2,'BST','fxcapp1245',7,'07','testr2','str','udts'),(3,2,'BST','fxcapp1245',8,'08','testr2','str','udts'),(3,3,'LED-LCD','fxcapp1171',1,'81','testr1','str','udts'),(3,3,'LED-LCD','fxcapp1171',2,'82','testr1','str','udts'),(3,3,'LED-LCD','fxcapp1171',3,'83','testr1','str','udts'),(3,3,'LED-LCD','fxcapp1171',4,'84','testr1','str','udts'),(3,3,'LED-LCD','fxcapp1171',5,'85','testr1','str','udts'),(3,3,'LED-LCD','fxcapp1171',6,'86','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',1,'53','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',2,'55','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',3,'57','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',4,'59','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',5,'61','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',6,'63','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',7,'65','testr1','str','udts'),(3,4,'DIAG','fxcapp1171',8,'67','testr1','str','udts'),(3,5,'KEYPAD','fxcapp1171',1,'69','testr1','str','udts'),(3,5,'KEYPAD','fxcapp1171',2,'71','testr1','str','udts'),(3,5,'KEYPAD','fxcapp1171',3,'73','testr1','str','udts'),(3,5,'KEYPAD','fxcapp1171',4,'75','testr1','str','udts'),(3,5,'KEYPAD','fxcapp1171',5,'77','testr1','str','udts'),(3,5,'KEYPAD','fxcapp1171',6,'79','testr1','str','udts');
/*!40000 ALTER TABLE `mfgiii_test_cell_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-16 17:39:58
