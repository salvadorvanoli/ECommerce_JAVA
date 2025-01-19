package services;

import clases.Cliente;
import clases.DTCantidad;
import clases.DTCategoria;
import clases.DTCambioEstado;
import clases.Categoria;
import clases.DTCliente;
import clases.DTComentario;
import clases.DTProveedor;
import clases.DTFecha;
import clases.DTOrdenDeCompra;
import clases.DTOrdenMinima;
import clases.DTProducto;
import clases.DTProductoDetallado;
import clases.DTReclamo;
import clases.DTUsuario;
import clases.DTUsuarioDetallado;
import clases.DetallesEnvio;
import clases.FormaPago;
import clases.ISistema;
import clases.Producto;
import clases.Proveedor;
import clases.Reclamo;
import clases.SistemaFactory;
import config.ConfigManager;
import excepciones.CategoriaNoExisteException;
import excepciones.CategoriaNoPuedeTenerProductosException;
import excepciones.ContraseniaIncorrectaException;
import excepciones.ProductoNoExisteException;
import excepciones.ProductoRepetidoException;
import excepciones.UsuarioNoEncontrado;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class Publicador {

    private Endpoint endpoint = null;
    private ISistema sis;

    public Publicador() {
        
        this.sis = SistemaFactory.getInstancia().getISistema();
    }

    @WebMethod(exclude = true)
    public void publicar(ConfigManager config) {
    	if(this.endpoint != null) {
    		this.endpoint.stop();
    	}
        this.endpoint = Endpoint.publish(config.getPropiedad("server.central.url") + "/publicador", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return this.endpoint;
    }
    
    public String saludar() {
    	return "Servidor corriendo";
    }
    
    public void crearCasos() {
    	this.sis.crearCasos();
    }
    
    public List<DTUsuario> listarUsuarios() {
    	return sis.listarUsuarios();
    }
    
    public List<DTCliente> listarClientes() {
    	return sis.listarClientes();
    }
    
    public List<DTProveedor> listarProveedores() {
    	return sis.listarProveedores();
    }
    
    public Collection<DTProductoDetallado> listarAllProductos() {
    	return sis.listarAllProductos();
    }
    
    public byte[] getImagen(String ruta) throws IOException {
    	return sis.getImagen(ruta);
    }
	
	public List<byte[]> getImagenes(List<String> rutas) throws IOException {
		return sis.getImagenes(rutas);
	}
    
    public DTUsuarioDetallado getDTUsuarioActual(String identificador) throws UsuarioNoExisteException {
		return sis.getDTUsuarioActual(identificador);
    }
    
    public void iniciarSesion(String emailOrNickname, String password) throws ContraseniaIncorrectaException, UsuarioNoEncontrado {
		sis.iniciarSesion(emailOrNickname, password);
    }
    
	public boolean altaUsuarioCliente(String nickname, String email, String nombre, String apellido, int dia, int mes, int anio, String ruta, byte[] imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException, IOException {
		DTFecha fechaNac = new DTFecha(dia, mes, anio);
		return sis.altaUsuarioCliente(nickname, email, nombre, apellido, fechaNac, ruta, imagen, contrasenia1, contrasenia2);
	}
	
	public boolean altaUsuarioProveedor(String nickname, String email, String nombre, String apellido, int dia, int mes, int anio, String nomCompania, String linkWeb, String ruta, byte[] imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException, IOException {
        DTFecha fechaNac = new DTFecha(dia, mes, anio);
		return sis.altaUsuarioProveedor(nickname, email, nombre, apellido, fechaNac, nomCompania, linkWeb, ruta, imagen, contrasenia1, contrasenia2);
	}
	
	public boolean registro(String nickname, String email) throws UsuarioRepetidoException {
		return sis.registro(nickname, email);
	}
	
	public int generarCodigoOrden() {
		return sis.generarCodigoOrden();
	}
	
	public List<DTCantidad> getCarritoActual(String nickname) throws UsuarioNoExisteException {
		return sis.getCarritoActual(nickname);
	}
	
	public void eliminarItemCarrito(String nickname, int numReferencia) throws UsuarioNoExisteException {
		sis.eliminarItemCarrito(nickname, numReferencia);
	}
	
	public void modificarCantidadItemCarrito(String nickname, int numReferencia, int cantidad) throws UsuarioNoExisteException {
		sis.modificarCantidadItemCarrito(nickname, numReferencia, cantidad);
	}
	
	public void realizarCompra(String nickname, int numero, DTFecha fecha, DetallesEnvio detallesEnvio, FormaPago formaPago) throws UsuarioNoExisteException {
		sis.realizarCompra(nickname, numero, fecha, detallesEnvio, formaPago);
	}

	public void cambiarNotificaciones(String nickname, boolean notificaciones) throws UsuarioNoExisteException {
		sis.actualizarClienteNoti(notificaciones, nickname);
	}
	
	public List<DTOrdenMinima> getOrdenesMinimas(String nickname) throws UsuarioNoExisteException {
		return sis.getOrdenesMinimasUsuario(nickname);
	}
	
	public List<DTReclamo> getReclamosProveedor(String nickname) throws Exception {
		return sis.getDTReclamosProveedor(nickname);
	}
    
	public List<DTProducto> getProductosProveedor(String nickname) throws Exception {
		return sis.getDTProductosProveedor(nickname);
	}
	
	public DTProductoDetallado getDetallesProducto(int numReferencia) throws ProductoNoExisteException {
		return sis.getDetallesProducto(numReferencia);
	}
	
	public List<String> getCategoriasProducto(int numReferencia) throws ProductoNoExisteException {
		return sis.getNombresCategoriasProducto(numReferencia);
	}
	
	public boolean usuarioHaCompradoProducto(String nickname, int numReferencia) throws Exception {
		return sis.usuarioHaCompradoProducto(nickname, numReferencia);
	}
	
	public List<DTComentario> obtenerComentariosProducto(int numReferencia) throws ProductoNoExisteException {
		return sis.obtenerComentariosProducto(numReferencia);
	}
	
	public void agregarComentarioAProducto(int numReferencia, String nickname, String texto, int estrellas, String nickRespuesta, int idRespuesta) throws ProductoNoExisteException, UsuarioNoExisteException {
		sis.agregarComentarioAProducto(numReferencia, nickname, texto, estrellas, nickRespuesta, idRespuesta);
	}
	
	public void agregarRespuestaAUnComentario(int numReferencia, String nickname, String texto, int idComentario, String nickRespuesta, int idRespuesta) throws ProductoNoExisteException, UsuarioNoExisteException {
		sis.agregarRespuestaAUnComentario(numReferencia, nickname, texto, idComentario, nickRespuesta, idRespuesta);
	}
	
	public DTOrdenDeCompra getOrdenPorId(int id) throws Exception {
		return sis.getOrdenPorId(id);
	}
	
	public List<DTCantidad> getDTCantidadOrden(int id) throws Exception {
		return sis.getDTCantidadOrden(id);
	}
    
	public List<DTCambioEstado> getEstadosOrden(int id) throws Exception {
		return sis.getEstadosOrden(id);
	}
	
	public DetallesEnvio getDetallesEnvio(int id) throws Exception {
		return sis.getDetallesEnvio(id);
	}
	
	public void agregarEstadoAUnaOrden(int id, String estadoOrden) {
		sis.agregarEstadoAUnaOrden(id, estadoOrden);
	}
	
	public void agregarAlCarrito(int numReferencia, int cantidad, String nickname) throws Exception {
		sis.agregarAlCarrito(numReferencia, cantidad, nickname);
	}
	
	public void altaProducto(String nickname, String nombre, int numeroReferencia, String descripcion, float precio, 
			List<String> especificaciones, List<String> rutas, List<byte[]> imagenes, List<String> categorias, String nombreCompania) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, UsuarioNoExisteException, CategoriaNoExisteException, IOException {

		// Inicialización de lista de categorías
		List<Categoria> categoriasClase = new ArrayList<>();
		for (String nombreCat: categorias){
			sis.elegirCategoria(nombreCat);
			Categoria catEncontrada = sis.getCategoriaActual();
			if ( catEncontrada != null)  {
				categoriasClase.add(catEncontrada);
			}
		}
		
		try {
			sis.registrarProducto(nickname, nombre, numeroReferencia, descripcion, especificaciones, precio, categoriasClase, rutas, imagenes, nombreCompania);
		} catch (ProductoRepetidoException e) {
			System.err.println("Error: Producto repetido.");
			throw e;
		} catch (CategoriaNoPuedeTenerProductosException e) {
			System.err.println("Error: Categoría no puede tener productos.");
			throw e;
		} catch (UsuarioNoExisteException e) {
			System.err.println("Error: Usuario no existe.");
			throw e;
		}
	}

	public void altaReclamo(String nicknameCliente, int idProd, String mensaje, int dia, int mes, int anio ) throws UsuarioNoExisteException {
		List<Producto>	prods = sis.getProductos();
		Producto productoEncontrado = null;

		for (Producto prod : prods) {
		    if (prod.getNumReferencia() == idProd) {
		        productoEncontrado = prod;
		        break; 
		    }
		}
		
		DTProducto prod = productoEncontrado.getDTProducto();
		DTFecha fecha = new DTFecha(dia, mes, anio);
		
		Cliente cli = (Cliente) sis.getUsuarioPorNick(nicknameCliente);
		String nomC = cli.getNombre();
		String apellidoC = cli.getApellido();
		Proveedor prov = productoEncontrado.getProveedor();
		Reclamo rec = new Reclamo(mensaje, prod, fecha, nicknameCliente, nomC, apellidoC, prov);
			
		sis.setReclamo(prov, rec);
	}
			
	public List<DTProducto> listarDTProductos() {
		return sis.listarDTProductos();
	}
	
	public List<DTCategoria> listarCategorias() {
		return sis.listarCategorias();
	}
	
	public List<DTCategoria> listarCategoriasConProductos() {
		return sis.listarCategoriasConProductos();
	}
}
