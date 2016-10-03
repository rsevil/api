package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import negocio.Rol;
import negocio.Usuario;

public class UsuarioMapper extends BaseMapper<Usuario> {

	private static UsuarioMapper instance;
	
	private UsuarioMapper() {
		
	}
	
	public static UsuarioMapper getInstancia()
	{
		if (instance == null)
			instance = new UsuarioMapper();
		return instance;
	}
	
	@Override
	public void insert(Usuario o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuario o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuario d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario selectOne(Object nombreUsuario) {
		try
		{			
			Usuario rta = null;
			Connection c = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement s = c.prepareStatement("select * from dbo.Usuario where nombre = ?");
			s.setString(1, nombreUsuario.toString());
			
			ResultSet result = s.executeQuery();
			
			while (result.next())
			{
				int id = result.getInt(1);
				String nombre = result.getString(2);
				String contrasenia = result.getString(3);
				boolean activo = result.getBoolean(4);
				
				Rol rol = RolMapper.getInstancia().selectOne(result.getInt(5));
				
				rta = new Usuario(rol, id, nombre, contrasenia, activo);						
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
