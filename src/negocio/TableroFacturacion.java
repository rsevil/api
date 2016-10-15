package negocio;

import java.util.Vector;

import persistencia.ReclamoFacturacionMapper;
import swing.ReclamoFacturacionView;

public class TableroFacturacion {

	private static TableroFacturacion instance;

	private TableroFacturacion() {
	}

	public static TableroFacturacion getInstance() {
		if (instance == null) {
			instance = new TableroFacturacion();
		}
		return instance;
	}

	public Vector<ReclamoFacturacionView> getReclamos() {
		Vector<ReclamoFacturacion> reclamos = ReclamoFacturacionMapper.getInstancia().selectAll(true);
		Vector<ReclamoFacturacionView> r = new Vector<ReclamoFacturacionView>();
		for (ReclamoFacturacion reclamoFacturacion : reclamos) {
			r.addElement(reclamoFacturacion.getView());
		}
		return r;
	}

}
