package models;

public class PessoaJuridica{

    private Pessoa pessoa;
    private String razaoSocial;
    private String cnpj;
    private String cnaePrincipal;
    private String nomeFantasia;

    public PessoaJuridica() {}

    public PessoaJuridica(Pessoa pessoa, String razaoSocial, String cnpj, String cnaePrincipal, String nomeFantasia) {
        this.pessoa = pessoa;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cnaePrincipal = cnaePrincipal;
        this.nomeFantasia = nomeFantasia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnaePrincipal() {
        return cnaePrincipal;
    }

    public void setCnaePrincipal(String cnaePrincipal) {
        this.cnaePrincipal = cnaePrincipal;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "pessoa=" + pessoa +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", cnaePrincipal='" + cnaePrincipal + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                '}';
    }
}
