package es.sidelab.SaleWeb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	
	//uno articulo tiene varios comentarios y se borran en cascada
	@OneToMany(cascade=CascadeType.ALL)
	List<Comentario> listaComentarios = new ArrayList<>();
	
	
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


	
	
	

}
