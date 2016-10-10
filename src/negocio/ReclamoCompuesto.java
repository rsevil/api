package negocio;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import enums.EstadosReclamo;
import persistencia.ReclamoCompuestoMapper;


public class ReclamoCompuesto extends Reclamo {
	
	private Vector<Reclamo> reclamos;
	
	private ReclamoCompuesto(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			Vector<Reclamo> reclamos,
			boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.reclamos = reclamos;
		
		if (persistir)
			ReclamoCompuestoMapper.getInstancia().insert(this);
	}
	
	public ReclamoCompuesto(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			Vector<Reclamo> reclamos) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, reclamos, false);
	}
	
	public ReclamoCompuesto(
			String descripcionReclamo,
			Cliente cliente){
		this(ReclamoCompuestoMapper.getInstancia().getUltimoId(),
				new Date(Calendar.getInstance().getTimeInMillis()),
				null,
				descripcionReclamo,
				EstadosReclamo.INGRESADO,
				cliente,
				new Vector<Reclamo>(),
				true);
	}
	
	public Vector<Reclamo> getReclamos() {
		return reclamos;
	}
	
	public void setReclamos(Vector<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}
	
	public void agregarReclamo(Reclamo r) {
		this.reclamos.addElement(r);
		// SOLO SE INSERTA LA RELACION, SE ASUME QUE EL QUE SE AGREGA YA ESTA PERSISTIDO
		ReclamoCompuestoMapper.getInstancia().insertReclamo(this, r);
	}
}
