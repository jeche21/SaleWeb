package es.sidelab.SaleWeb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sidelab.SaleWeb.Articulo;
import es.sidelab.SaleWeb.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
	List<Comentario> findByArticulo(Articulo articulo);
}