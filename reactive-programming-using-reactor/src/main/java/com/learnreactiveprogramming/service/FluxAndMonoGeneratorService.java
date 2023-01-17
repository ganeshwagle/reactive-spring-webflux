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
    Flux<String> flatMap(){
        return Flux.just("ganesh", "mahesh")
                .flatMap(this::fluxFromName)
                .distinct();
    }

    Flux<String> flatMapAsync(){
        return Flux.just("hello", "world")
                .flatMap(this::generateCharacterFluxFromNameWithDelay)
                .log();
    }

    Mono<List<String>> flatMapInMono(){
        return Mono.just("hello")
                .flatMap(this::generateMonoOfChars)
                .log();
    }


    Flux<String> concatMap(){
        //same as flat map but it maintains the order
        //slower compare to flatmap
        return Flux.just("hello", "world")
                .concatMap(this::generateCharacterFluxFromNameWithDelay)
                .log();
    }

    Flux<String> flatMapMany(){
        return Mono.just("hello")
                .flatMapMany(this::fluxFromName)
                .log();
    }


    Flux<String> generateCharacterFluxFromNameWithDelay(String name){
        System.out.println(name);
        String[] chars = name.split("");
        return Flux.fromArray(chars)
                .delayElements(Duration.ofMillis(new Random().nextInt(3000)));
    }

    Mono<List<String>> generateMonoOfChars(String name){
        return Mono.just(List.of(name.split("")));
    }

    Flux<String> fluxFromName(String name){
        String[] chars = name.split("");
        return Flux.fromArray(chars);
    }
}
