package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.WindowConstants;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


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
public class MenuPrincipalConsulta extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnReporte1;
	private JButton btnReporte2;
	private JButton btnReporte3;
	private JButton btnReporte4;
	
	public MenuPrincipalConsulta() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Consulta");
			FormLayout thisLayout = new FormLayout(
					"max(p;5dlu), max(p;5dlu), max(p;5dlu)", 
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			{
				btnReporte1 = new JButton();
				getContentPane().add(btnReporte1, new CellConstraints("2, 2, 1, 1, default, default"));
				btnReporte1.setText("Reporte: ranking clientes con mayor cantidad de reclamos");
				btnReporte1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						/*AltaReclamoCantidades a = new AltaReclamoCantidades();
						a.setVisible(true);*/
					}
				});
			}
			{
				btnReporte2 = new JButton();
				getContentPane().add(btnReporte2, new CellConstraints("2, 4, 1, 1, default, default"));
				btnReporte2.setText("Reporte: reclamos tratados por mes");
				btnReporte2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						/*AltaReclamoProducto a = new AltaReclamoProducto();
						a.setVisible(true);*/
					}
				});
			}
			{
				btnReporte3 = new JButton();
				getContentPane().add(btnReporte3, new CellConstraints("2, 6, 1, 1, default, default"));
				btnReporte3.setText("Reporte: ranking tratamiento reclamos");
				btnReporte3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						/*AltaReclamoFaltantes a = new AltaReclamoFaltantes();
						a.setVisible(true);*/
					}
				});
			}
			{
				btnReporte4 = new JButton();
				getContentPane().add(btnReporte4, new CellConstraints("2, 8, 1, 1, default, default"));
				btnReporte4.setText("Reporte: tiempo promedio de respuesta de los reclamos por responsable");
				btnReporte4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						/*AltaReclamoZona a = new AltaReclamoZona();
						a.setVisible(true);*/
					}
				});
			}
			pack();
			this.setSize(492, 196);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
