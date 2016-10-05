package persistencia;

import negocio.Cliente;

public class ClienteMapper extends BaseMapper<Cliente> {

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
	
	@Override
	public void insert(Cliente c) {
		tryExecuteCommand("insert into dbo.Cliente (?,?,?,?,?)", s -> {
			s.setInt(1,c.getNroCliente());
			s.setString(2, c.getNombre());
			s.setString(3,c.getDomicilio());
			s.setString(4, c.getTelefono());
			s.setString(5,c.getMail());
		});
	}

	@Override
	public void update(Cliente c) {
		tryExecuteCommand("update dbo.Cliente set domicilio = ?, telefono = ?, mail = ? where nroCliente = ?", s -> {
			s.setString(1, c.getDomicilio());
			s.setString(2, c.getTelefono());
			s.setString(3, c.getMail());			
			s.setInt(4, c.getNroCliente());			
		});	
	}

	@Override
	public void delete(Cliente c) {
		tryExecuteCommand("update dbo.Cliente set activo = 0 where nroCliente = ?", s -> s.setInt(1, c.getNroCliente()));
	}
	
	@Override
	public Cliente selectOne(Object id)
	{
		return tryExecuteQuery("select * from dbo.Cliente where nroCliente = ?", 
				s -> s.setInt(1, (int)id), 
				rs -> new Cliente(
						rs.getInt("nroCliente"), 
						rs.getString("nombre"), 
						rs.getString("domicilio"), 
						rs.getString("telefono"), 
						rs.getString("mail")));
	}
}
