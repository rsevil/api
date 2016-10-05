package persistencia;
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
		tryExecuteCommand("insert into dbo.Usuario (?,?,?,?,?)", s ->{
			s.setInt(1, o.getId());
			s.setString(2, o.getNombre());
			s.setString(3, o.getContrasenia());
			s.setBoolean(4, o.getActivo());
			// TODO Puede que sea null?
			s.setInt(5, o.getRol().getIdRol());
		});
	}

	@Override
	public void update(Usuario o) {
		tryExecuteCommand("update dbo.Usuario set nombre=?,set contrasenia=?,set activo=?,set idRol=? where id=?", s -> {
			s.setString(1, o.getNombre());
			s.setString(2, o.getContrasenia());
			s.setBoolean(3, o.getActivo());
			s.setInt(4, o.getRol().getIdRol());
			s.setInt(5, o.getId());
		});
	}

	@Override
	public void delete(Usuario o) {
		tryExecuteCommand("delete dbo.Usuario where id=?", s -> s.setInt(1, o.getId()));
	}

	@Override
	public Usuario selectOne(Object nombreUsuario) {
		return tryExecuteQuery(
				"select * from dbo.Usuario where nombre = ?", 
				s -> s.setString(1, nombreUsuario.toString()), 
				rs -> new Usuario(
						RolMapper.getInstancia().selectOne(rs.getInt("idRol")),
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("contrasenia"),
						rs.getBoolean("activo")));
	}

}
