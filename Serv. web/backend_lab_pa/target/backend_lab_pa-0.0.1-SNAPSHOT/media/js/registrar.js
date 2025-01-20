document.addEventListener('DOMContentLoaded', function() { 
    const buttonRegistrar = document.querySelector('.button-registrar');
    const emailInput = document.getElementById('floatingInput');
    const nicknameInput = document.getElementById('floatingInputNickname');
    const emailWarning = document.getElementById('emailWarning');
    const nicknameWarning = document.getElementById('nicknameWarning');
    const iniciarSesionButton = document.getElementById('IniciarSesion');

    // Función para validar el formato de correo electrónico
    function esCorreoValido(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }

    // Obtener usuarios registrados desde el sistema
    let usuariosRegistrados = [];
    fetch(`${contextPath}/usuarios`)
        .then(response => response.json())
        .then(data => {
            usuariosRegistrados = data; // Supongo que la respuesta es un arreglo de usuarios
        })
        .catch(error => console.error('Error al obtener usuarios:', error));

    // Verificar email
    emailInput.addEventListener('input', function() {
        const email = emailInput.value.trim();
        const emailExiste = usuariosRegistrados.some(user => user.email === email);

        if (!esCorreoValido(email)) {
            emailWarning.textContent = 'Por favor, introduce un correo electrónico válido.';
            emailInput.classList.add('is-invalid');
        } else if (emailExiste) {
            emailWarning.textContent = 'Este correo ya está registrado.';
            emailInput.classList.add('is-invalid');
        } else {
            emailWarning.textContent = '';
            emailInput.classList.remove('is-invalid');
        }
    });

    // Verificar nickname
    nicknameInput.addEventListener('input', function() {
        const nickname = nicknameInput.value.trim();
        const nicknameExiste = usuariosRegistrados.some(user => user.nickname === nickname);

        if (nicknameExiste) {
            nicknameWarning.textContent = 'Este nickname ya está registrado.';
            nicknameInput.classList.add('is-invalid');
        } else {
            nicknameWarning.textContent = '';
            nicknameInput.classList.remove('is-invalid');
        }
    });

    // Redirigir a la página de inicio de sesión
    iniciarSesionButton.onclick = function() {
        window.location.href = `${contextPath}/iniciarsesion`; 
    };

    // Verificación y redirección para el registro
    buttonRegistrar.onclick = function() {
        const email = emailInput.value.trim();
        const nickname = nicknameInput.value.trim();

        if (!email || !nickname) {
            alert('Por favor, completa todos los campos.');
            return;
        }

        if (!esCorreoValido(email)) {
            alert('Por favor, introduce un correo electrónico válido.');
            emailInput.classList.add('is-invalid');
            return;
        }

        const usuarioExistente = usuariosRegistrados.find(user => user.email === email || user.nickname === nickname);

        if (usuarioExistente) {
            alert('Ya existe un usuario registrado con ese email o nickname.');
        } else {
            // Redirigir con los parámetros email y nickname para el controlador
            window.location.href = `${contextPath}/ingresardatos?email=${encodeURIComponent(email)}&nickname=${encodeURIComponent(nickname)}`;
        }
    };
});
