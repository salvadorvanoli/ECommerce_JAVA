package clases;

import java.util.ArrayList;
import java.util.List;
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
public class Producto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int numReferencia;
	
    private String nombreProducto;
    
    private String descripcion;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "especificacion")
    @XmlElement(name = "String")
    private List<String> especificacion;
    
    private float precio;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "imagenes")
    @XmlElement(name = "String")
    private List<String> imagenes;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    	name = "producto_categoria",
    	joinColumns = @JoinColumn(name = "numReferencia"),
    	inverseJoinColumns = @JoinColumn(name = "nombreCat")
    )
    @XmlTransient
    private List<Categoria> categorias;
    
    @ManyToOne
    @JoinColumn(name = "nickname")
    @XmlTransient
    private Proveedor proveedor;
    
	private int estrellas;
	
	private int cantCompras;
	
    private String nombreTienda;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @XmlElementWrapper(name = "comentarios")
    @XmlElement(name = "Comentario")
    private List<Comentario> comentarios;

    public Producto() {}
    
    // Constructor
    public Producto(String nombreProducto, String descripcion, List<String> especificacion, int numReferencia, float precio, List<String> imagenes, List<Categoria> categorias, Proveedor proveedor, String nombreTienda) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.especificacion = especificacion;
        this.numReferencia = numReferencia;
        this.precio = precio;
        this.estrellas = 0;
        this.nombreTienda = nombreTienda;
        this.cantCompras = 0;
        
        
        this.comentarios = new ArrayList<>();
        
        this.imagenes = imagenes;
        this.categorias = categorias;
        this.proveedor = proveedor;
        
    }
    
    public void actualizarCantEstrellas() {
        int estrellasTotales = 0;
        int cantComentarios = 0;

        for (Comentario comentario : this.comentarios) {
            estrellasTotales += comentario.getEstrellas();
            if(comentario.getEstrellas() != 0) {
            	cantComentarios++;
            }
        }

        if (cantComentarios > 0) {
            this.estrellas = (int) Math.round((double) estrellasTotales / cantComentarios);
        } else {
            this.estrellas = 0;
        }
    }
    
    public int getId() {
    	return this.id;
    }
    
    public List<Comentario> getComentarios() {
    	return this.comentarios;
    }
    
    public void setComentarios(List<Comentario> comentarios) {
    	this.comentarios = comentarios;
    }
    
    public void agregarComentario(Comentario nuevo) {
    	this.comentarios.add(nuevo);
    }
    
    public int getEstrellas() {
    	return this.estrellas;
    }
    
    public void setEstrellas(int estrellas) {
    	this.estrellas = estrellas;
    }

    // Setters
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEspecificacion(List<String> especificacion) {
        this.especificacion = especificacion;
    }

    public void setNumReferencia(int numReferencia) {
        this.numReferencia = numReferencia;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public void setNombreTienda(String nombreTienda) {
    	this.nombreTienda = nombreTienda;
    }
    
    public void setCantCompras(int cantCompras) {
    	this.cantCompras = cantCompras;
    }

    // Getters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getEspecificacion() {
        return especificacion;
    }

    public int getNumReferencia() {
        return numReferencia;
    }

    public float getPrecio() {
        return precio;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    // Método para obtener los datos básicos del producto
    public DTProducto getDTProducto() {
    	List<String> imagenesDT = new ArrayList<>();
    	for (String imagen : this.imagenes) {
    		imagenesDT.add(imagen);
    	}
        return new DTProducto(nombreProducto, numReferencia, descripcion, precio, imagenesDT);
    }
    
    // Función para buscar un comentario por ID y agregar respuesta en el comentario correcto.
    public Comentario getComentario(int id) {
        for (Comentario comentario : comentarios) {
            if (comentario.getId() == id) {
                return comentario;
            }
        }
        return null;
    }
    
    public String getNombreTienda() {
    	return this.nombreTienda;
    }
    
    public int getCantCompras() {
    	return this.cantCompras;
    }

    // Método para obtener los datos detallados del producto
    public DTProductoDetallado getDTProductoDetallado() {
        // Convertir las categorías de Categoria a String
        List<String> nombresCategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            nombresCategorias.add(categoria.getNombreCat());
        }
        
        List<String> nombresEspecificaciones = new ArrayList<>();
        for(String esp : especificacion) {
        	nombresEspecificaciones.add(esp);
        }
        
        List<String> imagenesDT = new ArrayList<>();
    	for (String imagen : this.imagenes) {
    		imagenesDT.add(imagen);
    	}

        // Crear el objeto DTProveedor a partir del Proveedor
        DTProveedor dtProveedor = proveedor.getDTProveedor();

        // Crear y retornar el objeto DTProductoDetallado
        return new DTProductoDetallado(
            nombreProducto,
            descripcion,
            precio,
            numReferencia,
            nombresEspecificaciones,
            nombresCategorias,
            dtProveedor,
            imagenesDT,
            nombreTienda,
            estrellas,
            cantCompras
        );
    }

    // Método para modificar los datos del producto
    public void modificarDatosProducto(String tituloProducto, int numReferencia, String descripcion, float precio, List<String> especificacion) {
        this.nombreProducto = tituloProducto;
        this.numReferencia = numReferencia;
        this.descripcion = descripcion;
        this.precio = precio;
        this.especificacion = especificacion;
    }
    
    @Override
    public String toString() {
        return nombreProducto;  
    }
    
    public List<String> getCategoriasString() {
    	List<String> categorias = new ArrayList<>();
    	for(Categoria cat : this.getCategorias()) {
    		categorias.add(cat.getNombreCat());
    	}
    	return categorias;
    }
    
    public List<DTComentario> getDTComentarios() {
    	List<DTComentario> lista = new ArrayList<>();
    	for(Comentario com : this.getComentarios()) {
    		lista.add(com.obtenerDTComentario());
    	}
    	return lista;
    }

}