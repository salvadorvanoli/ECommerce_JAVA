  // Asignamos valores manualmente a los detalles de envío
  const detallesEnvio = {
    "nombre": "Salvador",
    "apellido": "Vanoli",
    "direccion1": "Calle Falsa 123",
    "direccion2": "Piso 1, A",
    "departamento": "Buenos Aires",
    "ciudad": "Buenos Aires",
    "codPostal": "1234",
    "numTelefono": "1234567890",
    "tipoEnvio": "express",
    "precioEnvio": 1500
};

// Asignamos valores manualmente a la forma de pago
const formaPago = {
    "tipoPago": "credito",
    "numTarjeta": "1234 5678 9101 1121",
    "fecVencimiento": "12/25",
    "cvv": "123",
    "nomTitular": "Salvador Vanoli"
};



// Verificamos que usuarioActual no sea null (en caso de que no exista)
if (!usuarioActual) {
    // Si no existe, lo inicializamos con valores predeterminados
    usuarioActual = {
        "nombre": "Salvador",
        "apellido": "Vanoli",
        "nickname": "salvaelpro777",
        "tipo": "proveedor",
        "email": "salva@salva.com",
        "fecha": "2004-05-01",
        "foto": "img/test.jpg",
        "web": "salva.com",
        "empresa": "salvaEnterprise",
        "id": "0",
        "contraseña": "jejejeje",
        "ordenes": [], // Inicializamos el arreglo de órdenes vacío
        "productos": [
            {
                "nombre": "Zucaritas",
                "estrellas": 3,
                "precio": 300,
                "descripcion": "Muy ricas, sisi muy muy ricas",
                "id": "777",
                "categorias": [
                    "Comida",
                    "Dulce",
                    "Cereales"
                ],
                "especificacion": [
                    "Cereal dulce de maíz",
                    "0 proteína 100% lípidos",
                    "Totalmente mortal para el cuerpo"
                ],
                "imagenes": [
                    "img/test.jpg",
                    "img/test.jpg"
                ]
            }
        ]
    };
}

// Creando una nueva orden
const nuevaOrden = {
    "id": usuarioActual.ordenes.length, // Asignamos un ID basado en la cantidad de órdenes existentes
    "fecha": new Date().toISOString(), // Fecha actual en formato ISO
    "productos": [
        {
            "nombre": "Zucaritas",
            "precio": 400,
            "descripcion": "Muy ricas, sisi muy muy ricas",
            "imagenes": [
                "img/test.jpg",
                "img/test.jpg"
            ],
            "id": "777",
            "cantidad": 6
        },
        {
            "nombre": "SSSSSSSSSSSESx",
            "precio": 200,
            "descripcion": "sii",
            "imagenes": [
                "img/test.jpg",
                "img/test.jpg"
            ],
            "id": "778",
            "cantidad": 8
        }
    ],
    "detallesEnvio": detallesEnvio, // Incluimos los detalles de envío
    "formaPago": formaPago // Incluimos la forma de pago
};

// Agregar la nueva orden al arreglo de órdenes del usuario actual
usuarioActual.ordenes.push(nuevaOrden);

// Guardamos el usuario actualizado nuevamente en localStorage
localStorage.setItem("usuarioActual", JSON.stringify(usuarioActual));

// Cargar y mostrar usuarioActual en consola
console.log("Usuario actual actualizado:", usuarioActual);
