package br.com.fintech.dao;

import br.com.fintech.exception.DBException;
import br.com.fintech.model.Usuario;

import java.util.List;

public interface UsuarioDAO {

    void cadastrar(Usuario usuario) throws DBException;
    void atualizar(Usuario usuario) throws DBException;
    void remover(int codigo) throws DBException;
    Usuario buscar(int id);
    List<Usuario> listar();

}