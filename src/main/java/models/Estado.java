package models;

public class Estado extends AbstractBaseEntity {

    private String nome;
    private String sigla;
    private Pais pais;

    public Estado() {}

    public Estado(String nome, String abreviacao, String registroAcademico, Pais pais) {
        this.nome = nome;
        this.sigla = abreviacao;
        this.pais = pais;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String abreviacao) {
        this.sigla = abreviacao;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id='" + super.getId() + '\'' +
                "nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", pais=" + pais + '\'' +
                ", ra='" + super.getRegistroAcademico() +
                '}' + "\n";
    }
}
