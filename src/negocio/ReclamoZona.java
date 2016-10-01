package negocio;

import java.sql.Date;

public class ReclamoZona extends Reclamo {
	
	private String zonaAfectada;

	public ReclamoZona(Date fecha, int numReclamo, Cliente cliente,
			String descripcionReclamo, String estado, Date fechaCierre,
			String zonaAfectada) {
		super(fecha, numReclamo, cliente, descripcionReclamo, estado,
				fechaCierre);
		this.zonaAfectada = zonaAfectada;
	}

	public String getZonaAfectada() {
		return zonaAfectada;
	}
	
	public void setZonaAfectada(String zonaAfectada) {
		this.zonaAfectada = zonaAfectada;
	}
}
