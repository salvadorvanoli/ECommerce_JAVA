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
import services.DetallesEnvio;
import services.DtCambioEstado;
import services.DtCantidad;
import services.DtOrdenDeCompra;
import services.DtUsuarioDetallado;
import services.Exception_Exception;
import services.Publicador;
import services.PublicadorService;

@WebServlet("/VerOrdenDeCompra")
public class infoOrden extends HttpServlet {
    private static final long serialVersionUID = 1L;
   
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception_Exception {
    	
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
		
        DtUsuarioDetallado user = (DtUsuarioDetallado) session.getAttribute("usuarioActual");
        
        String num = request.getParameter("ordenId");
        
        if(num == null || num == "") {
        	response.sendRedirect(request.getContextPath() + "/infoUsuario");
        	return;
        }
        
        int id;
        
        id = Integer.parseInt(num);
        
        DtOrdenDeCompra orden = port.getOrdenPorId(id);
        DtOrdenDeCompra ordenSeleccionada = null;
        
        if(orden != null && orden.getNumero() == id) {
        	ordenSeleccionada = orden;
        } else {
        	throw new Exception_Exception("La orden de compra no existe", null);
        }
        
        List<DtCantidad> listaProd = port.getDTCantidadOrden(id);
        
        List<DtCambioEstado> estados = port.getEstadosOrden(id);
        
        DetallesEnvio detalles = port.getDetallesEnvio(id);
        
        request.setAttribute("usuarioActual", user);
        request.setAttribute("productos", listaProd);
        request.setAttribute("orden", ordenSeleccionada);
        session.setAttribute("orden", ordenSeleccionada);
        request.setAttribute("estados", estados);
        request.setAttribute("detallesEnvio", detalles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/orden/infoOrden.jsp");
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