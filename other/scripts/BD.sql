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

CREATE TABLE Rol
(
	id int NOT NULL,
	nombre varchar(255) NOT NULL,
	descripcion varchar(255),
	activo bit DEFAULT 1,
	CONSTRAINT Rol_PK PRIMARY KEY (id)
)
GO

CREATE TABLE Usuario
(
	id int NOT NULL,
	nombre varchar(255) NOT NULL,
	contrasenia varchar(255),
	activo bit DEFAULT 1,
	idRol int,
	CONSTRAINT Usuario_PK PRIMARY KEY (id)
)
GO

CREATE TABLE Cliente
(
	nroCliente int NOT NULL,
	nombre varchar(255) NOT NULL,
	domicilio varchar(255),
	telefono varchar(255),
	mail varchar(255),
	activo bit DEFAULT 1,
	CONSTRAINT Cliente_PK PRIMARY KEY (nroCliente)
)
GO

CREATE TABLE Producto
(
	codigoProducto int NOT NULL,
	titulo varchar(255) NOT NULL,
	descripcion varchar(255),
	precio float,
	activo bit DEFAULT 1,
	CONSTRAINT Producto_PK PRIMARY KEY (codigoProducto)
)
GO

CREATE TABLE Factura
(
	idFactura int NOT NULL,
	nroCliente int NOT NULL,
	fecha datetime NOT NULL,
	activo bit DEFAULT 1,
	CONSTRAINT Factura_PK PRIMARY KEY (idFactura)
)
GO

CREATE TABLE ItemFactura
(
	idItemFactura int NOT NULL,
	idFactura int NOT NULL,
	codigoProducto int NOT NULL,
	cantidad int NOT NULL,
	precio decimal(18,2) NOT NULL,
	activo bit DEFAULT 1,
	CONSTRAINT ItemFactura_PK PRIMARY KEY (idItemFactura, idFactura)
)
GO

CREATE TABLE Reclamo
(
	nroReclamo int NOT NULL,
	fecha date NOT NULL,
	fechaCierre date,
	descripcionReclamo varchar(255),
	estado varchar(255) NOT NULL,
	activo bit DEFAULT 1,
	nroCliente int NOT NULL,
	codigoProducto int,
	CONSTRAINT Reclamo_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE ReclamoFacturacion
(
	nroReclamo int NOT NULL,
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoFacturacion_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE DetalleReclamoFacturacion
(
	idDetRecFact int NOT NULL,
	detalle varchar(255),
	activo bit DEFAULT 1,
	nroReclamo int NOT NULL,
	idFactura int NOT NULL,
	CONSTRAINT DetalleReclamoFacturacion_PK PRIMARY KEY (idDetRecFact)
)
GO

CREATE TABLE ReclamoProducto
(
	nroReclamo int NOT NULL,
	codigoProducto int NOT NULL,
	cantidad int,
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoProducto_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE ReclamoFaltante
(
	nroReclamo int NOT NULL,
	codigoProducto int NOT NULL,
	cantFaltante int,
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoFaltante_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE ReclamoZona
(
	nroReclamo int NOT NULL,
	zonaAfectada varchar(255),
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoZona_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE ReclamoCantidades
(
	nroReclamo int NOT NULL,
	codigoProducto int NOT NULL,
	cantidad int,
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoCantidades_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE ReclamoCompuesto
(
	nroReclamo int NOT NULL,
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoCompuesto_PK PRIMARY KEY (nroReclamo)
)
GO

CREATE TABLE ReclamoCompuestoReclamoSimple
(
	nroReclamoCompuesto int NOT NULL,
	nroReclamoSimple int NOT NULL,
	activo bit DEFAULT 1,
	CONSTRAINT ReclamoCompuestoReclamoSimple_PK PRIMARY KEY (nroReclamoCompuesto, nroReclamoSimple)
)
GO

-- Foreign keys

ALTER TABLE Usuario ADD
	CONSTRAINT Usuario_Rol_FK FOREIGN KEY (idRol) REFERENCES Rol(id);

ALTER TABLE Factura ADD
	CONSTRAINT Factura_Cliente_FK FOREIGN KEY (nroCliente) REFERENCES Cliente(nroCliente);

ALTER TABLE ItemFactura ADD
	CONSTRAINT ItemFactura_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto),
	CONSTRAINT ItemFactura_Factura_FK FOREIGN KEY (idFactura) REFERENCES Factura(idFactura);

ALTER TABLE DetalleReclamoFacturacion ADD
	CONSTRAINT DetalleReclamoFacturacion_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT DetalleReclamoFacturacion_Factura_FK FOREIGN KEY (idFactura) REFERENCES Factura(idFactura);

ALTER TABLE Reclamo ADD
	CONSTRAINT Reclamo_Cliente_FK FOREIGN KEY (nroCliente) REFERENCES Cliente(nroCliente);

ALTER TABLE ReclamoFacturacion ADD
	CONSTRAINT ReclamoFacturacion_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo);

ALTER TABLE DetalleReclamoFacturacion ADD
	CONSTRAINT DetalleReclamoFacturacion_ReclamoFacturacion_FK FOREIGN KEY (nroReclamo) REFERENCES ReclamoFacturacion(nroReclamo);

ALTER TABLE ReclamoProducto ADD
	CONSTRAINT ReclamoProducto_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT ReclamoProducto_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto);

ALTER TABLE ReclamoFaltante ADD
	CONSTRAINT ReclamoFaltante_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT ReclamoFaltante_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto);

ALTER TABLE ReclamoZona ADD
	CONSTRAINT ReclamoZona_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo);

ALTER TABLE ReclamoCantidades ADD
	CONSTRAINT ReclamoCantidades_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo),
	CONSTRAINT ReclamoCantidades_Producto_FK FOREIGN KEY (codigoProducto) REFERENCES Producto(codigoProducto);

ALTER TABLE ReclamoCompuesto ADD
	CONSTRAINT ReclamoCompuesto_Reclamo_FK FOREIGN KEY (nroReclamo) REFERENCES Reclamo(nroReclamo);

ALTER TABLE ReclamoCompuestoReclamoSimple ADD
	CONSTRAINT ReclamoCompuestoReclamoSimple_Compuesto_Reclamo_FK FOREIGN KEY (nroReclamoCompuesto) REFERENCES ReclamoCompuesto(nroReclamo),
	CONSTRAINT ReclamoCompuestoReclamoSimple_Simple_Reclamo_FK FOREIGN KEY (nroReclamoSimple) REFERENCES Reclamo(nroReclamo);