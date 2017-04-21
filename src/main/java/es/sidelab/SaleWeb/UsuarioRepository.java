package es.sidelab.SaleWeb;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@CacheConfig(cacheNames="saleweb")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@CacheEvict(allEntries=true)	
	Usuario save(Usuario usuario);

	@Cacheable
	Usuario findByEmailAndContraseña(String email, String contraseña);
	
	@Cacheable
	Usuario findByEmail(String email);
}
