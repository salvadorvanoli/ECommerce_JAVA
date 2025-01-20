<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.util.Comparator"%>
<%@ page import="java.util.Arrays"%>
<%--
<%@ page import="com.flamingo.models.Producto" %>
<%@ page import="com.flamingo.models.Categoria" %>
--%>
<%--
<%@ page import="clases.Producto" %>
<%@ page import="clases.Categoria" %>
--%>
<%@ page import="services.DtProductoDetallado" %>
<%@ page import="services.DtCategoria" %>


<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Catálogo</title>
    <link href='https://fonts.googleapis.com/css?family=Source Sans 3' rel='stylesheet'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="media/css/catalogo.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<jsp:include page="/WEB-INF/template/header.jsp"/>

    <%
    	String textoBusqueda = (String) request.getAttribute("textoBusqueda");
	%>

	<input type="hidden" id="textoBusqueda" value="<%= textoBusqueda != null ? textoBusqueda : "" %>">

	<!-- Mostrar el select de orden -->
	<div class="row container-fluid w-100 justify-content-end align-items-center">
        <header class="header-1 col align-self-end">
            <div class="text-center">
                <select class="select-1">
                    <option selected value="99">Seleccione Un Filtro (Sin orden)</option>
                    <option value="1">Ordenar por Precio Ascendente (↓)</option>
                    <option value="2">Ordenar por Precio Descendente (↑)</option>
                    <option value="3">Ordenar Alfabéticamente Ascendente (↓)</option>
                    <option value="4">Ordenar Alfabéticamente Descendente (↑)</option>
                    <option value="5">Ordenar por cantidad de compras Ascendente (↓)</option>
                    <option value="6">Ordenar por cantidad de Descendente (↑)</option>
                </select>
            </div>
        </header>
    </div>

	<div class="container-fluid w-100">
	
		<div class="row col-md-3 col-11 mx-md-3 mx-sm-5 mx-3" id="selectedCategories">
			Selecciona categorías aquí
		</div>
	
        <div class="row d-flex justify-content-center">
            <!-- Aside -->
            <aside id="aside" class="aside-1 scrolleable col-md-3 col-11 row ms-sm-4 ms-0">
                
            </aside>
            
            
            
            <!-- Productos -->
            <main id="prods" class="col-12 col-md ms-sm-3 ms-0">
                <article id="producto1" class="rectangle-1 row">
                
                </article>
            </main>
        </div>    
    </div>


	
<jsp:include page="/WEB-INF/template/footer.jsp" />


