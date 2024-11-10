package br.com.fintech.dao.impl;

import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleUsuarioDAO implements UsuarioDAO {

    @Override
    public void cadastrar(Usuario usuario) throws DBException {
        String sql = "INSERT INTO TB_FIN_USUARIO2 (id_usuario, nm_usuario, ds_email, ds_senha, tp_usuario) " +
                "VALUES (seq_usuario.nextval, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());

            stmt.executeUpdate();
            System.out.println("Usuário Cadastrado com sucesso!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao cadastrar o usuário", e);
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws DBException {
        String sql = "UPDATE TB_FIN_USUARIO2 SET nm_usuario = ?, ds_email = ?, ds_senha = ?, tp_usuario = ? WHERE id_usuario = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTipoUsuario());
            stmt.setInt(5, usuario.getIdUsuario());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhum usuário encontrado com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao atualizar o usuário", e);
        }
    }

    @Override
    public void remover(int id) throws DBException {
        String sql = "DELETE FROM TB_FIN_USUARIO2 WHERE id_usuario = ?";

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhum usuário encontrado com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao remover o usuário", e);
        }
    }

    @Override
    public Usuario buscar(int id) {
        String sql = "SELECT * FROM TB_FIN_USUARIO2 WHERE id_usuario = ?";
        Usuario usuario = null;

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nm_usuario");
                String email = rs.getString("ds_email");
                String senha = rs.getString("ds_senha");
                String tipoUsuario = rs.getString("tp_usuario");

                usuario = new Usuario(id, nome, email, senha, tipoUsuario);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        String sql = "SELECT * FROM TB_FIN_USUARIO2";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexao = ConnectionFactory.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idUsuario = rs.getInt("id_usuario");
                String nome = rs.getString("nm_usuario");
                String email = rs.getString("ds_email");
                String senha = rs.getString("ds_senha");
                String tipoUsuario = rs.getString("tp_usuario");

                Usuario usuario = new Usuario(idUsuario, nome, email, senha, tipoUsuario);
                usuarios.add(usuario);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}

