package br.com.fiap.dao;

import java.math.BigInteger;

import br.com.fiap.entity.Usuario;
import br.com.fiap.exceptions.EntityNotFoundException;

public interface UsuarioDao  extends GenericDao<Usuario, Integer>{
	
	Usuario findByCpf(String cpf) throws EntityNotFoundException;

	

}
