
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCategoria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtCategoria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hijas" type="{http://services/}dtCategoria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nombreCat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "dtCategoria", propOrder = {
    "hijas",
    "nombreCat",
    "tieneProductos"
})
public class DtCategoria {

    @XmlElement(nillable = true)
    protected List<DtCategoria> hijas;
    protected String nombreCat;
    protected boolean tieneProductos;

    /**
     * Gets the value of the hijas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hijas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHijas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCategoria }
     * 
     * 
     */
    public List<DtCategoria> getHijas() {
        if (hijas == null) {
            hijas = new ArrayList<DtCategoria>();
        }
        return this.hijas;
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
