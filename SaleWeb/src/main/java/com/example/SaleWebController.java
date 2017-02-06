package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


public class SaleWebController {
	
	private ArrayList<Articulo> articulos = new ArrayList<>();
	private ArrayList<Usuario> usuarios = new ArrayList<>();
		
		public SaleWebController(){
			articulos.add(new Articulo("Suricato", "Animal", "Fiera mascota"));
			articulos.add(new Articulo("Palmera", "Bollo", "Chocolate+Hojaldre"));
		}
		
		@GetMapping("/")
		public String principal (Model model){	
			/*Esta funcionalidad la he añadido para ver si se guardan bien los usuarios*/
			model.addAttribute("usuarios", usuarios);
			return "principal";
		}
		
		@PostMapping("/articulo/nuevo")
		public String nuevoArticulo(Model model, Articulo articulo) {
			
			articulos.add(articulo);
			
			return "articulo_guardado";
		}
		
		@GetMapping("/tienda")
		public String tienda (Model model){
			
			model.addAttribute("articulos", articulos);		
			
			return "tienda";
		}
		
		@GetMapping("/articulo/{num}")
		public String verArticulo (Model model, @PathVariable int num){
			
			Articulo articulo = articulos.get(num-1);
			
			model.addAttribute("articulo", articulo);
			
			return "ver_articulo";
		}
		
		@GetMapping("/articulo/{num}/comprado")
		public String comprarArticulo (Model model, @PathVariable int num){
			
			articulos.remove(num-1);
			
			return "comprar";
		}
		
		//**************JESUS ESTE SERIA EL NOMBRE QUE TIENES QUE UTILIZAR PARA LA VENTANA DE NUEVO USUARIO.
		@PostMapping("/usuario/nuevo")
		public String UsuarioNuevo (Model model, Usuario usuario){
			usuarios.add(usuario);
			return "usuario_registrado";
		}
		//**************BUYYYYI ESTE SERIA EL CONTROLADOR PARA EL CARRITO.
		@GetMapping("/carrito")
		public String carrito (Model model){
			
			return "carrito";
		}
	}
