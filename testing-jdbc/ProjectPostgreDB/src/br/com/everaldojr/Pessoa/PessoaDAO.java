package br.com.everaldojr.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

	private final Connection connection;

	public PessoaDAO(Connection connection) {
		this.connection = connection;
	}

	public void Cadastro(Pessoa Usuario) throws SQLException {
		String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

		try (PreparedStatement pst = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, Usuario.getNome());
			pst.setString(2, Usuario.getCpf());
			pst.setString(3, Usuario.getTelefone());

			pst.execute();

			try (ResultSet rst = pst.getGeneratedKeys()) {
				if(rst.next()) {
					int id = rst.getInt("id");
					Usuario.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void listarPessoas() throws SQLException {

		Statement pstm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rst = pstm.executeQuery("SELECT * FROM cliente");

		if(rst.first()) {

			do {
				System.out.println("NOME : " + rst.getString("nome") + " CPF : " + rst.getString("cpf") +
						" TELEFONE : " + rst.getString("telefone") );
			}
			while(rst.next());
		}


	}



	public List<Pessoa> listar() throws SQLException {

		List <Pessoa> Pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM cliente;";
		try(PreparedStatement pst = connection.prepareStatement(sql)) {
			pst.execute();

			try (ResultSet rst = pst.getResultSet()) {
				while (rst.next()) {

					int id = rst.getInt("id");
					String nome = rst.getString("nome");
					String cpf = rst.getString("cpf");
					String telefone = rst.getString("telefone");

					Pessoa pessoa = new Pessoa(nome, cpf, telefone);
					pessoa.setId(id);

					Pessoas.add(pessoa);
				}
			}
		}

		return Pessoas;
	}



}
