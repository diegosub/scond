package br.com.cdtec.repository;

import java.math.BigInteger;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cdtec.crud.repository.GenericRepository;
import br.com.cdtec.entity.Usuario;


@Lazy(true)
public interface UsuarioRepository extends GenericRepository<Usuario, BigInteger>  {
	
	@Query("SELECT u FROM Usuario u WHERE u.dsLogin = :dsLogin")
	Usuario pesquisarPorLogin(@Param("dsLogin") String dsLogin);
	
}