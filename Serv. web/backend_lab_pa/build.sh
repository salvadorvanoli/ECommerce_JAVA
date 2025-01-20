#!/bin/bash

# Carpeta de salida para los artefactos finales
OUTPUT_DIR="dist"
# Crear la carpeta si no existe
mkdir -p $OUTPUT_DIR

# Limpiar y empaquetar el proyecto
echo "Compilando y generando artefactos..."
mvn clean package

# Verificar si el .war fue generado
if [ -f target/backend_lab_pa-0.0.1-SNAPSHOT.war ]; then
    echo "Copiando el archivo WAR a $OUTPUT_DIR..."
    cp target/backend_lab_pa-0.0.1-SNAPSHOT.war $OUTPUT_DIR/backend_lab_pa.war
    echo "Archivo web.war generado correctamente en $OUTPUT_DIR."
else
    echo "Error: No se encontró el archivo WAR."
    exit 1
fi
