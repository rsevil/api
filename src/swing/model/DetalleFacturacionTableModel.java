package swing.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vista.DetalleReclamoFacturacionView;

public class DetalleFacturacionTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<DetalleReclamoFacturacionView> detalles;

	public DetalleFacturacionTableModel() {
		detalles = new ArrayList<DetalleReclamoFacturacionView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public DetalleFacturacionTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;

	}

	/**
	 * @param columnNames
	 * @param detalles
	 */
	public DetalleFacturacionTableModel(ArrayList<String> columnNames, ArrayList<DetalleReclamoFacturacionView> detalles) {
		super();
		this.columnNames = columnNames;
		this.detalles = detalles;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return detalles.size();
	}

	public void addRow(DetalleReclamoFacturacionView detalle) {
		detalle.setNumeroDetalle(detalles.size() + 1);
		detalles.add(detalle);
		fireTableRowsInserted(detalles.size() - 1, detalles.size() - 1);
	}

	public void setRow(DetalleReclamoFacturacionView material, int index) {
		detalles.set(index, material);
		fireTableRowsUpdated(index, index);
	}

	public DetalleReclamoFacturacionView getItemRow(int row) {
		return detalles.get(row);
	}

	public void removeRow(int row) {
		detalles.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		DetalleReclamoFacturacionView detalle = detalles.get(row);
		switch (col) {
		case 0:
			return detalle.getNumeroDetalle();
		case 1:
			return detalle.getDetalle();
		default:
			return null;
		}
	}
}
