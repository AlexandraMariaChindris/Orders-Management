-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: baza_de_date
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id_bill` int NOT NULL AUTO_INCREMENT,
  `nume_client` varchar(45) DEFAULT NULL,
  `nume_produs` varchar(45) DEFAULT NULL,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`id_bill`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (2,'Pop Alexandru','Fond de ten N1',55),(3,'Chindris Alexandra','Ruj MAC',200),(4,'Simona Sim','Fond de ten N2',156);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Pop Alexandru','Bistrita','alexandru@gmail.com'),(3,'Chis Vladut','Beclean','vlad@gmail.com'),(4,'Adi Alexandru','Cluj-Napoca','adi@gmail.com'),(11,'Chindris Alexandra','Tirlisua','ale.chindris13@gmail.com'),(13,'Ovidiu','Borleasa','daw@gmail.com'),(14,'Pop Adrian','Agries','adrian@gmail.com'),(15,'Simona Sim','Dorobantilor','sim@gmail.com'),(16,'Rus Lucian','Zorilor','luci@gmail.com'),(17,'Chereches Deea','Nicolae','deea@gmail.com'),(18,'Zorica Rus','Daicoviciu','zori@gmail.com'),(19,'Miruna ','Sighisoara','miru@gmail.com'),(20,'Dorian','Bistrita','dori@gmail.com'),(21,'Denisa','Zorilor','deni@gmail.com'),(22,'Gabriela','Maieru','gabi@gmail.com'),(23,'Denis Dobr','Bistrita','denis@gmail.com'),(24,'Daniela Cot','Cluj-Napoca','daniela@gmail.com'),(25,'Dana Pop','Manastur','dana@gmail.com'),(26,'Buzila Maria','Bistrita','maria@gmail.com'),(27,'Sara Mona','Marasti','sara@gmail.com'),(28,'Cristina Pop','Borleasa','Cristi@gmail.com'),(29,'Anamaria Rus','Bistrita','ana@gmail.com'),(30,'Niculina Timofe','Bistrita','nina@gmail.com'),(31,'Mihai Rus','Beclean','miha@gmail.com'),(32,'Vlad Ise','Faget','ise@gmail.com'),(33,'Mihaela Ise','Faget','miha@gmail.com'),(34,'Iulia Ise','Oasului','iulia@gmail.com'),(35,'Dan Ise','Faget','dan@gmail.com'),(36,'nume','adresa','email@gmaul.com');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `nume_produs` varchar(45) DEFAULT NULL,
  `cantitate_disponibila` int DEFAULT NULL,
  `pret` int DEFAULT NULL,
  PRIMARY KEY (`id_product`),
  UNIQUE KEY `nume_produs_UNIQUE` (`nume_produs`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (8,'Rimmel Revolution',5,24),(9,'Pudra Catrice',1,17),(11,'Fond de ten N1',15,55),(12,'Ruj MAC',8,100),(14,'Creion sprancene',1,40),(15,'Rimmel Catrice',1,25),(16,'Fond de ten N2',0,78),(17,'Fond de ten N3',5,84),(18,'Ruj Catrice 2.1',4,18),(19,'Pudra Bourjoi',2,95),(20,'Creion buze Evelin2',5,15),(21,'Rimmel Maybelline',5,58),(22,'Concealer Catrice2.0',8,30),(23,'Bronzer 5.1',4,38),(24,'Blush 4.0',8,26),(25,'Blush 3.6',10,35),(26,'Ruj MAC 2.5',24,120),(27,'Luciu de buze',32,40),(28,'Fard sprancene 2.2',15,75),(29,'Gel sprancene',13,60),(30,'Concealer 4.8',10,35),(31,'Creion buze 5.9',24,21),(32,'Ir de buze 2',1,24),(33,'Lipici gene',31,40),(34,'Fond de ten N5',29,90);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_order`
--

DROP TABLE IF EXISTS `product_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_order` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `produs` varchar(45) DEFAULT NULL,
  `nume_client` varchar(45) DEFAULT NULL,
  `cantitate_comandata` int DEFAULT NULL,
  PRIMARY KEY (`id_order`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_order`
--

LOCK TABLES `product_order` WRITE;
/*!40000 ALTER TABLE `product_order` DISABLE KEYS */;
INSERT INTO `product_order` VALUES (28,'Fond de ten N1','Pop Alexandru',1),(29,'Ruj MAC','Chindris Alexandra',2),(30,'Fond de ten N2','Simona Sim',2);
/*!40000 ALTER TABLE `product_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'baza_de_date'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-15  0:31:28
