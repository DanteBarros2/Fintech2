package br.com.fintech.factory;

import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.dao.PlanejamentoDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.dao.impl.OracleCategoriaDAO;
import br.com.fintech.dao.impl.OraclePlanejamentoDAO;
import br.com.fintech.dao.impl.OracleTransacaoDAO;
import br.com.fintech.dao.impl.OracleUsuarioDAO;

public class DaoFactory {

    public static UsuarioDAO getUsuarioDAO() {
        return new OracleUsuarioDAO();
    }

    public static TransacaoDAO getTransacaoDAO() {
        return new OracleTransacaoDAO();
    }

    public static PlanejamentoDAO getPlanejamentoDAO() {
        return new OraclePlanejamentoDAO();
    }

    public static CategoriaDAO getCategoriaDAO() {
        return new OracleCategoriaDAO();
    }
}
