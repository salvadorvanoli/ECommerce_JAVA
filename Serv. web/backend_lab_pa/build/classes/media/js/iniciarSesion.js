document.addEventListener('DOMContentLoaded', function() {
    const buttonIniciarSesion = document.querySelector('.button-iniciarSesion');
    const errorMensajeDiv = document.getElementById('error-mensaje'); 

    buttonIniciarSesion.onclick = function() {
        const emailOrNickname = document.getElementById('floatingInput').value.trim();
        const password = document.getElementById('floatingRepeatPassword').value.trim();
        const usuariosRegistrados = JSON.parse(localStorage.getItem('usuarios')) || [];
        
        errorMensajeDiv.style.display = 'none'; 
        errorMensajeDiv.textContent = '';

        
        const usuarioActual = usuariosRegistrados.find(usuario => 
            (usuario.email === emailOrNickname || usuario.nickname === emailOrNickname) && usuario.contraseña === password
        );

        if (usuarioActual) {
            alert('Inicio de sesión exitoso!');
            localStorage.setItem("usuarioActual", JSON.stringify(usuarioActual));
            window.location.href = 'index.html';
        } else {
            errorMensajeDiv.textContent = 'Correo electrónico/nickname o contraseña incorrectos.';
            errorMensajeDiv.style.display = 'block';
        }
    };
});

document.addEventListener('DOMContentLoaded', function() {
    const aRegistrarse = document.getElementById('aRegistrarse');

    aRegistrarse.onclick = function() {
        window.location.href = 'registrar.html';
    };

    document.getElementById("cancelarBtn").addEventListener("click", () => {
        window.location.href = "index.html";
    });
});
