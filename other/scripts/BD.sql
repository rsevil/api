-- Crear BD

CREATE DATABASE reclamosBD
COLLATE Modern_Spanish_CI_AS
GO


-- Crear usuario dbo

USE [master]
GO
CREATE LOGIN [reclamosdbo] WITH PASSWORD=N'reclamosdbo', DEFAULT_DATABASE=[reclamosBD], DEFAULT_LANGUAGE=[Español], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF
GO
USE [reclamosBD]
GO
CREATE USER [reclamosdbo] FOR LOGIN [reclamosdbo]
GO
USE [reclamosBD]
GO
ALTER USER [reclamosdbo] WITH DEFAULT_SCHEMA=[dbo]
GO
USE [reclamosBD]
GO
ALTER ROLE [db_owner] ADD MEMBER [reclamosdbo]
GO


-- Crear tablas

USE [reclamosBD]
GO

CREATE TABLE Rol
(
	id int IDENTITY(1,1) NOT NULL,
	nombre varchar(255) NOT NULL,
	descripcion varchar(255),
	activo bit NOT NULL,
	vista varchar(255) NULL,
	CONSTRAINT Rol_PK PRIMARY KEY (id)
)
GO

CREATE TABLE Usuario
(
	id int IDENTITY(1,1) NOT NULL,
	nombre varchar(255) NOT NULL,
	contrasenia varchar(255) NOT NULL,
	activo bit NOT NULL,
	idRol int NULL,
	CONSTRAINT Usuario_PK PRIMARY KEY (id),
	CONSTRAINT Usuario_Rol_FK FOREIGN KEY (idRol) REFERENCES Rol(id)
)
GO

CREATE TABLE Cliente
(
	nroCliente int NOT NULL,
	nombre varchar(255) NOT NULL,
	domicilio varchar(255),
	telefono varchar(255),
	mail varchar(255),
	activo bit NOT NULL,
	CONSTRAINT Cliente_PK PRIMARY KEY (nroCliente)
)
GO

CREATE TABLE Producto
(
	codigoProducto int NOT NULL,
	titulo varchar(255) NOT NULL,
	descripcion varchar(255) NOT NULL,
	precio float NOT NULL,
	activo bit NOT NULL,
	codigoPublicacion varchar(255) NOT NULL,
	CONSTRAINT Producto_PK PRIMARY KEY (codigoProducto)
)
GO

CREATE TABLE Factura
(
	idFactura int NOT NULL,
	nroCliente int NOT NULL,
	fecha datetime NOT NULL,
	activo bit NOT NULL,
	CONSTRAINT Factura_PK PRIMARY KEY (idFactura),
	CONSTRAINT Factura_Cliente_FK FOREIGN KEY (nroCliente) REFERENCES Cliente(nroCliente)
)
GO

CREATE TABLE ItemFactura
(
	idItemFactura int NOT NULL,
	idFactura int NOT NULL,
	codigoProducto int NOT NULL,
	cantidad int NOT NULL,
	precio float NOT NULL,
	activo bit NOT NULL,
	CONSTRAINT ItemFactura_PK PRIMARY KEY (idItemFactura, idFactura),
	CONSTRAINT ItemFactura_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto),
	CONSTRAINT ItemFactura_Factura_FK FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)
)
GO

CREATE TABLE Reclamo
(
	nroReclamo int NOT NULL,
	tipoReclamo varchar(255) NOT NULL,
	fecha date NOT NULL,
	fechaCierre date,
	descripcionReclamo varchar(255),
	estado varchar(255) NOT NULL,
	activo bit NOT NULL DEFAULT 1,
	nroCliente int NOT NULL,
	--ReclamosEspecificos
	codigoProducto int NULL,
	cantidad int NULL,
	cantFaltante int NULL,
	zonaAfectada varchar(255) NULL,
	CONSTRAINT Reclamo_PK PRIMARY KEY (nroReclamo),
	CONSTRAINT Reclamo_Cliente_FK FOREIGN KEY (nroCliente) REFERENCES Cliente(nroCliente),
	CONSTRAINT Reclamo_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto)
)
GO

CREATE TABLE DetalleReclamoFacturacion
(
	id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	detalle varchar(255),
	activo bit NOT NULL,
	nroReclamo int NOT NULL,
	idFactura int NOT NULL,
	CONSTRAINT DetalleReclamoFacturacion_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT DetalleReclamoFacturacion_Factura_FK FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)
)
GO

CREATE TABLE ReclamoCompuestoReclamoSimple
(
	nroReclamoCompuesto int NOT NULL,
	nroReclamo int NOT NULL,
	activo bit NOT NULL DEFAULT 1,
	CONSTRAINT ReclamoCompuestoReclamoSimple_PK PRIMARY KEY (nroReclamoCompuesto, nroReclamo),
	CONSTRAINT ReclamoCompuestoReclamoSimple_ReclamoCompuesto_FK FOREIGN KEY (nroReclamoCompuesto) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT ReclamoCompuestoReclamoSimple_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo)
)
GO

