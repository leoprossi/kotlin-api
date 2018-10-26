package com.example.Example.service

import com.example.Example.domain.Student
import com.example.Example.domain.request.StudentRequest
import com.example.Example.mapper.StudentMapper
import com.example.Example.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


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