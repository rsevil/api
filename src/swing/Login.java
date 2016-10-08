package swing;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import vista.RolView;
import controlador.SistemaAdministracionReclamos;
import enums.ExitCodes;


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
public class Login extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JButton btnLogin;
	private JPasswordField txtContrasenia;
	private JLabel lblContrasenia;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login inst = new Login();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Login() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			FormLayout thisLayout = new FormLayout(
					"6dlu, max(p;5dlu), 118dlu", 
					"max(p;5dlu), max(p;5dlu), max(p;5dlu)");
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Sistema de Administración de Reclamos");
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			{
				lblUsuario = new JLabel();
				getContentPane().add(lblUsuario, new CellConstraints("2, 1, 1, 1, default, default"));
				lblUsuario.setText("Usuario");
				lblUsuario.setPreferredSize(new java.awt.Dimension(41, 23));
			}
			{
				txtUsuario = new JTextField();
				getContentPane().add(txtUsuario, new CellConstraints("3, 1, 1, 1, default, default"));
			}
			{
				lblContrasenia = new JLabel();
				getContentPane().add(lblContrasenia, new CellConstraints("2, 2, 1, 1, default, default"));
				lblContrasenia.setText("Contraseña");
				lblContrasenia.setPreferredSize(new java.awt.Dimension(78, 23));
			}
			{
				txtContrasenia = new JPasswordField();
				getContentPane().add(txtContrasenia, new CellConstraints("3, 2, 1, 1, default, default"));
			}
			{
				btnLogin = new JButton();
				getContentPane().add(btnLogin, new CellConstraints("3, 3, 1, 1, default, default"));
				GridLayout btnLoginLayout = new GridLayout(1, 1);
				btnLoginLayout.setColumns(1);
				btnLoginLayout.setVgap(5);
				btnLoginLayout.setHgap(5);
				btnLogin.setLayout(btnLoginLayout);
				btnLogin.setText("Ingresar");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) 
					{
						int rdo = SistemaAdministracionReclamos.getInstancia().iniciarSesion(txtUsuario.getText(), String.valueOf(txtContrasenia.getPassword()));
					
						String mensaje = "";
						switch(rdo) {
						case ExitCodes.OK: {
							mensaje = "Datos correctos. Bienvenido!";	
							break;
						}
						case ExitCodes.DATOS_INGRESO_INCORRECTOS: {
							mensaje = "El usuario o contraseña es inválido.";	
							break;
						}
						default: {
							break;
						}	
					}
						JOptionPane.showMessageDialog(null, mensaje);						
						if (rdo == ExitCodes.OK) {							
							txtUsuario.setText("");
							txtContrasenia.setText("");
							
							// Cierro la ventana actual.
							dispose();
							
							RolView rol = SistemaAdministracionReclamos.getInstancia().obtenerRolDelUsuarioLogueado();
							
							if (rol != null) {
						        try {
						        	// Levanto la ventana según la configuración en la base de datos.
									Class.forName(rol.getVista()).newInstance();
								} 
						        catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
						        catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
							}
						}
					}
				}
			);
			}
			getRootPane().setDefaultButton(btnLogin);
			pack();
			this.setSize(367, 117);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
