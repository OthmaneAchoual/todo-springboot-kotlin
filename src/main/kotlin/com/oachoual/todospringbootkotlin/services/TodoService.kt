package com.oachoual.todospringbootkotlin.services

import com.oachoual.todospringbootkotlin.Todo
import com.oachoual.todospringbootkotlin.repositories.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoService(@Autowired val repository: TodoRepository) {
    fun all() = repository.findAll()
    fun create(todo: Todo) = repository.insert(todo)
    fun find(id: String) = repository.findById(id)
    fun update(todo: Todo) = repository.save(todo)
    fun delete(id: String) = repository.deleteById(id)
}