DROP TABLE IF EXISTS `codigo_moneda`;

CREATE TABLE `codigo_moneda` (
  `cod_moneda` INT NOT NULL,
  `pais` VARCHAR(45) NOT NULL,
  `simbolo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cod_moneda`)
) ;

DROP TABLE IF EXISTS `estado_cuenta`;

CREATE TABLE `estado_cuenta` (
  `id` INT NOT NULL,
  `detalle` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `cuentas`;

CREATE TABLE `cuentas` (
  `numcue` VARCHAR(44) NOT NULL,
  `persnum` INT NOT NULL,
  `divisa` INT NOT NULL,
  `estado` INT NOT NULL,
  `saldo` NUMERIC NOT NULL,
  PRIMARY KEY (`numcue`),
  CONSTRAINT `fk_codigomoneda` FOREIGN KEY (`divisa`) REFERENCES `codigo_moneda` (`cod_moneda`),
  CONSTRAINT `fk_estado` FOREIGN KEY (`estado`) REFERENCES `estado_cuenta` (`id`)
);
