package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import controlador.SistemaAdministracionReclamos;
import enums.EstadosReclamo;
import swing.model.TratamientoFacturacionTableModel;
import vista.ReclamoFacturacionView;

public class MenuPrincipalFacturacion extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tablaReclamos;
	private TratamientoFacturacionTableModel tableModel;

	public MenuPrincipalFacturacion() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Facturación");
			GridBagLayout thisLayout = new GridBagLayout();
			getContentPane().setBackground(new java.awt.Color(221, 221, 255));
			thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
			thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
			getContentPane().setLayout(thisLayout);
			{
				ArrayList<String> columnNames = new ArrayList<String>();
				columnNames.add("Número de Reclamo");
				columnNames.add("Fecha Creación");
				columnNames.add("Fecha Cierre");
				columnNames.add("Descripción");
				columnNames.add("Estado");
				columnNames.add("Cliente");
				ArrayList<ReclamoFacturacionView> reclamos = new ArrayList<ReclamoFacturacionView>(
						SistemaAdministracionReclamos.getInstancia().obtenerReclamosFacturacionTratamiento());
				tableModel = new TratamientoFacturacionTableModel(columnNames, reclamos);
				tablaReclamos = new JTable();
				tablaReclamos.setModel(tableModel);
				JScrollPane pane = new JScrollPane(tablaReclamos);
				pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getContentPane().add(pane, new GridBagConstraints(0, 0, 4, 4, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

				tablaReclamos.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {
						Point point = e.getPoint();
						int currentRow = tablaReclamos.rowAtPoint(point);
						tablaReclamos.setRowSelectionInterval(currentRow, currentRow);
					}
				});

				final JPopupMenu popupMenu = new JPopupMenu();

				popupMenu.addPopupMenuListener(new PopupMenuListener() {

					@Override
					public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
						int selectedRow = tablaReclamos.getSelectedRow();
						if (selectedRow > -1) {
							ArrayList<JMenuItem> itemsMenu = getItemsMenu(tableModel.getItemRow(selectedRow));
							for (JMenuItem jMenuItem : itemsMenu) {
								popupMenu.add(jMenuItem);
								System.out.println("MenuItem agregado:" + jMenuItem.getText());
							}
						}
					}

					@Override
					public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
						popupMenu.removeAll();
						System.out.println("Eliminados menu");
					}

					@Override
					public void popupMenuCanceled(PopupMenuEvent e) {
						popupMenu.removeAll();
						System.out.println("Eliminados menu");
					}
				});
				tablaReclamos.setComponentPopupMenu(popupMenu);
			}
			pack();
			setSize(800, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList<JMenuItem> getItemsMenu(ReclamoFacturacionView reclamo) {
		EstadosReclamo estadoReclamo = EstadosReclamo.getEstadoReclamo(reclamo.getEstado());
		ArrayList<JMenuItem> items = new ArrayList<>();
		switch (estadoReclamo) {
		case INGRESADO:
			items.add(enTratamientoItem());
			items.add(cerradoItem());
			items.add(solucionadoItem());
			break;
		case EN_TRATAMIENTO:
			items.add(cerradoItem());
			items.add(solucionadoItem());
			break;
		default:
			break;
		}
		return items;
	}

	private JMenuItem enTratamientoItem() {
		JMenuItem enTratamiento = new JMenuItem("Pasar a " + EstadosReclamo.EN_TRATAMIENTO.getTexto());
		enTratamiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarEstado(EstadosReclamo.EN_TRATAMIENTO);
			}

		});
		return enTratamiento;
	}

	private JMenuItem cerradoItem() {
		JMenuItem cerrado = new JMenuItem("Cerrar Caso");
		cerrado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarEstado(EstadosReclamo.CERRADO);
			}
		});
		return cerrado;
	}

	private JMenuItem solucionadoItem() {
		JMenuItem solucionado = new JMenuItem(EstadosReclamo.SOLUCIONADO.getTexto());
		solucionado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarEstado(EstadosReclamo.SOLUCIONADO);
			}
		});
		return solucionado;
	}

	private void actualizarEstado(EstadosReclamo estado) {
		int selectedRow = tablaReclamos.getSelectedRow();
		if (selectedRow > -1) {
			ReclamoFacturacionView itemRow = tableModel.getItemRow(selectedRow);
			boolean updateReclamo = SistemaAdministracionReclamos.getInstancia().updateReclamo(itemRow.getNroReclamo(),
					estado.getTexto());
			if (updateReclamo) {
				JOptionPane.showMessageDialog(null, "El estado se actualizó correctamente!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
				actualizarDatos();

			} else {
				JOptionPane.showMessageDialog(null, "Error al actualizar estado!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void actualizarDatos() {
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("Número de Reclamo");
		columnNames.add("Fecha Creación");
		columnNames.add("Fecha Cierre");
		columnNames.add("Descripción");
		columnNames.add("Estado");
		columnNames.add("Cliente");
		ArrayList<ReclamoFacturacionView> reclamos = new ArrayList<ReclamoFacturacionView>(
				SistemaAdministracionReclamos.getInstancia().obtenerReclamosFacturacionTratamiento());
		tableModel = new TratamientoFacturacionTableModel(columnNames, reclamos);
		tablaReclamos.setModel(tableModel);
	}
}
