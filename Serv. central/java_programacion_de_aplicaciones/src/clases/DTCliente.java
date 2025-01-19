package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCliente extends DTUsuarioDetallado {
	
	@XmlElement
	private boolean notificaciones;
	
	public DTCliente() {}
	
	public DTCliente(String nickname, String email) {
		super(nickname, email);
	}
	
	public DTCliente(String nickname, String nombre, String apellido, String email, DTFecha fechaNac, String foto, String contrasenia, boolean notificaciones) {
		super(nickname, nombre, apellido, email, fechaNac, foto, contrasenia);
		this.notificaciones = notificaciones;
	}

	public boolean getNotificaciones() {
		return notificaciones;
	}
	
	public void setNotificaciones(boolean notificaciones) {
		this.notificaciones = notificaciones;
	}
}
