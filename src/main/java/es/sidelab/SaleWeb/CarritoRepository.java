package es.sidelab.SaleWeb;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="saleweb")
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
	
	@CacheEvict(allEntries=true)	
	Carrito save(Carrito carrito);
	
	@Cacheable
	Carrito findById(long id);
	
}
