package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import negocio.Rol;

public class RolMapper extends BaseMapper<Rol> {

	private static RolMapper instance;
	
	private RolMapper() {
		
	}
	
	public static RolMapper getInstancia()
	{
		if (instance == null) {
			instance = new RolMapper();
		}
		return instance;
	}
	
	@Override
	public void insert(Rol o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Rol o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Rol d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rol selectOne(Object idRol) {
		try
		{			
			Rol rta = null;
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement("select * from dbo.Rol where id = ?");
			s.setInt(1, (int)idRol);
			
			ResultSet result = s.executeQuery();
			
			while (result.next())
			{
				int id = result.getInt(1);
				String nombre = result.getString(2);
				String descripcion = result.getString(3);
				boolean activo = result.getBoolean(4);
				
				rta = new Rol(id, nombre, descripcion, activo);						
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
