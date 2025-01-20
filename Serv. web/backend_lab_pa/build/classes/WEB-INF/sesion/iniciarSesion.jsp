<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'> <!-- Importamos la letra -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/css/iniciarSesion.css">
</head>

<%
	String userAgent = request.getHeader("User-Agent");
	boolean notMobile = (userAgent != null && ! userAgent.toLowerCase().contains("mobile"));
%>

<body>
    <main class="container-fluid p-0 d-flex">
        <article class="rectangle-1 text-wrap">
            <!-- Imagen del registro -->
            <img src="${pageContext.request.contextPath}/media/images/Flamin-Go.webp" alt="Flamin-Go" width="50px" height="50px">
            <h1 class="w-100 textoiniciarSesion"> Iniciar Sesión </h1>

            <!-- Formulario de inicio de sesión -->
            <form id="loginForm" action="${pageContext.request.contextPath}/iniciarsesion" method="post">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="floatingInput" name="emailOrNickname" placeholder="name@example.com" required>
                    <label for="floatingInput">Correo electrónico | Nickname</label>
                </div>
                
                <div class="form-floating mb-3">
                    <input type="password" class="form-control" id="floatingRepeatPassword" name="password" placeholder="Contraseña" required>
                    <label for="floatingRepeatPassword">Contraseña</label>
                </div>
                
                <%
                	if (notMobile) {
                %>
                <p id="aRegistrarse" class="text-primary text-decoration-underline" style="cursor: pointer;">
                    ¿No tienes una cuenta? Click aquí para registrarse.
                </p>
                <%
                	}
                %>
                
                <div id="error-mensaje" class="text-danger mt-2" style="display: <% if (request.getAttribute("error") != null) { %> block <% } else { %> none <% } %>;">
                    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
                </div>

                <div class="row g-2">
                    <div class="col-md-6">
                        <button type="button" class="button-cancelar" id="cancelarBtn"> Cancelar</button>
                    </div>
                    <div class="col-md-6">
                        <button type="submit" class="button-iniciarSesion"> Iniciar sesión</button>
                    </div>
                </div>
            </form>
        </article>
    </main>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const aRegistrarse = document.getElementById('aRegistrarse');

            aRegistrarse.onclick = function() {
                window.location.href = '${pageContext.request.contextPath}/registrar'; // Redirigir a la página de registro
            };

            document.getElementById("cancelarBtn").addEventListener("click", () => {
                window.location.href = '${pageContext.request.contextPath}/home'; // Redirigir a la página principal
            });
        });
    </script>

    <!-- Scripts externos -->
    <script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
