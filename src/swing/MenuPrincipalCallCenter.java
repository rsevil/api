package swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import javax.swing.JButton;
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
public class MenuPrincipalCallCenter extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton btnIngresarReclamoZona;
	private JButton btnIngresarReclamoCompuesto;
	private JButton btnIngresarReclamoFacturacion;
	private JButton btnIngresarReclamoFaltantes;
	private JButton btnIngresarReclamoProducto;
	private JButton btnIngresarReclamoCantidades;

	public MenuPrincipalCallCenter() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Call Center");
			FormLayout thisLayout = new FormLayout(
					"max(p;5dlu), max(p;5dlu), max(p;5dlu)", 
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			{
				btnIngresarReclamoCantidades = new JButton();
				getContentPane().add(btnIngresarReclamoCantidades, new CellConstraints("2, 2, 1, 1, default, default"));
				btnIngresarReclamoCantidades.setText("Ingresar Reclamo de Cantidades");
				btnIngresarReclamoCantidades.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AltaReclamoCantidades a = new AltaReclamoCantidades();
						a.setVisible(true);
					}
				});
			}
			{
				btnIngresarReclamoProducto = new JButton();
				getContentPane().add(btnIngresarReclamoProducto, new CellConstraints("2, 4, 1, 1, default, default"));
				btnIngresarReclamoProducto.setText("Ingresar Reclamo de Producto");
				btnIngresarReclamoProducto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AltaReclamoProducto a = new AltaReclamoProducto();
						a.setVisible(true);
					}
				});
			}
			{
				btnIngresarReclamoFaltantes = new JButton();
				getContentPane().add(btnIngresarReclamoFaltantes, new CellConstraints("2, 6, 1, 1, default, default"));
				btnIngresarReclamoFaltantes.setText("Ingresar Reclamo de Faltantes");
				btnIngresarReclamoFaltantes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AltaReclamoFaltantes a = new AltaReclamoFaltantes();
						a.setVisible(true);
					}
				});
			}
			{
				btnIngresarReclamoZona = new JButton();
				getContentPane().add(btnIngresarReclamoZona, new CellConstraints("2, 8, 1, 1, default, default"));
				btnIngresarReclamoZona.setText("Ingresar Reclamo de Zona");
				btnIngresarReclamoZona.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AltaReclamoZona a = new AltaReclamoZona();
						a.setVisible(true);
					}
				});
			}
			{
				btnIngresarReclamoFacturacion = new JButton();
				getContentPane().add(btnIngresarReclamoFacturacion, new CellConstraints("2, 10, 1, 1, default, default"));
				btnIngresarReclamoFacturacion.setText("Ingresar Reclamo de Facturación");
				btnIngresarReclamoFacturacion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AltaReclamoFacturacion a = new AltaReclamoFacturacion();
						a.setVisible(true);
					}
				});
			}
			{
				btnIngresarReclamoCompuesto = new JButton();
				getContentPane().add(btnIngresarReclamoCompuesto, new CellConstraints("2, 12, 1, 1, default, default"));
				btnIngresarReclamoCompuesto.setText("Ingresar Reclamo Compuesto");
				btnIngresarReclamoCompuesto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AltaReclamoCompuesto a = new AltaReclamoCompuesto();
						a.setVisible(true);
					}
				});
			}
			pack();
			this.setSize(275, 248);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
