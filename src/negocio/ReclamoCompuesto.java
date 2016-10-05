package negocio;

import java.sql.Date;
import java.util.Vector;


public class ReclamoCompuesto extends Reclamo {
	
	private Vector<Reclamo> reclamos;
	
	public ReclamoCompuesto(
			Date fecha, 
			int numReclamo, 
			Cliente cliente,
			String descripcionReclamo, 
			String estado, 
			Date fechaCierre,
			Vector<Reclamo> reclamos, 
			boolean activo) {
		super(fecha, numReclamo, cliente, descripcionReclamo, estado,fechaCierre, activo);
		this.reclamos = reclamos;
	}
	
	public Vector<Reclamo> getReclamos() {
		return reclamos;
	}
	
	public void setReclamos(Vector<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}
	
	public void agregarReclamo(Reclamo r) {
	
	}
}
