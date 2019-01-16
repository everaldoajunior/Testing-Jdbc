/*
 * DESENVOLVIDO POR EVERALDO J�NIOR
 * 
 * 			STUDY CLASS 
 * 
 * 
 * TODA COMENTADA PARA OBSERVA��ES FUTURAS
 * 
 * */

package br.com.junioralmeida.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
//		Var�aveis n�o mais Utilizada

		/*
		 * String nome = "Everaldo J�nior"; String cpf = "119.377.234-61"; String
		 * telefone = "(81) 98356-4858";
		 */

		Connection con = Database.getConnection();// Criando Conex�o j� configurada na Classe Database

		con.setAutoCommit(false);
		
		/*
		 * S� Envia o SQL Quando todas as Query estiver passando pelos tratamentos
		 * Quando execu��o Travar, n�o enviar� as SQL'S que passaram.
		 */

		try {
			String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

			PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//GW WAY para Enviar os arquivos vindo de um Met�do ( BO/Controlador 
//			stm.setString(1, nome);
//			stm.setString(2, cpf);
//			stm.setString(3, telefone);

//Nova forma de Enviar os mesmos dados de cima, sendo que numa �nica Linha

			adiciona("teste", "TESTE", "TESTE", stm);
//			
			con.commit();
		} catch (Exception e) {
//				con.rollback();
//				System.out.println("Rollback");
			e.printStackTrace();
		}

		/*
		 * Fechamento do PreparedStatement / Connection ( Conex�o com o Banco de Dados
		 * Logo ap�s a Execu��o e Fizaliza��o da Querry
		 */

		con.close();
	}

	/*
	 * Met�do Criado para Executar uma Querry e definir os valores setados com ( ? ,
	 * ? , ? ) na String SQL
	 */
	private static void adiciona(String nome, String cpf, String telefone, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, cpf);
		stm.setString(3, telefone);

		boolean resultado = stm.execute();
		System.out.println(resultado);

		// Travando a Query do SQL para Demonstrar que o Met�do Adiciona.
		// Demostrando que envia um SQL enquanto o outro est� travado !!

//		if (nome.equals("teste")) { // Se o nome enviado no Param�tro "Nome" for igual ao que existe dentro do Block
//									// if ele trava a execu��o
//			throw new IllegalArgumentException("Erro no Param�tro " + nome);
//		}

		ResultSet rst = stm.getGeneratedKeys();
		
		while (rst.next()) {
			String nomeInserido = rst.getString("nome");
			System.out.println("Gerado registro do Sr(a) : " + nomeInserido);
		}

		rst.close();
	}
}