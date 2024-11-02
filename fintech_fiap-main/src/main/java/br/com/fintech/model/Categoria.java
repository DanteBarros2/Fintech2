package br.com.fintech.model;

public class Categoria {
    private int idCategoria;      // Chave primária
    private String nmCategoria;   // Nome da categoria
    private String tpCategoria;   // Tipo da categoria

    // Construtor vazio
    public Categoria() {
    }

    // Construtor com parâmetros

    public Categoria(int idCategoria, String nmCategoria, String tpCategoria) {
        this.idCategoria = idCategoria;
        this.nmCategoria = nmCategoria;
        this.tpCategoria = tpCategoria;
    }

    public Categoria(String nmCategoria, String tpCategoria) {

        this.nmCategoria = nmCategoria;
        this.tpCategoria = tpCategoria;
    }

    // Getters e Setters


    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNmCategoria() {
        return nmCategoria;
    }

    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }

    public String getTpCategoria() {
        return tpCategoria;
    }

    public void setTpCategoria(String tpCategoria) {
        this.tpCategoria = tpCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                ", nmCategoria='" + nmCategoria + '\'' +
                ", tpCategoria='" + tpCategoria + '\'' +
                '}';
    }

}
