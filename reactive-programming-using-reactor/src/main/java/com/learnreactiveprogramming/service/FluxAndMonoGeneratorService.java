package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
}
