
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtReclamo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtReclamo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="apellidoCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripProd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="imagenProd" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nicknameCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nicknameProveedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreProd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numReferencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="precioProd" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtReclamo", propOrder = {
    "anio",
    "apellidoCliente",
    "descripProd",
    "dia",
    "id",
    "imagenProd",
    "mensaje",
    "mes",
    "nicknameCliente",
    "nicknameProveedor",
    "nombreCliente",
    "nombreProd",
    "numReferencia",
    "precioProd"
})
public class DtReclamo {

    protected int anio;
    protected String apellidoCliente;
    protected String descripProd;
    protected int dia;
    protected int id;
    @XmlElement(nillable = true)
    protected List<String> imagenProd;
    protected String mensaje;
    protected int mes;
    protected String nicknameCliente;
    protected String nicknameProveedor;
    protected String nombreCliente;
    protected String nombreProd;
    protected int numReferencia;
    protected float precioProd;

    /**
     * Obtiene el valor de la propiedad anio.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Define el valor de la propiedad anio.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    /**
     * Define el valor de la propiedad apellidoCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoCliente(String value) {
        this.apellidoCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad descripProd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripProd() {
        return descripProd;
    }

    /**
     * Define el valor de la propiedad descripProd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripProd(String value) {
        this.descripProd = value;
    }

    /**
     * Obtiene el valor de la propiedad dia.
     * 
     */
    public int getDia() {
        return dia;
    }

    /**
     * Define el valor de la propiedad dia.
     * 
     */
    public void setDia(int value) {
        this.dia = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the imagenProd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imagenProd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImagenProd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getImagenProd() {
        if (imagenProd == null) {
            imagenProd = new ArrayList<String>();
        }
        return this.imagenProd;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad mes.
     * 
     */
    public int getMes() {
        return mes;
    }

    /**
     * Define el valor de la propiedad mes.
     * 
     */
    public void setMes(int value) {
        this.mes = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameCliente() {
        return nicknameCliente;
    }

    /**
     * Define el valor de la propiedad nicknameCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameCliente(String value) {
        this.nicknameCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameProveedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameProveedor() {
        return nicknameProveedor;
    }

    /**
     * Define el valor de la propiedad nicknameProveedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameProveedor(String value) {
        this.nicknameProveedor = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Define el valor de la propiedad nombreCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCliente(String value) {
        this.nombreCliente = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreProd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProd() {
        return nombreProd;
    }

    /**
     * Define el valor de la propiedad nombreProd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProd(String value) {
        this.nombreProd = value;
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
     * Obtiene el valor de la propiedad precioProd.
     * 
     */
    public float getPrecioProd() {
        return precioProd;
    }

    /**
     * Define el valor de la propiedad precioProd.
     * 
     */
    public void setPrecioProd(float value) {
        this.precioProd = value;
    }

}
