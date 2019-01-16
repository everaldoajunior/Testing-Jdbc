package br.com.junioralmeida.Pessoa;

import java.sql.Connection;
import java.sql.SQLException;


import br.com.junioralmeida.Database.Database;

public class TesteInserirPessoa {

	public static void main(String[] args) throws SQLException {
		Pessoa Everaldo = new Pessoa("Everaldo de Almeida Macêdo Júnior ", "119.377.234-61", "(81) 98356-4858");
		
		try (Connection connection = Database.getConnection()) {
			new DAO(connection).salvaPessoa(Everaldo);
		}
		
		System.out.println("Usúario(a) cadastrado(a) com Sucesso : " + Everaldo);
	}
	
}
