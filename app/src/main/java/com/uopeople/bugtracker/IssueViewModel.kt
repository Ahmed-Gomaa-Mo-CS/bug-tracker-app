package com.uopeople.bugtracker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uopeople.bugtracker.data.Issue
import com.uopeople.bugtracker.repository.IssueRepository
import kotlinx.coroutines.launch

class IssueViewModel(
    private val repository: IssueRepository,
    private val state: SavedStateHandle
) : ViewModel() {

    fun submitIssue(issue: Issue) {

        state["pending_issue"] = issue

        viewModelScope.launch {

            repository.insertIssue(issue)


            repository.syncIssues()


            state.remove<Issue>("pending_issue")
        }
    }
}
