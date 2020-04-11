package com.oachoual.todospringbootkotlin.controllers

import com.oachoual.todospringbootkotlin.HelloController
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class HelloControllerTest {
    @Test
    internal fun `check sayHello with name`() {
        val client = WebTestClient.bindToController(HelloController()).build()

        client.get().uri("/hello")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("text/plain;charset=UTF-8")
                .expectBody<String>().isEqualTo("hello")
    }
}