package com.example;

public class Usuario {
	
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String contraseña;
	private String email;
	
	public Usuario(){
	}
	
	public Usuario(String nombre, String primerApellido, String segundoApelido, String contraseña, String email){
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApelido;
		this.contraseña = contraseña;
		this.email = email;
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
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", contraseña=" + contraseña + ", email=" + email + "]";
	}
	
	
}
