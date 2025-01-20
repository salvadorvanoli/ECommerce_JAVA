
package services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtProveedor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtProveedor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services/}dtUsuarioDetallado">
 *       &lt;sequence>
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
@XmlType(name = "dtProveedor", propOrder = {
    "link",
    "nomCompania"
})
public class DtProveedor
    extends DtUsuarioDetallado
{

    protected String link;
    protected String nomCompania;

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
