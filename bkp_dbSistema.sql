-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bdsistemahospital
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `agendamento`
--

DROP TABLE IF EXISTS `agendamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agendamento` (
  `codAgendamento` int NOT NULL AUTO_INCREMENT,
  `diaAtendimento` date NOT NULL,
  `observacao` varchar(200) NOT NULL,
  `codMedico` int NOT NULL,
  `codPaciente` int NOT NULL,
  `modalidade` varchar(20) NOT NULL,
  PRIMARY KEY (`codAgendamento`),
  KEY `fk_listaEspera_Medico` (`codMedico`),
  KEY `fk_listaEspera_paciente` (`codPaciente`),
  CONSTRAINT `fk_listaEspera_Medico` FOREIGN KEY (`codMedico`) REFERENCES `medico` (`codMedico`),
  CONSTRAINT `fk_listaEspera_paciente` FOREIGN KEY (`codPaciente`) REFERENCES `paciente` (`codPaciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agendamento`
--

LOCK TABLES `agendamento` WRITE;
/*!40000 ALTER TABLE `agendamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `agendamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bairro` (
  `codBairro` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `codCidade` int NOT NULL,
  PRIMARY KEY (`codBairro`),
  KEY `fk_bairro_cidade` (`codCidade`),
  CONSTRAINT `fk_bairro_cidade` FOREIGN KEY (`codCidade`) REFERENCES `cidade` (`codCidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cidade` (
  `codCidade` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `codEstado` int NOT NULL,
  PRIMARY KEY (`codCidade`),
  KEY `fk_estado_cidade` (`codEstado`),
  CONSTRAINT `fk_estado_cidade` FOREIGN KEY (`codEstado`) REFERENCES `estado` (`codEstado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `codEndereco` int NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(50) NOT NULL,
  `numero` int NOT NULL,
  `complemento` varchar(30) DEFAULT NULL,
  `codBairro` int NOT NULL,
  PRIMARY KEY (`codEndereco`),
  KEY `fk_endereco_bairro` (`codBairro`),
  CONSTRAINT `fk_endereco_bairro` FOREIGN KEY (`codBairro`) REFERENCES `bairro` (`codBairro`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especialidade`
--

DROP TABLE IF EXISTS `especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especialidade` (
  `codEspecialidade` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`codEspecialidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especialidade`
--

LOCK TABLES `especialidade` WRITE;
/*!40000 ALTER TABLE `especialidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `especialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `codEstado` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`codEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Acre'),(2,'Alagoas'),(3,'Amazonas'),(4,'Amapá'),(5,'Bahia'),(6,'Ceará'),(7,'Distrito Federal'),(8,'Espírito Santo'),(9,'Goiás'),(10,'Maranhão'),(11,'Minas Gerais'),(12,'Mato Grosso do Sul'),(13,'Mato Grosso'),(14,'Pará'),(15,'Paraíba'),(16,'Pernambuco'),(17,'Piauí'),(18,'Paraná'),(19,'Rio de Janeiro'),(20,'Rio Grande do Norte'),(21,'Rondônia'),(22,'Roraima'),(23,'Rio Grande do Sul'),(24,'Santa Catarina'),(25,'Sergipe'),(26,'São Paulo'),(27,'Tocantins');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listaespera`
--

DROP TABLE IF EXISTS `listaespera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listaespera` (
  `codFila` int NOT NULL AUTO_INCREMENT,
  `horarioAtendimento` time NOT NULL,
  `codAgendamento` int NOT NULL,
  PRIMARY KEY (`codFila`),
  KEY `fk_listaEspera_Agendamento` (`codAgendamento`),
  CONSTRAINT `fk_listaEspera_Agendamento` FOREIGN KEY (`codAgendamento`) REFERENCES `agendamento` (`codAgendamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listaespera`
--

LOCK TABLES `listaespera` WRITE;
/*!40000 ALTER TABLE `listaespera` DISABLE KEYS */;
/*!40000 ALTER TABLE `listaespera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico`
--

DROP TABLE IF EXISTS `medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medico` (
  `codMedico` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `CRM` int NOT NULL,
  `codEspecialidade` int NOT NULL,
  PRIMARY KEY (`codMedico`),
  KEY `fk_medico_especialidade` (`codEspecialidade`),
  CONSTRAINT `fk_medico_especialidade` FOREIGN KEY (`codEspecialidade`) REFERENCES `especialidade` (`codEspecialidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico`
--

LOCK TABLES `medico` WRITE;
/*!40000 ALTER TABLE `medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `codPaciente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `CPF` varchar(11) NOT NULL,
  `contato` varchar(11) NOT NULL,
  `codEndereco` int NOT NULL,
  `email` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`codPaciente`),
  KEY `fk_paciente_endereco` (`codEndereco`),
  CONSTRAINT `fk_paciente_endereco` FOREIGN KEY (`codEndereco`) REFERENCES `endereco` (`codEndereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-24 10:51:19
