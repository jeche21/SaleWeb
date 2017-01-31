package es.sidelab.ejerciciosDAD;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/formulario")
public class AnuncioController {
	
	@RequestMapping(method=RequestMethod.POST)
	public String formulario(Model model, @RequestParam String name){
		model.addAttribute(name);
		return "formulario";
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String formulario2(Model model,  @RequestParam String name){
		model.addAttribute(name);
		return "formulario";
	}
}
