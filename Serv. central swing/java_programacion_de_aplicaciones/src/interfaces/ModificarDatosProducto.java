package interfaces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import clases.ISistema;
import excepciones.ProductoNoExisteException;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import excepciones.CategoriaNoExisteException;

//Importamos clases del package "clases"

import clases.DTProducto;
import clases.DTProductoDetallado;
import clases.Categoria;

import javax.swing.ScrollPaneConstants;

public class ModificarDatosProducto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private ISistema sistema;
	private JTree JTreeSeleccionCategoriaPadre;
	private JComboBox<DTProducto> seleccionProducto;
	private JTextField textFieldNombre;
	private JTextField textFieldNumReferencia;
	private JTextField textFieldPrecio;
	private JTextField textFieldNombreTienda;
	private JTextArea textAreaDescripcion;
	private JTextArea textAreaEspecificacion;
	private JTextArea textAreaCategorias;
	private JButton btnNuevasImagenes;
	private JButton btnNuevasCategorias;
	private JButton btnAceptar;
	//private String textoCategoriasOriginal;
	private JButton btnVerImagenes;
	private JInternalFrame vistaImagenes;
	
	private List<Categoria> nuevasCategorias;
	private List<String> nuevasImagenes;
	private List<String> imagenesActuales;
	
	public void setNuevasCategorias(List<Categoria> nuevasCategorias) {
		this.nuevasCategorias = nuevasCategorias;
	}
	
	public void setTextAreaCategorias(String text) {
		this.textAreaCategorias.setText(text);
	}
	
	public String getTextAreaCategorias() {
		return this.textAreaCategorias.getText();
	}
	
	public void alternarCampos(boolean habilitar) {
		this.textFieldNombre.setEnabled(habilitar);
		this.textFieldNumReferencia.setEnabled(habilitar);
		this.textFieldPrecio.setEnabled(habilitar);
		this.textFieldNombreTienda.setEnabled(habilitar);
		this.textAreaDescripcion.setEnabled(habilitar);
		this.textAreaEspecificacion.setEnabled(habilitar);
		this.textAreaCategorias.setEnabled(habilitar);
		this.btnNuevasImagenes.setEnabled(habilitar);
		this.btnNuevasCategorias.setEnabled(habilitar);
		this.btnAceptar.setEnabled(habilitar);
		this.btnVerImagenes.setEnabled(habilitar);
	}
	
	public void limpiarCampos() {
		this.JTreeSeleccionCategoriaPadre.setSelectionRow(-1);
		this.seleccionProducto.setSelectedIndex(-1);
		this.textFieldNombre.setText("");
		this.textFieldNumReferencia.setText("");
		this.textFieldPrecio.setText("");
		this.textFieldNombreTienda.setText("");
		this.textAreaDescripcion.setText("");
		this.textAreaEspecificacion.setText("");
		this.textAreaCategorias.setText("");
		this.nuevasCategorias = new ArrayList<>();
		this.nuevasImagenes = new ArrayList<>();
		this.imagenesActuales = new ArrayList<>();
	}
	
	public void limpiarListaProductos() {
		this.seleccionProducto.removeAllItems();
	}
	
	private boolean camposCompletos() {
		return ! (this.textFieldNombre.getText().isBlank() || this.textFieldNombre.getText().isEmpty()) &&
				! (this.textFieldNumReferencia.getText().isBlank() || this.textFieldNumReferencia.getText().isEmpty()) &&
				! (this.textFieldPrecio.getText().isBlank() || this.textFieldPrecio.getText().isEmpty()) &&
				! (this.textFieldNombreTienda.getText().isBlank() || this.textFieldNombreTienda.getText().isEmpty()) &&
				! (this.textAreaDescripcion.getText().isBlank() || this.textAreaDescripcion.getText().isEmpty()) &&
				! (this.textAreaEspecificacion.getText().isBlank() || this.textAreaEspecificacion.getText().isEmpty());
	}
	
	private boolean checkNumReferencia() {
		try {
			int numReferencia = Integer.valueOf(this.textFieldNumReferencia.getText());
			return numReferencia > 0;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean checkPrecio() {
		try {
			float precio = Float.valueOf(this.textFieldPrecio.getText());
			return precio > 0;
		} catch (NumberFormatException e) {
			return false;
		}
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
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	public ModificarDatosProducto(ISistema sistema, Main menu) {
		setIconifiable(true);
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		setTitle("Modificar Datos de un Producto");
		setClosable(true);
		setBounds(100, 100, 638, 725);
		getContentPane().setLayout(null);
		
		this.sistema = sistema;
		
		JLabel lblTitulo = new JLabel("Modificar datos de un Producto");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(10, 11, 537, 29);
		getContentPane().add(lblTitulo);
		
		JLabel labelSeleccionCategoria = new JLabel("Selecciona una de las categorías listadas debajo *");
		labelSeleccionCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		labelSeleccionCategoria.setBounds(20, 54, 304, 28);
		getContentPane().add(labelSeleccionCategoria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(40, 93, 252, 166);
		getContentPane().add(scrollPane);
		
		JTree treeCategorias = new JTree();
		scrollPane.setViewportView(treeCategorias);
		treeCategorias.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (treeCategorias.getSelectionRows().length > 0 && treeCategorias.getSelectionRows()[0] > 0) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeCategorias.getLastSelectedPathComponent(); // Consigo el elemento del JTree seleccionado por el usuario
	                if (selectedNode == null) {
	                	JOptionPane.showMessageDialog(null, "Ninguna categoría fue seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
	                } else {
	                	Object node = selectedNode.getUserObject();
	                	if (node instanceof Categoria) {
			                try {
			                	sistema.elegirCategoria(selectedNode.toString());
			                	cargarProductos();
			                	seleccionProducto.setSelectedIndex(-1);
			                } catch (CategoriaNoExisteException exc) {
			                	JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                }
	                	} else {
	                		//bt
	                	}
	                }
				}
			}
		});
		
		this.JTreeSeleccionCategoriaPadre = treeCategorias;
		
		cargarJTree();
		
		JLabel labelSeleccionProducto = new JLabel("Selecciona uno de los productos de la lista *");
		labelSeleccionProducto.setHorizontalAlignment(SwingConstants.CENTER);
		labelSeleccionProducto.setBounds(303, 116, 304, 28);
		getContentPane().add(labelSeleccionProducto);
		
		JComboBox<DTProducto> seleccionProducto = new JComboBox<DTProducto>();
		seleccionProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DTProducto seleccionado = (DTProducto) seleccionProducto.getSelectedItem(); // TENEMOS QUE INCLUIR EL NUMERO DE REFERENCIA EN DTPRODUCTO
                if (seleccionado != null) {
                	alternarCampos(true);
                    String nombre = seleccionado.getNombre();
                    try {
                        sistema.elegirProducto(nombre);
                        DTProductoDetallado prodDetallado = sistema.verInformacionProducto();
                        textFieldNombre.setText(prodDetallado.getNombre());
                        textFieldNumReferencia.setText(String.valueOf(prodDetallado.getNumReferencia()));
                        textFieldPrecio.setText(String.valueOf(prodDetallado.getPrecio()));
                        textFieldNombreTienda.setText(prodDetallado.getNombreTienda());
                        textAreaDescripcion.setText(prodDetallado.getDescripcion());
                        String especificaciones = "";
                        for (int i = 0; i < prodDetallado.getEspecificaciones().size(); i++) {
                        	String especificacion = prodDetallado.getEspecificaciones().get(i);
                        	if (i < prodDetallado.getEspecificaciones().size() - 1) {
                        		especificaciones += especificacion + "\n";
                        	} else {
                        		especificaciones += especificacion;
                        	}
                        }
                        textAreaEspecificacion.setText(especificaciones);
                        textAreaCategorias.setText("");
                        for (String cat : prodDetallado.getCategorias()) {
                        	textAreaCategorias.setText(textAreaCategorias.getText() + cat + System.lineSeparator());
                        }
                        imagenesActuales = prodDetallado.getImagenes();
                        //textoCategoriasOriginal = textAreaCategorias.getText();
                    } catch (ProductoNoExisteException exc) {
                    	JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                	limpiarCampos();
                	alternarCampos(false);
                }
			}
		});
		seleccionProducto.setBounds(315, 167, 283, 28);
		getContentPane().add(seleccionProducto);
		
		this.seleccionProducto = seleccionProducto;
		
		JLabel labelDatosAModificar = new JLabel("A continuación, modifica los datos a tu elección");
		labelDatosAModificar.setHorizontalAlignment(SwingConstants.CENTER);
		labelDatosAModificar.setBounds(10, 280, 600, 28);
		getContentPane().add(labelDatosAModificar);
		
		JLabel labelNuevoNombreProducto = new JLabel("Nombre");
		labelNuevoNombreProducto.setBounds(40, 319, 87, 28);
		getContentPane().add(labelNuevoNombreProducto);
		
		JTextField textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(138, 323, 129, 20);
		getContentPane().add(textFieldNombre);
		
		this.textFieldNombre = textFieldNombre;
		
		JLabel labelNuevoNumReferenciaProducto = new JLabel("Num. Referencia");
		labelNuevoNumReferenciaProducto.setBounds(40, 358, 100, 28);
		getContentPane().add(labelNuevoNumReferenciaProducto);
		
		JTextField textFieldNumReferencia = new JTextField();
		textFieldNumReferencia.setColumns(10);
		textFieldNumReferencia.setBounds(138, 362, 129, 20);
		getContentPane().add(textFieldNumReferencia);
		
		this.textFieldNumReferencia = textFieldNumReferencia;
		
		JLabel labelNuevoPrecioProducto = new JLabel("Precio");
		labelNuevoPrecioProducto.setBounds(40, 397, 87, 28);
		getContentPane().add(labelNuevoPrecioProducto);
		
		JTextField textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(138, 401, 129, 20);
		getContentPane().add(textFieldPrecio);
		
		this.textFieldPrecio = textFieldPrecio;
		
		JLabel labelNuevoNombreTienda = new JLabel("Nombre Tienda");
		labelNuevoNombreTienda.setBounds(40, 434, 87, 28);
		getContentPane().add(labelNuevoNombreTienda);
		
		textFieldNombreTienda = new JTextField();
		textFieldNombreTienda.setEnabled(false);
		textFieldNombreTienda.setColumns(10);
		textFieldNombreTienda.setBounds(138, 438, 129, 20);
		getContentPane().add(textFieldNombreTienda);
		
		JLabel labelNuevaDescripcionProducto = new JLabel("Descripción");
		labelNuevaDescripcionProducto.setBounds(301, 319, 100, 28);
		getContentPane().add(labelNuevaDescripcionProducto);
		
		JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setColumns(10);
		textAreaDescripcion.setBounds(388, 321, 189, 62);
		getContentPane().add(textAreaDescripcion);
		
		this.textAreaDescripcion = textAreaDescripcion;
		
		JLabel labelNuevaEspecificacionProducto = new JLabel("Especificación");
		labelNuevaEspecificacionProducto.setBounds(301, 397, 100, 28);
		getContentPane().add(labelNuevaEspecificacionProducto);
		
		JTextArea textAreaEspecificacion = new JTextArea();
		textAreaEspecificacion.setColumns(10);
		textAreaEspecificacion.setBounds(388, 399, 189, 63);
		getContentPane().add(textAreaEspecificacion);
		
		this.textAreaEspecificacion = textAreaEspecificacion;
		
		JButton btnNuevasCategorias = new JButton("Elegir nuevas Categorías");
		btnNuevasCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.getModificarCategoriasProductoInternalFrame().cargarJTree();
				menu.getMenuPrincipal().getContentPane().add(menu.getModificarCategoriasProductoInternalFrame());
				menu.getModificarCategoriasProductoInternalFrame().setVisible(true);
				menu.getModificarCategoriasProductoInternalFrame().setLocation(0, 0);  // Ajustar la posición del InternalFrame
				menu.getMenuPrincipal().revalidate();
				menu.getMenuPrincipal().repaint();
			}
		});
		btnNuevasCategorias.setBounds(40, 507, 189, 28);
		getContentPane().add(btnNuevasCategorias);
		
		this.btnNuevasCategorias = btnNuevasCategorias;
		
		JButton btnNuevasImagenes = new JButton("Elegir nuevas Imágenes");
		btnNuevasImagenes.addActionListener(new ActionListener() {
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
                    

                    List<String> rutasImagenes = new ArrayList<>();
                    // Mostrar los archivos seleccionados en la consola
                    for (File file : selectedFiles) {
                    	if (imageFilter.accept(file)) {
                    		rutasImagenes.add(file.getAbsolutePath());

                    	} else {
                    		rutasImagenes.clear();
        		            JOptionPane.showMessageDialog(null, "Un archivo elegido no coincide con el tipo aceptado (jpg, jpeg o png).", "Error", JOptionPane.ERROR_MESSAGE);
        		            break;
                    	}
                    }
                    if (!(rutasImagenes.isEmpty())){
                    	nuevasImagenes = rutasImagenes;

                    } 
                } 

				
			}
		});
		btnNuevasImagenes.setBounds(388, 507, 189, 28);
		getContentPane().add(btnNuevasImagenes);
		
		this.btnNuevasImagenes = btnNuevasImagenes;
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					camposValidos();
					String nombreProd = textFieldNombre.getText().trim();
					int numReferencia = Integer.valueOf(textFieldNumReferencia.getText().trim());
					float precio = Float.valueOf(textFieldPrecio.getText().trim());
					String descripcion = textAreaDescripcion.getText().trim();
					String especificacion = textAreaEspecificacion.getText().trim();
				    List<String> especificaciones = ModificarDatosProducto.this.generarEspecificaciones(especificacion);
					String nomTienda = textFieldNombreTienda.getText().trim();
					// sistema.agregarCategoriasAProducto(nuevasCategorias);
					// boolean sePuedenModificarCategorias = (nuevasCategorias != null && ! (nuevasCategorias.isEmpty()));
					// sistema.quitarProductoDeCategorias(sePuedenModificarCategorias);
					// sistema.agregarCategoriasAProducto(nuevasCategorias);
					// sistema.agregarProductoACategorias(nuevasCategorias);
					// List<String> rutasOriginales = guardarImagenesEnCarpeta(imagenesActuales, nuevasImagenes);
					sistema.modificarDatosProducto(nombreProd, numReferencia, descripcion, precio, especificaciones, nomTienda, nuevasCategorias, nuevasImagenes);
					// sistema.modificarImagenesProducto(rutasActualizadas);
			        JOptionPane.showMessageDialog(null, "Producto modificado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			        cargarProductos();
			        limpiarCampos();
				} catch (Exception exc) {
		            JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnAceptar.setBounds(256, 643, 100, 29);
		getContentPane().add(btnAceptar);
		
		this.btnAceptar = btnAceptar;
		
		JTextArea textAreaCategorias = new JTextArea();
		textAreaCategorias.setEditable(false);
		textAreaCategorias.setBounds(40, 546, 189, 101);
		getContentPane().add(textAreaCategorias);
		
		this.textAreaCategorias = textAreaCategorias;
		
		JButton btnVerImagenes = new JButton("Ver Imagenes");
		btnVerImagenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<String> listaImagenes = null;
				if (nuevasImagenes != null && ! (nuevasImagenes.isEmpty())) {
					listaImagenes = nuevasImagenes;
				} else {
					listaImagenes = imagenesActuales;
				}

				if (listaImagenes != null && ! (listaImagenes.isEmpty())) {
					
					boolean panelVacio = true;

			        // Crear un JPanel para contener las imágenes
			        JPanel panelImagenes = new JPanel();
			        panelImagenes.setLayout(new FlowLayout());

			        // Cargar y agregar las imágenes al panel
			        for (String ruta : listaImagenes) {
			            File archivoImagen = new File(ruta);
			            if (archivoImagen.exists()) {
			                ImageIcon icono = new ImageIcon(ruta);
			                JLabel etiquetaImagen = new JLabel(icono);
			                panelImagenes.add(etiquetaImagen);
			                panelVacio = false;
			            } else {
        		            JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + ruta, "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }

			        if (!panelVacio) {
			        	// Crear el JInternalFrame
						vistaImagenes = new JInternalFrame("Flamin-Go", true, true, true, true);
						vistaImagenes.setFrameIcon(new ImageIcon(ModificarDatosProducto.class.getResource("/Images/Flamin-Go.png")));
						vistaImagenes.setSize(600, 400);
						vistaImagenes.getContentPane().setLayout(new BorderLayout());
			        	
			        	// Añadir el panel al JInternalFrame
				        JScrollPane scrollPane = new JScrollPane(panelImagenes);
				        vistaImagenes.getContentPane().add(scrollPane, BorderLayout.CENTER);

				        // Añadir el JInternalFrame al JDesktopPane
				        menu.getMenuPrincipal().getContentPane().add(vistaImagenes);
				        vistaImagenes.setVisible(true);
				        
			        } else {
		            	JOptionPane.showMessageDialog(null, "No fue posible cargar ninguna imagen", "Error", JOptionPane.ERROR_MESSAGE);
			        }

				} else {
	            	JOptionPane.showMessageDialog(null, "El producto actual no posee imágenes y las mismas no han sido seleccionadas", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnVerImagenes.setBounds(418, 576, 129, 28);
		getContentPane().add(btnVerImagenes);
		
		this.btnVerImagenes = btnVerImagenes;
		
		alternarCampos(false);


	}
	
	public void cargarJTree() {
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorías");
		
		for (Categoria cat : sistema.getCategorias().values()) {
			this.cargarCategoriaJTree(cat, root); // ROOT SERIA EL NODO RAIZ (PODEMOS PONERLE CATEGORIA NOMAS)
		}
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
        this.JTreeSeleccionCategoriaPadre.setModel(treeModel);
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
		
		try {
			lista = this.sistema.listarProductos();
		} catch (NullPointerException e) {
			throw new NullPointerException (e.getMessage()); // FALTA POPUP DE ERROR
		}
		
		this.seleccionProducto.removeAllItems();
		
		for (DTProducto prod : lista) {
			this.seleccionProducto.addItem(prod);
		}
		
	}
    
    public List<String> generarEspecificaciones(String textoEspecificaciones) {
    	// Expresión regular para dividir en saltos de línea
        String[] parts = textoEspecificaciones.split("\\n");
        
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
    
}
