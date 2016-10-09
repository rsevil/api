ALTER TABLE Producto ADD codigoPublicacion varchar(255) NOT NULL

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
