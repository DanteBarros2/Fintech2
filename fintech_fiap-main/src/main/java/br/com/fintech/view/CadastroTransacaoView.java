package br.com.fintech.view;

import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Transacao;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CadastroTransacaoView {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            int opcao;

            do {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Cadastrar Transação");
                System.out.println("2 - Deletar Transação");
                System.out.println("3 - Pesquisar Transações por ID do Usuário");
                System.out.println("4 - Sair");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        cadastrarTransacao(scanner, transacaoDAO);
                        break;
                    case 2:
                        deletarTransacao(scanner, transacaoDAO);
                        break;
                    case 3:
                        pesquisarTransacao(scanner, transacaoDAO);
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 4);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    }

    private static void cadastrarTransacao(Scanner scanner, TransacaoDAO transacaoDAO) throws SQLException, ClassNotFoundException {
        System.out.println("Cadastro de Transação");

        System.out.print("Digite o ID do Usuário: ");
        int idUsuario = scanner.nextInt();

        System.out.print("Digite o ID da Categoria: ");
        int idCategoria = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Digite o Tipo de Transação: ");
        String tpTransacao = scanner.nextLine();

        System.out.print("Digite a Descrição da Transação: ");
        String dsTransacao = scanner.nextLine();

        System.out.print("Digite o Valor da Transação: ");
        float vlTransacao = scanner.nextFloat();

        Transacao transacao = new Transacao(idUsuario, idCategoria, tpTransacao, dsTransacao, vlTransacao);
        transacaoDAO.cadastrarTransacao(transacao);

        System.out.println("Transação cadastrada com sucesso!");
    }

    private static void deletarTransacao(Scanner scanner, TransacaoDAO transacaoDAO) throws SQLException, ClassNotFoundException {
        System.out.print("Digite o ID da Transação a ser deletada: ");
        int id = scanner.nextInt();

        boolean sucesso = transacaoDAO.deletarTransacao(id);
        if (sucesso) {
            System.out.println("Transação deletada com sucesso!");
        } else {
            System.out.println("Transação não encontrada.");
        }
    }

    private static void pesquisarTransacao(Scanner scanner, TransacaoDAO transacaoDAO) throws SQLException, ClassNotFoundException {
        System.out.print("Digite o ID do Usuário para buscar transações: ");
        int idUsuario = scanner.nextInt();

        List<Transacao> transacoes = transacaoDAO.pesquisarTransacao(idUsuario);
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação encontrada para este usuário.");
        } else {
            System.out.println("Transações encontradas:");
            for (Transacao transacao : transacoes) {
                System.out.println("ID: " + transacao.getIdTransacao() + ", Tipo: " + transacao.getTpTransacao() +
                        ", Descrição: " + transacao.getDsTransacao() + ", Valor: " + transacao.getVlTransacao());
            }
        }
    }
}

