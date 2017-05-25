package com.example.kotlindemo.controllers

import com.example.kotlindemo.models.Status
import com.example.kotlindemo.models.Todo
import com.example.kotlindemo.repositories.TodosRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.websocket.server.PathParam

@RestController
class TodosController(val todosRepository: TodosRepository) : BaseController() {

    @GetMapping("/")
    fun findAll() = todosRepository.findAll()

    /**
     * Yes, I could query by status, but I want to use Kotlin idiom
     */
    @GetMapping("/not_done")
    fun findAllNotDone(): List<Todo> {
        return todosRepository.findAll().filter { it.status == Status.NOT_DONE }
    }

    @GetMapping("/done")
    fun findAllDone(): List<Todo> {
        return todosRepository.findAll().filter { it.status == Status.DONE }
    }

    @GetMapping("/by_uid")
    fun findByUid(@PathParam("uid") uid: String) : Todo? {
        return todosRepository.findByUid(uid)
    }

    @PostMapping("/")
    fun create(@PathParam("name") name: String,
               @PathParam("status") status: Int?): Todo {
        val t = Todo()
        t.status = Status.fromCode(status ?: 0)
        t.task = name

        return todosRepository.save(t)
    }

    @PostMapping("mark_as_done")
    fun markAsDone(@PathParam("uid") uid: String): ResponseEntity<Map<String, String>> {
        val todo = todosRepository.findByUid(uid) ?: return ResponseEntity(notFound(), HttpStatus.NOT_FOUND)
        todo.done()
        todosRepository.save(todo)
        return ResponseEntity.ok(success())
    }
}