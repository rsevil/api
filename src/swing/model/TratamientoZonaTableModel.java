package swing.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vista.ReclamoZonaView;

public class TratamientoZonaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<ReclamoZonaView> reclamos;

	public TratamientoZonaTableModel() {
		reclamos = new ArrayList<ReclamoZonaView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public TratamientoZonaTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;

	}

	/**
	 * @param columnNames
	 * @param reclamos
	 */
	public TratamientoZonaTableModel(ArrayList<String> columnNames, ArrayList<ReclamoZonaView> reclamos) {
		super();
		this.columnNames = columnNames;
		this.reclamos = reclamos;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return reclamos.size();
	}

	public void addRow(ReclamoZonaView reclamo) {
		reclamos.add(reclamo);
		fireTableRowsInserted(reclamos.size() - 1, reclamos.size() - 1);
	}

	public void setRow(ReclamoZonaView material, int index) {
		reclamos.set(index, material);
		fireTableRowsUpdated(index, index);
	}

	public ReclamoZonaView getItemRow(int row) {
		return reclamos.get(row);
	}

	public void removeRow(int row) {
		reclamos.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		ReclamoZonaView reclamo = reclamos.get(row);
		switch (col) {
		case 0:
			return reclamo.getNroReclamo();
		case 1:
			return reclamo.getFecha();
		case 2:
			return reclamo.getFechaCierre();
		case 3:
			return reclamo.getDescripcionReclamo();
		case 4:
			return reclamo.getEstado();
		case 5:
			return reclamo.getCliente();
		case 6:
			return reclamo.getZona();
		default:
			return null;
		}
	}
}
