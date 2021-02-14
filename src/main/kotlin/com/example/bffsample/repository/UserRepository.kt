package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.User
import org.apache.http.conn.ConnectTimeoutException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.time.Duration

@Repository
class UserRepository(
    restTemplateBuilder: RestTemplateBuilder,
    @Value("\${external.user.url}") private val userApiUrl: String = "",
    @Value("\${external.user.connect-timeout-millis}") private val connectTimeoutMillis: Long,
    @Value("\${external.user.read-timeout-millis}") private val readTimeoutMillis: Long
) {

    private var restTemplate: RestTemplate? = null

    init {
        restTemplate = restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(connectTimeoutMillis))
            .setReadTimeout(Duration.ofMillis(readTimeoutMillis))
            .build()
    }

    @Retryable(
        value = [ConnectTimeoutException::class],
        maxAttemptsExpression = "#{\${external.user.connect-retry-attempts}}",
        backoff = Backoff(delayExpression = "#{\${external.user.connect-retry-interval-millis}}")
    )
    fun getUser(userId: Int): User? {
        val uri = "$userApiUrl/users/$userId"

        return restTemplate?.getForObject(uri, User::class)
    }
}
