package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.DTCantidadProducto;
import clases.DTFecha;
import clases.DTOrdenDeCompra;
import clases.DTOrdenDeCompraDetallada;
import clases.ISistema;
import excepciones.OrdenDeCompraNoExisteException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class VerInfoOrdenDeCompra extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> selectOrdenOrdenDeCompra;
    private ISistema sistema;
    private Main main;
    private JTextField numOrdenTextField;
    private JTextField fechaTextField;
    private JTextField totalTextField;
    private JTextField nicknameTextField;
    private JTextField nombreProductoTextField;
    private JTextField precioUnitarioTextField;
    private JTextField cantidadDelProductoTextField;
    private JTextField subtotalDeLaLineaTextField;
    private JList<String> lineasList;
    private JTextField estadoTextField;
    
    public JComboBox<String> getCombobox() {
    	return this.selectOrdenOrdenDeCompra;
    }
    
    public VerInfoOrdenDeCompra(ISistema sistema, Main main) {
    	setIconifiable(true);
    	this.main = main;
        this.sistema = sistema; // Guardar la referencia al sistema
        inicializarComponentes();
        cargarOrdenesDeCompra(); // Cargar las órdenes de compra al inicializar la ventana
    }

    private void inicializarComponentes() {
        setClosable(true);
        setTitle("Ver Información de una Orden de Compra");
        ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        setFrameIcon(new ImageIcon(img));
        setBounds(100, 100, 689, 641);
        getContentPane().setLayout(null);
        
        JLabel labelSelectOrdenCancelarOrdenDeCompra = new JLabel("Seleccione la orden de compra *");
        labelSelectOrdenCancelarOrdenDeCompra.setBounds(27, 73, 224, 14);
        getContentPane().add(labelSelectOrdenCancelarOrdenDeCompra);
        
        selectOrdenOrdenDeCompra = new JComboBox<>();
        selectOrdenOrdenDeCompra.setBounds(23, 109, 637, 22);
        getContentPane().add(selectOrdenOrdenDeCompra);
        
        JLabel verinfotitulo = new JLabel("Ver información de orden de compra");
        verinfotitulo.setVerticalAlignment(SwingConstants.TOP);
        verinfotitulo.setHorizontalAlignment(SwingConstants.CENTER);
        verinfotitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        verinfotitulo.setBounds(172, 23, 334, 20);
        getContentPane().add(verinfotitulo);
        
        numOrdenTextField = new JTextField();
        numOrdenTextField.setEditable(false);
        numOrdenTextField.setBounds(27, 228, 112, 20);
        getContentPane().add(numOrdenTextField);
        numOrdenTextField.setColumns(10);
        
        fechaTextField = new JTextField();
        fechaTextField.setEditable(false);
        fechaTextField.setBounds(270, 228, 111, 20);
        getContentPane().add(fechaTextField);
        fechaTextField.setColumns(10);
        
        totalTextField = new JTextField();
        totalTextField.setEditable(false);
        totalTextField.setBounds(27, 292, 112, 20);
        getContentPane().add(totalTextField);
        totalTextField.setColumns(10);
        
        nicknameTextField = new JTextField();
        nicknameTextField.setEditable(false);
        nicknameTextField.setColumns(10);
        nicknameTextField.setBounds(270, 292, 112, 20);
        getContentPane().add(nicknameTextField);
        
        JLabel numOrdenLabel = new JLabel("Número de orden");
        numOrdenLabel.setBounds(149, 231, 224, 14);
        getContentPane().add(numOrdenLabel);
        
        JLabel fechaLabel = new JLabel("Fecha");
        fechaLabel.setBounds(391, 231, 224, 14);
        getContentPane().add(fechaLabel);
        
        JLabel totalLabel = new JLabel("Total");
        totalLabel.setBounds(149, 295, 224, 14);
        getContentPane().add(totalLabel);
        
        JLabel nicknameLabel = new JLabel("Nickname Cliente");
        nicknameLabel.setBounds(391, 295, 224, 14);
        getContentPane().add(nicknameLabel);
        
        JLabel productosLabel = new JLabel("Lineas de la orden de compra");
        productosLabel.setBounds(191, 347, 224, 14);
        getContentPane().add(productosLabel);
        
        JScrollPane scrollLineasList = new JScrollPane();
        scrollLineasList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollLineasList.setBounds(27, 383, 224, 188);
        getContentPane().add(scrollLineasList);
        
        lineasList = new JList<>(new DefaultListModel<>());
        scrollLineasList.setViewportView(lineasList);
        
        nombreProductoTextField = new JTextField();
        nombreProductoTextField.setEditable(false);
        nombreProductoTextField.setColumns(10);
        nombreProductoTextField.setBounds(270, 385, 112, 20);
        getContentPane().add(nombreProductoTextField);
        
        precioUnitarioTextField = new JTextField();
        precioUnitarioTextField.setEditable(false);
        precioUnitarioTextField.setColumns(10);
        precioUnitarioTextField.setBounds(269, 429, 112, 20);
        getContentPane().add(precioUnitarioTextField);
        
        cantidadDelProductoTextField = new JTextField();
        cantidadDelProductoTextField.setEditable(false);
        cantidadDelProductoTextField.setColumns(10);
        cantidadDelProductoTextField.setBounds(269, 477, 112, 20);
        getContentPane().add(cantidadDelProductoTextField);
        
        subtotalDeLaLineaTextField = new JTextField();
        subtotalDeLaLineaTextField.setEditable(false);
        subtotalDeLaLineaTextField.setColumns(10);
        subtotalDeLaLineaTextField.setBounds(270, 528, 112, 20);
        getContentPane().add(subtotalDeLaLineaTextField);
        
        JLabel nombreDelProductoLabel = new JLabel("Nombre del producto");
        nombreDelProductoLabel.setBounds(391, 385, 224, 14);
        getContentPane().add(nombreDelProductoLabel);
        
        JLabel precioUnitarioDelProductoLabel = new JLabel("Precio unitario del producto");
        precioUnitarioDelProductoLabel.setBounds(391, 432, 224, 14);
        getContentPane().add(precioUnitarioDelProductoLabel);
        
        JLabel cantidadDelProductoLabel = new JLabel("Cantidad del producto");
        cantidadDelProductoLabel.setBounds(391, 480, 224, 14);
        getContentPane().add(cantidadDelProductoLabel);
        
        JLabel subtotalDeLaLineaLabel = new JLabel("Subtotal de la línea");
        subtotalDeLaLineaLabel.setBounds(391, 531, 224, 14);
        getContentPane().add(subtotalDeLaLineaLabel);
        
        JButton otra = new JButton("Ver otra orden");
        otra.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarCampos();
        	}
        });
        otra.setBounds(520, 577, 140, 23);
        getContentPane().add(otra);
        
        JButton datosEnvioBtn = new JButton("Datos Envío");
        datosEnvioBtn.setBounds(149, 161, 111, 23);
        getContentPane().add(datosEnvioBtn);
        datosEnvioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!main.getDatosEnvioInternalFrame().isVisible()) {
					if (selectOrdenOrdenDeCompra != null && selectOrdenOrdenDeCompra.getSelectedItem() != null && !selectOrdenOrdenDeCompra.getSelectedItem().toString().isEmpty()) {
						main.getMenuPrincipal().getContentPane().add(main.getDatosEnvioInternalFrame());
						main.getDatosEnvioInternalFrame().setVisible(true);
						main.getDatosEnvioInternalFrame().setLocation(0, 0);  // Ajustar la posición del InternalFrame
						main.getMenuPrincipal().revalidate();
						main.getMenuPrincipal().repaint();
					} else {
						JOptionPane.showMessageDialog(null, "No hay ninguna orden de compra seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ya está la ventana abierta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        
        JButton datosPagoBtn = new JButton("Datos Pago");
        datosPagoBtn.setBounds(27, 161, 112, 23);
        getContentPane().add(datosPagoBtn);
        datosPagoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!main.getFormaPagoInternalFrame().isVisible()) {
					if (selectOrdenOrdenDeCompra != null && selectOrdenOrdenDeCompra.getSelectedItem() != null && !selectOrdenOrdenDeCompra.getSelectedItem().toString().isEmpty()) {
						main.getMenuPrincipal().getContentPane().add(main.getFormaPagoInternalFrame());
						main.getFormaPagoInternalFrame().setVisible(true);
						main.getFormaPagoInternalFrame().setLocation(0, 0);  // Ajustar la posición del InternalFrame
						main.getMenuPrincipal().revalidate();
						main.getMenuPrincipal().repaint();
					} else {
						JOptionPane.showMessageDialog(null, "No hay ninguna orden de compra seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ya está la ventana abierta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        
        estadoTextField = new JTextField();
        estadoTextField.setEditable(false);
        estadoTextField.setColumns(10);
        estadoTextField.setBounds(480, 228, 111, 20);
        getContentPane().add(estadoTextField);
        
        JLabel estadoLabel = new JLabel("Estado");
        estadoLabel.setBounds(601, 231, 224, 14);
        getContentPane().add(estadoLabel);

        selectOrdenOrdenDeCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) selectOrdenOrdenDeCompra.getSelectedItem();
                if (seleccionado != null && seleccionado.contains(" - ")) {
                    String numeroOrdenStr = seleccionado.split(" - ")[0];
                    int numeroOrden = Integer.parseInt(numeroOrdenStr);
                    try {
                    	limpiarCampos();
                    	
                    	sistema.elegirOrdenDeCompra(numeroOrden);
                    	
                    	// Si el caso cancelarOrdenDeCompra está activo, muestra el valor que le interesa a ese caso
                    	
                    	if(main.getCancelarOrdenInternalFrame().isVisible()) {
                    		String seleccionadoCanc = (String) main.getCancelarOrdenInternalFrame().getCombobox().getSelectedItem();
                    		String numeroOrdenStrCanc = seleccionadoCanc.split(" - ")[0];
                            int numeroOrdenCanc = Integer.parseInt(numeroOrdenStrCanc);
                    		sistema.elegirOrdenDeCompra(numeroOrdenCanc);
                    	}
                        
                        DTOrdenDeCompraDetallada orden = sistema.verInformacionOrdenDeCompra();
                        
                        numOrdenTextField.setText(String.valueOf(orden.getNumero()));
                        
                        DTFecha fecha = orden.getFecha();
                        String fechaFormateada = String.format("%02d/%02d/%04d", fecha.getDia(), fecha.getMes(), fecha.getAnio());
                        fechaTextField.setText(fechaFormateada);
                        
                        totalTextField.setText(String.valueOf(orden.getPrecioTotal()));
                        
                        nicknameTextField.setText(orden.getCliente().getNickname());
                        
                        estadoTextField.setText(sistema.getOrdenDeCompraActual().getEstado().toString());
                        
                        // Obtener el modelo de la lista
                        DefaultListModel<String> listModel = (DefaultListModel<String>) lineasList.getModel();
                        listModel.clear();
                        
                        for (DTCantidadProducto cantProd : orden.getProductosCantidad()) {
                            // Agregar el nombre del producto al modelo de la lista
                            listModel.addElement(cantProd.getProducto().getNombre());
                        }
                    } catch (OrdenDeCompraNoExisteException e1) {
                    	JOptionPane.showMessageDialog(null, "La orden de compra seleccionada no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                	limpiarCampos();
                }
            }
        });
        
        lineasList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Esto previene que el evento se dispare dos veces
                    String selectedProductName = lineasList.getSelectedValue();
                    if (selectedProductName != null) {
                        // Buscar el producto correspondiente y mostrar la información detallada en productosTextArea
                        DTOrdenDeCompraDetallada orden = sistema.verInformacionOrdenDeCompra();
                        
                        for (DTCantidadProducto cantProd : orden.getProductosCantidad()) {
                            if (cantProd.getProducto().getNombre().equals(selectedProductName)) {
                                
                                nombreProductoTextField.setText(cantProd.getProducto().getNombre());
                                cantidadDelProductoTextField.setText(String.valueOf(cantProd.getCantidad()));
                                precioUnitarioTextField.setText(String.valueOf(cantProd.getProducto().getPrecio()));
                                subtotalDeLaLineaTextField.setText(String.valueOf(cantProd.getSubtotal()));
                                
                                break;
                            }
                        }
                    }
                }
            }
        });

       
    }
    
    public void cargarOrdenesDeCompra() {
        selectOrdenOrdenDeCompra.removeAllItems(); // Limpiar el JComboBox
        List<DTOrdenDeCompra> lista = sistema.listarOrdenesDeCompra();
        if(lista.isEmpty()) {
        	
        	
        }
        for (DTOrdenDeCompra orden : lista) {
            DTFecha fecha = orden.getFecha();
            String fechaFormateada = String.format("%02d/%02d/%04d", fecha.getDia(), fecha.getMes(), fecha.getAnio());
            String opcion = orden.getNumero() + " - " + fechaFormateada;
            selectOrdenOrdenDeCompra.addItem(opcion);
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
        	limpiarCampos();
            cargarOrdenesDeCompra(); // Recargar las órdenes de compra cada vez que se muestra la ventana
        }
    }
    
    private void limpiarCampos() {
        numOrdenTextField.setText("");
        fechaTextField.setText("");
        totalTextField.setText("");
        nicknameTextField.setText("");
        nombreProductoTextField.setText("");
        precioUnitarioTextField.setText("");
        cantidadDelProductoTextField.setText("");
        subtotalDeLaLineaTextField.setText("");
        estadoTextField.setText("");
        ((DefaultListModel<String>) lineasList.getModel()).clear();
    }	
}