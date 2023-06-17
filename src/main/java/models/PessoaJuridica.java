package models;

public class PessoaJuridica extends Pessoa {

    private String razaoSocial;
    private String cnpj;
    private String cnaePrincipal;
    private String nomeFantasia;

    public PessoaJuridica() {}

    public PessoaJuridica(int id, String email, String registroAcademico, String razaoSocial, String cnpj, String cnaePrincipal, String nomeFantasia) {
        super(id, email, registroAcademico);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cnaePrincipal = cnaePrincipal;
        this.nomeFantasia = nomeFantasia;
    }
}
