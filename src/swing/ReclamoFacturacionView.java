package swing;

import java.text.SimpleDateFormat;
import java.util.Vector;

import negocio.ReclamoFacturacion;
import vista.DetalleReclamoFacturacionView;

public class ReclamoFacturacionView {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

	private int nroReclamo;

	private String fecha;

	private String fechaCierre;

	private String descripcionReclamo;

	private String estado;

	private Vector<DetalleReclamoFacturacionView> detalles;

	private String cliente;

	public ReclamoFacturacionView(final ReclamoFacturacion reclamo) {
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
		this.detalles = new Vector<DetalleReclamoFacturacionView>();
	}

	public Vector<DetalleReclamoFacturacionView> getDetalles() {
		return detalles;
	}

	public void agregarDetalle(DetalleReclamoFacturacionView detalleFacturacion) {
		this.detalles.addElement(detalleFacturacion);
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
}
