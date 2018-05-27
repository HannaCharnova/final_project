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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `name` varchar(20) NOT NULL COMMENT 'Имя клиента',
  `surname` varchar(20) NOT NULL COMMENT 'Фамилия клиента',
  `email` varchar(30) NOT NULL COMMENT 'Адрес электронной почты клиента, который необходим для рассылки уведомлений системы, либо для восстановления пароля',
  `point` decimal(3,1) DEFAULT NULL COMMENT 'Баллы лояльности, которые начисляются клиенту автоматически в случае предварительного совершения заказа, либо снимаются в случае, если клиент не забирает готовый заказ',
  `ban` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Статус клиента в системе, т.е. клиент может быть либо забанен, либо разбанен. По умолчанию при регистрации бан у клиента отсутствует',
  `user_iduser` int(10) unsigned NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`user_iduser`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `user_iduser_UNIQUE` (`user_iduser`),
  KEY `fk_client_user1_idx` (`user_iduser`),
  CONSTRAINT `fk_client_user1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица предназначена для хранения сведений о клиентах, зарегистрированных в системе';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('Екатерина','Букатова','bukatova@gmail.com',2.0,0,7,'ул.Крупской,д.51,кв 56'),('Винцаревич','Владислав','vintsarevich@gmail.com',4.0,0,8,'ул.Крупской,д.51,кв 56'),('Тимур','Анашкин','anachkin@gmail.com',1.0,0,9,'ул.Крупской,д.51,кв 56'),('Никита','Аробей','arobey@gmail.com',0.0,0,10,'ул.Крупской,д.51,кв 56'),('Руслан','Баланович','balanovich@gmail.com',0.0,0,11,'ул.Крупской,д.51,кв 56'),('Иван','Будаев','budaev@gmail.com',0.0,0,12,'ул.Крупской,д.51,кв 56'),('Оксана','Бурнос','burnos@gmail.com',0.0,0,13,'ул.Крупской,д.51,кв 56'),('Яна','Голодюк','goloduk@gmail.com',0.0,0,14,'ул.Крупской,д.51,кв 56'),('Вероника','Григорьева','grigorieva@gmail.com',0.0,0,15,'ул.Крупской,д.51,кв 56'),('Дмитрий','Волощук','voloshuk@gmail.com',0.0,0,16,'ул.Крупской,д.51,кв 56'),('Вероника','Марченко','marchenko@gmail.com',1.0,0,17,'ул.Крупской,д.51,кв 56'),('Андрей','Марковский','markovskiy@gmal.com',1.0,0,18,'ул.Крупской,д.51,кв 56'),('Екатерина','Макаревич','makarevich@gmail.com',1.0,0,19,'ул.Крупской,д.51,кв 56'),('Анастасия','Михмель','mihmel@gmail.com',1.0,0,20,'ул.Крупской,д.51,кв 56'),('Егор','Олешкевич','oleshkevich@gmail.com',4.0,0,21,'ул.Крупской,д.51,кв 56'),('Анастасия','Потапчук','potapchuk@gmail.com',5.0,0,22,'ул.Крупской,д.51,кв 56'),('Арина','Делендик','delendik@gmail.com',2.0,0,23,'ул.Крупской,д.51,кв 56'),('Евгений','Гулинский','gulinski@gmail.com',0.0,0,24,'ул.Крупской,д.51,кв 56'),('Кирилл','Лосиков','losikov@gmail.com',0.0,0,25,'ул.Крупской,д.51,кв 56'),('Иван','Подгайский','podgaiski@gmail.com',0.0,0,26,'ул.Крупской,д.51,кв 56'),('Артемка','Лайша','laisha@gmail.com',0.0,0,27,'ул.Крупской,д.51,кв 30'),('Иван','Стефанович','stefanovich@gmail.com',0.0,0,28,'ул.Крупской,д.51,кв 56'),('Вероника','Симоненко','simonenko@gmail.com',0.0,0,29,'ул.Крупской,д.51,кв 56'),('Андрей','Кравцов','kravcov@gmail.com',4.0,0,30,'ул.Крупской,д.51,кв 56'),('Олег','Радкевич','radkevich@gmail.com',6.0,0,31,'ул.Крупской,д.51,кв 56'),('Petr','Petrov','petrov@gmail.com',7.0,0,51,'ул.Крупской,д.51,кв 56');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
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
