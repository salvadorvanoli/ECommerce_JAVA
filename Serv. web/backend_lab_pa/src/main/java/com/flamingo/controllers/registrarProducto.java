package com.flamingo.controllers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.CategoriaNoExisteException_Exception;
import services.CategoriaNoPuedeTenerProductosException_Exception;
import services.DtCategoria;
import services.DtProducto;
import services.DtUsuario;
import services.DtUsuarioDetallado;
import services.IOException_Exception;
import services.ProductoNoExisteException_Exception;
import services.ProductoRepetidoException_Exception;
import services.Publicador;
import services.PublicadorService;
import services.UsuarioNoExisteException_Exception;

@WebServlet ("/registrarProducto")
public class registrarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public registrarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ProductoNoExisteException_Exception, CategoriaNoExisteException_Exception {
    	
    	// Si se ingresa desde un dispositivo movil, se redirige a home.
    	String userAgent = request.getHeader("User-Agent");
    	if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
    		response.sendRedirect("home");
    		return;
    	}
        
    	Publicador port;
		
		if (getServletContext().getAttribute("publicador") == null) {
			PublicadorService service = new PublicadorService();
			port = service.getPublicadorPort();
			getServletContext().setAttribute("publicador", port);
		} else {
			port = (Publicador) getServletContext().getAttribute("publicador");
		}
		port.crearCasos();
		
		// Obtener la sesión actual
		HttpSession session = request.getSession();

		// Obtener el usuario desde la sesión
		Object usuario = session.getAttribute("usuarioActual");

		// Validar si el usuario existe y es del tipo correcto
		DtUsuarioDetallado usr = (usuario instanceof DtUsuarioDetallado) ? (DtUsuarioDetallado) usuario : null;
		if (usr != null) {
		    // El usuario es válido; configurarlo en la request y en la sesión
		    request.setAttribute("usuarioActual", usr);
		    session.setAttribute("usuarioActual", usr);
		} else {
		    // Si el usuario no es válido, redirigir al inicio de sesión
		    response.sendRedirect("iniciarSesion");
		    return;
		}

        // Obtener los nombres y números de referencia de los productos del sistema
        List<String> nombresProductos = new ArrayList<>();
        List<Integer> numerosProductos = new ArrayList<>();
        
        for (DtProducto producto : port.listarDTProductos()) {
            nombresProductos.add(producto.getNombre());
            numerosProductos.add(producto.getNumReferencia());
        }

        // Pasar los nombres y números de productos y categorías al JSP
        request.setAttribute("nombres", nombresProductos);
        request.setAttribute("numeros", numerosProductos);
        request.setAttribute("categorias", port.listarCategoriasConProductos());

        // Pasar las categorías como un HashMap (suponiendo que ya está definido como tal)
        List<DtCategoria> ctg = (List<DtCategoria>) port.listarCategoriasConProductos();
        
        request.setAttribute("categorias", ctg);

        // Redirigir al JSP de registro de productos con las categorías y nombres
        request.getRequestDispatcher("/WEB-INF/registrarProducto/registrarProducto.jsp").forward(request, response);
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				processRequest(request, response);
			} catch (CategoriaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductoNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Si se ingresa desde un dispositivo movil, se redirige a home.
    	String userAgent = request.getHeader("User-Agent");
    	if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
    		response.sendRedirect("home");
    		return;
    	}
		
    	Publicador port;
		
		if (getServletContext().getAttribute("publicador") == null) {
			PublicadorService service = new PublicadorService();
			port = service.getPublicadorPort();
			getServletContext().setAttribute("publicador", port);
		} else {
			port = (Publicador) getServletContext().getAttribute("publicador");
		}
		port.crearCasos();
		
		HttpSession session = request.getSession();	
		
		// Configurar el encoding para manejar caracteres especiales (por si es necesario)
	    request.setCharacterEncoding("UTF-8");
	    
	    Object usuario = session.getAttribute("usuarioActual");
		
		if(usuario == null) {
			
			session.setAttribute("usuarioActual", null);
			
			request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp").
					forward(request, response);
		} else {
			DtUsuarioDetallado usr = (DtUsuarioDetallado) usuario;
			request.setAttribute("usuarioActual", usr);
			session.setAttribute("usuarioActual", usr);
		
			// Obtener los parámetros del formulario que enviaste
		    String nombre = request.getParameter("nombre");
		    String precio = request.getParameter("precio");
		    String numeroRef = request.getParameter("numero");
		    String descripcion = request.getParameter("descripcion");

		    // Declarar la lista de imágenes fuera del if
		    List<byte[]> listaDataImagenes = new ArrayList<>();
	
		    // Obtener el parámetro de imágenes
		    String img = request.getParameter("imagenes");
	
		    // Verificar si el parámetro no es nulo o vacío
		    if (img != null && !img.isEmpty()) {
		        
		        Gson gson = new Gson();
					
		        List<String> listaImagenes = gson.fromJson(img, new TypeToken<List<String>>() {}.getType());
			        
		        // Limpiar posibles comas al final de cada imagen
		        for (String base64 : listaImagenes) {
		        	base64 = base64.substring(base64.indexOf(",")).replaceAll("[^A-Za-z0-9+/=]", "");
		            try {
		                // Decodifica cada imagen
		                byte[] datos = Base64.getDecoder().decode(base64);
		                listaDataImagenes.add(datos);
		            } catch (IllegalArgumentException e) {
		                // Maneja errores de decodificación si alguno ocurre
		                System.err.println("Error al decodificar imagen: " + e.getMessage());
		            }
		        }
		    }
		    
		    List<String> listaNombresArchivos = new ArrayList<>();
		    
		    String nombresArchivosString = request.getParameter("nombresArchivos");
		    
		    if (nombresArchivosString != null && !nombresArchivosString.isEmpty()) {
		        // Eliminar los corchetes y las comillas
		    	nombresArchivosString = nombresArchivosString.replace("[", "").replace("]", "").replace("\"", "");
	
		        // Dividir la cadena por comas para obtener cada especificación
		        String[] nombresArchivosArray = nombresArchivosString.split(",");
	
		        // Crear la lista de especificaciones
		        listaNombresArchivos = new ArrayList<>(Arrays.asList(nombresArchivosArray));
		    }
	
		    // Declarar la lista de especificaciones fuera del if
		    List<String> listaEspecificaciones = new ArrayList<>();
	
		    // Obtener el parámetro de especificaciones
		    String especificacionesString = request.getParameter("especificaciones"); // Suponiendo que obtienes la cadena como un solo parámetro
	
		    // Verificar si el parámetro no es nulo o vacío
		    if (especificacionesString != null && !especificacionesString.isEmpty()) {
		        // Eliminar los corchetes y las comillas
		        especificacionesString = especificacionesString.replace("[", "").replace("]", "").replace("\"", "");
	
		        // Dividir la cadena por comas para obtener cada especificación
		        String[] especificacionesArray = especificacionesString.split(",");
	
		        // Crear la lista de especificaciones
		        listaEspecificaciones = new ArrayList<>(Arrays.asList(especificacionesArray));
	
		    }
	
			// Obtener el parámetro de categorías
		    String categoriasString = request.getParameter("categorias"); 
		    
	        // Eliminar los corchetes y las comillas
	        categoriasString = categoriasString.replace("[", "").replace("]", "").replace("\"", "");
	
	        // Dividir la cadena por comas para obtener cada categoría
	        String[] categoriasArray = categoriasString.split(",");
	
	        // Crear la lista de categorías
	        List<String> listaCategorias = new ArrayList<>(Arrays.asList(categoriasArray));
	
		    // Convertir precio a un número float
		    float precioFloat = 0f;
		    if (precio != null && !precio.isEmpty()) {
		        try {
		            precioFloat = Float.parseFloat(precio);
		        } catch (NumberFormatException e) {
		            // Manejar el error si el formato no es correcto
		            e.printStackTrace();
		        }
		    }
		    
		    // Inicializar una variable para el valor convertido
		    int numeroRefInt = 0; // o double, dependiendo del tipo que necesites
	
		    // Verificar si el parámetro no es null y no está vacío
		    if (numeroRef != null && !numeroRef.isEmpty()) {
		        try {
		            // Convertir el parámetro a un número entero
		            numeroRefInt = Integer.parseInt(numeroRef);
		        } catch (NumberFormatException e) {
		            // Manejar el error si el formato no es correcto
		            e.printStackTrace();
		            
		        }
		    }
			    
		    try {
				port.altaProducto(((DtUsuario) usuario).getNickname(), nombre, numeroRefInt, descripcion, precioFloat, listaEspecificaciones, listaNombresArchivos, listaDataImagenes, listaCategorias, "vacio");
			} catch (CategoriaNoPuedeTenerProductosException_Exception | ProductoRepetidoException_Exception
					| UsuarioNoExisteException_Exception | CategoriaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    session.setAttribute("usuarioActual", usuario);
		
		    request.getRequestDispatcher("infoUsuario").forward(request, response); // No pierdes el request ni los atributos
		}
	}

	

}
