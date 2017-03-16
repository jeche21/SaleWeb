package es.sidelab.SaleWeb;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.sidelab.SaleWeb.Pedido;
import es.sidelab.SaleWeb.PedidoRepository;
import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.ArticuloRepository;
import es.sidelab.SaleWeb.Comentario;
import es.sidelab.SaleWeb.ComentarioRepository;
import es.sidelab.SaleWeb.Usuario;
import es.sidelab.SaleWeb.UsuarioRepository;
import es.sidelab.SaleWeb.Carrito;



@Controller
public class SaleWebController {
	
	@Autowired
	private ArticuloRepository articulo_repository;
	
	@Autowired
	private ComentarioRepository comentario_repository;
	
	@Autowired
	private UsuarioRepository usuario_repository;
	
	@Autowired
	private PedidoRepository pedido_repository;
	

	@GetMapping("/")
	public String principal (){
		return "principal";
	}
	
	
	@GetMapping("/loggin")
	public String logginUsuario(){
		return "loggin_usuario";
	}
	
	
	@GetMapping("/tienda")
	public String tienda (Model model,HttpServletRequest request, HttpSession sesion){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		sesion = request.getSession();
		sesion.setAttribute("email", currentPrincipalName);
		model.addAttribute("articulos", articulo_repository.findAll());
		model.addAttribute("admin",request.isUserInRole("ADMIN"));
		return "tienda";
	}
	
	
	@PostMapping("/articulo/nuevo")
	public String nuevoArticulo(Model model, Articulo articulo) {
	
		Articulo articuloEncontrado = articulo_repository.findByNombre(articulo.getNombre());
		if(articuloEncontrado!=null){
			int cantidad = articuloEncontrado.getCantidad();
			cantidad++;
			articuloEncontrado.setCantidad(cantidad);
			articulo_repository.save(articuloEncontrado);
		}else{
			articulo.setCantidad(1);
			articulo_repository.save(articulo);
		}
		
		return "articulo_guardado";
	}
	
	
	@GetMapping("/articulo/{id}")
	public String verArticulo (Model model, @PathVariable long id,HttpServletRequest request){
		
		Articulo articulo = articulo_repository.findOne(id);
		model.addAttribute("articulo", articulo);
		model.addAttribute("comentarios",comentario_repository.findByArticulo(articulo));
		model.addAttribute("admin",request.isUserInRole("ADMIN"));
		
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
		
		String resultado = "";
		Articulo articulo = articulo_repository.findOne(id);

		Usuario usuario = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		Carrito carritoUsuario = usuario.getCarrito();
		articulo.getArticulosEnCarrito().add(carritoUsuario);
		int cantidad = articulo.getCantidad();
		if (cantidad == 0){
			resultado = "producto_no_disponible";
		}else{
			cantidad--;
			articulo.setCantidad(cantidad);
			articulo_repository.save(articulo);
			resultado = "articulo_añadido";
		}
		return resultado;
	}
	
	
	@GetMapping("/carrito/{num}/eliminado")
	public String eliminarArticuloCarrito (Model model, @PathVariable int num, HttpSession sesion){

		Usuario usuarioBuscado = usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		Carrito usuario = usuarioBuscado.getCarrito();
		Articulo articulo = usuario.getArticulosCarrito().get(num-1);
		articulo.getArticulosEnCarrito().remove(usuario);

		int cantidad = articulo.getCantidad();
		cantidad++;
		articulo.setCantidad(cantidad);
		articulo_repository.save(articulo);
		return "articuloCarritoEliminado";
	}
	
	
	@PostMapping("/usuario/nuevo")
	public String UsuarioNuevo (Model model, Usuario usuario, HttpSession sesion){
		
		//Guardo el usuario creado
		sesion.setAttribute("email", usuario.getEmail());
		List<String> rol = new ArrayList<String>();
		rol.add("ROLE_USER");
		usuario.setRol(rol);
		
		//Puente ya que el constructor de la clase no me lo hace
		Carrito carrito = new Carrito();
		usuario.setCarrito(carrito);
		String contraseña = usuario.getContraseña();
		usuario.setContraseña(new BCryptPasswordEncoder().encode(contraseña));
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
	public String verComentario(Model model, @PathVariable long id, HttpServletRequest request, HttpSession sesion){
		
		Comentario comentario = comentario_repository.findOne(id);
		Boolean permitoEliminar = ((request.isUserInRole("ROLE_ADMIN")) || 
									(comentario.getAutor().getEmail().equals((String) sesion.getAttribute("email"))));
		model.addAttribute("botonEliminar", permitoEliminar);
		model.addAttribute("comentario", comentario);
		return "ver_comentario";
	}
		
	
	@PostMapping("/carrito/comprar")
	public String comprarArticulos(Model model, HttpSession sesion){
		
		Usuario usuario =  usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		Carrito carrito = usuario.getCarrito();
		model.addAttribute("usuario",usuario);
		model.addAttribute("articulos_carrito", carrito.getArticulosCarrito());
		return "pedido";
	}
		
	
	@PostMapping("/pedido")
	public String pedido(Model model, HttpSession sesion){
		
		Usuario usuario =  usuario_repository.findByEmail((String) sesion.getAttribute("email"));
		List<Articulo> articulosPedido = articulo_repository.findByArticulosEnCarrito(usuario.getCarrito());
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		SocketCliente socket = new SocketCliente();
		socket.enviarEmail(usuario.getEmail());
		
		for(Articulo articulo: articulosPedido){
			pedido.getArticulosComprados().add(articulo);
		}
		pedido_repository.save(pedido);
			
		Carrito carrito = usuario.getCarrito();
		for(Articulo articulo: articulosPedido){
			articulo.getArticulosEnCarrito().remove(carrito);
			articulo_repository.save(articulo);
		}
			
		return "pedido_realizado";
	}
	
	
	@PostMapping("/registrar_usuario")
	public String registrarUsuario(Model model){
		return "nuevoUsuario";
	}
	
	
	@PostMapping("/nuevo_articulo")
	public String añadirArticulo(){
		return "nuevoArticulo";
	}
}