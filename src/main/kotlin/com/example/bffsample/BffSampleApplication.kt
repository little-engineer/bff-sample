package com.example.bffsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@EnableRetry
class BffSampleApplication

fun main(args: Array<String>) {
    runApplication<BffSampleApplication>(*args)
}
