package com.flamingo.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.ProductoNoExisteException_Exception;
import services.DtUsuarioDetallado;
import services.Exception_Exception;
import services.DtComentario;
import services.DtProductoDetallado;
import services.DtProveedor;
import services.Publicador;
import services.PublicadorService;

@WebServlet ("/infoProducto")
public class infoProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public infoProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ProductoNoExisteException_Exception, Exception_Exception {
		
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
		
		String numReferenciaStr = request.getParameter("productoSeleccionado");
		
		if(numReferenciaStr.isBlank() || numReferenciaStr.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/home/home.jsp").
			forward(request, response);
			return;
		}
		
		int numReferencia = Integer.parseInt(numReferenciaStr);
		DtProductoDetallado productoSeleccionado = null;
		
		productoSeleccionado = port.getDetallesProducto(numReferencia);
		
		if(productoSeleccionado == null) {
			session.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/user/ERROR.jsp").
					forward(request, response);
		}

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
			
			if (usr instanceof DtProveedor) {
				boolean haCompradoProducto = false;
				request.setAttribute("haCompradoProducto", haCompradoProducto);
			} else {
				boolean haCompradoProducto = port.usuarioHaCompradoProducto(usr.getNickname(), numReferencia);
				request.setAttribute("haCompradoProducto", haCompradoProducto);
			}
			
			List<DtComentario> comentarios = port.obtenerComentariosProducto(numReferencia);
			request.setAttribute("comentarios", comentarios);
			
			request.getRequestDispatcher("/WEB-INF/products/infoProducto.jsp").
					forward(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProductoNoExisteException_Exception e) {
			e.printStackTrace();
		} catch (Exception_Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ProductoNoExisteException_Exception e) {
			e.printStackTrace();
		} catch (Exception_Exception e) {
			e.printStackTrace();
		}
	}

}
