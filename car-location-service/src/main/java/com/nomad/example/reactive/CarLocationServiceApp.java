package com.nomad.example.reactive;

import com.nomad.example.reactive.domain.Car;
import com.nomad.example.reactive.domain.LocationGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoActionOperation;
import org.springframework.data.mongodb.core.MongoOperations;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class CarLocationServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(CarLocationServiceApp.class, args);
	}

	@Bean
	public CommandLineRunner initData(MongoOperations mongo) {

		return (String... args) -> {
			mongo.dropCollection(Car.class);
			mongo.createCollection(Car.class, CollectionOptions.empty().size(100000).capped());

			LocationGenerator generator = new LocationGenerator(40.740900, -73.988000);

			Flux.range(1, 100)
					.map(i -> new Car(i.longValue(), generator.location()))
					.doOnNext(mongo::save)
					.blockLast(Duration.ofSeconds(5));
		};

	}
}
