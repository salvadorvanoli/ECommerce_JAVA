package com.flamingo.controllers;
 
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.DtOrdenDeCompra;
import services.Exception_Exception;
import services.Publicador;
import services.PublicadorService;

@WebServlet("/cambiarEstadoOrden")
public class cambiarEstadoOrden extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception_Exception {
    	
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
        
        String estadoOrden = request.getParameter("estadoOrden");
        String numeroOrden = request.getParameter("numeroOrden");
        
        if(estadoOrden == null || estadoOrden == "" || numeroOrden == null || numeroOrden == "") {
        	response.sendRedirect(request.getContextPath() + "/infoUsuario");
        	return;
        }
        
        int id;
        
        id = Integer.parseInt(numeroOrden);
        
        DtOrdenDeCompra ordenSeleccionada = (DtOrdenDeCompra) session.getAttribute("orden");

        if (ordenSeleccionada == null) {
            throw new Exception_Exception("La orden de compra no existe", null);
        }
        
        if(estadoOrden.equals("entregada")) {
        	
        	if(!ordenSeleccionada.getEstado().equals("enCamino")) {
        		
        		response.sendRedirect(request.getContextPath() + "/infoUsuario");
        		return;
        	}
        }
        
        port.agregarEstadoAUnaOrden(id, estadoOrden);
        
        request.setAttribute("orden", null);
        session.setAttribute("orden", null);

        response.sendRedirect(request.getContextPath() + "/infoUsuario");
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