package com.example.bffsample.model.externalapi

import java.util.*

data class Task(
    val taskId: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val created: Date? = null
)