package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.SubjectRepository
import com.talp.smartteacher.database.entities.Subject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubjectViewModel(private val repository: SubjectRepository) : ViewModel() {
    private val _subjects = MutableStateFlow<List<Subject>>(emptyList())
    val subjects: StateFlow<List<Subject>> = _subjects.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadSubjectsByTeacher(teacherId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getSubjectsByTeacher(teacherId).collect { subjectList ->
                    _subjects.value = subjectList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch {
            try {
                repository.insertSubject(subject)
                loadSubjectsByTeacher(subject.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun updateSubject(subject: Subject) {
        viewModelScope.launch {
            try {
                repository.updateSubject(subject)
                loadSubjectsByTeacher(subject.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun deleteSubject(subject: Subject) {
        viewModelScope.launch {
            try {
                repository.deleteSubject(subject)
                loadSubjectsByTeacher(subject.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
