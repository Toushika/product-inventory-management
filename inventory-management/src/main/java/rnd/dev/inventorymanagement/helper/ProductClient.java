package rnd.dev.inventorymanagement.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.UUID;

@Component
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8095/product").build();
    }

    public boolean productExists(UUID productId) {
        try {
            // matches /availability/{productId}
            // returns true/false
            return Boolean.TRUE.equals(webClient.get()
                    .uri("/availability/{id}", productId) // matches /availability/{productId}
                    .retrieve()
                    .bodyToMono(Boolean.class)           // returns true/false
                    .block());                            // blocking call
        } catch (WebClientResponseException.NotFound e) {
            return false; // Product does not exist
        } catch (WebClientResponseException.BadRequest e) {
            System.out.println("Bad request. Check UUID format: " + productId);
            return false;
        }
    }


}
