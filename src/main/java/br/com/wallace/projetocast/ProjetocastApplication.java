package br.com.wallace.projetocast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.wallace.projetocast.service.AuditingService;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingService")
public class ProjetocastApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetocastApplication.class, args);

	}

	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditingService();
	}

}
