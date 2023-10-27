package com.azzam.githubusers.ui.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azzam.githubusers.domain.repository.UsersRepository
import com.azzam.githubusers.ui.users.userDetails.UserDetailsState
import com.azzam.githubusers.ui.users.usersList.UsersListState
import com.azzam.githubusers.utils.DELAY
import com.azzam.githubusers.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class UsersViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    private val _searchKeyword = mutableStateOf("")
    val searchKeyword: State<String> = _searchKeyword

    private val _usersListState = mutableStateOf(UsersListState())
    val usersListState: State<UsersListState> = _usersListState

    private var searchGithubUsersJob: Job? = null

    private val _usersListEventFlow = MutableSharedFlow<UIEvent>()
    val usersListEventFlow = _usersListEventFlow.asSharedFlow()

    private val _userDetailsEventFlow = MutableSharedFlow<UIEvent>()
    val userDetailsEventFlow = _userDetailsEventFlow.asSharedFlow()

    private val _userDetailsState = mutableStateOf(UserDetailsState())
    val userDetailsState: State<UserDetailsState> = _userDetailsState

    fun searchUsers(keyword: String) {
        _searchKeyword.value = keyword
        searchGithubUsersJob?.cancel()
        if (keyword.length >= 2) {
            searchGithubUsersJob = viewModelScope.launch(Dispatchers.IO) {
                delay(DELAY)
                usersRepository.searchUsers(keyword = keyword).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            withContext(Dispatchers.Main) {
                                _usersListState.value = _usersListState.value.copy(
                                    users = result.data,
                                    isLoading = false
                                )
                            }
                        }

                        is Resource.Error -> {
                            withContext(Dispatchers.Main) {
                                Timber.v(" Error ${result.errorCode} , ${result.message}")
                                _usersListState.value = _usersListState.value.copy(
                                    isLoading = false
                                )
                                _usersListEventFlow.emit(UIEvent.ShowToast("${result.errorCode}\n ${result.message}"))

                            }
                        }

                        is Resource.Loading -> {
                            withContext(Dispatchers.Main) {
                                _usersListState.value = _usersListState.value.copy(
                                    isLoading = true
                                )
                            }
                        }
                    }
                }.launchIn(this)
            }
        }
    }

    fun getUser(username: String?){
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.getUser(username).onEach { result ->
                when(result){
                    is Resource.Success -> {
                        withContext(Dispatchers.Main){
                            _userDetailsState.value = _userDetailsState.value.copy(
                                loading = false,
                                user = result.data
                            )
                        }
                    }
                    is Resource.Error -> {
                        withContext(Dispatchers.Main){
                            _userDetailsState.value = _userDetailsState.value.copy(
                                loading = false
                            )
                            _userDetailsEventFlow.emit(UIEvent.ShowToast("${result.errorCode}\n ${result.message}"))
                        }
                    }
                    is Resource.Loading -> {
                        _userDetailsState.value = _userDetailsState.value.copy(
                            loading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }


    sealed class UIEvent {
        data class ShowToast(val message: String) : UIEvent()
    }
}