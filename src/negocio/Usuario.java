package negocio;

public class Usuario {
	private Rol rol;
	private int id;
	private String nombre;
	private String password;
	private boolean activo;
	
	public Usuario(Rol rol, int id, String nombre, String password, boolean activo) {
		super();
		this.rol = rol;
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.activo = activo;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}	
}
