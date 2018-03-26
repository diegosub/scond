package br.com.cdtec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages={"br.com.cdtec.entity"})
@EnableJpaRepositories(basePackages={"br.com.cdtec.repository"})
public class ScondApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScondApplication.class, args);
	}
}
