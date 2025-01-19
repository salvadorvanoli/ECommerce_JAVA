package clases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
// import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// import javax.swing.JOptionPane;

import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import jakarta.persistence.*;

// Importamos las excepciones necesarias

import excepciones.*;

public class Sistema extends ISistema {
	
	private List<Usuario> usuarios;
	private Usuario usuarioActual;
	private FormaPago formaPagoActual;
	private DetallesEnvio detallesEnvioActual;
	private EntityManagerFactory emf;
	
	public FormaPago getFormaPagoActual() {
		return formaPagoActual;
	}

	public void setFormaPagoActual(FormaPago formaPagoActual) {
		this.formaPagoActual = formaPagoActual;
	}

	public DetallesEnvio getDetallesEnvioActual() {
		return detallesEnvioActual;
	}

	public void setDetallesEnvioActual(DetallesEnvio detallesEnvioActual) {
		this.detallesEnvioActual = detallesEnvioActual;
	}

	private HashMap<Integer, OrdenDeCompra> ordenes;
	private OrdenDeCompra ordenActual;
	
	private HashMap<String, Categoria> categorias;
	private Categoria categoriaActual;
	
	private List<Producto> productos;
	
	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}

	public void setCategoriaActual(Categoria categoriaActual) {
		this.categoriaActual = categoriaActual;
	}

	private Producto productoActual;
	
	private List<Cantidad> listaOrden;
	
	public Sistema() {
		this.usuarios = new ArrayList<>();
		this.ordenes = new HashMap<Integer, OrdenDeCompra>();
		this.categorias = new HashMap<String, Categoria>();
		this.usuarioActual = null;
		this.ordenActual = null;
		this.categoriaActual = null;
		this.productoActual = null;
		this.listaOrden = new ArrayList<>(); 
		this.productos = new ArrayList<>();
		this.emf = Persistence.createEntityManagerFactory("obligatoriopa");
	}
	
	@Override
	public List<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
	@Override
	public HashMap<Integer, OrdenDeCompra> getOrdenes(){
		return this.ordenes;
	}
	
	@Override
	public HashMap<String, Categoria> getCategorias(){
		this.crearCasos();
		return this.categorias;
	}
	
	@Override
	public Usuario getUsuarioActual() {
		return this.usuarioActual;
	}
	
	@Override
	public OrdenDeCompra getOrdenDeCompraActual() {
		return this.ordenActual;
	}
	
	@Override
	public Categoria getCategoriaActual() {
		return this.categoriaActual;
	}
	
	@Override
	public Producto getProductoActual() {
		return this.productoActual;
	}
	
	@Override
	public DTFecha getFechaActual() {
		// Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Extraer el día, mes y año
        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int anio = fechaActual.getYear();
        
        return new DTFecha(dia, mes, anio);
	}
	
	public void setProductoActual(Producto prod) {
		this.productoActual = prod;
	}
	
	@Override
	public boolean altaUsuarioCliente(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String ruta, byte[] imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException, IOException {
		// Lista para almacenar los errores
	    List<String> errores = new ArrayList<>();

	    // Validar nombre
	    if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3 || !validarNombreSinNumeros(nombre)) {
	        errores.add("El nombre debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar apellido
	    if (apellido == null || apellido.trim().isEmpty() || apellido.length() < 3 || !validarNombreSinNumeros(apellido)) {
	        errores.add("El apellido debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar contraseñas
	    if (contrasenia1 == null || contrasenia1.length() < 8 || contrasenia2 == null || contrasenia2.length() < 8) {
	        errores.add("La contraseña debe tener al menos 8 caracteres.");
	    } else if (!contrasenia1.equals(contrasenia2)) {
	        errores.add("Las contraseñas no coinciden.");
	    }

	    // Validar fecha de nacimiento
	    if (fechaNac == null) {
	        errores.add("Se debe ingresar una fecha de nacimiento válida.");
	    }

	    // Si hay errores tirar excepción
	    if (!errores.isEmpty()) {
	        throw new IllegalArgumentException("Errores de validación: " + String.join(", ", errores));
	    }
		
		for (Usuario user : this.usuarios) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el email " + '"' + email + '"' + '.');
			}
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el nickname " + '"' + nickname + '"' + '.');
			}
		}
		
		if (!contrasenia1.equals(contrasenia2)) {
			throw new ContraseniaIncorrectaException("Las contraseñas no coinciden.");
		}
		
		if (ruta == null || ruta.isBlank() || ruta.isEmpty()) {
			ruta = "src/images/Chica1.png";
		}
		
		String rutaPublica = this.generarRutaUnica(ruta);
		
		EntityManager em = this.emf.createEntityManager(); 
		EntityTransaction transaction = em.getTransaction();
		
		try {
	        transaction.begin();

	        // Crear y persistir el nuevo proveedor
	        Cliente nuevo = new Cliente(nickname, nombre, apellido, email, fechaNac, rutaPublica, contrasenia1);
	        em.persist(nuevo);

	        transaction.commit();

	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;

	    } finally {
	        em.close();
	    }
		
		if (imagen != null && imagen.length != 0) {
			this.guardarImagenWeb(imagen, rutaPublica);
		} else {
			this.guardarImagen(ruta, rutaPublica);
		}
        return true;
		
	}
	
	@Override
	public boolean registro(String nickname, String email) throws UsuarioRepetidoException {
		
	    List<String> errores = new ArrayList<>();
	    
	    // Validar nickname
	    if (nickname == null || nickname.trim().isEmpty()) {
	        errores.add("El nickname no puede ser vacio");
	    }
	    
	    // Validar email
	    if (email == null || email.trim().isEmpty() || !Pattern.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", email)) {
	        errores.add("Por favor, introduce un correo electrónico válido.");
	    }
	    
	    // Si hay errores tirar excepción
	    if (!errores.isEmpty()) {
	        throw new IllegalArgumentException("Errores de validación: " + String.join(", ", errores));
	    }
	    
		for (Usuario user : this.usuarios) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el email " + '"' + email + '"' + '.');
			}
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el nickname " + '"' + nickname + '"' + '.');
			}
		}
		return true;
	}
	
	@Override
	public boolean altaUsuarioProveedor(String nickname, String email, String nombre, String apellido, DTFecha fechaNac, String nomCompania, String linkWeb, String ruta, byte[] imagen, String contrasenia1, String contrasenia2) throws UsuarioRepetidoException, ContraseniaIncorrectaException, IOException {
		
		// Lista para almacenar los errores
	    List<String> errores = new ArrayList<>();

	    // Validar nombre
	    if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3 || !validarNombreSinNumeros(nombre)) {
	        errores.add("El nombre debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar apellido
	    if (apellido == null || apellido.trim().isEmpty() || apellido.length() < 3 || !validarNombreSinNumeros(apellido)) {
	        errores.add("El apellido debe tener al menos 3 caracteres y no puede contener números.");
	    }

	    // Validar contraseñas
	    if (contrasenia1 == null || contrasenia1.length() < 8 || contrasenia2 == null || contrasenia2.length() < 8) {
	        errores.add("La contraseña debe tener al menos 8 caracteres.");
	    } else if (!contrasenia1.equals(contrasenia2)) {
	        errores.add("Las contraseñas no coinciden.");
	    }

	    // Validar fecha de nacimiento
	    if (fechaNac == null) {
	        errores.add("Se debe ingresar una fecha de nacimiento válida.");
	    }

	    // Validar URL del sitio web si es proporcionada
	    if (linkWeb != null && !linkWeb.trim().isEmpty() && !validarUrl(linkWeb)) {  // Validar el correo) {
	        errores.add("La URL del sitio web no es válida.");
	    }
	    
	    // Validar URL del sitio web obligatoria
	    if (linkWeb == null || linkWeb.trim().isEmpty()) {
	        errores.add("La URL del sitio web es obligatoria.");
	    }

	    // Validar nombre de la compañía
	    if (nomCompania == null || nomCompania.trim().isEmpty()) {
	        errores.add("La compañía es obligatoria.");
	    }

	    // Si hay errores tirar excepción
	    if (!errores.isEmpty()) {
	        throw new IllegalArgumentException("Errores de validación: " + String.join(", ", errores));
	    }
	    
		for (Usuario user : this.usuarios) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el email " + '"' + email + '"' + '.');
			}
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				throw new UsuarioRepetidoException("Ya existe un usuario registrado con el nickname " + '"' + nickname + '"' + '.');
			}
		}
		
		if (!contrasenia1.equals(contrasenia2)) {
			throw new ContraseniaIncorrectaException("Las contraseñas no coinciden.");
		}
		
		if (ruta == null || ruta.isBlank() || ruta.isEmpty()) {
			ruta = "src/images/Chica1.png";
		}
		
		String rutaPublica = this.generarRutaUnica(ruta);
		
		EntityManager em = this.emf.createEntityManager(); 
		EntityTransaction transaction = em.getTransaction();
		
		try {
	        transaction.begin();

	        // Crear y persistir el nuevo proveedor
	        Proveedor nuevo = new Proveedor(nickname, nombre, apellido, email, fechaNac, rutaPublica, nomCompania, linkWeb, contrasenia1);
       
	        em.persist(nuevo);

	        transaction.commit();

	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return false;

	    } finally {
	        em.close();
	    }
		
		if (imagen != null && imagen.length != 0) {
			this.guardarImagenWeb(imagen, rutaPublica);
		} else {
			this.guardarImagen(ruta, rutaPublica);
		}
        return true;
		
	}
	
	public boolean validarNombreSinNumeros(String nombre) {
	    return !nombre.matches(".*\\d.*");
	}

	// Método para validar la URL
	public boolean validarUrl(String url) {
	    String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(:[0-9]+)?(/.*)?$";
	    return url.matches(regex);
	}
	
	@Override
	public boolean registrarProducto(String nickname, String titulo, int numReferencia, String descrip, List<String> especificaciones, float precio, List<Categoria> categorias, List<String> rutas, List<byte[]> imagenes, String nombreTienda) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, UsuarioNoExisteException, IOException {
		Usuario user;
	
		user = this.getUsuarioPorNick(nickname);
		
		Proveedor proveedor = (Proveedor) user;
		String nomCompania = proveedor.getnomCompania();
		
		this.crearCasos();
		
		try {
			this.existeProducto(titulo, numReferencia);
		} catch (ProductoRepetidoException e) {
			throw new ProductoRepetidoException(e.getMessage());
		}
		
		String categoriasSinProductos = "";
		for(Categoria cat : categorias) {
			if(!cat.isTieneProductos()) {
				if(categoriasSinProductos != "") {
					categoriasSinProductos += ", ";
				}
				categoriasSinProductos += cat.getNombreCat();
			}
		}
		
		if (categoriasSinProductos != "") {
	        throw new CategoriaNoPuedeTenerProductosException("Las categorías " + categoriasSinProductos + " no puede contener productos.");
	    }
		
		List<String> rutasNuevas = this.generarRutasUnicas(rutas);
		
		Producto prod = null;
		
		EntityManager em = this.emf.createEntityManager();
	    EntityTransaction transaction = em.getTransaction();
		
		try {
			transaction.begin();
			
			List<Categoria> listaCatDB = new ArrayList<>();
			for (Categoria cat : categorias) {
    			Categoria catDB = em.find(Categoria.class, cat.getNombreCat());
    			listaCatDB.add(catDB);
    		} 

			prod = new Producto(titulo, descrip, especificaciones, numReferencia, precio, rutasNuevas, categorias, proveedor, nomCompania);

			proveedor.agregarProducto(prod);
			
			this.agregarProductoACategorias(listaCatDB, prod);
			
			em.persist(prod);
		  	transaction.commit();
		        
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
		
		if (imagenes != null && imagenes.size() != 0) {
			this.guardarImagenesWeb(imagenes, rutasNuevas);
		} else {
			this.guardarImagenes(rutas, rutasNuevas);
		}
		
		return true;
	}
	
	
	@Override
	public DTProductoDetallado verInformacionProducto() {
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		return this.productoActual.getDTProductoDetallado();
	}

	@Override
	public List<DTCategoria> listarCategorias(){
		List<DTCategoria> lista = new ArrayList<>();
		for (Categoria cat : this.categorias.values()) {
			DTCategoria dt = cat.getDTCategoria();
			lista.add(dt);
		}
		return lista;
	}
	

	@Override
	public boolean elegirCategoria(String nombreCat) throws CategoriaNoExisteException {
	    Categoria cat = buscarCategoriaRecursivamente(nombreCat, new ArrayList<>(this.categorias.values()));
	    if (cat == null) {
	        throw new CategoriaNoExisteException("La categoría de nombre " + '"' + nombreCat + '"' + " no existe.");
	    }
	    this.categoriaActual = cat;
	    return true;
	}

	@Override
	public Categoria buscarCategoriaRecursivamente(String nombreCat, List<Categoria> categorias) {
		if (!categorias.isEmpty()) {
		    for (Categoria cat : categorias) {
		        if (cat.getNombreCat().equals(nombreCat)) {
		            return cat;
		        }
		        Categoria catEncontrada = buscarCategoriaRecursivamente(nombreCat, cat.getHijos());
		        if (catEncontrada != null) {
		            return catEncontrada;
		        }
		    }
		}
	    return null;
	}
	
	@Override
	public boolean existeCategoria(String nombreCategoria) {
        for (Categoria categoria : getCategorias().values()) {
            if (categoria.getNombreCat().equals(nombreCategoria) || existeCategoriaRecursivamente(categoria, nombreCategoria)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeCategoriaRecursivamente(Categoria categoria, String nombreCategoria) {
        for (Categoria hijo : categoria.getHijos()) {
            if (hijo.getNombreCat().equals(nombreCategoria) || existeCategoriaRecursivamente(hijo, nombreCategoria)) {
                return true;
            }
        }
        return false;
    }

	@Override
	public List<DTProducto> listarProductos(){
		
		this.crearCasos();
		
		if (this.categoriaActual == null) {
			throw new NullPointerException("No se ha elegido una categoría previamente.");
		}
		List<DTProducto> lista = this.categoriaActual.listarProductos();
		return lista;
	}
	
	@Override
	public Collection<DTProductoDetallado> listarAllProductos(){
		
		this.crearCasos();
		
		HashMap<Integer, DTProductoDetallado> lista = new HashMap<>();
		
		for (Producto prod : this.getProductos()) {
			DTProductoDetallado dt = prod.getDTProductoDetallado();
			if (!lista.containsKey(dt.getNumReferencia())) {
				lista.put(dt.getNumReferencia(), dt);
			}
		}
		
		return lista.values();
	}

	@Override
	public boolean elegirProducto(String nombreProd) throws ProductoNoExisteException {
		this.crearCasos();
		if (this.categoriaActual == null) {
			throw new NullPointerException("No se ha elegido una categoría previamente.");
		}
		Producto prod = this.categoriaActual.seleccionarProducto(nombreProd);
		if (prod == null) {
			throw new ProductoNoExisteException("El producto de nombre " + '"' + nombreProd + '"' + " no existe.");
		}
		this.productoActual = prod;
		return true;
	}

	
	@Override
	public Categoria altaCategoria(String nombre, boolean tieneProductos, Categoria padre) throws CategoriaRepetidaException {
	    this.crearCasos();

	    if (this.categorias.containsKey(nombre)) {
	        throw new CategoriaRepetidaException("Ya existe una categoría con el nombre " + '"' + nombre + '"' + '.');
	    }
	    
	    Categoria cat = new Categoria(nombre, tieneProductos, padre);
	    EntityManager em = this.emf.createEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
	        transaction.begin();

	        // Si padre no es null, asegúrate de que está en el contexto de persistencia
	        if (padre != null) {
	            // Reacerca al padre desde la base de datos para asegurar que está administrado
	            padre = em.merge(padre);
	            padre.agregarHijo(cat); // Llama a agregarHijo ahora, con el padre administrado
	        } else {
	            this.categorias.put(nombre, cat); // Agregar a la colección
	        }

	        em.persist(cat);
	        transaction.commit();
	        
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }

	    return cat;
	}

	@Override
	public List<DTOrdenDeCompra> listarOrdenesDeCompra(){
		this.crearCasos();
		List<DTOrdenDeCompra> lista = new ArrayList<>();
		if (this.usuarioActual != null && (this.usuarioActual instanceof Cliente)) {
			Cliente cli = (Cliente) this.usuarioActual;
			if (cli.getOrdenesDeCompras() == null || cli.getOrdenesDeCompras().isEmpty()) {
				return lista;
			}
			for (OrdenDeCompra ord : cli.getOrdenesDeCompras()) {
				DTOrdenDeCompra dt = ord.getDTOrden();
				lista.add(dt);
			}
		} else {
			for (OrdenDeCompra ord : this.ordenes.values()) {
				DTOrdenDeCompra dt = ord.getDTOrden();
				lista.add(dt);
			}
		}
		return lista;
	}
	

	@Override
	public boolean elegirOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException {
        OrdenDeCompra ord = this.ordenes.get(numero);
        if (ord == null) {
            throw new OrdenDeCompraNoExisteException("La orden de compra número " + '"' + numero + '"' + " no existe.");
        }
        this.ordenActual = ord;
        return true;
	}
	
	
	// FUNCION VER INFORMACION ORDEN DE COMPRA
	
	@Override
	public DTOrdenDeCompraDetallada verInformacionOrdenDeCompra() {
		if (this.ordenActual == null) {
			throw new NullPointerException("No se ha elegido una orden de compra previamente.");
		}
		return this.ordenActual.getDTOrdenDetallada();
	}
	
	
	// FUNCION GENERAR CODIGO ORDEN
	
	@Override
	public OrdenDeCompra agregarOrden(List<Cantidad> cantidad) {
	    if (this.usuarioActual instanceof Cliente) {
	        Cliente clienteActual = (Cliente) this.usuarioActual;
	        
	        int codigoOrden = this.generarCodigoOrden();
	        OrdenDeCompra nueva = new OrdenDeCompra(codigoOrden, this.getFechaActual(), clienteActual, null);
	        nueva.setDetallesEnvio(this.getDetallesEnvioActual());
	        nueva.setFormaPago(this.getFormaPagoActual());
	        this.setDetallesEnvioActual(null);
	        this.setFormaPagoActual(null);
	        
	        List<Proveedor> listaProveedores = new ArrayList<>();
	        
	        float sumPrecio = 0;
	        if (cantidad != null && !(cantidad.isEmpty())) {
				for (Cantidad can : cantidad) {
					float subtotal = can.getCantidad() * can.getProducto().getPrecio();
					sumPrecio += subtotal;
				}
			}
	        
	        nueva.setPrecioTotal(sumPrecio);
	        
	        List<String> elementos = new ArrayList<>();

	        for (Cantidad cant : cantidad) {
	        	Proveedor prov = cant.getProducto().getProveedor();
	        	
	            nueva.agregarProducto(cant.getProducto().getDTProducto(), cant.getCantidad());
	            
	            if (!listaProveedores.contains(prov))
            		listaProveedores.add(prov);
	            
	            cant.getProducto().getProveedor().getOrdenesDeCompras().add(nueva);
	            
	            elementos.add(cant.getDTCantidad().toString());
	        }
	        
	        nueva.setProveedores(listaProveedores);
	        
	        boolean notificar = false;
	        
	        // Persistencia de la orden
	        EntityManager em = emf.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(nueva);
	            em.getTransaction().commit();
	            if(clienteActual.getNotificaciones()) {
	            	notificar = true;
	            }
	            
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            throw e; // O maneja el error de manera adecuada
	        } finally {
	            em.close();
	        }
	        
	        if (notificar) {
	        	
	        	// Email para el cliente
	        	String toAddressCli = clienteActual.getEmail();
	            String subjectCli = "Orden de Compra creada con éxito";
	            StringBuilder htmlMessageCli = new StringBuilder();
	
	            htmlMessageCli.append("<html><body>")
	                    .append("<h2>Notificación de Flamin-Goo</h2>")
	                    .append("<p>Buenas, ").append(clienteActual.getNickname())
	                    .append(". Se ha <b>creado</b> una orden de compra asociada a tu cuenta. A continuación se listan cada uno de los items:</p>")
	                    .append("<ul>");
	            for (String str : elementos) {
	            	htmlMessageCli.append("<li>").append(str).append("</li>");
	            }

	            String ip = null;
	            Properties propiedadesServer = new Properties();
	            try (InputStream input = new FileInputStream("src/propiedades/server.properties")) {
	            	propiedadesServer.load(input);
	            	ip = propiedadesServer.getProperty("server.central.url");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            htmlMessageCli.append("</ul>")
	                    .append("<p>Si deseas dejar de recibir notificaciones, haz clic en el siguiente enlace:</p>")
	                    .append("<a href='" + ip + "/backend_lab_pa/infoUsuario?method=POST&recibirNotificaciones=0'>Desactivar Notificaciones</a>")
	                    .append("</body></html>");
	
	            // Creamos un hilo con el objetivo de que el programa no se detenga a esperar al envío del email
	            new Thread(() -> {
	                try {
	                	// Enviar el correo
	    	            new ServicioEmail().sendEmail(toAddressCli, subjectCli, htmlMessageCli.toString());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }).start();

	        }

	        this.ordenes.put(codigoOrden, nueva);
	        
	        List<OrdenDeCompra> ordenes = clienteActual.getOrdenesDeCompras();
	        ordenes.add(nueva);
	        clienteActual.setOrdenesDeCompras(ordenes);
	        return nueva;
	    } else {
	        throw new IllegalArgumentException("El usuario actual no es un cliente.");
	    }
	}

	@Override
	public int generarCodigoOrden() {
		this.crearCasos();
		if (this.ordenes.isEmpty()) {
			return 1;
		}
		int numero = 0;
		for (int key : this.ordenes.keySet()) {
			if (key >= numero) {
				numero = key+1;
			}
		}
		return numero;
	}
	
	@Override
	public int generarIdComentario() {
		if (this.productos.isEmpty()) {
			return 0;
		}
		int numero = 0;
		for (Producto prod : this.productos) {
			for(Comentario com : prod.getComentarios()) {
				if (com.getId() >= numero) {
					numero = com.getId();
				}
			}
		}
		return numero + 1;
	}
	
	@Override
	public Usuario getUsuarioPorNick(String nickname) throws UsuarioNoExisteException {
		this.crearCasos();
		for(Usuario user : this.getUsuarios()) {
			if(user.getNickname().equals(nickname)) {
				return user;
			}
		}
		throw new UsuarioNoExisteException("No se encontró un usuario con el nickname proporcionado");
	}

	@Override
	public void actualizarClienteNoti(boolean notif, String nickname) throws UsuarioNoExisteException {
		Usuario usuarioProporcionado = this.getUsuarioPorNick(nickname);
	    if (usuarioProporcionado != null && usuarioProporcionado instanceof Cliente) {
	        
	        EntityManager em = this.emf.createEntityManager();
	        EntityTransaction transaction = em.getTransaction();
	        
	        try {
	            transaction.begin();
	            
	            int rowsUpdated = em.createNativeQuery(
	                    "UPDATE cliente SET notificaciones = :notificaciones WHERE nickname = :nickname")
	                    .setParameter("notificaciones", notif ? 1 : 0)
	                    .setParameter("nickname", nickname)
	                    .executeUpdate();

	            if (rowsUpdated != 0) {
	            	Cliente clienteSistema = (Cliente) usuarioProporcionado;
	            	clienteSistema.setNotificaciones(notif);
	            }
	            
	            transaction.commit();

	        } catch (RuntimeException e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    } else {
	    	return;
	    }
	}

	// FUNCION CANCELAR ORDEN DE COMPRA
	
	@Override
	public void cancelarOrdenDeCompra(int numero) throws OrdenDeCompraNoExisteException {    
        EntityManager em = this.emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        this.crearCasos();
        
        boolean notificar = false;
        Cliente cli = null;
        List<String> elementos = new ArrayList<>();
        
        try {
            transaction.begin();
            
            OrdenDeCompra ordenJPA = em.find(OrdenDeCompra.class, numero);
            
            if(ordenJPA == null) {
            	throw new OrdenDeCompraNoExisteException("La orden de compra número " + '"' + numero + '"' + " no existe.");
            }
            
            cli = ordenJPA.getCliente();
            
            for(DTCantidad cant : ordenJPA.getCantidad()) {
            	elementos.add(cant.toString());
            }
            	
        	for(Proveedor prov : ordenJPA.getProveedores()) {
        		prov.getOrdenesDeCompras().remove(ordenJPA);
        	}
        	ordenJPA.getProveedores().clear();
        	
        	em.createQuery("DELETE FROM DTCantidad c WHERE c.ordenPadre.numero = :numero").setParameter("numero", numero).executeUpdate();
        	
        	em.createQuery("DELETE FROM CambioEstado c WHERE c.ordenPadre.numero = :numero").setParameter("numero", numero).executeUpdate();
        	
        	em.createQuery("DELETE FROM OrdenDeCompra o WHERE o.numero = :numero").setParameter("numero", numero).executeUpdate();

            transaction.commit();
            
            if(cli.getNotificaciones()) {
            	notificar = true;
            }
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
        	em.close();
        }
        
        if (notificar) {
        	
        	// Email para el cliente
        	String toAddress = cli.getEmail();
            String subject = "Orden de Compra eliminada";
            StringBuilder htmlMessage = new StringBuilder();

            htmlMessage.append("<html><body>")
                    .append("<h2>Notificación de Flamin-Goo</h2>")
                    .append("<p>Buenas, ").append(cli.getNickname())
                    .append(". Se ha <b>eliminado</b> una orden de compra asociada a tu cuenta. A continuación se listan cada uno de los items:</p>")
                    .append("<ul>");
            for (String str : elementos) {
                htmlMessage.append("<li>").append(str).append("</li>");
            }
            
            String ip = null;
            Properties propiedadesServer = new Properties();
            try (InputStream input = new FileInputStream("src/propiedades/server.properties")) {
            	propiedadesServer.load(input);
            	ip = propiedadesServer.getProperty("server.central.url");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            htmlMessage.append("</ul>")
                    .append("<p>Si deseas dejar de recibir notificaciones, haz clic en el siguiente enlace:</p>")
                    .append("<a href='" + ip + "/backend_lab_pa/infoUsuario?method=POST&recibirNotificaciones=0'>Desactivar Notificaciones</a>")
                    .append("</body></html>");

            // Creamos un hilo con el objetivo de que el programa no se detenga a esperar al envío del email
            new Thread(() -> {
                try {
                	// Enviar el correo
    	            new ServicioEmail().sendEmail(toAddress, subject, htmlMessage.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            
        }
        
        this.ordenActual = null;
        
        this.crearCasos();
        
        return;
	}
	
	// FUNCION AGREGAR PRODUCTO
	
	@Override
	public boolean agregarProducto(String nombreProducto, int cantidad) throws ProductoNoExisteException {
		if (cantidad < 1) {
			throw new IllegalArgumentException("La cantidad de un producto no puede ser menor a 1.");
		}
		if (this.categoriaActual == null) {
			throw new NullPointerException("No se ha elegido una categoría previamente.");
		}
		Producto prod = this.categoriaActual.seleccionarProducto(nombreProducto);
		if (prod == null) {
			throw new ProductoNoExisteException("El producto de nombre " + '"' + nombreProducto + '"' + " no existe.");
		}
		Cantidad cant = new Cantidad(prod, cantidad);
		this.listaOrden.add(cant);
		return true;
	}
	

	@Override
	public List<DTCliente> listarClientes(){

		this.crearCasos();
		
		List<DTCliente> lista = new ArrayList<>();
		for (Usuario user : this.usuarios) {
			if (user instanceof Cliente) {
				Cliente cl = (Cliente) user;
				DTCliente dt = cl.getDTCliente();
				lista.add(dt);
			}
		}
		return lista;
	}
	

	@Override
	public boolean elegirCliente(String nickname) throws UsuarioNoExisteException {
		this.crearCasos();
		
		for (Usuario user : this.usuarios) {
			if (user.getNickname().equalsIgnoreCase(nickname)) {
				if (user instanceof Cliente) {
					Cliente cli = (Cliente) user;
					this.usuarioActual = cli;
					return true;
				}
				throw new UsuarioNoExisteException("El usuario de nickname " + '"' + nickname + '"' + " existe, pero no es un cliente.");
			}
		}
		throw new UsuarioNoExisteException("El usuario de nickname " + '"' + nickname + '"' + " no existe.");
	}
	
	@Override
	public DTCliente verInformacionCliente() {
		if (this.usuarioActual == null || ! (this.usuarioActual instanceof Cliente)) {
			throw new NullPointerException("No se ha elegido un cliente previamente.");
		}
		Cliente cli = (Cliente) this.usuarioActual;
		return cli.getDTCliente();
	}
	
	//@Override
	private void quitarProductoDeCategoriasSistema(Categoria cat, int numReferenciaActual) {
		for (Categoria hijo : cat.getHijos()) {
	        hijo.getProductos().removeIf(p -> p.getNumReferencia() == numReferenciaActual);
	        quitarProductoDeCategoriasSistema(hijo, numReferenciaActual);
	    }
	}

	@Override
	public void quitarProductoDeCategorias(boolean seAgreganCategorias, int numReferenciaActual, EntityManager em) {
		this.crearCasos();
        	
    	if (seAgreganCategorias) {
			for(Categoria cat : this.categorias.values()) {
				
				Categoria catDB = em.find(Categoria.class, cat.getNombreCat());
				
				catDB.getProductos().removeIf(p -> p.getNumReferencia() == numReferenciaActual);
				quitarProductoDeCategoriasSistema(catDB, numReferenciaActual);
			}
		}
		
	}
	
	@Override
	public void agregarProductoACategorias(List<Categoria> categorias, Producto prod) throws CategoriaNoPuedeTenerProductosException {
		this.crearCasos();
		if (prod == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		
		String categoriasSinProductos = "";
		for(Categoria cat : categorias) {
			if(!cat.isTieneProductos()) {
				if(categoriasSinProductos != "") {
					categoriasSinProductos += ", ";
				}
				categoriasSinProductos += cat.getNombreCat();
			}
		}
		
		if (categoriasSinProductos != "") {
	        throw new CategoriaNoPuedeTenerProductosException("Las categorías " + categoriasSinProductos + " no puede contener productos.");
	    }
		
		if (categorias != null && ! (categorias.isEmpty())) {
			for (Categoria cat : categorias) {
				cat.agregarProducto(prod);
			}
		}
	}
	
	@Override
	public void agregarCategoriasAProducto(List<Categoria> categorias, Producto prod) throws CategoriaNoPuedeTenerProductosException {
		this.crearCasos();
		if (prod == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		String categoriasSinProductos = "";
		for(Categoria cat : categorias) {
			if(!cat.isTieneProductos()) {
				if(categoriasSinProductos != "") {
					categoriasSinProductos += ", ";
				}
				categoriasSinProductos += cat.getNombreCat();
			}
		}
		
		if (categoriasSinProductos != "") {
	        throw new CategoriaNoPuedeTenerProductosException("Las categorías " + categoriasSinProductos + " no puede contener productos.");
	    }
		if (categorias != null && ! (categorias.isEmpty())) {
			prod.setCategorias(categorias);
		}
	}
	
	@Override
	public void existeProducto(String nombreProd, int numReferencia) throws ProductoRepetidoException {
		for (Categoria categoria : this.categorias.values()) {
			try {
				buscarProductoEnCategoria(categoria, nombreProd, numReferencia);
			} catch (ProductoRepetidoException e) {
				throw new ProductoRepetidoException(e.getMessage());
			}
	    }
	}

	//@Override
	private void buscarProductoEnCategoria(Categoria categoria, String nombreProd, int numReferencia) throws ProductoRepetidoException { // Hay que probar esto
	    for (Producto producto : categoria.getProductos()) {
	        if (producto.getNombreProducto().equalsIgnoreCase(nombreProd)) {
	        	if (producto != this.productoActual) {
	        		throw new ProductoRepetidoException("Ya existe un producto con ese nombre en el sistema.");
	        	}
	        }
	        if (producto.getNumReferencia() == numReferencia){
	        	if (producto != this.productoActual) {
	        		throw new ProductoRepetidoException("Ya existe un producto con ese número de referencia en el sistema.");
	        	}
	        }
	    }

	    for (Categoria subcategoria : categoria.getHijos()) {
	    	try {
				buscarProductoEnCategoria(subcategoria, nombreProd, numReferencia);
			} catch (ProductoRepetidoException e) {
				throw new ProductoRepetidoException(e.getMessage());
			}
	    }
	}
	
	@Override
	public void modificarDatosProducto(String nombreProd, int numReferencia, String descripcion, float precio, List<String> especificacion, String nomTienda, List<Categoria> nuevasCategorias, List<String> rutasActualizadas) throws ProductoRepetidoException, CategoriaNoPuedeTenerProductosException, IOException {
		this.crearCasos();
		if (this.productoActual == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		
		if (nombreProd == null || nombreProd.equals("") || descripcion == null || descripcion.equals("") || especificacion == null || nomTienda == null || nomTienda.equals("")) {
			throw new IllegalArgumentException("Ningún campo puede estar vacío.");
		}
		if (precio <= 0) {
			throw new IllegalArgumentException("El precio elegido no puede ser menor o igual a 0.");
		}
		if (numReferencia <= 0) {
			throw new IllegalArgumentException("El número de referencia debe ser válido.");
		}
		if (especificacion.size() == 0) {
			throw new IllegalArgumentException("Debe haber al menos una especificación.");
		}
		try {
			existeProducto(nombreProd, numReferencia);
		} catch (ProductoRepetidoException e) {
			throw new ProductoRepetidoException(e.getMessage());
		}
		
		int numReferenciaOriginal = this.productoActual.getNumReferencia();
		
		Producto prod = null;
		
		List<String> nuevasRutas = this.generarRutasUnicas(rutasActualizadas);
		boolean imagenesModificadas = false;
		
		EntityManager em = this.emf.createEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
	        transaction.begin();

	        prod = em.createQuery("SELECT p FROM Producto p WHERE p.numReferencia = :num", Producto.class).setParameter("num", numReferenciaOriginal).getSingleResult();
	        
	        if (prod != null) {
	        	
	        	prod.setNombreProducto(nombreProd);
	        	prod.setNumReferencia(numReferencia);
	        	prod.setDescripcion(descripcion);
	        	prod.setEspecificacion(especificacion);
	        	prod.setPrecio(precio);
	    		prod.setNombreTienda(nomTienda);
	    		
	    		List<Categoria> listaCatDB = new ArrayList<>();
	    		
	    		for (Categoria cat : nuevasCategorias) {
	    			Categoria catDB = em.find(Categoria.class, cat.getNombreCat());
	    			listaCatDB.add(catDB);
	    		}
	    		
	    		boolean sePuedenModificarCategorias = (listaCatDB != null && ! (listaCatDB.isEmpty()));
	    		this.quitarProductoDeCategorias(sePuedenModificarCategorias, numReferenciaOriginal, em);
	    		
	    		try {
	    			this.agregarCategoriasAProducto(listaCatDB, prod);
	    			this.agregarProductoACategorias(listaCatDB, prod);
	    		} catch (CategoriaNoPuedeTenerProductosException e) {
	                throw new CategoriaNoPuedeTenerProductosException(e.getMessage());
	    		}
	    		
	    		this.borrarImagenes(prod.getImagenes());
	    		
	    		imagenesModificadas = this.modificarImagenesProducto(nuevasRutas, prod);
	    		
	        }
	        
	        transaction.commit();
	        
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	    
	    if (imagenesModificadas) {
	    	this.guardarImagenes(rutasActualizadas, nuevasRutas);
	    }
		
	}

	@Override
	public boolean modificarImagenesProducto(List<String> imagenes, Producto prod) {
		if (prod == null) {
			throw new NullPointerException("No se ha elegido un producto previamente.");
		}
		if (imagenes != null && ! (imagenes.isEmpty())) {
			prod.setImagenes(imagenes);
			return true;
		}
		return false;
	}
	
	@Override
	public List<DTProveedor> listarProveedores(){
		List<DTProveedor> lista = new ArrayList<>();
		for (Usuario user : this.usuarios) {
			if (user instanceof Proveedor) {
				Proveedor pr = (Proveedor) user;
				DTProveedor dt = pr.getDTProveedor();
				lista.add(dt);
			}
		}
		return lista;
	}
	
	@Override
	public void elegirProveedor(String nickname) throws UsuarioNoExisteException {
		this.crearCasos();
		
		EntityManager em = this.emf.createEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
		    transaction.begin();
		    Proveedor prov = em.createQuery("SELECT p FROM Proveedor p WHERE p.nickname = :nick", Proveedor.class).setParameter("nick", nickname).getSingleResult();
		    this.usuarioActual = prov;
		    transaction.commit();	    
	    }catch(NoResultException e){
	    	if(transaction.isActive()) {
	    		transaction.rollback();
	    	}
	    	 e.printStackTrace();
	    	 
	    } finally {
	        em.close();
	    }
	    
	   
	}
	
	@Override
	public DTProveedor verInformacionProveedor() {
		if (this.usuarioActual == null || ! (this.usuarioActual instanceof Proveedor)) {
			throw new NullPointerException("No se ha elegido un proveedor previamente.");
		}
		this.crearCasos();
		Proveedor pr = (Proveedor) this.usuarioActual;
		return pr.getDTProveedor();
	}
	
	public void setTodoNull() {
		this.ordenActual = null;
		this.categoriaActual = null;
		this.productoActual = null;
		this.usuarioActual = null;
		this.listaOrden.clear();
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public void crearCasos() {
		
		EntityManager em = this.emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
	
        try {
        	transaction.begin();
        	
            String jpql = "SELECT c FROM Categoria c WHERE c.padre IS NULL";
            TypedQuery<Categoria> query = em.createQuery(jpql, Categoria.class);
            List<Categoria> categorias = query.getResultList();

            this.categorias.clear();
            for(Categoria cat : categorias) {
                this.categorias.put(cat.getNombreCat(), cat);
            }
            
            String jpql1 = "SELECT c FROM Usuario c";
            TypedQuery<Usuario> query1 = em.createQuery(jpql1, Usuario.class);
            List<Usuario> usuarios = query1.getResultList();
            
            this.usuarios = usuarios;
            
            String jpql2 = "SELECT c FROM OrdenDeCompra c";
            TypedQuery<OrdenDeCompra> query2 = em.createQuery(jpql2, OrdenDeCompra.class);
            List<OrdenDeCompra> Ordenes = query2.getResultList();
            
            this.ordenes.clear();
            for(OrdenDeCompra orden : Ordenes) {
                this.ordenes.put(orden.getNumero(), orden);
            }
            
            String jpql3 = "SELECT c FROM Producto c";
            TypedQuery<Producto> query3 = em.createQuery(jpql3, Producto.class);
            List<Producto> productos = query3.getResultList();
            
            this.productos = productos;
            
            if (this.productoActual != null) {
            	this.productoActual = em.find(Producto.class, this.productoActual.getId());
            }
            
            if (this.usuarioActual != null) {
            	this.usuarioActual = em.find(Usuario.class, this.usuarioActual.getNickname());
            }
            
            if (this.ordenActual != null) {
            	this.ordenActual = em.find(OrdenDeCompra.class, this.ordenActual.getNumero());
            }
            
            if (this.categoriaActual != null) {
            	this.categoriaActual = em.find(Categoria.class, this.categoriaActual.getNombreCat());
            }
                
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
		
	}

	@Override
	public void iniciarSesion(String emailOrNickname, String password) throws ContraseniaIncorrectaException, UsuarioNoEncontrado {
		
		Usuario usuarioEncontrado = null;

		for (Usuario user : this.usuarios) {
	        if (user.getEmail().equalsIgnoreCase(emailOrNickname) || user.getNickname().equalsIgnoreCase(emailOrNickname)) 
	        {
	            usuarioEncontrado = user;
	            break;
	        }
		}
		
		if (usuarioEncontrado == null) {
			throw new UsuarioNoEncontrado("El Nickname o Email son incorrectos");
		}
		
	    if (!usuarioEncontrado.getContrasenia().equals(password)) {
	        throw new ContraseniaIncorrectaException("La contraseña es incorrecta.");
	    }

	    this.usuarioActual = usuarioEncontrado;
		
	}
	
	@Override
	public List<DTCantidad> getCarritoActual(String nickname) throws UsuarioNoExisteException {
		Usuario user = this.getUsuarioPorNick(nickname);
		if (user instanceof Cliente) {
			Cliente cli = (Cliente) user;
			return cli.getDTCarrito();
		} else {
			throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
		}
	}
	
	@Override
	public void eliminarItemCarrito(String nickname, int numReferencia) throws UsuarioNoExisteException {
		Usuario user = this.getUsuarioPorNick(nickname);
		if (user instanceof Cliente) {
			
			EntityManager em = this.emf.createEntityManager(); 
			EntityTransaction transaction = em.getTransaction();
				
			try {
		        transaction.begin();

		        Cliente cli = em.find(Cliente.class, user.getNickname());
		        cli.quitarProducto(numReferencia);

		        transaction.commit();

		    } catch (Exception e) {
		        if (transaction.isActive()) {
		            transaction.rollback();
		        }
		        e.printStackTrace();

		    } finally {
		        em.close();
		    }
			
		} else {
			throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
		}
	}
	
	@Override
	public void modificarCantidadItemCarrito(String nickname, int numReferencia, int cantidad) throws UsuarioNoExisteException {
		Usuario user = this.getUsuarioPorNick(nickname);
		if (user instanceof Cliente) {
			
			EntityManager em = this.emf.createEntityManager(); 
			EntityTransaction transaction = em.getTransaction();
				
			try {
		        transaction.begin();

		        Cliente cli = em.find(Cliente.class, user.getNickname());
		        cli.modificarCantidadItemCarrito(numReferencia, cantidad);

		        transaction.commit();

		    } catch (Exception e) {
		        if (transaction.isActive()) {
		            transaction.rollback();
		        }
		        e.printStackTrace();

		    } finally {
		        em.close();
		    }
			
		} else {
			throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
		}
	}
	
	@Override
	public void realizarCompra(String nickname, int numero, DTFecha fecha, DetallesEnvio detallesEnvio, FormaPago formaPago) throws UsuarioNoExisteException {
		Usuario user = this.getUsuarioPorNick(nickname);
		if (user instanceof Cliente) {
			
			this.crearCasos();
			
			boolean notificar = false;
			Cliente cli = null;
			List<String> elementos = new ArrayList<>();
			
			OrdenDeCompra ord = null;
			
			EntityManager em = this.emf.createEntityManager(); 
			EntityTransaction transaction = em.getTransaction();
				
			try {
		        transaction.begin();

		        cli = em.find(Cliente.class, this.usuarioActual.getNickname());
		        
		        List<Proveedor> proveedores = new ArrayList<>();
		        for (Cantidad cant : cli.getCarrito()) {
		        	Proveedor prov = cant.getProducto().getProveedor();
		        	if (!proveedores.contains(prov)) {
		        		proveedores.add(prov);
		        	}
		        }
		        
		        ord = new OrdenDeCompra(numero, fecha, cli, proveedores, cli.getDTCarrito(), formaPago, detallesEnvio);
		        cli.realizarCompra(ord);
		        
		        em.persist(ord);
		        
		        for (DTCantidad cant : ord.getCantidad()) {
		            elementos.add(cant.toString());
		        }

		        transaction.commit();
		        if(cli.getNotificaciones()) {
		        	notificar = true;
		        }

		    } catch (Exception e) {
		        if (transaction.isActive()) {
		            transaction.rollback();
		        }
		        e.printStackTrace();

		    } finally {
		        em.close();
		    }
			
			this.ordenes.put(ord.getNumero(), ord);
			
			if (notificar) {
	            
	            String toAddress = cli.getEmail();
	            String subject = "Orden de Compra creada con éxito";
	            StringBuilder htmlMessage = new StringBuilder();
	
	            htmlMessage.append("<html><body>")
	                    .append("<h2>Notificación de Flamin-Goo</h2>")
	                    .append("<p>Buenas, ").append(cli.getNickname())
	                    .append(". Se ha <b>creado</b> una orden de compra asociada a tu cuenta. A continuación se listan cada uno de los items:</p>")
	                    .append("<ul>");
	            for (String str : elementos) {
	                htmlMessage.append("<li>").append(str).append("</li>");
	            }

	            String ip = null;
	            Properties propiedadesServer = new Properties();
	            try (InputStream input = new FileInputStream("src/propiedades/server.properties")) {
	            	propiedadesServer.load(input);
	            	ip = propiedadesServer.getProperty("server.central.url");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            htmlMessage.append("</ul>")
	                    .append("<p>Si deseas dejar de recibir notificaciones, haz clic en el siguiente enlace:</p>")
	                    .append("<a href='" + ip + "/backend_lab_pa/infoUsuario?method=POST&recibirNotificaciones=0'>Desactivar Notificaciones</a>")
	                    .append("</body></html>");
	
	            // Creamos un hilo con el objetivo de que el programa no se detenga a esperar al envío del email
	            new Thread(() -> {
	                try {
	                	// Enviar el correo
	    	            new ServicioEmail().sendEmail(toAddress, subject, htmlMessage.toString());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }).start();
	            
			}
			
			return;
		} else {
			throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
		}
	}
	
	@Override
	public void setOrdenActual(OrdenDeCompra ord) {
		this.ordenActual = ord;
	}
	
	@Override
	public void setCarritoActual(List<Cantidad> car) throws UsuarioNoExisteException {
		if (this.usuarioActual != null) {
			if (this.usuarioActual instanceof Cliente) {
				Cliente cli = (Cliente) this.usuarioActual;
				cli.setCarrito(car);
				return;
			} else {
				throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
			}
		}
		throw new UsuarioNoExisteException("El usuario actual no es un Cliente.");
	}
	
	@Override
	public List<Producto> getDestacados() {
		List<Producto> listaProd = this.getProductos();
		
        List<Producto> top10Productos = listaProd.stream()
        .sorted((p1, p2) -> Integer.compare(p2.getCantCompras(), p1.getCantCompras()))
        .limit(10)
        .collect(Collectors.toList());
        
        return top10Productos;
	}
	
	@Override
	public void agregarEstadoAUnaOrden(int id, String estadoOrden) throws IllegalArgumentException {
		
		LocalDate fechaActual = LocalDate.now();
		
        int dia = fechaActual.getDayOfMonth();
        int mes = fechaActual.getMonthValue();
        int anio = fechaActual.getYear();
        
        OrdenDeCompra ord = null;
        for(OrdenDeCompra orden : this.getOrdenes().values()) {
			if(orden.getNumero() == id) {
				ord = orden;
			}
		}
        
        if(ord == null) {
        	throw new IllegalArgumentException("La orden de compra no existe");
        }

        DTFecha dtFecha = new DTFecha(dia, mes, anio);
        
        Estado est = null;
        
        switch (estadoOrden.toLowerCase()) {
        case "comprada":
        	est = Estado.comprada;
        	break;
        case "enpreparacion":
            est = Estado.enPreparacion;
            break;
        case "encamino":
        	est = Estado.enCamino;
        	break;
        case "entregada":
            est = Estado.entregada;
            break;
        default:
            throw new IllegalArgumentException("Estado no válido: " + estadoOrden);
        }
		
		this.crearCasos();
		
		EntityManager em = this.emf.createEntityManager();
	    EntityTransaction transaction = em.getTransaction();
	    
	    try {
		    transaction.begin();
		    
		    OrdenDeCompra ordenGestionada = em.contains(ord) ? ord : em.merge(ord);
	
	        ordenGestionada.agregarCambioEstado(est, dtFecha);
	        
	        ordenGestionada.setEstado(est);
		    
		    transaction.commit();	    
	    }catch(NoResultException e){
	    	if(transaction.isActive()) {
	    		transaction.rollback();
	    	}
	    	 e.printStackTrace();
	    	 
	    } finally {
	        em.close();
	    }
		
	    this.crearCasos();
	}
	
	@Override
	public void setReclamo(Proveedor proveedor, Reclamo reclamo) {
	    if (proveedor != null && reclamo != null) {
	        // System.out.println("Proveedor y Reclamo no son nulos");

	        // Clonar la lista de imágenes para evitar referencias compartidas
	        List<String> imagenesCopia = new ArrayList<>(reclamo.getProducto().getImagenes());
	        reclamo.getProducto().setImagenes(imagenesCopia);

	        // Persistir la entidad
	        EntityManager em = emf.createEntityManager();
	        // System.out.println("EntityManager creado");

	        try {
	            em.getTransaction().begin();
	            // System.out.println("Transacción iniciada");

	            em.persist(reclamo);
	            // System.out.println("Reclamo persistido");

	            em.merge(proveedor);
	            // System.out.println("Proveedor actualizado con el nuevo reclamo");

	            em.getTransaction().commit();
	            // System.out.println("Transacción commitada");

	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) {
	                em.getTransaction().rollback();
	                // System.out.println("Transacción revertida debido a error");
	            }
	            e.printStackTrace();
	        } finally {
	            em.close();
	            // System.out.println("EntityManager cerrado");
	        }

	    } else {
	        throw new IllegalArgumentException("El proveedor o el reclamo no pueden ser nulos");
	    }
	}
	
	@Override
	public List<DTUsuario> listarUsuarios() {
		this.crearCasos();
		
		List<DTUsuario> lista = new ArrayList<>();
		for (Usuario user : this.usuarios) {
			DTUsuario dt = user.getDTUsuario();
			lista.add(dt);
		}
		return lista;
	}
	
	@Override
	public DTUsuarioDetallado getDTUsuarioActual(String identificador) throws UsuarioNoExisteException {
		this.crearCasos();
		for (Usuario user : this.usuarios) {
			if (user.getNickname().equals(identificador) || user.getEmail().equals(identificador)) {
				if (user instanceof Cliente) {
					Cliente cli = (Cliente) user;
					return (DTUsuarioDetallado) cli.getDTCliente();
				} else {
					Proveedor prov = (Proveedor) user;
					return (DTUsuarioDetallado) prov.getDTProveedor();
				}
			}
		}
		throw new UsuarioNoExisteException("El usuario especificado no existe.");
	}

	@Override
	public List<DTOrdenMinima> getOrdenesMinimasUsuario(String nickname) throws UsuarioNoExisteException {
		Usuario user = this.getUsuarioPorNick(nickname);
		return user.getOrdenesMinimas();
	}
	
	@Override
	public List<DTReclamo> getDTReclamosProveedor(String nickname) throws Exception {
		Usuario user = this.getUsuarioPorNick(nickname);
		if(user instanceof Cliente) {
			throw new Exception("El usuario no es un proveedor");
		}
		
		Proveedor prov = (Proveedor) user;
		
		return prov.getDTReclamos();
	}

	@Override
	public List<DTProducto> getDTProductosProveedor(String nickname) throws Exception {
		Usuario user = this.getUsuarioPorNick(nickname);
		if(user instanceof Cliente) {
			throw new Exception("El usuario no es un proveedor");
		}
		
		Proveedor prov = (Proveedor) user;
		
		return prov.getDTProductos();
	}
	
	@Override
	public List<String> getNombresCategoriasProducto(int numReferencia) throws ProductoNoExisteException {
		for(Producto prod : this.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				return prod.getCategoriasString();
			}
		}
		throw new ProductoNoExisteException("El producto seleccionado no existe");
	}
	
	@Override
	public boolean usuarioHaCompradoProducto(String nickname, int numReferencia) throws Exception {
		Usuario user = this.getUsuarioPorNick(nickname);
		if(user instanceof Proveedor) {
			throw new Exception("El usuario no es un cliente");
		}
		Cliente cl = (Cliente) user;
		return cl.comproProducto(numReferencia);
	}
	
	@Override
	public DTProductoDetallado getDetallesProducto(int numReferencia) throws ProductoNoExisteException {
		for(Producto prod : this.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				return prod.getDTProductoDetallado();
			}
		}
		throw new ProductoNoExisteException("El producto seleccionado no existe");
	}
	
	@Override
	public List<DTComentario> obtenerComentariosProducto(int numReferencia) throws ProductoNoExisteException {
		for(Producto prod : this.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				return prod.getDTComentarios();
			}
		}
		throw new ProductoNoExisteException("El producto seleccionado no existe");
	}
	
	@Override
	public void agregarComentarioAProducto(int numReferencia, String nickname, String texto, int estrellas, String nickRespuesta, int idRespuesta) throws ProductoNoExisteException, UsuarioNoExisteException {
		
		Usuario user = this.getUsuarioPorNick(nickname);
		Cliente cliente = (Cliente) user;
		for(Producto prod : this.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				
				LocalDate fechaActual = LocalDate.now();
	            DTFecha fecha = new DTFecha(fechaActual.getDayOfMonth(), fechaActual.getMonthValue(), fechaActual.getYear());
	            
	            Comentario nuevoComentario = new Comentario(this.generarIdComentario(), texto, cliente, prod, fecha, estrellas, "", 0);
	            
				prod.agregarComentario(nuevoComentario);
				
				EntityManager em = emf.createEntityManager();

		        try {
		            em.getTransaction().begin();

		            em.persist(nuevoComentario);
		            
		            prod.actualizarCantEstrellas();
		            
		            em.merge(prod);
		            
		            em.getTransaction().commit();

		        } catch (Exception e) {
		            if (em.getTransaction().isActive()) {
		                em.getTransaction().rollback();
		            }
		            e.printStackTrace();
		        } finally {
		            em.close();
		        }
				
				return;
			}
		}
		throw new ProductoNoExisteException("El producto seleccionado no existe");
	}
	
	@Override
	public void agregarRespuestaAUnComentario(int numReferencia, String nickname, String texto, int idComentario, String nickRespuesta, int idRespuesta) throws ProductoNoExisteException, UsuarioNoExisteException {
		
		Usuario user = this.getUsuarioPorNick(nickname);
		Cliente cliente = (Cliente) user;
		for(Producto prod : this.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				
				LocalDate fechaActual = LocalDate.now();
	            DTFecha fecha = new DTFecha(fechaActual.getDayOfMonth(), fechaActual.getMonthValue(), fechaActual.getYear());
	            
	            String nick = "";
	            
	            for(Comentario com : prod.getComentarios()) {
	            	if(com.getId() == idRespuesta) {
	            		nick = com.getCliente().getNickname();
	            	}
	            }
	            
	            if(nick == "") {
	        		throw new ProductoNoExisteException("El producto seleccionado no existe");
	            }
	            
	            Comentario nuevoComentario = new Comentario(this.generarIdComentario(), texto, cliente, prod, fecha, 0, nick, idRespuesta);
	            
	            prod.agregarComentario(nuevoComentario);
	            	            
	            EntityManager em = emf.createEntityManager();

		        try {
		            em.getTransaction().begin();

		            em.persist(nuevoComentario);
		            
		            prod.actualizarCantEstrellas();
		            
		            em.merge(prod);

		            em.getTransaction().commit();

		        } catch (Exception e) {
		            if (em.getTransaction().isActive()) {
		                em.getTransaction().rollback();
		            }
		            e.printStackTrace();
		        } finally {
		            em.close();
		        }
	            
	            return;
			}
		}
		throw new ProductoNoExisteException("El producto seleccionado no existe");
	}
	
	@Override
	public byte[] getImagen(String ruta) throws IOException {
		byte[] byteArray = null;
		try {
	        File f = new File(ruta);
	        try (FileInputStream streamer = new FileInputStream(f)) {
				byteArray = new byte[streamer.available()];
				streamer.read(byteArray);
			}
		} catch (IOException e) {
	        throw e;
		}
		return byteArray;
	}
	
	@Override
	public List<byte[]> getImagenes(List<String> rutas) throws IOException {
		List<byte[]> byteArray = new ArrayList<>();
		for (String ruta : rutas) {
			try {
		        File f = new File(ruta);
		        
		        try (FileInputStream streamer = new FileInputStream(f)) {
					byteArray.add(new byte[streamer.available()]);
					streamer.read(byteArray.getLast());
				}
			} catch (IOException e) {
		        throw e;
			}
		}
		return byteArray;
	}
	
	@Override
	public String generarRutaUnica(String nuevaImagen) {
		if (nuevaImagen == null || nuevaImagen.trim().isEmpty()) {
	        throw new IllegalArgumentException("La ruta de la imagen no puede ser nula o vacía.");
	    }
		
		Path pathImagen = Paths.get(nuevaImagen);

		File fileToUpload = null;
		
	    if (Files.exists(pathImagen) && Files.isRegularFile(pathImagen)) {
	    	fileToUpload = new File(nuevaImagen);
		    if (!fileToUpload.exists() || !fileToUpload.isFile()) {
		        throw new IllegalArgumentException("El archivo de origen no existe: " + nuevaImagen);
		    }
	    }

	    String carpetaDestino = "src/images/";
	    String nombreArchivo;
	    if (fileToUpload != null) {
	    	nombreArchivo = fileToUpload.getName();
	    } else {
	    	nombreArchivo = nuevaImagen;
	    }
	    String nombreSinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
	    String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));

	    String nuevoNombre = nombreArchivo;
	    int contador = 1;

	    while (new File(carpetaDestino + nuevoNombre).exists()) {
	        nuevoNombre = nombreSinExtension + "_" + contador + extension;
	        contador++;
	    }

	    return carpetaDestino + nuevoNombre;
	}
	
	@Override
	public void guardarImagen(String rutaOriginal, String rutaGenerada) throws IOException {
		if (rutaOriginal == null || rutaOriginal.trim().isEmpty()) {
	        throw new IllegalArgumentException("La ruta de la imagen original no puede ser nula o vacía.");
	    }
	    if (rutaGenerada == null || rutaGenerada.trim().isEmpty()) {
	        throw new IllegalArgumentException("La ruta generada no puede ser nula o vacía.");
	    }

	    File fileToUpload = new File(rutaOriginal);
	    if (!fileToUpload.exists() || !fileToUpload.isFile()) {
	        throw new FileNotFoundException("El archivo original no existe: " + rutaOriginal);
	    }

	    File destinoFinal = new File(rutaGenerada);

	    // Crear directorios si no existen
	    File directorioDestino = destinoFinal.getParentFile();
	    if (!directorioDestino.exists()) {
	        directorioDestino.mkdirs();
	    }

	    // Copiar el archivo
	    try {
	        Files.copy(fileToUpload.toPath(), destinoFinal.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    } catch (IOException e) {
	        throw new IOException("Error al guardar la imagen: " + e.getMessage(), e);
	    }
	}
	
	@Override
	public void guardarImagenWeb(byte[] datosImagen, String rutaGenerada) throws IOException {
		if (datosImagen == null || datosImagen.length == 0) {
	        throw new IllegalArgumentException("Los datos de la imagen no pueden ser nulos o vacíos.");
	    }
	    if (rutaGenerada == null || rutaGenerada.trim().isEmpty()) {
	        throw new IllegalArgumentException("La ruta generada no puede ser nula o vacía.");
	    }

	    String carpetaDestino = "src/images/";
	    File destinoFinal = new File(carpetaDestino + new File(rutaGenerada).getName());

	    // Crear directorios si no existen
	    File directorioDestino = destinoFinal.getParentFile();
	    if (!directorioDestino.exists()) {
	        directorioDestino.mkdirs();
	    }

	    try {
	        // Escribir los bytes de la imagen en el archivo de destino
	        Files.write(destinoFinal.toPath(), datosImagen);
	    } catch (IOException ioException) {
	        throw new IOException("Error al guardar la imagen: " + ioException.getMessage(), ioException);
	    }
	}
	
	@Override
	public List<String> generarRutasUnicas(List<String> nuevasImagenes) {
	    List<String> rutasUnicas = new ArrayList<>();
	    String carpetaDestino = "src/images/";

	    if (nuevasImagenes != null && !nuevasImagenes.isEmpty()) {
	        for (String imagen : nuevasImagenes) {
	        	if (imagen != null && !imagen.isBlank() && !imagen.isEmpty()) {
		        	Path pathImagen = Paths.get(imagen);
		        	File fileToUpload = null;
		        	
		        	if (Files.exists(pathImagen) && Files.isRegularFile(pathImagen)) {
		    	    	fileToUpload = new File(imagen);
		    		    if (!fileToUpload.exists() || !fileToUpload.isFile()) {
		    		        throw new IllegalArgumentException("El archivo de origen no existe: " + imagen);
		    		    }
		    	    }
	
		            // Obtener el nombre y extensión
		            String nombreArchivo = null;
		            if (fileToUpload != null) {
		    	    	nombreArchivo = fileToUpload.getName();
		    	    } else {
		    	    	nombreArchivo = imagen;
		    	    }
		            String nombreSinExtension = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));
		            String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf('.'));
	
		            // Generar un nombre único
		            String nuevoNombre = nombreArchivo;
		            int contador = 1;
	
		            // Mientras el archivo exista, genera un nuevo nombre
		            while (new File(carpetaDestino + nuevoNombre).exists()) {
		                nuevoNombre = nombreSinExtension + "_" + contador + extension;
		                contador++;
		            }
	
		            // Agregar la nueva ruta en formato relativo (src/images/...)
		            String rutaPublica = "src/images/" + nuevoNombre;
		            rutasUnicas.add(rutaPublica);
	        	}
	        }
	    }
	    return rutasUnicas;
	}
	
	@Override
	public void guardarImagenes(List<String> rutasOriginales, List<String> rutasGeneradas) throws IOException {
	    String carpetaDestino = "src/images/";

	    if (rutasOriginales != null && rutasGeneradas != null && rutasOriginales.size() == rutasGeneradas.size()) {
	        for (int i = 0; i < rutasOriginales.size(); i++) {
	        	if (rutasOriginales.get(i) != null && !rutasOriginales.get(i).isBlank() && !rutasOriginales.get(i).trim().isEmpty()) {
		            String rutaOriginal = rutasOriginales.get(i);
		            String rutaGenerada = rutasGeneradas.get(i);
	
		            File fileToUpload = new File(rutaOriginal);
		            File destinoFinal = new File(carpetaDestino + new File(rutaGenerada).getName());
	
		            try {
		                // Copiar el archivo a la carpeta de destino
		                Files.copy(fileToUpload.toPath(), destinoFinal.toPath(), StandardCopyOption.REPLACE_EXISTING);
		            } catch (IOException ioException) {
		            	throw new IOException("Error al guardar la imagen: " + ioException.getMessage());
		            }
	        	}
	        }
	    } else {
	    	return;
	    }
	}
	
	@Override
	public void guardarImagenesWeb(List<byte[]> datosImagenes, List<String> rutasGeneradas) throws IOException {
		
		if (datosImagenes != null && !datosImagenes.isEmpty() && rutasGeneradas != null && !rutasGeneradas.isEmpty() && datosImagenes.size() == rutasGeneradas.size()) {
			
			for (int i = 0; i < datosImagenes.size(); i++) {
				
				if (datosImagenes.get(i) != null && datosImagenes.get(i).length != 0 && rutasGeneradas.get(i) != null && !rutasGeneradas.get(i).isBlank() && !rutasGeneradas.get(i).isEmpty()) {
				
					if (datosImagenes.get(i) == null || datosImagenes.get(i).length == 0) {
				        throw new IllegalArgumentException("Los datos de la imagen no pueden ser nulos o vacíos.");
				    }
				    if (rutasGeneradas.get(i) == null || rutasGeneradas.get(i).trim().isEmpty()) {
				        throw new IllegalArgumentException("La ruta generada no puede ser nula o vacía.");
				    }
		
				    String carpetaDestino = "src/images/";
				    File destinoFinal = new File(carpetaDestino + new File(rutasGeneradas.get(i)).getName());
		
				    // Crear directorios si no existen
				    File directorioDestino = destinoFinal.getParentFile();
				    if (!directorioDestino.exists()) {
				        directorioDestino.mkdirs();
				    }
		
				    try {
				        // Escribir los bytes de la imagen en el archivo de destino
				        Files.write(destinoFinal.toPath(), datosImagenes.get(i));
				    } catch (IOException ioException) {
				        throw new IOException("Error al guardar la imagen: " + ioException.getMessage(), ioException);
				    }
			    
				}
		    
			}
			
		}
		
	}
	
	@Override
	public void borrarImagenes(List<String> imagenesAnteriores) throws IOException {
    	
    	if (imagenesAnteriores != null && !imagenesAnteriores.isEmpty()) {
    		for (String rutaImagen : imagenesAnteriores) {
    			Path path = Paths.get(rutaImagen);
                try {
                    // Eliminar la imagen si existe
                    Files.deleteIfExists(path);
                    
                } catch (IOException e) {
                	throw new IOException("Error al eliminar la imagen: " + path.toString());
                }

    		}
    	}
	}
	
	@Override
	public DTOrdenDeCompra getOrdenPorId(int id) throws Exception {
		for(OrdenDeCompra orden : this.getOrdenes().values()) {
			if(orden.getNumero() == id) {
				return orden.getDTOrden();
			}
		}
		throw new Exception("La orden no existe");
	}
	
	@Override
	public List<DTCantidad> getDTCantidadOrden(int id) throws Exception {
		return this.getOrdenPorId(id).getCantidades();
	}
	
	@Override
	public List<DTCambioEstado> getEstadosOrden(int id) throws Exception {
		for(OrdenDeCompra orden : this.getOrdenes().values()) {
			if(orden.getNumero() == id) {
				return orden.getDTCambioEstado();
			}
		}
		throw new Exception("La orden no existe");
	}
	
	@Override
	public DetallesEnvio getDetallesEnvio(int id) throws Exception {
		for(OrdenDeCompra orden : this.getOrdenes().values()) {
			if(orden.getNumero() == id) {
				return orden.getDetallesEnvio();
			}
		}
		throw new Exception("La orden no existe");
	}

	@Override
	public void agregarAlCarrito(int numReferencia, int cantidad, String nickname) throws Exception {
		Usuario user = this.getUsuarioPorNick(nickname);
		if(user instanceof Proveedor) {
			throw new Exception("El usuario no es un cliente");
		}
		
		Cliente cliente = (Cliente) user;
		
		Producto producto = null;
		
		for(Producto prod : this.getProductos()) {
			if(prod.getNumReferencia() == numReferencia) {
				producto = prod;
			}
		}
		
		if(producto == null) {
			throw new Exception("El producto no existe");
		}
		
		Cantidad cant = new Cantidad(producto, cantidad);
		
		cliente.agregarProducto(cant);
		
		EntityManager em = emf.createEntityManager();
		
		try {
            em.getTransaction().begin();
            
            em.merge(cliente);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

	}
	
	//Devuelve los productos
	@Override
	public List<DTProducto> listarDTProductos() {
	    // Crear casos para inicializar datos
	    this.crearCasos();

	    // Lista para almacenar los productos únicos
	    List<DTProducto> lista = new ArrayList<>();
	   
	    // Iterar sobre la lista de productos
	    for (Producto prod : this.getProductos()) {
	        DTProducto dt = prod.getDTProducto();
	        lista.add(dt);
	    }

	    return lista;
	}
	
	@Override
	public List<DTCategoria> listarCategoriasConProductos() {
		List<DTCategoria> lista = new ArrayList<>();
		for(Categoria cat : this.getCategorias().values()) {
			if(cat.isTieneProductos()) {
				lista.add(cat.getDTCategoria());
			}
		}
		return lista;
	}
}