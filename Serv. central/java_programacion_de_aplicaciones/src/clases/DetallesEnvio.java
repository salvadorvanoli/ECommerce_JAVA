package clases;

import java.util.regex.Pattern;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DetallesEnvio {

	private String nombre;
	private String apellido;
	private String direccion1;
	private String direccion2;
	private String departamento;
	private String ciudad;
	private String codPostal;
	private String numTelefono;
	private String tipoEnvio;
	private String precioEnvio;
	
	public DetallesEnvio() {}
	
	public DetallesEnvio(String nombre, String apellido, String direccion1, String direccion2, String departamento, String ciudad, String codPostal, String numTelefono, String tipoEnvio) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.departamento = departamento;
		this.ciudad = ciudad;
		this.codPostal = codPostal;
		this.numTelefono = numTelefono;
		this.tipoEnvio = tipoEnvio;
		if(tipoEnvio.equals("free")) {
				this.precioEnvio = "0";	
				}
		else {
			this.precioEnvio = "20";
		}
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
	
	public String getDireccion1() {
		return direccion1;
	}
	
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}
	
	public String getDireccion2() {
		return direccion2;
	}
	
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getCodPostal() {
		return codPostal;
	}
	
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	
	public String getNumTelefono() {
		return numTelefono;
	}
	
	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}
	
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	
	public String getPrecioEnvio() {
		return precioEnvio;
	}
	
	public void setPrecioEnvio(String precioEnvio) {
		this.precioEnvio = precioEnvio;
	}
	
	public Boolean esValida() {
		Boolean nombre = Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(this.nombre).matches();
		Boolean apellido = Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(this.apellido).matches();
		Boolean direccion1 = Pattern.compile("[A-Za-z0-9]+(( ?([A-Za-z0-9]+))?)+ [0-9]+").matcher(this.direccion1).matches();
		Boolean departamento = (this.departamento != "");
		Boolean ciudad = (this.ciudad != "");
		Boolean codPostal = Pattern.compile("[0-9]{5}").matcher(this.codPostal).matches();
		Boolean numTelefono = Pattern.compile("09[1-9][0-9]{6}").matcher(this.numTelefono).matches();
		Boolean tipoEnvio = Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(this.tipoEnvio).matches();
		Boolean precioEnvio = Pattern.compile("[0-9]+(\\.[0-9]+)?").matcher(this.precioEnvio).matches();
		
		return nombre && apellido && direccion1 && departamento && ciudad && codPostal && numTelefono && tipoEnvio && precioEnvio;
		
	}
	
}
