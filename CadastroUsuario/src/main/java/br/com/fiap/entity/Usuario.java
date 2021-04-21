package br.com.fiap.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.sf.json.JSONObject;

@Entity
@Table(name = "tb_usuario")
@SequenceGenerator(sequenceName = "sq_tb_usuario", name = "usuario", allocationSize = 1)

public class Usuario {
	
	@Id
	@Column (name="id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
	private int idUsuario;
	
	@Column (name="nm_usuario", nullable = false, length = 100)
	private String nomeUsuario;
	
	@Column(name="dt_nasc", nullable = false, length = 100)
	private Date dataNasc;
	
	@Column(name="nr_cpf", nullable = false, length = 100)
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	@Column(name="ds_genero", length = 20)
	private Genero genero;
	
	@OneToOne
	@JoinColumn(name="id_endereco")
	private Endereco endereco;
	
	@Column(name="telefone", nullable = false, length = 100)
	private String telefone;
	
	public Usuario() {
		
	}
	
	public Usuario(String nomeUsuario, Date dataNasc, Genero genero, Endereco endereco, String telefone) {
		this.nomeUsuario = nomeUsuario;
		this.dataNasc = dataNasc;
		this.genero = genero;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		
		json.put("idUsuario", this.idUsuario);
		json.put("cpf", this.cpf);
		json.put("nomeUsuario", this.nomeUsuario);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		json.put("dataNasc", sdf.format(this.dataNasc));
		json.put("telefone", this.telefone);
		json.put("endereco", this.endereco.toJson());
		if(this.genero == Genero.H) {
			json.put("genero", "masculino");
		}else if (this.genero == Genero.M){
			json.put("genero", "feminino");
		}else {
			json.put("genero", "outro");
		}
		return json;
	}
	
	
	

}
