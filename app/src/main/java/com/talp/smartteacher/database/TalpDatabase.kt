package com.talp.smartteacher.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.talp.smartteacher.database.dao.ExamDao
import com.talp.smartteacher.database.dao.GradeDao
import com.talp.smartteacher.database.dao.HomeworkDao
import com.talp.smartteacher.database.dao.NoteDao
import com.talp.smartteacher.database.dao.ScheduleDao
import com.talp.smartteacher.database.dao.StudentDao
import com.talp.smartteacher.database.dao.SubjectDao
import com.talp.smartteacher.database.dao.TeacherDao
import com.talp.smartteacher.database.entities.Exam
import com.talp.smartteacher.database.entities.Grade
import com.talp.smartteacher.database.entities.Homework
import com.talp.smartteacher.database.entities.Note
import com.talp.smartteacher.database.entities.Schedule
import com.talp.smartteacher.database.entities.Student
import com.talp.smartteacher.database.entities.Subject
import com.talp.smartteacher.database.entities.Teacher

@Database(
    entities = [
        Teacher::class,
        Student::class,
        Subject::class,
        Homework::class,
        Grade::class,
        Note::class,
        Schedule::class,
        Exam::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TalpDatabase : RoomDatabase() {
    abstract fun teacherDao(): TeacherDao
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun homeworkDao(): HomeworkDao
    abstract fun gradeDao(): GradeDao
    abstract fun noteDao(): NoteDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun examDao(): ExamDao

    companion object {
        const val DATABASE_NAME = "talp_database"
    }
}
