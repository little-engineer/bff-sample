package com.example.bffsample.controller

import com.example.bffsample.model.forfrontend.Task
import com.example.bffsample.service.TaskService
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping

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
        @RequestBody task: Task
    ): Task {
        return taskService.updateTask(taskId, task)
    }

    @DeleteMapping("/{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable("taskId") taskId: Int) {
        taskService.deleteTask(taskId)
    }
}
