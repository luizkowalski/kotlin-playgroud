package com.example.kotlindemo.utils

import java.util.*

object UIDGenerator {
    fun newUid(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }
}