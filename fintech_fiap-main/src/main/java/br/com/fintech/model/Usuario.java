package br.com.fintech.model;

public class Usuario {
    private Long idUsuario;
    private String nmUsuario;
    private String dsEmail;
    private String dsSenha;
    private String tpUsuario;

    // Construtor com idUsuario
    public Usuario(Long idUsuario, String nmUsuario, String dsEmail, String dsSenha, String tpUsuario) {
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
        this.tpUsuario = tpUsuario;
    }
    public Usuario( String nmUsuario, String dsEmail, String dsSenha, String tpUsuario) {
        this.nmUsuario = nmUsuario;
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
        this.tpUsuario = tpUsuario;
    }

    // Construtor vazio

    public Long getIdUsuario() {
        return idUsuario;
    }

    // Removido o setter para idUsuario, pois é um atributo final e não pode ser alterado depois de inicializado

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }

    public String getTpUsuario() {
        return tpUsuario;
    }

    public void setTpUsuario(String tpUsuario) {
        this.tpUsuario = tpUsuario;
    }
}
