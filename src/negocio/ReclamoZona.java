package negocio;

import java.sql.Date;

public class ReclamoZona extends Reclamo {
	
	private String zonaAfectada;

	public ReclamoZona(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			String estado, 
			Cliente cliente,
			String zonaAfectada) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.zonaAfectada = zonaAfectada;
	}

	public String getZonaAfectada() {
		return zonaAfectada;
	}
	
	public void setZonaAfectada(String zonaAfectada) {
		this.zonaAfectada = zonaAfectada;
	}
}
