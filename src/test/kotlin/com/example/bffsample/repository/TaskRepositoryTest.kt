package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.Task
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import java.util.*

@RestClientTest(TaskRepository::class)
internal class TaskRepositoryTest {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var mockServer: MockRestServiceServer

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun getTask() {
        val task = Task(
                12345,
                "タスクのタイトル",
                "タスクの詳細説明",
                Date())

        mockServer.expect(requestTo("http://localhost:50000/tasks/12345"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(task), MediaType.APPLICATION_JSON))

        this.taskRepository.getTask(12345)

        println(task.toString())
        println(objectMapper.writeValueAsString(task))
    }
}
