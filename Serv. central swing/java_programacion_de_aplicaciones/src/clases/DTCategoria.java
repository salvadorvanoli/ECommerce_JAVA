package clases;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCategoria {
	
	private String nombreCat;
	
	@XmlElementWrapper(name = "hijas")
	@XmlElement(name = "DTCategoria")
	private List<DTCategoria> hijas;
	
	private boolean tieneProductos;
	
	public String getNombreCat() {
		return nombreCat;
	}
	public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}
	public List<DTCategoria> getHijas() {
		return hijas;
	}
	public void setHijas(List<DTCategoria> hijas) {
		this.hijas = hijas;
	}
	
	public DTCategoria() {}
	
	public DTCategoria(String nombreCat, List<DTCategoria> hijos, boolean tieneProductos) {
		super();
		this.nombreCat = nombreCat;
		this.hijas = hijos;
		this.tieneProductos = tieneProductos;
	}
	
	public boolean isTieneProductos() {
		return tieneProductos;
	}

	public void setTieneProductos(boolean tieneProductos) {
		this.tieneProductos = tieneProductos;
	}
	
}
