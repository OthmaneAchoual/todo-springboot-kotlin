package com.oachoual.todospringbootkotlin.services

import com.oachoual.todospringbootkotlin.Todo
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class TodoService {
    fun all(): Flux<Todo> = Flux.just(Todo(title = "Appointment", description = "2 o'clock appointment with Linda", done = false))
}