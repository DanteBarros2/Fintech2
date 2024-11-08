package br.com.fintech.dao;

import br.com.fintech.model.Transacao;
import br.com.fintech.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransacaoDAO {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            int opcao;

            do {
                System.out.println("Escolha uma opção:");
                System.out.println("1 - Cadastrar Transação");
                System.out.println("2 - Deletar Transação");
                System.out.println("3 - Pesquisar Transações por ID do Usuário");
                System.out.println("4 - Alterar Transação");
                System.out.println("5 - Sair");
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
                        alterarTransacao(scanner, transacaoDAO);
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 5);

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

    public void cadastrarTransacao(Transacao transacao) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO TB_FIN_TRANSACAO (id_transacao, id_usuario, id_categoria, tp_transacao, ds_transacao, vl_transacao) " +
                "VALUES (seq_fintransacao.nextval, ?, ?, ?, ?,?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, transacao.getIdUsuario());
            stmt.setInt(2, transacao.getIdCategoria());
            stmt.setString(3, transacao.getTpTransacao());
            stmt.setString(4, transacao.getDsTransacao());
            stmt.setFloat(5, transacao.getVlTransacao());

            stmt.executeUpdate();
        }
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

    public boolean deletarTransacao(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM TB_FIN_TRANSACAO WHERE id_transacao = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
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

    public List<Transacao> pesquisarTransacao(int idUsuario) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM TB_FIN_TRANSACAO WHERE id_usuario = ?";
        List<Transacao> transacoes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idTransacao = rs.getInt("id_transacao");
                int idCategoria = rs.getInt("id_categoria");
                String tpTransacao = rs.getString("tp_transacao");
                String dsTransacao = rs.getString("ds_transacao");
                float vlTransacao = rs.getFloat("vl_transacao");

                Transacao transacao = new Transacao((int) idTransacao, idUsuario, idCategoria, tpTransacao, dsTransacao, vlTransacao);
                transacoes.add(transacao);
            }
        }
        return transacoes;
    }

    private static void alterarTransacao(Scanner scanner, TransacaoDAO transacaoDAO) throws SQLException, ClassNotFoundException {
        System.out.print("Digite o ID da Transação a ser alterada: ");
        int idTransacao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        System.out.print("Digite o novo Tipo de Transação: ");
        String tpTransacao = scanner.nextLine();

        System.out.print("Digite a nova Descrição da Transação: ");
        String dsTransacao = scanner.nextLine();

        System.out.print("Digite o novo Valor da Transação: ");
        float vlTransacao = scanner.nextFloat();

        // Alterar a transação no banco de dados
        Transacao transacao = new Transacao(idTransacao, tpTransacao, dsTransacao, vlTransacao);
        boolean sucesso = transacaoDAO.alterarTransacao(transacao);

        if (sucesso) {
            System.out.println("Transação alterada com sucesso!");
        } else {
            System.out.println("Erro ao alterar a transação.");
        }
    }

    public boolean alterarTransacao(Transacao transacao) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TB_FIN_TRANSACAO SET tp_transacao = ?, ds_transacao = ?, vl_transacao = ? " +
                "WHERE id_transacao = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, transacao.getTpTransacao());
            stmt.setString(2, transacao.getDsTransacao());
            stmt.setFloat(3, transacao.getVlTransacao());
            stmt.setInt(4, transacao.getIdTransacao());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
