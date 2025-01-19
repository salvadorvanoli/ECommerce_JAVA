package interfaces;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import clases.OrdenDeCompra;
import java.util.List;

import javax.swing.DefaultListModel;

import javax.swing.JOptionPane;
import clases.Categoria;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import clases.ISistema;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import clases.Cantidad;
import clases.Producto;
import excepciones.UsuarioNoExisteException;
import clases.DTCantidad;
import clases.DTCliente;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Image;

public class GenerarOrdenDeCompra extends JInternalFrame {
	public List<Cantidad> listaCantidades;

	private static final long serialVersionUID = 1L;

	// private static final Categoria  = null;
	private ISistema sistema;
	private JTextField cantidadPoner;
	private JComboBox<DTCliente> seleccionarCliente;
	private JTree seleccionarProducto;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param sistema 
	 */
	public GenerarOrdenDeCompra(ISistema sistema, Main main) {
		setIconifiable(true);
		setClosable(true);
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
		Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
		setFrameIcon(new ImageIcon(img));
		setTitle("Registrar Orden de Compra");
		setBounds(100, 100, 486, 389);
		getContentPane().setLayout(null);
		
		this.sistema = sistema;
		this.listaCantidades = new ArrayList<>();
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 142, 169, 133);
		getContentPane().add(scrollPane);
		
		JTree seleccionarProducto = new JTree();
		
		this.seleccionarProducto = seleccionarProducto;
		

		scrollPane.setViewportView(seleccionarProducto);
		seleccionarProducto.setName("");
		seleccionarProducto.setToggleClickCount(1);
		
		cargarJTree();
		
		
		JComboBox<DTCliente> seleccionarCliente = new JComboBox<DTCliente>();
		seleccionarCliente.addItem(new DTCliente("Seleccione un cliente", ""));
		seleccionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ACA SE TIENE QUE SELECCIONAR EL USUARIO EN EL SISTEMA
			}
		});
		seleccionarCliente.setBounds(27, 84, 124, 22);
		getContentPane().add(seleccionarCliente);

		this.seleccionarCliente = seleccionarCliente;
		
		List<DTCliente> clientes = sistema.listarClientes(); // Lista de clientes

		// Lleno el JComboBox con los clientes.
		for (DTCliente cliente : clientes) {
		    // Agregar el nickname del cliente al JComboBox
		    seleccionarCliente.addItem(cliente);
		}


		
		JLabel lblNewLabel = new JLabel("Seleccionar cliente:");
		lblNewLabel.setBounds(27, 59, 154, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccionar producto:");
		lblNewLabel_1.setBounds(27, 117, 186, 14);
		getContentPane().add(lblNewLabel_1);
		
		cantidadPoner = new JTextField();
		cantidadPoner.setBounds(81, 286, 30, 20);
		getContentPane().add(cantidadPoner);
		cantidadPoner.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad:");
		lblNewLabel_2.setBounds(27, 289, 59, 14);
		getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(278, 142, 169, 133);
		getContentPane().add(scrollPane_1);
		
		JList<String> orden = new JList<>();
		DefaultListModel<String> model = new DefaultListModel<>();
		orden.setModel(model); 
		scrollPane_1.setViewportView(orden);
		
		
//////////////////botón para agregar línea///////////
		JButton agregar = new JButton("Agregar a la orden");
		agregar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            //Obtenengo los valores seleccionados
		            DTCliente cliente = (DTCliente) seleccionarCliente.getSelectedItem();
		            sistema.elegirCliente(cliente.getNickname());
		            
		            // Obtener el nodo
		            DefaultMutableTreeNode nodoSeleccionado = (DefaultMutableTreeNode) seleccionarProducto.getLastSelectedPathComponent();
		            
		            //Definir la variable 'producto'
		            Producto producto = null;
		            
		            // Verifico si el nodo seleccionado no es nulo y si contiene un Producto
		            if (nodoSeleccionado != null && nodoSeleccionado.getUserObject() instanceof Producto) {
		                producto = (Producto) nodoSeleccionado.getUserObject();
		            }
		            
		            //Verificar si 'producto' sigue siendo null
		            if (producto == null) {
		                throw new NullPointerException("Seleccione un producto");
		            }
		            
		            // Obtener la cantidad del JTextField
		            String cantidadTexto = cantidadPoner.getText();
		            
		            // Verificar si la cantidad no está vacía
		            if (cantidadTexto.trim().isEmpty()) {
		                throw new NumberFormatException("El campo de cantidad está vacío.");
		            }
		            
		            // Convertir la cantidad a entero
		            int cantidad = Integer.parseInt(cantidadTexto);
		            
		            // Verificar si la cantidad es mayor que cero
		            if (cantidad <= 0) {
		                throw new NumberFormatException("La cantidad debe ser mayor que cero.");
		            }
		            
		            // Crear una nueva instancia de Cantidad
		            Cantidad nuevaCantidad = new Cantidad(producto, cantidad);
		            
		            // Guardar la instancia en la lista
		            listaCantidades.add(nuevaCantidad);
		            
		            // Agregar una representación de la cantidad a la JList
		            model.addElement(nuevaCantidad.toString2());
		            
		            // Limpiar los campos
		            cantidadPoner.setText("");
		            
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (NullPointerException ex) {
		            JOptionPane.showMessageDialog(null, "Seleccione un producto.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (UsuarioNoExisteException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		    }
		});

		
			

	
		agregar.setBounds(27, 325, 149, 23);
		getContentPane().add(agregar);
		

		
		JLabel lblNewLabel_3 = new JLabel("Orden provisoria:");
		lblNewLabel_3.setBounds(278, 117, 135, 14);
		getContentPane().add(lblNewLabel_3);
		
		
//////////////////botón para descartar línea///////////
		JButton descartar = new JButton("Descartar linea");
		descartar.setEnabled(false);
		
		orden.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Habilitar el botón si hay una selección, deshabilitar si no hay selección
                boolean hasSelection = !orden.isSelectionEmpty();
                descartar.setEnabled(hasSelection);
            }
        });
		
		descartar.addActionListener(new ActionListener() {
		
			
			public void actionPerformed(ActionEvent e) {
			    int selectedIndex = orden.getSelectedIndex();
			    if (selectedIndex != -1) {
			        String cantidadStr = model.getElementAt(selectedIndex);

			        // Eliminar el elemento de la JList
			        model.remove(selectedIndex);

			        // Encontrar la instancia correspondiente en la lista de Cantidades y eliminarla
			        Cantidad cantidadAEliminar = null;
			        for (Cantidad c : listaCantidades) {
			            if (c.toString2().equals(cantidadStr)) {
			                cantidadAEliminar = c;
			                break;
			            }
			        }

			        if (cantidadAEliminar != null) {
			            listaCantidades.remove(cantidadAEliminar);
			        }
			    }
			}


		}); 
		
		descartar.setBounds(278, 285, 144, 23);
		getContentPane().add(descartar);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (listaCantidades != null) {
				        listaCantidades.clear(); // Limpio todos los elementos de la lista
				    }
				 sistema.setDetallesEnvioActual(null);
				 sistema.setFormaPagoActual(null);
				    //Borro los elementos de la JList y actualizo la vista
				    DefaultListModel<?> model = (DefaultListModel<?>) orden.getModel(); 
				    model.clear(); //Limpia todos los elementos del modelo
			}
		});
		cancelar.setBounds(236, 325, 99, 23);
		getContentPane().add(cancelar);
		
