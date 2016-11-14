package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.Vector;
import java.util.function.Function;

import enums.ExitCodes;
import interfaces.SistemaFacturacion;
import negocio.Cliente;
import negocio.NovedadReclamo;
import negocio.Producto;
import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoCompuesto;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;
import negocio.ReclamoZona;
import negocio.Reporte;
import negocio.Rol;
import negocio.TableroFacturacion;
import negocio.TableroZona;
import negocio.Usuario;
import persistencia.ClienteMapper;
import persistencia.ProductoMapper;
import persistencia.ReclamoCantidadesMapper;
import persistencia.ReclamoCompuestoMapper;
import persistencia.ReclamoFacturacionMapper;
import persistencia.ReclamoMapper;
import persistencia.RolMapper;
import persistencia.UsuarioMapper;
import utils.Func;
import vista.ReclamoFacturacionView;
import vista.ReclamoZonaView;
import vista.ReporteView;
import vista.RolView;
import vista.UsuarioView;

public class SistemaAdministracionReclamos {
	private static SistemaAdministracionReclamos instance;
	private Vector<Cliente> clientes = new Vector<Cliente>();
	private Vector<Producto> productos = new Vector<Producto>();
	private Vector<Reclamo> reclamos = new Vector<Reclamo>();
	private Usuario usuarioLogueado;

	private SistemaAdministracionReclamos() {
	}

	public static SistemaAdministracionReclamos getInstancia() {
		if (instance == null)
			instance = new SistemaAdministracionReclamos();

		return instance;
	}

	public int iniciarSesion(String nombreUsuario, String contrasenia) {
		Usuario usuario = UsuarioMapper.getInstancia().selectOne(nombreUsuario);

		if (usuario == null || !usuario.getContrasenia().equals(contrasenia))
			return ExitCodes.DATOS_INGRESO_INCORRECTOS;

		this.usuarioLogueado = usuario;
		return ExitCodes.OK;
	}

	public RolView obtenerRolDelUsuarioLogueado() {
		if (this.usuarioLogueado == null)
			return null;

		return this.usuarioLogueado.getRol().getView();
	}

	public Vector<UsuarioView> listarUsuarios() {
		Vector<UsuarioView> usuariosView = new Vector<UsuarioView>();
		Vector<Usuario> usuarios = UsuarioMapper.getInstancia().selectAll();

		for (Usuario u : usuarios)
			usuariosView.add(u.getView());

		return usuariosView;
	}

	public Vector<RolView> listarRoles() {
		Vector<RolView> rolesView = new Vector<RolView>();
		Vector<Rol> roles = RolMapper.getInstancia().selectAll();

		for (Rol r : roles)
			rolesView.add(r.getView());

		return rolesView;
	}

	public int asignarRol(int idUsuario, int idRol) {
		Usuario usuario = UsuarioMapper.getInstancia().selectById(idUsuario);

		if (usuario == null)
			return ExitCodes.ERROR_GENERICO;

		Rol rol = RolMapper.getInstancia().selectOne(idRol);

		if (rol != null) {
			usuario.setRol(rol);
			usuario.update();
		}

		return ExitCodes.OK;
	}

	public void addObserver(Observer o) {

	}

	public void removeObserver(Observer o) {

	}

