package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    GenerateFluxAndMono generateFluxAndMono = new GenerateFluxAndMono();
    @Test
    void fluxTest() {
        StepVerifier.create(generateFluxAndMono.generateNamesFlux())
                .expectNext("ganesh", "mahesh", "rakesh", "rajesh")
                .verifyComplete();

        /*StepVerifier.create(generateFluxAndMono.generateNamesFlux())
                .expectNextCount(4)
                .verifyComplete();*/

       /* StepVerifier.create(generateFluxAndMono.generateNamesFlux())
                .expectNext("ganesh")
                .expectNextCount(3)
                .verifyComplete();*/
    }
}