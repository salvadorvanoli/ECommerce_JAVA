
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para categoria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="categoria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hijos" type="{http://services/}categoria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombreCat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="padre" type="{http://services/}categoria" minOccurs="0"/>
 *         &lt;element name="productos" type="{http://services/}producto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tieneProductos" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoria", propOrder = {
    "hijos",
    "nombreCat",
    "padre",
    "productos",
    "tieneProductos"
})
public class Categoria {

    @XmlElement(nillable = true)
    protected List<Categoria> hijos;
    protected String nombreCat;
    protected Categoria padre;
    @XmlElement(nillable = true)
    protected List<Producto> productos;
    protected boolean tieneProductos;

    /**
     * Gets the value of the hijos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hijos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHijos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Categoria }
     * 
     * 
     */
    public List<Categoria> getHijos() {
        if (hijos == null) {
            hijos = new ArrayList<Categoria>();
        }
        return this.hijos;
    }

    /**
     * Obtiene el valor de la propiedad nombreCat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCat() {
        return nombreCat;
    }

    /**
     * Define el valor de la propiedad nombreCat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCat(String value) {
        this.nombreCat = value;
    }

    /**
     * Obtiene el valor de la propiedad padre.
     * 
     * @return
     *     possible object is
     *     {@link Categoria }
     *     
     */
    public Categoria getPadre() {
        return padre;
    }

    /**
     * Define el valor de la propiedad padre.
     * 
     * @param value
     *     allowed object is
     *     {@link Categoria }
     *     
     */
    public void setPadre(Categoria value) {
        this.padre = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Producto }
     * 
     * 
     */
    public List<Producto> getProductos() {
        if (productos == null) {
            productos = new ArrayList<Producto>();
        }
        return this.productos;
    }

    /**
     * Obtiene el valor de la propiedad tieneProductos.
     * 
     */
    public boolean isTieneProductos() {
        return tieneProductos;
    }

    /**
     * Define el valor de la propiedad tieneProductos.
     * 
     */
    public void setTieneProductos(boolean value) {
        this.tieneProductos = value;
    }

}
