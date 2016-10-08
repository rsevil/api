package persistencia;

import java.util.Vector;

import negocio.Rol;

public class RolMapper extends BaseMapper {

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

//	public void insert(Rol o) {
//		tryCommand("insert into dbo.Rol (?,?,?,?,?)", s -> {
//			s.setInt(1, o.getIdRol());
//			s.setString(2, o.getNombre());
//			s.setString(3, o.getDescripcion());
//			s.setBoolean(4, o.getActivo());
//			s.setString(5, o.getVista());
//		});
//	}
	
	public void update(Rol o) {
		tryCommand("update dbo.Rol set nombre=?, set descripcion=?, set vista=? where id=?", s -> {
			s.setString(1, o.getNombre());
			s.setString(2, o.getDescripcion());
			s.setString(3, o.getVista());
			s.setInt(4, o.getId());
		});
	}

	public void delete(Rol r) {
		tryCommand("update dbo.Rol set activo=0 where id=?", s -> {
			s.setInt(1, r.getId());
		});
	}

	public Rol selectOne(int id) {
		return tryQuery(
				"select * from dbo.Rol where id = ?", 
				s -> s.setInt(1, id), 
				rs -> new Rol(
						rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getString("descripcion"), 
						rs.getString("vista"))
			);
	}
	
	public Vector<Rol> selectAll() {
		return tryQueryMany(
				"select * from dbo.Rol where activo = ?", 
				s -> s.setBoolean(1, true), 
				rs -> new Rol(
						rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getString("descripcion"), 
						rs.getString("vista"))
			);
	}
}
