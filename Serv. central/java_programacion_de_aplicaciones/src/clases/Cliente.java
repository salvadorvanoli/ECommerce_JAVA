package clases;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CLIENTE")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente extends Usuario{
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "ordenesDeCompras")
	@XmlElement(name = "OrdenDeCompra")
	private List <OrdenDeCompra> ordenesDeCompras;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@XmlTransient
	private List <Comentario> Comentarios;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "carrito")
	@XmlElement(name = "Cantidad")
	private List <Cantidad> carrito;
	
	private boolean notificaciones;
	
	public Cliente() {}
	
	public Cliente(String nickName, String nombre, String apellido, String email, DTFecha fecha, String foto, String contrasenia){
		super(nickName, nombre, apellido, email, fecha, foto, contrasenia);
		this.Comentarios = new ArrayList<>();
		this.carrito = new ArrayList<>();
		this.ordenesDeCompras = new ArrayList<>();
		this.notificaciones = true;
	}
	
	public DTCliente getDTCliente() {
		DTCliente c = new DTCliente(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getFechaNac(), this.getFoto(), this.getContrasenia(), this.notificaciones);
		return c;
	}
	
	@Override
	public List <OrdenDeCompra> getOrdenesDeCompras(){
		return this.ordenesDeCompras;
	}
	
	@Override
	public void setOrdenesDeCompras(List <OrdenDeCompra> OrdenesDeCompras){
		this.ordenesDeCompras = OrdenesDeCompras;
	}
	
	@Override
	public void desvincularOrdenDeCompra(OrdenDeCompra ord){
		int index = 0;
		for (OrdenDeCompra o : this.ordenesDeCompras) {
			if (o.getNumero() == ord.getNumero()) {
				this.ordenesDeCompras.remove(index);
				return;
			}
			index++;
		}
	}
	
	@Override
	public void vincularOrdenDeCompra(OrdenDeCompra ord) {
		for (OrdenDeCompra o : this.ordenesDeCompras) {
			if (o.getNumero() == ord.getNumero()) {
				return;
			}
		}
		this.ordenesDeCompras.add(ord);
	}
	
	public void setComentarios(List <Comentario> Comentarios){
		this.Comentarios = Comentarios;
	}
	
	public List <Comentario> getComentarios(){
		return this.Comentarios;
	}
	
	public boolean getNotificaciones() {
		return this.notificaciones;
	}
	
	public void setNotificaciones(boolean notificacion) {
		this.notificaciones = notificacion;
	}
	
	public List<Cantidad> getCarrito(){
		return this.carrito;
	}
	
	public void agregarProducto(Cantidad prod) {
		for (Cantidad cant : this.carrito) {
			if (cant.getProducto().getNumReferencia() == prod.getProducto().getNumReferencia()) {
				return;
			}
		}
		prod.setCliente(this);
		this.carrito.add(prod);
	}
	
	public void quitarProducto(int numProd) {
		int index = 0;
		for (Cantidad cant : this.carrito) {
			if (cant.getProducto().getNumReferencia() == numProd) {
				cant.setCliente(null);
				this.carrito.remove(index);
				return;
			}
			index++;
		}
	}
	
	public void setCarrito(List<Cantidad> carrito) {
		this.carrito = carrito;
	}
	
	public void modificarCantidadItemCarrito(int numReferencia, int cantidad) {
		int index = 0;
		for (Cantidad cant : this.carrito) {
			if (cant.getProducto().getNumReferencia() == numReferencia) {
				cant.setCantidad(cantidad);
				this.carrito.set(index, cant);
			}
			index++;
		}
	}
	
	public List<DTCantidad> getDTCarrito() {
		List<DTCantidad> carrito = new ArrayList<>();
		for (Cantidad cant : this.carrito) {
			DTCantidad dt = cant.getDTCantidad();
			carrito.add(dt);
		}
		return carrito;
	}
	
	public Boolean comproProducto(int numReferencia) {
		for(OrdenDeCompra orden : this.getOrdenesDeCompras()) {
			if(orden.tieneProducto(numReferencia)) {
				return true;
			}
		}
		return false;
	}
	
	public void vaciarCarrito() {
		for (Cantidad cant : this.carrito) {
	        cant.setCliente(null); // Desasocia explícitamente cada Cantidad
	    }
	    this.carrito.clear();
	}
	
	public void realizarCompra(OrdenDeCompra ord) {
		ord.setCliente(this);
		List<Proveedor> proveedores = new ArrayList<>();
		for (Cantidad item : this.carrito) {
			if (!proveedores.contains(item.getProducto().getProveedor())) {
				proveedores.add(item.getProducto().getProveedor());
			}
		}
		ord.setProveedores(proveedores);
		for (Proveedor prov : ord.getProveedores()) {
			prov.getOrdenesDeCompras().add(ord);
		}
		if (!ord.esValida()) {
			throw new IllegalArgumentException("La orden de compra es inválida");
		}
		for (Cantidad item : this.carrito) {
			Producto prod = item.getProducto();
			prod.setCantCompras(prod.getCantCompras() + item.getCantidad());
			item.setCliente(null);
		}
		this.carrito.clear();
		this.vincularOrdenDeCompra(ord);
	}
	
	@Override
	public List<DTOrdenMinima> getOrdenesMinimas() {
		List<DTOrdenMinima> lista = new ArrayList<>();
		for(OrdenDeCompra orden : this.getOrdenesDeCompras()) {
			lista.add(orden.getOrdenMinima());
		}
		return lista;
	}
	
	
}
