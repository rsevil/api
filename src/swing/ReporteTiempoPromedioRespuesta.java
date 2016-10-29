package swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import swing.model.ReporteRankingClientesTableModel;
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
public class ReporteTiempoPromedioRespuesta extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tablaReporte;
	private ReporteRankingClientesTableModel tableModel;
	
	public ReporteTiempoPromedioRespuesta() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Reporte: tiempo promedio de respuesta de los reclamos por responsable");
			GridBagLayout thisLayout = new GridBagLayout();
			getContentPane().setBackground(new java.awt.Color(221, 221, 255));
			thisLayout.rowWeights = new double[] { 0.1, 0.1, 0.1, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7, 7, 7 };
			thisLayout.columnWeights = new double[] {0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7};
			getContentPane().setLayout(thisLayout);
			{
				ArrayList<String> columnNames = new ArrayList<String>();
				columnNames.add("Nombre cliente");
				columnNames.add("Cantidad reclamos");
				ArrayList<ReporteView> itemsReporte = new ArrayList<ReporteView>(
						SistemaAdministracionReclamos.getInstancia().obtenerReporteRankingClientes());
				tableModel = new ReporteRankingClientesTableModel(columnNames, itemsReporte);
				tablaReporte = new JTable();
				tablaReporte.setModel(tableModel);
				JScrollPane pane = new JScrollPane(tablaReporte);
				pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getContentPane().add(pane, new GridBagConstraints(0, 0, 3, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
