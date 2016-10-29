package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import controlador.SistemaAdministracionReclamos;
import enums.ExitCodes;
import utils.NumeroUtils;
import utils.TextoUtils;

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
public class AltaReclamoCompuesto extends javax.swing.JFrame {

	/**
	* Auto-generated main method to display this JFrame
	*/
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AltaReclamoCompuesto inst = new AltaReclamoCompuesto();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}*/
	
	private static final long serialVersionUID = 1L;
	private JLabel reclamosSimplesjLabel;
	private JTextArea reclamosSimplesjTextArea;

	public AltaReclamoCompuesto() {
		super();
		initGUI();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private JScrollPane scrllTxtDescripcion;
	private JScrollPane reclamosSimplesscrllTxtDescripcion;
	private JButton btnRegistrarReclamo;
	private JTextArea txtDescripcion;
	private JTextField txtNroCliente;
	private JLabel lblDescripcion;
	private JLabel lblNroCliente;
		
	private void initGUI() {

			try {
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				getContentPane().setBackground(new java.awt.Color(221,221,255));
				FormLayout thisLayout = new FormLayout(
						"max(p;5dlu), max(p;5dlu), max(p;5dlu), 115dlu", 
						"max(p;5dlu), max(p;5dlu), max(p;5dlu), 69dlu, max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
				getContentPane().setLayout(thisLayout);
				this.setTitle("Ingresar Reclamo de Producto");
				{
					lblNroCliente = new JLabel();
					getContentPane().add(lblNroCliente, new CellConstraints("2, 2, 1, 1, default, default"));
					lblNroCliente.setText("Número de cliente");
				}
				{
					lblDescripcion = new JLabel();
					getContentPane().add(lblDescripcion, new CellConstraints("2, 4, 1, 1, default, default"));
					lblDescripcion.setText("Descripción del reclamo");
				}

				{
					txtNroCliente = new JTextField();
					getContentPane().add(txtNroCliente, new CellConstraints("4, 2, 1, 1, default, default"));
				}

				{
					btnRegistrarReclamo = new JButton();
					getContentPane().add(btnRegistrarReclamo, new CellConstraints("4, 8, 1, 1, default, default"));
					btnRegistrarReclamo.setText("Registrar reclamo");
					btnRegistrarReclamo.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) 
						{				
							String nroCliente = txtNroCliente.getText(); 
							String descripcion = txtDescripcion.getText(); 
							String reclamosSimples = reclamosSimplesjTextArea.getText();
							
							if (!TextoUtils.isNullOrEmpty(nroCliente) &&
									!TextoUtils.isNullOrEmpty(descripcion) &&
									!TextoUtils.isNullOrEmpty(reclamosSimples)) {

								if (!NumeroUtils.isInteger(nroCliente)) {
									JOptionPane.showMessageDialog(null, "El número de cliente es inválido.");
								} else {								
									int rdo = SistemaAdministracionReclamos.getInstancia().registrarReclamoCompuesto(Integer.parseInt(nroCliente), descripcion);
									String mensaje = "";
									int rdo2= -99;
									if (rdo > 0) {
										String[] reclamosSimplesVector = reclamosSimples.split(" ");
										
										for(String reclamoSimple: reclamosSimplesVector){
											reclamoSimple=reclamoSimple.trim();
											rdo2=SistemaAdministracionReclamos.getInstancia().agregarReclamoReclamoCompuesto(rdo, Integer.parseInt(reclamoSimple));
											if (rdo2<0)
												break;
										}
									}
									if (rdo == ExitCodes.NO_EXISTE_CLIENTE) {
										mensaje=mensaje.concat("No existe el cliente.\n");
									}
									if (rdo2 == ExitCodes.NO_EXISTE_RECLAMO) {
										mensaje=mensaje.concat("No existe el reclamo simple o compuesto.");
									}
									if (rdo >=0 && rdo2>=0){
										mensaje=mensaje.concat("Reclamo generado correctamente.");
									}
										
									
									JOptionPane.showMessageDialog(null, mensaje);
									if (rdo >=0 && rdo2>=0){
										txtNroCliente.setText("");
										txtDescripcion.setText("");
										reclamosSimplesjTextArea.setText("");
									}
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos.");
							}
						}
					});
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
						txtDescripcion.setPreferredSize(new java.awt.Dimension(183, 8));
					}
				}
				{
					reclamosSimplesscrllTxtDescripcion = new JScrollPane();
					reclamosSimplesscrllTxtDescripcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					reclamosSimplesscrllTxtDescripcion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					getContentPane().add(reclamosSimplesscrllTxtDescripcion, new CellConstraints("4, 6, 1, 1, default, fill"));
					{
						reclamosSimplesjTextArea = new JTextArea();
						reclamosSimplesjTextArea.setLineWrap(true);
						reclamosSimplesscrllTxtDescripcion.setViewportView(reclamosSimplesjTextArea);
						reclamosSimplesjTextArea.setPreferredSize(new java.awt.Dimension(183, 59));
					}
				}
				{
					reclamosSimplesjLabel = new JLabel();
					getContentPane().add(reclamosSimplesjLabel, new CellConstraints("2, 6, 1, 1, default, default"));
					reclamosSimplesjLabel.setText("<html>Reclamos simples<br>separados por espacios</html>");
				}

				pack();
				this.setSize(472, 312);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	public void registrarReclamo(int id){
		/**
		 * TODO agregar reclamo
		 */
	}

}