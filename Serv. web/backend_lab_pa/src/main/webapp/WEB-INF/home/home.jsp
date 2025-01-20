<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <jsp:include page="/WEB-INF/template/head.jsp" />
</head>	

<body>
	
	<jsp:include page="/WEB-INF/template/header.jsp" />

	<div class="container-fluid">
	    <div class="row">
	        <div class="col-12">
	            <div class="text-center">
	                <img src="media/images/InicioPortada.webp" alt="Flamingo Image" class="img-fluid" id="imagenHome">
	            </div>
	        </div>
		</div>
		
		<div class="row>">
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

