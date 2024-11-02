package br.com.fintech.model;

public class Investimento {
    private int idInvestimento;
    private int idUsuario;
    private String tbInvestimento;
    private String mnInvestimento;
    private float vlInicial;
    private float vlRentabilidade;
    private String dsRisco;

    // Construtor vazio
    public Investimento() {
        this.idInvestimento = 0; // Inicializa idInvestimento como 0
    }

    // Construtor completo (sem datas)
    public Investimento(int idInvestimento, int idUsuario, String tbInvestimento, String mnInvestimento, float vlInicial,
                        float vlRentabilidade, String dsRisco) {
        this.idInvestimento = idInvestimento;
        this.idUsuario = idUsuario;
        this.tbInvestimento = tbInvestimento;
        this.mnInvestimento = mnInvestimento;
        this.vlInicial = vlInicial;
        this.vlRentabilidade = vlRentabilidade;
        this.dsRisco = dsRisco;
    }

    public Investimento(int idUsuario, String tbInvestimento, String mnInvestimento, float vlInicial,
                        float vlRentabilidade, String dsRisco) {
        this.idUsuario = idUsuario;
        this.tbInvestimento = tbInvestimento;
        this.mnInvestimento = mnInvestimento;
        this.vlInicial = vlInicial;
        this.vlRentabilidade = vlRentabilidade;
        this.dsRisco = dsRisco;
    }

    // Getters e Setters
    public int getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(int idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTbInvestimento() {
        return tbInvestimento;
    }

    public void setTpInvestimento(String tpInvestimento) {
        this.tbInvestimento = tpInvestimento;
    }

    public String getMnInvestimento() {
        return mnInvestimento;
    }

    public void setMnInvestimento(String mnInvestimento) {
        this.mnInvestimento = mnInvestimento;
    }

    public float getVlInicial() {
        return vlInicial;
    }

    public void setVlInicial(float vlInicial) {
        this.vlInicial = vlInicial;
    }

    public float getVlRentabilidade() {
        return vlRentabilidade;
    }

    public void setVlRentabilidade(float vlRentabilidade) {
        this.vlRentabilidade = vlRentabilidade;
    }

    public String getDsRisco() {
        return dsRisco;
    }

    public void setDsRisco(String dsRisco) {
        this.dsRisco = dsRisco;
    }
}
