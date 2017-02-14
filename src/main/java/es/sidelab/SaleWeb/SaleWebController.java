package es.sidelab.SaleWeb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.ArticuloRepository;
import es.sidelab.SaleWeb.CarritoRepository;
import es.sidelab.SaleWeb.Comentario;
import es.sidelab.SaleWeb.ComentarioRepository;
import es.sidelab.SaleWeb.Usuario;
import es.sidelab.SaleWeb.UsuarioRepository;
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
	
	@Autowired
	private PedidoRepository pedido_repository;
	
	
	private List<Comentario> comentarios = new ArrayList<>();
	
	Usuario usuarioEnPagina;
	private Pedido pedido;
	
	@PostConstruct
	public void inicio(){
		articulo_repository.save(new Articulo("Suricato", "Animal", "Fiera mascota"));
		articulo_repository.save(new Articulo("Palmera", "Bollo", "Chocolate+Hojaldre"));
	}
	
	@GetMapping("/")
	public String principal (){
		return "principal";
	}
	
	@PostMapping("/loggin")
	public String logginUsuario(Model model, @RequestParam String email, @RequestParam String contraseña, HttpSession sesion){
		Usuario usuario = usuario_repository.findByEmailAndContraseña(email, contraseña);
		sesion.setAttribute("email", usuario.getEmail());
		boolean existe = false;
		boolean noexiste=false;
		if(usuario==null){
			existe=false;
			noexiste=true;
		}
		else{
			existe=true;
			noexiste=false;
		}
		model.addAttribute("existe", existe);
		model.addAttribute("noexiste", noexiste);
		return "loggin_usuario";
	}
	
	@GetMapping("/tienda")
	public String tienda (Model model){
		model.addAttribute("articulos", articulo_repository.findAll());
		return "tienda";
	}
	
	@PostMapping("/articulo/nuevo")
	public String nuevoArticulo(Model model, Articulo articulo) {
		
		articulo_repository.save(articulo);
		return "articulo_guardado";
	}
	
	@GetMapping("/articulo/{id}")
	public String verArticulo (Model model, @PathVariable long id){
		
		Articulo articulo = articulo_repository.findOne(id);
		model.addAttribute("articulo", articulo);
		model.addAttribute("comentarios",comentario_repository.findByArticulo(articulo));
		return "ver_articulo";
	}
	
	@GetMapping("/articulo/{id}/eliminar")
	public String eliminarArticulo (Model model, @PathVariable long id){
		
		Articulo articulo = articulo_repository.findOne(id);
		articulo_repository.delete(articulo);
		model.addAttribute("articulos", articulo_repository.findAll());
		return "articulo_eliminado";
	}
	
	@GetMapping("/carrito")
	public String verCarrito (Model model, HttpSession sesion){
		Usuario usuario = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		model.addAttribute("articulos_carrito",  usuario.getCarrito().getArticulosCarrito());
		
		return "carrito";
	}
	
	@GetMapping("/carrito/{num}")
	public String verArticuloCarrito (Model model, @PathVariable int num, HttpSession sesion){
		Usuario usuario = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		model.addAttribute("articulo_carrito", usuario.getCarrito().getArticulosCarrito().get(num-1));
		
		return "ver_articuloCarrito";
	}
	
	
	@GetMapping("/articulo/{id}/añadido")
	public String añadirArticulo (Model model, @PathVariable long id, HttpSession sesion){
		
		Articulo articulo = articulo_repository.findOne(id);
		Usuario usuario = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		Carrito carritoUsuario = usuario.getCarrito();
		carrito_repository.delete(carritoUsuario);
		carritoUsuario.getArticulosCarrito().add(articulo);
		carrito_repository.save(carritoUsuario);
		
		return "articulo_añadido";
	}
	
	@GetMapping("/carrito/{num}/eliminado")
	public String eliminarArticuloCarrito (Model model,@PathVariable int num, HttpSession sesion){
		
		//articulos_carrito.remove(num-1);
		Usuario usuarioBuscado = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		Carrito usuario = usuarioBuscado.getCarrito();
		carrito_repository.delete(usuario);
		usuarioBuscado.getCarrito().getArticulos_carrito().remove(num-1);
		carrito_repository.save(usuario);
		return "articuloCarritoEliminado";
	}
	
	@PostMapping("/usuario/nuevo")
	public String UsuarioNuevo (Model model, Usuario usuario, HttpSession sesion){
		//Guardo el usuario creado
		sesion.setAttribute("email", usuario.getEmail());
		Carrito carritoUsuarioNuevo = new Carrito();
		usuario.setCarrito(carritoUsuarioNuevo);
		usuario_repository.save(usuario);
		return "usuario_registrado";
	}
	
	@PostMapping("/comentario/articulo/{id}")
	public String comentar (Model model, Comentario comentario, @PathVariable long id, HttpSession sesion){
		//Guardo el comentario escrito creado
		Articulo articulo = articulo_repository.findOne(id);
		comentario.setArticulo(articulo);
		Usuario usuario = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		comentario.setAutor(usuario);
		articulo.getComentarios().add(comentario);
		comentario_repository.save(comentario);
		return "comentario_guardado";
	}
	
	@GetMapping("/comentario/{id}/eliminado")
	public String comentarioEliminar (Model model,@PathVariable long id){
		comentario_repository.delete(id);
		return "comentario_eliminado";
	}
		
	@GetMapping("/articulo/comentario/{id}")
	public String verComentario(Model model, @PathVariable long id){
		
		model.addAttribute("comentario", comentario_repository.findOne(id));
		return "ver_comentario";
	}
		
	@PostMapping("/carrito/comprar")
	public String comprarArticulos(Model model,Usuario usuario){
		Carrito carrito = usuario.getCarrito();
		List<Articulo> listaCarrito = carrito.getArticulos_carrito();
		Pedido pedido =  new Pedido();
		pedido.setArticulosComprados(listaCarrito);
		usuario_repository.delete(usuario);
		usuario.getPedidos().add(pedido);
		usuario_repository.save(usuario);
		pedido_repository.save(pedido);
		model.addAttribute("usuario",usuario);
		model.addAttribute("articulo_carrito", listaCarrito);
		return "pedido";
	}
	
	@PostMapping("/pedido")
	public String pedido(){
		return "pedido_realizado";
	}
	
	@GetMapping("/comentario/{num}/eliminado")
	public String comentarioEliminar (Model model,@PathVariable int num){
		
		Comentario comentarioSeleccionado = comentarios.get(num-1);
		comentarios.remove(comentarioSeleccionado);
		comentario_repository.delete(comentarioSeleccionado);
		model.addAttribute("comentarios",comentarios);
		return "ver_articulo";
	}
		
	@GetMapping("/comentario/{num}")
	public String verComentario(Model model,@PathVariable int num){
		
		Comentario comentario = comentarios.get(num-1);
		model.addAttribute("articulo", comentario);
		return "ver_comentario";
	}
	
		
}
