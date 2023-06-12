package exceptions;

public class TamanhoCampoInvalidoException extends Exception {

    public TamanhoCampoInvalidoException(String campo, int tamanho) {
        super("O campo " + campo + " é maior que o tamanho aceito de " + tamanho + " caracteres!");
    }
}
