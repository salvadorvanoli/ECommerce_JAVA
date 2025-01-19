package excepciones;

//Indica la existencia de una categoría repetida en el sistema.

@SuppressWarnings("serial")
public class OrdenDeCompraRepetidaException extends Exception {

 public OrdenDeCompraRepetidaException(String string) {
     super(string);
 }

}
