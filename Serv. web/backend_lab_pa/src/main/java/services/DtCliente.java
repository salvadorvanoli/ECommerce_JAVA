
package services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtCliente">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services/}dtUsuarioDetallado">
 *       &lt;sequence>
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
@XmlType(name = "dtCliente", propOrder = {
    "notificaciones"
})
public class DtCliente
    extends DtUsuarioDetallado
{

    protected boolean notificaciones;

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
