package com.example.integration

import com.example.util.CleanupDatabase
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/*
    Here's another example of a java way of injecting dependencies with Spring's
    @Autowired. The lazeinit statement indicates that a non-nullable
    value can be injected later without violating the 'not-null object's principle
    of the Kotlin language.
 */
@SpringBootTest
abstract class BaseIntegrationTest {

    @Autowired
    lateinit var clean: CleanupDatabase

    @Before
    fun setup() {
        clean.clean()
    }
}