package br.com.cdtec.dao.repository;

import java.math.BigInteger;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.crud.repository.GenericRepository;
import br.com.cdtec.entity.Categoria;
import br.com.cdtec.entity.Estabelecimento;


@Lazy(true)
public interface EstabelecimentoRepository extends GenericRepository<Estabelecimento, BigInteger>  {
	
	@Query("SELECT e FROM Estabelecimento e WHERE e.dsEstabelecimento= :dsEstabelecimento")
	Categoria pesquisarPorDescricao(@Param("dsEstabelecimento") String dsEstabelecimento);
	
	@Query("SELECT count(e) FROM Estabelecimento e WHERE e.dsEstabelecimento = :dsEstabelecimento")
	Integer quantidadePorDescricao(@Param("dsEstabelecimento") String dsEstabelecimento);
	
	@Query("SELECT count(e) FROM Estabelecimento e WHERE e.dsEstabelecimento = :dsEstabelecimento AND e.idEstabelecimento <> :idEstabelecimento")
	Integer quantidadePorDescricao(@Param("dsEstabelecimento") String dsEstabelecimento, @Param("idEstabelecimento") BigInteger idEstabelecimento);
	
}