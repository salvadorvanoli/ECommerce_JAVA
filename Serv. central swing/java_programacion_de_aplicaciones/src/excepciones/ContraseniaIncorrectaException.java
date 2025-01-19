package excepciones;

// Indica la existencia de un usuario repetido en el sistema.

@SuppressWarnings("serial")
public class ContraseniaIncorrectaException extends Exception {

    public ContraseniaIncorrectaException(String string) {
        super(string);
    }

}
