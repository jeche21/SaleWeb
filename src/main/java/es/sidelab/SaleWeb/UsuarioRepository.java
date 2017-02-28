package es.sidelab.SaleWeb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmailAndContraseña(String email, String contraseña);
	Usuario findByEmail(String email);
}
