package br.com.fintech.model;

import java.util.Date;

public class Planejamento {   private int idTransacao;
    private int idUsuario;
    private String dsPlanejamento;
    private float vlValorAlvo;
    private float vlValorInicial;
    private Date dtInicio;
    private Date dtFim;

    // Construtor vazio
    public Planejamento() {}

    // Construtor com parâmetros
    public Planejamento(int idTransacao, int idUsuario, String dsPlanejamento, float vlValorAlvo, float vlValorInicial, Date dtInicio, Date dtFim) {
        this.idTransacao = idTransacao;
        this.idUsuario = idUsuario;
        this.dsPlanejamento = dsPlanejamento;
        this.vlValorAlvo = vlValorAlvo;
        this.vlValorInicial = vlValorInicial;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }
    public Planejamento(int idUsuario, String dsPlanejamento, float vlValorAlvo, float vlValorInicial, Date dtInicio, Date dtFim) {
        this.idUsuario = idUsuario;
        this.dsPlanejamento = dsPlanejamento;
        this.vlValorAlvo = vlValorAlvo;
        this.vlValorInicial = vlValorInicial;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
    }

    // Getters e Setters
    public int getIdTransacao() {
        return idTransacao;
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

    public String getDsPlanejamento() {
        return dsPlanejamento;
    }

    public void setDsPlanejamento(String dsPlanejamento) {
        this.dsPlanejamento = dsPlanejamento;
    }

    public float getVlValorAlvo() {
        return vlValorAlvo;
    }

    public void setVlValorAlvo(float vlValorAlvo) {
        this.vlValorAlvo = vlValorAlvo;
    }

    public float getVlValorInicial() {
        return vlValorInicial;
    }

    public void setVlValorInicial(float vlValorInicial) {
        this.vlValorInicial = vlValorInicial;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    @Override
    public String toString() {
        return "Planejamento{" +
                "idTransacao=" + idTransacao +
                ", idUsuario=" + idUsuario +
                ", dsPlanejamento='" + dsPlanejamento + '\'' +
                ", vlValorAlvo=" + vlValorAlvo +
                ", vlValorInicial=" + vlValorInicial +
                ", dtInicio=" + dtInicio +
                ", dtFim=" + dtFim +
                '}';
    }
}
