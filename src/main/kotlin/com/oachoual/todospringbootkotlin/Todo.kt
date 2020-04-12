package com.oachoual.todospringbootkotlin

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Todo(
        @Id var id: String? = null,
        val title: String = "",
        val description: String,
        val done: Boolean
)