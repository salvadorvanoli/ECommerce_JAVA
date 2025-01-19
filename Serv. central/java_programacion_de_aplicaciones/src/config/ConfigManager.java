package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;



public class ConfigManager {
	
	private String PropertiesRuta;
	
	private void crearArchivoProperties(String nombrePropertie) {
		 
		String user = System.getProperty("user.name");
	 	String raiz = "/home/";
	 	
	 	// test
	 	// user = "felip";
	 	// raiz = "/mnt/c/Users/";
	 	
	 	File rutaPadre = new File(raiz + user + "/DirectMarket");
		 	
	        String rutaAbsoluta = rutaPadre + "/" + nombrePropertie + ".properties";
		 
	        this.PropertiesRuta = rutaAbsoluta;
	        
	        if (!rutaPadre.exists()) {
	            if (rutaPadre.mkdirs()) {
	                System.out.println("Directorio creado: " + rutaPadre);
	            } else {
	                System.out.println("No se pudo crear el directorio: " + rutaPadre);
	            }
	        } else {
	            System.out.println("El directorio ya existe: " + rutaPadre);
	        }
	        
	        File archivo = new File(rutaAbsoluta);
	        
	        File directorio = archivo.getParentFile();
	        if (directorio != null && !directorio.exists()) {
	            if (directorio.mkdirs()) {
	                System.out.println("Directorio creado: " + directorio.getAbsolutePath());
	            } else {
	                System.out.println("No se pudo crear el directorio.");
	                return;
	            }
	        }
	        
	        Properties properties = new Properties();
			try (InputStream input = new FileInputStream("src/propiedades/server.properties")) {
				properties.load(input);
			} catch (IOException e) {
			    e.printStackTrace();
			}
	       
	        try (FileOutputStream fos = new FileOutputStream(archivo)) {
	            properties.store(fos, "Configuracion del Sistema");
	            System.out.println("Archivo .properties creado en: " + archivo.getAbsolutePath());
	        } catch (IOException e) {
	            System.out.println("Error al crear el archivo: " + e.getMessage());
	        }
	        
	}
	 
	public String getPropiedad(String clave) {
		Properties prop = new Properties();
		
		try(FileInputStream fis = new FileInputStream(this.PropertiesRuta)){
			prop.load(fis);
		}	catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		return prop.getProperty(clave);
	}
	 

    public void crearProperties() {  	
        crearArchivoProperties("Config");    
    }
    
    /*
    public static void main(String args[]) {
    	ConfigManager a = new ConfigManager();
    	a.crearProperties();
    }
    */
    
}
