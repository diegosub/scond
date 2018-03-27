package br.com.cdtec.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;

import br.com.cdtec.util.ApplicationContextProvider;

@Configuration
@ComponentScan(basePackages = {"br.com.cdtec.controller", "br.com.cdtec.service" , "br.com.cdtec.security.service"})
@EnableScheduling
public class SpringConfig {
	
	@Lazy(false)
	@Bean(name="applicationContextProvider")
	public ApplicationContextProvider applicationContextProvider( ApplicationContext applicationContext ){
		ApplicationContextProvider applicationContextProvider = new ApplicationContextProvider();
		applicationContextProvider.setApplicationContext( applicationContext );
		return applicationContextProvider;
	}

}