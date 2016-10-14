package vista;

public class DetalleReclamoFacturacionView {

	private String detalle;
	private String idFactura;
	private int numeroDetalle;

	public DetalleReclamoFacturacionView(String detalle, String idFactura, int numeroDetalle) {
		super();
		this.detalle = detalle;
		this.idFactura = idFactura;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public int getNumeroDetalle() {
		return numeroDetalle;
	}

	public void setNumeroDetalle(int numeroDetalle) {
		this.numeroDetalle = numeroDetalle;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
}
