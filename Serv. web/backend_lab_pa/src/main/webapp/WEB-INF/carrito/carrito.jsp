<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="services.DtUsuario"%>
<%@page import="services.DtCliente"%>
<%@page import="services.DtCantidad"%>
<%@page import="services.DtProducto"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <link rel="stylesheet" type="text/css" href="media/css/carrito.css">
    <title>Carrito</title>
</head>
<body>

	<!-- NAVBAR -->
	<jsp:include page="/WEB-INF/template/header.jsp"/>

	<%
		
	%>

	<main>
	<%	
		Object obj = session.getAttribute("usuarioActual");
		if (obj != null) {
			if (obj instanceof DtUsuario) {
				DtUsuario user = (DtUsuario) obj;
				if (user instanceof DtCliente){
					DtCliente cli = (DtCliente) user;
					
					List<DtCantidad> carrito = (List<DtCantidad>) request.getAttribute("carrito");

					if (!carrito.isEmpty()) {
						%>
						
						<!-- ENCABEZADO DE FASES -->
					    <div class="container-fluid p-0" id="encabezado1">
					        <div id="rectangulo-fase-estetica">
					            <div class="container-lg">
						            <div class="row rectangulo-fase align-items-end">
						                <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
						                    <h1 class="titulo-fase-principal">1. Carrito de compra</h1>
						                </div>
						                <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
						                    <h1 class="titulo-fase-secundaria">2. Detalles del envío</h1>
						                </div>
						                <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
						                    <h1 class="titulo-fase-secundaria">3. Método de pago</h1>
						                </div>
						                <div class="row d-flex align-items-end m-0 p-0">
						                    <div class="col-4 linea-principal"></div>
						                    <div class="col-8 linea-secundario"></div>
						                </div>
						            </div>
					        	</div>
					        </div>
					    </div>
					    
					    <div class="container-md container-fluid" id="seccion1">
					        <div class="row d-flex justify-content-center">
					
					            <form class="row g-3 needs-validation" novalidate id="formCarrito"> <!-- DUDOSOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->
					
					                <div class="col-md-8 col-12 mt-5">
					
					                    <h1 id="titulo-carrito">Carrito de Compra</h1>
					
					                    <!-- CONTENEDOR DE LOS PRODUCTOS DEL CARRITO -->
					                    <div class="contenedor-productos" id="carrito">
	
					                    </div>
					
					                </div>
					
					                <!-- RESUMEN DEL CARRITO -->
					                <div class="col-md-4 col-12 mt-5">
					                    <h1 class="titulo-resumen">Resumen</h1>
					                    <div class="linea-resumen my-2"></div>
					                    <div class="row texto-resumen mt-3">
					                        <p class="col-6">SUBTOTAL</p>
					                        <p class="col-6 text-end precio-subtotal">$0</p>
					                    </div>
					                    <div class="row texto-resumen">
					                        <p class="col-6">ENVÍO</p>
					                        <p class="col-6 text-end precio-envio">GRATIS</p>
					                    </div>
					                    <div class="row texto-resumen">
					                        <p class="col-6">IMPUESTOS</p>
					                        <p class="col-6 text-end precio-impuestos">$0</p>
					                    </div>
					                    <div class="linea-resumen mb-3"></div>
					                    <div class="row texto-total">
					                        <p class="col-6">TOTAL</p>
					                        <p class="col-6 text-end precio-total">$0</p>
					                    </div>
					                </div>
					
					                <!-- BOTONES PARA CONTINUAR O CANCELAR -->
					                <div class="row">
					                    <div class="col-12">
					                        
					                        <input type="submit" class="btn siguiente-button my-4 me-4" id="botonSiguiente1" value="Siguiente">
					                        <input type="button" class="btn cancelar-button my-4" id="botonVolver" value="Volver">
					
					                    </div>
					                </div>
					            </form>
					
					        </div>
					    </div>
					
					    <div class="container-fluid p-0 d-none" id="encabezado2">
					        <div id="rectangulo-fase-estetica">
					            <div class="container-lg">
					                <div class="row rectangulo-fase align-items-end">
					                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                        <h1 class="titulo-fase-secundaria">1. Carrito de compra</h1>
					                    </div>
					                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                        <h1 class="titulo-fase-principal">2. Detalles del envío</h1>
					                    </div>
					                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                        <h1 class="titulo-fase-secundaria">3. Método de pago</h1>
					                    </div>
					                    <div class="row d-flex align-items-end m-0 p-0">
					                        <div class="col-4 linea-secundario"></div>
					                        <div class="col-4 linea-principal"></div>
					                        <div class="col-4 linea-secundario"></div>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					
					    <div class="container-md container-fluid d-none" id="seccion2">
					        <div class="row d-flex justify-content-center">
					
					            <form class="row g-3 needs-validation" novalidate id="formEnvio"> <!-- DUDOSOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->
					
					                <div class="col-md-8 col-12 mt-5">
					
					                    <h1 class="titulo-resumen">Detalles del envío</h1>
					                    
					                    <div class="row mb-md-4">
					                        <div class="form-floating col-md-6 mb-md-0 mb-4">
					                            <input type="text" class="form-control estetica-input" id="nombre" placeholder="Nombre" required pattern="[A-Za-z]+(( ?([A-Za-z]+))?)+">
					                            <label for="nombre" class="ms-2 label-input">Nombre</label>
					                            <div class="invalid-feedback">
					                                <i class="fa-solid fa-triangle-exclamation"></i> "Nombre" no puede estar vacío (solo se aceptan letras y espacios).
					                            </div>
					                        </div>
					                        <div class="form-floating col-md-6 mb-md-0 mb-4">
					                            <input type="text" class="form-control estetica-input" id="apellido" placeholder="Apellido" required pattern="[A-Za-z]+(( ?([A-Za-z]+))?)+">
					                            <label for="apellido" class="ms-2 label-input">Apellido</label>
					                            <div class="invalid-feedback">
					                                <i class="fa-solid fa-triangle-exclamation"></i> "Apellido" no puede estar vacío (solo se aceptan letras).
					                            </div>
					                        </div>
					                    </div>
					
					                    
					                    <div class="form-floating mb-4">
					                        <input type="text" class="form-control estetica-input" id="direccion1" placeholder="Dirección" required pattern="[A-Za-z0-9]+(( ?([A-Za-z0-9]+))?)+ [0-9]+">
					                        <label for="direccion" class="label-input">Dirección</label>
					                        <div class="invalid-feedback">
					                            <i class="fa-solid fa-triangle-exclamation"></i> "Dirección" no puede estar vacío (calle seguido de un número).
					                        </div>
					                    </div>
					
					
					                    <div class="form-floating mb-4">
					                        <input type="text" class="form-control estetica-input" id="direccion2" placeholder="Dirección 2">
					                        <label for="direccion2" class="label-input">Dirección 2</label>
					                    </div>
					
					                    <div class="row direcciones mb-md-4">
					                        <div class="col-md-6 mb-md-0 mb-4">
					                            <select class="form-select estetica-input" aria-label="Selecciona un departamento" id="departamento" required>
					                                <option value="" selected>Elige un departamento</option>
					                                <option value="Artigas">Artigas</option>
					                                <option value="Canelones">Canelones</option>
					                                <option value="Cerro Largo">Cerro Largo</option>
					                                <option value="Colonia">Colonia</option>
					                                <option value="Durazno">Durazno</option>
					                                <option value="Flores">Flores</option>
					                                <option value="Florida">Florida</option>
					                                <option value="Lavalleja">Lavalleja</option>
					                                <option value="Maldonado">Maldonado</option>
					                                <option value="Montevideo">Montevideo</option>
					                                <option value="Paysandú">Paysandú</option>
					                                <option value="Río Negro">Río Negro</option>
					                                <option value="Rivera">Rivera</option>
					                                <option value="Rocha">Rocha</option>
					                                <option value="Salto">Salto</option>
					                                <option value="San José">San José</option>
					                                <option value="Soriano">Soriano</option>
					                                <option value="Tacuarembó">Tacuarembó</option>
					                                <option value="Treinta Y Tres">Treinta Y Tres</option>
					                            </select>
					                            <div class="invalid-feedback">
					                                <i class="fa-solid fa-triangle-exclamation"></i> Se debe seleccionar un departamento.
					                            </div>
					                        </div>
					                        <div class="col-md-6 mb-md-0 mb-4">
					                            <select class="form-select estetica-input" aria-label="Selecciona una ciudad" id="ciudad" disabled required>
					                                <option value="" selected>Elige una ciudad</option>
					                            </select>
					                            <div class="invalid-feedback">
					                                <i class="fa-solid fa-triangle-exclamation"></i> Se debe seleccionar una ciudad.
					                            </div>
					                        </div>
					                    </div>
					
					                    <div class="row mb-md-4">
					                        <div class="form-floating col-md-6 mb-md-0 mb-4">
					                            <input type="text" class="form-control estetica-input" id="codPostal" placeholder="Código Postal" required pattern="[0-9]{5}">
					                            <label for="codPostal" class="ms-2 label-input">Código Postal</label>
					                            <div class="invalid-feedback">
					                                <i class="fa-solid fa-triangle-exclamation"></i> "Código Postal" no puede estar vacío (5 dígitos).
					                            </div>
					                        </div>
					                        <div class="form-floating col-md-6 mb-md-0 mb-4">
					                            <input type="text" class="form-control estetica-input" id="numTelefono" placeholder="Número de teléfono" required pattern="09[1-9][0-9]{6}">
					                            <label for="numTelefono" class="ms-2 label-input">Número de teléfono</label>
					                            <div class="invalid-feedback">
					                                <i class="fa-solid fa-triangle-exclamation"></i> "Número de teléfono" no puede estar vacío (ejemplo: 091234567).
					                            </div>
					                        </div>
					                    </div>
					
					                    <div class="linea-resumen my-4"></div>
					
					                    <div class="row d-flex justify-content-around">
					                        <div class="col-md-5 col-8 mb-md-0 mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
					                            <div class="row w-100">
					                                <div class="col-md-2 col-3 d-flex align-items-center justify-content-end">
					                                    <input class="form-check-input mt-0" type="radio" name="tipoEnvio" id="envioGratis" value="free" aria-label="Radio button for following text input" checked onchange="modificarEnvio()">
					                                </div>
					                                <div class="col-md-10 col-9">
					                                    <p class="p-0 m-0">Envío gratuito</p>
					                                    <p class="p-0 m-0">Entre 2 - 5 días</p>
					                                </div>
					                            </div>
					                        </div>
					
					                        <div class="col-md-5 col-8 mb-md-0 mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
					                            <div class="row w-100">
					                                <div class="col-md-2 col-3 d-flex align-items-center justify-content-end">
					                                    <input class="form-check-input mt-0" type="radio" name="tipoEnvio" id="envioVip" value="vip" aria-label="Radio button for following text input" onchange="modificarEnvio()">
					                                </div>
					                                <div class="col-md-10 col-9">
					                                    <p class="p-0 m-0">Envío VIP - $20</p>
					                                    <p class="p-0 m-0">24 horas luego de la compra</p>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					
					                    <div class="linea-resumen my-4"></div>
					
					                </div>
					
					                <!-- RESUMEN DEL CARRITO -->
					                <div class="col-md-4 col-12 mt-5">
					
					                    <h1 class="titulo-resumen">Resumen</h1>
					                    <!-- CONTENEDOR DE LOS PRODUCTOS DEL CARRITO -->
					                    <div class="contenedor-productos contenedor-secundario">
					
					                        
					
					                    </div>
					
					                    <div class="linea-resumen my-2 mt-4"></div>
					                    <div class="row texto-resumen mt-3">
					                        <p class="col-6">SUBTOTAL</p>
					                        <p class="col-6 text-end precio-subtotal">$0</p>
					                    </div>
					                    <div class="row texto-resumen">
					                        <p class="col-6">ENVÍO</p>
					                        <p class="col-6 text-end precio-envio">GRATIS</p>
					                    </div>
					                    <div class="row texto-resumen">
					                        <p class="col-6">IMPUESTOS</p>
					                        <p class="col-6 text-end precio-impuestos">$0</p>
					                    </div>
					                    <div class="linea-resumen mb-3"></div>
					                    <div class="row texto-total">
					                        <p class="col-6">TOTAL</p>
					                        <p class="col-6 text-end precio-total">$0</p>
					                    </div>
					
					                </div>
					
					                <!-- BOTONES PARA CONTINUAR O CANCELAR -->
					                <div class="row">
					                    <div class="col-12">
					                        
					                        <input type="submit" class="btn siguiente-button my-4 me-4" value="Siguiente" id="botonSiguiente2">
					                        <input type="button" class="btn cancelar-button my-4" value="Atrás" id="botonAtras1">
					                        
					
					                    </div>
					                </div>
					            </form>
					        </div>
					    </div>
					
					
					    <div class="container-fluid p-0 d-none" id="encabezado3">
					        <div id="rectangulo-fase-estetica">
					            <div class="container-lg">
					                <div class="row rectangulo-fase align-items-end">
					                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                        <h1 class="titulo-fase-secundaria">1. Carrito de compra</h1>
					                    </div>
					                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                        <h1 class="titulo-fase-secundaria">2. Detalles del envío</h1>
					                    </div>
					                    <div class="col-12 col-sm-4 d-flex align-items-center justify-content-center">
					                        <h1 class="titulo-fase-principal">3. Método de pago</h1>
					                    </div>
					                    <div class="row d-flex align-items-end m-0 p-0">
					                        <div class="col-8 linea-secundario"></div>
					                        <div class="col-4 linea-principal"></div>
					                    </div>
					                </div>
					            </div>
					        </div>
					    </div>
					
					
					    <div class="container-md container-fluid d-none" id="seccion3">
					        <div class="row d-flex justify-content-center">
					
					            <form class="row g-3 needs-validation" novalidate id="formPago">
					
					                <div class="col-md-8 col-12 mt-5">
					
					                    <h1 class="titulo-resumen">Método de pago</h1>
					
					                    <div class="mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
					                        <div class="row w-100">
					                            <div class="col-1 d-flex align-items-center justify-content-center">
					                                <input class="form-check-input mt-0" type="radio" name="tipoPago" id="pagoTarjeta" value="credito" aria-label="Radio button for following text input" checked>
					                            </div>
					                            <div class="col-11">
					                                <p class="p-0 m-0">Tarjeta de crédito</p>
					                                <p class="p-0 m-0">Realiza pagos rápidos y seguros con tu tarjeta de crédito. Aceptamos Visa, MasterCard, y más.</p>
					                            </div>
					                            <div class="row col-12">
					                                <div class="col-sm-6 col-12">
					                                    <div class="input-group m-3">
					                                        <span class="input-group-text" id="addon-wrapping"><i class="fa-regular fa-credit-card"></i></span>
					                                        <input type="text" class="form-control estetica-input" id="numTarjeta" placeholder="0000 0000 0000 0000" aria-label="Número de tarjeta" pattern="([0-9]{16}|(([0-9]){4} ){3}[0-9]{4})" required>
					                                        <div class="invalid-feedback">
					                                            <i class="fa-solid fa-triangle-exclamation"></i> "Número de tarjeta" no puede estar vacío (16 dígitos).
					                                        </div>
					                                    </div>
					                                </div>
					                                <div class="col-sm-3 col-6">
					                                    <div class="input-group m-3">
					                                        <span class="input-group-text" id="addon-wrapping"><i class="fa-solid fa-calendar-days"></i></span>
					                                        <input type="text" class="form-control estetica-input" id="fecVencimiento" placeholder="MM/YY" aria-label="Fecha de vencimiento" pattern="(0[1-9]|1[0-2])/[0-9]{2}" required maxlength="5">
					                                        <div class="invalid-feedback">
					                                            <i class="fa-solid fa-triangle-exclamation"></i> "Fecha de vencimiento" no puede estar vacío (la fecha no puede ser posterior a la fecha actual).
					                                        </div>
					                                    </div>
					                                </div>
					                                <div class="col-sm-3 col-6">
					                                    <div class="input-group m-3">
					                                        <span class="input-group-text" id="addon-wrapping"><i class="fa-solid fa-lock"></i></span>
					                                        <input type="text" class="form-control estetica-input" id="cvv" placeholder="CVV" aria-label="Código de verificación" pattern="[0-9]{3}" required maxlength="3">
					                                        <div class="invalid-feedback">
					                                            <i class="fa-solid fa-triangle-exclamation"></i> "Código de verificación" no puede estar vacío (3 dígitos).
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
					                            <div class="row col-12">
					                                <div class="input-group m-3">
					                                    <span class="input-group-text" id="addon-wrapping"><i class="fa-solid fa-user"></i></span>
					                                    <input type="text" class="form-control estetica-input" id="nomTitular" placeholder="Nombre del titular" aria-label="Nombre del titular" required pattern="[A-Za-z]+(( ?([A-Za-z]+))?)+">
					                                    <div class="invalid-feedback">
					                                        <i class="fa-solid fa-triangle-exclamation"></i> "Nombre del titular" no puede estar vacío.
					                                    </div>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					
					                    <div class="mb-4 opcion-box d-flex align-items-center justify-content-center py-2">
					                        <div class="row w-100">
					                            <div class="col-1 d-flex align-items-center justify-content-center">
					                                <input class="form-check-input mt-0" type="radio" name="tipoPago" id="pagoPaypal" value="paypal" aria-label="Radio button for following text input" disabled>
					                            </div>
					                            <div class="col-11">
					                                <p class="p-0 m-0">PayPal</p>
					                                <p class="p-0 m-0">Usa PayPal para pagar de forma segura sin compartir tu información financiera. Fácil, rápido y seguro.</p>
					                            </div>
					                        </div>
					                    </div>
					
					                </div>
					
					                <!-- RESUMEN DEL CARRITO -->
					                <div class="col-md-4 col-12 mt-5">
					
					                    <h1 class="titulo-resumen">Resumen</h1>
					                    <!-- CONTENEDOR DE LOS PRODUCTOS DEL CARRITO -->
					                    <div class="contenedor-productos contenedor-secundario">
					
					                        
					
					                    </div>
					
					                    <div class="linea-resumen my-2 mt-4"></div>
					                    <div class="row texto-resumen mt-3">
					                        <p class="col-6">SUBTOTAL</p>
					                        <p class="col-6 text-end precio-subtotal">$0</p>
					                    </div>
					                    <div class="row texto-resumen">
					                        <p class="col-6">ENVÍO</p>
					                        <p class="col-6 text-end precio-envio">GRATIS</p>
					                    </div>
					                    <div class="row texto-resumen">
					                        <p class="col-6">IMPUESTOS</p>
					                        <p class="col-6 text-end precio-impuestos">$0</p>
					                    </div>
					                    <div class="linea-resumen mb-3"></div>
					                    <div class="row texto-total">
					                        <p class="col-6">TOTAL</p>
					                        <p class="col-6 text-end precio-total">$0</p>
					                    </div>
					
					                </div>
					
					                <!-- BOTONES PARA CONTINUAR O CANCELAR -->
					                <div class="row">
					                    <div class="col-12">
					                        
					                        <input type="submit" class="btn siguiente-button my-4 me-4" value="Pagar ahora" id="botonPagar">
					                        <input type="button" class="btn cancelar-button my-4" value="Atrás" id="botonAtras2">
					
					                    </div>
					                </div>
					
					            </form>
					
					        </div>
					    </div>
							
					<%
					} else {
						%>
						<div class="text-center fs-1 m-5">
							No hay productos en el carrito actualmente.
						</div>
						<%
					}
				} else {
					%>
					<div class="text-center fs-1 m-5">
						El usuario ingresado no es un Cliente.
					</div>
					<%
				}
			} else {
				%>
				<div class="text-center fs-1 m-5">
					El usuario ingresado no es válido.
				</div>
				<%
			}
		} else {
			%>
				<div class="text-center fs-1 m-5">
					Aún no tienes la sesión iniciada.
				</div>
			<%
			// Falta redirigir al usuario
		}
	
	%>
	</main>


    <!-- FOOTER -->
	<jsp:include page="/WEB-INF/template/footer.jsp"/>
    <script type="text/javascript">
    	
    	let carritoActual;
    
    	async function getCarrito(URL, tipo) {
    		try {
    	        const response = await fetch(URL, {
    	            method: 'GET',
    	            headers: {
    	                'tipo': tipo
    	            }
    	        });
    	        const data = await response.json();
    	        // console.log('Datos recibidos: ', data);
    	        return data;
    	    } catch (error) {
    	        // console.error('Hubo un problema con la solicitud: ', error);
    	    }
    	}
    	
    	async function updateCarrito(URL, body, tipo) {
    		try {
    			
    			// console.log(body);
    			
    	        const response = await fetch(URL, {
    	            method: 'POST',
    	            headers: {
    	                'Content-Type': 'application/json',
    	                'tipo': tipo
    	            },
    	            body: JSON.stringify(body)
    	        });

    	        
    	        if (!response.ok) {
    	            throw new Error("Error en la solicitud: " + respuesta.status);
    	        }

    	        const result = await response.text();
    	        // console.log("Datos recibidos del servidor: ", result);
    	        return result;
    	        
    	    } catch (error) {
    	        // console.error('Hubo un problema con la solicitud:', error);
    	    }
    	}
    	
    	
    	
	    const subtotales = document.querySelectorAll(".precio-subtotal");
	    const envios = document.querySelectorAll(".precio-envio");
	    const impuestos = document.querySelectorAll(".precio-impuestos");
	    const totales = document.querySelectorAll(".precio-total");

	    const envioGratis = document.querySelector("#envioGratis");
	    const envioVip = document.querySelector("#envioVip");
	
	    const encabezado1 = document.querySelector("#encabezado1");
	    const seccion1 = document.querySelector("#seccion1");
	
	    const encabezado2 = document.querySelector("#encabezado2");
	    const seccion2 = document.querySelector("#seccion2");
	
	    const encabezado3 = document.querySelector("#encabezado3");
	    const seccion3 = document.querySelector("#seccion3");
	
	
	    function modificarTextos(array, text) {
	        array.forEach(item => {
	            item.textContent = text;
	        });
	    }
	
	
	    const carrito = document.querySelector("#carrito");
	    const contenedoresSecundarios = document.querySelectorAll(".contenedor-secundario");
	
	    function manejarCarritoVacio() {
	        encabezado1.remove();
	        seccion1.remove();
	        encabezado2.remove();
	        seccion2.remove();
	        encabezado3.remove();
	        seccion3.remove();
	        const texto = document.createElement("div");
	        texto.classList.add("text-center", "fs-1", "m-5");
	        texto.innerHTML = "No hay productos en el carrito actualmente";
	        document.querySelector("main").appendChild(texto);
	    }
	
	    function cargarProducto(id) {
            window.location.href = "/backend_lab_pa/infoProducto?productoSeleccionado=" + id;
	    }
	
	    function cargarElementosCarrito(array) {
	        if (array == undefined || ! array.length > 0){
	            manejarCarritoVacio();
	        } else {
	            let subtotal = 0;
	            for (let element = 0; element < array.length; element++){
	                let itemCarrito = document.createElement("div");
	                // console.log(array[element]);
	                
	                if(!array[element].producto.imagenes || array[element].producto.imagenes[0] == null || array[element].producto.imagenes[0] == ""){
	                	itemCarrito.innerHTML =
		                    `<div class="row my-3 d-flex align-items-center" id="producto` + array[element].producto.numReferencia + `">
		                        <div class="col-sm-3 col-4 d-flex align-items-center justify-content-center" onclick="cargarProducto(` + array[element].producto.numReferencia + `)">
		                            <img class="w-75" src="media/images/default.webp" alt="` + array[element].producto.nombre + `">
		                        </div>
		                        <div class="col-sm-6 col-4">
		                            <div class="row titulo-producto">
		                                <p class="col-sm-6 col-12">` + array[element].producto.nombre + `</p>
		                                <p class="col-sm-6 col-12">Nro. ` + array[element].producto.numReferencia + `</p>
		                            </div>
		                            <div class="row descripcion-producto d-none d-sm-block">
		                                <p>` + array[element].producto.descripcion + `</p>
		                            </div>
		                            <div class="row precio-producto">
		                                <p>$` + array[element].producto.precio + `</p>
		                            </div>
		                        </div>
		                        <div class="col-3">
		                            <div class="col-12">
		                                <label for="cantidad">Cantidad</label>
		                                <input type="number" name="cantidad" class="cantidad-producto" min="1" required value="` + array[element].cantidad + `" onchange="manejarCantidad(this, ` + array[element].producto.numReferencia + `)">
		                                <div class="invalid-feedback">
		                                    <i class="fa-solid fa-triangle-exclamation"></i> Valor inválido.
		                                </div>
		                            </div>
		                            <br>
		                            <div class="col-12">    
		                                <button type="button" class="btn btn-danger text-nowrap" onclick="eliminarItem(` + array[element].producto.numReferencia + `)"><i class="fa-solid fa-trash-can"></i></button>
		                            </div>
		                        </div>
		                    </div>`;
	                } else {
	                	itemCarrito.innerHTML =
		                    `<div class="row my-3 d-flex align-items-center" id="producto` + array[element].producto.numReferencia + `">
		                        <div class="col-sm-3 col-4 d-flex align-items-center justify-content-center" onclick="cargarProducto(` + array[element].producto.numReferencia + `)">
		                            <img class="w-75" src="` + array[element].producto.imagenes[0] + `" alt="` + array[element].producto.nombre + `">
		                        </div>
		                        <div class="col-sm-6 col-4">
		                            <div class="row titulo-producto">
		                                <p class="col-sm-6 col-12">` + array[element].producto.nombre + `</p>
		                                <p class="col-sm-6 col-12">Nro. ` + array[element].producto.numReferencia + `</p>
		                            </div>
		                            <div class="row descripcion-producto d-none d-sm-block">
		                                <p>` + array[element].producto.descripcion + `</p>
		                            </div>
		                            <div class="row precio-producto">
		                                <p>$` + array[element].producto.precio + `</p>
		                            </div>
		                        </div>
		                        <div class="col-3">
		                            <div class="col-12">
		                                <label for="cantidad">Cantidad</label>
		                                <input type="number" name="cantidad" class="cantidad-producto" min="1" required value="` + array[element].cantidad + `" onchange="manejarCantidad(this, ` + array[element].producto.numReferencia + `)">
		                                <div class="invalid-feedback">
		                                    <i class="fa-solid fa-triangle-exclamation"></i> Valor inválido.
		                                </div>
		                            </div>
		                            <br>
		                            <div class="col-12">    
		                                <button type="button" class="btn btn-danger text-nowrap" onclick="eliminarItem(` + array[element].producto.numReferencia + `)"><i class="fa-solid fa-trash-can"></i></button>
		                            </div>
		                        </div>
		                    </div>`;
	                }
	
	                carrito.appendChild(itemCarrito);
	
	                contenedoresSecundarios.forEach(contenedor => {
	                    let imagen;
	                    let itemContenedor = document.createElement("div");
	                    if (Array.isArray(array[element].producto.imagenes)){
	                        imagen = array[element].producto.imagenes[0];
	                    } else {
	                        imagen = array[element].producto.imagenes;
	                    }
	                    
	                    if(imagen == null || imagen == ""){
	                    	imagen = "media/images/default.webp";	
	                    }
	                    
	                    itemContenedor.innerHTML =
	                        `<div class="row my-3 d-flex align-items-center producto-secundario` + array[element].producto.numReferencia + `">
	                            <div class="col-4 d-flex align-items-center justify-content-center">
	                                <img class="w-75" src="` + imagen + `" alt="Zucaritas">
	                            </div>
	                            <div class="col-4">
	                                <div class="row titulo-producto">
	                                    <p>` + array[element].producto.nombre + `</p>
	                                </div>
	                                <div class="row precio-producto">
	                                    <p>$` + array[element].producto.precio + `</p>
	                                </div>
	                            </div>
	                            <div class="col-3">
	                                <div class="col-12">
	                                    <label for="cantidad">Cant.</label>
	                                    <input type="number" name="cantidad" class="cantidad-producto" disabled value="` + array[element].cantidad + `">
	                                </div>
	                            </div>
	                        </div>`;
	
	                    contenedor.appendChild(itemContenedor);
	                });
	
	                subtotal += Math.round(array[element].producto.precio * array[element].cantidad);
	
	            }
	
	            modificarTextos(subtotales, "$" + subtotal);
	            modificarTextos(envios, "GRATIS");
	            modificarTextos(impuestos, "$" + Math.round(subtotal * 0.02));
	            modificarTextos(totales, "$" + Math.round(subtotal * 1.02));
	
	        }
	    }
	
	
	    function modificarAllTextos(array){
	        let subtotal = 0;
	        
	        // console.log(array);
	
	        for(let i = 0; i < array.length; i++){
	            subtotal += array[i].producto.precio * array[i].cantidad;
	        }
	
	        modificarTextos(subtotales, "$" + Math.round(subtotal));
	        if (envioGratis.checked){
	            modificarTextos(envios, "GRATIS");
	        } else {
	            modificarTextos(envios, "$20");
	            subtotal += 20;
	        }
	        modificarTextos(impuestos, "$" + Math.round(subtotal * 0.02));
	        modificarTextos(totales, "$" + Math.round(subtotal * 1.02));
	    }
	
	
	    function manejarCantidad(input, id){
	        const cantidad = Number.parseInt(input.value);
	        if ( !(Number.isInteger(cantidad)) || cantidad < 1){
	            input.value = 1;
	        }
	
	        // const carritoActual = JSON.parse(localStorage.getItem('carritoActual'));
	
	        for (let i = 0; i < carritoActual.length; i++){
	            if (carritoActual[i].producto.numReferencia == id){
	                carritoActual[i].cantidad = Number.parseInt(input.value);
	                break;
	            }
	        }
	
	        const itemsSecundarios = document.querySelectorAll(".producto-secundario" + id);
	
	        itemsSecundarios.forEach(itemSecundario => {
	            itemSecundario.querySelector(".cantidad-producto").value = input.value;
	        });
	
	        modificarAllTextos(carritoActual);
	        
	        updateCarrito("/backend_lab_pa/manejarcarrito", [Number.parseInt(id), Number.parseInt(input.value)], "manejarCantidad");
	
	    }
	
	
	    async function eliminarItem(id){
			
	        carritoActual = carritoActual.filter(item => item.producto.numReferencia !== id);
	        // delete carritoActual[id];
	        
	        if (carritoActual.length == 0){ // Object.keys(carritoActual) == 0
	        	
	            encabezado1.remove();
	            seccion1.remove();
	            encabezado2.remove();
	            seccion2.remove();
	            encabezado3.remove();
	            seccion3.remove();
	            const texto = document.createElement("div");
	            texto.classList.add("text-center", "fs-1", "m-5");
	            texto.innerHTML = "No hay productos en el carrito actualmente.";
	            document.querySelector("main").appendChild(texto);
	            
	        } else {
		
		        modificarAllTextos(carritoActual);
		        
		        // console.log(document.querySelector("#producto" + id));
		
		        const item = document.querySelector("#producto" + id);
		        item.remove();
		
		        const itemsSecundarios = document.querySelectorAll(".producto-secundario" + id);
		
		        itemsSecundarios.forEach(itemSecundario => {
		            itemSecundario.remove();
		        });
	        
	        }
	        
	        await updateCarrito("/backend_lab_pa/manejarcarrito", id, "eliminarItem");
	
	    }
	
	
	    function modificarEnvio(){
	        // const carritoActual = JSON.parse(localStorage.getItem("carritoActual"));
	        let subtotal = 0;
	        for (let i = 0; i < carritoActual.length; i++) {
	            subtotal += carritoActual[i].producto.precio * carritoActual[i].cantidad;
	        }
	
	        // console.log(envioGratis.checked);
	
	        if (envioGratis.checked){
	            modificarTextos(envios, "GRATIS");
	            modificarTextos(impuestos, "$" + Math.round(subtotal * 0.02));
	            modificarTextos(totales, "$" + Math.round(subtotal + (subtotal * 0.02)));
	        } else {
	            modificarTextos(envios, "$20");
	            modificarTextos(impuestos, "$" + Math.round((subtotal + 20) * 0.02));
	            modificarTextos(totales, "$" + Math.round(subtotal + 20 + ((subtotal + 20) * 0.02)));
	        }
	    }
	
	    function mostrarAlerta(message, type, icon){
	        const alertaActual = document.querySelector(".alerta-carrito");
	        if (alertaActual != undefined)
	            alertaActual.remove();
	        const div = document.createElement("div");
	        div.innerHTML = 
	            `<div class="alert ${type} alert-dismissible fade show alerta-carrito fs-5 d-flex justify-items-center align-items-center" role="alert" id="alertaFormulario">
	                <strong>${icon}</strong> ${message}
	                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" onclick="alertaEnPantalla=false;"></button>
	            </div>`;
	        document.body.appendChild(div);
	    }
	
	
	    function checkUsuarioActual(){
	        if (usuarioActual == undefined){
	            encabezado1.remove();
	            seccion1.remove();
	            encabezado2.remove();
	            seccion2.remove();
	            encabezado3.remove();
	            seccion3.remove();
	            const texto = document.createElement("div");
	            texto.classList.add("text-center", "fs-1", "m-5");
	            texto.innerHTML = "Aún no tienes la sesión iniciada. Serás redirigido a inicio en unos segundos.";
	            document.querySelector("main").appendChild(texto);
	            setTimeout(function () {
	                window.location.href = "index.html";
	            }, 5000);
	        }
	    }
	
	    // checkUsuarioActual();
	
	
	
	    const form1 = document.querySelector("#formCarrito");
	    const form2 = document.querySelector("#formEnvio");
	    const form3 = document.querySelector("#formPago");
	    const formFinal = document.querySelector("#formFinal");
	
	    const departamentos = document.querySelector("#departamento");
	    const ciudades = document.querySelector("#ciudad");
	
	
	    form1.addEventListener('submit', function(e) {
	        e.preventDefault();
	        e.stopPropagation();
	        if (form1.checkValidity()) {
	            encabezado1.classList.add("d-none");
	            seccion1.classList.add("d-none");
	            encabezado2.classList.remove("d-none");
	            seccion2.classList.remove("d-none");
	        } else {
	            if (document.querySelector("#alertaFormulario") == undefined){
	                mostrarAlerta("Aún hay campos incompletos o con valores inválidos.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
	            }
	        }
	
	        form1.classList.add('was-validated');
	    }, false);
	
	    form2.addEventListener("submit", function(e) {
	        e.preventDefault();
	        e.stopPropagation();
	        if (form2.checkValidity()) {
	            encabezado2.classList.add("d-none");
	            seccion2.classList.add("d-none");
	            encabezado3.classList.remove("d-none");
	            seccion3.classList.remove("d-none");
	        } else {
	            if (document.querySelector("#alertaFormulario") == undefined){
	                mostrarAlerta("Aún hay campos incompletos o con valores inválidos.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
	            }
	        }
	
	        form2.classList.add('was-validated');
	    }, false);
	
	
	    async function agregarOrdenCompra() {
	        let idOrden = await getCarrito("/backend_lab_pa/manejarcarrito", "getIDOrden");

	        let subtotal = 0;
	        
	        // IMPORTANTE!!! Hacer un objeto a mano "Orden de Compra" con todos los datos (fecha, id, List<Cantidad>, FormaPago, DetallesEnvio). Despues, enviar ese objeto mediante POST.
	        
	        for(let i = 0; i < carritoActual.length; i++){
	            subtotal += carritoActual[i].producto.precio * carritoActual[i].cantidad;
	        }
	        
	        const nombre = document.querySelector("#nombre").value;
	        const apellido = document.querySelector("#apellido").value;
	        const direccion1 = document.querySelector("#direccion1").value;
	        const direccion2 = document.querySelector("#direccion2").value;
	        const departamento = document.querySelector("#departamento").value;
	        const ciudad = document.querySelector("#ciudad").value;
	        const codPostal = document.querySelector("#codPostal").value.toString();
	        const numTelefono = document.querySelector("#numTelefono").value.toString();
	
	        let tipoEnvio;
	        let precioEnvio;
	        if (document.querySelector("#envioGratis").checked) {
	            tipoEnvio = document.querySelector("#envioGratis").value;
	            precioEnvio = '0';
	        } else {
	            tipoEnvio = document.querySelector("#envioVip").value;
	            precioEnvio = '20';
	            subtotal += 20;
	        }
	
	        const detallesEnvio = {
	            "nombre": nombre,
	            "apellido": apellido,
	            "direccion1": direccion1,
	            "direccion2": direccion2,
	            "departamento": departamento,
	            "ciudad": ciudad,
	            "codPostal": codPostal,
	            "numTelefono": numTelefono,
	            "tipoEnvio": tipoEnvio,
	            "precioEnvio": precioEnvio
	        }
	
	        let formaPago;
	
	        if (document.querySelector("#pagoTarjeta").checked){
	            const numTarjeta = document.querySelector("#numTarjeta").value;
	            const fecVencimiento = document.querySelector("#fecVencimiento").value;
	            const cvv = document.querySelector("#cvv").value;
	            const nomTitular = document.querySelector("#nomTitular").value;
	            formaPago = {
	                "tipoPago": "credito",
	                "numTarjeta": numTarjeta,
	                "fecVencimiento": fecVencimiento,
	                "cvv": cvv,
	                "nomTitular": nomTitular
	            }
	        } else {
	            // Manejar el pago con PayPal
	        }
	        
	        const date = new Date();
	        
	        const fechaActual = {
	        		"dia": Number.parseInt(date.getUTCDate()),
	        		"mes": Number.parseInt(date.getUTCMonth() + 1),
	        		"anio": Number.parseInt(date.getUTCFullYear())
	        }
	        
	    	const total = Math.round(subtotal * 1.02)
	
	        const nuevaOrden = {
	        	"numero": Number.parseInt(idOrden),
	            "precioTotal": Number.parseFloat(total),
	            "fecha": fechaActual,
	            "cliente": null,
	            "proveedores": null,
	            "cantidad": Object.values(carritoActual),
	            "formaPago": formaPago,
	            "detallesEnvio": detallesEnvio,
	            "estado": "comprada"
	        }

	        updateCarrito("/backend_lab_pa/manejarcarrito", nuevaOrden, "realizarCompra");
	
	        /*
	        usuarioActual.ordenes.push(nuevaOrden);
	
	        localStorage.setItem("usuarioActual", JSON.stringify(usuarioActual));
	        for (let i = 0; i < usuarios.length; i++){
	            if (usuarios[i].nickname == usuarioActual.nickname){
	                usuarios[i] = usuarioActual;
	                break;
	            }
	        }
	        localStorage.setItem("usuarios", JSON.stringify(usuarios));
	        localStorage.setItem("carritoActual", "[]");
	        */
	        
	
	    }
	
	    function fechaValida(){
	        let partesFecha = document.querySelector("#fecVencimiento").value.split('/');
	
	        if (partesFecha.length != 2){
	            return false;
	        }
	
	        for (let i = 0; i < partesFecha.length; i++){
	            partesFecha[i] = Number.parseInt(partesFecha[i]);
	        }
	
	        const date = new Date();
	        const MM = Number.parseInt(date.getMonth()) + 1;
	        const year = date.getFullYear().toString();
	
	        // console.log(partesFecha);
	
	        const YY = Number.parseInt(year.slice(2));
	
	        /*
	        console.log("MM: " + MM + "\nYY: " + YY);
	
	        console.log("CONDICION 1: " + partesFecha[1] > YY);
	        console.log("CONDICION 2: " + (partesFecha[1] == YY && partesFecha[0] > MM));
	        */
	
	        return !(partesFecha[1] < YY || (partesFecha[1] == YY && partesFecha[0] < MM));
	    }
	
	    function disableInputs() {
	        inputs = document.querySelectorAll("input");
	        inputs.forEach(input => {
	            input.setAttribute("disabled", true);
	        });
	    }
	
	    form3.addEventListener("submit", function(e) {
	        e.preventDefault();
	        e.stopPropagation();
	        // let formulariosValidos = true;
	        let mensajeError = "";
	        if (form3.checkValidity()) {
	            if (fechaValida()) {
	                if (form2.checkValidity()) {
	                    if (form1.checkValidity()) {
	                        disableInputs();
	                        agregarOrdenCompra();
	                        mostrarAlerta("¡Compra realizada de manera exitosa! Serás redirigido al inicio.", "alert-success", '<i class="fa-solid fa-circle-check me-3"></i>');
	                        setTimeout(function () {
	                            window.location.href = "/backend_lab_pa/home";
	                        }, 5000);
	                    } else {
	                        mensajeError = "Se encontraron errores de validación en el Carrito de Compra."
	                        // formulariosValidos = false;
	                    }
	                } else {
	                    // formulariosValidos = false;
	                    if (mensajeError != ""){
	                        mensajeError = mensajeError.slice(0, -1);
	                        mensajeError += " y en los Detalles del envío.";
	                    } else {
	                        mensajeError = "Se encontraron errores de validación en los Detalles del envío."
	                    }
	                }
	            } else {
	                mostrarAlerta("La fecha ingresada es inválida.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
	                const fecha = document.querySelector("#fecVencimiento");
	                fecha.value = "";
	            }
	        } else {
	            if (document.querySelector("#alertaFormulario") == undefined){
	                mostrarAlerta("Aún hay campos incompletos o con valores inválidos.", "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
	            }
	        }
	
	        if (mensajeError != ""){
	            mostrarAlerta(mensajeError, "alert-warning", '<i class="fa-solid fa-triangle-exclamation me-3"></i>');
	        }
	
	        form3.classList.add('was-validated');
	    }, false);
	
	    volver = document.querySelector("#botonVolver");
	
	    volver.addEventListener("click", function(e) {
	        // Te redirige a inicio
	    });
	
	    atras1 = document.querySelector("#botonAtras1");
	
	    atras1.addEventListener("click", function(e) {
	        encabezado2.classList.add("d-none");
	        seccion2.classList.add("d-none");
	        encabezado1.classList.remove("d-none");
	        seccion1.classList.remove("d-none");
	    });
	
	    atras2 = document.querySelector("#botonAtras2");
	
	    atras2.addEventListener("click", function(e) {
	        encabezado3.classList.add("d-none");
	        seccion3.classList.add("d-none");
	        encabezado2.classList.remove("d-none");
	        seccion2.classList.remove("d-none");
	    });
	
	
	
	    async function getRegiones() {
	        const URL_REGIONES = "https://gist.githubusercontent.com/fedebabrauskas/b708c2a1b7a29af94927ad0e8d6d6a27/raw/b0c544d53c82de298ccedb824f8dd5e5ef5477e7/localidades.json";
	        try {
	            const response = await fetch(URL_REGIONES);
	            if (!response.ok){
	                throw new Error("Response status: " + response.status);
	            }
	            const json = await response.json();
	            // console.log(json);
	            return json;
	        } catch (error) {
	            // console.log(error.message);
	        }
	    }
	
	    async function cargarCiudades() {
	        const regiones = await getRegiones();
	        regiones.forEach(region => {
	            if (region.name == departamentos.value){   
	                if (region.towns.length != 0){
	                    ciudades.textContent = "";
	                    region.towns.forEach(town => {
	                        const option = document.createElement("option");
	                        const text = document.createTextNode(town.name);
	                        option.setAttribute("value", town.name);
	                        option.appendChild(text);
	                        ciudades.appendChild(option);
	                    });
	                    return;
	                }
	            }
	        });
	    }
	
        // cargarElementosCarrito(carritoActual);
	
	    departamentos.addEventListener("change", async function(e){
	        if (departamentos.value != ""){
	            ciudades.removeAttribute('disabled');
	            await cargarCiudades();
	        } else {
	            ciudades.setAttribute('disabled', true);
	            ciudades.textContent = "";
	            const option = document.createElement("option");
	            const text = document.createTextNode("Elige una ciudad");
	            option.setAttribute("value", "");
	            option.appendChild(text);
	            ciudades.appendChild(option);
	        }
	    });
	
	    // departamentos.value = "";
	    
	    document.addEventListener("DOMContentLoaded", async function(){
	    	carritoActual = await getCarrito("/backend_lab_pa/manejarcarrito", "getCarrito");
	    	// console.log(carritoActual);
	    	
	    	for (const item of carritoActual) {
				if (item.producto.imagenes && item.producto.imagenes[0]){
					item.producto.imagenes[0] = "/backend_lab_pa/getImagen?ruta=" + item.producto.imagenes[0];
				}
			}
	    	
	    	cargarElementosCarrito(carritoActual);
	    	
	    	departamentos.value = "";
	    });
    
    </script>

</body>
</html>