document.addEventListener('DOMContentLoaded', function() {
    const buttonRegistrar = document.getElementById('button-registrar');
    const buttonCancelar = document.getElementById('button-cancelar');
    const sitioWebInput = document.getElementById('floatingInputSitioWeb');
    const companiaInput = document.getElementById('floatingInputCompania');
    const nombreInput = document.getElementById('floatingInputNombre');
    const apellidoInput = document.getElementById('floatingInputApellido');
    const passwordInput = document.getElementById('floatingPassword');
    const repeatPasswordInput = document.getElementById('floatingRepeatPassword');
    const fechaInput = document.getElementById('floatingDate');

    
    
    const errores = {};

    function validarCampoTexto(input, minLength, fieldName) {
        if (input.value.trim().length < minLength) {
            errores[fieldName] = fieldName + `debe tener al menos` + minLength + `caracteres.`;
            input.classList.add('is-invalid');
        } else {
            delete errores[fieldName];
            input.classList.remove('is-invalid');
        }
        mostrarErrores();
    }

    function validarContraseñas() {
        const contraseña = passwordInput.value;
        const repetirContraseña = repeatPasswordInput.value;

        if (contraseña.length < 8) {
            errores['contraseña'] = 'La contraseña debe tener al menos 8 caracteres.';
            passwordInput.classList.add('is-invalid');
        } else {
            delete errores['contraseña'];
            passwordInput.classList.remove('is-invalid');
        }

        if (contraseña !== repetirContraseña) {
            errores['repetirContraseña'] = 'Las contraseñas no coinciden.';
            repeatPasswordInput.classList.add('is-invalid');
        } else {
            delete errores['repetirContraseña'];
            repeatPasswordInput.classList.remove('is-invalid');
        }

        mostrarErrores();
    }

    function validarFecha() {
        const fechaActual = new Date().toISOString().split('T')[0];
        
        if (fechaInput.value > fechaActual) {
            errores['fecha'] = 'La fecha no puede ser posterior a la actual.';
            fechaInput.classList.add('is-invalid');
        } else if (fechaInput.value == "" || fechaInput.value == null) {
            errores['fecha'] = 'Se debe ingresar una fecha';
            fechaInput.classList.add('is-invalid');
        } else {
            delete errores['fecha'];
            fechaInput.classList.remove('is-invalid');
        }
        mostrarErrores();
    }

    function validarSitioWeb() {
        const sitioWeb = sitioWebInput.value.trim();
        if (!sitioWeb || !validarUrl(sitioWeb) || document.querySelector('input[value="Cliente"]').checked) {
            errores['sitioWeb'] = 'La URL del sitio web no es válida. Debe tener dos 2 puntos o más.';
            sitioWebInput.classList.add('is-invalid');
        } else {
            delete errores['sitioWeb'];
            sitioWebInput.classList.remove('is-invalid');
        }
        mostrarErrores();
    }

    function validarNombreSinNumeros(nombre) {
        const regex = /\d/; // Verifica si hay dígitos en la cadena
        return !regex.test(nombre); // Devuelve true si no hay dígitos
    }

    function validarNombre() {
        const nombre = nombreInput.value.trim();
        if (!validarNombreSinNumeros(nombre)) {
            errores['nombre'] = 'El nombre no puede contener números.';
            nombreInput.classList.add('is-invalid');
        } else {
            delete errores['nombre'];
            nombreInput.classList.remove('is-invalid');
        }
        mostrarErrores();
    }

    function mostrarErrores() {
        const errorDiv = document.getElementById('error-mensajes');
        errorDiv.innerHTML = ''; // Limpiar mensajes anteriores
        for (let key in errores) {
            const errorMensaje = document.createElement('p');
            errorMensaje.classList.add('text-danger'); // Clase de Bootstrap para texto rojo
            errorMensaje.textContent = errores[key];
            errorDiv.appendChild(errorMensaje);
        }
    }

    // Validaciones en tiempo real
    nombreInput.addEventListener('input', function() {
        validarCampoTexto(nombreInput, 3, 'Nombre');
        validarNombre(); // Llamada a la función de validación de números
    });

    apellidoInput.addEventListener('input', function() {
        validarCampoTexto(apellidoInput, 3, 'Apellido');
    });

    passwordInput.addEventListener('input', validarContraseñas);
    repeatPasswordInput.addEventListener('input', validarContraseñas);

    fechaInput.addEventListener('change', validarFecha);
    sitioWebInput.addEventListener('input', validarSitioWeb);
    companiaInput.addEventListener('input', function() {
        validarCampoTexto(companiaInput, 1, 'Compañía');
    });

    // Habilitar/deshabilitar los campos según el tipo de usuario
    function toggleInputs() {
        const tipoUsuarioElement = document.querySelector('input[name="tipoUsuario"]:checked');
        const tipoUsuario = tipoUsuarioElement ? tipoUsuarioElement.value : undefined;

        if (tipoUsuario === 'Cliente') {
            sitioWebInput.disabled = true; 
            companiaInput.disabled = true;
            sitioWebInput.required = false;
            companiaInput.required = false;
            delete errores['sitioWeb'];
            delete errores['Compañía'];
        } else if (tipoUsuario === 'Proveedor') {
            sitioWebInput.disabled = false;
            companiaInput.disabled = false;
            sitioWebInput.required = true;
            companiaInput.required = true;
            validarSitioWeb();
            validarCampoTexto(companiaInput, 1, 'Compañía');
        }
        mostrarErrores();
    }

    const radios = document.querySelectorAll('input[name="tipoUsuario"]');
    radios.forEach(radio => {
        radio.addEventListener('change', toggleInputs);
    });

    toggleInputs();
    
    // Al hacer clic en registrar
    if (buttonRegistrar) {
        buttonRegistrar.onclick = function() {
            validarCampoTexto(nombreInput, 3, 'Nombre');
            validarCampoTexto(apellidoInput, 3, 'Apellido');
            validarContraseñas();
            validarFecha();
            if(!document.querySelector('input[value="Cliente"]').checked) {
                validarSitioWeb();
                validarCampoTexto(companiaInput, 1, 'Compañía');
            }
            
            if (Object.keys(errores).length === 0) {
                const credencialesTemp = JSON.parse(localStorage.getItem('credencialesTemp'));
                const email = credencialesTemp.email;
                const nickname = credencialesTemp.nickname;

                let imgInput = document.getElementById("buttonElegirImagen");

                const usuario = {
                    nombre: nombreInput.value.trim(),
                    apellido: apellidoInput.value.trim(),
                    nickname: nickname,
                    email: email,
                    contraseña: passwordInput.value,
                    fecha: fechaInput.value,
                    sitioWeb: sitioWebInput.value.trim() || null,
                    compania: companiaInput.value.trim() || null,
                    tipo: document.querySelector('input[name="tipoUsuario"]:checked').value,
                    img: "img/oreo.webp",
                    ordenes: [],
                    productos: [],
                    id: 0 // A futuro debe traer el id de la base de datos (cantidad de usuarios + 1)
                };

                let usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];
                usuariosRegistrados.push(usuario);
                localStorage.setItem('usuarios', JSON.stringify(usuariosRegistrados));

                alert('Registro exitoso.');
                window.location.href = 'iniciarSesion';
            } else {
                alert('Por favor, corrija los errores antes de continuar.');
            }
        };
    }
    
    // Cancelar
    if (buttonCancelar) {
        buttonCancelar.onclick = function() {
            // Reiniciar los campos
            nombreInput.value = '';
            apellidoInput.value = '';
            passwordInput.value = '';
            repeatPasswordInput.value = '';
            fechaInput.value = '';
            sitioWebInput.value = '';
            companiaInput.value = '';
            mostrarErrores();
        };
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const buttonElegirImagen = document.getElementById('buttonElegirImagen');
    const inputImagen = document.getElementById('inputImagen');
    const iconoUsuario = document.getElementById('icono-usuario2'); 
    
    buttonElegirImagen.onclick = function() {
        inputImagen.click(); 
    };

    // 
    inputImagen.onchange = function(event) {
        const archivo = event.target.files[0];
        if (archivo) {
            const reader = new FileReader();
            reader.onload = function(e) {
                
                const img = document.createElement('img');
                img.src = e.target.result; 
                img.style.width = '100%'; 
                img.style.height = '100%'; 
                img.style.borderRadius = '50%'; 
                img.style.objectFit = 'cover'; 
                img.style.display = 'block'; 
                img.style.margin = 'auto'; 
                img.style.maxWidth = '150px'; 
                img.style.maxHeight = '150px'; 

                
                iconoUsuario.replaceWith(img);
            };
            reader.readAsDataURL(archivo);
        }
    };
});

function validarUrl(url) {
    const regex = /^(https?:\/\/)?[a-zA-Z0-9.-]+(\.[a-zA-Z0-9.-]+){2}([\/\w .-]*)*\/?$/;
    const puntos = (url.match(/\./g) || []).length; 
    return regex.test(url) && puntos >= 2; 
}
