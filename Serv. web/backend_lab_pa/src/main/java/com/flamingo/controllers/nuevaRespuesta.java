package com.flamingo.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.DtCliente;
import services.DtProductoDetallado;
import services.DtUsuarioDetallado;
import services.Publicador;
import services.PublicadorService;

@WebServlet("/nuevaRespuesta")
public class nuevaRespuesta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public nuevaRespuesta() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
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
		
    	// Establecer tipo de respuesta
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
            // Obtener los parámetros del formulario
            String textoComentario = request.getParameter("texto");
            int idComentario = Integer.parseInt(request.getParameter("idRespuesta"));
            
            if(textoComentario.isEmpty() || textoComentario.equals("")) {
            	response.sendRedirect("home");
        		return;
            }
            
            DtUsuarioDetallado usuarioActual = (DtUsuarioDetallado) session.getAttribute("usuarioActual");

            // Supongamos que obtienes el usuario logueado de la sesión
            DtCliente usuarioActualCliente = (DtCliente) usuarioActual;

            DtProductoDetallado producto = (DtProductoDetallado) session.getAttribute("productoActual");

            if(!port.usuarioHaCompradoProducto(usuarioActualCliente.getNickname(), producto.getNumReferencia())) {
            	return;
            }

            if (producto != null && usuarioActualCliente != null) {

            	port.agregarRespuestaAUnComentario(producto.getNumReferencia(), usuarioActualCliente.getNickname(), textoComentario, idComentario, "", idComentario);
            	
                out.println("{\"message\": \"Comentario agregado exitosamente\"}");
                
                response.sendRedirect("infoProducto?productoSeleccionado=" + producto.getNumReferencia());
                
            } else {
                out.println("{\"error\": \"Producto no encontrado\"}");
            }
        } catch (Exception e) {
            // Manejar errores
            out.println("{\"error\": \"Ocurrió un error al agregar el comentario: " + e.getMessage() + "\"}");
        } finally {
            out.close();
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        processRequest(request, response);
    }
}