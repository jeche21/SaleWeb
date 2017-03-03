package es.sidelab.SaleWeb;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CarritoRepository extends JpaRepository<Carrito, Long> {
	Carrito findById(long id);
	
}
