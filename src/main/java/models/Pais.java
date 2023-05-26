package models;

public class Pais {

    private int id;
    private String nome;
    private String abreviacao;
    private String ra;

    public Pais() {}

    public Pais(int id, String nome, String abreviacao, String ra) {
        this.id = id;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.ra = ra;
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

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
