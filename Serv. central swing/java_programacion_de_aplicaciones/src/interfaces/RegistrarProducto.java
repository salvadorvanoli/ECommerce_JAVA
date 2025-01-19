package interfaces;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import clases.Categoria;
import clases.DTProducto;
import clases.DTProveedor;
import clases.ISistema;
import clases.Proveedor;
import excepciones.CategoriaNoExisteException;
import excepciones.ProductoRepetidoException;
import excepciones.UsuarioNoExisteException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


@SuppressWarnings("unused")
public class RegistrarProducto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private Main menu;
	private JTextField campoNumRef;
	private JComboBox <DTProveedor> boxProveedor;
	private JTextField campoNombre;
	private JTextField campoEspecificacion;
	private JTextArea campoDescripcion;
	private JTextField campoPrecio;
	private JTextField campoTienda;
	private JTree treeCategorias;
	private List<Categoria> Categorias;
	private List<String> Imagenes;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	
	
	public List<Categoria> listaCategorias() {
		return this.Categorias;
	}
	
	public List<String> listaImagenes() {
		return this.Imagenes;
	}
	
	public void cargarImagen(String a){
		this.Imagenes.add(a);
	}
	
	// Funcion para generar la lista de especificaciones:

	public List<String> generarEspecificaciones(String textoEspecificaciones) {
	        // Expresión regular para dividir en saltos de línea
	        String[] parts = textoEspecificaciones.split("\n");

	        // Crear la lista y agregar las partes sin incluir los saltos de línea
	        List<String> lista = new ArrayList<>();
	        for (String especificacion : parts) {
	            especificacion = especificacion.trim();
	            if (!especificacion.isEmpty()) {  // Evita agregar valores vacíos
	                lista.add(especificacion);
	            }
	        }

	        return lista;
	}
	
	public void cargarJTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorías");
		
		for (Categoria cat : sistema.getCategorias().values()) {
			this.cargarCategoriaJTree(cat, root); // ROOT SERIA EL NODO RAIZ (PODEMOS PONERLE CATEGORIA NOMAS)
		}
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
        this.treeCategorias.setModel(treeModel);
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
	
	public void cargarProveedores() {
		List<DTProveedor> lista = null;
		
		try {
			lista = this.sistema.listarProveedores();
		} catch (NullPointerException e) {
			throw new NullPointerException (e.getMessage()); // FALTA POPUP DE ERROR
		}
		
		this.boxProveedor.removeAllItems();
		
		for (DTProveedor prod : lista) {
			this.boxProveedor.addItem(prod);
		}
		this.boxProveedor.setSelectedIndex(-1);
	}
	
	public void limpiarCampos() {
		this.boxProveedor.setSelectedIndex(-1);
		this.treeCategorias.setSelectionRow(-1);
		this.campoNombre.setText("");
		this.campoNumRef.setText("");
		this.campoPrecio.setText("");
		this.campoDescripcion.setText("");
		this.campoEspecificacion.setText("");
		this.Categorias = new ArrayList<>();
		this.Imagenes = new ArrayList<>();
		this.campoTienda.setText("");
	}
	
	private boolean camposCompletos() {
		return ! (this.campoNombre.getText().isBlank() || this.campoNombre.getText().isEmpty()) &&
				! (this.campoEspecificacion.getText().isBlank() || this.campoEspecificacion.getText().isEmpty()) &&
				! (this.campoPrecio.getText().isBlank() || this.campoPrecio.getText().isEmpty()) &&
				! (this.campoDescripcion.getText().isBlank() || this.campoDescripcion.getText().isEmpty()) &&
				! (this.campoNumRef.getText().isBlank() || this.campoNumRef.getText().isEmpty()) &&
				! (this.campoTienda.getText().isBlank() || this.campoTienda.getText().isEmpty());
	}
	
	private boolean checkNumReferencia() {
		try {
			int numReferencia = Integer.valueOf(this.campoNumRef.getText());
			return numReferencia > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean checkPrecio() {
		try {
			float precio = Float.valueOf(this.campoPrecio.getText());
			return precio > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean checkProveedor() {
		return this.boxProveedor.getSelectedIndex() == -1 || this.boxProveedor.getSelectedItem() == null;
	}
	
	private boolean checkCategorias() {
		return this.Categorias == null || this.Categorias.isEmpty();
	}
	

	private void camposValidos() {
		if (!this.camposCompletos()) {
			throw new IllegalStateException("Aún hay campos vacíos.");
		}
		if (!this.checkNumReferencia()) {
			throw new NumberFormatException("El número de referencia debe ser un valor entero positivo.");
		}
		if (!this.checkPrecio()) {
			throw new NumberFormatException("El precio debe ser un valor entero (o decimal) mayor que 0.");
		}
		if (checkProveedor()) {
			throw new IllegalStateException("No se eligió a ningún proveedor.");
		}
		if(checkCategorias()) {
			throw new IllegalStateException("No se eligió a ninguna categoría.");
		}
	}
	
	
	public void mostrarInformacion() {
	    JOptionPane.showMessageDialog(
	        this,                          // 'this' se refiere al componente actual, usa null si no tienes un componente padre específico
	        "Todo ha salido bien.",        // El mensaje de información
	        "Éxito",                       // El título de la ventana
	        JOptionPane.INFORMATION_MESSAGE // El tipo de mensaje (en este caso, un mensaje de información)
	    );
	}
		
	public RegistrarProducto(ISistema sistema, Main menu) {
		setIconifiable(true);
		this.sistema = sistema;
		getContentPane().setBackground(new Color(240, 240, 240));
		setClosable(true);
		setBackground(new Color(255, 192, 203));
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		setTitle("Registrar Producto");
		setBounds(100, 100, 533, 368);
		getContentPane().setLayout(null);
		
		JTextField campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(42, 55, 86, 20);
		getContentPane().add(campoNombre);
		
		this.campoNombre = campoNombre;
		
		JLabel TextoNombre = new JLabel("* Nombre ");
		TextoNombre.setBounds(42, 36, 86, 14);
		getContentPane().add(TextoNombre);
	
		JLabel TextoEspecificacion = new JLabel("* Especificación");
		TextoEspecificacion.setBounds(172, 36, 109, 14);
		getContentPane().add(TextoEspecificacion);
		
		JTextField campoEspecificacion = new JTextField();
		campoEspecificacion.setColumns(10);
		campoEspecificacion.setBounds(172, 55, 86, 20);
		getContentPane().add(campoEspecificacion);
		
		this.campoEspecificacion = campoEspecificacion;
		
		JTextArea campoDescripcion = new JTextArea();
		campoDescripcion.setBounds(42, 114, 216, 77);
		getContentPane().add(campoDescripcion);
		
		this.campoDescripcion = campoDescripcion;
		
		JLabel TextoDescripcion = new JLabel("* Descripción");
		TextoDescripcion.setBounds(42, 86, 148, 14);
		getContentPane().add(TextoDescripcion);
		
		JLabel TextoPrecio = new JLabel("* Precio ");
		TextoPrecio.setBounds(42, 202, 60, 14);
		getContentPane().add(TextoPrecio);
		
		JTextField campoPrecio = new JTextField();
		campoPrecio.setColumns(10);
		campoPrecio.setBounds(42, 227, 86, 20);
		getContentPane().add(campoPrecio);
		
		this.campoPrecio = campoPrecio;
		
		JLabel TextoImagen = new JLabel("Imagen");
		TextoImagen.setBounds(151, 202, 78, 14);
		getContentPane().add(TextoImagen);
		
		JButton BotonSeleccionImagen = new JButton("Seleccionar");
		BotonSeleccionImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// Crear un JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true); // Permitir seleccionar múltiples archivos
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo archivos, no directorios

                // Filtro para permitir solo imágenes
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Archivos de Imagen (JPG, PNG)", "jpg", "jpeg", "png");
                fileChooser.setFileFilter(imageFilter); // Establecer el filtro
                fileChooser.setAcceptAllFileFilterUsed(false);

                // Mostrar el diálogo de selección de archivos
                int result = fileChooser.showOpenDialog(menu.getMenuPrincipal());
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtener los archivos seleccionados
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    
                    // String textImagenesAnt = textAreaImagenes.getText();
                    // textAreaImagenes.setText("");

                    List<String> rutasImagenes = new ArrayList<>();
                    // Mostrar los archivos seleccionados en la consola
                    for (File file : selectedFiles) {
                    	if (imageFilter.accept(file)) {
                    		rutasImagenes.add(file.getAbsolutePath());
                    		//textAreaImagenes.setText(textAreaImagenes.getText() + file.getAbsolutePath() + System.lineSeparator());
                    	} else {
                    		rutasImagenes.clear();
                    		//textAreaImagenes.setText(textImagenesAnt);
        		            JOptionPane.showMessageDialog(null, "Un archivo elegido no coincide con el tipo aceptado (jpg, jpeg o png).", "Error", JOptionPane.ERROR_MESSAGE);
        		            break;
                    	}
                    }
                    if (!(rutasImagenes.isEmpty())){
                    	Imagenes = rutasImagenes;
                    	// textoImagenesOriginal = textAreaImagenes.getText();
                    } 
                }
                
            }
        });
		
		BotonSeleccionImagen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		BotonSeleccionImagen.setBounds(151, 226, 107, 23);
		getContentPane().add(BotonSeleccionImagen);
		
		JLabel TextoTitulo = new JLabel("Registrar Producto");
		TextoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		TextoTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TextoTitulo.setBounds(0, 0, 498, 29);
		getContentPane().add(TextoTitulo);
		
		JLabel TextoProveedor = new JLabel("* Seleccione un proveedor:");
		TextoProveedor.setBounds(292, 202, 183, 14);
		getContentPane().add(TextoProveedor);
		
		JLabel TextoCategoria = new JLabel("*Selecciona una categoria");
		TextoCategoria.setBounds(292, 36, 183, 14);
		getContentPane().add(TextoCategoria);

		JScrollPane scrollLineasList = new JScrollPane();
		scrollLineasList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollLineasList.setBounds(292, 56, 193, 135);
		getContentPane().add(scrollLineasList);
		
		JTree treeCategorias_1 = new JTree();
		scrollLineasList.setViewportView(treeCategorias_1);
		treeCategorias_1.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION); // Configurar el TreeSelectionModel para selección múltiple no contigua
		treeCategorias_1.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				TreePath[] selectedPaths = treeCategorias_1.getSelectionPaths();
				
				if (selectedPaths != null) {
					Categorias.clear();
	                for (TreePath item : selectedPaths) {
	                	DefaultMutableTreeNode node = (DefaultMutableTreeNode) item.getLastPathComponent();
	                	Object userObject = node.getUserObject();
	                	if (userObject instanceof Categoria) {
	                		Categoria cat = (Categoria) userObject;
	                		Categorias.add(cat);
	                	}
	                }
            	}
				
			
			}
		});
		
		this.treeCategorias = treeCategorias_1;
		
		cargarJTree();
		
		JLabel TextoNum = new JLabel("* Numero de Referencia");
		TextoNum.setBounds(42, 258, 113, 14);
		getContentPane().add(TextoNum);
		
		JTextField campoNumRef = new JTextField();
		campoNumRef.setColumns(10);
		campoNumRef.setBounds(42, 283, 113, 20);
		getContentPane().add(campoNumRef);
		
		this.campoNumRef = campoNumRef;
		
		this.addInternalFrameListener(new InternalFrameAdapter() {
	         @Override
	         public void internalFrameClosing(InternalFrameEvent e) {
	        	 limpiarCampos();
	        	 dispose();
	         }
	    });
	
		JComboBox <DTProveedor> boxProveedor = new JComboBox<DTProveedor>(sistema.listarProveedores().toArray(new DTProveedor[0]));
		boxProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTProveedor selectedItem = (DTProveedor) boxProveedor.getSelectedItem();
				if(selectedItem != null) {
					String nick = selectedItem.getNickname();
					try {
						sistema.elegirProveedor(nick);
					} catch (UsuarioNoExisteException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		boxProveedor.setEditable(true);
		boxProveedor.setBounds(291, 227, 194, 20);
		boxProveedor.setSelectedIndex(-1);
		getContentPane().add(boxProveedor);
		
		this.boxProveedor = boxProveedor;
		
		JLabel TiendaTitulo = new JLabel("* Nombre de Tienda");
		TiendaTitulo.setBounds(168, 258, 113, 14);
		getContentPane().add(TiendaTitulo);
		
		JTextField campoTienda = new JTextField();
		campoTienda.setColumns(10);
		campoTienda.setBounds(168, 283, 113, 20);
		getContentPane().add(campoTienda);
		
		this.campoTienda = campoTienda;
			
	
		JButton BotonRegistrar = new JButton("Registrar");
		BotonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String titulo = null;
				String descrip = null;
				List<String> especificacion = null;				
				int numReferencia = -1;
				float precio = -1;
				List<Categoria> categoria = null;
				List<String> imagenes = null;
				String nombreTienda = null;
				
				
				try {
					camposValidos();	
					titulo = campoNombre.getText();
					descrip = campoDescripcion.getText();
					especificacion = generarEspecificaciones(campoEspecificacion.getText());
					numReferencia = Integer.valueOf(campoNumRef.getText().trim());
					precio = Float.valueOf(campoPrecio.getText().trim());
					categoria = listaCategorias();
					imagenes = listaImagenes();
					nombreTienda = campoTienda.getText();
					//List<String> rutasActualizadas = guardarImagenesEnCarpeta(imagenes);
					DTProveedor proveedorSeleccionado = (DTProveedor) boxProveedor.getSelectedItem();
// nombreProducto,  descripcion, especificacion, numReferencia, precio, imagenes, categorias, proveedor, nombreTienda
					sistema.registrarProducto(proveedorSeleccionado.getNickname(), titulo,  numReferencia,  descrip,  especificacion,  precio,  categoria,  imagenes, null, nombreTienda);
					mostrarInformacion();
					limpiarCampos();
				} catch(Exception exc) {
					JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		BotonRegistrar.setBounds(381, 304, 104, 23);
		getContentPane().add(BotonRegistrar);
		
		
	}
}
