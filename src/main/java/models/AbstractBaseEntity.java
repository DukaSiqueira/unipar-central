package models;

abstract class AbstractBaseEntity {

    private int id;
    private String registroAcademico;

    public AbstractBaseEntity() {
    }

    public AbstractBaseEntity(int id, String registroAcademico) {
        this.id = id;
        this.registroAcademico = registroAcademico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistroAcademico() {
        return registroAcademico;
    }

    public void setRegistroAcademico(String registroAcademico) {
        this.registroAcademico = registroAcademico;
    }
}
