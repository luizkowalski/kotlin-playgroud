package com.example.kotlindemo.controllers

open class BaseController {

    fun notFound(): Map<String, String> {
        return mapOf("error" to "record not found")
    }

    fun success(): Map<String, String> {
        return mapOf("message" to "success")
    }

}