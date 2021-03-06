package negocio;

import vista.DetalleReclamoFacturacionView;

public class DetalleReclamoFacturacion {
	
	private String detalle;
	private int idFactura;
	
	public DetalleReclamoFacturacion(
			String detalle, 
			int idFactura) {
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
	
	public int getNumFactura() {
		return idFactura;
	}
	
	public void setNumFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	
	public DetalleReclamoFacturacionView getView(){
		return new DetalleReclamoFacturacionView(this.detalle, String.valueOf(idFactura), 0);
	}
}
