package models;

public class Estado {

    private int id;
    private String nome;
    private String abreviacao;
    private String ra;
    private Pais pais;

    public Estado() {}

    public Estado(int id, String nome, String abreviacao, String ra, Pais pais) {
        this.id = id;
        this.nome = nome;
        this.abreviacao = abreviacao;
        this.ra = ra;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
