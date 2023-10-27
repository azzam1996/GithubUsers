package com.azzam.githubusers.ui.users.usersList

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.azzam.githubusers.R
import com.azzam.githubusers.ui.navigation.Screen
import com.azzam.githubusers.ui.users.UsersViewModel
import com.azzam.githubusers.ui.users.widgets.GithubUserListItem
import com.azzam.githubusers.ui.users.widgets.SearchBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UsersListScreen(
    viewModel: UsersViewModel,
    navController: NavController
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.usersListEventFlow.collectLatest { event ->
            when (event) {
                is UsersViewModel.UIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (searchBar, usersList, progressIndicator, tvNoData) = createRefs()

        SearchBar(
            searchUsers = viewModel::searchUsers,
            searchKeyword = viewModel.searchKeyword.value,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(searchBar) {
                    top.linkTo(parent.top)
                }
        )

        when (viewModel.usersListState.value.isLoading) {
            true -> {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(progressIndicator) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
            }

            else -> {
                when (viewModel.usersListState.value.users.isNullOrEmpty()) {
                    true -> {
                        Text(
                            text = stringResource(id = R.string.no_data),
                            color = Color.White,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .constrainAs(tvNoData) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .constrainAs(usersList) {
                                    top.linkTo(searchBar.bottom, 20.dp)
                                    bottom.linkTo(parent.bottom)
                                    height = Dimension.fillToConstraints
                                }
                        ) {
                            viewModel.usersListState.value.users?.let { users ->
                                itemsIndexed(users) { _, value ->
                                    GithubUserListItem(githubUser = value) { user ->
                                        navController.navigate(
                                            Screen.UserDetailsScreen.withArgs(
                                                user?.login
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}