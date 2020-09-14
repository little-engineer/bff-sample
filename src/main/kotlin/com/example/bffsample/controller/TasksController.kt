package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TasksController {

    @GetMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getItem(@PathVariable("taskId") taskId: Int): Task {
        return Task(
                taskId = taskId,
                title = "タスクのタイトル",
                description = "タスクの詳細説明",
                userName = "タスク担当者A")
    }
}
