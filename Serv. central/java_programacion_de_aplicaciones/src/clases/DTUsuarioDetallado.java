package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({ DTCliente.class, DTProveedor.class })
public class DTUsuarioDetallado extends DTUsuario{
	
	private String nombre;
    
    private String apellido;
    
    @XmlElement
    private DTFecha fechaNac;
    
    private String foto;
    
    public DTUsuarioDetallado() {}
    
    public DTUsuarioDetallado(String nickname, String email) {
		super(nickname, email);
	}

    public DTUsuarioDetallado(String nickname, String nombre, String apellido, String email, DTFecha fechaNac, String foto, String contrasenia){
    	super(nickname, email, contrasenia);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.foto = foto;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public DTFecha getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(DTFecha fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}