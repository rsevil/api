package negocio;

import persistencia.UsuarioMapper;
import vista.UsuarioView;

public class Usuario {
	private Rol rol;
	private int id;
	private String nombre;
	private String contrasenia;
	
	public Usuario(Rol rol, int id, String nombre, String contrasenia) {
		super();
		this.rol = rol;
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
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
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String password) {
		this.contrasenia = password;
	}
	
	public UsuarioView getView() {
		return new UsuarioView(this.id, this.nombre, this.rol != null ? this.rol.getView() : null);
	}
	
	public void update() {
		UsuarioMapper.getInstancia().update(this);
	}
}
