-- Crear BD

CREATE DATABASE reclamosBD
COLLATE Modern_Spanish_CI_AS
GO

-- Crear usuario
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
	CONSTRAINT Reclamo_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
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