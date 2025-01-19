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
public class Categoria {
	
	@Id
    private String nombreCat;
	
	@ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "productos")
	@XmlElement(name = "Producto")
    private List<Producto> productos;
	
	@ManyToOne
	@JoinColumn(name = "nombreCat_padre")
	@XmlTransient
    private Categoria padre;
    
	@OneToMany(mappedBy = "padre", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@XmlElementWrapper(name = "hijos")
	@XmlElement(name = "Categoria")
    private List<Categoria> hijos;
    
    private boolean tieneProductos;
    
    
	public List<Categoria> getHijos() {
		return hijos;
	}
    
    public boolean tieneSubcategorias() {
        return !hijos.isEmpty(); // Devuelve true si tiene subcategorías (si hijos no está vacío)
    }
    
    public Categoria() {}
    
    // Constructor
    public Categoria(String nombreCat, boolean tieneProductos, Categoria padre) {
        this.nombreCat = nombreCat;
        this.productos = new ArrayList<>();
        this.tieneProductos = tieneProductos;
        this.padre = padre;
        this.hijos = new ArrayList<>();
    }

    // Getter para nombreCat
    public String getNombreCat() {
        return nombreCat;
    }
    
    public void setNombreCat(String nombreCat) {
		this.nombreCat = nombreCat;
	}

    public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Categoria getPadre() {
		return padre;
	}

	public void setPadre(Categoria padre) {
		this.padre = padre;
	}

	public void setHijos(List<Categoria> hijos) {
		this.hijos = hijos;
	}

	public boolean isTieneProductos() {
		return tieneProductos;
	}

	public void setTieneProductos(boolean tieneProductos) {
		this.tieneProductos = tieneProductos;
	}

	// Método para obtener los datos básicos de la categoría
    public DTCategoria getDTCategoria() {
    	List<DTCategoria> lista = new ArrayList<>();
    	for (Categoria cat : this.hijos) {
    		DTCategoria dt = cat.getDTCategoria();
    		lista.add(dt);
    	}
        return new DTCategoria(nombreCat, lista, this.tieneProductos);
    }

    // Método para quitar un producto de la categoría
    public boolean quitarProducto(Producto p) {
        return productos.remove(p);
    }

    // Método para agregar un producto a la categoría
    public void agregarProducto(Producto p) {
        productos.add(p);
    }
    
    // Agregar un hijo
    public void agregarHijo(Categoria categoriaHija) {
    	for (Categoria cat : this.hijos) {
    		if (cat.getNombreCat().equals(categoriaHija.getNombreCat())) {
    			return;
    		}
    	}
        hijos.add(categoriaHija);
    }

    // Eliminar un hijo
    public void eliminarHijo(String clave) {
    	int index = 0;
    	for (Categoria cat : this.hijos) {
    		if (cat.getNombreCat().equals(clave)) {
    			this.hijos.remove(index);
    		}
    		index++;
    	}
    }
    
    // Método para listar los productos de la categoría
    public List<DTProducto> listarProductos() {
        List<DTProducto> listaProductos = new ArrayList<>();
        for (Producto producto : productos) {
            listaProductos.add(producto.getDTProducto());
        }
        return listaProductos;
    }

    // Método para listar los hijos de una categoría
    public List<Categoria> listarHijos() {
        return this.hijos;
    }
    
    // Método para seleccionar un producto por su nombre
    public Producto seleccionarProducto(String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombreProducto().equalsIgnoreCase(nombreProducto)) { // equalsIgnoreCase compara dos strings ignorando la diferencia entre mayúsculas y minúsculas
                return producto;
            }
        }
        return null; // Devuelve null si no se encuentra el producto
    }


	@Override
	public String toString() {
	    return nombreCat; 
	}
	

}