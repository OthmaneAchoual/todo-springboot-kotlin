package com.oachoual.todospringbootkotlin.controllers

import com.oachoual.todospringbootkotlin.Todo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

internal class TodoControllerTest {

    @Test
    internal fun `get all todos`() {
        val client = WebTestClient.bindToController(TodoController()).build()

        client.get().uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody<List<Todo>>().consumeWith {
                    assertAll(
                            { assertThat(it.responseBody?.size).isEqualTo(1) }
                    )
                }
    }
}