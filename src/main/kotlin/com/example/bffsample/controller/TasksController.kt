package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TasksController @Autowired constructor(val taskRepository: TaskRepository){

    @GetMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getTask(@PathVariable("taskId") taskId: Int): Task {

        val task = taskRepository.getTask(taskId)
        println(task)

        return Task(
                taskId = taskId,
                title = "タスクのタイトル",
                description = "タスクの詳細説明",
                userName = "タスク担当者A")
    }
}
