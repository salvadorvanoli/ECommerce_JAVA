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
public class Reclamo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mensaje;

    @Embedded
    @XmlElement
    private DTProducto producto;

    @Embedded
    @XmlElement
    private DTFecha fecha;
    
    private String nicknameCliente;
    
    private String nombreCliente;
    
    private String apellidoCliente;
    
    @ManyToOne
    @XmlTransient
    private Proveedor proveedor;
    
    public Reclamo() {
    }

	public Reclamo(String mensaje, DTProducto producto, DTFecha fecha, String nicknameCliente,
			String nombreCliente, String apellidoCliente, Proveedor proveedor) {
		super();
		this.mensaje = mensaje;
		this.producto = producto;
		this.fecha = fecha;
		this.nicknameCliente = nicknameCliente;
		this.nombreCliente = nombreCliente;
		this.apellidoCliente = apellidoCliente;
		this.proveedor = proveedor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public DTProducto getProducto() {
		return producto;
	}

	public void setProducto(DTProducto producto) {
		this.producto = producto;
	}

	public String getNicknameCliente() {
		return nicknameCliente;
	}

	public void setNicknameCliente(String nicknameCliente) {
		this.nicknameCliente = nicknameCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public DTFecha getFecha() {
		return fecha;
	}

	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	
	public DTReclamo getDTReclamo() {
		return new DTReclamo(this.id, this.mensaje, producto.getNumReferencia(), this.fecha.getDia(), this.fecha.getMes(), this.fecha.getAnio(), this.nicknameCliente, this.nombreCliente, this.apellidoCliente, proveedor.getNickname(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getImagenes());
	}

}