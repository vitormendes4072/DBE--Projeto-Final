package br.com.fiap.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.fiap.entity.Endereco;
import br.com.fiap.exceptions.EntityNotFoundException;

public interface EnderecoDao extends GenericDao<Endereco, Integer>{

	List<Endereco> findByCep(BigInteger cep) throws EntityNotFoundException;
	Endereco findByLogradouroAndNumLogradouro (String logradouro, int numLogradouro) throws EntityNotFoundException;

}
