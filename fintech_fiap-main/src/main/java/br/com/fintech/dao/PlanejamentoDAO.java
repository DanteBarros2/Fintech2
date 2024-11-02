package br.com.fintech.dao;

import br.com.fintech.model.Planejamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.util.List;

public class PlanejamentoDAO {
    private Connection connection;

    public PlanejamentoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo Planejamento
    public void inserirPlanejamento(Planejamento planejamento) throws SQLException {
        String sql = "INSERT INTO TB_FIN_PLANEJAMENTO (id_transacao, id_usuario, ds_planejamento, vl_valor_alvo, vl_valor_inicial, dt_inicio, dt_fim) VALUES (1, 2, 'nada', 55, 5, 2024-10-30, 1)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, planejamento.getIdTransacao());
            stmt.setInt(2, planejamento.getIdUsuario());
            stmt.setString(3, planejamento.getDsPlanejamento());
            stmt.setFloat(4, planejamento.getVlValorAlvo());
            stmt.setFloat(5, planejamento.getVlValorInicial());
            stmt.setDate(6, (java.sql.Date) new Date(planejamento.getDtInicio().getTime()));
            stmt.setDate(7, (java.sql.Date) new Date(planejamento.getDtFim().getTime()));

            stmt.executeUpdate();
        }
    }

    // Método para buscar um Planejamento por ID
    public Planejamento buscarPlanejamentoPorId(int id) throws SQLException {
        String sql = "SELECT id_transacao, id_usuario, ds_planejamento, vl_valor_alvo, vl_valor_inicial, dt_inicio, dt_fim FROM TB_FIN_PLANEJAMENTO WHERE id_transacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Planejamento planejamento = new Planejamento();
                    planejamento.setIdTransacao(rs.getInt("id_transacao"));
                    planejamento.setIdUsuario(rs.getInt("id_usuario"));
                    planejamento.setDsPlanejamento(rs.getString("ds_planejamento"));
                    planejamento.setVlValorAlvo(rs.getFloat("vl_valor_alvo"));
                    planejamento.setVlValorInicial(rs.getFloat("vl_valor_inicial"));
                    planejamento.setDtInicio(rs.getDate("dt_inicio"));
                    planejamento.setDtFim(rs.getDate("dt_fim"));

                    return planejamento;
                }
            }
        }
        return null;
    }

    // Método para listar todos os Planejamentos
    public List<Planejamento> listarTodosPlanejamentos() throws SQLException {
        List<Planejamento> planejamentos = new ArrayList<>();
        String sql = "SELECT id_transacao, id_usuario, ds_planejamento, vl_valor_alvo, vl_valor_inicial, dt_inicio, dt_fim FROM TB_FIN_PLANEJAMENTO";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Planejamento planejamento = new Planejamento();
                planejamento.setIdTransacao(rs.getInt("id_transacao"));
                planejamento.setIdUsuario(rs.getInt("id_usuario"));
                planejamento.setDsPlanejamento(rs.getString("ds_planejamento"));
                planejamento.setVlValorAlvo(rs.getFloat("vl_valor_alvo"));
                planejamento.setVlValorInicial(rs.getFloat("vl_valor_inicial"));
                planejamento.setDtInicio(rs.getDate("dt_inicio"));
                planejamento.setDtFim(rs.getDate("dt_fim"));

                planejamentos.add(planejamento);
            }
        }
        return planejamentos;
    }

    // Método para atualizar um Planejamento
    public void atualizarPlanejamento(Planejamento planejamento) throws SQLException {
        String sql = "UPDATE TB_FIN_PLANEJAMENTO SET id_usuario = ?, ds_planejamento = ?, vl_valor_alvo = ?, vl_valor_inicial = ?, dt_inicio = ?, dt_fim = ? WHERE id_transacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, planejamento.getIdUsuario());
            stmt.setString(2, planejamento.getDsPlanejamento());
            stmt.setFloat(3, planejamento.getVlValorAlvo());
            stmt.setFloat(4, planejamento.getVlValorInicial());
            stmt.setDate(5, (java.sql.Date) new Date(planejamento.getDtInicio().getTime()));
            stmt.setDate(6, (java.sql.Date) new Date(planejamento.getDtFim().getTime()));
            stmt.setInt(7, planejamento.getIdTransacao());

            stmt.executeUpdate();
        }
    }

    // Método para excluir um Planejamento
    public void excluirPlanejamento(int id) throws SQLException {
        String sql = "DELETE FROM TB_FIN_PLANEJAMENTO WHERE id_transacao = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
