package clases;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOrdenDeCompra {
	
	private int numero;
	
	@XmlElement
	private DTFecha fecha;
	
	private float precioTotal;
	
	@XmlTransient
	private Cliente cliente;
	
	@XmlElementWrapper(name = "cantidades")
	@XmlElement(name = "DTCantidad")
	private List<DTCantidad> cantidades;
	
	private String estado;
	
	public DTOrdenDeCompra() {
    }
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public List<DTCantidad> getCantidades() {
		return cantidades;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public DTFecha getFecha() {
		return fecha;
	}
	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public DTOrdenDeCompra(int numero, Cliente cliente, float precio, DTFecha fecha, List<DTCantidad> cantidades, String estado) {
		super();
		this.numero = numero;
		this.cliente = cliente;
		this.precioTotal = precio;
		this.fecha = fecha;
		this.cantidades = cantidades;
		this.estado = estado;
	}
	
	 @Override
	    public String toString() {
	        return "Número de la orden: " + numero;
	    }
	 
	 public String toStringCliente() {
			return this.getCliente().getNickname();
		}
	 
	 public String toStringNumero() {
		 return String.valueOf(this.getNumero());

		}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
