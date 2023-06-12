package models;

public class Pais extends AbstractBaseEntity {

    private String nome;
    private String abreviacao;

    public Pais() {}

    public Pais(int id, String nome, String abreviacao, String registroAcademico) {
        super(id, registroAcademico);
        this.nome = nome;
        this.abreviacao = abreviacao;
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

    @Override
    public String toString() {
        return "Pais{" +
                "id='" + super.getId() + '\'' +
                ", nome='" + nome + '\'' +
                ", abreviacao='" + abreviacao + '\'' +
                ", RA='" + super.getRegistroAcademico() + '\'' +
                '}' + "\n";
    }
}
