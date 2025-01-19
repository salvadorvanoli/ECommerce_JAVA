package clases;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdenDeCompra {
	
	@Id
	private int numero;
	
	private float precioTotal;
	
	@Embedded
	@XmlElement
	private DTFecha fecha;
	
	@ManyToOne
	@JoinColumn(name = "nickname", nullable = false)
	@XmlTransient
	private Cliente cliente;
	
	@ManyToMany
    @JoinTable(
        name = "ordendecompra_proveedor",
        joinColumns = @JoinColumn(name = "numero"),
        inverseJoinColumns = @JoinColumn(name = "nickname")
    )
	@XmlTransient
	private List<Proveedor> proveedores;
	
	@OneToMany(mappedBy = "ordenPadre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "cantidad")
    @XmlElement(name = "DTCantidad")
	private List<DTCantidad> cantidad;

	@Embedded
	@XmlElement
	private FormaPago formaPago;
	
	@Embedded
	@XmlElement
	private DetallesEnvio detallesEnvio;
	
	@Enumerated(EnumType.STRING)
	@XmlElement
	private Estado estado;
	
	@OneToMany(mappedBy = "ordenPadre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "cambios")
    @XmlElement(name = "CambioEstado")
	private List<CambioEstado> cambios;
	
	public void agregarCambioEstado(Estado estado, DTFecha fecha) {
		CambioEstado nuevo = new CambioEstado(estado, fecha, this);
		this.cambios.add(nuevo);
	}
	
	public List<CambioEstado> getCambios() {
		return this.cambios;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	public DTFecha getFecha() {
		return fecha;
	}
	public void setFecha(DTFecha fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<DTCantidad> getCantidad() {
		return cantidad;
	}
	public void setCantidad(List<DTCantidad> cantidad) {
		this.cantidad = cantidad;
	}
	
	public List<Proveedor> getProveedores() {
		return proveedores;
	}
	
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	
	public FormaPago getFormaPago() {
		return this.formaPago;
	}
	
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
	
	public DetallesEnvio getDetallesEnvio() {
		return this.detallesEnvio;
	}
	
	public void setDetallesEnvio(DetallesEnvio detallesEnvio) {
		this.detallesEnvio = detallesEnvio;
	}
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void desvincularCliente () {
		this.cliente.desvincularOrdenDeCompra(this);
	}
	
	public void agregarProducto(DTProducto producto, int cantidad) {
		DTCantidad nueva = new DTCantidad(cantidad, producto, this);
		List<DTCantidad> lista = this.getCantidad();
		lista.add(nueva);
		this.setCantidad(lista);
	}
	
	public OrdenDeCompra() {}

	public OrdenDeCompra(int numero, DTFecha fecha, Cliente cliente, List<DTCantidad> cantidades) {
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.estado = Estado.comprada;
		this.cambios = new ArrayList<>();
		this.agregarCambioEstado(Estado.comprada, fecha);
		if (cantidades != null && !(cantidades.isEmpty())) {
			this.cantidad = cantidades;
			float sumPrecio = 0;
			for (DTCantidad can : cantidades) {
				float subtotal = can.getCantidad() * can.getProducto().getPrecio();
				sumPrecio += subtotal;
			}
			this.precioTotal = sumPrecio;
		} else {
			this.cantidad = new ArrayList<>();
			this.precioTotal = 0;
		}
	}
	
	public OrdenDeCompra(int numero, DTFecha fecha, Cliente cliente, List<Proveedor> proveedores, List<DTCantidad> cantidades, FormaPago formaPago, DetallesEnvio detallesEnvio) {
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.proveedores = proveedores;
		this.formaPago = formaPago;
		this.detallesEnvio = detallesEnvio;
		this.estado = Estado.comprada;
		this.cambios = new ArrayList<>();
		this.agregarCambioEstado(Estado.comprada, fecha);
		if (cantidades != null && !(cantidades.isEmpty())) {
			this.cantidad = cantidades;
			float sumPrecio = 0;
			if (detallesEnvio.getTipoEnvio().equals("vip")) {
				sumPrecio = 20;
			}
			for (DTCantidad can : cantidades) {
				float subtotal = can.getCantidad() * can.getProducto().getPrecio();
				sumPrecio += subtotal;
				can.setOrdenPadre(this);
			}
			this.precioTotal = sumPrecio;
		} else {
			this.cantidad = new ArrayList<>();
			this.precioTotal = 0;
		}
	}
	
	public String toString(){
		String fechaFormateada = String.format("%02d/%02d/%04d", this.fecha.getDia(), this.fecha.getMes(), this.fecha.getAnio());
	    String retorno = "Orden de Compra " + this.numero + System.lineSeparator()
	                + "Fecha: " + fechaFormateada + System.lineSeparator()
	                + "Precio total: " + this.getPrecioTotal() + System.lineSeparator()
	                + "Nombre cliente: " + this.cliente.getNickname() + System.lineSeparator()
	                + "Estado: " + this.estado.name() + System.lineSeparator()
	                + System.lineSeparator() +  "-------------------- PRODUCTOS --------------------" + System.lineSeparator() + System.lineSeparator();
	    Integer i = 1;
	    for (DTCantidad prod : this.cantidad){
	        retorno += "Producto número " + i.toString() + ": "  + System.lineSeparator() + prod.toString() + System.lineSeparator();
	        i++;
	    }
	    i = 1;
	    retorno += System.lineSeparator() +  "-------------------- PROVEEDORES --------------------" + System.lineSeparator() + System.lineSeparator();
	    for (Proveedor prov : this.proveedores){
	        retorno += "Proveedor número " + i.toString() + ": "  + System.lineSeparator() + prov.toString() + System.lineSeparator();
	        i++;
	    }
	    return retorno;
	}
	
	
	public DTOrdenDeCompra getDTOrden() {
		return new DTOrdenDeCompra(this.numero, this.cliente, this.precioTotal, this.fecha, this.cantidad, this.estado.toString());
	}
	
	public DTOrdenDeCompraDetallada getDTOrdenDetallada() {
		List<DTCantidadProducto> lista = new ArrayList<>();
		for(DTCantidad cant : this.cantidad) {
			// DTCantidadProducto nuevo = cant.getDTCantidadProducto();
			DTCantidadProducto cantProd = new DTCantidadProducto(cant.getCantidad(), cant.getProducto(), cant.getProducto().getPrecio() * cant.getCantidad());
			lista.add(cantProd);
		}
		return new DTOrdenDeCompraDetallada(this.numero, this.cliente, this.precioTotal, this.fecha, this.cantidad, lista, this.estado.toString());
	}
	
	public Boolean tieneProducto(int numReferencia) {
		for(DTCantidad cantprod : this.getCantidad()) {
			if(cantprod.getProducto().getNumReferencia() == numReferencia) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean esValida() {
		Boolean id = Pattern.compile("[0-9]+").matcher(Integer.toString(this.numero)).matches();
		Boolean precioTotal = Pattern.compile("[0-9]+(\\.[0-9]+)?").matcher(Float.toString(this.precioTotal)).matches();
		Boolean fecha = (this.fecha != null);
		Boolean cliente = (this.cliente != null);
		Boolean proveedores = (this.proveedores != null && this.proveedores.size() > 0);
		Boolean cantidad = (this.cantidad != null && this.cantidad.size() > 0);
		Boolean detallesEnvio = (this.detallesEnvio != null && this.detallesEnvio.esValida());
		Boolean formaPago = (this.formaPago != null && this.formaPago.esValida());
		Boolean estado = (this.estado == Estado.comprada || this.estado == Estado.enCamino || this.estado == Estado.enPreparacion || this.estado == Estado.entregada);
		
		return id && precioTotal && fecha && cliente && proveedores && cantidad && detallesEnvio && formaPago && estado;
		
	}
	
	public void setPadreDeCantidades() {
		for (DTCantidad cant : this.cantidad) {
			cant.setOrdenPadre(this);
		}
	}
	
	public DTOrdenMinima getOrdenMinima() {
		return new DTOrdenMinima(this.getNumero(), this.getFecha());
	}
	
	public DTOrdenDeCompra getDTCantidadOrden() {
		return new DTOrdenDeCompra(this.getNumero(), this.getCliente(), this.getPrecioTotal(), this.getFecha(), this.getCantidad(), this.getEstado().toString());
	}
	
	public List<DTCambioEstado> getDTCambioEstado() {
		List<DTCambioEstado> lista = new ArrayList<>();
		
		for(CambioEstado ord : this.getCambios()) {
			lista.add(ord.getDTCambioEstado());
		}
		
		return lista;
	}
}