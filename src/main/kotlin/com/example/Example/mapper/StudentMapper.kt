package com.example.Example.mapper

import com.example.Example.domain.Student
import com.example.Example.domain.request.StudentRequest

class StudentMapper {
    companion object {
        fun requestToEntity(studentRequest: StudentRequest): Student = Student(
                0,
                studentRequest.name,
                studentRequest.year
        )
    }
}