package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

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
    void flatMap() {

        StepVerifier.create(generateFluxAndMono.flatMap())
                .expectNext("g", "a", "n", "e", "s", "h", "m")
                .verifyComplete();

    }

    @Test
    void flatMapAsync() {

        StepVerifier.create(generateFluxAndMono.flatMapAsync())
                //.expectNext("h", "e", "l", "l", "o", "w", "o", "r", "l", "d")
                .expectNextCount(10)
                .verifyComplete();

    }

    @Test
    void flatMapInMono() {

        StepVerifier.create(generateFluxAndMono.flatMapInMono())
                .expectNext(List.of("h", "e", "l", "l", "o"))
                .verifyComplete();

    }

    @Test
    void concatMap() {

        StepVerifier.create(generateFluxAndMono.concatMap())
                .expectNext("h", "e", "l", "l", "o", "w", "o", "r", "l", "d")
                .verifyComplete();

    }

    @Test
    void flatMapMany() {

        StepVerifier.create(generateFluxAndMono.flatMapMany())
                .expectNext("h", "e", "l", "l", "o")
                .verifyComplete();

    }

    @Test
    void transform() {
        StepVerifier.create(generateFluxAndMono.transform())
                .expectNext("5-HELLO", "5-WORLD")
                .verifyComplete();
    }

    @Test
    void concat() {
        StepVerifier.create(generateFluxAndMono.concat())
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }

    @Test
    void merge() {
        StepVerifier.create(generateFluxAndMono.merge())
                .expectNext("a", "d", "b", "e", "c", "f")
                .verifyComplete();
    }

    @Test
    void mergeSequential() {
        StepVerifier.create(generateFluxAndMono.mergeSequential())
                .expectNext("a", "b", "c", "d", "e", "f")
                .verifyComplete();
    }
}