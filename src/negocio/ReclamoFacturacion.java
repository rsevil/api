package negocio;

import java.sql.Date;
import java.util.Vector;

public class ReclamoFacturacion extends Reclamo {
	
	private Vector<DetalleReclamoFacturacion> detalles;
	
	public ReclamoFacturacion(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			String estado, 
			Cliente cliente,
			Vector<DetalleReclamoFacturacion> detalles) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.detalles = detalles;
	}

	public Vector<DetalleReclamoFacturacion> getDetalles() {
		return detalles;
	}
	
	public void setDetalles(Vector<DetalleReclamoFacturacion> detalles) {
		this.detalles = detalles;
	}

	public void agregarDetalle(int nroFactura) {
	
	}
}
