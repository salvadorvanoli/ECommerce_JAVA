package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;

import clases.FormaPago;
import clases.ISistema;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class agregarFormaPago extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private JTextField numTarjetaTextField;
	private JTextField fechaVencimientoTextField;
	private JTextField CVVTextField;
	private JTextField nombreTitularTextField;

	public agregarFormaPago(ISistema sistema, Main main) {
		
        this.sistema = sistema;
		
		setClosable(true);
		setBounds(100, 100, 306, 368);
		getContentPane().setLayout(null);
		
		JLabel informacionPagoLabel = new JLabel("Información Pago");
		informacionPagoLabel.setVerticalAlignment(SwingConstants.TOP);
		informacionPagoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informacionPagoLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		informacionPagoLabel.setBounds(-24, 11, 334, 20);
		getContentPane().add(informacionPagoLabel);
		
		numTarjetaTextField = new JTextField();
		numTarjetaTextField.setEditable(true);
		numTarjetaTextField.setColumns(10);
		numTarjetaTextField.setBounds(37, 62, 112, 20);
		getContentPane().add(numTarjetaTextField);
		
		JLabel numeroTarjetaLabel = new JLabel("Número tarjeta");
		numeroTarjetaLabel.setBounds(159, 65, 224, 14);
		getContentPane().add(numeroTarjetaLabel);
		
		fechaVencimientoTextField = new JTextField();
		fechaVencimientoTextField.setEditable(true);
		fechaVencimientoTextField.setColumns(10);
		fechaVencimientoTextField.setBounds(37, 126, 112, 20);
		getContentPane().add(fechaVencimientoTextField);
		
		JLabel fechaVencimientoLabel = new JLabel("Fecha vencimiento");
		fechaVencimientoLabel.setBounds(159, 129, 224, 14);
		getContentPane().add(fechaVencimientoLabel);
		
		CVVTextField = new JTextField();
		CVVTextField.setEditable(true);
		CVVTextField.setColumns(10);
		CVVTextField.setBounds(37, 191, 112, 20);
		getContentPane().add(CVVTextField);
		
		JLabel CVVLabel = new JLabel("CVV");
		CVVLabel.setBounds(159, 194, 224, 14);
		getContentPane().add(CVVLabel);
		
		nombreTitularTextField = new JTextField();
		nombreTitularTextField.setEditable(true);
		nombreTitularTextField.setColumns(10);
		nombreTitularTextField.setBounds(37, 255, 112, 20);
		getContentPane().add(nombreTitularTextField);
		
		JLabel nombreTitularLabel = new JLabel("Nombre Titular");
		nombreTitularLabel.setBounds(159, 258, 224, 14);
		getContentPane().add(nombreTitularLabel);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(37, 304, 89, 23);
		getContentPane().add(aceptar);
		
		// Agregar ActionListener al botón
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( chequeoDatos( ) ) {
                	FormaPago formaPago = new FormaPago(numTarjetaTextField.getText(), fechaVencimientoTextField.getText(), CVVTextField.getText(), nombreTitularTextField.getText());
                	sistema.setFormaPagoActual(formaPago);
                	dispose();
                }
            }
        });

	}
	
	@Override
	public void setVisible(boolean aFlag) {
	    super.setVisible(aFlag);
	    if (aFlag) {
	        FormaPago formaPagoActual = sistema.getFormaPagoActual();
	        
	        if (formaPagoActual == null) {
	            limpiarCampos();
	        } else {
	            numTarjetaTextField.setText(formaPagoActual.getNumTarjeta());
	            fechaVencimientoTextField.setText(formaPagoActual.getFecVencimiento());
	            CVVTextField.setText(formaPagoActual.getCvv());
	            nombreTitularTextField.setText(formaPagoActual.getNomTitular());
	        }
	    }
	}

	
	public void limpiarCampos() {
		numTarjetaTextField.setText("");
		fechaVencimientoTextField.setText("");
		CVVTextField.setText("");
		nombreTitularTextField.setText("");
	}
	
	
	
	public boolean chequeoDatos() {
	    // Verificar número de tarjeta
	    if (!Pattern.compile("([0-9]{16}|(([0-9]){4} ){3}[0-9]{4})").matcher(numTarjetaTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "Número de tarjeta inválido. Debe tener 16 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    // Verificar fecha de vencimiento
	    if (!Pattern.compile("(0[1-9]|1[0-2])/[0-9]{2}").matcher(fechaVencimientoTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "Fecha de vencimiento inválida. Formato esperado: MM/AA.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    if (YearMonth.parse(fechaVencimientoTextField.getText(), DateTimeFormatter.ofPattern("MM/yy")).isBefore(YearMonth.now())) {
	        JOptionPane.showMessageDialog(null, "La fecha de vencimiento debe ser posterior a la fecha actual.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    
	    // Verificar CVV
	    if (!Pattern.compile("[0-9]{3}").matcher(CVVTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "CVV inválido. Debe tener 3 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    // Verificar nombre del titular
	    if (!Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(nombreTitularTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "Nombre del titular inválido. Debe contener solo letras.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
	        return false;    
	    }
	    
	    return true;
	}


	
	
}
