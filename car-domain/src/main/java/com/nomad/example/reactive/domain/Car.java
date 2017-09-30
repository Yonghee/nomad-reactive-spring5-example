package com.nomad.example.reactive.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by yhlee on 2017. 8. 13..
 */
@Getter
@ToString
public class Car {

    private final Long id;

    private final Location location;


    @JsonCreator
    public Car(@JsonProperty("id") Long id, @JsonProperty("location") Location location) {
        this.id = id;
        this.location = location;
    }
}
