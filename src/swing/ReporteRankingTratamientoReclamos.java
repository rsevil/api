package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import swing.model.ReporteRankingTratamientoReclamosTableModel;
import utils.FechaUtils;
import utils.TextoUtils;
import vista.ReporteView;
import controlador.SistemaAdministracionReclamos;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ReporteRankingTratamientoReclamos extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnVerReporte;
	private JTable tablaReporte;
	private ReporteRankingTratamientoReclamosTableModel tableModel;
	JLabel lblFechaDesde = new JLabel("Fecha desde:");
	JTextField txtFechaDesde = new JTextField();
	JLabel lblFechaHasta = new JLabel("Fecha hasta:");
	JTextField txtFechaHasta = new JTextField();
	
	public ReporteRankingTratamientoReclamos() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Reporte: ranking tratamiento de reclamos");
			GridBagLayout thisLayout = new GridBagLayout();
			getContentPane().setBackground(new java.awt.Color(221, 221, 255));
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				JPanel filtrosPane = new JPanel(new GridBagLayout());
				filtrosPane.setBackground(new java.awt.Color(221, 221, 255));
				GridBagLayout filtrosPaneLayout = new GridBagLayout();
				filtrosPane.setLayout(filtrosPaneLayout);
				filtrosPane.add(lblFechaDesde);
				filtrosPane.add(txtFechaDesde);
				txtFechaDesde.setSize(100, 23);
				txtFechaDesde.setPreferredSize(new java.awt.Dimension(100, 23));
				filtrosPane.add(lblFechaHasta);
				filtrosPane.add(txtFechaHasta);
				txtFechaHasta.setSize(100, 23);
				txtFechaHasta.setPreferredSize(new java.awt.Dimension(100, 23));
				{
					btnVerReporte = new JButton();
					filtrosPane.add(btnVerReporte, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					btnVerReporte.setText("Ver reporte");
					btnVerReporte.addActionListener(this);
				}
				tablaReporte = new JTable();
				JScrollPane pane = new JScrollPane(tablaReporte);
				pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getContentPane().add(pane, new GridBagConstraints(0, 1, 3, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				getContentPane().add(filtrosPane, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				filtrosPaneLayout.columnWidths = new int[] {0, 120, 0, 120, 88};
				filtrosPaneLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};
				filtrosPaneLayout.rowWeights = new double[] {0.1};
				filtrosPaneLayout.rowHeights = new int[] {7};
			}
			pack();
			this.setSize(531, 320);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent evt) 
	{				
		String fechaDesde = txtFechaDesde.getText(); 
		String fechaHasta = txtFechaHasta.getText(); 
		Date fechaDesdeParseada; 
		Date fechaHastaParseada; 
		
		if (!TextoUtils.isNullOrEmpty(fechaDesde) &&
				!TextoUtils.isNullOrEmpty(fechaHasta)) {

			fechaDesdeParseada = FechaUtils.parsearFecha(fechaDesde);
			fechaHastaParseada = FechaUtils.parsearFecha(fechaHasta);
					
			if (fechaDesdeParseada == null) {
				JOptionPane.showMessageDialog(null, "La fecha desde es inválida.");
			} else if (fechaHastaParseada == null) {
				JOptionPane.showMessageDialog(null, "La fecha hasta es inválida.");
			}
			else if (fechaHastaParseada.compareTo(fechaDesdeParseada) < 0) {
				JOptionPane.showMessageDialog(null, "La fecha hasta no puede ser anterior a la fecha desde.");
			}
			else {								
				ArrayList<ReporteView> itemsReporte = new ArrayList<ReporteView>(
						SistemaAdministracionReclamos.getInstancia().obtenerReporteRankingTratamientoReclamos(fechaDesdeParseada, fechaHastaParseada));	
				this.llenarTabla(itemsReporte);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos.");
		}
	}
	
	private void llenarTabla(ArrayList<ReporteView> itemsReporte) {
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("Nombre de usuario");
		columnNames.add("Cantidad reclamos");
		tableModel = new ReporteRankingTratamientoReclamosTableModel(columnNames, itemsReporte);
		tablaReporte.setModel(tableModel);
	}
}
