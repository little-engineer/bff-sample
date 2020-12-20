package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.Task
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForObject
import org.springframework.web.client.postForObject
import java.time.Duration

@Repository
class TaskRepository(
        restTemplateBuilder: RestTemplateBuilder,
        @Value("\${external.task.url}") private val taskApiUrl: String = "",
        @Value("\${external.task.connect-timeout-millis}") private val connectTimeoutMillis: Long,
        @Value("\${external.task.read-timeout-millis}") private val readTimeoutMillis: Long
) {
    private var restTemplate: RestTemplate? = null

    init {
        restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(connectTimeoutMillis))
                .setReadTimeout(Duration.ofMillis(readTimeoutMillis))
                .build()
    }

    fun getTask(taskId: Int): Task? {
        val uri = "$taskApiUrl/tasks/$taskId"

        return restTemplate?.getForObject(uri, Task::class)
    }

    fun postTask(task: Task): Task? {
        val uri = "$taskApiUrl/tasks"

        return restTemplate?.postForObject(uri, task, Task::class)
    }

    fun putTask(taskId: Int, task: Task): Task? {
        val uri = "$taskApiUrl/tasks/$taskId"

        val requestEntity = HttpEntity<Task>(task)
        val responseEntity: ResponseEntity<Task>? = restTemplate?.exchange(
                uri, HttpMethod.PUT, requestEntity, Task::class)

        return responseEntity?.body
    }

    fun deleteTask(taskId: Int) {
        val uri = "$taskApiUrl/tasks/$taskId"
        restTemplate?.delete(uri)
    }
}
