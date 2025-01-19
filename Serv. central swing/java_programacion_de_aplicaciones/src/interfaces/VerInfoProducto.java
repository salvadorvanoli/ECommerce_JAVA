package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.SwingConstants;

import clases.Categoria;
import clases.DTProducto;
import clases.ISistema;
import excepciones.CategoriaNoExisteException;
import excepciones.ProductoNoExisteException;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class VerInfoProducto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTree treeCat;
	private JComboBox<DTProducto> comboBoxProd;
	private JTextArea textAreaEsp;
	private ISistema sistema;
	private JTextField textNom;
	private JTextField textDesc;
	private JTextField textPrec;
	private JTextField textRef;
	private JTextField textCat;
	private JTextField textProv;
	private JButton ButtonImg;
	private Main main;
	private JInternalFrame vistaImagenes;
	private JTextField textTienda;
	private JTextField textCantComp;
	private JTextField textEstrellas;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	public VerInfoProducto(ISistema sistema, Main main) {
		setIconifiable(true);
		setResizable(true);
		this.sistema = sistema;
		this.main = main;
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		setTitle("Ver Información de un Producto");
		setClosable(true);
		setBounds(100, 100, 661, 600);
		getContentPane().setLayout(null);
		
		JLabel LabelSelectCat = new JLabel("Seleccione una categoría del sistema y luego \"Confirmar\"");
		LabelSelectCat.setHorizontalAlignment(SwingConstants.CENTER);
		LabelSelectCat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelSelectCat.setBounds(0, 11, 573, 27);
		getContentPane().add(LabelSelectCat);
		
		JLabel LabelProd = new JLabel("Seleccione un producto");
		LabelProd.setHorizontalAlignment(SwingConstants.CENTER);
		LabelProd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LabelProd.setBounds(0, 203, 645, 14);
		getContentPane().add(LabelProd);
		
		this.comboBoxProd = new JComboBox<DTProducto>();
		comboBoxProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBoxProd.getSelectedItem() == null || comboBoxProd.getSelectedIndex() == -1) {
                    ButtonImg.setEnabled(false);
                    return;
                }
				
				DTProducto producto = (DTProducto) comboBoxProd.getSelectedItem();
				String nombreProducto = producto.getNombre();

				try {
					sistema.elegirProducto(nombreProducto);
					String nomDet = sistema.verInformacionProducto().getNombre().toString();
					String descDet = sistema.verInformacionProducto().getDescripcion().toString();
					Float prec = sistema.verInformacionProducto().getPrecio();
					String precDet = Float.toString(prec);
					
					String nickProvDet = sistema.verInformacionProducto().getProveedor().getNickname();
					String mailProvDet = sistema.verInformacionProducto().getProveedor().getEmail();
					String provDet = nickProvDet + " - " + mailProvDet;
					String catDet = sistema.verInformacionProducto().getCategorias().toString();

					Integer num = sistema.verInformacionProducto().getNumReferencia();

					String numDet = Integer.toString(num);
					String estrellas = Integer.toString(sistema.verInformacionProducto().getEstrellas());
					String cantCompras = Integer.toString(sistema.verInformacionProducto().getCantCompras());
					String tienda = sistema.verInformacionProducto().getNombreTienda();
					String espec = generarTextoEspecificaciones(sistema.verInformacionProducto().getEspecificaciones());
					
					textNom.setText(nomDet);
					textDesc.setText(descDet);
					textPrec.setText(precDet);
					textProv.setText(provDet);
					textCat.setText(catDet);
					textRef.setText(numDet);
					textEstrellas.setText(estrellas);
					textCantComp.setText(cantCompras);
					textTienda.setText(tienda);
					textAreaEsp.setText(espec);
					ButtonImg.setEnabled(true);
				} catch (ProductoNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBoxProd.setBounds(86, 228, 473, 22);
		getContentPane().add(comboBoxProd);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(46, 49, 169, 133);
		getContentPane().add(scrollPane);
		
		
		treeCat = new JTree();
		scrollPane.setViewportView(treeCat);
		cargarJTree();
		
		JButton ButtonConfCat = new JButton("Confirmar\r\n");
		ButtonConfCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeCat.getLastSelectedPathComponent();
                

                if (selectedNode != null && !selectedNode.toString().equals("Categorías")) {
                    String nombreCat = selectedNode.toString();
                    try {
                        sistema.elegirCategoria(nombreCat);
                        cargarProductos();
                    } catch (CategoriaNoExisteException e1) {
                        // MANEJAR ERROR
                    }
                }
			}
		});
		
		ButtonConfCat.setBounds(225, 97, 115, 36);
		getContentPane().add(ButtonConfCat);
		
		JLabel LabelNom = new JLabel("Nombre:");
		LabelNom.setBounds(43, 301, 67, 14);
		getContentPane().add(LabelNom);
		
		JTextField textNom = new JTextField();
		textNom.setEditable(false);
		textNom.setBounds(109, 296, 231, 20);
		getContentPane().add(textNom);
		textNom.setColumns(10);
		this.textNom = textNom;
		
		JLabel labelDesc = new JLabel("Descripción:");
		labelDesc.setBounds(43, 329, 76, 14);
		getContentPane().add(labelDesc);
		
		JTextField textDesc = new JTextField();
		textDesc.setEditable(false);
		textDesc.setColumns(10);
		textDesc.setBounds(118, 326, 222, 42);
		getContentPane().add(textDesc);
		this.textDesc = textDesc;
		
		JLabel labelPrec = new JLabel("Precio:");
		labelPrec.setBounds(43, 382, 67, 14);
		getContentPane().add(labelPrec);
		
		JTextField textPrec = new JTextField();
		textPrec.setEditable(false);
		textPrec.setColumns(10);
		textPrec.setBounds(118, 379, 222, 20);
		getContentPane().add(textPrec);
		this.textPrec = textPrec;
		
		
		JLabel labelRef = new JLabel("N° de Referencia:");
		labelRef.setBounds(43, 413, 115, 14);
		getContentPane().add(labelRef);
		
		JTextField textRef = new JTextField();
		textRef.setEditable(false);
		textRef.setColumns(10);
		textRef.setBounds(152, 410, 188, 20);
		getContentPane().add(textRef);
		this.textRef = textRef;
		
		JLabel labelCat = new JLabel("Categorías:");
		labelCat.setBounds(43, 443, 88, 14);
		getContentPane().add(labelCat);
		
		JTextField textCat = new JTextField();
		textCat.setEditable(false);
		textCat.setColumns(10);
		textCat.setBounds(118, 440, 222, 42);
		getContentPane().add(textCat);
		this.textCat = textCat;
		
		JTextField textProv = new JTextField();
		textProv.setEditable(false);
		textProv.setColumns(10);
		textProv.setBounds(118, 493, 222, 20);
		getContentPane().add(textProv);
		this.textProv = textProv;
		
		JLabel labelProv = new JLabel("Proveedor:");
		labelProv.setBounds(43, 496, 88, 14);
		getContentPane().add(labelProv);
		
		ButtonImg = new JButton("Ver Imágenes");
		ButtonImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> imagenes = sistema.verInformacionProducto().getImagenes();
				if (imagenes == null) {
					JOptionPane.showMessageDialog(null, "Este Producto no tiene imágenes", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
				mostrarImagenesEnInternalFrame(imagenes);		
				}
									

			}
		});
		
		ButtonImg.setBounds(197, 536, 143, 23);
		getContentPane().add(ButtonImg);
		ButtonImg.setEnabled(false);
		
		JTextArea textAreaEsp = new JTextArea();
		textAreaEsp.setBounds(445, 324, 157, 97);
		getContentPane().add(textAreaEsp);
		this.textAreaEsp = textAreaEsp;
		
		JLabel LabelEsp = new JLabel("Especificaciones:");
		LabelEsp.setBounds(391, 301, 157, 14);
		getContentPane().add(LabelEsp);
		
		JLabel LabelTienda = new JLabel("Tienda:");
		LabelTienda.setBounds(391, 496, 53, 14);
		getContentPane().add(LabelTienda);
		
		textTienda = new JTextField();
		textTienda.setEditable(false);
		textTienda.setColumns(10);
		textTienda.setBounds(443, 493, 157, 20);
		getContentPane().add(textTienda);
		
		JLabel LabelCantCom = new JLabel("Cantidad de compras:");
		LabelCantCom.setBounds(393, 465, 113, 14);
		getContentPane().add(LabelCantCom);
		
		textCantComp = new JTextField();
		textCantComp.setEditable(false);
		textCantComp.setColumns(10);
		textCantComp.setBounds(514, 462, 88, 20);
		getContentPane().add(textCantComp);
		
		JLabel LabelEstrellas = new JLabel("Estrellas:");
		LabelEstrellas.setBounds(393, 434, 53, 14);
		getContentPane().add(LabelEstrellas);
		
		textEstrellas = new JTextField();
		textEstrellas.setEditable(false);
		textEstrellas.setColumns(10);
		textEstrellas.setBounds(445, 431, 157, 20);
		getContentPane().add(textEstrellas);
		
	}
	
	
	public void limpiarCampos() {
		// Limpiar el JComboBox
	    comboBoxProd.removeAllItems();
    	textNom.setText("[Nombre del Producto]");
    	textDesc.setText("[Descripción del Producto]");
    	textPrec.setText("[Precio del Producto]");
    	textProv.setText("[Proveedor del Producto]");
    	textCat.setText("[Categorías del Producto]");
    	textRef.setText("[N° de Referencia del Producto]");
	}
	
	public void cargarJTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorías");
		
		for (Categoria cat : sistema.getCategorias().values()) {
			this.cargarCategoriaJTree(cat, root); // ROOT SERIA EL NODO RAIZ (PODEMOS PONERLE CATEGORIA NOMAS)
		}
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
        this.treeCat.setModel(treeModel);
	}
	
	public void cargarCategoriaJTree(Categoria cat, DefaultMutableTreeNode nodo) {
		DefaultMutableTreeNode newNodo = new DefaultMutableTreeNode(cat);
		if (!(cat.getHijos().isEmpty())) {
			for (Categoria hijo : cat.getHijos()) {
				cargarCategoriaJTree(hijo, newNodo);
			}
		}
		nodo.add(newNodo);
	}
	
	public void cargarProductos() {
		List<DTProducto> lista = null;
		
		lista = this.sistema.listarProductos();
		
		this.comboBoxProd.removeAllItems();
		
		for (DTProducto prod : lista) {
			this.comboBoxProd.addItem(prod);
		}
		this.comboBoxProd.setSelectedIndex(-1);
	}
	
	private void mostrarImagenesEnInternalFrame(List<String> rutasImagenes) {
		
		if (rutasImagenes != null && ! (rutasImagenes.isEmpty())) {

	        boolean hayImagenes = false;
	        
	        // Crear un JPanel para contener las imágenes
	        JPanel panelImagenes = new JPanel();
	        panelImagenes.setLayout(new FlowLayout());
	
	        // Cargar y agregar las imágenes al panel
	        for (String ruta : rutasImagenes) {
	            File archivoImagen = new File(ruta);
	            if (archivoImagen.exists()) {
	                ImageIcon icono = new ImageIcon(ruta);
	                JLabel etiquetaImagen = new JLabel(icono);
	                panelImagenes.add(etiquetaImagen);
	                hayImagenes = true;
	            } else {
	                JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        
	        if (hayImagenes) {
	        	// Crear el JInternalFrame
	        	
	        	if (this.vistaImagenes != null) {
	        		this.vistaImagenes.dispose();
	        	}
	        	
	        	this.vistaImagenes = new JInternalFrame("Galería de Imágenes", true, true, true, true);
	        	ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		        this.vistaImagenes.setFrameIcon(new ImageIcon(img));
	        	this.vistaImagenes.setSize(600, 400);
	        	this.vistaImagenes.getContentPane().setLayout(new BorderLayout());
	            
	            // Añadir el panel al JInternalFrame
	            JScrollPane scrollPane = new JScrollPane(panelImagenes);
	            this.vistaImagenes.getContentPane().add(scrollPane, BorderLayout.CENTER);
	
	            // Añadir el JInternalFrame al JDesktopPane
	            this.main.getMenuPrincipal().getContentPane().add(this.vistaImagenes);
	            this.vistaImagenes.setVisible(true);
	            
	        } else {
	        	// MOSTRAR ERROR
	        }
        
		} else {
			// MOSTRAR ERROR
		}
    }
	
	public static String generarTextoEspecificaciones(List<String> listaEspecificaciones) {
        StringBuilder textoEspecificaciones = new StringBuilder();
        
        for (String especificacion : listaEspecificaciones) {
            if (!especificacion.isEmpty()) { // Evita agregar líneas vacías
                textoEspecificaciones.append(especificacion).append("\n");
            }
        }
        
        return textoEspecificaciones.toString().trim(); // Trim para evitar un salto de línea final
    }
}


