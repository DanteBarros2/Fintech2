package br.com.fintech.view;

import br.com.fintech.dao.CategoriaDAO;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CadastroCategoriaView {

    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Cadastrar Categoria");
                System.out.println("2 - Pesquisar Categoria");
                System.out.println("3 - Deletar Categoria");
                System.out.println("4 - Listar Todas as Categorias");
                System.out.println("5 - Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();  // Consumir a nova linha após o número

                switch (opcao) {
                    case 1:
                        cadastrarCategoria(scanner, categoriaDAO);
                        break;
                    case 2:
                        pesquisarCategoria(scanner, categoriaDAO);
                        break;
                    case 3:
                        deletarCategoria(scanner, categoriaDAO);
                        break;
                    case 4:
                        listarCategorias(categoriaDAO);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao conectar ao banco de dados ou ao operar com Categoria.");
            e.printStackTrace();
        }
    }

    private static void cadastrarCategoria(Scanner scanner, CategoriaDAO categoriaDAO) throws SQLException {
        System.out.println("\nCadastro de Categoria");

        System.out.print("Digite o Nome da Categoria: ");
        String nmCategoria = scanner.nextLine();

        System.out.print("Digite o Tipo da Categoria (Ex: Receita, Despesa): ");
        String tpCategoria = scanner.nextLine();

        Categoria categoria = new Categoria(nmCategoria, tpCategoria);
        categoriaDAO.inserirCategoria(categoria);

        System.out.println("Categoria cadastrada com sucesso!");
    }

    private static void pesquisarCategoria(Scanner scanner, CategoriaDAO categoriaDAO) throws SQLException {
        System.out.print("\nDigite o Nome da Categoria para pesquisa: ");
        String nmCategoria = scanner.nextLine();

        Categoria categoria = categoriaDAO.pesquisarCategoriaPorNome(nmCategoria);

        if (categoria != null) {
            System.out.println("Categoria encontrada: ");
            System.out.println("Nome: " + categoria.getNmCategoria());
            System.out.println("Tipo: " + categoria.getTpCategoria());
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }

    private static void deletarCategoria(Scanner scanner, CategoriaDAO categoriaDAO) throws SQLException {
        System.out.print("\nDigite o Nome da Categoria para exclusão: ");
        String nmCategoria = scanner.nextLine();

        boolean deletado = categoriaDAO.deletarCategoriaPorNome(nmCategoria);

        if (deletado) {
            System.out.println("Categoria deletada com sucesso.");
        } else {
            System.out.println("Categoria não encontrada para exclusão.");
        }
    }

    private static void listarCategorias(CategoriaDAO categoriaDAO) throws SQLException {
        System.out.println("\nLista de Categorias:");
        List<Categoria> categorias = categoriaDAO.listarCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            for (Categoria categoria : categorias) {
                System.out.println("Nome: " + categoria.getNmCategoria() + " | Tipo: " + categoria.getTpCategoria());
            }
        }
    }
}
