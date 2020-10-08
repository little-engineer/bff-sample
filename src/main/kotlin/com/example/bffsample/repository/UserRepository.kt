package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Repository
class UserRepository(restTemplateBuilder: RestTemplateBuilder) {
    @Value("\${external.user.url}")
    val userApiUrl:String = ""

    private var restTemplate: RestTemplate? = null

    init {
        restTemplate = restTemplateBuilder.build()
    }

    fun getUser(userId: Int): User? {
        val uri = "$userApiUrl/users/$userId"

        return restTemplate?.getForObject(uri, User::class)
    }
}
