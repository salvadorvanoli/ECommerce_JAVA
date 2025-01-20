let productoSeleccionado = JSON.parse(localStorage.getItem("productoSeleccionado")) || productos[0];


function obtenerCategorias(categoria) {
    let nombres = categoria.nombre;
    if (categoria.hijos && categoria.hijos.length > 0) {
        for (let i = 0; i < categoria.hijos.length; i++) {
            // Si no es el último hijo, añadir el símbolo '>' entre las categorías
            nombres += " > " + obtenerCategorias(categoria.hijos[i]);
        }
    }
    return nombres;
}

// Lógica para cargar el producto en la página
function cargarProducto(){
    // Nombre del producto
    document.getElementById("nombre-producto").innerHTML = productoSeleccionado.nombre;

    // Estrellas
    document.getElementById("estrellas-container").innerHTML += "";
    for(let i=0; i<productoSeleccionado.estrellas; i++){
        document.getElementById("estrellas-container").innerHTML += `<i class="fas fa-star" style="color: #7A7A7A;"></i>`;
    }
    for(let i=0; i<(5 - productoSeleccionado.estrellas); i++){
        document.getElementById("estrellas-container").innerHTML += `<i class="fas fa-star" style="color: #EBEBEB;"></i>`;
    }

    // Precio
    document.getElementById("precio-container").innerHTML = "$" + productoSeleccionado.precio;

    // Descripcion
    document.getElementById("descripcion-texto").innerHTML = productoSeleccionado.descripcion;

    // Número del producto
    document.getElementById("numero-producto").innerHTML = "Identificador del producto: " + productoSeleccionado.id;

    // Categorías
    // Inicializa la cadena de categorías
    let categoriasDelProducto = "Categorías: ";

    // Recorre todas las categorías principales del producto
    for (let categoria of productoSeleccionado.categorias) {
        categoriasDelProducto += obtenerCategorias(categoria) + ", ";
    }

    // Elimina la última coma y espacio
    if (categoriasDelProducto.endsWith(", ")) {
        categoriasDelProducto = categoriasDelProducto.slice(0, -2);
    }

    // Actualiza el contenedor en el DOM
    document.getElementById("categorias-container").innerText = categoriasDelProducto;

    // Especificaciones
    let htmlToInsert = `
        <ul>
    `;
    for(let especificacion of productoSeleccionado.especificacion) {
        htmlToInsert += `
            <li>${especificacion}</li>
        `;
    }
    htmlToInsert += `
        </ul>
    `;
    document.getElementById("especificaciones-container").innerHTML = htmlToInsert;

    // Imágenes
    let contador = 0;
    for(let imagen of productoSeleccionado.imagenes) {
        if(contador == 0){
            document.getElementById("indicadores-carrusel").innerHTML += `
            <li data-bs-target="#imgCarousel" data-slide-to="${contador}" class="active"></li>
            `;
        } else {
            document.getElementById("indicadores-carrusel").innerHTML += `
            <li data-bs-target="#imgCarousel" data-slide-to="${contador}" class=""></li>
            `;
        }
        

        if(contador == 0){
            document.getElementById("item-carrusel").innerHTML += `
            <div class="carousel-item active">
                <img class="d-block w-100" src="${imagen}" alt="Slide">
            </div>
            `;
        } else {
            document.getElementById("item-carrusel").innerHTML += `
            <div class="carousel-item">
                <img class="d-block w-100" src="${imagen}" alt="Slide">
            </div>
            `;
        }
        
        contador++;
    }

    if(productoSeleccionado.imagenes.length == 0) {
        document.getElementById("imgCarousel").style.display = "none";
        document.getElementById("informacion-producto-container").classList.remove("col-md-6");
        document.getElementById("informacion-producto-container").classList.add("col-md-12");
        document.getElementById("numero-producto").style.textAlign = "left";
    }

    // Comentarios
    let comentarios = cargarComentarios() || "";
    if(comentarios == "") {
        document.getElementById("comentarios-container").innerHTML = `
        <div style="text-align:center;color:red;font-weight:bolder;"> Este producto aún no tiene comentarios </div>
        `;
    } else {
        document.getElementById("comentarios-container").innerHTML = comentarios;
    }
}

function obtenerNuevoId() {
    let maxId = 0;

    // Recorre todos los comentarios y respuestas para encontrar el ID más alto
    productos.forEach(producto => {
        producto.comentarios.forEach(comentario => {
            maxId = Math.max(maxId, comentario.id);
            comentario.respuestas.forEach(respuesta => {
                maxId = Math.max(maxId, respuesta.id);
            });
        });
    });

    return maxId + 1;
}

