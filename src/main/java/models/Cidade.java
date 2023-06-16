package models;

public class Cidade extends AbstractBaseEntity {

    private String nome;
    private Estado estado;

    public Cidade() {}

    public Cidade(int id, String nome, String registroAcademico, Estado estado) {
        super(id, registroAcademico);
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id='" + super.getId() + '\'' +
                "nome='" + nome + '\'' +
                ", estado=" + estado + '\'' +
                ", ra='" + super.getRegistroAcademico() +
                '}' + "\n";
    }
}
