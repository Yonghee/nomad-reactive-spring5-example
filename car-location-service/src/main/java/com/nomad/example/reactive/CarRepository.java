package com.nomad.example.reactive;

import com.nomad.example.reactive.domain.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * Created by yhlee on 2017. 8. 13..
 */
public interface CarRepository extends ReactiveMongoRepository<Car,Long> {

    @Tailable
    Flux<Car> findCarsBy();
}
