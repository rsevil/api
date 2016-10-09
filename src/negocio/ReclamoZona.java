package negocio;

import java.sql.Date;
import java.util.Calendar;

import enums.EstadosReclamo;
import persistencia.ReclamoZonaMapper;

public class ReclamoZona extends Reclamo {
	
	private String zonaAfectada;

	private ReclamoZona(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			String zonaAfectada,
			boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.zonaAfectada = zonaAfectada;
		
		if (persistir)
			ReclamoZonaMapper.getInstancia().insert(this);
	} 
	
	public ReclamoZona(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			String zonaAfectada) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, zonaAfectada, false);
	}
	
	public ReclamoZona(
			String descripcionReclamo,
			Cliente cliente,
			String zonaAfectada){
		this(ReclamoZonaMapper.getInstancia().getUltimoId(),
				new Date(Calendar.getInstance().getTimeInMillis()),
				null,
				descripcionReclamo,
				EstadosReclamo.INGRESADO,
				cliente,
				zonaAfectada,
				true);
	}

	public String getZonaAfectada() {
		return zonaAfectada;
	}
	
	public void setZonaAfectada(String zonaAfectada) {
		this.zonaAfectada = zonaAfectada;
	}
}
