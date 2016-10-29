package swing.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vista.ReporteView;

public class ReporteRankingClientesTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<ReporteView> itemsReporte;

	public ReporteRankingClientesTableModel() {
		this.itemsReporte = new ArrayList<ReporteView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public ReporteRankingClientesTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;
	}

	/**
	 * @param columnNames
	 * @param itemsReporte
	 */
	public ReporteRankingClientesTableModel(ArrayList<String> columnNames, ArrayList<ReporteView> itemsReporte) {
		super();
		this.columnNames = columnNames;
		this.itemsReporte = itemsReporte;
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
		return itemsReporte.size();
	}

	public void addRow(ReporteView item) {
		this.itemsReporte.add(item);
		fireTableRowsInserted(itemsReporte.size() - 1, itemsReporte.size() - 1);
	}

	public void setRow(ReporteView item, int index) {
		itemsReporte.set(index, item);
		fireTableRowsUpdated(index, index);
	}

	public ReporteView getItemRow(int row) {
		return itemsReporte.get(row);
	}

	public void removeRow(int row) {
		itemsReporte.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		ReporteView item = itemsReporte.get(row);
		switch (col) {
		case 0:
			return item.getNombre();
		case 1:
			return item.getCantidad();
		default:
			return null;
		}
	}
}
