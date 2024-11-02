package br.com.fintech;

import br.com.fintech.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class App
{
    public static void main(String[] args) {
        Connection conexao = null;

        try {
            // Define o Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Abre uma conexão com a URL definida para acesso ao banco
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM555793",
                    "190902");

            System.out.println("Conectou!");

        } catch (SQLException e) {
            // Tratamento de erro de conexão
            System.err.println("Não foi possível conectar no ORACLE FIAP");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // Tratamento de erro de carregamento da classe do driver
            System.err.println("Driver JDBC não encontrado");
            e.printStackTrace();
        } finally {
            // Fecha a conexão
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão");
                    e.printStackTrace();
                }
            }
        }
    }
}

