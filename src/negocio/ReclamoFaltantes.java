package negocio;

import java.sql.Date;
import java.util.Calendar;

import enums.EstadosReclamo;
import persistencia.ReclamoFaltanteMapper;
import vista.ReclamoDistribucionView;

public class ReclamoFaltantes extends Reclamo {

	private int cantFaltante;
	private Producto producto;

	private ReclamoFaltantes(int nroReclamo, Date fecha, Date fechaCierre, String descripcionReclamo,
			EstadosReclamo estado, Cliente cliente, int cantFaltante, Producto producto, boolean persistir) {
		super(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente);
		this.cantFaltante = cantFaltante;
		this.producto = producto;

		if (persistir)
			ReclamoFaltanteMapper.getInstancia().insert(this);
	}

	public ReclamoFaltantes(int nroReclamo, Date fecha, Date fechaCierre, String descripcionReclamo,
			EstadosReclamo estado, Cliente cliente, int cantFaltante, Producto producto) {
		this(nroReclamo, fecha, fechaCierre, descripcionReclamo, estado, cliente, cantFaltante, producto, false);
	}

	public ReclamoFaltantes(String descripcionReclamo, Cliente cliente, int cantFaltante, Producto producto) {
		this(ReclamoFaltanteMapper.getInstancia().getUltimoId(), new Date(Calendar.getInstance().getTimeInMillis()),
				null, descripcionReclamo, EstadosReclamo.INGRESADO, cliente, cantFaltante, producto, true);
	}

	public int getCantFaltante() {
		return cantFaltante;
	}

	public void setCantFaltante(int cantFaltante) {
		this.cantFaltante = cantFaltante;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ReclamoDistribucionView getView() {
		return new ReclamoDistribucionView(this);
	}
}
