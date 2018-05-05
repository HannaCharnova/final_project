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
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `idproduct` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Синтетический ключ, который играет роль уникального идентификатора каждого блюда',
  `name_en` varchar(70) CHARACTER SET utf8 NOT NULL COMMENT 'Наименование блюда',
  `cost` double unsigned NOT NULL COMMENT 'Стоимость одной порции блюда',
  `type` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT 'Тип блюда, т.е. первое, второе, десерт, алкогольный/безалкогольный напиток и пр.',
  `weight` double unsigned NOT NULL COMMENT 'Вес одной порции блюда',
  `image_path` varchar(45) NOT NULL,
  `name_ru` varchar(70) CHARACTER SET utf8 NOT NULL COMMENT 'Наименование блюда',
  PRIMARY KEY (`idproduct`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=latin1 COMMENT='Таблица предназначена для хранения информации о блюдах, которые готовятся в данном кафе, т.е. является своеобразным меню';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (197,'Cheesecakes with sour cream and berry sauce',4.3,'завтраки',130,'syrniki.jpg','Сырники со сметаной и ягодным соусом'),(198,'American sandwich with beef',7.9,'завтраки',300,'sandwich.jpg','Американский сэндвич с говядиной'),(199,'Cheeseburger',6.9,'завтраки',200,'burger.jpg','Чизбургер'),(200,'Pancakes with ham, chicken and cheese',4.9,'завтраки',180,'pancakes.jpg','Блинчики с ветчиной, курицей и сыром'),(201,'Scrambled eggs',2,'завтраки',100,'yaichnica.jpg','Яичница'),(202,'Omelet',2,'завтраки',120,'omlet.jpg','Омлет '),(203,'Oatmeal porridge',2,'завтраки',200,'ovsyanka.jpg','Каша овсяная'),(204,'Pizza Margarita',7.9,'пицца',410,'margarita.jpg','Пицца Маргарита'),(205,'Pizza Mexican',14.9,'пицца',640,'meksikanskay.jpg','Пицца Мексиканская'),(206,'Pepperoni Pizza',11.4,'пицца',460,'peperoni.jpg','Пицца Пепперони'),(207,'Pizza Super Pepperoni',13.7,'пицца',510,'super_peperoni.jpg','Пицца Супер пепперони'),(208,'Pizza Carbonara',12.6,'пицца',570,'carbonara.jpg','Пицца Карбонара'),(209,'Pizza Siciliana',14.9,'пицца',600,'siciliana.jpg','Пицца Сицилиана'),(210,'Suzuki',33.5,'суши',870,'sudzuki.jpg','Судзуки'),(211,'Toyota',33.5,'суши',850,'tayota.jpg','Тойота'),(212,'Infiniti',33.5,'суши',872,'infiniti.jpg','Инфинити'),(213,'Tar-tar from salmon',12.9,'салаты и закуски',165,'tartar_losos.jpg','Тар-тар из лосося'),(214,'Pollo Tonnato',8.4,'салаты и закуски',155,'pollo.jpg','Полло Тоннато'),(215,'Salad with falafely',8.3,'салаты и закуски',250,'falafeli.jpg','Салат с фалафели'),(216,'Caesar with smoked salmon',11.9,'салаты и закуски',165,'cezar_losos.jpg','Цезарь с копченым лососем'),(217,'Caesar with chicken',8.9,'салаты и закуски',200,'cezar_kurica.jpg','Цезарь с курицей'),(218,'Mexican salad with pork Taco',8.4,'салаты и закуски',190,'tako.jpg','Мексиканский салат со свининой Тако'),(219,'Steak with egg and mashed potatoes',10.5,'горячие блюда',300,'bifshteks.jpg','Бифштекс с яйцом и картофельным пюре'),(220,'Grilled salmon steak 50 °',16.9,'горячие блюда',180,'steik_losos.jpg','Стейк из лосося 50°'),(221,'Chicken sausages with stewed cabbage',10.7,'горячие блюда',200,'kolbaski.jpg','Куриные колбаски с тушеной капустой'),(222,'Duck leg with vegetables on the grill',14.9,'горячие блюда',180,'utka.jpg','Утиная ножка с овощами на гриле'),(223,'Cheesecake',5.7,'десерты',130,'cheezcake.jpg','Чизкейк'),(224,'Vanilla-chocolate ice cream with forest berries',5.2,'десерты',210,'morozh.jpg','Мороженое ванильно-шоколадное с лесными ягодами'),(225,'Crepe-suzet with orange caramel',3.7,'десерты',160,'krept.jpg','Креп-сузет с апельсиновой карамелью'),(226,'Vanilla ice cream with caramel poppy seeds with elderberry flavor',4.7,'десерты',100,'morozh_vanil.jpg','Ванильное мороженое с карамельным маком с ароматом бузины'),(227,'Oreo',5.9,'милкшейки',311,'oreo.jpg','Орео'),(228,'Banana Caramel',4.9,'милкшейки',355,'banan.jpg','Банан Карамель'),(229,'Клубничный чай с корицей и лимоном',4.9,'милкшейки',351,'klubnichnuy.jpg','Клубничный'),(230,'Snickers',5.9,'милкшейки',325,'snikers.jpg','Сникерс'),(231,'Sea-buckthorn-Banana',4.4,'смуззи',242,'oblepiha.jpg','Облепиха-Банан'),(232,'Caffeine',4.4,'смуззи',266,'kofe.jpg','Кофеиновый'),(233,'Berry',4.4,'смуззи',268,'klubnichnuy_smuz.jpg','Ягодный'),(234,'Chocolate-Mint Latte',4,'кофе',236,'myata_latte.jpg','Шоколадно-мятный Латте'),(235,'Chocolate latte with marshmallow',4,'кофе',230,'marshmello.jpg','Шоколадный латте с Маршмеллоу'),(236,'Raspberry latte',4,'кофе',230,'malin_latte.jpg','Малиновый латте'),(237,'Caramel - popcorn',4.9,'кофе',295,'karamel.jpg','Карамель - попкорн'),(238,'Ice Latte Vanilla',3.5,'кофе',200,'ice_latte.jpg','Айс латте ваниль'),(239,'Cocoa Classic',3,'какао',297,'classik_cacao.jpg','Какао классический'),(240,'Cocoa from Marshmelow',3.5,'какао',308,'kakao_marsh.jpg','Какао с Маршмеллоу'),(241,'Chocolate Cocoa',3.5,'какао',322,'choko_cocao.jpg','Шоколадный Какао'),(242,'Cocoa with Whipped Cream',3.5,'какао',328,'slivki.jpg','Какао с Взбитыми сливками'),(243,'Ginger tea with honey and rosemary',6.9,'чай',594,'imbir.jpg','Имбирный чай с медом и розмарином'),(244,'Strawberry tea with cinnamon and lemon',6.9,'чай',550,'klubn_chai.jpg','Клубничный чай с корицей и лимоном'),(245,'Mint tea',6.9,'чай',545,'myata_chai.jpg','Мятный чай'),(246,'Sea-buckthorn tea with mint',6.9,'чай',552,'oblepiha_chai.jpg','Облепиховый чай с мятой'),(247,'Tropical tea',5.6,'чай',690,'tropich_chai.jpg','Тропический чай'),(248,'Raspberry tea with rosemary',3.5,'чай',228,'rozmarin.jpg','Малиновый чай с розмарин'),(249,'Apple Punch with cinnamon and lime',3.5,'чай',213,'punsh.jpg','Яблочный Пунш с корицей и лаймом'),(250,'Tom kha with chicken',5.9,'супы',280,'tom_kha.jpg','Том кха с курицей'),(251,'Tomato soup with shrimps',6.4,'супы',280,'tomatnuy.jpg','Томатный суп с креветками'),(252,'Solyanka',5.5,'супы',280,'solyanka.jpg','Солянка');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-06  1:02:51