CREATE TABLE ItemReclamoCantidad
(
	nroReclamo int NOT NULL,
	codigoProducto int NOT NULL,
	cantidad int,
	activo bit NOT NULL,
	CONSTRAINT ItemReclamoCantidad_PK PRIMARY KEY (nroReclamo, codigoProducto),
	CONSTRAINT ItemReclamoCantidad_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT ItemReclamoCantidad_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto)
)
GO

CREATE TABLE NovedadReclamo
(
	idNovedadReclamo int NOT NULL IDENTITY(1,1),
	nroReclamo int NOT NULL,
	fecha datetime NOT NULL,
	novedad varchar(255) NOT NULL,
	CONSTRAINT NovedadReclamo_PK PRIMARY KEY (idNovedadReclamo),
	CONSTRAINT NovedadReclamo_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo)
)


-- Datos de prueba

-- Roles
SET IDENTITY_INSERT [dbo].[Rol] ON 
	INSERT [dbo].[Rol] ([id], [nombre], [descripcion], [activo], [vista]) VALUES 
		(1, 'ResponsableFacturacion', 'Responsable facturación', 1, 'swing.MenuPrincipalFacturacion'),
		(2, 'ResponsableDistribucion', 'Responsable distribución', 1, 'swing.MenuPrincipalDistribucion'),
		(3, 'ResponsableZonaEntrega', 'Responsable zona entrega', 1, 'swing.MenuPrincipalZona'),
		(4, 'CallCenter', 'Call center', 1, 'swing.MenuPrincipalCallCenter'),
		(5, 'Administrador', 'Administrador', 1, 'swing.MenuPrincipalAdmin'),
		(6, 'Consulta', 'Consulta', 1, 'swing.MenuPrincipalConsulta')
SET IDENTITY_INSERT [dbo].[Rol] OFF

-- Usuarios
SET IDENTITY_INSERT [dbo].[Usuario] ON 
	INSERT [dbo].[Usuario] ([id], [nombre], [contrasenia], [activo], [idRol]) VALUES 
		(1, 'facturacion', 'facturacion', 1, 1),
		(2, 'distribucion', 'distribucion', 1, 2),
		(3, 'zona', 'zona', 1, 3),
		(4, 'callcenter', 'callcenter', 1, 4),
		(5, 'admin', 'admin', 1, 5),
		(6, 'consulta', 'consulta', 1, 6)
SET IDENTITY_INSERT [dbo].[Usuario] OFF

-- Clientes
INSERT INTO [dbo].[Cliente] ([nroCliente],[nombre],[domicilio],[telefono],[mail],[activo]) VALUES
    (1,'Cliente 1','Domicilio 1','1111-1111','cliente1@reclamos.com',1),
	(2,'Cliente 2','Domicilio 2','2222-2222','cliente2@reclamos.com',1),
	(3,'Cliente 3','Domicilio 3','3333-3333','cliente3@reclamos.com',1),
	(4,'Cliente 4','Domicilio 4','4444-4444','cliente4@reclamos.com',1),
	(5,'Cliente 5','Domicilio 5','5555-5555','cliente5@reclamos.com',1)
GO

-- Productos
INSERT INTO [dbo].[Producto] ([codigoProducto],[titulo],[descripcion],[precio],[activo],[codigoPublicacion]) VALUES
    (1,'Producto 1','Descripción Producto 1',1.00,1,'Prod1'),
	(2,'Producto 2','Descripción Producto 2',2.00,1,'Prod2'),
	(3,'Producto 3','Descripción Producto 3',3.00,1,'Prod3'),
	(4,'Producto 4','Descripción Producto 4',4.00,1,'Prod4'),
	(5,'Producto 5','Descripción Producto 5',5.00,1,'Prod5')
GO

-- Facturas
INSERT INTO [dbo].[Factura] ([idFactura],[nroCliente],[fecha],[activo]) VALUES
	(1,1,'20161009',1),
	(2,2,'20161009',1),
	(3,3,'20161009',1),
	(4,4,'20161009',1),
	(5,5,'20161009',1)
GO

-- Items Facturas
INSERT INTO [dbo].[ItemFactura] ([idItemFactura],[idFactura],[codigoProducto],[cantidad],[precio],[activo]) VALUES
	(1,1,1,1,1.0,1),
	(2,1,2,2,4.0,1),
	(3,2,2,1,2.0,1),
	(4,3,3,1,3.0,1),
	(5,4,4,1,4.0,1),
	(6,5,5,1,5.0,1)
GO
