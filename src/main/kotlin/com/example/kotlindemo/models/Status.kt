package com.example.kotlindemo.models

enum class Status(val code: Int) {
    NOT_DONE(0),
    DONE(1);

    companion object {
        fun fromCode(code: Int): Status {
            return Status.values().filter { it.code == code }[0]
        }
    }
}