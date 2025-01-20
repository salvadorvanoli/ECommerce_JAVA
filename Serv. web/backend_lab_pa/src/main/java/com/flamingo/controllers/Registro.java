package com.flamingo.controllers;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.Publicador;
import services.PublicadorService;
import services.UsuarioRepetidoException_Exception;

@WebServlet("/registrar")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Si se ingresa desde un dispositivo movil, se redirige a home.
    	String userAgent = request.getHeader("User-Agent");
    	if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
    		response.sendRedirect("home");
    		return;
    	}
    	
        // Mostrar el formulario de registro
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/registro.jsp");
        dispatcher.forward(request, response); // Cambia la ruta según tu estructura de proyecto
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Si se ingresa desde un dispositivo movil, se redirige a home.
    	String userAgent = request.getHeader("User-Agent");
    	if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
    		response.sendRedirect("home");
    		return;
    	}
    	
        // Obtener los parámetros enviados desde el formulario de registro
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        
        Publicador port;
		if (getServletContext().getAttribute("publicador") == null) {
			PublicadorService service = new PublicadorService();
			port = service.getPublicadorPort();
			getServletContext().setAttribute("publicador", port);
		} else {
			port = (Publicador) getServletContext().getAttribute("publicador");
		}
		port.crearCasos();
		
        try {
			port.registro(nickname, email);
		} catch (UsuarioRepetidoException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Guardar estos valores en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("nickname", nickname);
        
        response.sendRedirect(request.getContextPath() + "/ingresardatos"); 
    }
}
