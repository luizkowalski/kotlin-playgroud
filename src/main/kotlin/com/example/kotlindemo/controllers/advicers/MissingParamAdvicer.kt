package com.example.kotlindemo.controllers.advicers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class MissingParamAdvicer {

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun missingParam(e: MissingServletRequestParameterException): Map<String, String> {
        return mapOf(
                "error" to "missing required parameter",
                "message" to e.message.orEmpty())
    }
}