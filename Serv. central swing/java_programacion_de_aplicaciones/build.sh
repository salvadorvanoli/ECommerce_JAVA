#!/bin/bash

# Carpeta de salida para los artefactos finales
OUTPUT_DIR="dist"
# Crear la carpeta si no existe
mkdir -p "$OUTPUT_DIR"

# Limpiar y empaquetar el proyecto
echo "Compilando y generando artefactos..."
mvn clean package

# Verificar si el archivo JAR fue generado
if [ -f target/java_programacion_de_aplicaciones-0.0.1-SNAPSHOT.jar ]; then
    echo "Copiando el archivo JAR a $OUTPUT_DIR..."
    cp target/java_programacion_de_aplicaciones-0.0.1-SNAPSHOT.jar "$OUTPUT_DIR/app.jar"
    echo "Archivo app.jar generado correctamente en $OUTPUT_DIR."
else
    echo "Error: No se encontró el archivo JAR."
    exit 1
fi
