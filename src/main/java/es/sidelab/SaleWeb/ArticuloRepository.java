package es.sidelab.SaleWeb;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.Carrito;

@CacheConfig(cacheNames="saleweb")
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
	
	@CacheEvict(allEntries=true)
	Articulo save(Articulo articulo);
	
	@Cacheable
	Articulo findByNombre(String nombre);
	
	@Cacheable
	List<Articulo> findBySeccion(String seccion);
	
	@Cacheable
	List<Articulo> findByArticulosEnCarrito(Carrito carrito);
	
}
