package com.example.bffsample.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@DisplayName("ItemsController")
internal class ItemsControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getItem")
    inner class GetItem {

        @DisplayName("should return id which is input to url.")
        @Test
        fun getItem() {
            mockMvc.perform(get("/items/12345"))
                    .andExpect(status().isOk)
                    .andExpect(content().string("item id = 12345"))
        }
    }
}