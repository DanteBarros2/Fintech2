package br.com.fintech.dao;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.Planejamento;


import java.util.List;

public interface PlanejamentoDAO {

    void cadastrar(Planejamento planejamento) throws DBException;
    void atualizar(Planejamento planejamento) throws DBException;
    void remover(int codigo) throws DBException;
    Planejamento buscar(int id);
    List<Planejamento> listar();

}
