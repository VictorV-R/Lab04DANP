package com.example.danp2023room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey
    val courseId: Int,
    val courseName: String,
) {
}