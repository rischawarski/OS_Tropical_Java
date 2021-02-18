-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tropicalt
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_c` int(11) NOT NULL AUTO_INCREMENT,
  `nome_c` varchar(255) NOT NULL,
  `telefone_c` varchar(50) DEFAULT NULL,
  `obs_c` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_c`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `costureira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `costureira` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_c` varchar(100) NOT NULL,
  `cidade_c` varchar(100) NOT NULL,
  `loja_c` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `nome_f` varchar(100) NOT NULL,
  `login_f` varchar(100) NOT NULL,
  `senha_f` varchar(100) NOT NULL,
  `cidade_f` varchar(100) NOT NULL,
  `loja_f` varchar(100) NOT NULL,
  `perfil` varchar(50) NOT NULL,
  `id_F` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material` varchar(150) NOT NULL,
  `corte` varchar(100) DEFAULT NULL,
  `bordado_pintura` varchar(100) DEFAULT NULL,
  `costura` varchar(100) DEFAULT NULL,
  `costureira` varchar(200) DEFAULT NULL,
  `obs_material` varchar(255) DEFAULT NULL,
  `funcionario_p` varchar(100) DEFAULT NULL,
  `loja_p` varchar(100) DEFAULT NULL,
  `cidade_p` varchar(100) DEFAULT NULL,
  `data_p` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `finalizado` varchar(100) DEFAULT NULL,
  `id_c` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_c` (`id_c`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

