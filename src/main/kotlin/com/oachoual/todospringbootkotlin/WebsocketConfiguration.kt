package com.oachoual.todospringbootkotlin

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Flux
import java.time.Duration

@Configuration
class WebsocketConfiguration {
    @Bean
    fun websocketHandler() = WebSocketHandler { session ->
        session.send(Flux.generate<Todo> {
            it.next(Todo(title = "Task2", description = "Wear Tutu", done = false))
        }.map {
            session.textMessage(it.description)
        }.delayElements(Duration.ofSeconds(1))
                .doFinally {
                    println("Goodbye")
                })
    }

    @Bean
    fun handlerMapping(): HandlerMapping {
        val mapping = SimpleUrlHandlerMapping()
        mapping.urlMap = mapOf("/ws/hello" to websocketHandler())
        mapping.order = 10
        return mapping
    }

    @Bean
    fun handlerAdapter() = WebSocketHandlerAdapter()
}