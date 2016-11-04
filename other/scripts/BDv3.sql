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
