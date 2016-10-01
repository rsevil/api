package negocio;

import java.sql.Date;

public class ReclamoCantidades extends Reclamo {
	
	private Producto producto;
	private int cantidad;
		
	public ReclamoCantidades(Date fecha, int numReclamo, Cliente cliente,
			String descripcionReclamo, String estado, Date fechaCierre,
			Producto producto, int cantidad) {
		super(fecha, numReclamo, cliente, descripcionReclamo, estado,
				fechaCierre);
		this.producto = producto;
		this.cantidad = cantidad;
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
