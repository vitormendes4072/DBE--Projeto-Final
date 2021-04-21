package br.com.fiap.dao.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entity.Usuario;
import br.com.fiap.exceptions.EntityNotFoundException;

public class UsuarioDaoImpl extends GenericDaoImpl<Usuario, Integer> implements UsuarioDao {

	private EntityManager em;
	
	public UsuarioDaoImpl(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	public Usuario findByCpf(String cpf) throws EntityNotFoundException {
		Usuario usu = em.createQuery("select u from Usuario u where u.cpf = :cpf", Usuario.class).setParameter("cpf", cpf).getSingleResult();
		if (usu == null)
			throw new EntityNotFoundException();
		return usu;
	}

}
