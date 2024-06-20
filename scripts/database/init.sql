-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: manage_apartment
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `User_id` int NOT NULL,
  PRIMARY KEY (`User_id`),
  KEY `fk_Admin_User1_idx` (`User_id`),
  CONSTRAINT `fk_Admin_User1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabinet`
--

DROP TABLE IF EXISTS `cabinet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabinet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cabinetcol` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Contract_id` int NOT NULL,
  `isActive` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_cabinet_Contract1_idx` (`Contract_id`),
  CONSTRAINT `fk_cabinet_Contract1` FOREIGN KEY (`Contract_id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinet`
--

LOCK TABLES `cabinet` WRITE;
/*!40000 ALTER TABLE `cabinet` DISABLE KEYS */;
INSERT INTO `cabinet` VALUES (1,'Tủ của phòng',25,0),(2,'tủ chứa cức',26,1);
/*!40000 ALTER TABLE `cabinet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Room_id` int NOT NULL,
  `Resident_User_id` int NOT NULL,
  `total_month` int NOT NULL,
  `security_deposit` decimal(18,0) NOT NULL,
  `created_date` date NOT NULL,
  `started_date` date NOT NULL,
  `ended_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Room_has_Resident_Resident1_idx` (`Resident_User_id`),
  KEY `fk_Room_has_Resident_Room1_idx` (`Room_id`),
  CONSTRAINT `fk_Room_has_Resident_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`),
  CONSTRAINT `fk_Room_has_Resident_Room1` FOREIGN KEY (`Room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (25,15,96,12,23232323,'2024-06-10','2003-01-28','2004-01-28'),(26,16,97,12,232323,'2024-06-10','2038-01-28','2039-01-28');
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criterion`
--

DROP TABLE IF EXISTS `criterion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criterion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criterion`
--

LOCK TABLES `criterion` WRITE;
/*!40000 ALTER TABLE `criterion` DISABLE KEYS */;
/*!40000 ALTER TABLE `criterion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_evoluation`
--

DROP TABLE IF EXISTS `detail_evoluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_evoluation` (
  `Criterion_id` int NOT NULL,
  `Evaluation_id` int NOT NULL,
  `Rate` enum('Không hài lòng','Hài lòng','Rất hài lòng') DEFAULT NULL,
  PRIMARY KEY (`Criterion_id`,`Evaluation_id`),
  KEY `fk_Criterion_has_Evaluation_Evaluation1_idx` (`Evaluation_id`),
  KEY `fk_Criterion_has_Evaluation_Criterion1_idx` (`Criterion_id`),
  CONSTRAINT `fk_Criterion_has_Evaluation_Criterion1` FOREIGN KEY (`Criterion_id`) REFERENCES `criterion` (`id`),
  CONSTRAINT `fk_Criterion_has_Evaluation_Evaluation1` FOREIGN KEY (`Evaluation_id`) REFERENCES `evaluation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_evoluation`
--

LOCK TABLES `detail_evoluation` WRITE;
/*!40000 ALTER TABLE `detail_evoluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `detail_evoluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `feedback` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Resident_User_id` int NOT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Evaluation_Resident1_idx` (`Resident_User_id`),
  CONSTRAINT `fk_Evaluation_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family_member`
--

DROP TABLE IF EXISTS `family_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `family_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `Resident_User_id` int NOT NULL,
  `dob` date NOT NULL,
  `createdAt` date DEFAULT NULL,
  `relationship_type` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Family_member_Resident1_idx` (`Resident_User_id`),
  CONSTRAINT `fk_Family_member_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_member`
--

LOCK TABLES `family_member` WRITE;
/*!40000 ALTER TABLE `family_member` DISABLE KEYS */;
INSERT INTO `family_member` VALUES (11,'Tran Kien',1,135,'2024-05-29','2024-06-15','Ông'),(12,'Le Be',1,135,'2024-05-20','2024-06-15','Bà');
/*!40000 ALTER TABLE `family_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (1,'Tầng 1'),(2,'Tầng 2'),(3,'Tầng 3');
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incurred_cost`
--

DROP TABLE IF EXISTS `incurred_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incurred_cost` (
  `id` int NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(18,0) DEFAULT NULL,
  `Incurred_costcol` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Receipt_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Incurred_cost_Receipt1_idx` (`Receipt_id`),
  CONSTRAINT `fk_Incurred_cost_Receipt1` FOREIGN KEY (`Receipt_id`) REFERENCES `receipt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incurred_cost`
--

LOCK TABLES `incurred_cost` WRITE;
/*!40000 ALTER TABLE `incurred_cost` DISABLE KEYS */;
/*!40000 ALTER TABLE `incurred_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `cabinet_id` int NOT NULL,
  `image` varchar(255) NOT NULL,
  `received_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Items_cabinet1_idx` (`cabinet_id`),
  CONSTRAINT `fk_Items_cabinet1` FOREIGN KEY (`cabinet_id`) REFERENCES `cabinet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Quần áo','áo quần','2024-06-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp','2024-06-15'),(2,'Đồ Gia Dụng','đồ gia dụng','2024-05-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp','2024-06-07'),(3,'Quần Áo','ád','2024-11-11',2,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718109827/syzxjdhfxkyvkwafvlly.jpg',NULL),(4,'Đồ Điện Tử','Iphone','2024-04-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718109827/syzxjdhfxkyvkwafvlly.jpg','2024-06-15'),(12,'Bút','Bút','2024-02-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718109827/syzxjdhfxkyvkwafvlly.jpg','2024-06-15'),(13,'Bút','Bút','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718109827/syzxjdhfxkyvkwafvlly.jpg','2024-06-15'),(14,'Bút','Bút','2024-01-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718109827/syzxjdhfxkyvkwafvlly.jpg',NULL),(15,'LapTop','LapTop','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718109827/syzxjdhfxkyvkwafvlly.jpg',NULL),(16,'Cặp Sách','A','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(17,'Quần Áo','Áo Cộc','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(18,'Đồ Tiện Tủ','Laptop','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(19,'Bút Viết','Bút Mực','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(20,'Sách Vở','Sách Giáo Khoa','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(21,'Giày Dép','Giày Thể Thao','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(22,'Đồ Chơi','Xe Đạp','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(23,'Văn Phòng Phẩm','Giấy A4','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(24,'Đồ Điện','Quạt Điện','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL),(25,'Đồ Nhà Bếp','Nồi Cơm Điện','2024-03-06',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718096365/box-500x500_lsuubw.webp',NULL);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_in_room`
--

DROP TABLE IF EXISTS `member_in_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_in_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Contract_id` int NOT NULL,
  `Resident_User_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Contract_has_Resident_Resident1_idx` (`Resident_User_id`),
  KEY `fk_Contract_has_Resident_Contract1_idx` (`Contract_id`),
  CONSTRAINT `fk_Contract_has_Resident_Contract1` FOREIGN KEY (`Contract_id`) REFERENCES `contract` (`id`),
  CONSTRAINT `fk_Contract_has_Resident_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_in_room`
--

LOCK TABLES `member_in_room` WRITE;
/*!40000 ALTER TABLE `member_in_room` DISABLE KEYS */;
INSERT INTO `member_in_room` VALUES (8,26,98),(11,26,124),(13,25,135);
/*!40000 ALTER TABLE `member_in_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Contract_id` int NOT NULL,
  `started_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Receipt_Contract1_idx` (`Contract_id`),
  CONSTRAINT `fk_Receipt_Contract1` FOREIGN KEY (`Contract_id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt_detail`
--

DROP TABLE IF EXISTS `receipt_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `receipt_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Services_id` int NOT NULL,
  `Receipt_id` int NOT NULL,
  `amount` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Services_has_Receipt_Receipt1_idx` (`Receipt_id`),
  KEY `fk_Services_has_Receipt_Services1_idx` (`Services_id`),
  CONSTRAINT `fk_Services_has_Receipt_Receipt1` FOREIGN KEY (`Receipt_id`) REFERENCES `receipt` (`id`),
  CONSTRAINT `fk_Services_has_Receipt_Services1` FOREIGN KEY (`Services_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_detail`
--

LOCK TABLES `receipt_detail` WRITE;
/*!40000 ALTER TABLE `receipt_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `receipt_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `content` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `created_date` date NOT NULL,
  `Resident_User_id` int NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Report_Resident1_idx` (`Resident_User_id`),
  CONSTRAINT `fk_Report_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resident`
--

DROP TABLE IF EXISTS `resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resident` (
  `User_id` int NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `date_of_birth` varchar(45) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `ward` varchar(45) DEFAULT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `number_plate` varchar(10) NOT NULL,
  `address` varchar(45) NOT NULL,
  `identity` varchar(12) NOT NULL,
  PRIMARY KEY (`User_id`),
  KEY `fk_resident_User1_idx` (`User_id`),
  CONSTRAINT `fk_resident_User1` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resident`
--

LOCK TABLES `resident` WRITE;
/*!40000 ALTER TABLE `resident` DISABLE KEYS */;
INSERT INTO `resident` VALUES (96,'Antien12','2003-01-28',1,NULL,'Tỉnh Cà Mau','Huyện U Minh','0938438132','antien2k3@gmail.com','2323','bc1q6krzxt665awpa5f8wst45k7terd944lepn7hdc','079203011225'),(97,'tranAntien','32323-02-23',1,NULL,'Thành phố Hồ Chí Minh','Quận 6','2323','antien2k3@gmail.com','2323','232323','079203011225'),(98,'TheAnh','',0,NULL,NULL,NULL,'232323',NULL,'2323','hoc mon','0123456789'),(124,'Hung','',0,NULL,NULL,NULL,'2322',NULL,'232323','32323','23232323'),(135,'TheAnh','2003-01-28',0,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718642722/emw0z96napecbphhzo8b.jpg','HCM','HocMon','0938438132','a@gmail.com','123','123','25645'),(136,'NguyenTheAnh','2003-02-02',1,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1718642552/tv34osw2tsl4vhncgwco.jpg','Ho Chi Minh','Hoc Mon','0363402342','b@gmail.com','23425','bc - hem2','234224534');
/*!40000 ALTER TABLE `resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `Price` decimal(18,0) NOT NULL,
  `length` float NOT NULL,
  `width` float NOT NULL,
  `maximum` int NOT NULL,
  `description` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` enum('Bảo trì','còn trống','đã thuê') NOT NULL,
  `Floor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Room_Floor_idx` (`Floor_id`),
  CONSTRAINT `fk_Room_Floor` FOREIGN KEY (`Floor_id`) REFERENCES `floor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (15,'Phòng 1.1',4000000,5,5,3,'phòng như lozz','đã thuê',1),(16,'Phòng 1.2',3500000,6,6,2,'phòng như cặc','đã thuê',1),(17,'Phòng 1.3',2000000,3,5,1,'phòng dơ bỏ mẹ','còn trống',1),(18,'Phòng 1.4',3000000,5,6,2,'phòng có cức','còn trống',1),(19,'phòng 2.1',8000000,6,10,4,'phòng có ma','còn trống',2),(20,'Phòng 2.2',4000000,4,5,2,'phòng bị ngu','còn trống',2),(21,'Phòng 2.3',5600000,7,8,2,'Phòng có thằng ngu','còn trống',2),(22,'Phòng 2.4',4400000,6,4,2,'nhà như cặc','còn trống',2);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `price` varchar(45) NOT NULL,
  `unit` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Điện',NULL,'3000','kwh'),(2,'Nước',NULL,'7000','m3'),(3,'Xe',NULL,'70000','xe');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(20) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (96,'079203011225','$2a$10$VH/K.EIHNydPoR8br41g1.vPtrzPVkHNNYnJN1RPE0MW0G.u9tPo2','Resident',1),(97,'123322323','$2a$10$t5onyG6kParokYI/r1BXdeAte3ZgY4zcOb8yH/NAUIwhGtsLV9m.2','Resident',1),(98,'0123456789','$2a$10$06sI8LyDGHZDHrGnczaF/uaRa1v7pfiuICmrg990LfLvPaWNWYjL6','Resident',1),(124,'23232323','$2a$10$16xnyKdkEXtH162.clUHseZzVMGlwgCpiuDq1TlaXBl1ALvEe24Q2','Resident',1),(135,'admin1','$2a$10$LBd1qAVE3xsJxLxlkyT9iuamNzhzlEpRfDEY02QAkSsp0XHZpoCdq','ROLE_USER',1),(136,'resident','$2a$10$kjMLZc0efecNq7GF3BORaOQFQ6hvtImJG0WeWW9F6SEohVnhG8xpO','ROLE_USER',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-20  1:22:57
