package com.example.mapper

import com.example.domain.Student
import com.example.domain.request.StudentRequest

class StudentMapper {
    companion object {
        fun requestToEntity(studentRequest: StudentRequest): Student = Student(
                0,
                studentRequest.name,
                studentRequest.year
        )
    }
}