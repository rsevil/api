package negocio;

import java.sql.Date;

public class ReclamoCantidades extends Reclamo {
	
	private int cantidad;
	private Producto producto;
		
	public ReclamoCantidades(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			String estado, 
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
