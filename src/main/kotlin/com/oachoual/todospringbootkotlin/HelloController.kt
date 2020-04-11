package com.oachoual.todospringbootkotlin

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloController {

    @GetMapping("/hello", produces = [MediaType.TEXT_PLAIN_VALUE])
    fun sayHello(): Mono<String> = Mono.just("hello")
}