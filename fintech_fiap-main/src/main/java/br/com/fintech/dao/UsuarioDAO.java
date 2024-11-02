package br.com.fintech.dao;

import br.com.fintech.model.Usuario;
import br.com.fintech.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    // Construtor que inicializa a conexão
    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.connection = ConnectionFactory.getConnection();
    }

    // Método para inserir usuário
    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO TB_FIN_USUARIO2 (id_usuario, nm_usuario, ds_email, ds_senha, tp_usuario) " +
                "VALUES (seq_usuario.nextval, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNmUsuario());
            stmt.setString(2, usuario.getDsEmail());
            stmt.setString(3, usuario.getDsSenha());
            stmt.setString(4, usuario.getTpUsuario());
            stmt.executeUpdate();
        }
    }

    // Método para deletar usuário por ID
    public boolean deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM TB_FIN_USUARIO2 WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método para pesquisar um usuário por nome ou email
    public Usuario pesquisarUsuario(String identificador) throws SQLException {
        String sql = "SELECT * FROM TB_FIN_USUARIO2 WHERE nm_usuario = ? OR ds_email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, identificador);
            stmt.setString(2, identificador);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Se o usuário for encontrado, cria uma instância de Usuario com os dados
                String nome = rs.getString("nm_usuario");
                String email = rs.getString("ds_email");
                String senha = rs.getString("ds_senha");
                String tipoUsuario = rs.getString("tp_usuario");

                return new Usuario(nome, email, senha, tipoUsuario);
            }
        }
        return null; // Retorna null se o usuário não for encontrado
    }

    // Método para fechar a conexão
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
