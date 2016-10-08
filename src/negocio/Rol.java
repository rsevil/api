package negocio;

import vista.RolView;

public class Rol {
	
	private int id;
	private String nombre;
	private String descripcion;
	private String vista;
	
	public Rol(int id, String nombre, String descripcion, String vista) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.vista = vista;
	}
	
	public int getId() {
		return id;
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
		return new RolView(this.id, this.nombre, this.descripcion, this.vista);
	}
}
