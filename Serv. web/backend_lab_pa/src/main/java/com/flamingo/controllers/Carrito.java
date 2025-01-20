package com.flamingo.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.DtCliente;
import services.DtUsuarioDetallado;
import services.Publicador;
import services.PublicadorService;
import services.UsuarioNoExisteException_Exception;

/**
 * Servlet implementation class Carrito
 */
@WebServlet ("/carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, UsuarioNoExisteException_Exception {
		
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

		Object usuario = session.getAttribute("usuarioActual");	
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
					forward(request, response);
				
		} else {
			DtUsuarioDetallado usr = (DtUsuarioDetallado) usuario;
			
			if (usr instanceof DtCliente) {
				request.setAttribute("carrito", port.getCarritoActual(usr.getNickname()));
				request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
						forward(request, response);
			} else {
				response.sendRedirect("home");
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
