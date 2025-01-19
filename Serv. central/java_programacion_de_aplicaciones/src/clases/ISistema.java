package clases;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import excepciones.*;
import jakarta.persistence.EntityManager;

public abstract class ISistema{
	
	public ISistema() {};
	
	// Agrego detalles y método de pago actual ( no sé si sea provisorio)
	public abstract FormaPago getFormaPagoActual();
	
	public abstract void setFormaPagoActual(FormaPago formaPagoActual);
	
	public abstract DetallesEnvio getDetallesEnvioActual();
	
	public abstract void setDetallesEnvioActual(DetallesEnvio detallesEnvio);
	
	public abstract List<DTProducto> listarDTProductos();
	
	public abstract void setReclamo(Proveedor proveedor, Reclamo reclamo);
	
	public abstract void setUsuarioActual(Usuario usuarioActual);
	
	public abstract void setCategoriaActual(Categoria categoriaActual);
	
	public abstract List<Producto> getProductos();
	
	public abstract List<Usuario> getUsuarios();
	
	public abstract HashMap<Integer, OrdenDeCompra> getOrdenes();
	
	public abstract HashMap<String, Categoria> getCategorias();
	
	public abstract Usuario getUsuarioActual();
	
	public abstract OrdenDeCompra getOrdenDeCompraActual();
	
	public abstract Categoria getCategoriaActual();
	
	public abstract Producto getProductoActual();
	
	public abstract void setOrdenActual(OrdenDeCompra ord);
	
	public abstract void setCarritoActual(List<Cantidad> car) throws UsuarioNoExisteException;
	
	public abstract void crearCasos();

	public abstract DTFecha getFechaActual();
	
