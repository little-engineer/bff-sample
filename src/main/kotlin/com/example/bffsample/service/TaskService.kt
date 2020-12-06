package com.example.bffsample.service

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.repository.TaskRepository
import com.example.bffsample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

const val UNKNOWN_USER_NAME: String = "unknown"

@Service
class TaskService @Autowired constructor(
        val taskRepository: TaskRepository,
        val userRepository: UserRepository) {

    fun getTask(taskId: Int): Task {
        val task = taskRepository.getTask(taskId)
        val user = userRepository.getUser(1)

        return Task(
                taskId = task?.taskId ?: 0,
                title = task?.title ?: "",
                description = task?.description ?: "",
                userName = user?.userName ?: UNKNOWN_USER_NAME)
    }

    fun createTask(task: Task): Task {
        val postTask = taskRepository.postTask(
                com.example.bffsample.model.externalapi.Task(
                        title = task.title,
                        description = task.description
                )
        )

        return Task(
                taskId = postTask?.taskId ?: 0,
                title = postTask?.title ?: "",
                description = postTask?.description ?: "",
                userName = UNKNOWN_USER_NAME)
    }

    fun updateTask(taskId: Int, task: Task): Task {
        val putTask = taskRepository.putTask(
                taskId,
                com.example.bffsample.model.externalapi.Task(
                        title = task.title,
                        description = task.description
                )
        )

        val user = userRepository.getUser(taskId)

        return Task(
                taskId = putTask?.taskId ?: 0,
                title = putTask?.title ?: "",
                description = putTask?.description ?: "",
                userName = user?.userName)
    }

    fun deleteTask(taskId: Int) {
        taskRepository.deleteTask(taskId)
    }
}