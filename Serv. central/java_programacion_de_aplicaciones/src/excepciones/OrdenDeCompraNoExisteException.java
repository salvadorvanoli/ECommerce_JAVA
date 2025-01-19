package excepciones;

//Indica la inexistencia de una categoría en el sistema.

@SuppressWarnings("serial")
public class OrdenDeCompraNoExisteException extends Exception {

 public OrdenDeCompraNoExisteException(String string) {
     super(string);
 }

}
