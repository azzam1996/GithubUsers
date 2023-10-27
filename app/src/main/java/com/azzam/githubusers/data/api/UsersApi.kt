package com.azzam.githubusers.data.api

import com.azzam.githubusers.data.api.dto.GithubUserDto
import com.azzam.githubusers.data.api.dto.SearchGithubUsersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApi {

    @GET("/search/users")
    suspend fun searchUsers(@Query("q") keyword: String?): Response<SearchGithubUsersDto?>

    @GET("/users/{username}")
    suspend fun getUser(@Path("username") username: String?): Response<GithubUserDto?>
}