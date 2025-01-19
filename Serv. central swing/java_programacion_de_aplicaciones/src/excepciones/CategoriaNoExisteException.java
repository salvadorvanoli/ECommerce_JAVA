package excepciones;

// Indica la inexistencia de una categoría en el sistema.

@SuppressWarnings("serial")
public class CategoriaNoExisteException extends Exception {

    public CategoriaNoExisteException(String string) {
        super(string);
    }

}
