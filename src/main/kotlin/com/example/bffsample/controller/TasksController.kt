package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.repository.TaskRepository
import com.example.bffsample.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TasksController @Autowired constructor(
        val taskRepository: TaskRepository,
        val userRepository: UserRepository
){

    @GetMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getTask(@PathVariable("taskId") taskId: Int): Task {

        val task = taskRepository.getTask(taskId)
        println(task)

        val user = userRepository.getUser(1)
        println(user)

        return Task(
                taskId = taskId,
                title = "タスクのタイトル",
                description = "タスクの詳細説明",
                userName = "タスク担当者A")
    }
}
