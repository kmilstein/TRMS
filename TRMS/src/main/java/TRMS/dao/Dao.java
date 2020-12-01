package TRMS.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
	
	Optional<T> get(long id);
	
	List<T> getAll();

	void save(T t);
	
	void update(long id, T t);
	
	void delete(T t);
}
