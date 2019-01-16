package br.com.junioralmeida.Pessoa;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private String telefone;
	
	public Pessoa(String nome, String cpf, String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String toString() {
		return String.format("Pessoa: %d %s %s %s ", id, nome, cpf, telefone);
	}
}
