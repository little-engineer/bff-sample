package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.service.TaskService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TasksController @Autowired constructor(
        val taskService: TaskService
) {
    private val log = LogFactory.getLog(TasksController::class.java)

    @GetMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getTask(@PathVariable("taskId") taskId: Int): Task {
        log.info("getTask is called with taskId($taskId).")

        return taskService.getTask(taskId)
    }

    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task {
        return taskService.createTask(task)
    }

    @PutMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun updateTask(
            @PathVariable("taskId") taskId: Int,
            @RequestBody task: Task): Task {
        return taskService.updateTask(taskId, task)
    }
}
