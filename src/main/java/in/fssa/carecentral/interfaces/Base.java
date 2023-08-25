package in.fssa.carecentral.interfaces;

import java.util.Set;

public interface Base<T> {
	public abstract int create(T newT);
	public abstract Set<T> findAll();
	public abstract void update(int id , T newT);
	public abstract void delete(int id);
	public abstract  <T>T findById(int id);
}
