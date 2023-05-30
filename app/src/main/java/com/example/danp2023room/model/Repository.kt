package com.example.danp2023room.model

import com.example.danp2023room.entities.BookEntity
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.CourseStudentCrossRef
import com.example.danp2023room.entities.CourseWithStudents
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.entities.StudentWithBooks

class Repository(private val appDatabase: AppDatabase) {

    suspend fun getAllStudents(): List<StudentEntity> {
        return appDatabase.studentDao().getAll()
    }

    suspend fun getStudentWithBooks(): List<StudentWithBooks> {
        return appDatabase.studentDao().getStudentWithBooks()
    }

    suspend fun insertStudents(studentsEntity: List<StudentEntity>) {
        appDatabase.studentDao().insert(studentsEntity)
    }

    suspend fun insertBooks(books: List<BookEntity>) {
        appDatabase.bookDao().insert(books)
    }

    suspend fun insertBook(book: BookEntity) {
        appDatabase.bookDao().insert(book)
    }

    suspend fun getAllBooks(): List<BookEntity> {
        return appDatabase.bookDao().getAll()
    }

    suspend fun insertCourses(coursesEntity: List<CourseEntity>){
        return appDatabase.courseDao().insert(coursesEntity)
    }

    suspend fun insertCoursesStudents(courseStudentCrossRef: CourseStudentCrossRef){
        return appDatabase.courseDao().insertStudenCourse(courseStudentCrossRef)
    }

    suspend fun getCoursesWithStudents(): List<CourseWithStudents>{
        return appDatabase.courseDao().getCourseWithStudents()
    }

    suspend fun getCourses(): List<CourseEntity>{
        return appDatabase.courseDao().getAll()
    }
}