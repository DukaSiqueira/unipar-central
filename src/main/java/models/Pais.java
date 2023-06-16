package models;

public class Pais extends AbstractBaseEntity {

    private String nome;
    private String sigla;

    public Pais() {}

    public Pais(int id, String nome, String abreviacao, String registroAcademico) {
        super(id, registroAcademico);
        this.nome = nome;
        this.sigla = abreviacao;
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

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "id='" + super.getId() + '\'' +
                ", nome='" + nome + '\'' +
                ", abreviacao='" + sigla + '\'' +
                ", RA='" + super.getRegistroAcademico() + '\'' +
                '}' + "\n";
    }
}
