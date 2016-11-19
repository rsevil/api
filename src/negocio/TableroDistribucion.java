package negocio;

import java.util.Vector;

import persistencia.ReclamoCantidadesMapper;
import persistencia.ReclamoFaltanteMapper;
import persistencia.ReclamoProductoMapper;
import vista.ReclamoDistribucionView;

public class TableroDistribucion {

	private static TableroDistribucion instance;

	private Vector<Reclamo> reclamos;

	private TableroDistribucion() {
		this.reclamos = new Vector<Reclamo>();
	}

	public static TableroDistribucion getInstance() {
		if (instance == null) {
			instance = new TableroDistribucion();
		}
		return instance;
	}

	public Vector<ReclamoDistribucionView> getReclamos() {
		Vector<ReclamoDistribucionView> views = new Vector<ReclamoDistribucionView>();

		Vector<ReclamoCantidades> cantidades = ReclamoCantidadesMapper.getInstancia().selectAll(true);
		this.reclamos.addAll(cantidades);
		for (ReclamoCantidades reclamoCantidad : cantidades) {
			views.addElement(reclamoCantidad.getView());
		}
		Vector<ReclamoProducto> productos = ReclamoProductoMapper.getInstancia().selectAll(true);
		this.reclamos.addAll(productos);
		for (ReclamoProducto reclamoCantidad : productos) {
			views.addElement(reclamoCantidad.getView());
		}
		Vector<ReclamoFaltantes> faltantes = ReclamoFaltanteMapper.getInstancia().selectAll(true);
		this.reclamos.addAll(faltantes);
		for (ReclamoFaltantes reclamoCantidad : faltantes) {
			views.addElement(reclamoCantidad.getView());
		}
		return views;
	}

}
