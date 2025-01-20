
package services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para cliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="cliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services/}usuario">
 *       &lt;sequence>
 *         &lt;element name="carrito" type="{http://services/}cantidad" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comentarios" type="{http://services/}comentario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="notificaciones" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cliente", propOrder = {
    "carrito",
    "comentarios",
    "notificaciones"
})
public class Cliente
    extends Usuario
{

    @XmlElement(nillable = true)
    protected List<Cantidad> carrito;
    @XmlElement(nillable = true)
    protected List<Comentario> comentarios;
    protected boolean notificaciones;

    /**
     * Gets the value of the carrito property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carrito property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarrito().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Cantidad }
     * 
     * 
     */
    public List<Cantidad> getCarrito() {
        if (carrito == null) {
            carrito = new ArrayList<Cantidad>();
        }
        return this.carrito;
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
     * Obtiene el valor de la propiedad notificaciones.
     * 
     */
    public boolean isNotificaciones() {
        return notificaciones;
    }

    /**
     * Define el valor de la propiedad notificaciones.
     * 
     */
    public void setNotificaciones(boolean value) {
        this.notificaciones = value;
    }

}
