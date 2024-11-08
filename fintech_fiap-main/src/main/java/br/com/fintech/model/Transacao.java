package br.com.fintech.model;

public class Transacao {

    private int idTransacao;
    private int idUsuario;      // Adicionado id_usuario
    private int idCategoria;
    private String tpTransacao;
    private String dsTransacao;
    private float vlTransacao;

    // Construtor vazio
    public Transacao(int idTransacao, String tpTransacao, String dsTransacao, float vlTransacao) {}

    // Construtor com parâmetros
    public Transacao(int idTransacao, int idUsuario, int idCategoria, String tpTransacao, String dsTransacao, float vlTransacao) {
        this.idTransacao = idTransacao;
        this.idUsuario = idUsuario;      // Atribuição do id_usuario
        this.idCategoria = idCategoria;
        this.tpTransacao = tpTransacao;
        this.dsTransacao = dsTransacao;
        this.vlTransacao = vlTransacao;
    }

    // Removido o erro de sintaxe aqui
    public Transacao(int idUsuario, int idCategoria, String tpTransacao, String dsTransacao, float vlTransacao) {
        this.idUsuario = idUsuario;      // Atribuição do id_usuario
        this.idCategoria = idCategoria;
        this.tpTransacao = tpTransacao;
        this.dsTransacao = dsTransacao;
        this.vlTransacao = vlTransacao;
    }


    // Getters e Setters
    public int getIdTransacao() {
        return Math.toIntExact(idTransacao);
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTpTransacao() {
        return tpTransacao;
    }

    public void setTpTransacao(String tpTransacao) {
        this.tpTransacao = tpTransacao;
    }

    public String getDsTransacao() {
        return dsTransacao;
    }

    public void setDsTransacao(String dsTransacao) {
        this.dsTransacao = dsTransacao;
    }

    public float getVlTransacao() {
        return vlTransacao;
    }

    public void setVlTransacao(float vlTransacao) {
        this.vlTransacao = vlTransacao;
    }
}
