package exceptions;

public class EntidadeNaoInformadaException extends Exception {

    public EntidadeNaoInformadaException(String entidade) {
        super("A entidade " + entidade + " não foi informada!");
    }
}
