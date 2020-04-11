package com.oachoual.todospringbootkotlin.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.test.StepVerifier

internal class TodoServiceTest {

    @Test
    internal fun `dummy service`() {

        val service = TodoService()
        StepVerifier.create(service.all())
                .consumeNextWith {
                    assertThat(it.title).isEqualTo("Appointment")
                }.expectComplete()
                .verify()
    }
}