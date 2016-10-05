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
		tryCommand("insert into dbo.Producto (?,?,?,?,?)", s -> {
			s.setString(1, o.getCodigoPublicacion());
			s.setString(2, o.getTitulo());
			s.setString(3, o.getDescripcion());
			s.setFloat(4, o.getPrecio());
			s.setBoolean(5, o.getActivo());
		});
	}

	public void update(Producto o) {
		tryCommand("update dbo.Producto set titulo=?, set descripcion=?, set precio=?, set activo=? where codigoProducto=?", s -> {
			s.setString(1, o.getTitulo());
			s.setString(2, o.getDescripcion());
			s.setFloat(3, o.getPrecio());
			s.setBoolean(4, o.getActivo());
			s.setString(5, o.getCodigoPublicacion());
		});
	}

	public void delete(Producto r) {
		tryCommand("update dbo.Producto set activo=0 where codigoProducto=?", s -> {
			s.setString(1, r.getCodigoPublicacion());
		});
	}

	public Producto selectOne(String codigoProducto) {
		return tryQuery(
				"select * from dbo.Producto where codigoProducto = ?", 
				s -> s.setString(1, codigoProducto), 
				rs -> new Producto(
						rs.getString("codigoProducto"), 
						rs.getString("titulo"), 
						rs.getString("descripcion"), 
						rs.getFloat("precio"), 
						rs.getBoolean("activo"))
			);
	}
}
