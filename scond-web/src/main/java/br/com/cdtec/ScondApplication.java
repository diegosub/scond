package br.com.cdtec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.cdtec.security.entity.Usuario;
import br.com.cdtec.security.repository.UsuarioRepository;

@SpringBootApplication
@EntityScan(basePackages={"br.com.cdtec.entity" , "br.com.cdtec.security.entity"})
@EnableJpaRepositories(basePackages={"br.com.cdtec.repository" , "br.com.cdtec.security.repository"})
public class ScondApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScondApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			initUSuario(usuarioRepository, passwordEncoder);
		};
	}
	
	private void initUSuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		
		Usuario usuario = new Usuario();
		usuario.setDsEmail("cdiego.lima@gmail.com");
		usuario.setDsLogin("diego");
		usuario.setDsNome("Carlos Diego de Lima Chaves");
		usuario.setDsPassword(passwordEncoder.encode("123"));
		usuario.setIdPerfil(new Integer(1));
		
		Usuario find = usuarioRepository.pesquisarPorLogin("diego");
		if(find == null) {
			usuarioRepository.save(usuario);
		}
	}
}
