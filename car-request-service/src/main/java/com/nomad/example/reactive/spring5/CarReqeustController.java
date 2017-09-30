package com.nomad.example.reactive.spring5;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Random;

/**
 * Created by yhlee on 2017. 8. 15..
 */
@RestController
public class CarReqeustController {
    private static final Random RANDOM = new Random();

    @PostMapping("/cars/{id}/booking")
    public Mono<ResponseEntity<Void>> requestCar(@PathVariable Long id) {

        return Mono.delay(randomThinkTime())
                .map(l -> ResponseEntity.created(bookingUrl(id)).build());

    }

    private URI bookingUrl(Long id) {
        return URI.create("/car/" + id + "/booking/" + Math.abs(RANDOM.nextInt()));
    }

    private Duration randomThinkTime() {
        return Duration.ofSeconds(RANDOM.nextInt(5 - 2) + 2);
    }
}
