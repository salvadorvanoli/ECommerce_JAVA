package excepciones;

// Indica la inexistencia de un usuario en el sistema.

@SuppressWarnings("serial")
public class UsuarioNoEncontrado extends Exception {

    public UsuarioNoEncontrado(String string) {
        super(string);
    }
}
