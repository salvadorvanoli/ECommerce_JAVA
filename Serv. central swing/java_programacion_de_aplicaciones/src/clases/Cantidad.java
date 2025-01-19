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
public class Cantidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "numReferencia", nullable = false)
    @XmlElement
    private Producto producto;

    private int cantidad;
    
	@ManyToOne
	@JoinColumn(name = "nickname", nullable = false)
	@XmlTransient
    private Cliente cliente;
    
    // Constructor vacío 
    public Cantidad() {}
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Cantidad(int cantidad) {
		super();
		this.cantidad = cantidad;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void linkProducto(Producto producto) {
		this.producto = producto;
	}
	
	public float getSubtotal() {
		return this.cantidad*this.producto.getPrecio();
	}
	
	public Cantidad(Producto producto, int cantidad) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public DTCantidad getDTCantidad() {
		return new DTCantidad(this.cantidad, this.producto.getDTProducto());
	}
	
	public DTCantidadProducto getDTCantidadProducto() {
		return new DTCantidadProducto(this.cantidad, this.producto.getDTProducto(), this.getSubtotal());
	}
	
	public String toString() {
		return "Nombre del producto: " + this.producto.getNombreProducto() + System.lineSeparator()
        + "Cantidad del producto = " + this.cantidad + ";" + System.lineSeparator();
	}
	
	public String toString2() {
		return this.producto.getNombreProducto() + System.lineSeparator()
        + "   X" + this.cantidad + "" + System.lineSeparator();
	}
}
