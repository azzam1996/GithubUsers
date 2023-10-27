package com.azzam.githubusers.ui.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.azzam.githubusers.CoroutinesTestRule
import com.azzam.githubusers.data.mappers.toGithubUser
import com.azzam.githubusers.data.mappers.toGithubUsersList
import com.azzam.githubusers.domain.repository.UsersRepository
import com.azzam.githubusers.searchUsersSuccessfulResponse
import com.azzam.githubusers.user
import com.azzam.githubusers.username
import com.azzam.githubusers.utils.DELAY
import com.azzam.githubusers.utils.Resource
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var usersRepository: UsersRepository

    private lateinit var usersViewModel: UsersViewModel

    @Before
    fun setup() {
        usersViewModel = UsersViewModel(usersRepository)
    }

    @Test
    fun `call searchUsers in viewModel should call searchUsers in Repository`() = runTest {
        val privateField = UsersViewModel::class.java.getDeclaredField("searchGithubUsersJob")
        privateField.isAccessible = true

        whenever(usersRepository.searchUsers(username)).thenReturn(flowOf(Resource.Success(data = searchUsersSuccessfulResponse.items?.map { it?.toGithubUser() })))
        usersViewModel.searchUsers(username)
        val searchJob = privateField.get(usersViewModel) as Job?
        searchJob?.join()

        verify(usersRepository).searchUsers(username)
    }

    @Test
    fun `call searchUsers in viewModel with keyword less than 2 characters should not call searchUsers in Repository`() = runTest {
        val privateField = UsersViewModel::class.java.getDeclaredField("searchGithubUsersJob")
        privateField.isAccessible = true

        usersViewModel.searchUsers("A")
        val searchJob = privateField.get(usersViewModel) as Job?
        searchJob?.join()

        verify(usersRepository, never()).searchUsers("A")
    }

    @Test
    fun `if searchUsers in Repository return List of GithubUsers then ViewModel State Should be Updated`() = runTest {
        val searchJob = UsersViewModel::class.java.getDeclaredField("searchGithubUsersJob")
        searchJob.isAccessible = true

        whenever(usersRepository.searchUsers(username)).thenReturn(flowOf(Resource.Success(data = searchUsersSuccessfulResponse.items?.toGithubUsersList())))
        usersViewModel.searchUsers(username)
        val job = searchJob.get(usersViewModel) as Job
        job.join()

        assertThat(usersViewModel.usersListState.value.users).isEqualTo(
            searchUsersSuccessfulResponse.items?.toGithubUsersList()
        )
    }

    @Test
    fun `if searchUsers in Repository return Error then ViewModel Should Send Error Event`() = runTest {
        val searchJob = UsersViewModel::class.java.getDeclaredField("searchGithubUsersJob")
        searchJob.isAccessible = true

        whenever(usersRepository.searchUsers(username)).thenReturn(
            flowOf(
                Resource.Error(
                    message = "Error",
                    errorCode = 404
                )
            )
        )

        usersViewModel.usersListEventFlow.test {
            usersViewModel.searchUsers(username)
            val job = searchJob.get(usersViewModel) as Job
            job.join()
            val item = awaitItem()
            if (item is UsersViewModel.UIEvent.ShowToast) {
                assertThat(item.message).isNotNull()
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `call getUser in viewModel should call getUser in Repository`() = runTest {
        whenever(usersRepository.getUser(username)).thenReturn(flowOf(Resource.Success(data = user.toGithubUser())))
        usersViewModel.getUser(username)
        verify(usersRepository).getUser(username)
    }

    @Test
    fun `if getUser in Repository return GithubUser then ViewModel State Should be Updated`() = runTest {
        whenever(usersRepository.getUser(username)).thenReturn(flowOf(Resource.Success(data = user.toGithubUser())))
        usersViewModel.getUser(username)

        runBlocking { delay(DELAY) }
        assertThat(usersViewModel.userDetailsState.value.user).isEqualTo(user.toGithubUser())
    }

    @Test
    fun `if getUser in Repository return Error then ViewModel Should Send Error Event`() = runTest {
        whenever(usersRepository.getUser(username)).thenReturn(
            flowOf(
                Resource.Error(
                    message = "Error",
                    errorCode = 404
                )
            )
        )
        usersViewModel.userDetailsEventFlow.test {
            usersViewModel.getUser(username)
            runBlocking { delay(DELAY) }
            val item = awaitItem()
            if (item is UsersViewModel.UIEvent.ShowToast) {
                assertThat(item.message).isNotNull()
            }
            cancelAndConsumeRemainingEvents()
        }
    }
}