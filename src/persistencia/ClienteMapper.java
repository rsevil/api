package persistencia;

import negocio.Cliente;

public class ClienteMapper extends BaseMapper {

	private static ClienteMapper instance;
	
	private ClienteMapper() {
		
	}
	
	public static ClienteMapper getInstancia()
	{
		if (instance == null) {
			instance = new ClienteMapper();
		}
		return instance;
	}
	
	public void insert(Cliente c) {
		tryCommand("INSERT dbo.Cliente (nroCliente, nombre, domicilio, telefono, mail, activo) VALUES (?,?,?,?,?)", s -> {
			s.setInt(1,c.getNroCliente());
			s.setString(2, c.getNombre());
			s.setString(3,c.getDomicilio());
			s.setString(4, c.getTelefono());
			s.setString(5,c.getMail());
			s.setBoolean(6, true);
		});
	}

	public void update(Cliente c) {
		tryCommand(
				"UPDATE dbo.Cliente "
				+ "SET nombre = ?, "
				+ "SET domicilio = ?, "
				+ "SET telefono = ?, "
				+ "SET mail = ? "
				+ "WHERE nroCliente = ? "
				+ "AND activo = 1", 
				s -> {
					s.setString(1, c.getNombre());
					s.setString(2, c.getDomicilio());
					s.setString(3, c.getTelefono());
					s.setString(4, c.getMail());			
					s.setInt(5, c.getNroCliente());			
				});	
	}

	public void delete(Cliente c) {
		tryCommand("UPDATE dbo.Cliente SET activo = 0 where nroCliente = ?", s -> s.setInt(1, c.getNroCliente()));
	}
	
	public Cliente selectOne(int nroCliente)
	{
		return tryQuery("SELECT * "
				+ "FROM dbo.Cliente "
				+ "WHERE nroCliente = ? "
				+ "AND activo = 1", 
				s -> s.setInt(1, nroCliente), 
				rs -> new Cliente(
						rs.getInt("nroCliente"), 
						rs.getString("nombre"), 
						rs.getString("domicilio"), 
						rs.getString("telefono"), 
						rs.getString("mail")));
	}
}
