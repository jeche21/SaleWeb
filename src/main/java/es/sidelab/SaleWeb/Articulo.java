package es.sidelab.SaleWeb;

public class Articulo {
	
	private String nombre;
	private String seccion;
	private String descripcion;
	
	public Articulo(){
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
