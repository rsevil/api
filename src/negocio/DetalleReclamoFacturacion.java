package negocio;

public class DetalleReclamoFacturacion {
	
	private String detalle;
	private String numFactura;
	
	public DetalleReclamoFacturacion(String detalle, String numFactura) {
		super();
		this.detalle = detalle;
		this.numFactura = numFactura;
	}
	
	public String getDetalle() {
		return detalle;
	}
	
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getNumFactura() {
		return numFactura;
	}
	
	public void setNumFactura(String numFactura) {
		this.numFactura = numFactura;
	}
}
