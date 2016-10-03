package swing;

import javax.swing.WindowConstants;

public class MenuPrincipalCallCenter extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	
	public MenuPrincipalCallCenter() {
		super();
		initGUI();
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Menú Call Center");
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
