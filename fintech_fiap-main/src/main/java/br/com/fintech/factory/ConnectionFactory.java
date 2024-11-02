package br.com.fintech.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Método estático para obter a conexão
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conexao = null;

        // Define o Driver
        Class.forName("oracle.jdbc.driver.OracleDriver");

        // Abre uma conexão com a URL definida para acesso ao banco
        conexao = DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM555781", "210800");

        return conexao;
    }

    // Exemplo de uso do método getConnection
    public static void main(String[] args) {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.getConnection();
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
        // Método para fechar a conexão

    }
    // Método para fechar a conexão
    public static void fecharConexao(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Logar a exceção
            }
}}}