-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cafe
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `idaccount` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Синтетический ключ, который играет роль уникального идентификатора клиентского денежного счета',
  `credit` decimal(10,2) DEFAULT NULL COMMENT 'Количество денег, которые хранятся на данном счете',
  `account_number` varchar(7) CHARACTER SET utf8 NOT NULL COMMENT 'Уникальный номер денежного счета, который предоставляется банком при его регистрации',
  `client_user_iduser` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idaccount`),
  UNIQUE KEY `account_number_UNIQUE` (`account_number`),
  KEY `fk_account_client1_idx` (`client_user_iduser`),
  CONSTRAINT `fk_account_client1` FOREIGN KEY (`client_user_iduser`) REFERENCES `client` (`user_iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1 COMMENT='Таблица предназначена для хранения сведений о денежных счетах клиентов';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (37,103.50,'1756753',7),(38,50.00,'0567983',8),(39,4500.00,'4758960',9),(40,238.00,'1209545',10),(41,676.00,'1051135',11),(42,108.00,'0195245',13),(43,2306.00,'1847256',15),(44,18.70,'8540265',16),(45,500.00,'2759557',17),(46,478.00,'1452755',18),(47,3900.00,'1054835',20),(48,209.00,'1045463',21),(49,499.00,'1055335',22),(50,295.00,'2065356',23),(51,3756.00,'5556669',24),(52,246.00,'2477089',25),(53,79.00,'2455666',26),(56,5490.39,'1234567',27);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-16  0:16:44
