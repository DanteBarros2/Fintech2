package br.com.fintech.dao;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.Categoria;


import java.util.List;

public interface CategoriaDAO {

    void cadastrar(Categoria categoria) throws DBException;
    void atualizar(Categoria categoria) throws DBException;
    void remover(int codigo) throws DBException;
    Categoria buscar(int id);
    List<Categoria> listar();

}