package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTProveedor extends DTUsuarioDetallado {
	
	private String nomCompania;
	
	private String link;
	
	public DTProveedor() {}
	
	public DTProveedor(String nickname, String email) {
		super(nickname, email);
	}
	
	public DTProveedor(String nickname, String nombre, String apellido, String email, DTFecha fechaNac, String foto, String contrasenia, String nomCompania, String link) {
		super(nickname, nombre, apellido, email, fechaNac, foto, contrasenia);
		this.nomCompania = nomCompania;
		this.link = link;
	}

	public String getNomCompania() {
		return nomCompania;
	}

	public void setNomCompania(String nomCompania) {
		this.nomCompania = nomCompania;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
