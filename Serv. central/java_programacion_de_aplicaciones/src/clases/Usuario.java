package clases;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ Cliente.class, Proveedor.class })
public abstract class Usuario{
	
	@Id
	@Column(unique = true, nullable = false)
    private String nickname;
	
    private String nombre;
    
    private String apellido;
    
    @Column(unique = true, nullable = false)
    private String email;

    @XmlElement
    private DTFecha fechaNac;
    
    private String foto;
    
    private String contrasenia;
    
    public Usuario() {}

    public Usuario(String nickname, String nombre, String apellido, String email, DTFecha fechaNac, String foto, String contrasenia){
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNac = fechaNac;
        this.foto = foto;
        this.contrasenia = contrasenia;
    }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DTFecha getFechaNac() {
		return fechaNac;
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public DTUsuario getDTUsuario() {
		return new DTUsuario(nickname, email, contrasenia);
	}
	
	public DTUsuarioDetallado getDTUsuarioDetallado() {
		return new DTUsuarioDetallado(nickname, nombre, apellido, email, fechaNac, foto, contrasenia);
	}
	
	// Hice que los 4 métodos relacionados con ordenes de compras sean abstractos
	public abstract List <OrdenDeCompra> getOrdenesDeCompras();
	
	public abstract void setOrdenesDeCompras(List <OrdenDeCompra> OrdenesDeCompras);
	
	public abstract void desvincularOrdenDeCompra(OrdenDeCompra ord);

	public abstract void vincularOrdenDeCompra(OrdenDeCompra ord);
	
	public abstract List<DTOrdenMinima> getOrdenesMinimas();
	
	public String toString() {
		return this.nombre + " - " + this.apellido;
	}
	
}