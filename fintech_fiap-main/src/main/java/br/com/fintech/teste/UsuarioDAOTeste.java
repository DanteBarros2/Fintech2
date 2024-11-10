package br.com.fintech.teste;

import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DaoFactory;
import br.com.fintech.model.Usuario;

public class UsuarioDAOTeste {
    public static void main(String[] args){

        //Cadastrar um Usuario
        UsuarioDAO dao = DaoFactory.getUsuarioDAO();

        Usuario usuario = new Usuario(
                2,
                "Matheus",
                "matheus@hotmail.com",
                "12345",
                "Admin"

        );
        try {
            dao.cadastrar(usuario);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        // Buscar usuario pelo código e att senha
        usuario = dao.buscar(1);
        usuario.setSenha("senha");
        try {
            dao.atualizar(usuario);
        } catch (DBException e) {
            DBException e1 = e;
            e1.printStackTrace();
            
        }



    }
}
