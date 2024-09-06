DROP TABLE IF EXISTS `estado_tarjeta`;

CREATE TABLE `estado_tarjeta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `detalle` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `resumen_emitidos`;

CREATE TABLE `resumen_emitidos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resumeMongoID` VARCHAR(45) NOT NULL,
  `total_pesos` decimal(10,0) NOT NULL,
  `total_dolares` decimal(10,0) NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`id`,`resumeMongoID`)
);

DROP TABLE IF EXISTS `tarjetas`;

CREATE TABLE `tarjetas` (
  `numtarj` VARCHAR(30) NOT NULL,
  `numcue` INT NOT NULL,
  `f_vencimiento` VARCHAR(10) NOT NULL,
  `pin` INT NOT NULL,
  `estado` INT NOT NULL,
  `f_emision` VARCHAR(10) NOT NULL,
  `tipo` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`numtarj`),
  CONSTRAINT `fk_estado` FOREIGN KEY (`estado`) REFERENCES `estado_tarjeta` (`id`)
);