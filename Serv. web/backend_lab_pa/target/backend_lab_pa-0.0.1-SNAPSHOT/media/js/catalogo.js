// Asumiendo que las categorías están almacenadas en el localStorage  
const categor = JSON.parse(localStorage.getItem('categorias')) || [];
const prod = JSON.parse(localStorage.getItem('productos')) || [];

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
    } else {
        categoriasSeleccionadas = categoriasSeleccionadas.filter(cat => cat !== categoria);
        boton.classList.remove('categoria-seleccionada'); // Quitar la clase CSS
    }

    document.querySelector('.select-1').value = "99"; // Reiniciar la opción de ordenamiento a "99"
    filtrarProductosPorCategoria(); // Filtrar productos
}


function buscarEnSubcategorias(categoria, categoriasSeleccionadas) {
    if (categoriasSeleccionadas.includes(categoria.nombre)) {
        return true; // Si la categoría actual está seleccionada, devolver true
    }

    // Recorrer las subcategorías (hijos)
    if (categoria.hijos && categoria.hijos.length > 0) {
        return categoria.hijos.some(subcategoria => buscarEnSubcategorias(subcategoria, categoriasSeleccionadas));
    }

    return false; // Si no se encontró en ninguna subcategoría, devolver false
}

// Función para filtrar productos por categorías seleccionadas
function filtrarProductosPorCategoria() {
    // Si no hay categorías seleccionadas, carga todos los productos
    if (categoriasSeleccionadas.length === 0) {
        cargarCatalogo(productos);
        return;
    }

    const productosFiltrados = productos.filter(producto => {
        return producto.categorias.some(categoria => {
            // Función recursiva para buscar en todas las subcategorías
            return buscarEnSubcategorias(categoria, categoriasSeleccionadas);
        });
    });

    cargarCatalogo(productosFiltrados); // Cargar los productos filtrados
}

// Función para cargar las categorías desde localStorage
function cargarCategorias(categor) {
    const contenedorPadre = document.getElementById("aside");

    // Limpiar el contenedor de categorías antes de agregar nuevas
    contenedorPadre.innerHTML = '';

    categor.forEach(categoria => {
        const dropdownDiv = document.createElement('div');
        dropdownDiv.classList.add('dropdown');

        const button = document.createElement('button');
        button.classList.add('dropbtn');
        button.innerHTML = `&#9654; ${categoria.nombre}`;

        dropdownDiv.appendChild(button);

        const dropdownContent = document.createElement('div');
        dropdownContent.classList.add('dropdown-content');

        // Manejar subcategorías
        categoria.hijas.forEach(subcategoria => {
            const subcategoriaLink = document.createElement('a');
            subcategoriaLink.href = "#";
            subcategoriaLink.innerHTML = `&#9654; ${subcategoria.nombre}`;
            subcategoriaLink.addEventListener('click', function(event) {
                event.preventDefault(); // Evitar el comportamiento por defecto del enlace
                agregarCategoria(subcategoria.nombre, subcategoriaLink); // Seleccionar subcategoría
            });

            dropdownContent.appendChild(subcategoriaLink);

            // Manejar subcategorías de subcategorías
            subcategoria.hijas.forEach(subsubcategoria => {
                const subsubcategoriaLink = document.createElement('a');
                subsubcategoriaLink.href = "#";
                subsubcategoriaLink.innerHTML = `&emsp;&emsp;&#9654; ${subsubcategoria.nombre}`;
                subsubcategoriaLink.addEventListener('click', function(event) {
                    event.preventDefault(); // Evitar el comportamiento por defecto del enlace
                    agregarCategoria(subsubcategoria.nombre, subsubcategoriaLink); // Seleccionar subsubcategoría
                });

                dropdownContent.appendChild(subsubcategoriaLink);
            });
        });

        dropdownDiv.appendChild(dropdownContent);
        contenedorPadre.appendChild(dropdownDiv);
    });
}

