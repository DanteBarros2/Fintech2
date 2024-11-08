package br.com.fintech.dao;

import br.com.fintech.model.Usuario;
import br.com.fintech.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // Método para atualizar apenas a senha de um usuário pelo nome
    public boolean atualizarSenhaUsuarioPorNome(String nome, String novaSenha) throws SQLException {
        String sql = "UPDATE TB_FIN_USUARIO2 SET ds_senha = ? WHERE nm_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setString(2, nome);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retorna true se alguma linha foi afetada
        }
    }


    public List<Usuario> listarUsuarios() throws SQLException {
        String sql = "SELECT * FROM TB_FIN_USUARIO2";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Extrai os dados do usuário do ResultSet
                String nome = rs.getString("nm_usuario");
                String email = rs.getString("ds_email");
                String senha = rs.getString("ds_senha");
                String tipoUsuario = rs.getString("tp_usuario");

                // Adiciona o usuário na lista
                usuarios.add(new Usuario(nome, email, senha, tipoUsuario));
            }
        }
        return usuarios;
    }

    // Método para fechar a conexão
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
