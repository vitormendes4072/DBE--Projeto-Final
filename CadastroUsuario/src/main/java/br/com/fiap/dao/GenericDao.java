package br.com.fiap.dao;


import br.com.fiap.exceptions.CommitException;
import br.com.fiap.exceptions.EntityNotFoundException;

public interface GenericDao<E,K> {

	void create(E entidade);
	
	E findById(K id) throws EntityNotFoundException;
	
	void update(E entidade);
	
	void delete(K id) throws EntityNotFoundException;
	
	void commit() throws CommitException;
	
}