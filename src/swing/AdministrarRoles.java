package swing;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import controlador.SistemaAdministracionReclamos;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import java.util.Vector;

import vista.*;
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
public class AdministrarRoles extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnAsignarRol;
	private JComboBox<ComboItem<Integer, String>> cmbRol;
	private JComboBox<ComboItem<Integer, String>> cmbUsuario;
	private JLabel lblRol;
	private JLabel lblUsuario;
	private Canvas canvas;
	
	private Vector<UsuarioView> usuariosView;
	private Vector<RolView> rolesView;

	public AdministrarRoles() {
		super();
		initGUI();
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Administrar Roles");
			FormLayout thisLayout = new FormLayout(
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), 155dlu", 
					"max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5px), max(p;5px), max(p;5dlu)");
			getContentPane().setLayout(thisLayout);
			getContentPane().setBackground(new java.awt.Color(221,221,255));
			{
				canvas = new Canvas();
				getContentPane().add(canvas, new CellConstraints("2, 1, 1, 1, default, default"));
			}
			{
				lblUsuario = new JLabel();
				getContentPane().add(lblUsuario, new CellConstraints("2, 2, 1, 1, default, default"));
				lblUsuario.setText("Usuario");
			}
			{
				lblRol = new JLabel();
				getContentPane().add(lblRol, new CellConstraints("2, 4, 1, 1, default, default"));
				lblRol.setText("Rol");
			}
			{
				this.usuariosView = SistemaAdministracionReclamos.getInstancia().listarUsuarios();
				Vector<ComboItem<Integer, String>> itemsUsuarios;
				cmbUsuario = new JComboBox<ComboItem<Integer, String>>();
				if (usuariosView != null && usuariosView.size() > 0) {
					itemsUsuarios =  new Vector<ComboItem<Integer, String>>();
					itemsUsuarios.add(new ComboItem<Integer, String>(0, "Seleccione...")); 
					for (UsuarioView uv : usuariosView) {
						itemsUsuarios.add(new ComboItem<Integer, String>(uv.getId(), uv.getNombre()));
					}
					
					cmbUsuario = new JComboBox<ComboItem<Integer, String>>(itemsUsuarios);
				} else {
					cmbUsuario = new JComboBox<ComboItem<Integer, String>>();
				}
				
				getContentPane().add(cmbUsuario, new CellConstraints("4, 2, 1, 1, default, default"));
				
				cmbUsuario.addActionListener(new ActionListener()
				{
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent evt) 
					{
						ComboItem<Integer, String> usuarioSeleccionado = (ComboItem<Integer, String>)cmbUsuario.getSelectedItem(); 
						ComboItem<Integer, String> rolASeleccionar; 
						
						if (usuarioSeleccionado.getValue() != 0) {
							for (UsuarioView uv : usuariosView) {
								if (uv.getId() == usuarioSeleccionado.getValue()) {
							        for (int i = 0; i < cmbRol.getItemCount(); i++)
							        {
							            rolASeleccionar = (ComboItem<Integer, String>)cmbRol.getItemAt(i);
							            if (rolASeleccionar.getValue() == uv.getRolView().getId())
							            {
							            	cmbRol.setSelectedItem(rolASeleccionar);
							                break;
							            }
							        }
								}
							}
						}
						else {
							cmbRol.setSelectedIndex(0);
						}
					}
				});
			}
			{
				this.rolesView = SistemaAdministracionReclamos.getInstancia().listarRoles();
				Vector<ComboItem<Integer, String>> itemsRoles;
				cmbRol = new JComboBox<ComboItem<Integer, String>>();
				if (rolesView  != null && rolesView.size() > 0) {
					itemsRoles =  new Vector<ComboItem<Integer, String>>();
					itemsRoles.add(new ComboItem<Integer, String>(0, "Seleccione...")); 
					for (RolView rv : rolesView) {
						itemsRoles.add(new ComboItem<Integer, String>(rv.getId(), rv.getDescripcion()));
					}
					
					cmbRol = new JComboBox<ComboItem<Integer, String>>(itemsRoles);
				} else {
					cmbRol = new JComboBox<ComboItem<Integer, String>>();
				}
				
				getContentPane().add(cmbRol, new CellConstraints("4, 4, 1, 1, default, default"));
			}
			{
				btnAsignarRol = new JButton();
				getContentPane().add(btnAsignarRol, new CellConstraints("4, 6, 1, 1, default, default"));
				btnAsignarRol.setText("Asignar rol");
				btnAsignarRol.addActionListener(new ActionListener()
				{
					@SuppressWarnings("unchecked")
					public void actionPerformed(ActionEvent evt) 
					{
						ComboItem<Integer, String> usuarioSeleccionado = (ComboItem<Integer, String>)cmbUsuario.getSelectedItem(); 
						ComboItem<Integer, String> rolSeleccionado = (ComboItem<Integer, String>)cmbRol.getSelectedItem();
						
						if (usuarioSeleccionado.getValue() != 0 && rolSeleccionado.getValue() != 0) {
							int rdo = SistemaAdministracionReclamos.getInstancia().asignarRol(usuarioSeleccionado.getValue(), rolSeleccionado.getValue());					
							String mensaje = "";
							switch(rdo) {
								case ExitCodes.OK: {
									mensaje = "El rol se ha asignado con exito.";
									break;
								}
								case ExitCodes.ERROR_GENERICO: {
									mensaje = "Hubo un error al asignar el rol.";
									break;
								}
								default: {
									break;
								}
							}
							
							JOptionPane.showMessageDialog(null, mensaje);
							if (rdo == ExitCodes.OK){
								cmbUsuario.setSelectedIndex(0);
								cmbRol.setSelectedIndex(0);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario y un rol.");
						}
					}
				});
			}
			pack();
			this.setSize(400, 142);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
