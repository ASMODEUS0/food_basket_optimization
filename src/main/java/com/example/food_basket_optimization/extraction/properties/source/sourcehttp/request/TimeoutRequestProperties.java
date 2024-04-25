package com.example.food_basket_optimization.extraction.properties.source.sourcehttp.request;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class TimeoutRequestProperties extends RequestProperties {
    private final double intervalA;
    private final double intervalB;

    public TimeoutRequestProperties(double intervalA, double intervalB) {
        if(intervalA > intervalB){
          log.error("Interval A bigger than interval B");
          throw new IllegalArgumentException("Interval A bigger than interval B, A = " + intervalA + " B = " + intervalB);
        }

        this.intervalA = intervalA;
        this.intervalB = intervalB;
    }
}
