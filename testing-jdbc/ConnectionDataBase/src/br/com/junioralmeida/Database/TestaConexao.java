/*
 * DESENVOLVIDO POR EVERALDO JÚNIOR
 * 
 * 			STUDY CLASS 
 * 
 * 
 * TODA COMENTADA PARA OBSERVAÇÕES FUTURAS
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
//		Varíaveis não mais Utilizada

		/*
		 * String nome = "Everaldo Júnior"; String cpf = "119.377.234-61"; String
		 * telefone = "(81) 98356-4858";
		 */

		Connection con = Database.getConnection();// Criando Conexão já configurada na Classe Database

		con.setAutoCommit(false);
		
		/*
		 * Só Envia o SQL Quando todas as Query estiver passando pelos tratamentos
		 * Quando execução Travar, não enviará as SQL'S que passaram.
		 */

		try {
			String sql = "INSERT INTO cliente (nome, cpf, telefone) VALUES (?, ?, ?)";

			PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

//GW WAY para Enviar os arquivos vindo de um Metódo ( BO/Controlador 
//			stm.setString(1, nome);
//			stm.setString(2, cpf);
//			stm.setString(3, telefone);

//Nova forma de Enviar os mesmos dados de cima, sendo que numa única Linha

			adiciona("teste", "TESTE", "TESTE", stm);
//			
			con.commit();
		} catch (Exception e) {
//				con.rollback();
//				System.out.println("Rollback");
			e.printStackTrace();
		}

		/*
		 * Fechamento do PreparedStatement / Connection ( Conexão com o Banco de Dados
		 * Logo após a Execução e Fizalização da Querry
		 */

		con.close();
	}

	/*
	 * Metódo Criado para Executar uma Querry e definir os valores setados com ( ? ,
	 * ? , ? ) na String SQL
	 */
	private static void adiciona(String nome, String cpf, String telefone, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, cpf);
		stm.setString(3, telefone);

		boolean resultado = stm.execute();
		System.out.println(resultado);

		// Travando a Query do SQL para Demonstrar que o Metódo Adiciona.
		// Demostrando que envia um SQL enquanto o outro está travado !!

//		if (nome.equals("teste")) { // Se o nome enviado no Paramêtro "Nome" for igual ao que existe dentro do Block
//									// if ele trava a execução
//			throw new IllegalArgumentException("Erro no Paramêtro " + nome);
//		}

		ResultSet rst = stm.getGeneratedKeys();
		
		while (rst.next()) {
			String nomeInserido = rst.getString("nome");
			System.out.println("Gerado registro do Sr(a) : " + nomeInserido);
		}

		rst.close();
	}
}