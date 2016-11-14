package vista;

import java.text.SimpleDateFormat;

import negocio.ReclamoZona;

public class ReclamoZonaView {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

	private int nroReclamo;

	private String fecha;

	private String fechaCierre;

	private String descripcionReclamo;

	private String estado;

	private String cliente;

	private String zona;

	public ReclamoZonaView(final ReclamoZona reclamo) {
		this.nroReclamo = reclamo.getNroReclamo();
		if (reclamo.getFecha() != null) {
			this.fecha = FORMAT.format(reclamo.getFecha());
		}
		if (reclamo.getFechaCierre() != null) {
			this.fechaCierre = FORMAT.format(reclamo.getFechaCierre());
		}
		this.descripcionReclamo = reclamo.getDescripcionReclamo();
		this.estado = reclamo.getEstado();
		this.cliente = reclamo.getCliente().getNombre();
		this.zona = reclamo.getZonaAfectada();
	}

	public int getNroReclamo() {
		return nroReclamo;
	}

	public String getFecha() {
		return fecha;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public String getDescripcionReclamo() {
		return descripcionReclamo;
	}

	public String getEstado() {
		return estado;
	}

	public String getCliente() {
		return cliente;
	}

	public String getZona() {
		return zona;
	}
}
