package es.sidelab.SaleWeb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import es.sidelab.SaleWeb.Articulo;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToMany
	private List<Articulo> articulosComprados = new ArrayList<Articulo>();
	
	public Pedido(){
	}
	
	public Pedido(ArrayList<Articulo> articulosComprados){
		this.articulosComprados = articulosComprados;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Articulo> getArticulosComprados() {
		return articulosComprados;
	}

	public void setArticulosComprados(List<Articulo> articulosComprados) {
		this.articulosComprados = articulosComprados;
	}
	
}

