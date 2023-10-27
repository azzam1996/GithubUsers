package com.azzam.githubusers.ui.users.userDetails

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.azzam.githubusers.ui.users.UsersViewModel
import com.azzam.githubusers.ui.users.widgets.Avatar
import com.azzam.githubusers.ui.users.widgets.LabelValueItem
import com.azzam.githubusers.utils.getValueOrNoData
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserDetailsScreen(
    username: String?,
    viewModel: UsersViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.userDetailsEventFlow.collectLatest { event ->
            when (event) {
                is UsersViewModel.UIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getUser(username = username)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (progressIndicator, avatar, name, id, clBio, details) = createRefs()

        when (viewModel.userDetailsState.value.loading) {
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
                Avatar(
                    url = viewModel.userDetailsState.value.user?.avatarUrl,
                    size = 150.dp,
                    modifier = Modifier
                        .constrainAs(avatar) {
                            top.linkTo(parent.top, 30.dp)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = getValueOrNoData(value = viewModel.userDetailsState.value.user?.name),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(name) {
                            top.linkTo(avatar.bottom, 20.dp)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = getValueOrNoData(value = viewModel.userDetailsState.value.user?.id?.toString()),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(id) {
                            top.linkTo(name.bottom, 10.dp)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                        }
                )

                ConstraintLayout(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(10.dp)
                        .constrainAs(clBio) {
                            top.linkTo(id.bottom, 20.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                ) {
                    val (tvBioLabel, tvBio) = createRefs()

                    Text(
                        text = "Bio:",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .constrainAs(tvBioLabel) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                            }
                    )

                    Text(
                        text = getValueOrNoData(value = viewModel.userDetailsState.value.user?.bio),
                        color = Color.White,
                        modifier = Modifier
                            .constrainAs(tvBio) {
                                top.linkTo(tvBioLabel.bottom, 10.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                width = Dimension.fillToConstraints
                            }
                    )
                }

                Column(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(10.dp)
                        .constrainAs(details) {
                            top.linkTo(clBio.bottom, 10.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.fillToConstraints
                        }
                ) {
                    Text(
                        text = "Details:",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    LabelValueItem(
                        label = "Email",
                        value = viewModel.userDetailsState.value.user?.email
                    )
                    LabelValueItem(
                        label = "Company",
                        value = viewModel.userDetailsState.value.user?.company
                    )
                    LabelValueItem(
                        label = "Followers",
                        value = viewModel.userDetailsState.value.user?.followers.toString()
                    )
                    LabelValueItem(
                        label = "Following",
                        value = viewModel.userDetailsState.value.user?.following.toString()
                    )
                    LabelValueItem(
                        label = "Public Repos",
                        value = viewModel.userDetailsState.value.user?.publicRepos.toString()
                    )
                    LabelValueItem(
                        label = "Public Gists",
                        value = viewModel.userDetailsState.value.user?.publicGists.toString()
                    )
                    LabelValueItem(
                        label = "Location",
                        value = viewModel.userDetailsState.value.user?.location
                    )
                    LabelValueItem(
                        label = "Twitter",
                        value = viewModel.userDetailsState.value.user?.twitterUsername
                    )
                }
            }
        }
    }
}