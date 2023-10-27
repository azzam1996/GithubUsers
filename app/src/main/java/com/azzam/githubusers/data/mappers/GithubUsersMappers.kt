package com.azzam.githubusers.data.mappers

import com.azzam.githubusers.data.api.dto.GithubUserDto
import com.azzam.githubusers.domain.model.GithubUser

fun GithubUserDto.toGithubUser() = GithubUser(
    avatarUrl = avatarUrl,
    bio = bio,
    blog = blog,
    company = company,
    createdAt = createdAt,
    email = email,
    eventsUrl = eventsUrl,
    followers = followers,
    followersUrl = followersUrl,
    following = following,
    followingUrl = followingUrl,
    gistsUrl = gistsUrl,
    gravatarId = gravatarId,
    hireable = hireable,
    htmlUrl = htmlUrl,
    id = id,
    location = location,
    login = login,
    name = name,
    nodeId = nodeId,
    organizationsUrl = organizationsUrl,
    publicGists = publicGists,
    publicRepos = publicRepos,
    receivedEventsUrl = receivedEventsUrl,
    reposUrl = reposUrl,
    siteAdmin = siteAdmin,
    starredUrl = starredUrl,
    subscriptionsUrl = subscriptionsUrl,
    twitterUsername = twitterUsername,
    type = type,
    updatedAt = updatedAt,
    url = url
)

fun List<GithubUserDto?>.toGithubUsersList() = this.map { it?.toGithubUser() }
