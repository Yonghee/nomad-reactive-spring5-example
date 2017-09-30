package com.nomad.example.reactive.spring5;

import com.nomad.example.reactive.domain.Car;
import com.nomad.example.reactive.domain.LocationGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * Created by yhlee on 2017. 8. 15..
 */
@Slf4j
public class ClientUploadApp {

    public static void main(String[] args) {

        WebClient client = WebClient.create("http://localhost:8081");

        LocationGenerator gen = new LocationGenerator(40.740900, -73.988000);
        Flux<Car> cars = Flux.interval(Duration.ofSeconds(2))
                .map(i -> new Car(i + 200, gen.location()));

        client.post()
                .uri("/cars")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(cars, Car.class)
                .retrieve()
                .bodyToFlux(Car.class)
                .doOnNext(car -> log.info("Uploaded : {}", car))
                .blockLast();
    }
}
