package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.Task
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import java.util.*

@RestClientTest(TaskRepository::class)
@DisplayName("TaskRepository")
internal class TaskRepositoryTest {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var mockServer: MockRestServiceServer

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Nested
    @DisplayName("getTask")
    inner class GetTask {

        @Test
        @DisplayName("should return task data when taskRepository call to GET task to external Task-api with task id.")
        fun getTask() {
            val task = Task(12345, "タスクのタイトル", "タスクの詳細説明", Date())
            mockServer.expect(requestTo("http://localhost:50000/tasks/12345"))
                    .andRespond(withSuccess(objectMapper.writeValueAsString(task), MediaType.APPLICATION_JSON))

            val actual = taskRepository.getTask(12345) ?: Task()

            assertEquals(12345, actual.taskId)
            assertEquals("タスクのタイトル", actual.title)
            assertEquals("タスクの詳細説明", actual.description)
            assertNotNull(actual.created)
        }
    }
}
