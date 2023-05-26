package models;

public class Cidade {

    private int id;
    private String nome;
    private String ra;
    private Estado estado;

    public Cidade() {}

    public Cidade(int id, String nome, String ra, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
