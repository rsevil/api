package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import controlador.SistemaAdministracionReclamos;
import enums.ExitCodes;
import swing.model.DetalleFacturacionTableModel;
import utils.NumeroUtils;
import utils.TextoUtils;
import vista.DetalleReclamoFacturacionView;

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
public class AltaReclamoFacturacion extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblNroFactura;
	private JLabel jLabel1;
	private JTable jTableItems;
	private JButton btnAgregarDetalle;
	private JTextArea txtDetalleFactura;
	private JScrollPane scrllDetalleFactura;
	private JLabel lblDetalleFactura;
	private JTextField txtNroFactura;
	private JScrollPane scrllTxtDescripcion;
	private JButton btnRegistrarReclamo;
	private JTextArea txtDescripcion;
	private JTextField txtNroCliente;
	private JLabel lblDescripcion;
	private JLabel lblNroCliente;
	
	private int rdo;
	private DetalleFacturacionTableModel detalleFacturacionTableModel;
	
	public AltaReclamoFacturacion() {
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
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), 69dlu, max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
			getContentPane().setLayout(thisLayout);
			this.setTitle("Ingresar Reclamo de Facturación");
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
				getContentPane().add(btnRegistrarReclamo, new CellConstraints("4, 6, 1, 1, default, default"));
				btnRegistrarReclamo.setText("Registrar reclamo");
				btnRegistrarReclamo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{				
						String nroCliente = txtNroCliente.getText(); 
						String descripcion = txtDescripcion.getText(); 
						
						if (!TextoUtils.isNullOrEmpty(nroCliente) &&
								!TextoUtils.isNullOrEmpty(descripcion)) {

							if (!NumeroUtils.isInteger(nroCliente)) {
								JOptionPane.showMessageDialog(null, "El número de cliente es inválido.");
							} else {								
								rdo = SistemaAdministracionReclamos.getInstancia().registrarReclamoFacturacion(Integer.parseInt(nroCliente), descripcion);
								
								String mensaje = "";
								if (rdo > 0) {
									mensaje = "El reclamo se ha registrado con éxito.";
								} else  if (rdo == ExitCodes.NO_EXISTE_CLIENTE) {
										mensaje = "No existe el cliente.";
								}
								
								JOptionPane.showMessageDialog(null, mensaje);
								if (rdo > 0){
									txtNroCliente.setEnabled(false);
									txtDescripcion.setEnabled(false);
									btnRegistrarReclamo.setEnabled(false);
									txtNroFactura.setEnabled(true);
									txtDetalleFactura.setEnabled(true);
									btnAgregarDetalle.setEnabled(true);
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
				lblNroFactura = new JLabel();
				getContentPane().add(lblNroFactura, new CellConstraints("2, 8, 1, 1, default, default"));
				lblNroFactura.setText("Número de factura");
			}
			{
				txtNroFactura = new JTextField();
				txtNroFactura.setEnabled(false);
				getContentPane().add(txtNroFactura, new CellConstraints("4, 8, 1, 1, default, default"));
			}
			{
				lblDetalleFactura = new JLabel();
				getContentPane().add(lblDetalleFactura, new CellConstraints("2, 10, 1, 1, default, default"));
				lblDetalleFactura.setText("Detalle de factura");
			}
			{
				scrllDetalleFactura = new JScrollPane();
				scrllDetalleFactura.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				scrllDetalleFactura.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getContentPane().add(scrllDetalleFactura, new CellConstraints("4, 10, 1, 1, default, fill"));
				{
					txtDetalleFactura = new JTextArea();
					txtDetalleFactura.setLineWrap(true);
					txtDetalleFactura.setEnabled(false);
					scrllDetalleFactura.setViewportView(txtDetalleFactura);
					txtDetalleFactura.setPreferredSize(new java.awt.Dimension(183, 61));
				}
			}
			{
				btnAgregarDetalle = new JButton();
				getContentPane().add(btnAgregarDetalle, new CellConstraints("4, 12, 1, 1, default, default"));
				btnAgregarDetalle.setText("Agregar detalle factura");
				btnAgregarDetalle.setEnabled(false);
				btnAgregarDetalle.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{				
						String nroFactura = txtNroFactura.getText(); 
						String detalle = txtDetalleFactura.getText(); 
						
						if (!TextoUtils.isNullOrEmpty(nroFactura) &&
								!TextoUtils.isNullOrEmpty(detalle)) {

							if (!NumeroUtils.isInteger(nroFactura)) {
								JOptionPane.showMessageDialog(null, "El número de factura es inválido.");
							} else {								
								int rdo2 = SistemaAdministracionReclamos.getInstancia().agregarDetalleReclamoFacturacion(rdo, detalle, Integer.parseInt(nroFactura));
								
								String mensaje = "";
								if (rdo2 == ExitCodes.OK) {	
									mensaje = "El detalle se ha registrado con éxito.";
									detalleFacturacionTableModel.addRow(new DetalleReclamoFacturacionView(detalle, nroFactura, 0));
								} else  if (rdo2 == ExitCodes.NO_EXISTE_RECLAMO) {
										mensaje = "No existe el reclamo.";
								} else  if (rdo2 == ExitCodes.FALLA_RECLAMO_DETALLE_FACTURACION) {
									mensaje = "La factura no existe, o bien la misma no pertenece al cliente ingresado.";
								}
								
								JOptionPane.showMessageDialog(null, mensaje);
								if (rdo2 == ExitCodes.OK){
									//txtNroCliente.setText("");
									//txtDescripcion.setText("");
									//txtNroCliente.setEnabled(true);
									//txtDescripcion.setEnabled(true);
									//btnRegistrarReclamo.setEnabled(true);
									txtNroFactura.setEnabled(true);
									txtDetalleFactura.setEnabled(true);
									btnAgregarDetalle.setEnabled(true);
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
				ArrayList<String> columnas = new ArrayList<String>();
				columnas.add("Número");
				columnas.add("Descripción");
				detalleFacturacionTableModel = new DetalleFacturacionTableModel(columnas);
				

				jTableItems = new JTable();
				jTableItems.setModel(detalleFacturacionTableModel);
				JScrollPane pane = new JScrollPane(jTableItems);
				pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				getContentPane().add(pane, new CellConstraints("4, 14, 1, 1, default, default"));
				pane.setPreferredSize(new java.awt.Dimension(230, 150));
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new CellConstraints("2, 14, 1, 1, default, default"));
				jLabel1.setText("Conceptos ingresados");
			}
			pack();
			this.setSize(442, 539);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}