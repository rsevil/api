package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AltaReclamoCompuesto extends javax.swing.JFrame {

	/**
	 * Auto-generated main method to display this JFrame
	 */
	/*
	 * public static void main(String[] args) { SwingUtilities.invokeLater(new
	 * Runnable() { public void run() { AltaReclamoCompuesto inst = new
	 * AltaReclamoCompuesto(); inst.setLocationRelativeTo(null);
	 * inst.setVisible(true); } }); }
	 */

	private ArrayList<Integer> reclamosSimplesVector;

	private static final long serialVersionUID = 1L;
	private JButton btnFaltantes;
	private JButton btnProducto;
	private JButton btnZona;
	private JButton btnFacturacion;
	private JButton btnCantidades;

	public AltaReclamoCompuesto() {
		super();
		initGUI();
		setLocationRelativeTo(null);
		setResizable(false);
		this.reclamosSimplesVector = new ArrayList<Integer>();
	}

	private JScrollPane scrllTxtDescripcion;
	private JButton btnRegistrarReclamo;
	private JTextArea txtDescripcion;
	private JTextField txtNroCliente;
	private JLabel lblDescripcion;
	private JLabel lblNroCliente;

	private void initGUI() {

		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setBackground(new java.awt.Color(221, 221, 255));
			FormLayout thisLayout = new FormLayout("max(p;5dlu), max(p;5dlu), max(p;5dlu), 115dlu",
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), 69dlu, max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;15dlu), max(p;15dlu), max(p;15dlu)");
			getContentPane().setLayout(thisLayout);
			this.setTitle("Ingresar Reclamo Compuesto");
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
				getContentPane().add(btnRegistrarReclamo, new CellConstraints("4, 12, 1, 1, default, default"));
				btnRegistrarReclamo.setText("Registrar Reclamo");
				btnRegistrarReclamo.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent evt) {
						String nroCliente = txtNroCliente.getText();
						String descripcion = txtDescripcion.getText();

						if (reclamosSimplesVector.isEmpty()) {
							JOptionPane.showMessageDialog(null, "No se puede Crear un reclamo Compuesto sin reclamos!");
							return;
						}
						if (!NumeroUtils.isInteger(nroCliente)) {
							JOptionPane.showMessageDialog(null, "El número de cliente es inválido.");
						} else {
							int rdo = SistemaAdministracionReclamos.getInstancia()
									.registrarReclamoCompuesto(Integer.parseInt(nroCliente), descripcion);
							String mensaje = "";
							int rdo2 = -99;
							if (rdo > 0) {
								for (int reclamoSimple : reclamosSimplesVector) {
									rdo2 = SistemaAdministracionReclamos.getInstancia()
											.agregarReclamoReclamoCompuesto(rdo, reclamoSimple);
									if (rdo2 < 0)
										break;
								}
							}
							if (rdo == ExitCodes.NO_EXISTE_CLIENTE) {
								mensaje = mensaje.concat("No existe el cliente.\n");
							}
							if (rdo2 == ExitCodes.NO_EXISTE_RECLAMO) {
								mensaje = mensaje.concat("No existe el reclamo simple o compuesto.");
							}
							if (rdo >= 0 && rdo2 >= 0) {
								mensaje = mensaje.concat("Reclamo generado correctamente.");
							}

							JOptionPane.showMessageDialog(null, mensaje);
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
				btnCantidades = new JButton();
				getContentPane().add(btnCantidades, new CellConstraints("4, 6, 1, 1, default, default"));
				btnCantidades.setText("Crear Reclamo Cantidades");
				btnCantidades.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AltaReclamoCantidades n = new AltaReclamoCantidades(AltaReclamoCompuesto.this);
						n.setVisible(true);
					}
				});
			}
			{
				btnFacturacion = new JButton();
				getContentPane().add(btnFacturacion, new CellConstraints("4, 7, 1, 1, default, default"));
				btnFacturacion.setText("Crear Reclamo Facturacion");
				btnFacturacion.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AltaReclamoFacturacion n = new AltaReclamoFacturacion(AltaReclamoCompuesto.this);
						n.setVisible(true);
					}
				});
			}
			{
				btnZona = new JButton();
				getContentPane().add(btnZona, new CellConstraints("4, 8, 1, 1, default, default"));
				btnZona.setText("Crear Reclamo Zona");
				btnZona.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AltaReclamoZona n = new AltaReclamoZona(AltaReclamoCompuesto.this);
						n.setVisible(true);
					}
				});
			}
			{
				btnProducto = new JButton();
				getContentPane().add(btnProducto, new CellConstraints("4, 9, 1, 1, default, default"));
				btnProducto.setText("Crear Reclamo Producto");
				btnProducto.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AltaReclamoProducto n = new AltaReclamoProducto(AltaReclamoCompuesto.this);
						n.setVisible(true);
					}
				});
			}
			{
				btnFaltantes = new JButton();
				getContentPane().add(btnFaltantes, new CellConstraints("4, 10, 1, 1, default, default"));
				btnFaltantes.setText("Crear Reclamo Faltante");
				btnFaltantes.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AltaReclamoFaltantes n = new AltaReclamoFaltantes(AltaReclamoCompuesto.this);
						n.setVisible(true);
					}
				});
			}

			pack();
			this.setSize(470, 412);
		} catch (Exception e) {
			e.printStackTrace();
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (reclamosSimplesVector != null && !reclamosSimplesVector.isEmpty()) {
					SistemaAdministracionReclamos.getInstancia().borrarReclamos(reclamosSimplesVector);
				}
				e.getWindow().dispose();
			}
		});
	}

	public void registrarReclamo(int id) {
		reclamosSimplesVector.add(id);
	}

}