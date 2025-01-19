package clases;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTFecha {

	private int dia;
	private int mes;
	private int anio;
	
	
	public int getDia() {
		return dia;
	}
	
	public void setDia(int dia) {
		this.dia = dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public void setMes(int mes) {
		this.mes = mes;
	}
	
	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public DTFecha() {}
	
	public DTFecha(int dia, int mes, int anio) {
		super();
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	
	public String toString() {
		return String.valueOf(this.dia) + '/' + String.valueOf(this.mes) + '/' + String.valueOf(this.anio);
	}
	
	public String getFechaEnFormatoInput() {
		
		String mesInput = String.valueOf(mes);
		if(mes <= 9) {
			mesInput = "0" + mesInput;
		}
		
		String diaInput = String.valueOf(dia);
		if(dia <= 9) {
			diaInput = "0" + diaInput;
		}
		return anio + "-" + mesInput + "-" + diaInput;
	}
	
}
