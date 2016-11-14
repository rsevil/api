package negocio;

import java.util.Vector;

import persistencia.ReclamoZonaMapper;
import vista.ReclamoZonaView;

public class TableroZona {

	private static TableroZona instance;

	private Vector<ReclamoZona> reclamos;

	private TableroZona() {
		this.reclamos = new Vector<ReclamoZona>();
	}

	public static TableroZona getInstance() {
		return instance;
	}

	public Vector<ReclamoZonaView> getReclamos() {
		this.reclamos = ReclamoZonaMapper.getInstancia().selectAll();
		Vector<ReclamoZonaView> views = new Vector<ReclamoZonaView>();
		for (ReclamoZona reclamoZona : reclamos) {
			views.addElement(reclamoZona.getView());
		}
		return views;
	}
}