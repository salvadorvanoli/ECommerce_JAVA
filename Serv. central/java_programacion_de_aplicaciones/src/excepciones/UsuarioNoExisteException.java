package excepciones;

// Indica la inexistencia de un usuario en el sistema.

@SuppressWarnings("serial")
public class UsuarioNoExisteException extends Exception {

    public UsuarioNoExisteException(String string) {
        super(string);
    }
}
