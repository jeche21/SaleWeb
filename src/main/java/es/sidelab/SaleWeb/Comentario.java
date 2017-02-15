package es.sidelab.SaleWeb;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.Usuario;

@Entity
public class Comentario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String cuerpo;
	
	@ManyToOne
	private Usuario autor;
	
	@ManyToOne
	private Articulo articulo;
	
	protected Comentario(){
		//Sirve para que SpringData pueda instanciar el objeto 
	}
	
	public Comentario (String cuerpo, String fecha, Usuario autor){
		this.cuerpo = cuerpo;
		this.autor = autor;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
}
