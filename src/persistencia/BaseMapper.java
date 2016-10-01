package persistencia;
//import java.util.Vector;

public abstract class BaseMapper<T>
{
	public abstract void insert (T o);
	public abstract void update (T o);
	public abstract void delete (T d);
	//public abstract Vector<T> selectAll ();
	public abstract T selectOne (Object o);
}
