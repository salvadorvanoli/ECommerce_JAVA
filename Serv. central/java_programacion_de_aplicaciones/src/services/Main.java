package services;

import config.ConfigManager;

public class Main {
	public static void main (String[] args) {
		ConfigManager a = new ConfigManager();
		a.crearProperties();
		
		Publicador p = new Publicador();
		p.publicar(a);	
		System.out.print(p.saludar());
	}
}
