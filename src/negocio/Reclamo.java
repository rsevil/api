package negocio;

import java.sql.Date;

import enums.EstadosReclamo;

public abstract class Reclamo {
	
	private int nroReclamo;
	private Date fecha;
	private Date fechaCierre;
	private String descripcionReclamo;
	private String estado;
	
	private Cliente cliente;
	
	protected Reclamo(
			int numReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo,
			EstadosReclamo estado,
			Cliente cliente) {
		super();
		this.nroReclamo = numReclamo;
		this.fecha = fecha;
		this.fechaCierre = fechaCierre;
		this.descripcionReclamo = descripcionReclamo;
		this.estado = estado.getTexto();
		this.cliente = cliente;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getNroReclamo() {
		return nroReclamo;
	}
	
	public void setNumReclamo(int numReclamo) {
		this.nroReclamo = numReclamo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getDescripcionReclamo() {
		return descripcionReclamo;
	}
	
	public void setDescripcionReclamo(String descripcionReclamo) {
		this.descripcionReclamo = descripcionReclamo;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Date getFechaCierre() {
		return fechaCierre;
	}
	
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	public boolean sosReclamo(int nroReclamo){
		return (nroReclamo == this.nroReclamo);
	}
}
