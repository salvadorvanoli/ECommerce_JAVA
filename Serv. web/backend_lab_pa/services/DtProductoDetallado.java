
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtProductoDetallado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtProductoDetallado"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://services/}dtProducto"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cantCompras" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="categorias" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="especificaciones" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="estrellas" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nombreTienda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="proveedor" type="{http://services/}dtProveedor" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtProductoDetallado", propOrder = {
    "cantCompras",
    "categorias",
    "especificaciones",
    "estrellas",
    "nombreTienda",
    "proveedor"
})
public class DtProductoDetallado
    extends DtProducto
{

    protected int cantCompras;
    @XmlElement(nillable = true)
    protected List<String> categorias;
    @XmlElement(nillable = true)
    protected List<String> especificaciones;
    protected int estrellas;
    protected String nombreTienda;
    protected DtProveedor proveedor;

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
     * {@link String }
     * 
     * 
     */
    public List<String> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<String>();
        }
        return this.categorias;
    }

    /**
     * Gets the value of the especificaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the especificaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEspecificaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEspecificaciones() {
        if (especificaciones == null) {
            especificaciones = new ArrayList<String>();
        }
        return this.especificaciones;
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
     * Obtiene el valor de la propiedad proveedor.
     * 
     * @return
     *     possible object is
     *     {@link DtProveedor }
     *     
     */
    public DtProveedor getProveedor() {
        return proveedor;
    }

    /**
     * Define el valor de la propiedad proveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link DtProveedor }
     *     
     */
    public void setProveedor(DtProveedor value) {
        this.proveedor = value;
    }

}
