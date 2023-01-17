package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {
    public static void main(String[] args) {
        GenerateFluxAndMono generateFluxAndMono = new GenerateFluxAndMono();
        generateFluxAndMono.generateNamesFlux().subscribe(System.out::println);
        generateFluxAndMono.generateNameMono().subscribe(ele -> System.out.println("mono -> " + ele));
    }
}

class GenerateFluxAndMono{
    Flux<String> generateNamesFlux(){
        return Flux.just("ganesh", "mahesh", "rakesh", "rajesh");
        //return Flux.fromIterable(List.of("ganesh", "mahesh", "rakesh", "rajesh"));
    }

    Mono<String> generateNameMono(){
        return Mono.just("ganesh");
    }
    Flux<String> generateNamesFluxFlatMap(){
        return Flux.just("ganesh", "mahesh")
                .flatMap(this::generateCharacterFluxFromName)
                .distinct();
    }

    Flux<String> generateCharacterFluxFromName(String name){
        String[] chars = name.split("");
        return Flux.fromArray(chars);
    }

    Flux<String> generateNamesFluxFlatMapAsync(){
        return Flux.just("hello", "world")
                .flatMap(this::generateCharacterFluxFromNameWithDelay)
                .log();
    }

    Flux<String> generateCharacterFluxFromNameWithDelay(String name){
        Random random = new Random();
        String[] chars = name.split("");
        return Flux.fromArray(chars)
                .delayElements(Duration.ofMillis(random.nextInt(3000)));
    }
}
