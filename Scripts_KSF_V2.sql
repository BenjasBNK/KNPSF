-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: kanepe
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `carrinho`
--

DROP TABLE IF EXISTS `carrinho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrinho` (
  `idCarrinho` bigint NOT NULL AUTO_INCREMENT,
  `Usuarios_idUsuarios` bigint NOT NULL,
  `Produtores_idProdutores` bigint DEFAULT NULL,
  PRIMARY KEY (`idCarrinho`),
  KEY `Usuarios_idUsuarios` (`Usuarios_idUsuarios`),
  KEY `Produtores_idProdutores` (`Produtores_idProdutores`),
  CONSTRAINT `carrinho_ibfk_1` FOREIGN KEY (`Usuarios_idUsuarios`) REFERENCES `usuarios` (`idUsuarios`),
  CONSTRAINT `carrinho_ibfk_2` FOREIGN KEY (`Produtores_idProdutores`) REFERENCES `produtores` (`idProdutores`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrinho`
--

LOCK TABLES `carrinho` WRITE;
/*!40000 ALTER TABLE `carrinho` DISABLE KEYS */;
INSERT INTO `carrinho` VALUES (1,1,1);
/*!40000 ALTER TABLE `carrinho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idClientes` bigint NOT NULL AUTO_INCREMENT,
  `Usuarios_idUsuarios` bigint NOT NULL,
  `Enderecos_idEnderecos` bigint NOT NULL,
  PRIMARY KEY (`idClientes`),
  KEY `Usuarios_idUsuarios` (`Usuarios_idUsuarios`),
  KEY `Enderecos_idEnderecos` (`Enderecos_idEnderecos`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`Usuarios_idUsuarios`) REFERENCES `usuarios` (`idUsuarios`),
  CONSTRAINT `clientes_ibfk_2` FOREIGN KEY (`Enderecos_idEnderecos`) REFERENCES `enderecos` (`idEnderecos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compraprodutos`
--

DROP TABLE IF EXISTS `compraprodutos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compraprodutos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `idProdutos` bigint NOT NULL,
  `quantidade` int NOT NULL,
  `validade` date NOT NULL,
  `preco` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idProdutos` (`idProdutos`),
  CONSTRAINT `compraprodutos_ibfk_1` FOREIGN KEY (`idProdutos`) REFERENCES `produtos` (`idProdutos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compraprodutos`
--

LOCK TABLES `compraprodutos` WRITE;
/*!40000 ALTER TABLE `compraprodutos` DISABLE KEYS */;
/*!40000 ALTER TABLE `compraprodutos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enderecos`
--

DROP TABLE IF EXISTS `enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enderecos` (
  `idEnderecos` bigint NOT NULL AUTO_INCREMENT,
  `Cidade` varchar(100) NOT NULL,
  `Rua` varchar(100) NOT NULL,
  `Bairro` varchar(100) NOT NULL,
  `cep` varchar(8) NOT NULL,
  `numero` varchar(5) NOT NULL,
  PRIMARY KEY (`idEnderecos`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos`
--

LOCK TABLES `enderecos` WRITE;
/*!40000 ALTER TABLE `enderecos` DISABLE KEYS */;
INSERT INTO `enderecos` VALUES (1,'Gaspar','Josefina Schmitt','Belchior Central','89117625','83');
/*!40000 ALTER TABLE `enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itenscarrinho`
--

DROP TABLE IF EXISTS `itenscarrinho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itenscarrinho` (
  `idItensCarrinho` bigint NOT NULL AUTO_INCREMENT,
  `Carrinho_idCarrinho` bigint NOT NULL,
  `Produtos_idProdutos` bigint NOT NULL,
  `quantidade` bigint NOT NULL,
  `preco` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idItensCarrinho`),
  KEY `Produtos_idProdutos` (`Produtos_idProdutos`),
  KEY `Carrinho_idCarrinho` (`Carrinho_idCarrinho`),
  CONSTRAINT `itenscarrinho_ibfk_1` FOREIGN KEY (`Produtos_idProdutos`) REFERENCES `produtos` (`idProdutos`),
  CONSTRAINT `itenscarrinho_ibfk_2` FOREIGN KEY (`Carrinho_idCarrinho`) REFERENCES `carrinho` (`idCarrinho`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itenscarrinho`
--

LOCK TABLES `itenscarrinho` WRITE;
/*!40000 ALTER TABLE `itenscarrinho` DISABLE KEYS */;
INSERT INTO `itenscarrinho` VALUES (1,1,1,12,21.92),(2,1,2,12,12.98);
/*!40000 ALTER TABLE `itenscarrinho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `idPagamento` bigint NOT NULL,
  `idVenda` bigint NOT NULL,
  `valorTotal` float NOT NULL,
  PRIMARY KEY (`idPagamento`),
  KEY `fk_vendas_pagamento_idx` (`idVenda`),
  CONSTRAINT `fk_vendas_pagamento` FOREIGN KEY (`idVenda`) REFERENCES `vendas` (`idVendas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtores`
--

DROP TABLE IF EXISTS `produtores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtores` (
  `idProdutores` bigint NOT NULL AUTO_INCREMENT,
  `nomeNegocio` varchar(100) NOT NULL,
  `Usuarios_idUsuarios` bigint NOT NULL,
  `Enderecos_idEnderecos` bigint NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  `chavePix` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idProdutores`),
  UNIQUE KEY `cnpj` (`cnpj`),
  UNIQUE KEY `chavePix` (`chavePix`),
  KEY `Usuarios_idUsuarios` (`Usuarios_idUsuarios`),
  KEY `Enderecos_idEnderecos` (`Enderecos_idEnderecos`),
  CONSTRAINT `produtores_ibfk_1` FOREIGN KEY (`Usuarios_idUsuarios`) REFERENCES `usuarios` (`idUsuarios`),
  CONSTRAINT `produtores_ibfk_2` FOREIGN KEY (`Enderecos_idEnderecos`) REFERENCES `enderecos` (`idEnderecos`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtores`
--

LOCK TABLES `produtores` WRITE;
/*!40000 ALTER TABLE `produtores` DISABLE KEYS */;
INSERT INTO `produtores` VALUES (1,'vini',1,1,'12345678910111',NULL);
/*!40000 ALTER TABLE `produtores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
  `idProdutos` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` int NOT NULL,
  `validade` date NOT NULL,
  `nome_Produto` varchar(100) NOT NULL,
  `preco` double NOT NULL,
  `Produtores_idProdutores` bigint NOT NULL,
  `salinidade` tinyint(1) NOT NULL,
  `foto` longblob,
  PRIMARY KEY (`idProdutos`),
  KEY `Produtores_idProdutores` (`Produtores_idProdutores`),
  CONSTRAINT `produtos_ibfk_1` FOREIGN KEY (`Produtores_idProdutores`) REFERENCES `produtores` (`idProdutores`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,12,'2026-03-30','peixe',30.889999389648438,1,1,_binary 'ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342ÿ\Û\0C			\r\r2!!22222222222222222222222222222222222222222222222222ÿÀ\0\0”\0\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0\0}\0!1AQa\"q2‘¡#B±ÁR\Ñğ$3br‚	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\á\â\ã\ä\å\æ\ç\è\é\êñòóôõö÷øùúÿ\Ä\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0w\0!1AQaq\"2B‘¡±Á	#3Rğbr\Ñ\n$4\á%ñ\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz‚ƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\â\ã\ä\å\æ\ç\è\é\êòóôõö÷øùúÿ\Ú\0\0\0?\0Óº¸ò\Î=k›\Ö45¿¸®`Œµ$‘\ápw(\Ş:˜§\Ê\Î:\ÓNŸOFÇµ]I\Ûv\Ù^´k’\Í\ÊVE¤·\È2¬NkZ\Z#¡;4VÀ€@©&Ñ¢¸ˆ£4\í6^6\Ê1\ïZ\ÈÉ»ƒ\ÅF’\ìpw\Ú+\ÛJvş©t¨¦!ƒ\Åv\Ök?*+1•a?\"\ãğ©QJ^ğùŒi	Š‘PH>lcšÔ–##\î\Å@bO3‘\Íg\Ö\ÈeI¬_­W6p\ÅÓ¯µjKyy\İÒ¨\çkõ¥’ª\Z’=+N+\Å1a”Ub˜9\0Š­Àf•jN\"+jh	®KTQ+’¢µoV_\â<\Õ¶™²J\äV2wac\0©‘‡+RKb+Šc\Ú½)\\V2€\æœMNñ\ëùTD\nb>‚<ŒT’\"\ÇN*„—FzU95W‘Oq]ÎªKsHu+6¹n«z}”($û\Õu\Ôó\ÄN?J\Óg^õ›’ø‘¤n%\Äv\á†3N¶ˆ`óš¥sJA¥f«¯\'&”l\ÕÁ²h\Â`‡5^{x‰È¦\Ü\ä\Çò’*]¤jwŠZ DÃ™0µ“ªD`pW¯z\é$‰a]\Ä\Ö>¢EÂ€¸5T†™\Î\Ë*½©V\æ#MGc8$…\àV)i\Ö]‡¥s9>¦†À¿\Ã|¢6ª|£µ/4Ê£½m\\\é¶[±óbŸ3`s_4\×\0\ã5»*ğ«ÛµgZZ+K‚¹ ÷®\Â(±´¯µTe`9›˜“\Ì\Æ* ƒ \àWO©X\Ä2TV\"ü…†*˜¿Œ¬•œs]ü\É8¬9T+b„\Åcİµ\ÇsY\0±š³w;ùœY\×LşY#ƒZ\ËRb†[\Ù,·A½\ë¤XYc\ÍiS\Ê×ª\â»K*¯\0õpŠ\äm“+\Ü\æ5	®„˜Pqš³k~aŒ,Çù«W¼°kQ±f™Óµe\Íg¡I]¹Ybæš³ˆ95\Í\ÚMs\ã€=*\è/>A\\\àÕ\ë“sj\ŞVzV-“N$;—5¯c¯\äõ­´†»¥&\ÄdIûÁ†AƒY\×\Zl\î\Ğ_C\çGûºÄš\ÊV@\"¦\ë\íŒ’¿dºk ²Œ\İÇ†¾Ÿq\æO˜õz\Òğ$›UzUF\Ö+6•,YWô¢+p%\È\â·\ZH¥Q\Ø\Ö©1¶\â6›ƒLI\Üu\í¿\î\É\×)q\ÙI÷®›N¹óÁA¬\İ^İ£b\Ø\ë\éSS\Èf\Ìe\á\ák–¹…„\Ç+¸‰L\ÑV±olKK¦¢3\ÔXš8€ù‡5Nk=N\Ê\Ñh\ÒcÖ¤U€>µ\Ú\ãcŸš\Ç6šL¶Rùùk[N\Õ\Ò\ç÷L>aZ3 ’Œ\Ö}¦“,]H\ä\Ô\Å;y¥}Å¸A!\İY³E\æK´·­r<¸+ò\êy®\î_Ë†1“\ÆIöõ’\ÙjF²\Å\Â[Â¥ˆ\Å\Ã\âOS®‘£©‰N<Û—;G\×ı\riÁ«k\ÌM\Úi\Ï\ê°	ü\É?Ê”±i¾YI&mO\rZ¢\æŒ]§\ìLŒMfx‹Z“Ja·ó\î\'\Î\Ğr@\ÆI#\ê8®‡\ÃxñMœ³\ÚÍ³\ÈsĞ¿U=z0G\"²ş!hóiÖºT±€\ß\é,®ø<§ş´\áû·8½•\Ìi¤\êªr\ïc˜ş\Ğ\Õ\ÈÌ—Àdr¢5 {ûŠĞ·\ÔÄ€$\ÑbL`°<ô÷¬\Ø\áœ\ã,\é\ëS\Åd¤\î\ÜW€±•¯\'s\è]JQ²Ve\ß?<\n|’%¼Rj/³®7$¸>ı+=ôıkV71\éö’İ¬\0|•,TœzûkÓ£ˆ…m \Ï&¾­f´(]x•–|#\n‚mTİ¯\Ì\Ù5F}&rO\ÈC Œ~u•)kf\ç#»©}c\Ñü3`\Ò\âSœV¾§¦3/\0b¹¿\nøª\Ù-\ÄR7J\î\í\î\"¼ƒ\ÍSE7¢%+3“¶\Ó\ÉùB`z\Ó\ï´ûh\Õ€f­\Ş\ê\Ëgt\"U\ÍQ\Õ.R\ë\Ëd\Ísºv\Õ\rjt^IL\æœrYhv$Ô¨8¯F¤ï±‚W$–-¹\Úy\ÅP´‚\â<‡\ÏZ¹5ôvv\ÒO<‰1‚\Î\ìx¸ó\ã\Ë\İ^ô\ÛxsNI\ã}\Å\ß\n®\Ğ~½O\áY»FŸ<¥e\æiJråŠ»:‹\ĞUzW%\â\ë»ğ\Õ\ÒB\á‰zv&´Mş¯l‚[\ß\"÷hÌ‘¢y÷\Éş¹®¿\Ä6:M·„\'¿³\ŞfeO)²>b1\ÇN†°¡Zx¹R•\Ò\ÜŞ®\Z­	F5#k~“Ekeª…D\0UFVšL½]ŠËÇ’y4\ï$/^k\åy\â¤\ÚÕŸc\nrqWF·\Ãû\ï\ì\Ï\Ëj	0¾™#ô-ù\n\ïüC`š\æqa3\ìó@(\ãø«~\nò4œ\Ø\êš}\âp\Ö÷Q¶\Ù\Îô&½7T\ÕSN\Ón¯\'\ËGo9\Çp}~O/o‡´ºh|–oC\Øâ¹–—Wù`Ÿhˆ¼7l&)\"œ\é\íR,\Şõ\ÍXGy\â\İR}gU¸eRv\í‡\ä\É\àc°üë®ƒO¶H|˜\Ú@{r\ßÎ¾s0…\nU`\Ùôù{¯^Š©8\Ùz\îDÿ\0h™A¾\æV	\Zÿ\0´N\êk\Üt=6\ÓÃš4v6ûU\"]\ÒHx\Ş\İY¹\ë^/¡mƒ\Æ:_\Ã\Éû@7÷°Bş¸¯@øŒ\×/\á£‰¿vòÆ“`œ\ì\'¼\Ä×§•B1¡*ŸÖˆù\ìòs«Š§‡Z\'ù·c‰ño´][Z\Ú\ÒAh\Å„9i÷™»g°üı°<A\áè§´«\r\ÊÃ¡¨\àğ†£}\Ó\ë\È­zdKf¶¥?v(ƒ\åb³xS­®\Ó\ßm=ùeœ´œZJ\Û>ş§\éúMÜº‡•l@=k\Ö46k+£\nÙC¶±$¢\r\İ\Î+R›\Ê\ãn>•ôI5«<\Ü\Ç\ÕV)\'is\\\ì\Ú\ÜV\ìU\ãš\×ky\ï\æ0\ÃøŸJ©?ˆ}\ÓLA=©Â”ä®‘I‡·4˜â¥½Cn\Ü\Õ¸WlU\Ê2‹w1G3ñ;™¼,¦%&4Z\\wŸÌŠ­\à1öb(f27™·¨\ä\ã?‡ó®\Ù\à†\æ‚x–Hœa•\ÆA\Ã\Şø3R\Ño´<0\å#u›62>¤ò=q\ã0\Ó\Å\Ğöqvg~_Š†\Z²œ\Õ\Ñ\Ö\íV\"¥¿rş´Á·Bs\è\Ê3úVU£k7?H{I1ó<²¡A\ëŒO\å[QÆ²hGG*Ü¦‡\ïn\Îr=9\äW.K„¯‡hTV\æV^ºÿ\0™\é\æx\Ü=IS•7{Àÿ\0# ùGZG·ö«v\×óÇ˜y„¡»ğH?Ê¥1†\èk\å\ê9S›‹\è}:‘œ–\Ì\æõHB\Û\î?w:õ±\Ôt[\Ë0\Û~\Ó&}2+™\ÕmÁ³mÃ¸¨®\ÆıZ)¥\ÌQY€ğ+\í¸ZjTj_¹òœ@›«»5\áB\Û\Ëd\Ì\Ğ\Èr¹\í\ê=³š\ë–7	‘úV“£\Ë•¥Á·+0@D›y\äV\äMs»À\ÃóX\é\Âu\\©½\çOIlg]+Ì¸ƒƒ•a\ÔÆ½J\ŞşxL¬\à«\\Daœ‚88úG\àk‚À<^l\åV%3±À\ëY\í\ã-+H™¿²÷\Ş\Êx}­¶~¸9úø\×vOq¨\éò9E\ïnŸ¡\åg4)ÖŒd\å\Ë(\ìkiVOòE8ıô,cLş¶\ã]E¸Úi#¹¯&¿ñUııÙœ°…Y\Ã\á\àvúğ+¤±ñ>û@	$\Öô2zXuªj“\ÑŸs“šU©MAhú³¬¹¹@~c\Åršı\ÌDmŒ‚Ç¦*…\î­5Ì¡\"=x­M?AF·KŸ\ïW¾\íUY=¬O\á\Ëmhn%b3\Í`\ë7ó\Ïx\Â6 )\Æ+\Ñ~À‚\È&\ìtª^¶‘å•¹\Ü{\×D\á%N©¤Šº„\ís.\Õ\èi–\Úg;Zš=ª9«vqP\â¤\îÌŠw¹ƒ…–\ÃŠ.®FÁ\ëO€Œ•\Î\Õ\å \ï¡, \È9P\Î \È\ÛŞ´ğ~^j‹\Ûoc¸V‰;h4pø\Õt-B\à\ÚÙ¾¡¥O#L™b\'’\0\î2ıU¹gªKx•£\êŠŞ’\Â#\ÇıôEnGb‚@\İ\ÅMy*A	\ät¯*¾WB¼¹\äµ=*9¦\"Œ=œ]×™PFcó¶\çpbƒ‘‘Ó·<\â¯\Í~\Ë\Ì\ÅHÁ\ä\\ıÖ¦Š¹-Ò°\ïu\ã7\Ëg®\Â\Ã\Ù\ÒVG=j\Õ+Ën\ì«š–‘®m\Ôo’\ê\é|˜$0òñ’1€9\Ç\ëªNO›Õ¹5\Ã\ß[^\á°rqƒ\ëWlô»¨Áóµ©\ÓøRI2qb²‰b\æ¥O\İ\îz8<\İ\á\éºs»\ìC\âƒ6µ©U\Ë\Û[ g‚\İ\Ïôü=\ë=t¦?”bº4P`Ô…W¯j†_\ZT\Ô\Øò+b]I¹½\Ù\Í&\Ù\ç5¡;}À5¯\Ú\'\n\Ö4Lœ˜ı\Z\Ï7‚VRTv®\Ö\Ò3p\Êœ\n‡N\ÓQcÀ\à\Ô\ë3ióòx¬\ÚJjú!§¡¡s¾ +J9Vd\ç“X§VI—kŠ‚\ëWHÂ\Õ\Ö\êGry\\¤&\İIùsM{}ªkB+s¸UoÈ°\rMKE\\”ÌjL\Ùb}ªyÇ•\Ê{R³d\äS®q%©Áù±\\ĞŠÔ»–4\éuó‘šŠ\æò%cXVmv$!‰Û*ô¶2\äjg7k\"\É\Úùcˆ¶p+\ÔüB”SÈ¨u³-¥±@O5\Ê\ÚB÷\äš\ç»nÈ´‘r\âi®‰œSì¬Š\\\ZÔ¶³@«K\×L0\î÷‘Ÿb(\äHÀ\ã¥L.<Îœ\ncÛƒŠ|V\ê\rvsò\èd;n\êC\Å]HÀxsÜƒ*QŠ\Úğğœ\ã5FX2ı1	\ë)L¨ˆ”EÒ ¸\Ä\ç$\çdÈ§w¥1¿t	²½ñ#x\"…\ë¼-¸1\0Võ\äÒ¸ ­i\åó˜ƒœUT\Şx«t\Ûøv5=\n\\¨ùk\æZLµmJ\ÇwL\Õ;\Í\år«ZÕƒg\nfjÁ\Ç\ÍRq\å§šBfn6UI/Z\ŞM¤\Z\Ê)GtRÔŠM\èHñ©\á/·&£’\è7Uı)Z\ä¬*ŸÊŸ\"\Üw³9\Ï¶\å\éX–Acb\Ø\ï[Z¬W‡÷qT\íô‹\ßù\åŠ\åQq©Ì‘¯2\å,‰\éR,”±i7Dı\ÜV¶ƒ3}ü\×|j\ÎZF&W3Œ”\å\Ö\ßöP2\r;û$(\Æ*f«()3úTªÇ¸­`«Úö0z\n\Æõ”Zo(ô¥´[J¿&µP›i	¹*·°Õ‘¨Œ\0À¤u\È\ç½T±)äŠ‘¯\áa÷€¡sE\êk~\ÄRB‰ÀªRÁ\Í>÷Tµ…rfƒq\â¨\"|*–ú\nnªE\Üõ\Ó\Z\ã¥P™ˆ\èW¤sÄ–$Vˆ’£5F\î\Şs˜ÖŠ*šMÌ¦\Ç\ÚD{WoÒ·¿–>J(¦’°¤Eö(?¹RH?ç˜¢Š,„‡­´*\Ù*eE\Ç\İQT0e•‘®:QERS5À\â™k\éEvWR9{\Ö%ôŒ¹Á¢ŠÆ¨HÇº\éYø,\Ø.\Øú\ÑEy5÷	ŸJ¶Ÿı`sÿ\0­{Nñn(¢¦šEÿ\Ù'),(2,12,'2026-04-30','Peixe2',12.979999542236328,1,0,_binary 'ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342ÿ\Û\0C			\r\r2!!22222222222222222222222222222222222222222222222222ÿÀ\0\0”\0¦\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0\0}\0!1AQa\"q2‘¡#B±ÁR\Ñğ$3br‚	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\á\â\ã\ä\å\æ\ç\è\é\êñòóôõö÷øùúÿ\Ä\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0w\0!1AQaq\"2B‘¡±Á	#3Rğbr\Ñ\n$4\á%ñ\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz‚ƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\â\ã\ä\å\æ\ç\è\é\êòóôõö÷øùúÿ\Ú\0\0\0?\0÷\ê(¢€\n(¢€\n(¢€\n(¢€\n(£4\0QE\0QE\0QE\0QE\0QE\0QE\0QE\0QE\0QE\0QE\0WŸx\ÃX\Õ\ãñ<\Z] \Ö0?´3\Ã\Z³\È\Åö\ã,\0cÓ½z\ry¯öÿ\0\Âm \ì#y±»\×n\è¶\çñ\İú\Ö\Øt¥V*J\è\æ\Å\ÊP¡)EÙ¤Cc\ã\éN\İ5‹?ù\én¢;”ë¯ø`ık´\Ñ|U£x€§_$“(ù\í\Ü•?\ŞFÁ•p}…S½\ÒtıGo\Ú\í!™“\î9_™~‡¨¯F®]­7c\Å\Ã\ç3ZVWóG±\ç\Ø\×?¬ø\ÇD\Ğn\Ö\Îö\í\Í\Ñ]şD¼\Ò*ÿ\0xª@÷5\ç+¦<hcM_ZX\ÏğNo\ç»#ó¨¤¶\Zm²\Ú\é0F—w\×	\n3üÛ¤c÷Ü“–À\É\äö®¨J)\Ên\É¿\Ú\Ô\æ\ÔiE¶û\è{…ı®©aõ”\Ë5¬\Ê9£\nµYz‘ƒ¢Zip;\Éºmó\ï9\êXû’I­J\à=`¢Š(\0¢Š(\0¢Š(\0¢Š†y…¼+U±\Ç\\\n\0šŠ\â´Ï‰š¥%²º\ŞØ¥\ÈS—–\å#“wL0\È\È\ÆH\Îk´&›Mn$\Ó\ØZ\ãş!jwú_‡K	Z\ÜKs\İ/Ş‚6$\ã9\Ú2zn\Ïj\ì*½\íœ\Z…¤¶·P¤\Öò¡I\"q•u=A¡;;„•Õ:\r“\Ş=\äò²My#¾~¥³RÅ§Klsk«\ëÿ\0\î\ê2°ü˜‘úV†©\áKÂª^\Î9õ=G\0|÷V\Ã\Óò\Ñ·\Ì?\Ú\í¥Õ½õº\\ZL“Á Êº\r{t~¯Z:E-‰ú\æ\Z^ôİ»İŒÎ»\Ğx»[\Û\ïörô]2\Ú\Å-\î\'º’k‹«¹°$¹¹»°z\É\à\0=«;Vñ^™¤Mög”\ÏxG\Ëm\Ìßaø\×9s\âmrùˆˆA§@}™!üOò¤\Ş\ZŒ®–¾Gn-Í³E\ËM7\ïDw\ç€I\à¦¨\ÜkzU¦|ıF\Ö<u\İ(¨<!\á#\Ä>µ\Õ5¯µ\ê7.\Ò7PmvN\0v®\Ú\Ø5\ÕÜ°YÀ›™<¥0¨…Ç¶\ë^Vˆ)\â«Ô¡Jğİ¿[–]\Â\ÅTör©gk\ìv\Ï\ã?\'\Ş\Öm?\Ïò\0ñ‡¥\Öô)W¶\Û¢’\ÈYğª¸a’OÖ­|3Ò óV\Ô%·‰\Ö=–\È\Z0F~ó5…z1°²#\ÊØx—ü+\Â\Íx\Åa«O\n\é\Ş\İS5\\9K\r_İ›n,¿i\ã/_ZøƒL”ÿ\0³t¿\ã[QKÑ‰\"ut=NA®ó\Ãş»™m\ï42I¤RUZ\ÜÀu=3‘ù\Ö[|;\Ñ`”Ï¤=ö‹pH&M6\é£\é\ê§*G¶+Î¥\ÅXf×µƒşgs\ÃK£=C#Ö–¼\æ-GÆ¾\Û\æù>%\Ó\Ç\ŞÚ¢\Å\Ü\ì§ºŸø§Iñ,I§\ÌştG[J»%…½O#ùW½†\Æ\Ğ\ÅGšŒ“şº­\Ìefn\Ñ@4WQ!EPYºı\ÜV\Ôn\ç`±Am$ŒO`Õ»›¨,\í\â\æd†\Æ^I[j¨÷&¼\Ë\Ä~$_\"\éºz9\Ğ÷‡¹¼e+ö§!#’¤€Kt#œæ®9T—,L«V…9\Í\ÙúVŸğŠ\Ø\é×¬ˆ,\ãŠX\Ød”f»_‡º¬\Ò\Ù]h—’¼\×Z[*$\ËInÀ˜˜\ç\0©÷_z\çºõ©ºU\Ø\Ò|o¥]±\Û\r\èm>bznoš<ÿ\0À”¯üW­¢+®‡\Ïex¦±/i~g­QH*›˜--ä¸¸•b†5.\î\Ç@\êMx\Ç\Ó¿½µÓ¬¦¼½™ ¶‰wI#œõ\á±c\âŒu	´#.‰¤\Ô\İ\Ï\Û-\á$\áÂ\Ï\\u\ÏA›\âÿ\0\\xóPE\áûwÍ¼\r\Ãùh\ãù\ßZ\ê~ª}—Zr™öµ\İ.?›W›œ\âª\àğS­I\ÚZ%ó=%–K\êÊ½e¤’\ï\Öÿ\0€x¿\Â\Ú^…ğö\æ=.\Ñ û<\Ğ\Î\Ï\ÕÜ‰\Í\ÔğMp<g+\Òş\'\\ù^\Ëp\İyuP§yı¼º\æFŠ\İ\İs…;G«vsp¼\ê\ÔÁJ¥W{É»¿‘ô\ÙT\èÕ›øW\èµ=CÀ¯$?\Ä\Ê2À]:£¾?•y^œ6\é\ÖÀt/ò\î\Ú”ºg†ì´–\åa¶Xœ\ç7\æs^ ,\ä\Ó&›L˜~ò\ÊF½\Âı\Óø®\ã\\¼7‰…\\V*\İe•Ù†AQ}bI\î\×\êz‡\Ã\Õ|ct·“³ıC•ş@WWyyŸe=\åÔ-\í\Ğ\Ë#\Ê5\Å|-¸\'J\Ô\ì\Ïü±½,> ÿ\0<Õ¿0\Õõm#\Âjr—²}¦ô\ÖÑœ\àû3\0+\æ1øgS6«	\ív\Ûò\İş‹ˆN’{\İşe\ß\nA=\ìO\â+øö\Şj\0£=m\íºÆƒ\ßsz“\ì*Å²\ØßDÓŸTÖŠ†hU‚\Ç=\ZF\íôşUµ­\İ\Ü\éú%\İÍ«]]$¹,\ç…L‘Ÿj\ÏĞ´\ËO	\è/5\í\Â,Í›FöS2C\Ë1>™8\Õ\ÏÓ¨\Ş\"¤o­£ó¶¶JŞ¯\æb\î´_y—¦]|CPƒûWO\Ğæ²’@&’2I\'¾cƒ\Î:Õ©¯øhj7jºl\æ\Ã\\¶¸¼A÷¿ØJ…<b÷ı¦\ËÃš\íİ‡_µ\ÇmµHõ\n\Ä1…miº•¦¯a\í„\ÂX$\È\rŒA\äy¢·\Ä<nq\Äû/en\Ê\Éú«²W$¯ÜŸÁ\Ş)ÿ\0„“O™.`šµ“ù7öŸó\ÍıGª‘\È5\Ô×—\êdx{\â‰®\ÆvC©?ö]\èş$f&>\àŒgğ¯O+ô»f\Z5\×]ızœs,¬-Q]¤c\ãñ-Ï‹4\Ë;õK6\í-´m÷%¹\r\Î\áĞ•\\Õn*×¢kZ-†½§=ü>dlAR§k#Œ­\Ô\ê+\Î/¬/ü5:Aª¿\Úl\äo.\rD(‰<,€}\Ö>¿tŸC\Åzx\ZğŠöos\Ã\Ípu*?kR\è>©jÖ²\İ\é²$\éšÙ‰ÀYP\îCÿ\0}Wj½\íõ¾nnn¤\ÆÁ$“\Ğ\09$ú\nô\ê(¸µ-“’šp\Şú“\á\Írh6šœh>t=cq\Ã!÷øW\â)ŒüC\'‡¢v\ZšÁµ\'S´M\ÕmÁş\è-ø\n\ç\ì$ñ¥\Ä×¾±\Ô-š\ïã¼‰E³±\ã\Ì\ÚX29\È\ãk´\Ñ4˜t=*	“nZI_\ïH\ç–fõ$’kó Í©\á):xy©NZh\ïe\Õúö>ó¥U)N-y3\È<A§.\âGOD		qsn `yo\ÏF+£ø_?•¬\êÖ½füAeÿ\0\n\âj ñ’Tşô\Ù\Ë\æ»¹v\çñ-ú\Òü1\ä×µKÍ¿º\Ş83ş\Ñ%±ùcó¬ñUş±\ÃşÒ¦ö_zvül}mJ\Ó*Š–\êI/\ëĞ“\âŒ\ÌumÛ¢ˆ®&#\ß\äQúü\ë”\Ò\à[¯h¶\ï÷ş\"\Ã\Ô)İüv»OŠVM\å\é:š–	^\ŞC\è$şú@?\Z\ám\çû«¦\İÏ½õ¼‡\é¼ú]#SÉ¹iî”—\ÏSlO,­\ï¯\ä}úõ¯ ñ\ì1Aã™š<qg²\0?ˆ\\Ÿª¨ğ\ZõMVş\ßH\Ò\îµ¦\"hÌ¤\Ã\Üôük\ÅA\ÕüY\â9$Š}FóTŸ\İ\ÛD8>ƒõ$ş;Â”e\n\Ó\Å\Í\ÚM7÷n_%Nº­\'h\ÃVşV·\Ì\ì~£\í¹\Ç\Ü2\Å>¬\'ùŠ\Ñğ±]_\Æ^&\×~òE2\é–\äöX\Æ_ğ&­+Kµğ†L³H¶\Èó\É3u‘ğK1ÿ\0>Õ™ğ\ÊÕ­~\é!-5\Èk™õfv\'\'ô®\\v.5\Ş\'\r¤\ÔW§_ı%}\ç\rzÖ³·mu`\Ù\Ø\'‹<Qr.—~‘¢Ê¨°Ÿ¹=\Ö2K\á\\™\'Ò·‡$V/„\ï\í´‡\×Ú½\ëb8\îo.n\ê[\Ï|sœ[ğ\Z1«-\â´õ}N|LšŠ]\Îô.p—VË£øü¬ $\ZÕ«O\"ŸP[¬­\Ïû¢¸/\0|Lñˆ~$Ekp>\Åz$fª»a\Â\ä`õ8\Ç\\óš\ï5y—Rø“i8e\Òl$y\Øºó\n¿÷Ê±ü«\ës¥€«\Ï\Ûñ\éøœÔ®¦¬d|O/n®\âÿ\0[g4(}dSŸ\ç^©^WñIˆøu« û\Òyq¨õ%\ÔWª{W›Â—ú“ÿ\0ü‘¦\'\ã\n(¢¾˜\ç\n­}eo©Y\Ëgw\nMm2”’7\rY¨.n¡²¶’\æ\âDŠÔ»»œ©4\ä\Ğ\ÆöW\×\Ú\\\Î\Ò5…Á€H\İ]6†B}N\Ö\0\ä\ZM:.¼wb.€+me$ö\ÈÜ3r©lz…?†ib¯\ï¯õgFO·\ÎfDn«P©\ÇbUCØ±¨µ:+õ‰\ÚYm§·mğ\ÜBû^#\êô<\Zô±\ØJ¸ÌºT¹e%¹òôñ°ø÷R×Šg ñUµ\èt\Í:\æş\à\âhšWúŸ\é^}§x‹\Ä¸Ò§»ñ\rµ»œ\Çd¨¥\ŞÄœ`3€¹\äb»µ´ño„®\Êu’\ÛP¶eA\Ğ\äqôÁ\àş5ø\î7$­—Õ‚\ÄYÅ½Óº\ß_™öÔ±­\Å?‡’D5xˆ¨5µ\ŞÅ²c´„t\àgñ$û×°xA´ğî––6a›$\É,\Ï÷\åsÕ›ß\Ã¼\ë\á}\àÿ\0„’\á&R“\\i\à\í#Ñ¹\Ş=ˆ/úW­W£\Äøºª°pÒœR²]tıG;\Ï\Ù\Ç\á\ß5{ú½\ÎwÇG?€õ¯3–­2û2|\Ëú^<\Êg{hfK‹ˆ¢A\ê\ÅÖ»_ˆş$I\'‡\áp¶\Ñm’şlÿ\0À„cô\'\Û½?Àş[\ÈuıR6…#¬­˜a¹óv\ã íœ\Õ\ée5³2©V®\íÏ¬W\Ëõ:pµ¾¯†¨\åöôK\ï»ô\èu6´’ÿ\0Áš¬0)iD^b \ê\ÛHl~8®G\ád\ĞÉ¨k\n™+y±\ÉC»?\\şu\é¸ù¯+ğ>—y¥üA½·s\Åk71hŠ C\"´x=1Ú¼lºº©–bpòvµ¤¿\r?p\Æv¥(>¶vŸ“ü\ç\Æ2˜|­:õr\è4¾@Ñ”tqÿ\0\è4ÿ\0Û›\Ï	\ê\Ö\ê2^\ÒA_”\Õ_Ü‹¯\è³mz?¥yi\Âu\×óş†o\äl_Iwœao\Å\Ïc–S\Z·?\ŞÁ\Ç\í^k\â7\Æ÷~\Z\Ôôxt+Sg{x.\Â\Û_+4dÎ¿0]Á›\æõ>\Øõ:*ò\ìÚ®^Û¥\Û\Ş÷ÿ\01N’\ç\Ì\Ú^ \Ğü_mmc	±\Öd&(~Ô¡0X`Ÿ›Ÿıl+\ßü5 ÿ\0`i¦9®^\îúw3^]\É\Ë\Í!\î}‡A\ì*Î³¡\é\Úı‘µÔ­–d\ÎQ¿Š6şò·U>\â®A‘q™Rˆ{œ³c¹>µßš\ç²\ÇĞŒ»\İw\ì\ïúXŠt¹es’ø“wmm¢\é‘\ŞJ‘[M«[	]ú,jK±\ã\Ù+`øÿ\0SºOµ\é¾\Ô.4ş¢if)$_T<USC\Óõ—³kø\Â\Ò_:%o»»\ÉúÖ^ô`s\ç\ÂÆ\ŞWm·ú\èóÉ¶_\Ğõ«/\èöúš\âH&e#‚¤v ğEƒ\àã¼OC÷#[ bˆ¶?\àE¿\Ñ_¢Q©\íiÆ§tŸ\Şpµgc±s…$œŞ¼³YÖŸÅ—[‘ˆ\ĞblÀŸóøÀÿ\0¬oT\ÈùW¾7zc¼ñ\\WW\Õ\á±$]=œ«?¼Tâ¼²\ßV\Ó\Ò\Â\Ç\Èrÿ\0h…M½¼*^G\\<wô¯CN“”ú^gV¬i¨RZË±©Yº\ç™>šúu±?l\Ô?\Ñ-•z–~3ô\0–\'°­c ø£V?-„zE¾xù„’qı	‡Òº­Á–:É¿–ioõ6M­wpFTw£„OA’k¯‡+Œ5gƒÊª¹©\Õ\Ñ/¼\ŞÓ¬ \Ót\Ûk(+x\Ö5T] \01À\í^\â(¢ğó\ëv¬¿Ø·²¶X†\0\Å!ÿ\0–°¯|ÿ\0½E[ñO‰õ!¬Ë¡\è­©‚5k«\çP\ì…òBÆ½3I<85\ÍC¥[¥\Ï\Ú\î^k\Û\ßùú¼\É Ï¡<(ö\\\nòå•¬u\'\n‹\İ\Õ\Ñ\ëb3:xYYk\"¯‡t;\ãñ\rõ‹H”\è²4ó\ÅwŠRT‘F\0Á\Îwrx\í^™^k:Ÿ†î¥½ğò¬–\îKM¤\È\Ûcsİ£?Àß¡®«Cñ‘®¿Ù£•­uûöWK\åÌ¿û\Ã\Üf¾‰²Œu*ª¤\ã\Í¤“]—?Àõ°y,\\T¢õ\ìy§‰mÅ§\î\Ö\èf)5k‚ñD\ÅóR?\nõoY´\Ğmcš\äHL’yq\ÇåNn€õ®\âÆŸp÷–7V–³»\Éo-¹x£-ûÀU£Kb½\Zk}KNKmJ\Ö)Ğª\ïe7\ëš\Ë1­J¶Z¶±I¦“W\Ò\Ëñ;\ë\Í\Î\åz¥o\Å\Ûó9\Æñ\Ø\èº-\îÚ’1ÿ\0³\Z\Ìo\ëcP~Df)°V\É+ı\ï0\ã\æöÀ½\ë^÷À\Ö\ro#i\Ò\Ü\Ù\Ü>YóZHó\Øbxúb¸\ÛY¾\Ñk\Äm, °ş\é\î+\ëxw+\á\ì\Î=9]+>gµú«h|vk\ÌğN.N6o¢ü\ÏK\Òõ;]µ’[I<À¿$±0\Ã\Æº\Êzqù\×+ğØ¾—«\á;—>~“v\ŞVz´ÊŸ\Ï?nx.\Ù ğ¥Œ¢0’\ÜF.\'$r\ÎÜ’O\é\ì1U¼I İN\ß\Äz\Z§öÅ¢ym«wx\Øö=Á=ñ_ş¯\ZÕ°pvƒ~\ë}v¿®«\æ}D\Ü#9o\Ô\êh¨-\'k›8g{ym\Ù\Ğ1ŠQ†B{zŸ¾+Ã”e\ÊÎ‹œö½\â¸tyÅ½•Ş¥©²ov‘\î`½‹Š?À\Ö?†¼{©ëq\á[»{I&0˜eó•\ë€\Ê\àœWG©¡®¿=„–_\Ú\Ò(icB<\ÆŸR=:\Õ\rC\Ã¥üú®‹ª¾‘u/\Ír6	-\æÀûÎ„qü@ƒ\ëšö¨ıQC\ØÕ¥i4­\'}ıN\Ú3\Í{¦tµ“®\ëq\èÖªR3q{p|»;D\åç“°\Ó\Ôô³<=c\â\ïhVÚ”ºı…”7@ºy\Zv\çhòv¶Yğ20\İ3]V…\á\r?D¸k\İ÷ÚŒ‹µ\ï¯_Ì˜\îƒ\ÑW\Ù@\ë`xZ§´R\Ä\Érö]\È\Îx•ot—\Âz+\è:·&¼r\Ó\İ\Ê3\ÎG¶N°Võ÷\n)hq…bh\ŞÑ´	\î§Ó¬!†k©L’\È,I9À=—$\áG5·EP	ŒR\ÑE\0s:\ïƒ4\İvğ_4·VW\á<¿´\ÚI±™{ld\ã \ã5ˆÿ\0õ5\ì\Ş+—ş\Şlcÿ\0\ã¥k\Ğh«Y\ÃH»N…*óŠ#\Éu}_ğÍ‘¾¼¸²\Ôl#eó\Ş(š	#Rq»i,LƒTõ=\ÃV@·¶¨\ì¿qú:ºÃ‘]7\ÅÕ‡H\Óm%‘`²»¿As3ªª€¸R{e•G\é\\¢k\Ö\åØ´º”™À[šsŸ} øšôğµ¹\àı³ºó<,~Ó««E§\åq-\ãñ.\ßñ*×š\æ\Ò\×SS.=„€\îëš¸¾<\Õ4ø\Ëk>q\Æn,.E\ä\ã\î¶\äô\æ”E\â\Ì_	\ê¾_÷‹À\×i}ß¥ej7ğ\Ïd&+*¦Ÿ{o5ô\ÄRH\ãIŸrŸ(\'\ß\ä\ãr£¥%\Í\å¡Ó‡\Æ\æg\Õ^\ëv»ÿ\0€u£Æºdr¼¶\Õlœ\ÏÎ0\Ç\âŠ\än\"ğœ\×Rˆ¼eoig4Œ\Ò[>\×q\Ëf ¨\äö8\Í{”n²\ÆNU†AõÖ·¾ôŸªŠñp¹,pR”°•eÕ­·\Ü{u¹+¤ª\ÅJÚ}Œ¼#mn\Å\â\r%bB¢‹´8·Zañ\ç‡Í½\ë\Şgµ¥¬³şª¤W¡+QÈ·„}T‰\Z\'\İU_ \Åp.\Â\ŞóœŸ\İşF\ßY—c\ÏW_\Õõ\rÂšœ ô–÷m¤ø÷\Íù)©\ÓÂ\"\Öpu\İa,\íO[-\'([Ù¦?6?\İ»\ê+\Ó\Â\ä˜3\æ„.û½\à~J´\å\Ô\ægğ\'†¦\ÒSL\ZTQAy‘´_$ˆÿ\0\ß>m\Şù\Íd?Ãp‹k}\âróNş+IeO\Şî»…\Ã\êy®öŠô§Ji\Ê)\Ûk­Œ\Ókb \Ú†T‰*¢Œ 5V‚\n(¢€\n(¢€\n(¢€\n(¢€\"–\æˆ\Ç*+¡ê¬¹ğ¥Š\à]‘\"¢ú*\àT”P\Å`j\ŞĞµ»¸\îµ-:9§U\n_%K¨è­ƒóc¦º\n(ª¡T\0\0 \ê( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( Š( ÿ\Ù'),(3,20,'2029-12-12','peixe2',122,1,0,_binary 'ÿ\Øÿ\à\0JFIF\0\0\0\0\0\0ÿ\Û\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342ÿ\Û\0C			\r\r2!!22222222222222222222222222222222222222222222222222ÿÀ\0\0”\0\"\0ÿ\Ä\0\0\0\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0\0}\0!1AQa\"q2‘¡#B±ÁR\Ñğ$3br‚	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyzƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\á\â\ã\ä\å\æ\ç\è\é\êñòóôõö÷øùúÿ\Ä\0\0\0\0\0\0\0\0	\nÿ\Ä\0µ\0\0w\0!1AQaq\"2B‘¡±Á	#3Rğbr\Ñ\n$4\á%ñ\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz‚ƒ„…†‡ˆ‰Š’“”•–—˜™š¢£¤¥¦§¨©ª²³´µ¶·¸¹º\Â\Ã\Ä\Å\Æ\Ç\È\É\Ê\Ò\Ó\Ô\Õ\Ö\×\Ø\Ù\Ú\â\ã\ä\å\æ\ç\è\é\êòóôõö÷øùúÿ\Ú\0\0\0?\0Óº¸ò\Î=k›\Ö45¿¸®`Œµ$‘\ápw(\Ş:˜§\Ê\Î:\ÓNŸOFÇµ]I\Ûv\Ù^´k’\Í\ÊVE¤·\È2¬NkZ\Z#¡;4VÀ€@©&Ñ¢¸ˆ£4\í6^6\Ê1\ïZ\ÈÉ»ƒ\ÅF’\ìpw\Ú+\ÛJvş©t¨¦!ƒ\Åv\Ök?*+1•a?\"\ãğ©QJ^ğùŒi	Š‘PH>lcšÔ–##\î\Å@bO3‘\Íg\Ö\ÈeI¬_­W6p\ÅÓ¯µjKyy\İÒ¨\çkõ¥’ª\Z’=+N+\Å1a”Ub˜9\0Š­Àf•jN\"+jh	®KTQ+’¢µoV_\â<\Õ¶™²J\äV2wac\0©‘‡+RKb+Šc\Ú½)\\V2€\æœMNñ\ëùTD\nb>‚<ŒT’\"\ÇN*„—FzU95W‘Oq]ÎªKsHu+6¹n«z}”($û\Õu\Ôó\ÄN?J\Óg^õ›’ø‘¤n%\Äv\á†3N¶ˆ`óš¥sJA¥f«¯\'&”l\ÕÁ²h\Â`‡5^{x‰È¦\Ü\ä\Çò’*]¤jwŠZ DÃ™0µ“ªD`pW¯z\é$‰a]\Ä\Ö>¢EÂ€¸5T†™\Î\Ë*½©V\æ#MGc8$…\àV)i\Ö]‡¥s9>¦†À¿\Ã|¢6ª|£µ/4Ê£½m\\\é¶[±óbŸ3`s_4\×\0\ã5»*ğ«ÛµgZZ+K‚¹ ÷®\Â(±´¯µTe`9›˜“\Ì\Æ* ƒ \àWO©X\Ä2TV\"ü…†*˜¿Œ¬•œs]ü\É8¬9T+b„\Åcİµ\ÇsY\0±š³w;ùœY\×LşY#ƒZ\ËRb†[\Ù,·A½\ë¤XYc\ÍiS\Ê×ª\â»K*¯\0õpŠ\äm“+\Ü\æ5	®„˜Pqš³k~aŒ,Çù«W¼°kQ±f™Óµe\Íg¡I]¹Ybæš³ˆ95\Í\ÚMs\ã€=*\è/>A\\\àÕ\ë“sj\ŞVzV-“N$;—5¯c¯\äõ­´†»¥&\ÄdIûÁ†AƒY\×\Zl\î\Ğ_C\çGûºÄš\ÊV@\"¦\ë\íŒ’¿dºk ²Œ\İÇ†¾Ÿq\æO˜õz\Òğ$›UzUF\Ö+6•,YWô¢+p%\È\â·\ZH¥Q\Ø\Ö©1¶\â6›ƒLI\Üu\í¿\î\É\×)q\ÙI÷®›N¹óÁA¬\İ^İ£b\Ø\ë\éSS\Èf\Ìe\á\ák–¹…„\Ç+¸‰L\ÑV±olKK¦¢3\ÔXš8€ù‡5Nk=N\Ê\Ñh\ÒcÖ¤U€>µ\Ú\ãcŸš\Ç6šL¶Rùùk[N\Õ\Ò\ç÷L>aZ3 ’Œ\Ö}¦“,]H\ä\Ô\Å;y¥}Å¸A!\İY³E\æK´·­r<¸+ò\êy®\î_Ë†1“\ÆIöõ’\ÙjF²\Å\Â[Â¥ˆ\Å\Ã\âOS®‘£©‰N<Û—;G\×ı\riÁ«k\ÌM\Úi\Ï\ê°	ü\É?Ê”±i¾YI&mO\rZ¢\æŒ]§\ìLŒMfx‹Z“Ja·ó\î\'\Î\Ğr@\ÆI#\ê8®‡\ÃxñMœ³\ÚÍ³\ÈsĞ¿U=z0G\"²ş!hóiÖºT±€\ß\é,®ø<§ş´\áû·8½•\Ìi¤\êªr\ïc˜ş\Ğ\Õ\ÈÌ—Àdr¢5 {ûŠĞ·\ÔÄ€$\ÑbL`°<ô÷¬\Ø\áœ\ã,\é\ëS\Åd¤\î\ÜW€±•¯\'s\è]JQ²Ve\ß?<\n|’%¼Rj/³®7$¸>ı+=ôıkV71\éö’İ¬\0|•,TœzûkÓ£ˆ…m \Ï&¾­f´(]x•–|#\n‚mTİ¯\Ì\Ù5F}&rO\ÈC Œ~u•)kf\ç#»©}c\Ñü3`\Ò\âSœV¾§¦3/\0b¹¿\nøª\Ù-\ÄR7J\î\í\î\"¼ƒ\ÍSE7¢%+3“¶\Ó\ÉùB`z\Ó\ï´ûh\Õ€f­\Ş\ê\Ëgt\"U\ÍQ\Õ.R\ë\Ëd\Ísºv\Õ\rjt^IL\æœrYhv$Ô¨8¯F¤ï±‚W$–-¹\Úy\ÅP´‚\â<‡\ÏZ¹5ôvv\ÒO<‰1‚\Î\ìx¸ó\ã\Ë\İ^ô\ÛxsNI\ã}\Å\ß\n®\Ğ~½O\áY»FŸ<¥e\æiJråŠ»:‹\ĞUzW%\â\ë»ğ\Õ\ÒB\á‰zv&´Mş¯l‚[\ß\"÷hÌ‘¢y÷\Éş¹®¿\Ä6:M·„\'¿³\ŞfeO)²>b1\ÇN†°¡Zx¹R•\Ò\ÜŞ®\Z­	F5#k~“Ekeª…D\0UFVšL½]ŠËÇ’y4\ï$/^k\åy\â¤\ÚÕŸc\nrqWF·\Ãû\ï\ì\Ï\Ëj	0¾™#ô-ù\n\ïüC`š\æqa3\ìó@(\ãø«~\nò4œ\Ø\êš}\âp\Ö÷Q¶\Ù\Îô&½7T\ÕSN\Ón¯\'\ËGo9\Çp}~O/o‡´ºh|–oC\Øâ¹–—Wù`Ÿhˆ¼7l&)\"œ\é\íR,\Şõ\ÍXGy\â\İR}gU¸eRv\í‡\ä\É\àc°üë®ƒO¶H|˜\Ú@{r\ßÎ¾s0…\nU`\Ùôù{¯^Š©8\Ùz\îDÿ\0h™A¾\æV	\Zÿ\0´N\êk\Üt=6\ÓÃš4v6ûU\"]\ÒHx\Ş\İY¹\ë^/¡mƒ\Æ:_\Ã\Éû@7÷°Bş¸¯@øŒ\×/\á£‰¿vòÆ“`œ\ì\'¼\Ä×§•B1¡*ŸÖˆù\ìòs«Š§‡Z\'ù·c‰ño´][Z\Ú\ÒAh\Å„9i÷™»g°üı°<A\áè§´«\r\ÊÃ¡¨\àğ†£}\Ó\ë\È­zdKf¶¥?v(ƒ\åb³xS­®\Ó\ßm=ùeœ´œZJ\Û>ş§\éúMÜº‡•l@=k\Ö46k+£\nÙC¶±$¢\r\İ\Î+R›\Ê\ãn>•ôI5«<\Ü\Ç\ÕV)\'is\\\ì\Ú\ÜV\ìU\ãš\×ky\ï\æ0\ÃøŸJ©?ˆ}\ÓLA=©Â”ä®‘I‡·4˜â¥½Cn\Ü\Õ¸WlU\Ê2‹w1G3ñ;™¼,¦%&4Z\\wŸÌŠ­\à1öb(f27™·¨\ä\ã?‡ó®\Ù\à†\æ‚x–Hœa•\ÆA\Ã\Şø3R\Ño´<0\å#u›62>¤ò=q\ã0\Ó\Å\Ğöqvg~_Š†\Z²œ\Õ\Ñ\Ö\íV\"¥¿rş´Á·Bs\è\Ê3úVU£k7?H{I1ó<²¡A\ëŒO\å[QÆ²hGG*Ü¦‡\ïn\Îr=9\äW.K„¯‡hTV\æV^ºÿ\0™\é\æx\Ü=IS•7{Àÿ\0# ùGZG·ö«v\×óÇ˜y„¡»ğH?Ê¥1†\èk\å\ê9S›‹\è}:‘œ–\Ì\æõHB\Û\î?w:õ±\Ôt[\Ë0\Û~\Ó&}2+™\ÕmÁ³mÃ¸¨®\ÆıZ)¥\ÌQY€ğ+\í¸ZjTj_¹òœ@›«»5\áB\Û\Ëd\Ì\Ğ\Èr¹\í\ê=³š\ë–7	‘úV“£\Ë•¥Á·+0@D›y\äV\äMs»À\ÃóX\é\Âu\\©½\çOIlg]+Ì¸ƒƒ•a\ÔÆ½J\ŞşxL¬\à«\\Daœ‚88úG\àk‚À<^l\åV%3±À\ëY\í\ã-+H™¿²÷\Ş\Êx}­¶~¸9úø\×vOq¨\éò9E\ïnŸ¡\åg4)ÖŒd\å\Ë(\ìkiVOòE8ıô,cLş¶\ã]E¸Úi#¹¯&¿ñUııÙœ°…Y\Ã\á\àvúğ+¤±ñ>û@	$\Öô2zXuªj“\ÑŸs“šU©MAhú³¬¹¹@~c\Åršı\ÌDmŒ‚Ç¦*…\î­5Ì¡\"=x­M?AF·KŸ\ïW¾\íUY=¬O\á\Ëmhn%b3\Í`\ë7ó\Ïx\Â6 )\Æ+\Ñ~À‚\È&\ìtª^¶‘å•¹\Ü{\×D\á%N©¤Šº„\ís.\Õ\èi–\Úg;Zš=ª9«vqP\â¤\îÌŠw¹ƒ…–\ÃŠ.®FÁ\ëO€Œ•\Î\Õ\å \ï¡, \È9P\Î \È\ÛŞ´ğ~^j‹\Ûoc¸V‰;h4pø\Õt-B\à\ÚÙ¾¡¥O#L™b\'’\0\î2ıU¹gªKx•£\êŠŞ’\Â#\ÇıôEnGb‚@\İ\ÅMy*A	\ät¯*¾WB¼¹\äµ=*9¦\"Œ=œ]×™PFcó¶\çpbƒ‘‘Ó·<\â¯\Í~\Ë\Ì\ÅHÁ\ä\\ıÖ¦Š¹-Ò°\ïu\ã7\Ëg®\Â\Ã\Ù\ÒVG=j\Õ+Ën\ì«š–‘®m\Ôo’\ê\é|˜$0òñ’1€9\Ç\ëªNO›Õ¹5\Ã\ß[^\á°rqƒ\ëWlô»¨Áóµ©\ÓøRI2qb²‰b\æ¥O\İ\îz8<\İ\á\éºs»\ìC\âƒ6µ©U\Ë\Û[ g‚\İ\Ïôü=\ë=t¦?”bº4P`Ô…W¯j†_\ZT\Ô\Øò+b]I¹½\Ù\Í&\Ù\ç5¡;}À5¯\Ú\'\n\Ö4Lœ˜ı\Z\Ï7‚VRTv®\Ö\Ò3p\Êœ\n‡N\ÓQcÀ\à\Ô\ë3ióòx¬\ÚJjú!§¡¡s¾ +J9Vd\ç“X§VI—kŠ‚\ëWHÂ\Õ\Ö\êGry\\¤&\İIùsM{}ªkB+s¸UoÈ°\rMKE\\”ÌjL\Ùb}ªyÇ•\Ê{R³d\äS®q%©Áù±\\ĞŠÔ»–4\éuó‘šŠ\æò%cXVmv$!‰Û*ô¶2\äjg7k\"\É\Úùcˆ¶p+\ÔüB”SÈ¨u³-¥±@O5\Ê\ÚB÷\äš\ç»nÈ´‘r\âi®‰œSì¬Š\\\ZÔ¶³@«K\×L0\î÷‘Ÿb(\äHÀ\ã¥L.<Îœ\ncÛƒŠ|V\ê\rvsò\èd;n\êC\Å]HÀxsÜƒ*QŠ\Úğğœ\ã5FX2ı1	\ë)L¨ˆ”EÒ ¸\Ä\ç$\çdÈ§w¥1¿t	²½ñ#x\"…\ë¼-¸1\0Võ\äÒ¸ ­i\åó˜ƒœUT\Şx«t\Ûøv5=\n\\¨ùk\æZLµmJ\ÇwL\Õ;\Í\år«ZÕƒg\nfjÁ\Ç\ÍRq\å§šBfn6UI/Z\ŞM¤\Z\Ê)GtRÔŠM\èHñ©\á/·&£’\è7Uı)Z\ä¬*ŸÊŸ\"\Üw³9\Ï¶\å\éX–Acb\Ø\ï[Z¬W‡÷qT\íô‹\ßù\åŠ\åQq©Ì‘¯2\å,‰\éR,”±i7Dı\ÜV¶ƒ3}ü\×|j\ÎZF&W3Œ”\å\Ö\ßöP2\r;û$(\Æ*f«()3úTªÇ¸­`«Úö0z\n\Æõ”Zo(ô¥´[J¿&µP›i	¹*·°Õ‘¨Œ\0À¤u\È\ç½T±)äŠ‘¯\áa÷€¡sE\êk~\ÄRB‰ÀªRÁ\Í>÷Tµ…rfƒq\â¨\"|*–ú\nnªE\Üõ\Ó\Z\ã¥P™ˆ\èW¤sÄ–$Vˆ’£5F\î\Şs˜ÖŠ*šMÌ¦\Ç\ÚD{WoÒ·¿–>J(¦’°¤Eö(?¹RH?ç˜¢Š,„‡­´*\Ù*eE\Ç\İQT0e•‘®:QERS5À\â™k\éEvWR9{\Ö%ôŒ¹Á¢ŠÆ¨HÇº\éYø,\Ø.\Øú\ÑEy5÷	ŸJ¶Ÿı`sÿ\0­{Nñn(¢¦šEÿ\Ù');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuarios` bigint NOT NULL AUTO_INCREMENT,
  `cpf_Usuario` varchar(11) NOT NULL,
  `nome_Usuario` varchar(100) NOT NULL,
  `senha_Usuario` varchar(100) NOT NULL,
  `email_Usuario` varchar(100) NOT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `img` varchar(150) DEFAULT NULL,
  `descricao` varchar(360) DEFAULT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE KEY `cpf_Usuario` (`cpf_Usuario`),
  UNIQUE KEY `email_Usuario` (`email_Usuario`),
  UNIQUE KEY `telefone` (`telefone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'85318806961','vini','vini','vini',NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendas`
--

DROP TABLE IF EXISTS `vendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendas` (
  `idVendas` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` int NOT NULL,
  `Produtores_idProdutores` bigint NOT NULL,
  `Clientes_idClientes` bigint NOT NULL,
  PRIMARY KEY (`idVendas`),
  KEY `Produtores_idProdutores` (`Produtores_idProdutores`),
  KEY `Clientes_idClientes` (`Clientes_idClientes`),
  CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`Produtores_idProdutores`) REFERENCES `produtores` (`idProdutores`),
  CONSTRAINT `vendas_ibfk_2` FOREIGN KEY (`Clientes_idClientes`) REFERENCES `clientes` (`idClientes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendas`
--

LOCK TABLES `vendas` WRITE;
/*!40000 ALTER TABLE `vendas` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-09 10:13:49
