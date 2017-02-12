package es.sidelab.SaleWeb;

import java.util.List;

import javax.annotation.PostConstruct;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sidelab.SaleWeb.Carrito;
import es.sidelab.SaleWeb.Comentario;



@Controller
public class SaleWebController {
	
	@Autowired
	private ArticuloRepository articulo_repository;
	
	@Autowired
	private ComentarioRepository comentario_repository;
	
	@Autowired
	private CarritoRepository carrito_repository;
	
	@Autowired
	private UsuarioRepository usuario_repository;
	
	
	private List<Articulo> articulos = new ArrayList<>();
	//private List<Articulo> articulos_carrito = new ArrayList<>();
	//private List<Usuario> usuarios = new ArrayList<>();
	private List<Comentario> comentarios = new ArrayList<>();
	
	Usuario usuarioEnPagina;
	
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
		articulos = articulo_repository.findAll();
		model.addAttribute("articulos", articulos);		
		
		return "tienda";
	}
	
	//*** DONE ***
	@PostMapping("/articulo/nuevo")
	public String nuevoArticulo(Model model, Articulo articulo) {
		
		//articulos.add(articulo);
		articulo_repository.save(articulo);
		return "articulo_guardado";
	}
	
	//*** DONE ***
	@GetMapping("/articulo/{num}")
	public String verArticulo (Model model, /*@PathVariable long id*/ @PathVariable int num){
		
		Articulo articulo = articulos.get(num-1);
		model.addAttribute("articulo", articulo);
		//Articulo articulo_guardado = articulo_repository.findOne(id);
		//model.addAttribute("articulo",articulo_guardado);
		
		return "ver_articulo";
	}
	
	//*** DONE ***
	@GetMapping("/carrito")
	public String verCarrito (Model model){
			
		model.addAttribute("articulos_carrito",  usuarioEnPagina.getCarrito().getArticulosCarrito());		
			
		return "carrito";
	}
	
	//*** DONE ***
	@GetMapping("/carrito/{num}")
	public String verArticuloCarrito (Model model, @PathVariable int num){
		
		model.addAttribute("articulo_carrito", usuarioEnPagina.getCarrito().getArticulosCarrito().get(num-1));
		model.addAttribute("comentarios", comentarios);
		
		return "ver_articuloCarrito";
	}
	
	
	//*** DONE ***
	@GetMapping("/articulo/{num}/añadido")
	public String añadirArticulo (Model model, /*@PathVariable long id*/ @PathVariable int num){
		
		Articulo articulo = articulos.get(num-1);
		//articulos_carrito.add(articulo);
		//articulos.remove(num-1);
		
		//Articulo articulo_guardado = articulo_repository.findOne(id);
		Carrito carritoUsuario = usuarioEnPagina.getCarrito();
		carritoUsuario.getArticulosCarrito().add(articulo);
		carrito_repository.save(carritoUsuario);
		
		return "articulo_añadido";
	}
	
	//*** DONE ***
	
	//No me mola nadaaaaaaaa esta maaaaaal ¿{num} y luego long id?
	@GetMapping("/carrito/{num}/eliminado")
	public String eliminarArticuloCarrito (Model model,@PathVariable long id /*@PathVariable int num*/){
		
		//articulos_carrito.remove(num-1);
		Articulo articulo_guardado = articulo_repository.findOne(id);
		articulo_repository.delete(articulo_guardado);
		
		return "articuloCarritoEliminado";
	}
	
		@PostMapping("/usuario/nuevo")
		public String UsuarioNuevo (Model model, Usuario usuario){
			//Guardo el usuario creado
			Carrito carritoUsuarioNuevo = new Carrito();
			usuario.setCarrito(carritoUsuarioNuevo);
			usuarioEnPagina = usuario;
			usuario_repository.save(usuario);
			return "usuario_registrado";
		}
		
		@PostMapping("/usuario/comentario")
		public String comentariosLista (Model model,Comentario comentario){
			comentario_repository.save(comentario);
			comentarios.add(comentario);
			
			model.addAttribute("comentarios",comentarios);
			return "comentario_guardado";
		}
		
		@GetMapping("/comentario/eliminado")
		public String eliminarComentario (Model model,Comentario comentario){
			//borramos el comentario tanto de la base de datos....
			comentario_repository.delete(comentario);
			//como de la lista de comentarios,(que la ponemos para poder mostrar todos los comentarios a la lista que tenemos!!
			comentarios.remove(comentario);
			//finalmente mostramos de nuevo el ver articulo con la lista de los comentarios sin el que acabamos de borrar
			return "ver_articulo";
		}
		
		
	
	
}
