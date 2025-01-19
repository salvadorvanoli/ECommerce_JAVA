package clases;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOrdenDeCompraDetallada extends DTOrdenDeCompra{
	
	@XmlElementWrapper(name = "productosCantidad")
	@XmlElement(name = "DTCantidadProducto")
	private List<DTCantidadProducto> productosCantidad;
	
	public DTOrdenDeCompraDetallada(){
	}

	public DTOrdenDeCompraDetallada(int numero, Cliente cliente, float precio, DTFecha fecha, List<DTCantidad> cantidad, List<DTCantidadProducto> lista, String estado) {
		super(numero, cliente, precio, fecha, cantidad, estado);
		this.productosCantidad = lista;
	}

	public List<DTCantidadProducto> getProductosCantidad() {
		return productosCantidad;
	}

	public void setProductosCantidad(List<DTCantidadProducto> productosCantidad) {
		this.productosCantidad = productosCantidad;
	}

	@Override
	public String toString(){
	    String retorno = "Orden de Compra " + this.getNumero() + System.lineSeparator()
	                + "Fecha: " + this.getFecha() + System.lineSeparator()
	                + "Precio total: " + this.getPrecioTotal() + System.lineSeparator()
	                + "Nombre cliente: " + this.getCliente().getNickname() + System.lineSeparator()
	                + System.lineSeparator() +  "-------------------- PRODUCTOS --------------------" + System.lineSeparator() + System.lineSeparator();
	    Integer i = 1;
	    for (DTCantidadProducto prod : this.productosCantidad){
	        retorno += "Producto número " + i.toString() + ": "  + System.lineSeparator() + prod.toString() + System.lineSeparator();
	        i++;
	    }
	    return retorno;
	}
	
	
	public float getPrecioTotal() {
	    float total = 0;
	    
	    for (DTCantidadProducto item : productosCantidad) {
	        total += item.getProducto().getPrecio() * item.getCantidad();
	    }
	    
	    return total;
	}

	
}
