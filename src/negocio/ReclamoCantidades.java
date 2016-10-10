package negocio;

import java.sql.Date;

import enums.EstadosReclamo;

public class ReclamoCantidades extends Reclamo {
	
	private int cantidad;
	private Producto producto;
	
	private ReclamoCantidades(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente, 
			int cantidad,
			Producto producto, 
			boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.cantidad = cantidad;
		this.producto = producto;
	}
	
	public ReclamoCantidades(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente, 
			int cantidad,
			Producto producto) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, cantidad, producto, false);
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
