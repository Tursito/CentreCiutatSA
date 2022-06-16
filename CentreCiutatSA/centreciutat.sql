-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-06-2022 a las 00:35:15
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `centreciutat`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE `alquiler` (
  `idAlquiler` int(11) NOT NULL,
  `idEstacionamiento` varchar(10) NOT NULL,
  `precioMensual` double NOT NULL,
  `dni` varchar(10) NOT NULL,
  `matricula` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alquiler`
--

INSERT INTO `alquiler` (`idAlquiler`, `idEstacionamiento`, `precioMensual`, `dni`, `matricula`) VALUES
(1, '1A25', 500, '43056439H', '3348KFW');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estacionamientos`
--

CREATE TABLE `estacionamientos` (
  `idEstacionamiento` varchar(10) NOT NULL,
  `precioMensual` double NOT NULL,
  `m2` double NOT NULL,
  `disponible` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estacionamientos`
--

INSERT INTO `estacionamientos` (`idEstacionamiento`, `precioMensual`, `m2`, `disponible`) VALUES
('1A20', 200, 4, 0),
('1A21', 300, 3.5, 0),
('1A22', 300, 5, 0),
('1A23', 440, 4, 1),
('1A24', 340, 3.4, 0),
('1A25', 350, 3, 0),
('1A26', 250, 4, 0),
('1A27', 400, 4, 0),
('1A28', 475, 3, 1),
('1A29', 500, 4, 1),
('1A30', 320, 3, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `dni` varchar(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `cuentaCorriente` varchar(30) NOT NULL,
  `admin` tinyint(1) NOT NULL,
  `contrasena` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`dni`, `nombre`, `apellidos`, `direccion`, `cuentaCorriente`, `admin`, `contrasena`) VALUES
('43056439H', 'Reginaldo Patricio', 'Angue', 'Calle Volcán 34, bajos H', 'ES213243434356788986', 0, '123'),
('43451287S', 'Marcos', 'Perelló', 'Calle malta, 11', 'ES154287653251487591', 1, ''),
('43910299L', 'Antonio', 'Rodriguez Soleado', 'Calle Andalucia 293', 'ES933920475839028480', 0, ''),
('49403993T', 'Alfredo', 'Martinez Soria', 'Calle Peru 93', 'ES231234543245566532', 1, ''),
('50250345Q', 'martin', 'yela', 'calle soledad 1', 'ES293032101048489341', 1, ''),
('74984134S', 'Leonardo', 'Casio Enrique', 'Calle Mamadou 32, 2J', 'ES313243344356288896', 0, ''),
('94302049N', 'Adrian', 'Tur Marzo', 'Calle Instituto, 23', 'ES293032101048489345', 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `matricula` varchar(10) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `color` varchar(30) NOT NULL,
  `motor` varchar(30) NOT NULL,
  `tipoVehiculo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`matricula`, `marca`, `modelo`, `color`, `motor`, `tipoVehiculo`) VALUES
('3348KFW', 'Mazda', 'RX-7', 'Granate', '2JZ', 'Coche'),
('8493RUF', 'Seat', 'Leon', 'Blanco', '1.0 TSI', 'Coche'),
('8495AKV', 'Kawasaki', 'Ninja', 'Verde', 'H2R', 'Motocicleta');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD PRIMARY KEY (`idAlquiler`),
  ADD KEY `fk_alquilerEstacionamiento` (`idEstacionamiento`),
  ADD KEY `fk_alquilerUsuario` (`dni`),
  ADD KEY `fk_alquilerVehiculos` (`matricula`);

--
-- Indices de la tabla `estacionamientos`
--
ALTER TABLE `estacionamientos`
  ADD PRIMARY KEY (`idEstacionamiento`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`matricula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  MODIFY `idAlquiler` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD CONSTRAINT `fk_alquilerEstacionamiento` FOREIGN KEY (`idEstacionamiento`) REFERENCES `estacionamientos` (`idEstacionamiento`),
  ADD CONSTRAINT `fk_alquilerUsuario` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`),
  ADD CONSTRAINT `fk_alquilerVehiculos` FOREIGN KEY (`matricula`) REFERENCES `vehiculos` (`matricula`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
