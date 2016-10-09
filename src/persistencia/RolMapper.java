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
	public Rol selectOne(int id) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Rol "
				+ "WHERE id = ? "
				+ "AND activo = 1", 
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
				"select * from dbo.Rol where activo = ? order by nombre", 
				s -> s.setBoolean(1, true), 
				rs -> new Rol(
						rs.getInt("id"), 
						rs.getString("nombre"), 
						rs.getString("descripcion"), 
						rs.getString("vista"))
			);
	}
}
