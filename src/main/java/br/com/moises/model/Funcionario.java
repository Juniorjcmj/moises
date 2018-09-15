package br.com.moises.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "func_seq", sequenceName = "func_seq") //Cria uma sequence para ser usada com a tabela
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "func_seq") //Define que a tabela fará uso da sequence criada antes
	private Integer id;
	
	@NotNull(message="Nome é obrigatório")
	private String nome;
	
	@NotNull(message="CPF é obrigatório")
	private String cpf;
	
	
	
	private String situacao;
	
	
	
	public Funcionario() {
		// TODO Auto-generated constructor stub
	}



	public Funcionario(Integer id, @NotNull(message = "Nome é obrigatório") String nome,
			@NotNull(message = "CPF é obrigatório") String cpf, String situacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.situacao = situacao;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getSituacao() {
		return situacao;
	}



	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	
}
