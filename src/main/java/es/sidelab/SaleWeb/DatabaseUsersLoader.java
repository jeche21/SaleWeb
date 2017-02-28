package es.sidelab.SaleWeb;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUsersLoader {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostConstruct
	private void initDataBase(){
		usuarioRepository.save(new Usuario("jesus", "rosa", "martin", "adminpass", "jesus", "ROLE_ADMIN"));
	}
}
