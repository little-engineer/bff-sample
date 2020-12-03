package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.service.TaskService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
@DisplayName("TasksController")
internal class TasksControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @MockBean
    lateinit var mockTaskService: TaskService

    @Nested
    @DisplayName("getTask")
    inner class GetTask {

        @DisplayName("should return task data when get task api is called with task id.")
        @Test
        fun getTask() {
            given(mockTaskService.getTask(12345))
                    .willReturn(Task(
                            12345,
                            "タスクのタイトル",
                            "タスクの詳細説明",
                            "タスク担当者A")
                    )

            mockMvc.perform(get("/tasks/12345"))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("taskId").value(12345))
                    .andExpect(jsonPath("title").value("タスクのタイトル"))
                    .andExpect(jsonPath("description").value("タスクの詳細説明"))
                    .andExpect(jsonPath("userName").value("タスク担当者A"))
        }
    }

    @Nested
    @DisplayName("createTask")
    inner class CreateTask {

        @DisplayName("should return task data with new taskId and default user when create task api is called.")
        @Test
        fun createTask() {
            given(mockTaskService.createTask(Task(taskId = null, title = "タスクのタイトル", description = "タスクの詳細説明", userName = null)))
                    .willReturn(Task(
                            12345,
                            "タスクのタイトル",
                            "タスクの詳細説明",
                            "unknown")
                    )

            val requestBody = Task(taskId = null, title = "タスクのタイトル", description = "タスクの詳細説明", userName = null)
            val requestBodyJson = mapper.writeValueAsString(requestBody);

            mockMvc.perform(
                    post("/tasks")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBodyJson))
                    .andExpect(status().isCreated)
                    .andExpect(jsonPath("taskId").value(12345))
                    .andExpect(jsonPath("title").value("タスクのタイトル"))
                    .andExpect(jsonPath("description").value("タスクの詳細説明"))
                    .andExpect(jsonPath("userName").value("unknown"))
        }
    }
}
