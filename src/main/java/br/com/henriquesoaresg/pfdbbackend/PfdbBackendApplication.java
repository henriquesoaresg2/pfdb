package br.com.henriquesoaresg.pfdbbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PFDB Api", version = "1", description = "API desenvolvida para demonstrar testes de competência em Spring Boot"))
public class PfdbBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PfdbBackendApplication.class, args);
    }

}
