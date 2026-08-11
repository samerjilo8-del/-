package com.talp.smartteacher.navigation

sealed class TalpRoute(val route: String) {
    object LoginScreen : TalpRoute("login_screen")
    object TeacherDashboard : TalpRoute("teacher_dashboard")
    object StudentDashboard : TalpRoute("student_dashboard")
    object ManageStudents : TalpRoute("manage_students")
    object ManageSubjects : TalpRoute("manage_subjects")
    object Homework : TalpRoute("homework")
    object WeeklySchedule : TalpRoute("weekly_schedule")
    object Grades : TalpRoute("grades")
    object ExamSchedule : TalpRoute("exam_schedule")
    object Notes : TalpRoute("notes")
    object Settings : TalpRoute("settings")
    object StudentHomework : TalpRoute("student_homework")
    object StudentWeeklySchedule : TalpRoute("student_weekly_schedule")
    object StudentGrades : TalpRoute("student_grades")
    object StudentExamSchedule : TalpRoute("student_exam_schedule")
    object StudentNotes : TalpRoute("student_notes")
}
