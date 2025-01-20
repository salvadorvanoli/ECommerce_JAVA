package com.flamingo.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.DtProveedor;
import services.DtUsuarioDetallado;
import services.Exception_Exception;
import services.Publicador;
import services.PublicadorService;

@WebServlet("/agregarAlCarrito")
public class agregarAlCarrito extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public agregarAlCarrito() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
    	
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
    	
    	int numReferencia = Integer.parseInt(request.getParameter("numReferencia"));
    	
    	DtUsuarioDetallado usuario = (DtUsuarioDetallado) session.getAttribute("usuarioActual");
    	
        String cantidadStr = request.getParameter("cantidad");

        int cantidad = 0;
        
        if (cantidadStr != null) {
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if(cantidad <= 0) {
                	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "La cantidad debe ser mayor que cero.");
                    return;
                }
            } catch (NumberFormatException e) {
            	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cantidad no válida.");
                return;
            }
        }
        
        if(usuario instanceof DtProveedor) {
        	response.sendRedirect("home");
            return;
        }
        
        port.agregarAlCarrito(numReferencia, cantidad, usuario.getNickname());
        
        response.sendRedirect("infoProducto?productoSeleccionado=" + numReferencia);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}