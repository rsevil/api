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
import swing.model.TratamientoZonaTableModel;
import vista.ReclamoZonaView;

public class MenuPrincipalZona extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tablaReclamos;
	private TratamientoZonaTableModel tableModel;

	public MenuPrincipalZona() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Zona");
			GridBagLayout thisLayout = new GridBagLayout();
			getContentPane().setBackground(new java.awt.Color(221, 221, 255));
			thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
			thisLayout.columnWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.columnWidths = new int[] { 7, 7, 7, 7 };
			getContentPane().setLayout(thisLayout);
			{
				tablaReclamos = new JTable();
				actualizarDatos();
			}
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
							// System.out.println("MenuItem agregado:" +
							// jMenuItem.getText());
						}
					}
					popupMenu.add(add(resfreshItem()));
				}

				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					popupMenu.removeAll();
					// System.out.println("Eliminados menu");
				}

				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					popupMenu.removeAll();
					// System.out.println("Eliminados menu");
				}
			});
			tablaReclamos.setComponentPopupMenu(popupMenu);
			pack();
			setSize(800, 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<JMenuItem> getItemsMenu(ReclamoZonaView reclamo) {
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

	private JMenuItem resfreshItem() {
		JMenuItem refresh = new JMenuItem("Actualizar");
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}

		});
		return refresh;
	}

	private JMenuItem enTratamientoItem() {
		JMenuItem enTratamiento = new JMenuItem("Pasar a " + EstadosReclamo.EN_TRATAMIENTO.getTexto());
		enTratamiento.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPopUpNovedad(EstadosReclamo.EN_TRATAMIENTO);
			}

		});
		return enTratamiento;
	}

	private JMenuItem cerradoItem() {
		JMenuItem cerrado = new JMenuItem("Cerrar Caso");
		cerrado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPopUpNovedad(EstadosReclamo.CERRADO);
			}
		});
		return cerrado;
	}

	private JMenuItem solucionadoItem() {
		JMenuItem solucionado = new JMenuItem(EstadosReclamo.SOLUCIONADO.getTexto());
		solucionado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPopUpNovedad(EstadosReclamo.SOLUCIONADO);
			}
		});
		return solucionado;
	}

	private void mostrarPopUpNovedad(EstadosReclamo estado) {
		int selectedRow = tablaReclamos.getSelectedRow();
		if (selectedRow > -1) {
			ReclamoZonaView itemRow = tableModel.getItemRow(selectedRow);
			String texto = estado.getTexto();
			int nroReclamo = itemRow.getNroReclamo();

			String inputDialog = JOptionPane.showInputDialog("Ingrese una observación");
			if (inputDialog != null) {
				while (inputDialog != null && inputDialog.isEmpty()) {
					inputDialog = JOptionPane.showInputDialog("Ingrese una observación");
				}
				if (inputDialog != null && !inputDialog.isEmpty()) {
					cambiarEstado(texto, nroReclamo, inputDialog);
				}
			}
		}
	}

	private void cambiarEstado(String texto, int nroReclamo, String novedad) {
		boolean updateReclamo = false;
		
		if (texto.equals(EstadosReclamo.CERRADO.getTexto())) {
			updateReclamo = SistemaAdministracionReclamos.getInstancia().cerrarReclamo(nroReclamo, texto, novedad);
		}
		else {
			updateReclamo = SistemaAdministracionReclamos.getInstancia().updateReclamo(nroReclamo, texto, novedad);
		}
		
		if (updateReclamo) {
			JOptionPane.showMessageDialog(null, "El estado se actualizó correctamente!", "Info",
					JOptionPane.INFORMATION_MESSAGE);
			actualizarDatos();
		} else {
			JOptionPane.showMessageDialog(null, "Error al actualizar estado!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarDatos() {
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("Número de Reclamo");
		columnNames.add("Fecha de Creación");
		columnNames.add("Fecha de Cierre");
		columnNames.add("Descripción");
		columnNames.add("Estado");
		columnNames.add("Cliente");
		columnNames.add("Zona");
		ArrayList<ReclamoZonaView> reclamos = new ArrayList<ReclamoZonaView>(
				SistemaAdministracionReclamos.getInstancia().obtenerReclamosZonasTratamiento());
		tableModel = new TratamientoZonaTableModel(columnNames, reclamos);
		tablaReclamos.setModel(tableModel);
	}


}
