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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `idorder` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Синтетический ключ, который играет роль уникального идентификатора каждого заказа',
  `total_cost` double NOT NULL DEFAULT '0' COMMENT 'Итоговая стоимость заказа с учетом всех бонусов клиента и подсчета стоимости всех входящих в заказ блюд',
  `date` date NOT NULL COMMENT 'Указывающаяся клиентом дата, к которой заказ должен быть готов',
  `client_user_iduser` int(10) unsigned NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idorder`),
  UNIQUE KEY `idorder_UNIQUE` (`idorder`),
  KEY `fk_order_client1_idx` (`client_user_iduser`),
  CONSTRAINT `fk_order_client1` FOREIGN KEY (`client_user_iduser`) REFERENCES `client` (`user_iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 COMMENT='Таблица предназначена для хранения информации о клиентских заказах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,37.4,'2018-05-07',7,0),(2,47.8,'2018-05-09',22,1),(3,61.8,'2018-05-11',30,0),(4,16.5,'2018-05-09',7,0),(5,39,'2018-05-11',22,0),(6,4.3,'2018-05-09',16,0),(7,3350,'2018-05-09',16,0),(9,41.6,'2018-05-13',7,0),(10,15.8,'2018-05-14',27,0),(12,4.3,'2018-05-15',27,1),(13,8.6,'2018-05-27',12,0),(14,16.5,'2018-05-27',7,0),(15,20.799999999999997,'2018-05-27',7,0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-27 13:05:51
