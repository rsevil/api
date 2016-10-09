package persistencia;
import java.util.Vector;

import negocio.Usuario;

public class UsuarioMapper extends BaseMapper {

	private static UsuarioMapper instance;
	
	private UsuarioMapper() {
		
	}
	
	public static UsuarioMapper getInstancia()
	{
		if (instance == null)
			instance = new UsuarioMapper();
		return instance;
	}

	public void update(Usuario o) {
		tryCommand("UPDATE dbo.Usuario "
				+ "SET nombre = ?, "
				+ "contrasenia = ?, "
				+ "idRol = ? "
				+ "WHERE id = ? "
				+ "AND activo = 1", s -> {
			s.setString(1, o.getNombre());
			s.setString(2, o.getContrasenia());
			s.setInt(3, o.getRol().getId());
			s.setInt(4, o.getId());
		});
	}

	public void delete(Usuario o) {
		tryCommand("UPDATE dbo.Usuario SET activo = 0 WHERE id = ? AND activo = 1", s -> s.setInt(1, o.getId()));
	}

	public Usuario selectOne(String nombreUsuario) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Usuario "
				+ "WHERE nombre = ? "
				+ "AND activo = 1", 
				s -> s.setString(1, nombreUsuario.toString()), 
				rs -> new Usuario(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("contrasenia"),
						RolMapper.getInstancia().selectOne(rs.getInt("idRol"))));
	}
	
	public Usuario selectById(int id) {
		return tryQuery(
				"select * from dbo.Usuario where id = ?", 
				s -> s.setInt(1, id), 
				rs -> new Usuario(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("contrasenia"),
						RolMapper.getInstancia().selectOne(rs.getInt("idRol")))
				);
	}
	
	public Vector<Usuario> selectAll() {
		return tryQueryMany(
				"select * from dbo.Usuario where activo = ? order by nombre", 
				s -> s.setBoolean(1, true), 
				rs -> new Usuario(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("contrasenia"),
						RolMapper.getInstancia().selectOne(rs.getInt("idRol"))));
	}
}
