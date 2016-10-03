package vista;

//import java.util.Vector;

public class ClienteView {

	private int nroCliente;
	private String nombre;
	private String domicilio;
	private String telefono;
	private String mail;
	private boolean activo;
	
	public ClienteView(int nroCliente, String nombre, String domicilio, String mail, String telefono, boolean activo) {
		this.nroCliente = nroCliente;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
		this.activo = activo;
	}
	
	public int getNroCliente() {
		return this.nroCliente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public String getMail() {
		return this.mail;
	}
	
	public String getTelefono() {
		return this.telefono;
	}

	public boolean getActivo() {
		return this.activo;
	}
	
	/*public Vector<String> toVector()
	{
		Vector<String> v = new Vector<String>();
		v.add(Integer.toString(nroCliente));		
		v.add(nombre);		
		v.add(domicilio);
		v.add(telefono);
		v.add(mail);
		v.add(String.valueOf(activo));
		return v;
	}*/
}
