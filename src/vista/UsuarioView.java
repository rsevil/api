package vista;

public class UsuarioView {

	private int id;
	private String nombre;
	private RolView rolView;
	
	public UsuarioView(int id, String nombre, RolView rolView) {
		this.id = id;
		this.nombre = nombre;
		this.rolView = rolView;
	}
	
	public RolView getRolView() {
		return rolView;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}
