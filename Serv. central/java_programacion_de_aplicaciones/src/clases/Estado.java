package clases;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Estado {
    @XmlEnumValue("comprada")
    comprada,
    
    @XmlEnumValue("enPreparacion")
    enPreparacion,
    
    @XmlEnumValue("enCamino")
    enCamino,
    
    @XmlEnumValue("entregada")
    entregada
}