//////////////////botón para dar de alta///////////
		JButton btnNewButton_3 = new JButton("Dar de alta");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        // Obtén el cliente seleccionado como un objeto DTCliente
			        DTCliente clienteSeleccionado = (DTCliente) seleccionarCliente.getSelectedItem();

			        // Verifica si el clienteSeleccionado es null
			        if (clienteSeleccionado == null) {
			            throw new Exception("Debes elegir un cliente"); // Lanza excepción si no se seleccionó un cliente
			        }
			        
			    

			        // Verifica si listaCantidades está vacío o es null
			        if (listaCantidades == null || listaCantidades.isEmpty() || sistema.getFormaPagoActual() == null || sistema.getDetallesEnvioActual() == null) {
			            throw new Exception("Ingrese todos los datos de la orden."); // Lanza excepción si la lista está vacía
			        } else {
			        	OrdenDeCompra nueva = sistema.agregarOrden(listaCantidades);

			        	// Construir el mensaje con los detalles de la orden
			        	StringBuilder mensaje = new StringBuilder();
			        	mensaje.append("Cliente: ").append(nueva.getCliente().getNickname()).append("\n");
			        	mensaje.append("Fecha: ").append(nueva.getFecha()).append("\n");
			        	mensaje.append("Número de Orden: ").append(nueva.getNumero()).append("\n");
			        	mensaje.append("Detalles de la Orden:\n");

			        	float total = 0;

			        	for (DTCantidad c : nueva.getCantidad()) {
			        	    mensaje.append("- Producto: ").append(c.getProducto().getNombre())
			        	           .append(", Cantidad: ").append(c.getCantidad()).append("\n");
			        	    total += c.getProducto().getPrecio() * c.getCantidad();
			        	}

			        	// Agregar el total al mensaje
			        	mensaje.append("Total: $").append(String.format("%.2f", total)).append("\n");

			        	// Mostrar el mensaje de éxito con los detalles
			        	JOptionPane.showMessageDialog(null, mensaje.toString(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

			            // Cerrar el JInternalFrame
			            SwingUtilities.getAncestorOfClass(JInternalFrame.class, (Component) e.getSource());
			        }
			    } catch (Exception ex) {
			        // Mostrar solo el mensaje de error
			        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			    } finally {
			        // Limpiar listaCantidades y JList
			        if (listaCantidades != null) {
			            listaCantidades.clear(); // Limpio todos los elementos de la lista
			        }

			        DefaultListModel<?> model = (DefaultListModel<?>) orden.getModel();
			        model.clear(); // Limpia todos los elementos del modelo
			    }
			}

		});
		
		btnNewButton_3.setBounds(345, 325, 104, 23);
		getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Generar Orden de Compra");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(27, 11, 212, 22);
		getContentPane().add(lblNewLabel_4);
		
		JButton pago = new JButton("Método de pago");
		pago.setBounds(278, 72, 144, 23);
		getContentPane().add(pago);
		
		pago.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JInternalFrame agregarFormaPagoFrame = main.getAgregarFormaPagoInternalFrame();

		        // Verifica si el JInternalFrame no está visible
		        if (!agregarFormaPagoFrame.isVisible()) {
		            // Verifica si ya está agregado al contenedor
		            if (agregarFormaPagoFrame.getParent() == null) {
		                main.getMenuPrincipal().getContentPane().add(agregarFormaPagoFrame);
		            }

		            // Muestra el JInternalFrame
		            agregarFormaPagoFrame.setVisible(true);
		            agregarFormaPagoFrame.setLocation(0, 0);  // Ajustar la posición del InternalFrame
		            main.getMenuPrincipal().revalidate();
		            main.getMenuPrincipal().repaint();
		        } else {
		            // Si ya está visible, simplemente lo activa
		            agregarFormaPagoFrame.toFront();
		            agregarFormaPagoFrame.requestFocus();
		        }
		    }
		});

		
		JButton envio = new JButton("Detalles de envío");
		envio.setBounds(278, 38, 144, 23);
		getContentPane().add(envio);

		envio.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JInternalFrame agregarDatosEnvioFrame = main.getAgregarDatosEnvioInternalFrame();
		        
		        // Verifica si el JInternalFrame no está visible
		        if (!agregarDatosEnvioFrame.isVisible()) {
		            // Verifica si ya está agregado al contenedor
		            if (agregarDatosEnvioFrame.getParent() == null) {
		                main.getMenuPrincipal().getContentPane().add(agregarDatosEnvioFrame);
		            }

		            // Muestra el JInternalFrame
		            agregarDatosEnvioFrame.setVisible(true);
		            agregarDatosEnvioFrame.setLocation(0, 0);  // Ajustar la posición del InternalFrame
		            main.getMenuPrincipal().revalidate();
		            main.getMenuPrincipal().repaint();
		        } else {
		            // Si ya está visible, simplemente lo activa
		            agregarDatosEnvioFrame.toFront();
		            agregarDatosEnvioFrame.requestFocus();
		        }
		    }
		});

		
		//sistema.darAltaOrden(no se que parametros poner, falta hacerla);
		

	}
	
	public List<DTCliente> getClientes(){
		
		if (this.sistema == null) {
			// tiro el error
			throw new NullPointerException ("Error: El sistema no existe."); // FALTA POPUP
		}
		List<DTCliente> lista = null;
		
		lista = this.sistema.listarClientes();
		
		
		return lista;
		
	}

	public void cargarClientes() {
		List<DTCliente> lista = null;
		
		lista = this.getClientes();
		
		this.seleccionarCliente.removeAllItems();
		
		for (DTCliente cli : lista) {
			this.seleccionarCliente.addItem(cli);
		}
		
	}
	
	public void cargarJTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorías");
		
		for (Categoria cat : sistema.getCategorias().values()) {
			this.cargarElementosJTree(cat, root); // ROOT SERIA EL NODO RAIZ (PODEMOS PONERLE CATEGORIA NOMAS)
		}
		
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
        this.seleccionarProducto.setModel(treeModel);
	}
	
	// Esta funcion la agregué para ir creando recursivamente el JTree
	
	public void cargarElementosJTree(Categoria cat, DefaultMutableTreeNode nodo) {
		DefaultMutableTreeNode newNodo = new DefaultMutableTreeNode(cat);
		if (!(cat.getProductos().isEmpty())) {
			for (Producto prod : cat.getProductos()) {
				DefaultMutableTreeNode nodoProd = new DefaultMutableTreeNode(prod);
				newNodo.add(nodoProd);
			}
		}
		if (!(cat.getHijos().isEmpty())) {
			for (Categoria hijo : cat.getHijos()) {
				cargarElementosJTree(hijo, newNodo);
			}
		}
		nodo.add(newNodo);
	}

	
	
}
