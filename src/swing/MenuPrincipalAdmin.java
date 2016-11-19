package swing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class MenuPrincipalAdmin extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton btnAdministrarRoles;

	public MenuPrincipalAdmin() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Administrador");
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			getContentPane().setLayout(null);
			{
				btnAdministrarRoles = new JButton();
				getContentPane().add(btnAdministrarRoles);
				btnAdministrarRoles.setText("Administrar Roles");
				btnAdministrarRoles.setBounds(12, 14, 300, 23);
				btnAdministrarRoles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						AdministrarRoles a = new AdministrarRoles();
						a.setVisible(true);
					}
				});
			}
			pack();
			this.setSize(430, 117);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
