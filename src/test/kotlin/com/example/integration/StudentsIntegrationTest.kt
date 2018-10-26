package com.example.integration

import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate

class StudentsIntegrationTest: BaseIntegrationTest() {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun findAll() {

    }

    @Test
    fun findById() {

    }

    @Test
    fun save() {

    }


    @Test
    fun update() {

    }

    @Test
    fun delete() {

    }
}