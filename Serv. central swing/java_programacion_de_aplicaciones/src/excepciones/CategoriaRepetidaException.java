package excepciones;

// Indica la existencia de una categoría repetida en el sistema.

@SuppressWarnings("serial")
public class CategoriaRepetidaException extends Exception {

    public CategoriaRepetidaException(String string) {
        super(string);
    }

}
