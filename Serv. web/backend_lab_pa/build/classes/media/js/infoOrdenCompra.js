let usuarioActual = JSON.parse(localStorage.getItem('usuarioActual'));
let productos = JSON.parse(localStorage.getItem('productos'));


// Si el usuario actual y las órdenes existen
if (usuarioActual && usuarioActual.ordenes && usuarioActual.ordenes.length > 0) {
    const orden = usuarioActual.ordenes[0];

    const container = document.querySelector('main');
    const rectangle3 = document.querySelector('.rectangle-3');
    const precioSubtotal = document.querySelector('.subtotal2');
    const precioEnvio = document.querySelector('.envio2');
    const precioImpuesto = document.querySelector('.impuestos2');

    let subtotal = 0;

    // Limpiar los rectángulos anteriores
    const rectanglesPrevios = container.querySelectorAll('.rectangle-1');
    rectanglesPrevios.forEach(rect => rect.remove());

    const rectangle1 = document.createElement('div');
    rectangle1.className = 'rectangle-1 mb-4';

    container.insertBefore(rectangle1, rectangle3);

    function irADetalleProducto(idProducto) {
        //console.log("ID Producto:", idProducto);
        //console.log("Productos de la orden:", usuarioActual.ordenes[0].productos); 
        
        const productoSeleccionado = productos.find(producto => Number(producto.id) === Number(idProducto));
        
        if (productoSeleccionado) {
            localStorage.setItem('productoSeleccionado', JSON.stringify(productoSeleccionado));
            //console.log("Producto seleccionado guardado:", productoSeleccionado);
            window.location.href = 'infoProducto.html';
        } else {
            console.error("Producto no encontrado");
        }
    }
    
    /*
    // Iterar sobre los productos de la orden
    orden.productos.forEach(producto => {
        const rectangle2 = document.createElement('div');
        rectangle2.className = 'rectangle-2'; 
        const primeraImagen = producto.imagenes[0];
    
        rectangle2.innerHTML = `
            <div class="row"> 
                <div class="col-md-2 col-12 d-flex">
                    <div class="fotosProductos">
                        <img src="${primeraImagen}" alt="${producto.nombre}" class="imagenProducto" onerror="this.onerror=null; this.src='ruta_por_defecto.jpg';">
                    </div>   
                </div>
                <div class="col-md-4 col-12 d-flex flex-column justify-content-start p-0">
                    <h1 class="nombresProductos text-start mt-3" style="cursor: pointer;" onclick="irADetalleProducto(${producto.id})">${producto.nombre}</h1>    
                    <h2 class="descripcionProductos text-start m-0">${producto.descripcion}</h2>
                    <h2 class="precioProductos text-start mt-3">$${producto.precio}</h2>
                </div>
                <div class="col-md-4 col-12">
                    <h1 class="numProducto text-start mt-3">Nro. Producto: ${producto.id}</h1>
                    <div class="stepper-container d-flex align-items-end">
                        <input type="number" id="quantity" class="stepper" value="${producto.cantidad}" min="0" max="100" disabled>
                        <label for="quantity" class="labelCantidad">Cantidad</label> 
                    </div>          
                </div>
                <div class="col-md-2 col-12">
                    <h1 class="textoSubtotal mt-3">Subtotal</h1>
                    <h1 class="precioProductosSubt mt-3">$${(producto.precio * producto.cantidad).toFixed(2)}</h1>
                </div>
            </div>
        `;
    
        rectangle1.appendChild(rectangle2);
    
        subtotal += producto.precio * producto.cantidad;
    });
    
    // Actualizar los precios
    precioSubtotal.textContent = `$${subtotal.toFixed(2)}`;
    const envio2 = orden.detallesEnvio.precioEnvio; 
    precioEnvio.textContent = `$${envio2.toFixed(2)}`; 
    const impuestos = subtotal * 0.02; 
    precioImpuesto.textContent = `$${impuestos.toFixed(2)}`;
    const total = subtotal + envio2 + impuestos; 
    document.querySelector('.total2').textContent = `$${total.toFixed(2)}`;
} else {
    console.log("No se encontraron órdenes de compra.");
}

*/

	// Evento para volver a la página anterior
	document.getElementById("volver").addEventListener("click", function() {
	    window.location.href = "infoUsuario.html";
	});
}
