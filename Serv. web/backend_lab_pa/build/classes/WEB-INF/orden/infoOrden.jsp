<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.util.Comparator"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.text.DecimalFormat" %>
<%--
<%@ page import="com.flamingo.models.Producto" %>
<%@ page import="com.flamingo.models.OrdenDeCompra" %>
<%@ page import="com.flamingo.models.Usuario" %>
<%@ page import="com.flamingo.models.Proveedor" %>
<%@ page import="com.flamingo.models.Cliente" %>
<%@ page import="com.flamingo.models.DTCantidad" %>
<%@ page import="com.flamingo.models.DTProducto" %>
<%@ page import="com.flamingo.models.Estado" %>
--%>
<%@ page import="clases.Producto" %>
<%@ page import="clases.OrdenDeCompra" %>
<%@ page import="clases.Usuario" %>
<%@ page import="clases.Proveedor" %>
<%@ page import="clases.Cliente" %>
<%@ page import="clases.DTCantidad" %>
<%@ page import="clases.DTProducto" %>
<%@ page import="clases.Estado" %>
<%@ page import="clases.CambioEstado" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ingresarDatos</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="media/css/infoOrdenCompra.css">
</head>
<body>
	
	<jsp:include page="/WEB-INF/template/header.jsp"/>
	
	<%
	
		String userAgent = request.getHeader("User-Agent");
		boolean notMobile = (userAgent != null && ! userAgent.toLowerCase().contains("mobile"));
	
		Usuario usuarioActual = (Usuario) request.getAttribute("usuarioActual");
		List<DTProducto> productos = (List<DTProducto>) request.getAttribute("productos");
		OrdenDeCompra orden = (OrdenDeCompra) request.getAttribute("orden");
		int cantidad = 0;
		float subtotal = 0f;
		
		
		float subtotalTodo = 0;
		int impuestosTodo = 0;
		
		DecimalFormat df = new DecimalFormat("#.#");
		
		
	%>
	
	<% 
		if(orden != null ){
	%>
	
		<main class="container-fluid">
		<%
		
			
		
			if(productos != null) {
				
				for (DTProducto prod : productos) {
					cantidad = 0;
					subtotal = 0f;
			
		%>
		
				
			        <div class="rectangle-1" onclick="window.location.href='infoProducto?productoSeleccionado=<%= prod.getNumero() %>'">
			        
			            <div class="rectangle-2">
			                <div class="row"> 
			                    <div class="col-md-2 d-flex">
			                        <div class="fotosProductos">
			                        	<img src="<%= (prod != null && prod.getImagenes() != null && !prod.getImagenes().isEmpty()) ? prod.getImagenes().get(0) : "media/images/default.webp" %>" alt="<%= (prod != null) ? prod.getNombre() : "Imagen no disponible" %>" class="imagenProducto" onerror="this.onerror=null; this.src='/media/images/default.webp';">
			                        </div>   
			                    </div>
			                    <div class="col-md-4 d-flex flex-column justify-content-start p-0">
			                        <h1 class="nombresProductos text-start mt-3" style="cursor: pointer;"><%= prod.getNombre() %></h1>    
			                        <h2 class="descripcionProductos text-start m-0"><%= prod.getDescripcion() %></h2>
			                        <h2 class="precioProductos text-start mt-3">$ <%= prod.getPrecio() %></h2>
			                    </div>
			                    <div class="col-md-4">
			                        <h1 class="numProducto text-start mt-3">N° referencia: <%= prod.getNumero() %></h1>
			                        <div class="stepper-container d-flex align-items-end">
			                             
			                             
			                            <%
			                            
										    for (DTCantidad cant : orden.getCantidad()) {
										        if (prod.getNumero() == cant.getDTCantidadProducto().getProducto().getNumero()) {
										            cantidad = cant.getCantidad(); 
										            break; 
										        }
										    }
										%>
			                             
			                            <label for="quantity" class="labelCantidad">Cantidad</label>
			                            <input type="number" id="quantity" class="stepper" value="<%= cantidad %>" min="0" max="100" disabled>
			                            
			                        </div>          
			                    </div>
			                    <div class="col-md-2">
			                        <h1 class="textoSubtotal mt-3">Subtotal</h1>
			                        <%
										    for (DTCantidad cant : orden.getCantidad()) {
										        if (prod.getNumero() == cant.getDTCantidadProducto().getProducto().getNumero()) {
										            subtotal = prod.getPrecio() * cantidad;
										            subtotalTodo += subtotal;
										%>
			                        
			                        <h1 class="precioProductosSubt mt-3">$ <%= subtotal %></h1>
			                        
			                        <%
			                        			
										        }    
										    }   
										%>
			                    </div>
			                </div>
			            </div>
			        </div>
        
        <%
				}	
			}
        %>
        
	        <div class="rectangle-3 row">
	            <h1 class="textoResumen"> Resumen </h1>
	            <div class="horizontal-line"></div>
	            
	                <div class="col-md-6 mt-3">
	                    <h1 class="subtotal"> Subtotal </h1>
	                    <h1 class="envio"> Envio </h1>
	                    <h1 class="impuestos"> impuestos</h1>
	                </div>
	                <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
	                    <h1 class="subtotal2">$ <%= Math.round(subtotalTodo) %></h1>
	                    <h1 class="envio2"> 
	    					<%= (orden != null && orden.getDetallesEnvio() != null) ? orden.getDetallesEnvio().getPrecioEnvio() : 0 %> 
						</h1>
						<%
							float impuestos = 0f;
							impuestos = subtotalTodo * 0.02f;
							df.format(impuestos);
						%>
	                    <h1 class="impuestos2">$ <%= Math.round(impuestos) %></h1>
	                </div>
	            <div class="horizontal-line"></div>
	            <div class="col-md-6 mt-3">
	                <h1 class="total"> total </h1>
	            </div>
	            <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
	                <h1 class="total2">$ <%= Math.round(subtotalTodo + (subtotalTodo * 0.02)) %></h1>
	            </div>
	            
	            <div id="tablaHistorialEstados" class="mt-4">
				    <h3>Historial de cambios de estado</h3>
				    <table class="table table-striped">
				        <thead>
				            <tr>
				                <th>Estado</th>
				                <th>Fecha</th>
				            </tr>
				        </thead>
				        <tbody>
				            <%
				                // Verificar si existe historial de estados para mostrar
				                if (orden != null && orden.getCambios() != null) {
				                    for (CambioEstado historial : orden.getCambios()) {
				                        String estadoFormateado = "";
				                        switch (historial.getEstado()) {
				                            case comprada:
				                                estadoFormateado = "Comprada";
				                                break;
				                            case enPreparacion:
				                                estadoFormateado = "En preparación";
				                                break;
				                            case enCamino:
				                                estadoFormateado = "En camino";
				                                break;
				                            case entregada:
				                                estadoFormateado = "Entregada";
				                                break;
				                            // Agrega más casos si existen otros estados
				                        }
				            %>
				                        <tr>
				                            <td><%= estadoFormateado %></td>
				                            <td><%= historial.getFecha() != null ? historial.getFecha().toString() : "Sin fecha" %></td>
				                        </tr>
				            <%
				                    }
				                } else {
				            %>
				                    <tr>
				                        <td colspan="2">No hay historial de estados disponible.</td>
				                    </tr>
				            <%
				                }
				            %>
				        </tbody>
				    </table>
				</div>
	            
	            <%
	            
	            if(orden.getEstado() != Estado.entregada) {
	            
	            %>
	            
	            <div class="col-md-12 mt-5 d-flex flex-column align-items-end">
				    <form class="d-flex" action="cambiarEstadoOrden" method="POST">
				    	<%
		            		if (notMobile) {
				    	%>
				        <select name="estadoOrden" class="form-select me-2" required>
				            <%
				                if (usuarioActual instanceof Cliente) {
				            %>
				                <option value="entregada">Lo recibí</option>
				            <%
				                } else if (usuarioActual instanceof Proveedor) {
				            %>
				                <option value="enPreparacion">Lo estoy preparando</option>
				                <option value="enCamino">Lo envié</option>
				            <%
				                }
				            %>
				        </select>
				        <%
		            		}
				        %>
				
				        <%
				            String estadoFormateado = "";
				            if (orden.getEstado() != null) {
				                switch (orden.getEstado()) {
				                    case comprada:
				                        estadoFormateado = "Comprada";
				                        break;
				                    case enPreparacion:
				                        estadoFormateado = "En preparación";
				                        break;
				                    case enCamino:
				                        estadoFormateado = "En camino";
				                        break;
				                    case entregada:
				                        estadoFormateado = "Entregada";
				                        break;
				                }
				        %>
				            <input type="text" name="estadoOrden" class="form-control me-2" value="<%= estadoFormateado %>" disabled>
				        <%
				            }
				        %>
				
				        <input type="text" name="numeroOrden" class="d-none" value="<%= orden.getNumero() %>">
				
				    	<%
		            		if (notMobile) {
				    	%>
				        <button type="submit" class="btn btn-success"> Cambiar estado </button>
				    	<%
		            		}
				    	%>
				    </form>
				</div>
	            
	            <%
	            
           		}
	            
	            %>
	            
	            <div class="col-md-12 mt-5 d-flex flex-column align-items-end">
	                <button type="button" class="button-volver" id="volver" onclick="window.location.href='infoUsuario'"> Volver </button>
	            </div>
	        </div>   
	    </main>
    
    <%
    	}else{    
    %>
    	<p>No hay ordenes disponibles</p>
    	
   	<%
    	}
   	%>
    
    
    <jsp:include page="/WEB-INF/template/footer.jsp"/>
    
</body>

<script src="js/infoOrdenCompra.js"></script>
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="/media/js/infoOrdenCompra.js"></script>
</html>