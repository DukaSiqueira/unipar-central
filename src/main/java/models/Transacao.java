package models;

import com.mycompany.unipar.central.enums.TipoTransacaoEnum;

import java.sql.Date;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transacao extends AbstractBaseEntity {
    private LocalTime dataHora;
    private double valor;
    private String ra;
    private TipoTransacaoEnum tipoTransacao;
    private Conta contaOrigem;
    private Conta contaDestino;

    public Transacao(double valor,String ra, TipoTransacaoEnum tipoTransacao, Conta contaOrigem, Conta contaDestino) {
        this.dataHora = LocalTime.now();
        this.valor = valor;
        this.ra = ra;
        this.tipoTransacao = tipoTransacao;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    public Transacao(){

    }

    public String getDataHora() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHora.format(formato);
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = LocalTime.now();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoTransacaoEnum getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacaoEnum tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }



    @Override
    public String toString() {
        return "Transacao{" +
                "dataHora=" + dataHora +
                ", valor=" + valor +
                ", tipoTransacao=" + tipoTransacao +
                ", contaOrigem=" + contaOrigem +
                ", contaDestino=" + contaDestino +
                '}';
    }
}
