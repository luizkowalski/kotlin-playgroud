package com.example.kotlindemo.repositories

import com.example.kotlindemo.models.Todo
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.CrudRepository

interface TodosRepository : CrudRepository<Todo, Long> {
    @Cacheable(cacheNames = arrayOf("todos"), key = "#root.args[0]")
    fun findByUid(uid: String): Todo?
}