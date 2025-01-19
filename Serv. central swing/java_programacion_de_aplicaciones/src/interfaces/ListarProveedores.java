package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import clases.DTProveedor;
import clases.ISistema;
import excepciones.UsuarioNoExisteException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ListarProveedores extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private Main menu;
	private JComboBox<DTProveedor> boxProveedor;
	private JTextField campoNick;
	private JTextField campoNombre;
	private JTextField campoEmail;
	private JTextField campoApellido;
	private JTextField campoLink;
	private JTextField campoFecha;
	private JTextField campoCompañia;
	private String foto;
	private JInternalFrame vistaFoto;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	
	public void limpiarBox(){
		this.boxProveedor.setSelectedIndex(-1);
		this.boxProveedor.setSelectedItem(null);
	}
	
	private void limpiarFormulario(){
		this.campoNick.setText("");
		this.campoNombre.setText("");
		this.campoEmail.setText("");
		this.campoApellido.setText("");
		this.campoLink.setText("");
		this.campoFecha.setText("");
		this.campoCompañia.setText("");
		this.foto = null;
	}
	
	public ListarProveedores(ISistema sistema, Main main) {
		setIconifiable(true);
		setTitle("Ver Información de un Proveedor");
		setClosable(true);
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		getContentPane().setBackground(new Color(240, 240, 240));
		setBounds(100, 100, 411, 450);
		getContentPane().setLayout(null);
		
		this.sistema = sistema;
		this.menu = main;
		
		JLabel labelProveedor = new JLabel("Ver información proveedor");
		labelProveedor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		labelProveedor.setBounds(36, 11, 312, 20);
		getContentPane().add(labelProveedor);
		
		JLabel labelNick = new JLabel("Nickname");
		labelNick.setBounds(47, 100, 65, 14);
		getContentPane().add(labelNick);
		
		JTextField campoNick = new JTextField();
		campoNick.setColumns(10);
		campoNick.setBounds(116, 97, 232, 20);
		campoNick.setEditable(false);
		getContentPane().add(campoNick);
		
		this.campoNick = campoNick;
		
		JLabel labelCorreo = new JLabel("Correo Electrónico");
		labelCorreo.setBounds(47, 144, 123, 14);
		getContentPane().add(labelCorreo);
		
		JTextField campoEmail = new JTextField();
		campoEmail.setColumns(10);
		campoEmail.setBounds(168, 141, 180, 20);
		campoEmail.setEditable(false);
		getContentPane().add(campoEmail);
		
		this.campoEmail = campoEmail;
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(47, 189, 48, 14);
		getContentPane().add(labelNombre);
		
		JTextField campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(116, 186, 232, 20);
		campoNombre.setEditable(false);
		getContentPane().add(campoNombre);
		
		this.campoNombre = campoNombre;
		
		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(47, 234, 59, 14);
		getContentPane().add(labelApellido);
		
		JTextField campoApellido = new JTextField();
		campoApellido.setColumns(10);
		campoApellido.setBounds(116, 231, 232, 20);
		campoApellido.setEditable(false);
		getContentPane().add(campoApellido);
		
		this.campoApellido = campoApellido;
		
		JTextField campoCompañia = new JTextField();
		campoCompañia.setColumns(10);
		campoCompañia.setBounds(192, 270, 156, 20);
		campoCompañia.setEditable(false);
		getContentPane().add(campoCompañia);
		
		this.campoCompañia = campoCompañia;
		
		JLabel labelCompañia = new JLabel("Nombre de la Compañía");
		labelCompañia.setBounds(47, 273, 145, 14);
		getContentPane().add(labelCompañia);
		
		JLabel labelLink = new JLabel("Link Web");
		labelLink.setBounds(47, 318, 65, 14);
		getContentPane().add(labelLink);
		
		JTextField campoLink = new JTextField();
		campoLink.setColumns(10);
		campoLink.setBounds(116, 315, 232, 20);
		campoLink.setEditable(false);
		getContentPane().add(campoLink);
		
		this.campoLink = campoLink;
		
		JLabel labelFecha = new JLabel("Fecha de nacimiento");
		labelFecha.setBounds(47, 355, 123, 14);
		getContentPane().add(labelFecha);
		
		JTextField campoFecha = new JTextField();
		campoFecha.setColumns(10);
		campoFecha.setBounds(170, 352, 79, 20);
		campoFecha.setEditable(false);
		getContentPane().add(campoFecha);

		this.campoFecha = campoFecha;
		
		this.boxProveedor = new JComboBox<DTProveedor>();
		boxProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				DTProveedor selectedItem = (DTProveedor) boxProveedor.getSelectedItem();
				if (selectedItem != null &&  boxProveedor.getSelectedItem() != null && boxProveedor.getSelectedIndex() != -1) {
					String nick = selectedItem.getNickname();
					try {
						sistema.elegirProveedor(nick);
						DTProveedor prov = sistema.verInformacionProveedor();
						campoNick.setText(prov.getNickname());
						campoNombre.setText(prov.getNombre());
						campoEmail.setText(prov.getEmail());
						campoApellido.setText(prov.getApellido());
						campoLink.setText(prov.getLink());
						campoFecha.setText(prov.getFechaNac().toString());
						campoCompañia.setText(prov.getNomCompania());
						foto = prov.getFoto();
					} catch (UsuarioNoExisteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		boxProveedor.setEditable(true);
		boxProveedor.setBounds(116, 53, 232, 20);
		getContentPane().add(boxProveedor);
		
		
		JButton botonImagen = new JButton("Ver imagen");
		botonImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarFotoEnInternalFrame(foto);
			}
		});
		botonImagen.setBounds(144, 386, 118, 23);
		getContentPane().add(botonImagen);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(47, 56, 65, 14);
		getContentPane().add(lblProveedor);
		
		this.addInternalFrameListener(new InternalFrameAdapter() {
	         @Override
	         public void internalFrameClosing(InternalFrameEvent e) {
	        	 limpiarFormulario();
	        	 limpiarBox();
	        	 dispose();
	         }
	    });
			
	}
	
	public List<DTProveedor> getProveedores(){
		
		this.sistema.crearCasos();
		
		if (this.sistema == null) {
			// tiro el error
			throw new NullPointerException ("Error: El sistema no existe."); // FALTA POPUP
		}
		List<DTProveedor> lista = null;
		
		lista = this.sistema.listarProveedores();
		
		return lista;
		
	}

	public void cargarProveedores() {
		
		List<DTProveedor> lista = null;
		
		lista = this.getProveedores();
		
		this.boxProveedor.removeAllItems();
		
		for (DTProveedor prov : lista) {
			this.boxProveedor.addItem(prov);
		}
		limpiarBox();
	}
	
	private void mostrarFotoEnInternalFrame(String imagen) {
			
		if (imagen != null && (! imagen.isBlank()) && ! (imagen.isEmpty())) {
	
	        // Cargar y agregar la imagen al panel
	        File archivoImagen = new File(imagen);
	        if (archivoImagen.exists()) {
	        	
	        	if (this.vistaFoto != null) {
	        		this.vistaFoto.dispose();
	        	}
	        	
	        	// Crear el JInternalFrame
				this.vistaFoto = new JInternalFrame("Flamin-Go", true, true, true, true);
				ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
				this.vistaFoto.setFrameIcon(new ImageIcon(img));
				this.vistaFoto.setSize(600, 400);
				this.vistaFoto.getContentPane().setLayout(new BorderLayout());
				
		        // Crear un JPanel para contener las imágenes
		        JPanel panelImagenes = new JPanel();
		        panelImagenes.setLayout(new FlowLayout());
	        	
	            ImageIcon icono = new ImageIcon(imagen);
	            JLabel etiquetaImagen = new JLabel(icono);
	            panelImagenes.add(etiquetaImagen);
	            
	            // Añadir el panel al JInternalFrame
		        JScrollPane scrollPane = new JScrollPane(panelImagenes);
		        this.vistaFoto.getContentPane().add(scrollPane, BorderLayout.CENTER);
	
		        // Añadir el JInternalFrame al JDesktopPane
		        menu.getMenuPrincipal().getContentPane().add(this.vistaFoto);
		        this.vistaFoto.setVisible(true);
	        } else {
	            JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + imagen, "Error", JOptionPane.ERROR_MESSAGE);
	        }
		} else {
	    	JOptionPane.showMessageDialog(null, "El proveedor actual no tiene foto de perfil", "Error", JOptionPane.ERROR_MESSAGE);
		}
    }
}
