package models;


import enums.TipoContaEnum;

public class Conta extends AbstractBaseEntity {
    private String numero;
    private String digito;
    private double saldo;
    private TipoContaEnum tipoConta;
    private Agencia agencia;
    private Pessoa pessoa;

    public Conta() {
    }

    public Conta( String numero, String digito, double saldo, TipoContaEnum tipoConta, Agencia agencia, Pessoa pessoa) {
        this.numero = numero;
        this.digito = digito;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
        this.agencia = agencia;
        this.pessoa = pessoa;
    }



    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoContaEnum tipoConta) {
        this.tipoConta = tipoConta;
    }


    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String toString() {
        return "Conta{" +
                ", numero='" + numero + '\'' +
                ", digito='" + digito + '\'' +
                ", saldo=" + saldo +
                ", tipoConta=" + tipoConta +
                ", agencia=" + agencia +
                ", pessoa=" + pessoa +
                '}';
    }
}
