package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import clases.ISistema;

public class datosEnvio extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private JTextField codigoPostalTextField;
	private JTextField departamentoTextField;
	private JTextField direccion1TextInput;
	private JTextField nombreTextInput;
	private JTextField tipoEnvioTextField;
	private JTextField precioEnvioTextField;
	private JTextField numeroTelefonoTextField;
	private JTextField ciudadTextField;
	private JTextField direccion2TextField;
	private JTextField apellidoTextField;

	public datosEnvio(ISistema sistema, Main main) {
		
        this.sistema = sistema;
        
		setClosable(true);
		setBounds(100, 100, 506, 398);
		getContentPane().setLayout(null);
		
		codigoPostalTextField = new JTextField();
		codigoPostalTextField.setEditable(false);
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
		departamentoTextField.setEditable(false);
		departamentoTextField.setColumns(10);
		departamentoTextField.setBounds(21, 188, 112, 20);
		getContentPane().add(departamentoTextField);
		
		direccion1TextInput = new JTextField();
		direccion1TextInput.setEditable(false);
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
		nombreTextInput.setEditable(false);
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
		tipoEnvioTextField.setEditable(false);
		tipoEnvioTextField.setColumns(10);
		tipoEnvioTextField.setBounds(21, 315, 112, 20);
		getContentPane().add(tipoEnvioTextField);
		
		JLabel tipoEnvioLabel = new JLabel("Tipo Envio");
		tipoEnvioLabel.setBounds(143, 318, 224, 14);
		getContentPane().add(tipoEnvioLabel);
		
		precioEnvioTextField = new JTextField();
		precioEnvioTextField.setEditable(false);
		precioEnvioTextField.setColumns(10);
		precioEnvioTextField.setBounds(263, 315, 112, 20);
		getContentPane().add(precioEnvioTextField);
		
		JLabel precioEnvioLabel = new JLabel("Precio Envio");
		precioEnvioLabel.setBounds(385, 318, 224, 14);
		getContentPane().add(precioEnvioLabel);
		
		JLabel numeroTelefonoLabel = new JLabel("Número teléfono");
		numeroTelefonoLabel.setBounds(385, 255, 224, 14);
		getContentPane().add(numeroTelefonoLabel);
		
		numeroTelefonoTextField = new JTextField();
		numeroTelefonoTextField.setEditable(false);
		numeroTelefonoTextField.setColumns(10);
		numeroTelefonoTextField.setBounds(263, 252, 112, 20);
		getContentPane().add(numeroTelefonoTextField);
		
		ciudadTextField = new JTextField();
		ciudadTextField.setEditable(false);
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
		direccion2TextField.setEditable(false);
		direccion2TextField.setColumns(10);
		direccion2TextField.setBounds(263, 123, 112, 20);
		getContentPane().add(direccion2TextField);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setEditable(false);
		apellidoTextField.setColumns(10);
		apellidoTextField.setBounds(263, 59, 112, 20);
		getContentPane().add(apellidoTextField);
		
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(385, 62, 224, 14);
		getContentPane().add(apellidoLabel);

	}
	
	@Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
        	limpiarCampos();
            cargarDatos();
        }
    }
	
	public void limpiarCampos() {
		codigoPostalTextField.setText("");
		departamentoTextField.setText("");
		direccion1TextInput.setText("");
		nombreTextInput.setText("");
		tipoEnvioTextField.setText("");
		precioEnvioTextField.setText("");
		numeroTelefonoTextField.setText("");
		ciudadTextField.setText("");
		direccion2TextField.setText("");
		apellidoTextField.setText("");

	}
	
	public void cargarDatos() {
		codigoPostalTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getCodPostal());
		departamentoTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getDepartamento());
		direccion1TextInput.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getDireccion1());
		nombreTextInput.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getNombre());
		tipoEnvioTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getTipoEnvio());
		precioEnvioTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getPrecioEnvio());
		numeroTelefonoTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getNumTelefono());
		ciudadTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getCiudad());
		direccion2TextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getDireccion2());
		apellidoTextField.setText(sistema.getOrdenDeCompraActual().getDetallesEnvio().getApellido());
	}

}
