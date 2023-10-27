package com.azzam.githubusers.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.azzam.githubusers.R

@Composable
fun getValueOrNoData(value: String?): String{
    return value ?: stringResource(id = R.string.no_data)
}