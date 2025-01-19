package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOrdenMinima {

	@XmlElement
	private int numero;
	
	@XmlElement
	private DTFecha fecha;
	
	public DTOrdenMinima() {}
	
	public DTOrdenMinima(int numero, DTFecha fecha) {
		this.numero = numero;
		this.fecha = fecha;
	}

	public int getNumero() {
		return numero;
	}

	public DTFecha getFecha() {
		return fecha;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	
}
