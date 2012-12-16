-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 15-12-2012 a las 23:44:10
-- Versión del servidor: 5.5.28
-- Versión de PHP: 5.3.10-1ubuntu3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `mc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_nombre` varchar(45) DEFAULT NULL,
  `cliente_apellido` varchar(45) DEFAULT NULL,
  `cliente_email` varchar(45) DEFAULT NULL,
  `cliente_password` varchar(45) DEFAULT NULL,
  `cliente_nro_movil` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`cliente_id`, `cliente_nombre`, `cliente_apellido`, `cliente_email`, `cliente_password`, `cliente_nro_movil`) VALUES
(1, 'Juan', 'Bauer', 'bauerpy', '123', '595981884535');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `d_pedidos`
--

CREATE TABLE IF NOT EXISTS `d_pedidos` (
  `producto_id` int(11) NOT NULL,
  `pedido_id` int(11) NOT NULL,
  `d_pedido_monto` float DEFAULT NULL,
  `d_pedido_observacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`producto_id`,`pedido_id`),
  KEY `fk_d_pedidos_productos1` (`producto_id`),
  KEY `fk_d_pedidos_pedidos1` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE IF NOT EXISTS `pedidos` (
  `pedido_id` int(11) NOT NULL AUTO_INCREMENT,
  `pedido_envio_direccion` varchar(250) DEFAULT NULL,
  `pedido_envio_lat` float DEFAULT NULL,
  `pedido_envio_lon` float DEFAULT NULL,
  `pedido_observacion` varchar(250) DEFAULT NULL,
  `pedido_monto` float DEFAULT NULL,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  KEY `fk_pedidos_clientes1` (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `producto_id` int(11) NOT NULL AUTO_INCREMENT,
  `producto_nombre` varchar(45) DEFAULT NULL,
  `producto_descripcion` varchar(250) DEFAULT NULL,
  `producto_imagen` varchar(45) DEFAULT NULL,
  `producto_precio` float DEFAULT NULL,
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`producto_id`),
  KEY `fk_productos_clientes1` (`cliente_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`producto_id`, `producto_nombre`, `producto_descripcion`, `producto_imagen`, `producto_precio`, `cliente_id`) VALUES
(1, 'pizza 1', 'pizza grande', 'pizza1', 20000, 0),
(2, 'pizza 2', 'pizza mas grande que la anterior', 'pizza2', 25000, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sessions`
--

CREATE TABLE IF NOT EXISTS `sessions` (
  `session_id` varchar(40) NOT NULL DEFAULT '0',
  `ip_address` varchar(16) NOT NULL DEFAULT '0',
  `user_agent` varchar(50) NOT NULL,
  `last_activity` int(10) unsigned NOT NULL DEFAULT '0',
  `user_data` text NOT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sessions`
--

INSERT INTO `sessions` (`session_id`, `ip_address`, `user_agent`, `last_activity`, `user_data`) VALUES
('d21c47c7a0f6e32fa8c615c6a7891759', '127.0.0.1', 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) G', 1355624909, 'a:3:{s:6:"logged";b:1;s:7:"cliente";O:8:"stdClass":2:{s:13:"cliente_email";s:7:"bauerpy";s:10:"cliente_id";s:1:"1";}s:12:"session_live";i:1355624909;}');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `d_pedidos`
--
ALTER TABLE `d_pedidos`
  ADD CONSTRAINT `fk_d_pedidos_productos1` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_d_pedidos_pedidos1` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`pedido_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_pedidos_clientes1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `fk_productos_clientes1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
