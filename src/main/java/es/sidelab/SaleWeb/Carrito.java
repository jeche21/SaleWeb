package es.sidelab.SaleWeb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrito {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private long id_propietario;
	
	//el carrito tiene varios articulos y se borran en cascada
	@OneToMany(cascade=CascadeType.ALL)
	List<Articulo> articulos_carrito = new ArrayList<>();
	
	@OneToOne(mappedBy="carrito")
	private Usuario usuario;
	
	protected Carrito(){
		//Sirve para que SpringData pueda instanciar el objeto 
	}
	
	public Carrito (String nombre, long id_propietario){
		this.nombre = nombre;
		this.id_propietario = id_propietario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId_propietario() {
		return id_propietario;
	}

	public void setId_propietario(long id_propietario) {
		this.id_propietario = id_propietario;
	}

	public List<Articulo> getArticulos_carrito() {
		return articulos_carrito;
	}

	public void setArticulos_carrito(List<Articulo> articulos_carrito) {
		this.articulos_carrito = articulos_carrito;
	}
	
	
	

}
