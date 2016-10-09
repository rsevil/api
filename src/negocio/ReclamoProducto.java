package negocio;

import java.sql.Date;

import enums.EstadosReclamo;

public class ReclamoProducto extends Reclamo {
	
	private int cantidad;
	private Producto producto;
	
	public ReclamoProducto(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente, 
			int cantidad,
			Producto producto) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.cantidad = cantidad;
		this.producto = producto;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
