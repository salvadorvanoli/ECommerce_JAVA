package com.flamingo.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.Publicador;
import services.PublicadorService;

// import com.flamingo.exceptions.UsuarioNoEncontrado;
/*
import com.flamingo.models.ISistema;
import com.flamingo.models.OrdenDeCompra;
import com.flamingo.models.SistemaFactory;
*/
import com.google.gson.Gson;

/**
 * Servlet implementation class Carrito
 */
@WebServlet ("/manejarcatalogo")
public class manejarCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manejarCatalogo() {
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
		
		try {
			
			String tipoGET = request.getHeader("tipo");
			
			Gson gson = new Gson();
			
			String result = "";
			
			if (tipoGET.equals("getCategorias")) {
				result = gson.toJson(port.listarCategorias());
			} else if (tipoGET.equals("getProductos")) {	
				result = gson.toJson(port.listarAllProductos());
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
