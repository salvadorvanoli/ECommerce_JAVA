<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil</title>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" href="media/css/infoUsuario.css">

    <%@page import="services.DtUsuarioDetallado"%>
    <%@page import="services.DtCliente"%>
    <%@page import="services.DtReclamo"%>
    <%@page import="services.DtProveedor"%>
    <%@page import="services.DtOrdenMinima"%>
    <%@page import="services.DtProducto"%>
    <%@page import="services.DtFecha"%>
    <%@ page import="java.util.List" %>
</head>
<body>

	<%
		String userAgent = request.getHeader("User-Agent");
		boolean notMobile = (userAgent != null && ! userAgent.toLowerCase().contains("mobile"));
	
		DtUsuarioDetallado usuarioActual = (DtUsuarioDetallado) request.getAttribute("usuarioActual");
	
		if(usuarioActual == null) {
			response.sendRedirect("backend_lab_pa/iniciarsesion");
	        return;
		}
	%>
    
    <jsp:include page="/WEB-INF/template/header.jsp" />
    
    <main>

        <section id="cliente-section">

            <h1>Información personal</h1>

            <div class="container-md container-fluid">

                <form>
                    <div class="row">
                        <div class="col-md-6 col-12">
            
                            <div class="form-group d-flex">
                                <div class="w-50 me-2">
                                	<label for="inputNombre" class=" ">Nombre</label>
                                    <input type="text" class="form-control mt-2" value="<%= usuarioActual.getNombre() %>" id="inputNombre" placeholder="Ingrese su nombre" disabled>
                                </div>
                                <div class="w-50">
                                	<label for="inputApellido" class=" ">Apellido</label>
                                    <input type="text" class="form-control mt-2" value="<%= usuarioActual.getApellido() %>" id="inputApellido" placeholder="Ingrese su apellido" disabled>
                                </div>
                            </div>
            
                            <div class="form-group mt-3">
                            	<label for="inputNickname" class=" ">Nickname</label>
                                <input type="text" class="form-control mt-2" value="<%= usuarioActual.getNickname() %>" id="inputNickname" placeholder="Ingrese su nickname" disabled>
                            </div>
            
                            <div class="form-group mt-3">
                            	<label for="inputEmail" class=" ">Email</label>
                                <input type="email" class="form-control mt-2" value="<%= usuarioActual.getEmail() %>" id="inputEmail" placeholder="Ingrese su correo electrónico" disabled>
                            </div>

                            <div class="form-group mt-3">
                                <div class="mr-2 w-50">
                                	<label for="inputFecha" class=" ">Fecha de nacimiento</label>
                                    <%
									    String anio = String.valueOf(usuarioActual.getFechaNac().getAnio());
									    String mes = String.valueOf(usuarioActual.getFechaNac().getMes());
									    if (usuarioActual.getFechaNac().getMes() <= 9) {
									        mes = "0" + mes;
									    }
									
									    String dia = String.valueOf(usuarioActual.getFechaNac().getDia());
									    if (usuarioActual.getFechaNac().getDia() <= 9) {
									        dia = "0" + dia;
									    }
									
									    String fechaFormateada = anio + "-" + mes + "-" + dia;
									%>
									<input type="date" value="<%= fechaFormateada %>" class="form-control mt-2" id="inputFecha" disabled>
                                </div>
                            </div>

                            <div class="form-group d-flex">
                                <!--<div class="mr-2 w-50">
                                    <button type="button" onclick="if(revisarDatos()){ window.location.reload(); }" class="btn btn-primary botonRosado mt-2" id="actualizar-datos-usuario">Actualizar datos</button>
                                </div> -->
                                <div class="w-50">
                                    <button class="btn btn-primary botonRosado mt-2" type="submit" id="cerrar-sesion">Cerrar sesión</button>
                                </div>
                            </div>
                        </div>
            
                        <div class="col-md-6 col-12 centro">
                            <div> 
                                <img src="<%= usuarioActual.getFoto() != null && !usuarioActual.getFoto().isEmpty() ? "/backend_lab_pa/getImagen?ruta=" + usuarioActual.getFoto() : "media/images/Chica1.png" %>" alt="Foto de perfil" id="fotoPerfilUsuario" class="img-fluid">
                            </div>
                            
                            <!--
                            
                            
                            <div>
                                <input type="file" id="inputFile" class="btn btn-secondary mt-2" accept=".png, .jpg, .jpeg, .webp">
                            </div>  -->
                        </div>
                    </div>
                </form>
                
               <div class="col-md-6 col-12 text-start">
				    <%
				    // Verifica si el usuarioActual es de tipo Cliente
				    if (usuarioActual instanceof DtCliente) {
				    	DtCliente cliente = (DtCliente) usuarioActual;
				    %>
				    
				    <form id="notificacionesForm" action="infoUsuario" method="POST">
				        <input type="checkbox" id="recibirNotificaciones" name="recibirNotificaciones" value="true" 
						<%= (cliente != null && cliente.isNotificaciones()) ? "checked" : "" %> >
						<label for="recibirNotificaciones">Recibir notificaciones</label>
				        <button class="btn btn-primary botonRosado mt-2" type="submit" onclick="guardarNotificaciones()">Guardar</button>
				    </form>
				
				    <% } %>
				</div>
				
            </div>
        </section>


		<%
	    // Verifica si el usuarioActual es de tipo Cliente
	    if (usuarioActual instanceof DtUsuarioDetallado) {
	    	DtUsuarioDetallado usuario = (DtUsuarioDetallado) usuarioActual;
		%>
		
			<div class="linea-resumen"></div>
		
	        <section id="ver-orden-section">
	            <h2>Ver orden de compra</h2>
	
	            <div class="container-md container-fluid">
	                <div class="row">
	                    <div id="ver-orden-container">
	                        <div class="form-group d-flex align-items-center">
	                            <div class="mr-2 w-100">
	                                <label for="selectOrdenes">Seleccione una orden de compra</label>
	                                <select class="form-control mt-2" id="selectOrdenes">
	                                
	                                <%
	                                List<DtOrdenMinima> ordenes = (List<DtOrdenMinima>) request.getAttribute("ordenesDeCompra");
	                                
                                    for (DtOrdenMinima orden : ordenes) {
                                    	
                                    	String anio2 = String.valueOf(orden.getFecha().getAnio());
									    String mes2 = String.valueOf(orden.getFecha().getMes());
									    String dia2 = String.valueOf(orden.getFecha().getDia());
									    
									    if (orden.getFecha().getMes() <= 9) {
									        mes2 = "0" + mes2;
									    }
									
									    if (orden.getFecha().getDia() <= 9) {
									        dia2 = "0" + dia2;
									    }
									
									    String fechaFormateada2 = anio2 + "-" + mes2 + "-" + dia2;
	                                %>
	                                
	                                    <option value="<%= orden.getNumero() %>">Orden <%= orden.getNumero() %> - Fecha <%= fechaFormateada2 %></option>
	                                    
	                                <%
                                    }
	                                %>
	                                
	                                </select>
	                            </div>
	                        </div>
	                        <button type="button" onclick="window.location.href = 'VerOrdenDeCompra?ordenId=' + document.getElementById('selectOrdenes').value;" class="btn btn-primary botonRosado mt-2" id="ver-orden-btn">Ver orden</button>
	                    </div>
	                </div>
	            </div>
	        </section>
	        <%
	    }
		%>
	        
	        <div class="linea-resumen"></div>
		<%
	    // Verifica si el usuarioActual es de tipo Cliente
	    if (usuarioActual instanceof DtProveedor) {
	    	DtProveedor proveedor = (DtProveedor) usuarioActual;
	    	
	    	List<DtReclamo> reclamos = (List<DtReclamo>) request.getAttribute("reclamos");
	    	
		%>
	        <section id="ver-orden-section">
	            <h2>Ver reclamos</h2>
	
	            <div class="container-md container-fluid">
	                <div class="row">
	                    <div id="ver-orden-container">
	                        <div class="form-group d-flex align-items-center">
	                            <div class="mr-2 w-100">
	                                <label for="selectOrdenes">Seleccione un reporte de reclamo</label>
	                                <select class="form-control mt-2" id="selectReclamo">
	                                
	                                <%
                                    for (DtReclamo reclamo : reclamos) {
                                    	
                                    	String anio3 = String.valueOf(reclamo.getAnio());
									    String mes3 = String.valueOf(reclamo.getMes());
									    String dia3 = String.valueOf(reclamo.getDia());
									    
									    if (reclamo.getMes() <= 9) {
									        mes3 = "0" + mes3;
									    }
									
									    if (reclamo.getDia() <= 9) {
									        dia3 = "0" + dia3;
									    }
									
									    String fechaFormateada3 = anio3 + "-" + mes3 + "-" + dia3;
                                    	
	                                %>
	                                
	                                    <option value="<%= reclamo.getId() %>">Reclamo <%= reclamo.getId() %> - Fecha <%= fechaFormateada3 %></option>
	                                    
	                                <%
                                    }
	                                %>
	                                
	                                </select>
	                            </div>
	                        </div>
	                        <button type="button" onclick="window.location.href = 'VerReclamo?reclamoId=' + document.getElementById('selectReclamo').value;" class="btn btn-primary botonRosado mt-2" id="ver-reclamo-btn">Ver reclamo</button>
	                    </div>
	                </div>
	            </div>
	        </section>
		<%
	    }
		%>
		
        <div class="linea-resumen"></div>

		<%
	    // Verifica si el usuarioActual es de tipo Cliente
	    if (usuarioActual instanceof DtProveedor) {
	    	DtProveedor proveedor = (DtProveedor) usuarioActual;
		%>
        <section id="proveedor-section">
			<h2>
   	            Información Proveedor
   	        </h2>

   	        <div class="container-md container-fluid">
   	            <div class="row">
   	                <div id="info-proveedor-container">
   	                    <div class="form-group d-flex align-items-center">
   	                        <div class="mr-2 w-100 mb-5">
   	                        	<label for="inputSitioWeb" class="mt-3">Sitio web</label>
   	                            <input type="text" value="<%= proveedor.getLink() %>" class="form-control mt-2" id="inputSitioWeb" placeholder="" disabled>
   	                            <label for="inputCompañía" class="mt-3">Nombre de la compañía</label>
   	                            <input type="text" value="<%= proveedor.getNomCompania() %>" class="form-control mt-2 mb-2" id="inputCompañía" placeholder="" disabled>
   	                        </div>
   	                    </div>
   	                </div>
   	            </div>
   	        </div>

   	        <div id="productos-proveedor-container">
   	            
 				<%
			        // Supongamos que tienes una lista de productos en la variable "productos"
			        List<DtProducto> productos = (List<DtProducto>) request.getAttribute("productos");
			
			        if (productos != null) {
			            for (DtProducto producto : productos) {
			    %>
			                <div class="container-md container-fluid product-card" <% if (notMobile) { %>onclick="window.location.href='infoProducto?productoSeleccionado=<%= producto.getNumReferencia() %>' <% } %>">
			                    <div class="row">
			                        <div class="col-md-3 col-12">
			                            <img src="<%= (producto.getImagenes() != null && !producto.getImagenes().isEmpty() && producto.getImagenes().get(0) != "") ? "/backend_lab_pa/getImagen?ruta=" + producto.getImagenes().get(0) : "media/images/default.webp" %>" alt="Imagen producto" class="img-producto">
			                        </div>
			                        <div class="col-md-8 col-12">
			                            <div>
			                                <h4 class="titulo-producto"><%= producto.getNombre() %></h4>
			                                <p class="descripcion-producto"><%= producto.getDescripcion() %></p>
			                                <div class="precio-producto">
			                                    $<%= producto.getPrecio() %>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			    <%
			            }
			        }
			    %>
   	            
   	        </div>

			<%
				if (notMobile) {
   	        %>
   	        <div class="container-md container-fluid">
   	            <div class="row">
   	                <div id="boton-agregar-producto">
   	                    <div class="form-group d-flex align-items-center">
   	                        <div class="mr-2 w-100">
   	                            <button type="button" class="btn btn-primary botonRosado mt-2" onclick="window.location.href = 'registrarProducto';" id="agregar-producto-btn">Agregar producto</button>
   	                        </div>
   	                    </div>
   	                </div>
   	            </div>
   	        </div>
   	        <%
	    		}
   	        %>
        </section>
		<%
	    }
		%>

    </main>

    <jsp:include page="/WEB-INF/template/footer.jsp" />
	<script>
	
		document.getElementById("cerrar-sesion").addEventListener("click", () => {
		    fetch("/backend_lab_pa/cerrarSesion", {
		        method: "POST",
		        headers: {
		            "Content-Type": "application/json",
		        },
		    })
		    .then(response => {
		        if (response.ok) {
		            window.location.href = "home";
		        } else {
		            throw new Error('Error al cerrar sesión');
		        }
		    })
		    .catch(error => {
		        // console.error('Error:', error);
		    });
		});
		
		
		function guardarNotificaciones(event) {
		    
		    var form = document.getElementById("notificacionesForm");
		    
		    // checkbox seleccionado o no
		    var checkbox = document.getElementById("recibirNotificaciones");
		    var isChecked = checkbox.checked;
		    
		 
		    // actualizar algún valor o mostrar un mensaje al usuario

		   
		    form.submit();  // Enviar el formulario sin recargar la página
		}
		
	</script>
	

</html>