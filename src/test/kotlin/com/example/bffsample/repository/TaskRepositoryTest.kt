package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.Task
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate
import java.util.*

@RestClientTest(TaskRepository::class)
internal class TaskRepositoryTest {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var mockServer: MockRestServiceServer

    @Test
    fun getTask() {
        mockServer.expect(requestTo("http://localhost:50000/tasks/12345"))
                .andRespond(withSuccess(Task(
                        12345,
                        "タスクのタイトル",
                        "タスクの詳細説明",
                        Date()).toString(), MediaType.APPLICATION_JSON))
    }
}