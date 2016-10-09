package utils;

public interface Action<T>{
	void apply(T t) throws Exception;
}
