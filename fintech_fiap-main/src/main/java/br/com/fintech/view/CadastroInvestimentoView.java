package br.com.fintech.view;

import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.factory.ConnectionFactory;
import br.com.fintech.model.Investimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CadastroInvestimentoView {

    private Connection connection;

    // Construtor que recebe a conexão com o banco de dados
    public CadastroInvestimentoView(ConnectionFactory connectionFactory) throws SQLException, ClassNotFoundException {
        this.connection = connectionFactory.getConnection(); // Inicializando a conexão
    }

    // Método para cadastrar um investimento
    public void cadastrarInvestimento() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Cadastro de Investimento ===");

        System.out.print("ID do Usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após o nextInt()

        System.out.print("Tipo de Investimento: ");
        String tipoInvestimento = scanner.nextLine();

        System.out.print("Nome do Investimento: ");
        String nomeInvestimento = scanner.nextLine();

        System.out.print("Valor Inicial: ");
        float valorInicial = scanner.nextFloat();

        System.out.print("Valor de Rentabilidade: ");
        float valorRentabilidade = scanner.nextFloat();
        scanner.nextLine(); // Consumir a nova linha após o nextFloat()

        System.out.print("Descrição do Risco: ");
        String descricaoRisco = scanner.nextLine();

        try {
            // Criando o objeto Investimento com os dados recebidos
            Investimento investimento = new Investimento(idUsuario, tipoInvestimento, nomeInvestimento, valorInicial, valorRentabilidade, descricaoRisco);

            // Preparando a consulta SQL para inserir um investimento (sem datas)
            String sql = "INSERT INTO TB_FIN_INVESTIMENTO (id_investimento, id_usuario, tp_investimento, nm_investimento, vl_inicial, vl_rentabilidade, ds_risco) VALUES (NULL, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, investimento.getIdUsuario());
            stmt.setString(2, investimento.getTbInvestimento());
            stmt.setString(3, investimento.getMnInvestimento());
            stmt.setFloat(4, investimento.getVlInicial());
            stmt.setFloat(5, investimento.getVlRentabilidade());
            stmt.setString(6, investimento.getDsRisco());

            // Executando a inserção no banco de dados
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Investimento cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar o investimento.");
        }
    }

    // Método para deletar um investimento por ID
    public void deletarInvestimento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do investimento a ser deletado: ");
        int idInvestimento = scanner.nextInt();

        try {
            String sql = "DELETE FROM TB_FIN_INVESTIMENTO WHERE id_investimento = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idInvestimento);

            int rowsAffected = stmt.executeUpdate();
            stmt.close();

            if (rowsAffected > 0) {
                System.out.println("Investimento deletado com sucesso!");
            } else {
                System.out.println("Investimento não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao deletar o investimento.");
        }
    }

    // Método para pesquisar um investimento pelo nome
    public void pesquisarInvestimento() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do investimento para pesquisa: ");
        String nomeInvestimento = scanner.nextLine();

        try {
            String sql = "SELECT * FROM TB_FIN_INVESTIMENTO WHERE nm_investimento LIKE ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + nomeInvestimento + "%");

            ResultSet rs = stmt.executeQuery();

            boolean encontrado = false;
            while (rs.next()) {
                encontrado = true;
                System.out.println("ID: " + rs.getInt("id_investimento"));
                System.out.println("ID do Usuário: " + rs.getInt("id_usuario"));
                System.out.println("Tipo de Investimento: " + rs.getString("tp_investimento"));
                System.out.println("Nome: " + rs.getString("nm_investimento"));
                System.out.println("Valor Inicial: " + rs.getFloat("vl_inicial"));
                System.out.println("Valor de Rentabilidade: " + rs.getFloat("vl_rentabilidade"));
                System.out.println("Descrição do Risco: " + rs.getString("ds_risco"));
                System.out.println("--------------");
            }
            stmt.close();

            if (!encontrado) {
                System.out.println("Nenhum investimento encontrado com esse nome.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao pesquisar o investimento.");
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionFactory connectionFactory = new ConnectionFactory(); // Inicializando a ConnectionFactory
        CadastroInvestimentoView view = new CadastroInvestimentoView(connectionFactory); // Instanciando a view

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar Investimento");
        System.out.println("2 - Deletar Investimento");
        System.out.println("3 - Pesquisar Investimento");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha após o nextInt()

        switch (opcao) {
            case 1:
                view.cadastrarInvestimento();
                break;
            case 2:
                view.deletarInvestimento();
                break;
            case 3:
                view.pesquisarInvestimento();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
