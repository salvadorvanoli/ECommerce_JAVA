
package services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estado.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="estado">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="comprada"/>
 *     &lt;enumeration value="enPreparacion"/>
 *     &lt;enumeration value="enCamino"/>
 *     &lt;enumeration value="entregada"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estado")
@XmlEnum
public enum Estado {

    @XmlEnumValue("comprada")
    COMPRADA("comprada"),
    @XmlEnumValue("enPreparacion")
    EN_PREPARACION("enPreparacion"),
    @XmlEnumValue("enCamino")
    EN_CAMINO("enCamino"),
    @XmlEnumValue("entregada")
    ENTREGADA("entregada");
    private final String value;

    Estado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Estado fromValue(String v) {
        for (Estado c: Estado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
