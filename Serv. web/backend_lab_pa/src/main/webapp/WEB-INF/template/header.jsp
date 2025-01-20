<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
	String userAgent = request.getHeader("User-Agent");
	boolean notMobile = (userAgent != null && ! userAgent.toLowerCase().contains("mobile"));
%>

<link rel="stylesheet" href="media/css/header.css">

<nav class="navbar navbar-expand-md ms-5 me-5">
    <div class="container-fluid text-center">
        <a class="navbar-brand me-5" href="#">
            <img src="media/images/Flamin-Go.webp" alt="Flamin-Go" class="flamin-go-pic" onclick="window.location.href='home';">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon" onclick="searchbar()"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent"> <!-- d-flex justify-content-around -->
       		<%
               	if (notMobile) {
            %>
            <form class="d-flex me-lg-4" action="Catalogo" method="POST">
                <input class="form-control me-2 barra-busqueda" name="textoBusqueda" type="search" placeholder="Buscar..." aria-label="Search" id="searchbar">
                <button class="btn btn-outline-success" type="submit">Buscar</button>
            </form>
            <%
               	}
            %>
            <ul class="d-flex align-items-md-center <% if (notMobile) { %>justify-content-between<% } else { %>justify-content-around<% } %> w-75 navbar-nav ms-lg-5 ms-3 me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="home">Inicio</a>
                </li>
                <%
                	if (notMobile) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="Catalogo">Catálogo</a>
                </li>
                <%
                	}
                %>
                <li class="nav-item">
                    <a class="nav-link" href="infoUsuario"><i class="fa-solid fa-circle-user" id="icono-usuario"></i></a>
                </li>
                <%
                	if (notMobile) {
                %>
                <li class="nav-item text-nowrap">
                    <a class="nav-link px-3" id="carrito-button" href="/backend_lab_pa/carrito"><i class="fa-solid fa-cart-shopping"></i>&nbsp;&nbsp;Carrito</a> <!-- aria-disabled="true" para desactivar -->
                </li>
                <%
                	}
                %>
            </ul>
        </div>
    </div>
</nav>