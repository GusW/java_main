package br.unibh.escola.negocio;

import java.util.List;

import br.unibh.escola.entidades.Sala;

public interface DAO<T, K> {
	
	public T insert(T t) throws Exception;
	public T update(T t) throws Exception;
	public void delete(T t) throws Exception;
	public T find(K k) throws Exception;
	public List<T> findAll() throws Exception;
	public List<T> findByName(String name) throws Exception;
}