	public abstract boolean altaUsuarioCliente(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String ruta, byte[] imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException, IOException;
	
	public abstract boolean altaUsuarioProveedor(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String nomCompania, String linkWeb, String ruta, byte[] imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException, IOException;

	public abstract boolean registrarProducto(String nickname, String titulo, int numReferencia, String descrip, List<String> especificaciones, float precio, List<Categoria> categorias, List<String> rutas, List<byte[]> imagenes, String nombreTienda) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, UsuarioNoExisteException, IOException;

	public abstract boolean registro(String nickname, String email) throws UsuarioRepetidoException;
	
	public abstract boolean validarNombreSinNumeros(String nombre);
	
	public abstract boolean validarUrl(String url);
	
	public abstract DTProductoDetallado verInformacionProducto();

	public abstract List<DTCategoria> listarCategorias();

	public abstract boolean elegirCategoria(String nombreCat) throws CategoriaNoExisteException;
	
	public abstract Categoria buscarCategoriaRecursivamente(String nombreCat, List<Categoria> categorias);

	public abstract List<DTProducto> listarProductos();

	public abstract Collection<DTProductoDetallado> listarAllProductos();
	
	public abstract boolean elegirProducto(String nombreProd) throws ProductoNoExisteException; // ESTA LA AGREGUÉ DESPUÉS

	public abstract Categoria altaCategoria(String nombre, boolean tieneProductos, Categoria padre) throws CategoriaRepetidaException;
	
	public abstract boolean existeCategoria(String nombreCategoria);

	public abstract List<DTOrdenDeCompra> listarOrdenesDeCompra();

	public abstract boolean elegirOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException;
	
	public abstract DTOrdenDeCompraDetallada verInformacionOrdenDeCompra();
	
	public abstract int generarCodigoOrden();

	public abstract void cancelarOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException;

	public abstract boolean agregarProducto(String nombreProducto, int cantidad) throws ProductoNoExisteException;

	public abstract List<DTCliente> listarClientes();

	public abstract boolean elegirCliente(String nickname) throws UsuarioNoExisteException;
	
	public abstract DTCliente verInformacionCliente();

	public abstract void quitarProductoDeCategorias(boolean seAgreganCategorias, int numReferenciaActual, EntityManager em);
	
	public abstract void agregarProductoACategorias(List<Categoria> listaCat, Producto prod) throws CategoriaNoPuedeTenerProductosException;
	
	public abstract void agregarCategoriasAProducto(List<Categoria> listaCat, Producto prod) throws CategoriaNoPuedeTenerProductosException;
	
	public abstract void existeProducto(String nombreProd, int numReferencia) throws ProductoRepetidoException;

	public abstract void modificarDatosProducto(String nombreProd, int numReferencia, String descripcion, float precio, List<String> especificacion, String nomTienda, List<Categoria> nuevasCategorias, List<String> rutasActualizadas) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, IOException;
	
	public abstract boolean modificarImagenesProducto(List<String> imagenes, Producto prod);
	
	public abstract List<DTProveedor> listarProveedores();
	
	public abstract void elegirProveedor(String nickname) throws UsuarioNoExisteException;
	
	public abstract DTProveedor verInformacionProveedor();
	
	public abstract OrdenDeCompra agregarOrden(List<Cantidad> cantidad);
	
	public abstract void setTodoNull();

	public abstract void eliminarItemCarrito(String nickname, int numReferencia) throws UsuarioNoExisteException;
	
	public abstract void modificarCantidadItemCarrito(String nickname, int numReferencia, int cantidad) throws UsuarioNoExisteException;

	public abstract List<DTCantidad> getCarritoActual(String nickname) throws UsuarioNoExisteException;
	
	public abstract void realizarCompra(String nickname, int numero, DTFecha fecha, DetallesEnvio detallesEnvio, FormaPago formaPago) throws UsuarioNoExisteException;

	public abstract int generarIdComentario();
	
	public abstract void iniciarSesion(String emailOrNickname, String password) throws ContraseniaIncorrectaException, UsuarioNoEncontrado;

	public abstract void setProductoActual(Producto prd);

	public abstract boolean existeCategoriaRecursivamente(Categoria categoriaPadre, String string);

	public abstract List<Producto> getDestacados();
	
	public abstract List<DTUsuario> listarUsuarios();
	
	public abstract DTUsuarioDetallado getDTUsuarioActual(String identificador) throws UsuarioNoExisteException;

	public abstract void actualizarClienteNoti(boolean notif, String nickname) throws UsuarioNoExisteException;

	public abstract List<DTOrdenMinima> getOrdenesMinimasUsuario(String nickname) throws UsuarioNoExisteException;

	public abstract Usuario getUsuarioPorNick(String nickname) throws UsuarioNoExisteException;

	public abstract List<DTReclamo> getDTReclamosProveedor(String nickname) throws Exception;

	public abstract List<DTProducto> getDTProductosProveedor(String nickname) throws Exception;

	public abstract List<String> getNombresCategoriasProducto(int numReferencia) throws ProductoNoExisteException;

	public abstract boolean usuarioHaCompradoProducto(String nickname, int numReferencia) throws Exception;

	public abstract DTProductoDetallado getDetallesProducto(int numReferencia) throws ProductoNoExisteException;

	public abstract List<DTComentario> obtenerComentariosProducto(int numReferencia) throws ProductoNoExisteException;

	public abstract void agregarRespuestaAUnComentario(int numReferencia, String nickname, String texto, int idComentario,
			String nickRespuesta, int idRespuesta) throws ProductoNoExisteException, UsuarioNoExisteException;

	public abstract void agregarComentarioAProducto(int numReferencia, String nickname, String texto, int estrellas,
			String nickRespuesta, int idRespuesta) throws ProductoNoExisteException, UsuarioNoExisteException;
	
	public abstract byte[] getImagen(String ruta) throws IOException;
	
	public abstract List<byte[]> getImagenes(List<String> rutas) throws IOException;
	
	public abstract String generarRutaUnica(String nuevaImagen);
	
	public abstract void guardarImagen(String rutaOriginal, String rutaGenerada) throws IOException;
	
	public abstract void guardarImagenWeb(byte[] datosImagen, String rutaGenerada) throws IOException;
	
	public abstract List<String> generarRutasUnicas(List<String> nuevasImagenes);
	
	public abstract void guardarImagenes(List<String> rutasOriginales, List<String> rutasGeneradas) throws IOException;
	
	public abstract void guardarImagenesWeb(List<byte[]> datosImagenes, List<String> rutasGeneradas) throws IOException;
	
	public abstract void borrarImagenes(List<String> imagenesAnteriores) throws IOException;

	public abstract List<DTCantidad> getDTCantidadOrden(int id) throws Exception;

	public abstract DTOrdenDeCompra getOrdenPorId(int id) throws Exception;

	public abstract List<DTCambioEstado> getEstadosOrden(int id) throws Exception;

	public abstract DetallesEnvio getDetallesEnvio(int id) throws Exception;

	public abstract void agregarEstadoAUnaOrden(int id, String estadoOrden)  throws IllegalArgumentException ;

	public abstract void agregarAlCarrito(int numReferencia, int cantidad, String nickname) throws Exception;

	public abstract List<DTCategoria> listarCategoriasConProductos();

}