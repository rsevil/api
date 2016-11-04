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
public class AltaReclamoCantidades extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtCantidad;
	private JLabel lblCantidad;
	private JScrollPane scrllTxtDescripcion;
	private JButton btnRegistrarReclamo;
	private JTextField txtCodigoProducto;
	private JTextArea txtDescripcion;
	private JTextField txtNroCliente;
	private JLabel lblCodigoProducto;
	private JLabel lblDescripcion;
	private JLabel lblNroCliente;
	
	private AltaReclamoCompuesto compuesto;
	
	public AltaReclamoCantidades(AltaReclamoCompuesto parent){
		this();
		this.compuesto = parent;
	}
	
	public AltaReclamoCantidades() {
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
					lblCodigoProducto = new JLabel();
					getContentPane().add(lblCodigoProducto, new CellConstraints("2, 6, 1, 1, default, default"));
					lblCodigoProducto.setText("Código de producto");
				}
				{
					txtNroCliente = new JTextField();
					getContentPane().add(txtNroCliente, new CellConstraints("4, 2, 1, 1, default, default"));
				}
				{
					txtCodigoProducto = new JTextField();
					getContentPane().add(txtCodigoProducto, new CellConstraints("4, 6, 1, 1, default, default"));
				}
				{
					btnRegistrarReclamo = new JButton();
					getContentPane().add(btnRegistrarReclamo, new CellConstraints("4, 10, 1, 1, default, default"));
					btnRegistrarReclamo.setText("Registrar reclamo");
					btnRegistrarReclamo.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent evt) 
						{				
							String nroCliente = txtNroCliente.getText(); 
							String descripcion = txtDescripcion.getText(); 
							String codProducto = txtCodigoProducto.getText();
							String cant = txtCantidad.getText();
							
							if (!TextoUtils.isNullOrEmpty(nroCliente) &&
									!TextoUtils.isNullOrEmpty(descripcion) &&
									!TextoUtils.isNullOrEmpty(codProducto) &&
									!TextoUtils.isNullOrEmpty(cant)) {

								if (!NumeroUtils.isInteger(nroCliente)) {
									JOptionPane.showMessageDialog(null, "El número de cliente es inválido.");
								} else if (!NumeroUtils.isInteger(codProducto)) {
										JOptionPane.showMessageDialog(null, "El número de producto es inválido.");
								} else if (!NumeroUtils.isInteger(cant) || Integer.parseInt(cant) < 1) {
									JOptionPane.showMessageDialog(null, "La cantidad es inválida.");
								} else {								
									//int rdo = SistemaAdministracionReclamos.getInstancia().registrarReclamoProducto(Integer.parseInt(nroCliente), descripcion, Integer.parseInt(codProducto), Integer.parseInt(cant));
									int rdo = SistemaAdministracionReclamos.getInstancia().registrarReclamoCantidades(Integer.parseInt(nroCliente), descripcion);
									String mensaje = "";
									int rdo2= -99;
									if (rdo > 0) {
										rdo2=SistemaAdministracionReclamos.getInstancia().agregarProductoReclamoCantidades(rdo, Integer.parseInt(codProducto), Integer.parseInt(cant));
										if (rdo2 > 0) {
											mensaje=mensaje.concat("El reclamo se ha registrado con éxito.");
											if(compuesto != null){
												compuesto.registrarReclamo(rdo);
											}
										}
									}
									
									
									if (rdo == ExitCodes.NO_EXISTE_CLIENTE) {
										mensaje=mensaje.concat("No existe el cliente.\n");
									}
									if (rdo2 == ExitCodes.NO_EXISTE_PRODUCTO) {
										mensaje=mensaje.concat("No existe el producto.");
									}
									
									JOptionPane.showMessageDialog(null, mensaje);
									if (rdo > 0){
										txtNroCliente.setText("");
										txtDescripcion.setText("");
										txtCodigoProducto.setText("");
										txtCantidad.setText("");
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
					}
				}
				{
					lblCantidad = new JLabel();
					getContentPane().add(lblCantidad, new CellConstraints("2, 8, 1, 1, default, default"));
					lblCantidad.setText("Cantidad");
				}
				{
					txtCantidad = new JTextField();
					getContentPane().add(txtCantidad, new CellConstraints("4, 8, 1, 1, default, default"));
				}
				pack();
				this.setSize(472, 312);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
