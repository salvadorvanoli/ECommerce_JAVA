
package services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CategoriaNoExisteException_QNAME = new QName("http://services/", "CategoriaNoExisteException");
    private final static QName _CategoriaNoPuedeTenerProductosException_QNAME = new QName("http://services/", "CategoriaNoPuedeTenerProductosException");
    private final static QName _ContraseniaIncorrectaException_QNAME = new QName("http://services/", "ContraseniaIncorrectaException");
    private final static QName _Exception_QNAME = new QName("http://services/", "Exception");
    private final static QName _IOException_QNAME = new QName("http://services/", "IOException");
    private final static QName _ProductoNoExisteException_QNAME = new QName("http://services/", "ProductoNoExisteException");
    private final static QName _ProductoRepetidoException_QNAME = new QName("http://services/", "ProductoRepetidoException");
    private final static QName _UsuarioNoEncontrado_QNAME = new QName("http://services/", "UsuarioNoEncontrado");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://services/", "UsuarioNoExisteException");
    private final static QName _UsuarioRepetidoException_QNAME = new QName("http://services/", "UsuarioRepetidoException");
    private final static QName _AgregarAlCarrito_QNAME = new QName("http://services/", "agregarAlCarrito");
    private final static QName _AgregarAlCarritoResponse_QNAME = new QName("http://services/", "agregarAlCarritoResponse");
    private final static QName _AgregarComentarioAProducto_QNAME = new QName("http://services/", "agregarComentarioAProducto");
    private final static QName _AgregarComentarioAProductoResponse_QNAME = new QName("http://services/", "agregarComentarioAProductoResponse");
    private final static QName _AgregarEstadoAUnaOrden_QNAME = new QName("http://services/", "agregarEstadoAUnaOrden");
    private final static QName _AgregarEstadoAUnaOrdenResponse_QNAME = new QName("http://services/", "agregarEstadoAUnaOrdenResponse");
    private final static QName _AgregarRespuestaAUnComentario_QNAME = new QName("http://services/", "agregarRespuestaAUnComentario");
    private final static QName _AgregarRespuestaAUnComentarioResponse_QNAME = new QName("http://services/", "agregarRespuestaAUnComentarioResponse");
    private final static QName _AltaProducto_QNAME = new QName("http://services/", "altaProducto");
    private final static QName _AltaProductoResponse_QNAME = new QName("http://services/", "altaProductoResponse");
    private final static QName _AltaReclamo_QNAME = new QName("http://services/", "altaReclamo");
    private final static QName _AltaReclamoResponse_QNAME = new QName("http://services/", "altaReclamoResponse");
    private final static QName _AltaUsuarioCliente_QNAME = new QName("http://services/", "altaUsuarioCliente");
    private final static QName _AltaUsuarioClienteResponse_QNAME = new QName("http://services/", "altaUsuarioClienteResponse");
    private final static QName _AltaUsuarioProveedor_QNAME = new QName("http://services/", "altaUsuarioProveedor");
    private final static QName _AltaUsuarioProveedorResponse_QNAME = new QName("http://services/", "altaUsuarioProveedorResponse");
    private final static QName _CambiarNotificaciones_QNAME = new QName("http://services/", "cambiarNotificaciones");
    private final static QName _CambiarNotificacionesResponse_QNAME = new QName("http://services/", "cambiarNotificacionesResponse");
    private final static QName _CrearCasos_QNAME = new QName("http://services/", "crearCasos");
    private final static QName _CrearCasosResponse_QNAME = new QName("http://services/", "crearCasosResponse");
    private final static QName _EliminarItemCarrito_QNAME = new QName("http://services/", "eliminarItemCarrito");
    private final static QName _EliminarItemCarritoResponse_QNAME = new QName("http://services/", "eliminarItemCarritoResponse");
    private final static QName _GenerarCodigoOrden_QNAME = new QName("http://services/", "generarCodigoOrden");
    private final static QName _GenerarCodigoOrdenResponse_QNAME = new QName("http://services/", "generarCodigoOrdenResponse");
    private final static QName _GetCarritoActual_QNAME = new QName("http://services/", "getCarritoActual");
    private final static QName _GetCarritoActualResponse_QNAME = new QName("http://services/", "getCarritoActualResponse");
    private final static QName _GetCategoriasProducto_QNAME = new QName("http://services/", "getCategoriasProducto");
    private final static QName _GetCategoriasProductoResponse_QNAME = new QName("http://services/", "getCategoriasProductoResponse");
    private final static QName _GetDTCantidadOrden_QNAME = new QName("http://services/", "getDTCantidadOrden");
    private final static QName _GetDTCantidadOrdenResponse_QNAME = new QName("http://services/", "getDTCantidadOrdenResponse");
    private final static QName _GetDTUsuarioActual_QNAME = new QName("http://services/", "getDTUsuarioActual");
    private final static QName _GetDTUsuarioActualResponse_QNAME = new QName("http://services/", "getDTUsuarioActualResponse");
    private final static QName _GetDetallesEnvio_QNAME = new QName("http://services/", "getDetallesEnvio");
    private final static QName _GetDetallesEnvioResponse_QNAME = new QName("http://services/", "getDetallesEnvioResponse");
    private final static QName _GetDetallesProducto_QNAME = new QName("http://services/", "getDetallesProducto");
    private final static QName _GetDetallesProductoResponse_QNAME = new QName("http://services/", "getDetallesProductoResponse");
    private final static QName _GetEstadosOrden_QNAME = new QName("http://services/", "getEstadosOrden");
    private final static QName _GetEstadosOrdenResponse_QNAME = new QName("http://services/", "getEstadosOrdenResponse");
    private final static QName _GetImagen_QNAME = new QName("http://services/", "getImagen");
    private final static QName _GetImagenResponse_QNAME = new QName("http://services/", "getImagenResponse");
    private final static QName _GetImagenes_QNAME = new QName("http://services/", "getImagenes");
    private final static QName _GetImagenesResponse_QNAME = new QName("http://services/", "getImagenesResponse");
    private final static QName _GetOrdenPorId_QNAME = new QName("http://services/", "getOrdenPorId");
    private final static QName _GetOrdenPorIdResponse_QNAME = new QName("http://services/", "getOrdenPorIdResponse");
    private final static QName _GetOrdenesMinimas_QNAME = new QName("http://services/", "getOrdenesMinimas");
    private final static QName _GetOrdenesMinimasResponse_QNAME = new QName("http://services/", "getOrdenesMinimasResponse");
    private final static QName _GetProductosProveedor_QNAME = new QName("http://services/", "getProductosProveedor");
    private final static QName _GetProductosProveedorResponse_QNAME = new QName("http://services/", "getProductosProveedorResponse");
    private final static QName _GetReclamosProveedor_QNAME = new QName("http://services/", "getReclamosProveedor");
    private final static QName _GetReclamosProveedorResponse_QNAME = new QName("http://services/", "getReclamosProveedorResponse");
    private final static QName _IniciarSesion_QNAME = new QName("http://services/", "iniciarSesion");
    private final static QName _IniciarSesionResponse_QNAME = new QName("http://services/", "iniciarSesionResponse");
    private final static QName _ListarAllProductos_QNAME = new QName("http://services/", "listarAllProductos");
    private final static QName _ListarAllProductosResponse_QNAME = new QName("http://services/", "listarAllProductosResponse");
    private final static QName _ListarCategorias_QNAME = new QName("http://services/", "listarCategorias");
    private final static QName _ListarCategoriasConProductos_QNAME = new QName("http://services/", "listarCategoriasConProductos");
    private final static QName _ListarCategoriasConProductosResponse_QNAME = new QName("http://services/", "listarCategoriasConProductosResponse");
    private final static QName _ListarCategoriasResponse_QNAME = new QName("http://services/", "listarCategoriasResponse");
    private final static QName _ListarClientes_QNAME = new QName("http://services/", "listarClientes");
    private final static QName _ListarClientesResponse_QNAME = new QName("http://services/", "listarClientesResponse");
    private final static QName _ListarDTProductos_QNAME = new QName("http://services/", "listarDTProductos");
    private final static QName _ListarDTProductosResponse_QNAME = new QName("http://services/", "listarDTProductosResponse");
    private final static QName _ListarProveedores_QNAME = new QName("http://services/", "listarProveedores");
    private final static QName _ListarProveedoresResponse_QNAME = new QName("http://services/", "listarProveedoresResponse");
    private final static QName _ListarUsuarios_QNAME = new QName("http://services/", "listarUsuarios");
    private final static QName _ListarUsuariosResponse_QNAME = new QName("http://services/", "listarUsuariosResponse");
    private final static QName _ModificarCantidadItemCarrito_QNAME = new QName("http://services/", "modificarCantidadItemCarrito");
    private final static QName _ModificarCantidadItemCarritoResponse_QNAME = new QName("http://services/", "modificarCantidadItemCarritoResponse");
    private final static QName _ObtenerComentariosProducto_QNAME = new QName("http://services/", "obtenerComentariosProducto");
    private final static QName _ObtenerComentariosProductoResponse_QNAME = new QName("http://services/", "obtenerComentariosProductoResponse");
    private final static QName _RealizarCompra_QNAME = new QName("http://services/", "realizarCompra");
    private final static QName _RealizarCompraResponse_QNAME = new QName("http://services/", "realizarCompraResponse");
    private final static QName _Registro_QNAME = new QName("http://services/", "registro");
    private final static QName _RegistroResponse_QNAME = new QName("http://services/", "registroResponse");
    private final static QName _Saludar_QNAME = new QName("http://services/", "saludar");
    private final static QName _SaludarResponse_QNAME = new QName("http://services/", "saludarResponse");
    private final static QName _UsuarioHaCompradoProducto_QNAME = new QName("http://services/", "usuarioHaCompradoProducto");
    private final static QName _UsuarioHaCompradoProductoResponse_QNAME = new QName("http://services/", "usuarioHaCompradoProductoResponse");
    private final static QName _GetImagenResponseReturn_QNAME = new QName("", "return");
    private final static QName _AltaUsuarioProveedorArg10_QNAME = new QName("", "arg10");
    private final static QName _AltaUsuarioClienteArg8_QNAME = new QName("", "arg8");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoriaNoExisteException }
     * 
     */
    public CategoriaNoExisteException createCategoriaNoExisteException() {
        return new CategoriaNoExisteException();
    }

    /**
     * Create an instance of {@link CategoriaNoPuedeTenerProductosException }
     * 
     */
    public CategoriaNoPuedeTenerProductosException createCategoriaNoPuedeTenerProductosException() {
        return new CategoriaNoPuedeTenerProductosException();
    }

    /**
     * Create an instance of {@link ContraseniaIncorrectaException }
     * 
     */
    public ContraseniaIncorrectaException createContraseniaIncorrectaException() {
        return new ContraseniaIncorrectaException();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ProductoNoExisteException }
     * 
     */
    public ProductoNoExisteException createProductoNoExisteException() {
        return new ProductoNoExisteException();
    }

    /**
     * Create an instance of {@link ProductoRepetidoException }
     * 
     */
    public ProductoRepetidoException createProductoRepetidoException() {
        return new ProductoRepetidoException();
    }

    /**
     * Create an instance of {@link UsuarioNoEncontrado }
     * 
     */
    public UsuarioNoEncontrado createUsuarioNoEncontrado() {
        return new UsuarioNoEncontrado();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioRepetidoException }
     * 
     */
    public UsuarioRepetidoException createUsuarioRepetidoException() {
        return new UsuarioRepetidoException();
    }

    /**
     * Create an instance of {@link AgregarAlCarrito }
     * 
     */
    public AgregarAlCarrito createAgregarAlCarrito() {
        return new AgregarAlCarrito();
    }

    /**
     * Create an instance of {@link AgregarAlCarritoResponse }
     * 
     */
    public AgregarAlCarritoResponse createAgregarAlCarritoResponse() {
        return new AgregarAlCarritoResponse();
    }

    /**
     * Create an instance of {@link AgregarComentarioAProducto }
     * 
     */
    public AgregarComentarioAProducto createAgregarComentarioAProducto() {
        return new AgregarComentarioAProducto();
    }

    /**
     * Create an instance of {@link AgregarComentarioAProductoResponse }
     * 
     */
    public AgregarComentarioAProductoResponse createAgregarComentarioAProductoResponse() {
        return new AgregarComentarioAProductoResponse();
    }

    /**
     * Create an instance of {@link AgregarEstadoAUnaOrden }
     * 
     */
    public AgregarEstadoAUnaOrden createAgregarEstadoAUnaOrden() {
        return new AgregarEstadoAUnaOrden();
    }

    /**
     * Create an instance of {@link AgregarEstadoAUnaOrdenResponse }
     * 
     */
    public AgregarEstadoAUnaOrdenResponse createAgregarEstadoAUnaOrdenResponse() {
        return new AgregarEstadoAUnaOrdenResponse();
    }

    /**
     * Create an instance of {@link AgregarRespuestaAUnComentario }
     * 
     */
    public AgregarRespuestaAUnComentario createAgregarRespuestaAUnComentario() {
        return new AgregarRespuestaAUnComentario();
    }

    /**
     * Create an instance of {@link AgregarRespuestaAUnComentarioResponse }
     * 
     */
    public AgregarRespuestaAUnComentarioResponse createAgregarRespuestaAUnComentarioResponse() {
        return new AgregarRespuestaAUnComentarioResponse();
    }

    /**
     * Create an instance of {@link AltaProducto }
     * 
     */
    public AltaProducto createAltaProducto() {
        return new AltaProducto();
    }

    /**
     * Create an instance of {@link AltaProductoResponse }
     * 
     */
    public AltaProductoResponse createAltaProductoResponse() {
        return new AltaProductoResponse();
    }

    /**
     * Create an instance of {@link AltaReclamo }
     * 
     */
    public AltaReclamo createAltaReclamo() {
        return new AltaReclamo();
    }

    /**
     * Create an instance of {@link AltaReclamoResponse }
     * 
     */
    public AltaReclamoResponse createAltaReclamoResponse() {
        return new AltaReclamoResponse();
    }

    /**
     * Create an instance of {@link AltaUsuarioCliente }
     * 
     */
    public AltaUsuarioCliente createAltaUsuarioCliente() {
        return new AltaUsuarioCliente();
    }

    /**
     * Create an instance of {@link AltaUsuarioClienteResponse }
     * 
     */
    public AltaUsuarioClienteResponse createAltaUsuarioClienteResponse() {
        return new AltaUsuarioClienteResponse();
    }

    /**
     * Create an instance of {@link AltaUsuarioProveedor }
     * 
     */
    public AltaUsuarioProveedor createAltaUsuarioProveedor() {
        return new AltaUsuarioProveedor();
    }

    /**
     * Create an instance of {@link AltaUsuarioProveedorResponse }
     * 
     */
    public AltaUsuarioProveedorResponse createAltaUsuarioProveedorResponse() {
        return new AltaUsuarioProveedorResponse();
    }

    /**
     * Create an instance of {@link CambiarNotificaciones }
     * 
     */
    public CambiarNotificaciones createCambiarNotificaciones() {
        return new CambiarNotificaciones();
    }

    /**
     * Create an instance of {@link CambiarNotificacionesResponse }
     * 
     */
    public CambiarNotificacionesResponse createCambiarNotificacionesResponse() {
        return new CambiarNotificacionesResponse();
    }

    /**
     * Create an instance of {@link CrearCasos }
     * 
     */
    public CrearCasos createCrearCasos() {
        return new CrearCasos();
    }

    /**
     * Create an instance of {@link CrearCasosResponse }
     * 
     */
    public CrearCasosResponse createCrearCasosResponse() {
        return new CrearCasosResponse();
    }

    /**
     * Create an instance of {@link EliminarItemCarrito }
     * 
     */
    public EliminarItemCarrito createEliminarItemCarrito() {
        return new EliminarItemCarrito();
    }

    /**
     * Create an instance of {@link EliminarItemCarritoResponse }
     * 
     */
    public EliminarItemCarritoResponse createEliminarItemCarritoResponse() {
        return new EliminarItemCarritoResponse();
    }

    /**
     * Create an instance of {@link GenerarCodigoOrden }
     * 
     */
    public GenerarCodigoOrden createGenerarCodigoOrden() {
        return new GenerarCodigoOrden();
    }

    /**
     * Create an instance of {@link GenerarCodigoOrdenResponse }
     * 
     */
    public GenerarCodigoOrdenResponse createGenerarCodigoOrdenResponse() {
        return new GenerarCodigoOrdenResponse();
    }

    /**
     * Create an instance of {@link GetCarritoActual }
     * 
     */
    public GetCarritoActual createGetCarritoActual() {
        return new GetCarritoActual();
    }

    /**
     * Create an instance of {@link GetCarritoActualResponse }
     * 
     */
    public GetCarritoActualResponse createGetCarritoActualResponse() {
        return new GetCarritoActualResponse();
    }

    /**
     * Create an instance of {@link GetCategoriasProducto }
     * 
     */
    public GetCategoriasProducto createGetCategoriasProducto() {
        return new GetCategoriasProducto();
    }

    /**
     * Create an instance of {@link GetCategoriasProductoResponse }
     * 
     */
    public GetCategoriasProductoResponse createGetCategoriasProductoResponse() {
        return new GetCategoriasProductoResponse();
    }

    /**
     * Create an instance of {@link GetDTCantidadOrden }
     * 
     */
    public GetDTCantidadOrden createGetDTCantidadOrden() {
        return new GetDTCantidadOrden();
    }

    /**
     * Create an instance of {@link GetDTCantidadOrdenResponse }
     * 
     */
    public GetDTCantidadOrdenResponse createGetDTCantidadOrdenResponse() {
        return new GetDTCantidadOrdenResponse();
    }

    /**
     * Create an instance of {@link GetDTUsuarioActual }
     * 
     */
    public GetDTUsuarioActual createGetDTUsuarioActual() {
        return new GetDTUsuarioActual();
    }

    /**
     * Create an instance of {@link GetDTUsuarioActualResponse }
     * 
     */
    public GetDTUsuarioActualResponse createGetDTUsuarioActualResponse() {
        return new GetDTUsuarioActualResponse();
    }

    /**
     * Create an instance of {@link GetDetallesEnvio }
     * 
     */
    public GetDetallesEnvio createGetDetallesEnvio() {
        return new GetDetallesEnvio();
    }

    /**
     * Create an instance of {@link GetDetallesEnvioResponse }
     * 
     */
    public GetDetallesEnvioResponse createGetDetallesEnvioResponse() {
        return new GetDetallesEnvioResponse();
    }

    /**
     * Create an instance of {@link GetDetallesProducto }
     * 
     */
    public GetDetallesProducto createGetDetallesProducto() {
        return new GetDetallesProducto();
    }

    /**
     * Create an instance of {@link GetDetallesProductoResponse }
     * 
     */
    public GetDetallesProductoResponse createGetDetallesProductoResponse() {
        return new GetDetallesProductoResponse();
    }

    /**
     * Create an instance of {@link GetEstadosOrden }
     * 
     */
    public GetEstadosOrden createGetEstadosOrden() {
        return new GetEstadosOrden();
    }

    /**
     * Create an instance of {@link GetEstadosOrdenResponse }
     * 
     */
    public GetEstadosOrdenResponse createGetEstadosOrdenResponse() {
        return new GetEstadosOrdenResponse();
    }

    /**
     * Create an instance of {@link GetImagen }
     * 
     */
    public GetImagen createGetImagen() {
        return new GetImagen();
    }

    /**
     * Create an instance of {@link GetImagenResponse }
     * 
     */
    public GetImagenResponse createGetImagenResponse() {
        return new GetImagenResponse();
    }

    /**
     * Create an instance of {@link GetImagenes }
     * 
     */
    public GetImagenes createGetImagenes() {
        return new GetImagenes();
    }

    /**
     * Create an instance of {@link GetImagenesResponse }
     * 
     */
    public GetImagenesResponse createGetImagenesResponse() {
        return new GetImagenesResponse();
    }

    /**
     * Create an instance of {@link GetOrdenPorId }
     * 
     */
    public GetOrdenPorId createGetOrdenPorId() {
        return new GetOrdenPorId();
    }

    /**
     * Create an instance of {@link GetOrdenPorIdResponse }
     * 
     */
    public GetOrdenPorIdResponse createGetOrdenPorIdResponse() {
        return new GetOrdenPorIdResponse();
    }

    /**
     * Create an instance of {@link GetOrdenesMinimas }
     * 
     */
    public GetOrdenesMinimas createGetOrdenesMinimas() {
        return new GetOrdenesMinimas();
    }

    /**
     * Create an instance of {@link GetOrdenesMinimasResponse }
     * 
     */
    public GetOrdenesMinimasResponse createGetOrdenesMinimasResponse() {
        return new GetOrdenesMinimasResponse();
    }

    /**
     * Create an instance of {@link GetProductosProveedor }
     * 
     */
    public GetProductosProveedor createGetProductosProveedor() {
        return new GetProductosProveedor();
    }

    /**
     * Create an instance of {@link GetProductosProveedorResponse }
     * 
     */
    public GetProductosProveedorResponse createGetProductosProveedorResponse() {
        return new GetProductosProveedorResponse();
    }

    /**
     * Create an instance of {@link GetReclamosProveedor }
     * 
     */
    public GetReclamosProveedor createGetReclamosProveedor() {
        return new GetReclamosProveedor();
    }

    /**
     * Create an instance of {@link GetReclamosProveedorResponse }
     * 
     */
    public GetReclamosProveedorResponse createGetReclamosProveedorResponse() {
        return new GetReclamosProveedorResponse();
    }

    /**
     * Create an instance of {@link IniciarSesion }
     * 
     */
    public IniciarSesion createIniciarSesion() {
        return new IniciarSesion();
    }

    /**
     * Create an instance of {@link IniciarSesionResponse }
     * 
     */
    public IniciarSesionResponse createIniciarSesionResponse() {
        return new IniciarSesionResponse();
    }

    /**
     * Create an instance of {@link ListarAllProductos }
     * 
     */
    public ListarAllProductos createListarAllProductos() {
        return new ListarAllProductos();
    }

    /**
     * Create an instance of {@link ListarAllProductosResponse }
     * 
     */
    public ListarAllProductosResponse createListarAllProductosResponse() {
        return new ListarAllProductosResponse();
    }

    /**
     * Create an instance of {@link ListarCategorias }
     * 
     */
    public ListarCategorias createListarCategorias() {
        return new ListarCategorias();
    }

    /**
     * Create an instance of {@link ListarCategoriasConProductos }
     * 
     */
    public ListarCategoriasConProductos createListarCategoriasConProductos() {
        return new ListarCategoriasConProductos();
    }

    /**
     * Create an instance of {@link ListarCategoriasConProductosResponse }
     * 
     */
    public ListarCategoriasConProductosResponse createListarCategoriasConProductosResponse() {
        return new ListarCategoriasConProductosResponse();
    }

    /**
     * Create an instance of {@link ListarCategoriasResponse }
     * 
     */
    public ListarCategoriasResponse createListarCategoriasResponse() {
        return new ListarCategoriasResponse();
    }

    /**
     * Create an instance of {@link ListarClientes }
     * 
     */
    public ListarClientes createListarClientes() {
        return new ListarClientes();
    }

    /**
     * Create an instance of {@link ListarClientesResponse }
     * 
     */
    public ListarClientesResponse createListarClientesResponse() {
        return new ListarClientesResponse();
    }

    /**
     * Create an instance of {@link ListarDTProductos }
     * 
     */
    public ListarDTProductos createListarDTProductos() {
        return new ListarDTProductos();
    }

    /**
     * Create an instance of {@link ListarDTProductosResponse }
     * 
     */
    public ListarDTProductosResponse createListarDTProductosResponse() {
        return new ListarDTProductosResponse();
    }

    /**
     * Create an instance of {@link ListarProveedores }
     * 
     */
    public ListarProveedores createListarProveedores() {
        return new ListarProveedores();
    }

    /**
     * Create an instance of {@link ListarProveedoresResponse }
     * 
     */
    public ListarProveedoresResponse createListarProveedoresResponse() {
        return new ListarProveedoresResponse();
    }

    /**
     * Create an instance of {@link ListarUsuarios }
     * 
     */
    public ListarUsuarios createListarUsuarios() {
        return new ListarUsuarios();
    }

    /**
     * Create an instance of {@link ListarUsuariosResponse }
     * 
     */
    public ListarUsuariosResponse createListarUsuariosResponse() {
        return new ListarUsuariosResponse();
    }

    /**
     * Create an instance of {@link ModificarCantidadItemCarrito }
     * 
     */
    public ModificarCantidadItemCarrito createModificarCantidadItemCarrito() {
        return new ModificarCantidadItemCarrito();
    }

    /**
     * Create an instance of {@link ModificarCantidadItemCarritoResponse }
     * 
     */
    public ModificarCantidadItemCarritoResponse createModificarCantidadItemCarritoResponse() {
        return new ModificarCantidadItemCarritoResponse();
    }

    /**
     * Create an instance of {@link ObtenerComentariosProducto }
     * 
     */
    public ObtenerComentariosProducto createObtenerComentariosProducto() {
        return new ObtenerComentariosProducto();
    }

    /**
     * Create an instance of {@link ObtenerComentariosProductoResponse }
     * 
     */
    public ObtenerComentariosProductoResponse createObtenerComentariosProductoResponse() {
        return new ObtenerComentariosProductoResponse();
    }

    /**
     * Create an instance of {@link RealizarCompra }
     * 
     */
    public RealizarCompra createRealizarCompra() {
        return new RealizarCompra();
    }

    /**
     * Create an instance of {@link RealizarCompraResponse }
     * 
     */
    public RealizarCompraResponse createRealizarCompraResponse() {
        return new RealizarCompraResponse();
    }

    /**
     * Create an instance of {@link Registro }
     * 
     */
    public Registro createRegistro() {
        return new Registro();
    }

    /**
     * Create an instance of {@link RegistroResponse }
     * 
     */
    public RegistroResponse createRegistroResponse() {
        return new RegistroResponse();
    }

    /**
     * Create an instance of {@link Saludar }
     * 
     */
    public Saludar createSaludar() {
        return new Saludar();
    }

    /**
     * Create an instance of {@link SaludarResponse }
     * 
     */
    public SaludarResponse createSaludarResponse() {
        return new SaludarResponse();
    }

    /**
     * Create an instance of {@link UsuarioHaCompradoProducto }
     * 
     */
    public UsuarioHaCompradoProducto createUsuarioHaCompradoProducto() {
        return new UsuarioHaCompradoProducto();
    }

    /**
     * Create an instance of {@link UsuarioHaCompradoProductoResponse }
     * 
     */
    public UsuarioHaCompradoProductoResponse createUsuarioHaCompradoProductoResponse() {
        return new UsuarioHaCompradoProductoResponse();
    }

    /**
     * Create an instance of {@link DtOrdenDeCompra }
     * 
     */
    public DtOrdenDeCompra createDtOrdenDeCompra() {
        return new DtOrdenDeCompra();
    }

    /**
     * Create an instance of {@link DtFecha }
     * 
     */
    public DtFecha createDtFecha() {
        return new DtFecha();
    }

    /**
     * Create an instance of {@link DtCantidad }
     * 
     */
    public DtCantidad createDtCantidad() {
        return new DtCantidad();
    }

    /**
     * Create an instance of {@link DtProducto }
     * 
     */
    public DtProducto createDtProducto() {
        return new DtProducto();
    }

    /**
     * Create an instance of {@link DtCambioEstado }
     * 
     */
    public DtCambioEstado createDtCambioEstado() {
        return new DtCambioEstado();
    }

    /**
     * Create an instance of {@link DtProductoDetallado }
     * 
     */
    public DtProductoDetallado createDtProductoDetallado() {
        return new DtProductoDetallado();
    }

    /**
     * Create an instance of {@link DtProveedor }
     * 
     */
    public DtProveedor createDtProveedor() {
        return new DtProveedor();
    }

    /**
     * Create an instance of {@link DtUsuarioDetallado }
     * 
     */
    public DtUsuarioDetallado createDtUsuarioDetallado() {
        return new DtUsuarioDetallado();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtCliente }
     * 
     */
    public DtCliente createDtCliente() {
        return new DtCliente();
    }

    /**
     * Create an instance of {@link DetallesEnvio }
     * 
     */
    public DetallesEnvio createDetallesEnvio() {
        return new DetallesEnvio();
    }

    /**
     * Create an instance of {@link FormaPago }
     * 
     */
    public FormaPago createFormaPago() {
        return new FormaPago();
    }

    /**
     * Create an instance of {@link DtCategoria }
     * 
     */
    public DtCategoria createDtCategoria() {
        return new DtCategoria();
    }

    /**
     * Create an instance of {@link DtComentario }
     * 
     */
    public DtComentario createDtComentario() {
        return new DtComentario();
    }

    /**
     * Create an instance of {@link DtOrdenMinima }
     * 
     */
    public DtOrdenMinima createDtOrdenMinima() {
        return new DtOrdenMinima();
    }

    /**
     * Create an instance of {@link DtReclamo }
     * 
     */
    public DtReclamo createDtReclamo() {
        return new DtReclamo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoriaNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "CategoriaNoExisteException")
    public JAXBElement<CategoriaNoExisteException> createCategoriaNoExisteException(CategoriaNoExisteException value) {
        return new JAXBElement<CategoriaNoExisteException>(_CategoriaNoExisteException_QNAME, CategoriaNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaNoPuedeTenerProductosException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoriaNoPuedeTenerProductosException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "CategoriaNoPuedeTenerProductosException")
    public JAXBElement<CategoriaNoPuedeTenerProductosException> createCategoriaNoPuedeTenerProductosException(CategoriaNoPuedeTenerProductosException value) {
        return new JAXBElement<CategoriaNoPuedeTenerProductosException>(_CategoriaNoPuedeTenerProductosException_QNAME, CategoriaNoPuedeTenerProductosException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContraseniaIncorrectaException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ContraseniaIncorrectaException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "ContraseniaIncorrectaException")
    public JAXBElement<ContraseniaIncorrectaException> createContraseniaIncorrectaException(ContraseniaIncorrectaException value) {
        return new JAXBElement<ContraseniaIncorrectaException>(_ContraseniaIncorrectaException_QNAME, ContraseniaIncorrectaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductoNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProductoNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "ProductoNoExisteException")
    public JAXBElement<ProductoNoExisteException> createProductoNoExisteException(ProductoNoExisteException value) {
        return new JAXBElement<ProductoNoExisteException>(_ProductoNoExisteException_QNAME, ProductoNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductoRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProductoRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "ProductoRepetidoException")
    public JAXBElement<ProductoRepetidoException> createProductoRepetidoException(ProductoRepetidoException value) {
        return new JAXBElement<ProductoRepetidoException>(_ProductoRepetidoException_QNAME, ProductoRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoEncontrado }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoEncontrado }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "UsuarioNoEncontrado")
    public JAXBElement<UsuarioNoEncontrado> createUsuarioNoEncontrado(UsuarioNoEncontrado value) {
        return new JAXBElement<UsuarioNoEncontrado>(_UsuarioNoEncontrado_QNAME, UsuarioNoEncontrado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<UsuarioNoExisteException>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "UsuarioRepetidoException")
    public JAXBElement<UsuarioRepetidoException> createUsuarioRepetidoException(UsuarioRepetidoException value) {
        return new JAXBElement<UsuarioRepetidoException>(_UsuarioRepetidoException_QNAME, UsuarioRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarAlCarrito }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarAlCarrito }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarAlCarrito")
    public JAXBElement<AgregarAlCarrito> createAgregarAlCarrito(AgregarAlCarrito value) {
        return new JAXBElement<AgregarAlCarrito>(_AgregarAlCarrito_QNAME, AgregarAlCarrito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarAlCarritoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarAlCarritoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarAlCarritoResponse")
    public JAXBElement<AgregarAlCarritoResponse> createAgregarAlCarritoResponse(AgregarAlCarritoResponse value) {
        return new JAXBElement<AgregarAlCarritoResponse>(_AgregarAlCarritoResponse_QNAME, AgregarAlCarritoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarComentarioAProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarComentarioAProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarComentarioAProducto")
    public JAXBElement<AgregarComentarioAProducto> createAgregarComentarioAProducto(AgregarComentarioAProducto value) {
        return new JAXBElement<AgregarComentarioAProducto>(_AgregarComentarioAProducto_QNAME, AgregarComentarioAProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarComentarioAProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarComentarioAProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarComentarioAProductoResponse")
    public JAXBElement<AgregarComentarioAProductoResponse> createAgregarComentarioAProductoResponse(AgregarComentarioAProductoResponse value) {
        return new JAXBElement<AgregarComentarioAProductoResponse>(_AgregarComentarioAProductoResponse_QNAME, AgregarComentarioAProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarEstadoAUnaOrden }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarEstadoAUnaOrden }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarEstadoAUnaOrden")
    public JAXBElement<AgregarEstadoAUnaOrden> createAgregarEstadoAUnaOrden(AgregarEstadoAUnaOrden value) {
        return new JAXBElement<AgregarEstadoAUnaOrden>(_AgregarEstadoAUnaOrden_QNAME, AgregarEstadoAUnaOrden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarEstadoAUnaOrdenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarEstadoAUnaOrdenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarEstadoAUnaOrdenResponse")
    public JAXBElement<AgregarEstadoAUnaOrdenResponse> createAgregarEstadoAUnaOrdenResponse(AgregarEstadoAUnaOrdenResponse value) {
        return new JAXBElement<AgregarEstadoAUnaOrdenResponse>(_AgregarEstadoAUnaOrdenResponse_QNAME, AgregarEstadoAUnaOrdenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarRespuestaAUnComentario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarRespuestaAUnComentario }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarRespuestaAUnComentario")
    public JAXBElement<AgregarRespuestaAUnComentario> createAgregarRespuestaAUnComentario(AgregarRespuestaAUnComentario value) {
        return new JAXBElement<AgregarRespuestaAUnComentario>(_AgregarRespuestaAUnComentario_QNAME, AgregarRespuestaAUnComentario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AgregarRespuestaAUnComentarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AgregarRespuestaAUnComentarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "agregarRespuestaAUnComentarioResponse")
    public JAXBElement<AgregarRespuestaAUnComentarioResponse> createAgregarRespuestaAUnComentarioResponse(AgregarRespuestaAUnComentarioResponse value) {
        return new JAXBElement<AgregarRespuestaAUnComentarioResponse>(_AgregarRespuestaAUnComentarioResponse_QNAME, AgregarRespuestaAUnComentarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaProducto")
    public JAXBElement<AltaProducto> createAltaProducto(AltaProducto value) {
        return new JAXBElement<AltaProducto>(_AltaProducto_QNAME, AltaProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaProductoResponse")
    public JAXBElement<AltaProductoResponse> createAltaProductoResponse(AltaProductoResponse value) {
        return new JAXBElement<AltaProductoResponse>(_AltaProductoResponse_QNAME, AltaProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaReclamo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaReclamo }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaReclamo")
    public JAXBElement<AltaReclamo> createAltaReclamo(AltaReclamo value) {
        return new JAXBElement<AltaReclamo>(_AltaReclamo_QNAME, AltaReclamo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaReclamoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaReclamoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaReclamoResponse")
    public JAXBElement<AltaReclamoResponse> createAltaReclamoResponse(AltaReclamoResponse value) {
        return new JAXBElement<AltaReclamoResponse>(_AltaReclamoResponse_QNAME, AltaReclamoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuarioCliente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaUsuarioCliente }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaUsuarioCliente")
    public JAXBElement<AltaUsuarioCliente> createAltaUsuarioCliente(AltaUsuarioCliente value) {
        return new JAXBElement<AltaUsuarioCliente>(_AltaUsuarioCliente_QNAME, AltaUsuarioCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuarioClienteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaUsuarioClienteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaUsuarioClienteResponse")
    public JAXBElement<AltaUsuarioClienteResponse> createAltaUsuarioClienteResponse(AltaUsuarioClienteResponse value) {
        return new JAXBElement<AltaUsuarioClienteResponse>(_AltaUsuarioClienteResponse_QNAME, AltaUsuarioClienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuarioProveedor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaUsuarioProveedor }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaUsuarioProveedor")
    public JAXBElement<AltaUsuarioProveedor> createAltaUsuarioProveedor(AltaUsuarioProveedor value) {
        return new JAXBElement<AltaUsuarioProveedor>(_AltaUsuarioProveedor_QNAME, AltaUsuarioProveedor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuarioProveedorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaUsuarioProveedorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "altaUsuarioProveedorResponse")
    public JAXBElement<AltaUsuarioProveedorResponse> createAltaUsuarioProveedorResponse(AltaUsuarioProveedorResponse value) {
        return new JAXBElement<AltaUsuarioProveedorResponse>(_AltaUsuarioProveedorResponse_QNAME, AltaUsuarioProveedorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarNotificaciones }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CambiarNotificaciones }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "cambiarNotificaciones")
    public JAXBElement<CambiarNotificaciones> createCambiarNotificaciones(CambiarNotificaciones value) {
        return new JAXBElement<CambiarNotificaciones>(_CambiarNotificaciones_QNAME, CambiarNotificaciones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarNotificacionesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CambiarNotificacionesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "cambiarNotificacionesResponse")
    public JAXBElement<CambiarNotificacionesResponse> createCambiarNotificacionesResponse(CambiarNotificacionesResponse value) {
        return new JAXBElement<CambiarNotificacionesResponse>(_CambiarNotificacionesResponse_QNAME, CambiarNotificacionesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearCasos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CrearCasos }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "crearCasos")
    public JAXBElement<CrearCasos> createCrearCasos(CrearCasos value) {
        return new JAXBElement<CrearCasos>(_CrearCasos_QNAME, CrearCasos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CrearCasosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CrearCasosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "crearCasosResponse")
    public JAXBElement<CrearCasosResponse> createCrearCasosResponse(CrearCasosResponse value) {
        return new JAXBElement<CrearCasosResponse>(_CrearCasosResponse_QNAME, CrearCasosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarItemCarrito }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EliminarItemCarrito }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "eliminarItemCarrito")
    public JAXBElement<EliminarItemCarrito> createEliminarItemCarrito(EliminarItemCarrito value) {
        return new JAXBElement<EliminarItemCarrito>(_EliminarItemCarrito_QNAME, EliminarItemCarrito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EliminarItemCarritoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EliminarItemCarritoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "eliminarItemCarritoResponse")
    public JAXBElement<EliminarItemCarritoResponse> createEliminarItemCarritoResponse(EliminarItemCarritoResponse value) {
        return new JAXBElement<EliminarItemCarritoResponse>(_EliminarItemCarritoResponse_QNAME, EliminarItemCarritoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarCodigoOrden }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GenerarCodigoOrden }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "generarCodigoOrden")
    public JAXBElement<GenerarCodigoOrden> createGenerarCodigoOrden(GenerarCodigoOrden value) {
        return new JAXBElement<GenerarCodigoOrden>(_GenerarCodigoOrden_QNAME, GenerarCodigoOrden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarCodigoOrdenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GenerarCodigoOrdenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "generarCodigoOrdenResponse")
    public JAXBElement<GenerarCodigoOrdenResponse> createGenerarCodigoOrdenResponse(GenerarCodigoOrdenResponse value) {
        return new JAXBElement<GenerarCodigoOrdenResponse>(_GenerarCodigoOrdenResponse_QNAME, GenerarCodigoOrdenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarritoActual }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCarritoActual }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getCarritoActual")
    public JAXBElement<GetCarritoActual> createGetCarritoActual(GetCarritoActual value) {
        return new JAXBElement<GetCarritoActual>(_GetCarritoActual_QNAME, GetCarritoActual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCarritoActualResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCarritoActualResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getCarritoActualResponse")
    public JAXBElement<GetCarritoActualResponse> createGetCarritoActualResponse(GetCarritoActualResponse value) {
        return new JAXBElement<GetCarritoActualResponse>(_GetCarritoActualResponse_QNAME, GetCarritoActualResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategoriasProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCategoriasProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getCategoriasProducto")
    public JAXBElement<GetCategoriasProducto> createGetCategoriasProducto(GetCategoriasProducto value) {
        return new JAXBElement<GetCategoriasProducto>(_GetCategoriasProducto_QNAME, GetCategoriasProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategoriasProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetCategoriasProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getCategoriasProductoResponse")
    public JAXBElement<GetCategoriasProductoResponse> createGetCategoriasProductoResponse(GetCategoriasProductoResponse value) {
        return new JAXBElement<GetCategoriasProductoResponse>(_GetCategoriasProductoResponse_QNAME, GetCategoriasProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTCantidadOrden }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTCantidadOrden }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDTCantidadOrden")
    public JAXBElement<GetDTCantidadOrden> createGetDTCantidadOrden(GetDTCantidadOrden value) {
        return new JAXBElement<GetDTCantidadOrden>(_GetDTCantidadOrden_QNAME, GetDTCantidadOrden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTCantidadOrdenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTCantidadOrdenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDTCantidadOrdenResponse")
    public JAXBElement<GetDTCantidadOrdenResponse> createGetDTCantidadOrdenResponse(GetDTCantidadOrdenResponse value) {
        return new JAXBElement<GetDTCantidadOrdenResponse>(_GetDTCantidadOrdenResponse_QNAME, GetDTCantidadOrdenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTUsuarioActual }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTUsuarioActual }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDTUsuarioActual")
    public JAXBElement<GetDTUsuarioActual> createGetDTUsuarioActual(GetDTUsuarioActual value) {
        return new JAXBElement<GetDTUsuarioActual>(_GetDTUsuarioActual_QNAME, GetDTUsuarioActual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDTUsuarioActualResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDTUsuarioActualResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDTUsuarioActualResponse")
    public JAXBElement<GetDTUsuarioActualResponse> createGetDTUsuarioActualResponse(GetDTUsuarioActualResponse value) {
        return new JAXBElement<GetDTUsuarioActualResponse>(_GetDTUsuarioActualResponse_QNAME, GetDTUsuarioActualResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetallesEnvio }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDetallesEnvio }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDetallesEnvio")
    public JAXBElement<GetDetallesEnvio> createGetDetallesEnvio(GetDetallesEnvio value) {
        return new JAXBElement<GetDetallesEnvio>(_GetDetallesEnvio_QNAME, GetDetallesEnvio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetallesEnvioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDetallesEnvioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDetallesEnvioResponse")
    public JAXBElement<GetDetallesEnvioResponse> createGetDetallesEnvioResponse(GetDetallesEnvioResponse value) {
        return new JAXBElement<GetDetallesEnvioResponse>(_GetDetallesEnvioResponse_QNAME, GetDetallesEnvioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetallesProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDetallesProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDetallesProducto")
    public JAXBElement<GetDetallesProducto> createGetDetallesProducto(GetDetallesProducto value) {
        return new JAXBElement<GetDetallesProducto>(_GetDetallesProducto_QNAME, GetDetallesProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDetallesProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetDetallesProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getDetallesProductoResponse")
    public JAXBElement<GetDetallesProductoResponse> createGetDetallesProductoResponse(GetDetallesProductoResponse value) {
        return new JAXBElement<GetDetallesProductoResponse>(_GetDetallesProductoResponse_QNAME, GetDetallesProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEstadosOrden }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEstadosOrden }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getEstadosOrden")
    public JAXBElement<GetEstadosOrden> createGetEstadosOrden(GetEstadosOrden value) {
        return new JAXBElement<GetEstadosOrden>(_GetEstadosOrden_QNAME, GetEstadosOrden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEstadosOrdenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEstadosOrdenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getEstadosOrdenResponse")
    public JAXBElement<GetEstadosOrdenResponse> createGetEstadosOrdenResponse(GetEstadosOrdenResponse value) {
        return new JAXBElement<GetEstadosOrdenResponse>(_GetEstadosOrdenResponse_QNAME, GetEstadosOrdenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagen }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImagen }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getImagen")
    public JAXBElement<GetImagen> createGetImagen(GetImagen value) {
        return new JAXBElement<GetImagen>(_GetImagen_QNAME, GetImagen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImagenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getImagenResponse")
    public JAXBElement<GetImagenResponse> createGetImagenResponse(GetImagenResponse value) {
        return new JAXBElement<GetImagenResponse>(_GetImagenResponse_QNAME, GetImagenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagenes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImagenes }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getImagenes")
    public JAXBElement<GetImagenes> createGetImagenes(GetImagenes value) {
        return new JAXBElement<GetImagenes>(_GetImagenes_QNAME, GetImagenes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagenesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetImagenesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getImagenesResponse")
    public JAXBElement<GetImagenesResponse> createGetImagenesResponse(GetImagenesResponse value) {
        return new JAXBElement<GetImagenesResponse>(_GetImagenesResponse_QNAME, GetImagenesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdenPorId }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrdenPorId }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getOrdenPorId")
    public JAXBElement<GetOrdenPorId> createGetOrdenPorId(GetOrdenPorId value) {
        return new JAXBElement<GetOrdenPorId>(_GetOrdenPorId_QNAME, GetOrdenPorId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdenPorIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrdenPorIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getOrdenPorIdResponse")
    public JAXBElement<GetOrdenPorIdResponse> createGetOrdenPorIdResponse(GetOrdenPorIdResponse value) {
        return new JAXBElement<GetOrdenPorIdResponse>(_GetOrdenPorIdResponse_QNAME, GetOrdenPorIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdenesMinimas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrdenesMinimas }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getOrdenesMinimas")
    public JAXBElement<GetOrdenesMinimas> createGetOrdenesMinimas(GetOrdenesMinimas value) {
        return new JAXBElement<GetOrdenesMinimas>(_GetOrdenesMinimas_QNAME, GetOrdenesMinimas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdenesMinimasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrdenesMinimasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getOrdenesMinimasResponse")
    public JAXBElement<GetOrdenesMinimasResponse> createGetOrdenesMinimasResponse(GetOrdenesMinimasResponse value) {
        return new JAXBElement<GetOrdenesMinimasResponse>(_GetOrdenesMinimasResponse_QNAME, GetOrdenesMinimasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductosProveedor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductosProveedor }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getProductosProveedor")
    public JAXBElement<GetProductosProveedor> createGetProductosProveedor(GetProductosProveedor value) {
        return new JAXBElement<GetProductosProveedor>(_GetProductosProveedor_QNAME, GetProductosProveedor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductosProveedorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetProductosProveedorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getProductosProveedorResponse")
    public JAXBElement<GetProductosProveedorResponse> createGetProductosProveedorResponse(GetProductosProveedorResponse value) {
        return new JAXBElement<GetProductosProveedorResponse>(_GetProductosProveedorResponse_QNAME, GetProductosProveedorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReclamosProveedor }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetReclamosProveedor }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getReclamosProveedor")
    public JAXBElement<GetReclamosProveedor> createGetReclamosProveedor(GetReclamosProveedor value) {
        return new JAXBElement<GetReclamosProveedor>(_GetReclamosProveedor_QNAME, GetReclamosProveedor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReclamosProveedorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetReclamosProveedorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "getReclamosProveedorResponse")
    public JAXBElement<GetReclamosProveedorResponse> createGetReclamosProveedorResponse(GetReclamosProveedorResponse value) {
        return new JAXBElement<GetReclamosProveedorResponse>(_GetReclamosProveedorResponse_QNAME, GetReclamosProveedorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarSesion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IniciarSesion }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "iniciarSesion")
    public JAXBElement<IniciarSesion> createIniciarSesion(IniciarSesion value) {
        return new JAXBElement<IniciarSesion>(_IniciarSesion_QNAME, IniciarSesion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IniciarSesionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IniciarSesionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "iniciarSesionResponse")
    public JAXBElement<IniciarSesionResponse> createIniciarSesionResponse(IniciarSesionResponse value) {
        return new JAXBElement<IniciarSesionResponse>(_IniciarSesionResponse_QNAME, IniciarSesionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarAllProductos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarAllProductos }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarAllProductos")
    public JAXBElement<ListarAllProductos> createListarAllProductos(ListarAllProductos value) {
        return new JAXBElement<ListarAllProductos>(_ListarAllProductos_QNAME, ListarAllProductos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarAllProductosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarAllProductosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarAllProductosResponse")
    public JAXBElement<ListarAllProductosResponse> createListarAllProductosResponse(ListarAllProductosResponse value) {
        return new JAXBElement<ListarAllProductosResponse>(_ListarAllProductosResponse_QNAME, ListarAllProductosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategorias }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategorias }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarCategorias")
    public JAXBElement<ListarCategorias> createListarCategorias(ListarCategorias value) {
        return new JAXBElement<ListarCategorias>(_ListarCategorias_QNAME, ListarCategorias.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategoriasConProductos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategoriasConProductos }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarCategoriasConProductos")
    public JAXBElement<ListarCategoriasConProductos> createListarCategoriasConProductos(ListarCategoriasConProductos value) {
        return new JAXBElement<ListarCategoriasConProductos>(_ListarCategoriasConProductos_QNAME, ListarCategoriasConProductos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategoriasConProductosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategoriasConProductosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarCategoriasConProductosResponse")
    public JAXBElement<ListarCategoriasConProductosResponse> createListarCategoriasConProductosResponse(ListarCategoriasConProductosResponse value) {
        return new JAXBElement<ListarCategoriasConProductosResponse>(_ListarCategoriasConProductosResponse_QNAME, ListarCategoriasConProductosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategoriasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategoriasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarCategoriasResponse")
    public JAXBElement<ListarCategoriasResponse> createListarCategoriasResponse(ListarCategoriasResponse value) {
        return new JAXBElement<ListarCategoriasResponse>(_ListarCategoriasResponse_QNAME, ListarCategoriasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarClientes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarClientes }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarClientes")
    public JAXBElement<ListarClientes> createListarClientes(ListarClientes value) {
        return new JAXBElement<ListarClientes>(_ListarClientes_QNAME, ListarClientes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarClientesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarClientesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarClientesResponse")
    public JAXBElement<ListarClientesResponse> createListarClientesResponse(ListarClientesResponse value) {
        return new JAXBElement<ListarClientesResponse>(_ListarClientesResponse_QNAME, ListarClientesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarDTProductos }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarDTProductos }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarDTProductos")
    public JAXBElement<ListarDTProductos> createListarDTProductos(ListarDTProductos value) {
        return new JAXBElement<ListarDTProductos>(_ListarDTProductos_QNAME, ListarDTProductos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarDTProductosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarDTProductosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarDTProductosResponse")
    public JAXBElement<ListarDTProductosResponse> createListarDTProductosResponse(ListarDTProductosResponse value) {
        return new JAXBElement<ListarDTProductosResponse>(_ListarDTProductosResponse_QNAME, ListarDTProductosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarProveedores }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarProveedores }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarProveedores")
    public JAXBElement<ListarProveedores> createListarProveedores(ListarProveedores value) {
        return new JAXBElement<ListarProveedores>(_ListarProveedores_QNAME, ListarProveedores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarProveedoresResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarProveedoresResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarProveedoresResponse")
    public JAXBElement<ListarProveedoresResponse> createListarProveedoresResponse(ListarProveedoresResponse value) {
        return new JAXBElement<ListarProveedoresResponse>(_ListarProveedoresResponse_QNAME, ListarProveedoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuarios }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarUsuarios }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarUsuarios")
    public JAXBElement<ListarUsuarios> createListarUsuarios(ListarUsuarios value) {
        return new JAXBElement<ListarUsuarios>(_ListarUsuarios_QNAME, ListarUsuarios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUsuariosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarUsuariosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "listarUsuariosResponse")
    public JAXBElement<ListarUsuariosResponse> createListarUsuariosResponse(ListarUsuariosResponse value) {
        return new JAXBElement<ListarUsuariosResponse>(_ListarUsuariosResponse_QNAME, ListarUsuariosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificarCantidadItemCarrito }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModificarCantidadItemCarrito }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "modificarCantidadItemCarrito")
    public JAXBElement<ModificarCantidadItemCarrito> createModificarCantidadItemCarrito(ModificarCantidadItemCarrito value) {
        return new JAXBElement<ModificarCantidadItemCarrito>(_ModificarCantidadItemCarrito_QNAME, ModificarCantidadItemCarrito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificarCantidadItemCarritoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModificarCantidadItemCarritoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "modificarCantidadItemCarritoResponse")
    public JAXBElement<ModificarCantidadItemCarritoResponse> createModificarCantidadItemCarritoResponse(ModificarCantidadItemCarritoResponse value) {
        return new JAXBElement<ModificarCantidadItemCarritoResponse>(_ModificarCantidadItemCarritoResponse_QNAME, ModificarCantidadItemCarritoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerComentariosProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerComentariosProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "obtenerComentariosProducto")
    public JAXBElement<ObtenerComentariosProducto> createObtenerComentariosProducto(ObtenerComentariosProducto value) {
        return new JAXBElement<ObtenerComentariosProducto>(_ObtenerComentariosProducto_QNAME, ObtenerComentariosProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerComentariosProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerComentariosProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "obtenerComentariosProductoResponse")
    public JAXBElement<ObtenerComentariosProductoResponse> createObtenerComentariosProductoResponse(ObtenerComentariosProductoResponse value) {
        return new JAXBElement<ObtenerComentariosProductoResponse>(_ObtenerComentariosProductoResponse_QNAME, ObtenerComentariosProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealizarCompra }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealizarCompra }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "realizarCompra")
    public JAXBElement<RealizarCompra> createRealizarCompra(RealizarCompra value) {
        return new JAXBElement<RealizarCompra>(_RealizarCompra_QNAME, RealizarCompra.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RealizarCompraResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RealizarCompraResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "realizarCompraResponse")
    public JAXBElement<RealizarCompraResponse> createRealizarCompraResponse(RealizarCompraResponse value) {
        return new JAXBElement<RealizarCompraResponse>(_RealizarCompraResponse_QNAME, RealizarCompraResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Registro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Registro }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "registro")
    public JAXBElement<Registro> createRegistro(Registro value) {
        return new JAXBElement<Registro>(_Registro_QNAME, Registro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegistroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "registroResponse")
    public JAXBElement<RegistroResponse> createRegistroResponse(RegistroResponse value) {
        return new JAXBElement<RegistroResponse>(_RegistroResponse_QNAME, RegistroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Saludar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Saludar }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "saludar")
    public JAXBElement<Saludar> createSaludar(Saludar value) {
        return new JAXBElement<Saludar>(_Saludar_QNAME, Saludar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaludarResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SaludarResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "saludarResponse")
    public JAXBElement<SaludarResponse> createSaludarResponse(SaludarResponse value) {
        return new JAXBElement<SaludarResponse>(_SaludarResponse_QNAME, SaludarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioHaCompradoProducto }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioHaCompradoProducto }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "usuarioHaCompradoProducto")
    public JAXBElement<UsuarioHaCompradoProducto> createUsuarioHaCompradoProducto(UsuarioHaCompradoProducto value) {
        return new JAXBElement<UsuarioHaCompradoProducto>(_UsuarioHaCompradoProducto_QNAME, UsuarioHaCompradoProducto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioHaCompradoProductoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioHaCompradoProductoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services/", name = "usuarioHaCompradoProductoResponse")
    public JAXBElement<UsuarioHaCompradoProductoResponse> createUsuarioHaCompradoProductoResponse(UsuarioHaCompradoProductoResponse value) {
        return new JAXBElement<UsuarioHaCompradoProductoResponse>(_UsuarioHaCompradoProductoResponse_QNAME, UsuarioHaCompradoProductoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetImagenResponse.class)
    public JAXBElement<byte[]> createGetImagenResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetImagenResponseReturn_QNAME, byte[].class, GetImagenResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "arg10", scope = AltaUsuarioProveedor.class)
    public JAXBElement<byte[]> createAltaUsuarioProveedorArg10(byte[] value) {
        return new JAXBElement<byte[]>(_AltaUsuarioProveedorArg10_QNAME, byte[].class, AltaUsuarioProveedor.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "arg8", scope = AltaUsuarioCliente.class)
    public JAXBElement<byte[]> createAltaUsuarioClienteArg8(byte[] value) {
        return new JAXBElement<byte[]>(_AltaUsuarioClienteArg8_QNAME, byte[].class, AltaUsuarioCliente.class, ((byte[]) value));
    }

}
