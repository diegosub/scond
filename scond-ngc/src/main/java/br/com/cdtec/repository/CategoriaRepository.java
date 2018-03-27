package br.com.cdtec.repository;

import java.math.BigInteger;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.crud.repository.GenericRepository;
import br.com.cdtec.entity.Categoria;


@Lazy(true)
public interface CategoriaRepository extends GenericRepository<Categoria, BigInteger>  {
	
	@Query("SELECT c FROM Categoria c WHERE c.dsCategoria = :dsCategoria")
	Categoria pesquisarPorDescricao(@Param("dsCategoria") String dsCategoria);
	
}