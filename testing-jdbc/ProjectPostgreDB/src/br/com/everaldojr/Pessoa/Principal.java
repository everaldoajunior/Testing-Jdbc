package br.com.everaldojr.Pessoa;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.everaldojr.Database.Database;

public class Principal {
	public static void main(String[] args) throws SQLException {


		Pessoa usuario = new Pessoa("Erick", "11", "11");

		try (Connection connection = Database.getConnection()) {
			new PessoaDAO(connection).Cadastro(usuario);
		}

		try (Connection connection = Database.getConnection()) {
			new PessoaDAO(connection).listarPessoas();
		}
		
		System.out.println("Cadastro Realizado do Usuário : " + usuario);

	}
}
