package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import clases.ISistema;

import javax.swing.JTextField;

public class formaPago extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private JTextField numTarjetaTextField;
	private JTextField fechaVencimientoTextField;
	private JTextField CVVTextField;
	private JTextField nombreTitularTextField;

	public formaPago(ISistema sistema, Main main) {
		
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
		numTarjetaTextField.setEditable(false);
		numTarjetaTextField.setColumns(10);
		numTarjetaTextField.setBounds(37, 62, 112, 20);
		getContentPane().add(numTarjetaTextField);
		
		JLabel numeroTarjetaLabel = new JLabel("Número tarjeta");
		numeroTarjetaLabel.setBounds(159, 65, 224, 14);
		getContentPane().add(numeroTarjetaLabel);
		
		fechaVencimientoTextField = new JTextField();
		fechaVencimientoTextField.setEditable(false);
		fechaVencimientoTextField.setColumns(10);
		fechaVencimientoTextField.setBounds(37, 126, 112, 20);
		getContentPane().add(fechaVencimientoTextField);
		
		JLabel fechaVencimientoLabel = new JLabel("Fecha vencimiento");
		fechaVencimientoLabel.setBounds(159, 129, 224, 14);
		getContentPane().add(fechaVencimientoLabel);
		
		CVVTextField = new JTextField();
		CVVTextField.setEditable(false);
		CVVTextField.setColumns(10);
		CVVTextField.setBounds(37, 191, 112, 20);
		getContentPane().add(CVVTextField);
		
		JLabel CVVLabel = new JLabel("CVV");
		CVVLabel.setBounds(159, 194, 224, 14);
		getContentPane().add(CVVLabel);
		
		nombreTitularTextField = new JTextField();
		nombreTitularTextField.setEditable(false);
		nombreTitularTextField.setColumns(10);
		nombreTitularTextField.setBounds(37, 255, 112, 20);
		getContentPane().add(nombreTitularTextField);
		
		JLabel nombreTitularLabel = new JLabel("Nombre Titular");
		nombreTitularLabel.setBounds(159, 258, 224, 14);
		getContentPane().add(nombreTitularLabel);

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
		numTarjetaTextField.setText("");
		fechaVencimientoTextField.setText("");
		CVVTextField.setText("");
		nombreTitularTextField.setText("");
	}
	
	public void cargarDatos() {
		numTarjetaTextField.setText(sistema.getOrdenDeCompraActual().getFormaPago().getNumTarjeta());
		fechaVencimientoTextField.setText(sistema.getOrdenDeCompraActual().getFormaPago().getFecVencimiento());
		CVVTextField.setText(sistema.getOrdenDeCompraActual().getFormaPago().getCvv());
		nombreTitularTextField.setText(sistema.getOrdenDeCompraActual().getFormaPago().getNomTitular());
	}
}
