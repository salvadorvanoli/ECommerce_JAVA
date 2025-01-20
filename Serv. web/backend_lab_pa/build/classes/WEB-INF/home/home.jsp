<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--
<%@page import="com.flamingo.models.Cliente"%>
--%>
<%@page import="clases.Cliente"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
</head>	

<body>
	
	<jsp:include page="/WEB-INF/template/header.jsp" />
	
	<div class="container-md container-fluid">
	    <div class="row">
	        <!-- Imagen del flamingo -->
	        <div class="col-12">
	            <div class="text-center">
	                <img src="media/images/InicioPortada.webp" alt="Flamingo Image" class="img-fluid rounded">
	            </div>
	        </div>
		</div>
		
		<div class="row>">
			<!-- Recuadro con los textos -->
			<div class="col-md-12 text-center mt-4 mb-5">
			    <div class="recuadro d-flex justify-content-center align-items-center flex-column">
			        <div class="acercaNosotros">Acerca de nosotros</div>
			        <div class="equipo-flamin-go">Equipo Flamin-Go</div>
			    </div>
			</div>
		</div>
	   
	</div>

	<jsp:include page="/WEB-INF/template/footer.jsp" />
	
</body>
</html>

