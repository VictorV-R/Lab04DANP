package com.example.danp2023room.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.danp2023room.entities.BookEntity
import com.example.danp2023room.entities.CourseEntity
import com.example.danp2023room.entities.CourseStudentCrossRef
import com.example.danp2023room.entities.StudentEntity


@Database(
    entities = [StudentEntity::class, BookEntity::class, CourseEntity::class, CourseStudentCrossRef::class],
    version = 10
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun bookDao(): BookDao
    abstract fun courseDao(): CourseDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-name"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}