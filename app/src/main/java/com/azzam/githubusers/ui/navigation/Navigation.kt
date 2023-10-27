package com.azzam.githubusers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.azzam.githubusers.ui.users.UsersViewModel
import com.azzam.githubusers.ui.users.userDetails.UserDetailsScreen
import com.azzam.githubusers.ui.users.usersList.UsersListScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun Navigation(
    navController: NavHostController
) {
    val usersViewModel = getViewModel<UsersViewModel>()
    NavHost(navController = navController, startDestination = Screen.UsersListScreen.route) {
        composable(Screen.UsersListScreen.route) {
            UsersListScreen(usersViewModel, navController)
        }
        composable(
            Screen.UserDetailsScreen.route + "/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                })
        ) {
            val username = it.arguments?.getString("username")
            UserDetailsScreen(username = username, viewModel = usersViewModel)
        }
    }
}