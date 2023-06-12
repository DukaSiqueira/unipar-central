package exceptions;

public class CampoNaoInformadoException extends Exception {

    public CampoNaoInformadoException (String campo) {
        super("O campo " + campo + " não foi informado!");
    }

}
