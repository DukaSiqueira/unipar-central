package enums;

public enum OperadoraEnum {
    VIVO(15, "Vivo"),
    CLARO(21, "Claro"),
    TIM(41, "Tim"),
    ALGAR(12, "Algar"),
    SERCOMTEL(43, "Sercomtel"),
    GENTE(10, "GenteTelecom"),
    TRANSIT(17, "TransitTelecom"),
    NEOTELECOM (64, "NeoTelecom");

    private int codigo;
    private String descrição;

    OperadoraEnum(int codigo, String descrição) {
        this.codigo = codigo;
        this.descrição = descrição;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescrição() {
        return descrição;
    }
}
