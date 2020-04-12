package com.oachoual.todospringbootkotlin

import com.oachoual.todospringbootkotlin.repositories.TodoRepository
import com.oachoual.todospringbootkotlin.services.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux
import java.time.Duration

@SpringBootApplication
class TodoSpringbootKotlinApplication {

    @Bean
    fun routes(@Autowired service: TodoService) = router {
        GET("/frp/kotlin/hi") {
            ServerResponse.ok().body(Flux.just(("Hello")))
        }

        GET("/frp/kotlin/todos") {
            ServerResponse.ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(service.all().delayElements(Duration.ofSeconds(1)))
        }

        POST("/frp/kotlin/todos") {
            ServerResponse.ok().body(it.bodyToMono(Todo::class.java).flatMap {
                service.create(it)
            })
        }
    }

    @Bean
    fun runner(repository: TodoRepository) = CommandLineRunner { args ->
        repository.deleteAll()
                .thenMany(
                        Flux.just(
                                Todo(
                                        title = "Learn Spring",
                                        description = "Achieve sufficient level of understanding of the spring platform",
                                        done = false
                                ),
                                Todo(
                                        title = "Learn Akka",
                                        description = "Achieve sufficient level of understanding of the akka platform",
                                        done = false
                                )
                        )
                ).flatMap {
                    repository.save(it)
                }.subscribe {
                    println("Successfully Saved!")
                }
    }
}

fun main(args: Array<String>) {
    runApplication<TodoSpringbootKotlinApplication>(*args)
}
