package br.com.junioralmeida.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	private final Connection connection;
	
	public DAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvaPessoa(Pessoa Usuario) throws SQLException {
		String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS)) {
				
			stmt.setString(1, Usuario.getNome());
			stmt.setString(2, Usuario.getCpf());
			stmt.setString(3, Usuario.getTelefone());
			stmt.execute();
			
			try(ResultSet rst = stmt.getGeneratedKeys()){
				if(rst.next()) {
				int id = rst.getInt("id");
					Usuario.setId(id); 
				}
			}
		}
	}
	
}
