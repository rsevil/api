package vista;

import java.text.SimpleDateFormat;
import java.util.Vector;

import negocio.ItemReclamoCantidad;
import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;

public class ReclamoDistribucionView {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy-HH:mm");

	private int nroReclamo;

	private String fecha;

	private String fechaCierre;

	private String descripcionReclamo;

	private String estado;

	private String cliente;

	/**
	 * TODO
	 */
	private Vector<DetalleReclamoFacturacionView> detalles;

	private Vector<ItemReclamoCantidad> items;

	/**
	 * Productos
	 */

	private int cantidadProductos;

	private String producto;

	/**
	 * Faltantes
	 */

	private int cantidadFaltante;

	private String productoFaltante;

	public ReclamoDistribucionView(final ReclamoCantidades reclamo) {
		// Cargar Items de Cantidades
		this((Reclamo) reclamo);
	}

	public ReclamoDistribucionView(final ReclamoProducto reclamo) {
		this((Reclamo) reclamo);
		this.cantidadProductos = reclamo.getCantidad();
		this.producto = reclamo.getProducto().getTitulo();
	}

	public ReclamoDistribucionView(final ReclamoFaltantes reclamo) {
		this((Reclamo) reclamo);
		this.cantidadFaltante = reclamo.getCantFaltante();
		this.productoFaltante = reclamo.getProducto().getTitulo();
	}

	public ReclamoDistribucionView(final Reclamo reclamo) {
		this.nroReclamo = reclamo.getNroReclamo();
		if (reclamo.getFecha() != null) {
			this.fecha = FORMAT.format(reclamo.getFecha());
		}
		if (reclamo.getFechaCierre() != null) {
			this.fechaCierre = FORMAT.format(reclamo.getFechaCierre());
		}
		this.descripcionReclamo = reclamo.getDescripcionReclamo();
		this.estado = reclamo.getEstado();
		this.cliente = reclamo.getCliente().getNombre();
	}

	public int getNroReclamo() {
		return nroReclamo;
	}

	public String getFecha() {
		return fecha;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public String getDescripcionReclamo() {
		return descripcionReclamo;
	}

	public String getEstado() {
		return estado;
	}

	public String getCliente() {
		return cliente;
	}

	public static SimpleDateFormat getFormat() {
		return FORMAT;
	}

	public Vector<DetalleReclamoFacturacionView> getDetalles() {
		return detalles;
	}

	public Vector<ItemReclamoCantidad> getItems() {
		return items;
	}

	public int getCantidadProductos() {
		return cantidadProductos;
	}

	public String getProducto() {
		return producto;
	}

	public int getCantidadFaltante() {
		return cantidadFaltante;
	}

	public String getProductoFaltante() {
		return productoFaltante;
	}
}
