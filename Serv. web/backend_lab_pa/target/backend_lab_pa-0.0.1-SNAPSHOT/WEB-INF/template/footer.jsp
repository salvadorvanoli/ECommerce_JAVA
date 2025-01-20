<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%
	String userAgent = request.getHeader("User-Agent");
	boolean notMobile = (userAgent != null && ! userAgent.toLowerCase().contains("mobile"));
%>

<link rel="stylesheet" href="media/css/footer.css">

<footer class="row col-12">
	<br>
	<br>
	<br>
	<hr>
	<br>
	<br>
	<br>
	<div class="col-4">
	    <img class="imagen-utec" src="media/images/utec.webp" alt="UTEC en la vida real">
	    <p class="color-rosa">Calle Dr Evaristo Ciganda 461, 80000 San José de Mayo, Departamento de San José</p>
	    <p class="color-marron">©Flamin-Go</p>
	</div>
	<div class="col-12 col-md footer-segundaParte">
	    <h6 class="color-marron">Navegar</h6>
	    <br>
	    <h6 class="color-rosa" onclick="window.location.href='home'" style="cursor: pointer;">Inicio</h6>
	    <%
           	if (notMobile) {
        %>
	    <h6 class="color-rosa" onclick="window.location.href='Catalogo'" style="cursor: pointer;">Catálogo</h6>
	    <%
           	}
	    %>
	    <h6 class="color-rosa" onclick="window.location.href='infoUsuario'" style="cursor: pointer;">Perfil</h6>
	    <%
           	if (notMobile) {
        %>
	    <h6 class="color-rosa" onclick="window.location.href='carrito'" style="cursor: pointer;">Carrito</h6>
	    <%
           	}
	    %>
	</div>
</footer>

<script src="media/js/init.js"></script>
<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script src="media/js/navbar.js"></script>