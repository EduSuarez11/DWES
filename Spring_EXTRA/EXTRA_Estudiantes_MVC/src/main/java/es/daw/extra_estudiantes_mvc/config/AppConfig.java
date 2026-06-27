package es.daw.extra_estudiantes_mvc.config;

import es.daw.extra_estudiantes_mvc.dto.EstudianteDTO;
import es.daw.extra_estudiantes_mvc.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@PropertySource("classpath:config.properties")
@RequiredArgsConstructor
public class AppConfig {

    @Value("${api.estudiantes.url}")
    private String apiURL;

    @Value("${api.estudiantes.auth.url}")
    private String authURL;


    @Bean
    public WebClient webClientAuth() {
        return WebClient.builder().baseUrl(authURL).build();
    }

    @Bean
    public WebClient webClientAPI() {
        return WebClient.builder().baseUrl(apiURL).build();
    }

}
