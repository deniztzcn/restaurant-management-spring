package pja.edu.pl.s26772.restaurantmanagement;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Deniz Tezcan");
        myContact.setEmail("s26772@pjwstk.edu.pl");

        Info information = new Info()
                .title("Felicita Resturant API")
                .version("1.0")
                .description("This API provides operations for Italian restaurant called Felicita.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
