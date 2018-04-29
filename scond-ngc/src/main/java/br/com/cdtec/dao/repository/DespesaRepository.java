package br.com.cdtec.dao.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.crud.repository.GenericRepository;
import br.com.cdtec.entity.Despesa;


@Lazy(true)
public interface DespesaRepository extends GenericRepository<Despesa, BigInteger>  {
	
	@Query("SELECT d FROM Despesa d INNER JOIN FETCH d.segmento INNER JOIN FETCH d.estabelecimento LEFT JOIN FETCH d.cartao WHERE d.idDespesa = :idDespesa")
	Optional<Despesa> findById(@Param("idDespesa") BigInteger idDespesa);
	
}