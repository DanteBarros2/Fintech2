package br.com.fintech.dao.impl;

import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDAO implements TransacaoDAO {

    @Override
    public void cadastrar(Transacao transacao) throws DBException {
        String sql = "INSERT INTO TB_FIN_TRANSACAO (id_transacao, id_usuario, id_categoria, tp_transacao, ds_transacao, vl_transacao) " +
                "VALUES (seq_transacao.nextval, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, transacao.getIdUsuario());
            stmt.setInt(2, transacao.getIdCategoria());
            stmt.setString(3, transacao.getTpTransacao());
            stmt.setString(4, transacao.getDsTransacao());
            stmt.setFloat(5, transacao.getVlTransacao());

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao cadastrar a transação", e);
        }
    }

    @Override
    public void atualizar(Transacao transacao) throws DBException {
        String sql = "UPDATE TB_FIN_TRANSACAO SET id_usuario = ?, id_categoria = ?, tp_transacao = ?, ds_transacao = ?, vl_transacao = ? " +
                "WHERE id_transacao = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, transacao.getIdUsuario());
            stmt.setInt(2, transacao.getIdCategoria());
            stmt.setString(3, transacao.getTpTransacao());
            stmt.setString(4, transacao.getDsTransacao());
            stmt.setFloat(5, transacao.getVlTransacao());
            stmt.setInt(6, transacao.getIdTransacao());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhuma transação encontrada com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao atualizar a transação", e);
        }
    }

    @Override
    public void remover(int id) throws DBException {
        String sql = "DELETE FROM TB_FIN_TRANSACAO WHERE id_transacao = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhuma transação encontrada com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao remover a transação", e);
        }
    }

    @Override
    public Transacao buscar(int id) {
        String sql = "SELECT * FROM TB_FIN_TRANSACAO WHERE id_transacao = ?";
        Transacao transacao = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idTransacao = rs.getInt("id_transacao");
                int idUsuario = rs.getInt("id_usuario");
                int idCategoria = rs.getInt("id_categoria");
                String tpTransacao = rs.getString("tp_transacao");
                String dsTransacao = rs.getString("ds_transacao");
                float vlTransacao = rs.getFloat("vl_transacao");

                transacao = new Transacao(idTransacao, idUsuario, idCategoria, tpTransacao, dsTransacao, vlTransacao);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transacao;
    }

    @Override
    public List<Transacao> listar() {
        String sql = "SELECT * FROM TB_FIN_TRANSACAO";
        List<Transacao> transacoes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idTransacao = rs.getInt("id_transacao");
                int idUsuario = rs.getInt("id_usuario");
                int idCategoria = rs.getInt("id_categoria");
                String tpTransacao = rs.getString("tp_transacao");
                String dsTransacao = rs.getString("ds_transacao");
                float vlTransacao = rs.getFloat("vl_transacao");

                Transacao transacao = new Transacao(idTransacao, idUsuario, idCategoria, tpTransacao, dsTransacao, vlTransacao);
                transacoes.add(transacao);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transacoes;
    }
}

