package com.azzam.githubusers.data.api.dto


import com.google.gson.annotations.SerializedName

data class SearchGithubUsersDto(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<GithubUserDto?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)