function cargarRespuestas(respuestas, nivel = 1, contador) {
    let respuestasComentarios = "";

    for (let respuesta of respuestas) {
        respuestasComentarios += `
            <div class="carta-comentarios row" style="margin-left: ${nivel * 20}px;">
                <div class="informacion-usuario col-md-3 col-12">
                    <img src="${respuesta.foto}" alt="Perfil usuario" class="imagen-usuario">
                    <div>
                        <h4>${respuesta.usuario}</h4>
                        <p class="fecha">${respuesta.fecha}</p>
                        <button class="btn btn-success mt-2" onclick="mostrarCajaRespuesta(${contador}, ${respuesta.id})">Responder</button>
                    </div>
                </div>
                <div class="texto-comentario col-md-8 col-12">
                    ${respuesta.comentario}
                </div>
            </div>
        `;

        // Si hay respuestas anidadas, llama recursivamente a cargarRespuestas
        if (respuesta.respuestas && respuesta.respuestas.length > 0) {
            respuestasComentarios += cargarRespuestas(respuesta.respuestas, nivel + 1, contador);
        }
    }

    return respuestasComentarios;
}

function mostrarCajaRespuesta(contador, id) {
    document.getElementById("respuesta" + contador).classList.remove("d-none");
    document.getElementById("botonesComentario" + contador).innerHTML = `
        <button class="btn btn-success" onclick="aceptarComentario(${contador}, ${id})">Aceptar</button>
        <button class="btn btn-danger" onclick="cancelarComentario(${contador})">Cancelar</button>
    `;
}

function cancelarComentario(contador) {
    document.getElementById("respuesta" + contador).classList.add("d-none");
}

function aceptarComentario(contador, id) {

    let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null;

    if(usuarioActual == null || usuarioActual == ""){
        mostrarAlerta("No se puede escribir un comentario si no se tiene la sesión iniciada");
        return;
    }

    function buscarComentario(comentarios) {
        for (let comentario of comentarios) {
            if (comentario.id === id) {
                return comentario;
            }
            let respuestaEncontrada = buscarComentario(comentario.respuestas);
            if (respuestaEncontrada) {
                return respuestaEncontrada;
            }
        }
        return null;
    }
    
    for (let producto of productos) {
        if (producto.id == productoSeleccionado.id) {

            let comentario = buscarComentario(producto.comentarios);
            if (comentario) {
                // Obtenemos la fecha actual
                const fechaActual = new Date();
                const dia = String(fechaActual.getDate()).padStart(2, '0'); // Asegura que el día tenga dos dígitos
                const mes = String(fechaActual.getMonth() + 1).padStart(2, '0'); // +1 porque getMonth() empieza en 0
                const anio = fechaActual.getFullYear();
                const fechaFormateada = `${dia}/${mes}/${anio}`;
                const nuevoComentario = document.getElementById("comentarioInput" + contador).value.trim();

                if (nuevoComentario) {
                    // Agregamos la nueva respuesta al comentario
                    comentario.respuestas.push({
                        usuario: usuarioActual.nombre,
                        comentario: nuevoComentario,
                        foto: usuarioActual.img,
                        fecha: fechaFormateada,
                        id: obtenerNuevoId(), // Asegúrate de que esta función retorne un ID único
                        respuestas: []
                    });

                    // Guardamos los cambios en localStorage
                    localStorage.setItem("productos", JSON.stringify(productos));
                    localStorage.setItem("productoSeleccionado", JSON.stringify(producto));

                    // Recargamos la página para mostrar los nuevos comentarios
                    location.reload();
                } else {
                    alert("El comentario no puede estar vacío");
                }
            }
        }
    }
}

function cargarComentarios() {
    let comentarios = "";
    let contador = 0;

    for (let comentario of productoSeleccionado.comentarios) {
        let estrellas = "";

        for (let i = 0; i < comentario.estrellas; i++) {
            estrellas += `<i class="fas fa-star" style="color: #7A7A7A;"></i>`;
        }

        for (let i = 0; i < (5 - comentario.estrellas); i++) {
            estrellas += `<i class="fas fa-star" style="color: #EBEBEB;"></i>`;
        }

        comentarios += `
            <div class="comentario-respuesta-container container-md container-fluid">
                <div class="carta-comentarios row">
                    <div class="informacion-usuario col-md-3 col-12">
                        <img src="${comentario.foto}" alt="Perfil usuario" class="imagen-usuario">
                        <div>
                            <h4>${comentario.usuario}</h4>
                            <p class="fecha">${comentario.fecha}</p>
                            <div class="estrellas">
                                ${estrellas}
                            </div>
                            <button class="btn btn-success mt-2" onclick="mostrarCajaRespuesta(${contador}, ${comentario.id})">Responder</button>
                        </div>
                    </div>
                    <div class="texto-comentario col-md-8 col-12">
                        ${comentario.comentario}
                    </div>
                </div>

                <div class="respuestas-comentario">
                    ${cargarRespuestas(comentario.respuestas, 1, contador)}
                </div>

                <div class="caja-comentario card p-3 mt-3 mb-3 d-none" id="respuesta${contador}">
                    <div class="form-group">
                        <label for="comentarioInput${contador}">Escribe tu comentario:</label>
                        <input type="text" class="form-control" id="comentarioInput${contador}" placeholder="Escribe aquí...">
                    </div>
                    <div class="mt-3" id="botonesComentario${contador}">
                        <button class="btn btn-success" onclick="aceptarComentario(${contador}, ${comentario.id})">Aceptar</button>
                        <button class="btn btn-danger" onclick="cancelarComentario(${contador})">Cancelar</button>
                    </div>
                </div>
            </div>
        `;

        contador++;
    }

    return comentarios;
}

