package com.azzam.githubusers.data.repository

import app.cash.turbine.test
import com.azzam.githubusers.data.api.UsersApi
import com.azzam.githubusers.data.mappers.toGithubUser
import com.azzam.githubusers.data.mappers.toGithubUsersList
import com.azzam.githubusers.domain.repository.UsersRepository
import com.azzam.githubusers.getUserErrorResponseBodyString
import com.azzam.githubusers.searchUsersErrorResponseBodyString
import com.azzam.githubusers.searchUsersSuccessfulResponse
import com.azzam.githubusers.user
import com.azzam.githubusers.username
import com.azzam.githubusers.utils.Resource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class BookRepositoryTest {

    @Mock
    lateinit var api: UsersApi

    private lateinit var usersRepository: UsersRepository

    @Before
    fun setup() {
        usersRepository = UsersRepositoryImpl(api)
    }

    @Test
    fun `call searchUsers in repository should call searchUsers in api`() = runTest {
        val job = launch {
            usersRepository.searchUsers("azzam1996").test {
                awaitItem()
                verify(api).searchUsers("azzam1996")
                cancelAndConsumeRemainingEvents()
            }
        }

        job.join()
        job.cancel()
    }

    @Test
    fun `if searchUsers api is successful then return List of GithubUsers`() = runTest {
        whenever(api.searchUsers(username)).thenReturn(
            Response.success(
                searchUsersSuccessfulResponse
            )
        )

        val job = launch {
            usersRepository.searchUsers(username).test {
                for (i in 0..1) {
                    val item = awaitItem()
                    if (item is Resource.Success) {
                        assertThat(item.data).isEqualTo(searchUsersSuccessfulResponse.items?.toGithubUsersList())
                    }
                }
                cancelAndConsumeRemainingEvents()
            }
        }

        job.join()
        job.cancel()
    }

    @Test
    fun `if searchUsers api is not successful then return Error`() = runTest {
        whenever(api.searchUsers(username)).thenReturn(
            Response.error(
                422,
                searchUsersErrorResponseBodyString.toResponseBody()
            )
        )

        val job = launch {
            usersRepository.searchUsers(username).test {
                for (i in 0..1) {
                    val item = awaitItem()
                    if (item is Resource.Error) {
                        assertThat(item.errorCode).isEqualTo(422)
                    }
                }
                cancelAndConsumeRemainingEvents()
            }
        }

        job.join()
        job.cancel()
    }

    @Test
    fun `call getUser in repository should call getUser in api`() = runTest {
        val job = launch {
            usersRepository.getUser(username).test {
                awaitItem()
                verify(api).getUser(username)
                cancelAndConsumeRemainingEvents()
            }
        }

        job.join()
        job.cancel()
    }

    @Test
    fun `if getUser api is successful then return GithubUser Object`() = runTest {
        whenever(api.getUser(username)).thenReturn(Response.success(user))

        val job = launch {
            usersRepository.getUser(username).test {
                for (i in 0..1) {
                    val item = awaitItem()
                    if (item is Resource.Success) {
                        assertThat(item.data).isEqualTo(user.toGithubUser())
                    }
                }
                cancelAndConsumeRemainingEvents()
            }
        }

        job.join()
        job.cancel()
    }

    @Test
    fun `if getUser api is not successful then return Error`() = runTest {
        whenever(api.getUser(username)).thenReturn(
            Response.error(
                404,
                getUserErrorResponseBodyString.toResponseBody()
            )
        )

        val job = launch {
            usersRepository.getUser(username).test {
                for (i in 0..1) {
                    val item = awaitItem()
                    if (item is Resource.Error) {
                        assertThat(item.errorCode).isEqualTo(404)
                    }
                }
                cancelAndConsumeRemainingEvents()
            }
        }

        job.join()
        job.cancel()
    }
}