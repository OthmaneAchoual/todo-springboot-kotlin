package com.oachoual.todospringbootkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoSpringbootKotlinApplication

fun main(args: Array<String>) {
    runApplication<TodoSpringbootKotlinApplication>(*args)
}
