package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CourseWithStudents(
    @Embedded val course: CourseEntity,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(CourseStudentCrossRef::class)
    )
    val students: List<StudentEntity>
)
