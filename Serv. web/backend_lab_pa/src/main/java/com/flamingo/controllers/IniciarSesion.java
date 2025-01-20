package com.flamingo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.PublicadorService;
import services.ContraseniaIncorrectaException_Exception;
import services.DtUsuario;
import services.Publicador;
import services.UsuarioNoEncontrado_Exception;
import services.UsuarioNoExisteException_Exception;

@WebServlet("/iniciarsesion")
public class IniciarSesion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public IniciarSesion() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ContraseniaIncorrectaException_Exception, UsuarioNoEncontrado_Exception, UsuarioNoExisteException_Exception {
    	
    	Publicador port;
		if (getServletContext().getAttribute("publicador") == null) {

			PublicadorService service = new PublicadorService();
			port = service.getPublicadorPort();
			getServletContext().setAttribute("publicador", port);
		} else {
			port = (Publicador) getServletContext().getAttribute("publicador");
		}
		port.crearCasos();
    	
        HttpSession objSesion = request.getSession();
        String emailOrNickname = request.getParameter("emailOrNickname");
        String password = request.getParameter("password");
     
        if (emailOrNickname == null || emailOrNickname.isEmpty() || password == null || password.isEmpty()) {
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp");
            dispatcher.forward(request, response);
            return;
        }

        List<DtUsuario> usuariosRegistrados = port.listarUsuarios();
        
        DtUsuario usuarioEncontrado = null;
 
        for (DtUsuario usr : usuariosRegistrados) {
            if (usr.getEmail().equals(emailOrNickname) || usr.getNickname().equals(emailOrNickname)) {
            	try {
            		usuarioEncontrado = port.getDTUsuarioActual(emailOrNickname);
            	} catch (UsuarioNoExisteException_Exception e) {
                	e.printStackTrace();
                }
                break;
            }
        }

        // Validación del usuario y contraseña
        if (usuarioEncontrado == null || !usuarioEncontrado.getContrasenia().equals(password)) {
            request.setAttribute("error", "Correo electrónico/nickname o contraseña incorrectos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/iniciarSesion.jsp");
            dispatcher.forward(request, response);
        } else { //inicio de sesion exitoso
        	
            try {
				port.iniciarSesion(emailOrNickname, password);
			} catch (ContraseniaIncorrectaException_Exception e) {
				e.printStackTrace();
			} catch (UsuarioNoEncontrado_Exception e) {
				e.printStackTrace();
			}
            
            objSesion.setAttribute("usuarioActual", usuarioEncontrado);

            response.sendRedirect("home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		processRequest(request, response);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try {
    		processRequest(request, response);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
