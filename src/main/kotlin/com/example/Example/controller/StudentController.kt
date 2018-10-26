package com.example.Example.controller

import com.example.Example.domain.Student
import com.example.Example.domain.request.StudentRequest
import com.example.Example.service.Studentservice
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/students")
class SudentController(private val service: Studentservice) {

    @PostMapping
    @ApiOperation("Registers a new Student on the system and persists it on the database.")
    fun save(@Valid @RequestBody student: StudentRequest): ResponseEntity<Void> {
        var id = service.save(student)
        return ResponseEntity.created(URI.create("/students/$id")).build()
    }

    @GetMapping
    @ApiOperation("Returns a list containing all the registered Students.")
    fun findAll(): List<Student> = service.findAll()

    @GetMapping("/{id}")
    @ApiOperation("Returns the Student identified by the number passed at the end of the URL.")
    fun findById(@PathVariable id: Long): Student = service.findById(id)

    @PutMapping("/{id}")
    @ApiOperation("Completely replaces the data from the Student identified by the id passed at the end of the URL with those from the Student passed on the request's body.")
    fun update(@PathVariable id: Long, @Valid @RequestBody student: StudentRequest): ResponseEntity<Void> {
        service.update(id, student)
        return ResponseEntity.status(204).build()
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes the Student identified by the id passed at the end of the URL.")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id);
        return ResponseEntity.status(204).build()
    }
}
