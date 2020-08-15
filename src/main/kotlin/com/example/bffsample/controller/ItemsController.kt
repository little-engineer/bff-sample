package com.example.bffsample.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/items")
class ItemListController {

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    fun getItem(@PathVariable("id") id: Int): String {
        return "item id = $id"
    }
}
