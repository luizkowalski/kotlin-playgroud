package com.example.kotlindemo.controllers.advicers

import com.example.kotlindemo.exceptions.TodoNotFound
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionAdvicers {

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun missingParam(e: MissingServletRequestParameterException) = mapOf(
            "error" to "missing required parameter",
            "details" to e.message.orEmpty())

    @ExceptionHandler(TodoNotFound::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun todoNotFound(e: TodoNotFound) = mapOf(
            "error" to "TODO not found",
            "details" to e.message.orEmpty()
    )
}