package es.sidelab.SaleWeb;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmailAndContraseña(String email, String contraseña);
	Usuario findByEmail(String email);
}
