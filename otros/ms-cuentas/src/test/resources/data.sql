INSERT INTO `codigo_moneda` (`cod_moneda`, `pais`, `simbolo`) VALUES
(1, 'Estados Unidos', 'USD'),
(2, 'Eurozona', 'EUR'),
(3, 'Japón', 'JPY'),
(4, 'Reino Unido', 'GBP'),
(5, 'Australia', 'AUD'),
(6, 'Crocantes', 'ARG');

INSERT INTO `estado_cuenta` (`id`, `detalle`) VALUES
(1, 'Activa'),
(2, 'Inactiva'),
(3, 'Suspendida'),
(4, 'Cancelada'),
(5, 'Bloqueada');


INSERT INTO `cuentas` (`numcue`, `persnum`, `divisa`, `estado`, `saldo`) VALUES
('1234567890', 1, 1, 1, 10000),
('9876543210', 2, 2, 1, 5000),
('4567890123', 3, 3, 1, 8000),
('7890123456', 4, 4, 1, 3000),
('6543210987', 5, 5, 1, 12000);



