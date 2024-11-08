package br.com.fintech.view;

import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CadastroUsuarioView {
    public static void main(String[] args) {
        try {
            // Obtém a conexão usando a ConnectionFactory
            Connection connection = ConnectionFactory.getConnection();

            // Cria a instância da DAO para operações no banco de dados
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // Scanner para capturar a entrada do usuário
            Scanner scanner = new Scanner(System.in);

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Deletar Usuário por ID");
            System.out.println("3 - Listar Todos os Usuários");
            System.out.println("4 - Alterar Senha do Usuário por Nome");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    // Captura os dados do novo usuário
                    System.out.println("Cadastro de Usuário");

                    System.out.print("Digite o Nome do Usuário: ");
                    String nmUsuario = scanner.nextLine();

                    System.out.print("Digite o E-mail do Usuário: ");
                    String dsEmail = scanner.nextLine();

                    System.out.print("Digite a Senha do Usuário: ");
                    String dtSenha = scanner.nextLine();

                    System.out.print("Digite o Tipo de Usuário (Ex: Admin, Padrão): ");
                    String tpUsuario = scanner.nextLine();

                    // Cria uma nova instância de Usuario
                    Usuario usuario = new Usuario(nmUsuario, dsEmail, dtSenha, tpUsuario);

                    // Adiciona o usuário ao banco de dados
                    usuarioDAO.inserirUsuario(usuario);

                    System.out.println("Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    // Deleta um usuário por ID
                    System.out.print("Digite o ID do Usuário a ser deletado: ");
                    int id = scanner.nextInt();

                    // Tenta deletar o usuário pelo ID
                    boolean sucesso = usuarioDAO.deletarUsuario(id);

                    if (sucesso) {
                        System.out.println("Usuário deletado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 3:
                    // Lista todos os usuários
                    System.out.println("Lista de todos os usuários:");

                    List<Usuario> usuarios = usuarioDAO.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado.");
                    } else {
                        for (Usuario u : usuarios) {
                            System.out.println("Nome: " + u.getNmUsuario());
                            System.out.println("Email: " + u.getDsEmail());
                            System.out.println("Tipo de Usuário: " + u.getTpUsuario());
                            System.out.println("---------------");
                        }
                    }
                    break;

                case 4:
                    // Altera a senha de um usuário pelo nome
                    System.out.print("Digite o Nome do Usuário para alterar a senha: ");
                    String nomeUsuario = scanner.nextLine();

                    System.out.print("Digite a nova senha: ");
                    String novaSenha = scanner.nextLine();

                    // Atualiza a senha do usuário
                    boolean senhaAtualizada = usuarioDAO.atualizarSenhaUsuarioPorNome(nomeUsuario, novaSenha);

                    if (senhaAtualizada) {
                        System.out.println("Senha alterada com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

            // Fecha o scanner e a conexão
            scanner.close();
            usuarioDAO.close();

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados ou ao realizar a operação.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver de banco de dados.");
            e.printStackTrace();
        }
    }
}
