package com.flamingo.controllers;
 
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.DtCategoria;
import services.DtProductoDetallado;
import services.Publicador;
import services.PublicadorService;


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
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
        	
    		Collection<DtProductoDetallado> listaDeProductos = port.listarAllProductos();
            List<DtCategoria> listaDeCategorias = port.listarCategorias();
            
            String textoBusqueda = request.getParameter("textoBusqueda");
            
            if(textoBusqueda != null) {
            	request.setAttribute("textoBusqueda", textoBusqueda);
            }

            // Asegurarse de que las listas no sean nulas
            if (listaDeProductos == null) {
                listaDeProductos = new ArrayList<>();
            }
            if (listaDeCategorias == null) {
                listaDeCategorias = new ArrayList<>();
            }

            request.setAttribute("productos", listaDeProductos);
            request.setAttribute("categorias", listaDeCategorias);

            // Redirigir a la página JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/catalogo/catalogo.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			processRequest(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
    }



}