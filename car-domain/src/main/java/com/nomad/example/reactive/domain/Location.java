package com.nomad.example.reactive.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by yhlee on 2017. 8. 13..
 */
@Getter
@ToString
public class Location {

    private final BigDecimal longitude;
    private final BigDecimal latitude;

    @JsonCreator
    public Location(@JsonProperty("longitude") BigDecimal longitude, @JsonProperty("latitude") BigDecimal latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
