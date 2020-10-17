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
}