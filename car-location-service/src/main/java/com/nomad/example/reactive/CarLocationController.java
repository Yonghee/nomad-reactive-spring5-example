package com.nomad.example.reactive;

import com.nomad.example.reactive.domain.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/**
 * Created by yhlee on 2017. 8. 13..
 */
@RestController
public class CarLocationController {

    private final CarRepository repository;


    public CarLocationController(CarRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/cars")
    public Flux<Car> getCars() {
        return this.repository.findAll().log();
    }

    @PostMapping(path = "/cars", consumes = "application/stream+json") //Accept:application/stream+json
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Car> loadCars(@RequestBody Flux<Car> cars) {
        return this.repository.insert(cars);
    }
}
