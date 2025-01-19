package clases;

import java.util.ArrayList;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROVEEDOR")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Proveedor extends Usuario{
	
	@ManyToMany(mappedBy = "proveedores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "ordenesDeCompras")
	@XmlElement(name = "OrdenDeCompra")
	private List <OrdenDeCompra> ordenesDeCompras;
	
	@OneToMany(mappedBy = "proveedor", fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "reclamos")
	@XmlElement(name = "Reclamo")
    private List<Reclamo> reclamos;

	private String nomCompania;
	
	private String link;
	
	@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "Productos")
	@XmlElement(name = "Producto")
	private List <Producto> Productos;
	
	public Proveedor() {}
	
	public Proveedor(String nickName, String nombre, String apellido, String email, DTFecha fecha, String foto, String nomCompania, String link, String contrasenia){
		super(nickName, nombre, apellido, email, fecha, foto, contrasenia);
		this.link = link;
		this.nomCompania = nomCompania;
		this.Productos  = new ArrayList<>();
		this.ordenesDeCompras = new ArrayList<>();
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
	
	public List<Reclamo> getReclamos() {
		return reclamos;
	}
	
	public void setReclamos(List<Reclamo> reclamos) {
		this.reclamos = reclamos;
	}
	
	public void agregarProducto(Producto producto){
		this.Productos.add(producto);
	}
	
	public void setlink(String link){
		this.link = link;
	}
	
	public void setnomCompania(String nomCompania){
		this.nomCompania = nomCompania;
	}
	
	public void setProductos(List <Producto> Productos){
		this.Productos = Productos; 
	}
	
	public String getlink(){
		return this.link;
	}
	
	public String getnomCompania(){
		return this.nomCompania;
	}

	public List <Producto> getProductos(){
		return this.Productos;
	}
	
	public DTProveedor getDTProveedor(){
		DTProveedor c = new DTProveedor(this.getNickname(), this.getNombre(), this.getApellido(), this.getEmail(), this.getFechaNac(), this.getFoto(), this.getContrasenia(), this.nomCompania, this.link);
		return c;
	}
	
	public String toString() {
		return "Nickname: " + this.getNickname() + " - " + "Nombre completo: " + this.getNombre() + " " + this.getApellido();
	}
	
	@Override
	public List<DTOrdenMinima> getOrdenesMinimas() {
		List<DTOrdenMinima> lista = new ArrayList<>();
		for(OrdenDeCompra orden : this.getOrdenesDeCompras()) {
			lista.add(orden.getOrdenMinima());
		}
		return lista;
	}
	
	public List<DTReclamo> getDTReclamos() {
		List<DTReclamo> lista = new ArrayList<>();
		for(Reclamo reclamo : this.getReclamos()) {
			lista.add(reclamo.getDTReclamo());
		}
		return lista;
	}
	
	public List<DTProducto> getDTProductos() {
		List<DTProducto> lista = new ArrayList<>();
		for(Producto prod : this.getProductos()) {
			lista.add(prod.getDTProducto());
		}
		return lista;
	}
}
