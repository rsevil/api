package negocio;

import java.sql.Date;

public abstract class Reclamo {
	
	private Date fecha;
	private int numReclamo;
	private Cliente cliente;
	private String descripcionReclamo;
	private String estado;
	private Date fechaCierre;
	
	public Reclamo(Date fecha, int numReclamo, Cliente cliente,
			String descripcionReclamo, String estado, Date fechaCierre) {
		super();
		this.fecha = fecha;
		this.numReclamo = numReclamo;
		this.cliente = cliente;
		this.descripcionReclamo = descripcionReclamo;
		this.estado = estado;
		this.fechaCierre = fechaCierre;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getNumReclamo() {
		return numReclamo;
	}
	
	public void setNumReclamo(int numReclamo) {
		this.numReclamo = numReclamo;
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
}
