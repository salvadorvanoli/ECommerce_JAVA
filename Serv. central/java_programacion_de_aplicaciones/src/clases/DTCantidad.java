package clases;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCantidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Embedded
	@XmlElement
	private DTProducto producto;

	@ManyToOne
    @JoinColumn(name = "numero", nullable = false)
	@XmlTransient
	private OrdenDeCompra ordenPadre;
	
	private int cantidad;
	
	public DTCantidad() {}
	
	public DTCantidad(int cantidad, DTProducto prod, OrdenDeCompra ordenPadre) {
		super();
		this.cantidad = cantidad;
		this.producto = prod;
		this.ordenPadre = ordenPadre;
	}
	
	public DTCantidad(int cantidad, DTProducto prod) {
		super();
		this.cantidad = cantidad;
		this.producto = prod;
	}
	
	public DTProducto getProducto() {
		return this.producto;
	}

	public int getId() {
		return this.id;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}

	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public void setOrdenPadre(OrdenDeCompra ord) {
		this.ordenPadre = ord;
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this.cantidad, this.producto, this.getSubtotal());
	}
	
	public String toString() {
		return "Producto: " + this.producto.getNombre() + " - Cantidad: " + this.cantidad + " - Subtotal: " + this.getSubtotal();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setProducto(DTProducto producto) {
		this.producto = producto;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
