package negocio;

import java.sql.Date;
import java.util.Vector;


public class ReclamoCompuesto extends Reclamo {
	
	private Vector<Reclamo> reclamos;
	
	public ReclamoCompuesto(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			String estado, 
			Cliente cliente,
			Vector<Reclamo> reclamos) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
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
