package swing;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
public class AltaReclamoZona extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JScrollPane scrllTxtDescripcion;
	private JButton btnRegistrarReclamo;
	private JTextField txtZonaAfectada;
	private JTextArea txtDescripcion;
	private JTextField txtNroCliente;
	private JLabel lblZonaAfectada;
	private JLabel lblDescripcion;
	private JLabel lblNroCliente;

	public AltaReclamoZona() {
		super();
		initGUI();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			FormLayout thisLayout = new FormLayout(
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), 115dlu", 
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), 69dlu, max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
			getContentPane().setLayout(thisLayout);
			this.setTitle("Ingresar Reclamo de Zona");
			{
				lblNroCliente = new JLabel();
				getContentPane().add(lblNroCliente, new CellConstraints("2, 2, 1, 1, default, default"));
				lblNroCliente.setText("N�mero de cliente");
			}
			{
				lblDescripcion = new JLabel();
				getContentPane().add(lblDescripcion, new CellConstraints("2, 4, 1, 1, default, default"));
				lblDescripcion.setText("Descripci�n del reclamo");
			}
			{
				lblZonaAfectada = new JLabel();
				getContentPane().add(lblZonaAfectada, new CellConstraints("2, 6, 1, 1, default, default"));
				lblZonaAfectada.setText("Zona afectada");
			}
			{
				txtNroCliente = new JTextField();
				getContentPane().add(txtNroCliente, new CellConstraints("4, 2, 1, 1, default, default"));
			}
			{
				//txtDescripcion = new JTextArea();
				//getContentPane().add(txtDescripcion, new CellConstraints("4, 4, 1, 1, default, default"));
			}
			{
				txtZonaAfectada = new JTextField();
				getContentPane().add(txtZonaAfectada, new CellConstraints("4, 6, 1, 1, default, default"));
			}
			{
				btnRegistrarReclamo = new JButton();
				getContentPane().add(btnRegistrarReclamo, new CellConstraints("4, 8, 1, 1, default, default"));
				btnRegistrarReclamo.setText("Registrar reclamo");
			}
			{
				scrllTxtDescripcion = new JScrollPane();
				scrllTxtDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scrllTxtDescripcion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getContentPane().add(scrllTxtDescripcion, new CellConstraints("4, 4, 1, 1, default, fill"));
				{
					txtDescripcion = new JTextArea();
					txtDescripcion.setLineWrap(true);
					scrllTxtDescripcion.setViewportView(txtDescripcion);
				}
			}
			pack();
			this.setSize(432, 282);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}