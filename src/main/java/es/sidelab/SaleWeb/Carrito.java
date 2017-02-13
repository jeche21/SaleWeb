package es.sidelab.SaleWeb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.Usuario;

public class Carrito {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
		
	//el carrito tiene varios articulos y se borran en cascada
	@ManyToMany
	List<Articulo> articulosCarrito = new ArrayList<Articulo>();
	
	@OneToOne(mappedBy="carrito")
	private Usuario usuario;
	
	protected Carrito(){
		//Sirve para que SpringData pueda instanciar el objeto 
	}
	
	public Carrito (Usuario usuario){
		this.usuario = usuario;
		this.articulosCarrito = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Articulo> getArticulosCarrito() {
		return articulosCarrito;
	}

	public void setArticulosCarrito(List<Articulo> articulosCarrito) {
		this.articulosCarrito = articulosCarrito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Articulo> getArticulos_carrito() {
		return articulosCarrito;
	}
	
	public void setArticulos_carrito(List<Articulo> articulos_carrito) {
		this.articulosCarrito = articulos_carrito;
	}
}
