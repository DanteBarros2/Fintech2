package br.com.fintech.dao.impl;

import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleCategoriaDAO implements CategoriaDAO {

    @Override
    public void cadastrar(Categoria categoria) throws DBException {
        String sql = "INSERT INTO TB_FIN_CATEGORIA (id_categoria, tp_categoria) VALUES (seq_categoria.nextval, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, categoria.getTpCategoria());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao cadastrar a categoria", e);
        }
    }

    @Override
    public void atualizar(Categoria categoria) throws DBException {
        String sql = "UPDATE TB_FIN_CATEGORIA SET tp_categoria = ? WHERE id_categoria = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, categoria.getTpCategoria());
            stmt.setInt(2, categoria.getIdCategoria());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhuma categoria encontrada com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao atualizar a categoria", e);
        }
    }

    @Override
    public void remover(int id) throws DBException {
        String sql = "DELETE FROM TB_FIN_CATEGORIA WHERE id_categoria = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhuma categoria encontrada com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao remover a categoria", e);
        }
    }

    @Override
    public Categoria buscar(int id) {
        String sql = "SELECT * FROM TB_FIN_CATEGORIA WHERE id_categoria = ?";
        Categoria categoria = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idCategoria = rs.getInt("id_categoria");
                String tipoCategoria = rs.getString("tp_categoria");
                categoria = new Categoria(idCategoria, tipoCategoria);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<Categoria> listar() {
        String sql = "SELECT * FROM TB_FIN_CATEGORIA";
        List<Categoria> categorias = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCategoria = rs.getInt("id_categoria");
                String tipoCategoria = rs.getString("tp_categoria");
                Categoria categoria = new Categoria(idCategoria, tipoCategoria);
                categorias.add(categoria);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}


