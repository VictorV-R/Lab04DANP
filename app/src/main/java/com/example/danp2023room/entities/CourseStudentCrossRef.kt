package com.example.danp2023room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["courseId", "studentId"])
data class CourseStudentCrossRef(
    val courseId: Int,
    val studentId: Int
){

}
