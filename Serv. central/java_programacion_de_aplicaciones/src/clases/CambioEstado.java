package clases;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CambioEstado {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Enumerated(EnumType.STRING)
    @XmlSchemaType(name = "string")
	@XmlElement
	private Estado estado;
	
	@Embedded
	@XmlElement
	private DTFecha fecha;
	
	@ManyToOne
	@JoinColumn(name = "numero", nullable = false)
	@XmlTransient
	private OrdenDeCompra ordenPadre;
	
	public CambioEstado(Estado estado, DTFecha fecha, OrdenDeCompra padre) {
		this.estado = estado;
		this.fecha = fecha;
		this.ordenPadre = padre;
	}
	
	public CambioEstado() {}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public DTFecha getFecha() {
		return fecha;
	}
	
	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	
	public OrdenDeCompra getOrdenPadre() {
		return ordenPadre;
	}
	
	public void setOrdenPadre(OrdenDeCompra orden) {
		this.ordenPadre = orden;
	}
	
	public DTCambioEstado getDTCambioEstado() {
		return new DTCambioEstado(this.estado.toString(), this.getFecha());
	}
}