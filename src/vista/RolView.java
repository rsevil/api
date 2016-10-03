package vista;

public class RolView {
	
	private int idRol;
	private String nombre;
	private String descripcion;
	private boolean activo;
	private String vista;
	
	public RolView(int idRol, String nombre, String descripcion, boolean activo, String vista) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.activo = activo;
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
	
	public boolean getctivo() {
		return activo;
	}
	
	public String getVista() {
		return vista;
	}
}
