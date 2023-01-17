package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

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

    @Test
    void generateNamesFluxFlatMap() {

        StepVerifier.create(generateFluxAndMono.generateNamesFluxFlatMap())
                .expectNext("g", "a", "n", "e", "s", "h", "m")
                .verifyComplete();

    }
}