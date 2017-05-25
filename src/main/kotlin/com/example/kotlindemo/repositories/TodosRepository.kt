package com.example.kotlindemo.repositories

import com.example.kotlindemo.models.Todo
import org.springframework.data.repository.CrudRepository


interface TodosRepository : CrudRepository<Todo, Long> {
    fun findByUid(uid: String): Todo?
}