package controlador;

import java.util.Observer;
import java.util.Vector;

import enums.ExitCodes;
import negocio.*;
import persistencia.*;


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
		
		if (usuario != null) {
			if (usuario.getPassword().equals(contrasenia)) {
				this.usuarioLogueado = usuario;
				return ExitCodes.OK;
			}
			else {
				return ExitCodes.DATOS_INGRESO_INCORRECTOS;
			}
		}
		else {
			return ExitCodes.DATOS_INGRESO_INCORRECTOS;
		}
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
