package com.flamingo.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.DtUsuarioDetallado;
import services.Publicador;
import services.PublicadorService;
import services.OrdenDeCompra;

import com.google.gson.Gson;


/**
 * Servlet implementation class Carrito
 */
@WebServlet ("/manejarcarrito")
public class ManejarCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManejarCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			session.setAttribute("usuarioActual", null);
			request.getRequestDispatcher("/WEB-INF/carrito/carrito.jsp").
					forward(request, response);
				
		} else {
			try {
				
				DtUsuarioDetallado user = (DtUsuarioDetallado) usuario;
				
				String tipoGET = request.getHeader("tipo");
				
				Gson gson = new Gson();
				
				String result = "";
				
				if (tipoGET.equals("getIDOrden")) {
					result = gson.toJson(port.generarCodigoOrden());
				} else if (tipoGET.equals("getCarrito")) {
					result = gson.toJson(port.getCarritoActual(user.getNickname()));
				}
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				
				PrintWriter out = response.getWriter();
				out.print(result);
				out.flush();
				
			} catch (Exception e) {
				// Manejar Excepcion
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			try {
				
				DtUsuarioDetallado user = (DtUsuarioDetallado) usuario;
				// Leer el cuerpo de la solicitud (donde está el JSON)
				String tipoPOST = request.getHeader("tipo");
				
				BufferedReader reader = request.getReader();
		        StringBuilder jsonBuilder = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            jsonBuilder.append(line);
		        }
		        
		        Gson gson = new Gson();
				
				if (tipoPOST.equals("eliminarItem")) {
					
			        int numReferencia = gson.fromJson(jsonBuilder.toString(), int.class);

			        port.eliminarItemCarrito(user.getNickname(), numReferencia);
			        
				} else if (tipoPOST.equals("manejarCantidad")) {
					
					int[] elementos = gson.fromJson(jsonBuilder.toString(), int[].class);
					port.modificarCantidadItemCarrito(user.getNickname(), elementos[0], elementos[1]);
					
				} else if (tipoPOST.equals("realizarCompra")) {
					
					OrdenDeCompra orden = gson.fromJson(jsonBuilder.toString(), OrdenDeCompra.class);
					port.realizarCompra(user.getNickname(), orden.getNumero(), orden.getFecha(), orden.getDetallesEnvio(), orden.getFormaPago());
					
				}
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write("{\"message\":\"Producto recibido correctamente\"}");
			} catch (Exception e) {
				// Manejar Excepcion
			}
		}
	}

}
