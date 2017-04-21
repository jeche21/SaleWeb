package es.sidelab.SaleWeb;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.Comentario;

@CacheConfig(cacheNames="saleweb")
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	
	@CacheEvict(allEntries=true)	
	Comentario save(Comentario comentario);
	
	@Cacheable
	List<Comentario> findByArticulo(Articulo articulo);
}