
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ordenDeCompra complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ordenDeCompra">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cantidad" type="{http://services/}dtCantidad" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cliente" type="{http://services/}cliente" minOccurs="0"/>
 *         &lt;element name="detallesEnvio" type="{http://services/}detallesEnvio" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://services/}estado" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://services/}dtFecha" minOccurs="0"/>
 *         &lt;element name="formaPago" type="{http://services/}formaPago" minOccurs="0"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="precioTotal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="proveedores" type="{http://services/}proveedor" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ordenDeCompra", propOrder = {
    "cantidad",
    "cliente",
    "detallesEnvio",
    "estado",
    "fecha",
    "formaPago",
    "numero",
    "precioTotal",
    "proveedores"
})
public class OrdenDeCompra {

    @XmlElement(nillable = true)
    protected List<DtCantidad> cantidad;
    protected Cliente cliente;
    protected DetallesEnvio detallesEnvio;
    @XmlSchemaType(name = "string")
    protected Estado estado;
    protected DtFecha fecha;
    protected FormaPago formaPago;
    protected int numero;
    protected float precioTotal;
    @XmlElement(nillable = true)
    protected List<Proveedor> proveedores;

    /**
     * Gets the value of the cantidad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cantidad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCantidad().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCantidad }
     * 
     * 
     */
    public List<DtCantidad> getCantidad() {
        if (cantidad == null) {
            cantidad = new ArrayList<DtCantidad>();
        }
        return this.cantidad;
    }

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link Cliente }
     *     
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link Cliente }
     *     
     */
    public void setCliente(Cliente value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad detallesEnvio.
     * 
     * @return
     *     possible object is
     *     {@link DetallesEnvio }
     *     
     */
    public DetallesEnvio getDetallesEnvio() {
        return detallesEnvio;
    }

    /**
     * Define el valor de la propiedad detallesEnvio.
     * 
     * @param value
     *     allowed object is
     *     {@link DetallesEnvio }
     *     
     */
    public void setDetallesEnvio(DetallesEnvio value) {
        this.detallesEnvio = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstado(Estado value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link DtFecha }
     *     
     */
    public DtFecha getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFecha }
     *     
     */
    public void setFecha(DtFecha value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad formaPago.
     * 
     * @return
     *     possible object is
     *     {@link FormaPago }
     *     
     */
    public FormaPago getFormaPago() {
        return formaPago;
    }

    /**
     * Define el valor de la propiedad formaPago.
     * 
     * @param value
     *     allowed object is
     *     {@link FormaPago }
     *     
     */
    public void setFormaPago(FormaPago value) {
        this.formaPago = value;
    }

    /**
     * Obtiene el valor de la propiedad numero.
     * 
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Define el valor de la propiedad numero.
     * 
     */
    public void setNumero(int value) {
        this.numero = value;
    }

    /**
     * Obtiene el valor de la propiedad precioTotal.
     * 
     */
    public float getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Define el valor de la propiedad precioTotal.
     * 
     */
    public void setPrecioTotal(float value) {
        this.precioTotal = value;
    }

    /**
     * Gets the value of the proveedores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proveedores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProveedores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Proveedor }
     * 
     * 
     */
    public List<Proveedor> getProveedores() {
        if (proveedores == null) {
            proveedores = new ArrayList<Proveedor>();
        }
        return this.proveedores;
    }

}
