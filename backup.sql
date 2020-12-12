-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prueba
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alerta`
--

DROP TABLE IF EXISTS `alerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ubicacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerta`
--

LOCK TABLES `alerta` WRITE;
/*!40000 ALTER TABLE `alerta` DISABLE KEYS */;
/*!40000 ALTER TABLE `alerta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistenciaevento`
--

DROP TABLE IF EXISTS `asistenciaevento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistenciaevento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `evento_id` int(11) DEFAULT NULL,
  `Id_calendario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  /*KEY `FK_k9e48673ix245yb5lbakmuo4q` (`evento_id`),
  KEY `FK_4a3okiefi4rmcqdwf5oub75hn` (`Id_calendario`),*/
 CONSTRAINT `FK_4a3okiefi4rmcqdwf5oub75hn` FOREIGN KEY (`Id_calendario`) REFERENCES `calendarios` (`id`),
  CONSTRAINT `FK_k9e48673ix245yb5lbakmuo4q` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistenciaevento`
--

LOCK TABLES `asistenciaevento` WRITE;
/*!40000 ALTER TABLE `asistenciaevento` DISABLE KEYS */;
/*!40000 ALTER TABLE `asistenciaevento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atuendos`
--

DROP TABLE IF EXISTS `atuendos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atuendos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calzado_id` int(11) DEFAULT NULL,
  `guardarropaOrigen_id` int(11) DEFAULT NULL,
  `inferior_id` int(11) DEFAULT NULL,
  `superior_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9d9s0xplid83y5h6g2k6cg69v` (`calzado_id`),
  KEY `FK_i7lgbmiht6ihi7usablrrv2jg` (`guardarropaOrigen_id`),
  KEY `FK_lc16un2q96aedob9i9awoqnq1` (`inferior_id`),
  KEY `FK_mcvlw8rgykduswqrgapvxgbms` (`superior_id`),
  CONSTRAINT `FK_9d9s0xplid83y5h6g2k6cg69v` FOREIGN KEY (`calzado_id`) REFERENCES `prendas` (`id`),
  CONSTRAINT `FK_i7lgbmiht6ihi7usablrrv2jg` FOREIGN KEY (`guardarropaOrigen_id`) REFERENCES `guardarropas` (`id`),
  CONSTRAINT `FK_lc16un2q96aedob9i9awoqnq1` FOREIGN KEY (`inferior_id`) REFERENCES `prendas` (`id`),
  CONSTRAINT `FK_mcvlw8rgykduswqrgapvxgbms` FOREIGN KEY (`superior_id`) REFERENCES `prendas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atuendos`
--

LOCK TABLES `atuendos` WRITE;
/*!40000 ALTER TABLE `atuendos` DISABLE KEYS */;
/*!40000 ALTER TABLE `atuendos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `atuendos_prendas`
--

DROP TABLE IF EXISTS `atuendos_prendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atuendos_prendas` (
  `Atuendos_id` int(11) NOT NULL,
  `capasAbrigos_id` int(11) NOT NULL,
  `accesorios_id` int(11) NOT NULL,
  KEY `FK_cj8fhhw9uc88s378g6fmwnegu` (`capasAbrigos_id`),
  KEY `FK_6up2sj23nli5nhsltsbh6fl3r` (`Atuendos_id`),
  KEY `FK_ig421k1byymkpuxhmgmx15ns9` (`accesorios_id`),
  CONSTRAINT `FK_6up2sj23nli5nhsltsbh6fl3r` FOREIGN KEY (`Atuendos_id`) REFERENCES `atuendos` (`id`),
  CONSTRAINT `FK_cj8fhhw9uc88s378g6fmwnegu` FOREIGN KEY (`capasAbrigos_id`) REFERENCES `prendas` (`id`),
  CONSTRAINT `FK_ig421k1byymkpuxhmgmx15ns9` FOREIGN KEY (`accesorios_id`) REFERENCES `prendas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atuendos_prendas`
--

LOCK TABLES `atuendos_prendas` WRITE;
/*!40000 ALTER TABLE `atuendos_prendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `atuendos_prendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendarios`
--

DROP TABLE IF EXISTS `calendarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendarios`
--

LOCK TABLES `calendarios` WRITE;
/*!40000 ALTER TABLE `calendarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colorrgb`
--

DROP TABLE IF EXISTS `colorrgb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colorrgb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `azul` int(11) NOT NULL,
  `rojo` int(11) NOT NULL,
  `verde` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colorrgb`
--

LOCK TABLES `colorrgb` WRITE;
/*!40000 ALTER TABLE `colorrgb` DISABLE KEYS */;
/*!40000 ALTER TABLE `colorrgb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` tinyblob,
  `fechaFormateada` varchar(255) DEFAULT NULL,
  `tituloEvento` varchar(255) DEFAULT NULL,
  `ubicacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardarropas`
--

DROP TABLE IF EXISTS `guardarropas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guardarropas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardarropas`
--

LOCK TABLES `guardarropas` WRITE;
/*!40000 ALTER TABLE `guardarropas` DISABLE KEYS */;
/*!40000 ALTER TABLE `guardarropas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferenciasdeabrigo`
--

DROP TABLE IF EXISTS `preferenciasdeabrigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferenciasdeabrigo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NIVEL_CABEZA` double DEFAULT NULL,
  `PUNTAJE_CABEZA` int(11) DEFAULT NULL,
  `NIVEL_CUELLO` double DEFAULT NULL,
  `PUNTAJE_CUELLO` int(11) DEFAULT NULL,
  `NIVEL_MANOS` double DEFAULT NULL,
  `PUNTAJE_MANOS` int(11) DEFAULT NULL,
  `NIVEL_PECHO` double DEFAULT NULL,
  `PUNTAJE_PECHO` int(11) DEFAULT NULL,
  `NIVEL_PIERNAS` double DEFAULT NULL,
  `PUNTAJE_PIERNAS` int(11) DEFAULT NULL,
  `NIVEL_PIES` double DEFAULT NULL,
  `PUNTAJE_PIES` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferenciasdeabrigo`
--

LOCK TABLES `preferenciasdeabrigo` WRITE;
/*!40000 ALTER TABLE `preferenciasdeabrigo` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferenciasdeabrigo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prendas`
--

DROP TABLE IF EXISTS `prendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material` varchar(255) DEFAULT NULL,
  `pateAbrigada` varchar(255) DEFAULT NULL,
  `rutaImagen` varchar(255) DEFAULT NULL,
  `trama` varchar(255) DEFAULT NULL,
  `colorPrimario_id` int(11) DEFAULT NULL,
  `colorSecundario_id` int(11) DEFAULT NULL,
  `tipo_id` int(11) DEFAULT NULL,
  `id_Guardarropa_usadas` int(11) DEFAULT NULL,
  `id_Guardarropa_disponibles` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_75aovfoa4yffujh8mdws222fc` (`colorPrimario_id`),
  KEY `FK_dr8a9k5uetfygn5w2vdbau5ef` (`colorSecundario_id`),
  KEY `FK_hamyu3x93g8hl3n03ethpvicd` (`tipo_id`),
  KEY `FK_amwly5b0aee317a06day2kk49` (`id_Guardarropa_usadas`),
  KEY `FK_m6acnib4un92cfyuvxumk5k2s` (`id_Guardarropa_disponibles`),
  CONSTRAINT `FK_75aovfoa4yffujh8mdws222fc` FOREIGN KEY (`colorPrimario_id`) REFERENCES `colorrgb` (`id`),
  CONSTRAINT `FK_amwly5b0aee317a06day2kk49` FOREIGN KEY (`id_Guardarropa_usadas`) REFERENCES `guardarropas` (`id`),
  CONSTRAINT `FK_dr8a9k5uetfygn5w2vdbau5ef` FOREIGN KEY (`colorSecundario_id`) REFERENCES `colorrgb` (`id`),
  CONSTRAINT `FK_hamyu3x93g8hl3n03ethpvicd` FOREIGN KEY (`tipo_id`) REFERENCES `tipoprenda` (`id`),
  CONSTRAINT `FK_m6acnib4un92cfyuvxumk5k2s` FOREIGN KEY (`id_Guardarropa_disponibles`) REFERENCES `guardarropas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prendas`
--

LOCK TABLES `prendas` WRITE;
/*!40000 ALTER TABLE `prendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `prendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sugerencias`
--

DROP TABLE IF EXISTS `sugerencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sugerencias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `margen` int(11) NOT NULL,
  `Id_AsistenciaEvento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k5n7fglalc4ueb0n5ikgl8cc3` (`Id_AsistenciaEvento`),
  CONSTRAINT `FK_k5n7fglalc4ueb0n5ikgl8cc3` FOREIGN KEY (`Id_AsistenciaEvento`) REFERENCES `asistenciaevento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerencias`
--

LOCK TABLES `sugerencias` WRITE;
/*!40000 ALTER TABLE `sugerencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `sugerencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sugerenciasposibles`
--

DROP TABLE IF EXISTS `sugerenciasposibles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sugerenciasposibles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) DEFAULT NULL,
  `atuendo_id` int(11) DEFAULT NULL,
  `id_sugerencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5ykqprycv6t94wg8vrsh3q80r` (`atuendo_id`),
  KEY `FK_8llv5q41lwr0epls2h9ik2jdh` (`id_sugerencia`),
  CONSTRAINT `FK_5ykqprycv6t94wg8vrsh3q80r` FOREIGN KEY (`atuendo_id`) REFERENCES `atuendos` (`id`),
  CONSTRAINT `FK_8llv5q41lwr0epls2h9ik2jdh` FOREIGN KEY (`id_sugerencia`) REFERENCES `sugerencias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sugerenciasposibles`
--

LOCK TABLES `sugerenciasposibles` WRITE;
/*!40000 ALTER TABLE `sugerenciasposibles` DISABLE KEYS */;
/*!40000 ALTER TABLE `sugerenciasposibles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoprenda`
--

DROP TABLE IF EXISTS `tipoprenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoprenda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `TipoBasico` varchar(255) DEFAULT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `nivelAbrigo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoprenda`
--

LOCK TABLES `tipoprenda` WRITE;
/*!40000 ALTER TABLE `tipoprenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoprenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoprenda_materialespermitidos`
--

DROP TABLE IF EXISTS `tipoprenda_materialespermitidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoprenda_materialespermitidos` (
  `TipoPrenda_id` int(11) NOT NULL,
  `materialesPermitidos` varchar(255) DEFAULT NULL,
  KEY `FK_4grxyh3joam04ripn0l44ndh6` (`TipoPrenda_id`),
  CONSTRAINT `FK_4grxyh3joam04ripn0l44ndh6` FOREIGN KEY (`TipoPrenda_id`) REFERENCES `tipoprenda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoprenda_materialespermitidos`
--

LOCK TABLES `tipoprenda_materialespermitidos` WRITE;
/*!40000 ALTER TABLE `tipoprenda_materialespermitidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoprenda_materialespermitidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoprenda_tipoprenda`
--

DROP TABLE IF EXISTS `tipoprenda_tipoprenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoprenda_tipoprenda` (
  `TipoPrenda_id` int(11) NOT NULL,
  `tiposAceptados_id` int(11) NOT NULL,
  KEY `FK_j7fiml7igm0eis4s1scu4eqtg` (`tiposAceptados_id`),
  KEY `FK_go26ndxald5h074ti3iu7dn4v` (`TipoPrenda_id`),
  CONSTRAINT `FK_go26ndxald5h074ti3iu7dn4v` FOREIGN KEY (`TipoPrenda_id`) REFERENCES `tipoprenda` (`id`),
  CONSTRAINT `FK_j7fiml7igm0eis4s1scu4eqtg` FOREIGN KEY (`tiposAceptados_id`) REFERENCES `tipoprenda` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoprenda_tipoprenda`
--

LOCK TABLES `tipoprenda_tipoprenda` WRITE;
/*!40000 ALTER TABLE `tipoprenda_tipoprenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoprenda_tipoprenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposubscripcion`
--

DROP TABLE IF EXISTS `tiposubscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tiposubscripcion` (
  `tipo_Suscripcion` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidadMaxima` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposubscripcion`
--

LOCK TABLES `tiposubscripcion` WRITE;
/*!40000 ALTER TABLE `tiposubscripcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiposubscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usos`
--

DROP TABLE IF EXISTS `usos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(255) DEFAULT NULL,
  `fechaDeUso` tinyblob,
  `fechaFormateada` varchar(255) DEFAULT NULL,
  `puntuado` bit(1) NOT NULL,
  `temperaturaDeUso` double DEFAULT NULL,
  `atuendo_id` int(11) DEFAULT NULL,
  `Id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_giuidbqu4h2iylvlm228ukbi` (`atuendo_id`),
  KEY `FK_oa92fer00tc33jkjqtyjpjadp` (`Id_usuario`),
  CONSTRAINT `FK_giuidbqu4h2iylvlm228ukbi` FOREIGN KEY (`atuendo_id`) REFERENCES `atuendos` (`id`),
  CONSTRAINT `FK_oa92fer00tc33jkjqtyjpjadp` FOREIGN KEY (`Id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usos`
--

LOCK TABLES `usos` WRITE;
/*!40000 ALTER TABLE `usos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_informantes`
--

DROP TABLE IF EXISTS `usuario_informantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_informantes` (
  `Usuario_id` int(11) NOT NULL,
  `informantes` varchar(255) DEFAULT NULL,
  KEY `FK_ot339dqw70hfsg03bnw90gvd5` (`Usuario_id`),
  CONSTRAINT `FK_ot339dqw70hfsg03bnw90gvd5` FOREIGN KEY (`Usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_informantes`
--

LOCK TABLES `usuario_informantes` WRITE;
/*!40000 ALTER TABLE `usuario_informantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_informantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `calendarioEventos_id` int(11) DEFAULT NULL,
  `preferenciasDeAbrigo_id` int(11) DEFAULT NULL,
  `subscripcion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i1iqcewxitmhh12if4hurdta3` (`calendarioEventos_id`),
  KEY `FK_rnsb1um116dbb3rit4w4x5e71` (`preferenciasDeAbrigo_id`),
  KEY `FK_fqanxhf5g55g2mv6hrne1yaf3` (`subscripcion_id`),
  CONSTRAINT `FK_fqanxhf5g55g2mv6hrne1yaf3` FOREIGN KEY (`subscripcion_id`) REFERENCES `tiposubscripcion` (`id`),
  CONSTRAINT `FK_i1iqcewxitmhh12if4hurdta3` FOREIGN KEY (`calendarioEventos_id`) REFERENCES `calendarios` (`id`),
  CONSTRAINT `FK_rnsb1um116dbb3rit4w4x5e71` FOREIGN KEY (`preferenciasDeAbrigo_id`) REFERENCES `preferenciasdeabrigo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_guardarropas`
--

DROP TABLE IF EXISTS `usuarios_guardarropas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_guardarropas` (
  `Usuarios_id` int(11) NOT NULL,
  `guardarropas_id` int(11) NOT NULL,
  PRIMARY KEY (`Usuarios_id`,`guardarropas_id`),
  KEY `FK_tkbrfu90vy45ol3n4a94hwdhs` (`guardarropas_id`),
  CONSTRAINT `FK_6bu50dfm1dqaos9laj773l629` FOREIGN KEY (`Usuarios_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FK_tkbrfu90vy45ol3n4a94hwdhs` FOREIGN KEY (`guardarropas_id`) REFERENCES `guardarropas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_guardarropas`
--

LOCK TABLES `usuarios_guardarropas` WRITE;
/*!40000 ALTER TABLE `usuarios_guardarropas` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios_guardarropas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-27 15:18:21
