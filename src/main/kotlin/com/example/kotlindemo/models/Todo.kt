package com.example.kotlindemo.models

import com.example.kotlindemo.utils.UIDGenerator
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "todos", indexes = arrayOf(
        Index(name = "uid_index_on_todos", columnList = "uid", unique = true)
))
@SequenceGenerator(sequenceName = "todo_seq", name = "todo_seq", allocationSize = 10)
data class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq")
        @JsonIgnore
        var id: Long? = null,

        @Column(nullable = false)
        @NotNull
        var task: String = "",

        @Column(nullable = false)
        var status: Status = Status.NOT_DONE,

        @Column(nullable = false)
        var uid: String? = null
) {
    @PrePersist
    fun prePersist() {
        uid = uid ?: UIDGenerator.newUid()
    }

    fun finish() {
        status = Status.DONE
    }
}