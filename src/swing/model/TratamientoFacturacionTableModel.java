package swing.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import vista.ReclamoFacturacionView;

public class TratamientoFacturacionTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<String> columnNames;

	private ArrayList<ReclamoFacturacionView> reclamos;

	public TratamientoFacturacionTableModel() {
		this.reclamos = new ArrayList<ReclamoFacturacionView>();
		this.columnNames = new ArrayList<String>();
	}

	/**
	 * @param columnNames
	 */
	public TratamientoFacturacionTableModel(ArrayList<String> columnNames) {
		this();
		this.columnNames = columnNames;
	}

	/**
	 * @param columnNames
	 * @param reclamos
	 */
	public TratamientoFacturacionTableModel(ArrayList<String> columnNames, ArrayList<ReclamoFacturacionView> reclamos) {
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

	public void addRow(ReclamoFacturacionView reclamo) {
		this.reclamos.add(reclamo);
		fireTableRowsInserted(reclamos.size() - 1, reclamos.size() - 1);
	}

	public void setRow(ReclamoFacturacionView reclamo, int index) {
		reclamos.set(index, reclamo);
		fireTableRowsUpdated(index, index);
	}

	public ReclamoFacturacionView getItemRow(int row) {
		return reclamos.get(row);
	}

	public void removeRow(int row) {
		reclamos.remove(row);
		fireTableRowsDeleted(row, row);
	}

	@Override
	public Object getValueAt(int row, int col) {
		ReclamoFacturacionView reclamo = reclamos.get(row);
		switch (col) {
		case 0:
			return reclamo.getNroReclamo();
		case 1:
			String fecha = reclamo.getFecha();
			if (fecha == null) {
				fecha = "-";
			}
			return fecha;
		case 2:
			String fechaCierre = reclamo.getFechaCierre();
			if (fechaCierre == null) {
				fechaCierre = "-";
			}
			return fechaCierre;
		case 3:
			return reclamo.getDescripcionReclamo();
		case 4:
			return reclamo.getEstado();
		case 5:
			return reclamo.getCliente();
		default:
			return null;
		}
	}
}
