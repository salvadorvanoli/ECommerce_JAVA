package clases;

import java.util.regex.Pattern;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Embeddable
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FormaPago {
	
	private String numTarjeta;
	
	private String fecVencimiento;
	
	private String cvv;
	
	private String nomTitular;
	
	public FormaPago() {}
	
	public FormaPago(String numTarjeta, String fecVencimiento, String cvv, String nomTitular) {
		this.numTarjeta = numTarjeta;
		this.fecVencimiento = fecVencimiento;
		this.cvv = cvv;
		this.nomTitular = nomTitular;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getFecVencimiento() {
		return fecVencimiento;
	}

	public void setFecVencimiento(String fecVencimiento) {
		this.fecVencimiento = fecVencimiento;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getNomTitular() {
		return nomTitular;
	}

	public void setNomTitular(String nomTitular) {
		this.nomTitular = nomTitular;
	}
	
	public Boolean esValida() {
		Boolean numTarjeta = Pattern.compile("([0-9]{16}|(([0-9]){4} ){3}[0-9]{4})").matcher(this.numTarjeta).matches();
		Boolean fecVencimiento = Pattern.compile("(0[1-9]|1[0-2])/[0-9]{2}").matcher(this.fecVencimiento).matches();
		Boolean cvv = Pattern.compile("[0-9]{3}").matcher(this.cvv).matches();
		Boolean nomTitular = Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(this.nomTitular).matches();
		
		return numTarjeta && fecVencimiento && cvv && nomTitular;
		
	}

}