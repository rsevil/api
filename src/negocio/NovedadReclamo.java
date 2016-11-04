package negocio;

import java.sql.Date;

public class NovedadReclamo {

	private int nroReclamo;

	private Date fecha;

	private String novedad;

	public NovedadReclamo(int nroReclamo, Date fecha, String novedad) {
		super();
		this.nroReclamo = nroReclamo;
		this.fecha = fecha;
		this.novedad = novedad;
	}

	public int getNroReclamo() {
		return nroReclamo;
	}

	public void setNroReclamo(int nroReclamo) {
		this.nroReclamo = nroReclamo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNovedad() {
		return novedad;
	}

	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}
}
