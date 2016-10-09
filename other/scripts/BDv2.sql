ALTER TABLE Producto ADD codigoPublicacion varchar(255) NOT NULL

-- Clientes
INSERT INTO [dbo].[Cliente] ([nroCliente],[nombre],[domicilio],[telefono],[mail],[activo]) VALUES
    (1,'Cliente 1','Domicilio 1','1111-1111','cliente1@reclamos.com',1),
	(2,'Cliente 2','Domicilio 2','2222-2222','cliente2@reclamos.com',1),
	(3,'Cliente 3','Domicilio 3','3333-3333','cliente3@reclamos.com',1),
	(4,'Cliente 4','Domicilio 4','4444-4444','cliente4@reclamos.com',1),
	(5,'Cliente 5','Domicilio 5','5555-5555','cliente5@reclamos.com',1)
GO
