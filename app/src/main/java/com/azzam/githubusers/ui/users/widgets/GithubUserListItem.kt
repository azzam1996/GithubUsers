package com.azzam.githubusers.ui.users.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.azzam.githubusers.domain.model.GithubUser
import com.azzam.githubusers.ui.theme.Valentino
import com.azzam.githubusers.ui.theme.VividViolet
import com.azzam.githubusers.utils.getValueOrNoData

@Composable
fun GithubUserListItem(
    githubUser: GithubUser?,
    modifier: Modifier = Modifier,
    onClick: (GithubUser?) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
            .clickable {
                onClick.invoke(githubUser)
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Valentino, VividViolet),
                        start = Offset(0f, Float.POSITIVE_INFINITY),
                        end = Offset(Float.POSITIVE_INFINITY, 0f)
                    )
                )
                .padding(all = 10.dp)
        ) {
            val (avatar, clTexts) = createRefs()

            Avatar(
                url = githubUser?.avatarUrl,
                size = 90.dp,
                modifier = Modifier
                    .constrainAs(avatar) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

            ConstraintLayout(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .constrainAs(clTexts) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(avatar.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
            ) {
                val (tvName, tvAuthor) = createRefs()

                Text(
                    text = getValueOrNoData(value = githubUser?.login),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(tvName) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            width = Dimension.fillToConstraints
                        }
                )
                Text(
                    text = githubUser?.id.toString(),
                    color = Color.White,
                    modifier = Modifier
                        .constrainAs(tvAuthor) {
                            top.linkTo(tvName.bottom)
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            width = Dimension.fillToConstraints
                        }
                )
            }
        }
    }
}