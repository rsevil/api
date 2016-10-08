package vista;

public class RolView {
	
	private int id;
	private String nombre;
	private String descripcion;
	private String vista;
	
	public RolView(int id, String nombre, String descripcion, String vista) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.vista = vista;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getVista() {
		return this.vista;
	}
}
