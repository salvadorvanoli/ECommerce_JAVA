package interfaces;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JTextField;

import clases.Producto;
import clases.ISistema;

public class ProductosDestacados extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombreProveedorTextField;
	private JTextField CantidadComprasTextField;
	private ISistema sistema;
	private JList<String> ProductosDestacadosJList;
    private DefaultListModel<String> listModel;

	public ProductosDestacados(ISistema sistema) {
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 537, 435);
		getContentPane().setLayout(null);
		ImageIcon icon = new ImageIcon(AltaDeCategoria.class.getResource("/Images/Flamin-Go.png"));
        Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        setFrameIcon(new ImageIcon(img));
		
		this.sistema = sistema;
		
		setTitle("Productos Destacados");
		JLabel verDestacadosLabel = new JLabel("Productos Destacados");
		verDestacadosLabel.setVerticalAlignment(SwingConstants.TOP);
		verDestacadosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		verDestacadosLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		verDestacadosLabel.setBounds(84, 11, 334, 20);
		getContentPane().add(verDestacadosLabel);
		
		JScrollPane ScrollProductosDestacadosJList = new JScrollPane();
		ScrollProductosDestacadosJList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollProductosDestacadosJList.setBounds(20, 42, 480, 255);
		getContentPane().add(ScrollProductosDestacadosJList);
		
		// Inicializar el modelo de la lista
		listModel = new DefaultListModel<>();
		ProductosDestacadosJList = new JList<>(listModel);
		ScrollProductosDestacadosJList.setViewportView(ProductosDestacadosJList);

		// Agregar ListSelectionListener para manejar la selección de productos
		ProductosDestacadosJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Esto previene que el evento se dispare dos veces
                    String selectedProductName = ProductosDestacadosJList.getSelectedValue();
                    if (selectedProductName != null) {
                        for(Producto prod : sistema.getProductos()) {
                        	if(prod.getNombreProducto().equals(selectedProductName)) {
                        		sistema.setProductoActual(prod);
                        		mostrarDetallesProducto();
                        	}
                        }
                    }
                }
            }
        });
		
		nombreProveedorTextField = new JTextField();
		nombreProveedorTextField.setEditable(false);
		nombreProveedorTextField.setColumns(10);
		nombreProveedorTextField.setBounds(49, 327, 255, 20);
		getContentPane().add(nombreProveedorTextField);
		
		JLabel nombreDelProovedorLabel = new JLabel("Nombre del proveedor");
		nombreDelProovedorLabel.setBounds(314, 327, 224, 14);
		getContentPane().add(nombreDelProovedorLabel);
		
		JLabel cantidadComprasLabel = new JLabel("Cantidad de compras");
		cantidadComprasLabel.setBounds(314, 363, 224, 14);
		getContentPane().add(cantidadComprasLabel);
		
		CantidadComprasTextField = new JTextField();
		CantidadComprasTextField.setEditable(false);
		CantidadComprasTextField.setColumns(10);
		CantidadComprasTextField.setBounds(49, 363, 255, 20);
		getContentPane().add(CantidadComprasTextField);
	}

	private void mostrarDetallesProducto() {
        if (sistema.getProductoActual() != null) {
            nombreProveedorTextField.setText(sistema.getProductoActual().getProveedor().getNombre());
            CantidadComprasTextField.setText(String.valueOf(sistema.getProductoActual().getCantCompras()));
        }
    }
	
	private void cargarDatos() {
		sistema.crearCasos();
        
        List<Producto> top10Productos = sistema.getDestacados();
        
        if(!listModel.isEmpty()) {
        	listModel.clear();
        }
        
        for (Producto prod : top10Productos) {
            listModel.addElement(prod.getNombreProducto());
        }
    }
	
	@Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            cargarDatos();
        }
    }
}
