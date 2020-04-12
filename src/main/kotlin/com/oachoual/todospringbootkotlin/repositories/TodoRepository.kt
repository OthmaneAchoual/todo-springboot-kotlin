package com.oachoual.todospringbootkotlin.repositories

import com.oachoual.todospringbootkotlin.Todo
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface TodoRepository : ReactiveMongoRepository<Todo, String>