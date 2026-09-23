package com.uopeople.bugtracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uopeople.bugtracker.repository.IssueRepository

class IssueViewModelFactory(private val repository: IssueRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IssueViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IssueViewModel(repository, androidx.lifecycle.SavedStateHandle()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
