package swing;

import javax.swing.WindowConstants;

public class MenuPrincipalZona extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	public MenuPrincipalZona() {
		super();
		initGUI();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Zona");
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
