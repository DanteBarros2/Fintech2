package br.com.fintech.dao;

import br.com.fintech.exception.EntidadeNaoEcontradaException;
import br.com.fintech.model.Investimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvestimentoDAO {
    private Connection connection;

    public InvestimentoDAO(Connection connection) {
        this.connection = connection;
    }

    // Inserir novo investimento (sem datas)
    public void inserirInvestimento(Investimento investimento) throws SQLException {
        String sql = "INSERT INTO TB_FIN_INVESTIMENTO (id_investimento, id_usuario, tb_investimento, mn_investimento, vl_inicial, vl_rentabilidade, ds_risco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, investimento.getIdInvestimento());
            stmt.setInt(2, investimento.getIdUsuario());
            stmt.setString(3, investimento.getTbInvestimento());
            stmt.setString(4, investimento.getMnInvestimento());
            stmt.setFloat(5, investimento.getVlInicial());
            stmt.setFloat(6, investimento.getVlRentabilidade());
            stmt.setString(7, investimento.getDsRisco());
            stmt.executeUpdate();
        }
    }

    // Pesquisar investimento por código
    public Investimento pesquisar(long codigo) throws SQLException, EntidadeNaoEcontradaException {
        String sql = "SELECT * FROM tb_fin_investimento WHERE id_investimento = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, codigo);
            ResultSet result = stm.executeQuery();

            if (!result.next()) {
                throw new EntidadeNaoEcontradaException("Investimento não encontrado");
            }

            int idInvestimento = result.getInt("id_investimento");
            int idUsuario = result.getInt("id_usuario");
            String tbInvestimento = result.getString("tb_investimento");
            String mnInvestimento = result.getString("mn_investimento");
            float vlInicial = result.getFloat("vl_inicial");
            float vlRentabilidade = result.getFloat("vl_rentabilidade");
            String dsRisco = result.getString("ds_risco");

            return new Investimento(idInvestimento, idUsuario, tbInvestimento, mnInvestimento, vlInicial, vlRentabilidade, dsRisco);
        }
    }
}
