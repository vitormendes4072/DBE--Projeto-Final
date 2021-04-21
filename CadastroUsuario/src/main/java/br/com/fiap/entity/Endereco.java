package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name="tb_endereco")
@SequenceGenerator(name="endereco", sequenceName = "sq_tb_endereco", allocationSize = 1)

public class Endereco {
	
	@Id
	@Column(name="id_endereco")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
	private int idEndereco;
	
	@Column(name="logradouro", nullable = false, length = 100)
	private String logradouro;
	
	@Column(name="nr_logradouro", nullable = false, length = 100)
	private int numLogradouro;
	
	@Column(name="bairro", nullable = false, length = 100)
	private String bairro;
	
	@Column(name="cidade", nullable = false, length = 100)
	private String cidade;
	
	@Column(name="estado", nullable = false, length = 100)
	private String estado;
	
	@Column(name="cep", nullable = false, length = 100)
	private String cep;

	public Endereco() {
		
	}
	
	public Endereco(String logradouro, int numLogradouro, String bairro, String cidade, String estado, String cep) {
		this.logradouro = logradouro;
		this.numLogradouro = numLogradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumLogradouro() {
		return numLogradouro;
	}

	public void setNumLogradouro(int numLogradouro) {
		this.numLogradouro = numLogradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		json.put("idEndereco", this.idEndereco);
		json.put("cep", this.cep);
		json.put("logradouro", this.logradouro);
		json.put("numLogradouro", this.numLogradouro);
		json.put("bairro", this.bairro);
		json.put("cidade", this.cidade);
		json.put("estado", this.estado);
		
		return json;
	}
	
	
	

}
