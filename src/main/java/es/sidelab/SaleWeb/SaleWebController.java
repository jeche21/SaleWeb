package es.sidelab.SaleWeb;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SaleWebController {
	
	private List<Articulo> articulos = new ArrayList<>();
	private List<Articulo> articulos_carrito = new ArrayList<>();
	
	public SaleWebController(){
		
		articulos.add(new Articulo("Suricato", "Animal", "Fiera mascota"));
		articulos.add(new Articulo("Palmera", "Bollo", "Chocolate+Hojaldre"));
		
		//articulos_carrito.add(new Articulo("prueba", "prueba", "prueba"));
	}
	
	@RequestMapping("/")
	public String principal (){	
		return "principal";
	}
	
	@GetMapping("/tienda")
	public String tienda (Model model){
		
		model.addAttribute("articulos", articulos);		
		
		return "tienda";
	}
	
	//*** DONE ***
	@PostMapping("/articulo/nuevo")
	public String nuevoArticulo(Model model, Articulo articulo) {
		
		articulos.add(articulo);
		
		return "articulo_guardado";
	}
	
	//*** DONE ***
	@GetMapping("/articulo/{num}")
	public String verArticulo (Model model, @PathVariable int num){
		
		Articulo articulo = articulos.get(num-1);
		
		model.addAttribute("articulo", articulo);
		
		return "ver_articulo";
	}
	
	//*** DONE ***
	@GetMapping("/carrito")
	public String verCarrito (Model model){
			
		model.addAttribute("articulos_carrito", articulos_carrito);		
			
		return "carrito";
	}
	
	//*** DONE ***
	@GetMapping("/carrito/{num}")
	public String verArticuloCarrito (Model model, @PathVariable int num){
		
		Articulo articulo_carrito = articulos_carrito.get(num-1);
		model.addAttribute("articulo_carrito", articulo_carrito);
		
		return "ver_articuloCarrito";
	}
	
	
	//*** DONE ***
	@GetMapping("/articulo/{num}/añadido")
	public String añadirArticulo (Model model, @PathVariable int num){
		
		Articulo articulo = articulos.get(num-1);
		articulos_carrito.add(articulo);
		articulos.remove(num-1);
		
		return "articulo_añadido";
	}
	
	//**************JESUS ESTE SERIA EL NOMBRE QUE TIENES QUE UTILIZAR PARA LA VENTANA DE NUEVO USUARIO.
	@GetMapping("/usuario/nuevo")
	public String UsuarioNuevo (Model model){
		
		return "nuevo_usuario";
	}
	//**************BUYYYYI ESTE SERIA EL CONTROLADOR PARA EL CARRITO.
	@GetMapping("/carrito")
	public String carrito (Model model){
		
		return "carrito";
	}
	
	//*** DONE ***
	@GetMapping("/carrito/{num}/eliminado")
	public String eliminarArticuloCarrito (Model model, @PathVariable int num){
		
		articulos_carrito.remove(num-1);
		
		return "articuloCarritoEliminado";
	}
	
	
}
