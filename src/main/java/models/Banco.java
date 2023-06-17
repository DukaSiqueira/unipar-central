package models;

import java.sql.Timestamp;
import java.util.ArrayList;


public class Banco extends AbstractBaseEntity {

    private int id;
    private String nome;

    private Timestamp dataCadastro;

    public Banco( int id,String nome) {
        this.nome = nome;
        this.id = id;
    }

    public Banco() {

    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Banco{" +
                ", id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", ra='" + super.getRegistroAcademico() + '\'' +
                '}';
    }
}
