package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCantidadProducto {
	        
	private int cantidad;
	
	@XmlElement
	private DTProducto producto;
	
	private float subtotal;
	
	public DTCantidadProducto() {}
	 
	public DTCantidadProducto(int cantidad, DTProducto producto, float subtotal) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.subtotal = subtotal;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public DTProducto getProducto() {
		return producto;
	}
	public void setProducto(DTProducto producto) {
		this.producto = producto;
	}
	public float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	@Override
	public String toString() {
		return this.producto.getNombre() + " - Cantidad: " + this.cantidad + " - Subtotal: " + this.subtotal;
	}
	
	
}
