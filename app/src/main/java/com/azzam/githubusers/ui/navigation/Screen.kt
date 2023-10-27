package com.azzam.githubusers.ui.navigation

sealed class Screen(val route: String) {
    data object UsersListScreen : Screen("users_list_screen")
    data object UserDetailsScreen : Screen("user_details_screen")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
