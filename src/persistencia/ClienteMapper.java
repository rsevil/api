package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import negocio.Cliente;

public class ClienteMapper extends BaseMapper<Cliente> {

	private static ClienteMapper instance;
	
	private ClienteMapper() {
		
	}
	
	public static ClienteMapper getInstancia()
	{
		if (instance == null)
			instance = new ClienteMapper();
		return instance;
	}
	
	@Override
	public void insert(Cliente c) {
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("insert into A_Interactivas_XX.dbo.Clientes values (?,?,?,?,?)");
			s.setInt(1,c.getNroCliente());
			s.setString(2, c.getNombre());
			s.setString(3,c.getDomicilio());
			s.setString(4, c.getTelefono());
			s.setString(5,c.getMail());
			s.execute();
			PoolConnection.getPoolConnection().releaseConnection(con);
		}
		catch (Exception e)
		{
			
		}
	}

	@Override
	public void update(Cliente c) {
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update A_Interactivas_XX.dbo.Clientes set domicilio = ?, mail = ? where nroCliente = ?");
			s.setString(1, c.getDomicilio());
			s.setString(2, c.getMail());			
			s.setInt(3, c.getNroCliente());
			s.execute();
			PoolConnection.getPoolConnection().releaseConnection(con);
		}
		catch (Exception e)
		{
			
		}		
	}

	@Override
	public void delete(Cliente c) {
		try
		{
			Connection con = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = con.prepareStatement("update A_Interactivas_XX.dbo.Clientes set activo = 0 where nroCliente = ?");
			s.setInt(1, c.getNroCliente());
			s.execute();
			PoolConnection.getPoolConnection().releaseConnection(con);
		}
		catch (Exception e)
		{
			
		}
	}
	
	@Override
	public Cliente selectOne(Object numero)
	{
		try
		{			
			Cliente rta = null;
			Connection c = PoolConnection.getPoolConnection().getConnection();
			Statement s = c.createStatement();
			ResultSet result = s.executeQuery("Select * from A_Interactivas_XX.dbo.clientes where nroCliente = " + numero.toString());
			
			while (result.next())
			{
				int nroCliente = result.getInt(1);
				String nombre = result.getString(2);
				String domicilio = result.getString(3);
				String telefono = result.getString(4);
				String mail = result.getString(5);
				rta = new Cliente(nroCliente, nombre, domicilio, telefono, mail);						
			}
			
			PoolConnection.getPoolConnection().releaseConnection(c);
			return rta;
		}
		catch(Exception e)
		{
			
		}
		
		return null;
	}
}
