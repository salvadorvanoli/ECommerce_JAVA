package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import clases.DTFecha;
import clases.ISistema;
import excepciones.UsuarioRepetidoException;
import excepciones.ContraseniaIncorrectaException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.ImageIcon;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class RegistrarProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textNick;
	private JTextField textMail;
	private JTextField textNom;
	private JTextField textApe;
	private JTextField textCompan;
	private JTextField textLink;
	private JDateChooser DateFecha;
	private String rutaImagen = "";
	private JTextField contrasenia1input;
	private JTextField contrasenia2input;


	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	public RegistrarProveedor(ISistema sistema) {
		setIconifiable(true);
		setResizable(true);
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		setTitle("Registrar Proveedor");
		setClosable(true);
		setBounds(100, 100, 417, 620);
		getContentPane().setLayout(null);
		
		JLabel LabelRegProv = new JLabel("Registrar Proveedor");
		LabelRegProv.setHorizontalAlignment(SwingConstants.CENTER);
		LabelRegProv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LabelRegProv.setBounds(0, 11, 400, 29);
		getContentPane().add(LabelRegProv);
		
		JLabel LabelNick = new JLabel("Nickname");
		LabelNick.setBounds(38, 54, 67, 14);
		getContentPane().add(LabelNick);
		
		textNick = new JTextField();
		textNick.setColumns(10);
		textNick.setBounds(148, 51, 222, 20);
		getContentPane().add(textNick);
		
		JLabel LabelMail = new JLabel("Correo Electrónico");
		LabelMail.setBounds(38, 98, 118, 14);
		getContentPane().add(LabelMail);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(190, 95, 180, 20);
		getContentPane().add(textMail);
		
		JLabel LabelNom = new JLabel("Nombre");
		LabelNom.setBounds(38, 143, 67, 14);
		getContentPane().add(LabelNom);
		
		textNom = new JTextField();
		textNom.setColumns(10);
		textNom.setBounds(138, 140, 232, 20);
		getContentPane().add(textNom);
		
		JLabel LabelApe = new JLabel("Apellido");
		LabelApe.setBounds(38, 188, 67, 14);
		getContentPane().add(LabelApe);
		
		textApe = new JTextField();
		textApe.setColumns(10);
		textApe.setBounds(138, 185, 232, 20);
		getContentPane().add(textApe);
		
		textCompan = new JTextField();
		textCompan.setColumns(10);
		textCompan.setBounds(214, 224, 156, 20);
		getContentPane().add(textCompan);
		
		JLabel LabelCompan = new JLabel("Nombre de la Compañía");
		LabelCompan.setBounds(38, 227, 144, 14);
		getContentPane().add(LabelCompan);
		
		JLabel LabelLink = new JLabel("Link Web");
		LabelLink.setBounds(38, 272, 67, 14);
		getContentPane().add(LabelLink);
		
		textLink = new JTextField();
		textLink.setColumns(10);
		textLink.setBounds(138, 269, 232, 20);
		getContentPane().add(textLink);
		
		JLabel LabelFecha = new JLabel("Fecha de Nacimiento");
		LabelFecha.setBounds(38, 312, 118, 14);
		getContentPane().add(LabelFecha);
		
		JButton ButtonImg = new JButton("Asignar una imagen");
		ButtonImg.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Seleccione una imagen");
		        // Filtrar por imágenes
		        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "jpeg"));

		        int userSelection = fileChooser.showOpenDialog(RegistrarProveedor.this);

		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToUpload = fileChooser.getSelectedFile();
		            rutaImagen = fileToUpload.getAbsolutePath();  // Guardar la ruta de la imagen seleccionada
		            JOptionPane.showMessageDialog(RegistrarProveedor.this, "Imagen seleccionada: " + rutaImagen, "Imagen", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});

		ButtonImg.setBounds(38, 495, 168, 20);
		getContentPane().add(ButtonImg);
		
		JButton ButtonReg = new JButton("Registrar");
		ButtonReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(chequearFormulario()) {
						String nickname = textNick.getText();
						String correo = textMail.getText();
						String nombre = textNom.getText();
						String apellido = textApe.getText();
						String comp = textCompan.getText();
						String link = textLink.getText();
						Date fechaN = DateFecha.getDate();
						String contrasenia1 = contrasenia1input.getText();
						String contrasenia2 = contrasenia2input.getText();
						Calendar calendar = Calendar.getInstance();
				        calendar.setTime(fechaN);
				        int dia = calendar.get(Calendar.DAY_OF_MONTH);
				        int mes = calendar.get(Calendar.MONTH) + 1;
				        int anio = calendar.get(Calendar.YEAR);
				        Calendar currentCalendar = Calendar.getInstance();
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.setTime(fechaN);

                        if (selectedCalendar.after(currentCalendar)) {
                            JOptionPane.showMessageDialog(RegistrarProveedor.this, "La fecha seleccionada no puede ser posterior a la fecha actual.", "Fecha Inválida", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
				        DTFecha dtFecha = new DTFecha(dia, mes, anio);
				        if (!rutaImagen.isEmpty()) {
		                    File fileToUpload = new File(rutaImagen);
		                    String destinationPath = "src/images/" + fileToUpload.getName();  
		                    File destinationFile = new File(destinationPath);

		                    // Crear la carpeta si no existe
		                    destinationFile.getParentFile().mkdirs();

		                    try {
		                        // Copiar el archivo a la carpeta de destino
		                        Files.copy(fileToUpload.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		                        rutaImagen = destinationFile.getAbsolutePath();
		                    } catch (IOException ioException) {
		                        JOptionPane.showMessageDialog(RegistrarProveedor.this, "Error al guardar la imagen: " + ioException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                   
		                    }
		                }
						sistema.altaUsuarioProveedor(nickname, correo, nombre, apellido, dtFecha, comp, link, rutaImagen, null, contrasenia1, contrasenia2);
						JOptionPane.showMessageDialog(RegistrarProveedor.this, "El Proveedor se ha creado.", "Registrar Proveedor",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					}
				}
				catch(UsuarioRepetidoException e1){
					JOptionPane.showMessageDialog(RegistrarProveedor.this, e1.getMessage(), "Registrar Proveedor", JOptionPane.ERROR_MESSAGE);
				} catch (ContraseniaIncorrectaException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(RegistrarProveedor.this, e1.getMessage(), "Registrar Proveedor", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		ButtonReg.setBounds(256, 538, 114, 23);
		getContentPane().add(ButtonReg);
		
		DateFecha = new JDateChooser();
		DateFecha.setBounds(38, 337, 144, 20);
		getContentPane().add(DateFecha);
		
		JButton ButtonCancel = new JButton("Cancelar");
		ButtonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
			}
		});
		ButtonCancel.setBounds(38, 538, 105, 23);
		getContentPane().add(ButtonCancel);
		
		contrasenia1input = new JTextField();
		contrasenia1input.setColumns(10);
		contrasenia1input.setBounds(148, 384, 222, 20);
		getContentPane().add(contrasenia1input);
		
		JLabel Contrasenia1 = new JLabel("Contraseña");
		Contrasenia1.setBounds(38, 387, 67, 14);
		getContentPane().add(Contrasenia1);
		
		contrasenia2input = new JTextField();
		contrasenia2input.setColumns(10);
		contrasenia2input.setBounds(148, 433, 222, 20);
		getContentPane().add(contrasenia2input);
		
		JLabel Contrasenia2 = new JLabel("Repita contraseña");
		Contrasenia2.setBounds(38, 436, 105, 14);
		getContentPane().add(Contrasenia2);

	}
	
	public void limpiarFormulario() {
		textNick.setText("");
		textNom.setText("");
		textApe.setText("");
		textMail.setText("");
		textCompan.setText("");
		textLink.setText("");
		DateFecha.setDate(null);
		rutaImagen = "";
		contrasenia1input.setText("");
		contrasenia2input.setText("");
	}
	private boolean chequearFormulario() {
		String nickname = textNick.getText();
		String correo = textMail.getText();
		String nombre = textNom.getText();
		String apellido = textApe.getText();
		//Date fechaN = DateFecha.getDate();
		String comp = textCompan.getText();
		String link = textLink.getText();
		String contrasenia1 = contrasenia1input.getText();
		String contrasenia2 = contrasenia2input.getText();
		
		if (nickname.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || DateFecha.getDate() == null || comp.isEmpty() || link.isEmpty() || contrasenia1.isEmpty() || contrasenia2.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar ",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!esEmailValido(correo)) {
			JOptionPane.showMessageDialog(this, "El email no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
		}
		if (!validarUrl(link)) {
			JOptionPane.showMessageDialog(this, "El link no es válido.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
		}
		if (contrasenia1.length() < 8 || contrasenia2.length() < 8) {
			JOptionPane.showMessageDialog(this, "La contraseña debe ser de 8 caracteres o más", "Registrar ",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!contrasenia1.equals(contrasenia2)) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Registrar ",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!SinNumeros(nombre) || !SinNumeros(apellido)) {
	        JOptionPane.showMessageDialog(this, "El nombre y apellido no pueden contener números.", "Registrar Proveedor", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
		if (nombre.length() < 3 || apellido.length() < 3) {
			JOptionPane.showMessageDialog(this, "El nombre y apellido no pueden ser menores a tres carácteres.", "Registrar Proveedor", JOptionPane.ERROR_MESSAGE);
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
	
	private boolean validarUrl(String url) {
        String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(:[0-9]+)?(/.*)?$";
        return url.matches(regex);
    }
}