<script src="https://kit.fontawesome.com/d795c6c237.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">

	let categor;
	let prod;

	async function getDatos(URL, tipo) {
    	try {
    	    const response = await fetch(URL, {
    	        method: 'GET',
    	        headers: {
    	            'tipo': tipo
    	        }
    	    });
    	    let data = await response.json();
    	    
    	    const textoBusqueda = document.getElementById('textoBusqueda').value;
    	    
    	    if(tipo == "getProductos"){
    	    	if (textoBusqueda) {
    			    data = data.filter(producto => {
    			        let nombreProducto = producto.nombre.toLowerCase();
    			        
    			        let nombreProveedor = producto.proveedor.nickname.toLowerCase();
    			        
    			        let categoriasProducto = producto.categorias.map(categoria => categoria.toLowerCase());

    			        // Verifica si el texto de búsqueda coincide con el nombre del producto, proveedor o alguna categoría
    			        return nombreProducto.includes(textoBusqueda.toLowerCase()) ||
    			               nombreProveedor.includes(textoBusqueda.toLowerCase()) ||
    			               categoriasProducto.some(categoriaNombre => categoriaNombre.toLowerCase().includes(textoBusqueda.toLowerCase()));
    			    });
    			}
    	    }
    	    
    	    return data;
    	} catch (error) {
    	    // console.error('Hubo un problema con la solicitud: ', error);
    	}
  	}
	
	async function getImagen(URL) {
    	try {
    	    const response = await fetch(URL);
    	    let data = await response.json();
    	    return data;
    	} catch (error) {
    	    // console.error('Hubo un problema con la solicitud: ', error);
    	}
  	}
	
	// Manejar el cambio de estado de las categorías
	const categoriaLinks = document.querySelectorAll('.dropdown-item');
	
	if (categoriaLinks.length > 0) {
	    categoriaLinks.forEach(link => {
	        link.addEventListener('click', function(event) {
	            event.preventDefault(); // Evitar el comportamiento por defecto del enlace
	            const categoriaSeleccionada = this.innerHTML; // Obtener el nombre de la categoría
	
	            // Cambiar el estado de selección
	            agregarCategoria(categoriaSeleccionada, this);
	        });
	    });
	}
	
	// Mantener el dropdown abierto al hacer clic en el toggle
	document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
	    toggle.addEventListener('click', function(event) {
	        event.preventDefault(); // Evitar que se cierre al hacer clic
	    });
	});
	
	let categoriasSeleccionadas = [];
	
	// Función para manejar la selección de una categoría
	function agregarCategoria(categoria, boton) {
	    if (!categoriasSeleccionadas.includes(categoria)) {
	        categoriasSeleccionadas.push(categoria);
	        boton.classList.add('categoria-seleccionada'); // Añadir la clase CSS
	        boton.classList.remove('dropbtn-color');
	    } else {
	        categoriasSeleccionadas = categoriasSeleccionadas.filter(cat => cat !== categoria);
	        boton.classList.add('dropbtn-color');
	        boton.classList.remove('categoria-seleccionada'); // Quitar la clase CSS
	    }
	
	    document.querySelector('.select-1').value = "99"; // Reiniciar la opción de ordenamiento a "99"
	    filtrarProductosPorCategoria(); // Filtrar productos
	}
	
	function buscarEnSubcategorias(categoria, categoriasSeleccionadas) {
		for (let i = 0; i < categoriasSeleccionadas.length; i++){
			if (categoriasSeleccionadas[i].nombreCat.localeCompare(categoria) == 0){
				return true;
			}
		}
		
	 	// Recorrer las subcategorías (hijos)
	 	
		for (let i = 0; i < categoriasSeleccionadas.length; i++){
			if (categoriasSeleccionadas[i].hijas && categoriasSeleccionadas[i].hijas.length > 0){
				return buscarEnSubcategorias(categoria, categoriasSeleccionadas[i].hijas);
	    	}
		}
	
	    return false; // Si no se encontró en ninguna subcategoría, devolver false
	}
	
	// Función para filtrar productos por categorías seleccionadas
	function filtrarProductosPorCategoria() {
	    // Si no hay categorías seleccionadas, carga todos los productos
	    if (categoriasSeleccionadas.length === 0) {
	        document.querySelector("#selectedCategories").textContent = "Selecciona categorías aquí";
	        cargarCatalogo(prod);
	        return;
	    }
	    
	    let textoCategorias = "Categorías seleccionadas: ";
	    
	    for (let i = 0; i < categoriasSeleccionadas.length; i++) {
	        textoCategorias += categoriasSeleccionadas[i].nombreCat;
	        if ((i + 1) !== categoriasSeleccionadas.length) {
	            textoCategorias += " - ";
	        }
	    }
	    
	    document.querySelector("#selectedCategories").textContent = textoCategorias;
	
	    // Filtrar productos por categorías seleccionadas
	    let productosFiltrados = prod.filter(producto => {
	        return producto['categorias'].some(categoria => {
	            // Función recursiva para buscar en todas las subcategorías
	            return buscarEnSubcategorias(categoria, categoriasSeleccionadas);
	        });
	    });

	    cargarCatalogo(productosFiltrados); // Cargar los productos filtrados
	}
	
	/*

	function generarCategoria(categoria, padre) {
		const catElement = document.createElement("div");
		catElement.classList.add("dropdown");
		catElement.innerHTML +=
			`<button class="dropbtn dropbtn-color" onclick="agregarCategoria(` + `'` + categoria.nombreCat + `', this)">&#9654 ` + categoria.nombreCat + `</button>
			<div class="dropdown-content">
			</div>`;
		for (let cat in categoria.hijas){
			generarCategoria(categoria.hijas[cat], catElement);
		}
		if (padre != null){
			padre.getElementsByClassName("dropdown-content")[0].appendChild(catElement);
		} else {
			return catElement;
		}
	}
	
	*/
	
	// Función para cargar las categorías desde localStorage
	function cargarCategorias(categor, padre) {
		
		const contenedorPadre = document.getElementById("aside");
		if (padre == null){
	    	contenedorPadre.innerHTML = '';
		}
	
	    categor.forEach(categoria => {
	    	const catElement = document.createElement("div");
	    	catElement.classList.add("dropdown");

	    	// Crear el botón como un elemento
	    	const button = document.createElement("button");
	    	button.classList.add("dropbtn", "dropbtn-color");
	    	button.innerHTML = `&#9654; ` + categoria.nombreCat;
	    	catElement.appendChild(button);

	    	// Crear el dropdown como un elemento
	    	const dropdown = document.createElement("div");
	    	dropdown.classList.add("dropdown-content");
	    	catElement.appendChild(dropdown);
			if (padre != null){
				// Verificar que el padre tenga el dropdown-content
	            const dropdownContent = padre.querySelector(".dropdown-content");
	            if (dropdownContent) {
	                dropdownContent.appendChild(catElement);
	            }
			} else {
	        	contenedorPadre.appendChild(catElement);
			}
	        button.addEventListener("click", function(){
	        	agregarCategoria(categoria, this);
	        });
	        if (categoria.hijas && categoria.hijas.length > 0){
	        	cargarCategorias(categoria.hijas, catElement);
	        }
	    });
	}
	
	// Función para ordenar productos
	function ordenarProductos(orden) {
	    let productosFiltrados = prod; // Usar todos los productos inicialmente
	
	    // Filtrar productos por categorías seleccionadas si hay alguna
	    if (categoriasSeleccionadas.length > 0) {
	        productosFiltrados = prod.filter(producto => {
	            // Verificar si el producto pertenece a alguna de las categorías seleccionadas
	            return producto.categorias.some(categoria => {
	                // Función recursiva para buscar en todas las subcategorías
	                return buscarEnSubcategorias(categoria, categoriasSeleccionadas);
	            });
	        });
	    }
	
	    // Ordenar los productos según la opción seleccionada
	    if (orden === "1") {
	        productosFiltrados.sort((a, b) => a.precio - b.precio); // Ordenar por precio ascendente
	    } else if (orden === "2") {
	        productosFiltrados.sort((a, b) => b.precio - a.precio); // Ordenar por precio descendente
	    } else if (orden === "3") {
	        productosFiltrados.sort((a, b) => a.nombre.localeCompare(b.nombre)); // Ordenar por nombre ascendente
	    } else if (orden === "4") {
	        productosFiltrados.sort((a, b) => b.nombre.localeCompare(a.nombre)); // Ordenar por nombre descendente
	    } else if (orden === "5") {
	        productosFiltrados.sort((a, b) => a.cantCompras - b.cantCompras); // Ordenar por cantidad de compras ascendente
	    } else if (orden === "6") {
	        productosFiltrados.sort((a, b) => b.cantCompras - a.cantCompras); // Ordenar por cantidad de compras descendente
	    }
	
	    cargarCatalogo(productosFiltrados); // Cargar los productos ordenados
	}
	
	function cargarProducto(id) {
	    window.location.href = "/backend_lab_pa/infoProducto?productoSeleccionado=" + id;
	}
	
	// Función para cargar el catálogo
	function cargarCatalogo(prod) {
	    const contenedorPadre = document.getElementById("prods");
	    contenedorPadre.innerHTML = ''; // Limpiar el contenedor antes de agregar los productos
	
	    if (prod.length === 0) {
	    	contenedorPadre.innerHTML =
	    		`<div class="text-center fs-1 m-5">
					No hay productos en el catálogo.
				</div>`
	    }
	
	    prod.forEach(element => {
	
	
	        const ConjuntoEstrellas = document.createElement("div");
	
	        let estrellasMarcadas = element.estrellas;
	
	        for (let i = 1; i <= 5; i++) {
	            let star = document.createElement("span");
	            if (estrellasMarcadas > 0) {
	                star.classList.add("fa", "fa-star", "checked");
	                estrellasMarcadas--;
	            } else {
	                star.classList.add("fa", "fa-star");
	            }
	            ConjuntoEstrellas.appendChild(star);
	        }
	
	        ConjuntoEstrellas.classList.add("col-lg-4", "col-12", "text-end", "conjunto_estrellas", "d-flex", "justify-content-between", "m-lg-3", "ms-0", "my-2");
	
	        const contenedorEstrellas = document.createElement("div");
	
	        contenedorEstrellas.appendChild(ConjuntoEstrellas);
	
	        let producto;
	        
	        if(element.imagenes) {
	        	producto = document.createElement("div");
		        producto.innerHTML =
		            `<div class="col-12 row my-3 d-flex align-items-center justify-content-center pe-sm-5 pe-2 rectangle-1 product" id="producto` + element.numReferencia + `" onclick="cargarProducto(` + element.numReferencia + `)">
		                <div class="col-lg-4 col-sm-6 col-12 d-flex align-items-center justify-content-center">
		                    <img class="w-xs-75 w-50 image-1" src="` + element.imagenes[0] + `" alt="` + element.nombre + `">
		                </div>
		                <div class="col-lg-8 col-sm-6 col-10">
		                    <div class="row">
		                        <p class="col-md col-12 titulo-producto p-0 text-break text-sm-start text-center mb-0">` + element.nombre + `</p>` +
		                        contenedorEstrellas.innerHTML +
		                        `<p class="col-12 px-0 m-0 tienda-x text-break text-sm-start text-center my-2 my-sm-3 mt-lg-1 mb-lg-3">` + element.nombreTienda + `</p>
		                    </div>
		                    <div class="row precio-producto d-flex align-items-end">
		                        <p class="col-sm-6 col-12 p-0 precio text-break text-sm-start text-center mb-1">$` + element.precio + `</p>
		                    </div>
		                </div>
		            </div>
		            `
	        } else {
	        	producto = document.createElement("div");
		        producto.innerHTML =
		            `<div class="col-12 row my-3 d-flex align-items-center justify-content-center pe-sm-5 pe-2 rectangle-1 product" id="producto` + element.numReferencia + `" onclick="cargarProducto(` + element.numReferencia + `)">
		                <div class="col-lg-4 col-sm-6 col-12 d-flex align-items-center justify-content-center">
		                    <img class="w-xs-75 w-50 image-1" src="media/images/default.webp" alt="` + element.nombre + `">
		                </div>
		                <div class="col-lg-8 col-sm-6 col-10">
		                    <div class="row">
		                        <p class="col-md col-12 titulo-producto p-0 text-break text-sm-start text-center mb-0">` + element.nombre + `</p>` +
		                        contenedorEstrellas.innerHTML +
		                        `<p class="col-12 px-0 m-0 tienda-x text-break text-sm-start text-center my-2 my-sm-3 mt-lg-1 mb-lg-3">` + element.nombreTienda + `</p>
		                    </div>
		                    <div class="row precio-producto d-flex align-items-end">
		                        <p class="col-sm-6 col-12 p-0 precio text-break text-sm-start text-center mb-1">$` + element.precio + `</p>
		                    </div>
		                </div>
		            </div>
		            `
	        }
	
	        contenedorPadre.appendChild(producto);
	
	    });
	}


	document.addEventListener("DOMContentLoaded", async function(){
	    categor = await getDatos("/backend_lab_pa/manejarcatalogo", "getCategorias");
	    // console.log(categor);
		// console.log(categor[0]);

		prod = await getDatos("/backend_lab_pa/manejarcatalogo", "getProductos");
		// console.log(prod);
		
		for (const element of prod) {
			if (element.imagenes && element.imagenes[0]){
				element.imagenes[0] = "/backend_lab_pa/getImagen?ruta=" + element.imagenes[0];
			}
		}

		cargarCategorias(categor, null);
	    cargarCatalogo(prod); // Carga el catálogo inicialmente
	    
	    // Manejar el cambio de ordenamiento
	    document.querySelector('.select-1').addEventListener('change', function() {
	        const ordenSeleccionado = this.value;
	        ordenarProductos(ordenSeleccionado); // Llama a la función de ordenar productos
	    });
		
		if(prod) {
			document.getElementById("searchbar").value = document.getElementById("textoBusqueda").value;
		}
		
	});

</script>

</body>
</html>