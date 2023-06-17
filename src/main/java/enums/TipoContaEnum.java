package enums;

public enum TipoContaEnum {
    POUPANCA("Poupança", 1),
    CORRENTE("Corrente", 2),
    CONTASALARIO("Conta Salário", 3),

    /**
     *
     */
    TIPO_DESCONHECIDO("tipo de conta invalida",4);
    
    private String descricao;
    private int id;

    TipoContaEnum(String descricao, int id) {
        this.descricao = descricao;
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }
}
