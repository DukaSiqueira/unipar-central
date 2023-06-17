package models;

public class Pessoa extends AbstractBaseEntity {
    private String email;

    public Pessoa() {}

    public Pessoa(int id, String email, String registroAcademico) {
        super(id, registroAcademico);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + super.getId() + '\'' +
                "email='" + email + '\'' +
                "ra='" + super.getRegistroAcademico() + '\'' +
                '}' + "\n";
    }
}
