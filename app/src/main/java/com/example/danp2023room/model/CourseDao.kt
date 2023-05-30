package com.example.danp2023room.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.CourseStudentCrossRef
import com.example.danp2023room.entities.CourseWithStudents

@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    suspend fun getAll(): List<CourseEntity>

    @Insert
    suspend fun insert(coursesEntity: List<CourseEntity>)

    @Insert
    suspend fun insert(courseEntity: CourseEntity)

    @Insert
    suspend fun insertStudenCourse(courseStudentCrossRef: CourseStudentCrossRef)

    @Transaction
    @Query("SELECT * FROM course")
    suspend fun getCourseWithStudents(): List<CourseWithStudents>
}