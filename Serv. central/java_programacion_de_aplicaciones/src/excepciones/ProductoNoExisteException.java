package excepciones;

// Indica la inexistencia de un producto en una categoría.

@SuppressWarnings("serial")
public class ProductoNoExisteException extends Exception {

    public ProductoNoExisteException(String string) {
        super(string);
    }
}
