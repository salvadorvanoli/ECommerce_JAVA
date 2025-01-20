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
import services.DtReclamo;
import services.DtUsuario;
import services.Exception_Exception;
import services.Publicador;
import services.PublicadorService;

@WebServlet("/VerReclamo")
public class manejarReclamo extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception_Exception {
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
        
        String num = request.getParameter("reclamoId");
        
        if(num == null || num == "") {
        	response.sendRedirect(request.getContextPath() + "/infoUsuario");
        	return;
        }
        
        int id;
        
        id = Integer.parseInt(num);
        
        Object usuario = session.getAttribute("usuarioActual");
        
       
        List<DtReclamo> reclamos = port.getReclamosProveedor(((DtUsuario) usuario).getNickname());
        DtReclamo reclamoSeleccionado = null;
        
        for(DtReclamo rec : reclamos) {
        	if(rec.getId() == id) {
        		reclamoSeleccionado = rec;
        		break;
        	}
        }

        if (reclamoSeleccionado == null) {
            throw new Exception_Exception("La orden de compra no existe", null);
        }
       
        request.setAttribute("reclamo", reclamoSeleccionado);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/reclamo/infoReclamo.jsp");
        dispatcher.forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | Exception_Exception e) {
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException | Exception_Exception e) {
			 e.printStackTrace();
		}
    }
}