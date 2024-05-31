DROP TABLE IF EXISTS `codigo_moneda`;

CREATE TABLE `codigo_moneda` (
  `cod_moneda` int(11) NOT NULL,
  `pais` varchar(45) NOT NULL,
  `simbolo` varchar(45) NOT NULL,
  PRIMARY KEY (`cod_moneda`)
) ;

INSERT INTO `codigo_moneda` (`cod_moneda`, `pais`, `simbolo`) VALUES
(1, 'Estados Unidos', 'USD'),
(2, 'Eurozona', 'EUR'),
(3, 'Japón', 'JPY'),
(4, 'Reino Unido', 'GBP'),
(5, 'Australia', 'AUD');

DROP TABLE IF EXISTS `estado_cuenta`;

CREATE TABLE `estado_cuenta` (
  `id` int(11) NOT NULL,
  `detalle` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `estado_cuenta` (`id`, `detalle`) VALUES
(1, 'Activa'),
(2, 'Inactiva'),
(3, 'Suspendida'),
(4, 'Cancelada'),
(5, 'Bloqueada');

DROP TABLE IF EXISTS `cuentas`;

CREATE TABLE `cuentas` (
  `numcue` varchar(44) NOT NULL,
  `persnum` int(11) NOT NULL,
  `divisa` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `saldo` decimal(10,0) NOT NULL,
  PRIMARY KEY (`numcue`),
  KEY `fk_codigomoneda` (`divisa`),
  KEY `fk_estado` (`estado`),
  CONSTRAINT `fk_codigomoneda` FOREIGN KEY (`divisa`) REFERENCES `codigo_moneda` (`cod_moneda`),
  CONSTRAINT `fk_estado` FOREIGN KEY (`estado`) REFERENCES `estado_cuenta` (`id`)
);
