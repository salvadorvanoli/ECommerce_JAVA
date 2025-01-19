package clases;

public class SistemaFactory{
	
	private static SistemaFactory instancia;
	private Sistema sistema;
	
	private SistemaFactory() {
		this.sistema = null;
	}
	
	public static SistemaFactory getInstancia() {
		if (instancia == null) {
			instancia = new SistemaFactory();
		}
		return instancia;
	};
	
	public ISistema getISistema() {
		if (this.sistema == null) {
			this.sistema = new Sistema();
		}
		return this.sistema;
	}
}