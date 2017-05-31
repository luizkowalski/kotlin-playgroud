package com.example.kotlindemo.controllers

import com.example.kotlindemo.models.Status
import com.example.kotlindemo.models.Todo
import com.example.kotlindemo.repositories.TodosRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodosController(val todosRepository: TodosRepository) : BaseController() {

    @GetMapping
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

    @GetMapping("{uid}")
    fun findByUid(@PathVariable("uid") uid: String): Todo? {
        val todo = todosRepository.findByUid(uid) ?: throw RuntimeException("TODO not found")
        return todo
    }

    @PostMapping()
    fun create(@RequestParam("name") name: String,
               @RequestParam("status", required = false) status: Int?): Todo {
        val t = Todo()
        t.status = Status.fromCode(status ?: 0)
        t.task = name

        return todosRepository.save(t)
    }

    @PostMapping("/{uid}/done")
    fun markAsDone(@PathVariable("uid") uid: String): ResponseEntity<Map<String, String>> {
        var todo = todosRepository.findByUid(uid) ?: return ResponseEntity(notFound(), HttpStatus.NOT_FOUND)
        todo.finish()
        todosRepository.save(todo)
        return ResponseEntity.ok(success())
    }
}