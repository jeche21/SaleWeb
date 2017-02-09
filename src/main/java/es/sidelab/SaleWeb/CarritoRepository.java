package es.sidelab.SaleWeb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
	
	/*List<Articulo> findByNombre(String nombre);
	List<Articulo> findBySeccion(String seccion);*/
	
	
	
}
