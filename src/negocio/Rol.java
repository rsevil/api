package negocio;

public class Rol {
	
	private int idRol;
	private String nombre;
	private String descripcion;
	private boolean activo;
	
	public Rol(int idRol, String nombre, String descripcion, boolean activo) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
	}
	
	public int getIdRol() {
		return idRol;
	}
	
	public void setIdRol(int idRol) {
		this.idRol = idRol;
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

	public boolean getctivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}	
}
