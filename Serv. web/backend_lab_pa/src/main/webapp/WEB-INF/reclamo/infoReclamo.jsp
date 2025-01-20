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
<%@ page import="services.DtReclamo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>infoReclamo</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="media/css/infoOrdenCompra.css">
</head>
<body>
	
	<jsp:include page="/WEB-INF/template/header.jsp"/>
	
<%
    String userAgent = request.getHeader("User-Agent");
    boolean notMobile = (userAgent != null && !userAgent.toLowerCase().contains("mobile"));

    
   
    DtReclamo reclamo = (DtReclamo) request.getAttribute("reclamo");
    int cantidad = 0;
    String nick = reclamo.getNicknameCliente();
   String nombre = reclamo.getNombreCliente();
   String apellido = reclamo.getApellidoCliente();
    DecimalFormat df = new DecimalFormat("#.#");
    String contenido = reclamo.getMensaje();
    int dia = reclamo.getDia();
    int mes = reclamo.getMes();
    int anio = reclamo.getAnio();
    
%>

<% if (reclamo != null) { %>
    <main class="container-fluid">
        <!-- Sección de Resumen -->
        <div class="rectangle-3 row">
            <h1 class="textoResumen"> Reclamo de <%= nick %> </h1>

            <div class="horizontal-line"></div>
            <div class="col-md-6 mt-3">
                <h1 class="subtotal"> Nombre </h1>
                <h1 class="envio"> Apellido </h1>
                <h1 class="impuestos"> Fecha</h1>
            </div>
            <div class="col-md-6 mt-3 d-flex flex-column align-items-end">
                <h1 class="subtotal2"><%= nombre %></h1>
                <h1 class="envio2"><%= apellido %></h1>

                
                <h1 class="impuestos2"> <%= dia + "/" + mes + "/" + anio %> </h1>
            </div>
            <div class="horizontal-line"></div>
            <div class="col-md-6 mt-5">
                <h1 class="total"> Producto </h1>
            </div>
           
            
            
            
        </div>   

        <!-- Sección de Detalles de Productos -->
        <% if (reclamo != null) { 
           
                
        %>
            <div class="rectangle-1" onclick="window.location.href='infoProducto?productoSeleccionado=<%= reclamo.getNumReferencia() %>'">
                <div class="rectangle-2">
                    <div class="row"> 
                        <div class="col-md-2 d-flex">
                            <div class="fotosProductos">
                                <img src="<%= (reclamo != null && reclamo.getImagenProd() != null && !reclamo.getImagenProd().isEmpty()) ? "/backend_lab_pa/getImagen?ruta=" + reclamo.getImagenProd().get(0) : "media/images/default.webp" %>" alt="<%= (reclamo != null) ? reclamo.getNombreProd() : "Imagen no disponible" %>" class="imagenProducto" onerror="this.onerror=null; this.src='/media/images/default.webp';">
                            </div>   
                        </div>
                        <div class="col-md-4 d-flex flex-column justify-content-start p-0">
                            <h1 class="nombresProductos text-start mt-3" style="cursor: pointer;"><%= reclamo.getNombreProd() %></h1>    
                            <h2 class="descripcionProductos text-start m-0"><%= reclamo.getDescripProd()  %></h2>
                            <h2 class="precioProductos text-start mt-3">$ <%= reclamo.getPrecioProd() %></h2>
                        </div>
                        <div class="col-md-4">
                            <h1 class="numProducto text-start mt-3">N° referencia: <%= reclamo.getNumReferencia() %></h1>
                                    
                        </div>
                        
                    </div>
                </div>
            </div>
           
        <% 
            }   
        } 
        %>
        <div class="col-md-6 mt-5">
               <h1 class="textoResumen" style="margin-top: 3rem; text-align: center;">Contenido</h1>
            </div>
      

<div class="rectangle-1" style="width: 90%; border: 1px solid #AAAAAA; padding: 16px; position: relative;">
    <h1 style="
        width: 100%; /* Ocupa todo el ancho del contenedor */
        height: auto; /* Ajusta la altura al contenido */
        color: #606060;
        font-weight: 400;
        font-size: 18px;
        line-height: 1.3;
        word-wrap: break-word; /* Permite que el texto haga salto de línea */
        margin-top: 0; /* Elimina el margen superior del texto */
        text-align: left; /* Alinea el texto al lado izquierdo de la caja */
    ">
         <%= contenido %>
    </h1>
    
</div>

    <div class="col-md-12 mt-5 d-flex flex-column align-items-end">
    <button type="button" class="button-volver" id="button-volver-info-reclamo" onclick="window.location.href='infoUsuario'" style="padding-right: 20px;"> Volver </button>
</div>



        
                   
    </main>


    <jsp:include page="/WEB-INF/template/footer.jsp"/>
    
</body>

<script src="js/infoOrdenCompra.js"></script>
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="/media/js/infoOrdenCompra.js"></script>
</html>