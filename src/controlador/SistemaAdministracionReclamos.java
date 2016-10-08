package controlador;

import java.util.Observer;
import java.util.Vector;

import enums.ExitCodes;
import negocio.*;
import persistencia.*;
import vista.*;


public class SistemaAdministracionReclamos {
	private static SistemaAdministracionReclamos instance;
	private Vector<Cliente> clientes;
	private Vector<Producto> productos;
	private Vector<Reclamo> reclamos;
	private Usuario usuarioLogueado;
	
	private SistemaAdministracionReclamos() {
	
	}
	
	public static SistemaAdministracionReclamos getInstancia()
	{
		if (instance == null) {
			instance = new SistemaAdministracionReclamos();
		}
		return instance;
	}
	
	public int iniciarSesion(String nombreUsuario, String contrasenia) {
		Usuario usuario = UsuarioMapper.getInstancia().selectOne(nombreUsuario);
		
		if (usuario == null) {
			return ExitCodes.DATOS_INGRESO_INCORRECTOS;
		}
		if (!usuario.getContrasenia().equals(contrasenia)){
			return ExitCodes.DATOS_INGRESO_INCORRECTOS;
		}
		
		this.usuarioLogueado = usuario;
		return ExitCodes.OK;
	}
	
	public RolView obtenerRolDelUsuarioLogueado() {
		if (this.usuarioLogueado != null) {
			return this.usuarioLogueado.getRol().getView();
		}
		else {
			return null;
		}
	}
	
	public Vector<UsuarioView> listarUsuarios() {
		Vector<UsuarioView> usuariosView = new Vector<UsuarioView>();
		Vector<Usuario> usuarios = UsuarioMapper.getInstancia().selectAll();
		
		if (usuarios != null && usuarios.size() > 0){
			for (Usuario u : usuarios)
			{
				usuariosView.add(u.getView());
			}
		}
		
		return usuariosView;
	}
	
	public Vector<RolView> listarRoles() {
		Vector<RolView> rolesView = new Vector<RolView>();
		Vector<Rol> roles = RolMapper.getInstancia().selectAll();
		
		if (roles != null && roles.size() > 0){
			for (Rol r : roles)
			{
				rolesView.add(r.getView());
			}
		}
		
		return rolesView;
	}
	
	public int asignarRol(int idUsuario, int idRol) {
		Usuario usuario = UsuarioMapper.getInstancia().selectById(idUsuario);
		
		if (usuario != null) {
			Rol rol = RolMapper.getInstancia().selectOne(idRol);
			
			if (rol != null) {
				usuario.setRol(rol);
				usuario.update();
			}
			
			return ExitCodes.OK;
		}
		
		return ExitCodes.ERROR_GENERICO;
	}
	
	public void addObserver(Observer o) {

	}
	
	public void removeObserver(Observer o) {

	}
	
	public void notifyAll(Usuario u) {
		
	}
	
	private Cliente buscarCliente(int nroCliente) {
		Cliente clienteBuscado = null;		
		for (Cliente c : clientes){
			if(c.sosCliente(nroCliente)){
				clienteBuscado = c;
			}
		}		
		if(clienteBuscado == null){
			clienteBuscado = ClienteMapper.getInstancia().selectOne(nroCliente);
			if(clienteBuscado != null){
				clientes.add(clienteBuscado);
			}
		}
		return clienteBuscado;		
	}
	
	private Producto buscarProducto(int codProducto) {
		return new Producto(null, null, null, codProducto);
	}
	
	public int registrarReclamoProducto(int nroCliente, String descripcion, int codProducto, int cant) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return 0;
	}
	
	public int registrarReclamoFaltante(int nroCliente, String descripcion, int codProducto, int cantFaltante) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return 0;
	}
	
	public int registrarReclamoZona(int nroCliente, String descripcion, String zona) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return 0;
	}
	
	public int registrarReclamoCantidades(int nroCliente, String descripcion) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return 0;
	}
	
	public int registrarReclamoFacturacion(int nroCliente) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return 0;
	}
	
	private Reclamo buscarReclamo(int nroReclamo) {
		return null;
	}
}
