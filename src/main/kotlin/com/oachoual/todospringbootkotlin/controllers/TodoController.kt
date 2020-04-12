package com.oachoual.todospringbootkotlin.controllers

import com.oachoual.todospringbootkotlin.Todo
import com.oachoual.todospringbootkotlin.services.TodoService
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration

@RestController
class TodoController(@Autowired val service: TodoService) {

    @GetMapping("/todos", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun todos() = service.all().delayElements(Duration.ofSeconds(1))

    @GetMapping("/")
    fun all(): Flux<Todo> = Flux.fromArray(
            arrayOf(
                    Todo(title = "Appointment", description = "2 o'clock appointment with Linda", done = false)
            )
    )

    @GetMapping("/async", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun allAsync(): Publisher<Todo> {
        return Flux.generate<Todo> {
            it.next(Todo(title = "Task 1", description = "Pick flowers", done = false))
        }.delayElements(Duration.ofSeconds(1))
    }
}