package com.example.domain.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class StudentRequest(
        @NotBlank
        val name: String,

        @NotNull
        @Positive
        val year: Int
)