function agregarComentario(){
    let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null;

    if(usuarioActual == null || usuarioActual == ""){
        mostrarAlerta("No se puede escribir un comentario si no se tiene la sesión iniciada");
        return;
    }

    let texto = document.getElementById("comentarioInput").value;
    let cantEstrellas = document.getElementById("cantEstrellas").value;
    const fechaActual = new Date();
    const dia = String(fechaActual.getDate()).padStart(2, '0'); // Asegura que el día tenga dos dígitos
    const mes = String(fechaActual.getMonth() + 1).padStart(2, '0'); // +1 porque getMonth() empieza en 0
    const anio = fechaActual.getFullYear();
    const fechaFormateada = `${dia}/${mes}/${anio}`;

    if(texto == "" || texto == null || texto == undefined) {
        mostrarAlerta("No se puede agregar un comentario vacío");
        return;
    }

    if(cantEstrellas < 0 || cantEstrellas > 5) {
        mostrarAlerta("La cantidad de estrellas no es válida");
        return;
    }

    productoSeleccionado.comentarios.push({
    usuario: usuarioActual.nombre,
    comentario: texto,
    foto: usuarioActual.img,
    fecha: fechaFormateada,
    estrellas: cantEstrellas,
    id: obtenerNuevoId(),
    respuestas: []});

    localStorage.setItem("productoSeleccionado", JSON.stringify(productoSeleccionado));
    localStorage.setItem("productos", JSON.stringify());
    location.reload();
}

cargarProducto();

// Lógica para agregar un producto al carrito

function itemYaExiste(item) {
    let carritoActual = JSON.parse(localStorage.getItem("carritoActual")) || "";

    for(let producto of carritoActual) {
        if(item.id == producto.id) {
            return true;
        }
    }
    return false;
}

function checkCantidad() {
    let cantidad = parseInt(document.getElementById("cantidad-input").value) || 1;
    if(cantidad < 1) {
        cantidad = 1;
    }
    return cantidad;
}

document.getElementById("agregar-al-carrito").addEventListener("click", () => {
    let carritoActual = JSON.parse(localStorage.getItem("carritoActual")) || [];
    
    if(itemYaExiste(productoSeleccionado)) {
        mostrarAlerta("El item elegido ya existe en el carrito, no se volvió a agregar");
        document.getElementById("modalTitle").innerHTML = "El item elegido ya existe en el carrito, no se volvió a agregar";
    } else {
        document.getElementById("modalTitle").innerHTML = "¡Producto agregado con éxito!";
        carritoActual.push({
            "nombre": productoSeleccionado.nombre || "",
            "precio": productoSeleccionado.precio || 0,
            "descripcion": productoSeleccionado.descripcion || "",
            "imagenes": productoSeleccionado.imagenes || [],
            "id": productoSeleccionado.id || 0,
            "cantidad": checkCantidad() || 1
        });
    }

    localStorage.setItem("carritoActual", JSON.stringify(carritoActual));
});

function revisarProductoComprado(){
    let bool = false;
    let usuarioActual = JSON.parse(localStorage.getItem("usuarioActual")) || null;
    if(usuarioActual != null){
        for(let orden of usuarioActual.ordenes){
            for(let item of orden.productos){
                if(item.id == productoSeleccionado.id) {
                    bool = true;
                }
            }
        }
    }
    
    if(!bool){
        document.getElementById("respuesta").style.display = "none";
        const botonesResponder = document.querySelectorAll('button.btn.btn-primary.mt-2');

        botonesResponder.forEach(boton => {
            boton.disabled = true;
            boton.style.display = "none";
        });
    }
}

revisarProductoComprado();