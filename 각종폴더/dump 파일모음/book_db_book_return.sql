-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: book_db
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `book_return`
--

DROP TABLE IF EXISTS `book_return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_return` (
  `return_log` int NOT NULL AUTO_INCREMENT COMMENT '고유식별키',
  `id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '유저 아이디',
  `book_cd` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '도서 번호',
  `book_name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '도서 제목',
  `borrow_date` datetime NOT NULL COMMENT '대여 날짜',
  `return_date` datetime NOT NULL COMMENT '반납 날짜',
  PRIMARY KEY (`return_log`),
  KEY `id` (`id`),
  KEY `book_cd` (`book_cd`),
  CONSTRAINT `book_return_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `book_return_ibfk_2` FOREIGN KEY (`book_cd`) REFERENCES `book` (`book_cd`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='도서 반납';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_return`
--

LOCK TABLES `book_return` WRITE;
/*!40000 ALTER TABLE `book_return` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_return` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-02 16:56:10
