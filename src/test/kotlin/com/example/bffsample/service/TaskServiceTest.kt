package com.example.bffsample.service

import com.example.bffsample.model.externalapi.Task
import com.example.bffsample.model.externalapi.User
import com.example.bffsample.repository.TaskRepository
import com.example.bffsample.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*

@SpringBootTest
@DisplayName("TasksService")
internal class TaskServiceTest {
    @Autowired
    lateinit var taskService: TaskService

    @MockBean
    lateinit var mockTaskRepository: TaskRepository

    @MockBean
    lateinit var mockUserRepository: UserRepository

    @Nested
    @DisplayName("getTask")
    inner class GetTask {

        @DisplayName("should return task data with user information when getTask function is called with task id.")
        @Test
        fun getTask() {
            given(mockTaskRepository.getTask(12345))
                    .willReturn(Task(
                            12345,
                            "タスクのタイトル",
                            "タスクの詳細説明",
                            Date())
                    )

            given(mockUserRepository.getUser(1))
                    .willReturn(User(
                            1,
                            "タスクの担当者A",
                            Date())
                    )

            val actual = taskService.getTask(12345)

            assertEquals(12345, actual.taskId)
            assertEquals("タスクのタイトル", actual.title)
            assertEquals("タスクの詳細説明", actual.description)
            assertEquals("タスクの担当者A", actual.userName)
        }
    }
}
