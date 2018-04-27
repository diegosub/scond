package br.com.cdtec.dao.repository;

import java.math.BigInteger;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.crud.repository.GenericRepository;
import br.com.cdtec.entity.Cartao;


@Lazy(true)
public interface CartaoRepository extends GenericRepository<Cartao, BigInteger>  {
	
	@Query("SELECT c FROM Cartao c WHERE c.dsCartao= :dsCartao")
	Cartao pesquisarPorDescricao(@Param("dsCartao") String dsCartao);
	
	@Query("SELECT count(c) FROM Cartao c WHERE c.dsCartao = :dsCartao")
	Integer quantidadePorDescricao(@Param("dsCartao") String dsCartao);
	
	@Query("SELECT count(c) FROM Cartao c WHERE c.dsCartao = :dsCartao AND c.idCartao <> :idCartao")
	Integer quantidadePorDescricao(@Param("dsCartao") String dsCartao, @Param("idCartao") BigInteger idCartao);
	
}