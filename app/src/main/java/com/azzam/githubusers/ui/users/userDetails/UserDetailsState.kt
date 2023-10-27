package com.azzam.githubusers.ui.users.userDetails

import com.azzam.githubusers.domain.model.GithubUser

data class UserDetailsState(
    val user: GithubUser? = null,
    val loading: Boolean = false
)