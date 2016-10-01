package negocio;

import java.sql.Date;
import java.util.Vector;

public class ReclamoFacturacion extends Reclamo {
	
	private Vector<DetalleReclamoFacturacion> detalles;
	
	public ReclamoFacturacion(Date fecha, int numReclamo, Cliente cliente,
			String descripcionReclamo, String estado, Date fechaCierre,
			Vector<DetalleReclamoFacturacion> detalles) {
		super(fecha, numReclamo, cliente, descripcionReclamo, estado,
				fechaCierre);
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
