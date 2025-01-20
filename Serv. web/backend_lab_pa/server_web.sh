#!/bin/bash

# 1. Obtener la IP local de la máquina
IP_LOCAL=$(hostname -I | awk '{print $1}')
read -p "Ingrese la IP del servidor central: " IP_CENTRAL

# 2. Ruta del proyecto
PROJECT_PATH="backend_lab_pa"

# 3. Verificar si JAX-WS (wsimport) está disponible, si no, instalarlo
echo "Verificando si JAX-WS está instalado..."
if ! command -v wsimport &> /dev/null; then
    echo "JAX-WS no está instalado. Instalando OpenJDK..."
    sudo apt update
    sudo apt install -y jaxws
    if ! command -v wsimport &> /dev/null; then
        echo "Error: JAX-WS no está disponible después de instalar OpenJDK. Verifica la instalación de Java."
        exit 1
    fi
else
    echo "JAX-WS ya está instalado."
fi

#Verificar si Maven está instalado
if ! command -v mvn &> /dev/null; then
    echo "Maven no está instalado. Instalando Maven..."
    sudo apt update
    if sudo apt install -y maven; then
        echo "Maven instalado correctamente."
    else
        echo "Error: No se pudo instalar Maven. Verifica manualmente."
        exit 1
    fi
fi

mvn clean install

# 4. Instalar Apache Tomcat 10.1.24 si no está instalado
TOMCAT_PATH="/opt/tomcat"
TOMCAT_VERSION="10.1.24"

if [ ! -d "$TOMCAT_PATH" ]; then
    echo "Tomcat no está instalado. Instalando Tomcat $TOMCAT_VERSION..."
    TOMCAT_URL="https://archive.apache.org/dist/tomcat/tomcat-10/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz"
    wget "$TOMCAT_URL" -O tomcat.tar.gz
    if [ $? -ne 0 ]; then
        echo "Error al descargar Tomcat desde $TOMCAT_URL. Verifique la conexión a Internet."
        exit 1
    fi
    sudo mkdir -p "$TOMCAT_PATH"
    sudo tar -xvzf tomcat.tar.gz -C "$TOMCAT_PATH" --strip-components=1
    if [ $? -ne 0 ]; then
        echo "Error al extraer Tomcat. Verifique la descarga o los permisos."
        rm -f tomcat.tar.gz
        exit 1
    fi
    sudo rm -f tomcat.tar.gz
    sudo chmod -R 755 "$TOMCAT_PATH"
    sudo chown -R $USER:$USER "$TOMCAT_PATH"
else
    echo "Tomcat ya está instalado en $TOMCAT_PATH."
fi

# 5. Configurar Tomcat para aceptar conexiones desde cualquier dispositivo
SERVER_XML="$TOMCAT_PATH/conf/server.xml"
if [ -f "$SERVER_XML" ]; then
    if grep -q 'address="0.0.0.0"' "$SERVER_XML"; then
        echo "El archivo server.xml ya está configurado para aceptar conexiones externas."
    else
        echo "Configurando Tomcat para aceptar conexiones desde cualquier dispositivo..."
        sed -i '/<Connector port="8080"/ s|<Connector|<Connector address="0.0.0.0"|' "$SERVER_XML"
        if [ $? -ne 0 ]; then
            echo "Error al configurar server.xml. Verifica los permisos."
            exit 1
        fi
    fi
else
    echo "Error: No se encuentra el archivo server.xml. Verifique la instalación de Tomcat."
    exit 1
fi

# 6. Crear servicio systemd para Apache Tomcat
SERVICE_FILE="/etc/systemd/system/tomcat.service"
if [ ! -f "$SERVICE_FILE" ]; then
    echo "Creando servicio de systemd para Tomcat..."
    sudo bash -c "cat > $SERVICE_FILE" <<EOF
[Unit]
Description=Apache Tomcat $TOMCAT_VERSION
After=network.target

[Service]
Type=forking
Environment=JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
Environment=CATALINA_PID=$TOMCAT_PATH/temp/tomcat.pid
Environment=CATALINA_HOME=$TOMCAT_PATH
Environment=CATALINA_BASE=$TOMCAT_PATH
ExecStart=$TOMCAT_PATH/bin/startup.sh
ExecStop=$TOMCAT_PATH/bin/shutdown.sh
User=$USER
Group=$USER
UMask=0007
RestartSec=10
Restart=always

[Install]
WantedBy=multi-user.target
EOF
    sudo systemctl daemon-reload
    sudo systemctl enable tomcat
fi

# 7. Generar las clases necesarias desde el WSDL
WSDL_URL="http://$IP_CENTRAL:8084/publicador?wsdl"
WSIMPORT_DIR="src/main/java"

echo "Generando clases desde el WSDL..."
wsimport -keep -p services "$WSDL_URL" -d "$WSIMPORT_DIR"
if [ $? -ne 0 ]; then
    echo "Error al ejecutar wsimport. Verifica la URL del WSDL: $WSDL_URL"
    exit 1
fi

# 8. Construir el archivo WAR
echo "Construyendo el archivo WAR..."
bash build.sh
WAR_FILE="dist/backend_lab_pa.war"

if [ ! -f "$WAR_FILE" ]; then
    echo "Error: No se pudo generar el archivo WAR."
    exit 1
fi

# 9. Desplegar el archivo WAR en Tomcat
echo "Desplegando el WAR en Tomcat..."
cp "$WAR_FILE" "$TOMCAT_PATH/webapps/"
if [ $? -ne 0 ]; then
    echo "Error al copiar el WAR a la carpeta webapps. Verifica los permisos y rutas."
    exit 1
fi

# 10. Reiniciar Tomcat para aplicar los cambios
sudo systemctl restart tomcat
if [ $? -ne 0 ]; then
    echo "Error al reiniciar Tomcat. Verifica el estado del servicio."
    exit 1
fi

echo "Tomcat reiniciado correctamente. Servidor web disponible en: http://$IP_LOCAL:8080/backend_lab_pa"

