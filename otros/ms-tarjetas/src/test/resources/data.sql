INSERT INTO `estado_tarjeta` (`id`, `detalle`) VALUES
(1, 'Activa'),
(2, 'Inactiva'),
(3, 'Suspendida'),
(4, 'Cancelada'),
(5, 'Bloqueada');


INSERT INTO `resumen_emitidos` (`id`, `resumeMongoID`, `total_pesos`, `total_dolares`, `fecha_vencimiento`) VALUES
(1, 'ABCDE12345', 10000, 500, '2024-05-01'),
(2, 'FGHIJ67890', 7500, 300, '2024-04-28'),
(3, 'KLMNO54321', 8500, 400, '2024-05-05'),
(4, 'PQRST09876', 6000, 250, '2024-05-02'),
(5, 'UVWXY13579', 12000, 600, '2024-05-10');


INSERT INTO `tarjetas` (`numtarj`, `numcue`, `f_vencimiento`, `pin`, `estado`, `f_emision`, `tipo`) VALUES
('1111222233334444', 1, '05/28', 1234, 1, '04/24', 'C'),
('2222333344445555', 2, '06/28', 2345, 1, '04/24', 'C'),
('3333444455556666', 3, '07/28', 3456, 1, '04/24', 'C'),
('4444555566667777', 4, '08/28', 4567, 1, '04/24', 'C'),
('5555666677778888', 5, '09/28', 5678, 1, '04/24', 'C');
