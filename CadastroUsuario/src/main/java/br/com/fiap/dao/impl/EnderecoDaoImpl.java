package br.com.fiap.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EnderecoDao;
import br.com.fiap.entity.Endereco;
import br.com.fiap.exceptions.EntityNotFoundException;

public class EnderecoDaoImpl extends GenericDaoImpl<Endereco, Integer> implements EnderecoDao {

	EntityManager em;
	
	public EnderecoDaoImpl(EntityManager em) {
		super(em);
		this.em = em;
	}

	@Override
	public List<Endereco> findByCep(BigInteger cep) throws EntityNotFoundException {
		List<Endereco> lEnd = em.createQuery("select Endereco from Endereco where Endereco.cep = ?", Endereco.class).setParameter("cep", cep).getResultList();
		if (lEnd == null || lEnd.size() == 0)
			throw new EntityNotFoundException();
		return lEnd;
	}

	@Override
	public Endereco findByLogradouroAndNumLogradouro(String logradouro, int numLogradouro)
			throws EntityNotFoundException {
		Endereco end = em.createQuery("select e from Endereco e where e.logradouro = :logradouro and e.numLogradouro = :numLogradouro", Endereco.class)
				.setParameter("numLogradouro", numLogradouro)
				.setParameter("logradouro", logradouro).getSingleResult();
		
		if(end == null) throw new EntityNotFoundException();
		
		return end;
	}

}
