
package services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtFecha complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtFecha">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtFecha", propOrder = {
    "anio",
    "dia",
    "mes"
})
public class DtFecha {

    protected int anio;
    protected int dia;
    protected int mes;

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

}
