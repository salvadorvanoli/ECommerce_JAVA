package interfaces;

import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import clases.DTFecha;
import clases.DTOrdenDeCompra;
import clases.ISistema;
import excepciones.OrdenDeCompraNoExisteException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class CancelarOrdenDeCompra extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> selectOrdenCancelarOrdenDeCompra;
    private ISistema sistema;
    private Main menu;
    
    public JComboBox<String> getCombobox() {
    	return this.selectOrdenCancelarOrdenDeCompra;
    }
    
    public CancelarOrdenDeCompra(ISistema sistema, Main main) {
    	setIconifiable(true);
        this.sistema = sistema; // Guardar la referencia al sistema
        this.menu = main;
        inicializarComponentes();
        cargarOrdenesDeCompra(); // Cargar las órdenes de compra al inicializar la ventana
    }

    private void inicializarComponentes() {
        setClosable(true);
        setTitle("Cancelar una Orden de Compra");
        ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        setFrameIcon(new ImageIcon(img));
        setBounds(100, 100, 590, 328);
        getContentPane().setLayout(null);
        
        JTextPane tituloCancelarOrdenDeCompra = new JTextPane();
        tituloCancelarOrdenDeCompra.setBackground(new Color(240, 240, 240));
        tituloCancelarOrdenDeCompra.setEditable(false);
        tituloCancelarOrdenDeCompra.setText("Bienvenido a la interfaz de eliminación de órdenes de compra, elija una y seleccione \"Eliminar\"");
        tituloCancelarOrdenDeCompra.setBounds(10, 62, 534, 20);
        getContentPane().add(tituloCancelarOrdenDeCompra);
        
        JLabel labelSelectOrdenCancelarOrdenDeCompra = new JLabel("Seleccione la orden de compra *");
        labelSelectOrdenCancelarOrdenDeCompra.setBounds(27, 125, 224, 14);
        getContentPane().add(labelSelectOrdenCancelarOrdenDeCompra);
        
        selectOrdenCancelarOrdenDeCompra = new JComboBox<>();
        selectOrdenCancelarOrdenDeCompra.setBounds(27, 161, 423, 22);
        getContentPane().add(selectOrdenCancelarOrdenDeCompra);

        JButton eliminarOrdenCancelarOrdenDeCompra = new JButton("Eliminar");
        eliminarOrdenCancelarOrdenDeCompra.setBounds(455, 161, 89, 23);
        getContentPane().add(eliminarOrdenCancelarOrdenDeCompra);
        
        JLabel tituloPrincipalCancelarOrdenDeCompra = new JLabel("Cancelar Orden de Compra");
        tituloPrincipalCancelarOrdenDeCompra.setVerticalAlignment(SwingConstants.TOP);
        tituloPrincipalCancelarOrdenDeCompra.setHorizontalAlignment(SwingConstants.CENTER);
        tituloPrincipalCancelarOrdenDeCompra.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tituloPrincipalCancelarOrdenDeCompra.setBounds(189, 22, 192, 20);
        getContentPane().add(tituloPrincipalCancelarOrdenDeCompra);

        selectOrdenCancelarOrdenDeCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) selectOrdenCancelarOrdenDeCompra.getSelectedItem();
                if (seleccionado != null && seleccionado.contains(" - ")) {
                    String numeroOrdenStr = seleccionado.split(" - ")[0];
                    int numeroOrden = Integer.parseInt(numeroOrdenStr);
                    try {
                        sistema.elegirOrdenDeCompra(numeroOrden);
                        
                        if(menu.getInfoOrdenInternalFrame() != null) {
                        	if(menu.getInfoOrdenInternalFrame().isVisible()) {
                        		menu.getInfoOrdenInternalFrame().cargarOrdenesDeCompra();
                        		menu.getInfoOrdenInternalFrame().getCombobox().setSelectedItem(seleccionado);
                        	}
                        }
                    } catch (OrdenDeCompraNoExisteException e1) {
                    	JOptionPane.showMessageDialog(null, "La orden de compra seleccionada no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                	// Ignorar
                }
            }
        });

        eliminarOrdenCancelarOrdenDeCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccionado = (String) selectOrdenCancelarOrdenDeCompra.getSelectedItem();
                if (seleccionado != null && seleccionado.contains(" - ")) {
                    String numeroOrdenStr = seleccionado.split(" - ")[0];
                    int numeroOrden = Integer.parseInt(numeroOrdenStr);
                    try {
                        sistema.cancelarOrdenDeCompra(numeroOrden);
                        cargarOrdenesDeCompra();
                        menu.getInfoOrdenInternalFrame().cargarOrdenesDeCompra();
                        JOptionPane.showMessageDialog(null, "La orden se eliminó exitosamente", "Eliminar orden", JOptionPane.INFORMATION_MESSAGE);
                    } catch (OrdenDeCompraNoExisteException e1) {
                    	JOptionPane.showMessageDialog(null, "No hay ninguna orden de compra seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                	JOptionPane.showMessageDialog(null, "No hay ninguna orden de compra seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        JButton btnVerInfoOrdenes = new JButton("Ver Orden de Compra");
		btnVerInfoOrdenes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!menu.getInfoOrdenInternalFrame().isVisible()) {
					if(selectOrdenCancelarOrdenDeCompra.getSelectedItem() != null) {
						menu.getMenuPrincipal().getContentPane().add(menu.getInfoOrdenInternalFrame());
						menu.getInfoOrdenInternalFrame().setVisible(true);
						menu.getInfoOrdenInternalFrame().setLocation(0, 0);
						menu.getMenuPrincipal().revalidate();
						menu.getMenuPrincipal().repaint();
						
						DTFecha fecha = sistema.getOrdenDeCompraActual().getFecha();
			            String fechaFormateada = String.format("%02d/%02d/%04d", fecha.getDia(), fecha.getMes(), fecha.getAnio());
			            String opcion = sistema.getOrdenDeCompraActual().getNumero() + " - " + fechaFormateada;
			            
						menu.getInfoOrdenInternalFrame().getCombobox().setSelectedItem(opcion);
					} else {
						JOptionPane.showMessageDialog(null, "No hay ninguna orden de compra seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
					}	
				} else {
					JOptionPane.showMessageDialog(null, "Ya está la ventana abierta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnVerInfoOrdenes.setBounds(189, 234, 177, 23);
		getContentPane().add(btnVerInfoOrdenes);
    }
    
    private void cargarOrdenesDeCompra() {
    	
    	this.sistema.setOrdenActual(null);
    	this.sistema.crearCasos();
    	
        selectOrdenCancelarOrdenDeCompra.removeAllItems();
        List<DTOrdenDeCompra> lista = sistema.listarOrdenesDeCompra();
        if(lista.isEmpty()) {
        	
        }
        for (DTOrdenDeCompra orden : lista) {
            DTFecha fecha = orden.getFecha();
            String fechaFormateada = String.format("%02d/%02d/%04d", fecha.getDia(), fecha.getMes(), fecha.getAnio());
            String opcion = orden.getNumero() + " - " + fechaFormateada;
            selectOrdenCancelarOrdenDeCompra.addItem(opcion);
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            cargarOrdenesDeCompra(); // Recargar las órdenes de compra cada vez que se muestra la ventana
        }
    }
}
