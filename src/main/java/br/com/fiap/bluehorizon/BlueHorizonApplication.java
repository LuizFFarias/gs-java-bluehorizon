package br.com.fiap.bluehorizon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "BlueHorizon openApi", version = "1", description = "API para o projeto BlueHorizon da Global Solution"))
@SpringBootApplication
public class BlueHorizonApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlueHorizonApplication.class, args);
    }

}
