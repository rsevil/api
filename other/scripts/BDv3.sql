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

