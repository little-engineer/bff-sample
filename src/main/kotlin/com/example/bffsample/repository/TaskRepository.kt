package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Repository
class TaskRepository(restTemplateBuilder: RestTemplateBuilder) {

    private var restTemplate: RestTemplate? = null

    init {
        restTemplate = restTemplateBuilder.build()
    }

    fun getTask(taskId: Int): Task? {
        val uri = "http://localhost:50000/tasks/$taskId"

        return restTemplate?.getForObject(uri, Task::class)
    }
}