package persistencia;

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
		tryExecuteCommand("insert into dbo.Rol (?,?,?,?,?)", s -> {
			s.setInt(1, o.getIdRol());
			s.setString(2, o.getNombre());
			s.setString(3, o.getDescripcion());
			s.setBoolean(4, o.getActivo());
			s.setString(5, o.getVista());
		});
	}

	@Override
	public void update(Rol o) {
		tryExecuteCommand("update dbo.Rol set nombre=?, set descripcion=?, set activo=?, set vista=? where id=?", s -> {
			s.setString(1, o.getNombre());
			s.setString(2, o.getDescripcion());
			s.setBoolean(3, o.getActivo());
			s.setString(4, o.getVista());
			s.setInt(5, o.getIdRol());
		});
	}

	@Override
	public void delete(Rol r) {
		tryExecuteCommand("update dbo.Rol set activo=0 where id=?", s -> {
			s.setInt(1, r.getIdRol());
		});
	}

	@Override
	public Rol selectOne(Object id) {
		return tryExecuteQuery(
				"select * from dbo.Rol where id = ?", 
				s -> s.setInt(1, (int)id), 
				rs -> new Rol(
						rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getString("descripcion"), 
						rs.getBoolean("activo"), 
						rs.getString("vista"))
			);
	}
}
