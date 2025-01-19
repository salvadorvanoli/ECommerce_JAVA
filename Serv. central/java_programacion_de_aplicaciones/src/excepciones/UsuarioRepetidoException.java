package excepciones;

// Indica la existencia de un usuario repetido en el sistema.

@SuppressWarnings("serial")
public class UsuarioRepetidoException extends Exception {

    public UsuarioRepetidoException(String string) {
        super(string);
    }

}
