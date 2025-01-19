package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import clases.DTFecha;
import clases.ISistema;
import excepciones.ContraseniaIncorrectaException;
import excepciones.UsuarioRepetidoException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class RegistrarCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textNick;
	private JTextField textMail;
	private JTextField textNom;
	private JTextField textApe;
	private JDateChooser DateFecha;
	private String rutaImagen = "";
	private JTextField textCon;
	private JTextField textConfirmarCon;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	public RegistrarCliente(ISistema sistema) {
		setIconifiable(true);
		setResizable(true);
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		setTitle("Registrar Cliente");
		setClosable(true);
		setBounds(100, 100, 416, 519);
		getContentPane().setLayout(null);
		
		JLabel LabelRegCli = new JLabel("Registrar Cliente");
		LabelRegCli.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegCli.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelRegCli.setBounds(0, 11, 400, 29);
		getContentPane().add(LabelRegCli);
		
		JLabel LabelNick = new JLabel("Nickname");
		LabelNick.setBounds(38, 54, 77, 14);
		getContentPane().add(LabelNick);
		
		textNick = new JTextField();
		textNick.setColumns(10);
		textNick.setBounds(151, 51, 222, 20);
		getContentPane().add(textNick);
		
		JLabel LabelMail = new JLabel("Correo Electrónico");
		LabelMail.setBounds(38, 98, 115, 14);
		getContentPane().add(LabelMail);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(193, 95, 180, 20);
		getContentPane().add(textMail);
		
		JLabel LabelNom = new JLabel("Nombre");
		LabelNom.setBounds(38, 143, 77, 14);
		getContentPane().add(LabelNom);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(141, 140, 232, 20);
		getContentPane().add(textNom);
		
		JLabel LabelApe = new JLabel("Apellido");
		LabelApe.setBounds(38, 188, 77, 14);
		getContentPane().add(LabelApe);
		
		textApe = new JTextField();
		textApe.setColumns(10);
		textApe.setBounds(141, 185, 232, 20);
		getContentPane().add(textApe);
		
		JLabel LabelFecha = new JLabel("Fecha de Nacimiento");
		LabelFecha.setBounds(38, 334, 132, 14);
		getContentPane().add(LabelFecha);
		
		JButton ButtonImg = new JButton("Asignar una imagen");
		ButtonImg.setBounds(38, 410, 169, 20);
		getContentPane().add(ButtonImg);
		
		ButtonImg.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setDialogTitle("Seleccione una imagen");
			        // Filtrar por imágenes
			        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

			        int userSelection = fileChooser.showOpenDialog(RegistrarCliente.this);

			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File fileToUpload = fileChooser.getSelectedFile();
			            rutaImagen = fileToUpload.getAbsolutePath();  // Guardar la ruta de la imagen seleccionada
			            JOptionPane.showMessageDialog(RegistrarCliente.this, "Imagen seleccionada: " + rutaImagen, "Imagen", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }
		});

		DateFecha = new JDateChooser();
		DateFecha.setBounds(38, 359, 144, 20);
		getContentPane().add(DateFecha);
		
		JLabel LabelContrasenia = new JLabel("Contraseña");
		LabelContrasenia.setBounds(38, 234, 77, 14);
		getContentPane().add(LabelContrasenia);
		
		JLabel lblConfirmarContrasenia = new JLabel("Confirmar contraseña");
		lblConfirmarContrasenia.setBounds(38, 279, 115, 14);
		getContentPane().add(lblConfirmarContrasenia);
		
		textCon = new JTextField();
		textCon.setColumns(10);
		textCon.setBounds(141, 231, 232, 20);
		getContentPane().add(textCon);
		
		textConfirmarCon = new JTextField();
		textConfirmarCon.setColumns(10);
		textConfirmarCon.setBounds(193, 276, 180, 20);
		getContentPane().add(textConfirmarCon);
		
		JButton ButtonReg = new JButton("Registrar");
		ButtonReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (chequearFormulario()) {
						String nickname = textNick.getText();
						String correo = textMail.getText();
						String nombre = textNom.getText();
						String apellido = textApe.getText();
						String contrasenia = textCon.getText();
						String confirmarContrasenia = textConfirmarCon.getText();
						Date fechaN = DateFecha.getDate();
						Calendar calendar = Calendar.getInstance();
				        calendar.setTime(fechaN);
				        int dia = calendar.get(Calendar.DAY_OF_MONTH);
				        int mes = calendar.get(Calendar.MONTH) + 1;
				        int anio = calendar.get(Calendar.YEAR);
				        DTFecha dtFecha = new DTFecha(dia, mes, anio);
				        Calendar currentCalendar = Calendar.getInstance();
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.setTime(fechaN);

                        if (selectedCalendar.after(currentCalendar)) {
                            JOptionPane.showMessageDialog(RegistrarCliente.this, "La fecha seleccionada no puede ser posterior a la fecha actual.", "Fecha Inválida", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
						sistema.altaUsuarioCliente(nickname, correo, nombre, apellido, dtFecha, rutaImagen, null, contrasenia, confirmarContrasenia);
						
						JOptionPane.showMessageDialog(RegistrarCliente.this, "El Cliente se ha creado.", "Registrar Cliente",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					}
				}
				catch(UsuarioRepetidoException e){
					JOptionPane.showMessageDialog(RegistrarCliente.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ContraseniaIncorrectaException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(RegistrarCliente.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		ButtonReg.setBounds(268, 455, 105, 23);
		getContentPane().add(ButtonReg);
		
		JButton ButtonCancel = new JButton("Cancelar");
		ButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
			}
		});
		ButtonCancel.setBounds(38, 455, 105, 23);
		getContentPane().add(ButtonCancel);
		
	}
	
	//metodos
	
	public void limpiarFormulario() {
		textNick.setText("");
		textNom.setText("");
		textApe.setText("");
		textMail.setText("");
		textCon.setText("");
		textConfirmarCon.setText("");
		DateFecha.setDate(null);
		rutaImagen = "";
	}
		
	private boolean chequearFormulario() {
			String nickname = textNick.getText();
			String correo = textMail.getText();
			String nombre = textNom.getText();
			String apellido = textApe.getText();
			String contrasenia = textCon.getText();
			String confirmarContrasenia = textConfirmarCon.getText();
			//Date fechaN = DateFecha.getDate();
			if (nickname.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || DateFecha.getDate() == null || contrasenia.isEmpty() || confirmarContrasenia.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacíos.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (!esEmailValido(correo)) {
				JOptionPane.showMessageDialog(this, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
			}
			if (!SinNumeros(nombre) || !SinNumeros(apellido)) {
		        JOptionPane.showMessageDialog(this, "El nombre y apellido no pueden contener números.", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
		    }
			if (contrasenia.length() < 8 || confirmarContrasenia.length() < 8 ) {
				JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 8 caracteres.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (!contrasenia.equals(confirmarContrasenia)) {
				JOptionPane.showMessageDialog(this, "'Contraseña' y 'Confirmar contraseña' deben coincidir.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (nombre.length() < 3 || apellido.length() < 3) {
				JOptionPane.showMessageDialog(this, "El nombre y apellido no pueden ser menores a tres carácteres.", "Error", JOptionPane.ERROR_MESSAGE);
		        return false;
			}
			return true;
	}
			
	private boolean SinNumeros(String nombre) {
	    // Expresión regular que permite solo letras (mayúsculas y minúsculas) y espacios
	    return nombre.matches("[a-zA-Z ]+");
	}
	
	private static boolean esEmailValido(String email) {
        // Expresión regular para validar el formato del email
        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-z]{2,4}$";

        // Comprobación del email con el patrón
        return email != null && email.matches(emailRegex);
    }
}

