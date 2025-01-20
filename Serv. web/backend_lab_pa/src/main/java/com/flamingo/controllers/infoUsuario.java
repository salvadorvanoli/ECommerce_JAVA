package com.flamingo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.DtUsuarioDetallado;
import services.Exception_Exception;
import services.DtCliente;
import services.Publicador;
import services.PublicadorService;
import services.UsuarioNoExisteException_Exception;
import services.DtOrdenMinima;
import services.DtProducto;
import services.DtProveedor;
import services.DtReclamo;

@WebServlet ("/infoUsuario")
public class infoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public infoUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		DtUsuarioDetallado usuario = (DtUsuarioDetallado) session.getAttribute("usuarioActual");
		
		if(usuario == null) {
			request.setAttribute("usuarioActual", null);
			response.sendRedirect("iniciarsesion");
		} else {
			request.setAttribute("usuarioActual", usuario);
			
			List<DtOrdenMinima> ordenes = null;
			try {
				ordenes = port.getOrdenesMinimas(usuario.getNickname());
			} catch (UsuarioNoExisteException_Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("ordenesDeCompra", ordenes);

			if(usuario instanceof DtProveedor) {
				List<DtReclamo> reclamos = null;
				try {
					reclamos = port.getReclamosProveedor(usuario.getNickname());
				} catch (Exception_Exception e) {
					e.printStackTrace();
				}
				
				request.setAttribute("reclamos", reclamos);
				
				List<DtProducto> productos = null;
				try {
					productos = port.getProductosProveedor(usuario.getNickname());
				} catch (Exception_Exception e) {
					e.printStackTrace();
				}
				
				request.setAttribute("productos", productos);
			}
			
			// Verificar si se está haciendo una solicitud para guardar las notificaciones
            if (request.getMethod().equalsIgnoreCase("POST")) {
                // Obtener el parámetro de las notificaciones
                String recibirNotificaciones = request.getParameter("recibirNotificaciones");
                
                // System.out.println("RecibirNotificaciones: " + recibirNotificaciones);
                
                boolean notificaciones = recibirNotificaciones != null && recibirNotificaciones.equals("true");

                // Solo si el usuario es Cliente
                if (usuario instanceof DtCliente) {
                	
                    try {
						port.cambiarNotificaciones(usuario.getNickname(), notificaciones);
						((DtCliente) usuario).setNotificaciones(notificaciones);
						request.setAttribute("usuarioActual", usuario);
					} catch (UsuarioNoExisteException_Exception e) {
						e.printStackTrace();
					}
             

                    // Puedes realizar algún procesamiento adicional aquí, como guardar en la base de datos
                }
            }
            
			request.getRequestDispatcher("/WEB-INF/user/infoUsuario.jsp").
					forward(request, response);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