	public void notifyAll(Usuario u) {

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

	public int registrarReclamoFaltante(int nroCliente, String descripcion, int codProducto, int cantFaltante,
			int numFactura) {
		Cliente cliente = this.buscarCliente(nroCliente);

		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;

		Producto producto = this.buscarProducto(codProducto);

		if (producto == null)
			return ExitCodes.NO_EXISTE_PRODUCTO;

		if (!SistemaFacturacion.getInstancia().Facture(numFactura, producto, cantFaltante))
			return ExitCodes.FALLA_RECLAMO_PRODUCTO_FALTANTE;

		ReclamoFaltantes r = new ReclamoFaltantes(descripcion, cliente, cantFaltante, producto);

		this.reclamos.add(r);
		return r.getNroReclamo();
	}

	public int registrarReclamoZona(int nroCliente, String descripcion, String zona) {
		Cliente cliente = this.buscarCliente(nroCliente);

		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;

		ReclamoZona r = new ReclamoZona(descripcion, cliente, zona);

		this.reclamos.add(r);
		return r.getNroReclamo();
	}

	public int registrarReclamoCantidades(int nroCliente, String descripcion) {
		Cliente cliente = this.buscarCliente(nroCliente);

		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;

		ReclamoCantidades r = new ReclamoCantidades(descripcion, cliente);

		return r.getNroReclamo();
	}

	public int agregarProductoReclamoCantidades(int nroReclamo, int codProducto, int cant) {
		ReclamoCantidades reclamo = buscarReclamoCantidades(nroReclamo);

		if (reclamo == null)
			return ExitCodes.NO_EXISTE_RECLAMO;

		Producto producto = buscarProducto(codProducto);

		if (producto == null)
			return ExitCodes.NO_EXISTE_PRODUCTO;

		reclamo.agregarProducto(producto, cant);

		return ExitCodes.OK;
	}

	public int registrarReclamoFacturacion(int nroCliente, String descripcion) {
		Cliente cliente = this.buscarCliente(nroCliente);

		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;

		ReclamoFacturacion r = new ReclamoFacturacion(descripcion, cliente);

		this.reclamos.add(r);
		return r.getNroReclamo();
	}

	public int agregarDetalleReclamoFacturacion(int nroReclamo, String detalle, int nroFactura) {
		ReclamoFacturacion reclamo = buscarReclamoFacturacion(nroReclamo);

		if (reclamo == null)
			return ExitCodes.NO_EXISTE_RECLAMO;

		if (!SistemaFacturacion.getInstancia().facturaEsDeEsteCliente(nroFactura, reclamo.getCliente().getNroCliente()))
			return ExitCodes.FALLA_RECLAMO_DETALLE_FACTURACION;

		reclamo.agregarDetalle(nroFactura, detalle);

		return ExitCodes.OK;
	}

	public int registrarReclamoCompuesto(int nroCliente, String descripcion) {
		Cliente cliente = this.buscarCliente(nroCliente);

		if (cliente == null)
			return ExitCodes.NO_EXISTE_CLIENTE;

		ReclamoCompuesto r = new ReclamoCompuesto(descripcion, cliente);

		this.reclamos.add(r);
		return r.getNroReclamo();
	}

	public int agregarReclamoReclamoCompuesto(int nroReclamoCompuesto, int nroReclamo) {
		ReclamoCompuesto reclamoCompuesto = this.buscarReclamoCompuesto(nroReclamoCompuesto);

		if (reclamoCompuesto == null)
			return ExitCodes.NO_EXISTE_RECLAMO;

		Reclamo reclamo = this.buscarReclamo(nroReclamo, () -> ReclamoMapper.getInstancia().selectOne(nroReclamo));

		if (reclamo == null)
			return ExitCodes.NO_EXISTE_RECLAMO;

		reclamoCompuesto.agregarReclamo(reclamo);

		return ExitCodes.OK;
	}

	public Vector<ReclamoFacturacionView> obtenerReclamosFacturacionTratamiento() {
		return TableroFacturacion.getInstance().getReclamos();
	}
	
	public Vector<ReclamoZonaView> obtenerReclamosZonasTratamiento() {
		return TableroZona.getInstance().getReclamos();
	}
	
	public boolean updateReclamo(int numeroReclamo, String estado, String novedad) {
		try {
			ReclamoMapper.getInstancia().updateEstado(numeroReclamo, estado);
			ReclamoMapper.getInstancia().addNovedad(
					new NovedadReclamo(numeroReclamo, new java.sql.Date(System.currentTimeMillis()), novedad));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean cerrarReclamo(int numeroReclamo, String estado, String novedad) {
		try {
			ReclamoMapper.getInstancia().cerrarReclamo(numeroReclamo, estado, new java.sql.Date(System.currentTimeMillis()));
			ReclamoMapper.getInstancia().addNovedad(
					new NovedadReclamo(numeroReclamo, new java.sql.Date(System.currentTimeMillis()), novedad));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private ReclamoCompuesto buscarReclamoCompuesto(int nroReclamoCompuesto) {
		return this.buscarReclamo(nroReclamoCompuesto,
				() -> ReclamoCompuestoMapper.getInstancia().selectOne(nroReclamoCompuesto));
	}

	private <T> T buscar(Vector<T> cache, Function<T, Boolean> predicate, Func<T> getter) {
		for (T c : cache) {
			if (predicate.apply(c)) {
				return c;
			}
		}

		T o = null;

		try {
			o = getter.apply();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		if (o != null)
			cache.add(o);

		return o;
	}

	private Cliente buscarCliente(int nroCliente) {
		return buscar(clientes, c -> c.sosCliente(nroCliente),
				() -> ClienteMapper.getInstancia().selectOne(nroCliente));
	}

	private Producto buscarProducto(int codProducto) {
		return buscar(productos, p -> p.sosProducto(codProducto),
				() -> ProductoMapper.getInstancia().selectOne(codProducto));
	}

	@SuppressWarnings("unchecked")
	private <TReclamo extends Reclamo> TReclamo buscarReclamo(int nroReclamo, Func<TReclamo> getter) {
		return (TReclamo) buscar(reclamos, r -> r.sosReclamo(nroReclamo), () -> getter.apply());
	}

	private ReclamoFacturacion buscarReclamoFacturacion(int nroReclamo) {
		return buscarReclamo(nroReclamo, () -> ReclamoFacturacionMapper.getInstancia().selectOne(nroReclamo));
	}

	private ReclamoCantidades buscarReclamoCantidades(int nroReclamo) {
		return buscarReclamo(nroReclamo, () -> ReclamoCantidadesMapper.getInstancia().selectOne(nroReclamo));
	}

	/* Reportes */

	public Vector<ReporteView> obtenerReporteRankingClientes() {
		Vector<Reporte> itemsReporte = ReclamoMapper.getInstancia().getReporteRankingClientes();
		Vector<ReporteView> itemsReporteView = new Vector<ReporteView>();
		for (Reporte r : itemsReporte) {
			itemsReporteView.addElement(r.getView());
		}
		return itemsReporteView;
	}

	public Vector<ReporteView> obtenerReporteReclamosTratadosMesAnio() {
		Vector<Reporte> itemsReporte = ReclamoMapper.getInstancia().getReporteReclamosTratadosMesAnio();
		Vector<ReporteView> itemsReporteView = new Vector<ReporteView>();
		for (Reporte r : itemsReporte) {
			itemsReporteView.addElement(r.getView());
		}
		return itemsReporteView;
	}

	public Vector<ReporteView> obtenerReporteRankingTratamientoReclamos(Date fechaDesde, Date fechaHasta) {
		Vector<Reporte> itemsReporte = ReclamoMapper.getInstancia().getReporteRankingTratamientoReclamos(fechaDesde,
				fechaHasta);
		Vector<ReporteView> itemsReporteView = new Vector<ReporteView>();
		for (Reporte r : itemsReporte) {
			itemsReporteView.addElement(r.getView());
		}
		return itemsReporteView;
	}

	public Vector<ReporteView> obtenerReporteTiempoPromedioRespuesta(Date fechaDesde, Date fechaHasta) {
		Vector<Reporte> itemsReporte = ReclamoMapper.getInstancia().getReporteTiempoPromedioRespuesta(fechaDesde,
				fechaHasta);
		Vector<ReporteView> itemsReporteView = new Vector<ReporteView>();
		for (Reporte r : itemsReporte) {
			itemsReporteView.addElement(r.getView());
		}
		return itemsReporteView;
	}
	
	public void borrarReclamos(ArrayList<Integer> ids){
		ReclamoMapper.getInstancia().borrarReclamos(ids);
	}
}
