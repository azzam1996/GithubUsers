package com.azzam.githubusers.data.api

import com.azzam.githubusers.getUserErrorResponseBodyString
import com.azzam.githubusers.getUserSuccessfulResponseBodyString
import com.azzam.githubusers.searchUsersErrorResponseBodyString
import com.azzam.githubusers.searchUsersSuccessfulResponse
import com.azzam.githubusers.searchUsersSuccessfulResponseBodyString
import com.azzam.githubusers.user
import com.azzam.githubusers.username
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class UsersApiTest {
    @get:Rule
    val mockWebServer = MockWebServer()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService by lazy {
        retrofit.create(UsersApi::class.java)
    }



    @Test
    fun testSearchGithubUsers_SuccessfulResponse() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(searchUsersSuccessfulResponseBodyString)
        )

        val responseFromApi = apiService.searchUsers(username)

        Assert.assertEquals(
            responseFromApi.isSuccessful,
            true
        )

        assertEquals(
            searchUsersSuccessfulResponse,
            responseFromApi.body()
        )
    }

    @Test
    fun testSearchGithubUsers_ErrorResponse() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(401)
                .setBody(searchUsersErrorResponseBodyString)
        )

        val responseFromApi = apiService.searchUsers(username)

        Assert.assertEquals(
            responseFromApi.isSuccessful,
            false
        )
    }

    @Test
    fun testGetUsers_SuccessfulResponse() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(getUserSuccessfulResponseBodyString)
        )

        val responseFromApi = apiService.getUser(username)

        Assert.assertEquals(
            responseFromApi.isSuccessful,
            true
        )

        assertEquals(
            user,
            responseFromApi.body()
        )
    }

    @Test
    fun testGetUser_ErrorResponse() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody(getUserErrorResponseBodyString)
        )

        val responseFromApi = apiService.getUser("sfsgk;lksl;gk;lsdgket;wet;")

        Assert.assertEquals(
            responseFromApi.isSuccessful,
            false
        )
    }
}