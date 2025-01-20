package com.flamingo.controllers;

import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.Publicador;
import services.PublicadorService;
/**
 * Servlet implementation class Imagenes
 */
@WebServlet("/getImagen")
public class getImagen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getImagen() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Publicador port;
		if (getServletContext().getAttribute("publicador") == null) {
			PublicadorService service = new PublicadorService();
			port = service.getPublicadorPort();
			getServletContext().setAttribute("publicador", port);
		} else {
			port = (Publicador) getServletContext().getAttribute("publicador");
		}
		port.crearCasos();
        
        String ruta = (String) request.getParameter("ruta");
        
        System.out.println(ruta);
        
        byte[] img = null;

        try {

            img = port.getImagen(ruta);
            response.setContentType("image/jpg");
            response.setContentLength((int) img.length);
            OutputStream out = response.getOutputStream();
            //ImageIO.write(img, "png", out);
            out.write(img);
            out.close();
            
        } catch (Exception ex) {            

        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
