package persistencia;

import negocio.Producto;

public class ProductoMapper extends BaseMapper {

	private static ProductoMapper instance;
	
	private ProductoMapper() {
	}
	
	public static ProductoMapper getInstancia()
	{
		if (instance == null)
			instance = new ProductoMapper();
		
		return instance;
	}
	
	public void insert(Producto o) {
		tryCommand("INSERT INTO dbo.Producto (codigoProducto, codigoPublicacion, titulo, descripcion, precio, activo) VALUES (?,?,?,?,?,?)", s -> {
			s.setInt(1, o.getCodigoProducto());
			s.setString(2, o.getCodigoPublicacion());
			s.setString(3, o.getTitulo());
			s.setString(4, o.getDescripcion());
			s.setFloat(5, o.getPrecio());
			s.setBoolean(6, true);
		});
	}

	public void update(Producto o) {
		tryCommand("UPDATE dbo.Producto "
				+ "SET titulo = ?, "
				+ "SET descripcion = ?, "
				+ "SET precio = ? "
				+ "WHERE codigoProducto = ? "
				+ "AND activo = 1", s -> {
			s.setString(1, o.getTitulo());
			s.setString(2, o.getDescripcion());
			s.setFloat(3, o.getPrecio());
			s.setInt(4, o.getCodigoProducto());
		});
	}

	public void delete(Producto r) {
		tryCommand("UPDATE dbo.Producto "
				+ "SET activo = 0 "
				+ "WHERE codigoProducto = ?", s -> {
			s.setInt(1, r.getCodigoProducto());
		});
	}

	public Producto selectOne(int codigoProducto) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Producto "
				+ "WHERE codigoProducto = ? "
				+ "AND activo = 1", 
				s -> s.setInt(1, codigoProducto), 
				rs -> new Producto(
						rs.getInt("codigoProducto"),
						rs.getString("codigoPublicacion"), 
						rs.getString("titulo"), 
						rs.getString("descripcion"), 
						rs.getFloat("precio"))
			);
	}

	private int ultimoId = -1;
	
	public int getUltimoId(){
		if (ultimoId < 0)
			ultimoId = tryQuery("SELECT ISNULL(MAX(idProducto),0) AS idProducto FROM dbo.Producto", rs -> rs.getInt("idProducto"));
		
		ultimoId++;
		return ultimoId;
	}
}
