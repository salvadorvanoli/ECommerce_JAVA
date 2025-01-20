
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para producto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="producto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantCompras" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="categorias" type="{http://services/}categoria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comentarios" type="{http://services/}comentario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="especificacion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="estrellas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imagenes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombreProducto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreTienda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numReferencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="proveedor" type="{http://services/}proveedor" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "producto", propOrder = {
    "cantCompras",
    "categorias",
    "comentarios",
    "descripcion",
    "especificacion",
    "estrellas",
    "imagenes",
    "nombreProducto",
    "nombreTienda",
    "numReferencia",
    "precio",
    "proveedor"
})
public class Producto {

    protected int cantCompras;
    @XmlElement(nillable = true)
    protected List<Categoria> categorias;
    @XmlElement(nillable = true)
    protected List<Comentario> comentarios;
    protected String descripcion;
    @XmlElement(nillable = true)
    protected List<String> especificacion;
    protected int estrellas;
    @XmlElement(nillable = true)
    protected List<String> imagenes;
    protected String nombreProducto;
    protected String nombreTienda;
    protected int numReferencia;
    protected float precio;
    protected Proveedor proveedor;

    /**
     * Obtiene el valor de la propiedad cantCompras.
     * 
     */
    public int getCantCompras() {
        return cantCompras;
    }

    /**
     * Define el valor de la propiedad cantCompras.
     * 
     */
    public void setCantCompras(int value) {
        this.cantCompras = value;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Categoria }
     * 
     * 
     */
    public List<Categoria> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<Categoria>();
        }
        return this.categorias;
    }

    /**
     * Gets the value of the comentarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comentario }
     * 
     * 
     */
    public List<Comentario> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<Comentario>();
        }
        return this.comentarios;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the especificacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the especificacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEspecificacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEspecificacion() {
        if (especificacion == null) {
            especificacion = new ArrayList<String>();
        }
        return this.especificacion;
    }

    /**
     * Obtiene el valor de la propiedad estrellas.
     * 
     */
    public int getEstrellas() {
        return estrellas;
    }

    /**
     * Define el valor de la propiedad estrellas.
     * 
     */
    public void setEstrellas(int value) {
        this.estrellas = value;
    }

    /**
     * Gets the value of the imagenes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imagenes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImagenes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getImagenes() {
        if (imagenes == null) {
            imagenes = new ArrayList<String>();
        }
        return this.imagenes;
    }

    /**
     * Obtiene el valor de la propiedad nombreProducto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Define el valor de la propiedad nombreProducto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProducto(String value) {
        this.nombreProducto = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreTienda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTienda() {
        return nombreTienda;
    }

    /**
     * Define el valor de la propiedad nombreTienda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTienda(String value) {
        this.nombreTienda = value;
    }

    /**
     * Obtiene el valor de la propiedad numReferencia.
     * 
     */
    public int getNumReferencia() {
        return numReferencia;
    }

    /**
     * Define el valor de la propiedad numReferencia.
     * 
     */
    public void setNumReferencia(int value) {
        this.numReferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad precio.
     * 
     */
    public float getPrecio() {
        return precio;
    }

    /**
     * Define el valor de la propiedad precio.
     * 
     */
    public void setPrecio(float value) {
        this.precio = value;
    }

    /**
     * Obtiene el valor de la propiedad proveedor.
     * 
     * @return
     *     possible object is
     *     {@link Proveedor }
     *     
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * Define el valor de la propiedad proveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link Proveedor }
     *     
     */
    public void setProveedor(Proveedor value) {
        this.proveedor = value;
    }

}
