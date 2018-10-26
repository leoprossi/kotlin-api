package com.example.domain

import javax.persistence.*

@Entity
class Student (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @Column(nullable = false, length = 60)
        var name: String,

        var year: Int
)
