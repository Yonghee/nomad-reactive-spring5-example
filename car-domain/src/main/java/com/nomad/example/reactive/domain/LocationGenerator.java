package com.nomad.example.reactive.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

/**
 * Created by yhlee on 2017. 8. 13..
 */
public class LocationGenerator {

    private static final MathContext MATH_CONTEXT = new MathContext(8);

    private static final Random random = new Random();

    private final BigDecimal longitude;
    private final BigDecimal latitude;


    public LocationGenerator(double longitude, double latitude) {
        this.longitude = new BigDecimal(longitude,MATH_CONTEXT);
        this.latitude = new BigDecimal(latitude,MATH_CONTEXT);
    }

    public Location location() {
        return new Location(longitude.add(randomDeviation(), MATH_CONTEXT), latitude.add(randomDeviation(), MATH_CONTEXT));
    }

    private BigDecimal randomDeviation() {
        return BigDecimal.valueOf(random.nextGaussian());
    }


}
