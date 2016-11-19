package swing.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vista.ReclamoDistribucionView;

public class TratamientoDistribucionTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<ReclamoDistribucionView> reclamos;

	public TratamientoDistribucionTableModel() {
		reclamos = new ArrayList<ReclamoDistribucionView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public TratamientoDistribucionTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;

	}

	/**
	 * @param columnNames
	 * @param reclamos
	 */
	public TratamientoDistribucionTableModel(ArrayList<String> columnNames, ArrayList<ReclamoDistribucionView> reclamos) {
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

	public void addRow(ReclamoDistribucionView reclamo) {
		reclamos.add(reclamo);
		fireTableRowsInserted(reclamos.size() - 1, reclamos.size() - 1);
	}

	public void setRow(ReclamoDistribucionView material, int index) {
		reclamos.set(index, material);
		fireTableRowsUpdated(index, index);
	}

	public ReclamoDistribucionView getItemRow(int row) {
		return reclamos.get(row);
	}

	public void removeRow(int row) {
		reclamos.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		ReclamoDistribucionView reclamo = reclamos.get(row);
		switch (col) {
		case 0:
			return reclamo.getNroReclamo();
		case 1:
			return reclamo.getTipo();
		case 2:
			return reclamo.getFecha();
		case 3:
			return reclamo.getFechaCierre();
		case 4:
			return reclamo.getDescripcionReclamo();
		case 5:
			return reclamo.getEstado();
		case 6:
			return reclamo.getCliente();
		case 7:
			return reclamo.getCantidadProductos();
		case 8:
			return reclamo.getProducto();
		case 9:
			return reclamo.getCantidadFaltante();
		case 10:
			return reclamo.getProductoFaltante();
		default:
			return null;
		}
	}
}
