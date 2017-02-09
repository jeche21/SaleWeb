package es.sidelab.SaleWeb;

import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SaleWebController {
	
	@Autowired
	private ArticuloRepository articulo_repository;
	
	@Autowired
	private CarritoRepository carrito_repository;
	
	@Autowired
	private UsuarioRepository usuario_repository;
	
	private Carrito carrito;
	
	//private List<Articulo> articulos = new ArrayList<>();
	//private List<Articulo> articulos_carrito = new ArrayList<>();
	//private ArrayList<Usuario> usuarios = new ArrayList<>();
	
	@PostConstruct
	public void inicio(){
		
		articulo_repository.save(new Articulo("Suricato", "Animal", "Fiera mascota"));
		articulo_repository.save(new Articulo("Palmera", "Bollo", "Chocolate+Hojaldre"));
		
		//articulos_carrito.add(new Articulo("prueba", "prueba", "prueba"));
	}
	
	@RequestMapping("/")
	public String principal (){
		return "principal";
	}
	
	@GetMapping("/tienda")
	public String tienda (Model model){
		
		List<Articulo> articulos = articulo_repository.findAll();
		
		model.addAttribute("articulos", articulos);		
		
		return "tienda";
	}
	
	@PostMapping("/articulo/nuevo")
	public String nuevoArticulo(Model model, Articulo articulo) {
		
		//articulos.add(articulo);
		articulo_repository.save(articulo);
		return "articulo_guardado";
	}
	
	@GetMapping("/articulo/{num}")
	public String verArticulo (Model model, @PathVariable long id /*@PathVariable int num*/){
		
		//Articulo articulo = articulos.get(num-1);
		//model.addAttribute("articulo", articulo);
		
		Articulo articulo_guardado = articulo_repository.findOne(id);
		model.addAttribute("articulo",articulo_guardado);
		
		return "ver_articulo";
	}
	
	@GetMapping("/carrito")
	public String verCarrito (Model model){
		
		//duda: como coger los articulos del array de articulos del carrito
		
		model.addAttribute("articulos_carrito", carrito.articulos_carrito);		
			
		return "carrito";
	}
	
	@GetMapping("/carrito/{num}")
	public String verArticuloCarrito (Model model, @PathVariable int id /*@PathVariable int num*/){
		
		Articulo articulo_carrito = carrito.articulos_carrito.get(id-1);
		model.addAttribute("articulo_carrito", articulo_carrito);
		
		return "ver_articuloCarrito";
	}
	
	@GetMapping("/articulo/{num}/añadido")
	public String añadirArticulo (Model model, @PathVariable long id /*@PathVariable int num*/){
		
		/*Articulo articulo = articulos.get(num-1);
		articulos_carrito.add(articulo);
		articulos.remove(num-1);*/
		
		Articulo articulo_guardado = articulo_repository.findOne(id);
		
		carrito.articulos_carrito.add(articulo_guardado);
		//carrito_repository.save(articulo_guardado);
		articulo_repository.delete(articulo_guardado);
		
		return "articulo_añadido";
	}
	
	@GetMapping("/carrito/{num}/eliminado")
	public String eliminarArticuloCarrito (Model model,@PathVariable long id /*@PathVariable int num*/){
		
		//articulos_carrito.remove(num-1);
		Articulo articulo_guardado = articulo_repository.findOne(id);
		articulo_repository.delete(articulo_guardado);
		
		return "articuloCarritoEliminado";
	}
	
	
	@PostMapping("/usuario/nuevo")
	public String UsuarioNuevo (Usuario usuario){
		//Guardo el usuario creado
		usuario_repository.save(usuario);
		return "usuario_registrado";
	}

	
}
