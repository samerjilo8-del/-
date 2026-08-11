package com.talp.smartteacher

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.talp.smartteacher.database.TalpDatabase
import com.talp.smartteacher.data.repository.ExamRepository
import com.talp.smartteacher.data.repository.GradeRepository
import com.talp.smartteacher.data.repository.HomeworkRepository
import com.talp.smartteacher.data.repository.NoteRepository
import com.talp.smartteacher.data.repository.ScheduleRepository
import com.talp.smartteacher.data.repository.StudentRepository
import com.talp.smartteacher.data.repository.SubjectRepository
import com.talp.smartteacher.data.repository.TeacherRepository
import com.talp.smartteacher.utils.DataStoreManager

class TalpApplication : Application() {
    companion object {
        private lateinit var instance: TalpApplication
        private lateinit var database: TalpDatabase
        private lateinit var dataStoreManager: DataStoreManager

        fun getInstance(): TalpApplication = instance
        fun getDatabase(): TalpDatabase = database
        fun getDataStoreManager(): DataStoreManager = dataStoreManager

        // Repositories
        fun getTeacherRepository(): TeacherRepository = TeacherRepository(database.teacherDao())
        fun getStudentRepository(): StudentRepository = StudentRepository(database.studentDao())
        fun getSubjectRepository(): SubjectRepository = SubjectRepository(database.subjectDao())
        fun getHomeworkRepository(): HomeworkRepository = HomeworkRepository(database.homeworkDao())
        fun getGradeRepository(): GradeRepository = GradeRepository(database.gradeDao())
        fun getNoteRepository(): NoteRepository = NoteRepository(database.noteDao())
        fun getScheduleRepository(): ScheduleRepository = ScheduleRepository(database.scheduleDao())
        fun getExamRepository(): ExamRepository = ExamRepository(database.examDao())
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Initialize database
        database = Room.databaseBuilder(
            applicationContext,
            TalpDatabase::class.java,
            TalpDatabase.DATABASE_NAME
        ).build()

        // Initialize DataStore
        dataStoreManager = DataStoreManager(this)
    }
}
