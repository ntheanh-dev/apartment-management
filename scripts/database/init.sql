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
  `cabinetcol` varchar(45) DEFAULT NULL,
  `Contract_id` int NOT NULL,
  `isActive` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_cabinet_Contract1_idx` (`Contract_id`),
  CONSTRAINT `fk_cabinet_Contract1` FOREIGN KEY (`Contract_id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinet`
--

LOCK TABLES `cabinet` WRITE;
/*!40000 ALTER TABLE `cabinet` DISABLE KEYS */;
INSERT INTO `cabinet` VALUES (19,'Tủ của 101',38,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (38,24,171,12,2000000,'2024-06-25','2024-06-25','2025-06-25');
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
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criterion`
--

LOCK TABLES `criterion` WRITE;
/*!40000 ALTER TABLE `criterion` DISABLE KEYS */;
INSERT INTO `criterion` VALUES (4,'Vệ sinh'),(5,'An ninh'),(6,'Cơ sở vật chất');
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
  `created_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Evaluation_Resident1_idx` (`Resident_User_id`),
  CONSTRAINT `fk_Evaluation_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `active` varchar(1) NOT NULL,
  `Resident_User_id` int NOT NULL,
  `createdAt` date DEFAULT NULL,
  `relationship_type` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
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
INSERT INTO `family_member` VALUES (6,'Lê Thị Hoa','1',172,'2024-06-15','Chị','1985-03-25'),(7,'Phạm Minh Đức','1',172,'2024-06-15','Anh','2000-11-15'),(8,'Đặng Thị Hằng','1',172,'2024-06-15','Em','2005-07-30'),(9,'Hoàng Thị Mai','1',172,'2024-06-15','Chị','1998-02-10'),(10,'Nguyễn Văn Hùng','1',172,'2024-06-15','Anh','1995-12-05'),(11,'Vũ Thị Lan','1',172,'2024-06-15','Em','2002-09-18'),(12,'Phạm Quốc Tuấn','1',172,'2024-06-15','Chú','1993-06-07'),(13,'Bùi Thị Hương','1',172,'2024-06-15','Cô','1988-01-22');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Quần Áo','Xuống Lấy','2024-01-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg','2024-06-25'),(2,'Đồ Điện Tử','Iphone','2024-01-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg','2024-06-25'),(3,'Đồ Điện Tử','SamSung','2024-02-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg','2024-06-25'),(4,'Đồ Điện Tử','LapTop','2024-02-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg','2024-06-25'),(5,'Đồ Điện Tử','PC','2024-02-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL),(6,'Đồ Điện Tử','Monitor','2024-03-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL),(7,'Đồ Điện Tử','KeyBoard','2024-03-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL),(8,'Đồ Điện Tử','Mouse','2024-03-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL),(9,'Đồ Điện Tử','LapTop','2024-04-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL),(10,'Đồ Điện Tử','SamSung','2024-05-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL),(11,'Đồ Điện Tử','Iphone','2024-06-11',19,'https://res.cloudinary.com/dqpo9h5s2/image/upload/v1719331941/me7tjklwsjtdvq0buscu.jpg',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_in_room`
--

LOCK TABLES `member_in_room` WRITE;
/*!40000 ALTER TABLE `member_in_room` DISABLE KEYS */;
INSERT INTO `member_in_room` VALUES (14,38,171);
INSERT INTO `member_in_room` VALUES (14,38,172);
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
  `title` varchar(45) NOT NULL,
  `started_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('Đã thu','Chưa thu') NOT NULL DEFAULT 'Chưa thu',
  `Contract_id` int NOT NULL,
  `price` decimal(13,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `fk_Receipt_Contract1_idx` (`Contract_id`),
  CONSTRAINT `fk_Receipt_Contract1` FOREIGN KEY (`Contract_id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (8,'Hóa đơn tiền nhà tháng 5','2024-05-28','2024-06-27','Chưa thu',38,1085200000.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt_detail`
--

LOCK TABLES `receipt_detail` WRITE;
/*!40000 ALTER TABLE `receipt_detail` DISABLE KEYS */;
INSERT INTO `receipt_detail` VALUES (9,5,8,1260),(10,6,8,9),(11,7,8,NULL),(12,8,8,5),(13,9,8,NULL);
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
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Report_Resident1_idx` (`Resident_User_id`),
  CONSTRAINT `fk_Report_Resident1` FOREIGN KEY (`Resident_User_id`) REFERENCES `resident` (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `date_of_birth` date NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `ward` varchar(45) DEFAULT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `number_plate` varchar(10) NOT NULL,
  `address` varchar(45) NOT NULL,
  `identity` varchar(20) NOT NULL,
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
INSERT INTO `resident` VALUES (171,'Nguyễn Văn Minh','2000-01-01',1,NULL,'Thành phố Hồ Chí Minh','Quận Gò Vấp','0373506399','a@gmail.com','AB-239485','Nguyễn Kiệm','0012030300'),(172,'Nguyễn Thế Anh','2005-02-01',0,NULL,NULL,NULL,'037305747',NULL,'AC-20392','Đông Thạnh - Hóc Môn','0013090820');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (24,'101',2000000,20,10,4,'Phòng VIP','đã thuê',1),(25,'201',1000000,10,5,3,'Phòng VIP','còn trống',2),(26,'202',1500000,20,10,3,'Phòng VIP','còn trống',2),(27,'301',2000000,10,5,2,'Phòng VIP','còn trống',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (5,'Điện','','3000','Kwh'),(6,'Nước','','8000','M3'),(7,'Rác','','40000','Phòng'),(8,'Xe','','1000000','Chiếc'),(9,'An Ninh','','1000000','Phòng');
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
  `role` varchar(100) NOT NULL DEFAULT 'ROLE_USER',
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (161,'admin1','$2a$10$W5lYcrLSp9d9IUh.Wisd3.21UaTbLF/vvdg0NoMgKklnlBiuDk5dO','ROLE_ADMIN',1),(171,'0012030300','$2a$10$YARxIEU.dXf6Lo17lS/vLeDOXcBZQpIokyK1/Qupv7ndtrtMEETeS','ROLE_USER',1),(172,'0013090820','$2a$10$ahVY4Kxc/Y4/jzWPlUoEle8H8xBcQVKCH1CSZAjBnMLSJTLAlw3Ja','ROLE_USER',1);
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

-- Dump completed on 2024-06-28 15:36:41
