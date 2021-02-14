package com.example.bffsample.repository

import com.example.bffsample.model.externalapi.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.response.MockRestResponseCreators
import java.util.Date

@RestClientTest(UserRepository::class)
@DisplayName("UserRepository")
internal class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var mockServer: MockRestServiceServer

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Nested
    @DisplayName("getUser")
    inner class GetTask {

        @Test
        @DisplayName("should return user data when userRepository call to GET user to external Task-api with user id.")
        fun getUser() {
            val user = User(1, "タスクの管理者A", Date())
            mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:50001/users/1"))
                .andRespond(MockRestResponseCreators.withSuccess(objectMapper.writeValueAsString(user), MediaType.APPLICATION_JSON))

            val actual = userRepository.getUser(1) ?: User()

            assertEquals(1, actual.userId)
            assertEquals("タスクの管理者A", actual.userName)
            assertNotNull(actual.created)
        }
    }
}
