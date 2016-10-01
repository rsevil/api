package negocio;

import java.sql.Date;

public class ReclamoFaltantes extends Reclamo {
	
	private int cantFaltante;
	private Producto producto;

	public ReclamoFaltantes(Date fecha, int numReclamo, Cliente cliente,
			String descripcionReclamo, String estado, Date fechaCierre,
			int cantFaltante, Producto producto) {
		super(fecha, numReclamo, cliente, descripcionReclamo, estado,
				fechaCierre);
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
