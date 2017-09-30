package com.nomad.example.reactive.spring5;

import com.nomad.example.reactive.domain.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Created by yhlee on 2017. 8. 15..
 */
@RestController
@Slf4j
public class CarAppController {

    private WebClient carLocationClient = WebClient.create("http://localhost:8081");
    private WebClient carRequestClient = WebClient.create("http://localhost:8082");

    @PostMapping("/booking")
    public Mono<ResponseEntity<Void>> book() {

        return carLocationClient.get()
                .uri("/cars")
                .retrieve()
                .bodyToFlux(Car.class)
                .take(5)
                .flatMap(car -> {
                    log.info("Requesting " + car);
                    return carRequestClient.post()
                            .uri("/cars/" + car.getId() + "/booking")
                            .exchange()
                            .map(this::toBookingResponseEntity);
                }).next();

    }


    private ResponseEntity<Void> toBookingResponseEntity(ClientResponse clientResponse) {
        HttpStatus status = clientResponse.statusCode();
        URI location = clientResponse.headers().asHttpHeaders().getLocation();
        Assert.state(status.equals(HttpStatus.CREATED), "Booking Failed: " + status + ", Location : " + location);
        return ResponseEntity.created(location).build();
    }


}
