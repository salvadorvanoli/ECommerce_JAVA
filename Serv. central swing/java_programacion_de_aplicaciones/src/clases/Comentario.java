package clases;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Comentario {

    @Id
    private int id;

    @Column(name = "contenido", nullable = false, length = 1000)
    private String contenido;
    
    @XmlElement
	private String nickRespuesta;
	
	@XmlElement
	private int idRespuesta;

    @ManyToOne
    @JoinColumn(name = "nickname", nullable = false)
    @XmlElement
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "numReferencia", nullable = false)
    @XmlTransient
    private Producto producto;

    @Embedded
    @XmlElement
    private DTFecha fecha;

    private int estrellas;
    
    public Comentario() {}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Comentario(int id, String contenido) {
		super();
		this.id = id;
		this.contenido = contenido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Comentario(int id, String contenido, Cliente cliente,
			Producto producto, DTFecha fecha, int estrellas, String nickRespuesta, int idRespuesta) {
		super();
		this.id = id;
		this.contenido = contenido;
		this.nickRespuesta = nickRespuesta;
		this.idRespuesta = idRespuesta;
		this.cliente = cliente;
		this.producto = producto;
		this.setFecha(fecha);
		this.setEstrellas(estrellas);
	}
	
	public DTFecha getFecha() {
		return fecha;
	}
	
	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	
	public int getEstrellas() {
		return estrellas;
	}
	
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	
	public DTComentario obtenerDTComentario() {

	    return new DTComentario(
	        this.id,
	        this.contenido,
	        this.cliente.getDTCliente(),
	        this.producto,
	        this.fecha,
	        this.estrellas,
	        this.nickRespuesta,
	        this.idRespuesta
	    );
	}

	public String getNickRespuesta() {
		return nickRespuesta;
	}

	public void setNickRespuesta(String nickRespuesta) {
		this.nickRespuesta = nickRespuesta;
	}

	public int getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
}
