package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.Task
import com.example.bffsample.model.externalapi.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Repository
class UserRepository(restTemplateBuilder: RestTemplateBuilder) {

    private var restTemplate: RestTemplate? = null

    init {
        restTemplate = restTemplateBuilder.build()
    }

    fun getUser(userId: Int): User? {
        val uri = "http://localhost:50001/users/$userId"

        return restTemplate?.getForObject(uri, User::class)
    }
}
