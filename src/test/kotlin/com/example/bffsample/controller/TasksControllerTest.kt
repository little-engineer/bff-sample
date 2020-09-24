package com.example.bffsample.controller

import com.example.bffsample.model.externalapi.Task
import com.example.bffsample.repository.TaskRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest
@DisplayName("TasksController")
internal class TasksControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var mockTaskRepository: TaskRepository

    @Nested
    @DisplayName("getTask")
    inner class GetTask {

        @DisplayName("should return task data when get task api is called with task id.")
        @Test
        fun getTask() {
            given(mockTaskRepository.getTask(12345))
                    .willReturn(Task(
                            123456,
                            "タスクのタイトル",
                            "タスクの詳細説明",
                            Date())
                    )

            mockMvc.perform(get("/tasks/12345"))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("taskId").value(12345))
                    .andExpect(jsonPath("title").value("タスクのタイトル"))
                    .andExpect(jsonPath("description").value("タスクの詳細説明"))
                    .andExpect(jsonPath("userName").value("タスク担当者A"))
        }
    }
}
