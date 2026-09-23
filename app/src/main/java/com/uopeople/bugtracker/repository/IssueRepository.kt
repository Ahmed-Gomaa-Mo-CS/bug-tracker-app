package com.uopeople.bugtracker.repository

import com.uopeople.bugtracker.data.Issue
import com.uopeople.bugtracker.data.IssueDao
import com.uopeople.bugtracker.data.IssueApi

class IssueRepository(
    private val dao: IssueDao,
    private val api: IssueApi
) {


    suspend fun insertIssue(issue: Issue) {
        dao.insert(issue)
    }


    suspend fun syncIssues() {
        val pending = dao.getPendingSync()

        for (issue in pending) {
            try {
                val response = api.createIssue(issue)

                if (response.isSuccessful) {

                    dao.update(issue.copy(isSynced = true))
                }
            } catch (e: Exception) {

            }
        }
    }


    suspend fun refreshFromServer() {
        try {
            val remote = api.getIssues()
            remote.forEach { dao.insert(it) }
        } catch (e:Exception) {

        }
    }
}
