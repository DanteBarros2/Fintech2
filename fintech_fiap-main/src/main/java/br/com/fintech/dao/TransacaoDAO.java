package br.com.fintech.dao;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.Transacao;

import java.util.List;

public interface TransacaoDAO {

    void cadastrar(Transacao transacao) throws DBException;
    void atualizar(Transacao transacao) throws DBException;
    void remover(int codigo) throws DBException;
    Transacao buscar(int id);
    List<Transacao> listar();

}