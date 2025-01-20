package com.flamingo.models;

import java.util.List;
import services.DtComentario;

public class ComentarioRenderer {

    public static String renderComentarios(List<DtComentario> comentarios, int nivel, int contador, Boolean compro) {
        StringBuilder html = new StringBuilder();

        for (DtComentario comentario : comentarios) {
            if (comentario.getIdRespuesta() == 0) { // Comentarios principales
                html.append(renderComentario(comentario, nivel, contador, compro, comentarios));
            }
        }

        return html.toString();
    }

    private static String renderComentario(DtComentario comentario, int nivel, int contador, Boolean compro, List<DtComentario> todosComentarios) {
        StringBuilder html = new StringBuilder();

        // Renderizar comentario principal o respuesta
        html.append("<div class='comentario-respuesta-container container-md container-fluid'>")
            .append("<div class='carta-comentarios row' style='margin-left: ").append(nivel * 20).append("px;'>")
            .append("<div class='informacion-usuario col-md-3 col-12'>")
            .append("<img src='").append(comentario.getFoto() != null && !comentario.getFoto().isEmpty() ? "/backend_lab_pa/getImagen?ruta=" + comentario.getFoto() : "media/images/Chica1.png")
            .append("' alt='Perfil usuario' class='imagen-usuario'>")
            .append("<div><h4>").append(comentario.getNombre()).append("</h4>")
            .append("<p class='fecha'>").append(String.format("%04d-%02d-%02d", comentario.getFecha().getAnio(), comentario.getFecha().getMes(), comentario.getFecha().getDia()))
            .append("</p><div class='estrellas'>");

        // Mostrar estrellas solo en comentarios principales
        if (nivel == 0) {
            int estrellas = comentario.getEstrellas();
            for (int i = 0; i < estrellas; i++) {
                html.append("<i class='fas fa-star' style='color: #7A7A7A;'></i>");
            }
            for (int i = 0; i < (5 - estrellas); i++) {
                html.append("<i class='fas fa-star' style='color: #EBEBEB;'></i>");
            }
        }

        html.append("</div>");
        if (compro) {
            html.append("<button class='btn btn-success mt-2' onclick='mostrarCajaRespuesta(")
                .append(contador)
                .append(", \"")
                .append(comentario.getId())
                .append("\")'>Responder</button>");
        }

        html.append("</div></div>")
            .append("<div class='texto-comentario col-md-8 col-12'>");

        // Mostrar si es una respuesta
        if (comentario.getIdRespuesta() != 0) {
            html.append("<span class='respuesta-a'>@").append(comentario.getNickRespuesta()).append(comentario.getIdRespuesta()).append(":</span> ");
        }

        html.append(comentario.getContenido())
            .append("<p class='comentario-id'>ID: ").append(comentario.getId()).append("</p>")
            .append("</div></div>");

        // Caja para responder a este comentario
        contador++;
        String comentarioId = String.valueOf(comentario.getId());
        String comentarioNombre = comentario.getNombre() != null ? comentario.getNombre() : "Anónimo";

        html.append("<form action='nuevaRespuesta' method='POST' class='caja-comentario card p-3 mt-3 mb-3 d-none' id='respuesta_")
            .append(comentarioId)
            .append("'>")
            .append("<div class='form-group'>")
            .append("<label for='comentarioInput_").append(comentarioId).append("'>Escribe tu respuesta:</label>")
            .append("<input type='text' name='texto' class='form-control' id='comentarioInput_").append(comentarioId).append("' placeholder='Escribe aquí...'>")
            .append("</div>")
            .append("<input type='hidden' name='idRespuesta' value='").append(comentarioId).append("'>")
            .append("<input type='hidden' name='nickRespuesta' value='").append(comentarioNombre).append("'>")
            .append("<div class='mt-3'>")
            .append("<input type='submit' class='btn btn-success' value='Aceptar'>")
            .append("<button class='btn btn-danger' type='button' onclick='cancelarComentario(").append(comentarioId).append(")'>Cancelar</button>")
            .append("</div>")
            .append("</form>");

        // Renderizar respuestas anidadas
        for (DtComentario respuesta : todosComentarios) {
            if (respuesta.getIdRespuesta() == comentario.getId()) {
                html.append(renderComentario(respuesta, nivel + 1, contador, compro, todosComentarios));
            }
        }

        html.append("</div>"); // Cierra el contenedor principal
        return html.toString();
    }
}
