package es.sidelab.SaleWeb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import es.sidelab.SaleWeb.Carrito;
import es.sidelab.SaleWeb.Comentario;
import es.sidelab.SaleWeb.Pedido;

//es una entidad de la clase
@Entity
public class Articulo {
	//id principal 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String seccion;
	private String descripcion;
	
	@ManyToMany(mappedBy="articulosComprados")
	private List<Pedido> articulosEnPedidos = new ArrayList<Pedido>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Carrito> articulosEnCarrito = new ArrayList<Carrito>();
	
	@OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	protected Articulo(){
		//Sirve para que SpringData pueda instanciar el objeto 
	}
	
	public Articulo (String nombre, String seccion, String descripcion){
		this.nombre = nombre;
		this.seccion = seccion;
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
	
	

	public List<Pedido> getArticulosEnPedidos() {
		return articulosEnPedidos;
	}

	public void setArticulosEnPedidos(List<Pedido> articulosEnPedidos) {
		this.articulosEnPedidos = articulosEnPedidos;
	}

	public List<Carrito> getArticulosEnCarrito() {
		return articulosEnCarrito;
	}

	public void setArticulosEnCarrito(List<Carrito> articulosEnCarrito) {
		this.articulosEnCarrito = articulosEnCarrito;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
