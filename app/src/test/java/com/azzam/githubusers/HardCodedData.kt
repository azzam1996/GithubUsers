package com.azzam.githubusers

import com.azzam.githubusers.data.api.dto.GithubUserDto
import com.azzam.githubusers.data.api.dto.SearchGithubUsersDto


const val username = "azzam1996"
val user = GithubUserDto(
    avatarUrl = "https://avatars.githubusercontent.com/u/59395628?v=4",
    bio = "Software Engineer with  +5 years of experience in Android Development.\r\nStrong Engineering skills with master's degree focused on algorithms.",
    blog = "",
    company = null,
    createdAt = "2019-12-31T15:32:46Z",
    email = null,
    eventsUrl = "https://api.github.com/users/azzam1996/events{/privacy}",
    followers = 0,
    followersUrl = "https://api.github.com/users/azzam1996/followers",
    following = 0,
    followingUrl = "https://api.github.com/users/azzam1996/following{/other_user}",
    gistsUrl = "https://api.github.com/users/azzam1996/gists{/gist_id}",
    gravatarId = "",
    hireable = null,
    htmlUrl = "https://github.com/azzam1996",
    id = 59395628,
    location = null,
    login = "azzam1996",
    name = "Azzam Habib",
    nodeId = "MDQ6VXNlcjU5Mzk1NjI4",
    organizationsUrl = "https://api.github.com/users/azzam1996/orgs",
    publicGists = 0,
    publicRepos = 9,
    receivedEventsUrl = "https://api.github.com/users/azzam1996/received_events",
    reposUrl = "https://api.github.com/users/azzam1996/repos",
    siteAdmin = false,
    starredUrl = "https://api.github.com/users/azzam1996/starred{/owner}{/repo}",
    subscriptionsUrl = "https://api.github.com/users/azzam1996/subscriptions",
    twitterUsername = null,
    type = "User",
    updatedAt = "2023-10-01T08:24:29Z",
    url = "https://api.github.com/users/azzam1996"
)
val users = listOf(
    user
)
val searchUsersSuccessfulResponse = SearchGithubUsersDto(items = users, incompleteResults = false, totalCount = 1)

const val searchUsersErrorResponseBodyString = "{\"message\":\"ValidationFailed\",\"errors\":[{\"resource\":\"Search\",\"field\":\"q\",\"code\":\"missing\"}],\"documentation_url\":\"https://docs.github.com/v3/search\"}"
const val searchUsersSuccessfulResponseBodyString = "{\n" +
        "  \"total_count\": 1,\n" +
        "  \"incomplete_results\": false,\n" +
        "  \"items\": [\n" +
        "    {\n" +
        "      \"login\": \"azzam1996\",\n" +
        "      \"id\": 59395628,\n" +
        "      \"node_id\": \"MDQ6VXNlcjU5Mzk1NjI4\",\n" +
        "      \"avatar_url\": \"https://avatars.githubusercontent.com/u/59395628?v=4\",\n" +
        "      \"gravatar_id\": \"\",\n" +
        "      \"url\": \"https://api.github.com/users/azzam1996\",\n" +
        "      \"html_url\": \"https://github.com/azzam1996\",\n" +
        "      \"followers_url\": \"https://api.github.com/users/azzam1996/followers\",\n" +
        "      \"following_url\": \"https://api.github.com/users/azzam1996/following{/other_user}\",\n" +
        "      \"gists_url\": \"https://api.github.com/users/azzam1996/gists{/gist_id}\",\n" +
        "      \"starred_url\": \"https://api.github.com/users/azzam1996/starred{/owner}{/repo}\",\n" +
        "      \"subscriptions_url\": \"https://api.github.com/users/azzam1996/subscriptions\",\n" +
        "      \"organizations_url\": \"https://api.github.com/users/azzam1996/orgs\",\n" +
        "      \"repos_url\": \"https://api.github.com/users/azzam1996/repos\",\n" +
        "      \"events_url\": \"https://api.github.com/users/azzam1996/events{/privacy}\",\n" +
        "      \"received_events_url\": \"https://api.github.com/users/azzam1996/received_events\",\n" +
        "      \"type\": \"User\",\n" +
        "      \"site_admin\": false,\n" +
        "      \"name\": \"Azzam Habib\",\n" +
        "      \"company\": null,\n" +
        "      \"blog\": \"\",\n" +
        "      \"location\": null,\n" +
        "      \"email\": null,\n" +
        "      \"hireable\": null,\n" +
        "      \"bio\": \"Software Engineer with  +5 years of experience in Android Development.\\r\\nStrong Engineering skills with master's degree focused on algorithms.\",\n" +
        "      \"twitter_username\": null,\n" +
        "      \"public_repos\": 9,\n" +
        "      \"public_gists\": 0,\n" +
        "      \"followers\": 0,\n" +
        "      \"following\": 0,\n" +
        "      \"created_at\": \"2019-12-31T15:32:46Z\",\n" +
        "      \"updated_at\": \"2023-10-01T08:24:29Z\"\n" +
        "    }\n" +
        "  ]\n" +
        "}"


const val getUserErrorResponseBodyString = "{\"message\":\"NotFound\",\"documentation_url\":\"https://docs.github.com/rest/users/users#get-a-user\"}"
const val getUserSuccessfulResponseBodyString = "{\n" +
        "  \"login\": \"azzam1996\",\n" +
        "  \"id\": 59395628,\n" +
        "  \"node_id\": \"MDQ6VXNlcjU5Mzk1NjI4\",\n" +
        "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/59395628?v=4\",\n" +
        "  \"gravatar_id\": \"\",\n" +
        "  \"url\": \"https://api.github.com/users/azzam1996\",\n" +
        "  \"html_url\": \"https://github.com/azzam1996\",\n" +
        "  \"followers_url\": \"https://api.github.com/users/azzam1996/followers\",\n" +
        "  \"following_url\": \"https://api.github.com/users/azzam1996/following{/other_user}\",\n" +
        "  \"gists_url\": \"https://api.github.com/users/azzam1996/gists{/gist_id}\",\n" +
        "  \"starred_url\": \"https://api.github.com/users/azzam1996/starred{/owner}{/repo}\",\n" +
        "  \"subscriptions_url\": \"https://api.github.com/users/azzam1996/subscriptions\",\n" +
        "  \"organizations_url\": \"https://api.github.com/users/azzam1996/orgs\",\n" +
        "  \"repos_url\": \"https://api.github.com/users/azzam1996/repos\",\n" +
        "  \"events_url\": \"https://api.github.com/users/azzam1996/events{/privacy}\",\n" +
        "  \"received_events_url\": \"https://api.github.com/users/azzam1996/received_events\",\n" +
        "  \"type\": \"User\",\n" +
        "  \"site_admin\": false,\n" +
        "  \"name\": \"Azzam Habib\",\n" +
        "  \"company\": null,\n" +
        "  \"blog\": \"\",\n" +
        "  \"location\": null,\n" +
        "  \"email\": null,\n" +
        "  \"hireable\": null,\n" +
        "  \"bio\": \"Software Engineer with  +5 years of experience in Android Development.\\r\\nStrong Engineering skills with master's degree focused on algorithms.\",\n" +
        "  \"twitter_username\": null,\n" +
        "  \"public_repos\": 9,\n" +
        "  \"public_gists\": 0,\n" +
        "  \"followers\": 0,\n" +
        "  \"following\": 0,\n" +
        "  \"created_at\": \"2019-12-31T15:32:46Z\",\n" +
        "  \"updated_at\": \"2023-10-01T08:24:29Z\"\n" +
        "}"