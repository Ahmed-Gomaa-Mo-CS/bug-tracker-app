package com.uopeople.bugtracker.data

import retrofit2.Response
import retrofit2.http.*

interface IssueApi {

    @POST("issues")
    suspend fun createIssue(@Body issue: Issue): Response<Issue>

    @GET("issues")
    suspend fun getIssues(): List<Issue>

    @PUT("issues/{id}")
    suspend fun updateIssue(
        @Path("id") id: Int,
        @Body issue: Issue
    ): Response<Issue>

    @DELETE("issues/{id}")
    suspend fun deleteIssue(@Path("id") id: Int): Response<Unit>
}
