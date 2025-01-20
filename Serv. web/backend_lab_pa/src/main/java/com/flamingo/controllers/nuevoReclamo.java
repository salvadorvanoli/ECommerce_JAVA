package com.flamingo.controllers;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.DtProducto;
import services.DtProductoDetallado;
import services.DtUsuario;
import services.DtUsuarioDetallado;
import services.Exception_Exception;
import services.ProductoNoExisteException_Exception;
import services.Publicador;
import services.PublicadorService;
import services.UsuarioNoExisteException_Exception;
import services.DtFecha;
import services.CategoriaNoExisteException_Exception;


@WebServlet ("/nuevoReclamo")
public class nuevoReclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public nuevoReclamo() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ProductoNoExisteException_Exception, CategoriaNoExisteException_Exception, ProductoNoExisteException_Exception, UsuarioNoExisteException_Exception, Exception_Exception {
      
        // Si se ingresa desde un dispositivo móvil, se redirige a home.
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
		
		DtProductoDetallado productoSeleccionado = (DtProductoDetallado) session.getAttribute("productoActual");

		request.setAttribute("productoActual", productoSeleccionado);
		Object usuario = session.getAttribute("usuarioActual");
		Object producto = request.getAttribute("productoActual");	
		
		if(usuario == null) {
			
			session.setAttribute("usuarioActual", null);
			
			request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp").
					forward(request, response);
		} else {
			DtUsuarioDetallado usr = (DtUsuarioDetallado) usuario;
			request.setAttribute("usuarioActual", usr);
			session.setAttribute("usuarioActual", usr);
			
			DtProductoDetallado prd = (DtProductoDetallado) producto;
			request.setAttribute("productoActual", prd);
			session.setAttribute("productoActual", prd);
		}
		
		int prodId = ((DtProducto) productoSeleccionado).getNumReferencia();
		String nicknameC = ((DtUsuario) usuario).getNickname();
		
        // Obtener el parámetro "texto" enviado desde el formulario
        String texto = request.getParameter("texto");

        // Verificar si el parámetro "texto" está presente
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception_Exception("No se recibió texto", null);
        }
        
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now(); // Obtiene la fecha actual (día, mes, año)
        
        // Crear un objeto DTFecha con la fecha actual
        DtFecha fecha = new DtFecha();
        fecha.setAnio(fechaActual.getYear());
        fecha.setMes(fechaActual.getMonthValue());
        fecha.setDia(fechaActual.getDayOfMonth());
        
        // Llamada al método altaReclamo
        port.altaReclamo(nicknameC, prodId, texto, fechaActual.getDayOfMonth(), fechaActual.getMonthValue(), fechaActual.getYear());

        // Redirigir a la página de información del producto
        response.sendRedirect("infoProducto?productoSeleccionado=" + prodId);
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				processRequest(request, response);
			} catch (CategoriaNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProductoNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				try {
					processRequest(request, response);
				} catch (ProductoNoExisteException_Exception | UsuarioNoExisteException_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception_Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		}
	}

}
