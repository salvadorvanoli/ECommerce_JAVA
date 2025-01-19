package clases;

import java.util.List;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTProducto {
	
	@XmlElement
	private String nombre;
	
	@XmlElement
	private String descripcion;
	
	@XmlElement
	private float precio;
	
	@XmlElement
	private int numReferencia;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "imagenes")
	@XmlElement(name = "String")
	private List<String> imagenes;

	public DTProducto() {}
	
	public DTProducto(String nombre, int num, String descripcion, float precio, List<String> imagenes) {
		super();
		this.nombre = nombre;
		this.numReferencia = num;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagenes = imagenes;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}

	public void setDescripcion(String descricpion) {
		this.descripcion = descricpion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public int getNumReferencia() {
		return numReferencia;
	}
	
	public void setNumReferencia(int numReferencia) {
		this.numReferencia = numReferencia;
	}

	public List<String> getImagenes() {
		return this.imagenes;
	}
	
	public String toString() {
		return this.nombre + " - " + this.descripcion + " - " + String.valueOf(this.precio);
	}
	
}
