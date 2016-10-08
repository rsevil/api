package negocio;

import java.sql.Date;

public class ReclamoFaltantes extends Reclamo {
	
	private int cantFaltante;
	private Producto producto;

	public ReclamoFaltantes(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			String estado, 
			Cliente cliente,
			int cantFaltante,
			Producto producto) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.cantFaltante = cantFaltante;
		this.producto = producto;
	}

	public int getCantFaltante() {
		return cantFaltante;
	}
	
	public void setCantFaltante(int cantFaltante) {
		this.cantFaltante = cantFaltante;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}
