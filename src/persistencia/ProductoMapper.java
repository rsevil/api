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
		tryCommand("INSERT INTO dbo.Producto (codigoProducto, titulo, descripcion, precio, activo) VALUES (?,?,?,?,?)", s -> {
			s.setString(1, o.getCodigoProducto());
			s.setString(2, o.getTitulo());
			s.setString(3, o.getDescripcion());
			s.setFloat(4, o.getPrecio());
			s.setBoolean(5, true);
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
			s.setString(4, o.getCodigoProducto());
		});
	}

	public void delete(Producto r) {
		tryCommand("UPDATE dbo.Producto "
				+ "SET activo = 0 "
				+ "WHERE codigoProducto = ?", s -> {
			s.setString(1, r.getCodigoProducto());
		});
	}

	public Producto selectOne(String codigoProducto) {
		return tryQuery(
				"SELECT * "
				+ "FROM dbo.Producto "
				+ "WHERE codigoProducto = ? "
				+ "AND activo = 1", 
				s -> s.setString(1, codigoProducto), 
				rs -> new Producto(
						rs.getString("codigoProducto"), 
						rs.getString("titulo"), 
						rs.getString("descripcion"), 
						rs.getFloat("precio"))
			);
	}
}
