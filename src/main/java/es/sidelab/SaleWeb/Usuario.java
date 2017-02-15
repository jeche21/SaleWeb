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


import es.sidelab.SaleWeb.Comentario;
import es.sidelab.SaleWeb.Carrito;
import es.sidelab.SaleWeb.Pedido;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String contraseña;
	private String email;
	/*Se podría poner a la hora de realizar la compra para asi no tener un 
	 * cuestionario tan grande, para ello tendriamos que crear direcion como una
	 * nueva entidad.
	 * De esa forma podriamos añadir nuevas direciones al usuario, sino se queda con esta
	 * y no tiene opcion a cambiarla
	 */
	private String pais;
	private String provincia;
	private String municipio;
	private String calle;
	private int numero; 
	private String portal; //Dentro de una urbanizacion indica el portal o null si no esta en una urbanizacion
	private String escalera; //Hay portales con dos escaleras
	private int piso;
	private String letra; //2ºA o 2º1 damos las dos opciones con string
	
	@OneToMany(mappedBy="usuario", cascade = CascadeType.ALL)
	private List<Pedido> pedidos = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Carrito carrito;
	
	@OneToMany(mappedBy="autor")
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	
	public Usuario(){
	}
	
	public Usuario(String nombre, String primerApellido, String segundoApelido, String contraseña, String email,
			String pais, String provincia, String municipio, String calle, int numero, String portal, String escalera, int piso, String letra){
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApelido;
		this.contraseña = contraseña;
		this.email = email;
		this.pais = pais;
		this.provincia = provincia;
		this.municipio = municipio;
		this.calle = calle;
		this.numero = numero;
		this.portal = portal;
		this.escalera = escalera;
		this.piso = piso;
		this.letra = letra;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPortal() {
		return portal;
	}

	public void setPortal(String portal) {
		this.portal = portal;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", email=" + email + ", pais=" + pais + ", provincia=" + provincia + ", municipio="
				+ municipio + ", calle=" + calle + ", numero=" + numero + ", portal=" + portal + ", escalera="
				+ escalera + ", piso=" + piso + ", letra=" + letra + "]";
	}
}

