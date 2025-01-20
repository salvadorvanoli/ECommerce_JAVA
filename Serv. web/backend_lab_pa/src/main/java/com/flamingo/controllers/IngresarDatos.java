package com.flamingo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import services.ContraseniaIncorrectaException_Exception;
import services.IOException_Exception;
import services.Publicador;
import services.PublicadorService;
import services.UsuarioRepetidoException_Exception;

@WebServlet("/ingresardatos")
public class IngresarDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Si se ingresa desde un dispositivo movil, se redirige a home.
    	String userAgent = request.getHeader("User-Agent");
    	if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
    		response.sendRedirect("home");
    		return;
    	}
    	
        // Redirigir al JSP para el form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/ingresarDatos.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Si se ingresa desde un dispositivo movil, se redirige a home.
    	String userAgent = request.getHeader("User-Agent");
    	if (userAgent != null && userAgent.toLowerCase().contains("mobile")) {
    		response.sendRedirect("home");
    		return;
    	}
    	
        List<String> errores = new ArrayList<>();

        // Obtener email y nickname de la sesión
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String nickname = (String) session.getAttribute("nickname");
    
     
        if (request.getParameter("nombre") != null && !request.getParameter("nombre").isEmpty()) {
            
            // Procesar los parámetros solo si el nombre no es nulo o vacío
            String tipoUsuario = request.getParameter("tipoUsuario");
            
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String contraseña = request.getParameter("contraseña");
            String repetirContraseña = request.getParameter("repetirContraseña");
            String fecha = request.getParameter("fecha");
            String sitioWeb = request.getParameter("sitioWeb");
            String compañia = request.getParameter("compania");
            String imgBase64 = request.getParameter("imagenUrl");
            String nombreImagen = request.getParameter("nombreArchivo");
            
            // Validaciones
            if (nombre == null || nombre.trim().isEmpty() || nombre.length() < 3 || !validarNombreSinNumeros(nombre)) {
                errores.add("El nombre debe tener al menos 3 caracteres y no puede contener números.");
            }

            if (apellido == null || apellido.trim().isEmpty() || apellido.length() < 3 || !validarNombreSinNumeros(apellido)) {
                errores.add("El apellido debe tener al menos 3 caracteres y no puede contener numeros.");
            }

            if (contraseña == null || contraseña.length() < 8) {
                errores.add("La contraseña debe tener al menos 8 caracteres.");
            } else if (!contraseña.equals(repetirContraseña)) {
                errores.add("Las contraseñas no coinciden.");
            }

            if (fecha == null || fecha.trim().isEmpty()) {
                errores.add("Se debe ingresar una fecha.");
            }

            if(tipoUsuario == "Proveedor") {
            	if (sitioWeb == null || sitioWeb.trim().isEmpty() || !validarUrl(sitioWeb)) {
                    errores.add("La URL del sitio web no es válida.");
                }

                if (compañia == null || compañia.trim().isEmpty()) {
                    errores.add("La compañía es obligatoria.");
                }
            }
            
            byte[] imagen = null;
            
            if (imgBase64 != null && !imgBase64.isBlank() && !imgBase64.isEmpty()) {
                try {
                    // Decodificar la cadena base64 a byte[]
                	String base64Data = imgBase64.substring(imgBase64.indexOf(",") + 1); // Remueve el prefijo
                    imagen = Base64.getDecoder().decode(base64Data);
                } catch (IllegalArgumentException e) {
                    // Manejo de error si el formato base64 es inválido
                    System.err.println("Error al decodificar base64: " + e.getMessage());
                } catch (Exception ex) {
                    System.err.println("Error general: " + ex.getMessage());
                }
            }
            
            // Si hay errores, redirigir de vuelta al formulario con mensajes de error
            if (!errores.isEmpty()) {
                request.setAttribute("errores", errores);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sesion/ingresarDatos.jsp");
                dispatcher.forward(request, response);

            } else {
            	Publicador port;
        		if (getServletContext().getAttribute("publicador") == null) {
        			PublicadorService service = new PublicadorService();
        			port = service.getPublicadorPort();
        			getServletContext().setAttribute("publicador", port);
        		} else {
        			port = (Publicador) getServletContext().getAttribute("publicador");
        		}
        		port.crearCasos();
                // Procesar la fecha y registrar el usuario
				String[] partes = fecha.split("-");
				int anio = Integer.parseInt(partes[0]);
				int mes = Integer.parseInt(partes[1]);
				int dia = Integer.parseInt(partes[2]);

				//DtFecha fechaDT = new DtFecha(dia, mes, anio);
				if ("Proveedor".equals(tipoUsuario.trim())) {

				    try {
						port.altaUsuarioProveedor(nickname, email, nombre, apellido, dia, mes, anio, compañia, sitioWeb, nombreImagen, imagen, contraseña, repetirContraseña);
					} catch (ContraseniaIncorrectaException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UsuarioRepetidoException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if ("Cliente".equals(tipoUsuario.trim())) {

				    try {
						port.altaUsuarioCliente(nickname, email, nombre, apellido, dia, mes, anio, nombreImagen, imagen, contraseña, repetirContraseña);
					} catch (ContraseniaIncorrectaException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UsuarioRepetidoException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException_Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// Redirigir al login tras el registro exitoso
				response.sendRedirect(request.getContextPath() + "/iniciarsesion");
            }
        } else {
            // Si no hay nombre en la solicitud, redirigir al formulario (evita la ejecución doble)
            response.sendRedirect(request.getContextPath() + "/ingresardatos");
        }
    }

    private boolean validarNombreSinNumeros(String nombre) {
        return !nombre.matches(".*\\d.*");
    }

    private boolean validarUrl(String url) {
        String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(:[0-9]+)?(/.*)?$";
        return url.matches(regex);
    }
}
