
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para proveedor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="proveedor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services/}usuario">
 *       &lt;sequence>
 *         &lt;element name="productos" type="{http://services/}producto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="reclamos" type="{http://services/}reclamo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomCompania" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proveedor", propOrder = {
    "productos",
    "reclamos",
    "link",
    "nomCompania"
})
public class Proveedor
    extends Usuario
{

    @XmlElement(nillable = true)
    protected List<Producto> productos;
    @XmlElement(nillable = true)
    protected List<Reclamo> reclamos;
    protected String link;
    protected String nomCompania;

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
     * Gets the value of the reclamos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reclamos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReclamos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reclamo }
     * 
     * 
     */
    public List<Reclamo> getReclamos() {
        if (reclamos == null) {
            reclamos = new ArrayList<Reclamo>();
        }
        return this.reclamos;
    }

    /**
     * Obtiene el valor de la propiedad link.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Define el valor de la propiedad link.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Obtiene el valor de la propiedad nomCompania.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomCompania() {
        return nomCompania;
    }

    /**
     * Define el valor de la propiedad nomCompania.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomCompania(String value) {
        this.nomCompania = value;
    }

}
