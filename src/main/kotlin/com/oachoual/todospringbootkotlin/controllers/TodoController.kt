package com.oachoual.todospringbootkotlin.controllers

import com.oachoual.todospringbootkotlin.Todo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class TodoController {

    @GetMapping("/")
    fun all(): Flux<Todo> = Flux.fromArray(
            arrayOf(
                    Todo(title = "Appointment", description = "2 o'clock appointment with Linda", done = false)
            )
    )
}