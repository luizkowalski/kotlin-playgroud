package com.example.kotlindemo.models

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "todos", indexes = arrayOf(
        Index(name = "uid_index_on_todos", columnList = "uid")
))
data class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        var id: Long? = null,

        @Column(nullable = false)
        @NotNull
        var task: String = "",

        @Column(nullable = false)
        var status: Int = 0,

        @Column(nullable = false)
        var uid: String = ""
) {
    @PrePersist
    fun prePersist() {
        if (uid.isNullOrBlank())
            uid = UUID.randomUUID().toString()
    }

    fun done(){
        status = 1;
    }
}