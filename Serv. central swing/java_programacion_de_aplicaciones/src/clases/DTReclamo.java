package clases;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTReclamo {
	
	@XmlElement
	private int id;

	@XmlElement
	private String mensaje;

	@XmlElement
    private int numReferencia;

	@XmlElement
    private int dia;
	
	@XmlElement
    private int mes;
	
	@XmlElement
    private int anio;
    
	@XmlElement
    private String nicknameCliente;
    
	@XmlElement
    private String nombreCliente;
    
	@XmlElement
    private String apellidoCliente;

	@XmlElement
    private String nicknameProveedor;
	

	@XmlElement
    private String nombreProd;
	

	@XmlElement
    private String descripProd;
	

	@XmlElement
    private float precioProd;
	
	@XmlElement
    private List<String> imagenProd;
	

	
	public DTReclamo() {}
	
	

	public DTReclamo(int id, String mensaje, int numReferencia, int dia, int mes, int anio, String nicknameCliente,
			String nombreCliente, String apellidoCliente, String nicknameProveedor, String nombreProd,
			String descripProd, float precioProd, List<String> imagenProd) {
		super();
		this.id = id;
		this.mensaje = mensaje;
		this.numReferencia = numReferencia;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.nicknameCliente = nicknameCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.nicknameProveedor = nicknameProveedor;
		this.nombreProd = nombreProd;
		this.descripProd = descripProd;
		this.precioProd = precioProd;
		this.imagenProd = imagenProd;

	}



	public int getDia() {
		return dia;
	}



	public void setDia(int dia) {
		this.dia = dia;
	}



	public int getMes() {
		return mes;
	}



	public void setMes(int mes) {
		this.mes = mes;
	}



	public int getAnio() {
		return anio;
	}



	public void setAnio(int anio) {
		this.anio = anio;
	}



	public String getNombreProd() {
		return nombreProd;
	}



	public void setNombreProd(String nombreProd) {
		this.nombreProd = nombreProd;
	}



	public String getDescripProd() {
		return descripProd;
	}



	public void setDescripProd(String descripProd) {
		this.descripProd = descripProd;
	}



	public float getPrecioProd() {
		return precioProd;
	}



	public void setPrecioProd(float precioProd) {
		this.precioProd = precioProd;
	}


	public List<String> getImagenProd() {
		return imagenProd;
	}



	public void setImagenProd(List<String> imagenProd) {
		this.imagenProd = imagenProd;
	}



	public int getId() {
		return id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getNumReferencia() {
		return numReferencia;
	}

	

	public String getNicknameCliente() {
		return nicknameCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public String getNicknameProveedor() {
		return nicknameProveedor;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setNumReferencia(int numReferencia) {
		this.numReferencia = numReferencia;
	}

	

	public void setNicknameCliente(String nicknameCliente) {
		this.nicknameCliente = nicknameCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public void setNicknameProveedor(String nicknameProveedor) {
		this.nicknameProveedor = nicknameProveedor;
	}
}
