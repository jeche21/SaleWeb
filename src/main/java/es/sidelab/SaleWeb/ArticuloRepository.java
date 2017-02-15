package es.sidelab.SaleWeb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
	
	List<Articulo> findByNombre(String nombre);
	List<Articulo> findBySeccion(String seccion);
	
}
