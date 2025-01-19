package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({ DTUsuarioDetallado.class })
public class DTUsuario{
	
    private String nickname;
    
    private String email;
    
    private String contrasenia;
    
    public DTUsuario() {}
    
    public DTUsuario(String nickname, String email){
        this.nickname = nickname;
        this.email = email;
    }

    public DTUsuario(String nickname, String email, String contrasenia){
        this.nickname = nickname;
        this.email = email;
        this.contrasenia = contrasenia;
    }

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	public String toString() {
		return this.nickname + " - " + this.email;
	}
	
}