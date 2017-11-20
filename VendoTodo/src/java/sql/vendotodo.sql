# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Base de datos: veta
# Tiempo de Generación: 2017-11-12 8:50:45 p.m. +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

# Volcado de tabla tipo
# ------------------------------------------------------------
create database if not exists `vendotodo`;

USE `vendotodo`;

DROP TABLE IF EXISTS `tipo`;

CREATE TABLE `tipo` (
  `codigo_tipo` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`codigo_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# Volcado de tabla producto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `codigo_producto` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(25) NOT NULL DEFAULT '',
  `codigo_tipo` int(11) unsigned NOT NULL,
  `precio_producto` int(11) NOT NULL,
  PRIMARY KEY (`codigo_producto`),
  KEY `fk_tipo` (`codigo_tipo`),
  CONSTRAINT `fk_tipo` FOREIGN KEY (`codigo_tipo`) REFERENCES `tipo` (`codigo_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Volcado de tabla perfil
# ------------------------------------------------------------

DROP TABLE IF EXISTS `perfil`;

CREATE TABLE `perfil` (
  `codigo_perfil` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_perfil` varchar(25) NOT NULL DEFAULT '',
  PRIMARY KEY (`codigo_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Volcado de tabla usuarios
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login_usuario` varchar(25) NOT NULL DEFAULT '',
  `pass_usuario` varchar(25) NOT NULL DEFAULT '',
  `nombre_usuario` varchar(25) NOT NULL DEFAULT '',
  `apellido_usuario` varchar(25) NOT NULL DEFAULT '',
  `correo_usuario` varchar(50) NOT NULL DEFAULT '',
  `codigo_perfil` int(11) unsigned NOT NULL,
  `fechaNacimiento_usuario` date NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `fk_perfil` (`codigo_perfil`),
  CONSTRAINT `fk_perfil` FOREIGN KEY (`codigo_perfil`) REFERENCES `perfil` (`codigo_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Volcado de tabla ventas
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ventas`;

CREATE TABLE `ventas` (
  `codigo_venta` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `codigo_vendedor` int(11) unsigned NOT NULL,
  `fecha_venta` date NOT NULL,
  `total_venta` int(11) NOT NULL,
  PRIMARY KEY (`codigo_venta`),
  KEY `fk_vendedor` (`codigo_vendedor`),
  CONSTRAINT `fk_vendedor` FOREIGN KEY (`codigo_vendedor`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# Volcado de tabla detalle_venta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `detalle_venta`;

CREATE TABLE `detalle_venta` (
  `codigo_producto` int(11) unsigned NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `codigo_venta` int(11) unsigned NOT NULL,

  KEY `fk_producto` (`codigo_producto`),
  KEY `fk_venta` (`codigo_venta`),
  CONSTRAINT `fk_producto` FOREIGN KEY (`codigo_producto`) REFERENCES `producto` (`codigo_producto`),
  CONSTRAINT `fk_venta` FOREIGN KEY (`codigo_venta`) REFERENCES `ventas` (`codigo_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `eliminado` */

DROP TABLE IF EXISTS `eliminado`;

CREATE TABLE `eliminado` (
  `id_referencia` int(11) NOT NULL,
  `descripcion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


insert into perfil values(1,'Administrador');
insert into perfil values(2,'Consulta');
insert into perfil values(3,'Vendedor');

INSERT INTO usuarios ( login_usuario, pass_usuario, nombre_usuario, apellido_usuario, correo_usuario, codigo_perfil, fechaNacimiento_usuario) VALUES ('administrador01', '9k3xyHz7uuU=','Juan', 'Tapia', 'jtapia_admin@vendotodo.cl', 1, '1880-10-12');
INSERT INTO usuarios ( login_usuario, pass_usuario, nombre_usuario, apellido_usuario, correo_usuario, codigo_perfil, fechaNacimiento_usuario) VALUES ('JuanGConsultas', '9k3xyHz7uuU=', 'Juan', 'Perez', 'juPerez_consulta@vendotodo.cl', '2', '1891-6-10' );
INSERT INTO usuarios ( login_usuario, pass_usuario, nombre_usuario, apellido_usuario, correo_usuario, codigo_perfil, fechaNacimiento_usuario) VALUES ('JuanPVentas', '9k3xyHz7uuU=', 'Juan', 'Gonzalez', 'juGonzalez_venta@vendotodo.cl', '3', '1888-12-5' );

insert into tipo (nombre_tipo) values('Comida');
insert into tipo (nombre_tipo) values('Bebidas');
insert into tipo (nombre_tipo) values('Electro');
insert into tipo (nombre_tipo) values('Tecnologia');
insert into tipo (nombre_tipo) values('Muebles');

insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Leche entera caja 1L',2,600);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Yogurt frutilla 120g',1,140);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Platanos 1Kg',1,630);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Naranjas 1Kg',1,700);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('lechuga escarola 1Un',1,700);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Zanahorias 1Kg',1,530);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Nectar durazno 1,5L',2,700);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Nectar manzana 1,5L',2,700);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Nectar naranja 1,5L',2,700);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Bebida 3L',2,1500);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Bebida Zero 3L',2,1500);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('TV led 24"',3,60000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('TV led 32"',3,70000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('TV led 50"',3,150000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Tablet 10" 16GB',4,75000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Notebook 16" 500GB',4,100000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Notebook 19.5" 1TB',4,230000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Comedor 4 sillas',5,60000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Comedor 6 sillas',5,80000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Sofa 3 cuerpos cafe',5,100000);
insert into producto (nombre_producto,codigo_tipo,precio_producto) values ('Sofa 2 cuerpos Blanco',5,90000);

insert into ventas (codigo_vendedor,fecha_venta,total_venta) values (1,'2016-10-15',6000);
insert into ventas (codigo_vendedor,fecha_venta,total_venta) values (1,'2016-10-17',10500);
insert into ventas (codigo_vendedor,fecha_venta,total_venta) values (3,'2016-10-18',7000);

insert into detalle_venta (codigo_producto, cantidad, total, codigo_venta) values (1,10,6000,1);
insert into detalle_venta (codigo_producto, cantidad, total, codigo_venta) values (5,15,10500,2);
insert into detalle_venta (codigo_producto, cantidad, total, codigo_venta) values (7,10,7000,3);