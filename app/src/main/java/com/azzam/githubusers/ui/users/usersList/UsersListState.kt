package com.azzam.githubusers.ui.users.usersList

import com.azzam.githubusers.domain.model.GithubUser

data class UsersListState(
    val users: List<GithubUser?>? = null,
    val isLoading: Boolean = false,
)
