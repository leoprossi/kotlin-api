package com.example.service

import com.example.domain.Student
import com.example.domain.request.StudentRequest
import com.example.mapper.StudentMapper
import com.example.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/*
    It seems like private unchangable properties are automatically
    injected as a autowired dependencies. Although, you can find an
    exapmle on how to inject dependencies in a more 'java' way on
    the integration test classes.
 */
@Service
class Studentservice(private val studentRepository: StudentRepository) {

    @Transactional
    fun save(studentRequest: StudentRequest): Long {
        var student = StudentMapper.requestToEntity(studentRequest)
        return studentRepository.save(student).id
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Student> = studentRepository.findAll()

    @Transactional(readOnly = true)
    fun findById(id: Long): Student = studentRepository.findById(id)
            .orElseThrow { NoSuchElementException() }

    @Transactional
    fun update(id: Long, studentRequest: StudentRequest) {
        var student = StudentMapper.requestToEntity(studentRequest)
        student.id = id
        studentRepository.save(student)
    }

    @Transactional
    fun delete(id: Long) {
        studentRepository.deleteById(id)
    }
}