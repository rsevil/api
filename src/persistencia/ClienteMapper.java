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
		tryCommand("insert into dbo.Cliente (?,?,?,?,?)", s -> {
			s.setInt(1,c.getNroCliente());
			s.setString(2, c.getNombre());
			s.setString(3,c.getDomicilio());
			s.setString(4, c.getTelefono());
			s.setString(5,c.getMail());
		});
	}

	public void update(Cliente c) {
		tryCommand("update dbo.Cliente set domicilio = ?, telefono = ?, mail = ? where nroCliente = ?", s -> {
			s.setString(1, c.getDomicilio());
			s.setString(2, c.getTelefono());
			s.setString(3, c.getMail());			
			s.setInt(4, c.getNroCliente());			
		});	
	}

	public void delete(Cliente c) {
		tryCommand("update dbo.Cliente set activo = 0 where nroCliente = ?", s -> s.setInt(1, c.getNroCliente()));
	}
	
	public Cliente selectOne(int nroCliente)
	{
		return tryQuery("select * from dbo.Cliente where nroCliente = ?", 
				s -> s.setInt(1, nroCliente), 
				rs -> new Cliente(
						rs.getInt("nroCliente"), 
						rs.getString("nombre"), 
						rs.getString("domicilio"), 
						rs.getString("telefono"), 
						rs.getString("mail")));
	}
}
