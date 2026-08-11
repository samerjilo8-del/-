package com.talp.smartteacher.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferences
import androidx.datastore.preferences.core.stringPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {
    companion object {
        private val STUDENT_CODE_KEY = stringPreferences("student_code")
        private val STUDENT_NAME_KEY = stringPreferences("student_name")
        private val TEACHER_ID_KEY = intPreferences("teacher_id")
        private val USER_TYPE_KEY = stringPreferences("user_type") // "teacher" or "student"
    }

    suspend fun saveStudentCode(code: String) {
        context.dataStore.edit { prefs ->
            prefs[STUDENT_CODE_KEY] = code
        }
    }

    suspend fun saveStudentName(name: String) {
        context.dataStore.edit { prefs ->
            prefs[STUDENT_NAME_KEY] = name
        }
    }

    suspend fun saveTeacherId(id: Int) {
        context.dataStore.edit { prefs ->
            prefs[TEACHER_ID_KEY] = id
        }
    }

    suspend fun saveUserType(type: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_TYPE_KEY] = type
        }
    }

    fun getStudentCode(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[STUDENT_CODE_KEY]
        }
    }

    fun getStudentName(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[STUDENT_NAME_KEY]
        }
    }

    fun getTeacherId(): Flow<Int?> {
        return context.dataStore.data.map { prefs ->
            prefs[TEACHER_ID_KEY]
        }
    }

    fun getUserType(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[USER_TYPE_KEY]
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
