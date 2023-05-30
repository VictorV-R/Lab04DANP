package com.example.danp2023room.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.danp2023room.entities.BookEntity
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.CourseStudentCrossRef
import com.example.danp2023room.entities.StudentEntity
import com.example.danp2023room.model.AppDatabase
import com.example.danp2023room.model.Repository
import com.example.danp2023room.navigation.NavGraph
import com.example.danp2023room.ui.theme.DANP2023RoomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivity : ComponentActivity(
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DANP2023RoomTheme {
                NavGraph(navController = rememberNavController())
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RoomSample(
    navigateToListaEstudiantesPorCurso: () -> Unit,
) {
    val TAG = "RoomDatabase"
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val repository = Repository(AppDatabase.getInstance(context.applicationContext))
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val fillDataOnClick = {
                fillTables(repository, scope)
            }

            val studentsOnClick: () -> Unit = {
                scope.launch {
                    repository.getAllStudents().forEach {
                        Log.d(TAG, it.toString())
                    }
                }
            }

            val booksOnClick: () -> Unit = {
                scope.launch {
                    repository.getAllBooks().forEach {
                        Log.d(TAG, it.toString())
                    }
                }
            }

            val studentWithBooksOnClick: () -> Unit = {
                scope.launch {
                    repository.getStudentWithBooks().forEach {
                        Log.d(TAG, it.toString())
                    }
                }
            }

            val coursesWithStudents: () -> Unit = {
                scope.launch {
                    repository.getCoursesWithStudents().forEach() {
                        Log.d(TAG, it.toString())
                    }
                }
            }

            val courses: () -> Unit = {
                scope.launch {
                    repository.getCourses().forEach() {
                        Log.d(TAG, it.toString())
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = fillDataOnClick) {
                Text(text = "Fill student & book tables")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = studentsOnClick) {
                Text(text = "Show students")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = booksOnClick) {
                Text(text = "Show books")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = coursesWithStudents) {
                Text(text = "Show courses with students")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = courses) {
                Text(text = "Show courses")
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = studentWithBooksOnClick) {
                Text(text = "Student With Books")
            }

        }
    }
}

fun fillTables(rep: Repository, scope: CoroutineScope) {

    val students = ArrayList<StudentEntity>()
    val courses = ArrayList<CourseEntity>()

    for (i in 100..120) {
        val studentEntity = StudentEntity(i, fullname = "Student " + i.toString())
        students.add(studentEntity)
    }

    for (i in 1..5) {
        val courseEntity = CourseEntity(i, courseName = "Course " + i.toString())
        courses.add(courseEntity)
    }

    scope.launch {
        rep.insertCourses(courses)
    }

    scope.launch {
        rep.insertStudents(students)
    }

    // agregar cursos a alumnos
    for (i in 1..5) {
        for (j in 100..120) {
            val courseStudentCrossRef = CourseStudentCrossRef(i, j)
            val studentId = Random.nextInt(0, 10)
            if(studentId % 2 == 0){
                scope.launch {
                    rep.insertCoursesStudents(courseStudentCrossRef)
                }
            }
        }
    }

    for (i in 0..20) {
        val studentId = Random.nextInt(100, 120)
        val bookEntity = BookEntity(name = "Book " + i.toString(), studentId)
        scope.launch {
            rep.insertBook(bookEntity)
        }
    }
}

