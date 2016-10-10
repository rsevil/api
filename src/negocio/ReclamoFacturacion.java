package negocio;

import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import persistencia.ReclamoFacturacionMapper;
import enums.EstadosReclamo;

public class ReclamoFacturacion extends Reclamo {
	
	private Vector<DetalleReclamoFacturacion> detalles;
	
	private ReclamoFacturacion(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			Vector<DetalleReclamoFacturacion> detalles,
			boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.detalles = detalles;
		
		if (persistir)
			ReclamoFacturacionMapper.getInstancia().insert(this);
	} 
	
	public ReclamoFacturacion(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			Vector<DetalleReclamoFacturacion> detalles) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, detalles, false);
	}
	
	public ReclamoFacturacion(
			String descripcionReclamo,
			Cliente cliente){
		this(ReclamoFacturacionMapper.getInstancia().getUltimoId(),
				new Date(Calendar.getInstance().getTimeInMillis()),
				null,
				descripcionReclamo,
				EstadosReclamo.INGRESADO,
				cliente,
				null,
				true);
	}
	
	public Vector<DetalleReclamoFacturacion> getDetalles() {
		return detalles;
	}
	
	public void setDetalles(Vector<DetalleReclamoFacturacion> detalles) {
		this.detalles = detalles;
	}

	public void agregarDetalle(int nroFactura, String detalle) {
		this.detalles.addElement(new DetalleReclamoFacturacion(detalle, nroFactura));
	}
}
