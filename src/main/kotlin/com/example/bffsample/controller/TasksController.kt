package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.service.TaskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TasksController @Autowired constructor(
        val taskService: TaskService
){

    @GetMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getTask(@PathVariable("taskId") taskId: Int): Task {
        return taskService.getTask(taskId)
    }
}
