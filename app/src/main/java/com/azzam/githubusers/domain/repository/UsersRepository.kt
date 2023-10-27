package com.azzam.githubusers.domain.repository

import com.azzam.githubusers.domain.model.GithubUser
import com.azzam.githubusers.utils.Resource
import kotlinx.coroutines.flow.Flow


interface UsersRepository {
    fun searchUsers(keyword: String?): Flow<Resource<List<GithubUser?>?>>
    fun getUser(username: String?): Flow<Resource<GithubUser?>>
}