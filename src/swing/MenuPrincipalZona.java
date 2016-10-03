package swing;

import javax.swing.WindowConstants;

public class MenuPrincipalZona extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	public MenuPrincipalZona() {
		super();
		initGUI();
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Zona");
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
