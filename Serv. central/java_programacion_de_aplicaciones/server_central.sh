#!/bin/bash

# 1. Verificar si Java está instalado
if ! sudo update-alternatives --config java | grep -q "java-21"; then
    echo "Java no está instalado. Instalando Java..."
    sudo apt update
    sudo apt install -y openjdk-21-jdk
    sudo update-alternatives --config java
    if ! java -version &>/dev/null; then
        echo "Error: No se pudo instalar Java. Verifica manualmente."
        exit 1
    fi
fi

# 2. Verificar si Maven está instalado
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

# 3. Verificar si dos2unix está instalado
if ! command -v dos2unix &> /dev/null; then
    echo "dos2unix no está instalado. Instalándolo..."
    sudo apt update
    if sudo apt install -y dos2unix; then
        echo "dos2unix instalado correctamente."
    else
        echo "Error: No se pudo instalar dos2unix. Verifica manualmente."
        exit 1
    fi
fi

# 4. Instalar MySQL (o MariaDB) si no está instalado
if ! command -v mysql &> /dev/null; then
    echo "MySQL no está instalado. Instalando MySQL..."
    sudo apt update
    sudo apt install -y mysql-server
    sudo systemctl start mysql
    sudo systemctl enable mysql
fi

# 5. Configurar la base de datos
echo "Configurando la base de datos..."
sudo mysql -e "CREATE DATABASE IF NOT EXISTS obligatoriopa;"
sudo mysql -e "CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED BY '1234';"
sudo mysql -e "GRANT ALL PRIVILEGES ON obligatoriopa.* TO 'root'@'localhost';"
sudo mysql -e "FLUSH PRIVILEGES;"
sudo mysql -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';"
sudo systemctl restart mysql

# 6. Rutas importantes
BUILD_SCRIPT="build.sh"
JAR_PATH="target/java_programacion_de_aplicaciones-0.0.1-SNAPSHOT.jar"
CONFIG_PATH="src/propiedades/server.properties"

# 7. Convertir el script de construcción al formato Unix
if [ -f "$BUILD_SCRIPT" ]; then
    echo "Convirtiendo $BUILD_SCRIPT al formato Unix con dos2unix..."
    dos2unix "$BUILD_SCRIPT"
    if [ $? -ne 0 ]; then
        echo "Error: Falló la conversión de $BUILD_SCRIPT a formato Unix."
        exit 1
    fi
else
    echo "Error: No se encontró el script de construcción en $BUILD_SCRIPT."
    exit 1
fi

# 8. Ejecutar mvn clean install para asegurarse de que las dependencias estén actualizadas
echo "Ejecutando mvn clean install para asegurar que las dependencias estén actualizadas..."
mvn clean install
if [ $? -ne 0 ]; then
    echo "Error: Falló la ejecución de mvn clean install. Verifica las dependencias."
    exit 1
fi

# 9. Construir el archivo JAR
echo "Construyendo el archivo JAR..."
bash "$BUILD_SCRIPT"
if [ $? -ne 0 ]; then
    echo "Error: Falló la construcción del archivo JAR."
    exit 1
fi

# 10. Verificar la existencia del archivo JAR
if [ ! -f "$JAR_PATH" ]; then
    echo "Error: No se generó el archivo JAR esperado en $JAR_PATH."
    exit 1
fi

# 11. Obtener la IP local de la máquina
IP_LOCAL=$(hostname -I | awk '{print $1}')

# 12. Modificar la configuración para usar la IP local
echo "Configurando IP del servidor central en config.properties..."
if [ -f "$CONFIG_PATH" ]; then
    sed -i "s|server.central.url=.*|server.central.url=http://$IP_LOCAL:8084|" "$CONFIG_PATH"
else
    echo "Error: No se encontró el archivo de configuración en $CONFIG_PATH."
    exit 1
fi

# Verificar si lsof está instalado
if ! command -v lsof &> /dev/null; then
    echo "lsof no está instalado. Instalando lsof..."
    sudo apt-get update && sudo apt-get install -y lsof
else
    echo "lsof ya está instalado."
fi

# Verificar si el puerto 8084 está en uso
PORT=8084
PID=$(lsof -t -i:$PORT)

if [ -z "$PID" ]; then
    echo "El puerto $PORT está libre."
else
    echo "El puerto $PORT está en uso por el proceso con PID: $PID"
    
    # Detener el proceso que está utilizando el puerto
    echo "Deteniendo el proceso..."
    kill -9 $PID
    if [ $? -eq 0 ]; then
        echo "Proceso detenido exitosamente."
    else
        echo "No se pudo detener el proceso."
    fi
fi

# 13. Ejecutar el servidor central
echo "Iniciando el servidor central..."
nohup java --add-opens java.base/jdk.internal.misc=ALL-UNNAMED --add-opens java.base/java.lang=ALL-UNNAMED -jar "$JAR_PATH" > server_central.log 2>&1 &

# 14. Confirmar ejecución
if ps aux | grep -v grep | grep -q "$JAR_PATH"; then
    echo "Servidor central iniciado exitosamente en: http://$IP_LOCAL:$PORT"
    echo "Logs disponibles en: server_central.log"
else
    echo "Error: No se pudo iniciar el servidor central."
    exit 1
fi

