package vista;

public class RolView {
	
	private int idRol;
	private String nombre;
	private String descripcion;
	private String vista;
	
	public RolView(int idRol, String nombre, String descripcion, String vista) {
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

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getVista() {
		return vista;
	}
}
