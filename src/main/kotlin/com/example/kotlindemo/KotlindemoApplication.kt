package com.example.kotlindemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.CacheManager
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class KotlindemoApplication
    fun main(args: Array<String>) {
        SpringApplication.run(KotlindemoApplication::class.java, *args)
    }