package models;

public class Pessoa {

    private int id;
    private String email;
    private String ra;

    public Pessoa() {}

    public Pessoa(int id, String email, String ra) {
        this.id = id;
        this.email = email;
        this.ra = ra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
