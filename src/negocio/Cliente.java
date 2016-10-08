package negocio;

import persistencia.ClienteMapper;

public class Cliente {
	
	private int nroCliente;
	private String nombre;
	private String domicilio;
	private String telefono;
	private String mail;
	
	public Cliente(
			int nroCliente, 
			String nombre, 
			String domicilio, 
			String telefono, 
			String mail) {
		super();
		this.nroCliente = nroCliente;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
		ClienteMapper.getInstancia().insert(this);
	}
	
	public int getNroCliente() {
		return nroCliente;
	}
	
	public void setNroCliente(int nroCliente) {
		this.nroCliente = nroCliente;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean sosCliente(int nroCliente){
		return (nroCliente == this.nroCliente);
	}
}
