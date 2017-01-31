package es.sidelab.SaleWeb;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaleWebController {
	
	private List<Articulo> articulos = new ArrayList<>();
	
	public SaleWebController(){
		articulos.add(new Articulo("Suricato", "Animal", "Fiera mascota"));
		articulos.add(new Articulo("Palmera", "Bollo", "Chocolate+Hojaldre"));
	}
	
	@PostMapping("/articulo/nuevo")
	public String nuevoArticulo(Model model, Articulo articulo) {
		
		articulos.add(articulo);
		
		return "articulo_guardado";
	}
	
	@GetMapping("/")
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
}
