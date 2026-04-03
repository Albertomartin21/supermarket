package commercadona.supermarket.infraestructure.rest;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import commercadona.supermarket.domain.exception.RequestException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiClientWeb {

     public ApiClientWeb() {}

        @Retry(name = "webClientRetry", fallbackMethod = "fallbackGetRequest")
        @CircuitBreaker(name = "webClientCBReactive")
        public <T> T getRequest(String url, Class<T> type) {
        WebClient webClient = WebClient.create(url);
        return webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(type)
            .timeout(Duration.ofSeconds(5))
            .doOnSubscribe(s -> log.info("Realizando peticion a url {}", url))
            .block();
    }

     public <T> T fallbackGetRequest(String url, Class<T> type, Throwable ex) {
        String msg = String.format("Falló en la llamada a %s. Ejecutando retroceso. Causa: %s", url, ex.getClass().getSimpleName());
        log.error(msg + " "+ ex.getMessage()); 
        throw new RequestException(msg);
    }
}


