package enums;

public enum TipoTransacaoEnum {
    PIX(1, "Transferência PIX"),
    TED(2, "Transferência Eletrônica Disponível TED"),
    SAQUE(3, "Saque"),
    DEPOSITO(4, "Depósito"),
    TIPO_DESCONHECIDO(5, "Tipo de transação desconhecida");

    private int id;
    private String descricao;

    TipoTransacaoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
