<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Información producto</title>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <jsp:include page="/WEB-INF/template/head.jsp" />
 
    <link rel="stylesheet" href="media/css/infoProducto.css">
	
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    
    <%@page import="com.flamingo.models.ComentarioRenderer"%>
    <%@page import="services.DtUsuario"%>
    <%@page import="services.DtUsuarioDetallado"%>
    <%@page import="services.DtCliente"%>
    <%@page import="services.DtProveedor"%>
    <%@page import="services.DtProductoDetallado"%>
    <%@page import="services.DtComentario"%>

    
</head>
<body>
    
    <%
    	DtProductoDetallado producto = (DtProductoDetallado) request.getAttribute("productoActual");
	
		if(producto == null) {
			response.sendRedirect("backend_lab_pa/home");
	        return;
		}
	%>
    
    <jsp:include page="/WEB-INF/template/header.jsp" />

    <main>
        
        <div class="container-md container-fluid">
            <div class="row">
                <div class="col-md-6 col-12 mt-5">
                    <div id="imgCarousel" class="carousel slide <%= (producto.getImagenes() == null || producto.getImagenes().isEmpty()) ? "d-none" : "" %>" data-bs-ride="carousel">
					    <% 
					    List<String> imagenes = producto.getImagenes();
					    if (imagenes != null && !imagenes.isEmpty() && imagenes.get(0) != "") { 
					    %>
					        <ol class="carousel-indicators" id="indicadores-carrusel">
					            <%
					            for (int contador = 0; contador < imagenes.size(); contador++) {
					                if (contador == 0) {
					            %>
					                    <li data-bs-target="#imgCarousel" data-slide-to="<%= contador %>" class="active"></li>
					            <%
					                } else {
					            %>
					                    <li data-bs-target="#imgCarousel" data-slide-to="<%= contador %>" class=""></li>
					            <%
					                }
					            }
					            %>
					        </ol>
					        
					        <div class="carousel-inner" id="item-carrusel">
					            <%
					            for (int contador = 0; contador < imagenes.size(); contador++) {
					                String imagen = imagenes.get(contador);
					                if (contador == 0) {
					            %>
					                    <div class="carousel-item active carousel-container">
					                        <img class="d-block w-100 imgcarousel" src="<%= "/backend_lab_pa/getImagen?ruta=" + imagen %>" alt="Slide">
					                    </div>
					            <%
					                } else {
					            %>
					                    <div class="carousel-item carousel-container">
					                        <img class="d-block w-100 imgcarousel" src="<%= "/backend_lab_pa/getImagen?ruta=" + imagen %>" alt="Slide">
					                    </div>
					            <%
					                }
					            }
					            %>
					        </div>
					        
					        <a class="carousel-control-prev" href="#imgCarousel" role="button" data-slide="prev">
					            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
					            <span class="sr-only">Previous</span>
					        </a>
					        <a class="carousel-control-next" href="#imgCarousel" role="button" data-slide="next">
					            <span class="carousel-control-next-icon" aria-hidden="true"></span>
					            <span class="sr-only">Next</span>
					        </a>
					    <% 
					    } 
					    %>
					</div>
					
					<%
					    // Ocultar el carrusel si no hay imágenes
					    if (imagenes == null || imagenes.isEmpty() || imagenes.get(0) == "") {
					%>
					    <script>
						    document.addEventListener('DOMContentLoaded', function() {
						    	document.getElementById("imgCarousel").style.display = "none";
						        document.getElementById("informacion-producto-container").classList.remove("col-md-6");
						        document.getElementById("informacion-producto-container").classList.add("col-md-12");
						        document.getElementById("numero-producto").style.textAlign = "left";
						    });
					    </script>
					<%
					    }
					%>
                    <div id="numero-producto">
						Número de referencia: <%= producto.getNumReferencia() %>
                    </div>
                </div>
    
                
                <div class="col-md-6 col-12 mt-5" id="informacion-producto-container">
                    <h1 id="nombre-producto">
						<%= producto.getNombre() %>
                    </h1>

                    <div id="estrellas-container">
					    <%
					    int estrellas = producto.getEstrellas(); // Obtener la cantidad de estrellas
					    // Insertar estrellas llenas
					    for (int i = 0; i < estrellas; i++) {
					    %>
					        <i class="fas fa-star" style="color: #7A7A7A;"></i>
					    <%
					    }
					    
					    // Insertar estrellas vacías
					    for (int i = 0; i < (5 - estrellas); i++) {
					    %>
					        <i class="fas fa-star" style="color: #EBEBEB;"></i>
					    <%
					    }
					    %>
					</div>

                    <div class="linea-resumen"></div>
						
                    <div id="precio-container">
                    	$<%= producto.getPrecio() %>
                    </div>

                    <div class="linea-resumen"></div>

                    <div id="descripcion-container">
                        <p id="descripcion-texto">
							<%= producto.getDescripcion() %>
                        </p>
                    </div>

                    <div id="">
                    	
                    	<form id="carrito-form" action="agregarAlCarrito" method="POST">
						    <input type="text" name="numReferencia" value="<%= producto.getNumReferencia() %>" class="d-none">
						    <input type="number" name="cantidad" id="cantidad-input" min="1" value="1">
						    <button type="button" class="siguiente-button btn btn-danger" id="agregar-al-carrito" data-toggle="modal" data-target="#modalCarrito">Agregar al carrito</button>
						</form>
                    
                    </div>
                </div>
            </div>

            <div id="categorias-container">
			    <%
			        List<String> categorias = producto.getCategorias();
			        for (int i = 0; i < categorias.size(); i++) {
			            out.print(categorias.get(i));
			            if (i < categorias.size() - 1) {
			                out.print(", ");
			            }
			        }
			    %>
			</div>

        </div>

        <div class="linea-resumen"></div>

        <section id="especificaciones-section">

            <h2>
                Especificaciones del producto
            </h2>

            <div id="especificaciones-container">
			    <% 
			    if (producto.getEspecificaciones() != null && !producto.getEspecificaciones().isEmpty() && producto.getEspecificaciones().get(0) != "") { 
			    %>
			        <ul>
			        <% 
			        for (String especificacion : producto.getEspecificaciones()) { 
			        %>
			            <li class="especificacion"><%= especificacion %></li>
			        <% 
			        }
			        %>
			        </ul>
			    <% 
			    } else { 
			    %>
			        <p class="seccion-vacia">El proveedor no cargó especificaciones.</p>
			    <% 
			    } 
			    %>
			</div>

        </section>

		<div class="linea-resumen"></div>

		<section id="estrellas-section" class="container my-4 p-3 bg-light rounded shadow-sm">
		    <h5 class="mb-3">Puntaje del producto</h5>
		    
		    <ul class="list-group">
		        <% 
		        
		        	List<DtComentario> comentarios = (List<DtComentario>) request.getAttribute("comentarios");
		        
		            // Inicializar contadores para cada tipo de puntuación de estrellas
		            int[] contadorEstrellas = new int[5];
		            
		            for (DtComentario comentario : comentarios) {
		                int estrellasComentario = comentario.getEstrellas();
		                if (estrellasComentario >= 1 && estrellasComentario <= 5) {
		                    contadorEstrellas[estrellasComentario - 1]++;
		                }
		            }
		            
		            // Crear un loop para cada tipo de estrellas
		            for (int i = 0; i < 5; i++) {
		                int estrellas2 = i + 1;
		                int votaciones = contadorEstrellas[i];
		                
		        %>
		        
		        <!-- Item de lista para cada nivel de estrellas -->
		        <li class="list-group-item d-flex align-items-center">
		            <!-- Generar las estrellas llenas y vacías dinámicamente -->
		            <div class="mr-3">
		                <% for (int j = 0; j < 5; j++) { %>
		                    <i class="fas fa-star" style="color: <%= j < estrellas2 ? "#7A7A7A" : "#EBEBEB" %>;"></i>
		                <% } %>
		            </div>
		            <span><%= estrellas2 %> estrella(s): <strong><%= votaciones %></strong> votaciones</span>
		        </li>
		        
		        <% } %>
		    </ul>
		</section>


        <div class="linea-resumen"></div>

        <section id="comentarios-section">

            <h2>
                Comentarios del producto
            </h2>

			<%
			
				Boolean haCompradoProducto;
			
				DtUsuarioDetallado usuario = (DtUsuarioDetallado) session.getAttribute("usuarioActual");
				
				if (usuario instanceof DtProveedor) {
					haCompradoProducto = false;
				} else {
					// Obtiene el usuario actual (asumiendo que está almacenado en la sesión)
				    DtCliente usuarioActual = (DtCliente) usuario;
				
				    // Verifica si el usuario ha comprado el producto
				    haCompradoProducto = (boolean) (usuarioActual != null && (boolean) request.getAttribute("haCompradoProducto"));
				}
			
			
			%>


            <div class="caja-comentario card p-3 mt-3 mb-3 ms-5 me-5 <%= haCompradoProducto ? "" : "d-none" %>" id="respuesta">
			    <div class="form-group">
			        <form action="nuevoComentario" method="POST">
			            <label for="comentarioInput">Escribe tu comentario:</label>
			            <input type="text" name="texto" class="form-control" id="comentarioInput" placeholder="Escribe aquí...">
			            <input type="number" name="estrellas" class="form-control mt-3" id="cantEstrellas" value="5" min="1" placeholder="Inserte la cantidad de estrellas (1-5)">
			        
			            <div class="mt-3" id="botonesComentario">
			                <input type="submit" class="btn btn-success" value="Enviar">
			            </div>
			        </form>
			    </div>
			</div>

            <div id="comentarios-container" class="contenedor-responsive">
			    <%
			    
			        // Renderizar todos los comentarios y respuestas de manera recursiva
			        String htmlComentarios = ComentarioRenderer.renderComentarios(comentarios, 0, 0, haCompradoProducto);
			        if (htmlComentarios != null && !htmlComentarios.trim().isEmpty()) {
			            out.print(htmlComentarios);
			        } else {
			        	
			    %>
			        <p class="seccion-vacia">El producto no tiene comentarios aún.</p>
			    <%
			    
			        }
			     
			    %>
			</div>

        </section>
          
	<div class="linea-resumen <%= haCompradoProducto ? "" : "d-none" %>"></div>

	<section id="reclamos-section">

			<%
				
				if (usuario instanceof DtProveedor) {
					haCompradoProducto = false;
				} else {
					// Obtiene el usuario actual (asumiendo que está almacenado en la sesión)
				    DtCliente usuarioActual = (DtCliente) usuario;
				
				    // Verifica si el usuario ha comprado el producto
				    haCompradoProducto = (boolean) (usuarioActual != null && (boolean) request.getAttribute("haCompradoProducto"));
				}
			
			%>

		 	<h2 class="<%= haCompradoProducto ? "" : "d-none" %>">Realizar reclamo</h2>

            <div class="caja-comentario card p-3 mt-3 mb-3 ms-5 me-5 <%= haCompradoProducto ? "" : "d-none" %>" id="respuestas">
			    <div class="form-group">
			        <form action="nuevoReclamo" method="POST">
			            <label for="comentarioInput">Redacta tu reclamo:</label>
			            <input type="text" name="texto" class="form-control" id="reclamoInput" placeholder="Escribe aquí...">
			            
			            <div class="mt-3" id="botonesReclamo">
			                <input type="submit" class="btn btn-success" value="Enviar">
			            </div>
			        </form>
			    </div>
			</div>
		</section>
    </main>

    <footer>

    </footer>

    <!-- MODAL -->
    <div class="modal fade" id="modalCarrito" tabindex="-1" role="dialog" aria-labelledby="modalTitle" aria-hidden="true">
    	<div class="modal-dialog" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="modalTitle">¡Producto agregado con éxito!</h5>
	            </div>
	            <div class="modal-body">
	                Pulse "Agregar" para confirmar o "Cerrar" para cancelar.
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	                <button type="button" class="btn btn-primary" id="confirmar-agregar">Agregar</button>
	            </div>
	        </div>
	    </div>
	</div>
    <!-- /MODAL -->

    <jsp:include page="/WEB-INF/template/footer.jsp" />
</body>

    <script type="text/javascript">

	    function cancelarComentario(id) {
	    	
	        document.getElementById("respuesta_" + id).classList.add("d-none");
	    }
	    
	    function mostrarCajaRespuesta(contador, id) {

	    	contador++;
	    	
	        document.getElementById("respuesta_" + id).classList.remove("d-none");
	    }
    
	    document.getElementById('confirmar-agregar').addEventListener('click', function() {
	        document.getElementById('carrito-form').submit();
	    });
    </script>

</html>