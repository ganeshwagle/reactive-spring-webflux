package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {
    public static void main(String[] args) {
        GenerateFluxAndMono generateFluxAndMono = new GenerateFluxAndMono();
        generateFluxAndMono.generateNamesFlux().subscribe(System.out::println);
        generateFluxAndMono.generateNameMono().subscribe(ele -> System.out.println("mono -> " + ele));
    }
}

class GenerateFluxAndMono {
    Flux<String> generateNamesFlux() {
        return Flux.just("ganesh", "mahesh", "rakesh", "rajesh");
        //return Flux.fromIterable(List.of("ganesh", "mahesh", "rakesh", "rajesh"));
    }

    Mono<String> generateNameMono() {
        return Mono.just("ganesh");
    }

    Flux<String> flatMap() {
        return Flux.just("ganesh", "mahesh")
                .flatMap(this::fluxFromName)
                .distinct();
    }

    Flux<String> flatMapAsync() {
        return Flux.just("hello", "world")
                .flatMap(this::fluxFromNameWithDelay)
                .log();
    }

    Mono<List<String>> flatMapInMono() {
        return Mono.just("hello")
                .flatMap(this::monFromName)
                .log();
    }


    Flux<String> concatMap() {
        //same as flat map but it maintains the order
        //slower compare to flatmap
        return Flux.just("hello", "world")
                .concatMap(this::fluxFromNameWithDelay)
                .log();
    }

    Flux<String> flatMapMany() {
        return Mono.just("hello")
                .flatMapMany(this::fluxFromName)
                .log();
    }

    Flux<String> transform() {
        Function<Flux<String>, Flux<String>> fluxMonoFunction = name -> name
                .map(String::toUpperCase)
                .map(s -> s.length() + "-" + s);
        return Flux.just("hello", "world")
                .transform(fluxMonoFunction)
                .log();
    }

    Flux<String> concat() {
        Flux<String> abcFlux = Flux.just("a", "b", "c");
        Flux<String> defFlux = Flux.just("d", "e", "f");
        //return Flux.concat(abcFlux, defFlux);//factory concat
        return abcFlux.concatWith(defFlux); //both works same
    }

    Flux<String> merge() {
        Flux<String> abcFlux = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(150));
        //return Flux.merge(abcFlux, defFlux);
        return abcFlux.mergeWith(defFlux).log(); //both works same
    }

    Flux<String> mergeSequential() {
        Flux<String> abcFlux = Flux.just("a", "b", "c")
                .delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(150));
        return Flux.mergeSequential(abcFlux, defFlux).log();
    }

    Flux<String> zip() {
        Flux<String> abcFlux = Flux.just("a", "b", "c","g","h")
                .delayElements(Duration.ofMillis(100));
        Flux<String> defFlux = Flux.just("d", "e", "f")
                .delayElements(Duration.ofMillis(150));
        return Flux.zip(abcFlux, defFlux, (first, second) -> first + ":" + second)
                .log();
       /* return abcFlux.zipWith(defFlux)
                .map(tuple -> tuple.getT1() + ":" + tuple.getT2())
                .log();*/
    }

    Mono<List<String>> monFromName(String name) {
        return Mono.just(List.of(name.split("")));
    }

    Flux<String> fluxFromName(String name) {
        String[] chars = name.split("");
        return Flux.fromArray(chars);
    }

    Flux<String> fluxFromNameWithDelay(String name) {
        System.out.println(name);
        String[] chars = name.split("");
        return Flux.fromArray(chars)
                .delayElements(Duration.ofMillis(new Random().nextInt(3000)));
    }
}
