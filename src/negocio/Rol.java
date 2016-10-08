package negocio;

import vista.RolView;

public class Rol {
	
	private int idRol;
	private String nombre;
	private String descripcion;
	private String vista;
	
	public Rol(
			int idRol, 
			String nombre, 
			String descripcion,  
			String vista) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.vista = vista;
	}
	
	public int getIdRol() {
		return idRol;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getVista() {
		return vista;
	}

	public void setVista(String vista) {
		this.vista = vista;
	}

	public RolView getView()
	{
		RolView rolView = new RolView(this.idRol, this.nombre, this.descripcion, this.vista);
		return rolView;
	}
}
