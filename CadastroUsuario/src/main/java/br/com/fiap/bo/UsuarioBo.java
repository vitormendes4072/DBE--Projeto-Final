package br.com.fiap.bo;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import br.com.fiap.dao.EnderecoDao;
import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Genero;
import br.com.fiap.entity.Usuario;
import br.com.fiap.exceptions.EntityNotFoundException;
import net.sf.json.JSONObject;

public class UsuarioBo {

	UsuarioDao usuarioDao;
	EnderecoDao enderecoDao;

	public UsuarioBo() {

	}

	public UsuarioBo(UsuarioDao usuarioDao, EnderecoDao enderecoDao) {
		this.usuarioDao = usuarioDao;
		this.enderecoDao = enderecoDao;
	}

	public JSONObject saveUsuario(JSONObject usuarioJson) {
		JSONObject json = new JSONObject();
		try {
			Usuario u = null;
			try {
				u = usuarioDao.findByCpf(usuarioJson.getString("cpf"));
			} catch (NullPointerException e) {
				
			}

			if (u == null) {
				Usuario novoUsu = new Usuario();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				novoUsu.setCpf(usuarioJson.getString("cpf"));
				novoUsu.setNomeUsuario(usuarioJson.getString("nomeUsuario"));
				novoUsu.setDataNasc(sdf.parse(usuarioJson.getString("dataNascimento")));
				if (usuarioJson.getString("genero").toUpperCase().equals("MASCULINO")) {
					novoUsu.setGenero(Genero.H);
				} else if (usuarioJson.getString("genero").toUpperCase().equals("FEMININO")) {
					novoUsu.setGenero(Genero.M);
				} else {
					novoUsu.setGenero(Genero.OUTRO);
				}
				Endereco e = new Endereco();

				e.setLogradouro(usuarioJson.getString("logradouro"));
				e.setNumLogradouro(usuarioJson.getInt("numLogradouro"));
				e.setBairro(usuarioJson.getString("bairro"));
				e.setCidade(usuarioJson.getString("cidade"));
				e.setEstado(usuarioJson.getString("estado"));
				e.setCep(usuarioJson.getString("cep").toString().replace("-", ""));

				this.enderecoDao.create(e);
				this.enderecoDao.commit();

				e = this.enderecoDao.findByLogradouroAndNumLogradouro(usuarioJson.getString("logradouro"),
						usuarioJson.getInt("numLogradouro"));

				novoUsu.setEndereco(e);
				novoUsu.setTelefone(usuarioJson.getString("telefone"));
				novoUsu.setCpf(usuarioJson.getString("cpf"));

				this.usuarioDao.create(novoUsu);
				this.usuarioDao.commit();

				json.put("message", "Salvo com sucesso!");
				json.put("erro", false);

			} else if (u != null) {
				json.put("message", "CPF já existe");
				json.put("erro", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	public JSONObject updateUsuario(JSONObject usuarioJson) {
		JSONObject json = new JSONObject();
		try {
			Usuario novoUsu = null;
			try {
				novoUsu = this.usuarioDao.findByCpf(usuarioJson.getString("cpf"));
				
			}catch (EntityNotFoundException e) {
				
			}
					
			if (novoUsu == null) {
				json.put("message", "CPF não encontrado");
				json.put("erro", true);
				
			} else {

				novoUsu.setNomeUsuario(usuarioJson.getString("nomeUsuario"));
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				novoUsu.setDataNasc(sdf.parse(usuarioJson.getString("dataNasc")));
				if (usuarioJson.getString("genero").equals("MASCULINO")) {
					novoUsu.setGenero(Genero.H);
				} else if (usuarioJson.getString("genero").equals("FEMININO")) {
					novoUsu.setGenero(Genero.M);
				} else {
					novoUsu.setGenero(Genero.OUTRO);
				}
				Endereco e = novoUsu.getEndereco();

				e.setLogradouro(usuarioJson.getString("logradouro"));
				e.setNumLogradouro(usuarioJson.getInt("numLogradouro"));
				e.setBairro(usuarioJson.getString("bairro"));
				e.setCidade(usuarioJson.getString("cidade"));
				e.setEstado(usuarioJson.getString("estado"));
				e.setCep(usuarioJson.getString("cep").toString().replace("-", ""));

				this.enderecoDao.update(e);

				novoUsu.setTelefone(usuarioJson.getString("telefone"));

				this.usuarioDao.update(novoUsu);
				this.usuarioDao.commit();

				json.put("message", "Alterado com sucesso!");
				json.put("erro", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONObject findUsuarioByCpf(JSONObject usuarioJson) {
		Usuario novoUsu = null;
		try {
			novoUsu = this.usuarioDao.findByCpf(usuarioJson.getString("cpf"));
			return novoUsu.toJson();
		}catch (Exception e) {
			return null;
		}
	}	
		

}
