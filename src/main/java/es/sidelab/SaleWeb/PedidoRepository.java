
package es.sidelab.SaleWeb;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="saleweb")
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@CacheEvict(allEntries=true)	
	Pedido save(Pedido pedido);

}
