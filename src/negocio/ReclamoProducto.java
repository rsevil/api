package negocio;

import java.sql.Date;
import java.util.Calendar;

import persistencia.ReclamoProductoMapper;
import enums.EstadosReclamo;

public class ReclamoProducto extends Reclamo {
	
	private int cantidad;
	private Producto producto;
	
	private ReclamoProducto(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente, 
			int cantidad,
			Producto producto,
			boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.cantidad = cantidad;
		this.producto = producto;
		
		if (persistir)
			ReclamoProductoMapper.getInstancia().insert(this);
	}
	
	public ReclamoProducto(
			int nroReclamo, 
			Date fecha, 
			Date fechaCierre,
			String descripcionReclamo, 
			EstadosReclamo estado, 
			Cliente cliente,
			int cantidad,
			Producto producto) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, cantidad, producto, false);
	}
	
	public ReclamoProducto(
			String descripcionReclamo,
			Cliente cliente,
			int cantidad,
			Producto producto){
		this(ReclamoProductoMapper.getInstancia().getUltimoId(),
				new Date(Calendar.getInstance().getTimeInMillis()),
				null,
				descripcionReclamo,
				EstadosReclamo.INGRESADO,
				cliente,
				cantidad,
				producto,
				true);
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
