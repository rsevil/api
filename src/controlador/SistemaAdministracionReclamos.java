package controlador;

import java.util.Observer;
import java.util.Vector;
import java.util.function.Function;

import enums.ExitCodes;
import interfaces.SistemaFacturacion;
import negocio.*;
import persistencia.*;
import utils.Func;
import vista.*;


public class SistemaAdministracionReclamos {
	private static SistemaAdministracionReclamos instance;
	private Vector<Cliente> clientes = new Vector<Cliente>();
	private Vector<Producto> productos = new Vector<Producto>();
	private Vector<Reclamo> reclamos = new Vector<Reclamo>();
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
		return buscar(
				clientes,
				c -> c.sosCliente(nroCliente),
				() -> ClienteMapper.getInstancia().selectOne(nroCliente));
	}
	
	private Producto buscarProducto(int codProducto) {
		return buscar(
				productos,
				p -> p.sosProducto(codProducto), 
				() -> ProductoMapper.getInstancia().selectOne(codProducto));
	}
	
	
	private <T> T buscar(Vector<T> cache, Function<T, Boolean> predicate, Func<T> getter) {
		for (T c : cache){
			if (predicate.apply(c)){
				return c;
			}
		}
		
		T o = null;
		
		try{
			o = getter.apply();
		}catch(Exception ex){
			System.out.println(ex.toString());
		}
		
		if(o != null){
			cache.add(o);
		}
		
		return o;
	}
	
	public int registrarReclamoProducto(int nroCliente, String descripcion, int codProducto, int cant) {
		Cliente cliente = this.buscarCliente(nroCliente);
		
		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;
		
		Producto producto = this.buscarProducto(codProducto);
		
		if (producto == null)
			return ExitCodes.NO_EXISTE_PRODUCTO;	
		
		ReclamoProducto r = new ReclamoProducto(descripcion, cliente, cant, producto);
		
		this.reclamos.add(r);
		return r.getNroReclamo();
	}
	
	public int registrarReclamoFaltante(int nroCliente, String descripcion, int codProducto, int cantFaltante, int numFactura) {
		Cliente cliente = this.buscarCliente(nroCliente);
		
		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;
		
		Producto producto = this.buscarProducto(codProducto);
		
		if (producto == null)
			return ExitCodes.NO_EXISTE_PRODUCTO;		
		
		if (!SistemaFacturacion.getInstancia().Facture(numFactura, producto, cantFaltante))
			return ExitCodes.FALLA_RECLAMO_PRODUCTO_FALTANTE;
					
		ReclamoFaltantes r = new ReclamoFaltantes(descripcion,cliente,cantFaltante,producto);
		
		this.reclamos.add(r);
		return r.getNroReclamo();
	}
	
	public int registrarReclamoZona(int nroCliente, String descripcion, String zona) {
		Cliente cliente = this.buscarCliente(nroCliente);
		
		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;
		
		ReclamoZona r = new ReclamoZona(descripcion,cliente, zona);
		
		this.reclamos.add(r);
		return r.getNroReclamo();
	}
	
	public int registrarReclamoCantidades(int nroCliente, String descripcion) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return ExitCodes.OK;
	}
	
	public int registrarReclamoFacturacion(int nroCliente) {
		Cliente cliente = this.buscarCliente(nroCliente);
		return ExitCodes.OK;
	}
	
	private Reclamo buscarReclamo(int nroReclamo) {
		return null;
	}
}
