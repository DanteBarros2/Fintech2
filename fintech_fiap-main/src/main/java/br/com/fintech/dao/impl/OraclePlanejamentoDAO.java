package br.com.fintech.dao.impl;

import br.com.fintech.dao.PlanejamentoDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Planejamento;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OraclePlanejamentoDAO implements PlanejamentoDAO {

    @Override
    public void cadastrar(Planejamento planejamento) throws DBException {
        String sql = "INSERT INTO TB_FIN_PLANEJAMENTO (id_transacao, id_usuario, ds_planejamento, vl_valor_alvo, vl_valor_inicial, dt_inicio, dt_fim) " +
                "VALUES (seq_planejamento.nextval, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, planejamento.getIdUsuario());
            stmt.setString(2, planejamento.getDsPlanejamento());
            stmt.setFloat(3, planejamento.getVlValorAlvo());
            stmt.setFloat(4, planejamento.getVlValorInicial());
            stmt.setDate(5, new Date(planejamento.getDtInicio().getTime()));
            stmt.setDate(6, new Date(planejamento.getDtFim().getTime()));

            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao cadastrar o planejamento", e);
        }
    }

    @Override
    public void atualizar(Planejamento planejamento) throws DBException {
        String sql = "UPDATE TB_FIN_PLANEJAMENTO SET id_usuario = ?, ds_planejamento = ?, vl_valor_alvo = ?, vl_valor_inicial = ?, dt_inicio = ?, dt_fim = ? " +
                "WHERE id_transacao = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, planejamento.getIdUsuario());
            stmt.setString(2, planejamento.getDsPlanejamento());
            stmt.setFloat(3, planejamento.getVlValorAlvo());
            stmt.setFloat(4, planejamento.getVlValorInicial());
            stmt.setDate(5, new Date(planejamento.getDtInicio().getTime()));
            stmt.setDate(6, new Date(planejamento.getDtFim().getTime()));
            stmt.setInt(7, planejamento.getIdTransacao());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhum planejamento encontrado com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao atualizar o planejamento", e);
        }
    }

    @Override
    public void remover(int id) throws DBException {
        String sql = "DELETE FROM TB_FIN_PLANEJAMENTO WHERE id_transacao = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DBException("Nenhum planejamento encontrado com o ID especificado");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("Erro ao remover o planejamento", e);
        }
    }

    @Override
    public Planejamento buscar(int id) {
        String sql = "SELECT * FROM TB_FIN_PLANEJAMENTO WHERE id_transacao = ?";
        Planejamento planejamento = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idTransacao = rs.getInt("id_transacao");
                int idUsuario = rs.getInt("id_usuario");
                String dsPlanejamento = rs.getString("ds_planejamento");
                float vlValorAlvo = rs.getFloat("vl_valor_alvo");
                float vlValorInicial = rs.getFloat("vl_valor_inicial");
                Date dtInicio = rs.getDate("dt_inicio");
                Date dtFim = rs.getDate("dt_fim");

                planejamento = new Planejamento(idTransacao, idUsuario, dsPlanejamento, vlValorAlvo, vlValorInicial, dtInicio, dtFim);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return planejamento;
    }

    @Override
    public List<Planejamento> listar() {
        String sql = "SELECT * FROM TB_FIN_PLANEJAMENTO";
        List<Planejamento> planejamentos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idTransacao = rs.getInt("id_transacao");
                int idUsuario = rs.getInt("id_usuario");
                String dsPlanejamento = rs.getString("ds_planejamento");
                float vlValorAlvo = rs.getFloat("vl_valor_alvo");
                float vlValorInicial = rs.getFloat("vl_valor_inicial");
                Date dtInicio = rs.getDate("dt_inicio");
                Date dtFim = rs.getDate("dt_fim");

                Planejamento planejamento = new Planejamento(idTransacao, idUsuario, dsPlanejamento, vlValorAlvo, vlValorInicial, dtInicio, dtFim);
                planejamentos.add(planejamento);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return planejamentos;
    }
}