// Llamar a cargarCategorias cuando sea necesario, por ejemplo al cargar la página
document.addEventListener("DOMContentLoaded", function() {
    cargarCategorias(categor);
    cargarCatalogo(prod); // Carga el catálogo inicialmente
    
    // Manejar el cambio de ordenamiento
    document.querySelector('.select-1').addEventListener('change', function() {
        const ordenSeleccionado = this.value;
        ordenarProductos(ordenSeleccionado); // Llama a la función de ordenar productos
    });
});

// Función para ordenar productos
function ordenarProductos(orden) {
    let productosFiltrados = productos; // Usar todos los productos inicialmente

    // Filtrar productos por categorías seleccionadas si hay alguna
    if (categoriasSeleccionadas.length > 0) {
        productosFiltrados = productos.filter(producto => {
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

function verInfoProducto(id) {
    for(let item of prod) {
        if(item.id == id){
            localStorage.setItem("productoSeleccionado", JSON.stringify(item));
            window.location.href = "infoProducto.html";
        }
    }
}

// Función para cargar el catálogo
function cargarCatalogo(prod) {
    const contenedorPadre = document.getElementById("prods");
    contenedorPadre.innerHTML = ''; // Limpiar el contenedor antes de agregar los productos

    if (prod.length === 0) {
        return;
    }

    prod.forEach(element => {
        const Rectangulo = document.createElement("article");
        const nuevaImagen = document.createElement("img");
        const Demas = document.createElement("div");

        const padreItemEstrellas = document.createElement("div");
        const Titulo = document.createElement("div");
        const ConjuntoEstrellas = document.createElement("div");

        const nuevaTienda = document.createElement("div");

        const padrePrecioCarrito = document.createElement("div");
        const Precio = document.createElement("div");
        const PadreCarrito = document.createElement("div");
        const Carrito = document.createElement("div");

        nuevaImagen.src = element.imagenes[0]; // Usar la primera imagen

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

        // Verificar y agregar contenido
        if (element.nombre) Titulo.innerHTML = element.nombre;
        if (element.precio) Precio.innerHTML = "$UYU " + element.precio;
        nuevaTienda.innerHTML = element.tienda || "Tienda no disponible"; 

        // Agregar clases o atributos si es necesario
        Rectangulo.classList.add("rectangle-1", "row");
        Rectangulo.onclick = function() {
            verInfoProducto(element.id);
        };

        nuevaImagen.classList.add("col-3", "image-1");
        Demas.classList.add("col-9", "row", "todo-lodemas");

        padreItemEstrellas.classList.add("row", "col-12", "item-estrellas");
        Titulo.classList.add("col", "item-1");
        ConjuntoEstrellas.classList.add("col", "conjunto_estrellas");
        nuevaTienda.classList.add("tienda-x", "col-12");

        padrePrecioCarrito.classList.add("row", "col-12", "precio-carrito");
        Precio.classList.add("col", "precio");

        PadreCarrito.classList.add("col-2", "row");
        Carrito.classList.add("col", "carrito", "fa-solid", "fa-cart-shopping");

        Carrito.addEventListener('click', function() {
            let array = [element];
            cargarElementosCarrito(array);
        });

        // Meter los contenedores en el contenedor principal
        padreItemEstrellas.appendChild(Titulo);
        padreItemEstrellas.appendChild(ConjuntoEstrellas);

        PadreCarrito.appendChild(Carrito);
        padrePrecioCarrito.appendChild(Precio);
        padrePrecioCarrito.appendChild(PadreCarrito);

        Demas.appendChild(padreItemEstrellas);
        Demas.appendChild(nuevaTienda);
        Demas.appendChild(padrePrecioCarrito);

        Rectangulo.appendChild(nuevaImagen);
        Rectangulo.appendChild(Demas);

        contenedorPadre.appendChild(Rectangulo);
    });
}
