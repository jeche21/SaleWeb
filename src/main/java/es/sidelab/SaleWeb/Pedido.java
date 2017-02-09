package es.sidelab.SaleWeb;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario usuario;
	
	/*@OneToMany*/
	private ArrayList<Articulo> articulosComprados;
	
	public Pedido(){
	}
	
	public Pedido(ArrayList<Articulo> articulosComprados){
		this.articulosComprados = articulosComprados;
	}
}
