package br.com.fintech.view;

import br.com.fintech.dao.PlanejamentoDAO;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Planejamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PlanejamentoView {
    public static void main(String[] args) {
        try {
            // Obtém a conexão usando a ConnectionFactory
            Connection connection = ConnectionFactory.getConnection();
            PlanejamentoDAO planejamentoDAO = new PlanejamentoDAO(connection);

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                System.out.println("\n---- Menu Planejamento ----");
                System.out.println("1. Cadastrar Planejamento");
                System.out.println("2. Buscar Planejamento por ID");
                System.out.println("3. Listar Todos os Planejamentos");
                System.out.println("4. Atualizar Planejamento");
                System.out.println("5. Excluir Planejamento");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir nova linha

                switch (opcao) {
                    case 1:
                        cadastrarPlanejamento(planejamentoDAO, scanner);
                        break;
                    case 2:
                        buscarPlanejamentoPorId(planejamentoDAO, scanner);
                        break;
                    case 3:
                        listarTodosPlanejamentos(planejamentoDAO);
                        break;
                    case 4:
                        atualizarPlanejamento(planejamentoDAO, scanner);
                        break;
                    case 5:
                        excluirPlanejamento(planejamentoDAO, scanner);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (opcao != 0);

            connection.close();
            scanner.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    }

    private static void cadastrarPlanejamento(PlanejamentoDAO dao, Scanner scanner) throws SQLException {
        System.out.println("\n---- Cadastrar Planejamento ----");

        System.out.print("ID do Usuário: ");
        int idUsuario = scanner.nextInt();

        scanner.nextLine(); // Consumir nova linha
        System.out.print("Descrição do Planejamento: ");
        String dsPlanejamento = scanner.nextLine();

        System.out.print("Valor Alvo: ");
        float vlValorAlvo = scanner.nextFloat();

        System.out.print("Valor Inicial: ");
        float vlValorInicial = scanner.nextFloat();

        scanner.nextLine(); // Consumir nova linha
        System.out.print("Data de Início (yyyy-mm-dd): ");
        Date dtInicio = java.sql.Date.valueOf(scanner.nextLine());

        System.out.print("Data de Fim (yyyy-mm-dd): ");
        Date dtFim = java.sql.Date.valueOf(scanner.nextLine());

        Planejamento planejamento = new Planejamento(0, idUsuario, dsPlanejamento, vlValorAlvo, vlValorInicial, dtInicio, dtFim);
        dao.inserirPlanejamento(planejamento);

        System.out.println("Planejamento cadastrado com sucesso!");
    }

    private static void buscarPlanejamentoPorId(PlanejamentoDAO dao, Scanner scanner) throws SQLException {
        System.out.print("\nDigite o ID do Planejamento que deseja buscar: ");
        int id = scanner.nextInt();

        Planejamento planejamento = dao.buscarPlanejamentoPorId(id);
        if (planejamento != null) {
            System.out.println("\nPlanejamento encontrado: " + planejamento);
        } else {
            System.out.println("Planejamento não encontrado.");
        }
    }

    private static void listarTodosPlanejamentos(PlanejamentoDAO dao) throws SQLException {
        System.out.println("\n---- Listar Todos os Planejamentos ----");
        List<Planejamento> planejamentos = dao.listarTodosPlanejamentos();

        if (planejamentos.isEmpty()) {
            System.out.println("Nenhum planejamento cadastrado.");
        } else {
            for (Planejamento planejamento : planejamentos) {
                System.out.println(planejamento);
            }
        }
    }

    private static void atualizarPlanejamento(PlanejamentoDAO dao, Scanner scanner) throws SQLException {
        System.out.print("\nDigite o ID do Planejamento que deseja atualizar: ");
        int id = scanner.nextInt();

        Planejamento planejamento = dao.buscarPlanejamentoPorId(id);
        if (planejamento != null) {
            System.out.print("Nova Descrição do Planejamento: ");
            scanner.nextLine(); // Consumir nova linha
            planejamento.setDsPlanejamento(scanner.nextLine());

            System.out.print("Novo Valor Alvo: ");
            planejamento.setVlValorAlvo(scanner.nextFloat());

            System.out.print("Novo Valor Inicial: ");
            planejamento.setVlValorInicial(scanner.nextFloat());

            scanner.nextLine(); // Consumir nova linha
            System.out.print("Nova Data de Início (yyyy-mm-dd): ");
            planejamento.setDtInicio(java.sql.Date.valueOf(scanner.nextLine()));

            System.out.print("Nova Data de Fim (yyyy-mm-dd): ");
            planejamento.setDtFim(java.sql.Date.valueOf(scanner.nextLine()));

            dao.atualizarPlanejamento(planejamento);
            System.out.println("Planejamento atualizado com sucesso!");
        } else {
            System.out.println("Planejamento não encontrado.");
        }
    }

    private static void excluirPlanejamento(PlanejamentoDAO dao, Scanner scanner) throws SQLException {
        System.out.print("\nDigite o ID do Planejamento que deseja excluir: ");
        int id = scanner.nextInt();

        Planejamento planejamento = dao.buscarPlanejamentoPorId(id);
        if (planejamento != null) {
            dao.excluirPlanejamento(id);
            System.out.println("Planejamento excluído com sucesso!");
        } else {
            System.out.println("Planejamento não encontrado.");
        }
    }
}

