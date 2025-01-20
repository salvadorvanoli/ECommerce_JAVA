function mostrarAlerta(mensaje) {
    let alertaExistente = document.getElementById("alerta");
    
    if (alertaExistente) {
        alertaExistente.remove();
    }

    const alerta = document.createElement("div");
    alerta.className = "alert alert-danger absoluto";
    alerta.setAttribute("role", "alert");
    alerta.id = "alerta";
    alerta.innerHTML = `
        ${mensaje}
        <button type="button" class="close" aria-label="Close" onclick="this.parentElement.remove();">
            <span aria-hidden="true">&times;</span>
        </button>
    `;

    document.getElementsByTagName("body")[0].appendChild(alerta);
}