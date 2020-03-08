package br.unibh.persistencia;

import java.util.List;

public interface DAO<T,K> {
	
	public T find (Long id);
	public void insert(T t);
	public void update(T t);
	public void delete(T t);
	public List<T> findAll();

}
