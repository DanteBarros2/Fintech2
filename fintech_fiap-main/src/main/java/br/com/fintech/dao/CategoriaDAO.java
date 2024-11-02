package br.com.fintech.dao;

import br.com.fintech.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir uma nova categoria, com o ID gerado pela sequência seq_categoria
    public void inserirCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO TB_FIN_CATEGORIA (id_categoria, nm_categoria, tp_categoria) VALUES (seq_categoria.nextval, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNmCategoria());
            stmt.setString(2, categoria.getTpCategoria());
            stmt.executeUpdate();
        }
    }

    // Método para pesquisar uma categoria pelo nome
    public Categoria pesquisarCategoriaPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM TB_FIN_CATEGORIA WHERE nm_categoria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tp_categoria");
                return new Categoria(nome, tipo);
            }
        }
        return null;
    }

    // Método para deletar uma categoria pelo nome
    public boolean deletarCategoriaPorNome(String nome) throws SQLException {
        String sql = "DELETE FROM TB_FIN_CATEGORIA WHERE nm_categoria = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método para listar todas as categorias
    public List<Categoria> listarCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM TB_FIN_CATEGORIA";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nm_categoria");
                String tipo = rs.getString("tp_categoria");
                categorias.add(new Categoria(nome, tipo));
            }
        }
        return categorias;
    }
}


