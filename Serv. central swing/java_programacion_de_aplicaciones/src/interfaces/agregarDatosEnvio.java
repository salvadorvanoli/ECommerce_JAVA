package interfaces;

import clases.DetallesEnvio;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.SwingConstants;

import clases.ISistema;
import javax.swing.JButton;

public class agregarDatosEnvio extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private JTextField codigoPostalTextField;
	private JTextField departamentoTextField;
	private JTextField direccion1TextInput;
	private JTextField nombreTextInput;
	private JTextField tipoEnvioTextField;
	private JTextField numeroTelefonoTextField;
	private JTextField ciudadTextField;
	private JTextField direccion2TextField;
	private JTextField apellidoTextField;

	public agregarDatosEnvio(ISistema sistema, Main main) {

        this.sistema = sistema;
        
		setClosable(true);
		setBounds(100, 100, 506, 398);
		getContentPane().setLayout(null);
		
		codigoPostalTextField = new JTextField();
		codigoPostalTextField.setEditable(true);
		codigoPostalTextField.setColumns(10);
		codigoPostalTextField.setBounds(21, 252, 112, 20);
		getContentPane().add(codigoPostalTextField);
		
		JLabel codigoPostalLabel = new JLabel("Código Postal");
		codigoPostalLabel.setBounds(143, 255, 224, 14);
		getContentPane().add(codigoPostalLabel);
		
		JLabel departamentoLabel = new JLabel("Departamento");
		departamentoLabel.setBounds(143, 191, 224, 14);
		getContentPane().add(departamentoLabel);
		
		departamentoTextField = new JTextField();
		departamentoTextField.setEditable(true);
		departamentoTextField.setColumns(10);
		departamentoTextField.setBounds(21, 188, 112, 20);
		getContentPane().add(departamentoTextField);
		
		direccion1TextInput = new JTextField();
		direccion1TextInput.setEditable(true);
		direccion1TextInput.setColumns(10);
		direccion1TextInput.setBounds(21, 123, 112, 20);
		getContentPane().add(direccion1TextInput);
		
		JLabel direccion1Label = new JLabel("Dirección 1");
		direccion1Label.setBounds(143, 126, 224, 14);
		getContentPane().add(direccion1Label);
		
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(143, 62, 224, 14);
		getContentPane().add(nombreLabel);
		
		nombreTextInput = new JTextField();
		nombreTextInput.setEditable(true);
		nombreTextInput.setColumns(10);
		nombreTextInput.setBounds(21, 59, 112, 20);
		getContentPane().add(nombreTextInput);
		
		JLabel informacionEnvioLabel = new JLabel("Información Envio");
		informacionEnvioLabel.setVerticalAlignment(SwingConstants.TOP);
		informacionEnvioLabel.setHorizontalAlignment(SwingConstants.CENTER);
		informacionEnvioLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		informacionEnvioLabel.setBounds(72, 11, 334, 20);
		getContentPane().add(informacionEnvioLabel);
		
		tipoEnvioTextField = new JTextField();
		tipoEnvioTextField.setEditable(true);
		tipoEnvioTextField.setColumns(10);
		tipoEnvioTextField.setBounds(21, 315, 112, 20);
		getContentPane().add(tipoEnvioTextField);
		
		JLabel tipoEnvioLabel = new JLabel("Tipo Envio");
		tipoEnvioLabel.setBounds(143, 318, 224, 14);
		getContentPane().add(tipoEnvioLabel);
		
		JLabel numeroTelefonoLabel = new JLabel("Número teléfono");
		numeroTelefonoLabel.setBounds(385, 255, 224, 14);
		getContentPane().add(numeroTelefonoLabel);
		
		numeroTelefonoTextField = new JTextField();
		numeroTelefonoTextField.setEditable(true);
		numeroTelefonoTextField.setColumns(10);
		numeroTelefonoTextField.setBounds(263, 252, 112, 20);
		getContentPane().add(numeroTelefonoTextField);
		
		ciudadTextField = new JTextField();
		ciudadTextField.setEditable(true);
		ciudadTextField.setColumns(10);
		ciudadTextField.setBounds(263, 188, 112, 20);
		getContentPane().add(ciudadTextField);
		
		JLabel ciudadLabel = new JLabel("Ciudad");
		ciudadLabel.setBounds(385, 191, 224, 14);
		getContentPane().add(ciudadLabel);
		
		JLabel direccion2Label = new JLabel("Dirección 2");
		direccion2Label.setBounds(385, 126, 224, 14);
		getContentPane().add(direccion2Label);
		
		direccion2TextField = new JTextField();
		direccion2TextField.setEditable(true);
		direccion2TextField.setColumns(10);
		direccion2TextField.setBounds(263, 123, 112, 20);
		getContentPane().add(direccion2TextField);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setEditable(true);
		apellidoTextField.setColumns(10);
		apellidoTextField.setBounds(263, 59, 112, 20);
		getContentPane().add(apellidoTextField);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(385, 62, 224, 14);
		getContentPane().add(apellidoLabel);
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.setBounds(292, 314, 89, 23);
		getContentPane().add(aceptar);

		// Agregar ActionListener al botón
		aceptar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (chequearCampos()) {
		            // Crear la instancia de DetallesEnvio con los datos de los textfields
		            DetallesEnvio detalles = new DetallesEnvio(
		                nombreTextInput.getText(),
		                apellidoTextField.getText(),
		                direccion1TextInput.getText(),
		                direccion2TextField.getText(),
		                departamentoTextField.getText(),
		                ciudadTextField.getText(),
		                codigoPostalTextField.getText(),
		                numeroTelefonoTextField.getText(),
		                tipoEnvioTextField.getText()
		            );
		            sistema.setDetallesEnvioActual(detalles);
		            // Cerrar la ventana
		            dispose();
		        }
		    }
		});

		
		
	}
	
	
	
	@Override
	public void setVisible(boolean aFlag) {
	    super.setVisible(aFlag);
	    if (aFlag) {
	        if (sistema.getDetallesEnvioActual() == null) {
	            limpiarCampos(); // Limpia los campos si no hay detalles de envío
	        } else {
	            // Asigna los valores de detalles de envío a los campos de texto
	            DetallesEnvio detalleEnvio = sistema.getDetallesEnvioActual();
	            codigoPostalTextField.setText(detalleEnvio.getCodPostal());
	            departamentoTextField.setText(detalleEnvio.getDepartamento());
	            direccion1TextInput.setText(detalleEnvio.getDireccion1());
	            nombreTextInput.setText(detalleEnvio.getNombre());
	            tipoEnvioTextField.setText(detalleEnvio.getTipoEnvio());
	            numeroTelefonoTextField.setText(detalleEnvio.getNumTelefono());
	            ciudadTextField.setText(detalleEnvio.getCiudad());
	            direccion2TextField.setText(detalleEnvio.getDireccion2());
	            apellidoTextField.setText(detalleEnvio.getApellido());
	        }
	    }
	}

	
	public void limpiarCampos() {
		codigoPostalTextField.setText("");
		departamentoTextField.setText("");
		direccion1TextInput.setText("");
		nombreTextInput.setText("");
		tipoEnvioTextField.setText("");
		numeroTelefonoTextField.setText("");
		ciudadTextField.setText("");
		direccion2TextField.setText("");
		apellidoTextField.setText("");

	}
	
	public boolean chequearCampos() {
	    if (!Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(nombreTextInput.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras y espacios.", "Error en Nombre", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!Pattern.compile("[A-Za-z]+(( ?([A-Za-z]+))?)+").matcher(apellidoTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras y espacios.", "Error en Apellido", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!Pattern.compile("[A-Za-z0-9]+(( ?([A-Za-z0-9]+))?)+ [0-9]+").matcher(direccion1TextInput.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "La dirección debe contener letras, números y un número al final.", "Error en Dirección", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    
	    if (!direccion2TextField.getText().isEmpty() &&
	    	    !Pattern.compile("[A-Za-z0-9]+(( ?([A-Za-z0-9]+))?)+ [0-9]+")
	    	            .matcher(direccion2TextField.getText()).matches()) {
	    	    JOptionPane.showMessageDialog(null, "La dirección debe contener letras, números y un número al final.", 
	    	                                  "Error en Dirección", JOptionPane.ERROR_MESSAGE);
	    	    return false;
	    	}


	    // Validación para 'departamento' (solo letras y espacios, sin estar vacío)
	    if (!Pattern.compile("[A-Za-z]+( [A-Za-z]+)*").matcher(departamentoTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "El departamento solo debe contener letras y espacios, y no puede estar vacío.", "Error en Departamento", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    // Validación para 'ciudad' (solo letras y espacios, sin estar vacío)
	    if (!Pattern.compile("[A-Za-z]+( [A-Za-z]+)*").matcher(ciudadTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "La ciudad solo debe contener letras y espacios, y no puede estar vacía.", "Error en Ciudad", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!Pattern.compile("[0-9]{5}").matcher(codigoPostalTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "El código postal debe tener exactamente 5 dígitos.", "Error en Código Postal", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!Pattern.compile("09[1-9][0-9]{6}").matcher(numeroTelefonoTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "El número de teléfono debe comenzar con 09 y tener 9 dígitos.", "Error en Teléfono", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!Pattern.compile("^(free|vip)$", Pattern.CASE_INSENSITIVE).matcher(tipoEnvioTextField.getText()).matches()) {
	        JOptionPane.showMessageDialog(null, "El tipo de envío debe ser 'free' o 'vip'.", "Error en Tipo de Envío", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }


	    return true;
	}
}
