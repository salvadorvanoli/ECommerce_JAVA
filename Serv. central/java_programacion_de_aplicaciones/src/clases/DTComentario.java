package clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTComentario {

		@XmlID
		private int id;
		
		@XmlElement
		private String contenido;
		
		@XmlTransient
		private DTCliente cliente;
		
		@XmlElement
		private String nickRespuesta;
		
		@XmlElement
		private int idRespuesta;
		
		@XmlElement
		private String foto;
		
		@XmlElement
		private String nombre;
		
		@XmlTransient
		private Producto producto;
		
		@XmlElement
		private DTFecha fecha;
		
		private int estrellas;
		
		public DTComentario() {
		}
		
		public DTComentario(int id, String contenido, DTCliente cliente,
				Producto producto, DTFecha fecha, int estrellas, String nickRespuesta, int idRespuesta) {
			super();
			this.setId(id);
			this.setContenido(contenido);
			this.foto = cliente.getFoto();
			this.nombre = cliente.getNickname();
			this.cliente = cliente;
			this.producto = producto;
			this.setFecha(fecha);
			this.setEstrellas(estrellas);
			this.setNickRespuesta(nickRespuesta);
			this.idRespuesta = idRespuesta;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getContenido() {
			return contenido;
		}

		public void setContenido(String contenido) {
			this.contenido = contenido;
		}

		public DTCliente getCliente() {
			return cliente;
		}

		public Producto getProducto() {
			return producto;
		}

		public DTFecha getFecha() {
			return fecha;
		}

		public void setFecha(DTFecha fecha) {
			this.fecha = fecha;
		}

		public int getEstrellas() {
			return estrellas;
		}

		public void setEstrellas(int estrellas) {
			this.estrellas = estrellas;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getNickRespuesta() {
			return nickRespuesta;
		}

		public void setNickRespuesta(String nickRespuesta) {
			this.nickRespuesta = nickRespuesta;
		}

		public int getIdRespuesta() {
			return idRespuesta;
		}

		public void setIdRespuesta(int idRespuesta) {
			this.idRespuesta = idRespuesta;
		}
}
